package factor;

import java.util.ArrayList;

/**
 * CS2113
 *
 * Creates a set of threaded factorial jobs and times how long they takes.
 *
 * @author timwood@seas.gwu.edu
 *
 */
public class ThreadedFactoring {

	public static void main(String[] args) {
		long t = System.nanoTime();

		ArrayList<Thread> threads = new ArrayList<>();
		int numThreads = 200;
		for (int i=0; i < numThreads; i++) {
			Thread t1 = new Thread(new FactorJob(1000/numThreads));
			threads.add(t1);
		}

		for (Thread temp: threads) {
			temp.start();
		}

		for (Thread temp: threads) {
			try {
				temp.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


		// your code here!

		System.out.println("Time: " + (System.nanoTime() - t)/ 1000000 + " ms");
	}
}
