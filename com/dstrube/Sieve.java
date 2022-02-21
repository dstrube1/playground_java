package com.dstrube;

/*
From ~/java:

javac -d bin com/dstrube/Sieve.java
java -cp bin com.dstrube.Sieve

From:
https://www.algolist.net/Algorithms/Number_theoretic/Sieve_of_Eratosthenes

*/


public class Sieve {
	public static void main(String[] args) {
		runEratosthenesSieve(Integer.MAX_VALUE / 2);
	}
	
	public static void runEratosthenesSieve(int upperBound) {
      int upperBoundSquareRoot = (int) Math.sqrt(upperBound);
      boolean[] isComposite = new boolean[upperBound + 1];
      for (int m = 2; m <= upperBoundSquareRoot; m++) {
            if (!isComposite[m]) {
                  System.out.print(m + " ");
                  for (int k = m * m; k <= upperBound; k += m)
                        isComposite[k] = true;
            }
      }

      for (int m = upperBoundSquareRoot; m <= upperBound; m++)
            if (!isComposite[m])
                  System.out.print(m + " ");
	}
}