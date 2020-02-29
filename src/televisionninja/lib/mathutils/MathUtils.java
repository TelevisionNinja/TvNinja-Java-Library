package televisionninja.lib.mathutils;

import televisionninja.lib.numberutils.NumberUtils;

/**
 * @author TelevisionNinja
 *
 */
public class MathUtils extends NumberUtils {
	final public static double pi = 3.141592653589793238462643383279502884197169399375105820974944592307816406286d,
			e = 2.71828182845904523536028747135266249775724709369995957496696762772407663035354759457138217852516642742746639193d,
			phi = 1.618033988749894848204586834365638117720309179805762862135448622705260462818902449707207204189391137484754088d;

	/**
	 * Johann Carl Friedrich Gauss formula
	 * 
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public static long addSequence(final long n) {
		return (n + 1) / 2 * n;
	}

	/**
	 * Johann Carl Friedrich Gauss formula
	 * 
	 * @param start
	 * @param end
	 * @return
	 * @author TelevisionNinja
	 */
	public static long addSequence(final long start, final long end) {
		return (end - start + 1) / 2 * (start + end);
	}

	/**
	 * up to 6 decimal places
	 * puts the number over 10^n and then simplifies
	 * 
	 * @param d
	 * @return
	 * @author TelevisionNinja
	 */
	public static String decimalToFraction_1(final String d) {
		final int index = d.indexOf(".");
		final double denom = Math.pow(10, d.length() - index - 1);
		String str = Double.toString(denom);
		str = str.substring(0, str.length() - 2);

		return simplifyFraction(new StringBuilder(d).deleteCharAt(index).toString() + "/" + str);
	}
	
	/**
	 * calculus 2 sequence method
	 * 
	 * @param d
	 * @return
	 * @author TelevisionNinja
	 */
	public static double decimalToFraction_2(final double d, double accuracy) {
		return d / (1d - accuracy);
	}
	
	/**
	 * calculus 2 sequence method--------------------------------------------------------------------------------
	 * 
	 * @param d
	 * @return
	 * @author TelevisionNinja
	 */
	public static double decimalToFraction_3(final String d) {
		final int index = d.indexOf("."),
				accuracy = d.length() -  - 1;
		
		return Double.valueOf(d) / (1d - Math.pow(d.charAt(index), -accuracy));
	}

	/**
	 * 
	 * @param d
	 * @return
	 * @author TelevisionNinja
	 */
	public static double degreeToRad(final double d) {
		return pi / 180d * d;
	}

	/**
	 * 
	 * @param y1
	 * @param y2
	 * @param x1
	 * @param x2
	 * @return
	 * @author TelevisionNinja
	 */
	public static double distanceFormula(final double y1, final double y2, final double x1, final double x2) {
		final double x = x2 - x1,
				y = y2 - y1;
		return Math.sqrt(x * x + y * y);
	}

	/**
	 * recursion
	 * 
	 * textbook method
	 * 
	 * @param index
	 * @return
	 * @author TelevisionNinja
	 */
	public static long fibonacciNumberGenerator_1(final long index) {
		if (index == 0) {
			return 0;
		}
		else if (index == 1) {
			return 1;
		}
		return fibonacciNumberGenerator_1(index - 1) + fibonacciNumberGenerator_1(index - 2);
	}

	/**
	 * for loop method
	 * 
	 * textbook method
	 * 
	 * @param index
	 * @return
	 * @author TelevisionNinja
	 */
	public static long fibonacciNumberGenerator_2(final long index) {
		long f0 = 0,
				f1 = 1,
				f2 = 1;
		if (index == 0) {
			return f0;
		}
		else if (index == 1) {
			return f1;
		}
		else if (index == 2) {
			return f2;
		}
		for (long i = 3; i <= index; i++) {
			f0 = f1;
			f1 = f2;
			f2 = f0 + f1;
		}
		return f2;
	}

	/**
	 * uses Euclid method
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static long gcf(final long[] arr) {
		long result = arr[0];
		for (int i = 1; i < arr.length; i++) {
			result = gcf_3(arr[i], result);
		}
		return result;
	}

	/**
	 * while loop
	 * 
	 * textbook method
	 * 
	 * @param n1
	 * @param n2
	 * @return
	 * @author TelevisionNinja
	 */
	public static long gcf_1(final long n1, final long n2) {
		long gcd = 1,
				k = 2;
		while (k <= n1 && k <= n2) {
			if (n1 % k == 0 && n2 % k == 0) {
				gcd = k;
			}
			k++;
		}
		return gcd;
	}

	/**
	 * for loop
	 * 
	 * textbook method
	 * 
	 * @param m
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public static long gcf_2(final long m, final long n) {
		if (m % n == 0) {
			return n;
		}
		long gcd = 1;
		for (long k = n / 2; k >= 1; k--) {
			if (m % k == 0 && n % k == 0) {
				gcd = k;
				break;
			}
		}
		return gcd;
	}

	/**
	 * Euclid method
	 * 
	 * textbook method
	 * 
	 * @param m
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public static long gcf_3(final long m, final long n) {
		if (m % n == 0) {
			return n;
		}
		return gcf_3(n, m % n);
	}

	/**
	 * 
	 * @param x
	 * @return
	 * @author TelevisionNinja
	 */
	public static boolean isPowerOfTwo(final long x) {
		return x != 0 && ((x & (x - 1)) == 0);
	}

	/**
	 * online method
	 * 
	 * @param number
	 * @return
	 * @author TelevisionNinja
	 */
	public static boolean isPrime_1(final long number) {
		if (number < 2) {
			return false;
		}
		for (long i = 2; i * i < number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * online method
	 * 
	 * @param num
	 * @return
	 * @author TelevisionNinja
	 */
	public static boolean isPrime_2(final long num) {
		if (num < 2 || num > 2 && num % 2 == 0) {
			return false;
		}
		for (long i = 3; i <= Math.sqrt(num); i += 2){
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * online method
	 * 
	 * @param number
	 * @return
	 * @author TelevisionNinja
	 */
	public static boolean isPrime_3(final long number) {
		if (number == 2 || number == 3) {
			return true;
		}
		else if (number < 2 || number % 2 == 0 || number % 3 == 0) {
			return false;
		}

		for (long i = 6; i <=  Math.sqrt(number); i += 6) {
			if (number % (i - 1) == 0 || number % (i + 1) == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * online method
	 * 
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public static boolean isPrime_4(final long n) {
		if (n <= 1) {
			return false;
		}
		else if (n <= 3) {
			return true;
		}
		else if (n % 2 == 0 || n % 3 == 0) {
			return false;
		}
		for (long i = 5; i * i <= n; i += 6) {
			if (n % i == 0 || n % (i + 2) == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * online method
	 * 
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public static boolean isPrime_5(final long n) {
		if (n < 4) {
			return n > 1;
		}
		else if (n % 2 == 0 || n % 3 == 0) {
			return false;
		}
		for (long i = 5; i * i <= n; i += 6) {
			if (n % i == 0 || n % (i + 2) == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * TelevisionNinja method
	 * 
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public static boolean isProbablePrime(final long n) {
		if (n < 4) {
			return n > 1;
		}
		else if (n % 2 == 0 || n % 3 == 0) {
			return false;
		}
		for (long i = 5; i * i <= Math.sqrt(n); i += 6) {
			if (n % i == 0 || n % (i + 2) == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * for loop
	 * 
	 * @param num
	 * @param base
	 * @return
	 * 		the largest power that the base can go to to match or be near the num parameter
	 * 		returns -1 if no power is found
	 * @author TelevisionNinja
	 */
	public static long largestPower_1(final long num, final long base) {
		long n = 0;
		for (long x = 0; Math.pow(base, n) <= num; x++) {
			n = x;
		}
		return --n;
	}

	/**
	 * while loop
	 * 
	 * @param num
	 * @param base
	 * @return
	 * 		the largest power that the base can go to to match or be near the num parameter
	 * 		returns -1 if no power is found
	 * @author TelevisionNinja
	 */
	public static long largestPower_2(final long num, final long base) {
		long n = 0;
		while (Math.pow(base, n) <= num) {
			n++;
		}
		return --n;
	}

	/**
	 * more efficient
	 * 
	 * @param num
	 * @param base
	 * @return
	 * 		the largest power that the base can go to to match or be near the num parameter
	 * 		returns -2 if no power is found
	 * @author TelevisionNinja
	 */
	public static long largestPower_3(final long num, final long base) {
		long p = 0;
		while (Math.pow(base, p) <= num) {
			p += 2;
		}
		p -= 2;
		if (Math.pow(base, p + 1) <= num) {
			return p + 1;
		}
		return p;
	}

	/**
	 * 
	 * @param n1
	 * @param n2
	 * @return
	 * @author TelevisionNinja
	 */
	public static long lcm(final long n1, final long n2) {
		return n2 / gcf_3(n1, n2) * n1;
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static long lcm(final long[] arr) {
		long result = arr[0];
		for (int i = 1; i < arr.length; i++) {
			result = lcm(arr[i], result);
		}
		return result;
	}

	/**
	 * log(n)/log(base)
	 * 
	 * @param base
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public static double log(final long base, final long n) {
		return Math.log(n) / Math.log(base);
	}

	/**
	 * 
	 * @param x1
	 * @param x2
	 * @param y1
	 * @param y2
	 * @return
	 * @author TelevisionNinja
	 */
	public static double[] midpoint(final double x1, final double x2, final double y1, final double y2) {
		return new double[] {(x1 + x2) / 2d, (y1 + y2) / 2d};
	}

	/**
	 * 
	 * @param r
	 * @return
	 * @author TelevisionNinja
	 */
	public static double radToDegree(final double r) {
		return 180d / pi * r;
	}

	/**
	 * n^(1/root)
	 * 
	 * @param base
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public static double root_1(final int base, final double n) {
		return Math.pow(n, 1d / base);
	}

	/**
	 * e^(ln(n)/base)
	 * 
	 * @param base
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public static double root_2(final int base, final double n) {
		return Math.pow(MathUtils.e, Math.log(n) / base);
	}

	/**
	 * newton's method
	 * 
	 * @param base
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public static double root_3(final int base, final double n) {
		if (n < 0) {
			return -1;
		}
		else if (n == 0) {
			return 0;
		}
		else {
			double x_prev = n,
					x = n / base; // starting "guessed" value
			while (Math.abs(x - x_prev) > 0) {
				x_prev = x;
				x = ((base - 1d) * x + n / Math.pow(x, base - 1d)) / base;
			}
			return x;
		}
	}

	/**
	 * onilne method
	 * 
	 * @param value
	 * @param decimalPlaces
	 * @return
	 * @author TelevisionNinja
	 */
	public static double roundToDecimalPlace(double value, int decimalPlaces) {
	    int pow = 10;
	    for (int i = 1; i < decimalPlaces; i++) {
	        pow *= 10;
	    }
	    double tmp = value * pow,
	    		tmpSub = tmp - (int) tmp;

	    return ((double) ((int) (value >= 0 ? (tmpSub >= 0.5f ? tmp + 1 : tmp) : (tmpSub >= -0.5f ? tmp : tmp - 1)))) / pow;
	}

	/**
	 * 
	 * @param fraction
	 * 		-input string
	 * @return
	 * 		-simplified fraction as a string
	 * @author TelevisionNinja
	 */
	public static String simplifyFraction(final String fraction) {
		final String[] arr = fraction.split("/");
		final long numerator = Long.parseLong(arr[0]),
				denominator = Long.parseLong(arr[1]),
				gcf = gcf_3(numerator, denominator);
		if (denominator == gcf) {
			return Long.toString(numerator / gcf);
		}
		return (numerator / gcf) + "/" + (denominator / gcf);
	}
	
	/**
	 * 
	 * @param y1
	 * @param y2
	 * @param x1
	 * @param x2
	 * @return
	 * @author TelevisionNinja
	 */
	public static double slope(final double y1, final double y2, final double x1, final double x2) {
		return (y2 - y1) / (x2 - x1);
	}
}