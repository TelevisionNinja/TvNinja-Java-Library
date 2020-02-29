/**
 * 
 */
package televisionninja.lib.mathutils;

/**
 * @author TelevisionNinja
 *
 */
public class PhysicsUtils {
	/**
	 * 
	 * @param mass
	 * @param velocity
	 * @return
	 */
	public static double kineticEnergy(final double mass, final double velocity) {
		return mass / 2 * velocity * velocity;
	}

	/**
	 * 
	 * @param mass
	 * @param velocity
	 * @return
	 */
	public static double momentum(final double mass, final double velocity) {
		return mass * velocity;
	}
}