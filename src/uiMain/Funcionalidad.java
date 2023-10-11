// Alejandro Arias Orozco

package uiMain;

import baseDatos.BaseDatos;
import gestorAplicacion.parqueadero.Parqueadero;
import gestorAplicacion.personas.Cliente;

/**
 * Clase abstracta de Funcionalidad.
 *
 * Cada funcionalidad del programa tendrá que extender esta clase e implementar el método ejecutar,
 * el cual será llamado desde el main.
 *
 * También sirve para compartir código y atributos entre las funcionalidades, como la base de datos, el parqueadero,
 * y métodos que son usados en más de una funcionalidad.
 */
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
		// buscar si el cliente con esa cedula esta registrado en la base de datos
		Cliente cliente = baseDatos.buscarClienteRegistrado(cedula);
		if (cliente == null) { // si no se encuentra registrado, registrarlo. Preguntandole al cliente sus datos.
			System.out.println("Cliente no registrado");

			// preguntarle al cliente si quiere realizar el registro
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
		// Pedir los datos al cliente.
		System.out.println("Registro de cliente");
		String nombre = Consola.pedirString("Ingrese nombre");
		int telefono = Consola.pedirEntero("Ingrese teléfono");
		String correo = Consola.pedirString("Ingrese correo");
		String direccion = Consola.pedirString("Ingrese dirección");
		boolean discapacitado = Consola.pedirBoolean("Usted se encuentra en condición de discapacitado?");

		// Crear la instancia del cliente con la información suministrada
		Cliente cliente = new Cliente(nombre, cedula, telefono, correo, direccion, discapacitado);

		// agregar el cliente a la base de datos
		baseDatos.registrarCliente(cliente);

		// informar que el registro fue exitoso.
		System.out.println("Cliente registrado. Bienvenido!");
		return cliente;
	}
}
