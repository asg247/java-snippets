package benblack86.binary;

import java.util.BitSet;

public class BitSetTest {
	private static final int RUNS = 1000000;
	private static final int NUM_BITS = 64;
	private static long data = 0b0011100001101100001000100000010010101010001110000011000000000100l;

	private static long nativeBitTest() {
		int found = 0;

		long start = System.currentTimeMillis();

		for (int bit = 0; bit < NUM_BITS; ++bit) {
			if (((data >> bit) & 0b1) == 1) {
				++found;
			}
		}
		
		long time = System.currentTimeMillis() - start;
		
		//System.out.printf("found: %s\n", found);

		return time;
	}

	private static long bitsetBitTest() {		
		// setup
		BitSet bits = new BitSet(NUM_BITS);
		for (int bit = 0; bit < NUM_BITS; ++bit) {
			if (((data >> bit) & 0b1) == 0) {
				bits.set(bit);
			}
		}

		long start = System.currentTimeMillis();

		int found = 0;
		for (int i = bits.nextClearBit(0); i < NUM_BITS; i = bits.nextClearBit(i + 1)) {
			++found;
		}
		
		long time = System.currentTimeMillis() - start;
		
		//System.out.printf("found: %s\n", found);

		return time;
	}

	public static void main(String[] args) {
		double totalNative, totalBitset = totalNative = 0;

		for (double i = 0; i < RUNS; ++i) {
			totalNative += nativeBitTest();
			totalBitset += bitsetBitTest();
		}

		System.out.printf("Native: %f BitSet: %f", totalNative / RUNS, totalBitset / RUNS);
	}
}
