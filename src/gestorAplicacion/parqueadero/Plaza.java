//Sofía

package gestorAplicacion.parqueadero;

import java.util.ArrayList;
import java.util.List;

import gestorAplicacion.vehiculos.Vehiculo;

public class Plaza {
	private boolean discapacitado;  //Si es una plaza para clientes discapacitados o no
	private int numeroPlaza;
	private String estado;  //Disponible u ocupado
	private Vehiculo vehiculo; // El vehículo que lo está ocupando
	private static List<Plaza> plazasTotales = new ArrayList<Plaza>();
	
	public Plaza(int numPlaza, String estado, boolean discapacitado, Vehiculo vehi) {
		this.numeroPlaza = numPlaza;
		this.estado = estado;
		this.discapacitado = discapacitado;
		this.vehiculo = vehi;
		Plaza.plazasTotales.add(this);
		
	}
	
	
	
	

}
