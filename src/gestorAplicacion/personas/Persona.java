package gestorAplicacion.personas;

import java.util.ArrayList;
import gestorAplicacion.vehiculos.Vehiculo;

public class Persona{
    private String nombre;
    private int identificacion;
    private int telefono;
    private String correo;
    private String direccion;
    private boolean discapacitado;
    private ArrayList<Vehiculo> vehiculos = new ArrayList<>();

    public Persona(String nombre, int identificacion, int telefono, String correo, String direccion, boolean discapacitado){
        this.nombre= nombre;
        this.identificacion = identificacion;
        this.telefono= telefono;
        this.correo= correo;
        this.direccion= direccion;
        this.discapacitado= discapacitado;
    }
}