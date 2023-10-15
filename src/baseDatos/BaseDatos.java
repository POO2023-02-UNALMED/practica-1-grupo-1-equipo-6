// Alejandro Arias Orozco

package baseDatos;

import gestorAplicacion.Identificable;
import gestorAplicacion.parqueadero.Parqueadero;
import gestorAplicacion.personas.Cliente;
import gestorAplicacion.vehiculos.Vehiculo;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Esta clase se encarga de toda la persistencia.
 */
public class BaseDatos implements Serializable {
	private static final long serialVersionUID = 1L;

	private static File archivo = Paths.get(".", "src", "baseDatos", "temp", "datos.txt").toFile();
	private Map<Long, Cliente> clientesRegistrados = new HashMap<>();
	private Map<String, Vehiculo> vehiculosRegistrados = new HashMap<>();
	private Parqueadero parqueadero;

	/**
	 * Lee y carga los datos guardados en el archivo src/baseDatos/temp/datos.txt
	 * Retorna los datos en una instancia de BaseDatos si existen datos y se logró leerlos,
	 * null si no hay datos o si ocurrió algún error.
	 */
	public static BaseDatos leerDatos() throws BaseDatosException {
		// crear una instancia de Deserializador para realizar la lectura de los datos
		Deserializador deserializador = new Deserializador(archivo);

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

	/**
	 * Persiste los datos de la base de datos en un archivo.
	 */
	public void escribirDatos() throws BaseDatosException {
		// crear una instancia de Serializador para realizar el guardado de los datos
		Serializador serializador = new Serializador();
		// persistir datos de las clases
		serializador.escribirObjeto(archivo, this);
	}

	public Parqueadero getParqueadero() {
		return parqueadero;
	}

	/**
	 * Guarda el parqueadero en la base de datos.
	 */
	public void setParqueadero(Parqueadero parqueadero) {
		this.parqueadero = parqueadero;
	}

	/**
	 * Recibe la cédula del cliente y devuelve una instancia del Cliente con esa cédula
	 * en caso de que esté registrado, o null en caso contrario.
	 */
	public Cliente buscarClienteRegistrado(long cedula) {
		return clientesRegistrados.get(cedula);
	}

	/**
	 * Registra un cliente en la base de datos.
	 * Si el cliente ya está registrado, retorna false, de lo contrario retorna true.
	 */
	public boolean registrarCliente(Cliente cliente) {
		return registrar(clientesRegistrados, cliente);
	}

	/**
	 * Recibe la placa del vehículo y devuelve una instancia del Vehículo con esa placa
	 * en caso de que esté registrado, o null en caso contrario.
	 */
	public Vehiculo buscarVehiculoRegistrado(String placa) {
		return vehiculosRegistrados.get(Vehiculo.normalizarPlaca(placa));
	}

	/**
	 * Registra un vehículo en la base de datos.
	 * Si el vehículo ya está registrado, retorna false, de lo contrario retorna true.
	 */
	public boolean registrarVehiculo(Vehiculo vehiculo) {
		return registrar(vehiculosRegistrados, vehiculo);
	}

	public List<Vehiculo> vehiculosRegistradosPor(Cliente cliente) {
		long cedula = cliente.getIdentificacion();
		return vehiculosRegistrados.values().stream().filter(vehiculo -> vehiculo.getDueno().tieneIdentificacion(cedula)).toList();
	}

	public boolean hayClientesRegistrados() {
		return !clientesRegistrados.isEmpty();
	}

	public boolean hayVehiculosRegistrados() {
		return !clientesRegistrados.isEmpty();
	}

	public static void setArchivo(File archivo) {
		BaseDatos.archivo = archivo;
	}

	/**
	 * Agrega un objeto a un Map si no existe. La clave utilizada es la identificacion del objeto.
	 * Retorna true si se realizó el registro, false si el objecto ya estaba registrado.
	 */
	private <T, U extends Identificable<T>> boolean registrar(Map<T, U> registrados, U obj) {
		T identificacion = obj.getIdentificacion();
		if (registrados.get(identificacion) != null) {
			return false;
		}
		registrados.put(identificacion, obj);
		return true;
	}
}
