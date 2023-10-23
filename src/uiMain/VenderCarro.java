package uiMain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

import gestorAplicacion.parqueadero.Almacen;
import gestorAplicacion.personas.Cliente;
import gestorAplicacion.vehiculos.Vehiculo;
import gestorAplicacion.vehiculos.Carro;
import gestorAplicacion.vehiculos.MarcasCarro;
import gestorAplicacion.personas.Empleado;
import gestorAplicacion.parqueadero.Parqueadero;

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
		
		int eleccionBusqueda= Consola.pedirEleccion("Seleccione la opción por la que desea realizar la busqueda.", List.of(
			"Marca",
			"Color",
			"Precio máximo"
		));

		List<Carro> filtroCarros;

		if (eleccionBusqueda==0){
			List<String> marcas= Arrays.asList(MarcasCarro.values()).stream().map(MarcasCarro::name).toList();
			int eleccionMarca= Consola.pedirEleccion("Seleccione una marca", marcas);
			MarcasCarro atributo= MarcasCarro.valueOf(marcas.get(eleccionMarca));
			filtroCarros= new ArrayList<>(vendedor.getVehiculosVenta().stream().filter(carro-> atributo.name().equals(carro.getMarca().toUpperCase())).collect(collectors.toList()));
		}

		else if (eleccionBusqueda==1){
			String  atributo= Consola.pedirString("Elija un color");
			filtroCarros= new ArrayList<>(vendedor.getVehiculosVenta().stream().filter(carro-> atributo.equals(carro.getColor())).collect(collectors.toList()));
		}

		else if(eleccionBusqueda==2){
			long atributo= Consola.pedirLong("Agregue un precio por el que desea buscar");
			filtroCarros= new ArrayList<>(vendedor.getVehiculosVenta().stream().filter(carro-> atributo== carro.getPrecioVenta()).collect(collectors.toList()));
		}

		else if (eleccionBusqueda==3){
			boolean atributo= Consola.pedirBoolean("Elija si necesita el carro adaptado para discapacitados");
			filtroCarros= new ArrayList<>(vendedor.getVehiculosVenta().stream().filter(carro-> atributo== carro.isDiscapacitado().).collect(collectors.toList()));
		}




	}

}
