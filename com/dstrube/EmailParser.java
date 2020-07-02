/*
From ~/java:
javac -d bin com/dstrube/EmailParser.java
java -cp bin com.dstrube.EmailParser

This parses thru a text file of email addresses, does what python could not, for some reason
*/

package com.dstrube;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.OpenOption;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class EmailParser{

	final static List<String> emoryEdu = new ArrayList<>();
	final static List<String> emoryHealthcare = new ArrayList<>();
	final static List<String> edus = new ArrayList<>();
	final static List<String> emoryOther = new ArrayList<>();
	final static List<String> govs = new ArrayList<>();
	final static List<String> coms = new ArrayList<>();
	final static List<String> nets = new ArrayList<>();
	final static List<String> orgs = new ArrayList<>();
	final static List<String> etc = new ArrayList<>();
	
	public static void main(String[] args){
		try{
			File fileIn = new File("email_emory_out.txt");
			//1- read in file, starting at Etc
			final List<String> lines = readFile(fileIn);
			if (lines == null){
				return;
			}
			
			//2- for each line, put line into a list that it matches
			setLists(lines);
			
			//3- write out all lists into 1 new file
			writeFile();
			
			System.out.println("Done");
			
	    } catch (Exception exception){
	    	System.out.println("Exception caught at " + methodName() + ": " + exception);
	    }
	}
	
	//
	private static List<String> readFile(final File fileIn){
		Scanner scanner1 = null;
		try{
			
			//Now we're dealing with 2 files
			final FileInputStream fileInputStream1 = new FileInputStream(fileIn);
			final List<String> lines = new ArrayList<>();
			scanner1 = new Scanner(fileInputStream1);
			boolean etcFound = false;
			while(scanner1.hasNextLine()){
				final String line = scanner1.nextLine();
				if (line.startsWith("Etc=") || etcFound){
					etcFound = true;
					lines.add(line);
				}

			}//end while
			System.out.println("Read in " + lines.size() + " lines");
			return lines;

	    } catch (FileNotFoundException fnfe){
	    	System.out.println("FileNotFoundException");
	    	return null;
	    } finally{
	    	if (scanner1 != null){
	    		scanner1.close();
	    	}
	    }
	}
	
	private static void setLists(final List<String> lines){
		for(final String line : lines){
			if(line.endsWith("emory.edu")){
				emoryEdu.add(line);
			}else if(line.endsWith("emoryhealthcare.org")){
				emoryHealthcare.add(line);
			}else if(line.endsWith(".edu")){
				edus.add(line);
			}else if(line.contains("emory")){
				emoryOther.add(line);
			}else if(line.endsWith(".gov")){
				govs.add(line);
			}else if(line.endsWith(".com")){
				coms.add(line);
			}else if(line.endsWith(".net")){
				nets.add(line);
			}else if(line.endsWith(".org")){
				orgs.add(line);
			}else{
				etc.add(line);
			}
		}
	}
	
	private static void writeFile(){
		//Write data to file
		try{
			File file = new File("email_emory_out_0.txt");
			Path path = Paths.get(file.toURI());
			byte[] bytes = "==========================================".getBytes("utf-8");
			OpenOption[] options = {StandardOpenOption.CREATE, StandardOpenOption.APPEND};
			Charset charset = Charset.forName("UTF-8");

			Files.write(path, bytes, options);
			Files.write(path, emoryEdu, charset, options);
			Files.write(path, bytes, options);
			Files.write(path, emoryHealthcare, charset, options);
			Files.write(path, bytes, options);
			Files.write(path, edus, charset, options);
			Files.write(path, bytes, options);
			Files.write(path, emoryOther, charset, options);
			Files.write(path, bytes, options);
			Files.write(path, govs, charset, options);
			Files.write(path, bytes, options);
			Files.write(path, coms, charset, options);
			Files.write(path, bytes, options);
			Files.write(path, nets, charset, options);
			Files.write(path, bytes, options);
			Files.write(path, orgs, charset, options);
			Files.write(path, bytes, options);
			Files.write(path, etc, charset, options);
		}
		catch(IOException e){
			System.out.println("\nCaught exception: " + e);		
		}
	}
	
	private static String methodName() {
		//https://www.baeldung.com/java-name-of-executing-method
	    final String methodName = 
    	  Thread.currentThread()
    	  .getStackTrace()[2]//0 = "getStackTrace"; 1 = "methodName"; 2 = calling method
    	  .getMethodName();
        return methodName;
	}
}