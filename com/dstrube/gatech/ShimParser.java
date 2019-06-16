package com.dstrube.gatech;

import org.jsoup.Jsoup;
import org.jsoup.HttpStatusException;

import org.json.JSONObject;
import org.json.JSONArray;
//import javax.json.JSONObject;
//import javax.json.JSONArray;

//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;

//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.lang.reflect.Type;

/*
Author: David Strube
Date: 2019-06-07

https://jsoup.org/
https://github.com/codeforatlanta/nature-events-calendar/blob/master/java/ReiScraper.java

Just to be safe, we'll try to keep our URLs below 2k chars:
https://stackoverflow.com/questions/417142/what-is-the-maximum-length-of-a-url-in-different-browsers

GTPE: Ga Tech Professional Education, e.g., Global Learning Center

Compile:
with jsoup and org.json:
javac -cp bin;bin\jsoup-1.12.1.jar;bin\json-20180813.jar; -d bin com\dstrube\gatech\ShimParser.java
with jsoup, org.json, and gson:
javac -cp bin;bin\jsoup-1.12.1.jar;bin\json-20180813.jar;bin\gson-2.8.5.jar; -d bin com\dstrube\gatech\ShimParser.java
Run:
with jsoup and org.json:
java -cp bin;bin\jsoup-1.12.1.jar;bin\json-20180813.jar; com.dstrube.gatech.ShimParser
with jsoup, org.json, and gson:
java -cp bin;bin\jsoup-1.12.1.jar;bin\json-20180813.jar;bin\gson-2.8.5.jar; com.dstrube.gatech.ShimParser

TODO: Get Gson working
*/

public class ShimParser {
	private static final String URL_0 = "https://ems-22miles-shim-test.us-e1.cloudhub.io/api/22miles/campus/reservations?roomId=";
	private static final String URL_1 = "https://ems-22miles-shim-test.us-e1.cloudhub.io/api/22miles/gtpe/reservations?roomId=";
	private static final String PAGE_SIZE_URL = "&pageSize=";
	private static final String PAGE_URL = "&page=";
	private static final int JSOUP_TIMEOUT = 10000;
	private static final int ROOM_ID_INCREMENT = 50; //greater increment may result in timeouts
	private static final int PAGE_SIZE = 50;		//more?
	private static final int DEBUG_LEVEL = 0;		//0 = silent; 1 = quiet; 2 = noisy
	
	public static void main(String[] args) {
		parseURL(URL_0);
		parseURL(URL_1);
		System.out.println("\nDone");		
	}
	
	private static void parseURL(final String startUrl){
		final StringBuilder roomsBuilder = new StringBuilder();
		int startRoomId = 0;
		int endRoomId = startRoomId + ROOM_ID_INCREMENT;
		
		while (startRoomId < 2000){
			for (int roomId = startRoomId; roomId < endRoomId; roomId++){
				roomsBuilder.append(roomId);
				roomsBuilder.append(",");
			}
			final String roomIds = roomsBuilder.toString().substring(0, roomsBuilder.toString().length()-1);
			
			int pageNum = 0;
			int pageCount = 1;
			while(pageNum < pageCount){
				pageNum++;
				final String URL = startUrl + roomIds + PAGE_SIZE_URL + PAGE_SIZE + PAGE_URL + pageNum;
				System.out.print((DEBUG_LEVEL >= 1) ? "URL: " + URL + "\n" : "");
				System.out.print((DEBUG_LEVEL >= 1) ? "length of URL: " + URL.length() + "\n" : "");
				try {
				
					final String json = Jsoup.connect(URL).timeout(JSOUP_TIMEOUT).ignoreContentType(true).execute().body();
					final JSONObject pageResult = new JSONObject(json);
					pageNum = pageResult.getInt("Page");
					pageCount = pageResult.getInt("PageCount");
					final int resultsCount = pageResult.getInt("ResultsCount");
					if (resultsCount < 1){
						System.out.print((DEBUG_LEVEL >= 1) ? "resultsCount: " + resultsCount + "\n" : "");
						//break;
						//TODO: Come back to this once ResultsCount inaccuracy is fixed by EMS
					}
					final JSONArray events = pageResult.getJSONArray("Events");
					System.out.print((DEBUG_LEVEL >= 1) ? "page: " + pageNum + "\n" : "");
					System.out.print((DEBUG_LEVEL >= 1) ? "pageCount: " + pageCount + "\n" : "");
					System.out.print((DEBUG_LEVEL >= 1) ? "resultsCount: " + resultsCount + "\n" : "");

					for (int i = 0; i < events.length(); i++)
					{
						final JSONObject jsonObject = events.getJSONObject(i);
						
						final JSONObject event = jsonObject.getJSONObject("Event");
						String eventDescription = event.getString("Description");
						final String eventStart = event.getString("StartDateTime");
						final String eventEnd = event.getString("EndDateTime");
						String eventName = event.getString("Name");
						
						final JSONObject room = jsonObject.getJSONObject("Room");
						String roomDescription = room.getString("Description");
						String roomCode = room.getString("Code");
						
						final JSONObject floor = room.getJSONObject("Floor");
						final int floorId = floor.getInt("Id");
						String floorDescription = floor.getString("Description");
						
						eventDescription = clean(eventDescription);
						eventName = clean(eventName);
						roomCode = clean(roomCode);
						roomDescription = clean(roomDescription);
						floorDescription = clean(floorDescription);
						
						System.out.print((DEBUG_LEVEL >= 1) ? i + ": " : "");
						System.out.print(eventStart + ",");
						System.out.print(eventEnd + ",");
						System.out.print(eventName + ",");
						System.out.print(eventDescription + ",");//always blank right now, but that may change
						System.out.print(roomCode + ",");
						System.out.print(roomDescription + ",");
						System.out.print(floorId + ",");
						System.out.println(floorDescription);
						
					}
				}catch (HttpStatusException httpStatusException) {
					if (httpStatusException.getStatusCode() != 404){
						if (DEBUG_LEVEL >= 1){
							System.out.println("\nUnexpected httpStatusException at " + roomIds);
							httpStatusException.printStackTrace();
						}
					}else{
						if (DEBUG_LEVEL >= 2){
							System.out.print("(" + roomIds + ")");
							System.out.println("Expected 404");
						}
					}
					break;
				}catch(Exception exception){
					if (DEBUG_LEVEL >= 1){
						System.out.println("\nUnexpected exception at " + roomIds);
						exception.printStackTrace();
					}
					break;
				}
			}
			roomsBuilder.delete(0, roomsBuilder.length());
			startRoomId += ROOM_ID_INCREMENT;
			endRoomId += ROOM_ID_INCREMENT;
		}
	}
	
	private static String clean(String input){
		if (input == null){
			return input;
		}
		if (input.contains(",")){
			input = "\"" + input + "\"";
		}
		return input;
	}
	
	public static void main_old(String[] args) {
		final String URL_OLD = "http://ems-22miles-shim.us-e1.cloudhub.io/api/22miles/reservations?roomId=";
		boolean prevWasSuccess = false;
		for (int roomId = 1200; roomId < 1400; roomId++){
			try {
				final String URL = URL_OLD + roomId;
				final String json = Jsoup.connect(URL).timeout(JSOUP_TIMEOUT).ignoreContentType(true).execute().body();
				if (!prevWasSuccess){
					System.out.println();
				}
				final JSONArray arr = new JSONArray(json);
				String codeLast = "";
				String descriptionLast = "";
				for (int i = 0; i < arr.length(); i++)
				{
					JSONObject jsonObject = arr.getJSONObject(i);
					JSONObject room = jsonObject.getJSONObject("Room");
					String code = room.getString("Code");
					String description = room.getString("Description");
					if (code.contains(",")){
						code = "\"" + code + "\"";
					}
					if (description.contains(",")){
						description = "\"" + description + "\"";
					}
					if (!codeLast.equals(code) && !descriptionLast.equals(description))
					{
						System.out.print(roomId + ",");
						System.out.print(code + ",");
						System.out.println(description);
					}
					codeLast = code;
					descriptionLast = description;
				}
				prevWasSuccess = true;
				//final JSONObject jsonObject = new JSONObject(json);
				//String startDateTime = jsonObject.getString("StartDateTime");
				//System.out.println("startDateTime: " + startDateTime);
				
				/*final Gson gson = new Gson();
				//Type listType = new TypeToken<List<EventEntry>>(){}.getType();
				//List<EventEntry> eventEntries = gson.fromJson(json, listType);
				
				//System.out.println("eventEntry.event.name: " + eventEntries.get(0).event);//.name); //null
				//System.out.println("eventEntry.room.code: " + eventEntries.get(0).room);//.code); //null
				//System.out.println("eventEntry.start: " + eventEntries.get(0).getStart());//.code); //null
				
				//String toJson = gson.toJson(json);
				//System.out.println("gson.toJson(json): " + toJson);
				
				EventEntry[] eventEntries;
				ArrayList<EventEntry> list;
				eventEntries = new Gson().fromJson(json,EventEntry[].class);
				list = new ArrayList<>(Arrays.asList(eventEntries));
				System.out.println("eventEntry.event.name: " + list.get(0).event);//.name); //null
				System.out.println("eventEntry.room.code: " + list.get(0).room);//.code); //null
				System.out.println("eventEntry.start: " + list.get(0).getStart());//.code); //null*/
				//break;
			}catch (HttpStatusException httpStatusException) {
				prevWasSuccess = false;
				if (httpStatusException.getStatusCode() != 404){
					System.out.println("\nUnexpected httpStatusException at " + roomId);
					httpStatusException.printStackTrace();
					break;
				}else{
					System.out.print("(" + roomId + ")");
				}
			}catch(Exception exception){
				prevWasSuccess = false;
				System.out.println("\nUnexpected exception at " + roomId);
				exception.printStackTrace();
				break;
			}
		}
		System.out.println("\nDone");
	}
	/*
	static class EventEntry {
		private String start;
		private String end;
		public Event event;
		public Room room;
		EventEntry() {}
		public LocalDateTime getStart(){
			if (start == null){
				return null;
			}
			return LocalDateTime.parse(start);
		}
		public LocalDateTime getEnd(){
			if (end == null){
				return null;
			}
			return LocalDateTime.parse(end);
		}
	}
	
	static class Event{
		public String description;
		public String name;
		public String start;
		public String end;
		Event(){}
	}
	static class Room{
		public String description;
		public String code;
		Room(){}
	}
	*/
}
/*
OLD:
duplicates or out of order: all good
http://ems-22miles-shim.us-e1.cloudhub.io/api/22miles/reservations?roomId=642,554
http://ems-22miles-shim.us-e1.cloudhub.io/api/22miles/reservations?roomId=642,554,642
=>
[
  {
    "StartDateTime": "2019-06-07T12:30:00-04:00",
    "EndDateTime": "2019-06-07T13:45:00-04:00",
    "Event": {
      "Description": "",
      "StartDateTime": "2019-06-07T12:30:00-04:00",
      "EndDateTime": "2019-06-07T13:45:00-04:00",
      "Name": "CRN: 52197 MGT-3150-A Prin of Management"
    },
    "Room": {
      "Description": "201 Classroom COB",
      "Code": "201"
    }
  }
]

no event - same as if room id is invalid:
http://ems-22miles-shim.us-e1.cloudhub.io/api/22miles/reservations?roomId=554
=>
{
  "message": "No results found"
}

two events:
http://ems-22miles-shim.us-e1.cloudhub.io/api/22miles/reservations?roomId=642,128

END OLD
==================================================================================
NEW:
New URL with new JSON structure:
http://ems-22miles-shim-test.us-e1.cloudhub.io/api/22miles/campus/reservations?roomId=554,642
New URL path
Results removed StartDateTime & EndDateTime outside Event object
Results added:
"Page", "PageSize","PageCount","ResultsCount", & "Events"
Also in Room object: Floor object

http://ems-22miles-shim-test.us-e1.cloudhub.io/api/22miles/campus/reservations?roomId=554,642&pageSize=1&page=1
=>
{
  "Page": 1,
  "PageSize": 1,
  "PageCount": 4,
  "ResultsCount": 4,
  "Events": [
    {
      "Event": {
        "Description": "",
        "StartDateTime": "2019-06-11T08:00:00-04:00",
        "EndDateTime": "2019-06-11T09:50:00-04:00",
        "Name": "CRN: 55872 MGT-2255-ES Quant. Anlys. for Bus."
      },
      "Room": {
        "Description": "201 Classroom COB",
        "Code": "201",
        "Floor": {
          "Id": 2,
          "Description": "Second Floor"
        }
      }
    }
  ]
}

END NEW
==================================================================================
TODO:
Add headers to request:
client_id
client_secret
*/