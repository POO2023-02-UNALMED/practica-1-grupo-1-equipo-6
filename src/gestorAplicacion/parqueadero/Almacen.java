/*
 Funcionalidad del módulo: contiene la clase Almacen que se encarga de guardar los productos destinados para la venta
 Componentes del módulo: Almacen
 Autores: Sofía, Sebastián
*/

package gestorAplicacion.parqueadero;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Almacen guarda los productos y el inventario que son utilizados en los diferentes servicios del parqueadero.
 */
public class Almacen implements Serializable {
	private static final long serialVersionUID = 1L;

	private int capacidadMaxima;
	private static List<Producto> inventarioBase = new ArrayList<Producto>(); // lista con todos los tipos de productos, se comparte entre todos los objetos
	private List<Producto> inventario = new ArrayList<Producto>(); // lista con los productos alojados en cada almacen
	
	public Almacen(int capMax) {
		this.capacidadMaxima = capMax;
	}
	
	public void setCapacidadMaxima(int capMax) {
		this.capacidadMaxima = capMax;
	}
	public int getCapacidadMaxima() {
		return this.capacidadMaxima;
	}
	public static void setInventarioBase(List<Producto> inventarioBase) {
		Almacen.inventarioBase = inventarioBase;
	}
	public static List<Producto> getInventarioBase() {
		return Almacen.inventarioBase;
	}
	public void setInventario(List<Producto> inventario) {
		this.inventario = inventario;
	}
	public List<Producto> getInventario() {
		return this.inventario;
	}
	
	public void agregarProducto(Producto producto) {
		this.inventario.add(producto);
	}
	
	//metodo que retorna la primer ocurrencia de un producto que sea del tipo del parametro pasado y lo elimina del inventario
	public Producto conseguirProducto(TipoProducto tipoProducto) {
		for (Producto producto : this.inventario) {
			if (producto.getTipo().equals(tipoProducto)) {
				this.inventario.remove(producto);
				return producto;
			}
		}
		return null;
	}
	
	//metodo que se encarga de regresar el valor de un producto desde el inventario base
	public static double cotizarProducto(TipoProducto producto) {
		double r = 0;
		for (Producto p : Almacen.getInventarioBase()) {
			if (p.getTipo().equals(producto)) {
				r = p.getPrecio();
				break;
			}
		}
		return r;
	}
	
	//metodo que retorna true si existe por lo menos una instancia del producto preguntado, sino retorna false
	public boolean existeProducto(TipoProducto producto) {
		for (Producto p : this.inventario) {
			if (p.getTipo().equals(producto)) {
				return true;
			}
		}
		return false;
	}

}
