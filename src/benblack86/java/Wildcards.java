package benblack86.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Wildcards {

	/*
	 * The Get and Put principle: use an extends wildcard when you only get values
	 * out of a structure, use a super wildcard when you only put values into a
	 * structure, and don't use a wildcard when you both get and put.
	 * 
	 */
	public static void main(String[] args) {
		List<Number> items = new ArrayList<Number>();
		items.add(3);
		items.add(8.0);
		items.add(989898979879879879l);
		
		System.out.println(items);
		
		List<Double> more = Arrays.asList(3.0, 3.9, 8.0);
		
		Wildcards.copy(items, more);
		
		System.out.println(items);
	}
	
	
	public static <T> void copy(List<? super T> dest, List<? extends T> src) {
		for(T element : src) {
			dest.add(element);
		}
	}

}
