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
import gestorAplicacion.parqueadero.Producto;
import gestorAplicacion.parqueadero.Almacen;

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
		
		//Se crea el precio máximo para esa marca de carro
		long precioMaximo = precioMaximo(vehiculo, vendedor);
		
		//Se le pide el precio por el que desea vender el carro
		long precioCliente = precioCarro(precioMaximo);
		
		System.out.println("Su carro será revisado en el taller y se le dará una oferta de compra.");
		
		//Se lleva el vehículo al taller
		//Se muestran las opciones de mecanico que existen 
		List<Empleado> mecanicosVenta = new ArrayList<>(parqueadero.getEmpleados().stream().filter(empleado -> "Mecanico".equals(empleado.getCargo())).collect(Collectors.toList()));
		//lista con  los nombres de los mecanicos
		List<String> nombresMecanicosVenta = new ArrayList<>(mecanicosVenta.stream().map(Empleado::getNombre).toList());
		
		int escogerMecanicoVenta = Consola.pedirEleccion("Escoja el mecánico de su preferencia", nombresMecanicosVenta);
		Empleado mecanicoVenta = mecanicosVenta.get(escogerMecanicoVenta);
		
		System.out.printf("Hola, mi nombre es %s y voy a atenderlo.%n", mecanicoVenta.getNombre());
		
		//Se crea la lista de productos que necesitan reparacion
		List<Producto> productosMalos = mecanicoVenta.revisarVehiculo(vehiculo);
		
		//Se crea la cotizacion del parqueadero
		long precioParqueadero = cotizacionParqueadero(productosMalos, mecanicoVenta, precioMaximo, cliente);
		
		//Se comparan los precios del cliente y el parqueadero y se deja como precio final el menor
		long precioFinal = precioFinal(precioCliente, precioParqueadero);
		
		//Se crea una lista con todos los carros que tengan menor o igual precio al precio final
		List<Carro> carrosDisponibles = new ArrayList<>(vendedor.getVehiculosVenta().stream().filter(carro -> carro.getPrecioVenta()<=precioFinal).collect(Collectors.toList()));
		List<String> placasCarrosDisponibles = new ArrayList<>(carrosDisponibles.stream().map(Vehiculo::getPlaca).toList());
		
		//Se le permite al usuario escoger ente la venta del carro por dinero o intercabio por otro carro.
		List<String> opcionesVenta = new ArrayList<>();
		opcionesVenta.add("Vender el vehiculo por " + precioFinal);
		opcionesVenta.add("Intercambiar su carro por uno disponible para la venta en el rango de precio.");
		int opcionVenta = Consola.pedirEleccion("Su carro ha sido revisado en el taller, y podrá escoger entre las siguientes ofertas: ", opcionesVenta);
		
		if (opcionVenta == 1) {
			ventaPorDinero(cliente, vehiculo, vendedor, mecanicoVenta, precioFinal, productosMalos);
			System.out.println("Se ha generado su factura y ha finalizado la venta de vehiculo. ¡Adios!");
			return;
		} else {
			cambioDeCarro(vehiculo, placasCarrosDisponibles, vendedor, precioFinal, opcionVenta, cliente, carrosDisponibles);
			System.out.println("Se ha generado su factura por cambio de carro. ¡Vuelva pronto!");
			return;
		}
		
		
		
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
		
		int opcionCarroVenta = Consola.pedirEleccion("Escoja el vehículo que desea vender ", placasCarros);
		
		//Se verifica si desea volver al menú principal, o si el vehiculo se encuentra enparqueadero
		
		if (opcionCarroVenta == carrosMarcas.size() - 1) { //si desea volver se retorna null
			return null;
		}
		else { //cuando se escoge un vehiculo
			Vehiculo vehiculo = carrosMarcas.get(opcionCarroVenta);
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
	
	private long precioCarro(long precioMaximo) {
		long precioCliente = Consola.pedirLong("Escriba el precio por el que desea vender su vehículo");
		if (precioCliente<0) {
			System.out.println("Ese precio no es válido");
			return precioCarro(precioMaximo); //se vuelve a pedir el precio del carro
		} else if (precioCliente>precioMaximo) {
			System.out.println("El precio escogido no es aceptado por el administrador");//Revisar mensaje
			return precioCarro(precioMaximo);
		} else {
			return precioCliente;
		}
		}
	private long precioMaximo(Vehiculo vehiculo, Empleado vendedor) {
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
		return precioMaximo;
	}
	private long cotizacionParqueadero(List<Producto> productosMalos, Empleado mecanico, long precioMaximo, Cliente cliente) {
		cliente.getFactura().agregarServicio("Revision general", 1);
		for (Producto productoMalo: productosMalos) {
			precioMaximo -= productoMalo.getPrecio();
		}
		mecanico.setServiciosRealizados(mecanico.getServiciosRealizados() + 1);
		return precioMaximo;
	}
	
	private long precioFinal(long precioCliente, long precioParqueadero) {
		if (precioCliente<=precioParqueadero) {
			return precioCliente;
		} else {
			return precioParqueadero;
		}
	}

	private void ventaPorDinero(Cliente cliente, Vehiculo vehiculo, Empleado vendedor, Empleado mecanico, long precioFinal, List<Producto> productosMalos) {
		//Se le asigna el precio de Venta al carro
		((Carro)vehiculo).setPrecioVenta(precioFinal);
		
		//Se envia a reparar el carro
		System.out.println("El vehiculo está siendo reparado en el taller");
		Almacen almacen = parqueadero.getAlmacen();
		for (Producto productoMalo: productosMalos) {
			if (almacen.existeProducto(productoMalo.getTipo())) {
				Producto productoBueno = almacen.conseguirProducto(productoMalo.getTipo());
				mecanico.cambiar(productoMalo, productoBueno, vehiculo);
			}
		}
		mecanico.setServiciosRealizados(mecanico.getServiciosRealizados() + 1);
		
		//Se añade el carro a la lista de carros disponibles para la venta
		vendedor.agregarVehiculosVenta((Carro)vehiculo);
		
		//Se cambia el dueño del vehiculo por el parqueadero
		vehiculo.setDueno(null);
		
		//Se genera la factura
		cliente.getFactura().agregarServicio("Venta de carro", 1);
		
		vendedor.setServiciosRealizados(vendedor.getServiciosRealizados() + 1);
	}
	
	private void cambioDeCarro(Vehiculo vehiculo, List<String> placasCarrosDisponibles, Empleado vendedor, long precioFinal, int opcionVenta, Cliente cliente, List<Carro> carrosDisponibles) {
		placasCarrosDisponibles.add("Volver al menú anterior");
		long excedente = 0;
		int escogerOpcion = Consola.pedirEleccion("Seleccione el carro de su preferencia: ", placasCarrosDisponibles);
		if (escogerOpcion == placasCarrosDisponibles.size()-1) {
			return; //Revisar
		} else {
			//Se selecciona el carro nuevo para el intercambio
			Carro carroNuevo = carrosDisponibles.get(escogerOpcion);
			carroNuevo.setDueno(cliente);
			excedente = precioFinal - carroNuevo.getPrecioVenta();
			carroNuevo.setPrecioVenta(0);
			vendedor.getVehiculosVenta().remove(escogerOpcion);
			vendedor.setServiciosRealizados(vendedor.getServiciosRealizados() + 1);
			vehiculo.setDueno(null);
			vendedor.agregarVehiculosVenta((Carro)vehiculo);
			System.out.println("Se ha intercambiado el carro por el vehiculo escogido, su excedente es de "+excedente+" y será añadido a su cuenta.");
			cliente.getFactura().agregarServicio("Intercambio de carro", 1);
		}
		
	}
}