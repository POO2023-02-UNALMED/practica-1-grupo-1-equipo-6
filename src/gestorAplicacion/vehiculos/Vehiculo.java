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
		this.placa = placa.toUpperCase();
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	/**
	 * Este método retorna true si el vehículo se encuentra en el parqueadero, false si no lo está.
	 * El vehículo se considera parqueado si el atributo plaza no es null.
	 */
	public boolean estaParqueado() {
		return this.plaza != null;
	}
}
