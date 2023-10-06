// Alejandro Arias Orozco

package baseDatos;

/**
 * Excepción que alzarán las clases del paquete baseDatos. El mensaje será para mostrarlo al usuario.
 */
public class BaseDatosException extends Exception {
	public BaseDatosException(String message, Throwable cause) {
		super(message, cause);
	}
}
