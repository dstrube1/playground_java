package com.dstrube.mwPrime;

/*
This is the interface that a class must implement so that its child threads can let the 
parent thread know when they're done, and so the parent thread can handle those completions.

From ~/java:

Compile:
javac -d bin com/dstrube/mwPrime/INotifyingThreadListener.java
*/

public interface INotifyingThreadListener {
   	void notifyOfThreadComplete(final Thread thread);
   	void notifyOfThreadProgress(final Thread thread);
}