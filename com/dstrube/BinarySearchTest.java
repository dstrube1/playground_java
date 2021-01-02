package com.dstrube;

/*
commands to compile and run:
from ~/java:

Mac:
javac -d bin com/dstrube/BinarySearchTest.java
java -cp bin com.dstrube.BinarySearchTest

Windows:
javac -d bin com\dstrube\BinarySearchTest.java
java -cp bin com.dstrube.BinarySearchTest


Taking a stab at writing my own binary search algorithm,
starting from here:
https://en.wikipedia.org/wiki/Binary_search_algorithm
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//https://docs.oracle.com/javase/8/docs/api/java/util/Random.html

public class BinarySearchTest{
	
	private static int[] array;
	private static int steps;
	private static int swaps;
	
	public static void main(String[] args){
		final int size = 10000000;
		final Random random = new Random();
		final int[] initted = initArray(size);
		final int find = random.nextInt(size);
		System.out.println("Searching for " + formatNum(find));
		int found = binarySearch(initted, initted.length, find);
		System.out.println("Found at " + formatNum(found));
	}
	
	private static int[] initArray(int size){
		
		final List<Integer> list = new ArrayList<>(size);
		for (int i=0; i < size; i++){
			int j = i;
			
			list.add(j);
		}

		final int[] initted = new int[size];
		Integer ints[] = new Integer[size]; 
        ints = list.toArray(ints); 
        for(int i=0; i < size; i++){
        	//System.out.println("Adding to initted: " + ints[i]);
        	initted[i] = ints[i];
        }
		
		return initted;
	}

	private static int binarySearch(int[] array, int length, int find){
		int left = 0;
		int right = length - 1;
		while (left <= right){
			int mid = (left + right) / 2;
			if (array[mid] < find){
				//System.out.println("Not at " + formatNum(mid) + "; moving right...");
				left = mid + 1;
			}
			else if (array[mid] > find){
				//System.out.println("Not at " + formatNum(mid) + "; moving left...");
				right = mid - 1;
			}
			else{
				return mid;
			}
		}
		return -1;
	}
	
	private static String formatNum(int num){
		String s = ""+num;
		
		if(s.length() <= 3) return s;
		
		StringBuilder sb = new StringBuilder(s);  
		sb.reverse();  
		s = sb.toString();
		int i = 3; 
		while(i < s.length()){
			s = s.substring(0, i) + "," + s.substring(i);
			i += 4;
		}
		sb = new StringBuilder(s);  
		sb.reverse();  
		s = sb.toString();
		return s;
	}
}