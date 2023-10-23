/*
 Funcionalidad del módulo: contiene la clase Deserializador que se encarga de leer los objetos guardados en el archivo de datos
 Componentes del módulo: Deserializador
 Autores: Alejandro
*/

package baseDatos;

import java.io.*;

/**
 * Provee lectura de los datos guardados en un archivo
 * <br>
 * <br>
 * Luego de instanciar esta clase, utilice el método existenDatos para asegurarse de que el archivo
 * existe, es decir, que hay datos guardados. Luego puede obtener cada uno de los objetos guardados
 * en el archivo con el método leerObjeto. Por último cierre con el método close luego de haber
 * leido todos los objetos que deseaba obtener.
 * <br>
 * <br>
 * Los métodos de esta clase que alzan excepciones de tipo BaseDatosException son mensajes de error
 * para mostrar al usuario.
 */
public class Deserializador {
	private FileInputStream fileInputStream;
	private ObjectInputStream objectInputStream;

	public Deserializador(File archivo) throws BaseDatosException {
		// Instanciar FileInputStream
		// Si da error, entonces asignarle null y retornar para no continuar con el resto del constructor
		try {
			fileInputStream = new FileInputStream(archivo);
		} catch (FileNotFoundException e) {
			fileInputStream = null;
			return;
		}

		// instanciar ObjectInputStream
		// si da error, alzar una excepción con un mensaje explicando que ha ocurrido un error.
		try {
			objectInputStream = new ObjectInputStream(fileInputStream);
		} catch (IOException e) {
			throw new BaseDatosException("Ha ocurrido un error leyendo el archivo de datos", e);
		}
	}

	/**
	 * Utilice este método en un if antes de comenzar a leer objetos,
	 * devuelve true cuando hay datos por leer, false cuando no los hay.
	 */
	public boolean existenDatos() {
		return fileInputStream != null;
	}

	public Object leerObjeto() throws BaseDatosException {
		// lee el proximo objeto en el objectInputStream
		try {
			return objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			throw new BaseDatosException("Ha ocurrido un error leyendo el objeto serializado", e);
		}
	}

	public void close() throws BaseDatosException {
		// cierra las conexiones al archivo
		try {
			if (objectInputStream != null)
				objectInputStream.close();
			if (fileInputStream != null)
				fileInputStream.close();
		} catch (IOException e) {
			throw new BaseDatosException("Ha ocurrido un error cerrando los archivos de datos", e);
		}
	}
}
