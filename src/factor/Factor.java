package factor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * CS2113
 *
 * Compute factors of a random number.
 *
 * @author timwood@seas.gwu.edu
 *
 */
public class Factor {

	private static Random rand = new Random(12345);

	public static void calculateNextFactor() {
		long n;
		synchronized (rand) {
			n = Math.abs(rand.nextLong()) % 10000000l;
		}
		List<Long> factors = new ArrayList<Long>();
		for (long i = 2; i <= n; i++) {
			while (n % i == 0) {
				factors.add(i);
				n /= i;
			}
		}
	}
}
