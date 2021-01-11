package com.dstrube;

/*
commands to compile and run:
from ~/java
javac -d bin com/dstrube/IPValidator.java 
java -cp bin com.dstrube.IPValidator

IP address validator
*/

import java.util.ArrayList;
import java.util.List;

public class IPValidator{
	
	public static void main(String[] args){
	//
	}
	
	private static boolean validateIP(final String ip) {
		// 23.4.019.3.  split("\\.")
	    if (ip == null) return false;
	    if (ip.length() < 7) return false;
	    if (ip.length() > 15) return false;
	    String[] nums = ip.split("\\.");
	    if (nums.length > 4) return false;
    
	    for(String num : nums){
	      if(num.length() == 0) return false;
	      if(num.charAt(0) == '0' && num.length() > 1) return false;
	      
	      try{
	        int i = Integer.parseUnsignedInt(num);
	        //TODO: is this a valid IP?: 1.1.1.+1
    		//if (i < 0) return false;
        	if (i > 255) return false;
        
	      }catch(NumberFormatException n){
    		return false;
	      }
      
    	}
	  	return true;
	}
	
}