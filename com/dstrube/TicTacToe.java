package com.dstrube;

/*
commands to compile and run:
from ~/java
javac -d bin com/dstrube/TicTacToe.java 
java -cp bin com.dstrube.TicTacToe

Tic Tac Toe
*/

import java.util.Arrays;

public class TicTacToe{
	private static char[][]board = new char[3][3];
	public static void main(String[] args){
		initBoard();
		printBoard();
		//TODO while no win, alternate between player 1 & 2
		//after each move, check for win
		System.out.println("Done");
	}
	
	private static void initBoard(){
		int k = 0;
		for(int i = 0; i < board[0].length; i++){
			for(int j = 0; j < board[0].length; j++){
				board[i][j] = '-';//("" + (k++)).charAt(0);
				//System.out.println("["+i+"]["+j+"]");
			}
		}
	}
	
	private static void printBoard(){
		for(int i = 0; i < board[0].length; i++){
			for(int j = 0; j < board[0].length; j++){
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	//Did someone win?
	private static boolean win(final char c){
		return winH(c) || winV(c) || winD(c);
	}
	
	//horizontal win
	private static boolean winH(final char c){
		if(board[0][0] == c && board[0][0] == board[0][1] && board[0][1] == board[0][2]){
			return true;
		}
		if(board[1][0] == c && board[1][0] == board[1][1] && board[1][1] == board[1][2]){
			return true;
		}
		if(board[2][0] == c && board[2][0] == board[2][1] && board[2][1] == board[2][2]){
			return true;
		}
		return false;
	}
	
	//vertical win
	private static boolean winV(final char c){
		if(board[0][0] == c && board[0][0] == board[1][0] && board[1][0] == board[2][0]){
			return true;
		}
		if(board[0][1] == c && board[0][1] == board[1][1] && board[1][1] == board[2][1]){
			return true;
		}
		if(board[0][2] == c && board[0][2] == board[1][2] && board[1][2] == board[2][2]){
			return true;
		}
		return false;
	}
	
	//diagonal win
	private static boolean winD(final char c){
		if(board[0][0] == c && board[0][0] == board[1][1] && board[1][1] == board[2][2]){
			return true;
		}
		if(board[0][2] == c && board[0][2] == board[1][1] && board[1][1] == board[2][0]){
			return true;
		}
		return false;
	}
}