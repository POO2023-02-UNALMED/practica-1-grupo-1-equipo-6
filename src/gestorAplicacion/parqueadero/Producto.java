//Sofía
//Sebastian
package gestorAplicacion.parqueadero;

import java.io.Serializable;

public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	private TipoProducto tipo;
	private final long precio;
	private String estado;
	private String marca;
	
	//sobrecargar el constructor para tener uno para productos de venta y otro para los productos que contienen los vehiculos
	public Producto(TipoProducto tipo, long precio, String marca, String estado) {
		this.tipo = tipo;
		this.precio = precio;
		this.marca = marca;
		this.estado = estado;
	}
	//constructor para los vehiculos
	public Producto(TipoProducto tipo, String marca, String estado) {
		this(tipo, 0L, marca, estado);
	}
	
	public void setTipo(TipoProducto tipo) {
		this.tipo = tipo;
	}
	public TipoProducto getTipo() {
		return this.tipo;
	}
	public double getPrecio() {
		return this.precio;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getMarca() {
		return this.marca;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
