package paul.wintz.kata5;

import static com.google.common.base.Preconditions.*;
import static paul.wintz.utils.Utils.checkPositive;

public class Bitmap {

	private static final int BITS_PER_BYTE = 8;

	private final byte[] bytes;
	private Bitmap(int bytes) {
		this.bytes = new byte[bytes];
	}

	public void turnOnBit(int index){
		checkElementIndex(index, getBitsCount());
		int byteNumber = index / 8;
		int bitInByte = index % 8;
		bytes[byteNumber] |= 1 << bitInByte;
	}

	public void turnOffBit(int index) {
		checkElementIndex(index, getBitsCount());
		int byteNumber = index / 8;
		int bitInByte = index % 8;
		int negation = (~(bytes[byteNumber]) & 0xFF) | ( 1 << bitInByte);
		bytes[byteNumber] = (byte) ~negation;
	}

	public boolean getBit(int index) {
		checkElementIndex(index, getBitsCount());
		int byteNumber = index / 8;
		int bitInByte = index % 8;
		return ((bytes[byteNumber] & 0xFF) & (1 << bitInByte)) == 1 << bitInByte;
	}

	public int getBitsCount() {
		return bytes.length * BITS_PER_BYTE;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < bytes.length; i++) {
			if(i > 0) {
				sb.append(" ");
			}
			sb.append(byteToBinaryString(bytes[i]));
		}
		return sb.toString();
	}

	private static String byteToBinaryString(byte b) {
		return String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
	}

	public static class Builder {
		private int bytes;

		public Builder setByteCount(int bytes) {
			checkPositive(bytes);
			this.bytes = bytes;
			return this;
		}

		public Bitmap build() {
			return new Bitmap(bytes);
		}

		public Builder setBitsCount(int bitsCount) {
			checkPositive(bitsCount);
			checkArgument(bitsCount % BITS_PER_BYTE == 0, "bitsCount must be a multiple of 8 but instead was '%s'", bitsCount);
			bytes = bitsCount / BITS_PER_BYTE;
			return this;
		}
	}

}
