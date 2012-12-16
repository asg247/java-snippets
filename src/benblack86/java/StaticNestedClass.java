package benblack86.java;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class StaticNestedClass {
	/*
	 * Nested classes: way of logically grouping classes that are only used in one
	 * place, increase encapsulation, lead to more readable and maintainable code
	 * Static: cannot refer directly to instance variables or methods defined in
	 * its enclosing class, behave like top level classes, more efficient
	 */
	
	public static void main(String args[]) { 
		Collection<String> items = new LinkedCollection<String>();
		items.add("hello");
		items.add("world");
		
		for(String item : items) {
			System.out.println(item);
		}
	}
}


class LinkedCollection<E> extends AbstractCollection<E> {
	private static class Node<T> {
		private T element;
		private Node<T> next = null;
		
		private Node(T element) {
			this.element = element;
		}
	}
	
	private Node<E> first = new Node<E>(null);
	private Node<E> last = first;
	private int size = 0;
	
	public LinkedCollection() {}
	
	public LinkedCollection(Collection<? extends E> collection) {
		// extends E means we can get elements out of the collection
		this.addAll(collection);
	}

	@Override
	public int size() {
		return this.size;
	}
	
	@Override
	public boolean add(E element) {
		this.last.next = new Node<E>(element);
		this.last = this.last.next;
		this.size++;
		return true;
	}
	
	private static class LinkedIterator<T> implements Iterator<T> {
		private Node<T> current;
		
		public LinkedIterator(Node<T> first) {
			this.current = first;
		}
		
		@Override
		public boolean hasNext() {
			return current.next != null;
		}

		@Override
		public T next() {
			if (hasNext()) {
				current = current.next;
				return current.element;
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
	@Override
	public Iterator<E> iterator() {
		return new LinkedIterator<E>(first);
	}

}