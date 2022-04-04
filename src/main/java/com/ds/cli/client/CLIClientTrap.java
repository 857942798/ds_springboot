package com.ds.cli.client;

import java.io.*;
import java.net.InetAddress;  
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class CLIClientTrap implements Runnable
{
	private DataInputStream in;
	private int trap_id;
	private CLIResponse resp;

	public CLIClientTrap(DataInputStream Input, int TrapId, CLIResponse Resp)
	{
		in = Input;
		trap_id = TrapId;
		resp = Resp;
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

				if(head[3] > 4)
				{
					ts = new byte[4];
					in.read(ts, 0, 4);
					body = new byte[head[3]];
					len = in.read(body, 0, head[3]-4);
				}
				if(len <= 0)
				{
					return;
				}
				resp.Process(body, len, ok, cont);
			}
			catch (IOException e)
			{
				return;
			}
		}
	}

	private int[] ReadHead() throws IOException
	{
		byte[] h = new byte[8];	
		int[] head = new int[4];

		in.read(h, 0, 8);
		head[0] = (Byte.toUnsignedInt(h[1]) << 8) | Byte.toUnsignedInt(h[0]);
		head[1] = (Byte.toUnsignedInt(h[3]) << 8) | Byte.toUnsignedInt(h[2]);
		head[2] = (Byte.toUnsignedInt(h[5]) << 8) | Byte.toUnsignedInt(h[4]);
		head[3] = (Byte.toUnsignedInt(h[7]) << 8) | Byte.toUnsignedInt(h[6]);
		
		return head;
	}
}

