// Alejandro Arias Orozco

package gestorAplicacion.vehiculos;

import gestorAplicacion.personas.Cliente;

public class Vehiculo {
	private String placa;
	private Cliente dueno;

	public Vehiculo(String placa, Cliente dueno) {
		this.placa = placa;
		this.dueno = dueno;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Cliente getDueno() {
		return dueno;
	}

	public void setDueno(Cliente dueno) {
		this.dueno = dueno;
	}
}
