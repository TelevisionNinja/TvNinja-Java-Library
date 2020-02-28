/**
 * 
 */
package televisionninja.lib.mathutils.unitsutils;

/**
 * @author TelevisionNinja
 *
 */
public class TemperatureConversion {
	/**
	 * 
	 * @param celsius
	 * @return
	 * @author TelevisionNinja
	 */
	public static double celsiusToFahrenheit(final double celsius) {
		return celsius / 5d * 9d + 32d;
	}

	/**
	 * 
	 * @param c
	 * @return
	 * @author TelevisionNinja
	 */
	public static double celsiusToKelvin(final double c) {
		return c + 273.15d;
	}

	/**
	 * 
	 * @param fahrenheit
	 * @return
	 * @author TelevisionNinja
	 */
	public static double fahrenheitToCelsius(final double fahrenheit) {
		return (fahrenheit - 32d) / 9d * 5d;
	}

	/**
	 * 
	 * @param f
	 * @return
	 * @author TelevisionNinja
	 */
	public static double fahrenheitToKelvin(final double f) {
		return celsiusToKelvin(fahrenheitToCelsius(f));
	}

	/**
	 * 
	 * @param k
	 * @return
	 * @author TelevisionNinja
	 */
	public static double kelvinToCelsius(final double k) {
		return k - 273.15d;
	}

	/**
	 * 
	 * @param k
	 * @return
	 * @author TelevisionNinja
	 */
	public static double kelvinToFahrenheit(final double k) {
		return celsiusToFahrenheit(kelvinToCelsius(k));
	}
}