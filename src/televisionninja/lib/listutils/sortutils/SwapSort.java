package televisionninja.lib.listutils.sortutils;

import java.util.List;

/**
 * @author TelevisionNinja
 *
 */
public class SwapSort {

	/**
	 * textbook method
	 * 
	 * @param list
	 * @author TelevisionNinja
	 */
	public static void swapSort(final List<Double> list) {
		double temp,
		count = 0;
		for (@SuppressWarnings("unused") final Double loop : list) {
			for (int index = 0; index < list.size() - 1; index++) {
				if (list.get(index) > list.get(index + 1)) {
					temp = list.get(index);
					list.set(index, list.get(index + 1));
					list.set(index + 1, temp);
					count = 0;
				}
				else {
					count++;
				}
			}
			if (count == list.size()) {
				break;
			}
		}
	}
}