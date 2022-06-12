package com.dstrube;

/*
commands to compile and run:
from ~/java
javac -d bin com/dstrube/Jumble.java
java -cp bin com.dstrube.Jumble

*/

public class Jumble{
	private static final String alphabet_U = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String alphabet_L = "abcdefghijklmnopqrstuvwxyz";
	
	public static void main(String[] args){
		final String message = "";
		final StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < message.length(); i++){
			final char c = message.charAt(i);
		
			if (alphabet_U.contains("" + c)){
				sb.append(rot13(c, alphabet_U));
			}else if (alphabet_L.contains("" + c)){
				sb.append(rot13(c, alphabet_L));
			}else{
				sb.append(c);
			}
		}
		sb.reverse();
		System.out.println(sb.toString());
	}

	private static String rot13(char c, final String alphabet){
		final int index = alphabet.indexOf("" + c);
		
		//If called with a character outside the alphabet, this is being misused
		if(index == -1){
			System.out.println("Error: char not found: " + c);
		}
		
		//Similar to (but not identical to) the above check
		if(index > 25){
			System.out.println("Error: invalid index for: " + c + "; index: " + index);
		}
		
		//https://stackoverflow.com/questions/8981296/rot-13-function-in-java
		if ((c >= 'a' && c <= 'm') || (c >= 'A' && c <= 'M')) c += 13;
        else if  ((c >= 'n' && c <= 'z') || (c >= 'N' && c <= 'Z')) c -= 13;
        return "" + c;
	}
}
