//Sara, Sofía

package gestorAplicacion.parqueadero;

import gestorAplicacion.personas.*;

import java.io.Serializable;
import java.util.HashMap;
import java.time.*;

public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	private HashMap<String, Long> servicios; //servicios prestados(parqueadero, taller y ventas de repuestos o carros)
	private int numeroFactura;
	private LocalDate fecha;
	private long precioTotal;
	private Cliente cliente;
	private static int facturasCreadas; //atributo que lleva el conteo de facturas creadas, para asginar numeroFactura en las instancias
	
	public Factura(Cliente cliente) {
		this.numeroFactura = ++Factura.facturasCreadas;
		this.fecha = LocalDate.now();
		this.precioTotal = 0;
		this.cliente = cliente;
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

	public double getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(long precioTotal) {
		this.precioTotal = precioTotal;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Cliente getCliente() {
		return this.cliente;
	}
	public static int getFacturasCreadas() {
		return facturasCreadas;
	}
	public static void setFacturasCreadas(int facturasCreadas) {
		Factura.facturasCreadas = facturasCreadas;
	}
	
	
	//metodos para manipular el hashmap
	//metodo que agrega productos comprados y el numero de estos productos
	public void agregarProducto(Producto producto, int cantidad) {
		this.servicios.put(cap(producto.getTipo().name()), this.servicios.getOrDefault(cap(producto.getTipo().name()), 0L) + cantidad);
	}
	
	//metodo que agrega un servicio y su valor
	public void agregarServicio(String servicio) {
		this.servicios.put(servicio, 0L);
	}
	
	public String toString() {
		return "Cliente: " + this.cliente.getNombre() + "		" + this.fecha.toString() +"\n"
				+ this.servicios;
	}
	
	//metodo que se encarga de capitalizar una palabra jajaj, para usarlo en los metodos para el HashMap
	private static String cap(String palabra) {
        return Character.toUpperCase(palabra.charAt(0)) + palabra.substring(1).toLowerCase();
    }
}
