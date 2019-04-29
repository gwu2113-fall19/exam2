package factor;

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

		// your code here!

		System.out.println("Time: " + (System.nanoTime() - t)/ 1000000 + " ms");
	}
}
