package com.dstrube;

/*
From ~/java:
Mac / Linux:
javac -d bin com/dstrube/Anonymizer.java
java -cp bin com.dstrube.Anonymizer [-in:inFile.txt -out:outFile.txt -debug]
Windows:
javac -d bin com\dstrube\Anonymizer.java
java -cp bin com.dstrube.Anonymizer [-in:inFile.txt -out:outFile.txt -debug]
*/

import java.util.*;
import java.io.*;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.OpenOption;

import java.text.ParseException;

public class Anonymizer {
	private static boolean IS_DEBUG = false;
	private static final Random random = new Random();
	private static final String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static void main(String[] args) {
		//read in file
		//for each line:
		//   for each character
		//      if character is letter, replace with random letter
		//	iif character is digit, replace with random digit
		//write out file

		try{
			for(String arg : args){
				if (arg.equals("-debug")){
					IS_DEBUG = true;
					break;
				}
			}
			final File fileIn = new File(getInFileName(args));
			//1- read in file; for each line, set one or more fields in a record
			final List<String> records = readFile(fileIn);
			if (records == null){
				return;
			}
			
			//2- write out 
			writeFile(records, args);
			
			System.out.println("Done");
			
		    } catch (Exception exception){
		    	System.out.println("Exception caught at " + methodName() + ": " + exception);
		    }
	}
	private static List<String> readFile(final File fileIn) throws ParseException{
		Scanner scanner1 = null;
		try{
			final FileInputStream fileInputStream1 = new FileInputStream(fileIn);
			final List<String> records = new ArrayList<>();
			scanner1 = new Scanner(fileInputStream1);
			while(scanner1.hasNextLine()){
				final String line = scanner1.nextLine();
				final String anonymizedLine = anonymize(line);
				records.add(anonymizedLine);				
			}
			return records;
		} catch (FileNotFoundException fnfe){
	    		System.out.println("FileNotFoundException");
		    	return null;
		} finally{
		    	if (scanner1 != null){
		    		scanner1.close();
		    	}
	    }
	}

	private static String anonymize(String line) {
		final StringBuilder sb = new StringBuilder();
		for(final Character c : line.toCharArray()){
			if(Character.isLetter(c)){
				sb.append(randomLetter());
			}
			else if(Character.isDigit(c)){
				sb.append(randomDigit());
			}
			else{
				if (IS_DEBUG){
					System.out.print(c);
				}
				sb.append(c);
			}
			
		}
		return sb.toString();
	}

	private static Character randomLetter(){
		return letters.charAt(random.nextInt(letters.length()));
	}

	private static Character randomDigit(){
		//return int between 0-9; range is from 0 (inclusive) to bound (exclusive)
		return (""+random.nextInt(10)).charAt(0);
	}

	private static void writeFile(List<String> records, String[] args){
		//Write data to file
		try{
			File file = new File(getOutFileName(args));
			Path path = Paths.get(file.toURI());
			OpenOption[] options = {StandardOpenOption.CREATE};
			Charset charset = Charset.forName("UTF-8");
			
			Files.write(path, records, charset, options);
		}
		catch(IOException e){
			System.out.println("\nCaught exception: " + e);		
		}
	}

	private static String getInFileName(String[] args){
		final String defaultInFileName = "/home/dstrube/Downloads/inFile.txt";
		if (args == null || args.length < 1){
			return defaultInFileName;
		}
		for(String arg : args){
			if(arg.startsWith("-in:")){
				return arg.split(":")[1];
			}
		}
		return defaultInFileName;
	}

	private static String getOutFileName(String[] args){
		final String defaultOutFileName = "/home/dstrube/Downloads/outFile.txt";
		if (args == null || args.length < 1){
			return defaultOutFileName;
		}
		for(String arg : args){
			if(arg.startsWith("-out:")){
				return arg.split(":")[1];
			}
		}
		return defaultOutFileName;
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
