package graph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.text.LayeredHighlighter.LayerPainter;

import engine3D.Engine3DRenderPanel;
import engine3D.RotationMatrix3D;
import functionMenu.Function;
import functionMenu.FunctionMenuPanel;
import math.M;
import parser.Parser;

public class MainGraphable implements MouseWheelListener, MouseListener, MouseMotionListener {

	Toolkit tk = Toolkit.getDefaultToolkit();
	private JFrame frame = new JFrame();
	private JLayeredPane layerPane = new JLayeredPane();

	private JButton fun, rec, pol, dif, par, third, fps, exit;
	private JPanel menu;
	private boolean displayFun = false;
	public static FunctionMenuPanel funMenu;
	public static GraphBranch graph;
	public static Engine3DRenderPanel ThreeD;
	public static double xMin, xMax, xStep, yMin, yMax, yStep;
	public static int width, height;
	public static boolean updatePoints;
	private int preX, preY;
	private double ratio;
	public static String graphMode;

	public MainGraphable() {
		// sets action listeners
		frame.addMouseWheelListener(this);
		frame.addMouseListener(this);
		frame.addMouseMotionListener(this);

		// sets values
		this.width = ((int) tk.getScreenSize().getWidth());
		this.height = ((int) tk.getScreenSize().getHeight());
		this.ratio = 10.0 / this.height;
		this.xMin = -width * ratio;
		this.yMin = -height * ratio;
		this.xMax = width * ratio;
		this.yMax = height * ratio;
		this.xStep = 1;
		this.yStep = 1;
		this.updatePoints = true;

		// sets up the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setSize(width, height);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setLayout(new BorderLayout());
		frame.add(layerPane, BorderLayout.CENTER);
		layerPane.setBounds(0, 0, width, height);
//		frame.addComponentListener(new ComponentAdapter() 
//		{  
//		        public void componentResized(ComponentEvent evt) {
//		            Component c = (Component)evt.getSource();
//		            menu.setSize(c.getWidth(), menu.getHeight());
//		        }
//		});

		// sets up the menu
		menu = new JPanel();
		fun = new JButton("Function");
		rec = new JButton("Rectangular");
		par = new JButton("Parametric");
		pol = new JButton("Polar");
		dif = new JButton("Slope Field");
		third = new JButton("3D");
		fps = new JButton("fps: ");
		fps.setEnabled(false);
		fps.setBackground(Color.lightGray);
		exit = new JButton("exit");
		exit.setBackground(Color.lightGray);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		fun.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (displayFun) {
					displayFun = false;
					funMenu.setVisible(false);
				} else {
					displayFun = true;
					funMenu.updateMenu(graphMode);
					funMenu.setVisible(true);
				}
			}
		});
		rec.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (displayFun) {
					funMenu.updateMenu("Rectangular");
				}
				if (graphMode.equals("3D")) {
					ThreeD.setVisible(false);
					graph.setVisible(true);
				}
				graphMode = "Rectangular";
			}
		});
		par.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (displayFun) {
					funMenu.updateMenu("Parametric");
				}
				if (graphMode.equals("3D")) {
					ThreeD.setVisible(false);
					graph.setVisible(true);
				}
				graphMode = "Parametric";
			}
		});
		pol.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (displayFun) {
					funMenu.updateMenu("Polar");
				}
				if (graphMode.equals("3D")) {
					ThreeD.setVisible(false);
					graph.setVisible(true);
				}
				graphMode = "Polar";
			}
		});
		dif.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (displayFun) {
					funMenu.updateMenu("Slope");
				}
				if (graphMode.equals("3D")) {
					ThreeD.setVisible(false);
					graph.setVisible(true);
				}
				graphMode = "Slope";
			}
		});
		third.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (displayFun) {
					funMenu.updateMenu("3D");
				}
				if (!graphMode.equals("3D")) {
					ThreeD.setVisible(true);
					graph.setVisible(false);
				}
				graphMode = "3D";
			}
		});
		menu.add(rec);
		menu.add(par);
		menu.add(pol);
		menu.add(dif);
		;
		menu.add(third);
		menu.setBounds(0, 0, width, 35);
		menu.setBackground(Color.gray);

		// Sets up the graph
		graph = new GraphBranch();
		graph.setBackground(Color.LIGHT_GRAY);
		graph.setBounds(0, menu.getHeight(), width, width - menu.getHeight());
		graphMode = "Rectangular";

		// sets up 3D graph
		ThreeD = new Engine3DRenderPanel();
		ThreeD.setBounds(0, 0, width, height);
		ThreeD.setVisible(false);

		// sets up the function
		funMenu = new FunctionMenuPanel(0, menu.getHeight());
		// sets up the function button
		int wd = 2 * funMenu.getWidth() / 5;
		int hi = 2 * menu.getHeight() / 3;
		int x = (funMenu.getWidth() - wd) / 2;
		int y = (menu.getHeight() - hi) / 2;
		fun.setBounds(x, y, wd, hi);
		wd = 5 * wd / 8;
		fps.setBounds(width - wd - x, y, wd, hi);
		exit.setBounds(fps.getX() + wd + 5, fps.getY(), wd, hi);

		layerPane.add(menu, JLayeredPane.DEFAULT_LAYER);
		layerPane.add(graph, JLayeredPane.DEFAULT_LAYER);
		layerPane.add(ThreeD, JLayeredPane.DEFAULT_LAYER);
		layerPane.add(funMenu, JLayeredPane.PALETTE_LAYER);
		layerPane.add(fun, JLayeredPane.PALETTE_LAYER);
		layerPane.add(fps, JLayeredPane.PALETTE_LAYER);
		layerPane.add(exit, JLayeredPane.PALETTE_LAYER);
		update3D();
		frame.setVisible(true);
	}

	public void render() {
		if (graphMode.equals("3D")) {
			ThreeD.repaint();
		} else {
			graph.repaint();
		}
		funMenu.updateUI();
	}

	public static void update3D() {
		ThreeD.clearGraph();
		ArrayList<Function> eq = funMenu.getThreeDFun().getList();
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
		ThreeD.setBounds(min, max, step);

		for (int i = 0; i < eq.size() - 1; i++) {
			Color lineColor = eq.get(i).getColor();
			String fnx = eq.get(i).getEquation1().strip();
			String fny = eq.get(i).getEquation2().strip();
			String fnz = eq.get(i).getEquation3().strip();
			ThreeD.graphEq(fnx, fny, fnz, lineColor);
		}

	}

	public void setFPS(int fps) {
		this.fps.setText("fps: " + fps);
	}

//	public void update() {
//		((GraphBranch) graph).updatePoints();
//	}

	/*
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * 
	 * These are the user-active events These next methods will alter the graph
	 * based on user actions
	 * 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 */

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (graphMode.equals("3D")) {
			double scaleMultiplyFactor = 0.01;
			double scaleFactor = scaleMultiplyFactor * (-e.getUnitsToScroll());
			ThreeD.scale(1 + scaleFactor);
		} else {
			double x = -M.map(e.getX(), 0, width, xMin, xMax);
			double y = -M.map(e.getY(), height, 0, yMin, yMax);
			xMin += e.getUnitsToScroll() * (x + xMin) / 40;
			xMax += e.getUnitsToScroll() * (x + xMax) / 40;
			yMin += e.getUnitsToScroll() * (y + yMin) / 40;
			yMax += e.getUnitsToScroll() * (y + yMax) / 40;
			if ((int) (xMax - xMin) / 20 > 0) {
				xStep = (int) (xMax - xMin) / 20;
			} else if ((xMax - xMin) / xStep < 20) {
				xStep /= 2.0;
			} else if ((xMax - xMin) / xStep > 40) {
				xStep *= 2.0;
			}
			if ((int) (yMax - yMin) / 20 > 0) {
				yStep = (int) (yMax - yMin) / 20;
			} else if ((yMax - yMin) / yStep < 20) {
				yStep /= 2.0;
			} else if ((yMax - yMin) / yStep > 40) {
				yStep *= 2.0;
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		preX = e.getX();
		preY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (graphMode.equals("3D")) {
			double angleMultiplyFactor = 0.02;
			double angleY = angleMultiplyFactor * (e.getX() - preX);
			double angleX = angleMultiplyFactor * (e.getY() - preY);
			ThreeD.rotate(new RotationMatrix3D(-angleX, angleY, 0));
		} else {
			xMin -= (e.getX() - preX) * (xMax - xMin) / 500.0;
			xMax -= (e.getX() - preX) * (xMax - xMin) / 500.0;
			yMin += (e.getY() - preY) * (yMax - yMin) / 500.0;
			yMax += (e.getY() - preY) * (yMax - yMin) / 500.0;
		}

		preX = e.getX();
		preY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

}
