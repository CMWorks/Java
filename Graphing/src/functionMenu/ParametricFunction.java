package functionMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ParametricFunction extends JPanel {

	private ArrayList<Function> list = new ArrayList();
	private JButton addNewFunction;

	public ParametricFunction() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		addNewFunction = new JButton("Add new funciton");
		addNewFunction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				add(list.size() + "*t", "cos(t)", new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
						(int) (Math.random() * 255)));
			}
		});
		Function edTemp = new Function("Edit Graph", Color.black, "t-Min:", "0", "t-Max: ", "2*pi", "t-Step:  ", "100");
		edTemp.setEdit(true);
		list.add(edTemp);
		add("t", "t^2", Color.red);

	}

	public void add(String eq1, String eq2, Color col) {
		Function temp = new Function("Function " + (list.size()), col, "x(t) = ", eq1, "y(t) = ", eq2);
		list.add(list.size() - 1, temp);
		setPreferredSize(new Dimension(0, list.size() * 80));
		removeAll();
		for (int i = 0; i < list.size(); i++) {
			add(list.get(i));
		}
		add(addNewFunction);
	}

	public void remove() {

	}

	public ArrayList<Function> getList() {
		return list;
	}

}
