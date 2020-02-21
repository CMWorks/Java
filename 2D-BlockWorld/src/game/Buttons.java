package game;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Buttons extends JPanel{
	private JPanel storage;
	private JPanel menu;
	private JPanel statBar;
	private JLabel health;
	private JLabel hunger;
	private JLabel energy;
	private JLabel weight;
	private JLabel level;
	private JLabel time;
	private JLabel FPS;
	private JButton[] slots = new JButton[24];
	private int hour;
	private int minuet;
	private int second;
	
	
	public Buttons(){
		storage = new JPanel(new GridLayout(1,6,0,0));
		for(int i = 0; i<6; i++){
			slots[i] = new JButton(""+i);
			storage.add(slots[i]);
		}
		
		menu = new JPanel(new GridLayout(6,1,0,0));
		for(int i = 6; i<12; i++){
			slots[i] = new JButton(""+i);
			menu.add(slots[i]);
		}
		statBar = new JPanel(new GridLayout(1,6,0,0));
		FPS = new JLabel("FPS: ");
		health = new JLabel("Health: 100%");
		hunger = new JLabel("Hunger: 100%");
		energy = new JLabel("Energy: 89%");
		weight = new JLabel("Weight: 100");
		level = new JLabel("Level: 1");
		time = new JLabel("Time: ");
		statBar.add(FPS);
		statBar.add(health);
		statBar.add(hunger);
		statBar.add(energy);
		statBar.add(weight);
		statBar.add(level);
		statBar.add(time);
		
	}
	public void setTime(){
		FPS.setText("FPS: "+Game.getFPS());
		second++;
		if(second==60){
			second=0;
			minuet++;
		}
		if(minuet==60){
			minuet=0;
			hour++;
		}
		if(hour==24){
			hour=0;
		}
		time.setText("Time "+hour+":"+minuet+":"+second);
	}
	
	public JPanel addStorage(){
		return storage;
	}
	public JPanel addMenu(){
		return menu;
	}
	
	public JPanel addStatBal(){
		return statBar;
	}
	
	public void paintComponent( Graphics g ){
	    super.paintComponent( g );
	    
	}
	
	public void actionEvent(ActionEvent e){
		
	}
}
