//Sofía
//sebas
// Katherine

package gestorAplicacion.personas;

import java.io.Serializable;
import gestorAplicacion.vehiculos.*;

public class Empleado extends Persona implements Serializable{
    private static final long serialVersionUID = 1L;
	
	private String cargo;
    private double salario;
    private double comision;
	private Vehiculo vehiculo; //debe recibirse un vehiculo para que pueda ser usado en los métodos por el empleado, ya sea el que va a revisarlo o el que lo va a vender
    
    public Empleado(String nombre, long cedula, long telefono, String correo, String direccion, Vehiculo vehiculo, String cargo, double salario) {
    	super(nombre, cedula, telefono, correo, direccion, vehiculo);
    	this.cargo = cargo;
    	this.salario = salario;
    	this.comision = 0.1;
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
    
    
    /**
     * Metodo que utiliza un empleado tipo mecanico para revisar un vehiculo y devolver su estado, usar para verificar en la funcionalidad venta de
     * Vehiculo
     */
    public String revisarCarro(Vehiculo vehiculo) {
    	String r = ""; //string que se va a retornar
    	
    	//comprobar que el empleado sea de tipo mecanico, sino lanzar excepcion(implementar)
    	if (this.getCargo().equals("Mecanico")) {
    	
    		//verificar si el vehiculo es de tipo carro e ir buscando componente a componente si hay alguno en mal estado o dañado
    		if (vehiculo instanceof Carro) {
    			if (((Carro) vehiculo).getMotor().getEstado() == "Mal estado") {
    				r += "Motor\n";
    			}
    			if (((Carro) vehiculo).getTransmision().getEstado() == "Mal estado") {
    				r += "Transmision\n";
    			}
    			if (((Carro) vehiculo).getAcelerador().getEstado() == "Mal estado") {
    				r += "Acelerador\n";
    			}
    			if (((Carro) vehiculo).getFreno().getEstado() == "Mal estado") {
    				r += "Freno\n";
    			}
    			if (((Carro) vehiculo).getBateria().getEstado() == "Mal estado") {
    				r += "Bateria\n";
    			}
    			if (((Carro) vehiculo).getPedal().getEstado() == "Mal estado") {
    				r += "Pedal\n";
    			}
    		
    			int c = 0;
    			for (int i = 0; i < 3; i++) {
    				if (((Carro) vehiculo).getDepositos()[i].getEstado() == "Mal estado") {
    					c += 1;
    				}
    			}
    			if(c != 0) {
    				r += c + "Depositos\n"; c = 0;
    			}
    			
    			for (int i = 0; i < 4; i++) {
    				if (((Carro) vehiculo).getLlantas()[i].getEstado() == "Mal estado") {
    					c += 1;
    				}
    			}
    			if (c != 0) {
    				r += c + "Llantas\n"; c = 0;
    			}
    		
    			for (int i = 0; i < 4; i++) {
    				if (((Carro) vehiculo).getRines()[i].getEstado() == "Mal estado") {
    					c += 1;
    				}
    			}
    			if (c != 0) {
    				r += c + "Rines\n"; c = 0;
    			}
    		
    			for (int i = 0; i < 4; i++) {
    				if (((Carro) vehiculo).getAmortiguadores()[i].getEstado() == "Mal estado") {
    					c += 1;
    				}
    			}
    			if (c != 0) {
    				r += c + "Amortiguadores\n"; c = 0;
    			}
    		}
    		
    		//mismo proceso pero con un vehiculo de tipo moto
    		if (vehiculo instanceof Moto) {
    			//.... continuar, revisar antes porque no me gusta jajaja
    		}
    		
    		//despues revisar si el string sigue como se inicializo, es decir todos los componentes estan en buen estado, sino se regresa el string
    		//mutado
    		if (r.equals("")) {
        		return "El vehiculo esta en buen estado";
        	}
        	else {
        		return r;
        	}
    	}
    	else {
    		return "Empleado no autorizado para este servicio";
    	}
    }
}
