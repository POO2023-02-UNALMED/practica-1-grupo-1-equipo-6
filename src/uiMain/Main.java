// Alejandro Arias Orozco

package uiMain;

import baseDatos.BaseDatos;
import baseDatos.BaseDatosException;
import gestorAplicacion.parqueadero.Parqueadero;
import gestorAplicacion.personas.Cliente;

public class Main {
	private static BaseDatos baseDatos;
	private static Parqueadero parqueadero;
	private static Consola consola = new Consola();

	public static void main(String[] args) {
		try {
			leerDatos();
		} catch (BaseDatosException e) {
			consola.imprimirError(e);
			return;
		}

		int opcion;
		do {
			System.out.println("Menú principal");
			System.out.println("1. Ingresar un vehículo al parqueadero");
			System.out.println("2. Comprar un carro");
			System.out.println("3. Taller");
			System.out.println("4. Vender un carro");
			System.out.println("5. Manejo del parqueadero");
			System.out.println("6. Salir");
			opcion = consola.pedirEntero("Escoja una opción");
			switch (opcion) {
				case 1:
					ingresarVehiculo();
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				default:
					System.out.println("Opción inválida");
			}
		} while (opcion != 6);

		try {
			baseDatos.escribirDatos();
		} catch (BaseDatosException e) {
			consola.imprimirError(e);
		}
	}

	private static void ingresarVehiculo() {
		System.out.println("Ingresar vehículo");
		long cedula = consola.pedirLong("Ingrese cédula");
		Cliente cliente = baseDatos.buscarClienteRegistrado(cedula);
		if (cliente == null) {
			System.out.println("Cliente no registrado");
			boolean continuarRegistro = consola.pedirBoolean("Desea registrarse?");
			if (continuarRegistro) {
				cliente = registrarCliente(cedula);
			} else {
				System.out.println("Ha escogido no registrarse.");
				return;
			}
		} else {
			System.out.println("Bienvenido de nuevo, " + cliente.getNombre());
		}

		String placa = consola.pedirString("Ingrese la placa del vehículo a ingresar");
		// TODO: continuara...
	}

	private static Cliente registrarCliente(long cedula) {
		System.out.println("Registro de cliente");
		String nombre = consola.pedirString("Ingrese nombre");
		int telefono = consola.pedirEntero("Ingrese teléfono");
		String correo = consola.pedirString("Ingrese correo");
		String direccion = consola.pedirString("Ingrese dirección");
		boolean discapacitado = consola.pedirBoolean("Usted se encuentra en condición de discapacitado?");
		Cliente cliente = new Cliente(nombre, cedula, telefono, correo, direccion, discapacitado);
		baseDatos.registrarCliente(cliente);
		System.out.println("Cliente registrado. Bienvenido!");
		return cliente;
	}

	private static void leerDatos() throws BaseDatosException {
		baseDatos = BaseDatos.leerDatos();
		if (baseDatos != null) {
			parqueadero = baseDatos.getParqueadero();
		} else {
			baseDatos = new BaseDatos();
			// si no hay datos guardados, crear nuevas instancias de las clases
			System.out.println("Configuración inicial del parqueadero");
			int plazasTotales = consola.pedirEntero("Ingrese el número de plazas totales");
			double tarifa = consola.pedirEntero("Ingrese la tarifa");
			parqueadero = new Parqueadero(plazasTotales, tarifa);
			baseDatos.setParqueadero(parqueadero);
		}
	}
}
