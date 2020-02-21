package graph;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import functionMenu.Function;
import math.*;
import parser.CalculatorException;
import parser.Parser;

public class ParametricPloter {

	private double[] pac = new double[6];
	// 0 1 2 3 4 5
	// pac = [xMin, yMin, xMax, yMax, width, height]

	private double xStep, yStep;
	private Graphics g;

	public void updatePixels(Graphics g) {
		this.g = g;
		pac[0] = MainGraphable.xMin;
		pac[1] = MainGraphable.yMin;
		pac[2] = MainGraphable.xMax;
		pac[3] = MainGraphable.yMax;
		pac[4] = MainGraphable.width;
		pac[5] = MainGraphable.height;
		xStep = MainGraphable.xStep;
		yStep = MainGraphable.yStep;
		drawGrid();
		graphEq();
		// plotPoints();
		// g.fillRect(0, 0, img.getwidth(), img.getheight());
		// img.setRGB(200, 200, Color.red.getRGB());
	}

	public void drawGrid() {
		double gridLine = xStep / 10.0;
		if (pac[1] < 0 && pac[3] > 0) { // x-axis
			double xAxis = M.map(0, pac[1], pac[3], pac[5], 0);
			G.line(pac[0], 0, pac[2], 0, 1, Color.black, pac, g);
			for (double i = pac[0] - (pac[0] % xStep); i <= pac[2]; i += xStep) {
				if (i != 0) {
					int x = (int) M.map(i, pac[0], pac[2], 0, pac[4]);
					String num = "" + ((double) ((int) (i * 100))) / 100;
					G.line(i, 0 - gridLine, i, 0 + gridLine, 1, Color.black, pac, g);
					g.drawString(num, x - num.length() * 4, (int) xAxis + 20);
				}
			}
		}

		if (pac[0] < 0 && pac[2] > 0) { // y-axis
			double yAxis = M.map(0, pac[0], pac[2], 0, pac[4]);
			G.line(0, pac[1], 0, pac[3], 1, Color.black, pac, g);
			for (double i = pac[1] - (pac[1] % yStep); i <= pac[3]; i += yStep) {
				if (i != 0) {
					int y = (int) M.map(i, pac[1], pac[3], pac[5], 0);
					String num = "" + ((double) ((int) (i * 100))) / 100;
					G.line(0 - gridLine, i, 0 + gridLine, i, 1, Color.black, pac, g);
					g.drawString(num, (int) yAxis + 15, y + 5);
				}
			}
		}

	}

	public void graphEq() {
		ArrayList<Function> eq = MainGraphable.funMenu.getParFun().getList();
		double min = 0;
		double max = 6.28;
		double step = 30;
		try {
			min = Parser.eval(eq.get(eq.size() - 1).getEquation1(), new String[] { "" }, new Double[] {});
		} catch (NullPointerException e) {
		}
		try {
			max = Parser.eval(eq.get(eq.size() - 1).getEquation2(), new String[] { "" }, new Double[] {});
		} catch (NullPointerException e) {
		}
		try {
			step = Parser.eval(eq.get(eq.size() - 1).getEquation3(), new String[] { "" }, new Double[] {});
		} catch (NullPointerException e) {
		}
		step = Math.abs((max - min) / ((double) step));

		for (int j = 0; j < eq.size() - 1; j++) { // -1 since the last element in the list is the t-Max, t-Min, t-Step
			Color lineColor = eq.get(j).getColor();
			String fnx = eq.get(j).getEquation1().strip();
			String fny = eq.get(j).getEquation2().strip();
			if (!(fnx.equals("") || fnx.equalsIgnoreCase("NAN") || fnx.equalsIgnoreCase("null") || fny.equals("")
					|| fny.equalsIgnoreCase("NAN") || fny.equalsIgnoreCase("null"))) {
				for (double i = min; i < max; i += step) {
					double x = Parser.eval(fnx, new String[] { "t" }, new Double[] { i });
					double y = Parser.eval(fny, new String[] { "t" }, new Double[] { i });
					double x2 = Parser.eval(fnx, new String[] { "t" }, new Double[] { i + step });
					double y2 = Parser.eval(fny, new String[] { "t" }, new Double[] { i + step });

					if (y != Double.NaN && y != Double.NEGATIVE_INFINITY && y != Double.POSITIVE_INFINITY) {
						G.line(x, y, x2, y2, 1, lineColor, pac, g);
					}
					// line(i, F.f(i), i + step, F.f(i+step), 1, Color.red);
					// line(i, Math.cos(i), i + step, Math.cosh(i + step), 2, Color.blue);
				}
			}
		}
		/*
		 * double step = (F.gettMax()-F.gettMin())/F.getSteps(); double min =
		 * F.gettMin(); double max = F.gettMax(); for (double i = min; i < max; i+=step)
		 * { double x = F.x(i); double y = F.y(i); double x2 = F.x(i+step); double y2 =
		 * F.y(i+step);
		 * 
		 * if (y != Double.NaN && y != Double.NEGATIVE_INFINITY && y !=
		 * Double.POSITIVE_INFINITY) { G.line(x, y, x2, y2, 1, Color.red,pac,g); }
		 * //line(i, F.f(i), i + step, F.f(i+step), 1, Color.red); //line(i,
		 * Math.cos(i), i + step, Math.cosh(i + step), 2, Color.blue); }/
		 **/

	}

}

/*
 * public void plotPoints() { for (int i = 0; i < MainGraphable.points.length-1;
 * i++) { if (MainGraphable.points[i]!=null &&
 * MainGraphable.points[i].getPlot()!= null &&
 * MainGraphable.points[i].getPlot().equals("Parametric")) { if
 * (Math.abs(MainGraphable.points[i].getY()-MainGraphable.points[i+1].getY())<
 * 100) { double x = MainGraphable.points[i].getX(); double y =
 * MainGraphable.points[i].getY(); double x2 = MainGraphable.points[i+1].getX();
 * double y2 = MainGraphable.points[i+1].getY(); G.line(x, y, x2, y2, 1,
 * Color.red,pac,g); } }else { break; } } } public void updatePoints() { for
 * (Point p : MainGraphable.points) { p = null; } double step = (pac[2] -
 * pac[0]) / (MainGraphable.points.length-1); int n = 0; for (double i = pac[0];
 * i < pac[2]; i += step) { if (n<MainGraphable.points.length) { double x =
 * F.x(i); double y = F.y(i); MainGraphable.points[n].setXY(x, y);
 * MainGraphable.points[n].setPlot("Parametric"); n++; }else { break; } } }
 */
