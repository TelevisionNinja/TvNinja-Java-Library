package televisionninja.lib.stringutils;

import java.util.ArrayList;
import java.util.List;

import televisionninja.lib.listutils.ArrayUtils;
import televisionninja.lib.listutils.ListUtils;
import televisionninja.lib.mathutils.Random;

/**
 * @author TelevisionNinja
 *
 */
public class StringUtils {
	/**
	 * 
	 * @param num
	 * @param size
	 * @return
	 * @author TelevisionNinja
	 */
	public static String addLeadingToNum(final long num, final long size) {
		return String.format("%0" + size + "d", num);
	}

	/**
	 * 
	 * @param str
	 * @param c
	 * @param size
	 * @return
	 * @author TelevisionNinja
	 */
	public static String addLeadingToString_1(final String str, final char c, final long size) {
		final StringBuilder sized = new StringBuilder();
		for (long y = 0; y < size; y++) {
			sized.append(c);
		}
		return (sized.toString() + str).substring(str.length());
	}

	/**
	 * 
	 * @param str
	 * @param c
	 * @param size
	 * @return
	 * @author TelevisionNinja
	 */
	public static String addLeadingToString_2(final String str, final char c, final long size) {
		final StringBuilder sized = new StringBuilder();
		for (long y = 0; y < (size - str.length()); y++) {
			sized.append(c);
		}
		sized.append(str);
		return sized.toString();
	}

	/**
	 * 
	 * @param str
	 * @param c
	 * @param size
	 * @return
	 * @author TelevisionNinja
	 */
	public static String addTrailingToString(final String str, final char c, final long size) {
		final StringBuilder sized = new StringBuilder();
		sized.append(str);
		for (long y = 0; y < (size - str.length()); y++) {
			sized.append(c);
		}
		return sized.toString();
	}

	/**
	 * String.valueOf()
	 * 
	 * @param character
	 * @return
	 * @author TelevisionNinja
	 */
	public static String charToString(final char character) {
		return String.valueOf(character);
	}

	/**
	 * for loop method
	 * 
	 * @param str
	 * @param character
	 * @return
	 * @author TelevisionNinja
	 */
	public static boolean containsChar_1(final String str, final char character) {
		for (int x = 0; x < str.length(); x++) {
			if (str.charAt(x) == character) {
				return true;
			}
		}
		return false;
	}

	/**
	 * string.indexOf() method
	 * 
	 * @param str
	 * @param character
	 * @return
	 * 		false if char is not in str
	 * 		true if char is in string
	 * @author TelevisionNinja
	 */
	public static boolean containsChar_2(final String str, final char character) {
		return str.indexOf(character) != -1;
	}

	/**
	 * string.indexOf() method
	 * 
	 * @param str
	 * @param check
	 * @return
	 * 		false if char is not in str
	 * 		true if string is in str
	 * @author TelevisionNinja
	 */
	public static boolean containsString(final String str, final String check) {
		return str.indexOf(check) != -1;
	}

	/**
	 * 
	 * @param replacementChars
	 * 		must have 7 nulls between numbers and chars
	 * @param str
	 * @return
	 * 		string of chars
	 * @author TelevisionNinja
	 */
	public static String convertToChars(final List<String> replacementChars, final String str) {
		final String[] arr = str.split(" ");

		final StringBuilder string = new StringBuilder();
		for (final String character : arr) {
			string.append((char) (replacementChars.indexOf(character) + 48));
		}
		return string.toString();
	}

	/**
	 * List.set(List.indexOf(), "") && listToString2()
	 * 
	 * @param ln
	 * @param word
	 * @return
	 * @author TelevisionNinja
	 */
	public static String deleteWord1stInstance_1(final String ln, final String word) {
		final String space = " ";
		final List<String> list = ListUtils.stringToList_4(ln, space);
		list.set(list.indexOf(word), "");
		return ListUtils.listStringToString_2(list, space);
	}

	/**
	 * String.replaceFirst()
	 * 
	 * @param ln
	 * @param word
	 * @param replace
	 * @return
	 * @author TelevisionNinja
	 */
	public static String deleteWord1stInstance_2(final String ln, final String word) {
		final String boundary = "\\b";
		return ln.replaceFirst(boundary + word + boundary + "\\s", "");
	}

	/**
	 * enhanced for loop && listToString2()
	 * 
	 * @param ln
	 * @param word
	 * @param replace
	 * @return
	 * @author TelevisionNinja
	 */
	public static String deleteWordAllInstances_1(final String ln, final String word) {
		final String space = " ";
		final List<String> list = ListUtils.stringToList_2(ln, space);
		for (int x = 0; x < list.size(); x++) {
			if (list.get(x).equals(word)) {
				list.remove(x);
			}
		}
		return ListUtils.listStringToString_2(list, space);
	}

	/**
	 * String.replaceAll()
	 * 
	 * @param ln
	 * @param word
	 * @param replace
	 * @return
	 * @author TelevisionNinja
	 */
	public static String deleteWordAllInstances_2(final String ln, final String word) {
		final String boundary = "\\b";
		return ln.replaceAll(boundary + word + boundary + "\\s", "");
	}

	/**
	 * online method
	 * 
	 * @param strArr
	 * @return
	 * 		- the largest common substring of all strings in the array
	 * 		- an empty string of none is found
	 * @author TelevisionNinja
	 */
	public static String getLongestCommonSubStringOfNStrings(final String[] strArr) {
		String commonStr = "",
				tempCom = "";
		for (final char c: ArrayUtils.getMinString_2(strArr).toCharArray()) {
			tempCom += StringUtils.charToString(c);
			for (final String s1 : strArr) {
				if (!s1.contains(tempCom)) {
					tempCom = StringUtils.charToString(c);
					for (final String s2 : strArr) {
						if (!s2.contains(tempCom)) {
							tempCom = "";
							break;
						}
					}
					break;
				}
			}
			if (tempCom.length() > commonStr.length()) {
				commonStr = tempCom;
			}
		}
		return commonStr;
	}

	/**
	 * 
	 * @param strArr
	 * @return
	 * 		- a list of the largest common substrings of all strings in the array
	 * 		- an empty list of none is found
	 * @author TelevisionNinja
	 */
	public static List<String> getLongestCommonSubStringsListOfNStrings(final String[] strArr) {
		String commonStr = "",
				tempCom = "";
		final List<String> commons = new ArrayList<>();
		for (final char c: ArrayUtils.getMinString_2(strArr).toCharArray()) {
			tempCom += StringUtils.charToString(c);
			for (final String s1 : strArr) {
				if (!s1.contains(tempCom)) {
					tempCom = StringUtils.charToString(c);
					for (final String s2 : strArr) {
						if (!s2.contains(tempCom)) {
							tempCom = "";
							break;
						}
					}
					break;
				}
			}
			final long tempLength = tempCom.length(),
					commonStrLength = commonStr.length();
			if (tempLength == commonStrLength) {
				commons.add(tempCom);
			}
			else if (tempLength > commonStrLength) {
				commons.clear();
				commons.add(tempCom);
				commonStr = tempCom;
			}
		}
		return commons;
	}

	/**
	 * string.indexOf() method
	 * 
	 * @param character
	 * @param grammarString
	 * @return
	 * @author TelevisionNinja
	 */
	private static boolean grammarCheck(final char character, final String grammarString) {
		String grammar = "";
		if (grammarString == grammar) {
			return false;
		}
		else if (grammarString == null) {
			grammar = "@.";
		}
		else {
			grammar = grammarString;
		}
		return containsChar_2(grammar, character);
	}

	/**
	 * 
	 * @param s
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<String> groupByChars_1(final String s) {
		final List<String> splitted = new ArrayList<>();

		final StringBuilder element = new StringBuilder();

		for (int i = 0; i < s.length(); i++) {
			final char current = s.charAt(i);
			element.append(current);
			if (i + 1 == s.length() || current != s.charAt(i + 1)) {
				splitted.add(element.toString());
				element.setLength(0);
			}
		}

		return splitted;
	}

	/**
	 * 
	 * @param s
	 * @return
	 * @author TelevisionNinja
	 */
	public static String[] groupByChars_2(final String s) {
		return s.split("(?<=(.))(?!\\1)");
	}

	/**
	 * Integer.toString() method
	 * 
	 * @param x
	 * @return
	 * @author TelevisionNinja
	 */
	public static String intToString(final int x) {
		return Integer.toString(x);
	}

	/**
	 * x + '0' method
	 * 
	 * @param x
	 * @return
	 * @author TelevisionNinja
	 */
	public static char intValueToChar(final int x) {
		return (char) (x + '0');
	}

	/**
	 * 
	 * @param seed
	 * @param passwordLength
	 * @param charList
	 * @return
	 * @author TelevisionNinja
	 */
	public static String randStrAlg_1(int seed, final int passwordLength, final String charList) {
		final StringBuilder password = new StringBuilder();
		int divisor = 10;
		final int charListLength = charList.length();
		for (int index = 0; index < passwordLength; index++) {
			if (seed < 0) {
				seed *= -1;
			}
			else if (seed == 0) {
				seed = charListLength * index;
			}
			if (seed % 100 < charListLength) {
				divisor = 100;
			}
			password.append(charList.charAt(seed % divisor));
			seed /= divisor;
			divisor = 10;
		}
		return password.toString();
	}

	/**
	 * StringBuilder
	 * MathUtils().randomInt() method
	 * 
	 * @param passwordLength
	 * @param charList
	 * @return
	 * @author TelevisionNinja
	 */
	public static String randStrAlg_2(final long passwordLength, final String charList) {
		final StringBuilder password = new StringBuilder();
		final int charListLength = charList.length();
		for (long x = 0; x < passwordLength; x++) {
			password.append(charList.charAt(Random.randomInt_2(0, charListLength)));
		}
		return password.toString();
	}

	/**
	 * char[]
	 * MathUtils().randomInt() method
	 * 
	 * @param passwordLength
	 * @param charList
	 * @return
	 * @author TelevisionNinja
	 */
	public static String randStrAlg_3(final int passwordLength, final String charList) {
		final char[] password = new char[passwordLength];
		final int charListLength = charList.length();
		for (int x = 0; x < passwordLength; x++) {
			password[x] = charList.charAt(Random.randomInt_2(0, charListLength));
		}
		return new String(password);
	}

	/**
	 * 
	 * @param raplacementChars
	 * 		must have 7 nulls between numbers and chars
	 * @param c
	 * 		has to be a letter or digit
	 * @return
	 * 		-string of replaced chars of only numbers and letters
	 * 		-null if c is not letter or digit
	 * @author TelevisionNinja
	 */
	public static String replaceChar(final String[] replacementChars, final char c) {
		if (Character.isLetter(c) || Character.isDigit(c)) {
			int value = c;
			if (c >= 97) {
				value -= 32;
			}
			return replacementChars[value - 48];
		}
		return null;
	}

	/**
	 * 
	 * @param raplacementChars
	 * 		must have 7 nulls between numbers and chars
	 * @param str
	 * @return
	 * 		string of replaced chars of only numbers and letters
	 * @author TelevisionNinja
	 */
	public static String replaceChars(final String[] replacementChars, final String str) {
		final StringBuilder replacedChars = new StringBuilder();
		final int length = str.length();
		for (int x = 0; x < length; x++) {
			final char character = str.charAt(x);
			if (Character.isLetter(character) || Character.isDigit(character)) {
				int value = character;
				if (value >= 97) {
					value -= 32;
				}
				replacedChars.append(replacementChars[value - 48]);
				if (x < length - 1) {
					final char compare = str.charAt(x + 1);
					if (Character.isLetter(compare) ||
							Character.isDigit(compare) ||
							compare == ' ') {
						replacedChars.append(' ');
					}
				}
			}
		}
		return replacedChars.toString();
	}

	/**
	 * String.substring() method
	 * 
	 * @param str
	 * @param substring
	 * @param replacement
	 * @return
	 * @author TelevisionNinja
	 */
	public static String replaceSubstring1stInstance(final String str, final String substring, final String replacement) {
		return str.substring(0, str.indexOf(substring)) + replacement + str.substring(str.indexOf(substring) + substring.length());
	}

	/**
	 * String.replace() method
	 * 
	 * @param str
	 * @param substring
	 * @param replacement
	 * @return
	 * @author TelevisionNinja
	 */
	public static String replaceSubstringAllInstances(final String str, final String substring, final String replacement) {
		return str.replace(substring, replacement);
	}

	/**
	 * List.set(List.indexOf(), ) && listToString2()
	 * 
	 * @param ln
	 * @param word
	 * @param replace
	 * @return
	 * @author TelevisionNinja
	 */
	public static String replaceWord1stInstance_1(final String ln, final String word, final String replace) {
		final String space = " ";
		final List<String> list = ListUtils.stringToList_2(ln, space);
		list.set(list.indexOf(word), replace);
		return ListUtils.listStringToString_2(list, space);
	}

	/**
	 * String.replaceFirst()
	 * 
	 * @param ln
	 * @param word
	 * @param replace
	 * @return
	 * @author TelevisionNinja
	 */
	public static String replaceWord1stInstance_2(final String ln, final String word, final String replace) {
		final String boundary = "\\b";
		return ln.replaceFirst(boundary + word + boundary, replace);
	}

	/**
	 * enhanced for loop && listToString2()
	 * 
	 * @param ln
	 * @param word
	 * @param replace
	 * @return
	 * @author TelevisionNinja
	 */
	public static String replaceWordAllInstances_1(final String ln, final String word, final String replace) {
		final String space = " ";
		final List<String> list = ListUtils.stringToList_2(ln, space);
		for (int x = 0; x < list.size(); x++) {
			if (list.get(x).equals(word)) {
				list.set(x, replace);
			}
		}
		return ListUtils.listStringToString_2(list, space);
	}

	/**
	 * String.replaceAll()
	 * 
	 * @param ln
	 * @param word
	 * @param replace
	 * @return
	 * @author TelevisionNinja
	 */
	public static String replaceWordAllInstances_2(final String ln, final String word, final String replace) {
		final String boundary = "\\b";
		return ln.replaceAll(boundary + word + boundary, replace);
	}

	/**
	 * loop method
	 * 
	 * @param str
	 * @return
	 * @author TelevisionNinja
	 */
	public static String reverse_1(final String str) {
		final StringBuilder reverse = new StringBuilder();
		for (int x = str.length() - 1; x >= 0; x--) {
			reverse.append(str.charAt(x));
		}
		return reverse.toString();
	}

	/**
	 * StringBuffer().reverse().toString() method
	 * 
	 * @param string
	 * @return
	 * @author TelevisionNinja
	 */
	public static String reverse_2(final String string) {
		return new StringBuffer(string).reverse().toString();
	}

	/**
	 * StringBuilder().reverse().toString() method
	 * 
	 * @param str
	 * @return
	 * @author TelevisionNinja
	 */
	public static String reverse_3(final String str) {
		return new StringBuilder(str).reverse().toString();
	}

	/**
	 * 
	 * @param str
	 * @param lead
	 * @return
	 * @author TelevisionNinja
	 */
	public static String stripLeading_1(final String str, final char lead) {
		final int length = str.length();
		for (int x = 0; x < length; x++) {
			if (str.charAt(x) != lead) {
				return str.substring(x, length);
			}
		}
		return "";
	}

	/**
	 * 
	 * @param str
	 * @param lead
	 * @return
	 * @author TelevisionNinja
	 */
	public static String stripLeading_2(final String str, final char lead) {
		int x = 0;
		final int length = str.length();
		for (final char c : str.toCharArray()) {
			if (c != lead) {
				return str.substring(x, length);
			}
			x++;
		}
		return "";
	}

	/**
	 * 
	 * @param str
	 * @param lead
	 * @return
	 * @author TelevisionNinja
	 */
	public static String stripLeading_3(final String str, final char lead) {
		return str.replaceAll("^" + lead + "+", "");
	}

	/**
	 * 
	 * @param str
	 * @param lead
	 * @return
	 * @author TelevisionNinja
	 */
	public static String stripLeading_4(final String str, final String lead) {
		return str.replaceAll("^" + lead + "+", "");
	}

	/**
	 * 
	 * @param str
	 * @return
	 * @author TelevisionNinja
	 */
	public static String stripLeadingChar(final String str) {
		return str.replaceAll("^" + str.charAt(0) + "+", "");
	}

	/**
	 * 
	 * @param str
	 * @param lead
	 * @return
	 * @author TelevisionNinja
	 */
	public static String stripTrailing_1(final String str, final char trail) {
		for (int x = str.length(); x > 0; x--) {
			if (str.charAt(x - 1) != trail) {
				return str.substring(0, x);
			}
		}
		return "";
	}

	/**
	 * 
	 * @param str
	 * @param lead
	 * @return
	 * @author TelevisionNinja
	 */
	public static String stripTrailing_2(final String str, final char trail) {
		return str.replaceAll(trail + "+$", "");
	}

	/**
	 * 
	 * @param str
	 * @param trail
	 * @return
	 * @author TelevisionNinja
	 */
	public static String stripTrailing_3(final String str, final String trail) {
		return str.replaceAll(trail + "+$", "");
	}

	/**
	 * 
	 * @param str
	 * @return
	 * @author TelevisionNinja
	 */
	public static String stripTrailingChar(final String str) {
		return str.replaceAll(str.charAt(str.length() - 1) + "+$", "");
	}

	/**
	 * 
	 * @param str
	 * @return
	 * @author TelevisionNinja
	 */
	public static char strToChar(final String str) {
		return str.charAt(0);
	}

	/**
	 * 
	 * @param str
	 * 		-str to be title cased
	 * @return
	 * 		-title cased string
	 * 		-any character that isn't the 1st char in the word is left as is
	 * 		-first char is always capitalized
	 * @author TelevisionNinja
	 */
	public static String titleCase(final String str) {
		final char[] charArr = str.toCharArray();
		charArr[0] = Character.toUpperCase(charArr[0]);
		for (int y = 1; y < charArr.length; y++) {
			if (!(Character.isLetter(charArr[y - 1]))) {
				charArr[y] = Character.toUpperCase(charArr[y]);
			}
		}
		return new String(charArr);
	}

	/**
	 * 		default grammar check: @.
	 * 
	 * @param str
	 * 		-str to be title cased
	 * @param characters
	 * 		-chars that indicate method to not title case upcoming char
	 * 
	 * 		-empty str = everything is upper cased
	 * 		-null = default char list
	 * 		-anything else is custom char list
	 * @param lowerCase
	 * 		-true = lower cases everything not going to be title cased
	 * 		-false = leaves characters not going to be title cased as is
	 * @return
	 * 		-title cased string
	 * 		-first char is always capitalized
	 * @author TelevisionNinja
	 */
	public static String titleCaseCustom(final String str, final String characters, final boolean lowerCase) {
		final char[] charArr = str.toCharArray();
		charArr[0] = Character.toUpperCase(charArr[0]);
		for (int y = 1; y < charArr.length; y++) {
			if (Character.isLetter(charArr[y - 1])) {
				if (lowerCase) {
					charArr[y] = Character.toLowerCase(charArr[y]);
				}
			}
			else {
				if (grammarCheck(charArr[y - 1], characters)) {
					charArr[y] = Character.toLowerCase(charArr[y]);
				}
				else {
					charArr[y] = Character.toUpperCase(charArr[y]);
				}
			}
		}
		return new String(charArr);
	}

	/**
	 * trims the specified char off the provided string
	 * 
	 * @param str
	 * @param trimChar
	 * @return
	 * @author TelevisionNinja
	 */
	public static String trim_1(final String str, final char trimChar) {
		final int length = str.length();
		int start = 0,
				end = length;

		for (int i = 0; (i == start || i == length - end) && i < length; i++) {
			final int newEnd = end - 1;

			if (str.charAt(newEnd) == trimChar) {
				end = newEnd;
			}

			if (str.charAt(start) == trimChar) {
				start++;
			}
		}

		if (start < end) {
			if (end == length) {
				if (start == 0) {
					return str;
				}

				return str.substring(start);
			}

			return str.substring(start, end);
		}

		return "";
	}

	/**
	 * trims the specified char off the provided string
	 * 
	 * @param str
	 * @param trimChar
	 * @return
	 * @author TelevisionNinja
	 */
	public static String trim_2_0(final String str, final char trimChar) {
		final int length = str.length();
		int start = 0,
				end = length;

		while (start < end) {
			if (str.charAt(end - 1) == trimChar) {
				end--;

				if (str.charAt(start) == trimChar) {
					start++;
				}
			}
			else {
				if (str.charAt(start) == trimChar) {
					start++;
				}
				else {
					if (end == length) {
						if (start == 0) {
							return str;
						}

						return str.substring(start);
					}

					return str.substring(start, end);
				}
			}
		}

		return "";
	}

	/**
	 * trims the specified char off the provided string
	 * 
	 * @param str
	 * @param trimChar
	 * @return
	 * @author TelevisionNinja
	 */
	public static String trim_2_1(final String str, final char trimChar) {
		final int length = str.length();
		int start = 0,
				end = length,
				initialS = length,
				initialE = 0;

		while (start != initialS || end != initialE) {
			initialS = start;
			initialE = end;

			if (str.charAt(end - 1) == trimChar) {
				end--;

				if (end == 0) {
					return "";
				}
			}

			if (str.charAt(start) == trimChar) {
				start++;
			}
		}

		if (end == length) {
			if (start == 0) {
				return str;
			}

			return str.substring(start);
		}

		return str.substring(start, end);
	}

	/**
	 * trims the specified char off the provided string
	 * 
	 * @param str
	 * @param trimChar
	 * @return
	 * @author TelevisionNinja
	 */
	public static String trim_2_2(final String str, final char trimChar) {
		final int length = str.length();
		int start = 0,
				end = length;

		for (int i = 1; start < end; i++) {
			final int initialS = start,
					initialE = end,
					newEnd = end - 1;

			if (str.charAt(newEnd) == trimChar) {
				end = newEnd;
			}

			if (str.charAt(start) == trimChar) {
				start = i;
			}

			if (start == initialS && end == initialE) {
				if (end == length) {
					if (start == 0) {
						return str;
					}

					return str.substring(start);
				}

				return str.substring(start, end);
			}
		}

		return "";
	}

	/**
	 * trims the specified char off the provided string
	 * 
	 * @param str
	 * @param trimChar
	 * @return
	 * @author TelevisionNinja
	 */
	public static String trim_2_3(final String str, final char trimChar) {
		final int length = str.length();
		int start = 0,
				end = length;

		while (start < end) {
			final int initialS = start,
					initialE = end;

			if (str.charAt(end - 1) == trimChar) {
				end--;
			}

			if (str.charAt(start) == trimChar) {
				start++;
			}

			if (start == initialS && end == initialE) {
				if (end == length) {
					if (start == 0) {
						return str;
					}

					return str.substring(start);
				}

				return str.substring(start, end);
			}
		}

		return "";
	}

	/**
	 * trims the specified char off the provided string
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	public static String trim_3(final String str, final char trimChar) {
		final int length = str.length();
		int end = length - 1;
		for (; end > 0; end--) {
			if (str.charAt(end) != trimChar) {
				break;
			}
		}

		end++;

		if (end == 0) {
			return "";
		}

		int start = 0;
		for (; start < end; start++) {
			if (str.charAt(start) != trimChar) {
				break;
			}
		}

		if (end == length) {
			if (start == 0) {
				return str;
			}

			return str.substring(start);
		}

		return str.substring(start, end);
	}

	/**
	 * trims the specified char off the provided string
	 * 
	 * @param str
	 * @param trimChar
	 * @return
	 * @author TelevisionNinja
	 */
	public static String trim_4(final String str, final char trimChar) {
		final int length = str.length();
		int start = 0,
				end = length - 1;

		while (start < end) {
			final boolean startWhitespace = str.charAt(start) == trimChar,
					endWhitespace = str.charAt(end) == trimChar;

			if (!startWhitespace && !endWhitespace) {
				break;
			}

			if (startWhitespace) {
				start++;
			}

			if (endWhitespace) {
				end--;
			}
		}

		end++;

		if (start < end) {
			if (end == length) {
				if (start == 0) {
					return str;
				}

				return str.substring(start);
			}

			return str.substring(start, end);
		}

		return "";
	}

	/**
	 * trims the specified char off the provided string
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	public static String trim_5(final String str, final char trimChar) {
		final int length = str.length();
		int end = length;
		while (end > 0) {
			if (str.charAt(end - 1) != trimChar) {
				break;
			}

			end--;
		}

		if (end == 0) {
			return "";
		}

		int start = 0;
		while (start < end) {
			if (str.charAt(start) != trimChar) {
				break;
			}

			start++;
		}

		if (end == length) {
			if (start == 0) {
				return str;
			}

			return str.substring(start);
		}

		return str.substring(start, end);
	}

	/**
	 * trims the specified char off the provided string
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	public static String trim_6(final String str, final char trimChar) {
		final int length = str.length();
		int end = length;
		for (; end > 0; end--) {
			if (str.charAt(end - 1) != trimChar) {
				break;
			}
		}

		if (end == 0) {
			return "";
		}

		int start = 0;
		for (; start < end; start++) {
			if (str.charAt(start) != trimChar) {
				break;
			}
		}

		if (end == length) {
			if (start == 0) {
				return str;
			}

			return str.substring(start);
		}

		return str.substring(start, end);
	}

	/**
	 * 
	 * @param word1
	 * @param word2
	 * @return
	 * 		str of chars of 2 strings that are alternated w/ each other
	 * @author TelevisionNinja
	 */
	public static String zipWords(final String word1, final String word2) {
		long length = 0;
		final long word1Length = word1.length(),
				word2Length = word2.length();

		if (word1Length == 0 || word2Length == 0) {
			return word1 + word2;
		}

		final StringBuilder zippedWord = new StringBuilder();

		if (word1Length < word2Length) {
			length = word2Length;
		}
		else {
			length = word1Length;
		}

		for (int x = 0; x < length; x++) {
			if (x == word1Length) {
				zippedWord.append(word2.substring(x));
			}
			else if (x == word2Length) {
				zippedWord.append(word1.substring(x));
			}
			else {
				zippedWord.append(word1.substring(x, x + 1) + word2.substring(x, x + 1));
			}
		}

		return zippedWord.toString();
	}
}