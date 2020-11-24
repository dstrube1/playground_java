package com.dstrube.gatech;

//import java.io.BufferedReader;
import java.io.File;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.IOException;

//import java.time.ZonedDateTime;

import java.util.ArrayList;
//https://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
import java.util.HashMap;
//https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
//If a thread-safe implementation is not needed, it is recommended to use HashMap in place of Hashtable. 
//If a thread-safe highly-concurrent implementation is desired, then it is recommended to use ConcurrentHashMap in place of Hashtable.
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
//https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html
import java.util.List;
//https://docs.oracle.com/javase/8/docs/api/java/util/List.html
import java.util.Map;
//https://docs.oracle.com/javase/8/docs/api/java/util/Map.html
import java.util.Map.Entry;
//https://docs.oracle.com/javase/8/docs/api/java/util/Map.Entry.html
import java.util.NoSuchElementException;
import java.util.Scanner;
//https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html

//https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html

/*
Author: David Strube
Date: 2019-06-20

Purpose:
Test

Compile:
Mac / Linux:
javac -d bin com/dstrube/gatech/Test.java 
Run:
java -cp bin com.dstrube.gatech.Test 

or, if compiling and running from source folder:

Compile:
Mac / Linux:
javac -d . Test.java 
Run:
java -cp . Test 

*/

public class Test {
	

	public static void main(final String[] args) {
		
		//fileRename();
		
		final Map<String,String> map = new HashMap<>();
		map.put("key","value1");
		map.put("key","value2");//overwrites previous entry
		for (Map.Entry entry : map.entrySet()){
			System.out.println("entry key: " + entry.getKey());
			System.out.println("entry value: " + entry.getValue());
		}
		System.out.println("size of map: " + map.size());
		
		final List<String> list = new ArrayList<>();
		list.add("value1");
		for(final String item : list){
			System.out.println("item in list: " + item);
		}
		System.out.println("size of list: " + list.size());

		for (int i = 0; i < list.size(); i++){
			System.out.println("indexed item in list: " + list.toArray()[i]);
		}
		
		final String[] strings = {"1","2"};
		System.out.println("size of string array: " + strings.length);
		
		final String ids = "";
		final String id = "1";
		
		final String[] idArray = ids.replace("[", "").replace("]", "").split(",");
		try 
		{
			Integer.parseUnsignedInt(id);
		}
		catch (NumberFormatException numberFormatException) 
		{
			System.err.println("NumberFormatException in ");// + idType + ": " + ids);
			return;// false;
		}
		
		Scanner scanner = new Scanner(System.in);
		boolean completed = false;
		while(!completed){
			try{
				System.out.print("enter a number: ");
				String s = scanner.next();
				final int i = Integer.parseUnsignedInt(s);
				System.out.println("You entered: " + i);
				completed = true;
			}catch(/*InputMismatchException NoSuchElementException*/ NumberFormatException exception){
				//huh, either of these cause this loop not to work
				//scanner.reset();
				//scanner.close();
				scanner = new Scanner(System.in);
				System.out.println("Try again...");
			}
		}
		
		System.out.println("Done");
		
	}
	private static void fileRename(){
		final String oldName = "./old/file/pa th .txt";
		final String newName = oldName.replace(' ', '_');
		
		File file = new File(oldName);
		File newFile = new File(newName);
		if (file.renameTo(newFile)){
			System.out.println("rename succeeded");
		}else{
			System.out.println("rename failed");
		}
	}

}
