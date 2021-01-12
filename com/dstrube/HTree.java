package com.dstrube;

//H-Tree Construction
/*
An H-tree is a geometric shape that consists of a repeating pattern resembles the letter “H”.
https://en.wikipedia.org/wiki/H_tree

It can be constructed by starting with a line segment of arbitrary length, drawing two segments of the same length at right angles to the first through its endpoints, and continuing in the same vein, reducing (dividing) the length of the line segments drawn at each stage by √2.

Write a function drawHTree that constructs an H-tree, given its center (x and y coordinates), a starting length, and depth. Assume that the starting line is parallel to the X-axis.

Use the function drawLine provided to implement your algorithm. In a production code, a drawLine function would render a real line between two points. However, this is not a real production environment, so to make things easier, implement drawLine such that it simply prints its arguments (the print format is left to your discretion).

Analyze the time and space complexity of your algorithm. In your analysis, assume that drawLine's time and space complexities are constant, i.e. O(1).

Constraints:

[time limit] 5000ms

[input] double x
[input] double y
[input] double length
[input] double depth

0 ≤ depth ≤ 10
*/
public class PairsWithDifference{
	public static void main(String[] args){
	}
	public static void drawLine(double x1, double y1, double x2, double y2){
    	// draws line, assume implementation available
	}
    
  public static void drawHTree(double x, double y, double length, double depth){
  	if (depth == 0) return;
  	
  	double x1 = x - (length/2);
  	double x2 = x + (length/2);
  	double y1 = y - (length/2);
  	double y2 = y + (length/2);
    //left vertical:
    drawLine(x1, y1, x1, y2);
    //right vertical:
    drawLine(x2, y1, x2, y2);
    //connecting horizontal line    
    drawLine(x1, y, x2, y);
    
    //at each stage, the length of segments decreases by a factor of √2
    double newLength = length / Math.sqrt(2);
    
    //decrement depth by 1 and draw an H-tree
    //at each of the tips of the current ‘H’
    drawHTree(x1, y1, newLength, depth-1);     // lower left  H-tree
    drawHTree(x1, y2, newLength, depth-1);     // upper left  H-tree
    drawHTree(x2, y1, newLength, depth-1);     // lower right H-tree
    drawHTree(x2, y2, newLength, depth-1);     // upper right H-tree
    
    
  }
}
