package com.dstrube;

import java.util.Map;
import java.util.HashMap;

/*
From ~/java:

Mac:
javac -d bin com/dstrube/UnicodeTest.java
java -cp bin com.dstrube.UnicodeTest

Windows:
javac -d bin com\dstrube\UnicodeTest.java
java -cp bin com.dstrube.UnicodeTest

Replace special unicode letters with normal letters:

*/

public class UnicodeTest {
	public static void main(String[] args) {
		String input = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿ";
		final String specials = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿ";
		final Map<String,String> replacements = new HashMap<>();
		replacements.put("ÀÁÂÃÄÅÆ","A");
		replacements.put("Ç","C");
		replacements.put("ÈÉÊË","E");
		replacements.put("ÌÍÎÏ","I");
		replacements.put("Ð","D");
		replacements.put("Ñ","N");
		replacements.put("ÒÓÔÕÖ","O");
		replacements.put("×","x");
		replacements.put("Øø","0");
		replacements.put("ÙÚÛÜ","U");
		replacements.put("Ý","Y");
		replacements.put("Þ","Th");
		replacements.put("ß","B");
		replacements.put("àáâãäåæ","a");
		replacements.put("ç","c");
		replacements.put("èéêë","e");
		replacements.put("ìíîï","i");
		replacements.put("ðòóôõö","o");
		replacements.put("ñ","n");
		replacements.put("ùúûü","u");
		replacements.put("ýÿ","y");
		replacements.put("þ","th");
		replacements.put("÷","/");
		System.out.println("input before = " + input);
		for(char c : input.toCharArray()){
			for(String key : replacements.keySet())	{
				if(key.contains(""+c)){
					input = input.replace(""+c, replacements.get(key));
				}
			}
		}
		System.out.println("input after = " + input);
	}
	
}