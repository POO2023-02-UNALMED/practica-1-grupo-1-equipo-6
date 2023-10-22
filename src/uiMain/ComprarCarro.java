//Sofia

package uiMain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gestorAplicacion.personas.Cliente;
import gestorAplicacion.personas.Empleado;
import gestorAplicacion.vehiculos.Vehiculo;
import gestorAplicacion.vehiculos.Carro;

public class ComprarCarro extends Funcionalidad {
	@Override
	public void ejecutar() {
		//Funcionalidad Comprar un carro
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
		List<Empleado> vendedores = new ArrayList<>(parqueadero.getEmpleados().stream().filter(empleado -> "Vendedor".equals(empleado.getCargo())).collect(Collectors.toList()));
		//lista con  los nombres de los vendedores
		List<String> nombresVendedores = new ArrayList<>(vendedores.stream().map(Empleado::getNombre).toList());
		
		int escogerVendedor = Consola.pedirEleccion("Escoja el vendedor de su preferencia", nombresVendedores);
		Empleado vendedor = vendedores.get(escogerVendedor);
		
		System.out.printf("Hola, mi nombre es %s y voy a atenderlo.%n", vendedor.getNombre());
		
		System.out.println("Su carro será revisado en el taller y se le dará una oferta de compra.");
		
		//Se lleva el vehículo al taller
		//Se muestran las opciones de mecanico que existen 
		List<Empleado> mecanicosVenta = new ArrayList<>(parqueadero.getEmpleados().stream().filter(empleado -> "Mecanico".equals(empleado.getCargo())).collect(Collectors.toList()));
		//lista con  los nombres de los mecanicos
		List<String> nombresMecanicosVenta = new ArrayList<>(vendedores.stream().map(Empleado::getNombre).toList());
		
		int escogerMecanicoVenta = Consola.pedirEleccion("Escoja el mecánico de su preferencia", nombresMecanicosVenta);
		Empleado mecanicoVenta = mecanicosVenta.get(escogerMecanicoVenta);
		
		System.out.printf("Hola, mi nombre es %s y voy a atenderlo.%n", mecanicoVenta.getNombre());
		
		revisionTaller(vehiculo);
		
		
	}
	private Vehiculo escogerVehiculo(Cliente cliente) {
		// lista para obtener solo los carros
		List<Vehiculo> carros = new ArrayList<>(cliente.getVehiculos().stream().filter(vehiculo -> vehiculo instanceof Carro).collect(Collectors.toList()));
		List<String> placasCarros = new ArrayList<>(carros.stream().map(Vehiculo::getPlaca).toList());
		
		//vehiculos.add("Volver al menú principal");
		
		int opcionEscogida = Consola.pedirEleccion("Escoja el vehículo que desea vender ", placasCarros);
		
		//Se verifica si desea volver al menú principal, o si el vehiculo se encuentra enparqueadero
		
		if (opcionEscogida == carros.size() - 1) { //si desea volver se retorna null
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
		} else {
			return precioVenta;
		}
		}

	
	private void revisionTaller(Vehiculo vehiculo) {
		
	}

}