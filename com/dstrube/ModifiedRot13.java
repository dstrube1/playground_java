package com.dstrube;

/*
From ~/java:

Mac:
javac -d bin com/dstrube/ModifiedRot13.java
java -cp bin com.dstrube.ModifiedRot13

ModifiedRot13

from firmware.c:
	char message[] = "$Uryyb+Jbeyq!+Vs+lbh+pna+ernq+guvf+zrffntr+gura$gur+CvpbEI32+PCH"
			"+frrzf+gb+or+jbexvat+whfg+svar.$$++++++++++++++++GRFG+CNFFRQ!$$";
	for (int i = 0; message[i]; i++)
		switch (message[i])
		{
		case 'a' ... 'm':
		case 'A' ... 'M':
			message[i] += 13;
			break;
		case 'n' ... 'z':
		case 'N' ... 'Z':
			message[i] -= 13;
			break;
		case '$':
			message[i] = '\n';
			break;
		case '+':
			message[i] = ' ';
			break;
		}
	puts(message);
	
*/

public class ModifiedRot13 {
	public static void main(String[] args) {
		String input = "$Uryyb+Jbeyq!+Vs+lbh+pna+ernq+guvf+zrffntr+gura$gur+CvpbEI32+PCH"
			+ "+frrzf+gb+or+jbexvat+whfg+svar.$$++++++++++++++++GRFG+CNFFRQ!$$";
		String output = rot13Modified(input);
		System.out.println("output: ");
		System.out.println(output);
		/*
72101108108111 87111114108100! 73102 121111117 9997110 11410197100 116104105115 10910111511597103101 116104101110
116104101 8010599111828632 678085 115101101109115 116111 98101 119111114107105110103 106117115116 102105110101.

                84698384 806583836968!
		*/
		System.out.println("Done");
	}
	
	public static String rot13Modified(String input) {
		if (input == null){
			System.out.println("Null input");
			return input;
		}else if (input.length() == 0){
			System.out.println("Empty input");
			return input;
		}
		final StringBuilder sb = new StringBuilder();
		for(int i = 0; i < input.length(); i++){
			if((input.charAt(i) >= 'a' && input.charAt(i) <= 'm')
				|| (input.charAt(i) >= 'A' && input.charAt(i) <= 'M')){
				//System.out.println("Converting " + input.charAt(i) + " to " + (input.charAt(i) + 13));
				sb.append(input.charAt(i) + 13);
			}
			else if((input.charAt(i) >= 'n' && input.charAt(i) <= 'z')
				|| (input.charAt(i) >= 'N' && input.charAt(i) <= 'Z')){
				//System.out.println("Converting " + input.charAt(i) + " to " + (input.charAt(i) - 13));
				sb.append(input.charAt(i) - 13);
			}
			else if (input.charAt(i) == '$'){
				sb.append('\n');
			}
			else if (input.charAt(i) == '+'){
				sb.append(' ');
			}
			else{
				sb.append(input.charAt(i));
			}
			/*if(i > 2){
				break;
			}*/
		}
		return sb.toString();
	}
}