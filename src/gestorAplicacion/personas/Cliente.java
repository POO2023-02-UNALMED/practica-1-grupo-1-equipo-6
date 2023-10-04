package gestorAplicacion.personas;

//debemos heredar de clase persona
public class Cliente extends Persona{

	public Cliente(String nombre, long cedula, String placa, String correo) {
		super(nombre,cedula,placa,correo);
	}
}
