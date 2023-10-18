// Alejandro Arias Orozco

package uiMain;

import baseDatos.BaseDatos;
import gestorAplicacion.parqueadero.Factura;
import gestorAplicacion.parqueadero.Parqueadero;
import gestorAplicacion.parqueadero.Plaza;
import gestorAplicacion.personas.Cliente;
import gestorAplicacion.vehiculos.Carro;
import gestorAplicacion.vehiculos.Moto;
import gestorAplicacion.vehiculos.TipoVehiculo;
import gestorAplicacion.vehiculos.Vehiculo;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
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

	protected Vehiculo registrarVehiculo(Cliente cliente) {
		// Se le pide al cliente que ingrese la placa del vehiculo
		String placa = Consola.pedirString("Ingrese la placa del vehículo a registrar (o 'q' para salir)");

		if (placa.equals("q")) {
			return null;
		}

		if (baseDatos.buscarVehiculoRegistrado(placa) != null) {
			System.out.println("Ese vehículo ya está registrado");
			return registrarVehiculo(cliente);
		}
		
		List<String> tiposVehiculo = new ArrayList<>();
		tiposVehiculo.add("Carro");

		//verificar si el cliente esta en condicion de discapacidad, en caso afirmativo no permitirle registrar un vehiculo moto
		if (!cliente.isDiscapacitado()) {
			tiposVehiculo.add("Moto");
		}
		
		// Pedir los datos al cliente del vehículo por registrar.
		System.out.println("Registro de vehículo");
		int tipoVehiculo = Consola.pedirEleccion("Elija el tipo de vehiculo", tiposVehiculo);
		String marca = Consola.pedirString("Ingrese la marca del vehículo");
		String color = Consola.pedirString("Ingrese el color del vehículo");
		String modelo = Consola.pedirString("Ingrese el modelo del vehículo");

		Vehiculo vehiculo;

		// crear instancias del vehículo según el tipo escogido por el cliente
		if (tipoVehiculo == 0) { // Si el tipo de vehículo es un carro...
			// preguntarle al usuario el tipo y número de puestos del carro
			int tipoCarro = Consola.pedirEleccion("Elija el tipo de carro", List.of("Mecanico", "Automatico"));
			TipoVehiculo tipo = TipoVehiculo.MECANICO;
			if (tipoCarro == 1) {
				tipo = TipoVehiculo.AUTOMATICO;
			}
			int puestos = Consola.pedirEntero("Ingrese el numero de puestos del Carro");
			// crear la instancia del carro con la información suministrada por el cliente
			vehiculo = new Carro(placa, cliente, marca, color, modelo, tipo, puestos);
		} else { // Si el tipo de vehículo es una moto...
			// preguntar el tipo de moto al cliente y su cilindraje
			int tipoMoto = Consola.pedirEleccion("Elija el tipo de moto", List.of("Normal", "Alto cilindraje"));
			TipoVehiculo tipo = TipoVehiculo.NORMAL;
			if (tipoMoto == 1) {
				tipo = TipoVehiculo.ALTOCC;
			}
			int cilindraje = Consola.pedirEntero("Ingrese el cilindraje de la moto (su valor numerico)");
			// crear la instancia de la moto con la información suministrada por el cliente
			vehiculo = new Moto(placa, cliente, marca, color, modelo, tipo, cilindraje);
		}
		// agregar el vehículo a la base de datos
		baseDatos.registrarVehiculo(vehiculo);

		// agregar el vehículo al cliente
		cliente.agregarVehiculo(vehiculo);

		// informar al usuario que se ha completado el registro exitosamente.
		System.out.println("Vehículo registrado");

		return vehiculo;
	}
	
	protected void ingresarVehiculo(Cliente cliente, Vehiculo vehiculo) {
		// buscar las plazas que hay disponibles que cumplen con las características del vehículo
		// que el cliente desea ingresar
		List<Plaza> plazas = parqueadero.plazasDisponiblesPara(vehiculo);

		// en caso de que no hayan plazas disponibles para ese vehículo, se informa de esto al usuario
		if (plazas.isEmpty()) {
			System.out.println("No hay plazas disponibles. Lo sentimos.");
			return;
		}

		// mostrar las plazas disponibles al usuario y pedirle que escoja una.
		Plaza plaza = pedirPlaza(plazas);

		// ingresar el vehículo al parqueadero
		parqueadero.ingresarVehiculo(vehiculo, plaza);

		// asignar al cliente una factura con el servicio de Parqueadero en primera instancia
		generarFactura(cliente);
		System.out.println("Vehículo ingresado. Bienvenido!");
		
	}

	private Plaza pedirPlaza(List<Plaza> plazas) {
		// mostrar las plazas disponibles al cliente
		System.out.println("Plazas disponibles:");
		int i = 1;
		for (Plaza plaza : plazas) {
			System.out.printf("%d", plaza.getNumeroPlaza());
			if (i < plazas.size()) {
				System.out.print(" ");
			}
			i++;
		}
		System.out.println();

		// pedirle al cliente que elija una plaza
		int numeroPlaza = Consola.pedirEntero("Elija una plaza");

		// verificar que la plaza escogida está en la lista de plazas disponibles, y si es asi, encontrar su instancia
		Plaza plaza = buscarPlaza(plazas, numeroPlaza);

		// si no se encontró la plaza, entonces informar al usuario que escogió mal y volver a preguntarle.
		if (plaza == null) {
			System.out.println("Plaza no encontrada, por favor escoja una plaza válida.");
			return pedirPlaza(plazas);
		}
		return plaza;
	}

	private Plaza buscarPlaza(List<Plaza> plazas, int numeroPlaza) {
		// buscar una plaza por su numero de plaza.
		for (Plaza plaza : plazas) {
			if (plaza.getNumeroPlaza() == numeroPlaza) {
				return plaza;
			}
		}
		return null;
	}
	
	// metodo para asignar una factura a un cliente cuando este ingresa un vehiculo al parqueadero y retornar un string de la misma
	private void generarFactura(Cliente cliente) {
			Factura f = new Factura(cliente); f.agregarServicio("Paqueadero");
			System.out.printf("Se ha generado la factura con hora de ingreso: %s%n", f.getHoraIngreso().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));
	}
}
