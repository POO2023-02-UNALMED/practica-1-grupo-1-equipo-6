package gestorAplicacion;

/**
 * Esta interfaz la implementan clases que tienen una
 * forma de identificar objetos a través de un atributo.
 * La identificación debe ser única en su tipo de objeto.
 */
public interface Identificable<T> {
	/**
	 * Retorna la identificación del objeto.
	 */
	T getIdentificacion();

	/**
	 * Retorna true si el objeto tiene a `identificacion` como identificación.
	 * False en caso contrario.
	 */
	boolean tieneIdentificacion(T identificacion);
}
