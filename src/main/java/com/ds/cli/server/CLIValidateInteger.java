package com.ds.cli.server;

class CLIValidateInteger implements CLIValidate
{
	/**
	* validate cli argument of integer
	*
	* @param Value
	*        The argument value to validate.
	* @param Param1
	*        the minimum value of range. object type is 'Integer'
	* @param Param2
	*        the maximum value of range. object type is 'Integer'
	*/
	public boolean Validate(String Value, Object Param1, Object Param2)
	{
		int ival;
		int imin;
		int imax;

		try
		{
			ival = Integer.parseInt(Value);
		}
		catch (NumberFormatException e)
		{
			return false;
		}

		imin = ((Integer)Param1).intValue();
		imax = ((Integer)Param2).intValue();
		if(ival >= imin && ival <= imax)
		{
			return true;
		}
		return false;
	}
}

