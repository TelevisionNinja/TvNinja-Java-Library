/**
 * 
 */
package televisionninja.lib.listutils.sortutils;

/**
 * @author TelevisionNinja
 *
 */
public class MergeSort {
	/**
	 * online method
	 * 
	 * @param arr
	 * @param l
	 * @param m
	 * @param r
	 * @author TelevisionNinja
	 */
	private static void merge_1(final int arr[], final int l, final int m, final int r) {
		final int n1 = m - l + 1,
				n2 = r - m;
		int i = 0,
				j = 0,
				k = l;
		final int L[] = new int[n1],
				R[] = new int[n2];
		for (int a = 0; a < n1; ++a) {
			L[a] = arr[l + a];
		}
		for (int b = 0; b < n2; ++b) {
			R[b] = arr[m + 1 + b];
		}
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			}
			else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	/**
	 * online method
	 * 
	 * @param a
	 * @param l
	 * @param r
	 * @param left
	 * @param right
	 * @author TelevisionNinja
	 */
	private static void merge_2(final int[] a, final int[] l, final int[] r, final int left, final int right) {
		int i = 0,
				j = 0,
				k = 0;
		while (i < left && j < right) {
			if (l[i] <= r[j]) {
				a[k++] = l[i++];
			}
			else {
				a[k++] = r[j++];
			}
		}
		while (i < left) {
			a[k++] = l[i++];
		}
		while (j < right) {
			a[k++] = r[j++];
		}
	}

	/**
	 * textbook method
	 * 
	 * @param list1
	 * @param list2
	 * @param temp
	 * @author TelevisionNinja
	 */
	private static void merge_3(final int[] list1, final int[] list2, final int[] temp) {
		int current1 = 0,
				current2 = 0,
				current3 = 0;
		while (current1 < list1.length && current2 < list2.length) {
			if (list1[current1] < list2[current2]) {
				temp[current3++] = list1[current1++];
			}
			else {
				temp[current3++] = list2[current2++];
			}
		}
		while (current1 < list1.length) {
			temp[current3++] = list1[current1++];
		}
		while (current2 < list2.length) {
			temp[current3++] = list2[current2++];
		}
	}

	/**
	 * online method
	 * 
	 * @param arr
	 * @author TelevisionNinja
	 */
	public static void mergeSort_1(final int arr[]) {
		mergeSort_1(arr, 0, arr.length - 1);
	}

	/**
	 * online method
	 * 
	 * @param arr
	 * @param l
	 * @param r
	 * @author TelevisionNinja
	 */
	private static void mergeSort_1(final int arr[], final int l, final int r) {
		if (l < r) {
			final int m = (l + r) / 2;
			mergeSort_1(arr, l, m);
			mergeSort_1(arr , m + 1, r);
			merge_1(arr, l, m, r);
		}
	}

	/**
	 * online method
	 * 
	 * @param a
	 * @author TelevisionNinja
	 */
	public static void mergeSort_2(final int[] a) {
		mergeSort_2(a, a.length);
	}

	/**
	 * online method
	 * 
	 * @param a
	 * @param n
	 * @author TelevisionNinja
	 */
	private static void mergeSort_2(final int[] a, final int n) {
		if (n < 2) {
			return;
		}
		final int mid = n / 2;
		final int[] l = new int[mid],
				r = new int[n - mid];

		for (int i = 0; i < mid; i++) {
			l[i] = a[i];
		}
		for (int i = mid; i < n; i++) {
			r[i - mid] = a[i];
		}
		mergeSort_2(l, mid);
		mergeSort_2(r, n - mid);

		merge_2(a, l, r, mid, n - mid);
	}

	/**
	 * textbook method
	 * 
	 * @param list
	 * @author TelevisionNinja
	 */
	public static void mergeSort_3(final int[] list) {
		final int length = list.length;
		if (length > 1) {
			final int secondHalfLength = length - length / 2;
			final int[] firstHalf = new int[length / 2],
					secondHalf = new int[secondHalfLength];

			System.arraycopy(list, 0, firstHalf, 0, length / 2);
			mergeSort_3(firstHalf);

			System.arraycopy(list, length / 2,
					secondHalf, 0, secondHalfLength);
			mergeSort_3(secondHalf);

			merge_3(firstHalf, secondHalf, list);
		}
	}
}