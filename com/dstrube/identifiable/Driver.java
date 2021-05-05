package com.dstrube.identifiable;

/*
From ~/java:

javac -d bin com/dstrube/identifiable/Driver.java
java -cp bin com.dstrube.identifiable.Driver

*/

import java.io.Serializable;

public class Driver{
	
	public static void main(String[] args){
		try{
			Object obj = new Object();
			Identifiable identifiable = (Identifiable) obj;
			Serializable id = identifiable.getId();
			if (id != null) {
				System.out.println("Serializable id is NOT null");
			}else{
				System.out.println("Serializable id IS null");
			}
			/*String out = 
			//System.out.println(": '" + out + "'");
			for(int i=1; i<=4; i++){
				System.out.println("i: " + i);
			}*/
		}catch(Exception exception){
			System.out.println("Caught exception: " + exception);
		}
		System.out.println("Done");
	}
}