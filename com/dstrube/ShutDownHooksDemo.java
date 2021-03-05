package com.dstrube;

/*
From ~/java:

Mac:
javac -d bin com/dstrube/ShutDownHooksDemo.java
java -cp bin com.dstrube.ShutDownHooksDemo

Windows:
javac -d bin com\dstrube\ShutDownHooksDemo.java
java -cp bin com.dstrube.ShutDownHooksDemo

Demonstrating shutdown hooks, from #5 here:
https://www.javacodegeeks.com/2015/06/java-programming-tips-best-practices-beginners.html
*/

public class ShutDownHooksDemo {
	public static void main(String[] args) {
		//demo0();
		demo1();
	}
	
	public static void demo0() {
		for(int i=0; i<5; i++)
        {
            try {
                if(i==4) {
                    System.out.println("Inside Try Block.Exiting without executing Finally block.");
                    System.exit(0);
                }
            }
            finally {
                System.out.println("Inside Finally Block #" + (i+1));
            }
        }
	}
	public static void demo1() {
		for(int i=0; i<5; i++)
            {
                    final int final_i = i;
                    try {
                            Runtime.getRuntime().addShutdownHook(
                                            new Thread() {
                        	                    public void run() {
                          		                  if(final_i==4) {
                        System.out.println("Inside Try Block.Exiting without executing Finally block.");
            		    System.exit(0);
                                	            	}
                                    	        }
                                            });
                    }
                    finally {
                            System.out.println("Inside Finally Block #" + (i+1));
                    }
 
            }
	}
}