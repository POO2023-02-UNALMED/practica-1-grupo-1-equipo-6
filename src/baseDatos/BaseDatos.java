// Alejandro Arias Orozco

package baseDatos;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Esta clase simplemente se encarga de compartir el valor de RUTA_ARCHIVO en sus clases hijas.
 */
public abstract class BaseDatos {
	protected static final Path RUTA_ARCHIVO = Paths.get(".", "src", "baseDatos", "temp", "datos.txt");
}
