package com.dstrube;

/*
commands to compile and run:
from ~/java
javac -d bin com/dstrube/CycleSort.java 
java -cp bin com.dstrube.CycleSort

From here:
https://stackoverflow.com/questions/3623509/how-to-sort-an-array-using-minimum-number-of-writes/3852666#3852666

*/

import java.util.Random;
import java.util.Collections;
import java.util.Arrays;

public class CycleSort{

	public static final <T extends Comparable<T>> int cycleSort(final T[] array) {
		int writes = 0;

		// Loop through the array to find cycles to rotate.
		for (int cycleStart = 0; cycleStart < array.length - 1; cycleStart++) {
			T item = array[cycleStart];

			// Find where to put the item.
			int pos = cycleStart;
			for (int i = cycleStart + 1; i < array.length; i++)
				if (array[i].compareTo(item) < 0) pos++;

			// If the item is already there, this is not a cycle.
			if (pos == cycleStart) continue;

			// Otherwise, put the item there or right after any duplicates.
			while (item.equals(array[pos])) pos++;
			{
				final T temp = array[pos];
				array[pos] = item;
				item = temp;
			}
			writes++;

			// Rotate the rest of the cycle.
			while (pos != cycleStart) {
				// Find where to put the item.
				pos = cycleStart;
				for (int i = cycleStart + 1; i < array.length; i++)
					if (array[i].compareTo(item) < 0) pos++;

				// Put the item there or right after any duplicates.
				while (item.equals(array[pos])) pos++;
				{
					final T temp = array[pos];
					array[pos] = item;
					item = temp;
				}
				writes++;
			}
		} 
		return writes;
	}

	public static final void main(String[] args) {
		final Random rand = new Random();
		final int size = 80000; //8K comes back almost instantly; 80K takes a few seconds
		final Integer[] array = new Integer[size];
		for (int i = 0; i < array.length; i++) { array[i] = rand.nextInt(size); }

		for (int iteration = 0; iteration < 1; iteration++) {
			System.out.printf("array: %s ", Arrays.toString(array));
			final int writes = cycleSort(array);
			System.out.printf("sorted: %s writes: %d\n", Arrays.toString(array), writes);
			Collections.shuffle(Arrays.asList(array));
		}
	}
}