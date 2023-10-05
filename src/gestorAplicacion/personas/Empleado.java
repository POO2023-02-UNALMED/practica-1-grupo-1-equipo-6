//Sofía

package gestorAplicacion.personas;

public class Empleado extends Persona{
    private String cargo;
    private double salario;
    
    public Empleado(String nombre, long cedula, int telefono, String correo, String direccion, String cargo, double salario) {
    	super(nombre, cedula, telefono, correo, direccion);
    	this.cargo = cargo;
    	this.salario = salario;
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


}