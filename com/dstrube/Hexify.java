/*
From ~/java:

javac -d bin com/dstrube/Hexify.java
java -cp bin com.dstrube.Hexify

http://stackoverflow.com/questions/2817752/java-code-to-convert-byte-to-hexadecimal
http://stackoverflow.com/questions/9655181/how-to-convert-a-byte-array-to-a-hex-string-in-java

*/

package com.dstrube;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.TreeMap;

public class Hexify {
	public static void main(String[] args) {
		//Simple:
		//final String input = "01";
		//Breaking:
		//final String input = "¡"; //2 bytes
		//final String input = "∞"; //3 bytes
		//final String input = "§"; // 2 bytes
		//Full good
		final String input = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*()_+-=[]{}|;:,.<>?\\/";
		//Put all characters and their corresponding hex codes into a map and sort it
		final Map <Byte, Character> map = new TreeMap<>();
		boolean error = false;
		
		System.out.println("Hexifying: \n" + input);
		System.out.println("First, turn it all into bytes:");
		
		try{
			for (int i=0; i< input.length(); i++){
				final char c = input.charAt(i);
				final byte b = hexify("" + c, Charset.defaultCharset().name());
				if (b == -1) {
					error = true;
					break;
				}
				map.put(b, c);
			}
			
			if (error) return;
			
			final StringBuilder sb = new StringBuilder();
			
			System.out.println("\nNext, just for fun, sorted by byte value: ");
			
			for (Map.Entry<Byte, Character> entry : map.entrySet()){
				System.out.print(entry.getValue());
				//While printing out the sorteds prepare the hexified value of each character
				sb.append(String.format("%02X ", entry.getKey()));
			}
	    	System.out.println();
	    	System.out.println("Hexified: ");
	    	System.out.println(sb.toString());
		}catch (Exception e){
			System.out.println("exception caught");
		}	
	}
	
	private static byte hexify(final String input, final String charsetName) throws UnsupportedEncodingException{
		if (input == null || input.length() == 0){
			throw new IllegalArgumentException();
		}
		
        if (!Charset.isSupported(charsetName)) throw new UnsupportedEncodingException();
        
        final Charset charset = Charset.forName(charsetName);
        
        final byte[] bytes = input.getBytes(charset);
        
        if (bytes.length != 1){
        	System.out.println("\nUnexpected length of bytes: " + bytes.length + " where input = " + input + "; bytes:  ");
	        for (byte b : bytes){
    	        System.out.print(b + " ");
    		}
    		System.out.println();
    		return -1;
    	}
    	
    	System.out.print(bytes[0] + " ");

    	return bytes[0];
    }
}