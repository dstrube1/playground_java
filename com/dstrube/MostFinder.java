package com.dstrube;

/*
From ~/java:
Mac / Linux:
javac -d bin com/dstrube/MostFinder.java
java -cp bin com.dstrube.MostFinder [-in:inFile.txt -out:outFile.txt -debug]
Windows:
javac -d bin com\dstrube\MostFinder.java
java -cp bin com.dstrube.MostFinder [-in:inFile.txt -out:outFile.txt -debug]
*/

import java.util.*;
import java.util.stream.*;
import java.io.*;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.OpenOption;

import java.text.ParseException;

public class MostFinder {
	private static boolean IS_DEBUG = false;

	public static void main(String[] args) {
		//read in file
		//for each line:
		//   if line is a bit of code 
		//	strip off line number etc
		//	if bit of code is not in the list, add it to the list with count of 1
		//	else increment count
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
			final Map<String, Integer> map = readFile(fileIn);
			if (map == null){
				return;
			}
			/*if (IS_DEBUG){
				System.out.println("Size of map: " + map.size());
			}*/
			
			final List<String> list = getListFromMap(map);
			if (IS_DEBUG){
				System.out.println("Size of list: " + list.size());
				return;
			}
			
			//2- write out 
			writeFile(list, args);
			
			System.out.println("Done");
			
		    } catch (Exception exception){
		    	System.out.println("Exception caught at " + methodName() + ": " + exception);
		    }
	}

	private static Map<String, Integer> readFile(final File fileIn) throws ParseException{
		Scanner scanner1 = null;
		try{
			final FileInputStream fileInputStream1 = new FileInputStream(fileIn);
			Map<String, Integer> map = new HashMap<>();
			scanner1 = new Scanner(fileInputStream1);
			while(scanner1.hasNextLine()){
				final String line = scanner1.nextLine();
				/*if (IS_DEBUG){
					System.out.println("Checking line : " + line);
					break;
				}*/
				if(line.contains("<li>")){
					map = addToMap(line, map);
					/*if (IS_DEBUG){
						System.out.println("Adding line : " + line);
						break;
					}*/
				}
			}
			return map;
		} catch (FileNotFoundException fnfe){
	    		System.out.println("FileNotFoundException");
		    	return null;
		} finally{
		    	if (scanner1 != null){
		    		scanner1.close();
		    	}
	    }
	}

	private static List<String> getListFromMap(Map<String, Integer> map){
		List<String> list = new ArrayList<>();
		//sort map by values in descending order: https://stackoverflow.com/questions/55644583/how-to-sort-a-hashmap-based-on-values-in-descending-order
		LinkedHashMap<String, Integer> map2 = 
			map.entrySet()
			.stream()             
			.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			.collect(Collectors.toMap(e -> e.getKey(), 
                                 e -> e.getValue(), 
                                 (e1, e2) -> null, // or throw an exception
                                 () -> new LinkedHashMap<String, Integer>()));

		//add map keys and values to list:
		for(Map.Entry<String,Integer> entry : map2.entrySet()){
			list.add(entry.getKey() + ":" + entry.getValue());
		}
		return list;
	}

	private static Map<String, Integer> addToMap(String line, Map<String, Integer> map){
		final String LI = "<li>";
		while(line.contains(LI)){
			int firstLi = line.indexOf(LI) + LI.length();
			int stop = line.indexOf(LI, firstLi + 1);
			if (stop == -1){
				stop = line.indexOf("</td>", firstLi + 1);
			}
			/*if (IS_DEBUG){
				System.out.println("From line : " + line);
				System.out.println("where first li = " + firstLi);
				System.out.println("and stop = " + stop);
			}*/
			String key = line.substring(firstLi, stop);
			if (key.contains(":")){
				key = key.substring(0, key.indexOf(":"));
			}
			if (IS_DEBUG){
				System.out.println("key : " + key);
				//break;
			}
			line = line.substring(stop);
			if(! map.containsKey(key)){
				map.put(key,1);
			}else{
				int value = map.get(key);
				map.put(key, value + 1);
			}
			/*if (IS_DEBUG){
				System.out.println("Added key : " + key);
				break;
			}*/
		}
		return map;
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
