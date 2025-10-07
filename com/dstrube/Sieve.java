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
		int dotCount = 0;
		int hashCount = 0;
		for (int m = 2; m <= upperBoundSquareRoot; m++) {
			if (!isComposite[m]) {
				//System.out.print(m + " ");
				dotCount++;
				if(dotCount % 100 == 0)
					System.out.print(". ");
				for (int k = m * m; k <= upperBound; k += m)
					isComposite[k] = true;
			}
		}
		System.out.println("dot count: " + dotCount);

		//int debugPrintLimit = 0;

		for (int m = upperBoundSquareRoot; m <= upperBound; m++){
			/*if(debugPrintLimit < 100) {
				debugPrintLimit++;
				System.out.println("m = " + m + " ");
				System.out.println("upperBound = " + upperBound);
			}*/
			if (!isComposite[m]){
				//System.out.print(m + " ");
				hashCount++;
				if(hashCount % 100000 == 0){
					System.out.print("# ");
					
				}
			}
		}
		//System.out.println("hash count: " + hashCount);
	}
}