package com.ds.cli.client;

import java.io.*;
import java.util.Scanner;

class CLIDemoResponse implements CLIResponse
{
	public boolean Process(byte[] Data, int DataLen, boolean StatusOK, boolean ToBeContinue)
	{
		if(!StatusOK)
		{
			System.out.println("Error: " + new String(Data, 0, DataLen));
		}
		else
		{
			System.out.println(new String(Data, 0, DataLen));
		}
		if(ToBeContinue)
		{
			System.out.println("Press any key to continued ('Q' to cancel).");

			try
			{
				int c = System.in.read();
				if(c == 'q' || c == 'Q')
				{
					System.out.println("user canceled");
					return false;
				}
				return true;
			}
			catch (IOException e)
			{
			}
			return false;
		}
		return true;
	}
}

