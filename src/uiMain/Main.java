// Alejandro Arias Orozco

package uiMain;

import baseDatos.BaseDatosException;
import baseDatos.Deserializador;
import baseDatos.Serializador;
import gestorAplicacion.parqueadero.Parqueadero;

import java.util.List;

public class Main {
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
			escribirDatos();
		} catch (BaseDatosException e) {
			imprimirError(e);
		}
	}

	private static void leerDatos() throws BaseDatosException {
		Deserializador deserializador;
		deserializador = new Deserializador();

		if (deserializador.existenDatos()) {
			// leer los datos guardados de la clase parqueadero
			parqueadero = (Parqueadero) deserializador.leerObjeto();
		} else {
			// si no hay datos guardados, crear nuevas instancias de las clases
			parqueadero = new Parqueadero();
		}

		deserializador.close();
	}

	private static void escribirDatos() throws BaseDatosException {
		Serializador serializador = new Serializador();
		// persistir datos de las clases
		serializador.escribirObjetos(List.of(parqueadero));
	}

	private static void imprimirError(Exception error) {
		System.out.println("Error: " + error.getMessage());
		Throwable causa = error.getCause();
		if (causa != null) {
			System.out.println("\tCausado por: " + causa.getMessage());
		}
	}
}
