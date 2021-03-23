package com.dstrube;

/*
From ~/java:

Mac:
javac -d bin com/dstrube/OptionalTest.java
java -cp bin com.dstrube.OptionalTest

Exploring Optionals from here
https://www.oracle.com/technical-resources/articles/java/java8-optional.html

and here
https://www.baeldung.com/java-optional
(especially section 7)

*/

import java.util.Optional;

public class OptionalTest {
	public static void main(String[] args) {
		//String def = getMyDefault();
		//System.out.println("default: " + def);
		
		String text = "Text present";

	    System.out.println("Using orElseGet:");
    	String result 
	      	= Optional.ofNullable(text).orElseGet(OptionalTest::getMyDefault);
    	System.out.println("result = " + result);

	    System.out.println("Using orElse:");
    	result = Optional.ofNullable(text).orElse(getMyDefault());
	    System.out.println("result = " + result);
	}
	
	public static String getMyDefault() {
 	   System.out.println("Getting Default Value");
    	return "Default Value";
	}
	
	/*private static class Computer {
		private Optional<Soundcard> soundcard;  
		public Optional<Soundcard> getSoundcard() {
			soundcard = Soundcard.getSoundCard();
			return soundcard;
		}
	}

	private static class Soundcard {
		private Optional<USB> usb;
		public Optional<USB> getUSB() {
			usb = USB.getUSB();
			return usb;
		}
		public static Optional<Soundcard> getSoundCard(){
			Soundcard soundcard = new Soundcard();
			Optional<Soundcard> sc = Optional.of(soundcard);
			return sc;
		}
		
	}

	private static class USB{
		public String getVersion(){
			return "7";
		}
		public static Optional<USB> getUSB(){
			USB usb = new USB();
			Optional<USB> us = Optional.of(usb);
			return us;
		}
	}*/
}