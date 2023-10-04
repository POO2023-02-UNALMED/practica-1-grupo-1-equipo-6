package gestorAplicacion.parqueadero;

public class Factura {
	private int numeroFactura;
	private String fecha;
	private double precio;
	private int cantidad;
	
	public Factura(int numeroFactura,String fecha,double precio, int cantidad) {
		this.numeroFactura = numeroFactura;
		this.fecha = fecha;
		this.precio = precio;
		this.cantidad = cantidad;
		
	}

	public int getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(int numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
}
