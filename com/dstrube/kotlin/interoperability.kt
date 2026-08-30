import x.y.KotlinCallingJava
import x.y.JavaCallingKotlin

/*

-Compile 
kotlinc interoperability.kt x/y/KotlinCallingJava.kt x/y/JavaCallingKotlin.java -d out
javac -cp out -d out x/y/JavaCallingKotlin.java

-Run:
kotlin -cp out InteroperabilityKt

*/

fun main() { 
    // Kotlin calling Java:
    val kcj = KotlinCallingJava()
    kcj.callJavaFromKotlin()

    // Java calling Kotlin (from Kotlin, i.e. here / this)
    JavaCallingKotlin.callKotlinFromJava()

    // Both examples do their callings statically AND non-statically

    // Hopefully I did this in such a way that doesn't result in an endless loop :-p
}
