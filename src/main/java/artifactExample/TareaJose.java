package artifactExample;

import java.util.Scanner;

public class TareaJose {

	public static void main(String[] args) {

		// Condicionales
		boolean scapeCondition = true;

		// Datos usuario
		int clave = 8484;
		int claveReingresada = 0;
		int saldo = 150000;

		// Opciones
		Scanner scanner = new Scanner(System.in);
		int opcionMenu;
		int contadorBloqueo = 0;

		while (scapeCondition) {
			System.out.println("Por favor ingrese su clave secreta de 4 digitos");
			int claveIngresada = scanner.nextInt();

			if (claveIngresada != clave) {
				contadorBloqueo++;
				if(contadorBloqueo == 3) {
					System.out.println("Tarjeta bloqueada");
					scapeCondition = false;
					break;
				}
				System.out.println("Contraseña incorrecta , favor volver a intentar");
				continue;
			}

			while (scapeCondition) {
				System.out.println("Eliga una opción:");
				System.out.println("1 - Consulta de saldo");
				System.out.println("2 - Giro de dinero");
				System.out.println("3 - Cambio de clave");
				System.out.println("4 - Salir");
				opcionMenu = scanner.nextInt();
				// OPCIÓN 1
				if (opcionMenu == 1) {
					System.out.println("Su saldo es de : $" + saldo);
				}
				// OPCIÓN 2
				if (opcionMenu == 2) {
					System.out.println("Ingrese la cantidad a girar");
					int giro = scanner.nextInt();
					if (giro > saldo) {
						System.out.println("No posee saldo suficiente para realizar dicha acción");
					} else if (giro <= saldo && giro > 0) {
						saldo = saldo - giro;
						System.out.println("Usted ha girado : $" + giro + " , su nuevo saldo es de : $" + saldo);
					} else if (giro <= 0) {
						System.out.println("Debe indicar montos validos para girar");
					}
				}
				// OPCIÓN 3
				if (opcionMenu == 3) {
					System.out.println("Ingrese la nueva clave númerica de 4 dígitos");
					clave = scanner.nextInt();
					System.out.println("Ingrese nuevamente la clave de 4 dígitos");
					claveReingresada = scanner.nextInt();
					if (clave == claveReingresada) {
						System.out.println("Su clave ha sido cambiada con éxito");
					} else {
						System.out.println("Las claves no coinciden, favor reintentar");
					}
				}
				// OPCIÓN 4
				if (opcionMenu == 4) {
					System.out.println("Hasta Luego");
					scapeCondition = false;
				}
				// OPCIÓN INVÁLIDA
				if (opcionMenu != 1 && opcionMenu != 2 && opcionMenu != 3 && opcionMenu != 4) {
					System.out.println("Debe ingresar una opción valida.");
				}
			}
		}

	}
}
