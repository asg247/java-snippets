package benblack86.pattern;

public class Visitor {
	/*
	 * Visitor design pattern: a way of separating an algorithm from an object structure
	 * on which it operates.
	 */
	
	public static void main(String args[]) {
		Tree<Integer> tree = Tree.branch(Tree.branch(Tree.leaf(1), Tree.leaf(2)), Tree.leaf(3));
		
		System.out.println(TreeClient.toString(tree));
		System.out.println(TreeClient.sum(tree));
	}
	
}

class TreeClient {
	public static<T> String toString(Tree<T> t) {
		return t.visit(new Tree.Visitor<T, String>() {

			@Override
			public String leaf(T element) {
				return element.toString();
			}

			@Override
			public String branch(String left, String right) {
				return "("+left+"^"+right+")";
			}
			
		});
	}
	
	public static<N extends Number> double sum(Tree<N> t) {
		return t.visit(new Tree.Visitor<N, Double>() {

			@Override
			public Double leaf(N element) {
				return element.doubleValue();
			}

			@Override
			public Double branch(Double left, Double right) {
				return left+right;
			}
		});
	}
}

abstract class Tree<E> {
	public interface Visitor<E, R> {
		public R leaf(E element);
		public R branch(R left, R right);
	}
	
	public abstract <R> R visit(Visitor<E, R> v);
	
	// factory method for creating leaf
	public static <T> Tree<T> leaf(final T element) {
		return new Tree<T>() {
			public <R> R visit(Visitor<T, R> v) {
				return v.leaf(element);
			}
		};
	}
	
	// factory method for creating branch
	public static <T> Tree<T> branch(final Tree<T> left, final Tree<T> right) {
		return new Tree<T>() {
			public <R> R visit(Visitor<T, R> v) {
				return v.branch(left.visit(v), right.visit(v));
			}
		};
	}
}