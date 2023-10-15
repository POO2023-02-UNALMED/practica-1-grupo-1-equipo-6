package uiMain;

import gestorAplicacion.personas.Cliente;

public class RegistrarVehiculo extends Funcionalidad {
    @Override
    public void ejecutar() {
        long cedula = Consola.pedirLong("Ingrese su cédula"); // TODO: aquí debería ser `su cédula` o `la cédula del cliente`?
        Cliente cliente = buscarORegistrarCliente(cedula);
        if (cliente == null) {
            return;
        }
        registrarVehiculo(cliente);
    }
}
