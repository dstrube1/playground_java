package com.dstrube;

/*
commands to compile and run:
from ~/java
javac -d bin com/dstrube/MergesortTest.java
java -cp bin com.dstrube.MergesortTest

Taking a stab at writing my own mergesort algorithm,
starting from here:
https://www.geeksforgeeks.org/merge-sort/
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//https://docs.oracle.com/javase/8/docs/api/java/util/Random.html

public class MergesortTest{
	
	private static int[] array;
	
	public static void main(String[] args){
		final int[] initted = initArray(10000000);
		long startTime;
		long endTime;
		long duration;
		
		startTime = System.currentTimeMillis();	
		array = initted.clone();
		//printArray();
		mergesort(array, 0, array.length-1);
		//printArray();
		endTime = System.currentTimeMillis();
		duration = (endTime - startTime);
		System.out.println("Done mergesorting " + initted.length + " items in " 
			+ duration + " milliseconds.");
				
	}
	
	private static int[] initArray(int size){
		final Random random = new Random(0L);
		final List<Integer> list = new ArrayList<>(size);
		for (int i=0; i < size; i++){
			final int j = random.nextInt(size*2);
			
			list.add(j);
		}
		
		final int[] initted = new int[size];
		Integer ints[] = new Integer[size]; 
        ints = list.toArray(ints); 
        for(int i=0; i < size; i++){
        	initted[i] = ints[i];
        }
		
		return initted;
	}
	
	private static void printArray(){
		for(final int item : array){
			System.out.print(item + " ");
		}
		System.out.println();
	}
	
	private static void mergesort(final int[] arr, final int left, final int right){
		if (right > left){
			final int middle = (left + right) / 2;
			
			mergesort(arr, left, middle);
			mergesort(arr, middle + 1, right);
			
			merge(arr, left, middle, right);
		}
	}
	
	private static void merge(final int[] arr, final int left, final int middle, final int right){
        // Find sizes of two subarrays to be merged
		final int sizeOfLeft = middle - left + 1;
		final int sizeOfRight = right - middle;
		
		/* Create temp arrays */
        final int temp_L[] = new int[sizeOfLeft];
        final int temp_R[] = new int[sizeOfRight];
        
        /*Copy data to temp arrays*/
        for (int i = 0; i < sizeOfLeft; ++i)
            temp_L[i] = arr[left + i];
        for (int j = 0; j < sizeOfRight; ++j)
            temp_R[j] = arr[middle + 1 + j];
        
        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarray
        int k = left;
        while (i < sizeOfLeft && j < sizeOfRight) {
            if (temp_L[i] <= temp_R[j]) {
                arr[k] = temp_L[i];
                i++;
            }
            else {
                arr[k] = temp_R[j];
                j++;
            }
            k++;
        }
        
        /* Copy remaining elements of temp_L[] if any */
        while (i < sizeOfLeft) {
            arr[k] = temp_L[i];
            i++;
            k++;
        }
 
        /* Copy remaining elements of temp_R[] if any */
        while (j < sizeOfRight) {
            arr[k] = temp_R[j];
            j++;
            k++;
        }
	}
}