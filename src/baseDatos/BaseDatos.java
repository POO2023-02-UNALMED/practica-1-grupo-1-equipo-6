// Alejandro Arias Orozco

package baseDatos;

import gestorAplicacion.parqueadero.Parqueadero;
import gestorAplicacion.personas.Cliente;
import gestorAplicacion.vehiculos.Vehiculo;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase se encarga de toda la persistencia.
 */
public class BaseDatos implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final File ARCHIVO = Paths.get(".", "src", "baseDatos", "temp", "datos.txt").toFile();
	private List<Cliente> clientesRegistrados = new ArrayList<>();
	private List<Vehiculo> vehiculosRegistrados = new ArrayList<>();
	private Parqueadero parqueadero;

	/**
	 * Lee y carga los datos guardados en el archivo src/baseDatos/temp/datos.txt
	 * @return los datos en una instancia de BaseDatos si existen datos y se logró leerlos,
	 * null si no hay datos o si ocurrió algún error.
	 * @throws BaseDatosException
	 */
	public static BaseDatos leerDatos() throws BaseDatosException {
		Deserializador deserializador = new Deserializador(ARCHIVO);
		boolean existenDatos = deserializador.existenDatos();

		BaseDatos baseDatos = null;
		if (existenDatos) {
			// leer los datos guardados
			try {
				baseDatos = (BaseDatos) deserializador.leerObjeto();
			} catch (ClassCastException e) {
				baseDatos = null;
			}
		}

		deserializador.close();
		return baseDatos;
	}

	public void escribirDatos() throws BaseDatosException {
		Serializador serializador = new Serializador();
		// persistir datos de las clases
		serializador.escribirObjeto(ARCHIVO, this);
	}

	public Parqueadero getParqueadero() {
		return parqueadero;
	}

	public void setParqueadero(Parqueadero parqueadero) {
		this.parqueadero = parqueadero;
	}

	/**
	 * Recibe la cédula del cliente y devuelve una instancia del Cliente con esa cédula
	 * en caso de que esté registrado, o null en caso contrario.
	 */
	public Cliente buscarClienteRegistrado(long cedula) {
		for (Cliente cliente : clientesRegistrados) {
			if (cliente.getCedula() == cedula) {
				return cliente;
			}
		}
		return null;
	}

	public void registrarCliente(Cliente cliente) {
		this.clientesRegistrados.add(cliente);
	}

	/**
	 * Recibe la placa del vehículo y devuelve una instancia del Vehículo con esa placa
	 * en caso de que esté registrado, o null en caso contrario.
	 */
	public Vehiculo buscarVehiculoRegistrado(String placa) {
		placa = placa.toUpperCase();
		for (Vehiculo vehiculo : vehiculosRegistrados) {
			if (vehiculo.getPlaca().equals(placa)) {
				return vehiculo;
			}
		}
		return null;
	}

	public void registrarVehiculo(Vehiculo vehiculo) {
		this.vehiculosRegistrados.add(vehiculo);
	}
}
