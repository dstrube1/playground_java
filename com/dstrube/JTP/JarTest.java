package com.dstrube.JTP; //JarTestPackage

/*

Testing JAR stuff.
Starting here:
https://docs.oracle.com/javase/tutorial/deployment/jar/basicsindex.html
https://docs.oracle.com/javase/tutorial/deployment/jar/build.html

From ~/java:

Mac:
javac com/dstrube/JTP/JarTest.java
java com.dstrube.JTP.JarTest

Windows:
javac com\dstrube\JTP\JarTest.java
java com.dstrube.JTP.JarTest

Unlike my other java applications (where I put the class files in the bin folder structure),
this one compiles the class file to the same folder as the source (.java) file.
While this a little messier than what I normally do, 
this makes it easier to create a jar file that can be executed later on.

Create the jar file with manifest file:
javac com/dstrube/JTP/JarTest.java
jar cmvf0 com/dstrube/JTP/META-INF/MANIFEST.MF JarTest.jar
jar vuf0 JarTest.jar com/dstrube/JTP/JarTest.class
mv JarTest.jar com/dstrube/JTP/.

Verify jar structure and move it into place:
jar tf JarTest.jar
or
jar tf com/dstrube/JTP/JarTest.jar

This does not work:
jar cmvf0 com/dstrube/JTP/META-INF/MANIFEST.MF com/dstrube/JTP/JarTest.jar com/dstrube/JTP/JarTest.jar com/dstrube/JTP/JarTest.class

This is an important note about the manifest file:
Warning: The text file must end with a new line or carriage return. The last line will not be parsed properly if it does not end with a new line or carriage return.
from: 
https://docs.oracle.com/javase/tutorial/deployment/jar/appman.html

To run the jar:
java –jar JarTest.jar
or
java –jar com.dstrube.JTP.JarTest.jar
or 
java –jar com/dstrube/JTP/JarTest.jar

jar --verbose --create --no-compress --file=com/dstrube/JTP/JarTest.jar --manifest=com/dstrube/JTP/META-INF/MANIFEST.MF
jar --verbose --update --file=com/dstrube/JTP/JarTest.class --main-class=com.dstrube.JTP.JarTest

Damnit, this was working briefly one time, but now I can't figure out what I did to get it working -_-
*/

public class JarTest {
	public static void main(String[] args) {
		System.out.println("Hello from JarTest");
		if(args != null && args.length > 0){
			System.out.println("Args: ");
			for(String arg : args){
				System.out.println(arg);
			}
		}else{
			System.out.println("No args");
		}
		System.out.println("Done");
	}
	
}