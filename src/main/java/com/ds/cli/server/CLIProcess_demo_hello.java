package com.ds.cli.server;

import java.io.*;

public class CLIProcess_demo_hello implements CLIProcess
{
	public boolean Execute(CLIReply reply, CLIArg[] args)
	{
		System.out.print("Hello " + args[0].GetValue());
		System.out.println(", you are " + args[1].GetValue() + " years old in " + args[2].GetValue());
		return true;
	}
}

