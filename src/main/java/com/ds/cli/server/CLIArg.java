package com.ds.cli.server;

import java.util.Map;
import java.util.HashMap;

public abstract class CLIArg
{
	public static final int arg_type_integer = 0;
	public static final int arg_type_string = 1;
	public static final int arg_type_enum = 2;
	public static final int arg_flag_optional = 1;
	public static final int arg_flag_case_sensitive = 2;
	public static final int validate_integer_range = 1;
	public static final int validate_string_length = 2;

	public int aid = -1;
	public int arg_type;	// arg_type_xxx
	public int arg_flag;	// arg_flag_xxx
	public String arg_name;
	public String arg_desc;
	public String arg_extra;
	public CLIValidate validate_func;
	public Object validate_param1;
	public Object validate_param2;

	public static int aid_seq = 0;
	public static Map<Integer,CLIArg> aid_map = new HashMap<Integer,CLIArg>();

	abstract boolean SetValue(String value);
	abstract String GetValue();
	abstract String InfoResponse();

	static CLIArg GetArg(int aid)
	{
		return aid_map.get(aid);
	}

	CLIArg()
	{
	}

	CLIArg(CLIArg arg)
	{
		this.aid = arg.aid;
		this.arg_type = arg.arg_type;
		this.arg_flag = arg.arg_flag;
		this.arg_name = arg.arg_name;
		this.arg_desc = arg.arg_desc;
		this.arg_extra = arg.arg_extra;
		this.validate_func = arg.validate_func;
		this.validate_param1 = arg.validate_param1;
		this.validate_param2 = arg.validate_param2;
	}

	boolean IsOptional()
	{
		if((arg_flag & arg_flag_case_sensitive) != 0)
		{
			return true;
		}
		return false;
	}

	boolean Validate(String value)
	{
		if(validate_func == null)
		{
			return true;
		}
		return validate_func.Validate(value, validate_param1, validate_param2);
	}
}

