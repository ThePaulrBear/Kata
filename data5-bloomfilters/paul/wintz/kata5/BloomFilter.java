package paul.wintz.kata5;
import static java.lang.Math.abs;

import java.security.*;

public class BloomFilter {

	private final MessageDigest sha512;
	private final Bitmap bitmap;

	public BloomFilter()  {
		try {
			sha512 = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		bitmap = new Bitmap.Builder()
				.setByteCount(bitCount())
				.build();
	}

	public int bitCount() {
		return 2560000;
	}

	public void addWord(String word) {
		bitmap.turnOnBit(wordToIndex(word));
	}

	public int wordToIndex(String word) {
		sha512.update(word.getBytes());
		byte[] bytes = sha512.digest();

		int index = 1;
		for(int i = 0; i < 8; i++) {
			index = index * bytes[i] + 1; //One is necessary to not get stuck at zero
		}

		return abs(index) % bitCount();
	}

	public boolean containsWord(String word) {
		return bitmap.getBit(wordToIndex(word));
	}

}
