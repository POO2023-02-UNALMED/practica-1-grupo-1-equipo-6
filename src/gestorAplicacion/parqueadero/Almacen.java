package gestorAplicacion.parqueadero;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	
	public void setInventarioBase(List<Producto> inventarioBase) {
		Almacen.inventarioBase = inventarioBase;
	}
	public List<Producto> getInventarioBase() {
		return Almacen.inventarioBase;
	}
	
	public void agregarProducto(Producto producto) {
		this.inventario.add(producto);
	}
	
	//metodo que se encarga de regresar el valor de un producto desde el inventario base
	public static double cotizarProducto(TipoProducto producto) {
		double r = 0;
		for (Producto p : Almacen.inventarioBase) {
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
			if (p.getTipo() == producto) {
				return true;
			}
		}
		return false;
	}

}
