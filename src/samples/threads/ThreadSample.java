package samples.threads;

/**
 * Starts three tasks in separate threads.
 * Waits until all threads complete then prints
 * a message.
 *
 *
 * @author timwood@gwu.edu
 * Written 12/4/18
 *
 */
public class ThreadSample {

	public static void main(String[] args) {

		Thread t1 = new Thread(new SimpleJob(500));
		Thread t2 = new Thread(new SimpleJob(250));
		Thread t3 = new Thread(new SimpleJob(500));

		t1.start();
		t2.start();
		t3.start();
		System.out.println("Started all threads.");

		// Wait for all threads to finish
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("All threads finished.");

	}
}