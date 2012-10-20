package benblack86.properties;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public enum PropertiesManager {
	INSTANCE;

	public static void main(String[] args) {
		System.out.printf("Prop1:%s\n", PropertiesManager.INSTANCE.isProp1());
		System.out.printf("Prop2:%s\n", PropertiesManager.INSTANCE.getProp2());
		System.out.printf("Prop3:%s\n", PropertiesManager.INSTANCE.getProp3());
	}
	
	
	private boolean prop1;
	private String prop2;
	private int prop3;

	private PropertiesManager() {
		try {
			Map<String, Method> methods = new HashMap<String, Method>();

			for(PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(this.getClass()).getPropertyDescriptors()){
			   if(propertyDescriptor.getWriteMethod() != null) {
				   String name = propertyDescriptor.getWriteMethod().getName();

				   // cut off "set" from method name
				   if(name.startsWith("set")) {
				   	   methods.put(name.substring(3), propertyDescriptor.getWriteMethod());
				   }
			   }
			}
			
			// normally this data would be loaded from somewhere
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("Prop1", "true");
			properties.put("Prop2", "hello");
			properties.put("Prop3", "34");

			for(String property : properties.keySet()) {
				if(methods.containsKey(property)) {
					Method method = methods.get(property);

					Class<?> parameter = method.getParameterTypes()[0];

					if(parameter == String.class) {
						method.invoke(this, properties.get(property));
					} else if(parameter == boolean.class || parameter == Boolean.class) {
						method.invoke(this, Boolean.valueOf(properties.get(property)));
					} else if(parameter == int.class || parameter == Integer.class) {
						method.invoke(this, Integer.valueOf(properties.get(property)));
					} else if(parameter == double.class || parameter == Double.class) {
						method.invoke(this, Double.valueOf(properties.get(property)));
					} else if(parameter == long.class || parameter == Long.class) {
						method.invoke(this, Long.valueOf(properties.get(property)));
					} else if(parameter == short.class || parameter == Short.class) {
						method.invoke(this, Short.valueOf(properties.get(property)));
					} else if(parameter == float.class || parameter == Float.class) {
						method.invoke(this, Float.valueOf(properties.get(property)));
					} else if(parameter == byte.class || parameter == Byte.class) {
						method.invoke(this, Byte.valueOf(properties.get(property)));
					}
				} else {
					throw new Exception("property "+property+" not definned in PropertiesManager");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isProp1() {
		return prop1;
	}

	public void setProp1(boolean prop1) {
		this.prop1 = prop1;
	}

	public String getProp2() {
		return prop2;
	}

	public void setProp2(String prop2) {
		this.prop2 = prop2;
	}

	public int getProp3() {
		return prop3;
	}

	public void setProp3(int prop3) {
		this.prop3 = prop3;
	}
}
