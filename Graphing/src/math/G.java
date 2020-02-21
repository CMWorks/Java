package math;

import java.awt.Color;
import java.awt.Graphics;

public class G {

	// 0 1 2 3 4 5
	// pac = [xMin, yMin, xMax, yMax, width, height]

	public static void line(double x1, double y1, double x2, double y2, int thickness, Color c, double[] pac,
			Graphics g) {
		g.setColor(c);
		int newX1 = (int) M.map(x1, pac[0], pac[2], 0, pac[4]);
		int newY1 = (int) M.map(y1, pac[1], pac[3], pac[5], 0);
		int newX2 = (int) M.map(x2, pac[0], pac[2], 0, pac[4]);
		int newY2 = (int) M.map(y2, pac[1], pac[3], pac[5], 0);
		for (int i = -thickness; i < thickness; i++) {
			g.drawLine(newX1, newY1 + i, newX2, newY2 + i);
			g.drawLine(newX1 + i, newY1, newX2 + i, newY2);
		}
	}

	public static void line(double r, double theta, double r2, double theta2, Color c, double[] pac, Graphics g) {
		g.setColor(c);
		double x1 = r * Math.cos(theta);
		double y1 = r * Math.sin(theta);
		double x2 = r2 * Math.cos(theta2);
		double y2 = r2 * Math.sin(theta2);
		int newX1 = (int) M.map(x1, pac[0], pac[2], 0, pac[4]);
		int newY1 = (int) M.map(y1, pac[1], pac[3], pac[5], 0);
		int newX2 = (int) M.map(x2, pac[0], pac[2], 0, pac[4]);
		int newY2 = (int) M.map(y2, pac[1], pac[3], pac[5], 0);
		g.drawLine(newX1, newY1, newX2, newY2);
	}

}
