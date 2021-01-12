package com.dstrube.Singletons;

//Singleton way #1
public class S1{
	private static int count = 0;
	private static int num = 0;
	
	private S1(){
		count++;
		if(count > 1){
			System.out.println("ERROR: S1: " + count);
		}
		System.out.println("S1: " + count);
	}
	public static final S1 INSTANCE = new S1();
	
	public static void plusNum(){
		num++;
		System.out.println("S1 num: " + num);
	}
}
