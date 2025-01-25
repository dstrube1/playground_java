package com.dstrube;

/*
commands to compile and run:
from ~/java
javac -d bin com/dstrube/MatrixTransform.java
java -cp bin com.dstrube.MatrixTransform

This algorithm transforms a 2D array from before to after:
s = 0;
for (int i = 0; i <= x; i++){
	for(int j = 0; j <= y; j++){
		s = s + before(i,j);
	}
}
after(x,y) = s;
		
1: Confirm; example: 
	before = [[2,3], [5,7]]
	after = [[2,5][7,17]]
			
	[DONE]
			
2: Write reverse function

	[TODO]

*/
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
//import java.util.stream.Collectors;

public class MatrixTransform{
	
	public static void main(String[] args){
		/*
		int[] arr = {0};
		System.out.println("arr[0] before test: " + arr[0]);
		test1(arr);
		System.out.println("arr[0] after test: " + arr[0]);
		
		List<Integer> list = new ArrayList<>();
		list.add(0);
		System.out.println("list[0] before test: " + list.get(0));
		test2(list);
		System.out.println("list[0] after test: " + list.get(0));
		*/
		//Cool, passed by reference = don't have to return modified collection
		//On to the show...
		
		//Create before
		List<List<Integer>> before = new ArrayList<>();
		//Set before
		List<Integer> row = new ArrayList<>();
		row.add(2);
		row.add(3);
		before.add(row);
		row = new ArrayList<>();
		row.add(5);
		row.add(7);
		before.add(row);
		
		//Print
		System.out.println("before:");
		printMatrix(before);
		
		//Get after
		List<List<Integer>> after = beforeToAfter(before);
		
		//Print
		System.out.println("after:");
		printMatrix(after);
		
		//Reset before - not necessary here
		//before = new ArrayList<>();
		
		//Get before
		before = afterToBefore(after);
		
		//Print
		System.out.println("before (reversed from after):");
		printMatrix(before);
	}
	
	private static void test1(int[] arr){
		arr[0]++;
	}
	private static void test2(List<Integer> list){
		list.set(0, list.get(0)+1);
	}
	
	private static List<List<Integer>> beforeToAfter(List<List<Integer>> before){
		/*
		after[0][0] = before[0][0]
		after[0][1] = before[0][0] + before[0][1]
		after[1][0] = before[0][0] + before[1][0]
		after[1][1] = before[0][0] + before[0][1] + before[1][0] + before[1][1]
		
		*/
		List<List<Integer>> after = new ArrayList<>();
		//int[][] arr = new int[before.size()][before.get(0).size()];
		for(int x = 0; x < before.size(); x++){
			for(int y = 0; y < before.get(0).size(); y++){
				int sum = 0;
				for (int i = 0; i <= x; i++){
					for(int j = 0; j <= y; j++){
						List<Integer> inner = before.get(i);
						//System.out.println("sum = sum + inner.get(j) : ");
						//System.out.println("sum = " + sum + " + " + inner.get(j) + " = " + (sum + inner.get(j)));
						sum = sum + inner.get(j);
					}
				}
				//arr[x][y] = sum;
				
				//System.out.println("x = " + x + "; y = " + y + "; s = " + s);
				if(y % 2 == 0){
					List<Integer> list = new ArrayList<>();
					list.add(sum);
					after.add(list);
				}else{
					List<Integer> list = after.get(x);
					list.add(sum);
					after.set(x, list);
				}/**/
			}
		}
		
		/*
		Doesn't work:
		for(int[] ints : arr){
			after.addAll(Arrays.asList(ints));
		}*/
		
		/*
		Doesn't work:
		after = Arrays.stream(arr).flatMap(Arrays::stream).collect(Collectors.toList());
		*/
		
		/*
		Turns 2D to 1D:
		for(int i=0;i<before.size();i++)
		    for(int j=0;j<before.get(0).size();j++)
        		after.add(arr[i][j]);
		*/
		return after;
	}
	
	//WIP
	private static List<List<Integer>> afterToBefore(List<List<Integer>> after){
		/*
		before'[0][0] = after[0][0]
		before'[0][1] = after[0][1] - after[0][0]
		before'[1][0] = after[1][0] - after[0][0]
		before'[1][1] = after[1][1] - after[1][0] - (after[0][1] - after[0][0], or before'[0][1] <= order matters here)		
		46 years, 7 months = october
		*/
		List<List<Integer>> before = new ArrayList<>();
		
		//System.out.println("after.size() = " + after.size());
		//System.out.println("after.get(0).size() = "+after.get(0).size());
		
		for(int x = 0; x < after.size(); x++){
			for(int y = 0; y < after.get(0).size(); y++){
				int s = 0;
				for (int i = 0; i <= x; i++){
					for(int j = 0; j <= y; j++){
						List<Integer> inner = after.get(i);
						s = s - inner.get(j);
					}
				}
				System.out.println("x = " + x + "; y = " + y + "; s = " + s);
				if(y % 2 == 0){
					List<Integer> list = new ArrayList<>();
					list.add(s);
					before.add(list);
				}else{
					List<Integer> list = after.get(x);
					list.add(s);
					before.set(x, list);
				}
			}
		}
		return before;
	}
	
	private static void printMatrix(List<List<Integer>> matrix){
		System.out.print("[");
		final StringBuilder sb = new StringBuilder();
		
		for(List<Integer> list : matrix){
			sb.append("[ ");
			for(int i : list){
				sb.append(i + ", ");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.deleteCharAt(sb.length()-1);
			sb.append(" ],\n ");
		}
		try{
			//Take off last newline and space
			sb.deleteCharAt(sb.length()-1);
			sb.deleteCharAt(sb.length()-1);
			//Take off last comma
			sb.deleteCharAt(sb.length()-1);
		}catch(StringIndexOutOfBoundsException sioobe){
			//ignore
		}
		System.out.println(sb.toString() + " ]\n");
	}
}