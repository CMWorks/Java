package game.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;

public class Map extends JPanel
{
	protected final static int scale  =30;
	protected final static int mapSize = 200;
	private final int[][] data = new int[mapSize][mapSize];
	private final char[][] dataChar = new char[mapSize][mapSize];  //0;g=grass, 1;r=river, 2;s=sand;
	private final int[][] secondlayer = new int[mapSize][mapSize]; //0-99=trees,100-199=flowers,200,299=sand;
	private BufferedImage[] treeSprite = new BufferedImage[100];
	private BufferedImage[] flowerSprite = new BufferedImage[100];
	private BufferedImage[] desertSprite = new BufferedImage[100];
    private BufferedImage[] sprite = new BufferedImage[3];
    private int width;
    private int height;
	private int xOff = 0;
	private int yOff = 0;
	private int frameWidth;
	private int frameHeight;
	private int direction;
	private int move;
	private boolean swimming;
   
   public Map(int frameWidth, int frameHeight)
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
		loadSecondLayer();
	}   
   private void loadSecondLayer(){
	   int x = 0;
		int y = 0;
		SecondLayer layer = new SecondLayer(dataChar);
		while(y<secondlayer.length){
			while(x<secondlayer[y].length){
				secondlayer[y][x] = layer.getLayer(x, y);
			x++;
			}
		x=0;
		y++;
		}
		treeSprite = layer.loadTreeSprite();
		flowerSprite = layer.loadFlowerSprite();
		desertSprite = layer.loadDesertSprite();
   }
   public void setFrame(int frameWidth, int frameHeight, boolean restart)
   { 
	   this.frameWidth= frameWidth;
	   this.frameHeight = frameHeight;
	   width = frameWidth/scale;
	   height = frameHeight/scale;
	   repaint();
   }
   
   public void setX(int x, int direction, int move)
	{
	   	xOff+=x;
	   	int xx = ((frameWidth/2)-xOff+(width/2))/width;
   		int yy = ((frameHeight/2)-yOff+(height/2))/height;
  		if(xx>=0&&xx<=data[0].length-1&&secondlayer[yy][xx]==-1)
  		{
  			if(dataChar[yy][xx]=='r'){
  			setAnamation(direction, move, true);
  			xOff-=(x/2);
	  		}
	  		else{
	  			setAnamation(direction, move, false);
	  		}
  		repaint();
  		}
  		else{
  			xOff-=x;
  		}
	}
	
	public void setY(int y, int direction, int move)
	{
   		yOff+=y;
   		int xx =((frameWidth/2)-xOff+(width/2))/width;            
   		int yy = ((frameHeight/2)-yOff+(height/2))/height;
   		if(yy>=0&&yy<=data.length-1&&secondlayer[yy][xx]==-1){
   			if(dataChar[yy][xx]=='r'){
   			setAnamation(direction, move, true);
   			yOff-=(y/2);
	   		}
	   		else{
	   			setAnamation(direction, move, false);
	   		}
	   		repaint();
   		}
   		else{
   			yOff-=y;
   		}
	}
	
	public void setAnamation(int direction,int move, boolean swimming){
		this.direction = direction;
		this.move = move;
		this.swimming = swimming;
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
						if(x*width+xOff>-width&&x*width+xOff<frameWidth&&y*height+yOff>-height&&y*height+yOff<frameHeight){
							g.drawImage(sprite[count], x*width+xOff, y*height+yOff, width,height, null);
							
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
		
		int xx = 0;
		int yy = 0;
		int count2 = 0;
		while(yy<secondlayer.length){
			while(xx<secondlayer[yy].length){
				while(count2<300){
					if(secondlayer[yy][xx]==count2){
						if(xx*width+xOff>-width&&xx*width+xOff<frameWidth&&yy*height+yOff>-height&&yy*height+yOff<frameHeight){
							if(count2<100&&count2!=-1){
								g.drawImage(treeSprite[count2], xx*width+xOff, yy*height+yOff, width,height, null);
							}//tree
							else if(count2<200&&count2!=-1){
								g.drawImage(flowerSprite[count2-100], xx*width+xOff, yy*height+yOff, width,height, null);
							}//flower
							else{
								g.drawImage(desertSprite[count2-200], xx*width+xOff, yy*height+yOff, width,height, null);
							}//desert
						}
					}
					count2++;
				}
			count2=0;
			xx++;
			}
		xx=0;
		yy++;
		}
		/**/
		Player player = new Player(width,height,swimming);
			g.drawImage(player.getPlayer(direction,move),frameWidth/2,frameHeight/2,width,height,null);
		
   }
}