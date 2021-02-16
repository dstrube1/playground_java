package com.dstrube;

/*
commands to compile and run:
from ~/java:

javac -d bin com/dstrube/StringUtil.java
java -cp bin com.dstrube.StringUtil


*/

public class StringUtil {

	public String trim(String input) throws Exception{
		if(input == null){
			throw new Exception("Null input");
		}
		if(input.length() == 0){
			return input;
		}
		StringBuilder sb = new StringBuilder(input);
		if(sb.charAt(0) == ' '){
			while(sb.charAt(0) == ' '){
				sb.deleteCharAt(0);
			} 
		}
		sb = sb.reverse();
		if(sb.charAt(0) == ' '){
			while(sb.charAt(0) == ' ') {
				sb.deleteCharAt(0);
			}
		}
		sb = sb.reverse();
		return sb.toString();
	}



	public static void main(String[] args){
		StringUtil str = new StringUtil();
		String stringToTest = "    Hello David    ";
		try{
			System.out.println("*" + str.trim(stringToTest) + "*");
		}catch (Exception except){
			System.out.println("Caught exception: " + except);
		}
	}
}