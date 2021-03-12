package com.dstrube;

/*
From ~/java:

javac -d bin com/dstrube/SumOfMultiples.java
java -cp bin com.dstrube.SumOfMultiples

SumOfMultiples : find all multiples of A & B below X
For example: If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.

Find the sum of all the multiples of 3 or 5 below 1000.
*/

import java.util.HashSet;
import java.util.Set;

public class SumOfMultiples {
	public static void main(String[] args) {
		Set<Integer> multiples = new HashSet<>();
		int multipleA = 3;
		int multipleB = 5;
		int max = 1000;
		addMultiples(multipleA, max, multiples);
		addMultiples(multipleB, max, multiples);
		int sum = sumMultiples(multiples);
		System.out.println(sum);
	}
	
	public static void addMultiples(int multiple, int max, Set<Integer> set) {
		int i = 1;
		int product = multiple * i;
		while(product < max){
			if(!set.contains(product)){
				set.add(product);
			}
			i++;
			product = multiple * i;
		}
		
	}
	
	public static int sumMultiples(Set<Integer> set) {
		int sum = 0;
		for(int i : set){
			sum += i;
		}
		return sum;
	}
}