package uiMain;

import gestorAplicacion.personas.Cliente;
import gestorAplicacion.vehiculos.Carro;
import gestorAplicacion.vehiculos.Moto;
import gestorAplicacion.vehiculos.Vehiculo;

import java.util.List;

public class IngresarVehiculo extends Funcionalidad {
	@Override
	public void ejecutar() {
		System.out.println("Ingresar vehículo");
		long cedula = consola.pedirLong("Ingrese cédula");
		Cliente cliente = buscarORegistrarCliente(cedula);
		if (cliente == null) {
			return;
		} else {
			System.out.println("Bienvenido de nuevo, " + cliente.getNombre());
		}

		String placa = consola.pedirString("Ingrese la placa del vehículo a ingresar");
		Vehiculo vehiculo = baseDatos.buscarVehiculoRegistrado(placa);
		if (vehiculo == null) {
			vehiculo = registrarVehiculo(placa, cliente);
		}

		if (vehiculo.estaParqueado()) {
			System.out.println("El vehículo ya se encuentra en el parqueadero!");
			return;
		}

		if (vehiculo instanceof Carro) {
			// mostrar plazas para carro
		} else {
			// mostrar plazas para moto
		}

		// TODO: continuara...
	}

	private Vehiculo registrarVehiculo(String placa, Cliente dueno) {
		System.out.println("Registro de vehículo");
		int tipoVehiculo = consola.pedirEleccion("Elija el tipo de vehiculo", List.of("Carro", "Moto"));
		String marca = consola.pedirString("Ingrese la marca del vehículo");
		String color = consola.pedirString("Ingrese el color del vehículo");
		String modelo = consola.pedirString("Ingrese el modelo del vehículo");
		Vehiculo vehiculo;
		if (tipoVehiculo == 0) {
			vehiculo = new Carro(placa, dueno, marca, color, modelo);
		} else {
			int tipoMoto = consola.pedirEleccion("Elija el tipo de moto", List.of("Normal", "Alto cilindraje"));
			String tipo = "normal";
			if (tipoMoto == 1) {
				tipo = "altoCC";
			}
			vehiculo = new Moto(placa, dueno, marca, color, modelo, tipo);
		}
		baseDatos.registrarVehiculo(vehiculo);
		System.out.println("Vehículo registrado");
		return vehiculo;
	}
}
