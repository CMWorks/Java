package functionMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import graph.MainGraphable;

public class ThreeDFunction extends JPanel {

	private ArrayList<Function> list = new ArrayList();
	private JButton addNewFunction;

	public ThreeDFunction() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		addNewFunction = new JButton("Add new funciton");
		addNewFunction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				add(list.size() + "*t", "t", "t", new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
						(int) (Math.random() * 255)));
				MainGraphable.update3D();
			}
		});
		Function edTemp = new Function("Edit Graph", Color.black, "t-Min:", "0", "t-Max: ", "2*pi", "t-Step:  ", "100");
		edTemp.setEdit(true);
		list.add(edTemp);
		add("cos(t)", "sin(t)", "t", Color.black);

	}

	public void add(String eq1, String eq2, String eq3, Color col) {
		Function temp = new Function("Function " + (list.size()), col, "x(t) = ", eq1, "y(t) = ", eq2, "z(t) = ", eq3);
		list.add(list.size() - 1, temp);
		setPreferredSize(new Dimension(0, list.size() * 85));
		removeAll();
		for (int i = 0; i < list.size(); i++) {
			add(list.get(i));
		}
		add(addNewFunction);
	}

	public ArrayList<Function> getList() {
		return list;
	}

}
