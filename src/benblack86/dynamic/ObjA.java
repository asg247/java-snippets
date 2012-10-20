package benblack86.dynamic;

public class ObjA extends RequestObject {
	public String name;
	public String location;
	
	@Override
	public void execute() {
		System.out.printf("%s\n", this.toString());
	}

}
