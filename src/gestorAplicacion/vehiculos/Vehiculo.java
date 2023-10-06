// Alejandro Arias Orozco

package gestorAplicacion.vehiculos;

import gestorAplicacion.parqueadero.Plaza;
import gestorAplicacion.personas.Cliente;

import java.io.Serializable;

public class Vehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

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
	/**
	 * La marca del vehículo
	 */
	private String marca;
	/**
	 * El color del vehículo
	 */
	private String color;
	/**
	 * El modelo del vehículo
	 */
	private String modelo;

	public Vehiculo(String placa, Cliente dueno, String marca, String color, String modelo) {
		this.placa = placa.toUpperCase();
		this.dueno = dueno;
		this.marca = marca;
		this.color = color;
		this.modelo = modelo;
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
