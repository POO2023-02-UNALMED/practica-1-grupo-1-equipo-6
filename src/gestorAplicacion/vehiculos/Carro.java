// Alejandro Arias Orozco
// Sebastian
package gestorAplicacion.vehiculos;

import gestorAplicacion.personas.Cliente;
import gestorAplicacion.parqueadero.Producto;

import java.io.Serializable;

public class Carro extends Vehiculo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String tipo;//mecanico o automatico
	private int puestos;
	private Producto motor;
	private Producto transmision;
	private Producto acelerador;
	private Producto freno;
	private Producto bateria;
	private Producto pedal;//clutch 
	private Producto[] depositos;
	private Producto[] llantas;
	private Producto[] rines;
	private Producto[] amortiguadores;
	
	
	public Carro(String placa, Cliente dueno, String marca, String color, String modelo, String tipo, int puestos) {
		super(placa, dueno, marca, color, modelo);
		this.tipo = tipo;
		this.puestos = puestos;
		this.motor = this.inicializarProducto("Motor");
		this.transmision = this.inicializarProducto("Transmision");
		this.acelerador = this.inicializarProducto("Acelerador");
		this.freno = this.inicializarProducto("Freno");
		this.bateria = this.inicializarProducto("Bateria");
		this.pedal = this.inicializarProducto("Pedal");
		this.inicializarDepositos();
		this.inicializarLlantas();
		this.inicializarRines();
		this.inicializarAmortiguadores();
	}

	
	//getters and setters
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getPuestos() {
		return this.puestos;
	}
	public void setPuestos(int puestos) {
		this.puestos = puestos;
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
	public Producto getBateria() {
		return bateria;
	}
	public void setBateria(Producto bateria) {
		this.bateria = bateria;
	}
	public Producto getPedal() {
		return pedal;
	}
	public void setPedal(Producto pedal) {
		this.pedal = pedal;
	}
	public Producto[] getDepositos() {
		return depositos;
	}
	public void setDepositos(Producto[] depositos) {
		this.depositos = depositos;
	}
	public Producto[] getLlantas() {
		return llantas;
	}
	public void setLlantas(Producto[] llantas) {
		this.llantas = llantas;
	}
	public Producto[] getRines() {
		return rines;
	}
	public void setRines(Producto[] rines) {
		this.rines = rines;
	}
	public Producto[] getAmortiguadores() {
		return amortiguadores;
	}
	public void setAmortiguadores(Producto[] amortiguadores) {
		this.amortiguadores = amortiguadores;
	}


	//metodo que crea cuatro Productos tipo llanta y los agrega al array this.llantas
	private void inicializarLlantas(){
		this.llantas = new Producto[4]; // se asigna a this.llantas un array de tipo Producto con tamaño 4
		for (int i = 0; i < 4; i++) {
			this.llantas[i] = new Producto("Llanta", this.getMarca(), "Desgastado");
		}
	}
	//metodo que crea cuatro Productos tipo Rin y los agrega a this.rines
	private void inicializarRines(){
		this.llantas = new Producto[4]; // se asigna a this.rines un array de tipo Producto con tamaño 4
		for (int i = 0; i < 4; i++) {
			this.rines[i] = new Producto("Rin", this.getMarca(), "Desgastado");
		}
	}
		
	//metodo que crea los depositos y los asigna a this.depositos
	private void inicializarDepositos() {
		this.depositos = new Producto[3];
		this.depositos[0] = new Producto("Tanque de gasolina", this.getMarca(), "Desgastado");
		this.depositos[1] = new Producto("Desposito de aceite", this.getMarca(), "Desgastado");
		this.depositos[2] = new Producto("Desposito de liquidos", this.getMarca(), "Desgastado");
		}
		
	//metodo para asignar un solo producto
	private Producto inicializarProducto(String tipo) {
		return new Producto(tipo, this.getMarca(), "Desgastado");
	}
	
	//metodo para crear los amortiguadores
	private void inicializarAmortiguadores() {
		this.amortiguadores = new Producto[4];
		for (int i = 0; i < 4; i++) {
			this.amortiguadores[i] = new Producto("Amortiguador", this.getMarca(), "Desgastado");
		}
	}
}
