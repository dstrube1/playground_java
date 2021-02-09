package com.dstrube;

/*
commands to compile and run:
from ~/java
javac -d bin com/dstrube/MatrixSpiralCopy.java 
java -cp bin com.dstrube.MatrixSpiralCopy

Given a 2D array (matrix) input of integers, create a function that copies input's values into a 1D array in a spiral order, clockwise, then return that array.

For example:
input = [[1,2, 3, 4, 5],
		[6, 7, 8, 9, 10],
		[11,12,13,14,15],
		[16,17,18,19,20]]
output = [1,2,3,4,5,10,15,20,19,18,17,16,11,6,7,8,9,14,13,12]

https://www.geeksforgeeks.org/print-a-given-matrix-in-spiral-form/
Below is the implementation of method 1.

Other methods to consider: 2: recursive where params are startRowIndex, startColIndex, endRowIndex, and endColIndex, and each time spiralCopy, increase starts and decrease ends

also method 3: DFS 
TODO: is that^ doable in Java? Should be...

also cool:
https://teams.exercism.io/tracks/java/exercises/spiral-matrix/solutions/8008d2e15dc14c12b87dc68d671b4c7a
*/

import java.util.List;
import java.util.ArrayList;

public class MatrixSpiralCopy{
	
	public static void main(String[] args){
		int[][] input ={{1, 2, 3, 4, 5},
						{6, 7, 8, 9, 10},
						{11,12,13,14,15},
						{16,17,18,19,20}};
		int[]output = spiralCopy(input);
		System.out.println("Input: ");
		printInput(input);
		System.out.println("Output: ");
		printOutput(output);
	}
	
	private static int[] spiralCopy(int[][]input){
		final List<Integer> list = new ArrayList<>();
		int startRowIndex = 0;
		int endRowIndex = input.length;
		int startColIndex = 0;
		int endColIndex = input[0].length;
		
		int iterator = 0;
		
		while(startRowIndex < endRowIndex && startColIndex < endColIndex){
			//left to right
			for (iterator = startColIndex; iterator < endColIndex; iterator++ ){ 
				list.add(input[startRowIndex][iterator]);
			}
			startRowIndex++;
			
			//top to bottom
			for(iterator = startRowIndex; iterator < endRowIndex; iterator++){
				list.add(input[iterator][endColIndex - 1]);
			}
			endColIndex--;
			
			//right to left
			if(startRowIndex < endRowIndex){
				for(iterator = endColIndex - 1; iterator >= startColIndex; iterator--){
					list.add(input[endRowIndex - 1][iterator]);
				}
				endRowIndex--;
			}
			
			//bottom to top
			if(startColIndex < endColIndex){
				for(iterator = endRowIndex - 1; iterator >= startRowIndex; iterator--){
					list.add(input[iterator][startColIndex]);
				}
				startColIndex++;
			}
		}
		
		int[] out = new int[list.size()];
		for(int i = 0; i < list.size(); i++)
			out[i] = list.get(i);
		
		return out;
	}
	
	private static void printInput(int[][] input){
		System.out.print("[");
		final StringBuilder sb = new StringBuilder();
		for(int[] row : input){
			sb.append("[");
			//System.out.print("[");
			for(int col : row){
				sb.append(col + ",");
				//System.out.print(col + ",");
			}
			sb.deleteCharAt(sb.length()-1);
			//System.out.println("],");
			sb.append("],\n");
		}
		//Take off last newline
		sb.deleteCharAt(sb.length()-1);
		//Take off last comma
		sb.deleteCharAt(sb.length()-1);
		
		System.out.println(sb.toString() + "]\n");
	}

	private static void printOutput(int[] output){
		System.out.print("[");
		final StringBuilder sb = new StringBuilder();
		for(int i : output){
			//System.out.print(i + ",");
			sb.append(i + ",");
		}
		sb.deleteCharAt(sb.length()-1);
		
		System.out.println(sb.toString() + "]\n");
	}
}