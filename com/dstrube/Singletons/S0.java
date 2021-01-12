package com.dstrube.Singletons;

//Not a singleton
public class S0{
	private static int count = 0;
	public S0(){
		count++;
		System.out.println("S0: " + count);
	}
}