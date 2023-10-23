package uiMain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gestorAplicacion.parqueadero.Almacen;
import gestorAplicacion.personas.Cliente;
import gestorAplicacion.vehiculos.Vehiculo;
import gestorAplicacion.vehiculos.Carro;
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
		
		

	}

}
