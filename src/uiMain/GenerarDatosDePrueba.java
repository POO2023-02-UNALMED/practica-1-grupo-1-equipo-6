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
                new Producto(TipoProducto.TRANSMISION, 18664, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 61732, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 20875, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 29914, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 67265, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 75753, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 45075, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 80092, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 27075, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 47638, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.CADENA, 89884, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 65528, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 23309, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 47585, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 68054, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 94903, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 61932, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 92750, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 96862, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.TRANSMISION, 18690, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 47717, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 33179, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 25193, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 15990, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 10934, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 72238, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 87167, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 30300, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 62574, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 74890, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 50199, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 59574, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 13360, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 46066, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 42797, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 48762, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LIQUIDOS, 11389, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 47003, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 65639, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 28988, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LIQUIDOS, 41922, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 19920, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 29233, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 52469, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 73608, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 11041, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 95408, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 62721, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 65013, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 40383, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 29332, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 54107, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 46992, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 25657, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 54620, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 40199, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 10767, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 43278, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 83808, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.CADENA, 84198, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 74968, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 58157, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 63402, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 14509, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 27522, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.TRANSMISION, 30482, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 22540, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 20489, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 45708, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 30075, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 88628, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 73450, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 48691, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 97102, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 55051, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 77648, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 15657, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 90236, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 85454, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 97893, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 27500, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LIQUIDOS, 49179, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 35223, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 30673, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 92027, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 64865, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LIQUIDOS, 48786, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 33290, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 92280, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.TRANSMISION, 50935, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 54455, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 74964, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 10974, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 80716, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.CADENA, 86082, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 77542, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 40383, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 25375, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 36239, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 21650, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 33570, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 66409, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 17296, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 37032, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 23222, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 64890, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 28994, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 23438, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 39693, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 33691, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 46798, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LIQUIDOS, 99151, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 57209, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 19627, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 90869, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 14583, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.CADENA, 93862, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 73105, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.CADENA, 22324, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 38704, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 94664, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LIQUIDOS, 90940, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 28395, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.TRANSMISION, 62868, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 59595, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 10068, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 87136, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 59479, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 62939, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 70048, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.TRANSMISION, 83867, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 44093, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.TRANSMISION, 95792, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 98489, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 88164, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 69267, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 99284, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 31546, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 69854, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 95292, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 72358, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 20556, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 95187, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.TRANSMISION, 43355, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LIQUIDOS, 64255, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 70008, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 29109, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LIQUIDOS, 84509, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 86277, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 23164, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 64316, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 32057, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 55341, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 37794, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.CADENA, 64225, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 55795, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 40352, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 21775, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 72394, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 76965, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 54485, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 96696, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 98641, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 15892, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 11488, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LIQUIDOS, 25578, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.TRANSMISION, 88168, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 96985, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 93826, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 64368, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 36966, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 45177, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 68040, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 43733, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.FRENO, 52534, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.CADENA, 51450, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 56816, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.CADENA, 40272, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 54916, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 40710, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 86224, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LIQUIDOS, 53428, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDAL, 99376, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 97044, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.TRANSMISION, 18253, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.TRANSMISION, 95848, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 19215, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 99103, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.PEDALES, 21603, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.LLANTA, 82976, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.BATERIA, 34838, MarcasCarro.KIA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACEITE, 44317, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.GASOLINA, 73300, MarcasCarro.TOYOTA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 76879, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.TRANSMISION, 67076, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.MOTOR, 59415, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.AMORTIGUADOR, 54074, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.RIN, 13597, MarcasCarro.RENAULT.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.CADENA, 39744, MarcasCarro.MAZDA.name(), TipoEstado.EXCELENTE_ESTADO),
                new Producto(TipoProducto.ACELERADOR, 15261, MarcasCarro.CHEVROLET.name(), TipoEstado.EXCELENTE_ESTADO)
        );
        List<Carro> carrosVenta = List.of(
                new Carro("ILQ380", null, MarcasCarro.KIA.name(), "Azul", "2023", TipoVehiculo.MECANICO, 5, true, 20000000L),
                new Carro("EBU624", null, MarcasCarro.TOYOTA.name(), "Negro", "2012", TipoVehiculo.MECANICO, 5, false, 30000000L),
                new Carro("HMK074", null, MarcasCarro.CHEVROLET.name(), "Negro", "2020", TipoVehiculo.AUTOMATICO, 5, false, 25000000L),
                new Carro("AWE305", null, MarcasCarro.MAZDA.name(), "Verde", "2018", TipoVehiculo.MECANICO, 5, false, 40000000L)
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
        for (Cliente cliente : clientes) {
            baseDatos.registrarCliente(cliente);
        }
        for (Empleado empleado : empleados) {
            parqueadero.agregarEmpleado(empleado);
        }
        for (Producto producto : productos) {
            parqueadero.getAlmacen().agregarProducto(producto);
        }
        Almacen.setInventarioBase(productos);
        for (Carro carro : carrosVenta) {
            Empleado.agregarVehiculosVenta(carro);
        }
        for (Vehiculo vehiculo : vehiculosClientes) {
            baseDatos.registrarVehiculo(vehiculo);
            vehiculo.getDueno().agregarVehiculo(vehiculo);
        }
    }
}
