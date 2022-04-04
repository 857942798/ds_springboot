package com.ds.cli.server;

public class CLIProcess_demo_version implements CLIProcess
{
	public boolean Execute(CLIReply reply, CLIArg[] args)
	{
		reply.Add("Demo Version 3.0");
		return true;
	}
}

