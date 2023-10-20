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
import java.util.Arrays;

public class Taller extends Funcionalidad {
	@Override
	public void ejecutar() {
		//funcionalidad Taller
		System.out.println("Bienvenido al Taller");
		
		//se solicita la cedula y se busca el cliente en la base de datos
		long cedula = Consola.pedirLong("Ingrese su cédula");
		
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
		
		//lista con todos los mecanicos del taller
		List<Empleado> mecanicos = new ArrayList<>(parqueadero.getEmpleados().stream().filter(empleado -> "Mecanico".equals(empleado.getCargo())).collect(Collectors.toList()));
		//lista con  los nombres de los mecanicos
		List<String> nombresMecanicos = new ArrayList<>(mecanicos.stream().map(Empleado::getNombre).toList());
		
		// se pide escojer un mecanico antes de ofrecer los servicios 
		int mecanicoEleccion  = Consola.pedirEleccion("Escoja el mecanico de su preferencia", nombresMecanicos);
		Empleado mecanico = mecanicos.get(mecanicoEleccion);
		
		System.out.printf("Hola, mi nombre es %s y voy a atenderlo.%n", mecanico.getNombre());
		
		// luego de escoger el mecanico se procede a mostrar los servicios
		List<String> serviciosTaller = new ArrayList<>();
		serviciosTaller = List.of( // SE AGREGAN LOS SERVICIOS BASICOS Y SE COMPRUEBA EL TIPO DE VEHICULO
				"Revisión general", 
				"Cambio de motor",
				"Cambio de transmision",
				"Cambio de acelerador",
				"Cambio de freno",
				"Cambio de bateria",
				"Cambio de depositos",
				"Cambio de llantas",
				"Cambio de rines");
		
		// segun el tipo de vehiculo se agregan los servicios particulares
		if (vehiculo instanceof Carro) {
			String[] serviciosCarro = {"Cambio de pedal", "Cambio de amortiguadores"}; serviciosTaller.addAll(Arrays.asList(serviciosCarro));
		}
		else {
			String[] serviciosMoto = {"Cambio de cadena", "Cambio de pedales", "Cambio de amortiguador"}; serviciosTaller.addAll(Arrays.asList(serviciosMoto));
		}
		
		int servicioEscogido = Consola.pedirEleccion("¿Que le vamos a hacer hoy a su vehiculo?", serviciosTaller);
		switch(servicioEscogido){
			case 0 -> revisionGeneral(vehiculo, mecanico);
			case 1 -> cambioDe(TipoProducto.MOTOR);
			//TODO:continuar
		}
		
	}
	
	// metodo que muestra los vehiculos de un cliente y retorna el escogido
	private Vehiculo escogerVehiculo(Cliente cliente) {
		
		//verificar si el cliente tiene vehiculos registrados, en caso contrario pedir registrar e ingresar el vehiculo
		if (cliente.getVehiculos().size() == 0) {
			boolean e = Consola.pedirBoolean("Ups, usted no tiene vehiculos registrados para acceder al taller, ¿desea registrar e ingresar su vehiculo?");
			if (e) {
				Vehiculo vehiculoRegistrado = registrarVehiculo(cliente);
				if (vehiculoRegistrado != null) {
					ingresarVehiculo(cliente, vehiculoRegistrado);
				}
				} else {
					return null;
				}
			}
			else {
				return null; // cuando se decide que no se retorna null
			}
	
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

	//metodo para consefuir desde almacen los productos solicitados
	private List<Producto> conseguirRepuestos(Cliente cliente, List<Producto> productos) {
		// almacen
		Almacen almacen = parqueadero.getAlmacen();
		List<Producto> productosVendidos = new ArrayList<>();
		
		for (Producto producto : productos) {
			if (almacen.existeProducto(producto.getTipo())) {
				Producto nProducto = almacen.conseguirProducto(producto.getTipo());
				productosVendidos.add(nProducto);
				cliente.getFactura().agregarProducto(producto, 1);
			} // revisar que puede pasar si no hay un producto
		}
		return productosVendidos;
	}
	
	
	//metodo que emula la venta de un repuesto, como ya se sabe desde Taller que producto se debe comprar, 
	//se procede directamente con la compra de este (no damos opcion de salir para asegurar que siempre se compre un producto)
	private Producto ventaRespuesto(Cliente cliente, TipoProducto tipoProducto) {
		// almacen
		Almacen almacen = parqueadero.getAlmacen();
		System.out.println("Bienvenido al almacen");
		
		//lista de todos los vendedores del parqueadero
		List<Empleado> vendedores = new ArrayList<>(parqueadero.getEmpleados().stream().filter(empleado -> "Vendedor".equals(empleado.getCargo())).collect(Collectors.toList()));
		//lista con los nombres de los vendedores
		List<String> nombresVendedores = new ArrayList<>(vendedores.stream().map(Empleado::getNombre).toList());
		
		
		// se pide escoger un vendedor
		int vendedorEleccion = Consola.pedirEleccion("Seleccione su vendedor de preferencia", nombresVendedores);
		Empleado vendedor = vendedores.get(vendedorEleccion);
		
		// se verifica si hay instancias del producto en el inventario 
		if (almacen.existeProducto(tipoProducto)) { // si si hay se procede a conseguir este producto, a actualizar informaciones y a retornarlo
			Producto producto = almacen.conseguirProducto(tipoProducto);
			cliente.getFactura().agregarProducto(producto, 1);
			vendedor.setServiciosRealizados(vendedor.getServiciosRealizados() + 1);
			return producto;
		}
		return null; //si no hay se retorna null
	}
	
	//metodo para revisar el vehiculo y arreglar todos los componentes dañados
	private void revisionGeneral(Vehiculo vehiculo, Empleado mecanico) {
		// como se ha comprobado que en la instancia de un Carro o Moto simpre hay almenos un componente en mal estado
		// no se verifica esto, mirar si se debe implementar un catch
		List<Producto> componentesDañados = mecanico.revisarVehiculo(vehiculo);
		
		// verificar que la lista no este vaciay realizar los cambios correspondientes
		if (!componentesDañados.isEmpty()) {
			List<Producto> nuevosComponentes = conseguirRepuestos(vehiculo.getDueno(), componentesDañados); // componentes para realizar el cambio
			for (int i = 0; i < componentesDañados.size(); i++) {
				mecanico.cambiar(componentesDañados.get(i), nuevosComponentes.get(i), vehiculo);
				mecanico.setServiciosRealizados(mecanico.getServiciosRealizados() + 1); //TODO: mirar que mas hacer
			}
		}
		else {
			System.out.println("Su vehiculo no tiene imperfecciones");
		}
		
		//añadir el servicio a la factura del cliente
		vehiculo.getDueno().getFactura().agregarServicio("Revision general");
		System.out.println("Listo. Como nuevo :)");
	}
	
	private void cambioDe(TipoProducto producto) {
		//TODO: terminar
	}
}
	
	