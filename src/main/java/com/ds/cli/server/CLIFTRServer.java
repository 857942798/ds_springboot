package com.ds.cli.server;

import java.io.*;
import java.net.InetAddress;  
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.net.SocketTimeoutException;
import javax.net.SocketFactory;

public class CLIFTRServer implements Runnable
{
	private int mode;					// 0: upload  1: download
	private FileInputStream fin;		// for download
	private FileOutputStream fout;		// for upload
	private String file_dest;
	private String file_temp;			// for upload
	private long file_size;				// for upload
	private ServerSocket server;

	public boolean StartDownload(String host, int port, String file, StringBuffer err_str)
	{
		// open file to read
		File fobj = new File(file);
		if(!fobj.exists() || !fobj.isFile())
		{
			err_str.append("file is not existing");
			return false;
		}
		try
		{
			fin = new FileInputStream(fobj);
		}
		catch (FileNotFoundException e)
		{
			err_str.append("failed to open file");
			return false;
		}
		file_dest = new String(file);

		// create server to accept connection
		try
		{
			server = new ServerSocket(port, 5, InetAddress.getByName(host));
		}
		catch (Exception e)
		{
			err_str.append("failed to create ftr server");
			return false;
		}

		// create thread to send data
		mode = 1;
		new Thread(this).start();
		return true;
	}
	
	public boolean StartUpload(String host, int port, String file, long size, StringBuffer err_str)
	{
		// create file to write
		file_size = size;
		file_dest = new String(file);
		file_temp = new String(file + ".ftr");
		try
		{
			fout = new FileOutputStream(file_temp, false);
		}
		catch (FileNotFoundException e)
		{
			err_str.append("failed to open file");
			return false;
		}

		// create server to accept connection
		try
		{
			server = new ServerSocket(port, 5, InetAddress.getByName(host));
		}
		catch (Exception e)
		{
			err_str.append("failed to create ftr server");
			return false;
		}

		// create thread to receive data
		mode = 0;
		new Thread(this).start();
		return true;
	}

	public void run()
	{
		// accept connection
		Socket client;
		try
		{
			server.setSoTimeout(10*1000);
			client = server.accept();
			client.setSoTimeout(10*1000);
		}
		catch (SocketTimeoutException e)
		{
			System.out.println("connection timeout");
			return;
		}
		catch (Exception e)
		{
			return;
		}

		if(mode == 0)
		{
			DataInputStream in;
			int len;
			long rx_size = 0;
			byte[] buf = new byte[64*1024];

			try
			{
				// read data from socket
				in = new DataInputStream(client.getInputStream());
				while(true)
				{
					len = in.read(buf);
					if(len > 0)
					{
						// write to file
						fout.write(buf, 0, len);
						rx_size += len;
					}
					else if(len < 0)
					{
						break;
					}
				}
				
				// validate file complete or not
				if(file_size > 0 && rx_size < file_size)
				{
					System.out.println("missing file data");
					return;
				}
				fout.close();
				
				// rename to destination file
				File fobj_temp = new File(file_temp);
				File fobj_dest = new File(file_dest);
				if(fobj_dest.exists() && fobj_dest.isFile())
				{
					fobj_dest.delete();
				}
				fobj_temp.renameTo(fobj_dest);
				System.out.println("upload file \"" + file_dest + "\" complete");
			}
			catch (SocketTimeoutException e)
			{
				System.out.println("read data timeout");
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			DataOutputStream out;
			int len;
			long tx_size = 0;
			byte[] buf = new byte[64*1024];

			try
			{
				// read data from file
				out = new DataOutputStream(client.getOutputStream());
				while(true)
				{
					len = fin.read(buf);
					if(len > 0)
					{
						// write to socket
						out.write(buf, 0, len);
						tx_size += len;
					}
					else if(len < 0)
					{
						break;
					}
				}
				System.out.println("download file \"" + file_dest + "\" complete");
			}
			catch (SocketTimeoutException e)
			{
				System.out.println("write data timeout");
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}

