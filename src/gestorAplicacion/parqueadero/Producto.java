//Sofía
//Sebastian
package gestorAplicacion.parqueadero;

import java.io.Serializable;

public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	private TipoProducto tipo;
	private long precio;
	private String calidad;
	private String estado;
	private String marca;
	
	//sobrecargar el constructor para tener uno para productos de venta y otro para los productos que contienen los vehiculos
	public Producto(TipoProducto tipo, long precio, String marca, String calidad, String estado) {
		this.tipo = tipo;
		this.precio = precio;
		this.calidad = calidad;
		this.marca = marca;
		this.estado = estado;
	}
	//constructor para los vehiculos
	public Producto(TipoProducto tipo, String marca, String estado) {
		this(tipo, 0, marca, "Primera", estado);
	}
	
	public void setTipo(TipoProducto tipo) {
		this.tipo = tipo;
	}
	public String getTipo() {
		return this.tipo.name();
	}
	
	public void setPrecio(long precio) {
		this.precio = precio;
	}
	public long getPrecio() {
		return this.precio;
	}
	
	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}
	public String getCalidad() {
		return this.calidad;
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
