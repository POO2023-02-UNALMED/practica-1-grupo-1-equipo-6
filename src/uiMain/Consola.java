package uiMain;

import java.util.List;
import java.util.Scanner;

public class Consola {
	public static void imprimirError(Exception error) {
		System.out.println("Error: " + error.getMessage());
		Throwable causa = error.getCause();
		if (causa != null) {
			System.out.println("\tCausado por: " + causa.getMessage());
		}
	}

	public static int pedirEntero(String mensaje) {
		System.out.print(mensaje + ": ");
		return scanner().nextInt();
	}

	public static long pedirLong(String mensaje) {
		System.out.print(mensaje + ": ");
		return scanner().nextLong();
	}

	public static boolean pedirBoolean(String mensaje) {
		System.out.print(mensaje + " (si/no): ");
		char eleccion = scanner().nextLine().toLowerCase().charAt(0);
		if (eleccion == 's') {
			return true;
		} else if (eleccion == 'n') {
			return false;
		} else {
			System.out.println("Opción inválida");
			return pedirBoolean(mensaje);
		}
	}

	public static String pedirString(String mensaje) {
		System.out.print(mensaje + ": ");
		return scanner().nextLine();
	}

	/**
	 * Recibe el mensaje y una lista de opciones, devuelve el índice de la opción escogida.
	 * Este metodo imprime una lista de opciones en pantalla y espera que se escoja una,
	 * verifica que sea una opción válida y la retorna. Si no es válida entonces repite la
	 * pregunta.
	 */
	public static int pedirEleccion(String mensaje, List<String> opciones) {
		System.out.println(mensaje);
		for (int i = 0; i < opciones.size(); i++) {
			System.out.printf("%d. %s%n", i+1, opciones.get(i));
		}
		System.out.print("Escoja una opción: ");
		int eleccion = scanner().nextInt();
		if (eleccion <= 0 || eleccion > opciones.size()) {
			System.out.println("Opción inválida");
			return pedirEleccion(mensaje, opciones);
		}
		return eleccion - 1;
	}

	private static Scanner scanner() {
		return new Scanner(System.in);
	}
}
