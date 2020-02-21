package game.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	public String path;
	public BufferedImage spriteSheet;
	
	public SpriteSheet(BufferedImage s){
		this.spriteSheet = s;
	}
	
	public BufferedImage grabSprite(int x, int y, int width, int height){
		BufferedImage sprite = spriteSheet.getSubimage(x, y, width, height);
		return sprite;
	}
	
}
