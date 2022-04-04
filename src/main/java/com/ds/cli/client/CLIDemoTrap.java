package com.ds.cli.client;

import java.io.*;
import java.net.InetAddress;  
import java.net.Socket;
import java.net.UnknownHostException;

class CLIDemoTrap
{
	static CLIClient client;
	static CLIDemoResponse resp;

	public static void main(String[] args)
	{
		if(args.length < 2)
		{
			System.out.println("missing parameter 'cli-server-name' , 'cli-service-port'");
			return;
		}
		System.setProperty("javax.net.ssl.trustStore","cli.keystore");

		resp = new CLIDemoResponse();
		int port = Integer.parseInt(args[1]);

		System.out.print("Create cli object ... ");
		client = new CLIClient(args[0], port, false);
		if(!client.Login("user", "123"))
		{
			System.out.println("Login Error: " + client.LastError());
			return;
		}
		System.out.println("Done");

		client.Subscribe(1, resp);
		try
		{
			Thread.sleep(1000000);
		}
		catch (InterruptedException e)
		{
		}
	}
}

