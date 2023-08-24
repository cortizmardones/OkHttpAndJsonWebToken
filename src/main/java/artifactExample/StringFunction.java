package artifactExample;

import java.util.Arrays;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class StringFunction {
	
	private static final Logger LOGGER = LogManager.getLogger(StringFunction.class);

	public static void main(String[] args) {
		String numOC = "125CF48_3246";
		LOGGER.info(returnStringWithoutLetters(numOC));
		LOGGER.info(returnLength(numOC));
		LOGGER.info(returnLength(returnStringWithoutLetters(numOC)));
		
		LOGGER.info(checkWordPalindrom("reconocer"));
		
	}

	//ENTRADA : 		125CF48_3246
	//SALIDA ESPERADA: 	125483246
	public static String returnStringWithoutLetters(String input) {
		String output = "";
		char[] arrayCharInput = input.toCharArray();
		for (char iterator : arrayCharInput) {
			if (Character.isDigit(iterator)) {
				output = output + iterator;
			}
		}
		return output;
	}

	public static String returnLength(String input) {
		return "La entrada '" + input + "' contiene '" + input.length() + "' carácteres";
	}
	
	public static String checkWordPalindrom(String word) {
		
		// Forma fácil
		//String newWord = new StringBuilder(word).reverse().toString();
		
		String[] arrayOriginal = word.toLowerCase().split("");
		String[] arrayComparator = new String[word.length()];
		int j = 0;
		for(int i = arrayOriginal.length -1; i >= 0; i--) {
			arrayComparator[j] = arrayOriginal[i];
			j++;
		}
		if(Arrays.toString(arrayOriginal).equalsIgnoreCase(Arrays.toString(arrayComparator))) {
			return "La palabra: '" + word + "', SI es un palíndromo";
		}
		return "La palabra: '" + word + "', NO es un palíndromo";
	}
	
}
