package paul.wintz.kata5;
import static org.junit.Assume.assumeTrue;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ABloomFilterSlow {

	BloomFilter filter = new BloomFilter();

	@Test
	public void stressTest() {

		int falsePositives = 0;
		long seed = 1;
		for(; seed < 100001; seed++) {
			String word = Long.toBinaryString(seed);

			if(filter.containsWord(word)) {
				falsePositives++;
			}

			filter.addWord(word);
			assertTrue(filter.containsWord(word));

			if(seed % 1000 == 0){
				printFalsePositiveCount(falsePositives, seed);
			}
		}
		printFalsePositiveCount(falsePositives, seed);
	}

	public void printFalsePositiveCount(int falsePositives, long seed) {
		double falsePositivePercent = 100.0 * falsePositives / seed;
		System.out.println("falsePositives rate: " + falsePositivePercent + " %");
		assumeTrue(falsePositivePercent < 50.0);
	}
}
