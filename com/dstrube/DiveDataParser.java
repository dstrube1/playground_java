package com.dstrube;

/*
From ~/java:
javac -d bin com/dstrube/DiveDataParser.java
java -cp bin com.dstrube.DiveDataParser

This parses thru a text file of dive data, outputs a file more easily importable into Google Docs
*/

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.OpenOption;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class DiveDataParser{
	
	public static void main(String[] args){
		try{
			File fileIn = new File("DiveDataIn.txt");
			//1- read in file; for each line, set one or more fields in a record
			final List<Record> records = readFile(fileIn);
			if (records == null){
				return;
			}
			
			//2- write out 
			writeFile(records);
			
			System.out.println("Done");
			
	    } catch (Exception exception){
	    	System.out.println("Exception caught at " + methodName() + ": " + exception);
	    }
	}
	
	//
	private static List<Record> readFile(final File fileIn) throws ParseException{
		Scanner scanner1 = null;
		try{
			final FileInputStream fileInputStream1 = new FileInputStream(fileIn);
			final List<Record> records = new ArrayList<>();
			scanner1 = new Scanner(fileInputStream1);
			final String DATE = "Date for this dive: ";
			final String LOCATION = "Dive Location:	";
			final String VERIFICATION = "Dive Verification:	My Buddy:";
			final String VERIFIED_BY = " | Dive Verified By: ";
			final String STATS = "Dive Statistics:	Max Depth: ";
			final String AVG_DEPTH = " | Avg Depth: ";
			final String TIME = " | Dive Time: ";
			final String VIZ = " | Viz: ";
			final String TEMP = " | Temp: ";
			final String EQUIPMENT = "Equipment:	";
			final String CONDITIONS = "Dive Conditions:	";
			final String COMMENTS = "Comments:	";
			final String END_OF_RECORD = " Delete this Dive Log";
			Record record = null;
			//int count = 1;
			while(scanner1.hasNextLine()){
				final String line = scanner1.nextLine();
				if(line.startsWith(DATE)){
					record = new Record();
				}
				if (line.equals(END_OF_RECORD)){
					records.add(record);
					record = null;
					//System.out.println(count);
					//count++;
				}
				if(record!= null){
					if(line.startsWith(DATE)){
						String dateIn = line.substring(DATE.length());
						//System.out.println("dateIn: '" + dateIn + "'");
						SimpleDateFormat sdf =  new SimpleDateFormat("d MMM yyyy");
						Date date = sdf.parse(dateIn);		
						//System.out.println("date: '" + date + "'");
						String dateOut = new SimpleDateFormat("yyyy-MM-d").format(date);
						//System.out.println("dateOut: '" + dateOut + "'");
						record.date = dateOut;
					}else if(line.startsWith(LOCATION)){
						String location = line.substring(LOCATION.length());
						record.location = location;//.replace(",", "',");
					}else if(line.startsWith(VERIFICATION)){
						String buddy = line.substring(VERIFICATION.length(), line.indexOf(VERIFIED_BY));
						String verifiedBy = line.substring(line.indexOf(VERIFIED_BY) + VERIFIED_BY.length());
						record.buddy = buddy;
						record.verifiedBy = verifiedBy;
						if(buddy.contains(",") || verifiedBy.contains(",")){
							System.out.println("buddy: '" + buddy + "'");
							System.out.println("verifiedBy: '" + verifiedBy + "'");
						}
					}else if(line.startsWith(STATS)){
						String maxDepth = line.substring(STATS.length(), line.indexOf(AVG_DEPTH));
						String avgDepth = line.substring(line.indexOf(AVG_DEPTH) + AVG_DEPTH.length(), 
							line.indexOf(TIME));
						String time = line.substring(line.indexOf(TIME) + TIME.length(),
							line.indexOf(VIZ));
						String viz = line.substring(line.indexOf(VIZ) + VIZ.length(),
							line.indexOf(TEMP));
						String temp = line.substring(line.indexOf(TEMP) + TEMP.length());
						
						record.maxDepth = maxDepth;
						record.avgDepth = avgDepth;

						//time is tricky
						String[] times = time.split(":");
						record.hour = times[0];
						record.minute = times[1];

						record.viz = viz;
						record.temp = temp;
						
						/*System.out.println("maxDepth: '" + maxDepth + "'");
						System.out.println("avgDepth: '" + avgDepth + "'");
						System.out.println("time: '" + record.hour + "h " + record.minute + "m'");
						System.out.println("viz: '" + viz + "'");
						System.out.println("temp: '" + temp + "'");*/
						
					}else if(line.startsWith(EQUIPMENT)){
						String equipment = line.substring(EQUIPMENT.length());
						record.equipment = equipment;//.replace(",", "',");
						//System.out.println("equipment: '" + equipment + "'");
					}else if(line.startsWith(CONDITIONS)){
						String conditions = line.substring(CONDITIONS.length());
						record.conditions = conditions;
						//System.out.println("conditions: '" + conditions + "'");
					}else if(line.startsWith(COMMENTS)){
						String comments = line.substring(COMMENTS.length());
						record.comments = comments;//.replace(",", "',");
						//System.out.println("comments: '" + comments + "'");
						//break;
					}else {
						System.out.print("????");
					}
				}

			}//end while
			//System.out.println("Read in " + records.size() + " records");
			return records;

	    } catch (FileNotFoundException fnfe){
	    	System.out.println("FileNotFoundException");
	    	return null;
	    } finally{
	    	if (scanner1 != null){
	    		scanner1.close();
	    	}
	    }
	}
	
	private static void writeFile(List<Record> records){
		//Write data to file
		try{
			File file = new File("DiveDataOut.txt");
			Path path = Paths.get(file.toURI());
			OpenOption[] options = {StandardOpenOption.CREATE, StandardOpenOption.APPEND};
			Charset charset = Charset.forName("UTF-8");
			
			List<String> strings = recordsToStrings(records);
			Files.write(path, strings, charset, options);
		}
		catch(IOException e){
			System.out.println("\nCaught exception: " + e);		
		}
	}
	
	private static List<String> recordsToStrings(List<Record> records){
		List<String> strings = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for(Record record : records){
			sb.append(record.date);
			sb.append("\t");
			sb.append(record.location);
			sb.append("\t");
			sb.append(record.buddy);
			sb.append("\t");
			sb.append(record.verifiedBy);
			sb.append("\t");
			sb.append(record.maxDepth);
			sb.append("\t");
			sb.append(record.avgDepth);
			sb.append("\t");
			sb.append(record.hour);
			sb.append("\t");
			sb.append(record.minute);
			sb.append("\t");
			sb.append(record.viz);
			sb.append("\t");
			sb.append(record.temp);
			sb.append("\t");
			sb.append(record.equipment);
			sb.append("\t");
			sb.append(record.conditions);
			sb.append("\t");
			sb.append(record.comments);
			strings.add(sb.toString());
			sb = new StringBuilder();
		}
		return strings;
	}
	
	private static String methodName() {
		//https://www.baeldung.com/java-name-of-executing-method
	    final String methodName = 
    	  Thread.currentThread()
    	  .getStackTrace()[2]//0 = "getStackTrace"; 1 = "methodName"; 2 = calling method
    	  .getMethodName();
        return methodName;
	}
	
	private static class Record{
		public String date;
		public String location;
		public String buddy;
		public String verifiedBy;
		public String maxDepth;
		public String avgDepth;
		public String hour;
		public String minute;
		public String viz;
		public String temp;
		public String equipment;
		public String conditions;
		public String comments;
	}
}