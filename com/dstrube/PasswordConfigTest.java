package com.dstrube;

/*
From ~/java:

javac -d bin com/dstrube/PasswordConfigTest.java
java -cp bin com.dstrube.PasswordConfigTest

Just testing parseUnsignedInt
*/

public class PasswordConfigTest {
	public static void main(String[] args) {
		setExpiration(null);
		setExpiration("");
		setExpiration("-");
	}
	
	public static void setExpiration(String expiration) {
        try{
            int months = Integer.parseUnsignedInt(expiration);
            expiration = "" + months;
            System.out.println("expiration = " + expiration);
        }catch(NumberFormatException exception){
            expiration = expiration;
            System.out.println("Caught exception; expiration = " + expiration);
        }
    }
}