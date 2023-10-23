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

/**
 * Clase ComprarCarro
 * 
 * Permite que el cliente venda un carro al parqueadero.
 *
 */
public class ComprarCarro extends Funcionalidad {
	@Override
	public void ejecutar() {
		//Funcionalidad Comprar un carro
		System.out.println("Vender carro");
		//Se pide al cliente ingresar la cédula para verificar su registro
		/**
		 * Se pide al cliente ingresar la cédula, con este dato se utiliza el método
		 * buscarORegistrar que se encuentra en funcionalidad, el cual verifica si un cliente
		 * se encuentra en base de datos, o por el contrario le solicita registrarse con los
		 * datos necesarios. Si decide no registrarse se finaliza la funcionalidad, y se regresa 
		 * al menú principal.
		 * 
		 */
		long cedula = Consola.pedirLong("Ingrese cédula");
		Cliente cliente = buscarORegistrarCliente(cedula);
		if (cliente == null) {
			return;
		}
		/**
		 * Se usa el método escoger vehiculo para que el cliente seleccione el 
		 * carro que desea vender al parqueadero. En caso de que no escoja ningun
		 * vehiculo, se finaliza la funcionalidad y regresa al menú principal.
		 */
		Vehiculo vehiculo = escogerVehiculo(cliente);
		if (vehiculo == null) {
			return;
		}
		
		/**
		 * Para proceder con la venta, se le pide al cliente seleccionar uno de los 
		 * vendedores disponibles del parqueadero, por medio de una lista con sus nombres.
		 * Al escoger un vendedor, este se presentará con su nombre.
		 */
		List<Empleado> vendedores = new ArrayList<>(parqueadero.getEmpleados().stream().filter(empleado -> "Vendedor".equals(empleado.getCargo())).collect(Collectors.toList()));
		List<String> nombresVendedores = new ArrayList<>(vendedores.stream().map(Empleado::getNombre).toList());
		
		int escogerVendedor = Consola.pedirEleccion("Escoja el vendedor de su preferencia", nombresVendedores);
		Empleado vendedor = vendedores.get(escogerVendedor);
		
		System.out.printf("Hola, mi nombre es %s y voy a atenderlo.%n", vendedor.getNombre());
		
		/**
		 * Se utiliza el método precioMáximo para hallar el valor máximo que el parqueadero
		 * pagará por una marca específica de carro. Es decir, el precio de un carro de esa
		 * marca en excelente estado o uno nuevo.
		 */
		long precioMaximo = precioMaximo(vehiculo, vendedor);
		
		/**
		 * Se le pide al cliente el precio por el que desea vender el carro, y se verifica
		 * este precio por medio del método precioCarro.
		 */
		long precioCliente = precioCarro(precioMaximo);
		
		/**
		 * Para continuar con la venta, el carro debe ser revisado en el taller por un mecánico.
		 * Se le permite al cliente escoger un mecánico de su preferencia, por medio de una
		 * lista con los nombres de los empleados mecánicos disponibles.
		 */
		System.out.println("Su carro será revisado en el taller y se le dará una oferta de compra.");
		
		List<Empleado> mecanicosVenta = new ArrayList<>(parqueadero.getEmpleados().stream().filter(empleado -> "Mecanico".equals(empleado.getCargo())).collect(Collectors.toList()));
		List<String> nombresMecanicosVenta = new ArrayList<>(mecanicosVenta.stream().map(Empleado::getNombre).toList());
		
		int escogerMecanicoVenta = Consola.pedirEleccion("Escoja el mecánico de su preferencia", nombresMecanicosVenta);
		Empleado mecanicoVenta = mecanicosVenta.get(escogerMecanicoVenta);
		
		System.out.printf("Hola, mi nombre es %s y voy a atenderlo.%n", mecanicoVenta.getNombre());
		
		/**
		 * Por medio del método revisarVehiculo, que se encuentra en la clase Empleado, 
		 * el mecánico escogido realiza una revisión general del carro, y devuelve una lista
		 * con los productos que estan en mal estado y necesitan ser cambiados o reparados. 
		 */
		List<Producto> productosMalos = mecanicoVenta.revisarVehiculo(vehiculo);
		
		/**
		 * El parqueadero genera una cotizacion del carro por medio del método 
		 * cotizacionParqueadero, el cual, segun el precio máximo generado 
		 * anteriormente, y los productos que necesitan reparacion, 
		 * entrega un precio por parte del parqueadero.
		 */
		long precioParqueadero = cotizacionParqueadero(productosMalos, mecanicoVenta, precioMaximo, cliente);
		
		/**
		 * Se comparan el precio del parqueadero con el precio del cliente mediante 
		 * el método precioFinal, y se devuelve el precio menor como oferta final.
		 */
		long precioFinal = precioFinal(precioCliente, precioParqueadero);
		
		/**
		 * Una de las opciones entregadas al cliente será cambiar el carro
		 * que desea vender por uno que se encuentre disponible para la venta en 
		 * el parqueadero. Para esto, se genera una lista de carros disponibles, 
		 * tales que su precio sea menor o igual al precio acordado en precioFinal.
		 * Además, se crea una lista con las placas de estos carros, para enseñarlas
		 * al usuario si lo escoge.
		 */
		List<Carro> carrosDisponibles = new ArrayList<>(vendedor.getVehiculosVenta().stream().filter(carro -> carro.getPrecioVenta()<=precioFinal).collect(Collectors.toList()));
		List<String> placasCarrosDisponibles = new ArrayList<>(carrosDisponibles.stream().map(Vehiculo::getPlaca).toList());
		
		/**
		 * Se le presenta al cliente tres opciones, vender el carro por el precio que acordó
		 * el parqueadero, cambiar su carro por otro en la lista de carros disponibles, o finalizar
		 * la funcionalidad.
		 */
		List<String> opcionesVenta = new ArrayList<>();
		opcionesVenta.add("Vender el vehiculo por " + precioFinal);
		opcionesVenta.add("Intercambiar su carro por uno disponible para la venta en el rango de precio.");
		int opcionVenta = Consola.pedirEleccion("Su carro ha sido revisado en el taller, y podrá escoger entre las siguientes ofertas: ", opcionesVenta);
		
		/**
		 * Si se escoge la primer opción, venta de vehiculo por el precio final, 
		 * se utiliza el método ventaPorDinero, en cascontrario se utiliza el
		 * método cambioDeCarro, y al finalizar el método se acaba la funcionalidad.
		 * Se informa al cliente que se generó su factura y se vuelve al 
		 * menú principal.
		 */
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
				MarcasCarro marca = vehiculoRegistrado.getMarca();
				if (vehiculoRegistrado != null && (marca.equals(MarcasCarro.TOYOTA) || marca.equals(MarcasCarro.RENAULT)|| marca.equals(MarcasCarro.TOYOTA) || marca.equals(MarcasCarro.KIA) || marca.equals(MarcasCarro.MAZDA))) {
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
		List<Vehiculo> carrosMarcas = new ArrayList<>(carros.stream().filter(vehiculo -> (((Carro)vehiculo).getMarca().equals(MarcasCarro.TOYOTA) || ((Carro)vehiculo).getMarca().equals(MarcasCarro.RENAULT)|| ((Carro)vehiculo).getMarca().equals(MarcasCarro.CHEVROLET) || ((Carro)vehiculo).getMarca().equals(MarcasCarro.KIA) || ((Carro)vehiculo).getMarca().equals(MarcasCarro.MAZDA))).collect(Collectors.toList()));
		if (carrosMarcas.size() == 0) {
			boolean e = Consola.pedirBoolean("Usted no tiene vehiculos registrados con las marcas aceptadas por el parqueadero para venta, ¿desea registrar e ingresar su vehiculo?");
			if (e) {
				Vehiculo vehiculoRegistrado = registrarVehiculo(cliente);
				MarcasCarro marca = vehiculoRegistrado.getMarca();
				if (vehiculoRegistrado != null && (marca.equals(MarcasCarro.TOYOTA) || marca.equals(MarcasCarro.RENAULT)|| marca.equals(MarcasCarro.CHEVROLET) || marca.equals(MarcasCarro.KIA) || marca.equals(MarcasCarro.MAZDA))) {
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
		
		if (opcionCarroVenta == carrosMarcas.size()) { //si desea volver se retorna null
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
	
	private long precioCarro(long precioMaximo) { //TODO: se podria revisar el tipo del parametro
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
	private long precioMaximo(Vehiculo vehiculo, Empleado vendedor) { //TODO:el atributo marca es un string, que pasa cuando este no hace match con ninguna marca del enum?
		MarcasCarro marca = ((Carro)vehiculo).getMarca();
		long precioMaximo = 0;
		if (marca.equals(MarcasCarro.RENAULT)) {
			precioMaximo = vendedor.precioMaximoCarro(MarcasCarro.RENAULT);
		} else if (marca.equals(MarcasCarro.CHEVROLET)) {
			precioMaximo = vendedor.precioMaximoCarro(MarcasCarro.CHEVROLET);
		} else if (marca.equals(MarcasCarro.TOYOTA)) {
			precioMaximo = vendedor.precioMaximoCarro(MarcasCarro.TOYOTA);
		} else if (marca.equals(MarcasCarro.KIA)) {
			precioMaximo = vendedor.precioMaximoCarro(MarcasCarro.KIA);
		}if (marca.equals(MarcasCarro.MAZDA)) {
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
			System.out.println("Se ha intercambiado el carro por el vehiculo escogido, su excedente es de "+excedente+" y será añadido a su Factura."); //problema con factura para guardar los valores de los servicios
			cliente.getFactura().agregarServicio("Intercambio de carro", 1);
		}
		
	}
}