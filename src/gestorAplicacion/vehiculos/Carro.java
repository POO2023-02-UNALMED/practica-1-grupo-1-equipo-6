// Alejandro Arias Orozco

package gestorAplicacion.vehiculos;

import gestorAplicacion.personas.Cliente;

import java.io.Serializable;

public class Carro extends Vehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	public Carro(String placa, Cliente dueno, String marca, String color, String modelo) {
		super(placa, dueno, marca, color, modelo);
	}
}
