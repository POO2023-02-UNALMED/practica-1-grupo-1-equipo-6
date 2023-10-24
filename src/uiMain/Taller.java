/**
 * Funcionalidad del modulo: Contiene la clase Taller que ofrece servicios de mecanica a los vehiculos del parqueadero.
 * Componentes del modulo: Clase Taller.
 * Autores: Sebastián. 
 */
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

/**
 * Esta clase se encarga de ofrecer y realizar los servicios de mecanica que ofrece el parqueadero.
 */
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
		
		System.out.printf("Hola, mi nombre es %s y voy a atenderlo%n", mecanico.getNombre());
		
		// luego de escoger el mecanico se procede a mostrar los servicios
		List<String> serviciosTaller = new ArrayList<>(List.of( // SE AGREGAN LOS SERVICIOS BASICOS Y SE COMPRUEBA EL TIPO DE VEHICULO
				"Revisión general", 
				"Cambio de motor",
				"Cambio de transmision",
				"Cambio de acelerador",
				"Cambio de freno",
				"Cambio de bateria",
				"Cambio de deposito de gasolina",
				"Cambio de deposito de aceite",
				"Cambio de deposito de liquidos",
				"Cambio de llantas",
				"Cambio de rines"));
		
		// segun el tipo de vehiculo se agregan los servicios particulares
		if (vehiculo instanceof Carro) {
			String[] serviciosCarro = {"Cambio de pedal", "Cambio de amortiguadores", "Regresar al menú principal"}; serviciosTaller.addAll(Arrays.asList(serviciosCarro));
		}
		else {
			String[] serviciosMoto = {"Cambio de cadena", "Cambio de pedales", "Cambio de amortiguador", "Regresar al menú principal"}; serviciosTaller.addAll(Arrays.asList(serviciosMoto));
		}
		
		int servicioEscogido;
		do {
		servicioEscogido = Consola.pedirEleccion("¿Que le vamos a hacer a su vehiculo?", serviciosTaller);
		
		if (!serviciosTaller.get(servicioEscogido).equals("Regresar al menú principal")) {
			if (servicioEscogido == 0) {
			revisionGeneral(vehiculo, mecanico);
			}
			else {
			// se coge el string del componente que se va a cambiar
			String producto = (serviciosTaller.get(servicioEscogido)).split(" ")[(serviciosTaller.get(servicioEscogido)).split(" ").length - 1]; 
			cambioDe(producto, vehiculo, mecanico);
			}
		}
		} while (!serviciosTaller.get(servicioEscogido).equals("Regresar al menú principal"));
		System.out.println("Vuelva pronto");
	}
	
	/**
	 * Metodo que pide al cliente escoger un vehiculo entre los que tiene registrados, si no tiene vehiculos registrados se le ofrece registrarlo e ingresarlo
	 * al parqueadero
	 * @param cliente
	 * @return vehiculo
	 */
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

	//metodo para conseguir desde almacen los productos solicitados
	private List<Producto> conseguirRepuestos(Vehiculo vehiculo, List<Producto> productos) {
		// almacen
		Almacen almacen = parqueadero.getAlmacen();
		List<Producto> productosVendidos = new ArrayList<>();
		
		for (Producto producto : productos) {
			if (almacen.existeProducto(producto.getTipo())) {
				Producto nProducto = almacen.conseguirProducto(producto.getTipo());
				nProducto.setMarca(vehiculo.getMarca()); //hacer el producto de la misma marca del vehiculo
			    nProducto.setPrecio(0); // este producto ya no tendra el mismo valor comercial
				productosVendidos.add(nProducto);
				vehiculo.getDueno().getFactura().agregarProducto(producto, 1);
			}
			else {
				return null; //cuando no hay un producto se retorna null
			}
		}
		return productosVendidos;
	}	
	
	//metodo que emula la venta de un repuesto, como ya se sabe desde Taller que producto se debe comprar, 
	//se procede directamente con la compra de este (no damos opcion de salir para asegurar que siempre se compre un producto)
	private Producto ventaRespuesto(Cliente cliente, TipoProducto tipoProducto) { //TODO: este metodo al parecer es innecesario
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

		List<Producto> componentesDañados = mecanico.revisarVehiculo(vehiculo);
		
		
		// verificar que la lista no este vacia, es decir que halla al menos algo para cambiar y realizar los cambios correspondientes
		if (!componentesDañados.isEmpty()) {
			
			// se guardan en un str los componentes que se arreglaran
			String r = "";
			for (Producto producto : componentesDañados) {
				r += producto.getTipo().toString() + "\n";
			} 
			
			List<Producto> nuevosComponentes = conseguirRepuestos(vehiculo, componentesDañados); // componentes para realizar el cambio
			
			if(nuevosComponentes != null) { // si se tienen todos los componentes se realizan los cambios
				System.out.println("Los siguientes componentes se arreglaran:\n" + r);
				for (int i = 0; i < componentesDañados.size(); i++) {
					mecanico.cambiar(componentesDañados.get(i), nuevosComponentes.get(i), vehiculo);
					mecanico.setServiciosRealizados(mecanico.getServiciosRealizados() + 1); //TODO: mirar que mas hacer
				}
			}
			else {
				System.out.println("No contamos con los repuestos suficientes para realizar el servicio :(");
				return; //TODO: revisar
			}
		}
		else {
			System.out.println("Su vehiculo no tiene imperfecciones");
			return;
		}
		
		//añadir el servicio a la factura del cliente
		vehiculo.getDueno().getFactura().agregarServicio("Revision general", 1);
		System.out.println("*Sonidos de mecanico*");
		System.out.println("Se le asignaron los valores del servicio a su factura");
		System.out.println("Listo, como nuevo :)");
	}
	
	private void cambioDe(String productoS, Vehiculo vehiculo, Empleado mecanico) { //TODO: mirar que hacer en caso de nulls o false
		
		Almacen almacen = parqueadero.getAlmacen();
		// para llantas, amortiguadores, rines preguntamos que numero de componente quiere cambiar
		if(productoS.equals("llantas")) {
			
			if (vehiculo instanceof Carro) {
				String[] posiciones = {"Delantera izq","Delantera der", "Trasera izq", "Trasera der"};
				
				int llanta = Consola.pedirEleccion("Que llanta desea cambiar", Arrays.asList(posiciones));
				Producto llantaV = ((Carro) vehiculo).getLlantas()[llanta];
				
				if (almacen.existeProducto(TipoProducto.LLANTA)) {
					Producto llantaN = almacen.conseguirProducto(TipoProducto.LLANTA);
					vehiculo.getDueno().getFactura().agregarProducto(llantaN, 1); vehiculo.getDueno().getFactura().agregarServicio("Cambio de llanta", 1);
					mecanico.cambiar(llantaV, llantaN, vehiculo);
					mecanico.setServiciosRealizados(mecanico.getServiciosRealizados() + 1);
					System.out.println("*Sonidos de mecanico*\nListo, como nuevo :)");
					return;
				}
			} else {
			
				// para moto
				String[] posiciones = {"Delantera", "Trasera"};
				
				int llanta = Consola.pedirEleccion("Que llanta desea cambiar", Arrays.asList(posiciones));
				Producto llantaV = ((Moto) vehiculo).getLlantas()[llanta];
				
				if (almacen.existeProducto(TipoProducto.LLANTA)) {
					Producto llantaN = almacen.conseguirProducto(TipoProducto.LLANTA);
					vehiculo.getDueno().getFactura().agregarProducto(llantaN, 1); vehiculo.getDueno().getFactura().agregarServicio("Cambio de llanta", 1);
					mecanico.cambiar(llantaV, llantaN, vehiculo);
					mecanico.setServiciosRealizados(mecanico.getServiciosRealizados() + 1);
					System.out.println("*Sonidos de mecanico*\nListo, como nuevo :)");
					return;
				}
			 }
		}
		
		//para rines
		if(productoS.equals("rines")) {
			
			if (vehiculo instanceof Carro) {
				String[] posiciones = {"Delantero izq","Delantero der", "Trasero izq", "Trasero der"};
				
				int rin = Consola.pedirEleccion("Que rin desea cambiar", Arrays.asList(posiciones));
				Producto rinV = ((Carro) vehiculo).getRines()[rin];
				
				if (almacen.existeProducto(TipoProducto.RIN)) {
					Producto rinN = almacen.conseguirProducto(TipoProducto.RIN);
					vehiculo.getDueno().getFactura().agregarProducto(rinN, 1); vehiculo.getDueno().getFactura().agregarServicio("Cambio de rin", 1);
					mecanico.cambiar(rinV, rinN, vehiculo);
					mecanico.setServiciosRealizados(mecanico.getServiciosRealizados() + 1);
					System.out.println("*Sonidos de mecanico*\nListo, como nuevo :)");
					return;
				}
			} else {
			
				// para moto
				String[] posiciones = {"Delantero", "Trasero"};
				
				int rin = Consola.pedirEleccion("Que rin desea cambiar", Arrays.asList(posiciones));
				Producto rinV = ((Moto) vehiculo).getRines()[rin];
				
				if (almacen.existeProducto(TipoProducto.RIN)) {
					Producto rinN = almacen.conseguirProducto(TipoProducto.RIN);
					vehiculo.getDueno().getFactura().agregarProducto(rinN, 1); vehiculo.getDueno().getFactura().agregarServicio("Cambio de rin", 1);
					mecanico.cambiar(rinV, rinN, vehiculo);
					mecanico.setServiciosRealizados(mecanico.getServiciosRealizados() + 1);
					System.out.println("*Sonidos de mecanico*\nListo, como nuevo :)");
					return;
				}
			 }
		}
		
		// para amortiguadores
		if(productoS.equals("amortiguadores")) {
			String[] posiciones = {"Delantero izq","Delantero der", "Trasero izq", "Trasero der"};
			
			int amortiguador = Consola.pedirEleccion("Que amortiguador desea cambiar", Arrays.asList(posiciones));
			Producto amortiguadorV = ((Carro) vehiculo).getAmortiguadores()[amortiguador];
			
			if (almacen.existeProducto(TipoProducto.AMORTIGUADOR)) {
				Producto amortiguadorN = almacen.conseguirProducto(TipoProducto.AMORTIGUADOR);
				vehiculo.getDueno().getFactura().agregarProducto(amortiguadorN, 1); vehiculo.getDueno().getFactura().agregarServicio("Cambio de Amortiguador", 1);
				mecanico.cambiar(amortiguadorV, amortiguadorN, vehiculo);
				mecanico.setServiciosRealizados(mecanico.getServiciosRealizados() + 1);
				System.out.println("*Sonidos de mecanico*\nListo, como nuevo :)");
				return;
			}
		}
		
		// si el caso es un componente que es unico se prosigue asi:
		TipoProducto tipoProducto = TipoProducto.valueOf(productoS.toUpperCase());		
		List<TipoProducto> productos = Arrays.asList(TipoProducto.values());
		
		//verificamos que exista este producto
		if (parqueadero.getAlmacen().existeProducto(tipoProducto)) {
			Producto productoV = conseguir(productos.indexOf(tipoProducto), vehiculo);
			Producto productoN = almacen.conseguirProducto(tipoProducto); // producto para cambiar
			vehiculo.getDueno().getFactura().agregarProducto(productoN, 1); // se agrega a la factura del cliente el producto
			mecanico.cambiar(productoV, productoN, vehiculo);
			mecanico.setServiciosRealizados(mecanico.getServiciosRealizados() + 1);
			System.out.println("*Sonidos de mecanico*\nListo, como nuevo :)");
		}
		
	}
	
	//metodo para conseguir un componente de un vehiculo mediante su get
	private Producto conseguir(int i, Vehiculo vehiculo) {
		Producto r = null;
		if (vehiculo instanceof Carro) {
			switch(i) {
				case 0 -> r = ((Carro) vehiculo).getMotor();
				case 1 -> r = ((Carro) vehiculo).getTransmision();
				case 2 -> r = ((Carro) vehiculo).getAcelerador();
				case 3 -> r = ((Carro) vehiculo).getFreno();
				case 4 -> r = ((Carro) vehiculo).getBateria();
				case 5 -> r = ((Carro) vehiculo).getDepositos()[0];
				case 6 -> r = ((Carro) vehiculo).getDepositos()[1];
				case 7 -> r = ((Carro) vehiculo).getDepositos()[2];
				case 10 -> r = ((Carro) vehiculo).getPedal();
			}
			return r;
		}
		switch(i) {
			case 0 -> r = ((Moto) vehiculo).getMotor();
			case 1 -> r = ((Moto) vehiculo).getTransmision();
			case 2 -> r = ((Moto) vehiculo).getAcelerador();
			case 3 -> r = ((Moto) vehiculo).getFreno();
			case 4 -> r = ((Moto) vehiculo).getBateria();
			case 5 -> r = ((Moto) vehiculo).getDepositos()[0];
			case 6 -> r = ((Moto) vehiculo).getDepositos()[1];
			case 7 -> r = ((Moto) vehiculo).getDepositos()[2];
			case 11 -> r = ((Moto) vehiculo).getCadena();
			case 12 -> r = ((Moto) vehiculo).getPedales();
			case 13 -> r = ((Moto) vehiculo).getAmortiguador();
		}
		return r;
	}
}
	
	