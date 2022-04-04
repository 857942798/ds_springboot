package com.ds.cli.server;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.net.InetAddress;  
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class CLIEnv implements Runnable, CLIReply
{
	public static final int type_version_request = 0x0001;
	public static final int type_version_response = 0x8001;
	public static final int type_login_request = 0x0002;
	public static final int type_login_response = 0x8002;
	public static final int type_cmdtree_request = 0x0003;
	public static final int type_cmdtree_response = 0x8003;
	public static final int type_prompt_request = 0x0004;
	public static final int type_prompt_response = 0x8004;
	public static final int type_execute_request = 0x0005;
	public static final int type_execute_response = 0x8005;
	public static final int type_continue_request = 0x0006;
	public static final int type_continue_response = 0x8006;
	public static final int type_trap_register_request = 0x0007;
	public static final int type_trap_register_response = 0x8007;
	public static final int type_trap_explode = 0xc007;
	public static final int type_echo_request = 0x00ff;
	public static final int type_echo_response = 0x80ff;
	public static final int type_arginfo_request = 0x0008;
	public static final int type_arginfo_response = 0x8008;
	public static final int type_file_request = 0x0009;
	public static final int type_file_response = 0x8009;
	public static final int status_ok = 0x0000;
	public static final int status_error = 0x0001;
	public static final int status_prompt_changed = 0x0002;
	public static final int status_execute_continue = 0x0004;
	public static final int status_cmdtree_continue = 0x0004;
	public static final int status_exit = 0x8000;

	private Socket client;
	private DataInputStream in;
	private DataOutputStream out;
	private CLIServer server_ref;

	private String prompt = "\u0001>";
	private LinkedList<StringBuffer> reply_list;

	/**
	* create environment of cli-client
	*
	* @param sock
	*        The server's socket.
	* @param server
	*        The server object.
	*/
	public CLIEnv(Socket sock, CLIServer server)
	{
		client = sock;
		try
		{
			in = new DataInputStream(client.getInputStream());
			out = new DataOutputStream(client.getOutputStream());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		server_ref = server;
		reply_list = new LinkedList<StringBuffer>();
	}

	/**
	* add result string to reply cli-client
	*
	* @param result
	*        The result string.
	*/
	public void Add(String result)
	{
		StringBuffer str;

		if(reply_list.size() == 0)
		{
			str = new StringBuffer();
			reply_list.add(str);
		}
		else
		{
			str = reply_list.getLast();
		}
		str.append(result);
		str.append('\n');
	}

	/**
	* add new page for result string
	*
	*/
	public void NewPage()
	{
		reply_list.add(new StringBuffer());
	}

	public void run()
	{
		while(true)
		{
			try
			{
				int[] head = ReadHead();
				byte[] ts = null;
				byte[] body = null;
				int len = 0;
				boolean ok = (head[2] & 0x0001) == 0 ? true : false;
				boolean cont = ((head[2] >>> 2) & 0x0001) == 0 ? false : true;

				if(head[3] > 0)
				{
					body = new byte[head[3]];
					len = in.read(body, 0, head[3]);
					if(len <= 0)
					{
						System.out.println("client exit");
						break;
					}
				}

				switch(head[0])
				{
				case type_version_request:
					Response(type_version_response, head[1], status_ok, "v3.1");
					break;
				case type_login_request:
					Response(type_login_response, head[1], status_ok, null);
					break;
				case type_cmdtree_request:
					OnCmdtreeRequest(head[1]);
					break;
				case type_prompt_request:
					Response(type_prompt_response, head[1], status_ok, prompt);
					break;
				case type_execute_request:
					OnExecuteRequest(head[1], body);
					break;
				case type_continue_request:
					OnContinueRequest(head[1]);
					break;
				case type_echo_request:
					Response(type_echo_response, head[1], status_ok, null);
					break;
				case type_arginfo_request:
					OnArginfoRequest(head[1], body);
					break;
				case type_file_request:
					OnFileRequest(head[1], body);
					break;
				}
			}
			catch (IOException e)
			{
				System.out.println("client " + client.getRemoteSocketAddress().toString() + " exit");
				break;
			}
		}
		try
		{
			client.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private int[] ReadHead() throws IOException
	{
		byte[] h = new byte[8];	
		int[] head = new int[4];

		if(in.read(h, 0, 8) < 0)
		{
			throw new IOException();
		}
		head[0] = ((int)h[1]) << 8 | (int)h[0];
		head[1] = ((int)h[3]) << 8 | (int)h[2];
		head[2] = ((int)h[5]) << 8 | (int)h[4];
		head[3] = ((int)h[7]) << 8 | (int)h[6];
		
		/* print head data for debug
		System.out.println("type:   " + Long.toHexString(Integer.toUnsignedLong(head[0])));
		System.out.println("cmd:    " + head[1]);
		System.out.println("status: " + head[2]);
		System.out.println("length: " + head[3]);
		*/
		return head;
	}

	private static byte[] MakeHead(int type, int cmd, int status, int length)
	{
		byte h[] = new byte[8];
		h[0] = (byte)(type & 0xff);
		h[1] = (byte)(type >>> 8 & 0xff);
		h[2] = (byte)(cmd & 0xff);
		h[3] = (byte)(cmd >>> 8 & 0xff);
		h[4] = (byte)(status & 0xff);
		h[5] = (byte)(status >>> 8 & 0xff);
		h[6] = (byte)(length & 0xff);
		h[7] = (byte)(length >>> 8 & 0xff);
		return h;
	}

	private void Response(int type, int cmd, int status, String body)
	{
		byte h[] = new byte[8];
		h[0] = (byte)(type & 0xff);
		h[1] = (byte)(type >>> 8 & 0xff);
		h[2] = (byte)(cmd & 0xff);
		h[3] = (byte)(cmd >>> 8 & 0xff);
		h[4] = (byte)(status & 0xff);
		h[5] = (byte)(status >>> 8 & 0xff);
		try
		{
			if(body == null)
			{
				h[6] = 0;
				h[7] = 0;
				out.write(h);
			}
			else
			{
				h[6] = (byte)((body.length()+1) & 0xff);
				h[7] = (byte)((body.length()+1) >>> 8 & 0xff);

				out.write(h);
				out.write(body.getBytes("UTF-8"));
				out.write('\0');
			}
			out.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void OnCmdtreeRequest(int cmd)
	{
		Iterator<String> iter = server_ref.GetCmdtreeIter();
		try
		{
			while(iter.hasNext())
			{
				String body = iter.next();

				/*
				System.out.println("send cmdtree: " + body);
				*/
				out.write(MakeHead(type_cmdtree_response, cmd, status_ok|status_cmdtree_continue, body.length()+1));
				out.write(body.getBytes("UTF-8"));
				out.write('\0');
			}
			out.write(MakeHead(type_cmdtree_response, cmd, status_ok, 0));
			out.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void OnExecuteRequest(int cmd, byte[] body)
	{
		StringBuffer err_str = new StringBuffer();
		StringBuffer arg_str = new StringBuffer();
		CLITreeNode node = null;

		try
		{
			node = server_ref.FetchCommand(new String(body, 0, body.length-1, "UTF-8"), arg_str, err_str);
		}
		catch (UnsupportedEncodingException e)
		{
			err_str.append("unsupported encoding");
		}

		if(node != null)
		{
			List<CLIArg> arg_list = new ArrayList<CLIArg>(node.childs.size());
			Iterator<CLITreeNode> iter = node.childs.iterator();

			// get parameter list
			while(iter.hasNext())
			{
				CLITreeNode pnode = iter.next();
				if(pnode.cmd_type == 1 && pnode.arg != null)
				{
					switch(pnode.arg.arg_type)
					{
					case CLIArg.arg_type_integer:
						arg_list.add(new CLIArgInt((CLIArgInt)pnode.arg));
						break;
					case CLIArg.arg_type_string:
						arg_list.add(new CLIArgStr((CLIArgStr)pnode.arg));
						break;
					case CLIArg.arg_type_enum:
						arg_list.add(new CLIArgEnum((CLIArgEnum)pnode.arg));
						break;
					}
				}
			}

			// get value of parameter list
			String token[] = arg_str.toString().split("\\s");
			if(token.length < arg_list.size() &&
				!arg_list.get(token.length-1).IsOptional())
			{
				err_str.append("missing parameter");
			}
			else
			{
				int arg_num = arg_str.length() == 0 ? 0 : token.length;
				int idx;
				for(idx=0;idx<arg_num;idx++)
				{
					CLIArg arg = arg_list.get(idx);
					if(!arg.Validate(token[idx]))
					{
						err_str.append(new String("invalid parameter " +
										Integer.toString(idx+1) + ": " + token[idx]));
						break;
					}
					if(!arg.SetValue(token[idx]))
					{
						err_str.append(new String("failed to decode parameter " +
										Integer.toString(idx+1) + ": " + token[idx]));
						break;
					}
				}

				// parse parameters done, then execute command
				if(idx == arg_num)
				{
					reply_list.clear();
					CLIArg[] cli_args = null;

					if(arg_num > 0)
					{
						cli_args = (CLIArg[])arg_list.toArray(new CLIArg[arg_num]);
					}
					if(node.proc.Execute(this, cli_args))
					{
						StringBuffer str_buf = reply_list.size() == 0 ? null : reply_list.removeFirst();
						String str = str_buf != null ? str_buf.toString() : new String("command ok without response");
						int status = status_ok;

						if(reply_list.size() > 0)
						{
							status |= status_execute_continue;
						}
						try
						{
							out.write(MakeHead(type_execute_response, cmd, status, str.length()+1));
							out.write(str.getBytes("UTF-8"));
							out.write('\0');
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
						return;
					}
					else
					{
						err_str.append("execute command failed");
					}
				}
			}
		}
		try
		{
			out.write(MakeHead(type_execute_response, cmd, status_error, err_str.length()+1));
			out.write(err_str.toString().getBytes("UTF-8"));
			out.write('\0');
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void OnContinueRequest(int cmd)
	{
		StringBuffer str_buf = reply_list.removeFirst();
		String str = str_buf != null ? str_buf.toString() : new String("no more response");
		int status = status_ok;

		if(reply_list.size() > 0)
		{
			status |= status_execute_continue;
		}
		try
		{
			out.write(MakeHead(type_continue_response, cmd, status, str.length()+1));
			out.write(str.getBytes("UTF-8"));
			out.write('\0');
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void OnArginfoRequest(int cmd, byte[] body)
	{
		StringBuffer err_str = new StringBuffer();
		String aid_str = null;

		try
		{
			aid_str = new String(body, 0, body.length-1, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			err_str.append("unsupported encoding");
		}

		if(aid_str != null)
		{
			int aid;
			
			try
			{
				aid = Integer.parseInt(aid_str.toString());
			}
			catch (NumberFormatException e)
			{
				aid = -1;
			}
			
			CLIArg arg = CLIArg.GetArg(aid);
			if(arg == null)
			{
				err_str.append(new String("not found arg-id ") + aid_str);
			}
			else
			{
				String str = arg.InfoResponse();
				try
				{
					out.write(MakeHead(type_arginfo_response, cmd, status_ok, str.length()+1));
					out.write(str.getBytes("UTF-8"));
					out.write('\0');
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				return;
			}
		}
		try
		{
			out.write(MakeHead(type_arginfo_response, cmd, status_error, err_str.length()+1));
			out.write(err_str.toString().getBytes("UTF-8"));
			out.write('\0');
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void OnFileRequest(int cmd, byte[] body)
	{
		String cmd_str;
		try

		{
			cmd_str = new String(body, 0, body.length-1, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			Response(type_file_response, cmd, status_error, "unsupported encoding");
			return;
		}
		
		String token[] = cmd_str.split("\\|");
		CharSequence cs = "..";
		int port = server_ref.cmd_port + 1;
		long file_size = 0;
		String file_name;

		if(token[0].contains(cs))
		{
			Response(type_file_response, cmd, status_error, "request refused");
			return;
		}
		file_name = new String(server_ref.ftr_root + "/" + token[0]);
		if(token.length > 1)
		{
			try
			{
				port = Integer.parseInt(token[1]);
			}
			catch (NumberFormatException e)
			{
				Response(type_file_response, cmd, status_error, "invalid port");
				return;
			}
		}
		if(token.length > 2)
		{
			try
			{
				file_size = Long.parseLong(token[2]);
			}
			catch (NumberFormatException e)
			{
				Response(type_file_response, cmd, status_error, "invalid file size");
				return;
			}
		}

		CLIFTRServer ftr_server = new CLIFTRServer();
		StringBuffer err_str = new StringBuffer();
		if(cmd == 0)
		{
			if(ftr_server.StartUpload(server_ref.cmd_host, port, file_name, file_size, err_str))
			{
				Response(type_file_response, cmd, status_ok, new String(cmd_str + "|ok"));
			}
			else
			{
				Response(type_file_response, cmd, status_ok, new String(cmd_str + "|" + err_str.toString()));
			}
		}
		else
		{
			if(ftr_server.StartDownload(server_ref.cmd_host, port, file_name, err_str))
			{
				Response(type_file_response, cmd, status_ok, new String(cmd_str + "|ok"));
			}
			else
			{
				Response(type_file_response, cmd, status_ok, new String(cmd_str + "|" + err_str.toString()));
			}
		}
	}
}

