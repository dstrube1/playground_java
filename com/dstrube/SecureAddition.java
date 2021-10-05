package com.dstrube;

/*
From ~/java:

Mac:
javac -d bin com/dstrube/SecureAddition.java
java -cp bin com.dstrube.SecureAddition

Windows:
javac -d bin com\dstrube\SecureAddition.java
java -cp bin com.dstrube.SecureAddition

Simulating addition of the same values into an odd number of variables, 
making sure all match, and if not, take the majority
*/

public class SecureAddition {
	public static void main(String[] args) {
		try{
			final int c = add(1,2);
			System.out.println("Sum of 1 & 2: " + c);
		}catch(Exception exception){
			System.out.println("Caught exception: " + exception.getMessage());
		}
	}
	
	public static int add(final int a, final int b) throws Exception{
		final int i0 = a + b;
		final int i1 = a + b+1;
		final int i2 = a + b;
		final boolean i0EqI1 = i0 == i1;
		final boolean i1EqI2 = i1 == i2;
		final boolean i0EqI2 = i0 == i2;

		if (i0EqI1 && i1EqI2){
			//System.out.println("all equal");//possible
			return i0;
		}
		else{
			if(i0EqI1){
				//System.out.println("i0 == i1");
				return i0;
			}else if (i1EqI2){
				//System.out.println("i1 == i2");
				return i1;
			}else if (i0EqI2){
				//System.out.println("i0 == i2");
				return i0;
			}else{
				//System.out.println("No winner");//unlikely
				throw new Exception("No winner");
			}
		}
	}
}