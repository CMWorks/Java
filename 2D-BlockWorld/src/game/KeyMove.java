package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import game.gfx.Map;

public class KeyMove extends JFrame implements KeyListener {
	private int count[] = new int[4];
	private int moveSpeed;
	private int speed;
	private char grid;
	private int direction;
	private int move;
	
	public KeyMove(Map map, JFrame frame, int moveSpeed){
		this.moveSpeed = moveSpeed;
		frame.addKeyListener(this);
		frame.setFocusable(true);
	}
	
	public char getGrid(){
		return grid;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public int getDirection(){
		return direction;
	}
	
	public int getMove(){
		return move;
	}
	
	@Override
    public void keyPressed(KeyEvent e) {
    	//0=down, 1=left, 2=right, 3=up//
        if (e.getKeyCode() == KeyEvent.VK_RIGHT||e.getKeyChar() == 'd') {
        	grid='X';
        	if(count[2]<3){
        		speed = -moveSpeed;
        		direction = 2;
        		move = 0;
        		count[2]++;
        	}else if(count[2]<6){
        		speed = -moveSpeed;
        		direction = 2;
        		move = 1;
        		count[2]++;
        	}else if(count[2]<9){
        		speed = -moveSpeed;
        		direction = 2;
        		move = 2;
        		count[2]++;
        	}else if(count[2]<11){
        		speed = -moveSpeed;
        		direction = 2;
        		move = 3;
        		count[2]++;
        	}else{
        		speed = -moveSpeed;
        		direction = 2;
        		move = 3;
        		count[2]=0;
        	}
		  		
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT||e.getKeyChar() == 'a') {
        	grid='X';
        	if(count[1]<3){
        		speed = moveSpeed;
        		direction = 1;
        		move = 0;
        		count[1]++;
        	}else if(count[1]<6){
        		speed = moveSpeed;
        		direction = 1;
        		move = 1;
        		count[1]++;
        	}else if(count[1]<9){
        		speed = moveSpeed;
        		direction = 1;
        		move = 2;
        		count[1]++;
        	}else if(count[1]<11){
        		speed = moveSpeed;
        		direction = 1;
        		move = 3;
        		count[1]++;
        	}else{
        		speed = moveSpeed;
        		direction = 1;
        		move = 3;
        		count[1]=0;
        	}
        }
		  if (e.getKeyCode() == KeyEvent.VK_UP||e.getKeyChar() == 'w') {
			  grid='Y';
			  if(count[3]<3){
				  	speed = moveSpeed;
	        		direction = 3;
	        		move = 0;
	        		count[3]++;
	        	}else if(count[3]<6){
	        		speed = moveSpeed;
	        		direction = 3;
	        		move = 1;
	        		count[3]++;
	        	}else if(count[3]<9){
	        		speed = moveSpeed;
	        		direction = 3;
	        		move = 2;
	        		count[3]++;
	        	}else if(count[3]<11){
	        		speed = moveSpeed;
	        		direction = 3;
	        		move = 3;
	        		count[3]++;
	        	}else{
	        		speed = moveSpeed;
	        		direction = 3;
	        		move = 3;
	        		count[3]=0;
	        	}
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN||e.getKeyChar() == 's') {
        	grid='Y';
        	if(count[0]<3){
        		speed = -moveSpeed;
        		direction = 0;
        		move = 0;
        		count[0]++;
        	}else if(count[0]<6){
        		speed = -moveSpeed;
        		direction = 0;
        		move = 1;
        		count[0]++;
        	}else if(count[0]<9){
        		speed = -moveSpeed;
        		direction = 0;
        		move = 2;
        		count[0]++;
        	}else if(count[0]<11){
        		speed = -moveSpeed;
        		direction = 0;
        		move = 3;
        		count[0]++;
        	}else{
        		speed = -moveSpeed;
        		direction = 0;
        		move = 3;
        		count[0]=0;
        	}
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    	//0=down, 1=left, 2=right, 3=up//
    	if (e.getKeyCode() == KeyEvent.VK_RIGHT||e.getKeyChar() == 'd') {//2
        	grid='X';
    		speed = 0;
    		direction = 2;
    		move = 0;
    		count[2]=0;	
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT||e.getKeyChar() == 'a') {//1
        	grid='X';
        	speed = 0;
    		direction = 1;
    		move = 0;
    		count[1]=0;	
        }
		if (e.getKeyCode() == KeyEvent.VK_UP||e.getKeyChar() == 'w') {//3
			grid='Y';
			speed = 0;
			direction = 3;
			move = 0;
			count[3]=0;	
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN||e.getKeyChar() == 's') {//0
        	grid='Y';
        	speed = 0;
    		direction = 0;
    		move = 0;
    		count[0]=0;	
        }
    }
	 @Override
    public void keyTyped(KeyEvent e) {
        
	 }
}//keyMove class
