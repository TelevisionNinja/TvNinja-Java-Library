package televisionninja.lib.listutils;

import java.util.Arrays;
import java.util.List;

/**
 * @author TelevisionNinja
 *
 */
public class ArrayUtils {
	/**
	 * for loop method
	 * 
	 * @param arr
	 * @param separator
	 * @return
	 * @author TelevisionNinja
	 */
	public static <T> String arrToString_1(final T[] arr, final String separator) {
		final StringBuilder string = new StringBuilder();
		final long length = arr.length;
		for (int x = 0; x < length; x++) {
			string.append(arr[x]);
			if (x < length - 1) {
				string.append(separator);
			}
		}
		return string.toString();
	}

	/**
	 * enhanced for loop
	 * 
	 * @param arr
	 * @param separator
	 * @return
	 * @author TelevisionNinja
	 */
	public static <T> String arrToString_2(final T[] arr, final String separator) {
		final StringBuilder string = new StringBuilder();
		long count = 0;
		final long length = arr.length;
		for (final T index : arr) {
			string.append(index);
			count++;
			if (count != length) {
				string.append(separator);
			}
		}
		return string.toString();
	}

	/**
	 * Arrays.toString()
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static <T> String arrToString_3(final T[] arr) {
		return Arrays.toString(arr);
	}

	/**
	 * 
	 * @param <E>
	 * @param list
	 * @param index
	 * @return
	 * @author TelevisionNinja
	 */
	public static String circularPrintArray(final int[] list, final int index) {
		final StringBuilder str = new StringBuilder();
		str.append("[");
		for (int i = index; i < list.length + index - 1; i++) {
			str.append(list[i % list.length] + ", ");
		}
		str.append(list[(list.length + index - 1) % list.length] + "]");
		return str.toString();
	}

	/**
	 * 
	 * @param arr
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public static boolean contains(final long[] arr, final long n) {
		for (final long num : arr) {
			if (num == n) {
				return true;
			}
		}
		return false;
	}

	/**
	 * for loop
	 * 
	 * @param list
	 * @return
	 * @author TelevisionNinja
	 */
	public static String getMaxString_1(final String[] list) {
		long size = 0;
		int index = 0;
		for (int x = 0; x < list.length; x++) {
			final long temp = list[x].length();
			if (size < temp) {
				size = temp;
				index = x;
			}
		}
		return list[index];
	}

	/**
	 * enhanced for loop
	 * 
	 * @param list
	 * @return
	 * @author TelevisionNinja
	 */
	public static String getMaxString_2(final String[] list) {
		long size = 0;
		String shortest = "";
		for (final String str : list) {
			final long temp = str.length();
			if (size < temp) {
				size = temp;
				shortest = str;
			}
		}
		return shortest;
	}

	/**
	 * for loop
	 * 
	 * @param list
	 * @return
	 * @author TelevisionNinja
	 */
	public static long getMaxStringLength(final String[] list) {
		long size = 0;
		for (final String element : list) {
			final long temp = element.length();
			if (size < temp) {
				size = temp;
			}
		}
		return size;
	}

	/**
	 * for loop
	 * 
	 * @param list
	 * @return
	 * @author TelevisionNinja
	 */
	public static String getMinString_1(final String[] list) {
		long size = list[0].length();
		int index = 0;
		for (int x = 0; x < list.length; x++) {
			final long temp = list[x].length();
			if (size > temp) {
				size = temp;
				index = x;
			}
		}
		return list[index];
	}

	/**
	 * enhanced for loop
	 * 
	 * @param list
	 * @return
	 * @author TelevisionNinja
	 */
	public static String getMinString_2(final String[] list) {
		long size = list[0].length();
		String shortest = "";
		for (final String str : list) {
			final long temp = str.length();
			if (size > temp) {
				size = temp;
				shortest = str;
			}
		}
		return shortest;
	}

	/**
	 * 
	 * @param list
	 * @return
	 * @author TelevisionNinja
	 */
	public static boolean[] listBooleanToArr(final List<Boolean> list) {
		final int length = list.size();
		final boolean[] arr = new boolean[length];
		for (int x = 0; x < length; x++) {
			arr[x] = list.get(x);
		}
		return arr;
	}

	/**
	 * 
	 * @param list
	 * @return
	 * @author TelevisionNinja
	 */
	public static byte[] listByteToArr(final List<Byte> list) {
		final int length = list.size();
		final byte[] arr = new byte[length];
		for (int x = 0; x < length; x++) {
			arr[x] = list.get(x);
		}
		return arr;
	}

	/**
	 * 
	 * @param list
	 * @return
	 * @author TelevisionNinja
	 */
	public static char[] listCharToArr(final List<Character> list) {
		final int length = list.size();
		final char[] arr = new char[length];
		for (int x = 0; x < length; x++) {
			arr[x] = list.get(x);
		}
		return arr;
	}

	/**
	 * 
	 * @param list
	 * @return
	 * @author TelevisionNinja
	 */
	public static double[] listDoubleToArr(final List<Double> list) {
		final int length = list.size();
		final double[] arr = new double[length];
		for (int x = 0; x < length; x++) {
			arr[x] = list.get(x);
		}
		return arr;
	}

	/**
	 * 
	 * @param list
	 * @return
	 * @author TelevisionNinja
	 */
	public static float[] listFloatToArr(final List<Float> list) {
		final int length = list.size();
		final float[] arr = new float[length];
		for (int x = 0; x < length; x++) {
			arr[x] = list.get(x);
		}
		return arr;
	}

	/**
	 * 
	 * @param list
	 * @return
	 * @author TelevisionNinja
	 */
	public static int[] listIntegerToArr(final List<Integer> list) {
		final int length = list.size();
		final int[] arr = new int[length];
		for (int x = 0; x < length; x++) {
			arr[x] = list.get(x);
		}
		return arr;
	}

	/**
	 * 
	 * @param list
	 * @return
	 * @author TelevisionNinja
	 */
	public static long[] listLongToArr(final List<Long> list) {
		final int length = list.size();
		final long[] arr = new long[length];
		for (int x = 0; x < length; x++) {
			arr[x] = list.get(x);
		}
		return arr;
	}

	/**
	 * 
	 * @param list
	 * @return
	 * @author TelevisionNinja
	 */
	public static short[] listShortToArr(final List<Short> list) {
		final int length = list.size();
		final short[] arr = new short[length];
		for (int x = 0; x < length; x++) {
			arr[x] = list.get(x);
		}
		return arr;
	}

	/**
	 * 
	 * @param list
	 * @return
	 * @author TelevisionNinja
	 */
	public static Object[] listToArr(final List<Object> list) {
		return list.toArray();
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static String[] longArrToStrArr(final long[] arr) {
		final String[] strings = new String[arr.length];

		for (int x = 0; x < arr.length; x++) {
			strings[x] = String.valueOf(arr[x]);
		}

		return strings;
	}

	/**
	 * online method
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static <E> E[] reverse(final E[] arr) {
		for (int i = 0; i < arr.length / 2; i++) {
			final E temp = arr[i];
			arr[i] = arr[arr.length - i - 1];
			arr[arr.length - i - 1] = temp;
		}
		return arr;
	}

	/**
	 * online method
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static int[] reverse(final int[] arr) {
		for (int i = 0; i < arr.length / 2; i++) {
			final int temp = arr[i];
			arr[i] = arr[arr.length - i - 1];
			arr[arr.length - i - 1] = temp;
		}
		return arr;
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static long[] strArrToLongArr(final String[] arr) {
		final long[] longs = new long[arr.length];

		for (int x = 0; x < arr.length; x++) {
			longs[x] = Long.parseLong(arr[x]);
		}

		return longs;
	}

	/**
	 * String.join() method
	 * 
	 * @param arr
	 * @param separator
	 * @return
	 * @author TelevisionNinja
	 */
	public static String strArrToString(final String[] arr, final String separator) {
		return String.join(separator, arr);
	}

	/**
	 * String.split()
	 * 
	 * @param str
	 * @param separator
	 * @return
	 * @author TelevisionNinja
	 */
	public static String[] strToArr(final String str, final String separator) {
		return str.split(separator);
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static double sum_1(final double[] arr) {
		double sum = 0d;
		for (final double num : arr) {
			sum += num;
		}
		return sum;
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static double sum_2(final double[] arr) {
		return Arrays.stream(arr).sum();
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static double sumOfRange(final double[] arr, final int beginIndex, final int endIndex) {
		return Arrays.stream(arr, beginIndex, endIndex).sum();
	}
}