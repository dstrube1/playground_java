package com.dstrube;

/*
From ~/java:

without json:
javac -d bin com/dstrube/test.java
java -cp bin com.dstrube.test


with json:
javac -cp bin:bin/json-20210307.jar -d bin com/dstrube/test.java
java -cp bin:bin/json-20210307.jar com.dstrube.test

*/

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.UUID;
import java.util.Random;
import java.util.Stack;

//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

public class test{
	
	public static void main(String[] args){
		try{
			//String out = 
			//System.out.println(": '" + out + "'");
			/*for(int i=1; i<=4; i++){
				System.out.println("i: " + i);
			}*/
			A6Notes();
			System.out.println("getMethodName(): " + getMethodName());

		}catch(Exception exception){
			System.out.println("Caught exception: " + exception);
		}
		System.out.println("Done");
	}
	
	private static String getMethodName(){
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		return name;
	}
	
	private static void A6Notes(){
		try{
			System.out.print("Trying providedSnafuMethod(true,true): ");
			System.out.println(providedSnafuMethod(true,true)); //false
		}catch(Exception exception){
			System.out.println("Caught exception: " + exception);
		}
		try{
			System.out.print("Trying providedSnafuMethod(true,false): ");
			System.out.println(providedSnafuMethod(true,false)); // /0
		}catch(Exception exception){
			System.out.println("Caught exception: " + exception);
		}
		try{
			System.out.print("Trying providedSnafuMethod(false,true): ");
			System.out.println(providedSnafuMethod(false,true));// /0
		}catch(Exception exception){
			System.out.println("Caught exception: " + exception);
		}
		try{
			System.out.print("Trying providedSnafuMethod(false,false): ");
			System.out.println(providedSnafuMethod(false,false)); //true
		}catch(Exception exception){
			System.out.println("Caught exception: " + exception);
		}
			
			/*
			int k = 1/0;
			Date date = new Date();
			System.out.println("date: " + date); // Sun Oct 24 15:02:21 EDT 2021
			String sDate = "" + date;
			String sNum = sDate.substring(sDate.length()-1);
			System.out.println("sNum = " + sNum);
			System.out.println("date.getTime(): " + date.getTime());
			long getTime = date.getTime();
			long milliPerYear = 31557600000L; //not this? 365 * 24 * 60 * 60 * 1000;
			System.out.println("milliPerYear: " + milliPerYear);
			long diff = getTime - milliPerYear;
			System.out.println("diff: " + diff);
			Date lastYear = new Date(diff); //1603546361619
			System.out.println("lastYear: " + lastYear);
			long sum = getTime + milliPerYear;
			System.out.println("sum: " + sum);
			Date nextYear = new Date(sum); //1666662461855
			System.out.println("nextYear: " + nextYear);
			
			sum += milliPerYear;
			System.out.println("sum: " + sum);
			Date d2023 = new Date(sum); //
			System.out.println("d2023: " + d2023);
			
			sum += milliPerYear;
			System.out.println("sum: " + sum);
			Date d2024 = new Date(sum); //
			System.out.println("d2024: " + d2024);
			
			sum += milliPerYear;
			System.out.println("sum: " + sum);
			Date d2025 = new Date(sum); //
			System.out.println("d2025: " + d2025);
			
			sum += milliPerYear;
			System.out.println("sum: " + sum);
			Date d2026 = new Date(sum); //
			System.out.println("d2026: " + d2026);
			
			sum += milliPerYear;
			System.out.println("sum: " + sum);
			Date d2027 = new Date(sum); //
			System.out.println("d2027: " + d2027);
			
			sum += milliPerYear;
			System.out.println("sum: " + sum);
			Date d2028 = new Date(sum); //
			System.out.println("d2028: " + d2028);
			
			sum += milliPerYear;
			System.out.println("sum: " + sum);
			Date d2029 = new Date(sum); //
			System.out.println("d2029: " + d2029);
			
			int i = Integer.parseInt(sNum);
			int j = 1 / (i-1);
			System.out.println("j = " + j);*/
	}
	
	private static boolean providedSnafuMethod(boolean a, boolean b) {
		int x = 1;
		int y = 1;
		if(a)
			x -=1; 
		else 
			y +=1;
		if(b)
			x -=1;
		else 
			y +=1;
		return (y/x > 0);
	}

	private static void randomTest(){
		Random random = new Random();
			
		for (int i = 0; i < 50; i++){
			int r = random.nextInt(50);
			System.out.print(r+":");
			for(int j = 0; j < r; j++){
				System.out.print("#");
			}
			System.out.println();
		}
	}
	
	private static void arraySizeTest(final int size){
		//arraySizeTest(4);
		int[] arr = new int[size];
		for (int i = 0; i < size; i++){
			arr[i] = i;
			System.out.println("arr["+i+"] = " + arr[i]);
		}
	}
	
	private static void duplicateSpaceRemovalRegex(final int size){
		String s = "c 1  2   3";
		String t = s.trim().replaceAll("\\s+"," ");
		System.out.println("t: '" + t + "'");
	}
	
	private static void minMaxIntTest(){
		//minMaxIntTest();
		int min = Integer.MIN_VALUE;
		int minPlus1 = min + 1;
		System.out.println("min: " + min);
		System.out.println("minPlus1: " + minPlus1);
		System.out.println("Integer.MAX_VALUE: " + Integer.MAX_VALUE);
		int abs = Math.abs(min);
		int abs1 = Math.abs(minPlus1);
		System.out.println("abs: " + abs);
		System.out.println("abs1: " + abs1);
		int maxPlus1 = Integer.MAX_VALUE + 1;
		int minMinus1 = Integer.MIN_VALUE - 1;
		System.out.println("maxPlus1: " + maxPlus1);
		System.out.println("minMinus1: " + minMinus1);
		
		String largerThanMax = "4046246937";
		System.out.println("Larger than MAX_VALUE: " + largerThanMax);
		int test = 0;
		try {
			test = Integer.parseUnsignedInt(largerThanMax);
		}catch (NumberFormatException nfe){
			System.out.println("Does it throw an exception? If you see this at execution, yes; "
				+"but in reality, no.");
		}
		System.out.println("Larger than MAX_VALUE (bigInt): parsed as unsigned int: " + test);
		System.out.println("How to scan and flag it as invalid?...");
		BigInteger bigInt = new BigInteger(largerThanMax);
		BigInteger max = new BigInteger(""+Integer.MAX_VALUE);
		System.out.println("Digression: what happens if we increment max as a BigInteger?");
		max = max.add(BigInteger.ONE);
		System.out.println(max.toString());
		System.out.println("END Digression");
		if(max.compareTo(bigInt) < 0){
			System.out.println("Integer.MAX_VALUE is less than bigInt");
		}else if(max.compareTo(bigInt) == 0){
			System.out.println("Integer.MAX_VALUE is equal to bigInt");
		}else{//max.compareTo(bigInt) > 0
			System.out.println("Integer.MAX_VALUE is greater than bigInt");
		}
	}
	
	private static String isVinValid(String vin) {
			//String out = isVinValid("-1-");
			//System.out.println("isVinValid: '" + out + "'");
        int[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 0, 1, 2, 3, 4, 5, 0, 7, 0, 9,
                2, 3, 4, 5, 6, 7, 8, 9 };
        int[] weights = { 8, 7, 6, 5, 4, 3, 2, 10, 0, 9, 8, 7, 6, 5, 4, 3, 2 };
       

        String s = vin;
        s = s.replace("-", "");
        return s;
    }
	
	private static String getUrl(String host, String port) {
			//String out = getUrl("x", "y");
			//System.out.println("getUrl: '" + out + "'");
		String url = null;
		String prefix = "https://";

		if (host != null){
			if(port != null){
				//host != null && port != null
				url = prefix + host + ":" + port;
			}
			else{
				//host != null && port == null
				url = prefix + host;
			}
		}

        return url;
	}
	
	/*private static String replaceTextValues(String text, JSONObject entityJson) {
			//String out = "" + replaceTextValues("", new JSONObject());
			//System.out.println("replaceTextValues: '" + out + "'");
		if ( entityJson != null && text != null ) {
			Iterator<?> it = entityJson.keys();
			while( it.hasNext() ) {
				String key = (String)it.next();
				try {
					String value = (entityJson.get(key) != null ) ? entityJson.get(key).toString() : null;
					if ( value != null ) {
						text = text.replaceAll("#" + key + "#", value);					
					}										
				} catch ( JSONException exception) {
					System.out.println("Caught exception: " + exception);
				}
			}
		}
		
		return text;
	}*/
	
	private static boolean isDateBetweenTwoDates(ZonedDateTime startDate, ZonedDateTime endDate, ZonedDateTime checkDate) {
			//String out = "" + isDateBetweenTwoDates(ZonedDateTime.now(), ZonedDateTime.now(), ZonedDateTime.now());
			//System.out.println("isDateBetweenTwoDates: '" + out + "'");
		return checkDate.isAfter(startDate) && checkDate.isBefore(endDate);
	}
	
	private static Long calculateDuration(ZonedDateTime startDate, ZonedDateTime endDate) {
			//String out = "" + calculateDuration(ZonedDateTime.now(), ZonedDateTime.now());
			//System.out.println(": '" + out + "'");
		return Duration.between(startDate, endDate).getSeconds();
	}
	
	private static Integer daysToMinutes(Integer days) {
			//String out = "" + daysToMinutes(null);
			//System.out.println("daysToMinutes: '" + out + "'");
		return days * (24 * 60);
	}

	private static ZonedDateTime retrieveZonedDateTimeWithOffsetByDay(String offset, ZonedDateTime zdt) {
			//String out = ""+retrieveZonedDateTimeWithOffsetByDay("x", ZonedDateTime.now());
			//System.out.println("retrieveZonedDateTimeWithOffsetByDay: '" + out + "'");
		if(zdt == null){
			return null;
		}	
		return zdt.plusDays(Long.parseLong(offset));		
	}
	
	private static String convertDBDateToFormattedString(String dbDateWithTimeZone, 
		String defaultDateFormat, String existingDBFormatPattern) throws ParseException {
			//String out = convertDBDateToFormattedString("", null, "");
			//System.out.println("convertDBDateToFormattedString: '" + out + "'");
		SimpleDateFormat sdf =  new SimpleDateFormat(existingDBFormatPattern);
		Date date = sdf.parse(dbDateWithTimeZone);		
		return new SimpleDateFormat(defaultDateFormat).format(date);
	}
	
	/*private static String JsonTest(String in)
			throws JSONException {
			//String out = JsonTest(null);
			//System.out.println("JsonTest: '" + out +"'");
		JSONObject qual = new JSONObject();
		JSONArray newQual = new JSONArray();
		newQual.put(qual);
		newQual.put(in);
		
		return newQual.toString();
	}	

	private static String constructElasticsearchQualificationWithEntityId(final String entityId, String qual)
			throws JSONException {
			//String out = constructElasticsearchQualificationWithEntityId(null,"[2]");
			//System.out.println("constructElasticsearchQualificationWithEntityId: '" + out +"'");
		JSONObject jsonObj = new JSONObject(); 
		JSONArray jsonMustArray = new JSONArray(qual);
		
		if ( jsonMustArray != null && jsonMustArray.length() > 0 ) {
			JSONObject val = new JSONObject().put("x", new JSONObject().put("y", entityId));
			jsonMustArray.put(val);
		}
		
		jsonObj.put("true", new JSONObject().put("must", jsonMustArray));
		qual = jsonObj.toString();
		return qual;
	}*/
	
	private static String retrieveReadableNameFromEntity(String entityName) {
			//String out = retrieveReadableNameFromEntity("");
			//System.out.println("retrieveReadableNameFromEntity = '" + out + "'");
		String[] cc = entityName.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
		return String.join(" ", cc);
	}
	
	private static String identityHashCodeWithTimestamp(Object obj) {
			//String out = identityHashCodeWithTimestamp(new Object());
			//System.out.println("out: " + out);
		return LocalDateTime.now(ZoneOffset.UTC).atOffset(ZoneOffset.UTC).toInstant().toEpochMilli() 
			+ "-" + System.identityHashCode(obj);
	}
	
	private static UUID createRelatedKey(String entityId) {
			//UUID uuid = createRelatedKey("");
			//System.out.println("uuid: " + uuid);
		return UUID.nameUUIDFromBytes(entityId.getBytes());
	}
	
	private static String readFile(Path path) throws IOException {
			//Path path = Path.of("");
			//String content = readFile(path);
			//System.out.println("Content: " + content);
		StringBuilder data = new StringBuilder();
	    Stream<String> lines = Files.lines(path);
	    lines.forEach(line -> data.append(line).append("\n"));
	    lines.close();
	    
	    return data.toString();
	}
	
	private static String decrypt(String license){
	//		String license = "license";
	//		System.out.println("Decrypting license: " + decrypt(license));

		byte[] bytes = Base64.getDecoder().decode(license.trim().getBytes());			
		license = new String(bytes);
		
		return license;
	}
	
	private static void testLongOverflow(){
		long testL = Long.MAX_VALUE;
		System.out.println("testL before increment: " + testL);
		try{
			testL++;
			System.out.println("testL after increment: " + testL); //Long.MIN_VALUE
			System.out.println("testL reset...");
			testL = Long.MAX_VALUE;
			testL *= 1000;
			System.out.println("testL * 1000: " + testL); //-1000
		}catch(Exception exception){
			System.out.println("Caught exception: " + exception);
		}
	}
	
	private static boolean isNumeric(String str) {
		try {
	//	System.out.println("Is Integer.MIN_VALUE - 1 numeric?: " + isNumeric("-2147483649"));
	//	System.out.println("Is Integer.MAX_VALUE + 1 numeric?: " + isNumeric("2147483648"));
		//System.out.println("Is null numeric?: " + isNumeric(null));
			Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		
		return true;
	}
	
	private static void inputTest(){
		Scanner scanner = new Scanner(System.in);
		boolean good = false;
		System.out.println("Input an integer: ");
		while (!good){
			try{
				int i = scanner.nextInt();
				System.out.println("You entered: " + i);
				good = true;
			}catch(InputMismatchException inputMismatchException){
				System.out.println("Try again");
				//If we don't do this, then (with bad input) we end up in an infinite loop
				scanner.close();
				scanner = new Scanner(System.in);
				//scanner.reset();
				//^resets Delimiter, Locale, & Radix, not input
			}
		}
		scanner.close();
	}
	
	private static void exceptionTest() throws Exception{
		int i = 0;
		i++;
		if (i > 0) 
			throw new Exception("exceptionTest");
	}
	
	private static void minuteThread(){
		final Date begin = new Date();
		long lastMinuteCount = 0;
		while (true){
			final Date current = new Date();
			final long diff = current.getTime() - begin.getTime();
        	final long diffSeconds = TimeUnit.MILLISECONDS.toSeconds(diff) % 60;
        	final long diffMinutes = TimeUnit.MILLISECONDS.toMinutes(diff);
    	    final long diffHours = TimeUnit.MILLISECONDS.toHours(diff);
    	    
			if (diffMinutes == lastMinuteCount){
				System.out.println("Less than 1 minute (" + diffSeconds 
					+ " seconds) has passed; sleeping for a bit...");
			}else{
				System.out.println("1 minute has passed; total minutes: " + diffMinutes 
					+ " at " + current);
				lastMinuteCount = diffMinutes;
//				break;
			}
			try{
				Thread.sleep(10000);
			}catch(InterruptedException exception){
				System.out.println("Caught InterruptedException:" + exception);
			}
		}
	}
	
	private static void charCount(){
		String someString = "elephant";
		long count = someString.chars().filter(ch -> ch == 'e').count();
		long count2 = someString.codePoints().filter(ch -> ch == 'e').count();
		System.out.println("count = " + count + "; count2 = " + count2);
	}
	
	private static void printOdds(){
 	   for (int i=1; i<=99; i++){
    	    if (i % 2 == 1)
        	    System.out.println(i);
	    }
	}
	
	public static String replace(String str, char oldChr, char newChr) {
       if (str == null || str.length() == 0) return str;
       if (str.indexOf(oldChr) == -1) return str;
       for (int i =0; i< str.length(); i++){
           if (str.charAt(i) == oldChr){
			    String firstHalf = str.substring(0,i);
               String secondHalf = str.substring(i+1);
               str = firstHalf + newChr + secondHalf;
               }
       }
       return str;
	}
	
	private static int calculate(String s){
	// Write a function that takes in a string like “1+5-3” and produces the answer (3)
    if(s == null || s.length()==0){
        System.out.println("Null or empty string");
        return 0;
    }
    String valids = "1234567890+-";
    for(int i = 0; i < s.length(); i++){
        char c = s.charAt(i);
        if(!valids.contains(""+c)){
            System.out.println("Invalid character: " + c);
            return 0;
        }
    }
    
    Stack<Integer> stack = new Stack<>();
    int index = 0;
    int tally = 0;
    StringBuilder currentNumber = new StringBuilder();
    boolean nextOpAdd = false;
    Character op = null;
    
    while (index < s.length()){
        if(Character.isDigit(s.charAt(index))){ 
            //assume non negatives
            //push number onto stack
            currentNumber.append(""+s.charAt(index));
            index++;
        }else if(currentNumber.toString().length() > 0){
            //assume no NumberFormatException
            int number = Integer.parseInt(currentNumber.toString());
            if (op != null){
                tally = stack.pop();
                if(nextOpAdd){
                    tally += number;
                }else{
                    tally -= number;
                }
                stack.push(tally);
                op = null;
            }else{
                stack.push(number);
            }
            currentNumber = new StringBuilder();
            //assume no ++ or --
            op = s.charAt(index);
            if(op == '+'){
                nextOpAdd = true;
            }else {
                nextOpAdd = false;
            }
            index++;
        }
    }
    int lastNumber = Integer.parseInt(currentNumber.toString());
    if (op != null){
        tally = stack.pop();
        if(nextOpAdd){
            tally += lastNumber;
        }else{
            tally -= lastNumber;
        }
    }
    return tally;
}
}