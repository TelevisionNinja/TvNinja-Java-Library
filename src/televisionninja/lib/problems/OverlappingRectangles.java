/**
 * 
 */
package televisionninja.lib.problems;

/**
 * @author TelevisionNinja
 *
 */
public class OverlappingRectangles {
	/**
	 * coordinate object for the rectangle class
	 * 
	 * @author TelevisionNinja
	 *
	 */
	public static class Coordinate {
		public double x,
		y;
		public Coordinate(final double x, final double y) {
			this.x = x;
			this.y = y;
		}
	}

	/**
	 * rectangle class to solve the problem
	 * 
	 * @author TelevisionNinja
	 *
	 */
	public static class Rectangle {
		public Coordinate one,
		two;
		public Rectangle(final Coordinate one, final Coordinate two) {
			this.one = one;
			this.two = two;
		}
	}

	/**
	 * puts the coordinates of the rect in the correct order
	 * 
	 * @param z
	 */
	private static void cornerCorrect(final Rectangle z) {
		if (z.one.y > z.two.y) {
			final double temp = z.one.y;
			z.one.y = z.two.y;
			z.two.y = temp;
		}
	}

	/**
	 * length method
	 * 
	 * find the x length of the intersection
	 * find the y length of the intersection
	 * multiply the found lengths to get the area
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double findArea(final Rectangle a, final Rectangle b) {
		double length = 0,
				width = 0;

		sortByX(a);
		sortByX(b);

		cornerCorrect(a);
		cornerCorrect(b);

		double frontStick = b.one.x - a.one.x;
		if (b.one.x < a.one.x) {
			frontStick = -frontStick;
		}

		double backStick = b.two.x - a.two.x;
		if (b.two.x < a.two.x) {
			backStick = -backStick;
		}

		double beginOfStick = a.one.x;
		if (a.one.x > b.one.x) {
			beginOfStick = b.one.x;
		}

		double endOfStick = b.two.x;
		if (a.two.x > b.two.x) {
			endOfStick = a.two.x;
		}

		length = endOfStick - beginOfStick - frontStick - backStick;

		double bottomStick = b.one.y - a.one.y;
		if (b.one.y < a.one.y) {
			bottomStick = -bottomStick;
		}

		double topStick = b.two.y - a.two.y;
		if (b.two.y < a.two.y) {
			topStick = -topStick;
		}

		double rootOfStick = b.two.y;
		if (a.two.y > b.two.y) {
			rootOfStick = a.two.y;
		}

		double heightOfStick = a.one.y;
		if (a.one.y > b.one.y) {
			heightOfStick = b.one.y;
		}

		width = rootOfStick - heightOfStick - topStick - bottomStick;

		return length * width;
	}

	/**
	 * sorts the coordinate by the x values
	 * 
	 * @param z
	 */
	private static void sortByX(final Rectangle z) {
		if (z.one.x > z.two.x) {
			final Coordinate temp = z.one;
			z.one = z.two;
			z.two = temp;
		}
	}
}