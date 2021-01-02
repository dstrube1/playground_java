package com.dstrube;

/*
commands to compile and run:
from ~/java:

Mac:
javac -d bin com/dstrube/DepthFirstSearchTest.java
java -cp bin com.dstrube.DepthFirstSearchTest

Windows:
javac -d bin com\dstrube\DepthFirstSearchTest.java
java -cp bin com.dstrube.DepthFirstSearchTest


Taking a stab at writing my own depth first search algorithm,
starting from here:
https://en.wikipedia.org/wiki/Depth-first_search

https://www.baeldung.com/java-depth-first-search
*/

import java.util.Stack;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
//https://docs.oracle.com/javase/8/docs/api/java/util/Random.html

public class DepthFirstSearchTest{
	
	private static final Node<Integer> root = new Node<>(0);
	private static int lastInserted = 0;
	private static int steps = 0;
	private static final Map<Integer, Void> inserteds = new HashMap<>();
	
	public static void main(String[] args){
		final int size = 1000000; //1,000,000

		//prevent duplicate root value
		inserteds.put(0, null);

		initTree(root, size);
		
		final int find = lastInserted; //random.nextInt(size);
		System.out.println("Searching for " + formatNum(find));
		//Since numbers are generated randomly, lastInserted may have been also previously inserted, and found in less than V steps
		
		depthFirstSearchPreOrderRecursive(root, find);
		depthFirstSearchPreOrderWithoutRecursion(find);
	}
	
	private static void initTree(Node<Integer> root, int size){
		
		final Random random = new Random();
		//while i < size - 1 because otherwise the prevention of duplicates will run out of candidates and run forever:
		for (int i=0; i < size-1; i++){
			//insert(root, random.nextInt(size));
			int candidate = random.nextInt(size);
			//Prevent duplicates
			while(inserteds.containsKey(candidate)){
				candidate = random.nextInt(size);
			}
			inserteds.put(candidate, null);
			randomInsert(root, candidate);
		}
	}
	
	private static void insert(Node<Integer> node, int value) {
		//Inserts values in ascending order; makes for an unbalanced tree when new values are generated randomly
		//https://www.edureka.co/blog/java-binary-tree
        if (value < node.value) { 
			if (node.left != null) { 
				insert(node.left, value); 
			} else { 
				//System.out.println("Inserted " + value + " to left of " + node.value); 
				node.left = new Node<>(value); 
				lastInserted = value;
			} 
		} else if (value > node.value) {
			if (node.right != null) {
				insert(node.right, value);
			} else {
				//System.out.println("Inserted " + value + " to right of " + node.value);
				node.right = new Node<>(value);
				lastInserted = value;
			}
        }
    }
	
	private static void randomInsert(Node<Integer> node, int value){
		//Makes sure inserts are randomly left and right (on average, should be relatively balanced)
		final Random random = new Random();
		int choice = random.nextInt(2);
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

	private static void depthFirstSearchPreOrderRecursive(final Node<Integer> node, final int find) {
		if (node != null) {
			steps++;
			final boolean found = (node.value == find);
			if (found){
				System.out.println("Found recursively after " + formatNum(steps) + " steps");
				return;
			}
			depthFirstSearchPreOrderRecursive(node.left, find);
			depthFirstSearchPreOrderRecursive(node.right, find);
		}
	}
	
	private static void depthFirstSearchPreOrderWithoutRecursion(final int find) {
		final Stack<Node<Integer>> stack = new Stack<>();
		Node<Integer> current = root;
		stack.push(root);
		steps = 1;
		while(!stack.isEmpty()) {
			current = stack.pop();
			boolean found = (current.value == find);
			if (found){
				System.out.println("Found non-recursively after " + formatNum(steps) + " steps");
				return;
			}
			steps++;
			if(current.right != null) {
				stack.push(current.right);
			}    
			if(current.left != null) {
				stack.push(current.left);
			}
		}        
	}
	/*
	*/
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