// Alejandro Arias Orozco

package uiMain;

import baseDatos.BaseDatos;
import baseDatos.BaseDatosException;
import gestorAplicacion.parqueadero.Parqueadero;
import gestorAplicacion.personas.Cliente;
import gestorAplicacion.vehiculos.Carro;
import gestorAplicacion.vehiculos.Moto;
import gestorAplicacion.vehiculos.Vehiculo;

import java.util.List;

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
		Vehiculo vehiculo = baseDatos.buscarVehiculoRegistrado(placa);
		if (vehiculo == null) {
			vehiculo = registrarVehiculo(placa, cliente);
		}

		if (vehiculo instanceof Carro) {
			// mostrar plazas para carro
		} else {
			// mostrar plazas para moto
		}
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

	private static Vehiculo registrarVehiculo(String placa, Cliente dueno) {
		System.out.println("Registro de vehículo");
		int tipoVehiculo = consola.pedirEleccion("Elija el tipo de vehiculo", List.of("Carro", "Moto"));
		String marca = consola.pedirString("Ingrese la marca del vehículo");
		String color = consola.pedirString("Ingrese el color del vehículo");
		String modelo = consola.pedirString("Ingrese el modelo del vehículo");
		Vehiculo vehiculo;
		if (tipoVehiculo == 0) {
			vehiculo = new Carro(placa, dueno, marca, color, modelo);
		} else {
			int tipoMoto = consola.pedirEleccion("Elija el tipo de moto", List.of("Normal", "Alto cilindraje"));
			String tipo = "normal";
			if (tipoMoto == 1) {
				tipo = "altoCC";
			}
			vehiculo = new Moto(placa, dueno, marca, color, modelo, tipo);
		}
		baseDatos.registrarVehiculo(vehiculo);
		System.out.println("Vehículo registrado");
		return vehiculo;
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
			double tarifaCarro = consola.pedirEntero("Ingrese la tarifa para carros");
			double tarifaMoto = consola.pedirEntero("Ingrese la tarifa para motos");
			parqueadero = new Parqueadero(plazasTotales, tarifaCarro, tarifaMoto);
			baseDatos.setParqueadero(parqueadero);
		}
	}
}
