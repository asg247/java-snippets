package benblack86.java;

import java.lang.reflect.Method;

public class Bridge {
	public static void main(String[] args) {
		for(Method m : Point.class.getMethods()) {
			if(m.getName().equals("clone") || m.getName().equals("compareTo")) {
				System.out.println(m.toGenericString());
			}
		}
	}

}

class Point implements Comparable<Point> {

	@Override
	public int compareTo(Point point) {
		return 0;
	}
	
	@Override
	public Point clone() {
		return new Point();
	}
	
}
