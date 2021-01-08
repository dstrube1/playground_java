package com.dstrube;

/*
commands to compile and run:
from ~/java:

Mac:
javac -d bin com/dstrube/SwapTest.java
java -cp bin com.dstrube.SwapTest

Windows:
javac -d bin com\dstrube\SwapTest.java
java -cp bin com.dstrube.SwapTest

I love smart swapping (a = a + b; b = a - b; a = a-b;).
But does it always work?...
*/

public class SwapTest{
	private static final int[] testValues = {0, 1, -1, Integer.MAX_VALUE, Integer.MIN_VALUE};

	public static void main(String[] args){
		final int[] arrSmart = new int[2];
		final int[] arrDumb = new int[2];
		for(int i = 0; i < testValues.length; i++){
			for (int j = 0; j < testValues.length; j++){
				arrSmart[0] = testValues[i];
				arrSmart[1] = testValues[j];
				arrDumb[0] = testValues[i];
				arrDumb[1] = testValues[j];
				smartSwap(arrSmart);
				dumbSwap(arrDumb);
				if(arrSmart[0] != arrDumb[0] || arrSmart[1] != arrDumb[1]){
					System.out.println("Error at i = " + i + " & j = " + j);
				}
			}
		}
		System.out.println("Done");
		
		//Cool, but...
		//[
		//if a = max, b = max
		//and then a = a + b
		final int test0 = Integer.MAX_VALUE + Integer.MAX_VALUE;
		System.out.println("test0 = " + test0); // -2
		//b = a - b
		final int test1 = test0 - Integer.MAX_VALUE;
		System.out.println("test1 = " + test1); // 2147483647
		//a = a - b: see above.
		//]
		//Why -2?
		//And why (-2 - max) = max?
	}
	
	private static void smartSwap(final int[] arr){
		//swap values at indices 0 & 1, smartly
		arr[0] = arr[0] + arr[1];
		arr[1] = arr[0] - arr[1];
		arr[0] = arr[0] - arr[1];
	}
	
	private static void dumbSwap(final int[] arr){
		//swap values at indices 0 & 1, dumbly
		final int temp = arr[0];
		arr[0] = arr[1];
		arr[1] = temp;
	}
}