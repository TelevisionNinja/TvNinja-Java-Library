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
	public static double momentum(double mass, double velocity) {
		return mass * velocity;
	}
	
	/**
	 * 
	 * @param mass
	 * @param velocity
	 * @return
	 */
	public static double kineticEnergy(double mass, double velocity) {
		return mass / 2 * velocity * velocity;
	}
}