package com.dstrube;

/*
compile:
javac -d bin com/dstrube/Pi.java
run:
java -cp bin com.dstrube.Pi

Relevant:
https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html
https://docs.oracle.com/javase/8/docs/api/java/math/BigDecimal.html

https://en.wikipedia.org/wiki/Pi
^ around "Rate of convergence"

*/

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Map;
import java.util.HashMap;


public class Pi{
	private static final boolean debug = true;

	//A float approximation of pi
	private static float piF = 4.0f;
	//A double approximation of pi
	private static double piD = 4.0;
	
	private static long denominator;
	private static boolean isMinus;
	private static long count;
	private static final long limit = Long.parseLong("24000000000"); //24_000_000_000 => 24 billion
	
	// All BigDecimal constructors have at least 1 param, so can't just start off like this:
	//BigDecimal bd = new BigDecimal();
	private static final BigDecimal bd = new BigDecimal("3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706798214808651328230");
	
	public static void main(String[] args){
	
		//piExplorationBasics();
		
		//calcFloatPi();
		//Google says pi =~ 					  3.14159265359
		//System.out.println("Float pi: " + piF); //3.1415968
		
		//calcPi_GL(); //Gregory–Leibniz series
		//System.out.println("Double pi: " + piD);
		//System.out.println("Double.MAX_VALUE = " + Double.MAX_VALUE);//1.7976931348623157E308
		
		// print out precision ends... v here by default
		double bigPi = 3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706798214808651328230;
		//System.out.println("Testing : bigPi: " + bigPi);

		//What about ...		
		//System.out.println("bigPi stored as a BigDecimal: " + bd.toPlainString()); 
		//nice - prints out the whole thing - now we're cooking...

		//calcPi_N(); //Nilakantha series
		//calcPi_M(); //Machin-like formula
		calcPi_M_BD(); //Machin-like formula with BigDecimal
		//calcPi_C(); //Chudnovsky_algorithm
		
		System.out.println("Done");
	}
	
	private static void piExplorationBasics(){
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
		//System.out.println("extra digits:    " + ("" + extraDigits).replace(".",""));	
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
		//System.out.println("extra digits done differently: " + ("" + extraDigits).replace(".",""));	
		//System.out.println("Compare to Math.Pi:  " + Math.PI);
		// 3.141592653589793
		//           3589792847633362
		// noticeably different from 
		// 		   6535897930152714
				
		// END Illustration
		/////////////////////////////////////////////////////////////////////////////////
		
		//1- Which (if either) extraDigits is accurate?
		//According to this:
		//https://www.piday.org/million/
		//this is the first line in an sufficiently long and accurate approximation of pi:
		//3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706798214808651328230
		//first:  6535897930152714 - no
		//3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706798214808651328230
		//second: 	3589792847633362 - even worse
		
		//2- Can we do better with BigDecimal and an improved algorithm?
		//Several algorithms to choose from here:
		//https://en.wikipedia.org/wiki/Pi
	}
	
	private static void calcFloatPi() {
		//Gregory–Leibniz series
		float piB4 = 0.0f;
		denominator = 1;
		isMinus = true;
		count = 0;
		while (piB4 != piF) {
			piB4 = piF;
			denominator += 2;
			if (isMinus){
				isMinus = false;
				piF -= (float)4 / denominator;
			} else {
				isMinus = true;
				piF += (float)4 / denominator;
			}
			count++;
		}
		System.out.println("Float pi stopped changing after this many cycles: " + count); //16,777,216
	}

	private static void calcPi_GL() {
		//More Gregory–Leibniz series
		//Next try Nilakantha...
		double piB4 = 0.0;
		
		//So that this can be called before or after other functions:
		//Reset piD
		piD = 4.0;
		
		denominator = 1;
		isMinus = true;
		count = 1;
		final Map<Long,Double> progressGL = new HashMap<>();
		progressGL.put(Long.parseLong("1000000000"), (double)3.1415926525880504);
		progressGL.put(Long.parseLong("2000000000"), (double)3.1415926530880767);
		progressGL.put(Long.parseLong("3000000000"), (double)3.1415926532549254); 
		//if "put"ting the number literal (i.e. not in a string), even if trying to cast it like "(long)", 
		//then putting values in the map started failing here at 3 billion, thinking it's too big to be an int -_-
		progressGL.put(Long.parseLong("4000000000"), (double)3.1415926533379395);
		progressGL.put(Long.parseLong("5000000000"), (double)3.141592653388201);
		progressGL.put(Long.parseLong("6000000000"), (double)3.141592653421575);
		progressGL.put(Long.parseLong("7000000000"), (double)3.1415926534453438);
		progressGL.put(Long.parseLong("8000000000"), (double)3.1415926534632597);
		progressGL.put(Long.parseLong("9000000000"), (double)3.1415926534771974);
		progressGL.put(Long.parseLong("10000000000"), (double)3.141592653488346);
		progressGL.put(Long.parseLong("11000000000"), (double)3.1415926534974092);
		progressGL.put(Long.parseLong("12000000000"), (double)3.14159265350503);
		progressGL.put(Long.parseLong("13000000000"), (double)3.1415926535114695);
		progressGL.put(Long.parseLong("14000000000"), (double)3.1415926535169376);
		progressGL.put(Long.parseLong("15000000000"), (double)3.1415926535216876);
		progressGL.put(Long.parseLong("16000000000"), (double)3.141592653525827);
		progressGL.put(Long.parseLong("17000000000"), (double)3.141592653529473);
		progressGL.put(Long.parseLong("18000000000"), (double)3.1415926535327663);
		progressGL.put(Long.parseLong("19000000000"), (double)3.141592653535702);
		progressGL.put(Long.parseLong("20000000000"), (double)3.1415926535383965);
		progressGL.put(Long.parseLong("21000000000"), (double)3.1415926535407954);
		progressGL.put(Long.parseLong("22000000000"), (double)3.1415926535429324);
		progressGL.put(Long.parseLong("23000000000"), (double)3.1415926535449397);
		progressGL.put(Long.parseLong("24000000000"), (double)3.141592653546772);
		
		BigDecimal previousDrift = BigDecimal.ZERO;
		BigDecimal currentDifference = BigDecimal.ZERO;
		
/*
PROBLEM: this is never true: piB4 != piD
printing out progress once every 1000000000 shows that at some point there is an overflow or some other error
that makes pi have an invalid valid, starting with 9.xxx...

SOLUTION: problem was that denominator was an int, and getting incremented led to an overflow before long;
changing it -and count- to a long made the overflow something that one needn't worry about as soon;
by the time count is very big (790800000000), the calculation is pretty close (3.1415926535872503)
note: 1000000000000000000 is a few orders of magnitude bigger than 790800000000
*/
		//while (piB4 != piD) {//this comparison is no longer as useful as checking for an overflow
		while (count <= limit){
			piB4 = piD;
			denominator += 2;
			if (isMinus){
				isMinus = false;
				piD -= (double)4 / denominator;
			} else {
				isMinus = true;
				piD += (double)4 / denominator;
			}
			count++;
			if (debug && count % 1000000000 == 0){
				//System.out.println("count: " + count + "; Double pi progress (Gregory–Leibniz series): " + piD);
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
				//1- verify that re-running this gives the same numbers
				//2- check each to see how far off they each are, if they're getting closer or farther, or bouncing around
				//3- do other algorithms do better or worse?
				if (progressGL.containsKey(count)) {
					if (progressGL.get(count) != piD){
						System.out.println("Alert: " + progressGL.get(count) + " != " + piD);
					}else{
						System.out.println("At count " + count + ", progress = " + piD);
						final BigDecimal piBD = new BigDecimal(piD);
						final int compareResult = bd.compareTo(piBD); 
						//-1, 0, or 1 as this BigDecimal (bd) is numerically less than, equal to, or greater than val (piBD).
						if (compareResult == -1){
							currentDifference = piBD.subtract(bd);
							System.out.println("Progress is greater than pi, by " + currentDifference.toPlainString());
						}else if (compareResult == 1){
							currentDifference = bd.subtract(piBD);
							System.out.println("Pi is greater than progress, by " + currentDifference.toPlainString());
						}else{
							//0 - highly unlikely
							System.out.println("compareResult == 0 ?!?!?!?!");
							return;
						}
						if (previousDrift.equals(BigDecimal.ZERO)){
							System.out.println("First comparison; no previous to compare to...");
						}else{
							System.out.println("Previous difference was " + previousDrift.toPlainString());
							final int previousCompareResult = currentDifference.compareTo(previousDrift); 
							if (previousCompareResult == -1){
								System.out.println("currentDifference is less than previousDrift - getting closer :)");
							}else if (previousCompareResult == 1){
								System.out.println("previousDrift is less than currentDifference - getting farther :(");
							}else{
								//0 - seems a little less unlikely, but who knows by how much
								System.out.println("previousCompareResult == 0 ?!?!?!?!");
							
							}
						}
						System.out.println();//need a new line to keep these print outs clearer
						previousDrift = currentDifference;
					}
				}else{
					System.out.println("progressGL does not contain key " + count);
					System.out.println("End of progressGL; exiting...");
					break;
				} 
			}
		}
		System.out.println("Double pi stopped calculating after this many cycles: " + count);
	}
	
	private static void calcPi_N() {
		//Nilakantha series
		//"converges more rapidly than the Gregory–Leibniz series"; //quote from wikipedia
		//but, unlike Gregory–Leibniz series, the Nilakantha series seems not to consistently get closer to actual pi
		double piB4 = 0.0;
		
		//So that this can be called before or after other functions:
		//Reset piD
		piD = 3.0;
		
		denominator = 1;
		long denominatorA = 0;
		long denominatorB = 1;
		long denominatorC = 2;
		isMinus = false;
		count = 1;
		
		final Map<Long,Double> progressN = new HashMap<>();
		progressN.put(Long.parseLong("1000000000"), (double)3.141592653656787);
		progressN.put(Long.parseLong("2000000000"), (double)3.141592653623129);
		progressN.put(Long.parseLong("3000000000"), (double)3.141592653636524); 
		progressN.put(Long.parseLong("4000000000"), (double)3.1415926535171392);
		progressN.put(Long.parseLong("5000000000"), (double)3.141592654604429);
		progressN.put(Long.parseLong("6000000000"), (double)3.1415926481982037);
		progressN.put(Long.parseLong("7000000000"), (double)3.1415926478103313);
		progressN.put(Long.parseLong("8000000000"), (double)3.1415926467436424);
		progressN.put(Long.parseLong("9000000000"), (double)3.141592646963207);
		progressN.put(Long.parseLong("10000000000"), (double)3.141592646404458);
		progressN.put(Long.parseLong("11000000000"), (double)3.1415926480128578);
		progressN.put(Long.parseLong("12000000000"), (double)3.141592694294119);
		progressN.put(Long.parseLong("13000000000"), (double)3.1415926890000505);
		progressN.put(Long.parseLong("14000000000"), (double)3.141592693834243);
		progressN.put(Long.parseLong("15000000000"), (double)3.1415926947776684);
		progressN.put(Long.parseLong("16000000000"), (double)3.141592694986635);
		progressN.put(Long.parseLong("17000000000"), (double)3.1415926880236005);
		progressN.put(Long.parseLong("18000000000"), (double)3.141592687566944);
		progressN.put(Long.parseLong("19000000000"), (double)3.1415926875863174);
		progressN.put(Long.parseLong("20000000000"), (double)3.1415926867359114);
		progressN.put(Long.parseLong("21000000000"), (double)3.1415926865598167);
		progressN.put(Long.parseLong("22000000000"), (double)3.141592687112181);
		progressN.put(Long.parseLong("23000000000"), (double)3.1415926857157044);
		progressN.put(Long.parseLong("24000000000"), (double)3.1415926847803513);
		
		BigDecimal previousDrift = BigDecimal.ZERO;
		BigDecimal currentDifference = BigDecimal.ZERO;
		while (count <= limit){
			
			piB4 = piD;
			denominatorA += 2;
			denominatorB += 2;
			denominatorC += 2;
			denominator = denominatorA * denominatorB * denominatorC;
			if (isMinus){
				isMinus = false;
				piD -= (double)4 / denominator;
			} else {
				isMinus = true;
				piD += (double)4 / denominator;
			}
			count++;
			if (debug && count % 1000000000 == 0){
				System.out.println("count: " + count + "; Double pi progress (Nilakantha series): " + piD);
				/*
				*/
				if (progressN.containsKey(count)) {
					if (progressN.get(count) != piD){
						System.out.println("Alert: " + progressN.get(count) + " != " + piD);
					}else{
						System.out.println("At count " + count + ", progress = " + piD);
						final BigDecimal piBD = new BigDecimal(piD);
						final int compareResult = bd.compareTo(piBD); 
						//-1, 0, or 1 as this BigDecimal (bd) is numerically less than, equal to, or greater than val (piBD).
						if (compareResult == -1){
							currentDifference = piBD.subtract(bd);
							System.out.println("Progress is greater than pi, by " + currentDifference.toPlainString());
						}else if (compareResult == 1){
							currentDifference = bd.subtract(piBD);
							System.out.println("Pi is greater than progress, by " + currentDifference.toPlainString());
						}else{
							//0 - highly unlikely
							System.out.println("compareResult == 0 ?!?!?!?!");
							return;
						}
						if (previousDrift.equals(BigDecimal.ZERO)){
							System.out.println("First comparison; no previous to compare to...");
						}else{
							System.out.println("Previous difference was " + previousDrift.toPlainString());
							final int previousCompareResult = currentDifference.compareTo(previousDrift); 
							if (previousCompareResult == -1){
								System.out.println("currentDifference is less than previousDrift - getting closer :)");
							}else if (previousCompareResult == 1){
								System.out.println("previousDrift is less than currentDifference - getting farther :(");
							}else{
								//0 - seems a little less unlikely, but who knows by how much
								System.out.println("previousCompareResult == 0 ?!?!?!?!");
							
							}
						}
						System.out.println();//need a new line to keep these print outs clearer
						previousDrift = currentDifference;
					}
				}else{
					System.out.println("progressN does not contain key " + count);
					System.out.println("End of progressN; exiting...");
					break;
				} 
			}
		}
	}
	
	private static void calcPi_M() {
		//https://en.wikipedia.org/wiki/Machin-like_formula
		
		//So that this can be called before or after other functions:
		//Reset piD
		piD = 0.0;
		
		isMinus = false;
		count = 0;
		double n = 1.0;
		while (count <= 24){
			if (isMinus){
				piD -= calcPi_M_step(n);
				isMinus = false;
			} else{
				piD += calcPi_M_step(n);
				isMinus = true;
			}
			count++;
			n += 2.0;
			if (debug){//} && count % 100 == 0){
				System.out.println("count: " + count + "; Double pi progress (Machin-like formula): " + piD);
				/*
				Interesting:
count: 1000000000; Double pi progress (Machin-like formula): 3.1415926535897944
count: 2000000000; Double pi progress (Machin-like formula): 3.1415926535897944
count: 3000000000; Double pi progress (Machin-like formula): 3.1415926535897944

				Let's try with limit / 10 and count mod / 10...
				Same:
count: 100000000; Double pi progress (Machin-like formula): 3.1415926535897944
count: 200000000; Double pi progress (Machin-like formula): 3.1415926535897944
count: 300000000; Double pi progress (Machin-like formula): 3.1415926535897944
				
				How about /100? Same. /1000? Same. 
				
				How quickly does it converge? Pretty quickly:
count: 1; Double pi progress (Machin-like formula): 3.18326359832636
count: 2; Double pi progress (Machin-like formula): 3.1405970293260608
count: 3; Double pi progress (Machin-like formula): 3.141621029325035
count: 4; Double pi progress (Machin-like formula): 3.1415917721821778
count: 5; Double pi progress (Machin-like formula): 3.1415926824044
count: 6; Double pi progress (Machin-like formula): 3.141592652615309
count: 7; Double pi progress (Machin-like formula): 3.1415926536235554
count: 8; Double pi progress (Machin-like formula): 3.141592653588603
count: 9; Double pi progress (Machin-like formula): 3.1415926535898366
count: 10; Double pi progress (Machin-like formula): 3.1415926535897927
count: 11; Double pi progress (Machin-like formula): 3.1415926535897944
count: 12; Double pi progress (Machin-like formula): 3.1415926535897944
Compare to the authority...							 3.14159265358979323846264338327950...

				Let's try to get more accurate using BigDecimal...
				*/
			}
		}
	}
	
	private static double calcPi_M_step(double n){
		double result = 0.0;
		double termA = 1.0 / n;
		double termB = 16.0 / Math.pow(5.0, n);
		double termC = 4.0 / Math.pow(239.0, n);
		result = termA * (termB - termC);
		return result;
	}
	
	private static void calcPi_M_BD() {
		//https://en.wikipedia.org/wiki/Machin-like_formula
		//with BigDecimal
		
		BigDecimal piBD = BigDecimal.ZERO;
		isMinus = false;
		count = 0;
		int n = 1;

		while (count <= 24){
			final BigDecimal result = calcPi_M_step_BD(n);
			//System.out.println("In calcPi_M_BD, result: " + result.toPlainString());
		
			if (isMinus){
				piBD = piBD.subtract(result, MathContext.DECIMAL128);
				isMinus = false;
			} else{
				piBD = piBD.add(result, MathContext.DECIMAL128);
				isMinus = true;
			}
			count++;
			n += 2;
			if (debug){
				System.out.println("count: " + count + "; BD Pi progress (Machin-like formula): " + piBD.toPlainString());
				/*
				Seems to converge around iteration 23:
				
count: 21; BD Pi progress (Machin-like formula): 3.141592653589793238463070625346883
count: 22; BD Pi progress (Machin-like formula): 3.141592653589793238463070625346556
count: 23; BD Pi progress (Machin-like formula): 3.141592653589793238463070625346569
count: 24; BD Pi progress (Machin-like formula): 3.141592653589793238463070625346569
count: 25; BD Pi progress (Machin-like formula): 3.141592653589793238463070625346569
Compare to the authority...						 3.141592653589793238462643383279502...
				Indeed seemingly a little better with BigDecimal. 
				Might do better with different MathContexts, but this is good enough for now.
				Up next, Chudnovsky_algorithm...

After improvement to the inverse function:
count: 22; BD Pi progress (Machin-like formula): 3.141592653589793238462643383279492
count: 23; BD Pi progress (Machin-like formula): 3.141592653589793238462643383279505
count: 24; BD Pi progress (Machin-like formula): 3.141592653589793238462643383279505
Compare to the authority...						 3.141592653589793238462643383279502...
Better ^_^
				*/
			}
		}

	}
	
	private static BigDecimal calcPi_M_step_BD(int n){
		final BigDecimal termA = inverse(new BigDecimal(n));
		
		final BigDecimal termB1 = new BigDecimal(16);
		final BigDecimal termB2 = new BigDecimal(5);
		final BigDecimal termB3 = termB2.pow(n);
		final BigDecimal termB = termB1.divide(termB3, MathContext.DECIMAL128);
		
		final BigDecimal termC1 = new BigDecimal(4);
		final BigDecimal termC2 = new BigDecimal(239);
		final BigDecimal termC3 = termC2.pow(n);
		final BigDecimal termC = termC1.divide(termC3, MathContext.DECIMAL128);

		final BigDecimal termBC = termB.subtract(termC);
		final BigDecimal result = termA.multiply(termBC);
		//System.out.println("At step " + n + " result = " + result.toPlainString());

		return result;
	}
	
	private static void calcPi_C() {
		//https://en.wikipedia.org/wiki/Chudnovsky_algorithm
		//Jumping straight to using BigDecimal
		BigDecimal piBD = BigDecimal.ZERO;

		//12 * sum = inverse of pi
		BigDecimal sum = BigDecimal.ZERO;

		isMinus = false;
		count = 0;
		int kFactor = 0;
		final BigDecimal TWELVE = new BigDecimal(12);

		while (count <= 24){
			final BigDecimal result = calcPi_C_step_BD(kFactor);
		
			if (isMinus){
				sum = sum.subtract(result, MathContext.DECIMAL128);
				isMinus = false;
			} else{
				sum = sum.add(result, MathContext.DECIMAL128);
				isMinus = true;
			}
			piBD = inverse(sum.multiply(TWELVE));
			count++;
			kFactor++;
			if (debug){
				System.out.println("count: " + count + "; BD Pi progress (Chudnovsky algorithm): " + piBD.toPlainString());
/*
Converges quickly, but something is definitely wrong here...
count: 1; BD Pi progress (Chudnovsky algorithm): 1288083468960.35321418124245495123
count: 2; BD Pi progress (Chudnovsky algorithm): 1288083468960.37741737960064722198
count: 3; BD Pi progress (Chudnovsky algorithm): 1288083468960.37741737960064709576
count: 4; BD Pi progress (Chudnovsky algorithm): 1288083468960.37741737960064709576

Fixed:
count: 1; BD Pi progress (Chudnovsky algorithm): 3.141592653589734207668453591578299
count: 2; BD Pi progress (Chudnovsky algorithm): 3.141592653589793238462643383587350
count: 3; BD Pi progress (Chudnovsky algorithm): 3.141592653589793238462643383279503
count: 4; BD Pi progress (Chudnovsky algorithm): 3.141592653589793238462643383279503
Compare to the authority...						 3.141592653589793238462643383279502884...

That's pretty good.
Note, this is with an improvement to the inverse function. Revisiting calcPi_M_BD...
*/
			}
		}
	}
	
	private static BigDecimal calcPi_C_step_BD(int kFactor){
		
		final BigDecimal termA = factorial(6 * kFactor);
		final BigDecimal termB = new BigDecimal((545140134 * kFactor) + 13591409);
		
		final BigDecimal termAB = termA.multiply(termB);
		
		final BigDecimal termC = factorial(3 * kFactor);
		final BigDecimal termD = factorial(kFactor).pow(3);

		//Term E is complicated
		//https://mathinsight.org/exponentiation_basic_rules
		final BigDecimal termEbase = new BigDecimal(640320);
		final BigDecimal termE1 = termEbase.pow(3 * kFactor);
		//Wrong:
		//final BigDecimal termE2a = termEbase.pow(3);
		//final BigDecimal termE2b = termEbase.sqrt(MathContext.DECIMAL128);
		//final BigDecimal termE = termE1.multiply(termE2a.multiply(termE2b));

		//Right:
		final BigDecimal termE2 = termEbase.multiply(termEbase.sqrt(MathContext.DECIMAL128));
		final BigDecimal termE = termE1.multiply(termE2);
		
		//BigDecimal square root became available in Java 9 ^_^
		//https://docs.oracle.com/javase/9/docs/api/java/math/BigDecimal.html#sqrt-java.math.MathContext-
		//Huzzah!, that makes this a little easier

		final BigDecimal termCDE = termC.multiply(termD.multiply(termE));
		
		// (A * B) / (C * D * E)
		final BigDecimal result = termAB.divide(termCDE, MathContext.DECIMAL128);
		/*if (debug){
			System.out.println("kFactor: " + kFactor);
			System.out.println("A: " + termA.toPlainString());
			System.out.println("B: " + termB.toPlainString());
			System.out.println("AB: " + termAB.toPlainString());
			System.out.println("C: " + termC.toPlainString());
			System.out.println("D: " + termD.toPlainString());
			System.out.println("E: " + termE.toPlainString());
			System.out.println("CDE: " + termCDE.toPlainString());
		}*/
		return result;
	}
	
	private static BigDecimal inverse(final BigDecimal value) {
		//Useful for Chudnovsky algorithm, and Machin-like formula
		if (value == null || value.compareTo(BigDecimal.ZERO) == 0) {
			throw new ArithmeticException("Cannot calculate inverse of zero or null");
		}
		//https://docs.oracle.com/javase/8/docs/api/java/math/RoundingMode.html
		//BigDecimal.ROUND_HALF_UP is deprecated
		//return BigDecimal.ONE.divide(value, 20, RoundingMode.HALF_UP); // 20 is the scale
		//Better:
		return BigDecimal.ONE.divide(value, MathContext.DECIMAL128);
	}

	private static BigDecimal factorial(int value) {
		BigDecimal result = BigDecimal.ONE;
		for (int i = 2; i <= value; i++) {
			result = result.multiply(BigDecimal.valueOf(i));
		}
		return result;
	}
	
}




































