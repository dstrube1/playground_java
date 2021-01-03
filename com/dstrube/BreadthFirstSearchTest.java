package com.dstrube;

/*
commands to compile and run:
from ~/java:

Mac:
javac -d bin com/dstrube/BreadthFirstSearchTest.java
java -cp bin com.dstrube.BreadthFirstSearchTest

Windows:
javac -d bin com\dstrube\BreadthFirstSearchTest.java
java -cp bin com.dstrube.BreadthFirstSearchTest


Taking a stab at writing my own breadth first search algorithm,
starting from here:
https://en.wikipedia.org/wiki/Breadth-first_search

Maybe next: Heaps?
https://www.softwaretestinghelp.com/heap-data-structure-in-java/
https://docs.oracle.com/javase/8/docs/api/java/util/PriorityQueue.html

*/

import java.util.Queue;
import java.util.ArrayDeque;
//https://docs.oracle.com/javase/8/docs/api/java/util/ArrayDeque.html
import java.util.Random;
//https://docs.oracle.com/javase/8/docs/api/java/util/Random.html
import java.util.HashMap;
import java.util.Map;

public class BreadthFirstSearchTest{
	
	private static final Node<Integer> root = new Node<>(0);
	private static int lastInserted = 0;
	private static int steps = 0;
	private static final Map<Integer, Void> inserteds = new HashMap<>();
	private static final int size = 1000000; //1,000,000
	
	public static void main(String[] args){
		
		//prevent duplicate root value
		inserteds.put(0, null);

		initTree(root);
		
		final int find = lastInserted; //random.nextInt(size);
		System.out.println("Searching for " + formatNum(find));
		//Since numbers are generated randomly, lastInserted may have been also previously inserted, and found in less than V steps
		
		breadthFirstSearch(find);
	}
	
	private static void initTree(Node<Integer> root){
		
		final Random random = new Random();
		//while i < size - 1 because otherwise the prevention of duplicates will run out of candidates and run forever:
		for (int i=0; i < size-1; i++){
			int candidate = random.nextInt(size);
			//Prevent duplicates
			while(inserteds.containsKey(candidate)){
				candidate = random.nextInt(size);
			}
			inserteds.put(candidate, null);
			randomInsert(root, candidate);
		}
	}
	
	private static void randomInsert(Node<Integer> node, int value){
		//Makes sure inserts are randomly left and right (on average, should be relatively balanced)
		final Random random = new Random();
		int choice = random.nextInt(2); //0 or 1 === nextBoolean
		if (choice == 1){
			if (node.left != null) { 
				randomInsert(node.left, value); 
			} else { 
				//System.out.println("Inserted " + value + " to left of " + node.value); 
				node.left = new Node<>(value); 
				lastInserted = value;
			}
		}else{
			if (node.right != null) {
				randomInsert(node.right, value);
			} else {
				//System.out.println("Inserted " + value + " to right of " + node.value);
				node.right = new Node<>(value);
				lastInserted = value;
			}
		}
	}
	
	private static void breadthFirstSearch(final int find) {
		final Queue<Node<Integer>> queue = new ArrayDeque<>(size);
		Node<Integer> current = root;
		queue.add(root);
		steps = 1;
		while(!queue.isEmpty()) {
			current = queue.remove();
			boolean found = (current.value == find);
			if (found){
				System.out.println("Found after " + formatNum(steps) + " steps");
				return;
			}
			steps++;
			if(current.right != null) {
				queue.add(current.right);
			}    
			if(current.left != null) {
				queue.add(current.left);
			}
		}        
	}
	
	private static String formatNum(int num){
		String s = ""+num;
		
		if(s.length() <= 3) return s;
		
		StringBuilder sb = new StringBuilder(s);  
		sb.reverse();  
		s = sb.toString();
		int i = 3; 
		while(i < s.length()){
			s = s.substring(0, i) + "," + s.substring(i);
			i += 4;
		}
		sb = new StringBuilder(s);  
		sb.reverse();  
		s = sb.toString();
		return s;
	}

	private static class Node<T>{
		
		public Node<T> left;
		public Node<T> right;
		public T value;
		
		public Node(T value){
			//System.out.println("Init node with value " + value);
			this.value = value;
			left = null;
			right = null;
		}
		
		@Override
		public String toString() {
			return String.valueOf(this.value);
		}
	}
}