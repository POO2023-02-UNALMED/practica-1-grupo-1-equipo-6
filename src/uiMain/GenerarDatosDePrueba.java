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
        		new Producto(TipoProducto.TRANSMISION, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.RIN, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.PEDALES, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.FRENO, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.PEDALES, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.ACEITE, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.FRENO, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.TRANSMISION, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.PEDALES, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.LLANTA, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.PEDAL, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.MOTOR, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.LLANTA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.ACEITE, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.FRENO, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.FRENO, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.BATERIA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.AMORTIGUADOR, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.TRANSMISION, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.LLANTA, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.ACEITE, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.BATERIA, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.PEDAL, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.MOTOR, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.CADENA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.ACELERADOR, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.MOTOR, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.FRENO, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.ACELERADOR, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.GASOLINA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.ACELERADOR, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.PEDALES, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.LLANTA, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.LLANTA, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.RIN, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.ACELERADOR, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.AMORTIGUADOR, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.BATERIA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.LLANTA, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.AMORTIGUADOR, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.ACELERADOR, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.ACELERADOR, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.ACELERADOR, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.BATERIA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.GASOLINA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.PEDALES, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.CADENA, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.CADENA, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.BATERIA, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.ACEITE, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.LIQUIDOS, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.AMORTIGUADOR, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.BATERIA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.CADENA, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.CADENA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.GASOLINA, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.LIQUIDOS, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.AMORTIGUADOR, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.LIQUIDOS, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.LIQUIDOS, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.ACELERADOR, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.LIQUIDOS, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.RIN, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.ACEITE, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.LLANTA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.CADENA, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.AMORTIGUADOR, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.RIN, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.CADENA, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.MOTOR, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.BATERIA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.RIN, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.RIN, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.CADENA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.LLANTA, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.PEDALES, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.PEDAL, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.PEDALES, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.PEDALES, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.LIQUIDOS, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.CADENA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.LIQUIDOS, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.MOTOR, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.ACELERADOR, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.AMORTIGUADOR, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.PEDAL, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.LLANTA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.PEDALES, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.LLANTA, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.AMORTIGUADOR, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.ACEITE, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.CADENA, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.LIQUIDOS, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.LLANTA, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.PEDAL, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.AMORTIGUADOR, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.AMORTIGUADOR, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.LLANTA, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.CADENA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.BATERIA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.AMORTIGUADOR, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.PEDAL, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.LLANTA, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.TRANSMISION, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.FRENO, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.MOTOR, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.ACELERADOR, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.TRANSMISION, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.GASOLINA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.PEDAL, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.ACEITE, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.PEDALES, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.ACEITE, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.FRENO, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.RIN, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.ACELERADOR, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.MOTOR, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.MOTOR, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.LLANTA, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.ACEITE, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.GASOLINA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.PEDAL, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.RIN, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.BATERIA, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.CADENA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.GASOLINA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.RIN, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.RIN, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.ACEITE, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.TRANSMISION, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.CADENA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.ACEITE, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.LLANTA, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.FRENO, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.TRANSMISION, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.PEDAL, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.BATERIA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.LLANTA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.PEDAL, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.ACEITE, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.FRENO, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.RIN, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.FRENO, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.PEDALES, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.PEDAL, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.PEDAL, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.PEDAL, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.MOTOR, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.ACELERADOR, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.LLANTA, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.TRANSMISION, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.PEDAL, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.PEDALES, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.GASOLINA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.CADENA, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.BATERIA, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.MOTOR, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.FRENO, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.ACEITE, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.GASOLINA, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.TRANSMISION, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.LLANTA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.PEDAL, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.BATERIA, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.AMORTIGUADOR, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.FRENO, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.ACEITE, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.RIN, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.FRENO, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.GASOLINA, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.LLANTA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.PEDALES, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.ACEITE, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.AMORTIGUADOR, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.ACELERADOR, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.CADENA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.LLANTA, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.TRANSMISION, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.PEDAL, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.GASOLINA, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.LLANTA, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.ACELERADOR, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.FRENO, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.BATERIA, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.AMORTIGUADOR, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.PEDALES, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.PEDAL, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.RIN, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.GASOLINA, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.PEDAL, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.MOTOR, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.GASOLINA, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.RIN, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.LLANTA, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.FRENO, TipoEstado.MAL_ESTADO),
        		new Producto(TipoProducto.BATERIA, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.ACELERADOR, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.GASOLINA, TipoEstado.EXCELENTE_ESTADO),
        		new Producto(TipoProducto.FRENO, TipoEstado.BUEN_ESTADO),
        		new Producto(TipoProducto.PEDAL, TipoEstado.BUEN_ESTADO)
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
        
        for (Cliente cliente : clientes) {
            baseDatos.registrarCliente(cliente);
        }
        for (Empleado empleado : empleados) {
            parqueadero.agregarEmpleado(empleado);
        }
        for (Producto producto : productos) {
            parqueadero.getAlmacen().agregarProducto(producto);
        }
        for (Carro carro : carrosVenta) {
            Empleado.agregarVehiculosVenta(carro);
        }
        for (Vehiculo vehiculo : vehiculosClientes) {
            baseDatos.registrarVehiculo(vehiculo);
            vehiculo.getDueno().agregarVehiculo(vehiculo);
        }
    }
}
