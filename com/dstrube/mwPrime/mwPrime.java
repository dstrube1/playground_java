package com.dstrube.mwPrime;

/*
From ~/java:

Mac:
javac -d bin com/dstrube/mwPrime/mwPrime.java
java -cp bin com.dstrube.mwPrime.mwPrime

Most wanted prime:
https://www.youtube.com/watch?v=vKlVNFOHJ9I

Confirmed ns: 10, 2446

Search has gone up to 1 million (1000000)

Let's look up to 2 million. If that doesn't get unreasonable, go higher


//https://en.wikipedia.org/wiki/Pocklington_primality_test
//https://stackoverflow.com/questions/4551731/interview-question-what-is-the-fastest-way-to-generate-prime-number-recursivel
*/

import java.math.BigInteger;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class mwPrime implements IThreadManagerListener{
	
	private static AtomicReferenceArray<String> completedThreads;
	private static final String EMPTY = "";
	
	public static void main(String[] args) {
		final int processors = Runtime.getRuntime().availableProcessors();
		completedThreads = new AtomicReferenceArray<>(1);
        completedThreads.set(0, EMPTY);
        
        ThreadManager.getInstance().setProcessors(processors);
        
		Date begin = new Date();
		Date end = null;
		for(int n = 2; n < 3000; n++){
			BigInteger bi = getNext(n);
			boolean result = bi.isProbablePrime(1);
			end = new Date();
			if(result){
				System.out.print("n=" + n + " is prime");
				System.out.print(" found in " + getTimeDiff(begin, end) + "; ");
			}else if (n % 100 == 0){
				System.out.print("("+n);
				System.out.print(" found in " + getTimeDiff(begin, end) + "; )");
			}else{
				System.out.print(".");
			}
			
			begin = new Date();
			
		}
	}
	
	private static BigInteger getNext(int n) {
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<n; i++){
			sb.append(i);
		}
		for(int i=n; i>0; i--){
			sb.append(i);
		}
		//System.out.println("sb = " + sb);
		return new BigInteger(sb.toString());
	}
	
	private static String getTimeDiff(Date begin, Date end){
		//https://www.javatpoint.com/how-to-calculate-date-difference-in-java
		long time_difference = end.getTime() - begin.getTime();  
        // Calculate time difference in seconds  
        long seconds_difference = (time_difference / 1000)% 60;   
        // Calculate time difference in minutes  
        long minutes_difference = (time_difference / (1000*60)) % 60;         
        // Show difference in minutes and seconds   
        return (""+
                + minutes_difference   
                + " minutes, "  
                + seconds_difference   
                + " seconds"  
        );   
	}
	
	public void notifyOfThreadManagerProgress(final Thread thread){
		final NotifyingThread notifyingThread = (NotifyingThread) thread;
		System.out.println(thread.getName() " notifying");
    }
    
    public void notifyOfThreadComplete(final Thread thread){
		/*try{
			final int sleep = 100 + random.nextInt(900);
			if (isAnotherThreadNotifying){
// 				System.out.println(thread.getName() + " is waiting, locked out by " + lockedBy.get() + "; sleeping for " + sleep);
			}
			while (isAnotherThreadNotifying){
				thread.sleep(sleep);
			}
		}catch(InterruptedException interruptedException){
			System.out.println("Caught InterruptedException: " + interruptedException);
		}
		isAnotherThreadNotifying = true;
    	if (!completedThreads.get(completedThreads.length()-1).equals(EMPTY)){
//     		make arraylist, populate it with all values, then put them in new this:
//     		completedThreads = new AtomicReferenceArray<>(completedThreads.length()+10);
			final List<String> strings = new ArrayList<>();
			for(int i= 0; i<completedThreads.length(); i++){
				strings.add(completedThreads.get(i));
			}
			completedThreads = new AtomicReferenceArray<String>(completedThreads.length() + 10);
			int index = 0;
			for(String string : strings){
				completedThreads.set(index, string);
				index++;
			}
    	}
    	boolean added = false;
    	final NotifyingThread notifyingThread = (NotifyingThread) thread;
		final BigInteger start = notifyingThread.getStart();
    	for (int i = 0; i < completedThreads.length(); i++){
    		final String completedThread = completedThreads.get(i);
    		if (completedThread.equals(EMPTY)){
    			StringBuilder sb = new StringBuilder();
				sb.append(thread.getName());
				sb.append("(");
				sb.append(start.toString().substring(0,2));
				sb.append(":");
				sb.append(start.toString().length());		
				sb.append(")");
				final JLabel label = new JLabel(sb.toString());
	            panel.add(label);
    			added = true;
    			break;
    		}
    	}
    	if (!added){
    		System.out.println("Warning: No line added for this thread: " + notifyingThread.getName());
    	}
    	isAnotherThreadNotifying = false;*/
    }
}