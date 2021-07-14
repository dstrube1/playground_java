package com.dstrube;

/*
From ~/java:

javac -d bin com/dstrube/BigFileMaker.java
java -cp bin com.dstrube.BigFileMaker

For now just making files with big file names, not big contents
*/

import java.io.FileOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BigFileMaker {
	private static final int chunkSize = 1;
	
	public static void main(String[] args) {
		String fileNames = "abcdefghijklmnopqrstuvwxyz1234567890";
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < fileNames.length(); i++){
			//System.out.println("char at " + i + ": " + fileNames.charAt(i));
			sb = new StringBuilder();
			for (int j = 0; j < 250; j++){
				sb.append(fileNames.charAt(i));
			}
			//System.out.println("filename at " + i + ": " + sb.toString()+".txt");
			try {
				final File file = new File(sb.toString()+".txt");
				if (file.exists()){
					System.out.println("File exists: " + file);
					continue;
					//return;
				}
				final FileOutputStream fos = new FileOutputStream(file);
				final byte[] bytes = new byte[chunkSize];
				fos.write(bytes);
				fos.close();
			} catch (FileNotFoundException fileNotFoundException) {
				//Happens if try to make the file names too big
				fileNotFoundException.printStackTrace();
				return;
			} catch(IOException ioException){
				ioException.printStackTrace();
				return;
			}
			//break;
		}
		System.out.println("Done");
	}
	
}
