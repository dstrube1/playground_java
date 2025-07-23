package com.dstrube;

/*
From ~/java:

javac -d bin com/dstrube/JobScheduler.java
java -cp bin com.dstrube.JobScheduler

*/

import java.util.Arrays;
import java.util.List;

public class JobScheduler{
	
	public static void main(String[] args){
		
		List<Job> jobs = Arrays.asList(
            new Job("A", 0, 30),
            new Job("B", 5, 10),
            new Job("C", 15, 20),
            new Job("D", 25, 40)
        );

        int result = minNodesNeeded(jobs);
        System.out.println("Minimum number of nodes needed: " + result);
        
		System.out.println("Done");
	}
	
	private static class Job {
        String id;
        int start;
        int end;

        Job(String id, int start, int end) {
            this.id = id;
            this.start = start;
            this.end = end;
        }
    }
    
    private static int minNodesNeeded(List<Job> jobs) {
        int n = jobs.size();
        int[] startTimes = new int[n];
        int[] endTimes = new int[n];

        for (int i = 0; i < n; i++) {
            startTimes[i] = jobs.get(i).start;
            endTimes[i] = jobs.get(i).end;
        }

        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int nodes = 0, maxNodes = 0;
        int i = 0, j = 0;

        while (i < n && j < n) {
            if (startTimes[i] < endTimes[j]) {
                nodes++; // A job started
                maxNodes = Math.max(maxNodes, nodes);
                i++;
            } else {
                nodes--; // A job ended
                j++;
            }
        }

        return maxNodes;
    }

}



















