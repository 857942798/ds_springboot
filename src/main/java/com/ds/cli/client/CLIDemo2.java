package com.ds.cli.client;

import java.io.*;
import java.net.InetAddress;  
import java.net.Socket;
import java.net.UnknownHostException;

class CLIDemo2
{
	static CLIClient client;
	static CLIDemoResponse resp;

	public static void main(String[] args)
	{
		if(args.length < 3)
		{
			System.out.println("missing parameter 'cli-server-name' , 'cli-service-port' and 'file-to-transfer'");
			return;
		}
		System.setProperty("javax.net.ssl.trustStore","/Users/ds/projects/ds_springboot/src/main/java/com/ds/cli/client/cli.keystore");

		resp = new CLIDemoResponse();
		int port = Integer.parseInt(args[1]);

		System.out.print("Create cli object ... ");
		client = new CLIClient(args[0], port, true);
		if(!client.Login("user", "123"))
		{
			System.out.println("Login Error: " + client.LastError());
			return;
		}
		System.out.println("Done");

		System.out.print("Starting put file " + args[2] + " ...");
		client.PutFile(args[2], port + 1);
		System.out.println("Done");
	}
}

