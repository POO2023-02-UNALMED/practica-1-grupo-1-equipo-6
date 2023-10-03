// Sebastián
// Alejandro

package gestorAplicacion.parqueadero;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *Clase que instancia un parqueadero con servicio de parqueo, taller y concesionario.
 */
public class Parqueadero implements Serializable {
	private static final long serialVersionUID = 1L;

	private int plazasTotales;
	private int plazasDisponibles;
	private double tarifa;
	private ArrayList<Plaza> plazas = new ArrayList<Plaza>();
	
	public Parqueadero() {
		
	}
	
	//Getters and Setters
	public int getPlazasTotales() {
		return this.plazasTotales;
	}
	public void setPlazasTotales(int plazasTotales){
		this.plazasTotales = plazasTotales;
	}
	public int getPlazasDisponibles() {
		return this.plazasDisponibles;
	}
	public void setPlazasDisponibles(int plazasDisponibles) {
		this.plazasDisponibles = plazasDisponibles;
	}
	public double getTarifa() {
		return this.tarifa;
	}
	public void setTarifa(double tarifa) {
		this.tarifa = tarifa;
	}
	
}
