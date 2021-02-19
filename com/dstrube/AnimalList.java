package com.dstrube;

/*
commands to compile and run:
from ~/java:

javac -d bin com/dstrube/AnimalList.java
java -cp bin com.dstrube.AnimalList

*/
import java.util.function.Consumer;

public class AnimalList {
    String[] animalArray = null;
    
    public AnimalList setArray(String[] animalArray){
        this.animalArray = animalArray;
        return this;
    }

    public void forEach(Consumer<String> accept) {
		for(String s : animalArray){
			accept.accept(s);
		}
    }


    public static void main(String[] args){
        String[] animalArray = {"Cat","Dog","Bird","Pig","Cow","Sheep"};
        AnimalList animalList = new AnimalList();

		
        animalList.setArray(animalArray).forEach(item -> {
        	System.out.println(item);
        });

    }
}
/*
to learn:
spring boot 
inversion of control
hibernate
tomcat
elastic search
dependency injection
reflection
streams
collections
annotations
threads & concurrency, executor service, locks, synchronization
VS Code / Eclipse
maven
postgresql
SSL - java key tool or openssl

*/