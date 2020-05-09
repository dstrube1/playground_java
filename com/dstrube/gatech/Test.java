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
		final String oldName = "./old/file/pa th .txt";
		final String newName = oldName.replace(' ', '_');
		
		File file = new File(oldName);
		File newFile = new File(newName);
		if (file.renameTo(newFile)){
			System.out.println("rename succeeded");
		}else{
			System.out.println("rename failed");
		}
		System.out.println("Done");
	}


}
