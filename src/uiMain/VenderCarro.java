package uiMain;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import gestorAplicacion.parqueadero.Producto;
import gestorAplicacion.personas.Cliente;
import gestorAplicacion.vehiculos.Carro;
import gestorAplicacion.vehiculos.MarcasCarro;
import gestorAplicacion.personas.Empleado;


/**
 * FUNCIONALIDAD VENDER CARRO
 * Esta funcionalidad desde la vista del administrador vende un carro desde la clase venderCarro, desde la vista de un cliente se estaría realizando una compra.
 * 
 * 
 */

public class VenderCarro extends Funcionalidad {

	@Override
	public void ejecutar() {
		System.out.println("Comprar carro");
		
		//Se le pide al cliente ingresar la cedula
		long cedula= Consola.pedirLong("Ingrese cédula");

		//Si el cliente no está registrado se le da la opcion de registrarse o no
		Cliente cliente= buscarORegistrarCliente(cedula);
		if (cliente==null){
			return;
		}
		
		//Se le pide al cliente escoger de los vendedores disponibles 
		List<Empleado> vendedores = new ArrayList<>(parqueadero.getEmpleados().stream().filter(empleado -> "Vendedor".equals(empleado.getCargo())).collect(Collectors.toList()));
		//lista con  los nombres de los vendedores
		List<String> nombresVendedores = new ArrayList<>(vendedores.stream().map(Empleado::getNombre).toList());
		

		//Si la lista no tiene vendedores entonces se imprime un mensaje y se vuelve al menú principal
		if (nombresVendedores.isEmpty()){
			System.out.println("No hay vendedores disponibles en el momento, intente más tarde.");
			return;
		}

		//En caso de que la lista si tenga empleados disponibles entonces se le pide al usuario escoger uno
		int escogerVendedor = Consola.pedirEleccion("Escoja el vendedor de su preferencia", nombresVendedores);
		Empleado vendedor = vendedores.get(escogerVendedor);
		
		System.out.printf("Hola, mi nombre es %s y voy a atenderlo.%n", vendedor.getNombre());
		
		//Aquí se usa un método de consola para pedirle opciones al usuario para realizar un filtro de busqueda de carros
		int eleccionBusqueda;
		do {
		eleccionBusqueda= Consola.pedirEleccion("Seleccione la opción por la que desea realizar la busqueda.", List.of(
			"Marca",
			"Color",
			"Precio máximo",
			"Regresar al menú principal"
		));

		if (eleccionBusqueda != 3){
		List<Carro> filtroCarros = new ArrayList<>();
		
		//Se implementan los filtros en cada una de las opciones por las que se desea realizar la busqueda
		if (eleccionBusqueda==0){
			List<String> marcas= Arrays.asList(MarcasCarro.values()).stream().map(MarcasCarro::name).toList();
			int eleccionMarca= Consola.pedirEleccion("Seleccione una marca", marcas);
			MarcasCarro atributo= MarcasCarro.valueOf(marcas.get(eleccionMarca));
			filtroCarros= new ArrayList<>(Empleado.getVehiculosVenta().stream().filter(carro -> atributo.equals(carro.getMarca())).collect(Collectors.toList()));
		}

		else if (eleccionBusqueda==1){
			String  atributo= Consola.pedirString("Elija un color");
			filtroCarros= new ArrayList<>(Empleado.getVehiculosVenta().stream().filter(carro -> atributo.equals(carro.getColor())).collect(Collectors.toList()));
		}

		else if(eleccionBusqueda==2){
			long atributo= Consola.pedirLong("Agregue un precio por el que desea buscar");
			filtroCarros= new ArrayList<>(Empleado.getVehiculosVenta().stream().filter(carro -> atributo > carro.getPrecioVenta()).collect(Collectors.toList()));
		}


		//si el cliente es discapacitado inmediatamente se filtran carros con caracteristicas especiales que están adaptadas para estas personas
		if (Cliente.isDiscapacitado()){
		if (cliente.isDiscapacitado()){
			filtroCarros= filtroCarros.stream().filter(carro -> carro.isDiscapacitado()).collect(Collectors.toList());
		}


		//Se agrega volver a la lista para cuando al usuario se le muestren los vehiculos disponibles pero este no quiera comprar y quiera volver al menu
		
		if (filtroCarros.isEmpty()) {
			System.out.println("No se encontro un carro con las caracteristicas buscadas");
			continue;
		}
		
		//si no hay color, marca o precio anotar
		List<String> carrosFiltrados= new ArrayList<>(filtroCarros.stream().map(Carro::toString).toList());
		carrosFiltrados.add("Volver");

		//Si el usuario esta dispuesto a comprar debe elegir una opcion que contiene las caracteristicas del cual fue su carro de preferencia
		int eleccionCarro= Consola.pedirEleccion("Elija un carro para comprar", carrosFiltrados);

		if (eleccionCarro == carrosFiltrados.size() - 1) {
			continue;
		}

		Carro carro= filtroCarros.get(EleccionCarro);
		//Se manda un mecanico por defecto para que revise el carro elegido por el usuario antes de concretar la compra
		Carro carro= filtroCarros.get(eleccionCarro);

		Empleado mecanico= mecanicoRandom();
		//Luego de que el mecanico termine la revision, se le agrega un servicio a su contador
		List<String> revision= mecanico.revisarVehiculo(carro);

		List<Producto> revision= mecanico.revisarVehiculo(carro);
		mecanico.setServiciosRealizados(mecanico.getServiciosRealizados()+1);

		//metodo que nos revisa si la lista de cosas dañadas informadas desde la revision del mecanico esta vacia o no
		if (revision.isEmpty()){
			//si esta vacia se le pide el indice de ese vehicuolo en la lista
			int indx = mecanico.getVehiculosVenta().indexOf(carro); 
			//Luego se elimina desde el indice dicho carro, esto para confirmar que se vendio
			mecanico.getVehiculosVenta().remove(indx);
			//se agrega a la factura del usuario el monto y el servicio brindado
			cliente.getFactura().agregarServicio("Compra de carro " + cap(carro.getMarca().name()), carro.precioCarro);
			//Se le agrega el carro a la lista de vehiculos del cliente 
			int indx = Empleado.getVehiculosVenta().indexOf(carro); 
			Empleado.getVehiculosVenta().remove(indx);
			cliente.getFactura().agregarServicio("Compra de carro " + cap(carro.getMarca().name()), carro.getPrecioVenta());
			cliente.getVehiculos().add(carro);
			//se le agrega al contador del vendedor el servicio realizado
			vendedor.setServiciosRealizados(vendedor.getServiciosRealizados() + 1);
			//se imprime un mensaje final luego de que el usuario finaliza su compra
			System.out.println("¡Carro comprado exitosamente! Disfrute su compra :)");
			return;
		}


		//Si se presenta alguna falla en el carro después de su revision, entonces le decimos al usuario que no le podemos vender el carro porque no esta en condiciones aptas
		System.out.println("No podemos venderte este vehiculo");
		}
		} while (eleccionBusqueda != 3);
	}

	//metodo que le asigna un mecanico aleatorio de una lista a el usuario para su revision
	private Empleado mecanicoRandom(){
			int num = (int) (Math.random() * (parqueadero.getMecanicos().size() + 1));
			return parqueadero.getMecanicos().get(num);
	}
}
