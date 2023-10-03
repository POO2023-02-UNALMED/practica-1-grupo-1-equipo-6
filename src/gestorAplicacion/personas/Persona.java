package gestorAplicacion.personas;

public class Persona{
    private String nombre;
    private int identificacion;
    private int telefono;
    private String correo;
    private String direccion;

    public Persona(String nombre, int identificacion, int telefono, String correo, String direccion){
        this.nombre= nombre;
        this.identidficacion= identificacion;
        this.telefono= telefono;
        this.correo= correo;
        this.direccion= direccion;
    }
}