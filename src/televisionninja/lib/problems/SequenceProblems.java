/**
 * 
 */
package televisionninja.lib.problems;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import televisionninja.lib.listutils.ArrayUtils;

/**
 * @author TelevisionNinja
 *
 */
public class SequenceProblems {
	/**
	 * 
	 * @author TelevisionNinja
	 *
	 */
	public static class Pair {
		public int a,
		b;

		public Pair(final int a, final int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public String toString() {
			return "(" + this.a + ", " + this.b + ")";
		}
	}

	/**
	 * online method
	 * 
	 * @param list
	 * @param target
	 * @return
	 * @author TelevisionNinja
	 */
	private static int binarySearch(final List<Integer> list, final int target) {
		int start = 0,
				end = list.size() - 1;
		while (start + 1 < end) {
			final int mid = start + (end - start) / 2;
			final int current = list.get(mid);
			if (current == target) {
				return mid;
			}
			else if (current < target) {
				start = mid;
			}
			else {
				end = mid;
			}
		}
		return list.get(start) >= target ? start : end;
	}

	/**
	 * online method
	 * 
	 * function to print equal sum sets of array
	 * 
	 * @param arr
	 * @author TelevisionNinja
	 */
	public static void equalSumSets(final int []arr) {
		int i,
		currSum,
		sum = 0;

		final int n = arr.length;

		// Finding sum of array elements
		for (i = 0; i < arr.length; i++) {
			sum += arr[i];
		}

		// Check sum is even or odd.
		// If odd then array cannot
		// be partitioned. Print -1
		// and return.
		if ((sum & 1) == 1) {
			System.out.print("-1");
			return;
		}

		// Divide sum by 2 to find
		// sum of two possible subsets.
		final int k = sum >> 1;

		// Boolean DP table to store
		// result of states.
		// dp[i,j] = true if there is a
		// subset of elements in first i
		// elements of array that has sum
		// equal to j.
		final boolean [][]dp = new boolean[n + 1][k + 1];

		// If number of elements are zero,
		// then no sum can be obtained.
		for (i = 1; i <= k; i++) {
			dp[0][i] = false;
		}

		// Sum 0 can be obtained by
		// not selecting any element.
		for (i = 0; i <= n; i++) {
			dp[i][0] = true;
		}

		// Fill the DP table
		// in bottom up manner.
		for (i = 1; i <= n; i++) {
			for (currSum = 1; currSum <= k; currSum++) {

				// Excluding current element.
				dp[i][currSum] = dp[i - 1][currSum];

				// Including current element
				if (arr[i - 1] <= currSum) {
					dp[i][currSum] = dp[i][currSum] | dp[i - 1][currSum - arr[i - 1]];
				}
			}
		}

		// Required sets set1 and set2.
		final List<Integer> set1 = new ArrayList<>(),
				set2 = new ArrayList<>();

		// If partition is not possible print -1 and return.
		if (!dp[n][k]) {
			System.out.print("-1\n");
			return;
		}

		// Start from last
		// element in dp table.
		i = n;
		currSum = k;

		while (i > 0 && currSum >= 0) {
			/* If current element does not contribute to k
			 * then it belongs to set 2.
			 */
			if (dp[i - 1][currSum]) {
				i--;
				set2.add(arr[i]);
			}

			// If current element contribute to k then it belongs to set 1.
			else if (dp[i - 1][currSum - arr[i - 1]]) {
				i--;
				currSum -= arr[i];
				set1.add(arr[i]);
			}
		}

		// Print elements of both the sets.
		System.out.print("Set 1 elements: ");
		for (i = 0; i < set1.size(); i++) {
			System.out.print(set1.get(i) + " ");
		}

		System.out.print("\nSet 2 elements: ");

		for (i = 0; i < set2.size(); i++) {
			System.out.print(set2.get(i) + " ");
		}
	}

	/**
	 * online method
	 * 
	 * Returns the length and the LCIS of two arrays arr1[0..n-1] and arr2[0..m-1] and prints LCIS
	 * 
	 * @param arr1
	 * @param n
	 * @param arr2
	 * @param m
	 * @return
	 * @author TelevisionNinja
	 */
	public static int[] longestCommonIncreasingSubsequence(final int arr1[], final int arr2[]) {
		/*
		 * table[j] is going to store length of LCIS ending with arr2[j].
		 * We initialize it as 0.
		 */
		final int n = arr1.length,
				m = arr2.length;

		final int[] table = new int[m],
				parent = new int[m];
		for (int j = 0; j < m; j++) {
			table[j] = 0;
		}

		// Traverse all elements of arr1[]
		for (int i = 0; i < n; i++) {
			// Initialize current length of LCIS
			int current = 0,
					last = -1;

			// For each element of arr1[],
			// trvarse all elements of arr2[].
			for (int j = 0; j < m; j++) {
				// If both the array have same
				// elements. Note that we don't
				// break the loop here.
				if (arr1[i] == arr2[j]) {
					if (current + 1 > table[j]) {
						table[j] = current + 1;
						parent[j] = last;
					}
				}

				// Now seek for previous smaller common element for current element of arr1
				if (arr1[i] > arr2[j]) {
					if (table[j] > current) {
						current = table[j];
						last = j;
					}
				}
			}
		}

		// The maximum value in table[] is out result
		int result = 0,
				index = -1;

		for (int i = 0; i < m; i++) {
			if (table[i] > result) {
				result = table[i];
				index = i;
			}
		}

		// LCIS is going to store elements of LCIS
		final int lcis[] = new int[result];

		for (int i = 0; index != -1; i++) {
			lcis[i] = arr2[index];
			index = parent[index];
		}

		return ArrayUtils.reverse(lcis);
	}

	/**
	 * online method
	 * 
	 * Returns length of LCS for X[0..m-1], Y[0..n-1]
	 * 
	 * @param x
	 * @param y
	 * @return
	 * @author TelevisionNinja
	 */
	public static String LongestCommonSubsequence(final String x, final String y) {
		final char[] X = x.toCharArray(),
				Y = y.toCharArray();
		int m = x.length(),
				n = y.length();
		final int L[][] = new int[m + 1][n + 1];

		/* Following steps build L[m+1][n+1] in bottom up fashion.
		 * Note that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1]
		 */
		for (int i = 0; i <= m; i++){
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0) {
					L[i][j] = 0;
				}
				else if (X[i - 1] == Y[j - 1]) {
					L[i][j] = L[i-1][j - 1] + 1;
				}
				else {
					L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
				}
			}
		}

		// Following code is used to print LCS
		int index = L[m][n];

		// Create a character array to store the lcs string
		final char[] lcs = new char[index];

		// Start from the right-most-bottom-most corner and
		// one by one store characters in lcs[]
		while (m > 0 && n > 0) {
			// If current character in X[] and Y are same, then
			// current character is part of LCS
			final int mIndex = m - 1,
					nIndex = n - 1;
			final char xChar = X[mIndex];

			if (xChar == Y[nIndex]) {
				// Put current character in result
				lcs[--index] = xChar;

				// reduce values of i, j and index
				m--;
				n--;
			}

			// If not same, then find the larger of two and
			// go in the direction of larger value
			else if (L[mIndex][n] > L[m][nIndex]) {
				m--;
			}
			else {
				n--;
			}
		}

		return new String(lcs);
	}

	/**
	 * online method
	 * 
	 * O(nLogN) using binary serach
	 * Maintain a list of nums in increasing order
	 * When considering new num:
	 * 		- See if it can append (num > last-max-num from the list)
	 * 		- If not, do binary search with the list and see where the number may fit
	 * 		- Every time, set num to where it may fit in the list (find the smallest item from list which also > num)
	 * Why setting a number in the list?
	 * The list works as a baseline, which adjusts dynamically: any number less than the baseline won't be able to append
	 * However, it can help refine (lower) the baseline, which potentially allow future number to append
	 * 
	 * @param nums
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<Integer> longestIncreasingSubsequence(final int[] nums) {
		if (nums == null || nums.length == 0) {
			return new ArrayList<>();
		}

		final int n = nums.length;
		final List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (list.size() == 0 || nums[i] > list.get(list.size() - 1)) {
				list.add(nums[i]);
			}
			else {
				list.set(binarySearch(list, nums[i]), nums[i]);
			}
		}
		return list;
	}

	/**
	 * Function to construct Maximum Length Chain of Pairs
	 * 
	 * @param arr
	 * @author TelevisionNinja
	 */
	public static List<Pair> maxChainLength(final List<Pair> arr) {
		// Sort by start time
		arr.sort(new Comparator<Pair>() {
			// comparator function for sort function
			@Override
			public int compare(final Pair o1, final Pair o2) {
				if (o1.a < o2.a) {
					return -1;
				}
				else if (o1.a == o2.a) {
					return 0;
				}
				else {
					return 1;
				}
			}
		});

		// L[i] stores maximum length of chain of arr[0..i] that ends with arr[i].
		final List<List<Pair>> L = new ArrayList<>();

		for (int i = 0; i < arr.size(); i++) {
			L.add(new ArrayList<>());
		}

		// L[0] is equal to arr[0]
		L.get(0).add(arr.get(0));

		// start from index 1
		for (int i = 1; i < arr.size(); i++) {
			// for every j less than i
			for (int j = 0; j < i; j++) {
				// L[i] = {Max(L[j])} + arr[i]
				// where j < i and arr[j].b < arr[i].a
				if (arr.get(j).b < arr.get(i).a && L.get(j).size() > L.get(i).size()) {
					L.set(i, L.get(j));
				}
			}

			final List<Pair> tempList = L.get(i);
			final Pair arrPair = arr.get(i);
			if (tempList.size() == 0) {
				tempList.add(arrPair);
			}
			else if (tempList.get(tempList.size() - 1).b < arrPair.a) {
				tempList.add(arrPair);
			}
		}

		// create max length vector
		List<Pair> maxChain = new ArrayList<>();
		for (final List<Pair> x : L) {
			if (x.size() > maxChain.size()) {
				maxChain = x;
			}
		}

		return maxChain;
	}

	// bottom up tabular dp
	public static int painterProblem(final int arr[], final int k) {
		final int n = arr.length;

		// initialize table
		final int dp[][] = new int[k + 1][n + 1];

		// base cases
		// k=1
		for (int i = 1; i <= n; i++) {
			dp[1][i] = painterProblemSumFunc(arr, 0, i - 1);
		}

		// n=1
		for (int i = 1; i <= k; i++) {
			dp[i][1] = arr[0];
		}

		// 2 to k partitions
		for (int i = 2; i <= k; i++) { // 2 to n boards
			for (int j = 2; j <= n; j++) {

				// track minimum
				int best = Integer.MAX_VALUE;

				// i-1 th separator before position arr[p=1..j]
				for (int p = 1; p <= j; p++) {
					best = Math.min(best, Math.max(dp[i - 1][p], painterProblemSumFunc(arr, p, j - 1)));
				}

				dp[i][j] = best;
			}
		}

		// required
		return dp[k][n];
	}

	// function to calculate sum between two indices in array
	private static int painterProblemSumFunc(final int arr[], final int from, final int to) {
		int total = 0;
		for (int i = from; i <= to; i++) {
			total += arr[i];
		}
		return total;
	}

	/**
	 * online method
	 * 
	 * returns shortest supersequence of X and Y
	 * 
	 * @param X
	 * @param Y
	 * @return
	 * @author TelevisionNinja
	 */
	public static String shortestCommonSuperSequence(final String X, final String Y) {
		final int m = X.length(),
				n = Y.length();

		// dp[i][j] contains length of shortest supersequence for X[0..i-1] and Y[0..j-1]
		final int dp[][] = new int[m + 1][n + 1];

		// Fill table in bottom up manner
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {

				// Below steps follow recurrence relation
				if (i == 0) {
					dp[i][j] = j;
				}
				else if (j == 0) {
					dp[i][j] = i;
				}
				else if (X.charAt(i - 1) == Y.charAt(j - 1)) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
				}
				else {
					dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		// Following code is used to print
		// shortest supersequence dp[m][n] s
		// tores the length of the shortest
		// supersequence of X and Y
		int i = m,
				j = n;

		// string to store the shortest supersequence
		final StringBuilder str = new StringBuilder();

		// Start from the bottom right corner and one by one push characters in output string
		while (i > 0 && j > 0) {
			// If current character in X and Y are same, then current character is part of shortest supersequence
			if (X.charAt(i - 1) == Y.charAt(j - 1)) {
				// Put current character in result
				str.append(X.charAt(i - 1));

				// reduce values of i, j and index
				i--;
				j--;
			}

			// If current character in X and Y are different
			else if (dp[i - 1][j] > dp[i][j - 1]) {

				// Put current character of Y in result
				str.append(Y.charAt(j - 1));

				// reduce values of j and index
				j--;
			}
			else {
				// Put current character of X in result
				str.append(X.charAt(i - 1));

				// reduce values of i and index
				i--;
			}
		}

		// If Y reaches its end, put remaining characters of X in the result string
		while (i > 0) {
			str.append(X.charAt(i - 1));
			i--;
		}

		// If X reaches its end, put remaining characters of Y in the result string
		while (j > 0) {
			str.append(Y.charAt(j - 1));
			j--;
		}

		// reverse the string and return it
		str.reverse();
		return str.toString();
	}
}