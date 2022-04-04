package com.ds.cli.server;

public interface CLIProcess
{
	/**
	* process cli command
	*
	* @param reply
	*        the interface to reply result
	* @param args
	*        the parameter list of command.
	*/
	boolean Execute(CLIReply reply, CLIArg[] args);
}

