package functionMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SlopeFunction extends JPanel {

	private ArrayList<Function> list = new ArrayList<Function>();
	private ButtonGroup typeOfGraph;
	private JRadioButton stationary, moveable;
	private JButton addNewFunction;

	public SlopeFunction() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		addNewFunction = new JButton("Add new funciton");
		addNewFunction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				add(list.size() + "*x+y", new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
						(int) (Math.random() * 255)));
			}
		});
		stationary = new JRadioButton("Stationary lines", true);
		moveable = new JRadioButton("Moveable lines");
		typeOfGraph = new ButtonGroup();
		typeOfGraph.add(stationary);
		typeOfGraph.add(moveable);
		add("x+y", Color.black);
	}

	public void add(String eq, Color col) {
		Function temp = new Function("Function " + (list.size() + 1), col, "dy/dx = ", eq);
		list.add(temp);
		setPreferredSize(new Dimension(0, list.size() * 50));
		removeAll();
		for (int i = 0; i < list.size(); i++) {
			add(list.get(i));
		}
		add(stationary);
		add(moveable);
		add(addNewFunction);
	}

	public ArrayList<Function> getList() {
		return list;
	}

	public boolean isMoveable() {
		return moveable.isSelected();
	}
}
