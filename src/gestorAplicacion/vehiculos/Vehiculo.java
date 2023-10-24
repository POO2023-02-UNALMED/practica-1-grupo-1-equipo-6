/*
 Funcionalidad del módulo: contiene la clase Vehiculo que sirve de clase padre para Moto y Carro
 Componentes del módulo: Vehiculo
 Autores: Alejandro
*/

package gestorAplicacion.vehiculos;

import gestorAplicacion.Identificable;
import gestorAplicacion.parqueadero.Plaza;
import gestorAplicacion.personas.Cliente;

import java.io.Serializable;

/**
 * Vehiculo contiene atributos y código compartido entre Moto y Carro.
 */
public class Vehiculo implements Serializable, Identificable<String> {
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
		this.placa = normalizarPlaca(placa);
		this.dueno = dueno;
		this.marca = marca;
		this.color = color;
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = normalizarPlaca(placa);
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

	/**
	 * Retorna true si el vehículo fue registrado por el cliente pasado como parámetro.
	 */
	public boolean registradoPor(Cliente cliente) {
		return dueno.getCedula() == cliente.getCedula();
	}

	/**
	 * Devuelve la placa del vehículo
	 */
	@Override
	public String getIdentificacion() {
		return placa;
	}

	/**
	 * Devuelve true si la placa de este vehículo coincide con la placa pasada como parámetro.
	 */
	@Override
	public boolean tieneIdentificacion(String identificacion) {
		String placa = normalizarPlaca(identificacion);
		return this.placa.equals(placa);
	}

	/**
	 * Devuelve la placa pasada como parámetro luego de hacerla mayúsculas y quitarle los espacios extra.
	 */
	public static String normalizarPlaca(String placa) {
		return placa.strip().toUpperCase();
	}
}
