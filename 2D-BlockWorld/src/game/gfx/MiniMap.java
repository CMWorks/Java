package game.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import game.gfx.Player;
import javax.swing.JPanel;

public class MiniMap extends JPanel
{
	private final int scale  = 900;
	private final int mapSize = Map.mapSize;
	private final int[][] data = new int[mapSize][mapSize];
	private final char[][] dataChar = new char[mapSize][mapSize];  //0;g=grass, 1;r=river, 2;s=sand;
    private BufferedImage[] sprite = new BufferedImage[3];
    private int width;
    private int height;
	private int frameWidth;
	private int frameHeight;
   
   public MiniMap(int frameWidth, int frameHeight)
   { 
	   this.frameWidth= frameWidth;
	   this.frameHeight = frameHeight;
	   width = frameWidth/scale;
	   height = frameHeight/scale;
	   BufferedImageLoader loader = new BufferedImageLoader();
	   BufferedImage spriteSheet = null;
		try {
			spriteSheet = loader.loadImage("/land.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		sprite[0] = ss.grabSprite(0, 0, 47, 47); //g grass
		sprite[1] = ss.grabSprite(96, 0, 47, 47);//r river
		sprite[2] = ss.grabSprite(48, 0, 47, 47);//s sand
		
		int x = 0;
		 int y = 0;
		 int count;
		while(y<data.length){
			while(x<data[y].length){
				MapMaker mapmaker = new MapMaker(x,y,dataChar,mapSize);
				count=mapmaker.getCount();
				dataChar[y][x] = mapmaker.getChar(x,y);
				data[y][x]=count;
				
			x++;
			}
		x=0;
		y++;
		}
	} 
   
	public void paintComponent( Graphics g )
	{
	    super.paintComponent( g );
		int x = 0;
		int y = 0;
		int count = 0;
		while(y<data.length){
			while(x<data[y].length){
				while(count<sprite.length){
					if(data[y][x]==count){
						if(x*width>-width&&x*width<frameWidth&&y*height>-height&&y*height<frameHeight){
							g.drawImage(sprite[count], x*width, y*height, width,height, null);
							
						}
					}
					count++;
				}
			count=0;
			x++;
			}
		x=0;
		y++;
		}	
	}
}
