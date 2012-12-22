package benblack86.pattern;

public class Interpreter {
	/*
	 * Interpreter design pattern: specifies how to evaluate sentences in a language
	 */
	
	public static void main(String[] args) {
		Exp<Integer> e = Exp.left(Exp.pair(Exp.plus(Exp.num(3), Exp.num(4)), Exp.num(5)));
		
		System.out.println(e.eval());
	}

}

class Pair<A, B> {
	private final A left;
	private final B right;
	
	public Pair(A left, B right) {
		this.left = left;
		this.right = right;
	}
	
	public A left() {
		return left;
	}
	
	public B right() {
		return right;
	}
}

abstract class Exp<T> {
	abstract public T eval();
	
	public static Exp<Integer> num(final int i) {
		return new Exp<Integer>() {
			public Integer eval() {
				return i;
			}
		};
	}
	
	public static Exp<Integer> plus(final Exp<Integer> e1, final Exp<Integer> e2) {
		return new Exp<Integer>() {
			public Integer eval() {
				return e1.eval()+e2.eval();
			}
		};
	}
	
	public static <A, B> Exp<Pair<A, B>> pair(final Exp<A> e1, final Exp<B> e2) {
		return new Exp<Pair<A, B>>() {
			public Pair<A, B> eval() {
				return new Pair<A, B>(e1.eval(), e2.eval());
			}
		};
	}
	
	public static <A, B> Exp<A> left(final Exp<Pair<A, B>> e) {
		return new Exp<A>() {
			public A eval() {
				return e.eval().left();
			}
		};
	}
	
	public static <A, B> Exp<B> right(final Exp<Pair<A, B>> e) {
		return new Exp<B>() {
			public B eval() {
				return e.eval().right();
			}
		};
	}
}