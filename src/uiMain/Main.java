// Alejandro Arias Orozco

package uiMain;

import baseDatos.BaseDatos;
import baseDatos.BaseDatosException;
import gestorAplicacion.parqueadero.Parqueadero;

public class Main {
	private static BaseDatos baseDatos;
	private static Parqueadero parqueadero;

	public static void main(String[] args) {
		try {
			leerDatos();
		} catch (BaseDatosException e) {
			imprimirError(e);
			return;
		}

		// TODO: funcionalidades aqui...

		try {
			baseDatos.escribirDatos();
		} catch (BaseDatosException e) {
			imprimirError(e);
		}
	}

	private static void leerDatos() throws BaseDatosException {
		baseDatos = BaseDatos.leerDatos();
		if (baseDatos != null) {
			parqueadero = baseDatos.getParqueadero();
		} else {
			baseDatos = new BaseDatos();
			// si no hay datos guardados, crear nuevas instancias de las clases
			parqueadero = new Parqueadero();
		}
	}

	private static void imprimirError(Exception error) {
		System.out.println("Error: " + error.getMessage());
		Throwable causa = error.getCause();
		if (causa != null) {
			System.out.println("\tCausado por: " + causa.getMessage());
		}
	}
}
