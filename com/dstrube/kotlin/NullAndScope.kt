/*
Compile:
kotlinc NullAndScope.kt -d out

Run:
kotlin -cp out NullAndScopeKt

*/

data class Employee(val id: Int, var role: String){
// what happens if a data class has a body? that's fine
// with a function?
	fun myFun(){} // no problem
}
// Pro-tip: Avoid using var inside data classes, especially when objects are used as keys 
// in Maps or elements in Sets.
// https://proandroiddev.com/avoid-using-var-in-the-data-class-constructor-in-kotlin-f40cfb8e16c7
// also interesting:
// https://proandroiddev.com/avoid-using-array-in-the-data-class-constructor-in-kotlin-ebc308e46a95

fun main() {
    val employee = Employee(101, "Developer")
    val team = mutableSetOf(employee)

    println("First check: ${team.contains(employee)}") // Check 1 - true

    employee.role = "Engineering Lead"

    println("Second check: ${team.contains(employee)}") // Check 2 - false
    // Mutating employee.role changes the object's hash code
}

class ProfileManager {
    var bio: String? = "Software Engineer"

    fun printBioLength() {
        if (bio != null) {
            // Compiler Error here:
            //println("Length: ${bio.length}") 
            // smart cast to 'String' is impossible, because 'bio' is a mutable property 
            // that could be mutated concurrently.
            
            // Solution 1 : '?' null check
            println("Length: ${bio?.length}")
            // Solution 2: local *immutable* val
            val localBio = bio
			if (localBio != null) {
			    println("Length: ${localBio.length}") 
			}
            // Solution 3: Scope function
            bio?.let { println("Length: ${it.length}") }
        }
    }
}