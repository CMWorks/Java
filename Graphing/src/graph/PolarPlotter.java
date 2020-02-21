package graph;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import functionMenu.Function;
import math.G;
import math.M;
import parser.Parser;

public class PolarPlotter {

	private double[] pac = new double[6];
	// 0 1 2 3 4 5
	// pac = [xMin, yMin, xMax, yMax, width, height]

	private double xStep, yStep;
	private Graphics g;
	private Color theme = Color.gray;

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
	}

	private void drawGrid() {
		double gridLine = xStep / 10.0;
		if (pac[1] < 0 && pac[3] > 0) { // x-axis
			double xAxis = M.map(0, pac[1], pac[3], pac[5], 0);
			G.line(pac[0], 0, pac[2], 0, 1, theme, pac, g);
			for (double i = pac[0] - (pac[0] % xStep); i <= pac[2]; i += xStep) {
				if (i != 0) {
					int x = (int) M.map(i, pac[0], pac[2], 0, pac[4]);
					String num = "" + ((double) ((int) (i * 100))) / 100;
					G.line(i, 0 - gridLine, i, 0 + gridLine, 1, theme, pac, g);
					g.drawString(num, x - num.length() * 4, (int) xAxis + 20);
					// circle(i, Color.red);
				}
			}
		}

		if (pac[0] < 0 && pac[2] > 0) { // y-axis
			double yAxis = M.map(0, pac[0], pac[2], 0, pac[4]);
			G.line(0, pac[1], 0, pac[3], 1, theme, pac, g);
			for (double i = pac[1] - (pac[1] % yStep); i <= pac[3]; i += yStep) {
				if (i != 0) {
					int y = (int) M.map(i, pac[1], pac[3], pac[5], 0);
					String num = "" + ((double) ((int) (i * 100))) / 100;
					G.line(0 - gridLine, i, 0 + gridLine, i, 1, theme, pac, g);
					g.drawString(num, (int) yAxis + 15, y + 5);
				}
			}
		}

		// circles
		for (double i = pac[0] - (pac[0] % xStep); i <= pac[2]; i += xStep) {
			circle(Math.abs(i), theme);
		}

		// angel lines
		double r = Math.max(pac[2], pac[0]);
		double pi = Math.PI;
		G.line(0, 0, r, pi / 6, theme, pac, g);
		G.line(0, 0, r, pi / 4, theme, pac, g);
		G.line(0, 0, r, pi / 3, theme, pac, g);
		G.line(0, 0, r, 2 * pi / 3, theme, pac, g);
		G.line(0, 0, r, 3 * pi / 4, theme, pac, g);
		G.line(0, 0, r, 5 * pi / 6, theme, pac, g);
		G.line(0, 0, r, 7 * pi / 6, theme, pac, g);
		G.line(0, 0, r, 5 * pi / 4, theme, pac, g);
		G.line(0, 0, r, 4 * pi / 3, theme, pac, g);
		G.line(0, 0, r, 5 * pi / 3, theme, pac, g);
		G.line(0, 0, r, 7 * pi / 4, theme, pac, g);
		G.line(0, 0, r, 11 * pi / 6, theme, pac, g);
	}

	public void graphEq() {
		double r, r2;
		ArrayList<Function> eq = MainGraphable.funMenu.getPolFun().getList();
		double min = 0;
		double max = 2 * Math.PI;
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

		for (int j = 0; j < eq.size() - 1; j++) {
			Color lineColor = eq.get(j).getColor();
			String fn = eq.get(j).getEquation1().strip();
			if (!(fn.equals("") || fn.equalsIgnoreCase("NAN") || fn.equalsIgnoreCase("null"))) {
				for (double i = min; i < max; i += step) {
					r = Parser.eval(fn, new String[] { "x" }, new Double[] { i });
					r2 = Parser.eval(fn, new String[] { "x" }, new Double[] { i + step });
					if (r - r2 < 100) {
						G.line(r, i, r2, i + step, lineColor, pac, g);
					}
				}
			}
		}
	}

	private void circle(double r, Color c) {
		g.setColor(c);
		// if (y1 >= pac[1] && y1 <= pac[3] ) {
		int x = (int) M.map(0, pac[0], pac[2], 0, pac[4]);
		int y = (int) M.map(0, pac[1], pac[3], pac[5], 0);
		int newr = (int) (pac[4] / (pac[2] - pac[0]) * r * 2);
		g.drawOval(x - newr / 2, y - newr / 2, newr, newr);
		// }
	}

}

/*
 * public void updatePoints() { double step = (pac[2] - pac[0]) /
 * (MainGraphable.points.length); double r; int n = 0; for (double i = 0; true;
 * i+=step) { if (n<MainGraphable.points.length) { r = F.f(i);
 * MainGraphable.points[n].setRTheta(r, i);
 * MainGraphable.points[n].setPlot("Polar"); n++; }else { break; } } } private
 * void plotPoints() { for (int i = 0; i < MainGraphable.points.length-1; i++) {
 * if (MainGraphable.points[i]!=null && MainGraphable.points[i].getPlot()!= null
 * && MainGraphable.points[i].getPlot().equals("Polar")) { double x =
 * MainGraphable.points[i].getX(); double y = MainGraphable.points[i].getY();
 * double x2 = MainGraphable.points[i+1].getX(); double y2 =
 * MainGraphable.points[i+1].getY(); G.line(x, y, x2, y2, 1, Color.red,pac,g);
 * }else { break; } } }
 */