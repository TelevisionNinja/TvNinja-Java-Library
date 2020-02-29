package televisionninja.lib.listutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import televisionninja.lib.stringutils.StringUtils;

/**
 * @author TelevisionNinja
 *
 */
public class ListUtils {
	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static <T> List<T> arrObjectToList_1(final T[] arr) {
		final List<T> list = new ArrayList<>();
		for (final T obj : arr) {
			list.add(obj);
		}
		return list;
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static <T> List<T> arrObjectToList_2(final T[] arr) {
		return new ArrayList<>(Arrays.asList(arr));
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static <T> List<T> arrObjectToList_3(final T[] arr) {
		return new ArrayList<>(List.of(arr));
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<Boolean> arrPrimitiveToList(final boolean[] arr) {
		final List<Boolean> list = new ArrayList<>();
		for (final boolean b : arr) {
			list.add(b);
		}
		return list;
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<Byte> arrPrimitiveToList(final byte[] arr) {
		final List<Byte> list = new ArrayList<>();
		for (final byte b : arr) {
			list.add(b);
		}
		return list;
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<Character> arrPrimitiveToList(final char[] arr) {
		final List<Character> list = new ArrayList<>();
		for (final char c : arr) {
			list.add(c);
		}
		return list;
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<Double> arrPrimitiveToList(final double[] arr) {
		final List<Double> list = new ArrayList<>();
		for (final double d : arr) {
			list.add(d);
		}
		return list;
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<Float> arrPrimitiveToList(final float[] arr) {
		final List<Float> list = new ArrayList<>();
		for (final float f : arr) {
			list.add(f);
		}
		return list;
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<Integer> arrPrimitiveToList(final int[] arr) {
		final List<Integer> list = new ArrayList<>();
		for (final int i : arr) {
			list.add(i);
		}
		return list;
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<Long> arrPrimitiveToList(final long[] arr) {
		final List<Long> list = new ArrayList<>();
		for (final long l : arr) {
			list.add(l);
		}
		return list;
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<Short> arrPrimitiveToList(final short[] arr) {
		final List<Short> list = new ArrayList<>();
		for (final short s : arr) {
			list.add(s);
		}
		return list;
	}

	/**
	 * 
	 * @param <E>
	 * @param list
	 * @param index
	 * @return
	 * @author TelevisionNinja
	 */
	public static <E> String circularPrintList(final List<E> list, final int index) {
		final StringBuilder str = new StringBuilder();
		final int size = list.size();
		str.append("[");
		for (int i = index; i < size + index - 1; i++) {
			str.append(list.get(i % size) + ", ");
		}
		str.append(list.get((size + index - 1) % size) + "]");
		return str.toString();
	}

	/**
	 * 
	 * @param list
	 * 		list to be formatted
	 * @param character
	 * 		char to append to the index
	 * @return
	 * 		list with all indexes of equal length
	 * @author TelevisionNinja
	 */
	public static List<String> formatList_1(final List<String> list, final char character) {
		final long size = getMaxStringLength_1(list);
		for (final String index : list) {
			final long indexSize = index.length();
			if (indexSize < size) {
				final StringBuilder sized = new StringBuilder();
				for (long y = 0; y < (size - indexSize); y++) {
					sized.append(character);
				}
				list.set(list.indexOf(index), sized.toString() + index);
			}
		}

		return list;
	}

	/**
	 * 
	 * @param list
	 * @param character
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<String> formatList_2(final List<String> list, final char character) {
		final long size = getMaxStringLength_1(list);
		for (final String index : list) {
			final long indexSize = index.length();
			if (indexSize < size) {
				list.set(list.indexOf(index), StringUtils.addLeadingToString_2(index, character, size));
			}
		}

		return list;
	}

	/**
	 * 
	 * @param list
	 * @param character
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<String> formatList_3(final List<String> list, final char character) {
		final long size = getMaxStringLength_2(list);
		for (final String index : list) {
			final long indexSize = index.length();
			if (indexSize < size) {
				list.set(list.indexOf(index), StringUtils.addLeadingToString_2(index, character, size));
			}
		}

		return list;
	}

	/**
	 * loop method
	 * 
	 * @param list
	 * @return
	 * 		max string
	 * @author TelevisionNinja
	 */
	public static String getMaxString_1(final List<String> list) {
		long size = 0;
		int index = 0;
		for (int x = 0; x < list.size(); x++) {
			final long temp = list.get(x).length();
			if (size < temp) {
				size = temp;
				index = x;
			}
		}
		return list.get(index);
	}

	/**
	 * Collections.max() method
	 * 
	 * @param list
	 * @return
	 * @author TelevisionNinja
	 */
	public static String getMaxString_2(final List<String> list) {
		return Collections.max(list, Comparator.comparing(s -> s.length()));
	}

	/**
	 * loop method
	 * 
	 * @param list
	 * @return
	 * 		max str length
	 * @author TelevisionNinja
	 */
	public static long getMaxStringLength_1(final List<String> list) {
		long size = 0;
		for (int x = 0; x < list.size(); x++) {
			final int temp = list.get(x).length();
			if (size < temp) {
				size = temp;
			}
		}
		return size;
	}

	/**
	 * Collections.max().length() method
	 * 
	 * @param list
	 * @return
	 * @author TelevisionNinja
	 */
	public static long getMaxStringLength_2(final List<String> list) {
		return Collections.max(list, Comparator.comparing(s -> s.length())).length();
	}

	/**
	 * Collections.min() method
	 * 
	 * @param list
	 * @return
	 * @author TelevisionNinja
	 */
	public static String getMinString(final List<String> list) {
		return Collections.min(list, Comparator.comparing(s -> s.length()));
	}

	/**
	 * 
	 * @param list
	 * @return
	 * 		returns contents of list put together in a str using a loop
	 * @author TelevisionNinja
	 */
	public static String listStringToString_1(final List<String> list) {
		final StringBuilder str = new StringBuilder();
		for(int x = 0; x < list.size(); x++) {
			str.append(list.get(x));
		}
		return str.toString();
	}

	/**
	 * String.join()
	 * 
	 * @param list
	 * @param separator
	 * @return
	 * @author TelevisionNinja
	 */
	public static String listStringToString_2(final List<String> list, final String separator) {
		return String.join(separator, list);
	}

	/**
	 * 
	 * @param list
	 * @return
	 * @author TelevisionNinja
	 */
	public static <T> String listToString(final List<T> list) {
		final StringBuilder str = new StringBuilder();
		for(final T thing : list) {
			str.append(thing);
		}
		return str.toString();
	}

	/**
	 * 
	 * @param objects
	 * 		- the format of the list is:
	 * 				object separator weight
	 * @param separator
	 * @param capacity
	 * 		- if < 0 then it will return an empty list
	 * @param visibility
	 * 		- true: shows all items
	 * 		- false; shows only the items needed in the combination
	 * @return
	 * 		- list of how many of each object will provide the optimal combination to fill up the capacity
	 * 		- format of the list is
	 * 				object separator quantity
	 * @author TelevisionNinja
	 */
	public static List<String> optimalCombination(final List<String> objects, final String separator, double capacity, final boolean visibility) {
		final List<String> combination = new ArrayList<>();
		if (capacity < 0) {
			return combination;
		}
		final TreeMap<Double, String> treeMap = new TreeMap<>();
		for (final String str : objects) {
			final int index = str.lastIndexOf(separator);
			treeMap.put(Double.parseDouble(str.substring(index + 1)), str.substring(0, index));
		}
		for (final Entry<Double, String> entry : treeMap.descendingMap().entrySet()) {
			final Double weight = entry.getKey();
			if (weight <= 0) {
				combination.add(entry.getValue() + separator + "infinity");
			}
			else {
				final double quantity = Math.floor(capacity / weight);
				if (quantity > 0 || visibility) {
					combination.add(entry.getValue() + separator + (quantity));
				}
			}
			capacity %= weight;
		}
		return combination;
	}

	/**
	 * 
	 * @param str
	 * @param length
	 * @return
	 * 		returns list of substrings w/ the length of the length param
	 * @author TelevisionNinja
	 */
	public static List<String> stringToList(final String str, final int length) {
		final List<String> list = new ArrayList<>();
		final int strLen = str.length();
		for (int x = 0; x < strLen; x += length) {
			if (x + length > strLen) {
				list.add(str.substring(x, strLen));
			}
			else {
				list.add(str.substring(x, x + length));
			}
		}
		return list;
	}

	/**
	 * enhanced for loop
	 * 
	 * @param string
	 * @param separator
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<String> stringToList_1(final String string, final String separator) {
		final List<String> list = new ArrayList<>();
		final String[] arr = string.split(separator);
		for (final String str : arr) {
			list.add(str);
		}
		return list;
	}

	/**
	 * Arrays.asList(str.split())
	 * 
	 * @param str
	 * @param separator
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<String> stringToList_2(final String str, final String separator) {
		return new ArrayList<>(Arrays.asList(str.split(separator)));
	}

	/**
	 * List.of()
	 * 
	 * @param str
	 * @param separator
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<String> stringToList_3(final String str, final String separator) {
		return new ArrayList<>(List.of(str.split(separator)));
	}

	/**
	 * 
	 * @param str
	 * @param separator
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<String> stringToList_4(final String str, final String separator) {
		return arrObjectToList_3(str.split(separator));
	}
}