/**
 * 
 */
package televisionninja.lib.mathutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import televisionninja.lib.listutils.ArrayUtils;

/**
 * @author TelevisionNinja
 *
 */
public class Statistics {
	/**
	 * lowerHalf() method
	 * 
	 * @param set
	 * @return
	 * @author TelevisionNinja
	 */
	public static double firstQuartile_1(final double[] set) {
		return median(lowerHalf(set));
	}

	/**
	 * percentile() method
	 * 
	 * @param set
	 * @return
	 * @author TelevisionNinja
	 */
	public static double firstQuartile_2(final double[] set) {
		return percentile(set, 25d);
	}

	/**
	 * thirdQuartile1()
	 * firstQuartile1()
	 * 
	 * @param set
	 * @return
	 * @author TelevisionNinja
	 */
	public static double interquartileRange_1(final double[] set) {
		return thirdQuartile_1(set) - firstQuartile_1(set);
	}

	/**
	 * thirdQuartile2()
	 * firstQuartile2()
	 * 
	 * @param set
	 * @return
	 * @author TelevisionNinja
	 */
	public static double interquartileRange_2(final double[] set) {
		return thirdQuartile_2(set) - firstQuartile_2(set);
	}

	/**
	 * 
	 * @param set
	 * @return
	 * @author TelevisionNinja
	 */
	public static double[] lowerHalf(final double[] set) {
		Arrays.sort(set);
		return Arrays.copyOfRange(set, 0, set.length / 2);
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static double mean_1(final double[] arr) {
		double sum = 0d;
		for (final double num : arr) {
			sum += num;
		}
		return sum / arr.length;
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static double mean_2(final double[] arr) {
		return ArrayUtils.sum_2(arr) / arr.length;
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static double median(final double[] arr) {
		Arrays.sort(arr);
		int length = arr.length;
		if (length % 2 == 0) {
			length /= 2;
			return (arr[length] + arr[length - 1]) / 2d;
		}
		return arr[length / 2];
	}

	/**
	 * 
	 * @param list
	 * @return
	 * @author TelevisionNinja
	 */
	public static double median(final List<Double> list) {
		list.sort(Comparator.naturalOrder());
		int length = list.size();
		if (length % 2 == 0) {
			length /= 2;
			return (list.get(length) + list.get(length - 1)) / 2d;
		}
		return list.get(length / 2);
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static double mode_1(final double[] arr){

		long modeCount = 0,
				currentCount = 0;
		double mode = 0d;

		for (final double current : arr) {
			currentCount = 0;
			for (final double compare : arr) {
				if (current == compare) {
					currentCount++;
				}
			}
			if (currentCount > modeCount) {
				modeCount = currentCount;
				mode = current;
			}
		}
		return mode;
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static double mode_2(final double[] arr) {
		final HashMap<Double, Double> map = new HashMap<>();
		double max  = 1d,
				temp = 0d;

		for (final double element : arr) {
			if (map.get(element) == null) {
				map.put(element, 1d);
			}
			else {
				double count = map.get(element);
				count++;
				map.put(element, count);
				if(count > max) {
					max  = count;
					temp = element;
				}
			}
		}
		return temp;
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<Double> mode_3(final double[] arr){
		Arrays.sort(arr);
		long modeCount = 0;
		final List<Double> modeList = new ArrayList<>();
		final long length = arr.length;
		int x = 0;
		while (x < length) {
			final double num = arr[x];
			int count = x + 1;

			while (count < length && num == arr[count]) {
				count++;
			}

			final long currentCount = count - x;
			x = count;

			if (currentCount > modeCount) {
				modeCount = currentCount;
				modeList.clear();
				modeList.add(num);
			}
			else if (currentCount == modeCount) {
				modeList.add(num);
			}
		}
		return modeList;
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<Double> mode_4(final double[] arr){
		Arrays.sort(arr);
		long modeCount = 0;
		boolean mode = false;
		final List<Double> modeList = new ArrayList<>();
		final long length = arr.length;
		int x = 0;
		while (x < length) {
			final double num = arr[x];
			int count = x + 1;

			while (count < length && num == arr[count]) {
				count++;
			}

			final long currentCount = count - x;
			x = count;

			if (modeCount != 0 && currentCount != modeCount) {
				mode = true;
			}

			if (currentCount > modeCount) {
				modeCount = currentCount;
				modeList.clear();
				modeList.add(num);
			}
			else if (currentCount == modeCount) {
				modeList.add(num);
			}
		}
		if (!mode) {
			modeList.clear();
		}
		return modeList;
	}

	/**
	 * online method
	 * 
	 * @param list
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<Double> mode_5(final List<Double> list) {
		long modeCount = 0;
		final long size = list.size();
		boolean modeFound = false;
		final List<Double> modeSet = new ArrayList<>();
		list.sort(Comparator.naturalOrder());
		for (int i = 0; i < size; i++) {
			final double number = list.get(i);
			int count = 1;
			while ((i + count) < size && list.get(i + count) == number) {
				count++;
			}
			i += count - 1;
			if (modeCount != 0 && count != modeCount) {
				modeFound = true;
			}
			if (count > modeCount) {
				modeSet.clear();
				modeSet.add(number);
				modeCount = count;
			}
			else if (count == modeCount) {
				modeSet.add(number);
			}
		}
		if (!modeFound) {
			modeSet.clear();
		}
		return modeSet;
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<Double> mode_6(final double[] arr){
		Arrays.sort(arr);
		long modeCount = 0;
		int x = 0;
		boolean mode = false;
		final List<Double> modeList = new ArrayList<>();
		final long length = arr.length;
		while (x < length) {
			final double num = arr[x];
			final long count = x;

			x += 1;

			while (x < length && num == arr[x]) {
				x++;
			}

			final long currentCount = x - count;

			if (modeCount != 0 && currentCount != modeCount) {
				mode = true;
			}

			if (currentCount > modeCount) {
				modeCount = currentCount;
				modeList.clear();
				modeList.add(num);
			}
			else if (currentCount == modeCount) {
				modeList.add(num);
			}
		}
		if (!mode) {
			modeList.clear();
		}
		return modeList;
	}

	/**
	 * 
	 * @param list
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<Double> mode_6(final List<Double> list){
		list.sort(Comparator.naturalOrder());
		long modeCount = 0;
		int x = 0;
		final long length = list.size();
		boolean mode = false;
		final List<Double> modeList = new ArrayList<>();
		while (x < length) {
			final double num = list.get(x);
			final long count = x;

			x += 1;

			while (x < length && num == list.get(x)) {
				x++;
			}

			final long currentCount = x - count;

			if (modeCount != 0 && currentCount != modeCount) {
				mode = true;
			}

			if (currentCount > modeCount) {
				modeCount = currentCount;
				modeList.clear();
				modeList.add(num);
			}
			else if (currentCount == modeCount) {
				modeList.add(num);
			}
		}
		if (!mode) {
			modeList.clear();
		}
		return modeList;
	}

	/**
	 * set is not sorted in the method
	 * 
	 * @param set
	 * @param percent
	 * @return
	 * @author TelevisionNinja
	 */
	public static double percentile(final double[] set, final double percent) {
		final double index = percent * ((set.length + 1d) / 100d) - 1d,
				roundUp = Math.ceil(index),
				reoundDown = Math.floor(index);
		if (roundUp == reoundDown) {
			return set[(int) index];
		}
		return (set[(int) roundUp] + set[(int) reoundDown]) / 2d;
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static double range(final double[] arr) {
		Arrays.sort(arr);
		return arr[arr.length - 1] - arr[0];
	}

	/**
	 * 
	 * @param list
	 * @return
	 * @author TelevisionNinja
	 */
	public static double range(final List<Double> list) {
		list.sort(Comparator.naturalOrder());
		return list.get(list.size()) - list.get(0);
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static double standardDeviationPopulation_1(final double[] arr) {
		final double mean = mean_1(arr);

		for (int x = 0; x < arr.length; x++) {
			final double difference = (arr[x] - mean);
			arr[x] =  difference * difference;
		}

		return Math.sqrt(mean_1(arr));
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static double standardDeviationPopulation_2(final double[] arr) {
		return Math.sqrt(variancePopulation_2(arr));
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static double standardDeviationPopulation_3(final double[] arr) {
		return Math.sqrt(variancePopulation_3(arr));
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static double standardDeviationSample_1(final double[] arr) {
		return Math.sqrt(varianceSample_1(arr));
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static double standardDeviationSample_2(final double[] arr) {
		return Math.sqrt(varianceSample_2(arr));
	}

	/**
	 * upperHalf() method
	 * 
	 * @param set
	 * @return
	 * @author TelevisionNinja
	 */
	public static double thirdQuartile_1(final double[] set) {
		return median(upperHalf(set));
	}

	/**
	 * percentile() method
	 * 
	 * @param set
	 * @return
	 * @author TelevisionNinja
	 */
	public static double thirdQuartile_2(final double[] set) {
		return percentile(set, 75d);
	}

	/**
	 * 
	 * @param set
	 * @return
	 * @author TelevisionNinja
	 */
	public static double[] upperHalf(final double[] set) {
		Arrays.sort(set);
		final int len = set.length;
		if (len % 2 == 0) {
			return Arrays.copyOfRange(set, len / 2, len);
		}
		return Arrays.copyOfRange(set, len / 2 + 1, len);
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static double variancePopulation_1(final double[] arr) {
		final double mean = mean_1(arr);

		for (int x = 0; x < arr.length; x++) {
			final double difference = (arr[x] - mean);
			arr[x] =  difference * difference;
		}

		return mean_1(arr);
	}

	/**
	 * for loop
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static double variancePopulation_2(final double[] arr) {
		final double mean = mean_2(arr);

		for (int x = 0; x < arr.length; x++) {
			final double difference = (arr[x] - mean);
			arr[x] = difference * difference;
		}

		return mean_2(arr);
	}

	/**
	 * enhanced for loop
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static double variancePopulation_3(final double[] arr) {
		final double mean = mean_2(arr);
		double total = 0d;

		for (final double value : arr) {
			final double difference = (value - mean);
			total += difference * difference;
		}

		return total / arr.length;
	}

	/**
	 * for loop
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static double varianceSample_1(final double[] arr) {
		final double mean = mean_2(arr),
				length = arr.length;

		for (int x = 0; x < length; x++) {
			final double difference = (arr[x] - mean);
			arr[x] =  difference * difference;
		}

		return ArrayUtils.sum_2(arr) / (length - 1);
	}

	/**
	 * enhanced for loop
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static double varianceSample_2(final double[] arr) {
		final double mean = mean_2(arr);
		double total = 0d;

		for (final double value : arr) {
			final double difference = (value - mean);
			total += difference * difference;
		}

		return total / (arr.length - 1);
	}
}