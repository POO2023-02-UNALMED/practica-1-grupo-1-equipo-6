/**
 * Funcionalidad del módulo: contiene un enum con las marcas de carros aceptadas en el parqueadero,
 * ya sea para ingresar un vehiculo, comprar o venderlo, entre otras funciones
 Componentes del módulo: MarcasCarro
 */

package gestorAplicacion.vehiculos;

public enum MarcasCarro {
	TOYOTA(50000000.0), MAZDA(50000000.0), CHEVROLET(50000000.0), KIA(50000000.0), RENAULT(50000000.0);
	
	private double precioMaximo;
	private MarcasCarro(double precioMaximo) {
		this.precioMaximo = precioMaximo;
	}
	
	public double getPrecioMaximo() {
		return this.precioMaximo;
	}
}
