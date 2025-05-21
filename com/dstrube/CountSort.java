package com.dstrube;

/*
commands to compile and run:
from ~/java
javac -d bin com/dstrube/CountSort.java 
java -cp bin com.dstrube.CountSort

Sort numbers by their count in an array. Same count = show up in original order.

Example test:   [1, 3, 6, 4, 1, 2]
 output: [1, 1, 3, 6, 4, 2]

Example test:   [1, 2, 3]
 output: [1,2,3]

Example test:   [1, 3, 1, 5, 5, 2]
 output: [1,1,5,5,3,2]

Example test:   [1,2,2,3,3,3,4,5,5,6,6,6]
 output: [3,3,3,6,6,6,2,2,5,5,1,4]
*/

import java.util.List;
import java.util.Map;
import java.util.HashMap;
/*
HashMap isn't quite right- sorts single instances in ascending order:
Example test:    [1,3,6,4,1,2]
Expected output: [1,1,3,6,4,2]
output:          [1,1,6,4,3,2]
*/
/*
Let's see if Hashtable does any better...
import java.util.Hashtable;
https://docs.oracle.com/javase/8/docs/api/java/util/Hashtable.html
No it's even worse: sorts single instances in descending order:
Example test:    [1,3,6,4,1,2]
Expected output: [1,1,3,6,4,2]
output:          [1,1,6,4,3,2]
*/
import java.util.Map.Entry;
import java.util.ArrayList;

public class CountSort{
	
	public static void main(String[] args){
		List<Integer> output;// = new ArrayList<>();
		
		final int[] in1 = {1,3,6,4,1,2};
		final int[] expectedOutput1 = {1,1,3,6,4,2};
		output = frequenySortArray(in1);
		print(in1, expectedOutput1, output); //FAIL
		/**/
		final int[] in2 = {1,2,3};
		final int[] expectedOutput2 = {1,2,3};
		output = frequenySortArray(in2);
		print(in2, expectedOutput2, output); //PASS

		final int[] in3 = {1,3,1,5,5,2};
		final int[] expectedOutput3 = {1,1,5,5,3,2};
		output = frequenySortArray(in3);
		print(in3, expectedOutput3, output); //FAIL
		
		final int[] in4 = {1,2,2,3,3,3,4,5,5,6,6,6};
		final int[] expectedOutput4 = {3,3,3,6,6,6,2,2,5,5,1,4};
		output = frequenySortArray(in4);
		print(in4, expectedOutput4, output); //PASS
		/**/
	}
	
	private static void print(final int[] in, final int[] expectedOutput, 
			final List<Integer> out){
		System.out.print("Example test:    ");
		printIntArray(in);
		
		System.out.print("Expected output: ");
		printIntArray(expectedOutput);
		
		System.out.print("output:          [");
		
		StringBuilder sb = new StringBuilder();
		//System.out.println("out size = " + out.size());
		/*for(int i = 0; i < out.size(); i++){
			//System.out.println("*" + i + " = " + out.get(i) + "*");
			sb.append(out.get(i)+",");
		}*/
		for(int i : out){
			sb.append(i + ",");
		}
		System.out.print(sb.toString().substring(0, sb.toString().length()-1));
		System.out.println("]\n");
	}
	
	private static void printIntArray(final int[] arr){
		StringBuilder sb = new StringBuilder("[");
		for(int i : arr){
			sb.append(i + ",");
		}
		System.out.print(sb.toString().substring(0, sb.toString().length()-1));
		System.out.println("]");
	}
	
	private static List<Integer> frequenySortArray(int arr[])
	{
		//final Map<Integer, Integer> map = new Hashtable<>();
		final Map<Integer, Integer> map = new HashMap<>();
		int max = 1;
		for(int i = 0; i < arr.length; i++){
			if(map.containsKey(arr[i])){
				int count = map.get(arr[i]) + 1;
				map.replace(arr[i], count);
				if(count > max){
					max = count;
				}
			}else{
				map.put(arr[i], 1);
			}
		}
		//System.out.println("max = " + max);
		List<Integer> list = new ArrayList<>();
		for (int j = max; j > 0; j--){
			for(Map.Entry<Integer, Integer> entry : map.entrySet()){
				//System.out.println("Checking entry " + entry.getKey() + " with count = " + entry.getValue());
				if(entry.getValue() == j){
					for(int k = 0; k < j; k++){
						list.add(entry.getKey());
					}
				}
			}
		}
		return list;
	}
}