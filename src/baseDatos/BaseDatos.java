// Alejandro Arias Orozco

package baseDatos;

import gestorAplicacion.Identificable;
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
		// crear una instancia de Deserializador para realizar la lectura de los datos
		Deserializador deserializador = new Deserializador(ARCHIVO);

		// verifica si los datos existen
		boolean existenDatos = deserializador.existenDatos();

		// si los datos no existen, entonces se devolverá null
		BaseDatos baseDatos = null;
		if (existenDatos) {
			// Si los datos existen, entonces leer los datos guardados
			baseDatos = (BaseDatos) deserializador.leerObjeto();
		}

		// cerrar el deserializador luego de leer los datos
		deserializador.close();
		return baseDatos;
	}

	public void escribirDatos() throws BaseDatosException {
		// crear una instancia de Serializador para realizar el guardado de los datos
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
		return buscarRegistrado(clientesRegistrados, cedula);
	}

	public void registrarCliente(Cliente cliente) {
		this.clientesRegistrados.add(cliente);
	}

	/**
	 * Recibe la placa del vehículo y devuelve una instancia del Vehículo con esa placa
	 * en caso de que esté registrado, o null en caso contrario.
	 */
	public Vehiculo buscarVehiculoRegistrado(String placa) {
		return buscarRegistrado(vehiculosRegistrados, placa);
	}

	public void registrarVehiculo(Vehiculo vehiculo) {
		this.vehiculosRegistrados.add(vehiculo);
	}

	private <T, U extends Identificable<T>> U buscarRegistrado(List<U> registrados, T identificacion) {
		for (U registrado : registrados) {
			if (registrado.tieneIdentificacion(identificacion)) {
				return registrado;
			}
		}
		return null;
	}
}
