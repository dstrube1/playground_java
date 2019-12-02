//package com.dstrube.gatech;

import java.io.BufferedReader;
import java.io.File;
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

or, if compiling and running from source folder:

Compile:
Mac / Linux:
javac -d . Test.java 
Run:
java -cp . Test 

*/

public class Test {
	

	public static void main(final String[] args) {
		final String oldName = "./test/The Dark Knight Strikes Again 03 (of 3) (2002) (Digital) (Zone-Empire).cbr";
		final String newName = oldName.replace(' ', '_');
		
		File file = new File(oldName);
		File newFile = new File(newName);
		if (file.renameTo(newFile)){
			System.out.println("rename succeeded");
		}else{
			System.out.println("rename failed");
		}
		/*
		try{
            final String command = "unrar x "+
            	"./test" +
            	"/The_Dark_Knight_Strikes_Again_02_(of_3)_(2002)_(Digital)_(Zone-Empire).cbr " +
            	"./test" +
            	"/3_temp/";
            System.out.println("command: '" + command + "'");
			final Process process = Runtime.getRuntime().exec(command);
			final InputStream is = process.getInputStream();
			final InputStreamReader isr = new InputStreamReader(is);
			final BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
            process.waitFor();
            //System.out.println("exit: " + process.exitValue());
            process.destroy();
		}catch(InterruptedException interruptedException){
			System.out.println("Caught InterruptedException");    
 		}catch(IOException iOException){
			System.out.println("Caught IOException");
		}*/
		System.out.println("Done");
	}


}
