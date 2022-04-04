package com.ds.cli.server;

class CLIValidateString implements CLIValidate
{
	/**
	* validate cli argument of string
	*
	* @param Value
	*        The argument value to validate.
	* @param Param1
	*        the maximum length of string argument. zero or negative means unlimited.
	*        object type is 'Integer'
	* @param Param2
	*        not used.
	*/
	public boolean Validate(String value, Object param1, Object param2)
	{
		int max_len = ((Integer)param1).intValue();

		if(max_len <= 0)
		{
			return true;
		}
		return value.length() <= max_len;
	}
}

