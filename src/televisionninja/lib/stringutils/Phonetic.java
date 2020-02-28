package televisionninja.lib.stringutils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TelevisionNinja
 *
 */
public class Phonetic {
	/**
	 * everything in one method
	 * 
	 * @param str
	 * @return
	 * @author TelevisionNinja
	 */
	public static String phoneticToString_1(final String str) {
		final String[] arr = str.split(" ");
		final List<String> phoneticArr = new ArrayList<>();
		phoneticArr.add("zero"); /* 0 */
		phoneticArr.add("one"); /* 1 */
		phoneticArr.add("two"); /* 2 */
		phoneticArr.add("three"); /* 3 */
		phoneticArr.add("four"); /* 4 */
		phoneticArr.add("five"); /* 5 */
		phoneticArr.add("six"); /* 6 */
		phoneticArr.add("seven"); /* 7 */
		phoneticArr.add("eight"); /* 8 */
		phoneticArr.add("nine"); /* 9 */

		phoneticArr.add(null); /* symbol */
		phoneticArr.add(null); /* symbol */
		phoneticArr.add(null); /* symbol */
		phoneticArr.add(null); /* symbol */
		phoneticArr.add(null); /* symbol */
		phoneticArr.add(null); /* symbol */
		phoneticArr.add(null); /* symbol */

		phoneticArr.add("alpha"); /* a */
		phoneticArr.add("bravo"); /* b */
		phoneticArr.add("charlie"); /* c */
		phoneticArr.add("delta"); /* d */
		phoneticArr.add("echo"); /* e */
		phoneticArr.add("foxtrot"); /* f */
		phoneticArr.add("golf"); /* g */
		phoneticArr.add("hotel"); /* h */
		phoneticArr.add("india"); /* i */
		phoneticArr.add("juliet"); /* j */
		phoneticArr.add("kilo"); /* k */
		phoneticArr.add("lima"); /* l */
		phoneticArr.add("mike"); /* m */
		phoneticArr.add("november"); /* n */
		phoneticArr.add("oscar"); /* o */
		phoneticArr.add("papa"); /* p */
		phoneticArr.add("quebec"); /* q */
		phoneticArr.add("romeo"); /* r */
		phoneticArr.add("sierra"); /* s */
		phoneticArr.add("tengo"); /* t */
		phoneticArr.add("uniform"); /* u */
		phoneticArr.add("victor"); /* v */
		phoneticArr.add("whiskey"); /* w */
		phoneticArr.add("x-ray"); /* x */
		phoneticArr.add("yankee"); /* y */
		phoneticArr.add("zulu"); /* z */
		final StringBuilder string = new StringBuilder();
		for (final String character : arr) {
			string.append((char) (phoneticArr.indexOf(character) + 48));
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
	public static String phoneticToString_2(final String str) {
		final List<String> phoneticList = new ArrayList<>();
		phoneticList.add("zero"); /* 0 */
		phoneticList.add("one"); /* 1 */
		phoneticList.add("two"); /* 2 */
		phoneticList.add("three"); /* 3 */
		phoneticList.add("four"); /* 4 */
		phoneticList.add("five"); /* 5 */
		phoneticList.add("six"); /* 6 */
		phoneticList.add("seven"); /* 7 */
		phoneticList.add("eight"); /* 8 */
		phoneticList.add("nine"); /* 9 */

		phoneticList.add(null); /* symbol */
		phoneticList.add(null); /* symbol */
		phoneticList.add(null); /* symbol */
		phoneticList.add(null); /* symbol */
		phoneticList.add(null); /* symbol */
		phoneticList.add(null); /* symbol */
		phoneticList.add(null); /* symbol */

		phoneticList.add("alpha"); /* a */
		phoneticList.add("bravo"); /* b */
		phoneticList.add("charlie"); /* c */
		phoneticList.add("delta"); /* d */
		phoneticList.add("echo"); /* e */
		phoneticList.add("foxtrot"); /* f */
		phoneticList.add("golf"); /* g */
		phoneticList.add("hotel"); /* h */
		phoneticList.add("india"); /* i */
		phoneticList.add("juliet"); /* j */
		phoneticList.add("kilo"); /* k */
		phoneticList.add("lima"); /* l */
		phoneticList.add("mike"); /* m */
		phoneticList.add("november"); /* n */
		phoneticList.add("oscar"); /* o */
		phoneticList.add("papa"); /* p */
		phoneticList.add("quebec"); /* q */
		phoneticList.add("romeo"); /* r */
		phoneticList.add("sierra"); /* s */
		phoneticList.add("tengo"); /* t */
		phoneticList.add("uniform"); /* u */
		phoneticList.add("victor"); /* v */
		phoneticList.add("whiskey"); /* w */
		phoneticList.add("x-ray"); /* x */
		phoneticList.add("yankee"); /* y */
		phoneticList.add("zulu"); /* z */
		return StringUtils.convertToChars(phoneticList, str);
	}

	/**
	 * 
	 * @param str
	 * @return
	 * @author TelevisionNinja
	 */
	public static String stringToPhonetic_1(final String str) {
		final StringBuilder phonetic = new StringBuilder(),
				cleanUp = new StringBuilder();

		for (int x = 0; x < str.length(); x++) {
			final char character = str.charAt(x);
			if (Character.isLetter(character) || Character.isDigit(character)) {
				cleanUp.append(character);
			}
		}

		final String[] phoneticArr = {"zero", /* 0 */
				"one", /* 1 */
				"two", /* 2 */
				"three", /* 3 */
				"four", /* 4 */
				"five", /* 5 */
				"six", /* 6 */
				"seven", /* 7 */
				"eight", /* 8 */
				"nine", /* 9 */

				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */

				"alpha", /* a */
				"bravo", /* b */
				"charlie", /* c */
				"delta", /* d */
				"echo", /* e */
				"foxtrot", /* f */
				"golf", /* g */
				"hotel", /* h */
				"india", /* i */
				"juliet", /* j */
				"kilo", /* k */
				"lima", /* l */
				"mike", /* m */
				"november", /* n */
				"oscar", /* o */
				"papa", /* p */
				"quebec", /* q */
				"romeo", /* r */
				"sierra", /* s */
				"tengo", /* t */
				"uniform", /* u */
				"victor", /* v */
				"whiskey", /* w */
				"x-ray", /* x */
				"yankee", /* y */
		"zulu"}; /* z */

		final long cleanUpLength = cleanUp.length();

		for (int x = 0; x < cleanUpLength; x++) {
			final char character = cleanUp.charAt(x);
			if (Character.isLetter(character) || Character.isDigit(character)) {
				int value = character;
				if (value >= 97) {
					value -= 32;
				}
				phonetic.append(phoneticArr[value - 48]);
				if (x < cleanUpLength - 1) {
					phonetic.append(' ');
				}
			}
		}
		return phonetic.toString();
	}

	/**
	 * everything in one method
	 * 
	 * @param str
	 * @return
	 * @author TelevisionNinja
	 */
	public static String stringToPhonetic_2(final String str) {
		final StringBuilder phonetic = new StringBuilder();

		final String[] phoneticArr = {"zero", /* 0 */
				"one", /* 1 */
				"two", /* 2 */
				"three", /* 3 */
				"four", /* 4 */
				"five", /* 5 */
				"six", /* 6 */
				"seven", /* 7 */
				"eight", /* 8 */
				"nine", /* 9 */

				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */

				"alpha", /* a */
				"bravo", /* b */
				"charlie", /* c */
				"delta", /* d */
				"echo", /* e */
				"foxtrot", /* f */
				"golf", /* g */
				"hotel", /* h */
				"india", /* i */
				"juliet", /* j */
				"kilo", /* k */
				"lima", /* l */
				"mike", /* m */
				"november", /* n */
				"oscar", /* o */
				"papa", /* p */
				"quebec", /* q */
				"romeo", /* r */
				"sierra", /* s */
				"tengo", /* t */
				"uniform", /* u */
				"victor", /* v */
				"whiskey", /* w */
				"x-ray", /* x */
				"yankee", /* y */
		"zulu"}; /* z */

		final int length = str.length();
		for (int x = 0; x < length; x++) {
			final char character = str.charAt(x);
			if (Character.isLetter(character) || Character.isDigit(character)) {
				int value = character;
				if (value >= 97) {
					value -= 32;
				}
				phonetic.append(phoneticArr[value - 48]);
				if (x < length - 1) {
					final char compare = str.charAt(x + 1);
					if (Character.isLetter(compare) ||
							Character.isDigit(compare) ||
							compare == ' ') {
						phonetic.append(' ');
					}
				}
			}
		}

		return phonetic.toString();
	}

	/**
	 * methods in method
	 * 
	 * @param str
	 * @return
	 * @author TelevisionNinja
	 */
	public static String stringToPhonetic_3(final String str) {
		final String[] phoneticArr = {"zero", /* 0 */
				"one", /* 1 */
				"two", /* 2 */
				"three", /* 3 */
				"four", /* 4 */
				"five", /* 5 */
				"six", /* 6 */
				"seven", /* 7 */
				"eight", /* 8 */
				"nine", /* 9 */

				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */
				null, /* symbol */

				"alpha", /* a */
				"bravo", /* b */
				"charlie", /* c */
				"delta", /* d */
				"echo", /* e */
				"foxtrot", /* f */
				"golf", /* g */
				"hotel", /* h */
				"india", /* i */
				"juliet", /* j */
				"kilo", /* k */
				"lima", /* l */
				"mike", /* m */
				"november", /* n */
				"oscar", /* o */
				"papa", /* p */
				"quebec", /* q */
				"romeo", /* r */
				"sierra", /* s */
				"tengo", /* t */
				"uniform", /* u */
				"victor", /* v */
				"whiskey", /* w */
				"x-ray", /* x */
				"yankee", /* y */
		"zulu"}; /* z */
		return StringUtils.replaceChars(phoneticArr, str);
	}
}
