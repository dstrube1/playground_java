package com.dstrube;

/*
commands to compile and run:
from ~/java
javac -d bin com/dstrube/QuicksortTest.java
java -cp bin com.dstrube.QuicksortTest

Taking a stab at writing my own quicksort algorithm,
starting from here:
https://en.wikipedia.org/wiki/Quicksort
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//https://docs.oracle.com/javase/8/docs/api/java/util/Random.html

public class QuicksortTest{
	
	private static int[] array;
	private static int steps;
	private static int swaps;
	
	public static void main(String[] args){
		final int[] initted = initArray(10000000);
		long startTime;
		long endTime;
		long duration;
		
		startTime = System.currentTimeMillis();	
		array = initted.clone();
		steps = 0;
		swaps = 0;
		//printArray();
		quicksort_L(array, 0, array.length-1);
		//printArray();
		endTime = System.currentTimeMillis();
		duration = (endTime - startTime);
		System.out.println("Lomuto - Done in " + steps + " steps with " + swaps + " swaps "
			+ "in " + duration + " milliseconds.");
		
		startTime = System.currentTimeMillis();
		array = initted.clone();
		steps = 0;
		swaps = 0;
		//printArray();
		quicksort_H(array, 0, array.length-1);
		//printArray();
		endTime = System.currentTimeMillis();
		duration = (endTime - startTime);
		System.out.println("Hoare -  Done in " + steps + " steps with " + swaps + " swaps "
			+ "in " + duration + " milliseconds.");
		
	}
	
	private static int[] initArray(int size){
		final Random random = new Random(0L);
		final List<Integer> list = new ArrayList<>(size);
		for (int i=0; i < size; i++){
			int j = random.nextInt(size*2);
			
			//If we make the array presorted, 
			//this really demonstrates how Hoare is superior:
			//j = i;

			//If they're all the same, Lomuto does better than Hoare 
			//(# steps & swaps), except for runtime.
			//j=0;

			//System.out.println("Adding to list: " + j);

			list.add(j);
		}
		
		//Checking to make sure the two-variable swap works at the margins
		/*/
		list.add(-1);
		list.add(0);
		list.add(Integer.MIN_VALUE);
		list.add(Integer.MAX_VALUE);
		list.add(1);
		/*/

		final int[] initted = new int[size];
		Integer ints[] = new Integer[size]; 
        ints = list.toArray(ints); 
        for(int i=0; i < size; i++){
        	//System.out.println("Adding to initted: " + ints[i]);
        	initted[i] = ints[i];
        }
		
		return initted;
	}
	
	private static void printArray(){
		for(int item : array){
			System.out.print(item + " ");
		}
		System.out.println();
	}
	
	private static void swap(int[] arr, int i, int j){
		if (i == j || arr[i] == arr[j]) return;
		
		//System.out.println("Swapping arr["+i+"] with arr["+j+"]: " + arr[i] + " <=> " + arr[j]);
		//a = a + b
		//b = a - b
		//a = a - b
		arr[i] = arr[i] + arr[j];
		arr[j] = arr[i] - arr[j];
		arr[i] = arr[i] - arr[j];
		swaps++;
	}

	//Lomuto's
	private static void quicksort_L(int[] arr, int lo, int hi){
		//steps++;
		if (lo < hi){
			int p = partition_L(arr, lo, hi);
			quicksort_L(arr, lo, p-1);
			quicksort_L(arr, p+1, hi);
		}
	}
	
	private static int partition_L(int[] arr, int lo, int hi){
		int pivot = arr[hi];
		int i = lo;
		for (int j = lo; j < hi; j++){
			if(arr[j] < pivot){
				swap(arr, i, j);
				i++;
			}
		}
		swap(arr, i, hi);
		return i;
	}
	
	//Hoare's
	private static void quicksort_H(int[] arr, int lo, int hi){
		steps++;
		if (lo < hi){
			int p = partition_H(arr, lo, hi);
			quicksort_H(arr, lo, p);
			quicksort_H(arr, p+1, hi);
		}
	}
	
	private static int partition_H(int[] arr, int lo, int hi){
		int pivot = arr[(hi+lo)/2];
		int i = lo - 1;
		int j = hi + 1;
		while(true){
			do{
				i++;
			}while(arr[i] < pivot);
			
			do{
				j--;
			}while(arr[j] > pivot);
			
			if(i >= j){
				return j;
			}
			swap(arr, i, j);
		}
	}
	
}