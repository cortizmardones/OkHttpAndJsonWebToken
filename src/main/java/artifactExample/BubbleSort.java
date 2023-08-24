package artifactExample;

public class BubbleSort {

	public static void main(String[] args) {
		int[] array = new int[6];
		array[0] = 1540;
		array[1] = 2984;
		array[2] = 20;
		array[3] = 3040;
		array[4] = 5148;
		array[5] = 478;
		for (int iterator : bubleSort(array)) {
			System.out.println(iterator);
		}
	}

	// Ejemplo para iteración : [50] , [10] , [3]
	public static int[] bubleSort(int[] arrayInput) {
		for (int i = 0; i < arrayInput.length - 1; i++) {
			for (int j = 0; j < arrayInput.length - 1; j++) {
				if (arrayInput[j] > arrayInput[j + 1]) {
					int temp = arrayInput[j + 1];
					arrayInput[j + 1] = arrayInput[j];
					arrayInput[j] = temp;
				}
			}
		}
		return arrayInput;
	}

}
