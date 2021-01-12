package com.dstrube.Singletons;

//Singleton way #2
public class S2{
	private static int count = 0;
	private static int num = 0;
	private static final S2 instance = new S2();
	
	private S2(){
		count++;
		if(count > 1){
			System.out.println("ERROR: S2: " + count);
		}
		System.out.println("S2: " + count);
	}
	
	public static S2 getInstance(){
		return instance;
	} 
	
	public static void plusNum(){
		num++;
		System.out.println("S2 num: " + num);
	}
}
