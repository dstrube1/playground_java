package com.dstrube;

/*
Takes in binary data of all files from given folder, 
and glues them all together into a specified output file

commands to compile and run:
from ~/java
javac -d bin com/dstrube/FileUnsplitter.java
java -cp bin com.dstrube.FileUnsplitter

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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
//https://docs.oracle.com/javase/8/docs/api/java/util/List.html

public class FileUnsplitter {
	private static final String outFileName = "";
	
	public static void main(String[] args) {
		try {
			//Create the dir if it doesn't exist
			final String[] folders = {"Users","dstrube","Downloads","torrents",
				"temp"};
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
			
			final File outFile = new File(DIRECTORY_PATH + outFileName);
			if (outFile.exists()){
				System.out.println("Out file exists: " + outFile);
				return;
			}
			List<String> list = new ArrayList<>();
			for (File file : dir.listFiles()){
				//listFiles are in random order
				list.add(file.getName());
			}
			//sorting by default sorter (sort(null)) sorts alphabetically: 10 before 2
			//we want to sort numerically
			final Comparator<String> ca = new SortByIntAscending();
			list.sort(ca);
			final List<Object> byteList = new ArrayList<>();
			int totalSize = 0;
			for (String name : list){
				//System.out.println("Name: " + name);
				final File inFile = new File(DIRECTORY_PATH + name);
				final FileInputStream fis = new FileInputStream(inFile);
				int length = (int)inFile.length();
				totalSize += length;
				final byte[] bytesIn = new byte[length];
				final int readResult = fis.read(bytesIn, 0, length);
				if (readResult == -1){
					System.out.println("Unexpectedly reached end of file");
					fis.close();
					return;
				}
				byteList.add(bytesIn);
				fis.close();
			}
			final byte[] bytesOut = new byte[totalSize];
			int offset = 0;
			for(int i = 0; i < byteList.size(); i++){
				Object o = byteList.get(i);
				byte[] b = (byte[])o;
				System.arraycopy(o, 0, bytesOut, offset, b.length);
				offset += b.length;
			}
			System.out.println("Length of bytesOut: " + bytesOut.length);
			System.out.println("Size of byteList: " + byteList.size());
			final FileOutputStream fos = new FileOutputStream(outFile);
			fos.write(bytesOut);
			fos.close();
				
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		System.out.println("Done");
	}
	
	static final class SortByIntAscending implements Comparator<String> { 
    	public int compare(String a, String b) {
    		//https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html
    		Integer ai = Integer.parseUnsignedInt(a);
    		Integer bi = Integer.parseUnsignedInt(b);
	    	//to sort ascending:
	    	return ai.compareTo(bi);
	    } 
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