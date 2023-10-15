// Alejandro Arias Orozco
// Sebastián

package uiMain;

import gestorAplicacion.parqueadero.Plaza;
import gestorAplicacion.personas.Cliente;
import gestorAplicacion.vehiculos.Carro;
import gestorAplicacion.vehiculos.Moto;
import gestorAplicacion.vehiculos.TipoVehiculo;
import gestorAplicacion.vehiculos.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class IngresarVehiculo extends Funcionalidad {
	@Override
	public void ejecutar() {
		// Funcionalidad ingresar vehículo
		System.out.println("Ingresar vehículo");

		// Se le pide al cliente que ingrese su cédula
		long cedula = Consola.pedirLong("Ingrese cédula");

		// Se busca un cliente con esa cédula y si no existe, registrarlo.
		Cliente cliente = buscarORegistrarCliente(cedula);
		if (cliente == null) { // esto puede ocurrir si el usuario elige no registrarse
			return; // en ese caso, no continuamos con la funcionalidad y se regresa al menú principal
		}

		// mostrar al usuario sus vehículos registrados, entre otras opciones, y pedirle que elija uno
		Vehiculo vehiculo = pedirEleccionVehiculoRegistrado(cliente);
		if (vehiculo == null) { // sucede cuando el usuario elige volver al menú principal
			return; // volvemos al menú principal
		}

		// verificar que el vehículo que se está ingresando pertenece al cliente que lo intenta ingresar.
		if (!vehiculo.registradoPor(cliente)) {
			System.out.println("Este vehiculo se encuentra registrado por otro cliente.");
			return;
		}

		// buscar las plazas que hay disponibles que cumplen con las características del vehículo
		// que el cliente desea ingresar
		List<Plaza> plazas = parqueadero.plazasDisponiblesPara(vehiculo);

		// mostrar las plazas disponibles al usuario y pedirle que escoja una.
		Plaza plaza = pedirPlaza(plazas);

		// ingresar el vehículo al parqueadero
		parqueadero.ingresarVehiculo(vehiculo, plaza);

		// asignar al cliente una factura con el servicio de Parqueadero en primera instancia
		parqueadero.generarFactura(cliente); // TODO: solucionar el tema de la hora de ingreso
	}

	private Vehiculo pedirEleccionVehiculoRegistrado(Cliente cliente) {
		// se obtiene una lista de los vehículos que están registrados por el cliente
		List<Vehiculo> vehiculosDelCliente = baseDatos.vehiculosRegistradosPor(cliente);

		// se crea una lista de opciones para que el usuario escoja una. Incluye los vehículos del usuario (solo la placa)
		// y opciones para registrar vehículo y para volver al menú principal.
		List<String> opcionesVehiculos = new ArrayList<>(vehiculosDelCliente.stream().map(Vehiculo::getPlaca).toList());
		opcionesVehiculos.add("Registrar vehículo");
		opcionesVehiculos.add("Volver al menú principal");

		// se pide al usuario que elija una opción, y se verifica que si se escogió un vehículo,
		// este no se encuentre ya adentro del parqueadero.
		int vehiculoEleccion = Consola.pedirEleccion("Sus vehículos registrados", opcionesVehiculos, eleccion -> {
			// las elecciones >= al tamaño de vehiculosDelCliente son las de registrar y volver. En esas no verificamos nada
			if (eleccion >= vehiculosDelCliente.size()) {
				return true;
			}

			Vehiculo vehiculo = vehiculosDelCliente.get(eleccion);

			// si el vehículo ya se encuentra en el parqueadero, entonces informar al cliente de esto y
			// volver a preguntarle que escoja un vehículo
			if (vehiculo.estaParqueado()) {
				System.out.println("El vehículo ya se encuentra en el parqueadero!");
				return false;
			}

			return true;
		});

		Vehiculo vehiculo;
		if (vehiculoEleccion == vehiculosDelCliente.size()) { // cuando el usuario elige registrar vehículo
			vehiculo = registrarVehiculo(cliente);
			if (vehiculo == null) { // esto puede suceder si el usuario decidió salir del registro de vehículo
				return pedirEleccionVehiculoRegistrado(cliente);
			}
			return vehiculo;
		} else if (vehiculoEleccion == vehiculosDelCliente.size() + 1) { // cuando el usuario elige volver al menú
			return null;
		} else { // cuando el usuario elige un vehículo
			return vehiculosDelCliente.get(vehiculoEleccion);
		}
	}

	private Vehiculo registrarVehiculo(Cliente dueno) {
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

		// verificar que la plaza escogida esta en la lista de plazas disponibles, y si es asi, encontrar su instancia
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
}
