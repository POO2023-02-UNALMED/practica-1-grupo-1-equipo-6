// Alejandro Arias Orozco

package baseDatos;

import java.io.*;
import java.util.List;

/**
 * Provee escritura de los datos en el archivo src/baseDatos/temp/datos.txt
 * <br>
 * <br>
 * Tiene un único método escribirObjetos, el cual recibe como único parámetro una lista de todos los
 * objetos que se quieren escribir en el archivo, y éste se encarga de todo el proceso.
 */
public class Serializador extends BaseDatos {
	public void escribirObjetos(List<Serializable> objectos) throws BaseDatosException {
		// obtener el archivo src/baseDatos/temp/datos.txt.
		File archivo = RUTA_ARCHIVO.toFile();
		// crear las carpetas src/baseDatos/temp si no existen
		archivo.getParentFile().mkdirs();
		// crear el archivo src/baseDatos/temp/datos.txt si no existe
		try {
			archivo.createNewFile();
		} catch (IOException e) {
			throw new BaseDatosException("Ha ocurrido un error creando el archivo de datos", e);
		}

		FileOutputStream fileOutputStream;
		ObjectOutputStream objectOutputStream;

		// instanciar FileOutputStream y en caso de error devolver un mensaje explicando el error
		try {
			fileOutputStream = new FileOutputStream(archivo);
		} catch (FileNotFoundException e) {
			throw new BaseDatosException("Ha ocurrido un error abriendo el archivo de los datos", e);
		}

		// instanciar ObjectOutputStream y en caso de error devolver un mensaje explicando el error
		try {
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
		} catch (IOException e) {
			throw new BaseDatosException("Ha ocurrido un error durante el guardado de los datos", e);
		}

		// para cada objeto en la lista objetos, escribirlo en el archivo
		for (Serializable object : objectos) {
			try {
				objectOutputStream.writeObject(object);
			} catch (IOException e) {
				throw new BaseDatosException("Ha ocurrido un error escribiendo los datos del objeto", e);
			}
		}

		// cerrar el archivo
		try {
			objectOutputStream.close();
			fileOutputStream.close();
		} catch (IOException e) {
			throw new BaseDatosException("Ha ocurrido un error cerrando los archivos de datos", e);
		}
	}
}
