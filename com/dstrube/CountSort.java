package com.dstrube;

/*
commands to compile and run:
from ~/java
javac -d bin com/dstrube/CountSort.java 
java -cp bin com.dstrube.CountSort

Sort numbers by their count in an array. Same count = show up in original order.

Example test:   [1, 3, 6, 4, 1, 2]
 output: [1, 1, 3, 6, 4, 2]

Example test:   [1, 2, 3]
 output: [1,2,3]

Example test:   [1, 3, 1, 5, 5, 2]
 output: [1,1,5,5,3,2]

Example test:   [1,2,2,3,3,3,4,5,5,6,6,6]
 output: [3,3,3,6,6,6,2,2,5,5,1,4]
*/

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Hashtable;
//https://docs.oracle.com/javase/8/docs/api/java/util/Hashtable.html
import java.util.Map.Entry;
import java.util.ArrayList;

public class CountSort{
	
	public static void main(String[] args){
		//
	}
	
	public List<Integer> frequenySortArray(int arr[], int size)
	{
		Map<Integer, Integer> map = new HashMap<>();
		int max = 0;
		for(int i = 0; i < size; i++){
			if(map.containsKey(arr[i])){
				int count = map.get(arr[i]) + 1;
				map.replace(arr[i], count);
				if(count > max){
					max = count;
				}
			}else{
				map.put(arr[i], 1);
			}
		}
		List<Integer> list = new ArrayList<>();
		for (int j = max; j > 0; j--){
			for(Map.Entry<Integer, Integer> entry : map.entrySet()){
				if(entry.getValue() == j){
					for(int k = 0; k < j; k++){
						list.add(entry.getKey());
					}
				}
			}
		}
		return list;
	}
/*
tmobile test id:
277460058572488

===
public class Solution
{
	//https://stackoverflow.com/questions/53110374/how-to-rotate-2-d-array-in-java
    //Function signature begins
   int[][] rotatePictureMethod(int[][] img, int rows, int columns,
                                       int flag)
    {
        //Write code here
        if (flag != 0 && flag != 1) return null;
        
        if (flag == 0){
            //System.out.println("Before rotate left: ");
            //printArr(img, rows, columns);
            img = rotateL(img, rows, columns);
        }
        else{
            //System.out.println("Before rotate right: ");
            //printArr(img, rows, columns);
            img = rotateR(img, rows, columns);
        }
        //System.out.println("After rotation: ");
        //printArr(img, rows, columns);
        
        return img;
    }
    //Function signature ends
    
    int[][] rotateL(int[][] img, int rows, int columns){
        int[][] ret = new int[columns][rows];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                //System.out.println("i = " + i + "; j = " + j);
                ret[i][j] = img[j][columns - i - 1];
            }
        }
        return ret;
    }
    int[][] rotateR(int[][] img, int rows, int columns){
        int[][] ret = new int[columns][rows];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                ret[i][j] = img[rows - j - 1][i];
            }
        }
        return ret;
    }
    
    void printArr(int[][] img, int rows, int columns){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                System.out.print(img[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }
}

*/
}