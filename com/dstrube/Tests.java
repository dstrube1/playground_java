package com.dstrube;

/*
From ~/java:

Mac:
javac -d bin com/dstrube/Tests.java
java -cp bin com.dstrube.Tests

Windows:
javac -d bin com\dstrube\Tests.java
java -cp bin com.dstrube.Tests

*/

import java.util.*;
import java.math.BigInteger;
import java.io.*;

public class Tests {
	public static void main(String[] args) {
//		int i = 3;
//		System.out.println("3 / 2 = " + (i/2));

		//dotTest();
		
//		long m = 64;
//		long n = 2;
//		System.out.println("greatest common denominator of "+m+","+n+": " + gcd(m,n));
//		System.out.println("least common multiple of "+m+","+n+": " + lcm(m,n));

		selfListAdd();
		
//		queueTest();

		//scannerTest();
		
		//ioTest();
		
		//ArrayTest();
	}
	
	private static void xTest(){
		//
	}
	
	private static void ioTest(){
		System.out.println("Using BufferedReader, enter a number > 0 and <= 100:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{
			String input = br.readLine();
		    int num = Integer.parseInt(input);
	    	while (num < 1 || num > 100){
	    		System.out.println("Invalid. Try again: ");
		    	input = br.readLine();
		    	num = Integer.parseInt(input);
	    	}
		    System.out.println("Valid!: " + num);
	    } catch (IOException e0){
	    	System.out.println("Caught exception: " + e0);
	    } catch (NumberFormatException e1){
   	    	System.out.println("Caught exception: " + e1);
	    }
	}
	
	private static void scannerTest(){
		System.out.println("Using Scanner, enter a number: ");
		Scanner scanner = new Scanner(System.in);
		//	String temp = scanner.next();
		//	System.out.println("temp: " + temp);
		try{
			int i = scanner.nextInt();
			System.out.println("i: " + i);
		} catch (InputMismatchException e){
			System.out.println("exception " + e);
		}
	}
	
	private static void queueTest(){
		Queue<Long> q = new ArrayDeque<>();
		q.add(0L);
		q.add(1L);
		for (int i = 0; i < 8181; i++) {
		    long a = q.remove();
		    long b = q.remove();
		    q.add(b);
		    q.add(a + b);
		    System.out.println(a);
		}
		
		Queue<BigInteger> q0 = new ArrayDeque<>();
		q0.add(BigInteger.ZERO);
		q0.add(BigInteger.ONE);
		for (int i = 0; i < 8181; i++) {
		    BigInteger a = q0.remove();
		    BigInteger b = q0.remove();
		    q0.add(b);
		    q0.add(a.add(b));
		    System.out.println(b+"\n");
		}
	}
	
	private static void selfListAdd(){
		//while it is permissible for lists to contain themselves as elements, 
		//extreme caution is advised: 
		//the equals and hashCode methods are no longer well defined on a such a list
		List<Object> list1 = new ArrayList<>();
		List<Object> list2 = new ArrayList<>();
		list1.add(list2);
		list2.add(list1);

		System.out.println("After adding list1 to list2, and vice versa, attempting to check their equality...");
		try{
			System.out.println(list1.equals(list2));
		}catch (StackOverflowError e){
			System.out.println(e.getClass().getSimpleName());
		}
		
		List<Object> list = new ArrayList<>();
		list.add(list);

		System.out.println("After adding list to itself, attempting to print its hashcode...");
		try{
			System.out.println(list.hashCode());
		}catch (StackOverflowError e){
			System.out.println(e.getClass().getSimpleName());
		}
	}
	
	// return Greatest Common Denominator(|m|, |n|)
    private static long gcd(long m, long n) {
        if (m < 0) m = -m;
        if (n < 0) n = -n;
        if (0 == n) return m;
        else return gcd(n, m % n);
    }
	
	// return Least Common Multiple(|m|, |n|)
    private static long lcm(long m, long n) {
        if (m < 0) m = -m;
        if (n < 0) n = -n;
        return m * (n / gcd(m, n));    // parentheses important to avoid overflow
    }
    
	private static void dotTest(){
		System.out.println("threeDots(): "); 
		threeDots();
		System.out.println("threeDots(1): "); 
		threeDots(1);
		System.out.println("threeDots(0,1): "); 
		threeDots(0,1);
		//threeDots(null); //this throws a NPE
	}

	//private static void twoDots(int.. i){} invalid syntax
	
	private static void threeDots(int... i){
		for (int j = 0; j < i.length; j++){
			System.out.print(i[j] + " ");
		}
		System.out.println();
	}
	
	private static void ArrayTest(){
		/*
		int[] a = {1};
		int[] b = a;
		System.out.println("a[0] = " + a[0] + "; b[0] = " + b[0]);
		a[0] = 2;
		System.out.println("a[0] = " + a[0] + "; b[0] = " + b[0]);
		changeArray(a);
		System.out.println("a[0] = " + a[0] + "; b[0] = " + b[0]);
		*/
		List<Integer> list = new ArrayList<>();
		list.add(0);
		list.add(1);
		list.add(2);
		System.out.println("All in list: ");
		for(int i : list)
			System.out.print(i + " ");
		System.out.println();
		
		//ugly java8 way:
		int[] c = list.stream().mapToInt(i -> i).toArray();
		System.out.println("All in c via list.stream()...:");
		for(int i : c)
			System.out.print(i + " ");
		System.out.println();
		
		
		/*
		Nope, can't do the following :/
		Either the above ugly way or iterate thru the list
		
		c = getIntArray(list);
		System.out.println("All in c via getIntArray:");
		for(int i : c)
			System.out.print(i + " ");
		System.out.println();*/
	}
	
	private static int[] getIntArray(List<Integer> list){
		Integer[] ints = new Integer[list.size()];
		list.toArray(ints);
		
		return null;//ints;
	}
	
	private static void changeArray(int[] c){
		c[0] = 0;
	}
}