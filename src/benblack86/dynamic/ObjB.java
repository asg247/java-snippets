package benblack86.dynamic;

public class ObjB extends RequestObject {
	public String name;
	public double height;
	public double width;

	@Override
	public void execute() {
		System.out.printf("%s|area:%s\n", this.toString(), height*width);
	}

}
