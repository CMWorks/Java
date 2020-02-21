package functionMenu;

import java.awt.BorderLayout;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import graph.MainGraphable;

public class FunctionMenuPanel extends JPanel {

	private RectangularFunction recFun;
	private ParametricFunction parFun;
	private PolarFunction polFun;
	private SlopeFunction sloFun;
	private ThreeDFunction threeDFun;
	private JScrollPane recScroll, parScroll, polScroll, sloScroll, threeDScroll;

	public FunctionMenuPanel(int x, int y) {
		// setLayout(new ScrollPaneLayout());
		setBounds(x, y, 2 * MainGraphable.width / 13, MainGraphable.height / 4);

		recFun = new RectangularFunction();
		recScroll = new JScrollPane(recFun);

		parFun = new ParametricFunction();
		parScroll = new JScrollPane(parFun);

		polFun = new PolarFunction();
		polScroll = new JScrollPane(polFun);

		sloFun = new SlopeFunction();
		sloScroll = new JScrollPane(sloFun);

		threeDFun = new ThreeDFunction();
		threeDScroll = new JScrollPane(threeDFun);

		setLayout(new BorderLayout());

		setVisible(false);
	}

	public void updateMenu(String graphMode) {
		removeAll();
		if (graphMode.equals("Rectangular")) {
			add(recScroll, BorderLayout.CENTER);
		} else if (graphMode.equals("Parametric")) {
			add(parScroll, BorderLayout.CENTER);
		} else if (graphMode.equals("Polar")) {
			add(polScroll, BorderLayout.CENTER);
		} else if (graphMode.equals("Slope")) {
			add(sloScroll, BorderLayout.CENTER);
		} else {
			add(threeDScroll, BorderLayout.CENTER);
		}
	}

	public RectangularFunction getRecFun() {
		return recFun;
	}

	public ParametricFunction getParFun() {
		return parFun;
	}

	public PolarFunction getPolFun() {
		return polFun;
	}

	public SlopeFunction getSloFun() {
		return sloFun;
	}

	public ThreeDFunction getThreeDFun() {
		return threeDFun;
	}

}
