package com.dstrube;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

/*
compile:
javac -d bin com/dstrube/ResourceUser.java
run:
java -cp bin com.dstrube.ResourceUser

See also
https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/Lock.html
*/

/*
		Challenge: In the old way, program halts due to deadlock
		
		Solutions:
		1- put the locks in the same order, or use only 1 lock - too easy
		2- implement a timeout mechanism 
*/

public class ResourceUser {
	//Old
	//private final Object lockA = new Object(), lockB=new Object();
	//New
	private final Lock lockA = new ReentrantLock(); 
	private final Lock lockB = new ReentrantLock();
	private final long A1 = 1;
	private final long B1 = 1;
	private final long A2 = 1;
	private final long B2 = 1;
	
	
	private static int in;
	//Task 1 uses lockA then lockB
	public void method1(){ 
		/*
		Old
		synchronized(lockA){
			synchronized(lockB){
				//System.out.print("1");
				//In the old way, deadlock happens sooner if do this:
				System.out.print("1-" + in + ";");
			}
		}*/
		//New
		try{
			if(lockA.tryLock(A1, TimeUnit.MILLISECONDS)){
				try{
					if(lockB.tryLock(B1, TimeUnit.MILLISECONDS)){
						try{
							//System.out.print("1-" + in + ";");
							System.out.print(";");
						} finally{
							lockB.unlock();
						}
					}
				} finally{
					lockA.unlock();
				}
			}
		} catch(InterruptedException ie){
			System.out.print("[1TO]"); // method 1 timed out
			Thread.currentThread().interrupt();
		}
	}
	
	//Task 2 uses lockB then lockA
	public void method2(){ 
		/*
		Old
		synchronized(lockB){
			synchronized(lockA){
				//System.out.print("2");
				//In the old way, deadlock happens sooner if do this:
				System.out.print("2-" + in + ";");
			}
		}*/
		//New
		try{
			if(lockB.tryLock(A2, TimeUnit.MILLISECONDS)){
				try{
					if(lockA.tryLock(B2, TimeUnit.MILLISECONDS)){
						try{
							//System.out.print("2-" + in + ";");
							System.out.print("-");
						} finally{
							lockA.unlock();
						}
					}
				} finally{
					lockB.unlock();
				}
			}
		} catch(InterruptedException ie){
			System.out.print("[2TO]"); // method 2 timed out
			Thread.currentThread().interrupt();
		}
	}

	public static void main(String[] args){
		ResourceUser u = new ResourceUser();
		//Old: Likely to hit a deadlock situation with this many iterations
		//New: this works fine:
		for (int i = 0; i < 1000; i++){
		//This runs into an OutOfMemoryError, even if run with these parameters:
		//-Xms4G -Xmx10G
		//for (int i = 0; i < 10000; i++){
			in = i;
			//https://www.geeksforgeeks.org/java/double-colon-operator-in-java/#
			new Thread(u::method1).start(); 
			new Thread(u::method2).start();
		}
		System.out.println("\nDone");
	}	
}
