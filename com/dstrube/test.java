/*
From ~/java:

javac -d bin com/dstrube/test.java
java -cp bin com.dstrube.test

*/
package com.dstrube;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import java.util.InputMismatchException;

public class test{
	
	public static void main(String[] args){
		try{
			//
		}catch(Exception exception){
			System.out.println("Caught exception: " + exception);
		}
		System.out.println("Done");
	}
	
	public static void testLongOverflow(){
		long testL = Long.MAX_VALUE;
		System.out.println("testL before increment: " + testL);
		try{
			testL++;
			System.out.println("testL after increment: " + testL); //Long.MIN_VALUE
			System.out.println("testL reset...");
			testL = Long.MAX_VALUE;
			testL *= 1000;
			System.out.println("testL * 1000: " + testL); //-1000
		}catch(Exception exception){
			System.out.println("Caught exception: " + exception);
		}
	}
	
	public static boolean isNumeric(String str) {
		try {
			//System.out.println("Is null numeric?: " + isNumeric(null));
			Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		
		return true;
	}
	
	private static void inputTest(){
		Scanner scanner = new Scanner(System.in);
		boolean good = false;
		System.out.println("Input an integer: ");
		while (!good){
			try{
				int i = scanner.nextInt();
				System.out.println("You entered: " + i);
				good = true;
			}catch(InputMismatchException inputMismatchException){
				System.out.println("Try again");
				scanner.close();
				scanner = new Scanner(System.in);
				//scanner.reset();
				//^resets Delimiter, Locale, & Radix, not input
			}
		}
		scanner.close();
	}
	
	private static void exceptionTest() throws Exception{
		int i = 0;
		i++;
		if (i > 0) 
			throw new Exception("exceptionTest");
	}
	
	private static void minuteThread(){
		final Date masterDate = new Date();
		long lastMinuteCount = 0;
		while (true){
			final Date slaveDate = new Date();
			final long diff = slaveDate.getTime() - masterDate.getTime();
        	final long diffSeconds = TimeUnit.MILLISECONDS.toSeconds(diff) % 60;
        	final long diffMinutes = TimeUnit.MILLISECONDS.toMinutes(diff);
    	    final long diffHours = TimeUnit.MILLISECONDS.toHours(diff);
    	    
			if (diffMinutes == lastMinuteCount){
				System.out.println("Less than 1 minute ("+diffSeconds+" seconds) has passed; sleeping for a bit...");
			}else{
				System.out.println("1 minute has passed; total minutes: " + diffMinutes + " at " + slaveDate);
				lastMinuteCount = diffMinutes;
//				break;
			}
			try{
				Thread.sleep(10000);
			}catch(InterruptedException exception){
				System.out.println("Caught InterruptedException:" + exception);
			}
		}
	}
	
	private static void charCount(){
		String someString = "elephant";
		long count = someString.chars().filter(ch -> ch == 'e').count();
		long count2 = someString.codePoints().filter(ch -> ch == 'e').count();
		System.out.println("count = " + count + "; count2 = " + count2);
	}
	
	private static void printOdds(){
 	   for (int i=1; i<=99; i++){
    	    if (i % 2 == 1)
        	    System.out.println(i);
	    }
	}
	
	public static String replace(String str, char oldChr, char newChr) {
       if (str == null || str.length() == 0) return str;
       if (str.indexOf(oldChr) == -1) return str;
       for (int i =0; i< str.length(); i++){
           if (str.charAt(i) == oldChr){
			    String firstHalf = str.substring(0,i);
               String secondHalf = str.substring(i+1);
               str = firstHalf + newChr + secondHalf;
               }
       }
       return str;
	}
}