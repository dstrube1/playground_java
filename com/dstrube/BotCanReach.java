package com.dstrube;

/*
commands to compile and run:
from ~/java
javac -d bin com/dstrube/BotCanReach.java
java -cp bin com.dstrube.BotCanReach

There is a bot located at a pair of integer coordinates (x,y). It must be moved to a location with another set of coordinates. Though the bot can move any number of times, it can only make the following two types of moves:

1- From location (x,y) to location (x + y, y)
2- From location (x,y) to location (x, x + y)

For example, if the bot starts at (1,1), it might make the following sequence of moves: 
(1,1) -> (1,2) -> (3,2) -> (5,2)

Note that movement will always be either up or to the right.

Given starting and target ending coordinates, determine whether the bot can reach the ending coordinates given the rules of movement.

1 <= x1,x2,y1,y2 <= 1000

Sample input:
1,4,5,9
output: true

explanation:
(1,4) -> (5,4) -> (5,9)

Sample input:
1,2,2,1
output: false
explanation:

*/

public class BotCanReach{
	
	public static void main(String[] args){
		int x1 = 1;
		int y1 = 4;
		int x2 = 5;
		int y2 = 9;
		System.out.println("canReach(" + x1 + "," + y1 + "," + x2 + "," + y2 + ") = " 
			+ canReach(x1, y1, x2, y2));

		x1 = 1;
		y1 = 2;
		x2 = 2;
		y2 = 1;
		System.out.println("canReach(" + x1 + "," + y1 + "," + x2 + "," + y2 + ") = " 
			+ canReach(x1, y1, x2, y2));
	}
	
	public static boolean canReach(int x1, int y1, int x2, int y2){
		if(x1 > x2 || y1 > y2) return false;
		if(x1 == x2 && y1 == y2) return true;
		if(canReach(x1 + y1, y1, x2, y2) || canReach(x1, x1 + y1, x2, y2)) return true;
		return false;
	}
}