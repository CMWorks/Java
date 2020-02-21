package game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;
import game.gfx.Map;
import game.gfx.MiniMap;

//To up

import java.awt.Toolkit;

public class Game extends JFrame implements Runnable{

		Toolkit tk = Toolkit.getDefaultToolkit();
     int xSize = ((int) tk.getScreenSize().getWidth());
     int ySize = ((int) tk.getScreenSize().getHeight());

	public final int WIDTH = xSize;
	public final int HEIGHT = ySize;
	public static final String NAME = "Game";
	private int moveSpeed = 2;
	private JFrame frame;
	private JPanel entireScreen, MINIMAP, BUTTON;
	private Map map = new Map(WIDTH, HEIGHT);
	private MiniMap miniMap = new MiniMap(WIDTH, HEIGHT);
	private KeyMove keymove;
	private Buttons button;
	Thread runner;
	Image[] picture = new Image[6];
	int totalPictures = 0;
	int current = 0;
	private boolean restart;
	private static int FPS;

	public Game(){
		frame = new JFrame(NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setSize(500,500);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
        frame.setVisible(true);
        entireScreen = new JPanel();
        entireScreen.setLayout(new BorderLayout());
       // entireScreen.add(pause);
        //entireScreen.add(restart);

        keymove = new KeyMove(map, frame, moveSpeed);
        button = new Buttons();
        render();
       start();
	}

	public void render(){
		//BUTTON.add(button.addStorage(),BorderLayout.SOUTH);
		//BUTTON.add(button.addMenu());
		//entireScreen.add(button.addStatBal(), BorderLayout.NORTH);
		//entireScreen.add(map, BorderLayout.CENTER);
		//frame.add(BUTTON);
		//frame.add(MINIMAP);
		//entireScreen.add(miniMap);
		entireScreen.add(map);
		frame.add(entireScreen);
		frame.setContentPane(entireScreen);
	}

	public void start() {
	   if (runner == null) {
	      runner = new Thread(this);
	      runner.start();
	   }
	}

	   public void run() {
	     Thread thisThread = Thread.currentThread();
	     long lastTime = System.nanoTime();
    	 double nsPerTick = 1000000000.0/60.0;
    	 double delta = 0;
    	 int frames = 0;
    	 long lastTimer = System.currentTimeMillis();

		     while (runner == thisThread) {
		    	 long now = System.nanoTime();
		    	 delta+=(now-lastTime)/nsPerTick;
		    	 lastTime = now;
		    	 while(delta>=1){
		    		 update();
		    		 frames++;
		    		 delta--;
		    	 }
		    	 if(System.currentTimeMillis()-lastTimer>=1000){
		    		 lastTimer+=1000;
		    		 FPS = frames;
		    		 frames = 0;
		    		 restart = true;
		    		 button.setTime();
		    	 }
		     }
	   }

	   public void stop() {
	     if (runner != null) {
	       runner = null;
	     }
	   }

	public static int getFPS(){
		return FPS;
	}

	private void update(){
		map.setFrame(frame.getWidth(),frame.getHeight(),restart);
	   	 if(keymove.getGrid()=='X'){
	   		 map.setX(keymove.getSpeed(),keymove.getDirection(), keymove.getMove());
	   	 }
	   	 else{
	   		 map.setY(keymove.getSpeed(),keymove.getDirection(), keymove.getMove());
	   	 }
	}
	private static void runGUI()
    {
        JFrame.setDefaultLookAndFeelDecorated(true);

        Game program = new Game();
    }


    public static void main(String[] args)
    {
        /* Methods that create and show a GUI should be
           run from an event-dispatching thread */
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run() {
                runGUI();
            }
        }); //note this is an inner class created within a program --leave as is
    } //end of main

}
