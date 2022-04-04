package com.ds.cli.server;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

public class CLITreeNode
{
	public List<CLITreeNode> childs = new LinkedList<CLITreeNode>();
	public String name;
	public String desc;
	public int cmd_type;		// 0: command node, 1: parameter node
	public CLIArg arg;			// valid for 'parameter node'
	public CLIProcess proc;		// valid for 'command node'

	public CLITreeNode(String node_name, String node_desc, CLIProcess node_proc)
	{
		name = node_name;
		desc = node_desc;
		cmd_type = 0;
		arg = null;
		proc = node_proc;
	}

	public CLITreeNode(CLIArg arg_ref)
	{
		name = arg_ref.arg_name;
		desc = arg_ref.arg_desc;
		cmd_type = 1;
		arg = arg_ref;
		proc = null;
	}

	public CLITreeNode GetChild(String name)
	{
		Iterator<CLITreeNode> iter = childs.iterator();
		
		while(iter.hasNext())
		{
			CLITreeNode node = iter.next();
			if(node.name.equals(name))
			{
				return node;
			}
		}
		return null;
	}

	public boolean AddChild(CLITreeNode node)
	{
		return childs.add(node);
	}
}

