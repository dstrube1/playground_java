package com.dstrube;

/*
commands to compile and run:
from ~/java:

Mac:
javac -d bin com/dstrube/DuplicatesFinder.java
java -cp bin com.dstrube.DuplicatesFinder

Windows:
javac -d bin com\dstrube\DuplicatesFinder.java
java -cp bin com.dstrube.DuplicatesFinder

Finding duplicates, from good, to better, to more better
*/

import java.util.ArrayList;
import java.util.List;

public class DuplicatesFinder{
	public static void main(String[] args){
		//
	}
	
	//Good - brute force: O(n^2)
	private static int[] findDuplicates_1(int[] arr1, int[] arr2) {
		final List<Integer> list = new ArrayList<>();
		for (int i1 : arr1){
			for(int i = 0; i < arr2.length; i++){
				int i2 = arr2[i];
				while(i2 < i1){
					i++;
					if(i < arr2.length)
						i2 = arr2[i];
					else
						break;
				}
				if (i2 > i1){
					break;
				}
				if(i2 == i1){
					list.add(i2);
				}
			}
		}
		final int[] output = new int[list.size()];
		for(int i = 0; i < list.size(); i++){
			output[i] = list.get(i);
		}
		return output;
	}
	
	//Better - single loop with parallel indices: O(n)
	private static int[] findDuplicates_2(int[] arr1, int[] arr2){
		final List<Integer> duplicates = new ArrayList<>();
		int i = 0;
		int j = 0;

		while (i < arr1.length && j < arr2.length){
			if (arr1[i] == arr2[j]){
				duplicates.add(arr1[i]);
				i = i + 1;
				j = j + 1;
			}
			else if (arr1[i] < arr2[j])
				i = i + 1;
			else
				j = j + 1;
		}
		
		//ugly java8 way:
		final int[] output = duplicates.stream().mapToInt(h -> h).toArray();
		return output;
	}
	
	//More better- use binary search on the bigger array: O(log n)
	private static int[] findDuplicates_3(int[] arr1, int[] arr2){
		final List<Integer> duplicates = new ArrayList<>();
		if(arr1.length > arr2.length){
			for(int i : arr2){
				if (binarySearch(arr1, i) != -1){
					duplicates.add(i);
				}
			}
		}else{
			for(int i : arr1){
				if (binarySearch(arr2, i) != -1){
					duplicates.add(i);
				}
			}
		}
		final int[] output = duplicates.stream().mapToInt(h -> h).toArray();
		return output;
	}
	
	private static int binarySearch(final int[] array, final int find){
		int left = 0;
		int right = array.length - 1;
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
}
//leetcode.com 1229
//sjhxbug1024@gmail.com

/*
*/