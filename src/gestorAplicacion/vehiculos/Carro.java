// Alejandro Arias Orozco
// Sebastian

package gestorAplicacion.vehiculos;

import gestorAplicacion.personas.Cliente;
import gestorAplicacion.parqueadero.Producto;
import gestorAplicacion.parqueadero.TipoEstado;
import gestorAplicacion.parqueadero.TipoProducto;


import java.io.Serializable;

public class Carro extends Vehiculo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private TipoVehiculo tipo;//mecanico o automatico
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
	private double precioVenta;
	private boolean discapacitado; //Si el carro está adecuado para personas discapacitadas
	
	
	public Carro(String placa, Cliente dueno, MarcasCarro marca, String color, String modelo, TipoVehiculo tipo, int puestos, boolean discapacitado) {
		super(placa, dueno, marca, color, modelo);
		this.tipo = tipo;
		this.puestos = puestos;
		this.motor = this.inicializarProducto(TipoProducto.MOTOR);
		this.transmision = this.inicializarProducto(TipoProducto.TRANSMISION);
		this.acelerador = this.inicializarProducto(TipoProducto.ACELERADOR);
		this.freno = this.inicializarProducto(TipoProducto.FRENO);
		this.bateria = this.inicializarProducto(TipoProducto.BATERIA);
		this.pedal = this.inicializarProducto(TipoProducto.PEDAL);
		this.inicializarDepositos();
		this.inicializarLlantas();
		this.inicializarRines();
		this.inicializarAmortiguadores();
		this.precioVenta = 0;
		this.discapacitado = discapacitado;
	}
	public Carro(String placa, Cliente dueno, MarcasCarro marca, String color, String modelo, TipoVehiculo tipo, int puestos, boolean discapacitado, long precioVenta) {
		this(placa, dueno, marca, color, modelo, tipo, puestos, discapacitado);
		this.precioVenta = precioVenta;
		motor.setEstado(TipoEstado.EXCELENTE_ESTADO);
		acelerador.setEstado(TipoEstado.EXCELENTE_ESTADO);
		freno.setEstado(TipoEstado.EXCELENTE_ESTADO);
		bateria.setEstado(TipoEstado.EXCELENTE_ESTADO);
		pedal.setEstado(TipoEstado.EXCELENTE_ESTADO);
		transmision.setEstado(TipoEstado.EXCELENTE_ESTADO);
		for (Producto p : depositos) {
			p.setEstado(TipoEstado.EXCELENTE_ESTADO);
		}
		for (Producto p : llantas) {
			p.setEstado(TipoEstado.EXCELENTE_ESTADO);
		}
		for (Producto p : rines) {
			p.setEstado(TipoEstado.EXCELENTE_ESTADO);
		}
		for (Producto p : amortiguadores) {
			p.setEstado(TipoEstado.EXCELENTE_ESTADO);
		}
	}
	
	
	//getters and setters

	public boolean isDiscapacitado(){
		return this.discapacitado;
	}
	public void setDiscapacitado(boolean discapacitado) {
    	this.discapacitado = discapacitado;
    }
	public double getPrecioVenta() {
		return this.precioVenta;
	}
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	public String getTipo() {
		return tipo.name();
	}
	public void setTipo(TipoVehiculo tipo) {
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

	@Override
	public String toString() {
		if (this.precioVenta != 0) { // para los carros para venta se imprime su valor
			return String.format("%s %s %s%n%s %d puestos%nPrecio: %d", cap(this.getMarca().name()), this.getModelo(), this.getColor(), cap(this.getTipo()), this.getPuestos(), this.getPrecioVenta());
		}
		return String.format("%s %s %s%n%s %d puestos%n", cap(this.getMarca().name()), this.getModelo(), this.getColor(), cap(this.getTipo()), this.getPuestos());

	}
	
	
	//metodo que crea cuatro Productos tipo llanta y los agrega al array this.llantas
	private void inicializarLlantas(){
		this.llantas = new Producto[4]; // se asigna a this.llantas un array de tipo Producto con tamaño 4
		for (int i = 0; i < 4; i++) {
			this.llantas[i] = new Producto(TipoProducto.LLANTA, this.getMarca(), inicializarEstado());
		}
	}
	//metodo que crea cuatro Productos tipo Rin y los agrega a this.rines
	private void inicializarRines(){
		this.rines = new Producto[4]; // se asigna a this.rines un array de tipo Producto con tamaño 4
		for (int i = 0; i < 4; i++) {
			this.rines[i] = new Producto(TipoProducto.RIN, this.getMarca(), inicializarEstado());
		}
	}
		
	//metodo que crea los depositos y los asigna a this.depositos
	private void inicializarDepositos() {
		this.depositos = new Producto[3];
		this.depositos[0] = new Producto(TipoProducto.GASOLINA, this.getMarca(), inicializarEstado());
		this.depositos[1] = new Producto(TipoProducto.ACEITE, this.getMarca(), inicializarEstado());
		this.depositos[2] = new Producto(TipoProducto.LIQUIDOS, this.getMarca(), inicializarEstado());
		}
		
	//metodo para asignar un solo producto
	private Producto inicializarProducto(TipoProducto tipo) {
		return new Producto(tipo, this.getMarca(), inicializarEstado());
	}
	
	//metodo para crear los amortiguadores
	private void inicializarAmortiguadores() {
		this.amortiguadores = new Producto[4];
		for (int i = 0; i < 4; i++) {
			this.amortiguadores[i] = new Producto(TipoProducto.AMORTIGUADOR, this.getMarca(), inicializarEstado());
		}
	}
	
	
	//metodo para generar el estado de manera randomizada (retorna  1<=int<=2)
	public TipoEstado inicializarEstado() {
		int numero = (int) (Math.random() * 3);
		return TipoEstado.segunNumero(numero);
	}
	
	//metodo que se encarga de capitalizar una palabra jajaj, para usarlo en los metodos para el HashMap
	private static String cap(String palabra) {
		return Character.toUpperCase(palabra.charAt(0)) + palabra.substring(1).toLowerCase();
	}
	
}
