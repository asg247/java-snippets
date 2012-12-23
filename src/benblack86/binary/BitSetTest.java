package benblack86.binary;

import java.util.BitSet;

public class BitSetTest {
	private static final int RUNS = 100000;
	private static final int NUM_BITS = 64;
	// every other bit set
	private static long setMask = 0xAAAAAAAAAAAAAAAAl;

	private static long nativeBitTest() {
		long bits = 0;
		int found = 0;
		bits |= setMask;

		long start = System.currentTimeMillis();

		for (int bit = 0; bit < NUM_BITS; ++bit) {
			if (((bits >>> bit) & 0b1) == 0) {
				++found;
			}
		}
		
		System.out.printf("found: %s\n", found);

		return System.currentTimeMillis() - start;
	}

	private static long bitsetBitTest() {
		BitSet bits = new BitSet(NUM_BITS);
		for (int bit = 1; bit < NUM_BITS; bit += 2) {
			bits.set(bit);
		}

		long start = System.currentTimeMillis();

		int found = 0;
		for (int i = bits.nextClearBit(0); i < NUM_BITS; i = bits.nextClearBit(i + 1)) {
			++found;
		}
		
		System.out.printf("found: %s\n", found);

		return System.currentTimeMillis() - start;
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
