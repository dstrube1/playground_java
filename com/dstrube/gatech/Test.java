package com.dstrube.gatech;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
/*
Author: David Strube
Date: 2019-06-20

Purpose:
Test

Compile:
Mac / Linux:
javac -d bin com/dstrube/gatech/Test.java 
Run:
java -cp bin com.dstrube.gatech.Test ~/Downloads

*/

public class Test {
	
	private static final String[] ignoreFolders = {"/temp"};
	private static final String[] ignoreFiles = {".cbz"};
	
	public static void main(final String[] args) {
        File path = null;		
        if (args.length == 0){
            final String defaultPath = "~/Downloads/";
			System.out.println("No path found. Using default " + defaultPath);
			path = new File(defaultPath);
		} else if (args.length > 1){
			System.out.println("More than one path found");
			return;
		} else{
		    path = new File(args[0]);
        }

		if (!path.exists()){
			System.out.println("Path doesn't exist: " + args[0]);
			return;
		}
		if (!path.isDirectory()){
			System.out.println("Path is not a directory: " + args[0]);
			return;
		}
		if (!path.canRead()){
			System.out.println("Path is not readable: " + args[0]);
			return;
		}
		if (!path.canWrite()){
			System.out.println("Path is not writable: " + args[0]);
			return;
		}
		//from https://www.tutorialspoint.com/java/io/file_listfiles_filename_filter.htm
		final FilenameFilter filter = new FilenameFilter() {
   
			@Override
			public boolean accept(final File dir, final String name) {
                final File file = new File(dir.getPath() + File.separator + name);
                //System.out.println("in accept : " + file.getPath());
				if (file.isDirectory()){
                //new Exception().printStackTrace();
					for (final String ignoreFolder : ignoreFolders) {
						if (file.getPath().contains(ignoreFolder)){
							return false;
						}
					}
					return true;
				}
                //System.out.println("in accept is not dir?: " + file.getPath() );
			    if (name.lastIndexOf('.') > 0) {
					for (final String ignoreFile : ignoreFiles) {
						if (name.endsWith(ignoreFile)){
                            //System.out.println("name " + name + " ends with " + ignoreFile);
							return false;
						}
					}
               
					// get last index for '.' char
					final int lastIndex = name.lastIndexOf('.');
                  
					// get extension
					String str = name.substring(lastIndex);
                  
					// match path name extension
					if (str.equals(".cbr")) {
						return true;
					}
			    }
               
    	       return true;
            }
		};
		//end filter

		final File pathTemp = new File(path.getPath()+"/temp");
		if (!pathTemp.exists()){
			System.out.println("Path +/temp doesn't exist. (" + pathTemp.getPath() + ") Creating it...");
            try{
                boolean mkTempResult = pathTemp.mkdir();
                if (!mkTempResult){
        			System.out.println("Failed to create " + pathTemp.getPath());
                    return;
                }
            }catch(SecurityException securityException){
    			System.out.println("Caught securityException while creating " + pathTemp.getPath());
                return;
            }
		}
        
        //Temp folder should be empty
        if (pathTemp.list().length > 0){
			System.out.println("Temp folder is not empty: " + pathTemp.getPath());
            //return;
        }

		try{
			for (final File file : path.listFiles(filter)){
//				System.out.println("file: " + file);
            }
            System.out.println("pathTemp: '" + pathTemp.getPath() + "'");
            
            runProcCleanTemp(pathTemp);
 		}catch(SecurityException securityException){
			System.out.println("Caught securityException");
		}
		System.out.println("Done");
	}
	
	private static void runProcCleanTemp(final File pathTemp){
		try{
            if (pathTemp == null || pathTemp.length() == 0){
                System.out.println("Invalid path");
                return;
            }
            
			for (final File file : pathTemp.listFiles()){
				System.out.print("file: " + file);
                if (file.delete()){
                    System.out.println(" - deleted");
                }else{
                    System.out.println(" - not deleted");
                }
            }
/*
			final String procPath = "rm";
			final Process process = Runtime.getRuntime().exec("sudo rm -rf /home/david/Downloads/temp/*");//procPath + " -rf " + path + "/*");
			final InputStream is = process.getInputStream();
			final InputStreamReader isr = new InputStreamReader(is);
			final BufferedReader br = new BufferedReader(isr);
			String line;

			System.out.printf("Output of running %s is:\n", procPath);
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
            process.waitFor();
            System.out.println("exit: " + process.exitValue());
            process.destroy();*/
		//}catch(IOException iOexception){
			//System.out.println("Caught IOException");
//		}catch(InterruptedException interruptedException){
	//		System.out.println("Caught InterruptedException");
 		}catch(SecurityException securityException){
			System.out.println("Caught securityException");
		}
    }

}
