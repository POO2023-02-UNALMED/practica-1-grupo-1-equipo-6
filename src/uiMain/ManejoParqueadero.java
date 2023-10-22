package uiMain;

import java.util.List;
import java.util.stream.IntStream;

import gestorAplicacion.personas.Empleado;

public class ManejoParqueadero extends Funcionalidad {
	@Override
	public void ejecutar() {
		long cedulaAdministrador = Consola.pedirLong("Ingrese la cédula del administrador");
		if (!parqueadero.getAdministrador().tieneIdentificacion(cedulaAdministrador)) {
			System.out.println("Cédula de administrador incorrecta");
			return;
		}

		int eleccion = Consola.pedirEleccion("Parte del parqueadero a manejar", List.of(
				"Taller",
				"Parqueadero",
				"Almacén"
		));

		switch (eleccion) {
			case 0 -> administrarTaller();
			case 1 -> administrarParqueadero();
			case 2 -> administrarAlmacen();
		}
	}

	private void administrarTaller() {
		int eleccion;
		do {
			eleccion = Consola.pedirEleccion("Que desea hacer", List.of(
					"Nuevo mecanico",
					"Despedir mecanico",
					"Regresar"
					));
			
			switch (eleccion) {
				case 0 -> agregarEmpleado("Mecanico");
				case 1 -> despedirEmpleado(Consola.pedirLong("Ingrese la cédula del empleado"));
			}
		} while (eleccion != 2);
	}

	private void administrarParqueadero() {

	}

	private void administrarAlmacen() {
		
	}
	
	// metodo para "contratar un nuevo empleado"
	private void agregarEmpleado(String tipoEmpleado) {
		String nombre = Consola.pedirString("Ingrese el nombre: ");
		long cedula = Consola.pedirLong("Ingrese la cédula: ");
		long telefono = Consola.pedirLong("Ingrese el numero del telefono: ");
		String correo = Consola.pedirString("Ingrese el correo: ");
		String direccion = Consola.pedirString("Ingrese la dirección: ");
		double salario = Consola.pedirDouble("Ingrese el salario: ");
		Empleado nEmpleado = new Empleado(nombre, cedula, telefono, correo, direccion, tipoEmpleado, salario);
		parqueadero.agregarEmpleado(nEmpleado);
		System.out.println("Empleado contratado");
	}
	
	private void despedirEmpleado(long cedulaEmpleado) {
		List<Empleado> empleados = parqueadero.getEmpleados();
		// si existe el empleado obtenemos su indice, en cazo contrario el -1
		int empIndex =IntStream.range(0, empleados.size()).filter(i -> empleados.get(i).getCedula() == cedulaEmpleado).findFirst().orElse(-1);
		if (empIndex != -1) {
			Empleado empleado = empleados.get(empIndex);
			//se pregunta por ultima vez
			boolean decision = Consola.pedirBoolean("Esta seguro que desea despedir a " + empleado.getNombre());
			if (decision) {
				empleados.remove(empIndex);
			}
		}
		System.out.println("Empleado despedido");
	}
}
