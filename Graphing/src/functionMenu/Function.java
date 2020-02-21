package functionMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import graph.MainGraphable;

public class Function extends JPanel {
	private String equation1, equation2, equation3;
	private JLabel funName, name1, name2, name3;
	private JTextField field1, field2, field3;
	private JButton enter, color;
	private boolean isEdit;

	public Function() {
		setLayout(null);
	}

	public Function(String name, Color bnColor, String f1, String eq) {
		this();
		funName = new JLabel(name);
		funName.setBounds(2, 0, funName.getPreferredSize().width, funName.getPreferredSize().height);
		color = new JButton();
		color.setBackground(bnColor);
		color.setBounds(2, funName.getHeight(), color.getPreferredSize().width, color.getPreferredSize().height);
		color.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				color.setBackground(getNewColor.getColor("", color.getBackground()));
			}
		});
		name1 = new JLabel(f1);
		name1.setBounds(color.getWidth() + 25, color.getY(), name1.getPreferredSize().width,
				name1.getPreferredSize().height);
		field1 = new JTextField(eq, 10);
		field1.setBounds(name1.getX() + name1.getWidth() + 5, name1.getY(), field1.getPreferredSize().width,
				field1.getPreferredSize().height);
		equation1 = eq;
		enter = new JButton("enter");
		enter.setBounds(field1.getX() + field1.getWidth() + 5, field1.getY(), enter.getPreferredSize().width,
				field1.getHeight());
		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				equation1 = field1.getText().strip();
				field1.setText(equation1);
			}
		});
		add(funName);
		add(color);
		add(name1);
		add(field1);
		add(enter);

	}

	public Function(String name, Color bnColor, String f1, String eq1, String f2, String eq2) {
		this();
		// Heading
		funName = new JLabel(name);
		funName.setBounds(2, 0, funName.getPreferredSize().width, funName.getPreferredSize().height);
		color = new JButton();
		color.setBackground(bnColor);
		color.setBounds(2, funName.getHeight(), color.getPreferredSize().width, color.getPreferredSize().height);
		color.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				color.setBackground(getNewColor.getColor("", color.getBackground()));
			}
		});
		// first row
		name1 = new JLabel(f1);
		name1.setBounds(color.getWidth() + 15, color.getY(), name1.getPreferredSize().width,
				name1.getPreferredSize().height);
		field1 = new JTextField(eq1, 10);
		field1.setBounds(name1.getX() + name1.getWidth() + 5, name1.getY(), field1.getPreferredSize().width,
				field1.getPreferredSize().height);
		equation1 = eq1;
		// second row
		name2 = new JLabel(f2);
		name2.setBounds(name1.getX(), name1.getY() + name1.getHeight() + 5, name2.getPreferredSize().width,
				name2.getPreferredSize().height);
		field2 = new JTextField(eq2, 10);
		field2.setBounds(field1.getX(), name2.getY(), field2.getPreferredSize().width,
				field2.getPreferredSize().height);
		equation2 = eq2;
		enter = new JButton("enter");
		enter.setBounds(field2.getX() + field2.getWidth() + 5, field2.getY(), enter.getPreferredSize().width,
				field2.getHeight());
		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				equation1 = field1.getText().strip();
				field1.setText(equation1);
				equation2 = field2.getText().strip();
				field2.setText(equation2);
			}
		});
		add(funName);
		add(color);
		add(name1);
		add(field1);
		add(name2);
		add(field2);
		add(enter);
		setSize(getWidth(), getHeight() + 10);

	}

	public Function(String name, Color bnColor, String f1, String eq1, String f2, String eq2, String f3, String eq3) {
		this();
		// Heading
		funName = new JLabel(name);
		funName.setBounds(2, 0, funName.getPreferredSize().width, funName.getPreferredSize().height);
		color = new JButton();
		color.setBackground(bnColor);
		color.setBounds(2, funName.getHeight(), color.getPreferredSize().width, color.getPreferredSize().height);
		color.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				color.setBackground(getNewColor.getColor("", color.getBackground()));
				MainGraphable.update3D();
			}
		});
		// first row
		name1 = new JLabel(f1);
		name1.setBounds(color.getWidth() + 15, color.getY(), name1.getPreferredSize().width,
				name1.getPreferredSize().height);
		field1 = new JTextField(eq1, 10);
		field1.setBounds(name1.getX() + name1.getWidth() + 5, name1.getY(), field1.getPreferredSize().width,
				field1.getPreferredSize().height);
		equation1 = eq1;
		// second row
		name2 = new JLabel(f2);
		name2.setBounds(name1.getX(), name1.getY() + name1.getHeight() + 5, name2.getPreferredSize().width,
				name2.getPreferredSize().height);
		field2 = new JTextField(eq2, 10);
		field2.setBounds(field1.getX(), name2.getY(), field2.getPreferredSize().width,
				field2.getPreferredSize().height);
		equation2 = eq2;
		enter = new JButton("enter");
		enter.setBounds(field2.getX() + field2.getWidth() + 5, field2.getY(), enter.getPreferredSize().width,
				field1.getHeight());
		// third row
		name3 = new JLabel(f3);
		name3.setBounds(name2.getX(), name2.getY() + name2.getHeight() + 5, name3.getPreferredSize().width,
				name3.getPreferredSize().height);
		field3 = new JTextField(eq3, 10);
		field3.setBounds(field2.getX(), name3.getY(), field3.getPreferredSize().width,
				field3.getPreferredSize().height);
		equation3 = eq3;
		enter = new JButton("enter");
		enter.setBounds(field3.getX() + field3.getWidth() + 5, field3.getY(), enter.getPreferredSize().width,
				field3.getHeight());
		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				equation1 = field1.getText().strip();
				field1.setText(equation1);
				equation2 = field2.getText().strip();
				field2.setText(equation2);
				equation3 = field3.getText().strip();
				field3.setText(equation3);
				MainGraphable.update3D();
			}
		});
		add(funName);
		add(color);
		add(name1);
		add(field1);
		add(name2);
		add(field2);
		add(name3);
		add(field3);
		add(enter);
		setSize(getWidth(), getHeight() + 10);

	}

	public String getEquation1() {
		return equation1;
	}

	public void setEquation1(String equation1) {
		this.equation1 = equation1;
	}

	public String getEquation2() {
		return equation2;
	}

	public void setEquation2(String equation2) {
		this.equation2 = equation2;
	}

	public String getEquation3() {
		return equation3;
	}

	public void setEquation3(String equation3) {
		this.equation3 = equation3;
	}

	public Color getColor() {
		return color.getBackground();
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
		if (isEdit) {
			color.setEnabled(false);
			color.setVisible(false);

		} else {
			color.setEnabled(true);
			color.setVisible(true);
		}
	}

	private static class getNewColor {
		private static JFrame frame = new JFrame();

		private static Color getColor(String errorMessage, Color ogColor) {
			String in = JOptionPane.showInputDialog(frame, errorMessage + "RGB",
					"(" + ogColor.getRed() + "," + ogColor.getGreen() + "," + ogColor.getBlue() + ")");
			if (in != null) {
				if (in.charAt(0) == '(') {
					in = in.substring(1, in.length() - 1);
				}
				String red = in.substring(0, in.indexOf(','));
				in = in.substring(in.indexOf(',') + 1);
				String green = in.substring(0, in.indexOf(','));
				String blue = in.substring(in.indexOf(',') + 1);
				try {
					int r = Integer.parseInt(red);
					int g = Integer.parseInt(green);
					int b = Integer.parseInt(blue);
					return new Color(r, g, b);
				} catch (NumberFormatException e) {
					return getColor("Not a color\n", ogColor);
				} catch (IllegalArgumentException e) {
					return getColor("Not a color\n", ogColor);
				}
			} else {
				return ogColor;
			}
		}
	}

}
