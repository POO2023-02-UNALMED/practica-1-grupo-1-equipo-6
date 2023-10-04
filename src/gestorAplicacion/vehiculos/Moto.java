// Sebastián
// Alejandro

package gestorAplicacion.vehiculos;

import gestorAplicacion.personas.Cliente;

/**
 * Clase que instancia una Moto.
 */
public class Moto extends Vehiculo{
	private String tipo; //si es de altoCC o normal
	
	
	public Moto(String placa, Cliente dueno, String tipo) {
		super(placa, dueno);
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
