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
	private long precioTotal;
	private Cliente cliente;
	private LocalTime horaIngreso; // hora ingreso del vehiculo al parqueadero
	private static int facturasCreadas; //atributo que lleva el conteo de facturas creadas, para asginar numeroFactura en las instancias
	private static HashMap<String, Double> valorServicios = new HashMap<>(); // hashmap que contiene los servicios y su costo
	
	public Factura(Cliente cliente) {
		this.numeroFactura = ++Factura.facturasCreadas;
		this.fecha = LocalDate.now();
		this.horaIngreso = LocalTime.now();
		this.precioTotal = 0;
		this.cliente = cliente;
		this.servicios = new HashMap<>();
		cliente.setFactura(this);
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
	public LocalTime getHoraIngreso() {
		return horaIngreso;
	}
	public void setHoraIngreso(LocalTime horaIngreso) {
		this.horaIngreso = horaIngreso;
	}
	public static void setFacturasCreadas(int facturasCreadas) {
		Factura.facturasCreadas = facturasCreadas;
	}	
	public static HashMap<String, Double> getValorServicios() {
		return valorServicios;
	}
	public static void setValorServicios(HashMap<String, Double> valorServicios) {
		Factura.valorServicios = valorServicios;
	}
	public HashMap<String, Integer> getServicios() {
		return this.servicios;
	}

	//metodos para manipular el hashmap
	//metodo que agrega productos comprados y el numero de estos productos
	public void agregarProducto(Producto producto, int cantidad) {
		this.servicios.put("Compra de " + cap(producto.getTipo().name()), this.servicios.getOrDefault(cap(producto.getTipo().name()), 0) + cantidad);
	}
	
	//metodo que agrega un servicio y su cantidad
	public void agregarServicio(String servicio, int cantidad) {
		this.servicios.put(servicio, this.servicios.getOrDefault(servicio, 0) + cantidad);
	}
	
	public String toString() {
		String s = "";
		for (HashMap.Entry<String, Integer> entry : this.servicios.entrySet()) {	
			s += entry.getKey() + ": " + entry.getValue() + "\n";
		}
		return "Factura N°" + this.numeroFactura + "		" + this.fecha.toString() + "\nCliente: " + this.cliente.getNombre() +"\n"
				+ "Servicios: \n" + s;
	}

	//metodo que se encarga de capitalizar una palabra jajaj, para usarlo en los metodos para el HashMap
	private static String cap(String palabra) {
        return Character.toUpperCase(palabra.charAt(0)) + palabra.substring(1).toLowerCase();
    }
}
