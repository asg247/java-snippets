package benblack86.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MergeCollections {
	public static void main(String[] args) {
		Collection<Double> list1 = Arrays.asList(1.0, 5.0, 10.0);
		Collection<Double> list2 = Arrays.asList(7.0, 8.0, 100.0, 101.0);
		
		System.out.println(MergeCollections.merge(list1, list2));
		
	}
	
	
	public static <T extends Comparable<? super T>> List<T> merge(Collection<? extends T> c1, Collection<? extends T> c2) {
		List<T> mergedList = new ArrayList<T>();
		Iterator<? extends T> itr1 = c1.iterator();
		Iterator<? extends T> itr2 = c2.iterator();
		T element1 = getNextElement(itr1);
		T element2 = getNextElement(itr2);
		
		while (element1 != null || element2 != null) {
			boolean useElement1 = (element2 == null || element1 != null && element1.compareTo(element2) < 0);
			
			if(useElement1) {
				mergedList.add(element1);
				element1 = getNextElement(itr1);
			} else {
				mergedList.add(element2);
				element2 = getNextElement(itr2);
			}
		}
		
		return mergedList;
	}
	
	private static <E> E getNextElement(Iterator<E> itr) {
		if (itr.hasNext()) {
			E element = itr.next();
			
			if (element == null) {
				throw new NullPointerException();
			}
			
			return element;
		}
		
		return null;
	}
}
