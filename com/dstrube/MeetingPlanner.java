package com.dstrube;

/*
commands to compile and run:
from ~/java:

Mac:
javac -d bin com/dstrube/MeetingPlanner.java
java -cp bin com.dstrube.MeetingPlanner

Windows:
javac -d bin com\dstrube\MeetingPlanner.java
java -cp bin com.dstrube.MeetingPlanner

Given availability (slotsA & slots B) of two people and meeting duration (dur), return the earliest time slot that works for both people of duration dur. If there is no common time slot that satisfies the duration requirement, return empty array.
*/


public class MeetingPlanner{
	public static void main(String[] args){
		int[][]slotsA = {{10,50},{60,120}, {140,210}};
		int[][]slotsB = {{0,15},{60,70}};
		int dur = 8;
		int[] output = planner(slotsA, slotsB, dur);
		System.out.print("[");
		for(int i : output){
			System.out.print(i + " ");
		}
		System.out.println("]");
		
		//TODO: Why does this break?
		//slotsA = {{10,50},{60,120}, {140,210}};
		//slotsB = {{0,15},{60,70}};
		dur = 12;
		output = planner(slotsA, slotsB, dur);
		System.out.print("[");
		for(int i : output){
			System.out.print(i + " ");
		}
		System.out.println("]");
		
		
	}
	
	private static int[] planner(int[][]slotsA, int[][] slotsB, int dur){
		int i = 0;
		int j = 0;
		while (i < slotsA.length && j < slotsB.length){
			int left = max(slotsA[i][0], slotsB[j][0]);
			int right = min(slotsA[i][1], slotsB[j][1]);
			if (left < right){
				if(right - left >= dur){
					int[] output = {left, left + dur};
					return output;
				}
			}
			if(right == slotsA[i][1]) i++;
			if(right == slotsB[j][1]) j++;
		}
		int[] output = new int[0];
		return output;
	}
	private static int max(int a, int b){
		if(a > b) return a;
		return b;
	}
	private static int min(int a, int b){
		if (b > a) return a;
		return b;
	}
	
}