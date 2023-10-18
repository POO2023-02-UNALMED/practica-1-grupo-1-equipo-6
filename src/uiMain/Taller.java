// sebastian

package uiMain;

import gestorAplicacion.parqueadero.Almacen;
import gestorAplicacion.parqueadero.Producto;
import gestorAplicacion.parqueadero.TipoProducto;
import gestorAplicacion.personas.Cliente;
import gestorAplicacion.personas.Empleado;
import gestorAplicacion.vehiculos.*;
import java.util.List;
import java.util.stream.Collectors;
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
		// 
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
				boolean elec = Consola.pedirBoolean("El vehiculo no se encuentra en el parqueadero, ¿desea ingresarlo?");
				if (elec == false) { // si se escoge no se vuelve al menu del metodo
					return escogerVehiculo(cliente); 
				}
				else { // si se escoge si se llama la funcionalidad ingresar vehiculo y se regresa el menu del metodo
					ingresarVehiculo(cliente, vehiculo);
					return vehiculo;
				}
			}
			else { // si el vehiculo se encuentra parqueado se retorna el vehiculo
				return vehiculo;
			}
		}
	}

	//metodo que emula la venta de un unico repuesto, como ya se sabe desde Taller que producto se debe comprar, 
	//se procede directamente con la compra de este
	private Producto ventaRespuesto(Cliente cliente, TipoProducto tipoProducto) {
		// almacen
		Almacen almacen = parqueadero.getAlmacen();
		System.out.println("Bienvenido al almacen");
		
		//lista de todos los vendedores del parqueadero
		List<Empleado> vendedores = new ArrayList<>(parqueadero.getEmpleados().stream().filter(empleado -> "Vendedor".equals(empleado.getCargo())).collect(Collectors.toList()));
		//lista con los nombres de los vendedores
		List<String> nombresVendedores = new ArrayList<>(vendedores.stream().map(Empleado::getNombre).toList());
		
		
		// se pide escoger un vendedor
		int EmpleadoEleccion = Consola.pedirEleccion("Seleccione su vendedor de preferencia", nombresVendedores);
		Empleado vendedor = vendedores.get(EmpleadoEleccion);
		
		// se verifica si hay instancias del producto en el inventario 
		if (almacen.existeProducto(tipoProducto)) { // si si hay se procede a conseguir este producto
			Producto producto = almacen.conseguirProducto(tipoProducto);
		}
		return null;
		//TODO: continuar..
	}
}
