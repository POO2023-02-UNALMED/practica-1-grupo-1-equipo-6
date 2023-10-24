/**
 *  Funcionalidad del módulo: contiene la clase RegistrarVehiculo, es una clase que tiene un metodo
 * ejecutar que se utiliza para registrar a un nuevo vehiculo en la base de datos
 Componentes del módulo: RegistrarVehiculo
 */

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
