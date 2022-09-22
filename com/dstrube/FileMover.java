package com.dstrube;

/*
Takes in files from given folder, 
and moves them to a better place with better names to be more easily played on a certain player

commands to compile and run:
from ~/java
javac -d bin com/dstrube/FileMover.java
java -cp bin com.dstrube.FileMover

*/

import java.io.File;

public class FileMover {
	
	public static void main(String[] args) {
		try {
			//Verify dir exists
			final String[] folders = {"Users","dstrube","Downloads","JP",
				""};
			final StringBuilder sb = new StringBuilder();
			for (String folder : folders){
				sb.append(File.separator);
				sb.append(folder);
			}
			sb.append(File.separator);
			final String DIRECTORY_PATH = sb.toString();
			final File dir = new File(DIRECTORY_PATH);
			if (!dir.exists()){
				System.out.println("Directory not exists: " + dir);
				return;
			}
			
			//https://zetcode.com/java/listdirectory/
			final File[] dirList = dir.listFiles();
        	for (final File subDir: dirList) {
        		//System.out.println("subDir: " + subDir);
        		final String disc = subDir.toString().substring(subDir.toString().length() - 2);
        		//System.out.println("disc: " + disc);
            	final File[] subDirList = subDir.listFiles();
            	boolean error = false;
            	for(final File file : subDirList)
            	{
            		//System.out.println("file: " + file);
            		if (file.toString().endsWith(".mp4")){
            			final String old = file.toString();
            			//https://docs.oracle.com/javase/7/docs/api/java/lang/String.html
            			final String newName = disc + " - " + old.substring(1 + old.lastIndexOf(File.separator), old.lastIndexOf(File.separator) + 3) + ".mp4";
            			final File newFile = new File(DIRECTORY_PATH + newName);
            			if(newFile.exists()){
            				System.out.println("Error: newFile exists: " + newFile);
            				error = true;
         					break;
            			}
            			//https://www.tutorialspoint.com/javaexamples/file_rename.htm
            			if(file.renameTo(newFile)) {
                    		//System.out.println("renamed to " + newName);
			      		} else {
         					System.out.println("Error renaming " + file + " to " + newFile);
         					error = true;
         					break;
      					}
      					//System.out.println("renamed to " + newName);
            		}else{
            			//System.out.println("Skipping file: " + file);
            		}
	            	//break;
            	}
            	if (error)
            		break;
        	}
			
				
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		System.out.println("Done");
	}
	
}