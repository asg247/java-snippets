package benblack86.java;

import java.util.ArrayList;
import java.util.List;

public class Varargs {
	public static void main(String[] args) {
		System.out.println(Varargs.toList(new String[]{"one", "two"}));
		// System.out.println(Varargs.toList(null)); runtime exception
		System.out.println(Varargs.toList(new Object[]{null}));
		System.out.println(Varargs.toList(new Object[]{}));
	}
	
	public static <T> List<T> toList(T... array) {
		List<T> list = new ArrayList<T>();
		
		for(T element : array) {
			list.add(element);
		}
		
		return list;
	}
}
