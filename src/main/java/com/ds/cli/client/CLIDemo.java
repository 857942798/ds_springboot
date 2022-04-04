package com.ds.cli.client;

import java.io.*;
import java.net.InetAddress;  
import java.net.Socket;
import java.net.UnknownHostException;

class CLIDemo
{
	static CLIClient client;
	static CLIDemoResponse resp;

	public static void main(String[] args)
	{
		if(args.length < 3)
		{
			System.out.println("missing parameter 'cli-server-name' , 'cli-service-port' and 'cli-command'");
			return;
		}
		//System.setProperty("javax.net.debug", "ssl,handshake");
		System.setProperty("javax.net.ssl.keyStore", "/Users/ds/projects/ds_springboot/src/main/java/com/ds/cli/client/test.keystore");
		System.setProperty("javax.net.ssl.keyStorePassword", "test123456");
		System.setProperty("javax.net.ssl.trustStore", "/Users/ds/projects/ds_springboot/src/main/java/com/ds/cli/client/server.keystore");
		System.setProperty("javax.net.ssl.trustStorePassword", "test123456");

		boolean ssl = false;
		int cidx = 2;
		resp = new CLIDemoResponse();
		int port = Integer.parseInt(args[1]);

		if(args[2].equals("ssl"))
		{
//			ssl = true;
			cidx++;
		}
		System.out.print("Create cli object ... ");
		client = new CLIClient(args[0], port, ssl);
		if(!client.Login("user", "123"))
		{
			System.out.println("Login Error: " + client.LastError());
			return;
		}
		System.out.println("Done");

		for(int i=cidx;i<args.length;i++)
		{
			System.out.println("Command-" + (i-cidx+1) + ": " + args[i]);
			System.out.println("------------------------------------------------");
			if(!client.Execute(args[i], resp))
			{
				System.out.println("Execute Error: " + client.LastError());
				return;
			}
		}
	}
}

