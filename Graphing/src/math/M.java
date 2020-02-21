package math;

public class M {

	/**
	 * Re-maps a number from one range to another
	 * 
	 * @param value  The incoming value to be converted
	 * @param start1 Lower bound of the value's current range
	 * @param stop1  Upper bound of the value's current range
	 * @param start2 Lower bound of the value's target range
	 * @param stop2  Upper bound of the value's target range
	 */
	public static double map(double value, double start1, double stop1, double start2, double stop2) {
		return (value - start1) / (stop1 - start1) * (stop2 - start2) + start2;
	}

	public static double[] map(double[] point, double[] upperLeftCurrent, double[] bottemRightCurrent,
			double[] upperLeftTarget, double[] bottemRightTarget) {
		double a = map(point[0], upperLeftCurrent[0], bottemRightCurrent[0], upperLeftTarget[0], bottemRightTarget[0]);
		double b = map(point[1], upperLeftCurrent[1], bottemRightCurrent[1], upperLeftTarget[1], bottemRightTarget[1]);
		return new double[] { a, b };
	}

}
