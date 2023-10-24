//Sofía
//Sebastian
package gestorAplicacion.parqueadero;

import java.io.Serializable;

import gestorAplicacion.vehiculos.MarcasCarro;

public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	private TipoProducto tipo;
	private double precio;
	private TipoEstado estado;
	private String marca;
	
	//sobrecargar el constructor para tener uno para productos de venta y otro para los productos que contienen los vehiculos
	public Producto(TipoProducto tipo, double precio, String marca, TipoEstado estado) {
		this.tipo = tipo;
		this.precio = precio;
		this.marca = marca;
		this.estado = estado;
	}
	//constructor para los vehiculos
	public Producto(TipoProducto tipo, String marca, TipoEstado estado) {
		this(tipo, (double) 0, marca, estado);
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
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getMarca() {
		return this.marca;
	}
	public TipoEstado getEstado() {
		return estado;
	}
	public void setEstado(TipoEstado estado) {
		this.estado = estado;
	}

}
