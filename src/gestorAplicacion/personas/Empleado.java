//Sofía
//sebas

package gestorAplicacion.personas;

import java.io.Serializable;
import gestorAplicacion.vehiculos.*;

public class Empleado extends Persona implements Serializable{
    private static final long serialVersionUID = 1L;
	
	private String cargo;
    private double salario;
    private double comision;
    
    public Empleado(String nombre, long cedula, int telefono, String correo, String direccion, String cargo, double salario) {
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
     * Metodo que utiliza un empleado tipo mecanico para revisar un vehiculo y devolver su estado
     * 
     */
    private void revisarVehiculo(Empleado mecanico, Vehiculo vehiculo) {
    	if (vehiculo instanceof Carro) {
    		//continuar... (revisar)
    	}
    }
}