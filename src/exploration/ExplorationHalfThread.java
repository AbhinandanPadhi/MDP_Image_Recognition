package exploration;

import config.Constant;
import connection.ConnectionSocket;
import robot.Robot;
import robot.SimulatorRobot;

import java.util.concurrent.atomic.AtomicBoolean;

public class ExplorationHalfThread extends Thread{
	private Robot r;
	private int time;
	private int percentage;
	private int speed;
	private boolean image_recognition;

	private static final AtomicBoolean running = new AtomicBoolean(false);
	private static final AtomicBoolean completed = new AtomicBoolean(false);
	private static ExplorationHalfThread thread = null;

	private ExplorationHalfThread(Robot r, int time, int percentage, int speed, boolean image_recognition) {
		super("ExplorationThread");
		this.r = r;
		this.time = time;
		this.percentage = percentage;
		this.speed = speed;
		this.image_recognition = image_recognition;
		start();
	}
	
	public void run() {
		running.set(true);
		
		// Check if it is the simulator mode
		Exploration_Half e = new Exploration_Half();
		e.Exploration_Half(r, time, percentage, speed, image_recognition);

		stopThread();
	}

	public static ExplorationHalfThread getInstance(Robot r, int time, int percentage, int speed, boolean image_recognition) {
		if (thread == null) {
			thread = new ExplorationHalfThread(r, time, percentage, speed, image_recognition);
		}
		return thread;
	}

	public static boolean getRunning() {
		return running.get();
	}

	public static void stopThread() {
		running.set(false);
		thread = null;
	}

	public static boolean getCompleted() {
		return completed.get();
	}
}
