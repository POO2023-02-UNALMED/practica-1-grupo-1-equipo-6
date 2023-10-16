// sebastian

package uiMain;

import gestorAplicacion.personas.Cliente;
import gestorAplicacion.vehiculos.Vehiculo;
import java.util.List;
import java.util.ArrayList;

public class Taller extends Funcionalidad {
	@Override
	public void ejecutar() {
		//funcionalidad Taller
		System.out.println("Taller");
		
		//se solicita la cedula
		long cedula = Consola.pedirLong("Ingrese su cédula");
		
		Cliente cliente = buscarORegistrarCliente(cedula);
		if (cliente == null) {
			return;
		}
		
		//como solo se presta servicio de taller a vehiculos parqueados, se procede a 
		//mostrarle al cliente sus vehiculos registrados y a que escoja uno
		Vehiculo vehiculo = escogerVehiculo(cliente);
		if (vehiculo == null) {
			return; //cuando se decide volver al menú principal
		}
		
		
	}
	
	// metodo que muestra los vehiculos de un cliente y retorna el escogido
	private Vehiculo escogerVehiculo(Cliente cliente) {
		//se crea una lista con las placas de cada vehiculo y se pide escoger una
		List<String> vehiculos = new ArrayList<>(cliente.getVehiculos().stream().map(Vehiculo::getPlaca).toList());
		vehiculos.add("Volver al menú principal");
		
		int vehiculoEscogido = Consola.pedirEleccion("Escoja el vehículo que desea ingresar al taller", vehiculos);
		
		//verificar la opcion escogida y si el Vehiculo se encuentra en el parqueadero // TODO: Continuar...
		if (vehiculoEscogido == vehiculos.size() - 1) { //si desea volver se retorna null
			return null;
		}
		else { //cuando se escoge un vehiculo
			return cliente.getVehiculos().get(vehiculoEscogido);
		}
	}
}
