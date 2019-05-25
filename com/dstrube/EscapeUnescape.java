/* 
#Author: David Strube
#Date: 2019-05-24

From ~/java:

Compile:
javac -d bin com/dstrube/EscapeUnescape.java
Run:
java -cp bin com.dstrube.EscapeUnescape

For FullStory:
Write a function Escape with the signature:

  Escape(string) -> string

The function should map each character in the input string to the output using these rules:

  [a-zA-Z0-9] -> mapped as is
  '-'         -> mapped to '--'
  else        -> mapped to '-'<hex>'-', where <hex> is the hex encoding of the character (e.g., "." -> "-2E-")

Next, write a function that undoes the escaping, called Unescape
*/
 
package com.dstrube;

import java.util.List;
import java.util.ArrayList;

public class EscapeUnescape {
  public static void main(String[] args) {
    final List<String> testCases = new ArrayList<>();
    testCases.add("blah");
    testCases.add("blah-");
    testCases.add("blah-!@#$%^&*()");
    testCases.add("");
    testCases.add(null);
    
    String output = "";
    String unescaped = "";
    for (String test : testCases){
      
      try{
        output = Escape(test);
        unescaped = Unescape(output);
      }catch (Exception e){
      	System.out.println(e.toString());
      	output = "";
      	unescaped = "";
      }
      
      System.out.println("input: " + test + "; output: " + output + "; unescaped: " + unescaped);
      
    }
  }
  
  private static String Escape(String input)throws Exception{
    if (input == null){
        throw new Exception("null input");
    }
    if (input.length() == 0){
        return "";
    }
    final StringBuilder output = new StringBuilder();
    for (int i = 0; i< input.length(); i++){
      char c = input.charAt(i);
      if (Character.isDigit(c) || Character.isLetter(c)){
      output.append(c);
      } else if (c == '-'){
        output.append("--");
      }else{
          String hex = String.format("%02x", (int) c);
        output.append("-");
        output.append(hex);
        output.append("-");
      }
    }
    return output.toString();
  }
  
  private static String Unescape(String input)throws Exception{
    if (input == null){
        throw new Exception("null input");
    }
    if (input.length() == 0){
        return "";
    }
    final StringBuilder output = new StringBuilder();
    final boolean lastCharIsDash = input.charAt(input.length()-1) == '-';
    for (int i = 0; i< input.length(); i++){
      char c = input.charAt(i);
      final boolean notAtEnd = i < input.length()-1;
      final boolean farFromEnd = i < input.length()-2;
      final boolean nextCharIsLetter = farFromEnd && Character.isLetter(input.charAt(i+1));
      final boolean nextCharIsNumber = farFromEnd && Character.isDigit(input.charAt(i+1));
      final boolean nextChar2IsLetter= farFromEnd && Character.isLetter(input.charAt(i+2));
      final boolean nextChar2IsNumber = farFromEnd && Character.isDigit(input.charAt(i+2));
                                                                  
      if (Character.isDigit(c) || Character.isLetter(c)){
      output.append(c);
      } else if (c == '-'){
        if (notAtEnd){
          if (input.charAt(i+1) == '-'){
            output.append("-");
            i++;
          }else if (farFromEnd && (nextCharIsLetter || nextCharIsNumber) && (nextChar2IsLetter || nextChar2IsNumber) && lastCharIsDash){
            char c1 = input.charAt(i+1);
            char c2 = input.charAt(i+2);
            String hex = "" + c1 + c2;
            int hexToInt = Integer.parseInt(hex, 16);
            char intToChar = (char)hexToInt;
            output.append(intToChar);
            i += 3;
        }
      }
    }
    }
    return output.toString();
  }
}


