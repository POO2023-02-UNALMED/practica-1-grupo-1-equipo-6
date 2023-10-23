//Sofia

package uiMain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gestorAplicacion.personas.Cliente;
import gestorAplicacion.personas.Empleado;
import gestorAplicacion.vehiculos.Vehiculo;
import gestorAplicacion.vehiculos.Carro;
import gestorAplicacion.vehiculos.MarcasCarro;

//Permite que el cliente venda un carro al parqueadero
public class ComprarCarro extends Funcionalidad {
	@Override
	public void ejecutar() {
		//Funcionalidad Comprar un carro
		System.out.println("Vender carro");
		
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
		
		//Se muestran las opciones de vendedor que existen 
		List<Empleado> vendedores = new ArrayList<>(parqueadero.getEmpleados().stream().filter(empleado -> "Vendedor".equals(empleado.getCargo())).collect(Collectors.toList()));
		//lista con  los nombres de los vendedores
		List<String> nombresVendedores = new ArrayList<>(vendedores.stream().map(Empleado::getNombre).toList());
		
		int escogerVendedor = Consola.pedirEleccion("Escoja el vendedor de su preferencia", nombresVendedores);
		Empleado vendedor = vendedores.get(escogerVendedor);
		
		System.out.printf("Hola, mi nombre es %s y voy a atenderlo.%n", vendedor.getNombre());
		
		//Se le pide el precio por el que desea vender el carro
		long precioCliente = precioCarro(vehiculo, vendedor);
		
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
		if (cliente.getVehiculos().size() == 0) {
			boolean e = Consola.pedirBoolean("Usted no tiene vehiculos registrados para vender, ¿desea registrar e ingresar su vehiculo?");
			if (e) {
				Vehiculo vehiculoRegistrado = registrarVehiculo(cliente);
				String marca = vehiculoRegistrado.getMarca().toUpperCase();
				if (vehiculoRegistrado != null && (marca=="TOYOTA" || marca=="RENAULT"|| marca=="CHEVROLET" || marca=="KIA" || marca== "MAZDA")) {
					ingresarVehiculo(cliente, vehiculoRegistrado);
				}
				else if (vehiculoRegistrado != null){
					System.out.println("La marca seleccionada no es válida para venta de carro");
				}
			} else {
					return null;
			}
		}
		// lista para obtener solo los carros
		List<Vehiculo> carros = new ArrayList<>(cliente.getVehiculos().stream().filter(vehiculo -> vehiculo instanceof Carro).collect(Collectors.toList()));
		List<Vehiculo> carrosMarcas = new ArrayList<>(carros.stream().filter(vehiculo -> (((Carro)vehiculo).getMarca()=="TOYOTA" || ((Carro)vehiculo).getMarca()=="RENAULT"|| ((Carro)vehiculo).getMarca()=="CHEVROLET" || ((Carro)vehiculo).getMarca()=="KIA" || ((Carro)vehiculo).getMarca()== "MAZDA")).collect(Collectors.toList()));
		if (carrosMarcas.size() == 0) {
			boolean e = Consola.pedirBoolean("Usted no tiene vehiculos registrados con las marcas aceptadas por el parqueadero para venta, ¿desea registrar e ingresar su vehiculo?");
			if (e) {
				Vehiculo vehiculoRegistrado = registrarVehiculo(cliente);
				String marca = vehiculoRegistrado.getMarca().toUpperCase();
				if (vehiculoRegistrado != null && (marca=="TOYOTA" || marca=="RENAULT"|| marca=="CHEVROLET" || marca=="KIA" || marca== "MAZDA")) {
					ingresarVehiculo(cliente, vehiculoRegistrado);
				}
				else if (vehiculoRegistrado != null){
					System.out.println("La marca seleccionada no es válida para venta de carro");
				}
			} else {
					return null;
			}
		}
		List<String> placasCarros = new ArrayList<>(carrosMarcas.stream().map(Vehiculo::getPlaca).toList());
		placasCarros.add("Volver al menú principal");
		
		int opcionEscogida = Consola.pedirEleccion("Escoja el vehículo que desea vender ", placasCarros);
		
		//Se verifica si desea volver al menú principal, o si el vehiculo se encuentra enparqueadero
		
		if (opcionEscogida == carrosMarcas.size() - 1) { //si desea volver se retorna null
			return null;
		}
		else { //cuando se escoge un vehiculo
			Vehiculo vehiculo = carrosMarcas.get(opcionEscogida);
			if (!vehiculo.estaParqueado()) { // si el vehículo no esta parqueado se informa y se pide ingresar el vehículo 
				boolean elec = Consola.pedirBoolean("El vehiculo no se encuentra en el parqueadero, ¿desea ingresarlo?");
				if (elec == false) { // si se escoge no se vuelve al menu del metodo
					return escogerVehiculo(cliente); 
				}
				else { // si se escoge si se llama la funcionalidad ingresar vehiculo y se ingresa al parqueadero
					ingresarVehiculo(cliente, vehiculo);
					return vehiculo;
				}
			}
			else { // si el vehiculo se encuentra parqueado se retorna el vehiculo
				return vehiculo;
			}
		}
	}
	
	private long precioCarro(Vehiculo vehiculo, Empleado vendedor) {
		long precioCliente = Consola.pedirLong("Escriba el precio por el que desea vender su vehículo");
		String marca = ((Carro)vehiculo).getMarca();
		long precioMaximo = 0;
		if (marca.toUpperCase() == "RENAULT") {
			precioMaximo = vendedor.precioMaximoCarro(MarcasCarro.RENAULT);
		} else if (marca.toUpperCase() == "CHEVROLET") {
			precioMaximo = vendedor.precioMaximoCarro(MarcasCarro.CHEVROLET);
		} else if (marca.toUpperCase() == "TOYOTA") {
			precioMaximo = vendedor.precioMaximoCarro(MarcasCarro.TOYOTA);
		} else if (marca.toUpperCase() == "KIA") {
			precioMaximo = vendedor.precioMaximoCarro(MarcasCarro.KIA);
		}if (marca.toUpperCase() == "MAZDA") {
			precioMaximo = vendedor.precioMaximoCarro(MarcasCarro.MAZDA);
		}
		if (precioCliente<0) {
			System.out.println("Ese precio no es válido");
			return precioCarro(vehiculo, vendedor); //se vuelve a pedir el precio del carro
		} else if (precioCliente>precioMaximo) {
			System.out.println("El precio escogido no es aceptado por el administrador");//Revisar mensaje
			return precioCarro(vehiculo, vendedor);
		} else {
			return precioCliente;
		}
		}

	
	private void revisionTaller(Vehiculo vehiculo) {
		
	}

}