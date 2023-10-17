// sebastian

package uiMain;

import gestorAplicacion.personas.Cliente;
import gestorAplicacion.vehiculos.*;
import java.util.List;
import java.util.ArrayList;

public class Taller extends Funcionalidad {
	@Override
	public void ejecutar() {
		//funcionalidad Taller
		System.out.println("Taller");
		
		//se solicita la cedula y se busca el cliente en la base de datos
		long cedula = Consola.pedirLong("Ingrese cédula");
		
		Cliente cliente = buscarORegistrarCliente(cedula);
		if (cliente == null) {
			return; // cuando el cliente no decide registrarse
		}
		
		//como solo se presta servicio de taller a vehiculos parqueados, se procede a 
		//mostrarle al cliente sus vehiculos y a que escoja uno
		Vehiculo vehiculo = escogerVehiculo(cliente);
		if (vehiculo == null) {
			return; //cuando se decide volver al menú principal
		}
		
		int servicioEscogido;
		
		if (vehiculo instanceof Carro) { // si el vehiculo es un carro mostrar las opciones disponibles para carrp
		// mostrar los servicios disponibles
			servicioEscogido = Consola.pedirEleccion("Que desea realizar", List.of(
					"Revisión general", //revision general del vehiculo
					"Cambio de motor",
					"Cambio de transmision",
					"Cambio de acelerador",
					"Cambio de freno",
					"Cambio de bateria",
					"Cambio de pedal",
					"Cambio de depositos",
					"Cambio de llantas",
					"Cambio de rines",
					"Cambio de amortiguadores"));
		}
		else if (vehiculo instanceof Moto) { // si es una moto se procede de la misma manera
			servicioEscogido = Consola.pedirEleccion("Que desea realizar", List.of(
					"Revisión general", //revision general del vehiculo
					"Cambio de motor",
					"Cambio de transmision",
					"Cambio de acelerador",
					"Cambio de freno",
					"Cambio de bateria",
					"Cambio de cadena",
					"Cambio de pedales",
					"Cambio de amortiguador",
					"Cambio de depositos",
					"Cambio de llantas",
					"Cambio de rines"));
		}
		// TODO: mirar si es viable hacerlo asi
	}
	
	// metodo que muestra los vehiculos de un cliente y retorna el escogido
	private Vehiculo escogerVehiculo(Cliente cliente) {
		//se crea una lista con las placas de cada vehiculo y se pide escoger una
		List<String> vehiculos = new ArrayList<>(cliente.getVehiculos().stream().map(Vehiculo::getPlaca).toList());
		vehiculos.add("Volver al menú principal");
		
		int vehiculoEscogido = Consola.pedirEleccion("Escoja el vehículo que desea ingresar al taller", vehiculos);
		
		//verificar la opcion escogida y si el Vehiculo se encuentra en el parqueadero 
		if (vehiculoEscogido == vehiculos.size() - 1) { //si desea volver se retorna null
			return null;
		}
		else { //cuando se escoge un vehiculo
			Vehiculo vehiculo = cliente.getVehiculos().get(vehiculoEscogido);
			if (!vehiculo.estaParqueado()) { // si el vehículo no esta parqueado se informa y se pide ingresar el vehículo 
				int elec = Consola.pedirEleccion("El vehiculo no se encuentra en el parqueadero, ¿desea ingresarlo?", List.of("SI", "NO"));
				if (elec == 1) { // si se escoge no se vuelve al menu del metodo
					return escogerVehiculo(cliente); 
				}
				else { // si se escoge si se llama la funcionalidad ingresar vehiculo y se regresa el menu del metodo
					Funcionalidad funcionalidad = new IngresarVehiculo(); funcionalidad.setBaseDatos(baseDatos); funcionalidad.ejecutar(); // TODO: preguntar a alejandro sobre esta linea
					return escogerVehiculo(cliente);
				}
			}
			else { // si el vehiculo se encuentra parqueado se retorna el vehiculo
				return vehiculo;
			}
		}
	}
}
