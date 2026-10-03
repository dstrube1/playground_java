package com.dstrube;

/*
From ~/java:

javac -d bin com/dstrube/ReferenceTest.java
java -cp bin com.dstrube.ReferenceTest

*/

//import java.lang.ref.WeakReference;
//import java.lang.ref.SoftReference;
//import java.lang.ref.*;

class MyObject{
	private int value;
	public void setValue(int v){
		value = v;
	}
	public int getValue(){
		return value;
	}
}

public class ReferenceTest {

	public static void main(String[] args) {
		MyObject m0 = new MyObject();
		m0.setValue(0);
		System.out.println("Testing setting by passing to a method...");
		System.out.println("m0 value before test1: " + m0.getValue()); // 0
		System.out.println("Calling test1...");
		test1(m0);
// Starting here:
//https://lnkd.in/p/ejhfzXc6
// "Java is never pass by reference." ?
// This doesn't seem right. If it was, then wouldn't test1 result in no change to m0?
		System.out.println("m0 value after test1: " + m0.getValue()); // 1
		
		System.out.println("Testing swapping...");
// "The proof - swap two objects in a method and nothing swaps outside"
		MyObject m1 = new MyObject();
		m1.setValue(0);
		System.out.println("m0 value before test2: " + m0.getValue()); // 1
		System.out.println("m1 value before test2: " + m1.getValue()); // 0
		System.out.println("Calling test2...");
		test2(m0, m1);
		System.out.println("m0 value after test2: " + m0.getValue()); // 1
		System.out.println("m1 value after test2: " + m1.getValue()); // 0
		//Interesting...
		

// See also 
//https://www.geeksforgeeks.org/java/types-references-java/

		System.out.println("Done");
	}
	
	public static void test1(MyObject mA) {
		// Sets MyObject's value to 1
		mA.setValue(1);
	}
	
	public static void test2(MyObject mA, MyObject mB) {
		// Swaps A & B 
		MyObject mC = mA.copy();
		mA = mB; // mA is now what mB was
		mB = mC; // mB is now what mA was
	}
	//SoftReference<Gfg> softref = new SoftReference<Gfg>(g);
	//WeakReference<Gfg> weakref = new WeakReference<Gfg>(g);

}