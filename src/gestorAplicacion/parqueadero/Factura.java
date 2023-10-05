//Sara, Sofía

package gestorAplicacion.parqueadero;

import gestorAplicacion.personas.*;

public class Factura {
	private String tipoFactura; //Puede ser de ingreso, taller, compra de carro, venta de carro.
	private int numeroFactura;
	private String fecha;
	private double precio;
	private int cantidad;
	private Cliente cliente;
	private Empleado empleado;
	
	public Factura(String tipoFac, Cliente cliente, Empleado empleado, int numeroFactura,String fecha,double precio, int cantidad) {
		this.tipoFactura = tipoFac;
		this.numeroFactura = numeroFactura;
		this.fecha = fecha;
		this.precio = precio;
		this.cantidad = cantidad;
		this.cliente = cliente;
		this.empleado = empleado;
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
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public Empleado getEmpleado() {
		return this.empleado;
	}
	
	public void setTipoFactura(String tipoF) {
		this.tipoFactura = tipoF;
	}
	public String getTipoFactura() {
		return this.tipoFactura;
	}
	
}
