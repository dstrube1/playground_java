/*
-Compile 
kotlinc -cp lib/kotlinx-coroutines-core.jar CoroutinePuzzle.kt -d out

-Run:
kotlin -cp lib/kotlinx-coroutines-core.jar:out CoroutinePuzzleKt

Requires:
kotlinx-coroutines-core.jar

*/

import kotlinx.coroutines.*

suspend fun fetchUser(): String {
    delay(100)
    throw RuntimeException("Network timeout fetching user!")
}

suspend fun fetchPosts(): List<String> {
    delay(500)
    println("Successfully fetched posts!")
    return listOf("Post 1", "Post 2")
}

fun main() = runBlocking {
    try {
        coroutineScope {
        	/* 
        	This is governed by the concept of Structured Concurrency.
        	Parent coroutines manage child coroutines. Under standard coroutineScope:
				If one child fails, it immediately cancels the parent scope.
				The parent scope then cancels all other sibling coroutines running inside it.
				Finally, the parent re-throws the exception to the outer caller.			
			*/
            val userDeferred = async { fetchUser() }
            val postsDeferred = async { fetchPosts() }

            val user = userDeferred.await()
            val posts = postsDeferred.await()

/*
Solution 1:
			A supervisorScope breaks the cancellation chain. 
			A failure in one child will not cancel sibling coroutines or the parent scope.
supervisorScope {
        val userDeferred = async { fetchUser() }
        val postsDeferred = async { fetchPosts() }

        // Safely await user with try/catch
        val user = try {
            userDeferred.await()
        } catch (e: Exception) {
            "Default User (Fallback)"
        }

        val posts = postsDeferred.await()
        println("Result: $user, $posts")
    }
    
Solution 2:
			Keep the coroutineScope, use a try/catch inside the async block:
val userDeferred = async {
    try {
        fetchUser()
    } catch (e: Exception) {
        "Default User"
    }
}
*/
            println("Result: $user, $posts")
        }
    } catch (e: Exception) {
        println("Caught exception: ${e.message}")
    }
}