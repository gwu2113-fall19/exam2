package factor;

/**
 * CS2113
 *
 * Creates a factorial job and times how long it takes.
 *
 * @author timwood@seas.gwu.edu
 *
 */
public class NonThreadedFactoring {

	public static void main(String[] args) {
		long t = System.nanoTime();

		FactorJob job = new FactorJob(1000);
		job.run();

		System.out.println("Elapsed time: " + (System.nanoTime() - t)/1000000 + " ms");
	}
}
