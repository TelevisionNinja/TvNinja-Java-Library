/**
 * 
 */
package televisionninja.lib.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TelevisionNinja
 *
 */
public class StringProblems {
	/**
	 * online method
	 * 
	 * Prints super sequence of a[0..m-1] and b[0..n-1]
	 * 
	 * @param a
	 * @param b
	 * @author TelevisionNinja
	 */
	public static String shortestStringCombination(final String a, final String b) {
		final int m = a.length(),
				n = b.length();
		final int[][] dp = new int[m + 1][n + 1];

		// Fill table in bottom up manner
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				// Below steps follow above recurrence
				if (i == 0) {
					dp[i][j] = j;
				}
				else if (j == 0 ) {
					dp[i][j] = i;
				}
				else if (a.charAt(i - 1) == b.charAt(j - 1)) {
					dp[i][j] = 1 + dp[i-1][j-1];
				}
				else {
					dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		// Create a string of size index+1 to store the result
		final StringBuilder res = new StringBuilder();

		// Start from the right-most-bottom-most corner and
		// one by one store characters in res[]
		int i = m,
				j = n;

		while (i > 0 && j > 0) {
			// If current character in a[] and b are same,
			// then current character is part of LCS
			if (a.charAt(i - 1) == b.charAt(j - 1)) {
				// Put current character in result
				res.append(a.charAt(i - 1));

				// reduce values of i, j and indexs
				i--;
				j--;
			}

			// If not same, then find the larger of two and
			// go in the direction of larger value
			else if (dp[i - 1][j] < dp[i][j - 1]) {
				res.append(a.charAt(i - 1));
				i--;
			}
			else{
				res.append(b.charAt(j - 1));
				j--;
			}
		}

		// Copy remaining characters of string 'a'
		while (i > 0) {
			res.append(a.charAt(i - 1));
			i--;
		}

		// Copy remaining characters of string 'b'
		while (j > 0) {
			res.append(b.charAt(j - 1));
			j--;
		}

		// return the result
		return res.reverse().toString();
	}

	/**
	 * online method
	 * 
	 * Function to find space optimized solution of Word Wrap problem.
	 * 
	 * @param arr
	 * @param k
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<Integer> wordWrap(final int arr[], final int k) {
		final int n = arr.length;
		int i,
		j,
		currlen, // Variable to store number of characters in given line.
		cost; // Variable to store possible minimum cost of line.

		final int[] dp = new int[n], // DP table in which dp[i] represents cost of line starting with word arr[i].
				ans = new int[n]; // Array in which ans[i] store index of last word in line starting with word arr[i].

		// If only one word is present then only one line is required.
		// Cost of last line is zero.
		// Hence cost of this line is zero.
		// Ending point is also n-1 as single word is present.
		dp[n - 1] = 0;
		ans[n - 1] = n - 1;

		// Make each word first word of line by iterating over each index in arr.
		for (i = n - 2; i >= 0; i--) {
			currlen = -1;
			dp[i] = Integer.MAX_VALUE;

			// Keep on adding words in current line by iterating from starting word up to last word in arr.
			for (j = i; j < n; j++) {

				// Update number of characters in current line. arr[j] is number of characters in current word and 1 represents space character between two words.
				currlen += (arr[j] + 1);

				// If limit of characters is violated then no more words can be added to current line.
				if (currlen > k) {
					break;
				}

				// If current word that is added to line is last word of arr then current line is last line.
				// Cost of last line is 0.
				// Else cost is square of extra spaces plus cost of putting line breaks in rest of words from j+1 to n-1.
				if (j == n - 1) {
					cost = 0;
				}
				else {
					cost = (k - currlen) * (k - currlen) + dp[j + 1];
				}

				// Check if this arrangement gives minimum cost for line starting with word arr[i].
				if (cost < dp[i]) {
					dp[i] = cost;
					ans[i] = j;
				}
			}
		}

		// Print starting index and ending index of words present in each line.
		final List<Integer> list = new ArrayList<>();

		i = 0;
		while (i < n) {
			list.add(i + 1);
			list.add(ans[i] + 1);
			i = ans[i] + 1;
		}

		return list;
	}
}