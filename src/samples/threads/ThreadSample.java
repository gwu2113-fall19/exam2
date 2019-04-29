package samples.threads;

/**
 * Starts three tasks in separate threads.
 * Waits until all threads complete then prints
 * a message.
 * 
 * 
 * @author timwood@seas.gwu.edu
 * Written 12/5/11
 *
 */
public class ThreadSample {

	// Note that this is a synchronized function.
	public static synchronized void finishMsg(){
		System.out.println(Thread.currentThread().getName() + " is inside finishMsg...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " is done with finishMsg.");
	}
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new SimpleJob(500));
		t1.setName("T_1");
		Thread t2 = new Thread(new SimpleJob(250));
		t2.setName("T_2");
		Thread t3 = new Thread(new SimpleJob(500));
		t3.setName("T_3");
		
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
