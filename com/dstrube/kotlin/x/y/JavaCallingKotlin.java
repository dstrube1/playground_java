package x.y;

public class JavaCallingKotlin {

	// Java calling Kotlin
    public static void callKotlinFromJava(){
        System.out.println("\nCalling Kotlin from Java...");
        // Non-static:
        KotlinCallingJava kcj = new KotlinCallingJava();
        kcj.callKotlin();

        // Static (without the JvmStatic annotation on the callKotlinStatically function):
        //KotlinCallingJava.Companion.callKotlinStatically();
        // With the JvmStatic annotation on the callKotlinStatically function:
        KotlinCallingJava.callKotlinStatically();
    }

	// Calling java from Kotlin:
    public static void callJava(){
        System.out.println("Hello from Java, statically!");
    }

    public void callJavaNonStatic(){
        System.out.println("Hello from Java, non-statically!");
    }
}
