package com.dstrube;

/*
Takes in binary data of a file from given folder, 
and splits it out into a specified output folder, in as many files as needed

commands to compile and run:
from ~/java
javac -d bin com/dstrube/FileSplitter.java
java -cp bin com.dstrube.FileSplitter

*/

import java.io.File;
//https://docs.oracle.com/javase/8/docs/api/java/io/File.html
import java.io.FileInputStream;
//https://docs.oracle.com/javase/8/docs/api/java/io/FileInputStream.html
import java.io.FileOutputStream;
//https://docs.oracle.com/javase/8/docs/api/java/io/FileOutputStream.html
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSplitter {
	private static final String inFileName = "";
	private static final int chunkSize = 23 * 1000 * 1000; //23 MB
	
	public static void main(String[] args) {
		try {
			//Create the dir if it doesn't exist
			final String[] folders = {"Users","dstrube","Downloads","torrents",
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
				if (!createDir(DIRECTORY_PATH)){
					return;
				}
				System.out.println("Created directory " + dir);
			}
			
			final File file = new File(DIRECTORY_PATH + inFileName);
			if (!file.exists()){
				System.out.println("File not exists: " + file);
				return;
			}
			long length = file.length();
			System.out.println("File size: " + length);
			System.out.println("File size / chunkSize: " + (length / chunkSize));
			final int fullChunks = (int)(length / chunkSize);
			System.out.println("File size % chunkSize: " + (length % chunkSize));
			final int sizeOfLastChunk = (int)(length % chunkSize);
			
			final FileInputStream fis = new FileInputStream(file);
			int i = 0;
			for (; i < fullChunks; i++){
				//System.out.println("File " + i + " size = " + chunkSize);
				final File fileOut = new File(DIRECTORY_PATH + i);
				if (fileOut.exists()){
					System.out.println("File out exists: " + fileOut);
					fis.close();
					return;
				}
				final FileOutputStream fos = new FileOutputStream(fileOut);
				final byte[] bytes = new byte[chunkSize];
				final int readResult = fis.read(bytes, 0, chunkSize);
				if (readResult == -1){
					System.out.println("Unexpectedly reached end of file");
					fis.close();
					fos.close();
					return;
				}
				fos.write(bytes);
				fos.close();
			}
			if (sizeOfLastChunk > 0){
				//System.out.println("File " + i + " size = " + sizeOfLastChunk);
				final File fileOut = new File(DIRECTORY_PATH + i);
				if (fileOut.exists()){
					System.out.println("File out exists: " + fileOut);
					return;
				}
				final FileOutputStream fos = new FileOutputStream(fileOut);
				final byte[] bytes = new byte[sizeOfLastChunk];
				final int readResult = fis.read(bytes, 0, sizeOfLastChunk);
				if (readResult != -1 && readResult != sizeOfLastChunk){
					System.out.println("Expected readResult == -1 or "+sizeOfLastChunk+"; instead got : " + readResult);
				}
				fos.write(bytes);
				fos.close();
			}
			fis.close();
				
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		System.out.println("Done");
	}
	private static boolean createDir(String path) throws IOException{
		//from https://stackoverflow.com/questions/3634853/how-to-create-a-directory-in-java
		
		//If the directory already exists, this should still return true;
		try{
			Files.createDirectories(Paths.get(path));
		} 
		catch(SecurityException se){
			System.out.println("SecurityException creating " + path);
			return false;
		}   
		return true;
	}
	
}