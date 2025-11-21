/** Functions for checking if a given string is an anagram. */
import java.util.Random;

public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {

		String s1 = preProcess(str1);
		String s2 = preProcess(str2);

		// remove spaces from s1
		String noSpace1 = "";
		for (int i = 0; i < s1.length(); i++) {
			char c = s1.charAt(i);
			if (c != ' ') {
				noSpace1 = noSpace1 + c;
			}
		}

		// remove spaces from s2
		String noSpace2 = "";
		for (int i = 0; i < s2.length(); i++) {
			char c = s2.charAt(i);
			if (c != ' ') {
				noSpace2 = noSpace2 + c;
			}
		}

		if (noSpace1.length() != noSpace2.length()) {
			return false;
		}

		char[] arr2 = noSpace2.toCharArray();

		for (int i = 0; i < noSpace1.length(); i++) {
			char c1 = noSpace1.charAt(i);
			boolean found = false;

			for (int j = 0; j < arr2.length; j++) {
				if (arr2[j] == c1) {
					arr2[j] = 0;  
					found = true;
					break;
				}
			}

			if (!found) {
				return false;
			}
		}

		return true;
	}

	// Returns a preprocessed version of the given string:
	// letters to lower-case, delete other chars, keep spaces.
	public static String preProcess(String str) {
		String out = "";

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			if (c >= 'A' && c <= 'Z') {
				c = (char)(c + ('a' - 'A'));
				out = out + c;
			}
			else if (c >= 'a' && c <= 'z') {
				out = out + c;
			}
			else if (c == ' ') {
				out = out + c;
			}
		}

		return out;
	} 
	   
	// Returns a random anagram of the given string.
	public static String randomAnagram(String str) {
		Random r = new Random();

		String pool = str;  
		String out = "";    

		while (pool.length() > 0) {
			int idx = r.nextInt(pool.length());
			out = out + pool.charAt(idx);
			pool = pool.substring(0, idx) + pool.substring(idx + 1);
		}

		return out;
	}
}
