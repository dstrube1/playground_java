package com.dstrube;

/*
commands to compile and run:
from ~/java
javac -cp bin:bin/junit-4.12.jar -d bin com/dstrube/CheckWriterUnitTests.java
java -cp bin:bin/junit-4.12.jar com.dstrube.CheckWriterUnitTests

java -cp bin:bin/junit-4.12.jar org.junit.runner.JUnitCore com.dstrube.CheckWriter



title: Check Writer unit tests

*/

//import com.dstrube.CheckWriter;
import junit.framework.TestCase;
import org.junit.Test;

public class CheckWriterUnitTests extends TestCase{
	public static void main(String[] args)
	{
		//writeCheck with null
		testWriteCheckNull();
		//writeCheck with blank
		testWriteCheckBlank();
	}	
	
	@Test
	public static void testWriteCheckNull(){
		CheckWriter cw = new CheckWriter();
		String result = cw.writeCheck(null);
		assertEquals("testWriteCheckNull success", result, "Invalid input: null");
		
		//this should fail
		//result = cw.writeCheck("-1");
		//assertEquals("testWriteCheckNull success", result, "Invalid input: null");
	}
	
	@Test
	public static void testWriteCheckBlank(){
		String result = CheckWriter.writeCheck("");
		assertEquals("testWriteCheckBlank success", result, "Invalid input: blank");
	}
}