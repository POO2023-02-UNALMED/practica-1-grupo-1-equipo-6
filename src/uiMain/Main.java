// Alejandro Arias Orozco

package uiMain;

import baseDatos.BaseDatos;
import baseDatos.BaseDatosException;
import gestorAplicacion.parqueadero.Parqueadero;

import java.util.List;

public class Main {
	private static BaseDatos baseDatos;

	public static void main(String[] args) {
		// leer los datos del archivo src/baseDatos/temp/datos.txt y cargarlos en memoria
		try {
			leerDatos();
		} catch (BaseDatosException e) {
			Consola.imprimirError(e);
			return;
		}

		ejecutarFuncionalidades();

		// escribir los datos actualizados al archivo luego de haber ejecutado las funcionalidades
		// y de que el usuario haya decidido salir del programa.
		escribirDatos();
	}

	private static void ejecutarFuncionalidades() {
		int eleccion;
		do {
			// pedirle al usuario que elija una funcionalidad, o salir del programa.
			eleccion = Consola.pedirEleccion("Menú principal", List.of(
				"Ingresar un vehículo al parqueadero",
				"Comprar un carro",
				"Taller",
				"Vender un carro",
				"Manejo del parqueadero",
				"Otras funcionalidades",
				"Salir"
			));
			// instanciar las clases de las funcionalidades según la eleccion del usuario.
			Funcionalidad funcionalidad = switch (eleccion) {
				case 0 -> new IngresarVehiculo();
				case 1 -> new ComprarCarro();
				case 2 -> new Taller();
				case 3 -> new VenderCarro();
				case 4 -> new ManejoParqueadero();
				default -> null;
			};
			// si el usuario eligió otras funcionalidades, mostrarlas y pedirle que elija una
			if (eleccion == 5) {
				int otraEleccion = Consola.pedirEleccion("Funcionalidades extra", List.of(
						"Registrar cliente",
						"Registrar vehículo",
						"Volver al menú principal"
				));
				switch (otraEleccion) {
					case 0 -> funcionalidad = new RegistrarCliente();
					case 1 -> funcionalidad = new RegistrarVehiculo();
				}
			}
			// si el usuario eligió una funcionalidad (es decir, no salir), entonces ejecutarla.
			if (funcionalidad != null) {
				funcionalidad.setBaseDatos(baseDatos);
				funcionalidad.ejecutar();
			}

			// persistir los datos después de cada funcionalidad.
			escribirDatos();

		// La elección en el índice 6 es salir. Si el usuario elige esta opción,
		// entonces salir del ciclo while. Si elige una funcionalidad válida,
		// entonces se volverá a mostrar el menú al finalizar su ejecución.
		} while (eleccion != 6);

		System.out.println("Gracias por su visita, vuelva pronto.");
	}

	private static void leerDatos() throws BaseDatosException {
		// leer los datos guardados en el archivo y cargarlos en memoria
		baseDatos = BaseDatos.leerDatos();
		if (baseDatos == null) {
			// si el archivo no existe o no fue posible leerlo, entonces crear una nueva instancia de BaseDatos
			baseDatos = new BaseDatos();
			// Se debe crear una nueva instancia de Parqueadero, por lo tanto se pregunta al usuario
			// las características del mismo
			System.out.println("Configuración inicial del parqueadero");
			int plazasTotales = Consola.pedirEntero("Ingrese el número de plazas totales");
			double tarifaCarro = Consola.pedirEntero("Ingrese la tarifa para carros");
			double tarifaMoto = Consola.pedirEntero("Ingrese la tarifa para motos");
			// Se crea la instancia con base en los valores escogidos por el usuario.
			Parqueadero parqueadero = new Parqueadero(plazasTotales, tarifaCarro, tarifaMoto);
			// Se guarda el parqueadero en la base de datos
			baseDatos.setParqueadero(parqueadero);
		}
	}

	private static void escribirDatos() {
		try {
			baseDatos.escribirDatos();
		} catch (BaseDatosException e) {
			Consola.imprimirError(e);
		}
	}
}
