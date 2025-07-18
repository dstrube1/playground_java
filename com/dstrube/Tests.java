package com.dstrube;

/*
From ~/java:

without json:
javac -d bin com/dstrube/Tests.java
java -cp bin com.dstrube.Tests

with json:
javac -cp bin:bin/json-20210307.jar -d bin com/dstrube/Tests.java
java -cp bin:bin/json-20210307.jar com.dstrube.Tests

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;
import java.util.Random;
import java.util.Stack;
import java.util.Queue;
import java.util.TreeMap;
import java.util.UUID;

/*import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;*/

public class Tests{
	
	public static void main(String[] args){
		
		/*try{
			//String out = 
			//System.out.println(": '" + out + "'");
			/*for(int i=1; i<=4; i++){
				System.out.println("i: " + i);
			}* /

		}catch(Exception exception){
			System.out.println("Caught exception: " + exception);
		}*/
		
		matrixMultiplicationStuff();
		
		System.out.println("Done");
	}
	
	private static void xTest(){
		//
	}
	
	private static String getMethodName(){
		//System.out.println("getMethodName(): " + getMethodName());
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		return name;
	}
	
	private static void A6Notes(){
		//A6Notes();
		try{
			System.out.print("Trying providedSnafuMethod(true,true): ");
			System.out.println(providedSnafuMethod(true,true)); //false
		}catch(Exception exception){
			System.out.println("Caught exception: " + exception);
		}
		try{
			System.out.print("Trying providedSnafuMethod(true,false): ");
			System.out.println(providedSnafuMethod(true,false)); // java.lang.ArithmeticException: /0
		}catch(Exception exception){
			System.out.println("Caught exception: " + exception);
		}
		try{
			System.out.print("Trying providedSnafuMethod(false,true): ");
			System.out.println(providedSnafuMethod(false,true)); // java.lang.ArithmeticException: /0
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
		System.out.println("How to scan and flag it as invalid?");
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
		
		int i = 3;
		System.out.println("3 / 2 = " + (i/2));

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
	
	/*JSON stuff* /
	private static String JsonTest(String in)
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
	}
	
	private static String replaceTextValues(String text, JSONObject entityJson) {
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
	}
	
	/ *END JSON stuff*/
	
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
			System.out.println("testL reset.");
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
	
	private static void ioTest(){		
		//ioTest();
		
		System.out.println("Using BufferedReader, enter a number > 0 and <= 100:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{
			String input = br.readLine();
		    int num = Integer.parseInt(input);
	    	while (num < 1 || num > 100){
	    		System.out.println("Invalid. Try again: ");
		    	input = br.readLine();
		    	num = Integer.parseInt(input);
	    	}
		    System.out.println("Valid!: " + num);
	    } catch (IOException e0){
	    	System.out.println("Caught exception: " + e0);
	    } catch (NumberFormatException e1){
   	    	System.out.println("Caught exception: " + e1);
	    }
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
					+ " seconds) has passed; sleeping for a bit.");
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

	private static void dotTest(){
		//dotTest();
		
		System.out.println("threeDots(): "); 
		threeDots();
		System.out.println("threeDots(1): "); 
		threeDots(1);
		System.out.println("threeDots(0,1): "); 
		threeDots(0,1);
		//threeDots(null); //this throws a NPE
	}

	//private static void twoDots(int.. i){} invalid syntax
	
	private static void threeDots(int... i){
		for (int j = 0; j < i.length; j++){
			System.out.print(i[j] + " ");
		}
		System.out.println();
	}
	
    private static long gcd(long m, long n) {
	// return Greatest Common Denominator(|m|, |n|)

//		long m = 64;
//		long n = 2;
//		System.out.println("greatest common denominator of " + m + " and " + n + ": " + gcd(m,n));
//		System.out.println("least common multiple of "+m+" and "+n+": " + lcm(m,n));

        if (m < 0) m = -m;
        if (n < 0) n = -n;
        if (0 == n) {
        	//System.out.println("m = " + m + " and n = " + n + "...");
			return m;
        }
        else {
        	//System.out.println("gcd(n, m % n), where m = " + m + " and n = " + n + "...");
        	return gcd(n, m % n);
        }
    }
	
    private static long lcm(long m, long n) {
	// return Least Common Multiple(|m|, |n|)
        if (m < 0) m = -m;
        if (n < 0) n = -n;
        return m * (n / gcd(m, n));    // parentheses important to avoid overflow
    }
    
	private static void selfListAdd(){
		//selfListAdd();
		
		//while it is permissible for lists to contain themselves as elements, 
		//extreme caution is advised: 
		//the equals and hashCode methods are no longer well defined on a such a list
		List<Object> list1 = new ArrayList<>();
		List<Object> list2 = new ArrayList<>();
		list1.add(list2);
		list2.add(list1);

		System.out.println("After adding list1 to list2, and vice versa, attempting to check their equality...");
		try{
			System.out.println(list1.equals(list2));
		}catch (StackOverflowError e){
			System.out.println(e.getClass().getSimpleName());
		}
		
		List<Object> list = new ArrayList<>();
		list.add(list);

		System.out.println("After adding list to itself, attempting to print its hashcode...");
		try{
			System.out.println(list.hashCode());
		}catch (StackOverflowError e){
			System.out.println(e.getClass().getSimpleName());
		}
	}
	
	private static void queueTest(){
//		queueTest();

		Queue<Long> q = new ArrayDeque<>();
		q.add(0L);
		q.add(1L);
		for (int i = 0; i < 8181; i++) {
		    long a = q.remove();
		    long b = q.remove();
		    q.add(b);
		    q.add(a + b);
		    System.out.println(a);
		}
		
		Queue<BigInteger> q0 = new ArrayDeque<>();
		q0.add(BigInteger.ZERO);
		q0.add(BigInteger.ONE);
		for (int i = 0; i < 8181; i++) {
		    BigInteger a = q0.remove();
		    BigInteger b = q0.remove();
		    q0.add(b);
		    q0.add(a.add(b));
		    System.out.println(b+"\n");
		}
	}
	
	private static void ArrayTest(){
		//ArrayTest();

		/*
		int[] a = {1};
		int[] b = a;
		System.out.println("a[0] = " + a[0] + "; b[0] = " + b[0]);
		a[0] = 2;
		System.out.println("a[0] = " + a[0] + "; b[0] = " + b[0]);
		changeArray(a);
		System.out.println("a[0] = " + a[0] + "; b[0] = " + b[0]);
		*/
		List<Integer> list = new ArrayList<>();
		list.add(0);
		list.add(1);
		list.add(2);
		System.out.println("All in list: ");
		for(int i : list)
			System.out.print(i + " ");
		System.out.println();
		
		//ugly java8 way:
		int[] c = list.stream().mapToInt(i -> i).toArray();
		System.out.println("All in c via list.stream()...:");
		for(int i : c)
			System.out.print(i + " ");
		System.out.println();
		
		
		/*
		Nope, can't do the following :/
		Either the above ugly way or iterate thru the list
		
		c = getIntArray(list);
		System.out.println("All in c via getIntArray:");
		for(int i : c)
			System.out.print(i + " ");
		System.out.println();*/
	}
	
	private static int[] getIntArray(List<Integer> list){
		Integer[] ints = new Integer[list.size()];
		list.toArray(ints);
		
		return null;//ints;
	}
	
	private static void changeArray(int[] c){
		c[0] = 0;
	}
	
	private static boolean areTheyEqual(int[] array_a, int[] array_b) {
    if (array_a == null || array_b == null) {
    	if (array_a == null && array_b == null) return true;
    return false;
    }
    if (array_a.length != array_b.length) return false;
    Map<Integer,Integer> map_a = new HashMap<>();
    Map<Integer,Integer> map_b = new HashMap<>();
    for(int i : array_a){
      if (map_a.containsKey(i)) {
        int count = map_a.get(i);
        count++;
        map_a.replace(i, count);
      }else{
        map_a.put(i, 1);
      }
    }
    for(int i : array_b){
      if (map_b.containsKey(i)) {
        int count = map_b.get(i);
        count++;
        map_b.replace(i, count);
      }else{
        map_b.put(i, 1);
      }
    }
    for(int i : array_a){
      if (map_b.containsKey(i)){
        int count_b = map_b.get(i);
        int count_a = map_a.get(i);
        if (count_b != count_a){return false;}
      }else{
        return false;
      }
    }
    return true;
  }

	private static String rotationalCipher(String input, int rotationFactor) {
    	final String numbers = "0123456789";
    	final String letters = "abcdefghijklmnopqrstuvwxyz";
    	StringBuilder sb = new StringBuilder();
    	for (char c : input.toCharArray()){
    	  if (!Character.isDigit(c) && !Character.isAlphabetic(c)) {
        	sb.append(c);
	      }
    	  else if (Character.isDigit(c)){
        	int index = (numbers.indexOf(c) + rotationFactor) % numbers.length();
	        sb.append(numbers.charAt(index));
    	  }
	      else /*Character.isAlphabetic(c)*/{
    	    boolean upper = Character.isUpperCase(c);
        	if (upper) c = Character.toLowerCase(c);
	        int index = (letters.indexOf(c) + rotationFactor) % letters.length();
    	    char cNew = letters.charAt(index);
        	if (upper) cNew = Character.toUpperCase(cNew);
	        sb.append(cNew);
    	  }
	    }
		return sb.toString();
	}
	
	private static void isAlmostPalindromeTest(){
		boolean is;
		String[] strings = {
			//valids:
			"", "x", "ab", "abcba", "abccba", "abcbda", "adbcba", "abccbca", 
			//invalids:
			"123", null, "abccbdda", "accbcba"};
		for (String input : strings){
			is = isAlmostPalindrome(input);
			if (input != null && input.length() == 0){
				System.out.print("''");
			}else{
				System.out.print(input);
			}
			System.out.println(" is or almost is a palindrome: " + is);
		}	
	}
	
	private static boolean isAlmostPalindrome(String s){
		//Given a string s, determine if it can be a palindrome by dropping at most one char
		//valid examples: 
		// ""
		// x
		// ab
		// abcba
		// abccba
		// abcbda
		// adbcba
		// abccbda
		//invalid example:
		// abccbdda
		// 123
		if (s == null) return false;
		if (s.length() <= 2) return true;
    	int left = 0, right = s.length() - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // Check by skipping either left or right character
                return isAlmostPalindromeHelper(s, left + 1, right) 
                	|| isAlmostPalindromeHelper(s, left, right - 1);
            }
            left++;
            right--;
        }
        
        return true;
    }

	private static boolean isAlmostPalindromeHelper(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    private static void twoPointerStuff(){
    	//from https://newsletter.francofernando.com/p/mastering-the-two-pointers-technique
		final int size = 100_000_000;
		System.out.println("getRandomIntArray...");

		long start = 0;
		long end = 0;
		double timeout = 0;
		
		start = System.currentTimeMillis();
		int[] arr0 = getRandomIntArray(size);
		end = System.currentTimeMillis();
		
		timeout = (end - start) / 1000.0;
		System.out.println("Done in " + timeout + " second(s)");
		
		System.out.println("Sorting...");

		start = System.currentTimeMillis();
		Arrays.sort(arr0);
		end = System.currentTimeMillis();

		timeout = (end - start) / 1000.0;
		System.out.println("Done in " + timeout + " second(s)");
		
		Random random = new Random();
		int target = 100_000_000;//random.nextInt(size);
		int[] result  = new int[2];

		System.out.println("Searching with twoSum_bruteForce...");
		
		start = System.currentTimeMillis();
		result = twoSum_bruteForce(arr0, target);
		end = System.currentTimeMillis();

		timeout = (end - start) / 1000.0;
		System.out.println("Done in " + timeout + " second(s)");
		
		System.out.println("Result of twoSum_bruteForce: [" + result[0] + "," + result[1] + "]");
		if(result[0] != -1){
			System.out.println("arr0[result[0]], result[1]:  [" + arr0[result[0]] + "," + arr0[result[1]] + "]");
		}

		System.out.println("Searching with twoSum_twoPointers...");

		start = System.currentTimeMillis();
		result = twoSum_twoPointers(arr0, target);
		end = System.currentTimeMillis();
		
		timeout = (end - start) / 1000.0;
		System.out.println("Done in " + timeout + " second(s)");
		
		System.out.println("Result of twoSum_twoPointers: [" + result[0] + "," + result[1] + "]");
		if(result[0] != -1){
			System.out.println("arr0[result[0]], result[1]:  [" + arr0[result[0]] + "," + arr0[result[1]] + "]");
		}

		System.out.println("Searching with twoSum_Hash...");
		
		start = System.currentTimeMillis();
		result = twoSum_Hash(arr0, target);
		end = System.currentTimeMillis();

		timeout = (end - start) / 1000.0;
		System.out.println("Done in " + timeout + " second(s)");
		
		System.out.println("Result of twoSum_Hash: [" + result[0] + "," + result[1] + "]");
		if(result[0] != -1){
			System.out.println("arr0[result[0]], result[1]:  [" + arr0[result[0]] + "," + arr0[result[1]] + "]");
		}
    }
    
  	private static int[] getRandomIntArray(final int size){
		final Random random = new Random();
			
		final int[] arr = new int[size];
		for (int i = 0; i < size; i++){
			int randInt = random.nextInt(size);
			//the following is specifically for twoPointerStuff()
			if (i % 100_000 == 0)
				System.out.print('.');
			while (randInt <= 100){
				System.out.print('#');
				randInt = random.nextInt(size);
			}
			//END the following...
			arr[i] = randInt;
		}
		System.out.println();
		return arr;
	}

    private static int[] twoSum_bruteForce(int[] arr, int target){
    	//from https://newsletter.francofernando.com/p/mastering-the-two-pointers-technique
    	//Given a sorted array of size n and a target value, find two numbers that add up to the target
    	//Return the indices of the combination
    	//If the combination doesn't exist, return [-1, -1]
    	
    	//Brute force: O(n^2) :
    	for (int i = 0; i < arr.length; i++){
    		for (int j = 1; j < arr.length; j++){
	    		/*try{
	    			//Weird that brute force completes so quickly without this delay
	    			//If input isn't sorted, then no thread sleep is needed- it is very slow...
	    			//Oh, that's why sorted is so fast- because the target was set to a low 1000. 
	    			//If set to a bigger 1_000_000, then it's slower
    				Thread.sleep(1);
    			}
    			catch (InterruptedException ie){
    				System.out.println("Caught InterruptedException in " + getMethodName());
	    		}*/
    			if (i == j) {
    				//skip combinations where the indices are equal
    				continue;
    			}
    			if (arr[i] + arr[j] == target){
    				return new int[]{i,j};
    			}
    		}
    	}
    	return new int[]{-1,-1};
    }
    
    private static int[] twoSum_twoPointers(int[] arr, int target){
    	//Using two pointers: O(n) :
    	int left = 0; 
    	int right = arr.length-1;
    	while (left < right){
    		int sum = arr[left] + arr[right];
    		if (sum == target) return new int[]{left,right};
    		else if (sum < target) left++;
    		else right--;
    	}
    	return new int[]{-1,-1};
    }
    
    private static int[] twoSum_Hash(int[] arr, int target){
    	//Using HashMap
    	Map<Integer, Integer> map = new HashMap<>();
    	for (int i = 0; i < arr.length; i++){
    		int num = arr[i];
    		int complement = target - num;
    		if (map.containsKey(complement)){
    			return new int[]{i, map.get(complement)};
    		}
    		map.put(complement, i);
    		try{
    			Thread.sleep(10);
    			//Without this, the timer says it completes in no time.
    		}
    		catch (InterruptedException ie){
    			System.out.println("Caught InterruptedException in " + getMethodName());
    		}
    	}
    	return new int[]{-1,-1};
    }
    
    private static void findLoops(){
    	//For each word w in a list, find a word x that starts with the second letter in w
    	//Repeat this until a loop is detected (e.g., alpha, lima, india, november, oscar, sierra, STOP)
    	//Then print out the loop and size of loop for each word
    	Map<String, String> secondChars = new HashMap<>();
    	//TreeMap is sorted by key
    	Map<String, Set<String>> loops = new TreeMap<>();
    	/*Note, if the above line doesn't have a type for the set, like this:
    Map<String, Set> loops = new TreeMap<>();
    	this compiling will give a warning like this:
	Note: com/dstrube/Tests.java uses unchecked or unsafe operations.
	Note: Recompile with -Xlint:unchecked for details.
		And recompiling like this will show the warning detail:
	javac -d bin -Xlint:unchecked com/dstrube/Tests.java
		=>
	com/dstrube/Tests.java:1115: warning: [unchecked] unchecked conversion
	    		Set<String> set = loops.get(secondCharsKey);
	    		                           ^
	required: Set<String>
	found:    Set
		This can be suppressed by putting this at the top of the method:
    @SuppressWarnings("unchecked")
    	But better to declare the map correctly.
    	*/
    	List<String> words = new ArrayList<>();
    	words.add("alpha");
    	words.add("bravo");
    	words.add("charlie");
    	words.add("delta");
    	words.add("echo");
    	words.add("foxtrot");
    	words.add("golf");
    	words.add("hotel");
    	words.add("india");
    	words.add("juliet");
    	words.add("kilo");
    	words.add("lima");
    	words.add("mike");
    	words.add("november");
    	words.add("oscar");
    	words.add("papa");
    	words.add("queen");
    	words.add("romeo");
    	words.add("sierra");
    	words.add("tango");
    	words.add("uniform");
    	words.add("victor");
    	words.add("whiskey");
    	words.add("xray");
    	words.add("yankee");
    	words.add("zulu");
    	
    	int count = 0;
    	
    	//Assign values to secondChars & loops
    	for (String word : words) {
    		String secondWord = null;
    		String startsWith = "" + word.charAt(1);
    		for (String w2 : words) {
    			if (w2.startsWith(startsWith)) {
    				secondWord = w2;
    				break;
    			}
    		}
    		if (secondWord == null) {
    			System.out.println("secondWord is null for " + word);
    			return;
    		} else if (count < 3) {
    			//System.out.println(word + " => " + secondWord);
    			count++;
    		}
    		secondChars.put(word, secondWord);
    		//Using LinkedHashSet because it maintains the insertion order
    		loops.put(word, new LinkedHashSet<String>());
    	}
    	
    	//Compute word sets for each word
    	for (String secondCharsKey : secondChars.keySet()) {
    		//eg secondCharsKey = alpha
    		String secondWord = secondChars.get(secondCharsKey);
    		//eg secondWord = lima
    		
    		//warning: [unchecked] unchecked conversion
    		Set<String> set = loops.get(secondCharsKey);
    		while (!secondWord.equals(secondCharsKey) && !set.contains(secondWord)){
    			set.add(secondWord);
    			secondWord = secondChars.get(secondWord);
    		}
    	}
    	
    	//Print it all out
    	String smallestSet = "";
    	String largestSet = "";
    	int smallestSetCount = 30;
    	int largestSetCount = 0;
    	for (String loopsKey : loops.keySet()) {
    		//warning: [unchecked] unchecked conversion
    		Set<String> set = loops.get(loopsKey);
    		if (set.size() < smallestSetCount){
    			smallestSetCount = set.size() + 1; //adding 1 to include the key
    			smallestSet = loopsKey;
    		}
    		if (set.size() > largestSetCount){
    			largestSetCount = set.size() + 1;
    			largestSet = loopsKey;
    		}
    		System.out.print(loopsKey);
    		for(String loopWord : set) { 
    			System.out.print(" => " + loopWord);
    		}
    		System.out.println();
    	}
    	System.out.println("smallestSet: " + smallestSet + "; count: " + smallestSetCount);
    	System.out.println("largestSet: " + largestSet + "; count: " + largestSetCount);
    }
    
    private static void matrixMultiplicationStuff(){
    	/*int[][] A0 = { {0, 0, 0, 0},
    					{0, 0, 0, 0},
	    				{0, 0, 0, 0},
    					{0, 0, 0, 0} };
    	int[][] B0 = { {0, 0, 0, 0}, 
    					{0, 0, 0, 0}, 
    					{0, 0, 0, 0}, 
    					{0, 0, 0, 0}};
    	int[][] C0 = matrixMultiplicationBruteForce(A0,B0);
    	System.out.println("Brute force matrix multiplication of 2 empty 4 x 4s:");
    	printMatrix(C0);
    	/*
[[0,0,0,0,],
[0,0,0,0,],
[0,0,0,0,],
[0,0,0,0,]]
    	* /

    	//Test compare against matrixMultiplicationDivConq:
    	int[] startA = {0,0};
    	int[] startB = {0,0};
    	int size = A0.length; //Assuming they're the same size and both size is a power of 2
    	int[][] C0a = matrixMultiplicationDivConq(A0,B0, startA, startB, size);
    	System.out.println("Testing against divide-and-conquer:");
    	printMatrix(C0a);
    	
    	int[][] C0b = matrixMultiplicationStrassen(A0, B0);//, startA, startB, size);
    	System.out.println("Testing against Strassen:");
    	printMatrix(C0b);

    	int[][] A1 = { {1, 1, 1, 1},
    					{1, 1, 1, 1},
	    				{1, 1, 1, 1},
    					{1, 1, 1, 1} };
    	int[][] B1 = { {1, 1, 1, 1}, 
    					{1, 1, 1, 1}, 
    					{1, 1, 1, 1}, 
    					{1, 1, 1, 1}};
    	int[][] C1 = matrixMultiplicationBruteForce(A1,B1);
    	System.out.println("Brute force matrix multiplication of two 4 x 4s of 1s:");
		printMatrix(C1);
		/*
[[4,4,4,4,],
[4,4,4,4,],
[4,4,4,4,],
[4,4,4,4,]]
		*/

		//Test brute force against 1 x 1 matrices:
    	/*
    	int[][] A1a = { {0} };
    	int[][] B1a = { {0} };
    	int[][] C1a = matrixMultiplicationBruteForce(A1a,B1a);
    	System.out.println("Testing brute force against 1 x 1 matrices:");
		printMatrix(C1a);
		*/
		
		/*
    	int[][] A1b = { {1} };
    	int[][] B1b = { {1} };
    	int[][] C1b = matrixMultiplicationBruteForce(A1b,B1b);
		printMatrix(C1b);
		* /
		
		int[][] C1c = matrixMultiplicationDivConq(A1,B1, startA, startB, size);
    	System.out.println("Testing against divide-and-conquer:");
    	printMatrix(C1c);

		int[][] C1d = matrixMultiplicationStrassen(A1, B1);//, startA, startB, size);
    	System.out.println("Testing against Strassen:");
    	printMatrix(C1d);

    	int[][] A2 = { {2, 3, 4, 5},
    					{3, 4, 5, 6},
	    				{4, 5, 6, 7},
    					{5, 6, 7, 8} };
    	int[][] B2 = { {5, 6, 7, 8}, 
    					{4, 5, 6, 7}, 
    					{3, 4, 5, 6}, 
    					{2, 3, 4, 5}};
    	int[][] C2 = matrixMultiplicationBruteForce(A2, B2);
    	System.out.println("Brute force matrix multiplication of two more complicated 4 x 4s:");
		printMatrix(C2);
		/*
[[44,58,72,86,],
[58,76,94,112,],
[72,94,116,138,],
[86,112,138,164,]]
		* /
		int[][] C2a = matrixMultiplicationDivConq(A2, B2, startA, startB, size);
    	System.out.println("Testing against divide-and-conquer:");
    	printMatrix(C2a);

		int[][] C2b = matrixMultiplicationStrassen(A2, B2);//, startA, startB, size);
    	System.out.println("Testing against Strassen:");
    	printMatrix(C2b);
    	/*
    	Not quite:
[[96,94,88,86,],
[122,120,114,112,],
[148,146,140,138,],
[174,172,166,164,]]    	

		First debug with a smaller matrix, like 1 x 1, then 2 x 2, of ns > 1
    	* /

    	int[][] A2c = { {2 } };
    	int[][] B2c = { {5 }};
    	int[][] C2c = matrixMultiplicationBruteForce(A2c, B2c);
    	System.out.println("Brute force matrix multiplication of two more complicated 1 x 1s:");
		printMatrix(C2c);
		
		int[][] C2d = matrixMultiplicationStrassen(A2c, B2c);//, startA, startB, size);
    	System.out.println("Testing against Strassen:");
    	printMatrix(C2d);
*/
	   	
	   	int[][] A2e = { {2, 3 },
    					{5, 6 } };
    	int[][] B2e = { {5, 6 }, 
    					{2, 3 }};
    	System.out.println("Brute force matrix multiplication of two more complicated 2 x 2s:");
    	System.out.println("Beginning matrix A:");
		printMatrix(A2e);
    	System.out.println("Beginning matrix B:");
		printMatrix(B2e);
    	int[][] C2e = matrixMultiplicationBruteForce(A2e, B2e);
    	System.out.println("End of brute force matrix multiplication: ");
		printMatrix(C2e);

    	System.out.println("Testing against Strassen:");
    	System.out.println("Beginning matrix A:");
		printMatrix(A2e);
    	System.out.println("Beginning matrix B:");
		printMatrix(B2e);
		int[][] C2f = matrixMultiplicationStrassen(A2e, B2e);//, startA, startB, size);
    	printMatrix(C2f);

    }
    
    private static void printMatrix(int[][] A){
    	if (A == null) {
    		System.out.println("Null matrix");
    		return;
    	}

    	System.out.print("[");
		for (int[] row : A) {
	    	System.out.print("[");
			for (int i : row) {
				System.out.print(i + ",");
			}
			System.out.println("],");
		}
		System.out.println("]");
    }
    
    private static void printMatrix0(int[][] A){
    	if (A == null) {
    		System.out.println("Null matrix");
    		return;
    	}

    	System.out.print("[");
		for (int i = 0; i < A.length; ++i) {
			System.out.print("[");
			for (int j = 0; j < A[i].length; ++j) {
				System.out.print(A[i][j] + ",");
			}
			System.out.println("],");
		}
		System.out.println("]");
    }
    
    private static int[][] matrixMultiplicationBruteForce(int[][] A, int[][] B){
    	int[][] C = new int[A.length][B.length];
    	for(int i=0; i < A.length; i++){
    		for(int j=0; j < B[0].length; j++){
    			for(int k=0; k < A[0].length; k++){
    				System.out.println("C["+i+"]["+j+"]("+C[i][j]+")+=A["+i+"]["+k+"]("+A[i][k]+")*B["+k+"]["+j+"]("+B[k][j]+")");
    				C[i][j] += A[i][k] * B[k][j];
    				System.out.println("Brute force progress: ");
    				printMatrix(C);
    			}
    		}
    	}
    	return C;
    }

    private static int[][] matrixMultiplicationDivConq(int[][] A, int[][] B, int[] startA, int[] startB, int size){
    	//Divide and conquer
    	//Starting from here:
    	//https://github.com/FrancoFernando/Data-Structures-and-Algorithms/blob/main/python/math/square_matrix_multiplication.py
    	
    	//Works if both A and B matrices have size n x n and n is a power of 2. However, if the above conditions are not satisfied, 
    	// it’s always possible to pad the matrices with 0 for this method to work
    	
    	int rowA = startA[0];
    	int colA = startA[1];
    	int rowB = startB[0];
    	int colB = startB[1];
    	
    	if (size == 1) {
    		int[][] result = { { (A[rowA][colA] * B[rowB][colB]) } };
    		return result;
    	}
    	
    	//Divide matrices into quadrants
    	int newSize = size / 2;
    	
    	//Calculate quadrants
    	int[][] A11_B11 = matrixMultiplicationDivConq(A, B, startA, startB, newSize);
    	int[][] A12_B21 = matrixMultiplicationDivConq(A, B, new int[] {rowA, colA + newSize}, new int[] {rowB + newSize, colB}, newSize);
    	int[][] A11_B12 = matrixMultiplicationDivConq(A, B, startA, new int[] {rowB, colB + newSize}, newSize);
    	int[][] A12_B22 = matrixMultiplicationDivConq(A, B, new int[] {rowA, colA + newSize}, new int[] {rowB + newSize, colB + newSize}, newSize);
    	int[][] A21_B11 = matrixMultiplicationDivConq(A, B, new int[] {rowA + newSize, colA}, startB, newSize);
    	int[][] A22_B21 = matrixMultiplicationDivConq(A, B, new int[] {rowA + newSize, colA + newSize}, new int[] {rowB + newSize, colB}, newSize);
    	int[][] A21_B12 = matrixMultiplicationDivConq(A, B, new int[] {rowA + newSize, colA}, new int[] {rowB, colB + newSize}, newSize);
    	int[][] A22_B22 = matrixMultiplicationDivConq(A, B, new int[] {rowA + newSize, colA + newSize}, new int[] {rowB + newSize, colB + newSize}, newSize);
    	
    	//Next, add matrices...
    	int[][] C11 = addMatrices(A11_B11, A12_B21, newSize);
    	int[][] C12 = addMatrices(A11_B12, A12_B22, newSize);
    	int[][] C21 = addMatrices(A21_B11, A22_B21, newSize);
    	int[][] C22 = addMatrices(A21_B12, A22_B22, newSize);

    	return matrixConqer(C11, C12, C21, C22, newSize);
    }
    
    private static int[][] addMatrices(int[][] A, int[][] B, int size){
		//Add two matrices element-wise.
		int[][] C = new int[size][size];
    	for(int row = 0; row < size; row++){
    		for(int col = 0; col < size; col++){
    			C[row][col] = A[row][col] + B[row][col];
    		}
    	}
    	return C;
    }
    
    private static int[][] subtractMatrices(int[][] A, int[][] B, int size){
		//Subtract two matrices element-wise.
		int[][] C = new int[size][size];
    	for(int row = 0; row < size; row++){
    		for(int col = 0; col < size; col++){
    			if(A[row][col] >= B[row][col]){
	    			C[row][col] = A[row][col] - B[row][col];
    			}else{
    				C[row][col] = B[row][col] - A[row][col]; //TODO: This might not be right or necessary
    			}
    		}
    	}
    	return C;
    }
    
    private static int[][] matrixConqer(int[][] C11, int[][] C12, int[][] C21, int[][] C22, int size){
    	//Combine the four quadrants into a single matrix.
    	int[][] C = new int[size * 2][size * 2];
    	for(int row = 0; row < size; row++){
    		for(int col = 0; col < size; col++){
    			C[row][col] = C11[row][col];
    			C[row][col + size] = C12[row][col];
    			C[row + size][col] = C21[row][col];
    			C[row + size][col + size] = C22[row][col];
    		}
    	}
    	return C;
    }

    private static int[][] matrixMultiplicationStrassen(int[][] A, int[][] B){
    	//https://newsletter.francofernando.com/p/multiplying-matrix
    	//https://en.wikipedia.org/wiki/Strassen_algorithm
    	//https://www.geeksforgeeks.org/dsa/strassens-matrix-multiplication/#
    	
    	/*
    	P1 = A11 * (B12 - B22)
    	P2 = (A11 + A12) * B22
    	P3 = (A21 + A22) * B11
    	P4 = A22 * (B21 - B11)
    	P5 = (A11 + A22) * (B11 - B22)
    	P6 = (A12 + A22) * (B21 - B22)
    	P7 = (A11 + A21) * (B11 - B12)
    	
    	C11 = P5 + P4 - P2 + P6
    	C12 = P1 + P2
    	C21 = P3 + P4
    	C22 = P5 + P1 - P3 + P7
    	*/
    	
    	int size = A.length; //if this function doesn't terminate, it is probably because we're not passing in size
    	
    	if (size == 1) {
    		int[][] result = { { (A[0][0] * B[0][0]) } };
	    	System.out.println("size = 1; result = ");
	    	printMatrix(result);
    		return result;
    	}

    	//Divide matrices into quadrants
    	int newSize = size / 2;
    	System.out.println("size: " + size + "; newSize: " + newSize);

    	int[][] A11 = new int[newSize][newSize];
    	int[][] A12 = new int[newSize][newSize];
    	int[][] A21 = new int[newSize][newSize];
    	int[][] A22 = new int[newSize][newSize];
    	int[][] B11 = new int[newSize][newSize];
    	int[][] B12 = new int[newSize][newSize];
    	int[][] B21 = new int[newSize][newSize];
    	int[][] B22 = new int[newSize][newSize];
    	for (int i = 0; i < newSize; i++){
    		for (int j = 0; j < newSize; j++){
    			A11[i][j] = A[i][j];
    			A12[i][j] = A[i][j + newSize];
    			A21[i][j] = A[i + newSize][j];
    			A22[i][j] = A[i + newSize][j + newSize];
    			B11[i][j] = B[i][j];
    			B12[i][j] = B[i][j + newSize];
    			B21[i][j] = B[i + newSize][j];
    			B22[i][j] = B[i + newSize][j + newSize];
    		}
    	}

    	int[][] B12MinusB22 = subtractMatrices(B12, B22, newSize);
    	int[][] P1 = matrixMultiplicationStrassen(A11, B12MinusB22);
    	int[][] A11PlusA12 = addMatrices(A11, A12, newSize);
    	int[][] P2 = matrixMultiplicationStrassen(A11PlusA12, B22);
    	int[][] A21PlusA22 = addMatrices(A21, A22, newSize);
    	int[][] P3 = matrixMultiplicationStrassen(A21PlusA22, B11);
    	int[][] B21MinusB11 = subtractMatrices(B21, B11, newSize);
    	int[][] P4 = matrixMultiplicationStrassen(A22, B21MinusB11);
    	
    	int[][] A11PlusA22 = addMatrices(A11, A22, newSize);
    	int[][] B11MinusB22 = subtractMatrices(B11, B22, newSize);
    	int[][] P5 = matrixMultiplicationStrassen(A11PlusA22, B11MinusB22);
    	
    	int[][] A12PlusA22 = addMatrices(A12, A22, newSize);
    	int[][] B21MinusB22 = subtractMatrices(B21, B22, newSize);
    	int[][] P6 = matrixMultiplicationStrassen(A12PlusA22, B21MinusB22);
    	
    	int[][] A11PlusA21 = addMatrices(A11, A21, newSize);
    	int[][] B11MinusB12 = subtractMatrices(B11, B12, newSize);
    	int[][] P7 = matrixMultiplicationStrassen(A11PlusA21, B11MinusB12);
    	
    	int[][] P5PlusP4 = addMatrices(P5, P4, newSize);
    	int[][] P2PlusP6 = addMatrices(P2, P6, newSize);
    	int[][] C11 = subtractMatrices(P5PlusP4, P2PlusP6, newSize);
    	int[][] C12 = addMatrices(P1, P2, newSize);
    	int[][] C21 = addMatrices(P3, P4, newSize);
    	int[][] P5PlusP1 = addMatrices(P5, P1, newSize);
    	int[][] P3PlusP7 = addMatrices(P3, P7, newSize);
    	int[][] C22 = subtractMatrices(P5PlusP1, P3PlusP7, newSize);


    	return matrixConqer(C11, C12, C21, C22, newSize);
    }
}



















