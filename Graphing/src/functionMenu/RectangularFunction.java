package functionMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class RectangularFunction extends JPanel {

	private ArrayList<Function> list = new ArrayList();
	private JButton addNewFunction;

	public RectangularFunction() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		addNewFunction = new JButton("Add new funciton");
		addNewFunction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				add(list.size() + "*x", new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
						(int) (Math.random() * 255)));
			}
		});
		add("x", Color.red);
	}

	public void add(String eq, Color col) {
		Function temp = new Function("Function " + (list.size() + 1), col, "f(x) = ", eq);
		list.add(temp);
		setPreferredSize(new Dimension(0, list.size() * 50));
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
