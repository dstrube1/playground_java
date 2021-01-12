package com.dstrube;

/*
commands to compile and run:
from ~/java:

Mac:
javac -d bin com/dstrube/PairsWithDifference.java
java -cp bin com.dstrube.PairsWithDifference

Windows:
javac -d bin com\dstrube\PairsWithDifference.java
java -cp bin com.dstrube.PairsWithDifference

Find pairs with a given difference
*/
import java.util.*;

public class PairsWithDifference{
	public static void main(String[] args){
		int[] arr = {0, -1, -2, 2, 1};
		int k = 1;
		int[][] output = findPairsWithGivenDifference(arr, k);
		System.out.print("[");
		for(int[] a1 : output){
			System.out.print("[");
			for(int a2 : a1){
				System.out.print(a2 + " ");
			}
			System.out.print("], ");
		}
		System.out.println("]");
		
		int[] arr1 = {1,7,5,3,32,17,12};
		k = 17;
		output = findPairsWithGivenDifference(arr1, k);
		System.out.print("[");
		for(int[] a1 : output){
			System.out.print("[");
			for(int a2 : a1){
				System.out.print(a2 + " ");
			}
			System.out.print("], ");
		}
		System.out.println("]");
		
	}
	
	private static int[][] findPairsWithGivenDifference(final int[] arr, int k){
		if(k == 0) return new int[0][0];
		
		Map<Integer, Integer>pairs = new HashMap<>();
		Map<Integer, Integer>answers = new HashMap<>();
		for(int x : arr){
			pairs.put(x-k, x);
		}
		for (int y : arr){
			if(pairs.containsKey(y)){
				answers.put(pairs.get(y), y);
			}
		}
	    int[][] result = new int[answers.size()][2];
    	int i = 0;
	    for(int key : answers.keySet()){
	    	int[] entry = {key, answers.get(key)};
    	  result[i++] = entry;
	    }
   
    	return result;
	}
	
}
