package televisionninja.lib.listutils.sortutils;

import java.util.List;

/**
 * @author TelevisionNinja
 *
 */
public class QuickSort {
	/**
	 * online method
	 * 
	 * @param list
	 * @param first
	 * @param last
	 * @return
	 * @author TelevisionNinja
	 */
	private static int partition_2(final List<Double> list, final int first, final int last) {
		final Double pivot = list.get(first);
		int low = first + 1,
				high = last;
		while (high > low) {
			while (low <= high && list.get(low) <= pivot) {
				low++;
			}
			while (low <= high && list.get(high) > pivot) {
				high--;
			}
			if (high > low) {
				final Double temp = list.get(high);
				list.set(high, list.get(low));
				list.set(low, temp);
			}
		}
		while (high > first && list.get(high) >= pivot) {
			high--;
		}
		final double listHigh = list.get(high);
		if (pivot > listHigh) {
			list.set(first, listHigh);
			list.set(high, pivot);
			return high;
		}
		return first;
	}

	/**
	 * textbook method
	 * 
	 * @param list
	 * @param begin
	 * @param end
	 * @return
	 * @author TelevisionNinja
	 */
	private static int partition_3(final List<Double> list, final int begin, final int end) {
		final Double pivot = list.get(end);
		int i = begin - 1;
		Double swapTemp = 0d;
		for (int j = begin; j < end; j++) {
			if (list.get(j) <= pivot) {
				i++;
				swapTemp = list.get(i);
				list.set(i, list.get(j));
				list.set(j, swapTemp);
			}
		}
		swapTemp = list.get(++i);
		list.set(i, list.get(end));
		list.set(end, swapTemp);
		return i;
	}

	/**
	 * 2 total methods
	 * 3 loops
	 * 
	 * online method
	 * 
	 * @param list
	 * @author TelevisionNinja
	 */
	public static void quickSort_1(final List<Double> list) {
		quickSort_1(list, 0, list.size() - 1);
	}

	/**
	 * online method
	 * 
	 * @param list
	 * @param low
	 * @param high
	 * @author TelevisionNinja
	 */
	public static void quickSort_1(final List<Double> list, final int low, final int high) {
		if (!(list == null || high + 1 == 0 || low >= high)) {
			final double pivot = list.get(low + (high - low) / 2);
			int i = low, j = high;
			while (i <= j) {
				while (list.get(i) < pivot) {
					i++;
				}
				while (list.get(j) > pivot) {
					j--;
				}
				if (i <= j) {
					final double temp = list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
					i++;
					j--;
				}
			}
			if (low < j) {
				quickSort_1(list, low, j);
			}
			if (high > i) {
				quickSort_1(list, i, high);
			}
		}
	}

	/**
	 * 3 total methods
	 * 4 loops
	 * 
	 * online method
	 * 
	 * @param list
	 * @author TelevisionNinja
	 */
	public static void quickSort_2(final List<Double> list) {
		quickSort_2(list, 0, list.size() - 1);
	}

	/**
	 * online method
	 * 
	 * @param list
	 * @param first
	 * @param last
	 * @author TelevisionNinja
	 */
	public static void quickSort_2(final List<Double> list, final int first, final int last) {
		if (last > first) {
			final int pivotIndex = partition_2(list, first, last);
			quickSort_2(list, first, pivotIndex - 1);
			quickSort_2(list, pivotIndex + 1, last);
		}
	}

	/**
	 * 3 total methods
	 * 1 loop
	 * 
	 * textbook method
	 * 
	 * @param list
	 * @author TelevisionNinja
	 */
	public static void quickSort_3(final List<Double> list) {
		quickSort_3(list, 0, list.size() - 1);
	}

	/**
	 * textbook method
	 * 
	 * @param list
	 * @param begin
	 * @param end
	 * @author TelevisionNinja
	 */
	public static void quickSort_3(final List<Double> list, final int begin, final int end) {
		if (begin < end) {
			int partitionIndex = partition_3(list, begin, end);
			quickSort_3(list, begin, --partitionIndex);
			quickSort_3(list, ++partitionIndex, end);
		}
	}
}