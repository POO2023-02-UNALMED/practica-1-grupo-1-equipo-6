// Alejandro Arias Orozco

package uiMain;

import baseDatos.BaseDatos;
import baseDatos.BaseDatosException;
import gestorAplicacion.parqueadero.Parqueadero;

import java.util.List;

public class Main {
	private static BaseDatos baseDatos;

	public static void main(String[] args) {
		try {
			leerDatos();
		} catch (BaseDatosException e) {
			Consola.imprimirError(e);
			return;
		}

		ejecutarFuncionalidades();

		try {
			baseDatos.escribirDatos();
		} catch (BaseDatosException e) {
			Consola.imprimirError(e);
		}
	}

	private static void ejecutarFuncionalidades() {
		int eleccion;
		do {
			eleccion = Consola.pedirEleccion("Menú principal", List.of(
				"Ingresar un vehículo al parqueadero",
				"Comprar un carro",
				"Taller",
				"Vender un carro",
				"Manejo del parqueadero",
				"Salir"
			));
			Funcionalidad funcionalidad = null;
			switch (eleccion) {
				case 0:
					funcionalidad = new IngresarVehiculo();
					break;
				case 1:
					funcionalidad = new ComprarCarro();
					break;
				case 2:
					funcionalidad = new Taller();
					break;
				case 3:
					funcionalidad = new VenderCarro();
					break;
				case 4:
					funcionalidad = new ManejoParqueadero();
					break;
			}
			if (funcionalidad != null) {
				funcionalidad.setBaseDatos(baseDatos);
				funcionalidad.ejecutar();
			}
		} while (eleccion != 5);
	}

	private static void leerDatos() throws BaseDatosException {
		baseDatos = BaseDatos.leerDatos();
		if (baseDatos == null) {
			baseDatos = new BaseDatos();
			// si no hay datos guardados, crear nuevas instancias de las clases
			System.out.println("Configuración inicial del parqueadero");
			int plazasTotales = Consola.pedirEntero("Ingrese el número de plazas totales");
			double tarifaCarro = Consola.pedirEntero("Ingrese la tarifa para carros");
			double tarifaMoto = Consola.pedirEntero("Ingrese la tarifa para motos");
			Parqueadero parqueadero = new Parqueadero(plazasTotales, tarifaCarro, tarifaMoto);
			baseDatos.setParqueadero(parqueadero);
		}
	}
}
