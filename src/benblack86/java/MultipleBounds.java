package benblack86.java;

import java.io.Closeable;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;


public class MultipleBounds {

	public static void main(String[] args) {
		try {
			FileReader r = new FileReader("resources/MultipleBounds.file.in.txt");
			FileWriter w = new FileWriter("resources/MultipleBounds.file.out.txt");
			
			copy(r, w);
		
		} catch(Throwable t) {
			t.printStackTrace();
		}
	}

	public static <S extends Readable & Closeable, T extends Appendable & Closeable> void copy(S src, T trg) throws IOException {
		try {
			CharBuffer buf = CharBuffer.allocate(32);
			
			while(src.read(buf) >= 0) {
				buf.flip(); // prepare buffer for writing
				trg.append(buf);
				buf.clear(); // prepare buffer for reading
			}
		} finally {
			// not good design as resources should be open and closed in the same block
			src.close();
			trg.close();
		}
	}
}
