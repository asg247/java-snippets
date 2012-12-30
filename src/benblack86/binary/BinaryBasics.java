package benblack86.binary;

public class BinaryBasics {
	public static void main(String args[]) {

		System.out.printf("Capacity of primitives:\n");
		System.out.printf("  byte [ 8 bits]  min:%s max:%s\n", Byte.MIN_VALUE, Byte.MAX_VALUE);
		System.out.printf(" short [16 bits] min:%s max:%s\n", Short.MIN_VALUE, Short.MAX_VALUE);
		System.out.printf("   int [32 bits] min:%s max:%s\n", Integer.MIN_VALUE, Integer.MAX_VALUE);
		System.out.printf("  long [64 bits] min:%s max:%s\n", Long.MIN_VALUE, Long.MAX_VALUE);

		// 0x => hexadecimal (0xF => 15, 0x10 => 16)
		// 0b => binary (0b11 => 3)
		
		// i >> x: shift bits in i right by x positions (2 >> 1 => 1)
		// i << x: shift bits in i left by x positions (1 << 1 => 2)
		
		System.out.printf("\nPrint binary using method 1:\n");
		for(int i = 125; i < 130; i++) {
			printBinary(i);
		}
		printBinary(-100);
		
		System.out.printf("\nPrint binary using method 2:\n");
		for(int i = 125; i < 130; i++) {
			printBinary2(i);
		}
		printBinary2(-100);
		
		
		System.out.printf("\nBinary operations:\n");
		printBinary(128);     // 00000000000000000000000010000000 (128)
		printBinary(~128);    // 11111111111111111111111101111111 (-129)
		printBinary(-128);    // 11111111111111111111111110000000 (-128)
		printBinary(~-128);   // 00000000000000000000000001111111 (127)
		printBinary(128>>>1); // 00000000000000000000000001000000 (64) 
		printBinary(128>>>2); // 00000000000000000000000000100000 (32)
		printBinary(128>>1);  // 00000000000000000000000001000000 (64)
		printBinary(128>>2);  // 00000000000000000000000000100000 (32)
		printBinary(-127>>>1);// 01111111111111111111111111000000 (2147483584)
		printBinary(-127>>>2);// 00111111111111111111111111100000 (1073741792)
		printBinary(-127>>1); // 11111111111111111111111111000000 (-64)
		printBinary(-127>>2); // 11111111111111111111111111100000 (-32)
	}
	
	public static void printBinary(int i) {
		System.out.printf("%12s: ", i);
		for(int k = 31; k > -1; k--) {
			// need abs to work with negative numbers
			System.out.printf("%s", Math.abs(i >> k) % 2);
		}
		System.out.printf("\n");
	}
	
	public static void printBinary2(int i) {
		System.out.printf("%12s: ", i);
		for(int k = 31; k > -1; k--) {
			// shift bit to the most significant position and apply mask
			System.out.printf("%s", i >> k & 0b1);
		}
		System.out.printf("\n");
	}
}
