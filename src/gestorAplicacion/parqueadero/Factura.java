//Sara, Sofía

package gestorAplicacion.parqueadero;

import gestorAplicacion.personas.*;

import java.io.Serializable;
import java.util.HashMap;
import java.time.*;

public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	private HashMap<String, Integer> servicios; //servicios prestados(parqueadero, taller y ventas de repuestos o carros)
	private int numeroFactura;
	private LocalDate fecha;
	private double precio;
	private Cliente cliente;
	private Empleado empleado;
	
	public Factura(Cliente cliente, Empleado empleado, int numeroFactura, double precio, int cantidad) {
		this.numeroFactura = numeroFactura;
		this.fecha = LocalDate.now();
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
		return fecha.toString();
	}
	public void setFecha(LocalDate fecha) {
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
