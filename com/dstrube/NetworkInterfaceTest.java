package com.dstrube;

/*
From ~/java:

javac -d bin com/dstrube/NetworkInterfaceTest.java
java -cp bin com.dstrube.NetworkInterfaceTest

*/

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkInterfaceTest{
	
	public static void main(String[] args){
		System.out.println("getOneHardwareIdsToString: " + getOneHardwareIdsToString());
		List<String> list = getAllHardwareIdsToString();
		for(String s : list){
			System.out.println("getAllHardwareIdsToString: " + s);
		}
		try{
			System.out.println("retrieveServerHostname: " + retrieveServerHostname());
		}catch(UnknownHostException exception){
			System.out.println("caught exception);
		}
		System.out.println("Done");
	}
	
	public static String retrieveServerHostname() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostName();
	}
	
	public static String getOneHardwareIdsToString() {
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while(networkInterfaces.hasMoreElements())
			{
				NetworkInterface network = networkInterfaces.nextElement();
				byte[] mac = network.getHardwareAddress();
				if(mac != null && mac.length > 0)
				{
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < mac.length; i++)
					{
						sb.append(String.format("%02x", mac[i]));        
					}
					
					//return first interface
					return sb.toString();
				}
			}
		} catch (SocketException e){
    	    e.printStackTrace();
        	return null;
	    }
		return null;
	}
	
	public static List<String> getAllHardwareIdsToString() {
		List<String> hardwareIdList = new ArrayList<>();
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while(networkInterfaces.hasMoreElements())
			{
				NetworkInterface network = networkInterfaces.nextElement();
				byte[] mac = network.getHardwareAddress();
				if(mac != null && mac.length >0)
				{
				
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < mac.length; i++)
					{
						sb.append(String.format("%02x", mac[i]));        
					}
					
					//return first interface
					hardwareIdList.add(sb.toString());
				}
			}
    	} catch (SocketException e){
	        e.printStackTrace();
    	    return new ArrayList<>();
    	}
		return hardwareIdList;
	}
}