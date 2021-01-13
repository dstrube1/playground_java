import java.io.*;
import java.util.*;

class SpiralCopy {

  static int[] spiralCopy(int[][] inputMatrix) {
    String[] directions = {"LtR","TtB","RtL","BtT"};
    List<Integer> list = new ArrayList<>();
    int rowIndex = 0;
    int[] row = inputMatrix[0];
    for( ; rowIndex<inputMatrix[0].length; rowIndex++){
      list.add(row[rowIndex]);
      System.out.print(row[rowIndex] + " ");
    }
    
    for(int j = 1; j < inputMatrix.length; j++){
      int toAdd = inputMatrix[j][inputMatrix.length];
      System.out.println(toAdd + " ");
      list.add(toAdd);
    }
    return null;
  }

  public static void main(String[] args) {
    int[][] inputMatrix  = {{1,    2,   3,  4,    5},
                        {6,    7,   8,  9,   10},
                          {11,  12,  13,  14,  15},
                          {16,  17,  18,  19,  20} };
    spiralCopy(inputMatrix);
  }

}