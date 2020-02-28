/**
 * 
 */
package televisionninja.lib.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import televisionninja.lib.mathutils.MathUtils;

/**
 * @author TelevisionNinja
 *
 */
public class Knapsack {
	/**
	 * online method
	 * 
	 * item value class
	 * 
	 * @author TelevisionNinja
	 *
	 */
	private class ItemValue {
		Double cost;
		double wt,
		val;

		/**
		 * item value function
		 * 
		 * @param wt
		 * @param val
		 */
		public ItemValue(final int wt, final int val) {
			this.wt = wt;
			this.val = val;
			this.cost = Double.valueOf(val / wt);
		}
	}

	/**
	 * online method
	 * 
	 * Returns the maximum value that can be put in a knapsack of capacity W
	 * 
	 * @param W
	 * @param wt
	 * @param val
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public static int knapsackRecursion(final int W, final int wt[], final int val[], final int n) {
		// Base Case
		if (n == 0 || W == 0) {
			return 0;
		}

		/*
		 * If weight of the nth item is more than Knapsack capacity W
		 * then this item cannot be included in the optimal solution
		 */
		if (wt[n - 1] > W) {
			return knapsackRecursion(W, wt, val, n - 1);
		}

		/*
		 * Return the maximum of two cases:
		 * (1) nth item included
		 * (2) not included
		 */
		else {
			return Math.max(val[n - 1] + knapsackRecursion(W - wt[n - 1], wt, val, n - 1), knapsackRecursion(W, wt, val, n - 1));
		}
	}

	public double totalValue,
	totalWeight;

	public final List<String> weight = new ArrayList<>(),
			value = new ArrayList<>();

	public Knapsack() {}

	private void clear() {
		this.totalValue = 0;
		this.totalWeight = 0;
		this.weight.clear();
		this.value.clear();
	}


	/**
	 * online method
	 * 
	 * Returns the maximum value that can be put in a knapsack of capacity W
	 * 
	 * @param W
	 * @param wt
	 * @param val
	 * @author TelevisionNinja
	 */
	public void knapsackDynamic(final int W, final int wt[], final int val[]) {
		clear();
		this.totalWeight = W;
		int i,
		w,
		res;
		final int n = wt.length;
		final int K[][] = new int[n + 1][W + 1];

		// Build table K[][] in bottom up manner
		for (i = 0; i <= n; i++) {
			final int index = i - 1;
			for (w = 0; w <= W; w++) {
				if (i == 0 || w == 0) {
					K[i][w] = 0;
				}
				else if (wt[index] <= w) {
					K[i][w] = Math.max(val[index] + K[index][w - wt[index]], K[index][w]);
				}
				else {
					K[i][w] = K[index][w];
				}
			}
		}

		// stores the result of Knapsack
		res = K[n][W];
		this.totalValue = res;
		w = W;

		for (i = n; i > 0 && res > 0; i--) {
			/*
			 * either the result comes from the top
			 * (K[i-1][w]) or from (val[i-1] + K[i-1]
			 * [w-wt[i-1]]) as in Knapsack table.
			 * 
			 * If it comes from the latter one
			 * it means the item is included.
			 */
			final int index = i - 1;
			if (res != K[index][w]) {
				// This item is included.
				final int weight = wt[index],
						value = val[index];
				this.weight.add(Integer.toString(weight));
				this.value.add(Integer.toString(value));
				// Since this weight is included its value is deducted
				res = res - value;
				w = w - weight;
			}
		}
	}

	/**
	 * online method
	 * 
	 * function to get maximum value
	 * 
	 * @param capacity
	 * @param wt
	 * @param val
	 * @author TelevisionNinja
	 */
	public void knapsackFractional(double capacity, final int[] wt, final int[] val) {
		clear();
		this.totalWeight = capacity;
		final ItemValue[] iVal = new ItemValue[wt.length];

		for (int i = 0; i < wt.length; i++) {
			iVal[i] = new ItemValue(wt[i], val[i]);
		}

		//sorting items by value
		Arrays.sort(iVal, new Comparator<ItemValue>() {
			@Override
			public int compare(final ItemValue o1, final ItemValue o2) {
				return o2.cost.compareTo(o1.cost) ;
			}
		});


		double totalValue = 0;

		for (final ItemValue i: iVal) {
			final double curWt = i.wt,
					curVal = i.val;

			if (capacity - curWt >= 0) {
				// this weight can be picked while
				capacity = capacity - curWt;
				this.weight.add(Double.toString(curWt));
				this.value.add(Double.toString(curVal));
				totalValue += curVal;

			}
			else {
				// item cant be picked whole
				final double fraction = capacity / curWt,
						fracVal = curVal * fraction;
				final String fract = MathUtils.simplifyFraction((long) (capacity) + "/" + (long) (curWt));
				this.weight.add(fract + " of " + curWt + " = " + fraction * curWt);
				this.value.add(fract + " of " + curVal + " = " + Double.toString(fracVal));
				totalValue += fracVal;
				capacity = capacity - curWt;
				break;
			}
		}
		this.totalValue = totalValue;
	}

	@Override
	public String toString() {
		final StringBuilder string = new StringBuilder();
		final String separator = System.lineSeparator();
		string.append("total value: " + this.totalValue + separator +
				"total weight: " + this.totalWeight + separator + separator);

		for (int i = 0; i < this.weight.size(); i++) {
			string.append("value: " + this.value.get(i) + separator +
					"weight: " + this.weight.get(i) + separator + separator);
		}
		return string.toString();
	}
}