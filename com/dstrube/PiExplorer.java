package com.dstrube;

/*
From ~/java:

javac -d bin com/dstrube/PiExplorer.java
java -cp bin com.dstrube.PiExplorer

From:
https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html

*/


public class PiExplorer {
	public static void main(String[] args) {
		
		final double PI = Math.PI;
		System.out.println("Math.PI: " + PI);

		final int MILLION = 1_000_000;
		final int TMILLION = 10_000_000; //billion and HMillion don't work right
		double extraDigits = PI * MILLION;
		//System.out.println("pi * million: " + extraDigits);

		extraDigits %= 1;
		extraDigits *= TMILLION;
		System.out.println("extra digits?: " + extraDigits);
		
		//TODO:
		//1- Is extraDigits accurate?
		//2- Can we do better with BigNumber and an algorithm?
		
		System.out.println("Done");
	}
	
}