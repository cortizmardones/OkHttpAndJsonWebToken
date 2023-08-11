package artifactExample;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import Data.Persona;

public class Lambda {

	public static void main(String[] args) {
		
		int num = 10;
		IntStream.range(1, 10).forEach(i -> System.out.println(i));
		
		// REFERENCIA POR OBJETO
			List<Integer> numbers = new ArrayList<>(10);
			IntStream repeat = IntStream.range(1, 11);
			//repeat.forEach(i -> numbers.add(i));
			repeat.forEach(numbers::add);
			System.out.println(numbers);
		
		// REFERENCIA A METODO ESTATICO
			//Supplier<UUID> token = () -> UUID.randomUUID();
			Supplier<UUID> token = UUID::randomUUID;
			System.out.println(token.get());
		
		// INTERFACES FUNCIONALES
			Callable<Object> callable;
			Comparator comparator;
			Consumer consumer;
			Predicate predicate;
			BinaryOperator bynaryOperator;
		
		System.out.println("");
		System.out.println("-------------");
		
		//LAMBDA DURO
		
		
		Stream<Persona> streamPersonas = Stream.of(new Persona("Carlos", "Ortiz" , 35) , new Persona("Walter", "Tejeda" , 36), new Persona("Katherine", "Placencia" , 30), new Persona("Carlos", "Ortiz" , 35));
		
		//Stream para pruebas.
		Stream<Integer> stream = Stream.of(3,2,1,4,5,6,7,10,10);
		
		//CONTAR ELEMENTOS DEL STREAM
			//System.out.println(stream.count());
		
		//ITERACIÓN DE STREAM
			stream.forEach(i -> System.out.println(i));
		
		//MÉTODOS MATCH
			//Devuelve TRUE si alguno de los elementos Stream coincide con el predicado.
			//System.out.println(stream.anyMatch(i -> i > 500));
			
			//Devuelve TRUE si todos los elementos del stream coinciden con el predicado.
			//System.out.println(stream.allMatch(i -> i > 500));
			
			//Devuelve TRUE si ningún elemento del stream coincide con el predicado proporcionado.
			//System.out.println(stream.noneMatch(i -> i > 500));
		
		//MÉTODOS BUSQUEDA
			//RETORNA EL PRIMER ELEMENTO DEL ARRAY
			//Optional<Integer> optional = stream.findFirst();
			//System.out.println(optional.isPresent() ? optional.get(): "optional vacio");
			
			//RETORNA CUALQUIER ELEMENTO DEL ARRAY
			//Optional<Integer> optional = stream.findAny();
			//System.out.println(optional.isPresent() ? optional.get(): "optional vacio");
		
		//MÉTODOS ACUMULADORES
			//SUMA EL VALOR DE TODAS LAS ITERACIONES Y LUEGO CONSULTA SI EL VALOR ESTA PRESENTE Y LO IMPRIME
//			stream.reduce((acumulador, iterator) -> {
//				return acumulador + iterator;
//			}).ifPresent(i -> System.out.println(i));
		
		//MÉTODOS MÁXIMOS Y MÍNIMOS
			//System.out.println(stream.max((a,b) -> a - b));
			//System.out.println(stream.min((a,b) -> a - b));
			
		//MÉTODOS DISCTINT Y LIMIT/SKIP
			//System.out.println(stream.distinct().count());
			System.out.println(streamPersonas.distinct().filter(i -> i.getApellido().equalsIgnoreCase("Ortiz")).count()); 
			
			//Me trae los primeros indices que indiquemos
			//System.out.println(stream.limit(5).collect(Collectors.toList()));
			
			//Ignora los primeros indices que indiquemos
			System.out.println(stream.skip(5).collect(Collectors.toList()));
	}

}
