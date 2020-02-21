package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Study_App {

	public static void main(String[] args) {
		JFrame app = new JFrame("Study App");

		// sets up the app
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setResizable(true);
		app.setLocationRelativeTo(null);
		app.setSize(500, 500);
		// app.setExtendedState(JFrame.MAXIMIZED_BOTH);
		 app.setUndecorated(true);

		/*****************************/
		/*** sets up the Main Menu ***/
		JPanel main_menu = (JPanel) app.getContentPane();
		main_menu.setLayout(null);
		//main_menu.setLayout(new BorderLayout());
		JLabel title = new JLabel("Menu");
			title.setBackground(Color.lightGray);
			title.setOpaque(true);
			title.setBounds(0, 0, app.getWidth(), 20);
			main_menu.add(title);
		JButton newButton = new JButton("New");
			newButton.setBounds(app.getWidth()/3, app.getHeight()/2, app.getWidth()/7, app.getHeight()/11);
			main_menu.add(newButton);
		JButton loadButton = new JButton("Load");
			loadButton.setBounds(2*app.getWidth()/3, app.getHeight()/2, app.getWidth()/7, app.getHeight()/11);
			main_menu.add(loadButton);
		
		title.addMouseListener( new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			public void mousePressed(MouseEvent e) {
				app.setLocation(app.getX()+e.getX(), app.getY()+e.getY());
				
			}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
		});
		
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main_menu.setVisible(false);
			}
		});
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getModifiers());
			}
		});
		//main_menu.add(option_main,BorderLayout.CENTER);
		/*****************************/
		
		/*******************************/
		/*** sets up the Create Menu ***/
		/*******************************/
		
		/*****************************/
		/*** sets up the Load Menu ***/
		/*****************************/
		
		/******************************/
		/*** sets up the Study Menu ***/
		/******************************/
		
		/* starts the app */
		app.setVisible(true);
	}

}