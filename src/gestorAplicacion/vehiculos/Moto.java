// Sebastián
// Alejandro

package gestorAplicacion.vehiculos;

import gestorAplicacion.personas.Cliente;
import gestorAplicacion.parqueadero.Producto;
import gestorAplicacion.parqueadero.TipoProducto;

import java.io.Serializable;

/**
 * Clase que instancia una Moto.
 */
public class Moto extends Vehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	private TipoVehiculo tipo; //si es de altoCC o normal
	private int cilindraje;
	private Producto motor;
	private Producto transmision;
	private Producto acelerador;
	private Producto freno;
	private Producto bateria;
	private Producto cadena;
	private Producto pedales; //clutch y pedales de cambio
	private Producto amortiguador;
	private Producto[] depositos; //array con los depositos de la moto(gasolina, liquidos, aceite)
	private Producto[] llantas; //array que contiene las llantas del vehiculo
	private Producto[] rines; //array que contiene los rines del vehiculo
	
	
	public Moto(String placa, Cliente dueno, String marca, String color, String modelo, TipoVehiculo tipo, int cilindraje) {
		super(placa, dueno, marca, color, modelo);
		this.tipo = tipo;
		this.cilindraje = cilindraje;
		this.motor = this.inicializarProducto(TipoProducto.MOTOR);
		this.transmision = this.inicializarProducto(TipoProducto.TRANSMISION);
		this.acelerador = this.inicializarProducto(TipoProducto.ACELERADOR);
		this.freno = this.inicializarProducto(TipoProducto.FRENO);
		this.cadena = this.inicializarProducto(TipoProducto.CADENA);
		this.pedales = this.inicializarProducto(TipoProducto.PEDALES);
		this.bateria = this.inicializarProducto(TipoProducto.BATERIA);
		this.amortiguador = this.inicializarProducto(TipoProducto.AMORTIGUADOR);
		this.inicializarDepositos();
		this.inicializarLlantas();
		this.inicializarRines();
	}
	
	
	//getters and setters
	public String getTipo() {
		return this.tipo.name();
	}
	public void setTipo(TipoVehiculo tipo) {
		this.tipo = tipo;
	}
	public Producto[] getLlantas() {
		return llantas;
	}
	public void setLlantas(Producto[] llantas) {
		this.llantas = llantas;
	}
	public int getCilindraje() {
		return cilindraje;
	}
	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
	public Producto getMotor() {
		return motor;
	}
	public void setMotor(Producto motor) {
		this.motor = motor;
	}
	public Producto getTransmision() {
		return transmision;
	}
	public void setTransmision(Producto transmision) {
		this.transmision = transmision;
	}
	public Producto getAcelerador() {
		return acelerador;
	}
	public void setAcelerador(Producto acelerador) {
		this.acelerador = acelerador;
	}
	public Producto getFreno() {
		return freno;
	}
	public void setFreno(Producto freno) {
		this.freno = freno;
	}
	public Producto getCadena() {
		return cadena;
	}
	public void setCadena(Producto cadena) {
		this.cadena = cadena;
	}
	public Producto getPedales() {
		return pedales;
	}
	public void setPedales(Producto pedales) {
		this.pedales = pedales;
	}
	public Producto[] getDepositos() {
		return depositos;
	}
	public void setDepositos(Producto[] depositos) {
		this.depositos = depositos;
	}
	public Producto[] getRines() {
		return rines;
	}
	public void setRines(Producto[] rines) {
		this.rines = rines;
	}
	public Producto getBateria() {
		return bateria;
	}
	public void setBateria(Producto bateria) {
		this.bateria = bateria;
	}
	public Producto getAmortiguador() {
		return amortiguador;
	}
	public void setAmortiguador(Producto amortiguador) {
		this.amortiguador = amortiguador;
	}


	//metodo que crea dos Productos tipo llanta y los agrega al array this.llantas
	private void inicializarLlantas(){
		this.llantas = new Producto[2]; // se asigna a this.llantas un array de tipo Producto con tamaño 2
		for (int i = 0; i < 2; i++) {
			this.llantas[i] = new Producto(TipoProducto.LLANTA, this.getMarca(), "Desgastado");
		}
	}
	
	//metodo que crea dos Productos tipo Rin y los agrega a this.rines
	private void inicializarRines(){
		this.rines = new Producto[2]; // se asigna a this.rines un array de tipo Producto con tamaño 2
		for (int i = 0; i < 2; i++) {
			this.rines[i] = new Producto(TipoProducto.RIN, this.getMarca(), "Desgastado");
		}
	}
	
	//metodo que crea los depositos y los asigna a this.depositos
	private void inicializarDepositos() {
		this.depositos = new Producto[3];
		this.depositos[0] = new Producto(TipoProducto.GASOLINA, this.getMarca(), "Desgastado");
		this.depositos[1] = new Producto(TipoProducto.ACEITE, this.getMarca(), "Desgastado");
		this.depositos[2] = new Producto(TipoProducto.LIQUIDOS, this.getMarca(), "Desgastado");
	}
	
	//metodo para asignar un solo producto
	private Producto inicializarProducto(TipoProducto tipo) {
		return new Producto(tipo, this.getMarca(), "Desgastado");
	}
}
