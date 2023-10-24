/**
 * Funcionalidad del modulo: Este modulo contiene la clase Factura y su funcion es ofrecer facturacion a los clientes del parqueadero
 * Componentes del modulo: Clase Factura
 * Autores: Sara, Sofia
 */

package gestorAplicacion.parqueadero;

import gestorAplicacion.personas.*;

import java.io.Serializable;
import java.util.HashMap;
import java.time.*;

/**
 * Clase que representa una factura y se encarga de guardar la informacion de los servicios que ha tomado el cliente
 */
public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	private HashMap<String, Double> servicios; //servicios prestados(parqueadero, taller y ventas de repuestos o carros)
	private int numeroFactura;
	private LocalDate fecha;
	private double precioTotal;
	private Cliente cliente;
	private LocalTime horaIngreso; // hora ingreso del vehiculo al parqueadero
	private static int facturasCreadas; //atributo que lleva el conteo de facturas creadas, para asginar numeroFactura en las instancias
	private static HashMap<String, Double> valorServicios = new HashMap<>(); // hashmap que contiene los servicios y su costo
	
	static {
		Factura.valorServicios.put("Revision general", 100000.0);
		Factura.valorServicios.put("Cambio repuesto", 50000.0);
	}
	
	public Factura(Cliente cliente) {
		this.numeroFactura = ++Factura.facturasCreadas;
		this.fecha = LocalDate.now();
		this.horaIngreso = LocalTime.now();
		this.precioTotal = 0;
		this.cliente = cliente;
		this.servicios = new HashMap<>();
		cliente.setFactura(this);
	}

	//getters and setters
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
	public void setPrecioTotal(double precioTotal) {
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
	public HashMap<String, Double> getServicios() {
		return this.servicios;
	}
	
	/**
	 * Metodo que agrega un producto comprado y su precio de compra a la factura del cliente, en caso de que este producto ya se encuentre en la factura se suman sus valores de
	 * compra
	 * @param producto
	 * @param cantidad
	 */
	public void agregarProducto(Producto producto, double cantidad) {
		this.servicios.put("Compra de " + cap(producto.getTipo().name()) + "s", this.servicios.getOrDefault(cap(producto.getTipo().name()), (double) 0) + cantidad);
	}
	
	/**
	 * Metodo que agrega un servicio tomado a la factura del cliente, en caso de que este servicio ya se encuentre se suman sus valores
	 * @param servicio
	 * @param cantidad
	 */
	public void agregarServicio(String servicio, double cantidad) {
		this.servicios.put(servicio, this.servicios.getOrDefault(servicio, (double) 0) + cantidad);
	}
	
	@Override
	public String toString() {
		String s = "";
		for (HashMap.Entry<String, Double> entry : this.servicios.entrySet()) {	
			s += entry.getKey() + ": " + entry.getValue() + "\n";
		}
		return "Factura N°" + this.numeroFactura + "		" + this.fecha.toString() + "\nCliente: " + this.cliente.getNombre() +"\n"
				+ "Servicios: \n" + s;
	}
	
	/**
	 * Metodo que se encarga de capitalizar una palabra
	 * @param palabra
	 * @return palabra capitalizada
	 */
	private static String cap(String palabra) {
		return Character.toUpperCase(palabra.charAt(0)) + palabra.substring(1).toLowerCase();
	}
}
