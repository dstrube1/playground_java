/*
Starting from:
https://kotlinlang.org/docs/command-line.html#create-and-run-an-application
https://github.com/dstrube1/playground_android/tree/master/kotlin/KotlinTest/app/src/main/java/com/dstrube/kotlintest
https://www.w3schools.com/kotlin
https://en.wikipedia.org/wiki/Kotlin

-Compile 
kotlinc hello.kt -include-runtime -d out/hello.jar

-Run:
java -jar out/hello.jar

To see all available options, run:
kotlinc -help

TODO:
differences between let, apply, run, with, and also.
suspend functions, Dispatchers, and asynchronous execution.
Make a new repository: playground_kotlin
Move the following numbered items from package com.dstrube.kotlintest.MainActivity to here

Some of the ways in which Kotlin is superior to Java:
* 1. Null Safety
* 2. Data Class & String Interpolation
* 3. Copy Modifier for immutability updates
* 4. Default and Named Parameters
* 5. Single Expression Functions
* 6. 'when' Expressions and Ranges
* 7. 'when' checking with Smart Casting
* 8. Extension Functions
* 9. Infix Notation
* 10. Extension Properties
* 11. Operator Overloading
* 12. Higher-Order Functions & Lambda Syntax Variants
* 13. Sealed Class hierarchy
* 14. Lightweight Coroutines
*
* Bonus: 100% Interoperability
*/


fun main(args : Array<String>) { 
	// As of Kotlin 1.3, args : Array<String> is optional, but still good practice
	
	//type inference and string interpolation
	var says = "hello!"
    println("Kotlin says '$says'.")
    
	val myMap = mutableMapOf<Int, String>()

	// Const assignment option
	val myString : String
	myString = "Does This Work?"
	// Yes, as long as I don't try to assign it again:
	//myString = "how about now?" // no
	
	// String stuff
	var location = myString.length - 1
	var char = myString[location]
	val upper = myString.uppercase()
	val lower = myString.lowercase()
	println("myString ('$myString') at location $location (myString[$location]): $char")
	println("Uppercase: $upper")
	println("Lowercase: $lower")
	
	// conditional assignment / return
	val greeting = if (location < 18) "Good day." else "Good evening."
	
	// when - similar to switch
	when (location) {
		1 -> println("1")
		2 -> println("2")
		3 -> println("3")
  		else -> println("switch / case: location is $location.")
	}
	
	// array
	val arr = arrayOf(1,2,3)
	println("my array size: " + arr.size)
	if (1 in arr) println("1 is in my array")
	else println("1 is not in my array")
	var count = 0
	for (a in arr){
		++count
	}
	println("count done a different way: $count")
	
	// traditional for loop (for (int i = 0...)) requires use of range
	for(char in 'a'..'z')
		print("$char ")
	println()
	
	for(num in 10..20)
		// can't do this: print(num + " ")
		print("$num ")
	println()
	
	// FizzBuzz
	/*for (num in 0..100)
		if (num % 3 == 0 && num % 5 == 0) println("FizzBuzz")
		else if (num % 3 == 0) println("Fizz")
		else if (num % 5 == 0) println("Buzz")
		else println(num)
	println()*/
	
	val param1 = 1
	fun1(param1)
	val param2 = "param2"
	fun1(param2)
}

fun fun1(param1:Any) : Unit {
	// parameter type is any, return type is void / Unit
	println("Hello from fun1; param is: $param1")
	//return
	//^ => return type mismatch: expected 'Void', actual 'Unit'.
}

// Shorter syntax for return:
fun myFunction(x: Int, y: Int) = x + y

// Superclass
open class MyParentClass {
  val x = 5
}

// Subclass
class MyChildClass: MyParentClass() {
  fun myFunction() {
    println(x) // x is now inherited from the superclass
  }
}

class Car(var brand: String, var model: String, var year: Int) {
  // Class function
  fun drive() {
    println("Wrooom!")
  }
  
  // Class function with parameters
  fun speed(maxSpeed: Int) {
    println("Max speed is: " + maxSpeed)
  }
}

