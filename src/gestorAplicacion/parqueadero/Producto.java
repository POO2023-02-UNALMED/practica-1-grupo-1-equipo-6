//Sofía

package gestorAplicacion.parqueadero;

public class Producto {
	private String tipoRepuesto;
	private long precio;
	private String calidad;
	private String marca;
	
	public Producto(String tipoR, long precio,String calidad, String marca) {
		this.tipoRepuesto = tipoR;
		this.precio = precio;
		this.calidad = calidad;
		this.marca = marca;
	}
	
	public void setTipoRepuesto(String rep) {
		this.tipoRepuesto = rep;
	}
	public String getTipoRepuesto() {
		return this.tipoRepuesto;
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

}
