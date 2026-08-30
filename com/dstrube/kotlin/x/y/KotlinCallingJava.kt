package x.y

class KotlinCallingJava {
	// Kotlin calling Java
    fun callJavaFromKotlin(){
        println("\nCalling Java from Kotlin...")

        // Non-static:
        val jck = JavaCallingKotlin()
        jck.callJavaNonStatic()

        // Static:
        JavaCallingKotlin.callJava()
    }

	// Calling Kotlin from Java
    fun callKotlin(){
        println("Hello from Kotlin, non-statically!")
    }

    companion object{
    	@JvmStatic
        fun callKotlinStatically(){
            println("Hello from Kotlin, statically!")
        }
    }
}