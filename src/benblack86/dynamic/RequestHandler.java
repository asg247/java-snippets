package benblack86.dynamic;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler {
	public static void main(String[] args) {
		RequestHandler requestHandler = new RequestHandler();
		
		requestHandler.processRequest("ObjA|name:Ben|location:NYC").execute();
		requestHandler.processRequest("ObjB|name:Chen|width:40|height:182").execute();
		requestHandler.processRequest("ObjB|name:Izo|height:100").execute();
	}
	
	public RequestObject processRequest(String input) {
		try {
			String[] components = input.split("\\|");
			
			// create the object requested and get its fields
			Class<?> requestClass = Class.forName("benblack86.dynamic." + components[0]);
			RequestObject object = (RequestObject) requestClass.newInstance();
			Field[] fields = requestClass.getFields();
			
			// create a map for quick access (should cache in real system)
			Map<String, Field> nameToField = new HashMap<String, Field>();
			for(int i = 0; i< fields.length; i++) {
				nameToField.put(fields[i].getName(), fields[i]);
			}
			
			// loop over components and set value on to object
			for(int i = 1; i < components.length; i++) {
				int split = components[i].indexOf(":");
				String variable = components[i].substring(0, split);
				String value = components[i].substring(split+1);
				
				Field field = nameToField.get(variable);
				
				if(field != null) {
					field.set(object, stringToObject(field.getType(), value));
				}
				
			}
			
			
			return object;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private Object stringToObject(Class<?> parameter, String value) {
		if(parameter == String.class) {
			return value;
		} else if(parameter == boolean.class || parameter == Boolean.class) {
			return Boolean.valueOf(value);
		} else if(parameter == int.class || parameter == Integer.class) {
			return Integer.valueOf(value);
		} else if(parameter == double.class || parameter == Double.class) {
			return Double.valueOf(value);
		} else if(parameter == long.class || parameter == Long.class) {
			return Long.valueOf(value);
		} else if(parameter == short.class || parameter == Short.class) {
			return Short.valueOf(value);
		} else if(parameter == float.class || parameter == Float.class) {
			return Float.valueOf(value);
		} else if(parameter == byte.class || parameter == Byte.class) {
			return Byte.valueOf(value);
		}

		return null;
	}
}
