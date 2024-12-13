package com.dstrube;

/*
From ~/java:
Mac / Linux:
javac -d bin com/dstrube/MapTest.java
java -cp bin com.dstrube.MapTest
Windows:
javac -d bin com\dstrube\MapTest.java
java -cp bin com.dstrube.MapTest
*/

import java.util.*;

public class MapTest {
	public static void main(String[] args) {
		Long L1 = mapTest();
		System.out.println("s1: " + L1);
	}
	private static long mapTest(){
		Map <String,Long> map = new HashMap<>();
		long v1 = map.get("s1");
		System.out.println("s1: " + v1); //null
		return v1;
	}
}
