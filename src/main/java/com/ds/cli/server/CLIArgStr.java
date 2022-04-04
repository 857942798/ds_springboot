package com.ds.cli.server;

public class CLIArgStr extends CLIArg
{
	public String value_str;

	CLIArgStr(String name, String desc, int max_len)
	{
		this(name, desc, new CLIValidateString(), new Integer(max_len), null);
	}

	CLIArgStr(String name, String desc, CLIValidate validate, Object va_param1, Object va_param2)
	{
		aid = aid_seq++;
		arg_type = arg_type_string;
		arg_flag = 0;
		arg_name = name;
		arg_desc = desc;
		arg_extra = new String("S") + Integer.toString(aid);
		validate_func = validate;
		validate_param1 = va_param1;
		validate_param2 = va_param2;
		aid_map.put(aid, this);
		value_str = new String();
	}

	CLIArgStr(CLIArgStr arg)
	{
		super(arg);
		value_str = new String(arg.value_str);
	}

	boolean SetValue(String value)
	{
		value_str = new String(value);
		return true;
	}

	String GetValue()
	{
		return value_str;
	}

	String InfoResponse()
	{
		return new String("string|" + ((Integer)validate_param1).toString());
	}
}

