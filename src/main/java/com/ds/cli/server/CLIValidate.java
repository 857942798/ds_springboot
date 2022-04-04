package com.ds.cli.server;

public interface CLIValidate
{
	/**
	* validate cli arguments
	*
	* @param Value
	*        The argument value to validate.
	* @param Param1
	*        the first parameter.
	* @param Param2
	*        the second parameter.
	*/
	boolean Validate(String Value, Object Param1, Object Param2);
}

