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
    
    public Empleado(String nombre, long cedula, long telefono, String correo, String direccion, String cargo, double salario) {
    	super(nombre, cedula, telefono, correo, direccion);
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
    public String revisarVehiculo(Vehiculo vehiculo) {
    	String r = ""; //string que se va a retornar
    	
    	//comprobar que el empleado sea de tipo mecanico, sino lanzar excepcion(implementar)
    	if (this.getCargo().equals("Mecanico")) {
    	
    		//verificar si el vehiculo es de tipo carro e ir buscando componente a componente si hay alguno en mal estado o dañado
    		if (vehiculo instanceof Carro) {
    			if (((Carro) vehiculo).getMotor().getEstado().equals("Mal estado")) {
    				r += "Motor\n";
    			}
    			if (((Carro) vehiculo).getTransmision().getEstado().equals("Mal estado")) {
    				r += "Transmision\n";
    			}
    			if (((Carro) vehiculo).getAcelerador().getEstado().equals("Mal estado")) {
    				r += "Acelerador\n";
    			}
    			if (((Carro) vehiculo).getFreno().getEstado().equals("Mal estado")) {
    				r += "Freno\n";
    			}
    			if (((Carro) vehiculo).getBateria().getEstado().equals("Mal estado"))  {
    				r += "Bateria\n";
    			}
    			if (((Carro) vehiculo).getPedal().getEstado().equals("Mal estado")) {
    				r += "Pedal\n";
    			}
    		
    			int c = 0;
    			for (int i = 0; i < 3; i++) {
    				if (((Carro) vehiculo).getDepositos()[i].getEstado().equals("Mal estado") {
    					c += 1;
    				}
    			}
    			if(c != 0) {
    				r += c + " Depositos\n"; c = 0;
    			}
    			
    			for (int i = 0; i < 4; i++) {
    				if (((Carro) vehiculo).getLlantas()[i].getEstado().equals("Mal estado")) {
    					c += 1;
    				}
    			}
    			if (c != 0) {
    				r += c + " Llantas\n"; c = 0;
    			}
    		
    			for (int i = 0; i < 4; i++) {
    				if (((Carro) vehiculo).getRines()[i].getEstado().equals("Mal estado")) {
    					c += 1;
    				}
    			}
    			if (c != 0) {
    				r += c + " Rines\n"; c = 0;
    			}
    		
    			for (int i = 0; i < 4; i++) {
    				if (((Carro) vehiculo).getAmortiguadores()[i].getEstado().equals("Mal estado")) {
    					c += 1;
    				}
    			}
    			if (c != 0) {
    				r += c + " Amortiguadores\n"; c = 0;
    			}
    		}
    		
    		//mismo proceso pero con un vehiculo de tipo moto
    		if (vehiculo instanceof Moto) {
    			if (((Moto) vehiculo).getMotor().getEstado().equals("Mal estado")) {
    				r += "Motor\n";
    			}
    			if (((Moto) vehiculo).getTransmision().getEstado().equals("Mal estado")) {
    				r += "Transmision\n";
    			}
    			if (((Moto) vehiculo).getAcelerador().getEstado().equals("Mal estado")) {
    				r += "Acelerador\n";
    			}
    			if (((Moto) vehiculo).getFreno().getEstado().equals("Mal estado")) {
    				r += "Freno\n";
    			}
    			if (((Moto) vehiculo).getCadena().getEstado().equals("Mal estado")) {
    				r += "Cadena\n";
    			}
    			if (((Moto) vehiculo).getPedales().getEstado().equals("Mal estado")) {
    				r += "Pedales\n";
    			}
    			if (((Moto) vehiculo).getBateria().getEstado().equals("Mal estado")) {
    				r += "Bateria\n";
    			}
    			if (((Moto) vehiculo).getMotor().getEstado().equals("Male estado")) {
    				r += "Amortiguador\n";
    			}
    			
    			int c = 0;
    			for (int i = 0; i < 3; i++) {
    				if (((Moto) vehiculo).getDepositos()[i].getEstado().equals("Mal estado")) {
    					c += 1;
    				}
    			}
    			if(c != 0) {
    				r += c + " Depositos\n"; c = 0;
    			}
    			
    			for (int i = 0; i < 2; i++) {
    				if (((Moto) vehiculo).getLlantas()[i].getEstado().equals("Mal estado")) {
    					c += 1;
    				}
    			}
    			if(c != 0) {
    				r += c + " Llantas\n"; c = 0;
    			}
    			
    			for (int i = 0; i < 2; i++) {
    				if (((Moto) vehiculo).getRines()[i].getEstado().equals("Mal estado")) {
    					c += 1;
    				}
    			}
    			if(c != 0) {
    				r += c + " Rines\n"; c = 0;
    			}
    		}
    		
    		//despues revisar si el string sigue como se inicializo, es decir todos los componentes estan en buen estado, sino se regresa el string
    		//mutado
    		if (r.equals("")) {
        		return "El vehiculo esta en buen estado";
        	}
        	else {
        		return "Los siguientes componentes requieren reparacion:\n" + r;
        	}
    	}
    	else {
    		return "Empleado no autorizado para este servicio";
    	}
    }
}
