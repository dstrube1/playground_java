package com.dstrube;

/*
From ~/java:

javac -d bin com/dstrube/SystemTest.java
java -cp bin com.dstrube.SystemTest

Stuff I found interesting while looking at 
https://docs.oracle.com/javase/8/docs/api/java/lang/System.html#identityHashCode-java.lang.Object-
*/

import java.util.*;

public class SystemTest {
	
	private static SystemTest innerSelf = new SystemTest();
	private static X x = new X();
	
	public static void main(String[] args) {
		for(Map.Entry<String,String> entry : System.getenv().entrySet()){
			//System.out.println("key: " + entry.getKey() 
			//	+ "; value: " + entry.getValue());
			/*
key: PATH; value: /usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Applications/VMware Fusion.app/Contents/Public:/Applications/Wireshark.app/Contents/MacOS
key: __CFBundleIdentifier; value: com.apple.Terminal
key: SHELL; value: /bin/zsh
key: TERM_PROGRAM; value: Apple_Terminal
key: OLDPWD; value: /Users/dstrube
key: TERM; value: xterm-256color
key: USER; value: dstrube
key: LANG; value: en_US.UTF-8
key: TMPDIR; value: /var/folders/h1/_7yz2qps3pz0zp_ng2b2rl9w0000gp/T/
key: SSH_AUTH_SOCK; value: /private/tmp/com.apple.launchd.NllLNQiwcn/Listeners
key: XPC_FLAGS; value: 0x0
key: TERM_SESSION_ID; value: 4D82C19D-9A1B-4609-B53C-F750EE92C962
key: __CF_USER_TEXT_ENCODING; value: 0x1F6:0x0:0x0
key: LOGNAME; value: dstrube
key: JAVA_MAIN_CLASS_4824; value: com.dstrube.SystemTest
key: PWD; value: /Users/dstrube/Projects/java
key: TERM_PROGRAM_VERSION; value: 440
key: XPC_SERVICE_NAME; value: 0
key: SHLVL; value: 1
key: HOME; value: /Users/dstrube
key: _; value: /usr/bin/java
dstrube@Strube-MacBook-Pro java % cd com
dstrube@Strube-MacBook-Pro com % cd ..
dstrube@Strube-MacBook-Pro java % java -cp bin com.dstrube.SystemTest
...
key: OLDPWD; value: /Users/dstrube/Projects/java/com
...

			*/
		}
		//System.out.println();

		Properties properties = System.getProperties();
		//properties.list(System.out);
		/*
-- listing properties --
java.specification.version=15
sun.management.compiler=HotSpot 64-Bit Tiered Compilers
ftp.nonProxyHosts=local|*.local|169.254/16|*.169.254/16
sun.jnu.encoding=UTF-8
java.runtime.version=15.0.2+7
java.class.path=bin
user.name=dstrube
java.vm.vendor=AdoptOpenJDK
path.separator=:
sun.arch.data.model=64
os.version=10.16 <================================================*
java.runtime.name=OpenJDK Runtime Environment
java.vendor.url=https://adoptopenjdk.net/
file.encoding=UTF-8
java.vm.specification.version=15
os.name=Mac OS X
java.vm.name=OpenJDK 64-Bit Server VM
java.vendor.version=AdoptOpenJDK
sun.java.launcher=SUN_STANDARD
user.country=US
sun.boot.library.path=/Library/Java/JavaVirtualMachines/ado...
sun.java.command=com.dstrube.SystemTest
java.vendor.url.bug=https://github.com/AdoptOpenJDK/openj...
http.nonProxyHosts=local|*.local|169.254/16|*.169.254/16
java.io.tmpdir=/var/folders/h1/_7yz2qps3pz0zp_ng2b2r...
jdk.debug=release
sun.cpu.endian=little
java.version=15.0.2
user.home=/Users/dstrube
user.dir=/Users/dstrube/Projects/java
user.language=en
os.arch=x86_64
java.specification.vendor=Oracle Corporation
java.vm.specification.name=Java Virtual Machine Specification
java.version.date=2021-01-19
java.home=/Library/Java/JavaVirtualMachines/ado...
file.separator=/
java.vm.compressedOopsMode=Zero based
line.separator=

java.library.path=/Users/dstrube/Library/Java/Extension...
java.vm.specification.vendor=Oracle Corporation
java.specification.name=Java Platform API Specification
java.vm.info=mixed mode, sharing
java.vendor=AdoptOpenJDK
java.vm.version=15.0.2+7
sun.io.unicode.encoding=UnicodeBig
socksNonProxyHosts=local|*.local|169.254/16|*.169.254/16
java.class.version=59.0


os.version=10.16 <================================================WTF?!
Going to [apple], About this Mac: says current version is 11.2.3
		*/
		//See also 
		//https://docs.oracle.com/javase/8/docs/api/java/util/Properties.html
		
		System.out.println("Hashcode of my self: " + System.identityHashCode(innerSelf));
		//Result of identityHashCode may change later on during the execution of a program,
		//so when using it to set an object's hash code, should be called only once 
		//and saved somewhere safe (ie, final)
		
		System.out.println("nanoTime: "+System.nanoTime());
		System.out.println("nanoTime: "+System.nanoTime());
		
		System.out.print("Setting hash code for an object in its constructor: ");
		x.printHash();
		System.out.println();
	}
	
	public static void x(String[] args) {
	}
	
	private static class X{
		private final int hash;
		
		public X(){
			hash = System.identityHashCode(this);
		}
		public void printHash(){
			System.out.print(hash);
		}
	}
}