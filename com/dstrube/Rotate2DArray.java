package com.dstrube;

/*
commands to compile and run:
from ~/Projects/java
javac -d bin com/dstrube/Rotate2DArray.java
java -cp bin com.dstrube.Rotate2DArray


*/

public class Rotate2DArray{
	
	//https://stackoverflow.com/questions/53110374/how-to-rotate-2-d-array-in-java
	
	public static void main(String[] args) {
		int[][] in={{1,2,3},
					{4,5,6},
					{7,8,9}};

    	System.out.println("Original array: ");
    	printArr(in,3,3);
		
		int[][]out1 = rotatePictureMethod(in, 3,3,0);
    	System.out.println("Rotated left: ");
		printArr(out1,3,3);
		
		int[][]out2 = rotatePictureMethod(in, 3,3,1);
    	System.out.println("Rotated right: ");
		printArr(out2,3,3);
	}

   	private static int[][] rotatePictureMethod(int[][] img, int rows, int columns,
                                       int flag)
    {
        if (flag != 0 && flag != 1) return null;
        
        if (flag == 0){
            img = rotateL(img, rows, columns);
        }
        else{
            img = rotateR(img, rows, columns);
        }
        return img;
    }
    
    private static int[][] rotateL(int[][] img, int rows, int columns){
        int[][] ret = new int[columns][rows];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                ret[i][j] = img[j][columns - i - 1];
            }
        }
        return ret;
    }
    private static int[][] rotateR(int[][] img, int rows, int columns){
        int[][] ret = new int[columns][rows];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                ret[i][j] = img[rows - j - 1][i];
            }
        }
        return ret;
    }
    
    private static void printArr(int[][] img, int rows, int columns){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                System.out.print(img[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }
}