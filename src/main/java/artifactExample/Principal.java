package artifactExample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

public class Principal {
	
	private static Logger logger = Logger.getLogger(Principal.class);
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		//KATAS

		logger.info(multiplyWithoutSymbol(25, 3));
		int[] array = {3, 1, 4, 25, 73, 84, 914, 45712};
		logger.info(getBiggestNumber(array));
		ArrayList<Object> arrayList = new ArrayList<Object>();
		arrayList.add(null);
		arrayList.add("0");
		arrayList.add(4758);
		arrayList.add(35);
		arrayList.add(null);
		logger.info(cleanArray(arrayList));
		
		repeatWord("La luna de la tierra es pequeña en comparación con la luna de Jupiter y la luna de urano tierra tierra tierra tierra tierra");
				
		List<String> gameList = Arrays.asList("Zelda", "Mario", "Donkey", "StarWars", "Final Fantasy", "One Piece");
		List<String> gameList2 = gameList.stream()
											.filter(e -> e.endsWith("ce"))
											.map(e -> e)
											.collect(Collectors.toList());
		
		for(String iterator : gameList2) {
			System.out.println(iterator);
		}
		
		List<String> lista = new ArrayList<String>();
		lista.add("Hola");
		lista.add("Carlos");
		lista.add("Esteban");
		lista.add("Mundo");
		lista.add("Mundo");
		lista.add("Esteban");
		lista.add("Hola");
		
		lista.stream().reduce(String::concat).ifPresent(System.out::println);
		
		List<String> listaSinRepetir = lista.stream()
											.distinct()
											.collect(Collectors.toList());
		for(String iterator: listaSinRepetir) {
			System.out.println(iterator);
		}
		
		List<String> listaP = lista.stream()
									.filter(p-> p.equalsIgnoreCase("Carlos"))
									.collect(Collectors.toList());
		
		listaP.stream().forEach((i)->{
			System.out.println("Foreach Lista: " + i);
		});
		
		logger.info(lista);
		List<String> lista2 = new LinkedList<String>();
		lista2.add("Hola");
		lista2.add("Mundo");
		logger.info(lista2);
		
		//PORQUE USAR WRAPPER EN VEZ DE PRIMITIVOS
		Integer x1 = null;
		//int x2 = null;

		String header = Base64.getUrlEncoder().encodeToString("{\"alg\":\"HS256\",\"typ\":\"JWT\"}".getBytes());
		String payload = Base64.getUrlEncoder().encodeToString("{\"sub\":\"1234567890\",\"name\":\"John Doe\",\"iat\":1516239022}".getBytes());
		logger.info(header);
		logger.info(payload);
		
	}
	

					
	public static void repeatWord(String word) {
		
		//Variables a usar
		HashMap<Integer, String> wordMap = new HashMap<>();
		int countWordRepeat = 0;
		String wordRepeat = "";
		
		//Paso la palabra a minuscula y genero el array con las palabras en base al espacio en blanco.
		String[] arrayWords = word.toLowerCase().split(" ");
		
		for(int i = 0; i < arrayWords.length; i++) {
			//Doble for para comparar las palabras 
			for(int j = 0 ; j < arrayWords.length; j++) {
				if(arrayWords[i].equalsIgnoreCase(arrayWords[j])) {
					wordRepeat = arrayWords[i];
					countWordRepeat++;
				}
			}
			wordMap.put(countWordRepeat, wordRepeat);
			countWordRepeat = 0;
			wordRepeat = "";
		}
		
		//Guardo las keys del mapa en un arrayList para posteriormente sortearlas.
		ArrayList<Integer> keys = new ArrayList<Integer>(wordMap.keySet());
		
		//SORT de keys
		Collections.sort(keys);
		
		//El mayor valor (LA CANTIDAD DE VECES QUE SE REPITE LA PALABRA) debe estar en la ultima posición.  
		int wordMostRepeat = keys.get(keys.size()-1);
		
		System.out.println("La palabra más repetida es: '" + wordMap.get(wordMostRepeat) + "' con: " + wordMostRepeat + " repeticiones");
		
	}
	
	public static String cleanArray(ArrayList<Object> array) {
		ArrayList<Object> elementForDelete = new ArrayList<Object>();
		for(Object iterator : array) {
			if(iterator == null) {
				elementForDelete.add(iterator);
				continue;
			}
			if(iterator.equals("0")) {
				elementForDelete.add(iterator);
				continue;
			}
		}
		array.removeAll(elementForDelete);
		return array.toString();
	}
		
	public static int getBiggestNumber(int[] array) {
		int mayor = 0;
		for(int i = 0; i < array.length -1 ;i++) {
			if(array[i] > array[i+1]) {
				mayor = array[i];
			} else {
				mayor = array[i+1];
			}
		}
		return mayor;
	}
	
	public static int multiplyWithoutSymbol(int base , int multiply) {
		int result = 0;
		int i = 0;
		while(i < multiply) {
			result = result + base;
			i++;
		}
		return result;
	}

}