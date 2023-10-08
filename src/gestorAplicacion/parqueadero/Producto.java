//Sofía
//Sebastian
package gestorAplicacion.parqueadero;

public class Producto {
	private String tipo;
	private long precio;
	private String calidad;
	private String estado;
	private String marca;
	
	//sobrecargar el constructor para tener uno para productos de venta y otro para los productos que contienen los vehiculos
	public Producto(String tipo, long precio, String calidad, String marca) {
		this.tipo = tipo;
		this.precio = precio;
		this.calidad = calidad;
		this.estado = "Perfecto";
		this.marca = marca;
	}
	//constructor para los vehiculos
	public Producto(String tipo, String marca, String estado) {
		this(tipo, 0, estado, marca);
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTipo() {
		return this.tipo;
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
