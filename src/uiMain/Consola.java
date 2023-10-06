package uiMain;

import java.util.Scanner;

public class Consola {
	public void imprimirError(Exception error) {
		System.out.println("Error: " + error.getMessage());
		Throwable causa = error.getCause();
		if (causa != null) {
			System.out.println("\tCausado por: " + causa.getMessage());
		}
	}

	public int pedirEntero(String mensaje) {
		System.out.print(mensaje + ": ");
		return scanner().nextInt();
	}

	public long pedirLong(String mensaje) {
		System.out.print(mensaje + ": ");
		return scanner().nextLong();
	}

	public boolean pedirBoolean(String mensaje) {
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

	public String pedirString(String mensaje) {
		System.out.print(mensaje + ": ");
		return scanner().nextLine();
	}

	private Scanner scanner() {
		return new Scanner(System.in);
	}
}
