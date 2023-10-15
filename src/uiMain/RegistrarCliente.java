package uiMain;

public class RegistrarCliente extends Funcionalidad {
    @Override
    public void ejecutar() {
        long cedula = Consola.pedirLong("Ingrese la cédula");
        if (baseDatos.buscarClienteRegistrado(cedula) != null) {
            System.out.println("Cliente ya registrado");
            return;
        }
        registrarCliente(cedula);
    }
}
