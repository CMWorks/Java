package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private BufferedImage background;
	private static final long serialVersionUID = 1L;

	public ImagePanel() {
		try {
			URL imgUrl = this.getClass().getResource("/background.png");
			background = ImageIO.read(imgUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, 1920, 1080, null);
		try {
			g.drawImage(ImageViewer.current_image, ImageViewer.x, ImageViewer.y, ImageViewer.w, ImageViewer.h, null);			
		} catch (Exception e) {
			System.out.println("Could not draw image");
			g.drawString("File not suported: ", ImageViewer.Monitor_Width/2, ImageViewer.Monitor_Height/2);
		}
	}

}
