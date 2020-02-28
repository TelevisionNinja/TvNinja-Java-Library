/**
 * 
 */
package televisionninja.lib.mathutils;

/**
 * @author TelevisionNinja
 *
 */
public class Geometry {
	/**
	 * 
	 * @param r
	 * @return
	 * @author TelevisionNinja
	 */
	public static double areaOfCircle(final double r) {
		return MathUtils.pi * r * r;
	}

	/**
	 * 
	 * @param a
	 * @return
	 * @author TelevisionNinja
	 */
	public static double areaOfHexagon(final double a) {
		return a / 2d * 3d * Math.sqrt(3d) * a;
	}

	/**
	 * 
	 * @param b
	 * @param h
	 * @return
	 * @author TelevisionNinja
	 */
	public static double areaOfParallelogram(final double b, final double h) {
		return b * h;
	}

	/**
	 * 
	 * @param l
	 * @param w
	 * @return
	 * @author TelevisionNinja
	 */
	public static double areaOfRectangle(final double l, final double w) {
		return l * w;
	}

	/**
	 * 
	 * @param s
	 * @return
	 * @author TelevisionNinja
	 */
	public static double areaOfSquare(final double s) {
		return s * s;
	}

	/**
	 * 
	 * @param b1
	 * @param b2
	 * @param h
	 * @return
	 * @author TelevisionNinja
	 */
	public static double areaOfTrapezoid(final double b1, final double b2, final double h) {
		return h / 2d * (b1 + b2);
	}

	/**
	 * 
	 * @param b
	 * @param h
	 * @return
	 * @author TelevisionNinja
	 */
	public static double areaOfTriangle(final double b, final double h) {
		return h / 2d * b;
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 * @author TelevisionNinja
	 */
	public static double areaOfTriangle(final double a, final double b, final double c) {
		final double p = (a + b + c) / 2d;
		return Math.sqrt(p * (p - a) * (p - b) * (p - c));
	}

	/**
	 * 
	 * @param r
	 * @return
	 * @author TelevisionNinja
	 */
	public static double circumferenceOfCircle(final double r) {
		return 2d * MathUtils.pi * r;
	}

	/**
	 * 
	 * @param n
	 * @return
	 * 		degrees
	 * @author TelevisionNinja
	 */
	public static double interiorAngleofNSidedPolygon(final double n) {
		return (n - 2d) / n * 180d;
	}

	/**
	 * 
	 * @param r
	 * @param h
	 * @return
	 * @author TelevisionNinja
	 */
	public static double lateralSurfaceAreaOfCone(final double r, final double h) {
		return MathUtils.pi * r * Math.sqrt(h * h + r * r);
	}

	/**
	 * 
	 * @param r
	 * @param h
	 * @return
	 * @author TelevisionNinja
	 */
	public static double lateralSurfaceAreaOfCylinder(final double r, final double h) {
		return 2d * MathUtils.pi * r * h;
	}

	/**
	 * 
	 * @param s
	 * @return
	 * @author TelevisionNinja
	 */
	public static double perimeterOfEquilateralTriangle(final double s) {
		return 3d * s;
	}

	/**
	 * 
	 * @param s
	 * @return
	 * @author TelevisionNinja
	 */
	public static double perimeterOfHexagon(final double s) {
		return 6d * s;
	}

	/**
	 * 
	 * @param l
	 * @param w
	 * @return
	 * @author TelevisionNinja
	 */
	public static double perimeterOfRectangle(final double l, final double w) {
		return 2d * (l + w);
	}

	/**
	 * 
	 * @param s
	 * @return
	 * @author TelevisionNinja
	 */
	public static double perimeterOfSquare(final double s) {
		return 4d * s;
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 * @author TelevisionNinja
	 */
	public static double perimeterOfTriangle(final double a, final double b, final double c) {
		return a + b + c;
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 * @author TelevisionNinja
	 */
	public static double pythagoreanTheoremHypotenuse(final double a, final double b) {
		return Math.sqrt(a * a + b * b);
	}

	/**
	 * 
	 * @param a
	 * @param c
	 * @return
	 * @author TelevisionNinja
	 */
	public static double pythagoreanTheoremLeg(final double a, final double c) {
		return Math.sqrt(c * c - a * a);
	}

	/**
	 * 
	 * @param n
	 * @return
	 * 		degrees
	 * @author TelevisionNinja
	 */
	public static double sumOfInteriorAnglesOfPolygon(final double n) {
		return 180d * (n - 2d);
	}

	/**
	 * 
	 * @param r
	 * @param h
	 * @return
	 * @author TelevisionNinja
	 */
	public static double surfaceAreaOfCone(final double r, final double h) {
		return MathUtils.pi * r * (r + Math.sqrt(h * h + r * r));
	}

	/**
	 * 
	 * @param s
	 * @return
	 * @author TelevisionNinja
	 */
	public static double surfaceAreaOfCube(final double s) {
		return s * s * 6d;
	}

	/**
	 * 
	 * @param r
	 * @param h
	 * @return
	 * @author TelevisionNinja
	 */
	public static double surfaceAreaOfCylinder(final double r, final double h) {
		return 2d * MathUtils.pi * r * (h + r);
	}

	/**
	 * 
	 * @param r
	 * @return
	 * @author TelevisionNinja
	 */
	public static double surfaceAreaOfSphere(final double r) {
		return 4d * MathUtils.pi * r * r;
	}

	/**
	 * 
	 * @param A
	 * 		area of base
	 * @param b
	 * 		base length
	 * @param sl
	 * 		slant height
	 * @return
	 * @author TelevisionNinja
	 */
	public static double surfaceAreaOfTriangluarPyramid(final double A, final double b, final double sl) {
		return sl / 2d * 3d * b + A;
	}

	/**
	 * 
	 * @param r
	 * @param h
	 * @return
	 * @author TelevisionNinja
	 */
	public static double volumeOfCone(final double r, final double h) {
		return h / 3d * MathUtils.pi * r * r;
	}

	/**
	 * 
	 * @param s
	 * @return
	 * @author TelevisionNinja
	 */
	public static double volumeOfCube(final double s) {
		return s * s * s;
	}

	/**
	 * 
	 * @param r
	 * @param h
	 * @return
	 * @author TelevisionNinja
	 */
	public static double volumeOfCylinder(final double r, final double h) {
		return MathUtils.pi * r * r * h;
	}

	/**
	 * 
	 * @param l
	 * @param w
	 * @param h
	 * @return
	 * @author TelevisionNinja
	 */
	public static double volumeOfRectangularPyramid(final double l, final double w, final double h) {
		return h / 3d * l * w;
	}

	/**
	 * 
	 * @param r
	 * @return
	 * @author TelevisionNinja
	 */
	public static double volumeOfSphere(final double r) {
		return r / 3d * 4d * MathUtils.pi * r * r;
	}

	/**
	 * 
	 * @param a
	 * 		base edge
	 * @param h
	 * @return
	 * @author TelevisionNinja
	 */
	public static double volumeOfSquarePyramid(final double a, final double h) {
		return h / 3d * a * a;
	}

	/**
	 * 
	 * @param A
	 * 		area of base
	 * @param h
	 * @return
	 * @author TelevisionNinja
	 */
	public static double volumeOfTriangluarPyramid(final double A, final double h) {
		return h / 3d * A;
	}
}