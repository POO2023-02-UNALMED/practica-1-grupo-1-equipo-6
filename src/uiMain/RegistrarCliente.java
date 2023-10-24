/**
 *  Funcionalidad del módulo: contiene la clase RegistrarCliente, es una clase que tiene un metodo
 * ejecutar que se utiliza para registrar a un nuevo cliente en la base de datos
 Componentes del módulo: RegistrarCliente
 */

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
