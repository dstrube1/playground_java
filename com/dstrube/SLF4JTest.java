package com.dstrube;

/*
From ~/java:

Mac:
javac -cp bin:bin/slf4j-api-1.7.30.jar -d bin com/dstrube/SLF4JTest.java
java -cp bin:bin/slf4j-api-1.7.30.jar com.dstrube.SLF4JTest

Windows [unverified]:
javac -cp bin;bin\slf4j-api-1.7.30.jar -d bin com\dstrube\SLF4JTest.java
java -cp bin;bin\slf4j-api-1.7.30.jar com.dstrube.SLF4JTest

Testing the Simple Logging Facade for Java (SLF4J)
*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//'impl' does not exist:
//import org.slf4j.impl.StaticLoggerBinder;

public class SLF4JTest {
	private static final Logger log = LoggerFactory.getLogger(SLF4JTest.class);
	
	public static void main(String[] args) {
		log.debug("Debug log from main");
		log.info("Info log from main");
		log.warn("Warn log from main");
		log.trace("Trace log from main");
		try{
			int i = 1/0;
			System.out.println("1 / 0 = " + i);
			throw new Exception("No exception automatically thrown yet");
		}catch(Exception except){
			System.out.println("Logging error with just the exception...");
			log.error(except.toString());
			System.out.println("Logging error with the exception formatted...");
			log.error("{}",except);
			
		}
	}
	
}
/*
Result:
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
*/