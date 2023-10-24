/*
 Funcionalidad del módulo: contiene la implementación de generar datos de prueba, que es una funcionalidad que agrega
 clases con datos generados al azar para probar la aplicación.
 Componentes del módulo: GenerarDatosDePrueba
 Autores: Alejandro, Sebastián
*/

package uiMain;

import gestorAplicacion.parqueadero.Almacen;
import gestorAplicacion.parqueadero.Producto;
import gestorAplicacion.parqueadero.TipoEstado;
import gestorAplicacion.parqueadero.TipoProducto;
import gestorAplicacion.personas.Cliente;
import gestorAplicacion.personas.Empleado;
import gestorAplicacion.vehiculos.*;

import java.util.List;

/**
 * En esta clase se implementa la funcionalidad generar datos de prueba que sirve para agregar
 * objetos a la base de datos generados al azar para probar la aplicación.
 */
public class GenerarDatosDePrueba extends Funcionalidad {
    @Override
    public void ejecutar() {
        // datos generados con https://generatedata.com/generator
        List<Cliente> clientes = List.of(
                new Cliente("Georgia Hodges", 88282864L, 8822435588L, "odio.tristique@protonmail.ca", "P.O. Box 837, 1931 Faucibus Road", false),
                new Cliente("Ashton Buckner", 97216432L, 4784015772L, "quisque.ac.libero@outlook.couk", "610-489 Sodales Street", false),
                new Cliente("Eaton Gutierrez", 79618030L, 8782786843L, "arcu.ac.orci@icloud.net", "Ap #376-1454 Semper Avenue", false),
                new Cliente("Benedict Wyatt", 13251063L, 4554518795L, "cursus.vestibulum@hotmail.edu", "P.O. Box 189, 2706 Nibh. Road", false),
                new Cliente("Warren Watson", 28546557L, 5366791828L, "metus.vitae@icloud.com", "901-8274 Convallis Av.", false),
                new Cliente("Rigel Cleveland", 10351169L, 6582360165L, "varius.orci@yahoo.org", "Ap #269-8928 Aliquam Ave", false),
                new Cliente("Holly Barry", 77363835L, 3338525656L, "risus.morbi@aol.couk", "Ap #988-3255 Gravida. St.", true),
                new Cliente("Abbot English", 24694603L, 4879434732L, "morbi.tristique@icloud.net", "Ap #118-8902 Semper. Street", false)
        );
        List<Empleado> empleados = List.of(
                new Empleado("Belle Nolan", 53799227L, 2363874677L, "imperdiet.erat@hotmail.net", "P.O. Box 371, 4784 Nunc Rd.", "Vendedor", 1465938),
                new Empleado("Addison Hobbs", 34149111L, 3840124676L, "gravida.nunc.sed@google.net", "Ap #179-7256 Iaculis, Ave", "Vendedor", 1737354),
                new Empleado("Elton Carroll", 38543024L, 7261237929L, "egestas@google.org", "P.O. Box 717, 9254 Mi Rd.", "Mecanico", 1942580),
                new Empleado("Emery Whitehead", 21372067L, 7528886123L, "tincidunt@icloud.org", "8875 Est, Ave", "Vendedor", 1155662),
                new Empleado("Joelle Combs", 26943851L, 7718662286L, "cras.vehicula@google.com", "885-4962 Dapibus St.", "Mecanico", 1062081),
                new Empleado("Ira Lynn", 52682220L, 4148814432L, "suscipit.est@google.net", "444-6842 Auctor Rd.", "Vendedor", 1811143),
                new Empleado("Angelica Olsen", 63772800L, 7852724044L, "dui.fusce.diam@icloud.net", "P.O. Box 804, 7026 Sodales. Road", "Mecanico", 1957091),
                new Empleado("Tashya Floyd", 38868093L, 7958533122L, "quam.vel@aol.couk", "Ap #371-9085 Posuere St.", "Vendedor", 1400694),
                new Empleado("Iona Gordon", 46243263L, 1489760813L, "massa.mauris@icloud.org", "Ap #668-702 Magna. Av.", "Vendedor", 1448290),
                new Empleado("Lareina Wilkinson", 53388926L, 3735464647L, "odio@yahoo.org", "6399 Rutrum St.", "Mecanico", 1582047),
                new Empleado("Kai Miles", 66207025L, 5255067054L, "semper.rutrum@hotmail.couk", "992-5371 Mauris Ave", "Mecanico", 1106243),
                new Empleado("Paki Hill", 63116666L, 8541572636L, "lacinia.at@icloud.org", "P.O. Box 772, 3895 Aliquet Ave", "Mecanico", 1184994)
        );
        List<Producto> productos = List.of(
                new Producto(TipoProducto.TRANSMISION, 18664, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 61732, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 20875, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 29914, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 67265, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 75753, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 45075, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 80092, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 27075, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 47638, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.CADENA, 89884, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 65528, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 23309, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 47585, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 68054, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 94903, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 61932, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 92750, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 96862, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.TRANSMISION, 18690, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 47717, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 33179, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 25193, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 15990, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 10934, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 72238, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 87167, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 30300, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 62574, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 74890, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 50199, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 59574, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 13360, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 46066, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 42797, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 48762, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LIQUIDOS, 11389, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 47003, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 65639, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 28988, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LIQUIDOS, 41922, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 19920, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 29233, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 52469, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 73608, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 11041, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 95408, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 62721, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 65013, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 40383, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 29332, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 54107, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 46992, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 25657, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 54620, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 40199, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 10767, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 43278, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 83808, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.CADENA, 84198, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 74968, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 58157, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 63402, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 14509, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 27522, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.TRANSMISION, 30482, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 22540, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 20489, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 45708, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 30075, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 88628, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 73450, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 48691, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 97102, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 55051, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 77648, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 15657, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 90236, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 85454, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 97893, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 27500, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LIQUIDOS, 49179, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 35223, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 30673, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 92027, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 64865, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LIQUIDOS, 48786, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 33290, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 92280, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.TRANSMISION, 50935, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 54455, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 74964, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 10974, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 80716, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.CADENA, 86082, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 77542, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 40383, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 25375, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 36239, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 21650, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 33570, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 66409, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 17296, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 37032, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 23222, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 64890, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 28994, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 23438, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 39693, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 33691, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 46798, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LIQUIDOS, 99151, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 57209, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 19627, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 90869, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 14583, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.CADENA, 93862, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 73105, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.CADENA, 22324, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 38704, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 94664, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LIQUIDOS, 90940, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 28395, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.TRANSMISION, 62868, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 59595, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 10068, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 87136, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 59479, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 62939, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 70048, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.TRANSMISION, 83867, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 44093, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.TRANSMISION, 95792, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 98489, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 88164, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 69267, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 99284, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 31546, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 69854, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 95292, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 72358, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 20556, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 95187, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.TRANSMISION, 43355, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LIQUIDOS, 64255, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 70008, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 29109, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LIQUIDOS, 84509, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 86277, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 23164, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 64316, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 32057, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 55341, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 37794, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.CADENA, 64225, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 55795, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 40352, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 21775, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 72394, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 76965, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 54485, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 96696, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 98641, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 15892, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 11488, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LIQUIDOS, 25578, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.TRANSMISION, 88168, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 96985, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 93826, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 64368, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 36966, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 45177, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 68040, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 43733, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 52534, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.CADENA, 51450, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 56816, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.CADENA, 40272, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 54916, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 40710, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 86224, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LIQUIDOS, 53428, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 99376, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 97044, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.TRANSMISION, 18253, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.TRANSMISION, 95848, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 19215, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 99103, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 21603, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 82976, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 34838, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 44317, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 73300, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 76879, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.TRANSMISION, 67076, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 59415, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 54074, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 13597, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.CADENA, 39744, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 15261, TipoEstado.EXCELENTE_ESTADO)
        );
        List<Carro> carrosVenta = List.of(
                new Carro("ILQ380", null, MarcasCarro.KIA.name(), "Azul", "2023", TipoVehiculo.MECANICO, 5, true, 20000000),
                new Carro("EBU624", null, MarcasCarro.TOYOTA.name(), "Negro", "2012", TipoVehiculo.MECANICO, 5, false, 30000000),
                new Carro("HMK074", null, MarcasCarro.CHEVROLET.name(), "Negro", "2020", TipoVehiculo.AUTOMATICO, 5, false, 25000000),
                new Carro("AWE305", null, MarcasCarro.MAZDA.name(), "Verde", "2018", TipoVehiculo.MECANICO, 5, false, 40000000)
        );
        List<Vehiculo> vehiculosClientes = List.of(
                new Carro("LCX368", clientes.get(6), MarcasCarro.MAZDA.name(), "Negro", "2020", TipoVehiculo.MECANICO, 5, clientes.get(6).isDiscapacitado()),
                new Moto("LOR31V", clientes.get(4), MarcasCarro.KIA.name(), "Azul", "2011", TipoVehiculo.NORMAL, 174),
                new Moto("YOQ05B", clientes.get(3), MarcasCarro.KIA.name(), "Azul", "2016", TipoVehiculo.ALTOCC, 82),
                new Carro("YCI195", clientes.get(3), MarcasCarro.CHEVROLET.name(), "Azul", "2023", TipoVehiculo.AUTOMATICO, 5, clientes.get(3).isDiscapacitado()),
                new Carro("ISZ049", clientes.get(3), MarcasCarro.MAZDA.name(), "Verde", "2022", TipoVehiculo.AUTOMATICO, 5, clientes.get(3).isDiscapacitado()),
                new Moto("TZL87N", clientes.get(0), MarcasCarro.MAZDA.name(), "Azul", "2015", TipoVehiculo.NORMAL, 109),
                new Moto("LCI31H", clientes.get(5), MarcasCarro.TOYOTA.name(), "Rojo", "2017", TipoVehiculo.NORMAL, 112),
                new Carro("WOV536", clientes.get(7), MarcasCarro.KIA.name(), "Gris", "2014", TipoVehiculo.MECANICO, 5, clientes.get(7).isDiscapacitado()),
                new Carro("TAU635", clientes.get(4), MarcasCarro.RENAULT.name(), "Gris", "2011", TipoVehiculo.AUTOMATICO, 5, clientes.get(4).isDiscapacitado()),
                new Carro("RKX138", clientes.get(4), MarcasCarro.TOYOTA.name(), "Azul", "2011", TipoVehiculo.MECANICO, 5, clientes.get(4).isDiscapacitado()),
                new Carro("RSF358", clientes.get(2), MarcasCarro.KIA.name(), "Verde", "2011", TipoVehiculo.AUTOMATICO, 5, clientes.get(2).isDiscapacitado()),
                new Carro("VUJ819", clientes.get(6), MarcasCarro.RENAULT.name(), "Rojo", "2020", TipoVehiculo.MECANICO, 5, clientes.get(6).isDiscapacitado()),
                new Carro("BTV358", clientes.get(2), MarcasCarro.KIA.name(), "Gris", "2021", TipoVehiculo.AUTOMATICO, 5, clientes.get(2).isDiscapacitado()),
                new Moto("VLC37F", clientes.get(2), MarcasCarro.KIA.name(), "Verde", "2019", TipoVehiculo.ALTOCC, 124),
                new Carro("FPZ394", clientes.get(6), MarcasCarro.TOYOTA.name(), "Gris", "2022", TipoVehiculo.AUTOMATICO, 5, clientes.get(6).isDiscapacitado()),
                new Moto("VHD89N", clientes.get(5), MarcasCarro.KIA.name(), "Azul", "2010", TipoVehiculo.NORMAL, 99)
        );
        List<Producto> productosBase = List.of(
                new Producto(TipoProducto.TRANSMISION, 400000, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 300000, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 250000, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 400000, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 100000, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 120000, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 80000, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 40000, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.CADENA, 60000, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 40000, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 900000, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 40000, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 70000, TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LIQUIDOS, 30000, TipoEstado.EXCELENTE_ESTADO)
                );
        for (Cliente cliente : clientes) {
            baseDatos.registrarCliente(cliente);
        }
        for (Empleado empleado : empleados) {
            parqueadero.agregarEmpleado(empleado);
        }
        for (Producto producto : productos) {
            parqueadero.getAlmacen().agregarProducto(producto);
        }
        Almacen.setInventarioBase(productosBase);
        for (Carro carro : carrosVenta) {
            Empleado.agregarVehiculosVenta(carro);
        }
        for (Vehiculo vehiculo : vehiculosClientes) {
            baseDatos.registrarVehiculo(vehiculo);
            vehiculo.getDueno().agregarVehiculo(vehiculo);
        }
    }
}
