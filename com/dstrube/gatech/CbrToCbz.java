//package com.dstrube.gatech;

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
wait until done and then delete the extracted, 
and the cbr?
Compile:
javac -d . CbrToCbz.java 
or
javac -d . com\dstrube\gatech\CbrToCbz.java 
Run:
java -cp . CbrToCbz [path]
example:
java -cp . CbrToCbz 
See also 
https://github.com/dstrube1/playground_java/blob/master/com/dstrube/FolderComparer.java
*/

public class CbrToCbz {
	
	private static final String[] ignoreFolders = {File.separator + "temp", ".git", "puzzlers", "old", "bin"};
	private static final String[] ignoreFiles = {".cbz",".jpg",".png", ".java", ".class"};
	private static final String defaultPath = ".";//"~" + File.separator + "Downloads" + File.separator;
    private static File path = null;
    private static FilenameFilter filter = null;
	private static boolean isDeleteCbrs = false;
	private static final boolean isDebug = false;

	public static void main(final String[] args) {
        if (!doesPathCheckout()){//args)){
            return;
        }
		//System.out.println("doesPathCheckout: yes");//
		
		isDeleteCbrs = getIsDeleteCbrs(args);
		
        setFilter();
		//System.out.println("filter is set");
		//if (true) return;
		
		try{
			final int total = path.listFiles(filter).length;
			int count = 1;
			if (isDebug){
				System.out.println("Found " + total + " items in " + path.getPath());
			}
			for (final File file : path.listFiles(filter)){
				if (file.getPath().endsWith(".cbr")){
					System.out.println("Processing item " + count + " of " + total + " in " + path.getPath());
				}
				if (file.getPath().contains(" ")){
					System.out.println("Path contains space: '" + file.getPath() + "'");
					final File newFile = new File(file.getPath().replace(" ","_"));
					if (newFile.exists()){
						System.out.println("There is a file whose path contains space: '" + file.getPath() + "', and another file with the same name, but with underscores instead of spaces. Sort this out before proceeding.");
						return;
					}
					if (file.renameTo(newFile)){
						System.out.println("rename succeeded");
					}else{
						System.out.println("rename failed");
						return;
					}

				}
				if (!processFile(file)){
					//Error encountered
					return;
				}
				count++;
			}
		}catch(SecurityException securityException){
			System.out.println("Caught securityException");
		}
		System.out.println("Done");
	}

    private static boolean doesPathCheckout(){//final String[] args){
		//TODO If requested, allow for path getting passed in as an argument
		//System.out.println("No path found. Using default " + defaultPath);
		path = new File(defaultPath);
        
		/*if (args.length == 0){
		} else if (args.length > 1){
			System.out.println("More than one (" + args.length + ") path(s) found");
			return false;
		} else{
		    path = new File(args[0]);
        }*/

		if (!path.exists()){
			System.out.println("Path doesn't exist: " + path.getPath());
			return false;
		}
		if (!path.isDirectory()){
			System.out.println("Path is not a directory: " + path.getPath());
			return false;
		}
		if (!path.canRead()){
			System.out.println("Path is not readable: " + path.getPath());
			return false;
		}
		if (!path.canWrite()){
			System.out.println("Path is not writable: " + path.getPath());
			return false;
		}
        return true;
    }

	private static boolean getIsDeleteCbrs(final String[] args){
		if (args.length == 0){
			return false;
		}else if (args[0].equals("-deleteCbrs")){
			return true;
		}
		return false;
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
                if (name.startsWith(".")){
                	System.out.println("Ignoring file named " + name + " because it starts with .");
					return false;
                }
			    if (name.lastIndexOf('.') > 0) {
					for (final String ignoreFile : ignoreFiles) {
						if (name.endsWith(ignoreFile)){
                            System.out.println("Ignoring file named " + name + " because it ends with " + ignoreFile);
							return false;
						}
					}               
			    }               
    	       return true;
            }
		};
    }
    
    private static boolean processFile(final File file){
    	//System.out.println("file: " + file);
    	
    	//Handle case where input file is a folder
    	if (file.isDirectory()){
			final int total = file.listFiles(filter).length;
			int count = 1;
			if (isDebug){
				System.out.println("Found " + total + " items in " + file.getPath());
			}
    		for (final File subFile : file.listFiles(filter)){
				if (subFile.getPath().endsWith(".cbr")){
					System.out.println("Processing item " + count + " of " + total + " in " + file.getPath());
				}
				if (file.getPath().contains(" ")){
					System.out.println("Path contains space: '" + file.getPath() + "'");
					final File newFile = new File(file.getPath().replace(" ","_"));
					if (newFile.exists()){
						System.out.println("There is a file whose path contains space: '" + file.getPath() + "', and another file with the same name, but with underscores instead of spaces. Sort this out before proceeding.");
						return false;
					}
					if (file.renameTo(newFile)){
						System.out.println("rename succeeded");
					}else{
						System.out.println("rename failed");
						return false;
					}

				}

				if (!processFile(subFile)){
					//Error encountered
					return false;
				}
				count++;
			}
			return true;
    	}
    	
    	if (!file.getPath().endsWith(".cbr")){
    		if (isDebug){
    			System.out.println("File is not .cbr. Skipping " + file.getPath());
    		}
            return true;
        }
        
        if (isDebug){
			System.out.println("CBR file: " + file);
		}
		
        //Make sure this file hasn't already been unrarred
        final String fileNameWithExtension = file.getPath().substring(1 + file.getPath().lastIndexOf(File.separator));
        final String fileName = fileNameWithExtension.substring(0, fileNameWithExtension.lastIndexOf("."));
        //System.out.println("File name: " + fileName);
        boolean foundMatchingCbz = false;
        for (final File searchFile : file.getParentFile().listFiles()){//search without filter to find matching cbz file
        	if (searchFile.getPath().endsWith(fileName + ".cbz")) {
            	foundMatchingCbz = true;
                break;
            }
        }
        if (foundMatchingCbz){
        	System.out.println("Skipping this file because it seems to have already been unrarred and zipped: " + file.getPath());
            return true;
        }

		final String pathTempFileName = cleanFileName(fileName);
		final File pathTemp = new File(file.getParentFile().getPath() + File.separator + fileName + "_temp");
		if (!pathTemp.exists()){
			if (isDebug){
				System.out.println("Temp folder for this cbr file doesn't exist. (" + pathTemp.getPath() + ") Creating it.");
			}
            try{
                boolean mkTempResult = pathTemp.mkdir();
                if (!mkTempResult){
        			System.out.println("Failed to create " + pathTemp.getPath());
                    return false;
                }
            }catch(SecurityException securityException){
    			System.out.println("Caught securityException while creating " + pathTemp.getPath());
                return false;
            }
		}else{
			System.out.println("Temp folder exists. (" + pathTemp.getPath() + ") That's weird. \n"
				+ "A previous run didn't complete properly. Fix that before running this again.");
			return false;
        }
        
        if (isDebug){
	        if (!pathTemp.exists()){
    	    	//It should definitely exist now. If it doesn't something went wrong
        		System.out.println("Temp folder for this cbr file doesn't exist. (" + pathTemp.getPath() + ") Something went wrong.");
        		return false;
        	}else{
	        	System.out.println("Temp folder for this cbr file exists. (" + pathTemp.getPath() + ".");
    	    }
        }
        
        //Temp folder should be empty
        if (pathTemp.list().length > 0){
			System.out.println("Temp folder should empty but is not. Please sort this out before retrying: " + pathTemp.getPath());
            return false;
        }
        
        //Unrar this file to a folder in the temp folder
        if (isDebug){
        	System.out.println("Unrarring: " + file.getPath());
        }
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
        final String name = fileName /*pathTemp.list()[0].replace(' ', '_').replaceAll("[^a-zA-Z0-9_]","") */+ ".cbz";
        //System.out.println("Name: " + name);

        //Output a zip file to this folder with this name (param 1), using these contents (param 2)
        runProcZip(file.getParentFile().getPath() + File.separator + name, pathTemp.getPath()+"/.");

        //Clean up after yourself
        runProcCleanTemp(pathTemp.getPath(), true);
    	return true;
    }
	
	private static String cleanFileName(String fileName){
		return fileName.replace(' ', '_');//.replaceAll("[^a-zA-Z0-9_]","");
	}
		
	private static void runProcUnrar(final String inputFilePath){
		try{
            final File file = new File(inputFilePath);
            final String inputFileName = inputFilePath.substring(1 + inputFilePath.lastIndexOf(File.separator), inputFilePath.lastIndexOf("."));
            final String tempPath = inputFileName + "_temp";
            //final String inputFilePath = file.getPath();
            final String parentPath = file.getParent();
			final String procPath = "unrar";
			final String command = procPath + " x " + inputFilePath + " " + parentPath + File.separator + tempPath + File.separator;
            if (isDebug){
	            System.out.println("inputFileName = " + inputFileName + "; \ninputFilePath = " + inputFilePath + "; \nparentPath = " + parentPath + "; \ncommand = " + command);
            }
            
            final File pathTemp = new File(parentPath + File.separator + tempPath);
            if (isDebug){
		        if (!pathTemp.exists()){
    	    	//It should definitely exist now. If it doesn't something went wrong
	        		System.out.println("Temp folder for this cbr file doesn't exist. (" + pathTemp.getPath() + ") Something went wrong.");
    	    		return;
        		}else{
	        		System.out.println("Temp folder for this cbr file exists. (" + pathTemp.getPath() + ".");
    		    }
    	    }
			final Process process = Runtime.getRuntime().exec(command);
			final InputStream is = process.getInputStream();
			final InputStreamReader isr = new InputStreamReader(is);
			final BufferedReader br = new BufferedReader(isr);
			String line;

			if (isDebug){
				System.out.printf("Output of running %s is:\n", procPath);
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
			}
            process.waitFor();
            //System.out.println("exit: " + process.exitValue());
            process.destroy();
		}catch(InterruptedException interruptedException){
			System.out.println("Caught InterruptedException");
		}catch(IOException ioException){
			System.out.println("Caught IOException");
			ioException.printStackTrace();
		}
    }

	private static void runProcZip(final String outputPath, final String inputPath){
		try{
			final String procPath = "zip";
			final Process process = Runtime.getRuntime().exec(procPath + " -r \"" + outputPath + "\" \"" + inputPath + "\"");
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
		}catch(IOException ioException){
			System.out.println("Caught IOException");
			ioException.printStackTrace();
		}
    }

	private static void runProcCleanTemp(final String pathTemp, final boolean isRootTemp){
		try{
            final File temp = new File(pathTemp);
            if (temp == null || temp.length() == 0){
                System.out.println("Invalid path");
                return;
            }
            
			for (final File file : temp.listFiles()){//No filter = delete everything in this folder
                if (file.isDirectory()){
                    //Delete everything in the directory before deleting the directory itself
                    runProcCleanTemp(file.getPath(), false);
                }
				//System.out.print("file: " + file);
                if (file.delete()){
                    //System.out.println(" - deleted");
                }else{
                    System.out.println(file.getPath() + " - not deleted");
                }
            }
            if (isRootTemp){
	            if (temp.delete()){
    	            //System.out.println(" - deleted");
        	    }else{
                	System.out.println(temp.getPath() + " - not deleted");
            	}
            }

 		}catch(SecurityException securityException){
			System.out.println("Caught securityException");
		}
    }

}