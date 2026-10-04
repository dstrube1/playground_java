package com.dstrube;

/*
From ~/java:

Mac:
javac -d bin com/dstrube/PhoneNumberPuzzle.java
java -cp bin com.dstrube.PhoneNumberPuzzle

Starting here:
https://www.youtube.com/shorts/ausLKMojXaY
Math fact: 
Given any valid phone number P (10 digits), it possible to find another number A bigger than 0,
such that if you multiply P by A, it gives you a bigger number that is all 0s and 1s. For example, 2222222222 * 5 = 11111111110

Also, if the number ends in 1, 3, 7, or 9, 
then it's possible to find a number whose every digit is just a 1.
(Interesting: if the number doesn't end in one of those numbers, 
does that mean it's impossible to find such a product?)
Goal of the puzzle: prove why this is true.

My first goal: explore this fact.
Warning: just finding the number by brute force will probably take too much computation. 
Must be a little clever here.

For each number from 0-9, 
what are all the possible first digits of products from that number?

0 => 0
1 => 0,1,2,3,4,5,6,7,8,9
2 => 0,2,4,6,8
3 => 0,3,6,9,2,5,8,1,4,7
4 => 0,4,8,2,6
5 => 0,5
6 => 0,6,2,8,4
7 => 0,7,4,1,8,5,2,9,6,3
8 => 0,8,6,4,2
9 => 0,9,8,7,6,5,4,3,2,1

According to phoneNumberPuzzle.py:
2222222222 * 5 = 11111111110 : 11111111110
3333333333 * 33333333336666664960 = 111111111111111111111111111111 : 111111111111111105501764517888
4444444444 * 25 = 111111111100 : 111111111100
5555555555 * 2 = 11111111110 : 11111111110

https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html
https://docs.oracle.com/javase/8/docs/api/java/math/BigDecimal.html
https://docs.oracle.com/javase/8/docs/api/java/lang/Long.html
https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/math/BigDecimal.html
*/

import java.math.BigInteger;
import java.math.BigDecimal;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
	
public class PhoneNumberPuzzle {
	/*
	final static BigInteger b2s 			= new BigInteger("2222222222"); 
	final static BigInteger b1s 			= new BigInteger("1111111111"); 
	final static BigInteger max_P 			= new BigInteger("9999999999"); 
	final static BigInteger min_binary 		= new BigInteger("1000000000");
	*/
	final static long L2s 			= 2222222222L; 
	final static long L1s 			= 1111111111L; 
	final static long max_P 		= 9999999999L; 
	final static long min_binary 	= 1000000000L;
	final static String max_binary = Long.toString(Long.MAX_VALUE, 2);
	
	public static void main(String[] args) {
		long test_num = L2s;
		/*
		// Let's first confirm the findings of phoneNumberPuzzle.py:
		BigInteger b3s = new BigInteger("3333333333"); 
		BigInteger b4s = new BigInteger("4444444444"); 
		BigInteger b5s = new BigInteger("5555555555"); 

		BigInteger b5 = new BigInteger("5"); 
		BigInteger b3Solution = new BigInteger("33333333336666664960"); 
		BigInteger b25 = new BigInteger("25"); 
		BigInteger b2 = new BigInteger("2"); 
		
		These are mostly all valid...
		System.out.println("Solution for b2s: " + b2s.multiply(b5)); // valid

		// except for: 
		System.out.println("Solution for b3s: " + b3s.multiply(b3Solution)); // Invalid

		System.out.println("Solution for b4s: " + b4s.multiply(b25)); // valid
		System.out.println("Solution for b5s: " + b5s.multiply(b2)); // valid
		
		// Min and max radixes (radices?):
		System.out.println("Character.MIN_RADIX: " + Character.MIN_RADIX); // 2
		System.out.println("Character.MAX_RADIX: " + Character.MAX_RADIX); // 36
		
		// Test of toString with radix:
		final BigInteger radixTest = new BigInteger("7");
		System.out.println("7 in binary: " + radixTest.toString(2)); // 111
		
		System.out.println("Long.MAX_VALUE: " + Long.MAX_VALUE); // 9223372036854775807
		System.out.println("In binary: " + max_binary); 
		// 111111111111111111111111111111111111111111111111111111111111111
		System.out.println("Back to a long:" + Long.parseLong(max_binary, 2)); // 9223372036854775807

		workingFromHint();
		*/
		
		//Solution from Gemini:
		geminiSolution();
		
		//Confirming the geminiSolution:
		BigInteger b9s = new BigInteger("9999999999");
		BigInteger b9sProposed = new BigInteger("11111111112222222222333333333344444444445555555555666666666677777777778888888889");
		BigInteger b9sResult = b9s.multiply(b9sProposed);
		System.out.println("b9s (" + b9s + ") * b9sProposed (" + b9sProposed + ") = " + b9sResult);
		
		System.out.println("Done");
	}
	
	private static boolean isOnly1sAnd0s(BigInteger num) {
		//TODO: Should num be a BigInteger or String?
		
		if (num == null || num.toString().length() == 0) 
			return false;
		
		String num_str = num.toString();
		for(char c : num_str.toCharArray()){
			if (c == '0' || c == '1')
				continue;
			else
				return false;
		}
		return true;
	}
	
	/*
	Make a Java function like this Python one:
def nextBinary(num):
	# Takes in something that might be numeric, might be string
	# Returns next binary as string
	# Returns 0 if input is not binary
	if not isOnly1sAnd0s(num): 
		print("ERROR: this is not binary: " + num)
		return 0
	num_str = str(num)
	if num_str[0] == '0':
		num_str[0] = '1'
		return num_str
	else:
		dec_num = int(num, 2)
		dec_num += 1
		bin_num = bin(dec_num)[2:] # remove the "0b" prefix
		return bin_num
	*/
	private static String nextBinary(BigInteger num){
		//TODO: Should num be a BigInteger or String?
		if (!isOnly1sAnd0s(num)){
			System.out.println("ERROR: this is not binary: " + num);
			return "";
		}
		//TODO
		//BigInteger num10 = new BigInteger(num.toString())
		return "";
	}
	
	private static void testNextBinary(){
		BigInteger test = new BigInteger("0");
		String actual = nextBinary(test);
		String expected = "1";

		test = new BigInteger("1", 2);
		actual = nextBinary(test);
		expected = "10";
		
		test = new BigInteger("10", 2);
		actual = nextBinary(test);
		expected = "11";
		
		test = new BigInteger("11", 2);
		actual = nextBinary(test);
		expected = "100";
		
		test = new BigInteger("111111111111111111111111111111111111111111111111111111111111110", 2);
		actual = nextBinary(test);
		expected = "111111111111111111111111111111111111111111111111111111111111111";
		
		test = new BigInteger("111111111111111111111111111111111111111111111111111111111111111", 2);
		actual = nextBinary(test);
		expected = "0";
		
		test = null;
		actual = nextBinary(test);
		expected = "";
		
		test = new BigInteger("2");
		actual = nextBinary(test);
		expected = "";
				
	}
	
	private static void workingFromHint(){
	/*
	Hint in the comments:
===
Here's a way to find the number that multiply by your phone number equals all 1s (except it ends with 5 or even number).

1/(9*your phone number), and the cyclic section is that number.

And here's the proof (not very rigorous, tho)

1/9=0.11111...
1/9n=0.11111.../n
For any given number n which is coprime to 10, 
1/9n is a repeating decimal; its product with n equals 0.11111...
And if we take the cyclic section and multiply it by n, 
we'll get all 1s as well.
===
	*/
		double product = 2222222222L * 9;
		System.out.println("Progress: product: " + product);
		double quotient = 1 / product;
		System.out.println("quotient: " + quotient);
		try{
			BigDecimal bdProduct = new BigDecimal(product);
			BigDecimal bdQuotient = BigDecimal.ONE.divide(bdProduct);
			System.out.println("bdQuotient: " + bdQuotient);
		}
		catch (ArithmeticException ae){
			System.out.println("Caught ArithmeticException");
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	// Gemini solution
	/*
To solve this for any number efficiently, you can use a Breadth-First Search (BFS) 
algorithm combined with modular arithmetic. Instead of multiplying P by A = 1, 2, 3... 
(which takes too long), we build numbers consisting of only 1s and 0s (1, 10, 11, 100, 
101, etc.) and check their remainders when divided by $P$. By only tracking remainders 
we haven't seen yet, we ensure the algorithm runs incredibly fast—visiting at most $P$ states.

If you brute-force check every number made of 0s and 1s, the numbers get massively large, 
slowing down the computer.This BFS algorithm uses modulo math. If 10 % 7 = 3, then 
100 % 7 is the same as (3 * 10) % 7. We never have to calculate the division on gigantic, 
50-digit numbers during the search. We only do simple math on numbers smaller than $P$ 
until we hit a remainder of 0. Once we find the path of 1s and 0s that results in a 0 
remainder, we know we have our exact multiple.

P = 2222222222 # A = 5
P = 3333333333 #? 33333333336666666667
P = 4444444444 # A = 25
P = 5555555555 # A = 2
P = 6666666666 #? 166666666683333333335
P = 7777777777 # A = 13
P = 8888888888 # A = 125
P = 9999999999 # ???

TODO: Verify 3s, 6s, and find 9s
	*/
	/////////////////////////////////////////////////////////////////////////////////
	
	// First attempt - runs out of memory searching for 9s
	// Helper class to store the BFS state
    static class State {
        String numStr;
        long remainder;

        State(String numStr, long remainder) {
            this.numStr = numStr;
            this.remainder = remainder;
        }
    }
    
    // Class to hold the final result
    static class Result {
        BigInteger multiplier;      // A
        BigInteger binaryMultiple;  // P * A

        Result(BigInteger multiplier, BigInteger binaryMultiple) {
            this.multiplier = multiplier;
            this.binaryMultiple = binaryMultiple;
        }
    }
    
    public static Result findMultiplier_0(long P) {
        if (P <= 0) return null;

        // ArrayDeque is faster than LinkedList for standard Queue operations
        Queue<State> queue = new ArrayDeque<>();
        Set<Long> visitedRemainders = new HashSet<>();

        long initialRem = 1 % P;
        queue.add(new State("1", initialRem));
        visitedRemainders.add(initialRem);

        while (!queue.isEmpty()) {
            State current = queue.poll();

            // Found a valid multiple!
            if (current.remainder == 0) {
                BigInteger multiple = new BigInteger(current.numStr);
                BigInteger bigP = BigInteger.valueOf(P);
                BigInteger A = multiple.divide(bigP);
                return new Result(A, multiple);
            }

            // Append '0'
            long rem0 = (current.remainder * 10) % P;
            if (!visitedRemainders.contains(rem0)) {
                visitedRemainders.add(rem0);
                queue.add(new State(current.numStr + "0", rem0));
            }

            // Append '1'
            long rem1 = (current.remainder * 10 + 1) % P;
            if (!visitedRemainders.contains(rem1)) {
                visitedRemainders.add(rem1);
                queue.add(new State(current.numStr + "1", rem1));
            }
        }

        return null;
    }
    
	private static void geminiSolution_0(){
		long P = L2s; 
		while (P <= max_P){
			try{
	        	Result result = findMultiplier_0(P);

	    	    if (result != null) {
    	    	    System.out.println("P: " + P);
	            	System.out.println("A: " + result.multiplier);
	            	System.out.println("Multiple (P * A): " + result.binaryMultiple);
    	    	} else {
        		    System.out.println("No solution found.");
		        }
	        }catch(OutOfMemoryError oome){ 
	        	System.out.println("Caught OutOfMemoryError");
	        }
	        P += L1s;
        }
	}
	
	// Second attempt - searching just for 9s with memory optimization
	
	// Node in the search tree (parent-pointer chain)
    static class Node {
        long remainder;
        char digit;   // '0' or '1'
        Node parent;  // Pointer back to the previous state

        Node(long remainder, char digit, Node parent) {
            this.remainder = remainder;
            this.digit = digit;
            this.parent = parent;
        }
    }
    
	//static class Result is unchanged
	
	public static Result findMultiplier_1(long P) {
        if (P <= 0) return null;

        Queue<Node> queue = new ArrayDeque<>();
        Set<Long> visited = new HashSet<>();

        long initialRem = 1 % P;
        Node startNode = new Node(initialRem, '1', null);
        queue.add(startNode);
        visited.add(initialRem);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // Found a valid multiple! Reconstruct string backward from leaf to root
            if (current.remainder == 0) {
                StringBuilder sb = new StringBuilder();
                Node curr = current;
                
                while (curr != null) {
                    sb.append(curr.digit);
                    curr = curr.parent;
                }

                // Reverse to get digits in root-to-leaf order
                String binaryStr = sb.reverse().toString();

                BigInteger multiple = new BigInteger(binaryStr);
                BigInteger bigP = BigInteger.valueOf(P);
                BigInteger A = multiple.divide(bigP);
                return new Result(A, multiple);
            }

            // Append '0'
            long rem0 = (current.remainder * 10) % P;
            if (!visited.contains(rem0)) {
                visited.add(rem0);
                queue.add(new Node(rem0, '0', current));
            }

            // Append '1'
            long rem1 = (current.remainder * 10 + 1) % P;
            if (!visited.contains(rem1)) {
                visited.add(rem1);
                queue.add(new Node(rem1, '1', current));
            }
        }

        return null;
    }
	
	private static void geminiSolution_1(){
	/*
Why this saves massive amounts of memory
1- Zero Intermediate String Garbage: Java strings are immutable. Doing numStr + "0" on a 
50-digit string allocates a brand-new 51-character string (plus internal byte[] arrays) 
at every single step.
2- Fixed Lightweight Footprint: Each Node object requires only ~32 bytes in 64-bit Java 
(object header + reference to parent + char + long).
3- Single Allocation at the End: StringBuilder is allocated only once when remainder 0 is 
reached, traversing from leaf to root in $O(L)$ time, where $L$ is the length of the binary string.
	*/
		long P = max_P;

        Result result = findMultiplier(P);

        if (result != null) {
            System.out.println("P: " + P);
            System.out.println("A: " + result.multiplier);
            System.out.println("Multiple (P * A): " + result.binaryMultiple);
        } else {
            System.out.println("No solution found.");
        }
        
        // Still hits an OutOfMemoryError :(
	}
	
	// Third attempt - success
	//static class Result is still unchanged
	// No other static class (like Node or State) declared in this one. Interesting...
	
	public static Result findMultiplier(long P) {
		if (P <= 0) return null;

        // Step 1: Factor out powers of 2 and 5 from P
        long tempP = P;
        int count2 = 0;
        while (tempP % 2 == 0) {
            count2++;
            tempP /= 2;
        }

        int count5 = 0;
        while (tempP % 5 == 0) {
            count5++;
            tempP /= 5;
        }

        long M = tempP;
        int m = Math.max(count2, count5); // Number of trailing zeros needed

        // Step 2: Find k = smallest integer where 10^k % (9 * M) == 1
        BigInteger targetMod = BigInteger.valueOf(M).multiply(BigInteger.valueOf(9));
        
        int k = 1;
        BigInteger currentRem = BigInteger.TEN.mod(targetMod);
        BigInteger one = BigInteger.ONE;

        while (!currentRem.equals(one)) {
            currentRem = currentRem.multiply(BigInteger.TEN).mod(targetMod);
            k++;
        }

        // Step 3: Build the binary string (k ones followed by m zeros)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.append('1');
        }
        for (int i = 0; i < m; i++) {
            sb.append('0');
        }

        BigInteger binaryMultiple = new BigInteger(sb.toString());
        BigInteger bigP = BigInteger.valueOf(P);
        BigInteger A = binaryMultiple.divide(bigP);

        return new Result(A, binaryMultiple);
	}
	
	private static void geminiSolution(){
		long P = 9999999999L;

        Result result = findMultiplier(P);

        if (result != null) {
            System.out.println("P: " + P);
            System.out.println("A: " + result.multiplier);
            System.out.println("Binary Multiple (P * A): " + result.binaryMultiple);
            System.out.println("Digit Count of Multiple: " + result.binaryMultiple.toString().length());
        }
        /*
        According to Gemini:

The reason BFS hits an OutOfMemoryError for P = 9999999999L is that BFS tries to find 
the shortest binary multiple. For large values of P, the search tree branches heavily, 
and tracking millions of visited remainders in Java's heap memory quickly exhausts your RAM.

Instead of a graph search, you can solve this using number theory in O(1) auxiliary memory.

The Mathematical Solution: Repunits with Trailing Zeros
Instead of searching for arbitrary patterns of 0s and 1s, we can directly construct a 
valid number consisting of all 1s followed by zeros (111...11000...0).

Any integer P can be factored as:

P = 2^a * 5^b * M

(where M is the remaining part of P that is coprime to 10)

1- Handling M: Numbers made entirely of 1s (1, 11, 111, ...) are called repunits (R_k). By 
Euler's Totient Theorem, there is always a repunit R_k divisible by M. The required 
number of ones (k) is the smallest integer k greater than or equal to 1 where:

10^k = 1 (mod 9M)

2- Handling 2s and 5s: Appending m = max(a, b) trailing zeros to R_k ensures the number 
is also divisible by 2^a * 5^b.

Because k can be found using a simple loop with modular arithmetic, this approach uses 
zero extra heap memory for state tracking.

How it Performs
For P = 2222222222L:
Calculates k = 10 ones and m = 1 zero -> Multiple = 11111111110 (A = $). (Instant)

For $P = 9999999999L:
Calculates k = 90 ones and m = 0 zeros -> Multiple = 90 ones (A is an 81-digit integer). 
(Runs in less than 1 millisecond with zero memory overhead)
        */
	}
}


