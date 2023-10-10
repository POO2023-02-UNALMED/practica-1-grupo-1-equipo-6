// Alejandro Arias Orozco
// Sebastián

package uiMain;

import gestorAplicacion.parqueadero.Plaza;
import gestorAplicacion.personas.Cliente;
import gestorAplicacion.vehiculos.Carro;
import gestorAplicacion.vehiculos.Moto;
import gestorAplicacion.vehiculos.Vehiculo;

import java.util.List;

public class IngresarVehiculo extends Funcionalidad {
	@Override
	public void ejecutar() {
		System.out.println("Ingresar vehículo");
		long cedula = Consola.pedirLong("Ingrese cédula");
		Cliente cliente = buscarORegistrarCliente(cedula);
		if (cliente == null) {
			return;
		} else {
			System.out.println("Bienvenido de nuevo, " + cliente.getNombre());
		}

		String placa = Consola.pedirString("Ingrese la placa del vehículo a ingresar");
		Vehiculo vehiculo = baseDatos.buscarVehiculoRegistrado(placa);
		if (vehiculo == null) {
			vehiculo = registrarVehiculo(placa, cliente);
		}

		if (vehiculo.estaParqueado()) {
			System.out.println("El vehículo ya se encuentra en el parqueadero!");
			return;
		}

		List<Plaza> plazas = parqueadero.plazasDisponiblesPara(vehiculo);
		Plaza plaza = pedirPlaza(plazas);
		plaza.setVehiculo(vehiculo);

		// TODO: continuara...
	}

	private Vehiculo registrarVehiculo(String placa, Cliente dueno) {
		System.out.println("Registro de vehículo");
		int tipoVehiculo = Consola.pedirEleccion("Elija el tipo de vehiculo", List.of("Carro", "Moto"));
		String marca = Consola.pedirString("Ingrese la marca del vehículo");
		String color = Consola.pedirString("Ingrese el color del vehículo");
		String modelo = Consola.pedirString("Ingrese el modelo del vehículo");
		Vehiculo vehiculo;
		if (tipoVehiculo == 0) {
			int tipoCarro = Consola.pedirEleccion("Elija el tipo de carro", List.of("Mecanico", "Automatico"));
			String tipo = "mecanico";
			if (tipoCarro == 1) {
				tipo = "automatico";
			}
			int puestos = Consola.pedirEntero("Ingrese el numero de puestos del Carro");
			vehiculo = new Carro(placa, dueno, marca, color, modelo, tipo, puestos);
		} else {
			int tipoMoto = Consola.pedirEleccion("Elija el tipo de moto", List.of("Normal", "Alto cilindraje"));
			String tipo = "normal";
			if (tipoMoto == 1) {
				tipo = "altoCC";
			}
			int cilindraje = Consola.pedirEntero("Ingrese el cilindraje de la moto(su valor numerico)");
			vehiculo = new Moto(placa, dueno, marca, color, modelo, tipo, cilindraje);
		}
		baseDatos.registrarVehiculo(vehiculo);
		System.out.println("Vehículo registrado");
		return vehiculo;
	}

	private Plaza pedirPlaza(List<Plaza> plazas) {
		System.out.println("Plazas disponibles:");
		int i = 1;
		for (Plaza plaza : plazas) {
			System.out.printf("%d", plaza.getNumeroPlaza());
			if (i < plazas.size()) {
				System.out.print(" ");
			}
			i++;
		}
		System.out.println();

		int numeroPlaza = Consola.pedirEntero("Elija una plaza");
		Plaza plaza = buscarPlaza(plazas, numeroPlaza);
		if (plaza == null) {
			System.out.println("Plaza no encontrada, por favor escoja una plaza válida.");
			return pedirPlaza(plazas);
		}
		return plaza;
	}

	private Plaza buscarPlaza(List<Plaza> plazas, int numeroPlaza) {
		for (Plaza plaza : plazas) {
			if (plaza.getNumeroPlaza() == numeroPlaza) {
				return plaza;
			}
		}
		return null;
	}
}
