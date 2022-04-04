package com.ds.cli.server;

import java.io.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.net.InetAddress;  
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import javax.net.SocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

public class CLIServer implements Runnable
{
	private ServerSocket server;
	private List<String> cmd_list;
	private CLITreeNode cmd_tree;
	
	public String cmd_host;
	public int cmd_port;
	public String ftr_root = ".";

	/**
	* create cli-server with host & port
	*
	* @param host
	*        The server's host name or ip address.
	* @param port
	*        The server's port.
	* @throws UnknownHostException
	*         If param 'host' is unknow.
	*/
	public CLIServer(String host, int port) throws UnknownHostException
	{
		try
		{
			server = new ServerSocket(port, 5, InetAddress.getByName(host));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		cmd_list = new LinkedList<String>();
		cmd_tree = new CLITreeNode("root", "root node", null);
		cmd_host = new String(host);
		cmd_port = port;
	}

	/**
	* create cli-server with host & port
	*
	* @param host
	*        The server's host name or ip address.
	* @param port
	*        The server's port.
	* @param ssl
	*        Use SSL or not.
	* @throws UnknownHostException
	*         If param 'host' is unknow.
	*/
	public CLIServer(String host, int port, boolean ssl) throws UnknownHostException
	{
		try
		{
			if(!ssl)
			{
				server = new ServerSocket(port, 5, InetAddress.getByName(host));
			}
			else
			{
				SSLServerSocketFactory sf = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
				SSLServerSocket server_ssl = (SSLServerSocket)sf.createServerSocket(port, 5, InetAddress.getByName(host));
				server_ssl.setNeedClientAuth(false);
				server = server_ssl;
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		cmd_list = new LinkedList<String>();
		cmd_tree = new CLITreeNode("root", "root node", null);
		cmd_host = new String(host);
		cmd_port = port;
	}

	/**
	* process thread of cli-server
	*
	*/
	public void run()
	{
		while(true)
		{
			try
			{
				Socket client = server.accept();
				new Thread(new CLIEnv(client, this)).start();
			}
			catch (IOException e)
			{
				e.printStackTrace();
				break;
			}
		}
	}

	/**
	* set root path of file transfer
	*
	* @param path
	*        The root path to set.
	*/
	public void SetFTRRoot(String path)
	{
		ftr_root = new String(path);
	}

	/**
	* add node of command tree
	*
	* @param cmd_key
	*        The node name.
	* @param cmd_desc
	*        The node description.
	*/
	public boolean AddNode(String cmd_key, String cmd_desc)
	{
		return AddCommand(cmd_key, cmd_desc, null, null);
	}
	
	/**
	* add command of command tree
	*
	* @param cmd_key
	*        The command node name.
	* @param cmd_desc
	*        The command node description.
	* @param args
	*        The argument list of command.
	* @param proc
	*        The process interface of command.
	*/
	public boolean AddCommand(String cmd_key, String cmd_desc, CLIArg args[], CLIProcess proc)
	{
		String token[] = cmd_key.split("\\s");
		CLITreeNode parent_node = cmd_tree;
		int idx;

		// check paremeter
		if(token.length == 0)
		{
			System.out.println("invalid command: " + cmd_key);
			return false;
		}

		// get parent node
		for(idx=0;idx<token.length-1;idx++)
		{
			CLITreeNode node = parent_node.GetChild(token[idx]);
			if(node == null || parent_node.cmd_type == 1)
			{
				System.out.println("missing parent node for command: " + cmd_key);
				return false;
			}
			parent_node = node;
		}

		// check existing
		if(parent_node.GetChild(token[idx]) != null)
		{
			System.out.println("duplicate command: " + cmd_key);
			return false;
		}

		// create command node & insert into cmd_tree
		CLITreeNode new_node = new CLITreeNode(token[idx], cmd_desc, proc);
		parent_node.AddChild(new_node);

		// create parameter nodes & insert into cmd_tree
		if(args != null)
		{
			for(idx=0;idx<args.length;idx++)
			{
				new_node.AddChild(new CLITreeNode(args[idx]));
			}
		}

		// insert into cmd_list
		String cmd_type = new String(proc != null ? "|C|" : "|N|");
		cmd_list.add(new String(cmd_key + "|" + cmd_desc + cmd_type + "4294967295|"));
		if(args != null)
		{
			String arg_key;
			
			for(idx=0;idx<args.length;idx++)
			{
				arg_key = new String(cmd_key + " " + args[idx].arg_name);
				cmd_list.add(new String(arg_key + "|" + args[idx].arg_desc +
										"|P|4294967295|" + args[idx].arg_extra));
				cmd_key = arg_key;
			}
		}

		return true;
	}

	/**
	* match command node in command tree
	*
	* @param cmd_key
	*        The command node name to match.
	*/
	public CLITreeNode FetchCommand(String cmd_str, StringBuffer arg_str, StringBuffer err_str)
	{
		String token[] = cmd_str.split("\\s");
		CLITreeNode node = cmd_tree;
		int idx;

		// check command string length
		if(token.length == 0)
		{
			err_str.append("invalid command string");
			return null;
		}

		// get last key of command
		for(idx=0;idx<token.length;idx++)
		{
			CLITreeNode child = node.GetChild(token[idx]);
			if(child != null && child.cmd_type == 0)
			{
				node = child;
				continue;
			}
			break;
		}

		// check command process function
		if(node.proc != null)
		{
			for(;idx<token.length;idx++)
			{
				arg_str.append(token[idx]);
				arg_str.append(' ');
			}
			return node;
		}
		err_str.append("not match command");
		return null;
	}

	/**
	* get iterator of command tree
	*
	*/
	public Iterator<String> GetCmdtreeIter()
	{
		return cmd_list.iterator();
	}
}

