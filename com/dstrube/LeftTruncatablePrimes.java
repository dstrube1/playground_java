package com.dstrube;

/*
commands to compile and run:
from ~/java
javac -d bin com/dstrube/LeftTruncatablePrimes.java

java -Xms4G -Xmx10G -cp bin com.dstrube.LeftTruncatablePrimes

*/
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
//https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet; // like HashSet, but orderly

public class LeftTruncatablePrimes{

	private static Set<Integer> primes = new LinkedHashSet<>();
	
	public static void main(String[] args){
		//According to this:
		// https://www.youtube.com/watch?v=azL5ehbw_24
		//this is the largest left truncatable prime:
		BigInteger largestLeft = new BigInteger("357686312646216567629137"); //357_686_312_646_216_567_629_137 =~ 357 sextillion
		boolean is = largestLeft.isProbablePrime(1);
		//System.out.println("Is " + largestLeft + " probably prime? " + is); //true
		
		// Can I prove it, not with isProbablePrime, but with the Sieve of Eratosthenes?
		// Can Sieve of Eratosthenes work here? It's dependent on an array of booleans, 
		// which is limited by Integer.MAX_VALUE. It can output a large number of primes,
		// but is its largest big enough for this? Largest from Sieve.java: 2_147_265_203 =~ 2 billion
		// Clearly smaller. Okay, what is the largest left truncatable prime in Sieve's list?...
		
		// Read from file:
		final String fileName = "primes.txt";
		final File file = new File(fileName);
		if (file.exists()){
			System.out.println("File " + fileName + " found. Getting the contents...");
			Path path = Path.of(fileName);
			//This might have thrown an OutOfMemoryError (like it did in Sieve), but doesn't;
			// probably because of how efficiently readFile adds to the primes List
			
			// UPDATE: /interestingly, it does throw an OutOfMemoryError if using a Set / LinkedHashSet
			// instead of a List / ArrayList; thus the -Xmx parameter.
			/*
			Peculiar stack trace:
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.base/java.util.LinkedHashMap.newNode(LinkedHashMap.java:281)
	at java.base/java.util.HashMap.putVal(HashMap.java:638)
	at java.base/java.util.HashMap.put(HashMap.java:619)
	at java.base/java.util.HashSet.add(HashSet.java:230)
	at com.dstrube.LeftTruncatablePrimes.lambda$readFile$0(LeftTruncatablePrimes.java:116)
	
			Note, this error came from when readFile returned a Set, not a boolean.
			*/
			
			if (!readFile(path)) {
				System.out.println("readFile returned false");
				return;
			}
		}else{
			//File not found. Tell the user to run Sieve to create the file
			System.out.println("File " + fileName + " not found...");
			System.out.println("Run Sieve.java to create the file.");
			return;
		}
		
		if (primes == null || primes.size() == 0){
			System.out.println("Primes list not populated. Maybe try again or rethink this?");
			return;
		}else{
			System.out.println("Size of contents: " + primes.size()); // 105_087_370
		}
		
		// This takes a few minutes
		// Result: isProbablePrime works for all these
		/*
		System.out.println("Verifying all items in the list pass a test by BigInteger's isProbablePrime...");
		for(int i : primes){
			BigInteger test = new BigInteger(""+i);
			if (!test.isProbablePrime(1)){
				System.out.println("BigInteger thinks this is not probably prime: " + i);
				return;
			}
		}
		*/
		
		List<Integer> primesList = new ArrayList<>(primes);
		int lastInList = primesList.get(primes.size() - 1);
		System.out.println("Last int in primes list: " + lastInList); // 2_147_265_203
		// TODO: Start looking for left truncatable primes in the primes list...
		
		//Why use any certainty other than 1?
		//https://stackoverflow.com/questions/27430092/what-is-a-possible-use-case-of-bigintegers-isprobableprime
		//https://en.wikipedia.org/wiki/Probable_prime
		//https://en.wikipedia.org/wiki/Pseudoprime
		//https://mathcrypto.wordpress.com/2014/11/23/large-examples-of-strong-pseudoprimes-to-several-bases/
		//https://stackoverflow.com/questions/21740745/clarification-of-the-certainty-factor-in-isprobableprime
		BigInteger pseudo1 = new BigInteger("1195068768795265792518361315725116351898245581");
		is = pseudo1.isProbablePrime(1);
		//System.out.println("Is " + pseudo1 + " probably prime? " + is); //false
		
		BigInteger pseudo2 = new BigInteger("8038374574536394912570796143419421081388376882875581458374889175222974273765333652186502336163960045457915042023603208766569966760987284043965408232928738791850869166857328267761771029389697739470167082304286871099974399765441448453411558724506334092790222752962294149842306881685404326457534018329786111298960644845216191652872597534901");
		is = pseudo2.isProbablePrime(1);
		//System.out.println("Is " + pseudo2 + " probably prime? " + is); //false
		
		//All these tests are giving what seem to be right answers. At what point does isProbablePrime(1) start to fail?
		//TODO: Would it incorrectly tell us there is a larger LeftTruncatablePrimes than 357686312646216567629137?
		
		//At 5:24, the above video says this is the largest right truncatable prime:
		BigInteger largestRight = new BigInteger("73939133");
		//TODO: Prove it with brute force
		
		//Maybe todo:
		BigInteger largestLeftRight = new BigInteger("739397");
		
		//Not todo: prove that any-deleteable primes are infinite :-p
		
		System.out.println("Done");
	}
	
	private static boolean readFile(Path path) {
		boolean success = false;
	    Stream<String> lines = null;
	    try{
		    lines = Files.lines(path);
		    lines.forEach(line -> primes.add(Integer.parseInt(line)));
	    }catch(IOException ioe){
	    	System.out.println("Caught IOException: " + ioe.getMessage());
	    }finally{
	    	if(lines != null){
	    		lines.close();
	    		success = true;
	    	}else{
		    	System.out.println("Stream<String> lines was unexpectedly null");
	    	}
	    }
	    return success;
	}
	
	private static boolean isLeftTruncatablePrime(int candidate){
		//TODO
		//1- if this is not in the set of primes, return false
		//1.5 if this is 1 digit and is in the set of primes, return true
		//2- turn to string; trim off first digit; 
		//2.5- while new first digit is 0, trim off first digit; 
		//turn string to int new number
		// return isLeftTruncatablePrime(new number) 
		return false;
	}
}