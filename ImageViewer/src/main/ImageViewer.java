package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ImageViewer extends JFrame implements MouseWheelListener, MouseListener, MouseMotionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel image = new ImagePanel();
	private File[] image_list;
	private int[] image_index;
	private int size = 0;
	private String folder_path = "";
	public static BufferedImage current_image;
	private BufferedImage Image_Error;

	public static int x, y, w, h, preX, preY, mouseX, mouseY, current_position, Monitor_Width, Monitor_Height;

	private double ratio = 1;
	private int mode = 2;

	public ImageViewer() {
		folder_path = JOptionPane.showInputDialog("File Path");
		ImageViewer.Monitor_Width = 1920;
		ImageViewer.Monitor_Height = 1080;
		this.addMouseWheelListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setSize(Monitor_Width, Monitor_Height);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.add(image);
		ImageViewer.x = 0;
		ImageViewer.y = 0;
		ImageViewer.w = 1920;
		ImageViewer.h = 1080;
		ImageViewer.preX = 0;
		ImageViewer.preY = 0;
		ImageViewer.mouseX = 0;
		ImageViewer.mouseY = 0;
		ImageViewer.current_position = 0;
		try {
			URL imgUrl = this.getClass().getResource("/file_not_found.png");
			Image_Error = ImageIO.read(imgUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		readeImage();
		try {
			setImage(current_position);
		} catch (NullPointerException e) {
			setImageError();
			System.err.println("Null at iamge");
		}
		this.setVisible(true);
	}

	private void readeImage() {
		File myfolder = new File(folder_path);
		image_list = myfolder.listFiles();
		image_index = new int[image_list.length];
		Arrays.sort(image_list, new File_Compare());
		int image_i = 0;
		int index_i = 0;
		for (File file : image_list) {
			if (!file.isDirectory() && isImage(file)) {
				image_index[index_i] = image_i;
				index_i++;
				size++;
			}
			image_i++;
		}
		for (int j = 0; j < size; j++) {
			System.out.println(image_list[image_index[j]].getName());
		}

	}

	private void setImage(int position) throws NullPointerException {
		double start = System.nanoTime();
		if (size == 0) {
			return;
		}
		if (position < 0) {
			position = size - 1;
		} else if (position >= size) {
			position = 0;
		}
		try {
			current_image = ImageIO.read(image_list[image_index[position]]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		current_position = position;

		int set = 0;
		if (mode == 2) { // auto
			set = 0;
			ratio = (double) Monitor_Height / current_image.getHeight();
			if ((int) (current_image.getWidth() * ratio) > 1920) {
				ratio = (double) Monitor_Width / current_image.getWidth();
				set = 1;
			}
			if (ratio > 1) {
				ratio = 1;
				set = -1;
			}
		} else { // keep ratio, start at top
			y = 0;
		}
		w = (int) (current_image.getWidth() * ratio);
		h = (int) (current_image.getHeight() * ratio);
		if (set == -1) {
			x = (Monitor_Width - w) / 2;
			y = (Monitor_Height - h) / 2;
		} else if (set == 0) {
			y = 0;
			x = (Monitor_Width - w) / 2;
		} else if (set == 1) {
			x = 0;
			y = (Monitor_Height - h) / 2;
		}
		System.out.println("Time to set: " + ((System.nanoTime() - start) / 1000000000.0) + " sec");
	}

	private void setImageError() {
		current_image = Image_Error;
		int set = 0;
		if (mode == 2) { // auto
			set = 0;
			ratio = (double) Monitor_Height / current_image.getHeight();
			if ((int) (current_image.getWidth() * ratio) > 1920) {
				ratio = (double) Monitor_Width / current_image.getWidth();
				set = 1;
			}
			if (ratio > 1) {
				ratio = 1;
				set = -1;
			}
		} else { // keep ratio, start at top
			y = 0;
		}
		w = (int) (current_image.getWidth() * ratio);
		h = (int) (current_image.getHeight() * ratio);
		if (set == -1) {
			x = (Monitor_Width - w) / 2;
			y = (Monitor_Height - h) / 2;
		} else if (set == 0) {
			y = 0;
			x = (Monitor_Width - w) / 2;
		} else if (set == 1) {
			x = 0;
			y = (Monitor_Height - h) / 2;
		}
	}

	private static boolean isImage(File source) {
		if (source.getName().lastIndexOf('.') == -1) {
			return false;
		}
		String ext = source.getName().substring(source.getName().lastIndexOf('.'));
		switch (ext) {
		case ".png":
			return true;
		case ".jpg":
			return true;
		default:
			return false;
		}
	}

	public void render() {
		image.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (w > Monitor_Width) { // non-centered x, needs to be bounded
			x += e.getX() - preX;
			if (x > 0)
				x = 0;
			if (x < Monitor_Width - w)
				x = Monitor_Width - w;
		}
		if (h > Monitor_Height) { // non-centered y, needs to be bounded
			y += e.getY() - preY;
			if (y > 0)
				y = 0;
			if (y < Monitor_Height - h)
				y = Monitor_Height - h;
		}
		preX = e.getX();
		preY = e.getY();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		preX = e.getX();
		preY = e.getY();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// in = -3, out = 3;
		ratio += ratio / (-e.getUnitsToScroll() * 10);
		if (ratio < 50.0 / current_image.getWidth()) {
			ratio = 50.0 / current_image.getWidth();
		}
		if (ratio < 50.0 / current_image.getHeight()) {
			ratio = 50.0 / current_image.getHeight();
		}
		int prew = w;
		int preh = h;
		w = (int) (current_image.getWidth() * ratio);
		h = (int) (current_image.getHeight() * ratio);
		if (w < Monitor_Width) { // centers x
			x = (Monitor_Width - w) / 2;
		} else { // centers around h/2
			x -= (w - prew) / 2;
			if (x > 0)
				x = 0;
			if (x < Monitor_Width - w)
				x = Monitor_Width - w;
		}
		if (h < Monitor_Height) { // centers y
			y = (Monitor_Height - h) / 2;
		} else { // centers around h/2
			y -= (h - preh) / 2;
			if (y > 0)
				y = 0;
			if (y < Monitor_Height - h)
				y = Monitor_Height - h;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == 37)
			try {
				setImage(current_position - 1);
			} catch (NullPointerException g) {
				setImageError();
				System.err.println("Null at iamge - 1");
			}
		else if (code == 39)
			try {
				setImage(current_position + 1);
			} catch (NullPointerException g) {
				setImageError();
				System.err.println("Null at iamge + 1");
			}
		else if (code == 38)
			y += 20;
		else if (code == 40)
			y -= 20;
		if (mode == 2) {
			if (w > Monitor_Width) { // non-centered x, needs to be bounded
				if (x > 0)
					x = 0;
				if (x < Monitor_Width - w)
					x = Monitor_Width - w;
			}
			if (h > Monitor_Height) { // non-centered y, needs to be bounded
				if (y > 0)
					y = 0;
				if (y < Monitor_Height - h)
					y = Monitor_Height - h;
			}
			if (w < Monitor_Width) { // centered x
				x = (Monitor_Width - w) / 2;
			}
			if (h < Monitor_Height) { // centered y
				y = (Monitor_Height - h) / 2;
			}

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == 27) {
			System.exit(0);
		}
		if (code == 97) { // lock
			mode = 1;
		}
		if (code == 98) { // auto
			mode = 2;
			try {
				setImage(current_position);
			} catch (NullPointerException g) {
				setImageError();
				System.err.println("Null at reset iamge");
			}
		}

	}

	static class File_Compare implements Comparator<File> {
		public int compare(File f1, File f2) {
			if (!isImage(f1) || !isImage(f2)) {
				return f1.compareTo(f2);
			}
			String n1 = f1.getName();
			String n2 = f2.getName();
			int n1_index = 0;
			int n2_index = 0;
			for (n1_index = 0; n1_index < n1.length(); n1_index++) {
				if (!isNum(n1.charAt(n1_index))) {
					break;
				}
			}
			for (n2_index = 0; n2_index < n2.length(); n2_index++) {
				if (!isNum(n2.charAt(n2_index))) {
					break;
				}
			}
			if (n1_index == 0 && n2_index == 0) { // no number to test
				return n1.compareTo(n2);
			} else {
				try {
					n1_index = Integer.parseInt(n1.substring(0, n1_index));
					n2_index = Integer.parseInt(n2.substring(0, n2_index));
				} catch (Exception e) {
					return n1.compareTo(n2);
				}
				return n1_index - n2_index;
			}
		}

		private boolean isNum(char c) {
			return (byte) c > 47 && (byte) c < 58;
		}
	}
}
