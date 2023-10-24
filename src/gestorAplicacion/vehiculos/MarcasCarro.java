/**
 * Funcionalidad del módulo: contiene un enum con las marcas de carros aceptadas en el parqueadero,
 * ya sea para ingresar un vehiculo, comprar o venderlo, entre otras funciones
 Componentes del módulo: MarcasCarro
 */

package gestorAplicacion.vehiculos;

public enum MarcasCarro {
	TOYOTA(5), MAZDA(4), CHEVROLET(3), KIA(2), RENAULT(1);
	
	private int ordenPrecio;
	private MarcasCarro(int ordenPrecio) {
		this.ordenPrecio = ordenPrecio;
	}
	
	public int getOrdenPrecio() {
		return this.ordenPrecio;
	}
}
