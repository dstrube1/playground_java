package com.dstrube;

/*
commands to compile and run:
from ~/java:

Mac:
javac -d bin com/dstrube/PancakeSorter.java
java -cp bin com.dstrube.PancakeSorter

Windows:
javac -d bin com\dstrube\PancakeSorter.java
java -cp bin com.dstrube.PancakeSorter

Pancake sorting
https://en.wikipedia.org/wiki/Pancake_sorting
*/


public class PancakeSorter{
	public static void main(String[] args){
		int[] arr = {1,5,4,3,2};
		System.out.println("Array before pancake sort: ");
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		pancakeSort(arr);
		System.out.println("Array after pancake sort: ");
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
	}
	
	private static void pancakeSort(final int[] arr){
		int end = arr.length;
		while(end > 1){
			final int maxI = maxIndex(arr, end);
			if(maxI != end){
				flip(arr, maxI);
				flip(arr, end-1);
			}
			end--;
		}
	}
	
	private static int maxIndex(final int[] arr, final int k){
		int maxIndex = 0;
		for (int i = 0; i < k; i++){
			if(arr[i] > arr[maxIndex]){
				maxIndex = i;
			}
		}
		return maxIndex;
	}
	
	private static void flip(final int[] arr, int k){
		int left = 0;
		while (left < k){
			swap(arr, left, k);
			k--;
			left++;
		}
		//
	}
	
	private static void swap(final int[] arr, final int left, final int k){
		arr[left] = arr[left] + arr[k];
		arr[k] = arr[left] - arr[k];
		arr[left] = arr[left] - arr[k];
	}
}
