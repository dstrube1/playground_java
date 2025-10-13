package com.dstrube;

/*
commands to compile and run:
from ~/java
javac -d bin com/dstrube/LeftTruncatablePrimes.java
java -cp bin com.dstrube.LeftTruncatablePrimes

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


public class LeftTruncatablePrimes{
	
	public static void main(String[] args){
		//According to this:
		// https://www.youtube.com/watch?v=azL5ehbw_24
		//this is the largest left truncatable prime:
		BigInteger largestLeft = new BigInteger("357686312646216567629137");
		boolean is = largestLeft.isProbablePrime(1);
		System.out.println("Is " + largestLeft + " probably prime? " + is);
		
		//TODO: Prove it, not with isProbablePrime, but with the Sieve of Eratosthenes
		// On second thought, can Sieve of Eratosthenes work here? It's dependent on an array of booleans, 
		// which is limited by Integer.MAX_VALUE; it can output a large number of primes,
		// but is its largest big enough for this? TBD by Sieve.java...
		// In the mean time, prepare to read from file:
		final String fileName = "primes.txt";
		File file = new File(fileName);
		if (file.exists()){
			System.out.println("File " + fileName + " found. Getting the contents...");
			Path path = Path.of(fileName);
			try{
				List<Integer> primes = readFile(path);
				System.out.println("Size of contents: " + primes.size());
			} catch(IOException ioe){
				System.out.println("Caught: " + ioe.getMessage());
			}
		}else{
			//create the file?
			//or tell the user to run Sieve to create the file?
			System.out.println("File " + fileName + " not found...");
			//return;
		}
		
		//Why use any certainty other than 1?
		//https://stackoverflow.com/questions/27430092/what-is-a-possible-use-case-of-bigintegers-isprobableprime
		//https://en.wikipedia.org/wiki/Probable_prime
		//https://en.wikipedia.org/wiki/Pseudoprime
		//https://mathcrypto.wordpress.com/2014/11/23/large-examples-of-strong-pseudoprimes-to-several-bases/
		//https://stackoverflow.com/questions/21740745/clarification-of-the-certainty-factor-in-isprobableprime
		BigInteger pseudo1 = new BigInteger("1195068768795265792518361315725116351898245581");
		is = pseudo1.isProbablePrime(1);
		System.out.println("Is " + pseudo1 + " probably prime? " + is); //false
		
		BigInteger pseudo2 = new BigInteger("8038374574536394912570796143419421081388376882875581458374889175222974273765333652186502336163960045457915042023603208766569966760987284043965408232928738791850869166857328267761771029389697739470167082304286871099974399765441448453411558724506334092790222752962294149842306881685404326457534018329786111298960644845216191652872597534901");
		is = pseudo2.isProbablePrime(1);
		System.out.println("Is " + pseudo2 + " probably prime? " + is); //false
		
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
	
	private static List<Integer> readFile(Path path) throws IOException {
			//Path path = Path.of("");
			//String content = readFile(path);
			//System.out.println("Content: " + content);
		//StringBuilder data = new StringBuilder();
	    Stream<String> lines = Files.lines(path);
	    List<Integer> primes = new ArrayList<>();
	    lines.forEach(line -> primes.add(Integer.parseInt(line)));
	    lines.close();
	    
	    return primes;
	}
	
		
}