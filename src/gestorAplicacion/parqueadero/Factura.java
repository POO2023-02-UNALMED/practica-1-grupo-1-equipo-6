//Sara, Sofía

package gestorAplicacion.parqueadero;

import gestorAplicacion.personas.*;
import java.util.HashMap;

public class Factura {
	private HashMap<String, Integer> servicios; //servicios prestados(parqueadero, taller y ventas de repuestos o carros)
	private int numeroFactura;
	private String fecha;
	private double precio;
	private Cliente cliente;
	private Empleado empleado;
	
	public Factura(Cliente cliente, Empleado empleado, int numeroFactura, String fecha, double precio, int cantidad) {
		this.numeroFactura = numeroFactura;
		this.fecha = fecha;
		this.precio = precio;
		this.cliente = cliente;
		this.empleado = empleado;
		this.servicios = new HashMap<>();
	}

	public int getNumeroFactura() {
		return numeroFactura;
	}
	public void setNumeroFactura(int numeroFactura) {
		this.numeroFactura = numeroFactura;
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
	
	//metodos para manipular el hashmap
	
	//metodo que agrega productos comprados y el numero de estos productos
	public void agregarProducto(Producto producto, int cantidad) {
		this.servicios.put(producto.getTipo(), this.servicios.getOrDefault(producto.getTipo(), 0) + cantidad);
	}
	
	//metodo que agrega un servicio y su valor
	public void agregarServicio(String servicio, int valor) {
		this.servicios.put(servicio, valor);
	}
}
