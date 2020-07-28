package main;

public class App implements Runnable {

	private Thread runner;
	public static int frame = 0;
	private ImageViewer viewer;

	public App() {
		this.viewer = new ImageViewer();
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
				frame++;
				delta--;
			}
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
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
		viewer.render();
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		App program = new App();
	}

}