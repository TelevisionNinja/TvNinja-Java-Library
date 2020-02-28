/**
 * 
 */
package televisionninja.lib.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author TelevisionNinja
 *
 */
public class NumberProblems {
	/**
	 * online method
	 * 
	 * @param digit
	 * @param sign
	 * @param branches
	 * @return
	 * @author TelevisionNinja
	 */
	private static List<String> add(final long digit, final String sign, final List<String> branches) {
		for (int i = 0; i < branches.size(); i++) {
			branches.set(i, digit + sign + branches.get(i));
		}
		return branches;
	}

	/**
	 * online method
	 * 
	 * @param sum
	 * @param number
	 * @param index
	 * @param values
	 * @return
	 * @author TelevisionNinja
	 */
	private static List<String> AddOrSubCombinatonsToSum(final long sum, final long number, final int index, final long[] values) {
		final long digit = Math.abs(number % 10);
		if (index >= values.length) {
			if (sum == number) {
				final List<String> result = new ArrayList<>();
				result.add(Long.toString(digit));
				return result;
			}
			else {
				return new ArrayList<>();
			}
		}

		final List<String> branch1 = AddOrSubCombinatonsToSum(sum - number, values[index], index + 1, values),
				branch2 = AddOrSubCombinatonsToSum(sum - number, -values[index], index + 1, values);

		final long concatenatedNumber = number >= 0 ? 10 * number + values[index] : 10 * number - values[index];

		final List<String> branch3 = AddOrSubCombinatonsToSum(sum, concatenatedNumber, index + 1, values),
				results = new ArrayList<>();

		results.addAll(add(digit, " + ", branch1));
		results.addAll(add(digit, " - ", branch2));
		results.addAll(add(digit, "", branch3));

		return results;
	}

	/**
	 * online method
	 * 
	 * @param sum
	 * @param values
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<String> AddOrSubCombinatonsToSum(final long sum, final long[] values) {
		return AddOrSubCombinatonsToSum(sum, values[0], 1, values);
	}

	/**
	 * online method
	 * 
	 * Prints all subsets of arr[0..n-1] with sum 0.
	 * 
	 * @param arr
	 * @param sum
	 * @author TelevisionNinja
	 */
	public static List<List<Integer>> allSubsetSums(final int arr[], final int sum) {
		final int n = arr.length;

		final List<List<Integer>> output = new ArrayList<>();

		if (n == 0 || sum < 0) {
			return output;
		}

		//dp[i][j] is going to store true if sum j is possible with array elements from 0 to i.
		// Sum 0 can always be achieved with 0 elements
		final boolean[][] dp = new boolean[n][sum + 1];

		for (int i = 0; i < n; ++i) {
			dp[i][0] = true;
		}

		// Sum arr[0] can be achieved with single element
		if (arr[0] <= sum) {
			dp[0][arr[0]] = true;
		}

		// Fill rest of the entries in dp[][]
		for (int i = 1; i < n; ++i) {
			for (int j = 0; j < sum + 1; ++j) {
				dp[i][j] = (arr[i] <= j) ? (dp[i - 1][j] || dp[i - 1][j - arr[i]]) : dp[i - 1][j];
			}
		}

		if (dp[n - 1][sum] == false) {
			return output;
		}

		// Now recursively traverse dp[][] to find all paths from dp[n - 1][sum]
		final List<Integer> p = new ArrayList<>();
		allSubsetSums(arr, n - 1, sum, p, dp, output);

		return output;
	}

	/**
	 * online method
	 * 
	 * A recursive function to return all subsets with the help of dp[][]. Vector p[] stores current subset.
	 * 
	 * @param arr
	 * @param i
	 * @param sum
	 * @param p
	 * @param dp
	 * @author TelevisionNinja
	 */
	private static void allSubsetSums(final int arr[], final int i, final int sum, final List<Integer> p, final boolean[][] dp, final List<List<Integer>> output) {
		// If we reached end and sum is non-zero
		// We print p[] only if arr[0] is equal to sun OR dp[0][sum] is true
		if (i == 0 && sum != 0 && dp[0][sum]) {
			p.add(arr[i]);
			output.add(p);
			return;
		}

		// If sum becomes 0
		if (i == 0 && sum == 0) {
			output.add(p);
			return;
		}

		// If given sum can be achieved after ignoring current element.
		if (dp[i - 1][sum]) {
			// Create a new vector to store path
			final List<Integer> b = new ArrayList<>();
			b.addAll(p);
			allSubsetSums(arr, i - 1, sum, b, dp, output);
		}

		// If given sum can be achieved after considering current element.
		if (sum >= arr[i] && dp[i - 1][sum - arr[i]]) {
			p.add(arr[i]);
			allSubsetSums(arr, i - 1, sum - arr[i], p, dp, output);
		}
	}

	/**
	 * online method
	 * 
	 * @param values
	 * @return
	 * @author TelevisionNinja
	 * @param <E>
	 */
	public static <E> String greatestNumberArrangeMent(final E[] values) {
		Arrays.sort(values, new Comparator<E>() {
			@Override
			public int compare(final E o1, final E o2) {
				final String v1 = o1.toString(),
						v2 = o2.toString();
				return (v1 + v2).compareTo(v2 + v1) * -1;
			}
		});

		final StringBuilder result = new StringBuilder();
		for (final E value : values) {
			result.append(value.toString());
		}
		return result.toString();
	}

	/**
	 * online method
	 * 
	 * Matrix has dimension p[i-1] x p[i] for i = 1..n
	 * 
	 * @param p
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<String> matrixChainOrder(final int p[]) {
		final int n = p.length;

		/* For simplicity of the program, one extra row and one
        extra column are allocated in m[][].  0th row and 0th
        column of m[][] are not used */
		final int m[][] = new int[n][n];

		int i, j, k, L, q;

		/* m[i,j] = Minimum number of scalar multiplications needed
        to compute the matrix A[i]A[i+1]...A[j] = A[i..j] where
        dimension of A[i] is p[i-1] x p[i] */

		// cost is zero when multiplying one matrix.
		for (i = 1; i < n; i++) {
			m[i][i] = 0;
		}

		// L is chain length.
		for (L = 2; L < n; L++) {
			for (i = 1; i < n - L + 1; i++) {
				j = i + L - 1;
				if (j == n) {
					continue;
				}
				m[i][j] = Integer.MAX_VALUE;
				for (k = i; k <= j - 1; k++) {
					// q = cost/scalar multiplications
					q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
					if (q < m[i][j]) {
						m[i][j] = q;
						m[j][i] = k;
					}
				}
			}
		}

		final StringBuilder str = new StringBuilder();
		str.append("Optimal Parenthesization: ");

		matrixChainOrderParenthesis(1, n - 1, n, m, 'A', str);

		final List<String> list = new ArrayList<>();
		list.add("Optimal Cost: " + Integer.toString(m[1][n - 1]));
		list.add(str.toString());

		return list;
	}

	/**
	 * 
	 * Function for printing the optimal parenthesization of a matrix chain product
	 * 
	 * @param i
	 * @param j
	 * @param n
	 * @param bracket
	 * @param name
	 * @param str
	 * @author TelevisionNinja
	 */
	private static void matrixChainOrderParenthesis(final int i, final int j, final int n, final int[][] bracket, char name, final StringBuilder str) {
		// If only one matrix left in current segment
		if (i == j) {
			name += j - 1;
			str.append(name);
			return;
		}

		str.append("(");

		// Recursively put brackets around subexpression from i to bracket[j][i]
		matrixChainOrderParenthesis(i, bracket[j][i], n, bracket, name, str);

		// Recursively put brackets around subexpression from bracket[j][i] + 1 to i.
		matrixChainOrderParenthesis(bracket[j][i] + 1, j, n, bracket, name, str);
		str.append(")");
	}

	/**
	 * 
	 * @param n
	 * @param steps
	 * @return
	 * @author TelevisionNinja
	 */
	public static long numberOfCustomStepsBottomUp(final int n, final int[] steps) {
		final long[] dp = new long[n + 1];
		dp[0] = 1;
		for (int y = 1; y <= n; y++) {
			long sum = 0;
			for (final int step : steps) {
				final int sub = y - step;
				if (sub >= 0) {
					sum += dp[sub];
				}
			}
			dp[y] = sum;
		}
		return dp[n];
	}

	/**
	 * 
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public static long numberOfStepsBottomUp_1(final int n) {
		final long[] dp = new long[n + 1];

		for (int y = 1; y <= n; y++) {
			long sum = 0;
			for (int x = 1; x <= n; x++) {
				final int sub = n - x;
				if (sub < 0) {
					dp[sub] = 0;
				}
				if (sub == 0) {
					dp[sub] = 1;
				}
				sum += dp[sub];
			}
			dp[y] = sum;
		}
		return dp[n];
	}

	/**
	 * 
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public static long numberOfStepsBottomUp_2(final int n) {
		final long[] dp = new long[n + 1];
		dp[0] = 1;
		dp[1] = dp[0];
		for (int y = 2; y <= n; y++) {
			long sum = 0;
			for (int x = 1; x <= n; x++) {
				sum += dp[n - x];
			}
			dp[y] = sum;
		}
		return dp[n];
	}

	/**
	 * 
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public static long numberOfStepsRecursion(final int n) {
		return numberOfStepsRecursion(n, new long[n + 1]);
	}

	/**
	 * 
	 * @param n
	 * @param dp
	 * @return
	 * @author TelevisionNinja
	 */
	private static long numberOfStepsRecursion(final int n, final long[] dp) {
		if (dp[n] < 0) {
			return 0;
		}
		if (dp[n] == 1) {
			return 1;
		}
		if (n < 0) {
			dp[n] = 0;
			return 0;
		}
		if (n == 0) {
			dp[n] = 1;
			return 1;
		}

		int sum = 0;
		for (int x = 1; x <= n; x++) {
			sum += numberOfStepsRecursion(n - x, dp);
		}

		return sum;
	}

	/**
	 * online method
	 * 
	 * The main function that prints all combinations of size r in arr[] of size n.
	 * This function mainly uses combinationUtil()
	 * 
	 * @param arr
	 * @param r
	 * @author TelevisionNinja
	 */
	public static List<List<Integer>> subsetCombinationsOfGivenSize(final int arr[], final int r) {
		// A temporary array to store all combination one by one
		final int data[] = new int[r];

		final List<List<Integer>> output = new ArrayList<>();

		// Print all combination using temprary array 'data[]'
		subsetCombinationsOfGivenSize(arr, r, 0, data, 0, output);
		return output;
	}

	/**
	 * online method
	 * 
	 * @param arr
	 * 		Input Array
	 * @param r
	 * 		Size of a combination to be printed
	 * @param index
	 * 		Current index in data[]
	 * @param data
	 * 		Temporary array to store current combination
	 * @param i
	 * @author TelevisionNinja
	 */
	private static void subsetCombinationsOfGivenSize(final int arr[], final int r, final int index, final int data[], final int i, final List<List<Integer>> output) {
		final List<Integer> list = new ArrayList<>();

		// Current combination is ready to be printed, print it
		if (index == r) {
			for (int j = 0; j < r; j++) {
				list.add(data[j]);
			}
			output.add(list);
			return;
		}

		// When no more elements are there to put in data[]
		if (i >= arr.length) {
			return;
		}

		// current is included, put next at next
		// location
		data[index] = arr[i];
		subsetCombinationsOfGivenSize(arr, r, index + 1, data, i + 1, output);

		// current is excluded, replace it with
		// next (Note that i+1 is passed, but
		// index is not changed)
		subsetCombinationsOfGivenSize(arr, r, index, data, i + 1, output);
	}
}