package benblack86.dynamic;

import java.lang.reflect.Field;

public abstract class RequestObject {
	public String toString() {
		StringBuffer prop = new StringBuffer(this.getClass().getSimpleName());
		try {
			Class<?> requestClass = this.getClass();
			Field[] fields = requestClass.getFields();

			for(int i = 0; i < fields.length; i++ ) {
				prop.append("|").append(fields[i].getName()).append(":").append(fields[i].get(this));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		return prop.toString();
	}

	public abstract void execute();
}
