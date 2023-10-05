package gestorAplicacion.parqueadero;

import java.util.ArrayList;
import java.util.List;

public class Almacen {
	private int capacidadMaxima;
	private static List<Producto> inventario = new ArrayList<Producto>();
	
	public Almacen(int capMax) {
		this.capacidadMaxima = capMax;
	}
	
	public void setCapacidadMaxima(int capMax) {
		this.capacidadMaxima = capMax;
	}
	public int getCapacidadMaxima() {
		return this.capacidadMaxima;
	}
	
	public void setInventario(List<Producto> inventario) {
		Almacen.inventario = inventario;
	}
	public List<Producto> getInventario() {
		return Almacen.inventario;
	}
	
	public void agregarProducto(Producto producto) {
		Almacen.inventario.add(producto);
	}
	
	
//Se guardan los productos creados y se tiene inventario de ventas

}
