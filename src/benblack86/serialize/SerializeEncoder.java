package benblack86.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.codec.binary.Base64;

/**
 * Requires commons-codec for the encoding/decoding
 *
 */
public class SerializeEncoder {

	public static void main(String[] args) {
		try {
			String example = "Hello world";
			
			String encode = SerializeEncoder.seralizeAndEncode(example);
			String decode = SerializeEncoder.decodeAndDesealize(encode);
			
			System.out.printf("Encode: %s\n", encode);
			System.out.printf("Decode: %s\n", decode);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public static String seralizeAndEncode(String value) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(value);
		oos.close();
		return new String(Base64.encodeBase64(baos.toByteArray()));
	}

	public static String decodeAndDesealize(String value) throws IOException, ClassNotFoundException {
		byte data[] = Base64.decodeBase64(value);
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
		Object o = ois.readObject();
		ois.close();
		return (String)o;
	}
}
