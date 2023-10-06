// Sebastián
// Alejandro

package gestorAplicacion.vehiculos;

import gestorAplicacion.personas.Cliente;

import java.io.Serializable;

/**
 * Clase que instancia una Moto.
 */
public class Moto extends Vehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String tipo; //si es de altoCC o normal

	public Moto(String placa, Cliente dueno, String marca, String color, String modelo, String tipo) {
		super(placa, dueno, marca, color, modelo);
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
