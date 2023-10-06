// Alejandro Arias Orozco

package baseDatos;

import gestorAplicacion.parqueadero.Parqueadero;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Paths;

/**
 * Esta clase se encarga de toda la persistencia.
 */
public class BaseDatos implements Serializable {
	private static final File ARCHIVO = Paths.get(".", "src", "baseDatos", "temp", "datos.txt").toFile();
	private Parqueadero parqueadero;

	/**
	 * Lee y carga los datos guardados en el archivo src/baseDatos/temp/datos.txt
	 * @return true si existen datos y se logró leerlos, false si no hay datos o ocurrió algún error.
	 * @throws BaseDatosException
	 */
	public boolean leerDatos() throws BaseDatosException {
		Deserializador deserializador = new Deserializador(ARCHIVO);
		boolean existenDatos = deserializador.existenDatos();

		if (existenDatos) {
			// leer los datos guardados
			parqueadero = (Parqueadero) deserializador.leerObjeto();
		}

		deserializador.close();
		return existenDatos;
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
}
