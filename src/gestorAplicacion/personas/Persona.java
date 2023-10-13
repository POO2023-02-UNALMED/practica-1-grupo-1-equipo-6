// Katherine
//Sofía

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

	@Override
	public Long getIdentificacion() {
		return cedula;
	}

	@Override
	public boolean tieneIdentificacion(Long cedula) {
		return this.cedula == cedula;
	}
}