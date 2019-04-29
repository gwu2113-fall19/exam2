package samples.threads;

/**
 * A simple task that can be run inside a Thread.
 * Sleeps for a configurable amount of time,
 * then prints a message.  Repeats this process
 * five times.
 * 
 * @author timwood@seas.gwu.edu
 * Written 12/5/11
 *
 */
public class SimpleJob implements Runnable {
	// to be used by a thread, must "implement Runnable"

	private int sleep;

	public SimpleJob(int sleep) {
		this.sleep = sleep;
	}

	// Function called when a thread with this task is started
	public void run() {
		System.out.println(Thread.currentThread().getName() + " started.");
		for (int i = 1; i <= 5; i++) {
			try {
				Thread.sleep(sleep);
				System.out.println(Thread.currentThread().getName() + " finished sleep number " + i);
			} catch (InterruptedException e) {e.printStackTrace(); }
		}
	}
}