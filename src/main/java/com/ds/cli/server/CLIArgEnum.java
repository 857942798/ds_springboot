package com.ds.cli.server;

import java.util.Map;
import java.util.HashMap;

public class CLIArgEnum extends CLIArg
{
	public Map<String,Integer> enum_map = new HashMap<String,Integer>();
	public boolean case_sensitive;
	public String enum_value;

	CLIArgEnum(String name, String desc, boolean is_case_sensitive)
	{
		aid = aid_seq++;
		arg_type = arg_type_enum;
		arg_flag = is_case_sensitive ? arg_flag_case_sensitive : 0;
		arg_name = name;
		arg_desc = desc;
		arg_extra = new String("E") + Integer.toString(aid);
		validate_func = new CLIValidateEnum();
		validate_param1 = enum_map;
		validate_param2 = is_case_sensitive ? new Integer(1) : new Integer(0);
		aid_map.put(aid, this);
		case_sensitive = is_case_sensitive;
		enum_value = null;
	}

	CLIArgEnum(String name, String desc, boolean is_case_sensitive,
               CLIValidate validate, Object va_param1, Object va_param2)
	{
		aid = aid_seq++;
		arg_type = arg_type_enum;
		arg_flag = is_case_sensitive ? arg_flag_case_sensitive : 0;
		arg_name = name;
		arg_desc = desc;
		arg_extra = new String("E") + Integer.toString(aid);
		validate_func = validate;
		validate_param1 = va_param1;
		validate_param2 = va_param2;
		aid_map.put(aid, this);
		case_sensitive = is_case_sensitive;
		enum_value = null;
	}

	CLIArgEnum(CLIArgEnum arg)
	{
		super(arg);
		case_sensitive = false;
		enum_value = null;
	}

	boolean SetValue(String value)
	{
		enum_value = new String(value);
		return true;
	}

	String GetValue()
	{
		return enum_value;
	}

	String InfoResponse()
	{
		StringBuffer buf = new StringBuffer("enum|");
		
		for(String key : enum_map.keySet())
		{
			buf.append('|');
			buf.append(key);
		}
		return buf.toString();
	}

	boolean AddEnum(String enum_name, int enum_value)
	{
		if(!case_sensitive)
		{
			enum_name = enum_name.toLowerCase();
		}
		if(enum_map.containsKey(enum_name))
		{
			return false;
		}
		enum_map.put(enum_name, enum_value);
		return true;
	}
}

