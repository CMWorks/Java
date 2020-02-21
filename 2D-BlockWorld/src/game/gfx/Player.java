package game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JPanel;

public class Player extends JPanel{

	private BufferedImage[] sprite = new BufferedImage[16];

	public Player(int width, int height, boolean swimming)
   { 
	   BufferedImageLoader loader = new BufferedImageLoader();
	   BufferedImage spriteSheet = null;
	   if(swimming){
		   try {
				spriteSheet = loader.loadImage("/swimming.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
	   }
	   else{
		   try {
				spriteSheet = loader.loadImage("/player.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
	   }
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		//down
		sprite[0] = ss.grabSprite(1, 7, 26, 40);
		sprite[1] = ss.grabSprite(33, 10, 26, 40);
		sprite[2] = ss.grabSprite(60, 8, 26, 40);
		sprite[3] = ss.grabSprite(90, 10, 26, 40);
		//left
		sprite[4] = ss.grabSprite(1, 107, 26, 40);
		sprite[5] = ss.grabSprite(33, 108, 26, 40);
		sprite[6] = ss.grabSprite(60, 107, 26, 40);
		sprite[7] = ss.grabSprite(90, 108, 26, 40);
		//right
		sprite[8] = ss.grabSprite(1, 159, 26, 40);
		sprite[9] = ss.grabSprite(33, 157, 26, 40);
		sprite[10] = ss.grabSprite(60, 159, 26, 40);
		sprite[11] = ss.grabSprite(90, 157, 26, 40);
		//up
		sprite[12] = ss.grabSprite(1, 58, 26, 40);
		sprite[13] = ss.grabSprite(33, 59, 26, 40);
		sprite[14] = ss.grabSprite(60, 58, 26, 40);
		sprite[15] = ss.grabSprite(90, 59, 26, 40);
			
	}   
   public BufferedImage getPlayer(int direction,int move){
		if(direction==0)/*down*/{
			if(move==0){
		 		return sprite[0];	
			}
			else if(move==1){
				return sprite[1];
			}
			else if(move==2){
				return sprite[2];
			}
			else{
				return sprite[3];
			}
		}
		else if(direction==1)/*left*/{
			if(move==0){
				return sprite[4];
			}
			else if(move==1){
				return sprite[5];
			}
			else if(move==2){
				return sprite[6];
			}
			else{
		 		return sprite[7];
			}
		}
		else if(direction==2)/*right*/{
			if(move==0){
		 		return sprite[8];
			}
			else if(move==1){
		 		return sprite[9];
			}
			else if(move==2){
				return sprite[10];
			}
			else{
		 		
				return sprite[11];
			}
		}
		else/*up*/{
			if(move==0){
				return sprite[12];
			}
			else if(move==1){	
				return sprite[13];
			}
			else if(move==2){
				return sprite[14];
			}
			else{	
				return sprite[15];
			}
		}//up
	}//accessor
}//class