package com.dstrube;

/*
compile:
javac -d bin com/dstrube/ResourceUser.java
run:
java -cp bin com.dstrube.ResourceUser
*/


public class ResourceUser {
	private final Object lockA=new Object(), lockB=new Object();
	private static int in;
	//Task 1 uses lockA then lockB
	public void method1(){ 
		synchronized(lockA){
			synchronized(lockB){
				System.out.print("1");
				//Deadlock happens sooner if do this:
				//System.out.print("1-" + in + ";");
			}
		}
	}
	
	//Task 2 uses lockB then lockA
	public void method2(){ 
		synchronized(lockB){
			synchronized(lockA){
				System.out.print("2");
				//Deadlock happens sooner if do this:
				//System.out.print("2-" + in + ";");
			}
		}
	}

	public static void main(String[] args){
		ResourceUser u = new ResourceUser();
		//Likely to hit a deadlock situation with this many iterations
		for (int i = 0; i < 1000; i++){
			//https://www.geeksforgeeks.org/java/double-colon-operator-in-java/#
			in = i;
			new Thread(u::method1).start(); 
			new Thread(u::method2).start();
		}
		/*
		TODO: Solutions:
		1- put the locks in the same order
		2- implement a timeout mechanism
		3- check the status of depended-on locks
		*/
		System.out.println("\nDone");
	}	
}
