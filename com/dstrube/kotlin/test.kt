/*
Compile:
kotlinc test.kt -d out

Run:
kotlin -cp out TestKt

*/

import kotlin.system.*

interface ProducerConsumer<in T, out R> {
    fun produce(): R
    fun consume(item: T)

    // Compiler Error on the next two lines!
    //fun invalidProduce(): T 
// error: type parameter 'T' is declared as 'in' but occurs in 'out' position in 
// type 'T (of interface ProducerConsumer<in T, out R>)'.
    //fun invalidConsume(item: R)
// error: type parameter 'R' is declared as 'out' but occurs in 'in' position in 
// type 'R (of interface ProducerConsumer<in T, out R>)'.

	// Fixed:
	fun validProduce(): R
	fun validConsume(item: T)

}

var myListA : List<Any> = listOf('a')
//var myListS : List<String> = myListA
// error: initializer type mismatch: expected 'List<String>', actual 'List<Any>'.
// What about in the other directions?
var myListS : List<String> = listOf("b")
// This is okay
var myListAb : List<Any> = myListS
// This is not okay:
//myListAb = myListS //syntax error: Expecting a top level declaration.
// If you try this with MutableList, it will fail to compile:
//val mutableStringList: MutableList<String> = mutableListOf("Alice")
//val mutableAnyList: MutableList<Any> = mutableStringList // Compile Error!
// initializer type mismatch: expected 'MutableList<Any>', actual 'MutableList<String>'.


/**/
//TODO: Learn about lateinit & "by lazy"
class AppController {
    lateinit var apiService: List<String> //ApiService
    //^ Must be var (mutable)
    // Manually initialized later via code assignment
    // Cannot be used on primitive types (Int, Boolean) or nullable types
    // Not thread-safe by default
    // Throws UninitializedPropertyAccessException if accessed before being set
    
    /*val database: Database by lazy { 
    //						^^Must be val (read-only/immutable)
    // Automatically initialized on first access using the provided lambda block
    // Works with any type (primitives, nullable, non-null)
    // Thread-safe by default (uses synchronization locks under the hood)
    // Guaranteed to execute its block on first access, so it cannot be uninitialized
    
        println("Initializing Database...")
        Database() 
    }*/
}
/**/

//import kotlin.system.*
//Interesting: syntax error: imports are only allowed in the beginning of file.

fun main() { 

    var sqrt = 0
    val number = 1000
    val timeInNanos = measureNanoTime {
        while (sqrt * sqrt < number) sqrt++
    }
    println("(The operation took $timeInNanos ns)")
    println("The approximate square root of $number is between ${sqrt - 1} and $sqrt") 

}