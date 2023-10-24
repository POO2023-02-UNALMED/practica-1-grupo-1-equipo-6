/**
 * Funcionalidad del módulo: Contiene la clase persona que se encarga de definir los atributos y sus respectivos metodos get y set para que 
 * estos puedan ser usados por otras clases, más específicamente la clase empleado y cliente quienes heredan
 * de esta superclase
 Componentes del módulo: Persona
 Autores: Katherine, Sofia
 */
package gestorAplicacion.personas;

import gestorAplicacion.Identificable;

import java.io.Serializable;

public class Persona implements Serializable, Identificable<Long> {
	private static final long serialVersionUID = 1L;

    private String nombre;
    private long cedula;
    private long telefono;
    private String correo;
    private String direccion;

    public Persona(String nombre, long cedula, long telefono, String correo, String direccion){
        this.nombre= nombre;
        this.cedula = cedula;
        this.telefono= telefono;
        this.correo= correo;
        this.direccion= direccion;
    }
    
    //Metodos getters y setters
    
    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }
    public String getNombre() {
    	return this.nombre;
    }
    
    public void setCedula(long ced) {
    	this.cedula = ced;
    }
    public long getCedula() {
    	return this.cedula;
    }
    
    public void setTelefono(long telefono) {
    	this.telefono = telefono;
    }
    public long getTelefono() {
    	return this.telefono;
    }
    
    public void setCorreo(String correo) {
    	this.correo = correo;
    }
    public String getCorreo() {
    	return this.correo;
    }
    
    public void setDireccion(String direccion) {
    	this.direccion = direccion;
    }
    public String getDireccion() {
    	return this.direccion;
    }

    // metodo sobre escrito que retorna una identificacion de tipo long
	@Override
	public Long getIdentificacion() {
		return cedula;
	}

    // metodo sobre escrito que verifica si dos cedulas(identificacion de la persona) son iguales
	@Override
	public boolean tieneIdentificacion(Long cedula) {
		return this.cedula == cedula;
	}
}