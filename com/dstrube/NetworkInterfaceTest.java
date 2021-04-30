package com.dstrube;

/*
From ~/java:

javac -cp bin:bin/servlet-api.jar -d bin -Xlint:deprecation com/dstrube/NetworkInterfaceTest.java
java -cp bin:bin/servlet-api.jar com.dstrube.NetworkInterfaceTest

Started testing NetworkInterface. Then things grew a little.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import java.security.Principal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;


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
			System.out.println("caught UnknownHostException");
		}
		
		HttpServletRequest request = getHttpServletRequest();
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
	
	public static HttpServletRequest getHttpServletRequest(){
		HttpServletRequest request = new HttpServletRequest() {
            @Override
            public String getAuthType() {
                return null;
            }

            @Override
            public Cookie[] getCookies() {
                return new Cookie[0];
            }

            @Override
            public long getDateHeader(String s) {
                return 0;
            }

            @Override
            public String getHeader(String s) {
                return null;
            }

            @Override
            public Enumeration<String> getHeaders(String s) {
                return null;
            }

            @Override
            public Enumeration<String> getHeaderNames() {
                return null;
            }

            @Override
            public int getIntHeader(String s) {
                return 0;
            }

            @Override
            public String getMethod() {
                return null;
            }

            @Override
            public String getPathInfo() {
                return null;
            }

            @Override
            public String getPathTranslated() {
                return null;
            }

            @Override
            public String getContextPath() {
                return null;
            }

            @Override
            public String getQueryString() {
                return null;
            }

            @Override
            public String getRemoteUser() {
                return null;
            }

            @Override
            public boolean isUserInRole(String s) {
                return false;
            }

            @Override
            public Principal getUserPrincipal() {
                return null;
            }

            @Override
            public String getRequestedSessionId() {
                return null;
            }

            @Override
            public String getRequestURI() {
                return null;
            }

            @Override
            public StringBuffer getRequestURL() {
                return null;
            }

            @Override
            public String getServletPath() {
                return null;
            }

            @Override
            public HttpSession getSession(boolean b) {
                return null;
            }

            @Override
            public HttpSession getSession() {
                return null;
            }

            /*@Override
            public String changeSessionId() {
                return null;
            }*/

            @Override
            public boolean isRequestedSessionIdValid() {
                return false;
            }

            @Override
            public boolean isRequestedSessionIdFromCookie() {
                return false;
            }

            @Override
            public boolean isRequestedSessionIdFromURL() {
                return false;
            }

			@SuppressWarnings( "deprecation" )
            @Override
            public boolean isRequestedSessionIdFromUrl() {
                return false;
            }

            @Override
            public boolean authenticate(HttpServletResponse httpServletResponse) throws IOException, ServletException {
                return false;
            }

            @Override
            public void login(String s, String s1) throws ServletException {

            }

            @Override
            public void logout() throws ServletException {

            }

            @Override
            public Collection<Part> getParts() throws IOException, ServletException {
                return null;
            }

            @Override
            public Part getPart(String s) throws IOException, ServletException {
                return null;
            }

            /*@Override
            public <T extends HttpUpgradeHandler> T upgrade(Class<T> aClass) throws IOException, ServletException {
                return null;
            }*/

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public Enumeration<String> getAttributeNames() {
                return null;
            }

            @Override
            public String getCharacterEncoding() {
                return null;
            }

            @Override
            public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

            }

            @Override
            public int getContentLength() {
                return 0;
            }

            /*@Override
            public long getContentLengthLong() {
                return 0;
            }*/

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public ServletInputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public String getParameter(String s) {
                return null;
            }

            @Override
            public Enumeration<String> getParameterNames() {
                return null;
            }

            @Override
            public String[] getParameterValues(String s) {
                return new String[0];
            }

            @Override
            public Map<String, String[]> getParameterMap() {
                return null;
            }

            @Override
            public String getProtocol() {
                return null;
            }

            @Override
            public String getScheme() {
                return null;
            }

            @Override
            public String getServerName() {
                return null;
            }

            @Override
            public int getServerPort() {
                return 0;
            }

            @Override
            public BufferedReader getReader() throws IOException {
                return null;
            }

            @Override
            public String getRemoteAddr() {
                return null;
            }

            @Override
            public String getRemoteHost() {
                return null;
            }

            @Override
            public void setAttribute(String s, Object o) {

            }

            @Override
            public void removeAttribute(String s) {

            }

            @Override
            public Locale getLocale() {
                return null;
            }

            @Override
            public Enumeration<Locale> getLocales() {
                return null;
            }

            @Override
            public boolean isSecure() {
                return false;
            }

            @Override
            public RequestDispatcher getRequestDispatcher(String s) {
                return null;
            }
		
			@SuppressWarnings( "deprecation" )
            @Override
            public String getRealPath(String s) {
                return null;
            }

            @Override
            public int getRemotePort() {
                return 0;
            }

            @Override
            public String getLocalName() {
                return null;
            }

            @Override
            public String getLocalAddr() {
                return null;
            }

            @Override
            public int getLocalPort() {
                return 0;
            }

            @Override
            public ServletContext getServletContext() {
                return null;
            }

            @Override
            public AsyncContext startAsync() throws IllegalStateException {
                return null;
            }

            @Override
            public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
                return null;
            }

            @Override
            public boolean isAsyncStarted() {
                return false;
            }

            @Override
            public boolean isAsyncSupported() {
                return false;
            }

            @Override
            public AsyncContext getAsyncContext() {
                return null;
            }

            @Override
            public DispatcherType getDispatcherType() {
                return null;
            }
        };
        
        return request;
	}
}