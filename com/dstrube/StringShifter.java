package com.dstrube;

/*
commands to compile and run:
from ~/java
javac -d bin com/dstrube/StringShifter.java
java -cp bin com.dstrube.StringShifter

Left shift: a singular circular rotation of the string in which the first character becomes the last character and all other characters are shifted one index to the left. For example, abcde becomes bcdea after one left shift and cdeab after two left shifts.

Right shift: a singular circular rotation of the string in which the last character becomes the first character and all other characters are shifted one index to the right. For example, abcde becomes eabcd after one right shift and deabc after two right shifts.

Example:
s="abcdefg"
left=2
right=4

Expected output: fgabcde
*/

public class StringShifter{
	
	public static void main(String[] args){
		String s = "abcdefg";
		int left = 2;
		int right = 4;
		System.out.println("Input '" + s + "' shifted: " + getShifted(s,left,right));
	}
	
	private static String getShifted(String s, int left, int right){
		if (s.length() == 0 || left == right) return s;
		if(left > 0 && right > 0){
			if(left > right){
				left = left - right;
				left = left % s.length(); //in case input left > s.length()
				right = 0;
				//System.out.println("Right is now 0; left is now " + right);
			}else{
				right = right - left;
				right = right % s.length(); //in case input right > s.length()
				left = 0;
				//System.out.println("Left is now 0; right is now " + right);
			}
		}
		if(left > 0){
			s = s.substring(left) + s.substring(0,left);
		}
		if(right > 0){
			s = s.substring(s.length() - right) + s.substring(0, s.length() - right);
		}
		
		return s;
	}

}