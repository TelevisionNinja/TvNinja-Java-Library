package televisionninja.lib.numberutils;

import televisionninja.lib.stringutils.StringUtils;

/**
 * @author TelevisionNinja
 *
 */
public class NumberUtils {
	/**
	 * Byte.toUnsignedInt() method
	 * 
	 * @param x
	 * @return
	 * @author TelevisionNinja
	 */
	public static int byteToUnsignedInt(final byte x) {
		return Byte.toUnsignedInt(x);
	}

	/**
	 * Character.getNumericValue() method
	 * 
	 * @param ch
	 * @return
	 * @author TelevisionNinja
	 */
	public static int charToInt_1(final char ch) {
		return Character.getNumericValue(ch);
	}

	/**
	 * char - '0' method
	 * 
	 * @param ch
	 * @return
	 * @author TelevisionNinja
	 */
	public static int charToInt_2(final char ch) {
		return ch - '0';
	}

	/**
	 * 
	 * @param num
	 * @return
	 * @author TelevisionNinja
	 */
	private static String formatStringNumber(final String num) {
		return num.replace("-", "").replace(".", "");
	}

	/**
	 * Math.pow() method
	 * 
	 * online method
	 * 
	 * @param number
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public static long getNthDigit(final long number, final long n) {
		return (long) ((number / Math.pow(10, n - 1)) % 10);
	}

	/**
	 * 
	 * @param number
	 * @param n
	 * @return
	 * 		Nth digit of a number that is a string
	 * @author TelevisionNinja
	 */
	public static long getNthDigit(final String number, final int n) {
		return Long.parseLong(StringUtils.reverse_3(formatStringNumber(number)).substring(n - 1, n));
	}

	/**
	 * cast to byte method
	 * 
	 * @param x
	 * @return
	 * @author TelevisionNinja
	 */
	public static byte intToByte(final int x) {
		return (byte) (x);
	}

	/**
	 * 
	 * @param bi
	 * @return
	 * @author TelevisionNinja
	 */
	public static String invertBits_1(final String bi) {
		final StringBuilder flipped = new StringBuilder();
		for (final char c : bi.toCharArray()) {
			if (c == '0') {
				flipped.append("1");
			}
			else {
				flipped.append("0");
			}
		}
		return flipped.toString();
	}

	/**
	 * online method
	 * 
	 * @param bi
	 * @return
	 * @author TelevisionNinja
	 */
	public static String invertBits_2(final String bi) {
		return bi.replaceAll("0", "x").replaceAll("1", "0").replaceAll("x", "1");
	}

	/**
	 * online method
	 * 
	 * @param bi
	 * @return
	 * @author TelevisionNinja
	 */
	public static String invertBits_3(final String bi) {
		final String flipped = Long.toBinaryString(~Long.parseLong(bi, 2));
		final int flippedLength = flipped.length();
		return flipped.substring(flippedLength - bi.length(), flippedLength);
	}

	/**
	 * online method
	 * 
	 * @param n
	 * @return
	 * 		- true if even
	 * 		- false if odd
	 * @author TelevisionNinja
	 */
	public static boolean isEven(final long n) {
		return (n & 1) == 0;
	}

	/**
	 * 
	 * @param number
	 * @return
	 * @author TelevisionNinja
	 */
	public static boolean isPalindrome(final long number) {
		if (number == reverse(number)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param number
	 * @return
	 * 		# of digits of a number that is a string
	 * @author TelevisionNinja
	 */
	public static long numOfDigits(final String number) {
		return formatStringNumber(number).length();
	}

	/**
	 * logarithm method
	 * only counts whole #'s
	 * 
	 * online method
	 * 
	 * @param number
	 * @return
	 * @author TelevisionNinja
	 */
	public static long numOfDigitsLog(long number) {
		if (number < 0) {
			number *= -1;
		}
		return (long) (Math.log10(number) + 1);
	}

	/**
	 * recursion method
	 * only counts whole #'s
	 * 
	 * online method
	 * 
	 * @param number
	 * @return
	 * @author TelevisionNinja
	 */
	public static long numOfDigitsRecursion(final long number) {
		if (number == 0) {
			return 0;
		}
		return 1 + numOfDigitsRecursion(number / 10);
	}

	/**
	 * String.length() method
	 * finds length of real & decimal #'s
	 * java sci notation problem
	 * 17 digits is max of any decimal
	 * 
	 * @param number
	 * @return
	 * @author TelevisionNinja
	 */
	public static long numOfDigitsString_1(final double number) {
		String num = Double.toString(number).replaceAll("-", "");
		if (num.contains("E")) {
			num = num.replace(".", "");
			final String[] arr = num.split("E");
			final long string1 = arr[0].length(),
					string2 = Long.parseLong(arr[1]) + 1;
			if (string2 > string1) {
				if (string1 == 0) {
					return string2 - 1;
				}
				return string2;
			}
			return string1;
		}
		final long digits = num.replace(".", "").length();
		if (num.substring(0, 2).equals("0.")) {
			return digits - 1;
		}
		return digits;
	}

	/**
	 * String.length() method
	 * finds length of real & decimal #'s
	 * java sci notation problem
	 * 17 digits is max of any decimal
	 * 
	 * @param number
	 * @return
	 * @author TelevisionNinja
	 */
	public static long numOfDigitsString_2(final double number) {
		final String stringNum = Double.toString(number),
				formattedNum = formatStringNumber(stringNum);
		if (formattedNum.contains("E")) {
			final String[] arr = formattedNum.split("E");
			final long string1 = arr[0].length(),
					string2 = Long.parseLong(arr[1]) + 1;
			if (string2 > string1) {
				return string2;
			}
			return string1;
		}
		final long digits = formattedNum.length();
		if (stringNum.substring(0, 2).equals("0.")) {
			return digits - 1;
		}
		return digits;
	}

	/**
	 * divide & conquer method
	 * 
	 * online method
	 * 
	 * @param num
	 * @return
	 * @author TelevisionNinja
	 */
	public static int numOfIntDigits(int num) {
		if (num < 0) {
			num *= -1;
		}
		else if (num < 100000) {
			// 5 or less
			if (num < 100) {
				// 1 or 2
				if (num < 10) {
					return 1;      //3
				}
				return 2;      //3
			}
			// 3
			else if (num < 1000) {
				return 3;      //3
			}
			// 4 or 5
			else if (num < 10000) {
				return 4;      //4
			}
			return 5;      //4
		}
		// 6 or more
		else if (num < 10000000) {
			// 6 or 7
			if (num < 1000000) {
				return 6;      //3
			}
			return 7;      //3
		}
		// 8
		else if (num < 100000000) {
			return 8;      //3
		}
		// 9 or 10
		else if (num < 1000000000) {
			return 9;      //4
		}
		return 10;      //4
	}

	/**
	 * 
	 * @param number
	 * @return
	 * @author TelevisionNinja
	 */
	public static long numOfSignificantDigits_1(final String number) {
		String num = formatStringNumber(number);
		final int length = num.length();
		for (int x = 0; x < length; x++) {
			if (num.charAt(x) != '0') {
				num = num.substring(x, length);

				if (!(number.contains("."))) {
					for (int y = num.length(); y > 0; y--) {
						if (num.charAt(y - 1) != '0') {
							num = num.substring(0, y);
							return num.length();
						}
					}
				}
				return num.length();
			}
		}
		return 0;
	}

	/**
	 * 
	 * @param number
	 * @return
	 * @author TelevisionNinja
	 */
	public static long numOfSignificantDigits_2(final String number) {
		final String num = StringUtils.stripLeading_4(formatStringNumber(number), "0");
		if (number.contains(".")) {
			return num.length();
		}
		return StringUtils.stripTrailing_3(num, "0").length();
	}

	/**
	 * textbook method
	 * 
	 * @param number
	 * @return
	 * @author TelevisionNinja
	 */
	public static long reverse(long number) {
		long reverse = 0;
		while (number != 0) {
			reverse = reverse * 10 + number % 10;
			number /= 10;
		}
		return reverse;
	}

	/**
	 * Integer.parseInt() method
	 * 
	 * @param s
	 * @return
	 * @author TelevisionNinja
	 */
	public static int stringToInt(final String s) {
		return Integer.parseInt(s);
	}

	/**
	 * online method
	 * 
	 * @param s
	 * @return
	 * @author TelevisionNinja
	 */
	public static int stringToIntFast_1(final String s) {
		int ival = 0,
				idx = 0,
				end;
		boolean sign = false;
		char ch;
		if (s == null || (end = s.length()) == 0 ||
				((ch = s.charAt(0)) < '0' || ch > '9')
				&& (!(sign = ch == '-') || ++idx == end || ((ch = s.charAt(idx)) < '0' || ch > '9'))) {
			throw new NumberFormatException(s);
		}

		for (;; ival *= 10) {
			ival += '0' - ch;
			if (++idx == end) {
				return sign ? ival : -ival;
			}
			if ((ch = s.charAt(idx)) < '0' || ch > '9') {
				throw new NumberFormatException(s);
			}
		}
	}

	/**
	 * 
	 * @param s
	 * @return
	 * @author TelevisionNinja
	 */
	public static int stringToIntFast_2(final String s) {
		int ival = 0,
				idx = 0;
		char ch = s.charAt(0);
		final boolean sign = ch == 45;
		ch = sign ? s.charAt(++idx) : ch;

		for (;; ival *= 10) {
			ival += ch - 48;
			if (++idx == s.length()) {
				return sign ? -ival : ival;
			}
			ch = s.charAt(idx);
		}
	}

	/**
	 * online method
	 * 
	 * @param s
	 * @return
	 * @author TelevisionNinja
	 */
	public static int stringToIntFast_3(final String s) {
		int num = 0;
		int sign = -1;
		final int len = s.length();
		final char ch = s.charAt(0);
		if (ch == '-') {
			sign = 1;
		}
		else {
			num = '0' - ch;
		}
		int i = 1;
		while (i < len) {
			num = num * 10 + '0' - s.charAt(i++);
		}
		return sign * num;
	}

	/**
	 * 
	 * @param s
	 * @return
	 * @author TelevisionNinja
	 */
	public static int stringToIntFast_4(final String s) {
		int num = 0,
				sign = 1,
				i = 1;
		final char ch = s.charAt(0);

		if (ch == 45) { /* the num 45 is the value of the char - */
			sign = -1;
		}
		else {
			num = ch - 48; /* the number 48 is the value of the char 0 */
		}

		while (i < s.length()) {
			num = num * 10 + s.charAt(i++) - 48;
		}

		return sign * num;
	}

	/**
	 * Integer.valueOf() method
	 * 
	 * @param s
	 * @return
	 * @author TelevisionNinja
	 */
	public static Integer stringToIntObj(final String s) {
		return Integer.valueOf(s);
	}
}