// Alejandro Arias Orozco
// Sebastián

package uiMain;

import gestorAplicacion.parqueadero.Factura;
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
		System.out.println(generarFactura(cliente));
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
	private String generarFactura(Cliente cliente) {
			Factura f = new Factura(cliente); f.agregarServicio("Paqueadero");
			return "Se ha registrado el vehiculo. Bienvenido!!";
	}
}
