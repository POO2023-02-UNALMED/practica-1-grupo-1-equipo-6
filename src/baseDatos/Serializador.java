/*
 Funcionalidad del módulo: contiene la clase Serializador que se encarga de escribir los objetos en el archivo de datos.
 Componentes del módulo: Serializador
 Autores: Alejandro
*/

package baseDatos;

import java.io.*;

/**
 * Provee escritura de los datos en un archivo
 * <br>
 * <br>
 * Tiene un único método escribirObjetos, el cual recibe como único parámetro un objeto
 * que se quiere escribir en el archivo, y éste método se encarga de todo el proceso.
 */
public class Serializador {
	/**
	 * Escribe el objeto serializable en el archivo recibido por parámetro.
	 */
	public void escribirObjeto(File archivo, Serializable objeto) throws BaseDatosException {
		// crear las carpetas padre de archivo si no existen
		archivo.getParentFile().mkdirs();
		// crear el archivo si no existe
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

		// escribir el objeto en el archivo
		try {
			objectOutputStream.writeObject(objeto);
		} catch (IOException e) {
			throw new BaseDatosException("Ha ocurrido un error escribiendo los datos del objeto", e);
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
