/* 
ScopeFunctionsAndCoroutines
Demonstrate use of : apply, also, let, with, and run
while also using suspend functions, Dispatchers, and asynchronous execution

-Compile 
kotlinc -cp lib/kotlinx-coroutines-core.jar ScopeFunctionsAndCoroutines.kt -d out

-Run:
kotlin -cp lib/kotlinx-coroutines-core.jar:out ScopeFunctionsAndCoroutinesKt

Requires:
kotlinx-coroutines-core.jar
=>
curl -L -o kotlinx-coroutines-core.jar https://repo1.maven.org/maven2/org/jetbrains/kotlinx/kotlinx-coroutines-core-jvm/1.8.1/kotlinx-coroutines-core-jvm-1.8.1.jar
*/

import kotlinx.coroutines.*

data class UserRequest(var id: Int = 0, var endpoint: String = "")
data class UserData(val name: String, val score: Int)

// data class automatically generates* equals(), hashCode(), toString(), and copy()
// Also componentN(); for example:
// var (id, endpoint) = myUserRequest // calls component1() and component2()
// *: Unless there are explicit implementations of equals(), hashCode(), or 
// toString() in the data class body or final implementations in a superclass. 
// Then these functions are not generated, and the existing implementations are used. 
// (Providing explicit implementations for the componentN() and copy() functions is not 
// allowed.)

// Suspend function simulating asynchronous network work on Dispatchers.IO
suspend fun fetchUserData(request: UserRequest): UserData = withContext(Dispatchers.IO) {
    delay(300) // Simulates background network delay
    UserData("Alice", 95)
}

fun main() = runBlocking {
    // 1. APPLY (Context: 'this', Returns: receiver)
    // Useful for constructing/initializing objects.
    val request = UserRequest().apply {
        id = 42
        endpoint = "https://api.example.com/user"
    }

    // 2. ALSO (Context: 'it', Returns: receiver)
    // Useful for side-effects (logging, validation) without mutating the flow.
    request.also { println("Log: Executing request for ID ${it.id}") }

    // Asynchronous Execution via async/await on Dispatchers.Default
    val deferredData = async(Dispatchers.Default) {
        fetchUserData(request)
    }

    val userData: UserData? = deferredData.await()

    // 3. LET (Context: 'it', Returns: lambda result)
    // Useful for null-safety checks and transforming data.
    val greeting = userData?.let {
        "Hello, ${it.name}!" 
    }

    // 4. WITH (Context: 'this', Returns: lambda result)
    // Non-extension function for calling multiple methods/properties on an object.
    val summary = with(userData) {
        "User ${this?.name} scored ${this?.score} points."
    }

    // 5. RUN (Context: 'this', Returns: lambda result)
    // Combines 'with' and 'let': executes a block on a nullable receiver object.
    val isHighScorer = userData?.run {
        println(greeting)
        println(summary)
        score > 90
    }

    println("Is High Scorer: $isHighScorer")
}