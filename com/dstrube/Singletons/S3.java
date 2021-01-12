package com.dstrube.Singletons;

//Singleton way #3
public enum S3{
	INSTANCE;

	private static int num = 0;
	public static void plusNum(){
		num++;
		System.out.println("S3 num: " + num);
	}
}