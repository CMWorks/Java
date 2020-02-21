package graph;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import functionMenu.Function;
import math.G;
import math.M;
import parser.Parser;

public class RectangularPloter {

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
		ArrayList<Function> eq = MainGraphable.funMenu.getRecFun().getList();
		double step = (pac[2] - pac[0]) / 1000;
		for (Function function : eq) {
			Color lineColor = function.getColor();
			String fn = function.getEquation1().strip();
			if (!(fn.equals("") || fn.equalsIgnoreCase("NAN") || fn.equalsIgnoreCase("null"))) {
				for (double i = pac[0]; i < pac[2]; i += step) {
					double y = Parser.eval(fn, new String[] { "x" }, new Double[] { i });
					double y2 = Parser.eval(fn, new String[] { "x" }, new Double[] { i + step });
					if (y != Double.NaN && y != Double.NEGATIVE_INFINITY && y != Double.POSITIVE_INFINITY) {
						G.line(i, y, i + step, y2, 1, lineColor, pac, g);
					}
					// line(i, F.f(i), i + step, F.f(i+step), 1, Color.red);
					// line(i, Math.cos(i), i + step, Math.cosh(i + step), 2, Color.blue);
				}
			}
		}
	}

}

/*
 * 
 * public void updatePoints() { for (Point p : MainGraphable.points) { p = null;
 * } double step = (pac[2] - pac[0]) / (MainGraphable.points.length-1); int n =
 * 0; for (double i = pac[0]; i < pac[2]; i += step) { if
 * (n<MainGraphable.points.length) { double y = F.f(i);
 * MainGraphable.points[n].setXY(i, y);
 * MainGraphable.points[n].setPlot("Rectangular"); n++; }else { break; } } }
 * 
 * public void plotPoints() { for (int i = 0; i < MainGraphable.points.length-1;
 * i++) { if (MainGraphable.points[i]!=null &&
 * MainGraphable.points[i].getPlot()!= null &&
 * MainGraphable.points[i].getPlot().equals("Rectangular")) { if
 * (Math.abs(MainGraphable.points[i].getY()-MainGraphable.points[i+1].getY())<
 * 100) { double x = MainGraphable.points[i].getX(); double y =
 * MainGraphable.points[i].getY(); double x2 = MainGraphable.points[i+1].getX();
 * double y2 = MainGraphable.points[i+1].getY(); G.line(x, y, x2, y2, 1,
 * Color.red,pac,g); } }else { break; } } }
 */