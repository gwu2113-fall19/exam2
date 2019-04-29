package factor;

/**
 * CS2113
 *
 * Computes a set of factorials.
 *
 * @author timwood@seas.gwu.edu
 *
 */

public class FactorJob  {

	int iterations;

	public FactorJob(int i) {
		iterations = i;
	}

	public void run(){
		for(int i = 0; i < iterations; i++)
		{
			Factor.calculateNextFactor();
		}
	}
}
