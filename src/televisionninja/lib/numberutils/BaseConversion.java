package televisionninja.lib.numberutils;

import java.util.ArrayList;
import java.util.List;

import televisionninja.lib.mathutils.MathUtils;

/**
 * @author TelevisionNinja
 *
 */
public class BaseConversion {
	/**
	 * 
	 * @param num
	 * @return
	 * @author TelevisionNinja
	 */
	public static long[] baseToArr_1(String num) {
		num = num.toUpperCase();
		final int length = num.length();
		final long[] list = new long[length];
		final char[] chars = num.toCharArray();
		for (int x = 0; x < length; x++) {
			final char v = chars[x];
			if (v >= 65) {
				list[x] = v - 55;
			}
			else {
				list[x] = v - 48;
			}
		}
		return list;
	}

	/**
	 * 
	 * @param num
	 * @return
	 * @author TelevisionNinja
	 */
	public static long[] baseToArr_2(String num) {
		num = num.toUpperCase();
		final long[] list = new long[num.length()];
		int x = 0;
		for (final char c : num.toCharArray()) {
			if (c >= 65) {
				list[x] = c - 55;
			}
			else {
				list[x] = c - 48;
			}
			x++;
		}
		return list;
	}

	/**
	 * 
	 * @param num
	 * @param base1
	 * @param base2
	 * @return
	 * @author TelevisionNinja
	 */
	public static String baseToBase_1(final String num, final long base1, final long base2) {
		return BaseConversion.decToBase_1(BaseConversion.baseToDec_2(num, base1), base2);
	}

	/**
	 * 
	 * @param num
	 * @param base1
	 * @param base2
	 * @return
	 * @author TelevisionNinja
	 */
	public static String baseToBase_2(final String num, final long base1, final long base2) {
		return BaseConversion.decToBase_2(BaseConversion.baseToDec_2(num, base1), base2);
	}

	/**
	 * 
	 * @param num
	 * @param base1
	 * @param base2
	 * @return
	 * @author TelevisionNinja
	 */
	public static String baseToBase_3(final String num, final long base1, final long base2) {
		return BaseConversion.decToBase_2(BaseConversion.baseToDec_3(num, base1), base2);
	}

	/**
	 * while loop method
	 * can't do bases where numbers are letters
	 * 
	 * @param num
	 * @param base
	 * @return
	 * @author TelevisionNinja
	 */
	public static long baseToDec_1(long num, final long base) {
		long total = 0,
				n = 0;
		while (num > 0) {
			total += Math.pow(base, n++) * (num % 10);
			num /= 10;
		}
		return total;
	}

	/**
	 * enhanced for loop method
	 * base to list
	 * 
	 * @param str
	 * 		< 64 bit
	 * @param base
	 * @return
	 * @author TelevisionNinja
	 */
	public static long baseToDec_2(final String str, final long base) {
		final List<Long> num = baseToList_1(str);
		long total = 0,
				n = num.size() - 1;
		for (final long value : num) {
			total += Math.pow(base, n--) * value;
		}
		return total;
	}

	/**
	 * enhanced for loop method
	 * base to list
	 * 
	 * @param str
	 * 		< 64 bit
	 * @param base
	 * @return
	 * @author TelevisionNinja
	 */
	public static long baseToDec_3(final String str, final long base) {
		long total = 0,
				n = str.length() - 1;
		for (final long value : baseToList_2(str)) {
			total += Math.pow(base, n--) * value;
		}
		return total;
	}

	/**
	 * enhanced for loop method
	 * base to array
	 * 
	 * @param str
	 * 		< 64 bit
	 * @param base
	 * @return
	 * @author TelevisionNinja
	 */
	public static long baseToDec_4(final String str, final long base) {
		long total = 0,
				n = str.length() - 1;
		for (final long value : baseToArr_1(str)) {
			total += Math.pow(base, n--) * value;
		}
		return total;
	}

	/**
	 * enhanced for loop method
	 * base to array
	 * 
	 * @param str
	 * 		< 64 bit
	 * @param base
	 * @return
	 * @author TelevisionNinja
	 */
	public static long baseToDec_5(final String str, final long base) {
		long total = 0,
				n = str.length() - 1;
		for (final long value : baseToArr_2(str)) {
			total += Math.pow(base, n--) * value;
		}
		return total;
	}

	/**
	 * Long.parseInt()
	 * 
	 * @param num
	 * 		32 bit
	 * @param base
	 * @return
	 * @author TelevisionNinja
	 */
	public static long baseToDec_6(final String num, final int base) {
		return Long.parseLong(num, base);
	}

	/**
	 * 
	 * @param num
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<Long> baseToList_1(final String num) {
		final String formattedNum = num.toUpperCase();
		final List<Long> list = new ArrayList<>();
		for (int x = 0; x < formattedNum.length(); x++) {
			final long value = formattedNum.charAt(x);
			if (value >= 65) {
				list.add(value - 55);
			}
			else {
				list.add(value - 48);
			}
		}
		return list;
	}

	/**
	 * 
	 * @param num
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<Long> baseToList_2(String num) {
		num = num.toUpperCase();
		final List<Long> list = new ArrayList<>();
		for (final char v : num.toCharArray()) {
			if (v >= 65) {
				list.add((long) v - 55);
			}
			else {
				list.add((long) v - 48);
			}
		}
		return list;
	}

	/**
	 * while loop method
	 * 
	 * @param num
	 * @param base
	 * @return
	 * 		Integer list of a dec converted to a base
	 * @author TelevisionNinja
	 */
	public static String decToBase_1(long num, final long base) {
		long n = MathUtils.largestPower_3(num, base);

		final List<Long> list = new ArrayList<>();

		while (n >= 0) {
			final int baseToAPower = (int) Math.pow(base, n);
			if (n == MathUtils.largestPower_3(num, base)) {
				list.add(num / baseToAPower);
			}
			else {
				list.add(0L);
			}
			num -= baseToAPower * (num / baseToAPower);
			--n;
		}
		return listToBase_1(list);
	}

	/**
	 * max:
	 * 		bits: 54
	 * 		dec: 18014398509481982
	 * 		bi; 111111111111111111111111111111111111111111111111111110
	 * 
	 * @param num
	 * @param base
	 * @return
	 * @author TelevisionNinja
	 */
	public static String decToBase_2(long num, final long base) {
		long n = MathUtils.largestPower_3(num, base);

		final List<Long> list = new ArrayList<>();

		while (n >= 0) {
			final long baseToAPower = (long) Math.pow(base, n);
			if (n == MathUtils.largestPower_3(num, base)) {
				list.add(num / baseToAPower);
			}
			else {
				list.add(0L);
			}
			num -= baseToAPower * (num / baseToAPower);
			--n;
		}
		return listToBase_2(list);
	}

	/**
	 * Integer.toBinaryString()
	 * 
	 * @param num
	 * @return
	 * @author TelevisionNinja
	 */
	public static String decToBinary(final long num) {
		return Long.toBinaryString(num);
	}

	/**
	 * Integer.toHexString()
	 * 
	 * @param num
	 * @return
	 * @author TelevisionNinja
	 */
	public static String decToHex(final long num) {
		return Long.toHexString(num);
	}

	/**
	 * Integer.toOctalString()
	 * 
	 * @param num
	 * @return
	 * @author TelevisionNinja
	 */
	public static String decToOctal(final long num) {
		return Long.toOctalString(num);
	}

	/**
	 * 
	 * @param num
	 * @return
	 * @author TelevisionNinja
	 */
	public static String listToBase_1(final List<Long> num) {
		final StringBuilder str = new StringBuilder();
		for (int x = 0; x < num.size(); x++) {
			final long value = num.get(x);
			if (value > 9) {
				str.append((char) (value + 55));
			}
			else {
				str.append((char) (value + 48));
			}
		}
		return str.toString();
	}

	/**
	 * 
	 * @param num
	 * @return
	 * @author TelevisionNinja
	 */
	public static String listToBase_2(final List<Long> num) {
		final StringBuilder str = new StringBuilder();
		for (final long value : num) {
			if (value > 9) {
				str.append((char) (value + 55));
			}
			else {
				str.append((char) (value + 48));
			}
		}
		return str.toString();
	}
}