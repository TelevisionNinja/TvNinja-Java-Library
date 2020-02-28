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
// Java program to print all the possible steps to change a string to another
public class ConvertStringToAnother {
	private static int dp[][];

	// create List of lists that will store all sets of operations
	private static List<List<String>> arrs = new ArrayList<>();

	// Function to compute the DP matrix
	public static void editDP(final String s1, final String s2) {
		final int l1 = s1.length(),
				l2 = s2.length();
		final int[][] DP = new int[l1 + 1][l2 + 1];

		// initilize by the maximum edits possible
		for (int i = 0; i <= l1; i++) {
			DP[i][0] = i;
		}
		for (int j = 0; j <= l2; j++) {
			DP[0][j] = j;
		}

		// Compute the DP matrix
		for (int i = 1; i <= l1; i++) {
			for (int j = 1; j <= l2; j++) {

				// if the characters are same
				// no changes required
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					DP[i][j] = DP[i - 1][j - 1];
				} else {

					// minimu of three operations possible
					DP[i][j] = min(DP[i - 1][j - 1], DP[i - 1][j], DP[i][j - 1]) + 1;
				}
			}
		}

		// initialize to global array
		dp = DP;
	}

	// Driver Code
	public static void main(final String[] args) throws Exception {
		final String s1 = "abcdef";
		final String s2 = "axcdfdh";

		// calculate the DP matrix
		editDP(s1, s2);

		// Function to print all ways
		printWays(s1, s2, new ArrayList<String>());
	}

	// Function to find the minimum of three
	private static int min(final int a, final int b, final int c) {
		final int z = Math.min(a, b);
		return Math.min(z, c);
	}

	// Function to print all ways
	private static void printAllChanges(final String s1, final String s2, final List<String> changes) {

		int i = s1.length(),
				j = s2.length();

		// Iterate till end
		while (true) {
			if (i == 0 || j == 0) {
				// Add this list to our List of lists.
				arrs.add(changes);
				break;
			}

			// If same
			if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
				i--;
				j--;
			}

			else {
				boolean if1 = false,
						if2 = false;
				// Replace
				if (dp[i][j] == dp[i - 1][j - 1] + 1) {

					// Add this step
					changes.add("Change " + s1.charAt(i - 1) + " to " + s2.charAt(j - 1));
					i--;
					j--;

					// note whether this 'if' was true.
					if1 = true;
				}

				// Delete
				if (dp[i][j] == dp[i - 1][j] + 1) {
					if (if1 == false) {
						changes.add("Delete " + s1.charAt(i - 1));
						i--;
					}
					else {
						// If the previous method was true, create a new list as a copy of previous.
						final List<String> changes2 = new ArrayList<>();
						changes2.addAll(changes);

						// Remove last operation
						changes2.remove(changes.size() - 1);

						// Add this new operation
						changes2.add("Delete " + s1.charAt(i));

						// initiate new new instance of this
						// function with remaining substrings
						printAllChanges(s1.substring(0, i), s2.substring(0, j + 1), changes2);
					}

					if2 = true;
				}

				// Add charater step
				if (dp[i][j] == dp[i][j - 1] + 1) {
					if (if1 == false && if2 == false) {
						changes.add("Add " + s2.charAt(j - 1));
						j--;
					}
					else {

						// Add steps
						final List<String> changes2 = new ArrayList<>();
						changes2.addAll(changes);
						changes2.remove(changes.size() - 1);
						changes2.add("Add " + s2.charAt(j));

						// Recursively call for the next steps
						printAllChanges(s1.substring(0, i + 1), s2.substring(0, j), changes2);
					}
				}
			}
		}
	}

	public static void printWays(final String s1, final String s2, final List<String> changes) {

		// Function to print all the ways
		printAllChanges(s1, s2, new ArrayList<String>());

		int i = 1;

		// print all the possible ways
		for (final List<String> ar : arrs) {
			System.out.println("\nMethod " + i++ + " : \n");
			for (final String s : ar) {
				System.out.println(s);
			}
		}
	}
}