package artifactExample;

import Data.Persona;
import java.lang.reflect.*;

public class Reflection {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		// NOMBRE DE LA CLASE.
		Object persona = new Persona();
		System.out.println("NameClass: " + persona.getClass().getCanonicalName() + "\n");
		
		// ATRIBUTOS DE LA CLASE.
		Field[] fields = persona.getClass().getDeclaredFields();
		System.out.println("Atributos de la clase: ");
		for(Field iterator : fields) {
			System.out.println(iterator.getName());
		}
		System.out.println("");
		
		// METODOS DE LA CLASE.
		Method[] methods = persona.getClass().getDeclaredMethods();
		System.out.println("Métodos de la clase: ");
		for(Method iterator : methods) {
			System.out.println(iterator.getName());
		}
		System.out.println("");
		
		// CONSTRUCTORES DE LA CLASE.
		Constructor<?>[] constructors = persona.getClass().getConstructors();
		System.out.println("Constructores de la clase: ");
		for(Constructor iterator : constructors) {
			System.out.println(iterator);
			System.out.println(iterator.getParameterCount());
		}
		System.out.println("");
		
		//EJECUTAR METODO EN TIEMPO DE EJECUCION.
		Class<Persona> clase = Persona.class;
		Object instance = clase.getDeclaredConstructor().newInstance();
		Method method = clase.getMethod("metodoPruebaReflection", String.class);
		//Ejecuto el metodo.
		Object result = method.invoke(instance, "Carlos");
		
	}

}
