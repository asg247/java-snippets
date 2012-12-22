package benblack86.java;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Reification {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Collection<String> collection = Arrays.asList("hi", "there");
		List<String> list = Reification.asList(collection);
		System.out.println(list);
		
		List<Object> objects = Arrays.<Object>asList("three", "four");
		List<String> strings = Reification.promote(objects);
		System.out.println(strings);
		
		String[] stringArray = Reification.toArray(strings, new String[0]);
		System.out.println(Arrays.toString(stringArray));
		
		String[] stringArray2 = Reification.toArray(strings, String.class);
		System.out.println(Arrays.toString(stringArray2));
	}

	public static <T> List<T> asList(Collection<T> collection) {
		// cannot do List<T> as information is erased
		if (collection instanceof List<?>) {
			// can cast using T as the argument expects T
			return (List<T>) collection;
		} else {
			throw new IllegalArgumentException("Argument not a list");
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> promote(List<Object> objs) {
		for (Object obj : objs) {
			if (!(obj instanceof String)) {
				throw new ClassCastException();
			}
		}
		
		return (List<String>)(List<?>)objs; // unchecked cast
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(Collection<T> collection, T[] a) {
		if (a.length < collection.size()) {
			a = (T[])Array.newInstance(a.getClass().getComponentType(), collection.size()); // unchecked cast
		}
		
		int i = 0;
		for(T element : collection) {
			a[i++] = element;
		}
		
		return a;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(Collection<T> collection, Class<T> c) {
		T[] a = (T[])Array.newInstance(c, collection.size()); // unchecked cast
		
		int i = 0;
		for(T element : collection) {
			a[i++] = element;
		}
		
		return a;
	}
}
