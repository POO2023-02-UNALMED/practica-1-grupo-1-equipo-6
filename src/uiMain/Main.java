// Alejandro Arias Orozco

package uiMain;

import baseDatos.BaseDatosException;
import baseDatos.Deserializador;
import baseDatos.Serializador;

public class Main {
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
			// TODO: Leer los objetos cuando tengamos las clases, por ejemplo:
			// parqueadero = (Parqueadero) deserializador.leerObjeto();
		} else {
			// si no hay datos guardados, crear nuevas instancias de las clases
			// parquedero = new Parqueadero();
		}

		deserializador.close();
	}

	private static void escribirDatos() throws BaseDatosException {
		Serializador serializador = new Serializador();
		// TODO: escribir los objetos cuando tengamos las clases
		// serializador.escribirObjetos(List.of(parqueadero));
	}

	private static void imprimirError(Exception error) {
		System.out.println("Error: " + error.getMessage());
		Throwable causa = error.getCause();
		if (causa != null) {
			System.out.println("\tCausado por: " + causa.getMessage());
		}
	}
}
