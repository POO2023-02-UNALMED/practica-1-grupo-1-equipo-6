/*
 Funcionalidad del módulo: contiene la clase Almacen que se encarga de guardar los productos destinados para la venta
 Componentes del módulo: Almacen
 Autores: Sofía, Sebastián
*/

package gestorAplicacion.parqueadero;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Almacen guarda los productos y el inventario que son utilizados en los diferentes servicios del parqueadero.
 */
public class Almacen implements Serializable {
	private static final long serialVersionUID = 1L;

	private int capacidadMaxima;
	private static HashMap<TipoProducto, Double> inventarioBase = new HashMap<>(); // lista con todos los tipos de productos, se comparte entre todos los objetos
	private List<Producto> inventario = new ArrayList<Producto>(); // lista con los productos alojados en cada almacen
	
	static {
		Almacen.inicializarInventarioBase();
	}
	
	public Almacen(int capMax) {
		this.capacidadMaxima = capMax;
	}
	
	public void setCapacidadMaxima(int capMax) {
		this.capacidadMaxima = capMax;
	}
	public int getCapacidadMaxima() {
		return this.capacidadMaxima;
	}
	public static void setInventarioBase(HashMap<TipoProducto, Double> inventarioBase) {
		Almacen.inventarioBase = inventarioBase;
	}
	public static HashMap<TipoProducto, Double> getInventarioBase() {
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
	
	/**
	 * 
	 * @param tipoProducto
	 * @return
	 */
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
		r = Almacen.inventarioBase.get(producto);
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
	
	private static void inicializarInventarioBase() {
		Almacen.inventarioBase.put(TipoProducto.TRANSMISION, 400000.0);
		Almacen.inventarioBase.put(TipoProducto.AMORTIGUADOR, 400000.0);
		Almacen.inventarioBase.put(TipoProducto.LLANTA, 400000.0);
		Almacen.inventarioBase.put(TipoProducto.BATERIA, 400000.0);
		Almacen.inventarioBase.put(TipoProducto.ACELERADOR, 400000.0);
		Almacen.inventarioBase.put(TipoProducto.RIN, 400000.0);
		Almacen.inventarioBase.put(TipoProducto.PEDAL, 400000.0);
		Almacen.inventarioBase.put(TipoProducto.ACEITE, 400000.0);
		Almacen.inventarioBase.put(TipoProducto.CADENA, 400000.0);
		Almacen.inventarioBase.put(TipoProducto.GASOLINA, 400000.0);
		Almacen.inventarioBase.put(TipoProducto.MOTOR, 400000.0);
		Almacen.inventarioBase.put(TipoProducto.FRENO, 400000.0);
		Almacen.inventarioBase.put(TipoProducto.PEDALES, 400000.0);
		Almacen.inventarioBase.put(TipoProducto.LIQUIDOS, 400000.0);
	}

}
