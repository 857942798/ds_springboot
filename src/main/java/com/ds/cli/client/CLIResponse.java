package com.ds.cli.client;

public interface CLIResponse
{
	/**
	* process command response
	*
	* @param Data
	*        The octets of response string, decode by UTF-8 format.
	* @param DataLen
	*        The length of response string, in bytes.
	* @param StatusOK
	*        The response status. true for command execute succeed, false for error occured.
	* @param ToBeContinue
	*        Is any more response data.
	*        If true, this interface will be called again after method return true.
	*		 If false, the command response data is complete processed.
	*        When method return false, response data will not be commit later. It means user
	*        canceled response processing.
	*/
	boolean Process(byte[] Data, int DataLen, boolean StatusOK, boolean ToBeContinue);
}

