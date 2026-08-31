/*
ExtensionFunctions

Compile:
kotlinc ExtensionFunctions.kt -d out

Run:
kotlin -cp out ExtensionFunctionsKt

Notes:
1- If an extension function has the exact same name and signature as a class's member function, the member function always wins.
2- Extension functions can only access public members of the extended class
3- Extension functions are resolved statically based on the declared type of the variable at compile time, not dynamically at runtime via polymorphism
*/

data class Student(val name: String, val score: Int)

// 1. Extending a Standard Library class (String)
fun String.toTitleCase(): String {
    return this.split(" ")
        .joinToString(" ") { word -> 
            word.lowercase().replaceFirstChar { it.uppercase() } 
        }
}

// 2. Extending a primitive type (Int) with expression body syntax
fun Int.isPassing(): Boolean = this >= 60

// 3. Extending a custom Data Class (Student)
fun Student.gradeLetter(): String = when {
    score >= 90 -> "A"
    score >= 80 -> "B"
    score >= 70 -> "C"
    score >= 60 -> "D"
    else -> "F"
}

// 4. Nullable Receiver extension (can be safely called on null objects)
fun String?.orEmptyPlaceholder(): String = this ?: "N/A"

//=================================================================================
// Extension Functions vs Inheritance / Subclassing
open class Shape {
	open fun x() ="shape"
}
class Circle : Shape() {
	override fun x() = "circle"
}

fun Shape.identify() = "I am a generic shape"
fun Circle.identify() = "I am a circle"

fun printShapeInfo(shape: Shape) {
    println(shape.identify())
    println(shape.x())
}

/*class MyString : String(){
	// is this possible?
	// no:
	// error: this type is final, so it cannot be extended.
}*/

//=================================================================================

fun main() {
    // String extension call
    val userTitle = "kotlin technical interview"
    println(userTitle.toTitleCase()) // Output: Kotlin Technical Interview

    // Custom class & Int extension calls
    val alice = Student("Alice", 85)
    println("${alice.name}'s Grade: ${alice.gradeLetter()}") // Output: Alice's Grade: B
    println("Passed test: ${alice.score.isPassing()}")       // Output: Passed test: true

    // Nullable extension call
    val missingName: String? = null
    println("User name: ${missingName.orEmptyPlaceholder()}") // Output: User name: N/A
    
    /* 
    Extension Functions vs Inheritance / Subclassing
    Since extension functions are resolved statically at compile time based on the 
    declared parameter type (Shape), the compiler links printShapeInfo directly to 
    Shape.identify().
    */
    val myShape: Shape = Circle()
    printShapeInfo(myShape)
    /*
    When to use Extension Functions
Classes you don't own: You can't edit java.lang.String or a third-party library class, 
but you can write extensions for them (String.toTitleCase()).

Utility logic & readability: Keeping lightweight domain logic (formatting, validations, 
conversions) outside of your core data models prevents class bloat.

Functional style: Chaining functions cleanly without creating deep, messy class hierarchies.

	When to use Inheritance / Subclassing
Polymorphism (Dynamic Dispatch): When different child types need to override standard 
behavior dynamically at runtime (like your Shape/Circle example).

Accessing Internal State: When the method needs access to protected or private 
state/properties of the parent class.

True "Is-A" relationships: When modeling core architecture hierarchies where behavior 
is shared and specialized down a tree.
    */

}