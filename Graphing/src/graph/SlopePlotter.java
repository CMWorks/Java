package graph;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import functionMenu.Function;
import math.G;
import math.M;
import parser.Parser;

public class SlopePlotter {

	private double[] pac = new double[6];
	// 0 1 2 3 4 5
	// pac = [xMin, yMin, xMax, yMax, width, height]

	private double xStep, yStep;
	private Graphics g;
	private Color theme = Color.gray;
	private int segments = 50;
	private int segmentLength = 1;
	private boolean moveWihtGraph = false;

	public void updatePoints() {

	}

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
		graphSlopes();
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

	private void graphSlopes() {
		moveWihtGraph = MainGraphable.funMenu.getSloFun().isMoveable();
		ArrayList<Function> eq = MainGraphable.funMenu.getSloFun().getList();
		for (int j = 0; j < eq.size(); j++) {
			String fn = eq.get(j).getEquation1().strip();
			Color lineColor = eq.get(j).getColor();
			if (!(fn.equals("") || fn.equalsIgnoreCase("NAN") || fn.equalsIgnoreCase("null"))) {
				if (moveWihtGraph) {
					/* Moves with graph */
					double length = (pac[3] - pac[1]) * segmentLength / 75;
					for (double y = pac[1] - (pac[1] % yStep); y <= pac[3]; y += yStep) {
						for (double x = pac[0] - (pac[0] % xStep); x <= pac[2]; x += xStep) {
							double theta = Math.atan(Parser.eval(fn, new String[] { "x", "y" }, new Double[] { x, y }));// F.dy(x,
																														// y));
							double x1 = length * Math.cos(Math.PI + theta) + x;
							double y1 = length * Math.sin(Math.PI + theta) + y;
							double x2 = length * Math.cos(theta) + x;
							double y2 = length * Math.sin(theta) + y;
							G.line(x1, y1, x2, y2, 1, lineColor, pac, g);
						}
					}
				} else {
					/* Static points */
					double length = (pac[3] - pac[1]) * segmentLength / 200;
					for (double y = pac[1]; y < pac[3]; y += (pac[3] - pac[1]) / segments) {
						for (double x = pac[0]; x < pac[2]; x += (pac[2] - pac[0]) / segments) {
							double slope = Parser.eval(fn, new String[] { "x", "y" }, new Double[] { x, y });
							double theta = Math.atan(slope);
							double x1 = length * Math.cos(Math.PI + theta) + x;
							double y1 = length * Math.sin(Math.PI + theta) + y;
							double x2 = length * Math.cos(theta) + x;
							double y2 = length * Math.sin(theta) + y;
							G.line(x1, y1, x2, y2, 1, lineColor, pac, g);
						}
					}
				}
			}
		}

	}

	private void graphSolution() {

	}
}
