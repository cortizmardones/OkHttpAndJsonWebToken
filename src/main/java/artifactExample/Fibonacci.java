package artifactExample;

public class Fibonacci {
	
	public static void main(String[] args) {
//		int contador = 1;
//		int acumulador = 1;
//		for (int i = 0; i < 4; i++) {
//			int[] numeros = new int[contador];
//			for (int j = 0; j < numeros.length; j++) {
//				if (j == 0) {
//					numeros[j] = acumulador;
//				} else {
//					acumulador = acumulador + 2;
//					numeros[j] = acumulador;
//				}
//			}
//			for (int iterador : numeros) {
//				System.out.print(iterador + " ");
//			}
//			System.out.println();
//			acumulador = acumulador + 3;
//			contador++;
//		}

		int[] arrayFibonacci = new int[20];
		arrayFibonacci[0] = 1;
		arrayFibonacci[1] = 1;
		System.out.print(arrayFibonacci[0] + " ");
		System.out.print(arrayFibonacci[1] + " ");

		for (int i = 2; i < arrayFibonacci.length; i++) {
			arrayFibonacci[i] = arrayFibonacci[i - 1] + arrayFibonacci[i - 2];
			System.out.print(arrayFibonacci[i] + " ");
		}
	}

}
