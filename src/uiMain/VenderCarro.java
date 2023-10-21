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
		System.out.print("Vender un carro");
		long cedula = Consola.pedirLong("Ingrese cédula");

		Cliente cliente= buscarORegistrarCliente(cedula);
		if (cliente == null){
			return;
		}

		// TODO: se debe definir donde estaran almacenados los carros para la venta, y esta funcionalidad se encarga de vender esos carros
	}
}
