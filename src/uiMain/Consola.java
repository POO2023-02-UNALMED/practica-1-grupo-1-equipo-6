/*
 Funcionalidad del módulo: contiene la clase de utilidad Consola que sirve para interactuar con el usuario a través de la consola
 Componentes del módulo: Consola
 Autores: Alejandro, Sara
*/

package uiMain;

import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Esta es una clase con diferentes métodos para interactuar con el usuario
 * a través de la interfaz de consola, ya sea para imprimir mensajes o errores,
 * para pedirle al usuario que ingrese valores o para que elija entre una lista
 * de opciones, etc.
 */
public class Consola {
	private static Scanner scanner = new Scanner(System.in);

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
	 * Si el usuario ingresa otra cosa, volver a pedirle que ingrese un valor entero.
	 */
	public static int pedirEntero(String mensaje) {
		imprimirMensaje(mensaje);
		try {
			return Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Por favor ingrese un número");
			return pedirEntero(mensaje);
		}
	}

	/**
	 * Imprime el mensaje y espera que el usuario ingrese un long, retornándolo.
	 * Si el usuario ingresa otra cosa, volver a pedirle que ingrese un valor long.
	 */
	public static long pedirLong(String mensaje) {
		imprimirMensaje(mensaje);
		try {
			return Long.parseLong(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Por favor ingrese un número");
			return pedirEntero(mensaje);
		}
	}
	
	/**
	 * Imprime el mensaje y espera que el usuario ingrese un double, retornándolo.
	 * Si el usuario ingresa otra cosa, volver a pedirle que ingrese un valor double.
	 */
	public static double pedirDouble(String mensaje) {
		imprimirMensaje(mensaje);
		try {
			return Double.parseDouble(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Por favor ingrese un número valido");
			return pedirEntero(mensaje);
		}
	}

	/**
	 * Imprime el mensaje con un (si/no) y espera que el usuario ingrese "si" o "no".
	 * Retorna un boolean: true si el usuario ingresa "si", false si ingresa "no".
	 *
	 * Acepta como respuestas: "si", "s", "no", "n", o en mayusculas.
	 */
	public static boolean pedirBoolean(String mensaje) {
		imprimirMensaje(mensaje, "(si/no)");
		// pedir al usuario que ingrese su elección y considerarla en minúsculas
		String eleccion = scanner.nextLine().strip().toLowerCase();
		if (eleccion.equals("s") || eleccion.equals("si")) {
			return true;
		} else if (eleccion.equals("n") || eleccion.equals("no")) {
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
		String s = scanner.nextLine().strip();
		if (s.isEmpty()) {
			System.out.println("Por favor ingrese un valor!");
			return pedirString(mensaje);
		}
		return s;
	}

	/**
	 * Pide al usuario un porcentaje y lo devuelve como un double entre 0 y 1.
	 * Siendo 1 igual a 100%
	 */
	public static double pedirPorcentaje(String mensaje) {
		String s = pedirString(mensaje + " (ejemplo: 10%)");
		if (!s.endsWith("%")) {
			System.out.println("Por favor ingrese un porcentaje");
			return pedirPorcentaje(mensaje);
		}
		// s sin el '%'
		String s2 = s.substring(0, s.length() - 1);
		try {
			return Double.parseDouble(s2) / 100;
		} catch (NumberFormatException e) {
			System.out.println("Por favor ingrese un porcentaje válido");
			return pedirEntero(mensaje);
		}
	}

	/**
	 * Recibe el mensaje y una lista de opciones, devuelve el índice de la opción escogida.
	 * Este método imprime un mensaje junto con una lista de opciones en pantalla y espera
	 * que el usuario escoja una, luego verifica que esta sea una opción válida y la retorna.
	 * Si no es válida entonces repite la pregunta.
	 */
	public static int pedirEleccion(String mensaje, List<String> opciones) {
		return pedirEleccion(mensaje, opciones, e -> true);
	}

	/**
	 * Recibe el mensaje y una lista de opciones, devuelve el índice de la opción escogida.
	 * Este método imprime un mensaje junto con una lista de opciones en pantalla y espera
	 * que el usuario escoja una, luego verifica que esta sea una opción válida y la retorna.
	 * Si no es válida entonces repite la pregunta.
	 *
	 * El parámetro verificarCondiciones es una función que devuelve true o false y sirve para
	 * verificar condiciones extra que se deben cumplir para que la elección del usuario sea
	 * válida. Cuando verificarCondiciones devuelve false, se le vuelve a preguntar al usuario
	 * que elija una opción hasta que verificarCondiciones devuelva true.
	 */
	public static int pedirEleccion(String mensaje, List<String> opciones, Predicate<Integer> verificarCondiciones) {
		// imprimir el mensaje y las opciones
		System.out.println(mensaje);
		for (int i = 0; i < opciones.size(); i++) {
			System.out.printf("%d. %s%n", i+1, opciones.get(i));
		}

		// pedirle al usuario que escoja una opción
		int eleccion = pedirEleccion(opciones, verificarCondiciones);

		// devolver el índice de la opción escogida.
		return eleccion - 1;
	}

	/**
	 * Asigna el scanner que se utilizará para cuando se requiere input del usuario.
	 */
	public static void setScanner(Scanner scanner) {
		Consola.scanner = scanner;
	}

	/**
	 * Pide al usuario que elija entre una lista de opciones y verifica que la opción escogida es válida.
	 */
	private static int pedirEleccion(List<String> opciones, Predicate<Integer> verificarCondiciones) {
		int eleccion = pedirEntero("Escoja una opción");

		// verificar que la opción escogida es válida. Si no lo es, entonces volver a preguntar.
		if (eleccion <= 0 || eleccion > opciones.size() || !verificarCondiciones.test(eleccion - 1)) {
			System.out.println("Opción inválida");
			return pedirEleccion(opciones, verificarCondiciones);
		}

		return eleccion;
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
}
