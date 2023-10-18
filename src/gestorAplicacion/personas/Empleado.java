//Sofía
//sebas
// Katherine

package gestorAplicacion.personas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import gestorAplicacion.parqueadero.Producto;
import gestorAplicacion.parqueadero.TipoEstado;
import gestorAplicacion.vehiculos.*;

public class Empleado extends Persona implements Serializable{
    private static final long serialVersionUID = 1L;
	
	private String cargo;
    private double salario;
    private double comision;
    private int serviciosRealizados; // dependiendo del tipo de empleado serian productos vendidos o vehiculos vendidos y/o comprados, 
    								 // componentes arreglados, revisiones, servicios mecanicos en general, etc.
    
    public Empleado(String nombre, long cedula, long telefono, String correo, String direccion, String cargo, double salario) {
    	super(nombre, cedula, telefono, correo, direccion);
    	this.cargo = cargo;
    	this.salario = salario;
    	this.comision = 0.1;
    	serviciosRealizados = 0;
    }
    
    public void setCargo(String cargo) {
    	this.cargo = cargo;
    }
    public String getCargo() {
    	return this.cargo;
    }
    public void setSalario(double salario) {
    	this.salario = salario;
    }
    public double getSalario() {
    	return this.salario;
    }
    public void setComision(double comision) {
    	this.comision = comision;
    }
    public double getComision() {
    	return this.comision;
    }
    public void setServiciosRealizados(int serviciosRealizados) {
    	this.serviciosRealizados = serviciosRealizados;
    }
    public int getServiciosRealizados() {
    	return this.serviciosRealizados;
    }
    
    
    /**
     * Metodo que utiliza un empleado tipo mecanico para revisar un vehiculo y devolver su estado, usar para verificar en la funcionalidad venta de
     * Vehiculo
     */
    public Object revisarVehiculo(Vehiculo vehiculo) { // retornar boolean y array con los productos en mal estado
    	List<Producto> r = new ArrayList<>(); //string que se va a retornar
    	
    	//comprobar que el empleado sea de tipo mecanico, sino lanzar excepcion(implementar)
    	if (this.getCargo().equals("Mecanico")) {
    	
    		//verificar si el vehiculo es de tipo carro e ir buscando componente a componente si hay alguno en mal estado o dañado
    		if (vehiculo instanceof Carro) {
    			if (((Carro) vehiculo).getMotor().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Carro) vehiculo).getMotor());
    			}
    			if (((Carro) vehiculo).getTransmision().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Carro) vehiculo).getTransmision());
    			}
    			if (((Carro) vehiculo).getAcelerador().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Carro) vehiculo).getAcelerador());
    			}
    			if (((Carro) vehiculo).getFreno().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Carro) vehiculo).getFreno());
    			}
    			if (((Carro) vehiculo).getBateria().getEstado().equals(TipoEstado.MAL_ESTADO))  {
    				r.add(((Carro) vehiculo).getBateria());
    			}
    			if (((Carro) vehiculo).getPedal().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Carro) vehiculo).getPedal());
    			}
    		
    			for (Producto deposito : ((Carro) vehiculo).getDepositos()) {
    				if (deposito.getEstado().equals(TipoEstado.MAL_ESTADO)) {
    					r.add(deposito);
    				}
    			}
    			
    			for (Producto llanta : ((Carro) vehiculo).getLlantas()) {
    				if (llanta.getEstado().equals(TipoEstado.MAL_ESTADO)) {
    					r.add(llanta);
    				}
    			}
    		
    			for (Producto rin : ((Carro) vehiculo).getRines()) {
    				if (rin.getEstado().equals(TipoEstado.MAL_ESTADO)) {
    					r.add(rin);
    				}
    			}
    		
    			for (Producto amortiguador : ((Carro) vehiculo).getAmortiguadores()) {
    				if (amortiguador.getEstado().equals(TipoEstado.MAL_ESTADO)) {
    					r.add(amortiguador);
    				}
    			}
    		}
    		
    		//mismo proceso pero con un vehiculo de tipo moto
    		if (vehiculo instanceof Moto) {
    			if (((Moto) vehiculo).getMotor().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Moto) vehiculo).getMotor());
    			}
    			if (((Moto) vehiculo).getTransmision().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Moto) vehiculo).getTransmision());
    			}
    			if (((Moto) vehiculo).getAcelerador().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Moto) vehiculo).getAcelerador());
    			}
    			if (((Moto) vehiculo).getFreno().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Moto) vehiculo).getFreno());
    			}
    			if (((Moto) vehiculo).getCadena().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Moto) vehiculo).getCadena());
    			}
    			if (((Moto) vehiculo).getPedales().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Moto) vehiculo).getPedales());
    			}
    			if (((Moto) vehiculo).getBateria().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Moto) vehiculo).getBateria());
    			}
    			if (((Moto) vehiculo).getAmortiguador().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Moto) vehiculo).getAmortiguador());
    			}
    			
    			for (Producto deposito : ((Moto) vehiculo).getDepositos()) {
    				if (deposito.getEstado().equals(TipoEstado.MAL_ESTADO)) {
    					r.add(deposito);
    				}
    			}
    			
    			for (Producto llanta : ((Moto) vehiculo).getLlantas()) {
    				if (llanta.getEstado().equals(TipoEstado.MAL_ESTADO)) {
    					r.add(llanta);
    				}
    			}
    			
    			for (Producto rin : ((Moto) vehiculo).getRines()) {
    				if (rin.getEstado().equals(TipoEstado.MAL_ESTADO)) {
    					r.add(rin);
    				}
    			}
    		}
    		
    		//despues revisar si la lista se encuentra vacia o no y proceder
    		if (!r.isEmpty()) {
        		return r;
        	}
        	else {
        		return true;
        	}
    	}
    	else {
    		return "Empleado no autorizado para este servicio";
    	}
    }
}
