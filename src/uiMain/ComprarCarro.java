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
		double precioMaximo = precioMaximo(vehiculo, vendedor);
		
		/**
		 * Se le pide al cliente el precio por el que desea vender el carro, y se verifica
		 * este precio por medio del método precioCarro.
		 */
		double precioCliente = precioCarro(precioMaximo);
		
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
		double precioParqueadero = cotizacionParqueadero(productosMalos, mecanicoVenta, precioMaximo, cliente);
		
		/**
		 * Se comparan el precio del parqueadero con el precio del cliente mediante 
		 * el método precioFinal, y se devuelve el precio menor como oferta final.
		 */
		double precioFinal = precioFinal(precioCliente, precioParqueadero);
		
		/**
		 * Una de las opciones entregadas al cliente será cambiar el carro
		 * que desea vender por uno que se encuentre disponible para la venta en 
		 * el parqueadero. Para esto, se genera una lista de carros disponibles, 
		 * tales que su precio sea menor o igual al precio acordado en precioFinal.
		 * Además, se crea una lista con las placas de estos carros, para enseñarlas
		 * al usuario si lo escoge.
		 */
		List<Carro> carrosDisponibles = new ArrayList<>(Empleado.getVehiculosVenta().stream().filter(carro -> carro.getPrecioVenta()<=precioFinal).collect(Collectors.toList()));
		List<String> placasCarrosDisponibles = new ArrayList<>(carrosDisponibles.stream().map(Carro::toString).toList());
		
		/**
		 * Se le presenta al cliente tres opciones, vender el carro por el precio que acordó
		 * el parqueadero, cambiar su carro por otro en la lista de carros disponibles, o finalizar
		 * la funcionalidad.
		 */
		if (placasCarrosDisponibles.size()==0) {
			List<String> opcionesVenta = new ArrayList<>();
			opcionesVenta.add("Vender el vehiculo por " + Math.round(precioFinal));
			int opcionVenta = Consola.pedirEleccion("Su carro ha sido revisado en el taller, y el parqueadero le presenta la siguiente oferta: ", opcionesVenta);
			ventaPorDinero(cliente, vehiculo, vendedor, mecanicoVenta, precioFinal, productosMalos);
			System.out.println("Se ha generado su factura y ha finalizado la venta de vehiculo. ¡Adios!");
			return;
		} else {
			List<String> opcionesVenta = new ArrayList<>();
			opcionesVenta.add("Vender el vehiculo por " + Math.round(precioFinal));
			opcionesVenta.add("Intercambiar su carro por uno disponible para la venta en el rango de precio.");
			int opcionVenta = Consola.pedirEleccion("Su carro ha sido revisado en el taller, y podrá escoger entre las siguientes ofertas: ", opcionesVenta);
			if (opcionVenta == 0) {
				ventaPorDinero(cliente, vehiculo, vendedor, mecanicoVenta, precioFinal, productosMalos);
				System.out.println("Se ha generado su factura y ha finalizado la venta de vehiculo. ¡Adios!");
				return;
			} else {
				cambioDeCarro(vehiculo, placasCarrosDisponibles, vendedor, mecanicoVenta, productosMalos, precioFinal, cliente, carrosDisponibles);
				System.out.println("Se ha generado su factura por cambio de carro. ¡Vuelva pronto!");
				return;
			}
		}
		
		/**
		 * Si se escoge la primer opción, venta de vehiculo por el precio final, 
		 * se utiliza el método ventaPorDinero, en cascontrario se utiliza el
		 * método cambioDeCarro, y al finalizar el método se acaba la funcionalidad.
		 * Se informa al cliente que se generó su factura y se vuelve al 
		 * menú principal.
		 */
	
		
		
		
	}
	/** En este Método se solicita que entregue los datos del cliente que va a hacer uso de la funcionalidad, 
	 * se busca que efectivamente cuente con vehiculos registrados de lo contrario, se le pide que registre un vehiculo
	 * en caso de que si tenga vehiculos registrados  se toma la lista de vehiculos registrados y se filtran las marcas aceptadas por el parqueadero para su compra continue
	 * si el cliente no tiene ningun carro en su lista que cumpla con los requisitos que exige el parqueadero, se le pide que registre almenos un carro que lo cumpla
	 * 
	 * se continua mostrandole una lista de los carros del cliente disponibles para vender y se le pide que escoga uno el cual este registrado 
	 * y verificado por el parqueadero, finalmente se retorna el vehiculo a la venta
	 */
	
	private Vehiculo escogerVehiculo(Cliente cliente) {
		if (cliente.getVehiculos().isEmpty()) {
			boolean e = Consola.pedirBoolean("Usted no tiene vehiculos registrados para vender, ¿desea registrar e ingresar su vehiculo?");
			if (e) {
				Vehiculo vehiculoRegistrado = registrarVehiculo(cliente);
				if (vehiculoRegistrado == null) {
					return null;
				}
				MarcasCarro marca = MarcasCarro.valueOf(vehiculoRegistrado.getMarca());
				if ((marca.equals(MarcasCarro.TOYOTA) || marca.equals(MarcasCarro.RENAULT) || marca.equals(MarcasCarro.KIA) || marca.equals(MarcasCarro.MAZDA))) {
					ingresarVehiculo(cliente, vehiculoRegistrado);
				}
				else if (vehiculoRegistrado != null){
					System.out.println("La marca seleccionada no es válida para venta de carro");
				}
			} else {
				System.out.println("Ha decidido no registrar su vehiculo");	
				return null;
			}
		}
		// lista para obtener solo los carros
		List<Vehiculo> carros = new ArrayList<>(cliente.getVehiculos().stream().filter(vehiculo -> vehiculo instanceof Carro).collect(Collectors.toList()));
		List<Vehiculo> carrosMarcas = new ArrayList<>(carros.stream().filter(vehiculo -> (vehiculo.getMarca().equals(MarcasCarro.TOYOTA.name()) || vehiculo.getMarca().equals(MarcasCarro.RENAULT.name())|| vehiculo.getMarca().equals(MarcasCarro.CHEVROLET.name()) || ((Carro)vehiculo).getMarca().equals(MarcasCarro.KIA.name()) || vehiculo.getMarca().equals(MarcasCarro.MAZDA.name()))).collect(Collectors.toList()));
		if (carrosMarcas.isEmpty()) {
			boolean e = Consola.pedirBoolean("Usted no tiene vehiculos registrados con las marcas aceptadas por el parqueadero para venta, ¿desea registrar e ingresar su vehiculo?");
			if (e) {
				Vehiculo vehiculoRegistrado = registrarVehiculo(cliente);
				MarcasCarro marca = MarcasCarro.valueOf(vehiculoRegistrado.getMarca());
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
		
		int opcionCarroVenta = Consola.pedirEleccion("Escoja el carro que desea vender ", placasCarros);
		
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
	
	/**
	 * En el método precio carro se pide un long, el cuál es el precio máximo que el parqueadero
	 * está dispuesto a pagar por un carro. Se le solicita al cliente ingresar su oferta para la venta 
	 * del carro, y se verifican tres casos:
	 * 
	 * En el primero el cliente ingresa un numero negativo, por lo que se informa que el precio no es
	 * válido y se vuelve a iniciar el método.
	 * 
	 * En el segundo caso, el precio ingresado por el cliente supera el precio máximo, por lo que
	 * se le solicita al cliente ingresar un precio más bajo.
	 * 
	 * En el tercer caso, el precio ingresado cumple con los requisitos y se devuelve como la 
	 * oferta del cliente
	 * 
	 */
	private double precioCarro(double precioMaximo) { //TODO: se podria revisar el tipo del parametro
		double precioCliente = Consola.pedirDouble("Escriba el precio por el que desea vender su vehículo");
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
	
	/**
	 * Para el método precioMáximo se solicita el vehiculo que el cliente desea vender y el 
	 * empleado de tipo vendedor que está atendiendo al cliente.
	 * 
	 * Se verifica cual es la marca del carro, comparando con los objetos de tipo enum, y
	 * según su marca el vendedor utiliza el método precioMáximoCarro, el cual encuentra 
	 * el precio de un carro en perfecto estado. 
	 * 
	 * El método precioMáximoCarro toma las partes del carro y el valor que maneja el parqueadero
	 * para cada una, y lo multiplica por un valor asignado a cada marca según su calidad y/o 
	 * precio normal. El método suma los resultados por cada producto en una variable 
	 * y entrega el resultado al finalizar.
	 * 
	 * precioMaximo entrega el valor que retorna el vendedor por medio del método anterior.
	 * 
	 */
	private double precioMaximo(Vehiculo vehiculo, Empleado vendedor) { //TODO:el atributo marca es un string, que pasa cuando este no hace match con ninguna marca del enum?
																	//En escogerVehiculo se verifica que este cumpla con alguna de las marcas.
		MarcasCarro marca = MarcasCarro.valueOf(((Carro)vehiculo).getMarca());
		double precioMaximo = 0;
		if (marca.equals(MarcasCarro.RENAULT)) {
			precioMaximo = MarcasCarro.RENAULT.getPrecioMaximo();
		} else if (marca.equals(MarcasCarro.CHEVROLET)) {
			precioMaximo = MarcasCarro.RENAULT.getPrecioMaximo();
		} else if (marca.equals(MarcasCarro.TOYOTA)) {
			precioMaximo = MarcasCarro.RENAULT.getPrecioMaximo();
		} else if (marca.equals(MarcasCarro.KIA)) {
			precioMaximo = MarcasCarro.RENAULT.getPrecioMaximo();
		} else if (marca.equals(MarcasCarro.MAZDA)) {
			precioMaximo = MarcasCarro.RENAULT.getPrecioMaximo();
		}
		return precioMaximo;
	}
	
	/** En este método el parqueadero la un precio de compra al cliente correspondiente a los cambios de repuestos y el servicio del mecánico, 
	 * entonces, al cliente se le asigna una factura por el servicio e igualmente por cada producto dañado y cambiado se le resta al precio Maximo del vehiculo 
	 *el cual será la cotizacion final por parte del parqueadero 
	 * */
	private double cotizacionParqueadero(List<Producto> productosMalos, Empleado mecanico, double precioMaximo, Cliente cliente) {
		cliente.getFactura().agregarServicio("Revision general", 1);
		for (Producto productoMalo: productosMalos) {
			precioMaximo -= Almacen.cotizarProducto(productoMalo.getTipo());
		}
		mecanico.setServiciosRealizados(mecanico.getServiciosRealizados() + 1);
		return precioMaximo;
	}
	/**Finalmente en el Método precioFinal se le entrega el precio del Cliente y precio del Parqueadero, 
	 * estos se comparan y el menor precio entre ambas opciones se retornara como el precio final del carro 
	 *  */
	
	private double precioFinal(double precioCliente, double precioParqueadero) {
		if (precioCliente<=precioParqueadero) {
			return precioCliente;
		} else {
			return precioParqueadero;
		}
	}
	/** Al cliente se le entregan dos opciones para que el carro sea comprado por el parqueadero, ya sea dinero o intercamabiar el carro por uno del rango en precios
	 *  */
	/** En el caso del Método ventaPorDinero se le entrega los datos del cliente, el vehiculo que será comprado, el mecánico del taller y la lista de los productos malos
	 * entonces al vehiculo se le asigna el precio final de Venta al carro , se envía a reparar el carro al taller 
	 * y en el parqueadero guardamos Almacen con sus productos para realizar un for
	    en el que el mecánico cambia cada uno de los productos en mal estado por un producto bueno de cada tipo */
	
	private void ventaPorDinero(Cliente cliente, Vehiculo vehiculo, Empleado vendedor, Empleado mecanico, double precioFinal, List<Producto> productosMalos) {
		
		((Carro)vehiculo).setPrecioVenta(precioFinal);

		System.out.println("El vehiculo está siendo reparado en el taller");
		Almacen almacen = parqueadero.getAlmacen();
		for (Producto productoMalo: productosMalos) {
			if (almacen.existeProducto(productoMalo.getTipo())) {
				Producto productoBueno = almacen.conseguirProducto(productoMalo.getTipo());
				mecanico.cambiar(productoMalo, productoBueno, vehiculo);
			}
		}
		mecanico.setServiciosRealizados(mecanico.getServiciosRealizados() + 1);
		/*igualmente cada servicio realizado se le agrega a mecánico */
		Empleado.agregarVehiculosVenta((Carro)vehiculo);
		int indiceVehiculo = cliente.getVehiculos().indexOf(vehiculo);
		cliente.getVehiculos().remove(indiceVehiculo);
		vehiculo.setDueno(null);
		
		/** añadimos el carro a la lista de carros disponibles para la venta 
		 y cambiamos el dueño de carro por el parqueadero*/
		
		// para finalmente generar la factura
		
		vendedor.setServiciosRealizados(vendedor.getServiciosRealizados() + 1);
		//al vendedor tambien le agregamos cada uno de los servicios realizados
	}
	
	/**
	 * El método cambioDeCarro permite al cliente cambiar el carro que desea vender por uno
	 * que se encuentre en la lista de carros disponibles en el parqueadero, másel excedente
	 * por el precio acordado.
	 * 
	 * Para este método se piden como argumentos el vehiculo que el cliente desea vender, una lista
	 * con las placas de los carros disponibles para la venta y la lista de los objetos de tipo Carro 
	 * que estan disponobles, el vendedor escogido por el cliente, el precio final acordado 
	 * anteriormente por el parqueadero, el mecanico que va a reparar el vehiculo cuando el parqueadero
	 * lo compre, la lista de productos que se deben reparar, y el cliente que realiza la transaccion.
	 * 
	 * Se inicia por crear un atributo excedente, y le pide al usuario escoger el carro de su preferencia 
	 * entre los que se encuentran disponibles en el parqueadero. Si el cliente lo desea, se regresa a la 
	 * opcion anterior.
	 * 
	 * En caso de que el cliente escoja un carro de la lista, se saca el carro de la lista de disponibles, 
	 * se ingresa el nombre de su nuevo dueño, se calcula el excedente entre el precio del carro y el precio 
	 * acordado por el parqueadero, y se le quita su precio de venta. Se le agrega un servicio al vendedor.
	 * 
	 * Luego de esto, se toma el vehiculo que vendió el cliente al parqueadero y se envía al taller para que
	 * sea reparado por el mecanico y luego vendido por el parqueadero. Por cada producto malo que el mecanico
	 * encontróen su revision, se va a realizar un cambio con el método cambiar, por un producto bueno sacado
	 * del almacen. Se le añadeun servicio al mecánico.
	 * 
	 * Para finalizar, se pone null en el atributo dueno del carro que se vendió al parqueadero, y se genera la
	 * factura para el cliente.
	 */
	private void cambioDeCarro(Vehiculo vehiculo, List<String> placasCarrosDisponibles, Empleado vendedor, Empleado mecanico, List<Producto> productosMalos, double precioFinal, Cliente cliente, List<Carro> carrosDisponibles) {
		placasCarrosDisponibles.add("Volver al menú anterior");
		double excedente = 0;
		int escogerOpcion = Consola.pedirEleccion("Seleccione el carro de su preferencia: ", placasCarrosDisponibles);
		if (escogerOpcion == placasCarrosDisponibles.size()-1) {
			return; //Revisar
		} else {
			//Se selecciona el carro nuevo para el intercambio
			Carro carroNuevo = carrosDisponibles.get(escogerOpcion);
			carroNuevo.setDueno(cliente);
			excedente = precioFinal - carroNuevo.getPrecioVenta();
			carroNuevo.setPrecioVenta(0);
			Empleado.getVehiculosVenta().remove(escogerOpcion);
			vendedor.setServiciosRealizados(vendedor.getServiciosRealizados() + 1);
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
			int indiceVehiculo = cliente.getVehiculos().indexOf(vehiculo);
			cliente.getVehiculos().remove(indiceVehiculo);
			vehiculo.setDueno(null);
			Empleado.agregarVehiculosVenta((Carro)vehiculo);
			System.out.println("Se ha intercambiado el carro por el vehiculo escogido, su excedente es de "+excedente+" y será añadido a su Factura."); //problema con factura para guardar los valores de los servicios
		}
		
	}
}