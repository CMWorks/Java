package game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class SecondLayer {
	private final int RARE = 10;
	private final char[][] dataChar;
	private final int treeWidth = 64;
	private final int treeHeight = 64;
	private final int flowerWidth = 63;
	private final int flowerHeight = 64;
	private final int desertWidth = 97;
	private final int desertHeight = 82;
	private BufferedImage[] treeSprite = new BufferedImage[100];
	private BufferedImage[] flowerSprite = new BufferedImage[100];
	private BufferedImage[] desertSprite = new BufferedImage[100];
	
	public SecondLayer(char[][] dataChar){
		this.dataChar = dataChar;
	}
	
	public int getLayer(int x, int y){
		int place = (int)(Math.random()*RARE);
		if(place==0){
			if(dataChar[y][x]=='g'){
				int choice = (int)(Math.random()*2);
				if(choice==0){
					return (int)(Math.random()*treeSprite.length);
				}//tree
				else{
					return (int)(Math.random()*(flowerSprite.length+100)+100);
				}//flowers
			}//grass
			else if(dataChar[y][x]=='r'){
				return -1;
			}// river
			else{
				return (int)(Math.random()*(desertSprite.length+200)+200);
			}//desert
		}//places the 2nt layer
		else{
			return -1;
		}
	}
	public BufferedImage[] loadTreeSprite(){
	   BufferedImageLoader loader = new BufferedImageLoader();
	   BufferedImage spriteSheet = null;
		try {
			spriteSheet = loader.loadImage("/trees.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		
		int x = 0;
		int y = 0;
		int count = 0;
		while(y<10){
			while(x<10){
				treeSprite[count] = ss.grabSprite(x*treeWidth, y*treeHeight, treeWidth, treeHeight);
				count++;
				x++;
			}
		x=0;
		y++;
		}
		return treeSprite;
	}
	public BufferedImage[] loadFlowerSprite(){
			BufferedImageLoader loader = new BufferedImageLoader();
		   BufferedImage spriteSheet = null;
			try {
				spriteSheet = loader.loadImage("/flower.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
			SpriteSheet ss = new SpriteSheet(spriteSheet);
			
			int x = 0;
			int y = 0;
			int count = 0;
			while(y<10){
				while(x<10){
					flowerSprite[count] = ss.grabSprite(x*flowerWidth, y*flowerHeight, flowerWidth, flowerHeight);
					count++;
					x++;
				}
			x=0;
			y++;
			}
			return flowerSprite;
	}
	public BufferedImage[] loadDesertSprite(){
			BufferedImageLoader loader = new BufferedImageLoader();
		   BufferedImage spriteSheet = null;
			try {
				spriteSheet = loader.loadImage("/desert.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
			SpriteSheet ss = new SpriteSheet(spriteSheet);
			
			int x = 0;
			int y = 0;
			int count = 0;
			while(y<10){
				while(x<10){
					desertSprite[count] = ss.grabSprite(x*desertWidth, y*desertHeight, desertWidth, desertHeight);
					count++;
					x++;
				}
			x=0;
			y++;
			}
			return desertSprite;
	}

}
