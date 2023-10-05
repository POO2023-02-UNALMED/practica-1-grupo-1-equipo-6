// Sofía
//sara  Isabel

package gestorAplicacion.personas;

import java.util.ArrayList;
import gestorAplicacion.vehiculos.Vehiculo;

public class Cliente extends Persona{
	private boolean discapacitado;
    private ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();

	public Cliente(String nombre, long cedula, int telefono, String correo, String direccion, boolean discapacitado) {
		super(nombre, cedula, telefono, correo, direccion);
		this.discapacitado = discapacitado;
	}
	
    public void agregarVehiculo(Vehiculo vehiculo) {
    	this.vehiculos.add(vehiculo);
    }
    
    public void setDiscapacidad(boolean disc) {
    	this.discapacitado = disc;
    }
    public boolean isDiscapacidad() {
    	return this.discapacitado;
    }
}
