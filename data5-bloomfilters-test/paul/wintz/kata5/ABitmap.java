package paul.wintz.kata5;

import static org.hamcrest.CoreMatchers.*;

import org.hamcrest.Matcher;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class ABitmap {
	private static final int BITS_PER_BYTE = 8;

	private Bitmap bitmap = new Bitmap.Builder().setByteCount(4).build();

	@Test(expected = IllegalArgumentException.class)
	public void throwsIfNonPositiveBytes() {
		new Bitmap.Builder().setByteCount(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwsIfNonPositiveBits() {
		new Bitmap.Builder().setBitsCount(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwsIfBitsNotDivisiableBy8() {
		new Bitmap.Builder().setBitsCount(17);
	}

	@Test
	public final void containsZerosWhenCreated() {
		for(int i = 0; i < bitmap.getBitsCount(); i++) {
			assertThat(bitmap.getBit(0), isOff());
		}
	}

	@Test
	public void containsNumberOfBitsSpecified() {
		assertThat(new Bitmap.Builder()
				.setBitsCount(24)
				.build()
				.getBitsCount(),
				is(equalTo(24)));
	}

	@Test
	public void containsNumberOfBytesSpecified() {
		assertThat(new Bitmap.Builder()
				.setByteCount(29)
				.build()
				.getBitsCount(),
				is(equalTo(29 * BITS_PER_BYTE)));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void throwsIfNegativeIndexIsTurnedOn() {
		bitmap.turnOnBit(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void throwsIfTooLargeAnIndexIsTurnedOn() {
		bitmap.turnOnBit(bitmap.getBitsCount());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void throwsIfNegativeIndexIsTurnedOff() {
		bitmap.turnOffBit(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void throwsIfTooLargeAnIndexIsTurnedOff() {
		bitmap.turnOffBit(bitmap.getBitsCount());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void throwsIfNegativeIndexIsRead() {
		bitmap.getBit(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void throwsIfTooLargeAnIndexIsRead() {
		bitmap.getBit(bitmap.getBitsCount());
	}

	@Test
	public void canTurnOnFirstBit() {
		bitmap.turnOnBit(0);
		assertThat(bitmap.getBit(0), isOn());
	}

	@Test
	public void canTurnOnNinthBit() {
		bitmap.turnOnBit(8);
		assertThat(bitmap.getBit(8), isOn());
	}

	@Test
	public void canTurnOnLastBit() {
		int lastBit = bitmap.getBitsCount() - 1;
		bitmap.turnOnBit(lastBit);
		assertThat(bitmap.getBit(lastBit), isOn());
	}

	@Test
	public void canTurnBitOnThenOff() {
		bitmap.turnOnBit(7);
		bitmap.turnOffBit(7);
		assertThat(bitmap.getBit(7), isOff());
	}

	@Test
	public void bitStaysOnIfTurnedOnWhenOn() {
		bitmap.turnOnBit(12);
		bitmap.turnOnBit(12);
		assertThat(bitmap.getBit(12), isOn());
	}

	@Test
	public void bitStaysOffIfTurnedOffWhenOff() {
		bitmap.turnOffBit(2);
		bitmap.turnOffBit(2);
		assertThat(bitmap.getBit(2), isOff());
	}

	@Test
	public void twoBitsTurnOn() {
		bitmap.turnOnBit(0);
		bitmap.turnOnBit(1);
		assertThat(bitmap.getBit(0), isOn());
		assertThat(bitmap.getBit(1), isOn());
	}

	@Test
	public void twoBitsTurnOff() {
		bitmap.turnOnBit(0);
		bitmap.turnOnBit(1);
		bitmap.turnOffBit(0);
		bitmap.turnOffBit(1);
		assertThat(bitmap.getBit(0), isOff());
		assertThat(bitmap.getBit(1), isOff());
	}

	@Test
	public void allBitsTurnOn() {
		for(int i = 0; i < bitmap.getBitsCount(); i++) {
			bitmap.turnOnBit(i);
		}
		for(int i = 0; i < bitmap.getBitsCount(); i++) {
			assertThat(bitmap.getBit(i), isOn());
		}
	}

	@Test
	public void offBitStaysOffWhenAllOthersTurnedOn() {
		for(int i = 1; i < bitmap.getBitsCount(); i++) {
			bitmap.turnOnBit(i);
		}
		assertThat(bitmap.getBit(0), isOff());
	}

	private Matcher<Boolean> isOn() {
		return is(equalTo(true));
	}

	private Matcher<Boolean> isOff() {
		return is(equalTo(false));
	}

}
