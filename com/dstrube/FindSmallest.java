package com.dstrube;

/*
commands to compile and run:
from ~/java
javac -d bin com/dstrube/FindSmallest.java 
java -cp bin com.dstrube.FindSmallest

Find smallest integer greater than 0 not in an array

Example test:   [1, 3, 6, 4, 1, 2]
 5

Example test:   [1, 2, 3]
 4

Example test:   [-1, -3]
1
*/

import java.util.Arrays;

public class FindSmallest{
	
	public static void main(String[] args){
		int[] a1 = {1, 3, 6, 4, 1, 2};
		System.out.println("Smallest a1 = " + smallest(a1));
		int[] a2 = {1,2,3};
		System.out.println("Smallest a2 = " + smallest(a2));
		int[] a3 = {-1,-3};
		System.out.println("Smallest a3 = " + smallest(a3));
	}

    public static int smallest(int[] A) {
        Arrays.sort(A);
        int smallest = 1;
        while (binarySearch(A, smallest) != -1){
        	smallest++;
        }
        
        return smallest;
    }
    
    private static int binarySearch(int[] A, int find){
    	int left = 0;
    	int right = A.length - 1;
    	while(left <= right){
    		int mid = (left + right) / 2;
    		if(A[mid] < find)
    			left = mid + 1;
    		else if(A[mid] > find)
    			right = mid - 1;
    		else
    			return mid;
    	}
    	return -1;
    }
}