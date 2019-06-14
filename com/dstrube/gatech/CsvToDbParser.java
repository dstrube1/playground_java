/*
Author: David Strube
Date: 2019-06-04
Compile:
[mac - unverified]
javac -d bin com/dstrube/gatech/CsvToDbParser.java
[windows]:
javac -d bin com\dstrube\gatech\CsvToDbParser.java
Run:
[mac - unverified]
java -cp bin com.dstrube.gatech.CsvToDbParser [file]
[windows]:
java -cp bin com.dstrube.gatech.CsvToDbParser [file]

This is a console application that:
 - Accepts as an argument the name of a file containing a CSV file
 - Outputs to a new file "out.txt": INSERT statements for a database with the data in the input file
*/

package com.dstrube.gatech;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvToDbParser
{
	private static List<String> columns = null;
	private static String tableName = "";

	public static void main(String[] args)
	{
		if (args.length == 0)
		{
			System.out.println("Empty args");
			return;
		}
		
		try
		{
			if (!isFileOK(args[0]))
			{
				return;
			}
			
			final File file = new File(args[0]);
			if (!getTableAndColumns(file))
			{
				return;
			}
			
			if(!outputInserts(file)){
				return;
			}

		} 
		catch (FileNotFoundException fileNotFoundException)
		{
			System.out.println("File not found: " + args[0]);
		} 
		catch (IOException ioException)
		{
	    	System.out.println("IOException caught: " + ioException);
	    } 
	    catch (Exception exception)
	    {
	    	System.out.println("Exception caught: " + exception);
	    }
	    System.out.println("Done.");
	}
	
	//This method returns false if the file doesn't exist, is a folder, isn't readable, or is empty.
	//Else returns true
	public static boolean isFileOK(final String path) throws IOException
	{
		if (path == null)
		{
			System.out.println("Null file path");
			return false;
		}
		if (path.length() == 0)
		{
			System.out.println("Empty file path");
			return false;
		}
		final File file = new File(path);
		if (!file.exists())
		{
			System.out.println("File does not exist: " + file);
			return false;
		}
			
		if (!file.isFile())
		{
			System.out.println("This is not a file: " + file);
			return false;
		}
			
		if (!file.canRead())
		{
			System.out.println("Cannot read file: " + file);
			return false;
		}
		
		final FileInputStream fileInputStream = new FileInputStream(file);
		final Scanner scanner = new Scanner(fileInputStream);
		
		try
		{		
			if (!scanner.hasNextLine())
			{
				System.out.println("Empty file: " + file);
				return false;
			}
			//File is OK
			return true;
		}
		finally
		{
			//Debug statement to confirm that the scanner and fileInputStream are closed 
			//regardless of return value
			//System.out.println("Finally");
			
			scanner.close();
			fileInputStream.close();
		}
	}
	
	//This method populates a list of strings from the first line of a CSV file; returns false if an error is encountered along the way.
	public static boolean getTableAndColumns(final File file) throws IOException
	{
		if (file == null)
		{
			System.out.println("Null file");
			return false;
		}
		final FileInputStream fileInputStream = new FileInputStream(file);
		final Scanner scanner = new Scanner(fileInputStream);
		String line = "";
		
		try
		{
			columns = new ArrayList<>();
			if (scanner.hasNextLine()){
				tableName = scanner.nextLine();
			}else{
				return false;
			}
			if(scanner.hasNextLine())
			{
				line = scanner.nextLine();
				//System.out.println("Line: " + line);
				for (String column : line.split(",")){
					columns.add(column);
				}
			}else{
				return false;
			}
			return scanner.hasNextLine(); //unless we want to allow an empty table
		} 
		catch (Exception exception)
		{
			//Possible improvement: ignore empty lines
	    	System.out.println("Invalid input: caught Exception at line " + line + " - " + exception);
	    	return false;
		}
		finally
		{
			scanner.close();
			fileInputStream.close();
		}
	}
	
	//This method returns true if the output file is written to.
	//Else returns false
	public static boolean outputInserts(final File file) throws IOException
	{
		if (tableName == null)
		{
			System.out.println("tableName is null");
			return false;
		}
		if (tableName.length() == 0)
		{
			System.out.println("Empty tableName");
			return false;
		}
		if (columns == null)
		{
			System.out.println("List is null");
			return false;
		}
		if (columns.size() == 0)
		{
			System.out.println("Empty list");
			return false;
		}
		if (columns.size() == 1)
		{
			System.out.println("List of columns has only 1 element");
			return false;
		}
		System.out.println("tableName: " + tableName);
		final FileInputStream fileInputStream = new FileInputStream(file);
		final Scanner scanner = new Scanner(fileInputStream);
		String line = "";
		//throw away the first two lines
		//Safe to assume at this point that there are more than 2 lines
		line = scanner.nextLine();
		line = scanner.nextLine();
		final StringBuilder sb = new StringBuilder();
		try
		{
			while (scanner.hasNextLine()){
				line = scanner.nextLine();
				sb.append("INSERT INTO ");
				sb.append(tableName);
				sb.append(" (");
				for (int i = 0; i < columns.size(); i++)
				{
					sb.append(columns.get(i));
					if (i < columns.size() - 1){
						sb.append(",");
					}
				}
				sb.append(") VALUES(");
				String[] values = line.split(",");
				for (int i = 0; i < columns.size(); i++)
				{
					if(values.length > i){
						sb.append("\"");
						sb.append(values[i]);
						sb.append("\"");
					}else{
						sb.append("\"\"");
					}
					if (i < columns.size() - 1){
						sb.append(",");
					}
				}
				sb.append(")\n");
			}
		} 
		catch (Exception exception)
		{
			//Possible improvement: ignore empty lines
	    	System.out.println("Invalid input: caught Exception at line " + line + " - " + exception);
	    	return false;
		}
		finally
		{
			scanner.close();
			fileInputStream.close();
		}
		System.out.println("query: " + sb.toString());
		return true;
	}
}