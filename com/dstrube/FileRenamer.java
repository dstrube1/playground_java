package com.dstrube;

/*

javac -d bin com/dstrube/FileRenamer.java
java -cp bin com.dstrube.FileRenamer [-in:inPath -debug]

This renames all files in a given directory. If it has an extension, skip it; otherwise, add .txt
*/

import java.util.*;
import java.io.*;

public class FileRenamer {
	private static boolean IS_DEBUG = false;

	public static void main(String[] args) {
		try{
			for(String arg : args){
				if (arg.equals("-debug")){
					IS_DEBUG = true;
					break;
				}
			}
			
			//0- get the path
			final File pathIn = new File(getInPath(args));

			//1- get list of files
			final List<String> files = listFilesForFolder(pathIn);
			if (files == null || files.size() == 0){
				System.out.println("No files found at " + pathIn);
				return;
			}
			
			//2- rename what needs to be renamed
			renameFiles(files);
			
			System.out.println("Done");
			
		    } catch (Exception exception){
		    	System.out.println("Exception caught at " + methodName() + ": " + exception);
		    }
	}

	private static String getInPath(String[] args){
		final String defaultInPath = "/home/dstrube/Downloads/temp";
		if (args == null || args.length < 1){
			return defaultInPath;
		}
		for(String arg : args){
			if(arg.startsWith("-in:")){
				return arg.split(":")[1];
			}
		}
		return defaultInPath;
	}

	private static List<String> listFilesForFolder(final File folder) {
		List<String> files = new ArrayList<>();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				System.out.println("Skipping subdir " + fileEntry.getName());
			} else {
				files.add(fileEntry.getPath());
			}
		}
		return files;
	}

	private static void renameFiles(List<String> fileNames){
		//
		for(String fileName : fileNames){
			int index = fileName.indexOf(".");
			int lastSlash = fileName.lastIndexOf(File.separator);
			if (index != -1){
				if (IS_DEBUG){
					System.out.println("Skipping because dot found: " + fileName);	
				}
				continue;
			}
			/* TODO
			if (index <= lastSlash){
				if (IS_DEBUG){
					System.out.println("Skipping because index=" + index + " and lastSlash=" + lastSlash + ": " + fileName);
				}
				continue;
			}*/	
			
			File file = new File(fileName);
			File file2 = new File(fileName + ".txt");
			if (file2.exists()){
				System.out.println("File already exists: " + file2.getName());
			}
			boolean success = file.renameTo(file2);
			if (!success) {
				System.out.println("Rename unsuccessful: " + file2.getName());
			}else if (IS_DEBUG){
				System.out.println("Successfully renamed " + file.getName() + " to " + file2.getName());
			}
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
