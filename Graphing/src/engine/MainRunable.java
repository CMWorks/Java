package engine;

import graph.MainGraphable;

public class MainRunable implements Runnable {

	private Thread runner;
	public static int frame = 0;
	private MainGraphable graph;

	public MainRunable() {
		this.graph = new MainGraphable();
		start();
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
		double nsPerTick = 1000000000.0 / 60.0;
		double delta = 0;
		long lastTimer = System.currentTimeMillis();

		while (runner == thisThread) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			while (delta >= 1) {
				update();
				delta--;
			}
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				graph.setFPS(frame);
				frame = 0;
			}
		}
	}

	public void stop() {
		if (runner != null) {
			runner = null;
		}
	}

	public void update() {
		graph.render();
	}

	public static void main(String[] args) {
		MainRunable program = new MainRunable();
	}

}

/*
 * 
 * package engine;
 * 
 * import graph.MainGraphable;
 * 
 * public class MainRunable{ private int FPS; public static MainGraphable graph;
 * private GraphUpdater gUp; private PointUpdater pUp;
 * 
 * public MainRunable() { this.graph = new MainGraphable(); this.gUp = new
 * GraphUpdater(); this.pUp = new PointUpdater(); start(); }
 * 
 * public void start() { pUp.start(); gUp.start(); }
 * 
 * public void stop() {
 * 
 * }
 * 
 * public void update() { graph.render(); }
 * 
 * public static void main(String[] args) { MainRunable program = new
 * MainRunable(); }
 * 
 * }
 * 
 */