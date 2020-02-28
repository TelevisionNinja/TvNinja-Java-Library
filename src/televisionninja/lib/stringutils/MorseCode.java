package televisionninja.lib.stringutils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TelevisionNinja
 *
 */
public class MorseCode {
	/**
	 * everything in one method
	 * 
	 * @param str
	 * @return
	 * @author TelevisionNinja
	 */
	public static String morseCodeToString_1(final String str) {
		final String[] arr = str.split(" ");
		final List<String> morseCodeArr = new ArrayList<>();
		morseCodeArr.add("-----"); /* 0 */
		morseCodeArr.add(".----"); /* 1 */
		morseCodeArr.add("..---"); /* 2 */
		morseCodeArr.add("...--"); /* 3 */
		morseCodeArr.add("....-"); /* 4 */
		morseCodeArr.add("....."); /* 5 */
		morseCodeArr.add("-...."); /* 6 */
		morseCodeArr.add("--..."); /* 7 */
		morseCodeArr.add("---.."); /* 8 */
		morseCodeArr.add("----."); /* 9 */

		morseCodeArr.add(null); /* symbol */
		morseCodeArr.add(null); /* symbol */
		morseCodeArr.add(null); /* symbol */
		morseCodeArr.add(null); /* symbol */
		morseCodeArr.add(null); /* symbol */
		morseCodeArr.add(null); /* symbol */
		morseCodeArr.add(null); /* symbol */

		morseCodeArr.add(".-"); /* a */
		morseCodeArr.add("-..."); /* b */
		morseCodeArr.add("-.-."); /* c */
		morseCodeArr.add("-.."); /* d */
		morseCodeArr.add("."); /* e */
		morseCodeArr.add("..-."); /* f */
		morseCodeArr.add("--."); /* g */
		morseCodeArr.add("...."); /* h */
		morseCodeArr.add(".."); /* i */
		morseCodeArr.add(".---"); /* j */
		morseCodeArr.add("-.-"); /* k */
		morseCodeArr.add(".-.."); /* l */
		morseCodeArr.add("--"); /* m */
		morseCodeArr.add("-."); /* n */
		morseCodeArr.add("---"); /* o */
		morseCodeArr.add(".--."); /* p */
		morseCodeArr.add("--.-"); /* q */
		morseCodeArr.add(".-."); /* r */
		morseCodeArr.add("..."); /* s */
		morseCodeArr.add("-"); /* t */
		morseCodeArr.add("..-"); /* u */
		morseCodeArr.add("...-"); /* v */
		morseCodeArr.add(".--"); /* w */
		morseCodeArr.add("-..-"); /* x */
		morseCodeArr.add("-.--"); /* y */
		morseCodeArr.add("--.."); /* z */
		final StringBuilder string = new StringBuilder();
		for (final String character : arr) {
			string.append((char) (morseCodeArr.indexOf(character) + 48));
		}
		return string.toString();
	}

	/**
	 * methods in method
	 * 
	 * @param str
	 * @return
	 * @author TelevisionNinja
	 */
	public static String morseCodeToString_2(final String str) {
		final List<String> morseCodeList = new ArrayList<>();
		morseCodeList.add("-----"); /* 0 */
		morseCodeList.add(".----"); /* 1 */
		morseCodeList.add("..---"); /* 2 */
		morseCodeList.add("...--"); /* 3 */
		morseCodeList.add("....-"); /* 4 */
		morseCodeList.add("....."); /* 5 */
		morseCodeList.add("-...."); /* 6 */
		morseCodeList.add("--..."); /* 7 */
		morseCodeList.add("---.."); /* 8 */
		morseCodeList.add("----."); /* 9 */

		morseCodeList.add(null); /* symbol */
		morseCodeList.add(null); /* symbol */
		morseCodeList.add(null); /* symbol */
		morseCodeList.add(null); /* symbol */
		morseCodeList.add(null); /* symbol */
		morseCodeList.add(null); /* symbol */
		morseCodeList.add(null); /* symbol */

		morseCodeList.add(".-"); /* a */
		morseCodeList.add("-..."); /* b */
		morseCodeList.add("-.-."); /* c */
		morseCodeList.add("-.."); /* d */
		morseCodeList.add("."); /* e */
		morseCodeList.add("..-."); /* f */
		morseCodeList.add("--."); /* g */
		morseCodeList.add("...."); /* h */
		morseCodeList.add(".."); /* i */
		morseCodeList.add(".---"); /* j */
		morseCodeList.add("-.-"); /* k */
		morseCodeList.add(".-.."); /* l */
		morseCodeList.add("--"); /* m */
		morseCodeList.add("-."); /* n */
		morseCodeList.add("---"); /* o */
		morseCodeList.add(".--."); /* p */
		morseCodeList.add("--.-"); /* q */
		morseCodeList.add(".-."); /* r */
		morseCodeList.add("..."); /* s */
		morseCodeList.add("-"); /* t */
		morseCodeList.add("..-"); /* u */
		morseCodeList.add("...-"); /* v */
		morseCodeList.add(".--"); /* w */
		morseCodeList.add("-..-"); /* x */
		morseCodeList.add("-.--"); /* y */
		morseCodeList.add("--.."); /* z */
		return StringUtils.convertToChars(morseCodeList, str);
	}

	/**
	 * 
	 * @param morseCode
	 * @param morseCodeSpace
	 * 		-null: default spacing is 3 spaces
	 * 		-other: user defined spacing
	 * @return
	 * @author TelevisionNinja
	 */
	public static String morseCodeToString_3(final String morseCode, String morseCodeSpace) {
		final List<String> morseCodeList = new ArrayList<>();
		morseCodeList.add("-----"); /* 0 */
		morseCodeList.add(".----"); /* 1 */
		morseCodeList.add("..---"); /* 2 */
		morseCodeList.add("...--"); /* 3 */
		morseCodeList.add("....-"); /* 4 */
		morseCodeList.add("....."); /* 5 */
		morseCodeList.add("-...."); /* 6 */
		morseCodeList.add("--..."); /* 7 */
		morseCodeList.add("---.."); /* 8 */
		morseCodeList.add("----."); /* 9 */

		morseCodeList.add(null); /* symbol */
		morseCodeList.add(null); /* symbol */
		morseCodeList.add(null); /* symbol */
		morseCodeList.add(null); /* symbol */
		morseCodeList.add(null); /* symbol */
		morseCodeList.add(null); /* symbol */
		morseCodeList.add(null); /* symbol */

		morseCodeList.add(".-"); /* a */
		morseCodeList.add("-..."); /* b */
		morseCodeList.add("-.-."); /* c */
		morseCodeList.add("-.."); /* d */
		morseCodeList.add("."); /* e */
		morseCodeList.add("..-."); /* f */
		morseCodeList.add("--."); /* g */
		morseCodeList.add("...."); /* h */
		morseCodeList.add(".."); /* i */
		morseCodeList.add(".---"); /* j */
		morseCodeList.add("-.-"); /* k */
		morseCodeList.add(".-.."); /* l */
		morseCodeList.add("--"); /* m */
		morseCodeList.add("-."); /* n */
		morseCodeList.add("---"); /* o */
		morseCodeList.add(".--."); /* p */
		morseCodeList.add("--.-"); /* q */
		morseCodeList.add(".-."); /* r */
		morseCodeList.add("..."); /* s */
		morseCodeList.add("-"); /* t */
		morseCodeList.add("..-"); /* u */
		morseCodeList.add("...-"); /* v */
		morseCodeList.add(".--"); /* w */
		morseCodeList.add("-..-"); /* x */
		morseCodeList.add("-.--"); /* y */
		morseCodeList.add("--.."); /* z */

		if (morseCodeSpace == null) {
			morseCodeSpace = "   ";
		}
		final String[] words = morseCode.split(morseCodeSpace);
		final int length = words.length;
		final StringBuilder sentence = new StringBuilder();
		for (int x = 0; x < length; x++) {
			sentence.append(StringUtils.convertToChars(morseCodeList, words[x]));
			if (x < length - 1) {
				sentence.append(" ");
			}
		}
		return sentence.toString();
	}
	
	/**
	 * 
	 * @param morseCode
	 * @param wordSpace
	 * 		-null: default spacing is 3 spaces
	 * 		-other: user defined spacing
	 * @return
	 * @author TelevisionNinja
	 */
	public static String morseCodeToString_4(final String morseCode, String wordSpace, String letterSpace) {
		final List<String> morseCodeList = new ArrayList<>();
		morseCodeList.add("-----"); /* 0 */
		morseCodeList.add(".----"); /* 1 */
		morseCodeList.add("..---"); /* 2 */
		morseCodeList.add("...--"); /* 3 */
		morseCodeList.add("....-"); /* 4 */
		morseCodeList.add("....."); /* 5 */
		morseCodeList.add("-...."); /* 6 */
		morseCodeList.add("--..."); /* 7 */
		morseCodeList.add("---.."); /* 8 */
		morseCodeList.add("----."); /* 9 */

		morseCodeList.add(null); /* symbol */
		morseCodeList.add(null); /* symbol */
		morseCodeList.add(null); /* symbol */
		morseCodeList.add(null); /* symbol */
		morseCodeList.add(null); /* symbol */
		morseCodeList.add(null); /* symbol */
		morseCodeList.add(null); /* symbol */

		morseCodeList.add(".-"); /* a */
		morseCodeList.add("-..."); /* b */
		morseCodeList.add("-.-."); /* c */
		morseCodeList.add("-.."); /* d */
		morseCodeList.add("."); /* e */
		morseCodeList.add("..-."); /* f */
		morseCodeList.add("--."); /* g */
		morseCodeList.add("...."); /* h */
		morseCodeList.add(".."); /* i */
		morseCodeList.add(".---"); /* j */
		morseCodeList.add("-.-"); /* k */
		morseCodeList.add(".-.."); /* l */
		morseCodeList.add("--"); /* m */
		morseCodeList.add("-."); /* n */
		morseCodeList.add("---"); /* o */
		morseCodeList.add(".--."); /* p */
		morseCodeList.add("--.-"); /* q */
		morseCodeList.add(".-."); /* r */
		morseCodeList.add("..."); /* s */
		morseCodeList.add("-"); /* t */
		morseCodeList.add("..-"); /* u */
		morseCodeList.add("...-"); /* v */
		morseCodeList.add(".--"); /* w */
		morseCodeList.add("-..-"); /* x */
		morseCodeList.add("-.--"); /* y */
		morseCodeList.add("--.."); /* z */

		if (wordSpace == null) {
			wordSpace = "   ";
		}
		
		if (letterSpace == null) {
			letterSpace = " ";
		}
		
		final String[] words = morseCode.split(wordSpace);
		final int length = words.length;
		final StringBuilder sentence = new StringBuilder();
		
		for (int x = 0; x < length; x++) {
			for (String letter : words[x].split(letterSpace)) {
				sentence.append(StringUtils.convertToChars(morseCodeList, letter));
			}
			
			if (x < length - 1) {
				sentence.append(" ");
			}
		}
		return sentence.toString();
	}

	/**
	 * 
	 * @param str
	 * @return
	 * @author TelevisionNinja
	 */
	public static String stringToMorseCode_1(final String str) {
		final StringBuilder morseCode = new StringBuilder(),
				cleanUp = new StringBuilder();

		for (int x = 0; x < str.length(); x++) {
			final char character = str.charAt(x);
			if (Character.isLetter(character) || Character.isDigit(character)) {
				cleanUp.append(character);
			}
		}

		final String[] morseCodeArr = {"-----", /* 0 */
				".----", /* 1 */
				"..---", /* 2 */
				"...--", /* 3 */
				"....-", /* 4 */
				".....", /* 5 */
				"-....", /* 6 */
				"--...", /* 7 */
				"---..", /* 8 */
				"----.", /* 9 */

				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */

				".-", /* a */
				"-...", /* b */
				"-.-.", /* c */
				"-..", /* d */
				".", /* e */
				"..-.", /* f */
				"--.", /* g */
				"....", /* h */
				"..", /* i */
				".---", /* j */
				"-.-", /* k */
				".-..", /* l */
				"--", /* m */
				"-.", /* n */
				"---", /* o */
				".--.", /* p */
				"--.-", /* q */
				".-.", /* r */
				"...", /* s */
				"-", /* t */
				"..-", /* u */
				"...-", /* v */
				".--", /* w */
				"-..-", /* x */
				"-.--", /* y */
		"--.."}; /* z */

		final long cleanUpLength = cleanUp.length();

		for (int x = 0; x < cleanUpLength; x++) {
			final char character = cleanUp.charAt(x);
			if (Character.isLetter(character) || Character.isDigit(character)) {
				int value = character;
				if (value >= 97) {
					value -= 32;
				}
				morseCode.append(morseCodeArr[value - 48]);
				if (x < cleanUpLength - 1) {
					morseCode.append(' ');
				}
			}
		}
		return morseCode.toString();
	}

	/**
	 * 
	 * @param str
	 * @return
	 * @author TelevisionNinja
	 */
	public static String stringToMorseCode_2(final String str) {
		final StringBuilder morseCode = new StringBuilder(),
				cleanUp = new StringBuilder();

		for (int x = 0; x < str.length(); x++) {
			final char character = str.charAt(x);
			if (Character.isLetter(character) || Character.isDigit(character)) {
				cleanUp.append(character);
			}
		}

		final String[] morseCodeArr = {"-----", /* 0 */
				".----", /* 1 */
				"..---", /* 2 */
				"...--", /* 3 */
				"....-", /* 4 */
				".....", /* 5 */
				"-....", /* 6 */
				"--...", /* 7 */
				"---..", /* 8 */
				"----.", /* 9 */

				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */

				".-", /* a */
				"-...", /* b */
				"-.-.", /* c */
				"-..", /* d */
				".", /* e */
				"..-.", /* f */
				"--.", /* g */
				"....", /* h */
				"..", /* i */
				".---", /* j */
				"-.-", /* k */
				".-..", /* l */
				"--", /* m */
				"-.", /* n */
				"---", /* o */
				".--.", /* p */
				"--.-", /* q */
				".-.", /* r */
				"...", /* s */
				"-", /* t */
				"..-", /* u */
				"...-", /* v */
				".--", /* w */
				"-..-", /* x */
				"-.--", /* y */
		"--.."}; /* z */

		final long cleanUpLength = cleanUp.length();

		for (int x = 0; x < cleanUpLength; x++) {
			int value = cleanUp.charAt(x);
			if (value >= 97) {
				value -= 32;
			}
			morseCode.append(morseCodeArr[value - 48]);
			if (x < cleanUpLength - 1) {
				morseCode.append(' ');
			}
		}

		return morseCode.toString();
	}

	/**
	 * everything in one method
	 * 
	 * @param str
	 * @return
	 * @author TelevisionNinja
	 */
	public static String stringToMorseCode_3(final String str) {
		final StringBuilder morseCode = new StringBuilder();

		final String[] morseCodeArr = {"-----", /* 0 */
				".----", /* 1 */
				"..---", /* 2 */
				"...--", /* 3 */
				"....-", /* 4 */
				".....", /* 5 */
				"-....", /* 6 */
				"--...", /* 7 */
				"---..", /* 8 */
				"----.", /* 9 */

				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */

				".-", /* a */
				"-...", /* b */
				"-.-.", /* c */
				"-..", /* d */
				".", /* e */
				"..-.", /* f */
				"--.", /* g */
				"....", /* h */
				"..", /* i */
				".---", /* j */
				"-.-", /* k */
				".-..", /* l */
				"--", /* m */
				"-.", /* n */
				"---", /* o */
				".--.", /* p */
				"--.-", /* q */
				".-.", /* r */
				"...", /* s */
				"-", /* t */
				"..-", /* u */
				"...-", /* v */
				".--", /* w */
				"-..-", /* x */
				"-.--", /* y */
		"--.."}; /* z */

		final int length = str.length();
		for (int x = 0; x < length; x++) {
			final char character = str.charAt(x);
			if (Character.isLetter(character) || Character.isDigit(character)) {
				int value = character;
				if (value >= 97) {
					value -= 32;
				}
				morseCode.append(morseCodeArr[value - 48]);
				if (x < length - 1) {
					final char compare = str.charAt(x + 1);
					if (Character.isLetter(compare) ||
							Character.isDigit(compare) ||
							compare == ' ') {
						morseCode.append(' ');
					}
				}
			}
		}

		return morseCode.toString();
	}

	/**
	 * methods in method
	 * 
	 * @param str
	 * @return
	 * @author TelevisionNinja
	 */
	public static String stringToMorseCode_4(final String str) {
		final String[] morseCodeArr = {"-----", /* 0 */
				".----", /* 1 */
				"..---", /* 2 */
				"...--", /* 3 */
				"....-", /* 4 */
				".....", /* 5 */
				"-....", /* 6 */
				"--...", /* 7 */
				"---..", /* 8 */
				"----.", /* 9 */

				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */

				".-", /* a */
				"-...", /* b */
				"-.-.", /* c */
				"-..", /* d */
				".", /* e */
				"..-.", /* f */
				"--.", /* g */
				"....", /* h */
				"..", /* i */
				".---", /* j */
				"-.-", /* k */
				".-..", /* l */
				"--", /* m */
				"-.", /* n */
				"---", /* o */
				".--.", /* p */
				"--.-", /* q */
				".-.", /* r */
				"...", /* s */
				"-", /* t */
				"..-", /* u */
				"...-", /* v */
				".--", /* w */
				"-..-", /* x */
				"-.--", /* y */
		"--.."}; /* z */
		return StringUtils.replaceChars(morseCodeArr, str);
	}

	/**
	 * uses StringUtils.replaceChars()
	 * 
	 * @param sentence
	 * @param morseCodeSpace
	 * 		-null: default spacing is 3 spaces
	 * 		-other: user defined spacing
	 * @return
	 * @author TelevisionNinja
	 */
	public static String stringToMorseCode_5(final String sentence, final String morseCodeSpace) {
		final String[] morseCodeArr = {"-----", /* 0 */
				".----", /* 1 */
				"..---", /* 2 */
				"...--", /* 3 */
				"....-", /* 4 */
				".....", /* 5 */
				"-....", /* 6 */
				"--...", /* 7 */
				"---..", /* 8 */
				"----.", /* 9 */

				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */

				".-", /* a */
				"-...", /* b */
				"-.-.", /* c */
				"-..", /* d */
				".", /* e */
				"..-.", /* f */
				"--.", /* g */
				"....", /* h */
				"..", /* i */
				".---", /* j */
				"-.-", /* k */
				".-..", /* l */
				"--", /* m */
				"-.", /* n */
				"---", /* o */
				".--.", /* p */
				"--.-", /* q */
				".-.", /* r */
				"...", /* s */
				"-", /* t */
				"..-", /* u */
				"...-", /* v */
				".--", /* w */
				"-..-", /* x */
				"-.--", /* y */
		"--.."}; /* z */

		final String[] words = sentence.split(" ");
		final int length = words.length;
		final StringBuilder morseCode = new StringBuilder();
		for (int x = 0; x < length; x++) {
			morseCode.append(StringUtils.replaceChars(morseCodeArr, words[x]));
			if (x < length - 1) {
				if (morseCodeSpace == null) {
					morseCode.append("   ");
				}
				else {
					morseCode.append(morseCodeSpace);
				}
			}
		}
		return morseCode.toString();
	}

	/**
	 * uses Stirng.trim()
	 * uses StringUtils.replaceChar()
	 * uses Character.isWhitespace()
	 * 
	 * @param sentence
	 * @param morseCodeSpace
	 * 		-null: default spacing is 3 spaces
	 * 		-other: user defined spacing
	 * @return
	 * @author TelevisionNinja
	 */
	public static String stringToMorseCode_6(final String sentence, final String morseCodeSpace) {
		final String[] morseCodeArr = {"-----", /* 0 */
				".----", /* 1 */
				"..---", /* 2 */
				"...--", /* 3 */
				"....-", /* 4 */
				".....", /* 5 */
				"-....", /* 6 */
				"--...", /* 7 */
				"---..", /* 8 */
				"----.", /* 9 */

				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */

				".-", /* a */
				"-...", /* b */
				"-.-.", /* c */
				"-..", /* d */
				".", /* e */
				"..-.", /* f */
				"--.", /* g */
				"....", /* h */
				"..", /* i */
				".---", /* j */
				"-.-", /* k */
				".-..", /* l */
				"--", /* m */
				"-.", /* n */
				"---", /* o */
				".--.", /* p */
				"--.-", /* q */
				".-.", /* r */
				"...", /* s */
				"-", /* t */
				"..-", /* u */
				"...-", /* v */
				".--", /* w */
				"-..-", /* x */
				"-.--", /* y */
		"--.."}; /* z */

		final StringBuilder morseCode = new StringBuilder();

		final char[] chars = sentence.trim().toCharArray();
		final int length = chars.length;
		for (int x = 0; x < length; x++) {
			final String character = StringUtils.replaceChar(morseCodeArr, chars[x]);

			if (character != null) {
				morseCode.append(character);
				if (x < length - 1) {
					if (Character.isWhitespace(chars[x + 1])) {
						if (morseCodeSpace == null) {
							morseCode.append("   ");
						}
						else {
							morseCode.append(morseCodeSpace);
						}
					}
					else {
						morseCode.append(" ");
					}
				}
			}
		}
		/*
		 * ". "        dot                        "."
		 * "---"       dash                       "-"
		 * "  "        space between character    " "
		 * "      "    space between word         "   "
		 */
		return morseCode.toString();
	}

	/**
	 * uses Stirng.strip()
	 * uses StringUtils.replaceChar()
	 * uses Character.isWhitespace()
	 * 
	 * @param sentence
	 * @param morseCodeSpace
	 * 		-null: default spacing is 3 spaces
	 * 		-other: user defined spacing
	 * @return
	 * @author TelevisionNinja
	 */
	public static String stringToMorseCode_7(final String sentence, final String morseCodeSpace) {
		final String[] morseCodeArr = {"-----", /* 0 */
				".----", /* 1 */
				"..---", /* 2 */
				"...--", /* 3 */
				"....-", /* 4 */
				".....", /* 5 */
				"-....", /* 6 */
				"--...", /* 7 */
				"---..", /* 8 */
				"----.", /* 9 */

				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */

				".-", /* a */
				"-...", /* b */
				"-.-.", /* c */
				"-..", /* d */
				".", /* e */
				"..-.", /* f */
				"--.", /* g */
				"....", /* h */
				"..", /* i */
				".---", /* j */
				"-.-", /* k */
				".-..", /* l */
				"--", /* m */
				"-.", /* n */
				"---", /* o */
				".--.", /* p */
				"--.-", /* q */
				".-.", /* r */
				"...", /* s */
				"-", /* t */
				"..-", /* u */
				"...-", /* v */
				".--", /* w */
				"-..-", /* x */
				"-.--", /* y */
		"--.."}; /* z */

		final StringBuilder morseCode = new StringBuilder();

		final char[] chars = sentence.strip().toCharArray();
		final int length = chars.length;
		for (int x = 0; x < length; x++) {
			final String character = StringUtils.replaceChar(morseCodeArr, chars[x]);

			if (character != null) {
				morseCode.append(character);
				if (x < length - 1) {
					if (Character.isWhitespace(chars[x + 1])) {
						if (morseCodeSpace == null) {
							morseCode.append("   ");
						}
						else {
							morseCode.append(morseCodeSpace);
						}
					}
					else {
						morseCode.append(" ");
					}
				}
			}
		}
		return morseCode.toString();
	}
	
	/**
	 * uses Stirng.strip()
	 * uses StringUtils.replaceChar()
	 * uses Character.isWhitespace()
	 * 
	 * @param sentence
	 * @param wordSpace
	 * 		-null: default spacing is 3 spaces
	 * 		-other: user defined spacing
	 * @return
	 * @author TelevisionNinja
	 */
	public static String stringToMorseCode_8(final String sentence, final String wordSpace, final String letterSpace) {
		final String[] morseCodeArr = {"-----", /* 0 */
				".----", /* 1 */
				"..---", /* 2 */
				"...--", /* 3 */
				"....-", /* 4 */
				".....", /* 5 */
				"-....", /* 6 */
				"--...", /* 7 */
				"---..", /* 8 */
				"----.", /* 9 */

				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */

				".-", /* a */
				"-...", /* b */
				"-.-.", /* c */
				"-..", /* d */
				".", /* e */
				"..-.", /* f */
				"--.", /* g */
				"....", /* h */
				"..", /* i */
				".---", /* j */
				"-.-", /* k */
				".-..", /* l */
				"--", /* m */
				"-.", /* n */
				"---", /* o */
				".--.", /* p */
				"--.-", /* q */
				".-.", /* r */
				"...", /* s */
				"-", /* t */
				"..-", /* u */
				"...-", /* v */
				".--", /* w */
				"-..-", /* x */
				"-.--", /* y */
				"--.."}; /* z */

		final StringBuilder morseCode = new StringBuilder();

		final char[] chars = sentence.strip().toCharArray();
		final int length = chars.length;
		for (int x = 0; x < length; x++) {
			final String character = StringUtils.replaceChar(morseCodeArr, chars[x]);

			if (character != null) { // compare against null because of the null section between the numbers and the letters
				morseCode.append(character);
				if (x < length - 1) {
					if (Character.isWhitespace(chars[x + 1])) {
						if (wordSpace == null) {
							morseCode.append("   ");
						}
						else {
							morseCode.append(wordSpace);
						}
					}
					else if (letterSpace == null) {
						morseCode.append(" ");
					}
					else {
						morseCode.append(letterSpace);
					}
				}
			}
		}
		return morseCode.toString();
	}
}