package com.dstrube;

/*
From ~/java:

Mac:
javac -d bin com/dstrube/Temp.java
java -cp bin com.dstrube.Temp

Windows:
javac -d bin com\dstrube\Temp.java
java -cp bin com.dstrube.Temp

This is a temporary code holder.
Nothing here is permanent. 
Must be okay with losing anything in here; else, save it to somewhere else.
*/

import java.util.*;
import java.math.BigInteger;
import java.io.*;

public class Temp {
	public static void main(String[] args) {
		int i;
		long L;
		byte b;
		char c;
		String s;
		short sh;
		float f;
		double d;
		int[]arr = new int[0];
		List<Integer> list = new ArrayList<>();
		Queue<Byte> queue = new ArrayDeque<>();
		Stack<Short> stack = new Stack<>();
		//BigInteger bi = new BigInteger(""); //compiles, but fails at runtime
		BigInteger bi = new BigInteger("1"); 
		StringBuilder sb = new StringBuilder();

		//Default values printed out:
		//Java compiler doesn't like printing uninitialized things
//		System.out.println("int i: " + i);
//		System.out.println("long L: " + L);
//		System.out.println("byte b: " + b);
//		System.out.println("char c: '" + c + "'");
//		System.out.println("String s: '" + s + "'");
//		System.out.println("short sh: " + sh);
//		System.out.println("float f: " + f);
//		System.out.println("double d: " + d);
		System.out.println("int[]arr: " + arr); //garbage
		System.out.println("List<Integer> list = new ArrayList<>(): " + list); // []
		System.out.println("Queue<Byte> queue = new ArrayDeque<>(): " + queue); // []
		System.out.println("Stack<Short> stack: " + stack); // []
		System.out.println("BigInteger bi: " + bi); // 1
		System.out.println("StringBuilder sb: '" + sb + "'"); // [blank]
		
		//try{
		//}catch(){}
		System.out.println("Done");
	}
	
	public static void x(String[] args) {
	}
}