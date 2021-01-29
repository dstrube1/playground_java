package com.dstrube;

/*
commands to compile and run:
from ~/java
javac -d bin com/dstrube/CheckWriter.java 
java -cp bin com.dstrube.CheckWriter

title: Check Writer
description: Convert numbers to words

*/

import java.util.HashMap;
import java.util.Map;

public class CheckWriter{
	
	public static void main(String[] args){
	/*
	Input: 07 Output: Seven dollars only

	Input: 14 Output: Fourteen dollars only

	Input: 59 Output: Fifty nine dollars only
	
	Input: 90 Output: Ninety dollars only
	* /
	
	//String input = "$734.56";
	//expected Output: Seven hundred thirty four dollars and 56/100
	System.out.println(writeCheck("07"));
	System.out.println(writeCheck("14"));
	System.out.println(writeCheck("59"));
	System.out.println(writeCheck("90"));
	*/
	
	System.out.println(writeCheck("120"));
	System.out.println(writeCheck("125"));
	//TODO: Handle this better:
	//System.out.println(writeCheck("200"));
	}/**/
	
	public static String writeCheck(String input){
		if(input == null){
			return "Invalid input: null";
		}
		if(input.length() == 0){
			return "Invalid input: blank";
		}
		String output = "";
		
		Map<Integer, String> numbers = new HashMap<>();
		numbers.put(0, "zero");
		numbers.put(1, "one");
		numbers.put(2, "two");
		numbers.put(3, "three");
		numbers.put(4, "four");
		numbers.put(5, "five");
		numbers.put(6, "six");
		numbers.put(7, "seven");
		numbers.put(8, "eight");
		numbers.put(9, "nine");
		numbers.put(10, "ten");
		numbers.put(11, "eleven");
		numbers.put(12, "twelve");
		numbers.put(13, "thirteen");
		numbers.put(14, "fourteen");
		numbers.put(15, "fifteen");
		numbers.put(20, "twenty");
		numbers.put(30, "thirty");
		numbers.put(40, "forty");
		numbers.put(50, "fifty");
		numbers.put(60, "sixty");
		numbers.put(70, "seventy");
		numbers.put(80, "eighty");
		numbers.put(90, "ninety");

		if(Character.isDigit(input.charAt(0))){
			String cents = "";
			if (input.contains(".")){
				String[] nums = input.split(".");
				input = nums[0];
				cents = nums[1];
			}
			for(int i = 1; i < input.length(); i++){
				if(!Character.isDigit(input.charAt(i))){
					return "Invalid input: expected number at this character: " + input.charAt(i);
				}
			}
			int num = 0;
			try{
				num = Integer.parseInt(input);
				
			}catch(NumberFormatException nfe){
				return "Caught NumberFormatException: " + input;
			}
			if (num <= 15){
				return capitalizeWord(numbers.get(num)) + " dollars only";
			}else if (num >= 16 && num < 20){
				//handle teens
				int diff = num - 10;
				return capitalizeWord(""+diff) +"teen dollars only";
			}else if (num < 100){
				//handle all other two digits
				if (num %10 == 0){
					return capitalizeWord(numbers.get(num)) + " dollars only";
				}
				int tens = num / 10;
				int firstHalf = tens * 10;
				int secondHalf = num % firstHalf;
				return capitalizeWord(numbers.get(firstHalf)) + " " + numbers.get(secondHalf) + " dollars only";
			}else{
				int hundreds = num / 100;
				//System.out.println("hundreds = " + hundreds);
				int firstThird = hundreds;// * 100;
				num = num % 100;
				int tens = num / 10;
				int secondThird = tens * 10;
				
				int thirdThird = 0;
				if (secondThird > 0){
					thirdThird = num % secondThird;
				}
				if (cents.length() == 0){
					if (thirdThird == 0){
						return capitalizeWord(""+numbers.get(firstThird)) + " hundred " 
							+ numbers.get(secondThird) + " dollars only";
					}
					else{
						return capitalizeWord(""+numbers.get(firstThird)) + " hundred " 
							+ numbers.get(secondThird) + " " + numbers.get(thirdThird) + " dollars only";
					}
				}
				else{
					//handle cents
					return capitalizeWord(""+numbers.get(firstThird)) + " hundred " 
						+ numbers.get(secondThird) + " " + numbers.get(thirdThird) + " dollars and "
						+ cents + "/100";
				}
			}
		}
		return "";
	}
	
	public static String capitalizeWord(String str){  
	    String words[]=str.split("\\s");  
    	String capitalizeWord="";  
	    for(String w:words){  
    	    String first=w.substring(0,1);  
        	String afterfirst=w.substring(1);  
	        capitalizeWord+=first.toUpperCase()+afterfirst+" ";  
	    }  
    	return capitalizeWord.trim();  
	}
}