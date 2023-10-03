// Alejandro Arias Orozco

package gestorAplicacion.vehiculos;

import gestorAplicacion.parqueadero.Plaza;
import gestorAplicacion.personas.Cliente;

public class Vehiculo {
	/**
	 * La placa del vehículo
	 */
	private String placa;
	/**
	 * El cliente dueño de este vehículo
	 */
	private Cliente dueno;
	/**
	 * La plaza en la que se encuentra parqueado el vehículo,
	 * o null en caso de que no esté en el parqueadero
	 */
	private Plaza plaza;

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

	public Plaza getPlaza() {
		return plaza;
	}

	public void setPlaza(Plaza plaza) {
		this.plaza = plaza;
	}

	public boolean estaParqueado() {
		return this.plaza != null;
	}
}
