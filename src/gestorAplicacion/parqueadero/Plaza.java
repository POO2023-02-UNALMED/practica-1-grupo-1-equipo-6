//Sofía

package gestorAplicacion.parqueadero;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import gestorAplicacion.vehiculos.Vehiculo;

public class Plaza implements Serializable {
	private static final long serialVersionUID = 1L;

	private int numeroPlaza;
	private boolean discapacitado;  //Si es una plaza para clientes discapacitados o no
	private String estado;  //Disponible u ocupado
	private Vehiculo vehiculo; // El vehículo que lo está ocupando
	private String tipo; //Si es de tipo carro o moto
	private static List<Plaza> plazasTotales = new ArrayList<Plaza>();
	
	public Plaza(int numPlaza, boolean discapacitado, Vehiculo vehi, String tipo) {
		this.numeroPlaza = numPlaza;
		this.estado = "Disponible";
		this.discapacitado = discapacitado;
		this.vehiculo = vehi;
		this.tipo = tipo;
		Plaza.plazasTotales.add(this);
		
	}
	
	public void setDiscapacitado(boolean disc) {
		this.discapacitado = disc;
	}
	public boolean getDiscapacitado() {
		return this.discapacitado;
	}
	
	public void setNumeroPlaza(int numeroP) {
		this.numeroPlaza = numeroP;
	}
	public int getNumeroPlaza() {
		return this.numeroPlaza;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getEstado() {
		return this.estado;
	}
	
	public void setVehiculo(Vehiculo vehi) {
		this.vehiculo = vehi;
		if (vehi == null) {
			this.estado = "Disponible";
		} else {
			this.estado = "No disponible";
		}
	}

	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	

}
