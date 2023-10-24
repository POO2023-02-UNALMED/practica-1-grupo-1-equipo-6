/**
 * Funcionalidad del módulo: Contiene la clase venderCarro que se encarga de vender un carro desde la vista
 * del administrador, pero que al momento de ejecutar la funcionalidad por el usuario este tendrá la vista de comprar un carro
 * Componentes del módulo: VenderCarro
 * Autores: Katherine, Sebastian
 */

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


public class VenderCarro extends Funcionalidad {

	@Override
	public void ejecutar() {
		System.out.println("Comprar carro");
		
		/**
		 *Se le pide al cliente ingresar la cedula
		 */ 
		long cedula= Consola.pedirLong("Ingrese cédula");

		/**
		 * Si el cliente no está registrado se le da la opcion de registrarse o no registrarse.
		 * Si elige no entonces se devuelve al menu principal
		 */
		Cliente cliente= buscarORegistrarCliente(cedula);
		if (cliente==null){
			return;
		}
		
		/**
		 * Luego de que el usuario se registra se le pide al cliente escoger
		 * de los vendedores disponibles 
		 */
		List<Empleado> vendedores = new ArrayList<>(parqueadero.getEmpleados().stream().filter(empleado -> "Vendedor".equals(empleado.getCargo())).collect(Collectors.toList()));
		List<String> nombresVendedores = new ArrayList<>(vendedores.stream().map(Empleado::getNombre).toList());
		
		/**
		 * En caso de que la lista no tuviera vendedores entonces se imprime un mensaje y se vuelve al menú principal
		 */
		if (nombresVendedores.isEmpty()){
			System.out.println("No hay vendedores disponibles en el momento, intente más tarde.");
			return;
		}

		/**
		 * En caso de que la lista si tenga empleados disponibles entonces se le pide al usuario escoger uno
		 */
		int escogerVendedor = Consola.pedirEleccion("Escoja el vendedor de su preferencia", nombresVendedores);
		Empleado vendedor = vendedores.get(escogerVendedor);
		
		System.out.printf("Hola, mi nombre es %s y voy a atenderlo.%n", vendedor.getNombre());
		


		/**
		 * Creamos un metodo que va a filtrar la busqueda de los carros para vender, esto para facilitar al cliente
		 * la lista de opciones de carros de su preferencia. Empezaremos entonces pidiendo por consola un entero que será
		 * equivalente a la opcion elegida por el usuario. Las opciones de busqueda que estarán disponible serán busqueda por
		 * marca, color, precio maximo y en caso de que el cliente sea discapacitado entonces se le dará una lista de
		 * carros que estarán previamente adaptados para especialmente personas discapacitadas
		 */
		int eleccionBusqueda;
		do {
		eleccionBusqueda= Consola.pedirEleccion("Seleccione la opción por la que desea realizar la busqueda.", List.of(
			"Marca",
			"Color",
			"Precio máximo",
			"Regresar al menú principal"
		));

		if (eleccionBusqueda != 3){
		
		/**
		 * Se crea una lista que va a contener elementos de tipo carro, estos elementos serán los carros que pasarán
		 * por los filtros y cumplan con la especificacion pedida, luego de que el carro pase el filtro este será
		 * agregado a dicha lista, en caso contrario no se agregará
		 */
		List<Carro> filtroCarros = new ArrayList<>();
		
		/**
		 * A continuacion las lineas de codigo que hacen el respectivo filtro de cada tipo de busqueda
		 */
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
			double atributo= Consola.pedirDouble("Agregue un precio por el que desea buscar");
			filtroCarros= new ArrayList<>(Empleado.getVehiculosVenta().stream().filter(carro -> atributo >= carro.getPrecioVenta()).collect(Collectors.toList()));
		}

		/**
		 * Si el cliente es discapacitado inmediatamente se filtran carros con caracteristicas especiales
		 * que están adaptadas para estas personas
		 */

		//si el cliente es discapacitado inmediatamente se filtran carros con caracteristicas especiales que están adaptadas para estas personas
		if (cliente.isDiscapacitado()){
			filtroCarros= filtroCarros.stream().filter(carro -> carro.isDiscapacitado()).collect(Collectors.toList());
		}

		/**
		 * Si la lista está vacía se le muestra al usuario un mensaje que dice que no hay carros que cumplan
		 * los filtros seleccionados
		 */
		if (filtroCarros.isEmpty()) {
			System.out.println("No se encontro un carro con las caracteristicas buscadas");
			continue;
		}
		
		/**
		 * Se modifica la lista de los carros filtrados con toString para que se agreguen a la lista como la informacion del
		 * objeto y no como la referencia por defecto. Se agrega "volver" a la lista como una opcion a elegir para cuando
		 * al usuario se le muestren los vehiculos disponibles pero este no quiera comprar y quiera volver al menu
		 */
		List<String> carrosFiltrados= new ArrayList<>(filtroCarros.stream().map(Carro::toString).toList());
		carrosFiltrados.add("Volver");


		/**
		 * Si el usuario esta dispuesto a comprar debe elegir una opcion que contiene las caracteristicas del cual fue su carro de preferencia
		 * y eso se hace mediante consola
		 */
		int eleccionCarro= Consola.pedirEleccion("Elija un carro para comprar", carrosFiltrados);

		if (eleccionCarro == carrosFiltrados.size() - 1) {
			continue;
		}

		/**
		 * Se pide el carro seleccionado por el usuario para llevarlo a revision con un mecanico y ver
		 * en que estado se encuentra dicho vehiculo
		 */
		//Se manda un mecanico por defecto para que revise el carro elegido por el usuario antes de concretar la compra
		Carro carro= filtroCarros.get(eleccionCarro);

		/**
		 * Se elige un empleado de tipo mecanico aleatorio que haga revision del vehiculo
		 */
		Empleado mecanico = mecanicoRandom();
		//Luego de que el mecanico termine la revision, se le agrega un servicio a su contador

		/**
		 * Luego de que el mecanico termine la revision se le agregara un servicio al contador de
		 * los servicios realizados
		 */
		List<Producto> revision = mecanico.revisarVehiculo(carro);
		mecanico.setServiciosRealizados(mecanico.getServiciosRealizados()+1);


		/**
		 * Se revisa si la lista de la revision esta vacia o hay inconvenientes con el carro
		 */
		if (revision.isEmpty()){
			/**
			 * Si esta vacia significa que el carro esta en buenas condiciones entonces se procede a pedir
			 * el indice de la lista de ese vehiculo para proceder a venderlo. Entonces para saber que se vendio
			 * se elimina el carro de la lista con el indice previamente pedido
			 */
			//si esta vacia se le pide el indice de ese vehicuolo en la lista
			int indx = Empleado.getVehiculosVenta().indexOf(carro); 
			//Luego se elimina desde el indice dicho carro, esto para confirmar que se vendio
			Empleado.getVehiculosVenta().remove(indx);
			//se agrega a la factura del usuario el monto y el servicio brindado
			//Se le agrega el carro a la lista de vehiculos del cliente 
			cliente.getFactura().agregarServicio("Compra de carro " + cap(carro.getMarca()), carro.getPrecioVenta());
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
