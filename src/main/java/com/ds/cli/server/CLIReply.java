package com.ds.cli.server;

public interface CLIReply
{
	/**
	* add result for this command
	*
	* @param result
	*        the result to reply.
	*/
	void Add(String result);

	/**
	* set new page
	*/
	void NewPage();
}

