// Sebastián
// Alejandro

package gestorAplicacion.parqueadero;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gestorAplicacion.vehiculos.*;
import gestorAplicacion.personas.*;

/**
 *Clase que instancia un parqueadero con servicio de parqueo, taller y concesionario.
 */
public class Parqueadero implements Serializable {
	private static final long serialVersionUID = 1L;

	private int plazasTotales;//capacidad total de plazas que tiene el parqueadero
	private int plazasDisponibles;
	private double tarifaCarro;//tarifa del servicio de parqueadero por hora para carro
	private double tarifaMoto;//tarifa del servicio de parqueadero por hora para moto
	private ArrayList<Plaza> plazas;
	private ArrayList<Empleado> empleados = new ArrayList<>(); //arraylist con todos los empleados del parqueadero(Taller, almacen, etc)
	private Almacen almacen; //almacen de los productos
	private Empleado administrador; // el administrador del parqueadero

	public Parqueadero(int plazasTotales, double tarifaCarro, double tarifaMoto, Almacen almacen) {
		this.plazasTotales = plazasTotales;
		this.plazasDisponibles = plazasTotales;
		this.tarifaCarro = tarifaCarro;
		this.tarifaMoto = tarifaMoto;
		this.almacen = almacen;
		this.inicializarPlazas(plazasTotales);
	}
	
	//Getters and Setters
	public int getPlazasTotales() {
		return this.plazasTotales;
	}
	public void setPlazasTotales(int plazasTotales){
		this.plazasTotales = plazasTotales;
	}
	public int getPlazasDisponibles() {
		return this.plazasDisponibles;
	}
	public void setPlazasDisponibles(int plazasDisponibles) {
		this.plazasDisponibles = plazasDisponibles;
	}
	public double getTarifaCarro() {
		return this.tarifaCarro;
	}
	public void setTarifaCarro(double tarifa) {
		this.tarifaCarro = tarifa;
	}
	public double getTarifaMoto() {
		return this.tarifaMoto;
	}
	public void setTarifaMoto(double tarifaMoto) {
		this.tarifaMoto = tarifaMoto;
	}
	public ArrayList<Plaza> getPlazas() {
		return this.plazas;
	}
	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}
	public List<Empleado> getMecanicos() {
		return empleados.stream().filter(e -> e.getCargo().equals("Mecanico")).toList();
	}
	public void setEmpleados(ArrayList<Empleado> empleados) {
		this.empleados = empleados;
	}
	public Almacen getAlmacen() {
		return almacen;
	}
	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	//metodo para ingresar un vehiculo al parqueadero(movido desde Plaza)
	public void ingresarVehiculo(Vehiculo vehi, Plaza plaza) {
		// si esta plaza ya tiene un vehiculo, entonces hacer null la plaza del mismo.
		if (plaza.getVehiculo() != null) {
			plaza.getVehiculo().setPlaza(null);
		}

		plaza.setVehiculo(vehi);

		// asignar el estado de la plaza
		if (vehi == null) {
			plaza.setEstado("Disponible");
		} else {
			plaza.setEstado("No disponible");
			// asignarle la plaza al vehiculo
			vehi.setPlaza(plaza);
		}
	}
	
	//metodo que se encarga de retirar el vehiculo del parqueadero, es decir, de desasignar el vehiculo a la plaza donde se encuentra, se sobrecarga.
	public void retirarVehiculo(int numPlaza) {
		Plaza plaza = plazas.get(numPlaza-1);
		plaza.setVehiculo(null);
	}
	
	public void retirarVehiculo(String placa) {
		for (Plaza plaza : this.getPlazas()) {
			if (plaza.getVehiculo().getPlaca().equals(placa)) {
				plaza.setVehiculo(null);
			}
		}
	}

	// retorna las plazas que hay disponibles (estado == "Disponible") que tienen las características
	// del vehículo pasado como parámetro (tipo de vehículo: Carro, Moto, Moto altoCC) y que estén
	// disponibles para el dueño (si la plaza es para discapacitados, el dueño debe estar registrado como tal).
	public List<Plaza> plazasDisponiblesPara(Vehiculo vehiculo) {
		List<Plaza> plazasDisponibles = new ArrayList<>();
		String tipo;
		if (vehiculo instanceof Carro) {
			tipo = "Carro";
		} else {
			Moto moto = (Moto) vehiculo;
			if (moto.getTipo().equals("normal")) {
				tipo = "Moto";
			} else {
				tipo = "Moto altoCC";
			}
		}
		for (Plaza plaza : plazas) {
			if (plaza.getTipo().equals(tipo) && plaza.getEstado().equals("Disponible") && plaza.getDiscapacitado() == vehiculo.getDueno().isDiscapacitado()) {
				plazasDisponibles.add(plaza);
			}
		}
		return plazasDisponibles;
	}

	// busca una plaza por el número de plaza, si no la encuentra retorna null.
	public Plaza buscarPlaza(int numeroPlaza) {
		for (Plaza plaza : plazas) {
			if (plaza.getNumeroPlaza() == numeroPlaza) {
				return plaza;
			}
		}
		return null;
	}

	// tomemos por convencion que el 60% de las plazas sean de tipo carro y el 40 % de tipo moto
	// ademas que el 30 % de las plazas para motos sean de tipo altoCC y el 20 % de las plazas para carro sean de tipo discapacitado
	private void inicializarPlazas(int plazasTotales) {
		// asignar una lista vacia a this.plazas con capacidad plazasTotales
		this.plazas = new ArrayList<>(plazasTotales);
		
		// calcular el numero de plazas que serán para motos y para carros con base en nuestra convencion
		int numPlazasCarros = (int)(plazasTotales * 0.6);
		int numPlazasMotos = plazasTotales - numPlazasCarros;
		
		// calcular el numero de plazas para discapacitados y altoCC segun nuestra convencion
		int numPlazasDiscapacitadosCarros = (int) (numPlazasCarros * 0.2);
		int numPlazasAltoCCMotos = (int) (numPlazasMotos * 0.3);
		
		// instanciar las plazas tipo Carro
		for (int i = 1; i <= numPlazasCarros; i++) {
			if (i <= numPlazasDiscapacitadosCarros) {
				this.plazas.add(new Plaza(i, true, null, "Carro"));
			}
			else {
				this.plazas.add(new Plaza(i, false, null, "Carro"));
			}
		}
		
		// instanciar las plazas tipo moto
		for (int i = 1; i <= numPlazasMotos; i++) {
			if (i <= numPlazasAltoCCMotos) {
				this.plazas.add(new Plaza(i + numPlazasCarros, false, null, "Moto altoCC")); // hay que mirar que otro tipo de dato usar en tipo de plazas para mejor dinamismo
			}
			else {
				this.plazas.add(new Plaza(i + numPlazasCarros, false, null, "Moto"));
			}
		}
	}
	
	public void agregarEmpleado(Empleado empleado) {
		this.empleados.add(empleado);
	}

	// busca un empleado en la lista de empleados y si lo encuentra retorna true
	public boolean existeEmpleado(long cedula) {
		for (Empleado empleado : empleados) {
			if (empleado.tieneIdentificacion(cedula)) {
				return true;
			}
		}
		return false;
	}

	public Empleado getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Empleado administrador) {
		this.administrador = administrador;
	}

	public void agregarPlazas(int cantidad, boolean discapacitado, String tipo) {
		this.plazasTotales += cantidad;
		int ultimoNumPlaza = plazas.get(plazas.size() - 1).getNumeroPlaza();
		for (int i = 0; i < cantidad; i++) {
			plazas.add(new Plaza(++ultimoNumPlaza, discapacitado, null, tipo));
		}
	}
}

