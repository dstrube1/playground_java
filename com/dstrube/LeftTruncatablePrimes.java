package com.dstrube;

/*
commands to compile and run:
from ~/java
javac -d bin com/dstrube/LeftTruncatablePrimes.java
java -cp bin com.dstrube.LeftTruncatablePrimes

*/
import java.math.BigInteger;
//https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html

public class LeftTruncatablePrimes{
	
	public static void main(String[] args){
		//According to this:
		// https://www.youtube.com/watch?v=azL5ehbw_24
		//this is the largest left truncatable prime:
		BigInteger largestLeft = new BigInteger("357686312646216567629137");
		boolean is = largestLeft.isProbablePrime(1);
		System.out.println("Is " + largestLeft + " probably prime? " + is);
		
		//TODO: Prove it with brute force
		
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
	}
		
}