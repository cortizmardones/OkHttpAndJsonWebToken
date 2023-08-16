package artifactExample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestTecnicoSenior {

	public static void main(String[] args) {

		System.out.println("Resultado: " + orderNumRoman("CD"));

//		List<Integer> numberList = new ArrayList<Integer>();
//		numberList.add(10);
//		numberList.add(40);
//		numberList.add(2000);
//		numberList.add(60);
//		numberList.add(100);
//		numberList.add(10);
//		List<Integer> numberList2 = Arrays.asList(1, 2, 3, 4, 5);
//
//		//MAXIMO
//		Optional<Integer> max = numberList.stream().max((a, b) -> a - b);
//		if (max.isPresent()) {
//			System.out.println("Max: " + max.get());
//		}
//
//		//DISTINTO
//		List<Integer> distinctList = numberList.stream().distinct().map(i -> i).collect(Collectors.toList());
//
//		distinctList.forEach((i) -> {
//			System.out.println(i);
//		});
//		
//		//ORDENAR
//		distinctList.stream().sorted().forEach(System.out::println);
//		distinctList.stream().sorted(Collections.reverseOrder()).forEach(System.out::println);
	}

	public static int orderNumRoman(String value) {

		int result = 0;
		String[] splitValue = value.toUpperCase().trim().split("");
		
		List<String> orderList = Arrays.asList("M", "D", "C", "L", "X", "V", "I");

		HashMap<String, Integer> mapData = new HashMap<>();
		mapData.put("I", 1);
		mapData.put("V", 5);
		mapData.put("X", 10);
		mapData.put("L", 50);
		mapData.put("C", 100);
		mapData.put("D", 500);
		mapData.put("M", 1000);

		
		//XI
		for (int i = 0; i < splitValue.length ; i++) {
			if (mapData.containsKey(splitValue[i])) {
				int position1 = orderList.indexOf(splitValue[i]);
				if(i != splitValue.length -1) {
					int position2 = orderList.indexOf(splitValue[i + 1]);
					if (position1 > position2) {
					// if(orderList.indexOf(splitValue[i]) > orderList.indexOf(splitValue[i + 1]) )
						result = result - mapData.get(splitValue[i]);
					} else {
						result = result + mapData.get(splitValue[i]);
					}
				} else {
					result = result + mapData.get(splitValue[i]);
				}
			}
		}
		return result;
	}

}
