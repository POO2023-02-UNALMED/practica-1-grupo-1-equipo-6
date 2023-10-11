// Alejandro Arias Orozco

package uiMain;

import java.util.List;
import java.util.Scanner;

/**
 * Esta es una clase con diferentes métodos para interactuar con el usuario
 * a través de la interfaz de consola, ya sea para imprimir mensajes o errores,
 * para pedirle al usuario que ingrese valores o para que elija entre una lista
 * de opciones, etc.
 */
public class Consola {
	/**
	 * Imprime un error en consola, mostrando su mensaje y si existe, el mensaje de su causa.
	 */
	public static void imprimirError(Exception error) {
		System.out.println("Error: " + error.getMessage());
		Throwable causa = error.getCause();
		if (causa != null) {
			System.out.println("\tCausado por: " + causa.getMessage());
		}
	}

	/**
	 * Imprime el mensaje y espera que el usuario ingrese un entero, retornándolo.
	 */
	public static int pedirEntero(String mensaje) {
		imprimirMensaje(mensaje);
		return scanner().nextInt();
	}

	/**
	 * Imprime el mensaje y espera que el usuario ingrese un número long, retornándolo.
	 */
	public static long pedirLong(String mensaje) {
		imprimirMensaje(mensaje);
		return scanner().nextLong();
	}

	/**
	 * Imprime el mensaje con un (si/no) y espera que el usuario ingrese "si" o "no".
	 * Retorna un boolean: true si el usuario ingresa "si", false si ingresa "no".
	 *
	 * Sólo verifica la primera letra de lo que ingrese el usuario, sin importar si está
	 * en mayúsculas o minúsculas. Por ejemplo, "s", "S", "Sxyz", "sabc" son todos considerados
	 * como un "si"; "n", "N", "N...", "nao" son todos considerados como un "no".
	 */
	public static boolean pedirBoolean(String mensaje) {
		imprimirMensaje(mensaje, "(si/no)");
		// pedir al usuario que ingrese su eleccion y tomar solo la primera letra en minusculas
		char eleccion = scanner().nextLine().toLowerCase().charAt(0);
		if (eleccion == 's') {
			return true;
		} else if (eleccion == 'n') {
			return false;
		} else { // si el usuario escribió otra cosa, entonces volver a preguntarle.
			System.out.println("Opción inválida");
			return pedirBoolean(mensaje);
		}
	}

	/**
	 * Imprime el mensaje y espera que el usuario ingrese una línea, retornándola
	 * sin incluir separadores de línea.
	 */
	public static String pedirString(String mensaje) {
		imprimirMensaje(mensaje);
		return scanner().nextLine();
	}

	/**
	 * Recibe el mensaje y una lista de opciones, devuelve el índice de la opción escogida.
	 * Este metodo imprime un mensaje junto con una lista de opciones en pantalla y espera
	 * que el usuario escoja una, luego verifica que ésta sea una opción válida y la retorna.
	 * Si no es válida entonces repite la pregunta.
	 */
	public static int pedirEleccion(String mensaje, List<String> opciones) {
		// imprimir el mensaje y las opciones
		System.out.println(mensaje);
		for (int i = 0; i < opciones.size(); i++) {
			System.out.printf("%d. %s%n", i+1, opciones.get(i));
		}

		// pedirle al usuario que escoja una opcion
		imprimirMensaje("Escoja una opción");
		int eleccion = scanner().nextInt();

		// verificar que la opcion escogida es valida. Si no lo es, entonces volver a preguntar.
		if (eleccion <= 0 || eleccion > opciones.size()) {
			System.out.println("Opción inválida");
			return pedirEleccion(mensaje, opciones);
		}

		// devolver el indice de la opcion escogida.
		return eleccion - 1;
	}

	/**
	 * Imprime un mensaje precedido por un ">" y seguido por un ":" para indicarle al usuario
	 * que debe ingresar un valor.
	 */
	private static void imprimirMensaje(String mensaje) {
		System.out.print("> " + mensaje + ": ");
	}

	/**
	 * Imprime un mensaje precedido por un ">" y seguido por el valor del
	 * argumento `extra` y por un ":" para indicarle al usuario que debe
	 * ingresar un valor.
	 */
	private static void imprimirMensaje(String mensaje, String extra) {
		imprimirMensaje(mensaje + " " + extra);
	}

	private static Scanner scanner() {
		return new Scanner(System.in);
	}
}
