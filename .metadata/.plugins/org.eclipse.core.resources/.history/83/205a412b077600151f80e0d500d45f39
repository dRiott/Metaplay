package com.thoughtriott.metaplay.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTester {
public static void main(String[] args) {
	String regex1 = "^[a-zA-Z]+$";
	String regex2 = "^[0-9]+$";
	
	Pattern digitsPattern = Pattern.compile(regex1);
	Pattern lettersPattern = Pattern.compile(regex2);
	
	String justLetters = "asldkfjlkjdfdWELKFJLFO";
	String justNumbers = "2034920";
	String both = "102984lsdkfjals2098lsdkafjl";
	
	Matcher jlDigits = digitsPattern.matcher(justLetters);
	System.out.println("String: "+justLetters+" matches "+regex1+":"+jlDigits.matches());
	
	Matcher jlLetters = lettersPattern.matcher(justLetters);
	System.out.println("String: "+justLetters+" matches "+regex2+":"+jlLetters.matches());

	
	Matcher jnDigits = digitsPattern.matcher(justNumbers);
	System.out.println("String: "+justNumbers+" matches "+regex1+":"+jnDigits.matches());

	Matcher jnLetters = lettersPattern.matcher(justNumbers);
	System.out.println("String: "+justNumbers+" matches "+regex2+":"+jnLetters.matches());

	
	Matcher bDigits = digitsPattern.matcher(both);
	System.out.println("String: "+both+" matches "+regex1+":"+bDigits.matches());

	Matcher bLetters = lettersPattern.matcher(both);
	System.out.println("String: "+both+" matches "+regex2+":"+bLetters.matches());

		
	
	
}
}
