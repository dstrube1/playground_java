package com.dstrube;

/*
commands to compile and run:
from ~/java:

Mac:
javac -d bin com/dstrube/DuplicatesFinder.java
java -cp bin com.dstrube.DuplicatesFinder [path]

Finding duplicates, from good, to better, to more better, to a different kind of better
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class DuplicatesFinder{

	public static void main(String[] args){
		findDuplicates_4_util(args);
	}
	
	//Good - brute force: O(n^2)
	private static int[] findDuplicates_1(int[] arr1, int[] arr2) {
		final List<Integer> list = new ArrayList<>();
		for (int i1 : arr1){
			for(int i = 0; i < arr2.length; i++){
				int i2 = arr2[i];
				while(i2 < i1){
					i++;
					if(i < arr2.length)
						i2 = arr2[i];
					else
						break;
				}
				if (i2 > i1){
					break;
				}
				if(i2 == i1){
					list.add(i2);
				}
			}
		}
		final int[] output = new int[list.size()];
		for(int i = 0; i < list.size(); i++){
			output[i] = list.get(i);
		}
		return output;
	}
	
	//Better - single loop with parallel indices: O(n)
	private static int[] findDuplicates_2(int[] arr1, int[] arr2){
		final List<Integer> duplicates = new ArrayList<>();
		int i = 0;
		int j = 0;

		while (i < arr1.length && j < arr2.length){
			if (arr1[i] == arr2[j]){
				duplicates.add(arr1[i]);
				i = i + 1;
				j = j + 1;
			}
			else if (arr1[i] < arr2[j])
				i = i + 1;
			else
				j = j + 1;
		}
		
		//ugly java8 way:
		final int[] output = duplicates.stream().mapToInt(h -> h).toArray();
		return output;
	}
	
	//More better- use binary search on the bigger array: O(log n)
	private static int[] findDuplicates_3(int[] arr1, int[] arr2){
		final List<Integer> duplicates = new ArrayList<>();
		if(arr1.length > arr2.length){
			for(int i : arr2){
				if (binarySearch(arr1, i) != -1){
					duplicates.add(i);
				}
			}
		}else{
			for(int i : arr1){
				if (binarySearch(arr2, i) != -1){
					duplicates.add(i);
				}
			}
		}
		final int[] output = duplicates.stream().mapToInt(h -> h).toArray();
		return output;
	}
	
	private static int binarySearch(final int[] array, final int find){
		int left = 0;
		int right = array.length - 1;
		while (left <= right){
			int mid = (left + right) / 2;
			if (array[mid] < find){
				//System.out.println("Not at " + formatNum(mid) + "; moving right...");
				left = mid + 1;
			}
			else if (array[mid] > find){
				//System.out.println("Not at " + formatNum(mid) + "; moving left...");
				right = mid - 1;
			}
			else{
				return mid;
			}
		}
		return -1;
	}
	
	//a different kind of better - use HashSet
	private static int[] findDuplicates_4(int[] arr1, int[] arr2){
		Set<Integer> list1 = new HashSet<>();
		Set<Integer> list2 = new HashSet<>();
		for (int i : arr1){
			if (list1.contains(i)){
				System.out.println("Potential duplicate prevented from being added to list (set) 1: " + i);
			}else{
				list1.add(i);
			}
		}
		for (int i : arr2){
			if (list2.contains(i)){
				System.out.println("Potential duplicate prevented from being added to list (set) 2: " + i);
			}else{
				list2.add(i);
			}
		}
		final List<Integer> list = new ArrayList<>();
		for(int i : list1){
			if (!list2.contains(i)){
				list.add(i);
			}
			else{
				System.out.println("Skipping duplicate found in both list 1 & 2: " + i);
			}
		}
		final int[] output = new int[list.size()];
		for(int i = 0; i < list.size(); i++){
			output[i] = list.get(i);
		}
		return output;
	}
	
	private static void findDuplicates_4_util(String[] args){
		//This is a particular case where we have an input file with both lists.
		//First read in both lists, then call findDuplicates_4 with the lists (list1 & list2).
		//findDuplicates_4 should return anything in list1 that does not exist in list2
		String path;
		if (args == null || args.length == 0){
			path = "ListCompareInput.txt";
			System.out.println("Using default path: " + path);
		}else{
			path = args[0];
			System.out.println("Using inputted path: " + path);
		}
		final File file = new File(path);
		if (!file.exists()){
			System.out.println("File doesn't exist");
			return;
		}
		Scanner scanner1 = null;
		boolean list1Mode = false;
		boolean list2Mode = false;
		final List<Integer> list1 = new ArrayList<>();
		final List<Integer> list2 = new ArrayList<>();
		try{
			final FileInputStream fileInputStream = new FileInputStream(file);
			scanner1 = new Scanner(fileInputStream);
			while(scanner1.hasNextLine()){
				final String line = scanner1.nextLine();
				if (line.equals("list1:")){
					//System.out.println("list1");
					list1Mode = true;
					continue;
				}else if (line.equals("list2:")){
					//System.out.println("list2");
					list1Mode = false;
					list2Mode = true;
					continue;
				}
				if (!list1Mode && !list2Mode){
					System.out.println("Neither list1 nor list2???");
					return;
				}
				if (list1Mode){
					//System.out.println("adding to list1: " + line);
					list1.add(Integer.parseInt(line));
				}
				else{
					//System.out.println("adding to list2: " + line);
					list2.add(Integer.parseInt(line));
				}
			}
		} catch (FileNotFoundException fnfe){
	    	System.out.println("FileNotFoundException");
	    	return;
	    } catch (NumberFormatException nfe){
			System.out.println("NumberFormatException");
	    	return;
	    }
	     finally{
	    	if (scanner1 != null){
	    		scanner1.close();
	    	}
	    }
	    final int[] arr1 = list1.stream().mapToInt(h -> h).toArray();
	    final int[] arr2 = list2.stream().mapToInt(h -> h).toArray();
		final int[] originals = findDuplicates_4(arr1, arr2);
		System.out.println("List of originals from list 1: ");
		for(int i : originals){
			System.out.println(i);
		}
	}
	
}
//leetcode.com 1229
//sjhxbug1024@gmail.com

/*
*/