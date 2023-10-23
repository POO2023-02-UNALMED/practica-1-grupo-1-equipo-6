// Sofía
//sara  Isabel

package gestorAplicacion.personas;

import java.io.Serializable;
import java.util.ArrayList;
import gestorAplicacion.vehiculos.Vehiculo;
import gestorAplicacion.parqueadero.Factura;

public class Cliente extends Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean discapacitado;
    private ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
    private Factura factura;
    
	public Cliente(String nombre, long cedula, long telefono, String correo, String direccion, boolean discapacitado) {
		super(nombre, cedula, telefono, correo, direccion);
		this.discapacitado = discapacitado;
		this.factura = new Factura(this);
	}

    public void agregarVehiculo(Vehiculo vehiculo) {
    	this.vehiculos.add(vehiculo);
    }
    
    public void setDiscapacitado(boolean disc) {
    	this.discapacitado = disc;
    }
    public boolean isDiscapacitado() {
    	return this.discapacitado;
    }
    public void setFactura(Factura factura) {
    	this.factura = factura;
    }
    public Factura getFactura() {
    	return this.factura;
    }
	public ArrayList<Vehiculo> getVehiculos() {
		return vehiculos;
	}
	public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
    
}
