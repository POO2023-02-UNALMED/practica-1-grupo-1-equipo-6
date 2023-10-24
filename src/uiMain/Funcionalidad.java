/*
 Funcionalidad del módulo: contiene la clase Funcionalidad que implementan todas las funcionalidades del programa
 Componentes del módulo: Funcionalidad
 Autores: Alejandro, Sebastian
*/

package uiMain;

import baseDatos.BaseDatos;
import gestorAplicacion.parqueadero.Factura;
import gestorAplicacion.parqueadero.Parqueadero;
import gestorAplicacion.parqueadero.Plaza;
import gestorAplicacion.personas.Cliente;
import gestorAplicacion.vehiculos.Carro;
import gestorAplicacion.vehiculos.MarcasCarro;
import gestorAplicacion.vehiculos.MarcasMoto;
import gestorAplicacion.vehiculos.Moto;
import gestorAplicacion.vehiculos.TipoVehiculo;
import gestorAplicacion.vehiculos.Vehiculo;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
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

	/**
	 * Método donde estará implementada la funcionalidad
	 */
	public abstract void ejecutar();

	/**
	 * Asigna la base de datos y el parqueadero
	 */
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
		long telefono = Consola.pedirLong("Ingrese teléfono");
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

	/**
	 * Hace el proceso de registro de vehículo,
	 * puede retornar null en caso de que el usuario haya decidido no continuar con el registro.
	 */
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
		String color = Consola.pedirString("Ingrese el color del vehículo");
		String modelo = Consola.pedirString("Ingrese el modelo del vehículo");
		boolean discapacitado = cliente.isDiscapacitado();

		Vehiculo vehiculo;

		// crear instancias del vehículo según el tipo escogido por el cliente
		if (tipoVehiculo == 0) { // Si el tipo de vehículo es un carro...
			// preguntarle al usuario la marca, el tipo y número de puestos del carro
			int marcaEscogida = Consola.pedirEleccion("Escoja la marca del carro", Arrays.asList(MarcasCarro.values()).stream().map(MarcasCarro::name).toList());			
			String marca = MarcasCarro.values()[marcaEscogida].name();			
			int tipoCarro = Consola.pedirEleccion("Elija el tipo de carro", List.of("Mecanico", "Automatico"));
			TipoVehiculo tipo = TipoVehiculo.MECANICO;
			if (tipoCarro == 1) {
				tipo = TipoVehiculo.AUTOMATICO;
			}
			int puestos = Consola.pedirEntero("Ingrese el numero de puestos del Carro");
			// crear la instancia del carro con la información suministrada por el cliente
			vehiculo = new Carro(placa, cliente, marca, color, modelo, tipo, puestos, discapacitado);
		} else { // Si el tipo de vehículo es una moto...
			// preguntar la marca, el tipo de moto al cliente y su cilindraje
			int marcaEscogida = Consola.pedirEleccion("Escoja la marca de la moto", Arrays.asList(MarcasMoto.values()).stream().map(MarcasMoto::name).toList());			
			String marca = MarcasMoto.values()[marcaEscogida].name();			
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

	/**
	 * Ingresa un vehículo al parqueadero
	 * Recibe el cliente que es dueño del vehículo y la instancia del vehículo
	 * Pregunta al usuario que elija las plazas y genera una factura de ingreso.
	 */
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

	/**
	 * Imprime las plazas disponibles (pasadas como parámetro) y le pide al usuario que escoja una, verificando que la elección es válida
	 */
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

	/**
	 * Busca entre una lista de plazas pasada como parámetro una plaza por su número de plaza
	 * Retorna la plaza encontrada o null si no hay una plaza con ese número en la lista.
	 */
	private Plaza buscarPlaza(List<Plaza> plazas, int numeroPlaza) {
		// buscar una plaza por su numero de plaza.
		for (Plaza plaza : plazas) {
			if (plaza.getNumeroPlaza() == numeroPlaza) {
				return plaza;
			}
		}
		return null;
	}
	
	/**
	 * metodo para asignar una factura a un cliente cuando este ingresa un vehiculo al parqueadero y retornar un string de la misma
	 */
	private void generarFactura(Cliente cliente) {
			Factura f = new Factura(cliente); f.agregarServicio("Paqueadero", 1);
			System.out.printf("Se ha generado la factura con hora de ingreso: %s%n", f.getHoraIngreso().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));
	}
	
	/**
	 * metodo que se encarga de capitalizar una palabra jajaj, para usarlo en los metodos para el HashMap
	 */
	protected static String cap(String palabra) {
		return Character.toUpperCase(palabra.charAt(0)) + palabra.substring(1).toLowerCase();
	}
	
}
