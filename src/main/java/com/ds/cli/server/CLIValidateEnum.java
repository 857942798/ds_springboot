package com.ds.cli.server;

import java.util.Map;
import java.util.HashMap;

class CLIValidateEnum implements CLIValidate
{
	/**
	* validate cli argument of enumerate
	*
	* @param Value
	*        The argument value to validate.
	* @param Param1
	*        the enum value list. object type is 'Map<String,Integer>'
	* @param Param2
	*        is case sensitive. 1 for case sensitive, 0 for not. object type is 'Integer'
	*/
	@SuppressWarnings("unchecked")
	public boolean Validate(String value, Object param1, Object param2)
	{
		Map<String,Integer> enum_map = (HashMap<String,Integer>)param1;
		Integer case_sensitive = (Integer)param2;

		if(case_sensitive.intValue() == 0)
		{
			value = value.toLowerCase();
		}
		return enum_map.containsKey(value);
	}
}

