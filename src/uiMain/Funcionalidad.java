// Alejandro Arias Orozco

package uiMain;

import baseDatos.BaseDatos;
import gestorAplicacion.parqueadero.Parqueadero;
import gestorAplicacion.personas.Cliente;
import gestorAplicacion.vehiculos.Carro;
import gestorAplicacion.vehiculos.Moto;
import gestorAplicacion.vehiculos.TipoVehiculo;
import gestorAplicacion.vehiculos.Vehiculo;

import java.util.List;

/**
 * Clase abstracta de Funcionalidad.
 *
 * Cada funcionalidad del programa tendrá que extender esta clase e implementar el método ejecutar,
 * el cual será llamado desde el main.
 *
 * También sirve para compartir código y atributos entre las funcionalidades, como la base de datos, el parqueadero,
 * y métodos que son usados en más de una funcionalidad.
 */
public abstract class Funcionalidad {
	protected BaseDatos baseDatos;
	protected Parqueadero parqueadero;

	public abstract void ejecutar();

	public void setBaseDatos(BaseDatos baseDatos) {
		this.baseDatos = baseDatos;
		parqueadero = baseDatos.getParqueadero();
	}

	/**
	 * Busca un cliente en la base de datos por su cédula.
	 * Si el cliente no está registrado, le pregunta si desea registrarse y
	 * se procede con el registro.
	 * Cuando el cliente elige no registrarse devuelve null.
	 */
	protected Cliente buscarORegistrarCliente(long cedula) {
		// buscar si el cliente con esa cedula esta registrado en la base de datos
		Cliente cliente = baseDatos.buscarClienteRegistrado(cedula);
		if (cliente == null) { // si no se encuentra registrado, registrarlo. Preguntandole al cliente sus datos.
			System.out.println("Cliente no registrado");

			// preguntarle al cliente si quiere realizar el registro
			boolean continuarRegistro = Consola.pedirBoolean("Desea registrarse?");
			if (continuarRegistro) {
				return registrarCliente(cedula);
			} else {
				System.out.println("Ha escogido no registrarse.");
				return null;
			}
		} else {
			System.out.println("Bienvenido de nuevo, " + cliente.getNombre());
			return cliente;
		}
	}

	/**
	 * Le pregunta los datos al usuario y registra al cliente en la base de datos.
	 */
	protected Cliente registrarCliente(long cedula) {
		// Pedir los datos al cliente.
		System.out.println("Registro de cliente");
		String nombre = Consola.pedirString("Ingrese nombre");
		int telefono = Consola.pedirEntero("Ingrese teléfono");
		String correo = Consola.pedirString("Ingrese correo");
		String direccion = Consola.pedirString("Ingrese dirección");
		boolean discapacitado = Consola.pedirBoolean("Usted se encuentra en condición de discapacitado?");

		// Crear la instancia del cliente con la información suministrada
		Cliente cliente = new Cliente(nombre, cedula, telefono, correo, direccion, discapacitado);

		// agregar el cliente a la base de datos
		baseDatos.registrarCliente(cliente);

		// informar que el registro fue exitoso.
		System.out.println("Cliente registrado. Bienvenido!");
		return cliente;
	}

	protected Vehiculo registrarVehiculo(Cliente dueno) {
		// Se le pide al cliente que ingrese la placa del vehiculo
		String placa = Consola.pedirString("Ingrese la placa del vehículo a registrar (o 'q' para salir)");

		if (placa.equals("q")) {
			return null;
		}

		if (baseDatos.buscarVehiculoRegistrado(placa) != null) {
			System.out.println("Ese vehículo ya está registrado");
			return registrarVehiculo(dueno);
		}

		// Pedir los datos al cliente del vehiculo por registrar.
		System.out.println("Registro de vehículo");
		int tipoVehiculo = Consola.pedirEleccion("Elija el tipo de vehiculo", List.of("Carro", "Moto"));
		String marca = Consola.pedirString("Ingrese la marca del vehículo");
		String color = Consola.pedirString("Ingrese el color del vehículo");
		String modelo = Consola.pedirString("Ingrese el modelo del vehículo");

		Vehiculo vehiculo;

		// crear instancias del vehiculo según el tipo escogido por el cliente
		if (tipoVehiculo == 0) { // Si el tipo de vehiculo es un carro...
			// preguntarle al usuario el tipo y numero de puestos del carro
			int tipoCarro = Consola.pedirEleccion("Elija el tipo de carro", List.of("Mecanico", "Automatico"));
			TipoVehiculo tipo = TipoVehiculo.MECANICO;
			if (tipoCarro == 1) {
				tipo = TipoVehiculo.AUTOMATICO;
			}
			int puestos = Consola.pedirEntero("Ingrese el numero de puestos del Carro");
			// crear la instancia del carro con la información suministrada por el cliente
			vehiculo = new Carro(placa, dueno, marca, color, modelo, tipo, puestos);
		} else { // Si el tipo de vehiculo es una moto...
			// preguntar el tipo de moto al cliente y su cilindraje
			int tipoMoto = Consola.pedirEleccion("Elija el tipo de moto", List.of("Normal", "Alto cilindraje"));
			TipoVehiculo tipo = TipoVehiculo.NORMAL;
			if (tipoMoto == 1) {
				tipo = TipoVehiculo.ALTOCC;
			}
			int cilindraje = Consola.pedirEntero("Ingrese el cilindraje de la moto (su valor numerico)");
			// crear la instancia de la moto con la información suministrada por el cliente
			vehiculo = new Moto(placa, dueno, marca, color, modelo, tipo, cilindraje);
		}
		// agregar el vehiculo a la base de datos
		baseDatos.registrarVehiculo(vehiculo);

		// informar al usuario que se ha completado el registro existosamente.
		System.out.println("Vehículo registrado");

		return vehiculo;
	}
}
