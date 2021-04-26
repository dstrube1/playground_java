package com.dstrube;

/*
From ~/java:

javac -cp bin:bin/json-20210307.jar -d bin com/dstrube/JsonParseTest.java
java -cp bin:bin/json-20210307.jar com.dstrube.JsonParseTest

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;
import org.json.JSONArray;


public class JsonParseTest {
    public static void main(String[] args) throws ClassNotFoundException{
        System.out.println("Hello");
        final String url = "https://launch.smarthealthit.org/v/r4/sim/eyJoIjoiMSIsImoiOiIxIiwiZSI6ImU0NDNhYzU4LThlY2UtNDM4NS04ZDU1LTc3NWMxYjhmM2EzNyJ9/fhir/Patient/9da7d8c2-daef-4722-832e-dcf495d13a4e/$everything";
        final String text = readTextFromURL(url);

        final JSONObject jsonObject = new JSONObject(text);
        final JSONArray jsonArray = jsonObject.getJSONArray("entry");
        for (int i = 0; i < jsonArray.length(); i++)
		{
			final JSONObject jsonValue = jsonArray.getJSONObject(i);
			final JSONObject resource = jsonValue.getJSONObject("resource");
			final String resourceType = resource.getString("resourceType");
            //final int floorId = floor.getInt("Id");
            switch (resourceType)
            {
                case "Patient":
                	System.out.print("1");
                    break;
                case "Observation":
                	System.out.print("2");
                    break;
                case "Condition":
                	System.out.print("3");
                    break;
                case "MedicationRequest":
                	System.out.print("4");
                	parseMedicationRequest(resource);
                    break;
                case "Procedure":
                	System.out.print("5");
                	parseProcedure(resource);
                    break;
                case "Immunization":
                	System.out.print("6");
                	parseImmunization(resource);
                    break;
                case "DiagnosticReport":
                	System.out.print("7");
                    break;
                case "Encounter":
                	System.out.print("8");
                    break;
                default:
					System.out.println("Unexpected resourceType: " + resourceType);
			    	break;
            }
        }
    }

    private static String readTextFromURL(final String urlStr){
        String output = "";
        BufferedReader in = null;
        try {
            final StringBuilder stringBuilder = new StringBuilder();
            final URL url = new URL(urlStr);

            // read text returned by server
            in = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            while ((line = in.readLine()) != null) {
//                System.out.println(line);
                stringBuilder.append(line);
            }
            output = stringBuilder.toString();
        }
        catch (final MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        }
        catch (final IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
        finally {
            if(in != null){
                try {
                    in.close();
                }catch (IOException e) {
                    System.out.println("I/O Error: " + e.getMessage());
                }
            }
        }

        return output;
    }
    
    private static void parseImmunization(JSONObject resource){
    	final JSONObject vaccineCode = resource.getJSONObject("vaccineCode");
    	final String text = vaccineCode.getString("text");
    	final String patientReference = resource.getJSONObject("patient").getString("reference");
    	final String encounterReference = resource.getJSONObject("encounter").getString("reference");
    	final String occurrenceDateTime = resource.getString("occurrenceDateTime");
    	//System.out.println("\n parseImmunization \n vaccineCode: "+text+"; patientReference: "+patientReference);
    }
    
    private static void parseProcedure(JSONObject resource){
    	final String text = resource.getJSONObject("code").getString("text");
    	final String patientReference = resource.getJSONObject("subject").getString("reference");
    	final String encounterReference = resource.getJSONObject("encounter").getString("reference");
    	final String occurrenceDateTime = resource.getJSONObject("performedPeriod").getString("start");
    	//System.out.println("\n parseProcedure \n text: "+text+"; patientReference: "+patientReference);
    }
    
    private static void parseMedicationRequest(JSONObject resource){
    	final String text = resource.getJSONObject("medicationCodeableConcept").getString("text");
    	final String patientReference = resource.getJSONObject("subject").getString("reference");
    	final String encounterReference = resource.getJSONObject("encounter").getString("reference");
    	final String practitionerReference = resource.getJSONObject("requester").getString("reference");
    	/*final JSONArray conditionArray = resource.getJSONArray("reasonReference");
    	for (int i = 0; i < conditionArray.length(); i++){
    		
    	}*/
    	final String conditionReference = resource.getJSONArray("reasonReference").getJSONObject(0).getString("reference");
    	final String occurrenceDateTime = resource.getString("authoredOn");
    	//System.out.println("\n parseMedicationRequest \n text: "+text+"; patientReference: "+patientReference);
    }
    
    private static void parseEncounter(JSONObject resource){
    	/*final JSONArray conditionArray = resource.getJSONArray("reasonReference");
    	for (int i = 0; i < conditionArray.length(); i++){
    		
    	}*/
    	final String type = resource.getJSONArray("type").getJSONObject(0).getString("text");
    	final String patientReference = resource.getJSONObject("subject").getString("reference");
    	final String practitionerReference = resource.getJSONObject("participant").getJSONObject("individual").getString("reference");
    	final String occurrenceDateTime = resource.getJSONObject("period").getString("start");
    	final String organizationReference = resource.getJSONObject("serviceProvider").getString("reference");
    	//System.out.println("\n parseMedicationRequest \n text: "+type+"; patientReference: "+patientReference);
    }
}
