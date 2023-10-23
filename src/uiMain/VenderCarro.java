package uiMain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

import gestorAplicacion.parqueadero.Producto;
import gestorAplicacion.personas.Cliente;
import gestorAplicacion.vehiculos.Carro;
import gestorAplicacion.vehiculos.MarcasCarro;
import gestorAplicacion.personas.Empleado;

//Venta de carro punto de vista parqueadero

//Permite al cliente comprar un carro según características específicas
public class VenderCarro extends Funcionalidad {
	@Override
	public void ejecutar() {
		System.out.println("Comprar carro");
		
		long cedula= Consola.pedirLong("Ingrese cédula");

		Cliente cliente= buscarORegistrarCliente(cedula);
		if (cliente==null){
			return;
		}
		
		//Se le pide al cliente escoger de los vendedores disponibles 
		
		List<Empleado> vendedores = new ArrayList<>(parqueadero.getEmpleados().stream().filter(empleado -> "Vendedor".equals(empleado.getCargo())).collect(Collectors.toList()));
		//lista con  los nombres de los vendedores
		List<String> nombresVendedores = new ArrayList<>(vendedores.stream().map(Empleado::getNombre).toList());
		

		//Si la lista no tiene vendedores entonces no puede continuar
		if (nombresVendedores.isEmpty()){
			System.out.println("No hay vendedores disponibles en el momento, intente más tarde.");
			return;
		}

		int escogerVendedor = Consola.pedirEleccion("Escoja el vendedor de su preferencia", nombresVendedores);
		Empleado vendedor = vendedores.get(escogerVendedor);
		
		System.out.printf("Hola, mi nombre es %s y voy a atenderlo.%n", vendedor.getNombre());
		
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

		if (cliente.isDiscapacitado()){
			filtroCarros= filtroCarros.stream().filter(carro -> carro.isDiscapacitado()).collect(Collectors.toList());
		}
		
		if (filtroCarros.isEmpty()) {
			System.out.println("No se encontro un carro con las caracteristicas buscadas");
			continue;
		}
		
		//si no hay color, marca o precio anotar
		List<String> carrosFiltrados= new ArrayList<>(filtroCarros.stream().map(Carro::toString).toList());
		carrosFiltrados.add("Volver");

		int eleccionCarro= Consola.pedirEleccion("Elija un carro para comprar", carrosFiltrados);

		if (eleccionCarro == carrosFiltrados.size() - 1) {
			continue;
		}

		Carro carro= filtroCarros.get(eleccionCarro);

		Empleado mecanico= mecanicoRandom();

		List<Producto> revision= mecanico.revisarVehiculo(carro);
		mecanico.setServiciosRealizados(mecanico.getServiciosRealizados()+1);

		if (revision.isEmpty()){
			int indx = Empleado.getVehiculosVenta().indexOf(carro); 
			Empleado.getVehiculosVenta().remove(indx);
			cliente.getFactura().agregarServicio("Compra de carro " + cap(carro.getMarca().name()), carro.getPrecioVenta());
			cliente.getVehiculos().add(carro);
			vendedor.setServiciosRealizados(vendedor.getServiciosRealizados() + 1);
			System.out.println("Disfrute su compra :)");
			return;
		}

		System.out.println("No podemos venderte este vehiculo");
		}
		} while (eleccionBusqueda != 3);
	}

	private Empleado mecanicoRandom(){
			int num = (int) (Math.random() * (parqueadero.getMecanicos().size() + 1));
			return parqueadero.getMecanicos().get(num);
	}
}
