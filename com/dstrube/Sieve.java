package com.dstrube;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.ArrayList;

/*
From ~/java:

javac -d bin com/dstrube/Sieve.java
java -cp bin com.dstrube.Sieve

UPDATE:
-Xms	Sets the initial heap size.
-Xmx	Sets the maximum heap size.

java -Xms4G -Xmx10G -cp bin com.dstrube.Sieve

From:
https://www.algolist.net/Algorithms/Number_theoretic/Sieve_of_Eratosthenes

*/


public class Sieve {
	public static void main(String[] args) {
		// Exception in thread "main" java.lang.NegativeArraySizeException: -2147483648
		//final int upperBound = Integer.MAX_VALUE;
		
		// Exception in thread "main" java.lang.OutOfMemoryError: Requested array size exceeds VM limit
		//final int upperBound = Integer.MAX_VALUE - 1;
		
		// This  (half of MAX_VALUE) works (dot count: 3512), but can we do better?
		//final int upperBound = Integer.MAX_VALUE / 2;
		
		// 3 / 4 of MAX_VALUE - dot count: 4216
		//int upperBound = Integer.MAX_VALUE / 4;
		//upperBound *= 3;
		
		// 9 / 10 of MAX_VALUE - dot count: 4573
		//int upperBound = Integer.MAX_VALUE / 10;
		//upperBound *= 9;
		
		// 99 / 100 of MAX_VALUE - dot count: 4771
		//int upperBound = Integer.MAX_VALUE / 100;
		//upperBound *= 99;
		
		// 999 / 1,000 of MAX_VALUE - dot count: 4790
		//int upperBound = Integer.MAX_VALUE / 1_000;
		//upperBound *= 999;
		
		// 9,999 / 10,000 of MAX_VALUE - dot count: 4792
		int upperBound = Integer.MAX_VALUE / 10_000;
		upperBound *= 9_999; // 2,147,265,252
		
		// 99,999 / 100,000 of MAX_VALUE - dot count: 4792 - no change; above approach seems optimal
		//int upperBound = Integer.MAX_VALUE / 100_000;
		//upperBound *= 99_999;
		
		final List<Integer> primes = runEratosthenesSieve(upperBound);
		
		// look at list of primes
		System.out.println("Size of primes list: " + primes.size()); //105,087,370
		
		//TODO: if file "primes.txt" not exists, create and write to it
		
		final String fileName = "primes.txt";
		final File file = new File(fileName);
		if (file.exists()){
			System.out.println("File " + fileName + " found.");
		}else{
			//Create the file
			System.out.println("File " + fileName + " not found. Creating it...");
			writeFile(primes, fileName);
		}

		
		System.out.println("Done");
	}
	
	private static List<Integer> runEratosthenesSieve(final int upperBound) {
		final int lowerBound = 2;
		final int upperBoundSquareRoot = (int) Math.sqrt(upperBound);
		final boolean[] isComposite = new boolean[upperBound + 1];
		int dotCount = 0;
		int hashCount = 0;
		System.out.println("Finding primes between " + lowerBound + " and " + upperBound);
		for (int m = lowerBound; m <= upperBoundSquareRoot; m++) {
			if (!isComposite[m]) {
				//System.out.print(m + " ");
				dotCount++;
				if(dotCount % 100 == 0)
					System.out.print(". ");
				for (int k = m * m; k <= upperBound; k += m)
					isComposite[k] = true;
			}
		}
		//System.out.println("dot count: " + dotCount);

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
		System.out.println("Creating primes list...");
		final List<Integer> primes = new ArrayList<>();
		for (int m = lowerBound; m <= upperBound; m++) { // "<=" shouldn't throw error because "new boolean[upperBound + 1];"
			if (!isComposite[m]){
				primes.add(m);
			}
		}
		//System.out.println("hash count: " + hashCount);
		return primes;
	}
	
	private static void writeFile(final List<Integer> primes, final String fileName){
		//Files.write() requires an Iterable<? extends CharSequence> 
		final List<String> records = new ArrayList<>();
		for(int i : primes){
			//System.out.println("DEBUG: Adding " + i);
			//Hrm:
			//Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
			//Solution: increase heap size - see updated run command above
			records.add("" + i);
		}
		//TODO - see if this can be caught in a try/catch
		
		//Write data to file, assuming it doesn't exist already
		try{
			final File file = new File(fileName);
			final Path path = Paths.get(file.toURI());
			final OpenOption[] options = {StandardOpenOption.CREATE};
			final Charset charset = Charset.forName("UTF-8");
			
			Files.write(path, records, charset, options);
		}
		catch(IOException e){
			System.out.println("\nCaught exception: " + e);		
		}
	}
}