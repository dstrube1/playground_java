package com.dstrube;

/*
commands to compile and run:
from ~/java
javac -d bin com/dstrube/MatrixSpiralWriter.java
java -cp bin com.dstrube.MatrixSpiralWriter

Input:
1 2 3
4 5 6
7 8 9

Output:
1 2 3
8 9 4
7 6 5

see also:
http://rosettacode.org/wiki/Spiral_matrix#Java

*/
public class MatrixSpiralWriter{
	public static void main(String[] args){
		int[][] input ={{1,2,3}, 
						{4,5,6}, 
						{7,8,9}};
		//Transform n x n to 1 D array
		int[] newInput = transform2D_to_1D(input);
		print1D(newInput);
		
		int rows = input.length;
		int cols = input[0].length;

		int startRowIndex = 0;
		int endRowIndex = rows;
		int startColIndex = 0;
		int endColIndex = cols;
		
		int newInputIndex = 0;
		int [][]output = new int[rows][cols];
		int iterator = 0;
		
		while(startRowIndex < endRowIndex && startColIndex < endColIndex){
			//left to right
			for (iterator = startColIndex; iterator < endColIndex; iterator++ ){ 
				output[startRowIndex][iterator] = newInput[newInputIndex++];
			}
			startRowIndex++;
			
			//top to bottom
			for(iterator = startRowIndex; iterator < endRowIndex; iterator++){
				output[iterator][endColIndex - 1] = newInput[newInputIndex++];
			}
			endColIndex--;
			
			//right to left
			if(startRowIndex < endRowIndex){
				for(iterator = endColIndex - 1; iterator >= startColIndex; iterator--){
					output[endRowIndex - 1][iterator] = newInput[newInputIndex++];
				}
				endRowIndex--;
			}
			
			//bottom to top
			if(startColIndex < endColIndex){
				for(iterator = endRowIndex - 1; iterator >= startRowIndex; iterator--){
					output[iterator][startColIndex] = newInput[newInputIndex++];
				}
				startColIndex++;
			}
		}
		
		print2D(output);

	}

	public static int[] transform2D_to_1D(int[][] input){
		int[] output = new int[input[0].length * input.length];
		int i = 0;
		for(int j = 0; j < input.length; j++){
			for(int k = 0; k < input.length; k++){
				output[i] = input[j][k];
				i++;
			}
		}
		return output;
	}
	
	private static void print1D(int[] oneD){
		System.out.print("[");
		final StringBuilder sb = new StringBuilder();
		for(int i : oneD){
			//System.out.print(i + ",");
			sb.append(i + ",");
		}
		//Take off last comma
		sb.deleteCharAt(sb.length()-1);
		
		System.out.println(sb.toString() + "]\n");
	}
	
	private static void print2D(int[][] twoD){
		System.out.print("[");
		final StringBuilder sb = new StringBuilder();
		for(int[] row : twoD){
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
	
}