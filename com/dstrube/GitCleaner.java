package com.dstrube;

/*
From ~/java:

javac -d bin com/dstrube/GitCleaner.java
java -cp bin com.dstrube.GitCleaner [path]

*/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.util.stream.Stream;

public class GitCleaner{
	
	public static void main(String[] args){
		try{
			//System.out.println("getMethodName(): " + getMethodName());
			
			if (args.length == 0){
				System.out.println("Please provide path of where to start");
				return;
			}
			
			final Path path = FileSystems.getDefault().getPath(args[0]);
			if (Files.exists(path) && Files.isDirectory(path)){
				deleteGitStuff(path, false);
			}
			else{
				System.out.println("Path doesn't exist: " + args[0]);
			}
			

		}catch(Exception exception){
			System.out.println("Caught exception: " + exception);
		}
		System.out.println("Done");
	}
	
	private static String getMethodName(){
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		return name;
	}
	
	private static void deleteGitStuff(Path path, boolean inGitFolder) throws IOException {
		Stream<Path> pathStream = Files.list(path);
		Object[] paths = pathStream.toArray();
		for(Object po : paths){
			Path p = (Path) po;
			//System.out.println("path: " + p);
			if (p.toString().endsWith("/.gitignore")){
				System.out.println("Deleting: " + p);
				Files.delete(p);
			}else if (Files.isDirectory(p)){
				if (p.toString().endsWith("/.git")){
					deleteGitStuff(p, true);
					System.out.println("Deleting: " + p);
					Files.delete(p);
				}else if (p.toString().contains("/.git/")){
					deleteGitStuff(p, true);
					Files.delete(p);
				}else{
					deleteGitStuff(p, false);
				}
			}else if (inGitFolder){
				//delete it
				System.out.println("Deleting: " + p);
				Files.delete(p);
			}//else, skip it
		}
		//System.out.println();
	}
	
	
}