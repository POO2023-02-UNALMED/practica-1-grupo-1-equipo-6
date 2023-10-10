// Alejandro Arias Orozco

package uiMain;

import baseDatos.BaseDatos;
import gestorAplicacion.parqueadero.Parqueadero;
import gestorAplicacion.personas.Cliente;

public abstract class Funcionalidad {
	protected BaseDatos baseDatos;
	protected Parqueadero parqueadero;

	public abstract void ejecutar();

	public void setBaseDatos(BaseDatos baseDatos) {
		this.baseDatos = baseDatos;
		parqueadero = baseDatos.getParqueadero();
	}

	/**
	 * Busca un cliente en la base de datos por su cédula.
	 * Si el cliente no está registrado, le pregunta si desea registrarse y
	 * se procede con el registro.
	 * Cuando el cliente elige no registrarse devuelve null.
	 */
	protected Cliente buscarORegistrarCliente(long cedula) {
		Cliente cliente = baseDatos.buscarClienteRegistrado(cedula);
		if (cliente == null) {
			System.out.println("Cliente no registrado");
			boolean continuarRegistro = Consola.pedirBoolean("Desea registrarse?");
			if (continuarRegistro) {
				return registrarCliente(cedula);
			} else {
				System.out.println("Ha escogido no registrarse.");
				return null;
			}
		} else {
			return cliente;
		}
	}

	/**
	 * Le pregunta los datos al usuario y registra al cliente en la base de datos.
	 */
	private Cliente registrarCliente(long cedula) {
		System.out.println("Registro de cliente");
		String nombre = Consola.pedirString("Ingrese nombre");
		int telefono = Consola.pedirEntero("Ingrese teléfono");
		String correo = Consola.pedirString("Ingrese correo");
		String direccion = Consola.pedirString("Ingrese dirección");
		boolean discapacitado = Consola.pedirBoolean("Usted se encuentra en condición de discapacitado?");
		Cliente cliente = new Cliente(nombre, cedula, telefono, correo, direccion, discapacitado);
		baseDatos.registrarCliente(cliente);
		System.out.println("Cliente registrado. Bienvenido!");
		return cliente;
	}
}
