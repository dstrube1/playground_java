package com.dstrube.Singletons;

/*
commands to compile and run:
from ~/java
javac -d bin com/dstrube/Singletons/Driver.java 
java -cp bin com.dstrube.Singletons.Driver

Exploring ways of doing Singletons
*/

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class Driver{
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		//doS0();
		doS1();
		//doS2();
		//doS3();
		
	}
	
	private static void doS0(){
		//Not a singleton
		S0 s0a = new S0();
		S0 s0b = new S0();
		if(s0a == s0b){
			System.out.println("s0a == s0b");
		}else{
			//Expected
			System.out.println("s0a != s0b");
		}
		final String className = S0.class.getName();
		Class<?> c1 = null;
		try{
			c1 = Class.forName(className);
		}catch(ClassNotFoundException cnfe){
			System.out.println("Class not found: " + className);
			return;
		}
		Method[] methods = c1.getMethods(); 
		for(Method m : methods){
			System.out.println(m.toString());
		}
	}	
	
	private static void doS1(){
		//Singleton way #1
		S1 s1a = S1.INSTANCE;
		S1 s1b = S1.INSTANCE;
		if(s1a == s1b){
			//Expected
			System.out.println("s1a == s1b");
		}else{
			System.out.println("s1a != s1b");
		}
		System.out.println("plusNum static: ");
		S1.plusNum();
		System.out.println("plusNum non-static: ");
		s1a.plusNum();
		//S1 s1c = new S1(); //compiler error: constructor is private

		//Why is this not the best way?:
		final String className = S1.class.getName();//"com.dstrube.Singletons.S1";
		Class<?> c1 = null;
		try{
			c1 = Class.forName(className);
		}catch(ClassNotFoundException cnfe){
			System.out.println("Class not found: " + className);
			return;
		}

		/*System.out.println("Is c1 instance of S1? " + (c1.isInstance(s1a) ? "yes" : "no"));
		System.out.println("Got Class object for S1. What are its methods?...");
		Method[] methods = c1.getDeclaredMethods(); 
		String methodName = "";
		for(Method m : methods){
			methodName = m.toString();
			System.out.println(methodName);
		}
		try{
			//if we leave methodName set to what it was set to above, we'll get a NoSuchMethodException
			//must set it to a simpler name
			methodName = "plusNum";
			Method m1 = c1.getDeclaredMethod(methodName);  
			m1.invoke(null);
			
		}catch(NoSuchMethodException nsme){
			System.out.println("No such method: " + methodName);
		}catch(IllegalAccessException iae){
			System.out.println("IllegalAccessException while trying to invoke " + methodName);
		}catch(InvocationTargetException ite){
			System.out.println("InvocationTargetException while trying to invoke " + methodName);
		}*/
		
		 
		
		//Can't instantiate it, but can call its methods.
		//Since we can't instantiate it, we can't prove whether or not c1 is equal to s1a
		/*try{
			S1 s1d = (S1)c1.newInstance();
			if(s1a == s1d){
				System.out.println("s1a == s1d");
			}else{
				System.out.println("s1a != s1d");
			}
			s1d.plusNum();
		}catch(IllegalAccessException iae){
			System.out.println("IllegalAccessException while trying to instantiate s1d");
		}catch(InstantiationException ie){
			System.out.println("InstantiationException while trying to instantiate s1d");
		}catch(Exception ex){
			System.out.println("Caught Exception while trying to instantiate s1d: " + ex);
		}*/
		
		//https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Constructor.html
		try{
			Constructor[] ctors = c1.getDeclaredConstructors();
			System.out.println("Count of declared constructors: " + ctors.length); //1
			for(Constructor m2 : ctors){
				m2.setAccessible(true); 
				try{
					S1 s1d = (S1)m2.newInstance(null); 
					if(s1a == s1d){
						System.out.println("s1a == s1d");
					}else{
						System.out.println("s1a != s1d");
					}
				}catch(){}
			    
			}
						/*
		    
			//s1d.plusNum();*/

			
		}catch(SecurityException se){
			System.out.println("SecurityException while trying to getDeclaredConstructors");
		}
	}
	
	private static void doS2(){
		//Singleton way #2
		S2 s2a = S2.getInstance();
		S2 s2b = S2.getInstance();
		if(s2a == s2b){
			//Expected
			System.out.println("s2a == s2b");
		}else{
			System.out.println("s2a != s2b");
		}
		System.out.println("plusNum static: ");
		S2.plusNum();
		System.out.println("plusNum non-static: ");
		s2a.plusNum();
		//S2 s2c = new S2(); //compiler error: constructor is private
		//Why is this not the best way? See S1 exploration in doS1
		
	}
	
	private static void doS3(){
		//Singleton way #3
		S3 s3a = S3.INSTANCE;
		S3 s3b = S3.INSTANCE;
		if(s3a == s3b){
			//Expected
			System.out.println("s3a == s3b");
		}else{
			System.out.println("s3a != s3b");
		}
		System.out.println("plusNum static: ");
		S3.plusNum();
		System.out.println("plusNum non-static: ");
		s3a.plusNum();
		
		//Not susceptible to the reflection attacks from above, or serialization attack
		
		//S1 s3c = new S3(); //compiler error: constructor is private
		//						even though it's an enum without an explicit constructor
	}
}

