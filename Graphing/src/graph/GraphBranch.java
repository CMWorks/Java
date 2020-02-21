package graph;

import java.awt.Graphics;

import javax.swing.JPanel;

import engine.MainRunable;
import engine3D.Engine3DRenderPanel;

public class GraphBranch extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6148931593776460265L;
	private RectangularPloter rec = new RectangularPloter();
	private ParametricPloter par = new ParametricPloter();
	private PolarPlotter pol = new PolarPlotter();
	private SlopePlotter slope = new SlopePlotter();

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (MainGraphable.graphMode.equals("Rectangular")) {
			rec.updatePixels(g);
		} else if (MainGraphable.graphMode.equals("Parametric")) {
			par.updatePixels(g);
		} else if (MainGraphable.graphMode.equals("Polar")) {
			pol.updatePixels(g);
		} else if (MainGraphable.graphMode.equals("Slope")) {
			slope.updatePixels(g);
		}
		MainRunable.frame++;
	}

	/*
	 * public void updatePoints() { if
	 * (MainGraphable.graphMode.equals("Rectangular")) { rec.updatePoints(); }else
	 * if (MainGraphable.graphMode.equals("Parametric")) { par.updatePoints(); }else
	 * if (MainGraphable.graphMode.equals("Polar")) { pol.updatePoints(); }else if
	 * (MainGraphable.graphMode.equals("Slope")) { slope.updatePoints(); }
	 * MainRunable.uFrame++; }
	 */
}
