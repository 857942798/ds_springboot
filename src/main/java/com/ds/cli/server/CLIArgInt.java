package com.ds.cli.server;

public class CLIArgInt extends CLIArg
{
	public int range_max;
	public int range_min;
	public int value_int;

	CLIArgInt(String name, String desc)
	{
		this(name, desc, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	CLIArgInt(String name, String desc, int value_min, int value_max)
	{
		aid = aid_seq++;
		arg_type = arg_type_integer;
		arg_flag = 0;
		arg_name = name;
		arg_desc = desc;
		arg_extra = new String("I") + Integer.toString(aid);
		validate_func = new CLIValidateInteger();
		validate_param1 = new Integer(value_min);
		validate_param2 = new Integer(value_max);
		aid_map.put(aid, this);

		range_min = value_min;
		range_max = value_max;
		value_int = 0;
	}

	CLIArgInt(CLIArgInt arg)
	{
		super(arg);
		this.range_max = arg.range_max;
		this.range_min = arg.range_min;
		this.value_int = arg.value_int;
	}

	boolean SetValue(String value)
	{
		try
		{
			value_int = Integer.parseInt(value);
			return true;
		}
		catch (NumberFormatException e)
		{
			value_int = 0;
			return false;
		}
	}

	String GetValue()
	{
		return Integer.toString(value_int);
	}

	String InfoResponse()
	{
		return new String("integer|" + Integer.toString(range_min) +
								"|" + Integer.toString(range_max));
	}

	int GetInteger()
	{
		return value_int;
	}
}

