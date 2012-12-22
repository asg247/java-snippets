package benblack86.pattern;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Functions {

	/*
	 * Function pattern: converts arbitrary method into an object
	 */
	
	public static void main(String[] args) {
		Function<String, Integer, Error> length = new Function<String, Integer, Error>() {
			public Integer apply(String s) {
				return s.length();
			}
		};
		
		Function<String, Class<?>, ClassNotFoundException> forName = new Function<String, Class<?>, ClassNotFoundException>() {
			public Class<?> apply(String s) throws ClassNotFoundException {
				return Class.forName(s);
			}
		};
		
		Function<String, Method, Exception> getRunMethod = new Function<String, Method, Exception>() {
			public Method apply(String s) throws ClassNotFoundException, NoSuchMethodException {
				return Class.forName(s).getMethod("run");
			}
		};
		
		try {
			List<String> strings = Arrays.asList("java.lang.Thread", "java.lang.Runnable");
			
			System.out.println(applyAll(length, strings));
			
			System.out.println(applyAll(forName, strings));
			
			System.out.println(applyAll(getRunMethod, strings));
			
		} catch(Throwable t) {
			t.printStackTrace();
		}
	}

	public static <A, B, X extends Throwable> List<B> applyAll(Function<A, B, X> f, List<A> list) throws X {
		List<B> results = new ArrayList<B>();
		
		for(A a : list) {
			results.add(f.apply(a));
		}
		
		return results;
	}
}

interface Function<A, B, X extends Throwable> {
	public B apply(A a) throws X;
}

