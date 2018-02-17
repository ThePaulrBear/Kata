package paul.wintz.kata5;
import org.junit.Test;

import static org.junit.Assert.*;

public class ABloomFilterFast {

	BloomFilter filter = new BloomFilter();

	@Test
	public final void containsAddedWord() {
		filter.addWord("aardvark");
		assertTrue(filter.containsWord("aardvark"));
	}

	@Test
	public void doesNotContainWordWhenNoneAdded() {
		assertFalse(filter.containsWord("Zilch"));
	}

	@Test
	public void containsTwoAddedWords() {
		filter.addWord("Autometalogolex");
		filter.addWord("Gullible");

		assertTrue(filter.containsWord("Autometalogolex"));
		assertTrue(filter.containsWord("Gullible"));
	}

}
