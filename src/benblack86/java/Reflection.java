package benblack86.java;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Reflection {

	public static void main(String[] args) {
		try {
			System.out.println(GenericReflection.newInstance(new String()));
			
			System.out.println(GenericReflection.getComponentType(new String[0]));
			
			System.out.println(Arrays.toString(GenericReflection.newArray(String.class, 10)));
			
			System.out.println(Arrays.toString(GenericReflection.newArray(new Integer[0], 100)));
			
			Collection<String> items = new ArrayList<String>();
			items.add("hi");
			Collection<String> items2 = Reflection.copy(items);
			items.add("bye");
			System.out.println(items2);
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	public static <T, C extends Collection<T>> C copy(C collection) throws Exception {
		// GenericReflection class hides the unchecked cast
		C copy = GenericReflection.newInstance(collection);
		copy.addAll(collection);
		return copy;
	}

}

/*
 * The idea is to encapsulate unchecked casts into a number of library methods
 */
class GenericReflection {
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(T obj) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, NoSuchMethodException {
		// can only handle zero-argument constructors
		Object newObj = obj.getClass().getConstructor(new Class<?>[]{}).newInstance();
		return (T)newObj; // unchecked cast
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Class<? extends T> getComponentType(T[] a) {
		Class<?> k = a.getClass().getComponentType();
		return (Class<? extends T>)k; // unchecked cast
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] newArray(Class<? extends T> k, int size) {
		if (k.isPrimitive()) {
			throw new IllegalArgumentException("Argument cannot be primitive");
		}
		
		Object a = Array.newInstance(k, size);
		return (T[])a; // unchecked cast
	}
	
	public static <T> T[] newArray(T[] a, int size) {
		return GenericReflection.newArray(GenericReflection.getComponentType(a), size);
	}
}