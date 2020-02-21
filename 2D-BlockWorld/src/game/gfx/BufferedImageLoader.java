package game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class BufferedImageLoader {

	public BufferedImage loadImage(String pathRelative) throws IOException{
		URL url = this.getClass().getResource(pathRelative);
		BufferedImage img = ImageIO.read(url);
		return img;
	}
}

