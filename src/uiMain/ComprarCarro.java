//Sofia

package uiMain;

import java.util.ArrayList;
import java.util.List;

import gestorAplicacion.personas.Cliente;
import gestorAplicacion.vehiculos.Vehiculo;
import gestorAplicacion.vehiculos.Carro;

public class ComprarCarro extends Funcionalidad {
	@Override
	public void ejecutar() {
		// Funcionalidad Comprar un carro
		System.out.println("Comprar un carro");
		
		//Se pide al cliente ingresar la cédula para verificar su registro
		
		long cedula = Consola.pedirLong("Ingrese cédula");
		
		//Se busca en la base de datos un cliente con la cédula ingresada, de no existir se pide al cliente registrarse
		Cliente cliente = buscarORegistrarCliente(cedula);
		
		if (cliente == null) {
			return;
		}
		
		//Se muestra al usuario sus carros registrados o se permite ingresar el vehiculo que desea vender
		Vehiculo vehiculo = escogerVehiculo(cliente);
		
		if (vehiculo == null) {
			return;
		}
		
		//Se pide el precio por el que desea venderlo
		long precioVenta = precioCarro();
		((Carro)vehiculo).setPrecioVenta(precioVenta);
		
		//Se muestran las opciones de vendedor que existen 
		
	}
	private Vehiculo escogerVehiculo(Cliente cliente) {
		List<String> vehiculos = new ArrayList<>(cliente.getVehiculos().stream().map(Vehiculo::getPlaca).toList());
		vehiculos.add("Volver al menú principal");
		
		int opcionEscogida = Consola.pedirEleccion("Escoja el vehículo que desea vender ", vehiculos);
		
		//Se verifica si desea volver al menú principal, o si el vehiculo se encuentra enparqueadero
		
		if (opcionEscogida == vehiculos.size() - 1) { //si desea volver se retorna null
			return null;
		}
		else { //cuando se escoge un vehiculo
			Vehiculo vehiculo = cliente.getVehiculos().get(opcionEscogida);
			if (!vehiculo.estaParqueado()) { // si el vehículo no esta parqueado se informa y se pide ingresar el vehículo 
				boolean elec = Consola.pedirBoolean("El vehiculo no se encuentra en el parqueadero, ¿desea ingresarlo?");
				if (elec == false) { // si se escoge no se vuelve al menu del metodo
					return escogerVehiculo(cliente); 
				}
				else { // si se escoge si se llama la funcionalidad ingresar vehiculo y se ingresa al taller
					ingresarVehiculo(cliente, vehiculo);
					return vehiculo;
				}
			}
			else { // si el vehiculo se encuentra parqueado se retorna el vehiculo
				return vehiculo;
			}
		}
	}
	
	private long precioCarro() {
		long precioVenta = Consola.pedirLong("Escriba el precio por el que desea vender su vehículo");
		long precioMaximo = 130000000;
		if (precioVenta<0) {
			System.out.println("Ese precio no es válido");
			return precioCarro(); //se vuelve a pedir el precio del carro
		} else if (precioVenta>precioMaximo) {
			System.out.println("El precio escogido no es aceptado por el administrador");//Revisar mensaje
			return precioCarro();
		} else return precioVenta;
	}
	
}
