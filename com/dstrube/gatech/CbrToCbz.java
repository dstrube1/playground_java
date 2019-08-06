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
for each cbr file in a dir:
unrar files from the cbr
zip them as cbz
wait until done and then delete the extracteds, 
and the cbr?

Compile:
Windows:
javac -d bin com\dstrube\gatech\CbrToCbz.java 
Mac / Linux:
javac -d bin com/dstrube/gatech/CbrToCbz.java 
Run:
java -cp bin com.dstrube.gatech.CbrToCbz [path]
example:
java -cp bin com.dstrube.gatech.CbrToCbz ~/Downloads

See also 
https://github.com/dstrube1/playground_java/blob/master/com/dstrube/FolderComparer.java

*/

public class CbrToCbz {
	
	private static final String[] ignoreFolders = {File.separator + "temp"};
	private static final String[] ignoreFiles = {".cbz",".jpg",".png"};
	private static final String defaultPath = "~" + File.separator + "Downloads" + File.separator;
    private static File path = null;
    private static FilenameFilter filter = null;

	public static void main(final String[] args) {
        if (!doesPathCheckout(args)){
            return;
        }

        setFilter();

		final File pathTemp = new File(path.getPath() + File.separator + "temp");
		if (!pathTemp.exists()){
			System.out.println("Folder temp doesn't exist. (" + pathTemp.getPath() + ") Creating it...");
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
			System.out.println("Temp folder should empty but is not. Please sort this out before retrying: " + pathTemp.getPath());
            return;
        }
		
		try{
			for (final File file : path.listFiles(filter)){
				//System.out.println("file: " + file);
                if (!file.getPath().endsWith(".cbr")){
                    //System.out.println("File is not .cbr. Skipping " + file.getPath());
                    continue;
                }
				//System.out.println("CBR file: " + file);

                //Make sure this file hasn't already been unrarred
                final String fileNameWithExtension = file.getPath().substring(1 + file.getPath().lastIndexOf(File.separator));
                final String fileName = fileNameWithExtension.substring(0, fileNameWithExtension.lastIndexOf("."));
                //System.out.println("File name: " + fileName);
                boolean foundMatchingCbz = false;
                for (final File searchFile : path.listFiles()){//search without filter to find matching cbz file
                    if (searchFile.getPath().endsWith(fileName + ".cbz")) {
                        foundMatchingCbz = true;
                        break;
                    }
                }
                if (foundMatchingCbz){
                    System.out.println("Skipping this file because it seems to have already been unrarred and zipped: " + file.getPath());
                    continue;
                }

                //Unrar this file to a folder in the temp folder
                runProcUnrar(file.getPath());

                //There should then be just one item (the afore mentioned folder) in temp
                /*if (pathTemp.list().length != 1){
                    System.out.print("Something is wrong. ");
                    if (pathTemp.list().length > 1){
                        System.out.println("More than one item in temp folder after unrarring: " + pathTemp.getPath());
                    }else{
                        System.out.println("No item in temp folder after unrarring: " + pathTemp.getPath());
                    }
                    break;
                }*/

                //Make a name for the new cbz file
                final String name = pathTemp.list()[0].replace(' ', '_').replaceAll("[^a-zA-Z0-9_]","") + ".cbz";
                //System.out.println("Name: " + name);

                //Output a zip file to this folder with this name (param 1), using these contents (param 2)
                runProcZip(path.getPath() + File.separator + name, pathTemp.getPath()+"/.");

                //Clean up after yourself
                runProcCleanTemp(pathTemp.getPath());
			}
		}catch(SecurityException securityException){
			System.out.println("Caught securityException");
		}
		System.out.println("Done");
	}

    private static boolean doesPathCheckout(final String[] args){
        if (args.length == 0){
			System.out.println("No path found. Using default " + defaultPath);
			path = new File(defaultPath);
		} else if (args.length > 1){
			System.out.println("More than one (" + args.length + ") path(s) found");
			return false;
		} else{
		    path = new File(args[0]);
        }

		if (!path.exists()){
			System.out.println("Path doesn't exist: " + args[0]);
			return false;
		}
		if (!path.isDirectory()){
			System.out.println("Path is not a directory: " + args[0]);
			return false;
		}
		if (!path.canRead()){
			System.out.println("Path is not readable: " + args[0]);
			return false;
		}
		if (!path.canWrite()){
			System.out.println("Path is not writable: " + args[0]);
			return false;
		}
        return true;
    }

    private static void setFilter(){
		//from https://www.tutorialspoint.com/java/io/file_listfiles_filename_filter.htm
		filter = new FilenameFilter() {
   
			@Override
			public boolean accept(final File dir, final String name) {
                final File file = new File(dir.getPath() + File.separator + name);
                //System.out.println("in accept : " + file.getPath());
				if (file.isDirectory()){
                //new Exception().printStackTrace();
					for (final String ignoreFolder : ignoreFolders) {
						if (file.getPath().contains(ignoreFolder)){
                            //System.out.println("Ignoring folder " + ignoreFolder);
							return false;
						}
                        //System.out.println("Not ignoring folder " + file);
					}
					return true;
				}
                //System.out.println("in accept is not dir?: " + file.getPath() );
			    if (name.lastIndexOf('.') > 0) {
					for (final String ignoreFile : ignoreFiles) {
						if (name.endsWith(ignoreFile)){
                            //System.out.println("Ignoring file named " + name + " because it ends with " + ignoreFile);
							return false;
						}
					}               
			    }               
    	       return true;
            }
		};
    }
		
	private static void runProcUnrar(final String inputFilePath){
		try{
            final File file = new File(inputFilePath);
            //final String inputFilePath = file.getPath();
            final String parentPath = file.getParent();
			final String procPath = "unrar";
			final Process process = Runtime.getRuntime().exec(procPath + " x " + inputFilePath + " " + parentPath + File.separator + "temp" + File.separator + ".");
			final InputStream is = process.getInputStream();
			final InputStreamReader isr = new InputStreamReader(is);
			final BufferedReader br = new BufferedReader(isr);
			String line;

			//System.out.printf("Output of running %s is:\n", procPath);
			while ((line = br.readLine()) != null) {
				//System.out.println(line);
			}
            process.waitFor();
            //System.out.println("exit: " + process.exitValue());
            process.destroy();
		}catch(InterruptedException interruptedException){
			System.out.println("Caught InterruptedException");
		}catch(IOException exception){
			System.out.println("Caught IOException");
		}
    }

	private static void runProcZip(final String outputPath, final String inputPath){
		try{
			final String procPath = "zip";
			final Process process = Runtime.getRuntime().exec(procPath + " -r " + outputPath + " " + inputPath);
			final InputStream is = process.getInputStream();
			final InputStreamReader isr = new InputStreamReader(is);
			final BufferedReader br = new BufferedReader(isr);
			String line;

			//System.out.printf("Output of running %s is:\n", procPath);
			while ((line = br.readLine()) != null) {
				//System.out.println(line);
			}
            process.waitFor();
            //System.out.println("exit: " + process.exitValue());
            process.destroy();
		}catch(InterruptedException interruptedException){
			System.out.println("Caught InterruptedException");
		}catch(IOException exception){
			System.out.println("Caught IOException");
		}
    }

	private static void runProcCleanTemp(final String pathTemp){
		try{
            final File temp = new File(pathTemp);
            if (temp == null || temp.length() == 0){
                System.out.println("Invalid path");
                return;
            }
            
			for (final File file : temp.listFiles()){//No filter = delete everything in this folder
                if (file.isDirectory()){
                    //Delete everything in the directory before deleting the directory itself
                    runProcCleanTemp(file.getPath());
                }
				//System.out.print("file: " + file);
                if (file.delete()){
                    //System.out.println(" - deleted");
                }else{
                    //System.out.println(" - not deleted");
                }
            }
 		}catch(SecurityException securityException){
			System.out.println("Caught securityException");
		}
    }

}
