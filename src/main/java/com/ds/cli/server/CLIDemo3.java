package com.ds.cli.server;

import java.io.*;
import java.net.InetAddress;  
import java.net.Socket;
import java.net.UnknownHostException;

class CLIDemo3
{
	static CLIServer server;

	public static void main(String[] args)
	{
		if(args.length < 2)
		{
			System.out.println("missing parameter 'cli-server-name' , 'cli-service-port'");
			return;
		}
		//System.setProperty("javax.net.debug", "ssl,handshake");
		System.setProperty("javax.net.ssl.keyStore", "/Users/ds/projects/ds_springboot/src/main/java/com/ds/cli/server/server.keystore");
		System.setProperty("javax.net.ssl.keyStorePassword", "test123456");
		System.setProperty("javax.net.ssl.trustStore", "/Users/ds/projects/ds_springboot/src/main/java/com/ds/cli/server/test.keystore");
		System.setProperty("javax.net.ssl.trustStorePassword", "test123456");

		int port = Integer.parseInt(args[1]);
		boolean ssl = false;

		if(args.length > 2 && args[2].equals("ssl"))
		{
//			ssl = true;
		}
		System.out.print("Create cli server ... ");
		try
		{
			server = new CLIServer(args[0], port, ssl);
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
			return;
		}
		server.AddNode("demo", "demo commands");
		server.AddCommand("demo version", "show version of demo", null, new CLIProcess_demo_version());

		CLIArgEnum earg = new CLIArgEnum("level", "please enter your job level", false);
		earg.AddEnum("band5", 1);
		earg.AddEnum("band6", 2);
		earg.AddEnum("band7", 3);
		earg.AddEnum("band8", 4);
		earg.AddEnum("band8+", 5);
		CLIArg[] cli_args =
		{
			new CLIArgStr("name", "please enter your name", 16),
			new CLIArgInt("age", "please enter your age", 1, 120),
			earg
		};
		server.AddCommand("demo hello", "print hello information", cli_args, new CLIProcess_demo_hello());

		new Thread(server).start();
		System.out.println("Done");

		while(true)
		{
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
			}
		}
	}
}

