// Alejandro Arias Orozco

package baseDatos;

/**
 * Excepción que alzarán subclases de BaseDatos. El mensaje será para mostrarlo al usuario.
 */
public class BaseDatosException extends Exception {
	public BaseDatosException(String message, Throwable cause) {
		super(message, cause);
	}
}
