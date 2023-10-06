// Sebastián
// Alejandro

package gestorAplicacion.parqueadero;

import java.io.Serializable;
import java.util.ArrayList;
import gestorAplicacion.vehiculos.*;

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
	
	public Parqueadero(int plazasTotales, double tarifaCarro, double tarifaMoto) {
		this.plazasTotales = plazasTotales;
		this.plazasDisponibles = plazasTotales;
		this.tarifaCarro = tarifaCarro;
		this.tarifaMoto = tarifaMoto;
		inicializarPlazas(plazasTotales);
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
}

