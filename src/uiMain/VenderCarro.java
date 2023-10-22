package uiMain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gestorAplicacion.parqueadero.Almacen;
import gestorAplicacion.personas.Cliente;
import gestorAplicacion.vehiculos.Vehiculo;
import gestorAplicacion.vehiculos.Carro;
import gestorAplicacion.personas.Empleado;


public class VenderCarro extends Funcionalidad {
	@Override
	public void ejecutar() {
		System.out.println("Vender un carro");
		
		long cedula= Consola.pedirLong("Ingrese cédula");

		Cliente cliente= buscarORegistrarCliente(cedula);
		if (cliente==null){
			return;
		}

	}

}
