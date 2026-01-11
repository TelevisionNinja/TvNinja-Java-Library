package televisionninja.lib.datastructures;

import java.util.HashMap;
import java.util.HashSet;

public class AhoCorasickFilter extends AhoCorasick {
	public String filter(final String string, String censoredString) {
		if (censoredString == null) {
			censoredString = "*";
		}

	    AhoCorasickNode node = this.root;
	    HashMap<Integer, Integer> indices = new HashMap<>();

	    // empty string case is removed as it does not make sense in a filtering function

	    int i = 0;

	    while (i < string.length()) {
	        final char c = string.charAt(i);
	        if (node.children.containsKey(c)) {
	            node = node.children.get(c);
	            i++;

	            if (!node.outputLinks.isEmpty()) {
	                for (AhoCorasickNode outputNode : node.outputLinks) { // node.outputLinks.values()
	                    final int startIndex = i - outputNode.length;

	                    if (indices.containsKey(startIndex)) {
	                    	indices.put(startIndex, Math.max(indices.get(startIndex), outputNode.length));
	                    }
	                    else {
	                    	indices.put(startIndex, outputNode.length);
	                    }
	                }
	            }
	        }
	        else if (node == this.root) {
	            i++;
	        }
	        else {
	            node = node.suffixLink;
	        }
	    }

	    //---------

	    StringBuilder output = new StringBuilder();
	    int j = 0;

	    while (j < string.length()) {
	        if (indices.containsKey(j)) {
	            // the found longer bound is always in the array bounds because of the DFA
	            // loop is unrolled by 1 iteration to have all loop logic in the required iterations
	            // all accept indices will have return lengths greater than 0
	            output.append(censoredString);

	            int endIndex = j + indices.get(j);
	            j++;

	            while (j < endIndex) {
	                /*
	                intersection case:
	                0 * * *
	                  1 * * * * *

	                subset case:
	                0 * * * *
	                  1 * *

	                disjoint case:
	                0 * * * _ _
	                            6 * * *
	                */

	                if (indices.containsKey(j)) {
	                    final int secondEndIndex = j + indices.get(j);
	                    endIndex = Math.max(secondEndIndex, endIndex);
	                }

	                output.append(censoredString);
	                j++;
	            }
	        }
	        else {
	            output.append(string.charAt(j));
	            j++;
	        }
	    }

	    return output.toString();
	}

	public String filter(final String string) {
		return this.filter(string, "*");
	}

	public String filterAndIgnoreChars(final String string, String censoredString, HashSet<Character> ignoreChars) {
		if (censoredString == null) {
			censoredString = "*";
		}

	    AhoCorasickNode node = this.root;
	    HashMap<Integer, Integer> indices = new HashMap<>();

	    // empty string case is removed as it does not make sense in a filtering function

	    int i = 0;

	    while (i < string.length()) {
	        final char c = string.charAt(i);
	        
	        if (ignoreChars.contains(c)) {
	        	i++;
	        }
	        else if (node.children.containsKey(c)) {
	            node = node.children.get(c);

	            if (!node.outputLinks.isEmpty()) {
	                for (AhoCorasickNode outputNode : node.outputLinks) { // node.outputLinks.values()
	                    if (outputNode.length != 0) {
		                    if (indices.containsKey(i)) {
		                    	indices.put(i, Math.max(indices.get(i), outputNode.length));
		                    }
		                    else {
		                    	indices.put(i, outputNode.length);
		                    }
	                    }
	                }
	            }

	            i++;
	        }
	        else if (node == this.root) {
	            i++;
	        }
	        else {
	            node = node.suffixLink;
	        }
	    }

	    //---------

	    StringBuilder output = new StringBuilder();
	    int j = string.length();

	    while (j > 0) {
	    	j--;

	    	if (ignoreChars.contains(string.charAt(j)) || !indices.containsKey(j)) {
	    		output.append(string.charAt(j));
	    	}
	    	else {
	            // the found longer bound is always in the array bounds because of the DFA
	            // loop is unrolled by 1 iteration to have all loop logic in the required iterations
	            // all accept indices will have return lengths greater than 0
	            output.append(censoredString);

	            int length = indices.get(j);
	            int k = 1;

	            while (k < length) {
	            	j--;

	                /*
	                intersection case:
	                0 * * *
	                  1 * * * * *

	                subset case:
	                0 * * * *
	                  1 * *

	                disjoint case:
	                0 * * * _ _
	                            6 * * *
	                */

	            	if (ignoreChars.contains(string.charAt(j))) {
	            		output.append(string.charAt(j));
	            	}
	            	else {
		                if (indices.containsKey(j)) {
		                    final int otherLength = indices.get(j);
		                    
		                    if (length - k < otherLength) {
		                    	length = otherLength + k; // no minus 1 because k is 1 behind j here
		                    }
		                }

		                output.append(censoredString);
		                k++;
	            	}
	            }
	        }
	    }

	    return output.reverse().toString();
	}
}
