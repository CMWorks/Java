package engine;
/*

public class PointUpdater extends Thread {
	private int fps;
	private boolean on = true;
	public static int frame = 0;
	
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000.0/60.0;
		double delta = 0;
		long lastTimer = System.currentTimeMillis();
	   	 
		while (true) {
			long now = System.nanoTime();
			delta+=(now-lastTime)/nsPerTick;
			lastTime = now;
			while(delta>=1){
				update();
				delta--;
			}
			if(System.currentTimeMillis()-lastTimer>=1000){
				lastTimer+=1000;
				fps = frame;
				frame = 0;
				System.out.println("Point fps: "+fps);
			}
		}
	}
	
	
	
	
	public int getFps() {return fps;}
	public boolean isOn() {return on;}
	public void setOn(boolean on) {this.on = on;}

	private void update() {
		MainRunable.graph.update();
	}
	
}

*/