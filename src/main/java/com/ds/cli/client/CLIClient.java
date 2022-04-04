package com.ds.cli.client;

import java.io.*;
import java.net.InetAddress;  
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class CLIClient
{
	public String server_name;
	public int server_port;
	public Socket socket;
	public boolean secure;
	
	private DataInputStream in;
	private DataOutputStream out;
	private String err;

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

	private int[] ReadHead() throws IOException
	{
		byte[] h = new byte[8];	
		int[] head = new int[4];

		in.readFully(h, 0, 8);
		head[0] = (Byte.toUnsignedInt(h[1]) << 8) | Byte.toUnsignedInt(h[0]);
		head[1] = (Byte.toUnsignedInt(h[3]) << 8) | Byte.toUnsignedInt(h[2]);
		head[2] = (Byte.toUnsignedInt(h[5]) << 8) | Byte.toUnsignedInt(h[4]);
		head[3] = (Byte.toUnsignedInt(h[7]) << 8) | Byte.toUnsignedInt(h[6]);
		
		/* print head data for debug
		System.out.println("type:   " + Long.toHexString(Integer.toUnsignedLong(head[0])));
		System.out.println("cmd:    " + head[1]);
		System.out.println("status: " + head[2]);
		System.out.println("length: " + head[3]);
		*/
		return head;
	}

	private boolean CheckVersion()
	{
		try
		{
			out.write(MakeHead(1,0,0,0));
			out.flush();
			
			int[] head = ReadHead();
			if((head[2] & 0x0001) == 0)
			{
				byte[] body = new byte[64];
				in.readFully(body, 0, head[3]);
				if(body[1] >= 3)
				{
					return true;
				}
				err = new String("check version error: server version is too low");
			}
			else
			{
				err = new String("check version error: failed to query version");
			}
		}
		catch (IOException e)
		{
			err = new String("check version error: " + e.getMessage());
		}

		return false;
	}

	private boolean UserLogin(String user, String pwd)
	{
		String body = new String(user + "|" + pwd);

		try
		{
			out.write(MakeHead(2,0,0,body.length()));
			out.write(body.getBytes("UTF-8"));
			out.flush();
			
			int[] head = ReadHead();
			if((head[2] & 0x0001) == 0)
			{
				return true;
			}

			byte[] resp = new byte[256];
			in.readFully(resp, 0, head[3]);
			err = new String(resp);
		}
		catch (IOException e)
		{
			err = new String("user login error: " + e.getMessage());
		}
		return false;
	}

	/**
	* Create CLI client object without SSL
	*
	* @param ServerName
	*        The name or ip-address of server.
	* @param Port
	*        The connection port of server.
	*/
	public CLIClient(String ServerName, int Port)
	{
		server_name = ServerName;
		server_port = Port;
		secure = false;
	}
	
	/**
	* Create CLI client object with/without SSL
	*
	* @param ServerName
	*        The name or ip-address of server.
	* @param Port
	*        The connection port of server.
	* @param SSL
	*        Is use ssl protocol. true for use it.
	*/
	public CLIClient(String ServerName, int Port, boolean SSL)
	{
		server_name = ServerName;
		server_port = Port;
		secure = SSL;
	}

	/**
	* Connect to CLI server & login
	*
	* @param UserName
	*        The user name to login.
	* @param Password
	*        The password to login.
	*/
	public boolean Login(String UserName, String Password)
	{
		try
		{
			// create socket
			if(secure)
			{
				SocketFactory sf = SSLSocketFactory.getDefault();
				socket = sf.createSocket(server_name, server_port);
			}
			else
			{
				socket = new Socket(InetAddress.getByName(server_name), server_port);
			}
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			
			// check version & login
			if(CheckVersion() && UserLogin(UserName, Password))
			{
				return true;
			}
		}
		catch (UnknownHostException e)
		{
			err = new String("connect error: Unknow server name");
		}
		catch (IOException e)
		{
			err = new String("connect error: " + e.getMessage());
		}
		return false;
	}

	/**
	* execute command string
	*
	* @param CmdString
	*        The command string to execute.
	* @param Resp
	*        The interface object to process command response.
	*/
	public boolean Execute(String CmdString, CLIResponse Resp)
	{
		try
		{
			out.write(MakeHead(5,0,0,CmdString.length()+1));
			out.write(CmdString.getBytes("UTF-8"));
			out.write('\0');
			out.flush();
			
			while(true)
			{
				int[] head = ReadHead();
				byte[] body = new byte[4096];
				boolean ok = (head[2] & 0x0001) == 0 ? true : false;
				boolean cont = ((head[2] >>> 2) & 0x0001) == 0 ? false : true;
				int len = head[3];

				if(len > 0)
				{
					try
					{
						in.readFully(body, 0, len);
					}
					catch (EOFException e)
					{
						err = new String("execute error: no more response data to read");
						break;
					}
				}
				if(!Resp.Process(body, len, ok, cont) || !cont)
				{
					// process complete, or cancel by user
					return true;
				}
				out.write(MakeHead(6,0,0,0));
				out.flush();
			}
		}
		catch (IOException e)
		{
			err = new String("execute error: " + e.getMessage());
		}
		return false;
	}

	public boolean Subscribe(int Id, CLIResponse Resp)
	{
		try
		{
			String rid = Integer.toString(Id);
			out.write(MakeHead(7,'R',0,rid.length()+1));
			out.write(rid.getBytes("UTF-8"));
			out.write('\0');
			out.flush();

			int[] head = ReadHead();
			String result;
			if(head[3] > 0)
			{
				byte[] body = new byte[head[3]];
				result = new String(body, 0, in.read(body, 0, head[3]));
			}
			else
			{
				result = new String("no result messages");
			}
			if((head[2] & 0x0001) == 0)
			{
				new Thread(new CLIClientTrap(in, Id, Resp)).start();
				return true;
			}
			else
			{
				err = new String("subscribe error: " + result);
			}
		}
		catch (IOException e)
		{
			err = new String("subscribe error: " + e.getMessage());
		}
		return false;
	}

	public boolean PutFile(String FileName, int Port)
	{
		try
		{
			String cmd = new String(FileName + "|" + Port);
			out.write(MakeHead(9,0,0,cmd.length()+1));
			out.write(cmd.getBytes("UTF-8"));
			out.write('\0');
			out.flush();
			
			int[] head = ReadHead();
			byte[] body = new byte[4096];
			boolean ok = (head[2] & 0x0001) == 0 ? true : false;
			boolean cont = ((head[2] >>> 2) & 0x0001) == 0 ? false : true;
			int len = in.read(body, 0, head[3]);
			if(len < 0)
			{
				err = new String("execute error: no more response data to read");
			}
			else if(ok)
			{
				String res = new String(body, 0, len);
				String[] col = res.split("\\|");
				Socket sd = null;
				DataOutputStream od = null;
				FileInputStream fin = null;

				try
				{
					sd = new Socket(InetAddress.getByName(server_name), Integer.parseInt(col[1]));
					od = new DataOutputStream(sd.getOutputStream());
					File file = new File(FileName);
					fin = new FileInputStream(file);
					byte[] buf = new byte[4096];
					int rlen;
					
					while((rlen = fin.read(buf, 0, buf.length))>0)
					{
						od.write(buf, 0, rlen);
						od.flush();
					}
				}
				catch (IOException e)
				{
					err = new String("putfile data error: " + e.getMessage());
				}
				finally
				{
					if(od != null)
						od.close();
					if(fin != null)
						fin.close();
					if (sd != null)
						sd.close();
				}
			}
		}
		catch (IOException e)
		{
			err = new String("putfile command error: " + e.getMessage());
		}
		return false;
	}

	public String LastError()
	{
		return err;
	}
}

