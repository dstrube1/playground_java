package com.dstrube;

/*
From ~/java:

Mac:
javac -d bin com/dstrube/BitDemo.java
java -cp bin com.dstrube.BitDemo

Windows:
javac -d bin com\dstrube\BitDemo.java
java -cp bin com.dstrube.BitDemo

Starting from here:
https://docs.oracle.com/javase/tutorial/java/nutsandbolts/op3.html
*/

public class BitDemo {
	public static void main(String[] args) {
		int bitmask = 0x000F;
        int val = 0x2222;
        // prints "2"
        System.out.println("val (0x2222) & bitmask (0x000F): ");
        System.out.println(val & bitmask);
        
        int a = 3;
        int b = 4;
        int c = a * b;
        System.out.println("a (3) * b (4): " + c);
        
        int d = 3;
        int e = 6;
        int f = smartMultiply(d,e);
        System.out.println("c (3) smart multiply d(6): " + f);
	}
	
	private static int smartMultiply(int d, int e){
		//maybe not that smart, but interesting
		while(e % 2 == 0){
			d = d << 1;
			e = e >> 1;
		}
		return d * e;
	}
}