package com.dstrube;

/*
compile:
javac -d bin com/dstrube/Pi.java
run:
java -cp bin com.dstrube.Pi

From:
https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html

https://docs.oracle.com/javase/8/docs/api/java/math/BigDecimal.html

*/

import java.math.BigDecimal;

public class Pi{
	private static float piF = 4.0f;
	private static double piD = 4.0;
	private static long denominator;
	private static boolean minus;
	private static long count;
	
	public static void main(String[] args){
		System.out.println("Math.Pi: " + Math.PI);							// 3.141592653589793
		//System.out.println("Float.SIZE = " + Float.SIZE);					// 32
		//System.out.println("Float.MIN_VALUE = " + Float.MIN_VALUE);		// 1.4E-45
		//System.out.println("Float.MIN_NORMAL = " + Float.MIN_NORMAL);		// 1.1754944E-38
		//System.out.println("Float.MIN_EXPONENT = " + Float.MIN_EXPONENT); // -126
		
		final int MILLION =  1_000_000;
		final int TMILLION = 10_000_000; //billion and HMillion don't work right
		double extraDigits = Math.PI * MILLION;
		//System.out.println("pi * million: " + extraDigits);

		extraDigits %= 1;
		extraDigits *= TMILLION;
		System.out.println("extra digits:    " + ("" + extraDigits).replace(".",""));	
		// 3.141592653589793
		// 		   6535897930152714
		
		/////////////////////////////////////////////////////////////////////////////////
		// To illustrate "not right" :
		final int HMILLION = 100_000_000; 
		extraDigits = Math.PI * MILLION;
		extraDigits %= 1;
		extraDigits *= HMILLION;
		//System.out.println("extra digits done badly (pt 1 of 2):   " + extraDigits);
		// 6.535897930152714E7
		
		final int BILLION =  1_000_000_000; 
		extraDigits = Math.PI * MILLION;
		extraDigits %= 1;
		extraDigits *= BILLION;
		//System.out.println("extra digits done badly (pt 2 of 2):   " + extraDigits);
		// 6.535897930152714E8
		
		//What if step 1 = HMILLION and step 2 = MILLION
		extraDigits = Math.PI * HMILLION;
		extraDigits %= 1;
		extraDigits *= MILLION;
		System.out.println("extra digits done differently: " + ("" + extraDigits).replace(".",""));	
		System.out.println("Compare to Math.Pi:  " + Math.PI);
		// 3.141592653589793
		//           3589792847633362
		// noticeably different from 
		// 		   6535897930152714
				
		// END Illustration
		/////////////////////////////////////////////////////////////////////////////////
		
		//1- Which (if either) extraDigits is accurate?
		//According to this:
		//https://www.piday.org/million/
		//3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706798214808651328230
		//first:  6535897930152714 - no
		//3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706798214808651328230
		//second: 	3589792847633362 - even worse
		
		//2- Can we do better with BigDecimal and an improved algorithm?
		//Several algorithms to choose from here:
		//https://en.wikipedia.org/wiki/Pi
		
		// All BigDecimal constructors have at least 1 param, so can't just start off like this:
		//BigDecimal bd = new BigDecimal();

		calcFloatPi();
		//Google says pi =~ 					  3.14159265359
		System.out.println("Float pi: " + piF); //3.1415968
		
		//calcDoublePi_GL(); //This won't end anytime soon
		//System.out.println("Double pi: " + piD);
		//System.out.println("Double.MAX_VALUE = " + Double.MAX_VALUE);//1.7976931348623157E308
		
		//test
		// print out precision ends... v here
		double bigPi = 3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706798214808651328230;
		//System.out.println("Testing : bigPi: " + bigPi);
		//What about ...
		BigDecimal bd = new BigDecimal("3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706798214808651328230");
		System.out.println("bigPi stored as a BigDecimal: " + bd.toPlainString()); //nice, now we're cooking...

		System.out.println("Done");
	}
	
	//https://en.wikipedia.org/wiki/Pi
	//around "Rate of convergence"
	
	public static void calcFloatPi() {
		//Gregory–Leibniz series
		float piB4 = 0.0f;
		denominator = 1;
		minus = true;
		count = 0;
		while (piB4 != piF) {
			piB4 = piF;
			denominator += 2;
			if (minus){
				minus = false;
				piF -= (float)4 / denominator;
			} else {
				minus = true;
				piF += (float)4 / denominator;
			}
			count++;
		}
		System.out.println("Float pi stopped changing after this many cycles: " + count); //16,777,216
	}

/*
PROBLEM: this is never true: piB4 != piD
printing out progress once every 1000000000 shows that at some point there is an overflow or some other error
that makes pi have an invalid valid, starting with 9.xxx...

SOLUTION: problem was that denominator was an int, and getting incremented led to an overflow before long;
changing it -and count- to a long made the overflow something that one needn't worry about as soon;
by the time count is very big (790800000000), the calculation is pretty close (3.1415926535872503)
note: 1000000000000000000 is a few orders of magnitude bigger than 790800000000
*/
	public static void calcDoublePi_GL() {
		//More Gregory–Leibniz series
		//Next try Nilakantha...
		double piB4 = 0.0;
		denominator = 1;
		minus = true;
		count = 1;
		final boolean debug = true;
		while (count > 0){//piB4 != piD) {//
			piB4 = piD;
			denominator += 2;
			if (minus){
				minus = false;
				piD -= (double)4 / denominator;
			} else {
				minus = true;
				piD += (double)4 / denominator;
			}
			count++;
			if (debug && count % 1000000000 == 0){
				System.out.println("count: "+count+"; Double pi progress: " + piD);
				/*
count: 1000000000; Double pi progress: 3.1415926525880504
count: 2000000000; Double pi progress: 3.1415926530880767
count: 3000000000; Double pi progress: 3.1415926532549254
count: 4000000000; Double pi progress: 3.1415926533379395
count: 5000000000; Double pi progress: 3.141592653388201
count: 6000000000; Double pi progress: 3.141592653421575
count: 7000000000; Double pi progress: 3.1415926534453438
count: 8000000000; Double pi progress: 3.1415926534632597
count: 9000000000; Double pi progress: 3.1415926534771974
count: 10000000000; Double pi progress: 3.141592653488346
count: 11000000000; Double pi progress: 3.1415926534974092
count: 12000000000; Double pi progress: 3.14159265350503
count: 13000000000; Double pi progress: 3.1415926535114695
count: 14000000000; Double pi progress: 3.1415926535169376
count: 15000000000; Double pi progress: 3.1415926535216876
count: 16000000000; Double pi progress: 3.141592653525827
count: 17000000000; Double pi progress: 3.141592653529473
count: 18000000000; Double pi progress: 3.1415926535327663
count: 19000000000; Double pi progress: 3.141592653535702
count: 20000000000; Double pi progress: 3.1415926535383965
count: 21000000000; Double pi progress: 3.1415926535407954
count: 22000000000; Double pi progress: 3.1415926535429324
count: 23000000000; Double pi progress: 3.1415926535449397
count: 24000000000; Double pi progress: 3.141592653546772
				*/
				//TODO: First, let's verify that re-running this gives the same numbers,
				//then check each to see how far off they each are, if they're getting closer or farther, or bouncing around
				//this see if some other algorithms do better or worse
			}
		}
		System.out.println("Double pi stopped calculating after this many cycles: " + count);
	}

}