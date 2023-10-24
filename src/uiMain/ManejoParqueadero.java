/*
 Funcionalidad del módulo: contiene la implementación de la funcionalidad manejo parqueadero
 Componentes del módulo: ManejoParqueadero
 Autores: Alejandro, Sebastián, Sara
*/

package uiMain;

import java.time.Duration;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import gestorAplicacion.parqueadero.*;
import gestorAplicacion.personas.Cliente;
import gestorAplicacion.personas.Empleado;
import gestorAplicacion.vehiculos.TipoVehiculo;
import gestorAplicacion.vehiculos.Carro;
import gestorAplicacion.vehiculos.MarcasCarro;
import gestorAplicacion.vehiculos.Vehiculo;

/**
 * Esta clase incluye la implementación de la funcionalidad manejo parqueadero
 */
public class ManejoParqueadero extends Funcionalidad {
	/**
	 * Ejecuta la funcionalidad de manejo parqueadero
	 */
	@Override
	public void ejecutar() {
		long cedulaAdministrador = Consola.pedirLong("Ingrese la cédula del administrador");
		if (!parqueadero.getAdministrador().tieneIdentificacion(cedulaAdministrador)) {
			System.out.println("Cédula de administrador incorrecta");
			return;
		}
		
		System.out.println("Bienvenido " + parqueadero.getAdministrador().getNombre());

		int eleccion;
		do {
		eleccion = Consola.pedirEleccion("Parte del parqueadero a manejar", List.of(
				"Taller",
				"Parqueadero",
				"Almacén",
				"Regresar al menú principal"
		));
		
		switch (eleccion) {
			case 0 -> administrarTaller();
			case 1 -> administrarParqueadero();
			case 2 -> administrarAlmacen();
		}
		} while (eleccion != 3);
	}

	/**
	 * Da opciones al usuario de administración del taller
	 */
	private void administrarTaller() {
		int eleccion;
		do {
			eleccion = Consola.pedirEleccion("Qué desea hacer", List.of(
					"Nuevo mecanico",
					"Despedir mecanico",
					"Ver estadísticas de los mecánicos",
					"Generar bonificaciones para los mecánicos",
					"Regresar"
					));
			
			switch (eleccion) {
				case 0 -> agregarEmpleado("Mecanico");
				case 1 -> despedirEmpleado(Consola.pedirLong("Ingrese la cédula del mecánico"));
				case 2 -> estadisticasMecanicos();
				case 3 -> bonificacionesMecanicos();
			}
		} while (eleccion != 4);
	}

	/**
	 * Da opciones al usuario de administración del parqueadero
	 */
	private void administrarParqueadero() {
		int eleccion;
		do {
			eleccion = Consola.pedirEleccion("Qué desea hacer", List.of(
					"Agregar carros para vender",
					"Agregar plazas",
					"Contratar vendedor",
					"Retirar vehículo",
					"Ver información de un cliente",
					"Regresar"
			));

			switch (eleccion) {
				case 0 -> agregarCarroVenta(parqueadero.getAdministrador());
				case 1 -> agregarPlazas();
				case 2 -> agregarEmpleado("Vendedor");
				case 3 -> retirarVehiculo();
				case 4 -> informacionCliente();
			}
		} while (eleccion != 5);
	}

	/**
	 * Da opciones al usuario de administración del almacén
	 */
	private void administrarAlmacen() {
		int eleccion;
		do {
			eleccion = Consola.pedirEleccion("Qué desea hacer", List.of(
					"Agregar producto al almacén",
					"Ver inventario del almacén",
					"Regresar"
			));

			switch (eleccion) {
				case 0 -> agregarProducto();
				case 1 -> inventarioAlmacen();
			}
		} while (eleccion != 2);
	}

	/**
	 * método para agregar carros para vender
	 */
	private void agregarCarroVenta(Empleado administrador) {
		String placa = Consola.pedirString("Ingrese la placa");
		int marcaEscogida = Consola.pedirEleccion("Escoja la marca: ", Arrays.asList(MarcasCarro.values()).stream().map(MarcasCarro::name).toList());
		MarcasCarro marca = MarcasCarro.values()[marcaEscogida];
		String color = Consola.pedirString("Ingrese el color");
		String modelo = Consola.pedirString("Ingrese el modelo");
		int tipoCarro = Consola.pedirEleccion("Elija el tipo de carro", List.of("Mecanico", "Automatico"));
		TipoVehiculo tipo = TipoVehiculo.MECANICO;
		if (tipoCarro == 1) {
			tipo = TipoVehiculo.AUTOMATICO;
		}
		int puestos = Consola.pedirEntero("Ingrese el numero de puestos del carro");
		long precioVenta = Consola.pedirLong("Ingrese el precio del carro");
		Carro carroVenta = new Carro(placa, null, marca.name(), color, modelo, tipo, puestos, false, precioVenta);
		
		administrador.agregarVehiculosVenta(carroVenta);
	}

	/**
	 * metodo para "contratar un nuevo empleado"
	 */
	private void agregarEmpleado(String tipoEmpleado) {
		long cedula = Consola.pedirLong("Ingrese la cédula");

		if (parqueadero.existeEmpleado(cedula)) {
			System.out.printf("El empleado con cédula %d ya existe.%n", cedula);
			return;
		}

		String nombre = Consola.pedirString("Ingrese el nombre");
		long telefono = Consola.pedirLong("Ingrese el numero de telefono");
		String correo = Consola.pedirString("Ingrese el correo");
		String direccion = Consola.pedirString("Ingrese la dirección");
		double salario = Consola.pedirDouble("Ingrese el salario");
		Empleado nEmpleado = new Empleado(nombre, cedula, telefono, correo, direccion, tipoEmpleado, salario);
		parqueadero.agregarEmpleado(nEmpleado);
		System.out.println("Empleado contratado");
	}

	/**
	 * Realiza el despido del empleado con cédula `cedulaEmpleado`
	 */
	private void despedirEmpleado(long cedulaEmpleado) {
		List<Empleado> empleados = parqueadero.getEmpleados();
		// si existe el empleado obtenemos su indice, en cazo contrario el -1
		int empIndex = IntStream.range(0, empleados.size()).filter(i -> empleados.get(i).tieneIdentificacion(cedulaEmpleado)).findFirst().orElse(-1);
		if (empIndex != -1) {
			Empleado empleado = empleados.get(empIndex);
			//se pregunta por ultima vez
			boolean decision = Consola.pedirBoolean("Esta seguro que desea despedir a " + empleado.getNombre());
			if (decision) {
				empleados.remove(empIndex);
				System.out.println("Empleado despedido");
				return;
			}
			else {
				return;
			}
		}
		System.out.println("No hay ningun empleado con este numero de identificacion");
	}

	/**
	 * Muestra información de los mecánicos
	 */
	private void estadisticasMecanicos() {
		System.out.println("Estadísticas de los mecánicos\n");
		for (Empleado empleado : parqueadero.getMecanicos()) {
			System.out.println(empleado.getNombre());
			System.out.println("Cédula: " + empleado.getCedula());
			System.out.println("Salario: " + empleado.getSalario());
			System.out.println("Comisión: " + empleado.getComision());
			System.out.println("Servicios realizados: " + empleado.getServiciosRealizados());
			System.out.println();
		}
	}

	/**
	 * Le permite al administrador darle bonificaciones a mecánicos que cumplan ciertos requisitos.
	 */
	private void bonificacionesMecanicos() {
		System.out.println("Bonificaciones para los mecánicos");

		int minServiciosRealizados = Consola.pedirEntero("Ingrese el mínimo número de servicios realizados para recibir una bonificación");
		List<Empleado> mecanicosPorBonificar = parqueadero.getMecanicos().stream().filter(e -> e.getServiciosRealizados() > minServiciosRealizados).toList();
		System.out.println("Mecánicos que cumplen el requisito:");
		if (mecanicosPorBonificar.isEmpty()) {
			System.out.println("No hay mecánicos que cumplan el requisito");
			return;
		}
		for (Empleado mecanico : mecanicosPorBonificar) {
			System.out.println(mecanico.getNombre());
			System.out.println("Salario: " + mecanico.getSalario());
			System.out.println("Comisión: " + mecanico.getComision());
			System.out.println();
		}

		double salarioPorcetaje = Consola.pedirPorcentaje("Ingrese el porcentaje en que aumentará el salario") + 1;
		double comisionPorcentaje = Consola.pedirPorcentaje("Ingrese el porcentaje en que aumentará la comisión") + 1;
		for (Empleado mecanico : mecanicosPorBonificar) {
			double salarioAntes = mecanico.getSalario();
			double nuevoSalario = salarioAntes*salarioPorcetaje;
			mecanico.setSalario(nuevoSalario);
			double comisionAntes = mecanico.getComision();
			double nuevaComision = comisionAntes*comisionPorcentaje;
			mecanico.setComision(nuevaComision);
			System.out.println(mecanico.getNombre());
			System.out.printf("Salario: %f -> %f%n", salarioAntes, nuevoSalario);
			System.out.printf("Comisión: %f -> %f%n", comisionAntes, nuevaComision);
			System.out.println();
		}

		System.out.println("Bonificaciones realizadas");
	}

	/**
	 * Agrega un producto al almacén
	 */
	private void agregarProducto() {
		System.out.println("Agregar producto al almacén");

		Almacen almacen = parqueadero.getAlmacen();

		int tipoProductoIdx = Consola.pedirEleccion("Tipo de producto", List.of(
				"Motor",
				"Transmision",
				"Acelerador",
				"Freno",
				"Bateria",
				"Gasolina",
				"Aceite",
				"Liquidos",
				"Llanta",
				"Rin",
				"Pedal",
				"Cadena",
				"Pedales",
				"Amortiguador"
		));

		TipoProducto tipo = switch (tipoProductoIdx) {
			case 0 -> TipoProducto.MOTOR;
			case 1 -> TipoProducto.TRANSMISION;
			case 2 -> TipoProducto.ACELERADOR;
			case 3 -> TipoProducto.FRENO;
			case 4 -> TipoProducto.BATERIA;
			case 5 -> TipoProducto.GASOLINA;
			case 6 -> TipoProducto.ACEITE;
			case 7 -> TipoProducto.LIQUIDOS;
			case 8 -> TipoProducto.LLANTA;
			case 9 -> TipoProducto.RIN;
			case 10 -> TipoProducto.PEDAL;
			case 11 -> TipoProducto.CADENA;
			case 12 -> TipoProducto.PEDALES;
			default -> TipoProducto.AMORTIGUADOR; // case 13
		};

		int marcaEscogida = Consola.pedirEleccion("Escoja la marca: ", Arrays.asList(MarcasCarro.values()).stream().map(MarcasCarro::name).toList());
		MarcasCarro marca = MarcasCarro.values()[marcaEscogida];
		int tipoEstadoIdx = Consola.pedirEleccion("Elija el estado del producto", List.of(
				"Excelente",
				"Bueno",
				"Malo"
		));

		TipoEstado estado = switch (tipoEstadoIdx) {
			case 0 -> TipoEstado.EXCELENTE_ESTADO;
			case 1 -> TipoEstado.BUEN_ESTADO;
			default -> TipoEstado.MAL_ESTADO; // case 2
		};

		Producto producto = new Producto(tipo, estado);

		almacen.agregarProducto(producto);

		System.out.println("Producto agregado");
	}

	/**
	 * Muestra el inventario del almacén
	 */
	private void inventarioAlmacen() {
		System.out.println("Inventario del almacén\n");

		Almacen almacen = parqueadero.getAlmacen();

		for (Producto producto : almacen.getInventario()) {
			System.out.println("Marca: " + producto.getMarca());
			System.out.println("Tipo: " + producto.getTipo());
			System.out.println("Estado: " + producto.getEstado());
			System.out.println("Precio: " + producto.getPrecio());
			System.out.println();
		}
	}

	/**
	 * Agrega plazas al parqueadero
	 */
	private void agregarPlazas() {
		System.out.println("Agregar plazas al parqueadero");
		int nuevasPlazasCarros = Consola.pedirEntero("Ingrese el número de plazas nuevas para carros normales");
		int nuevasPlazasCarrosDiscapacitados = Consola.pedirEntero("Ingrese el número de plazas nuevas para carros modificados para personas discapacitadas");
		int nuevasPlazasMotos = Consola.pedirEntero("Ingrese el número de plazas nuevas para motos normales");
		int nuevasPlazasMotosAltoCC = Consola.pedirEntero("Ingrese el número de plazas nuevas para motos de alto cilindraje");
		parqueadero.agregarPlazas(nuevasPlazasCarros, false, "Carro");
		parqueadero.agregarPlazas(nuevasPlazasCarrosDiscapacitados, true, "Carro");
		parqueadero.agregarPlazas(nuevasPlazasMotos, false, "Moto");
		parqueadero.agregarPlazas(nuevasPlazasMotosAltoCC, false, "Moto altoCC");
	}

	/**
	 * Retira un vehículo del parqueadero
	 */
	private void retirarVehiculo() {
		String placa = Consola.pedirString("Ingrese la placa del vehículo a retirar");
		Vehiculo vehiculo = baseDatos.buscarVehiculoRegistrado(placa);
		if (vehiculo == null) {
			System.out.println("El vehículo no fue encontrado");
			return;
		}

		Factura factura = vehiculo.getDueno().getFactura();
		LocalTime horaSalida = LocalTime.now();
		long horas = ChronoUnit.HOURS.between(factura.getHoraIngreso(), horaSalida);
		factura.agregarServicio("Parqueadero", (double)horas);
		System.out.println(factura);
		vehiculo.getDueno().setFactura(null);
		parqueadero.retirarVehiculo(vehiculo.getPlaca());
		System.out.println("Vehiculo retirado");
	}

	private void informacionCliente() {
		System.out.println("Información del cliente");

		long cedula = Consola.pedirLong("Ingrese la cédula del cliente");
		Cliente cliente = baseDatos.buscarClienteRegistrado(cedula);
		if (cliente == null) {
			System.out.println("Cliente no encontrado");
			return;
		}

		System.out.println("Nombre: " + cliente.getNombre());
		System.out.println("Cédula: " + cliente.getCedula());
		System.out.println("Correo: " + cliente.getCorreo());
		System.out.println("Dirección: " + cliente.getDireccion());
		System.out.println("Teléfono: " + cliente.getTelefono());

		String discapacitado = "no";
		if (cliente.isDiscapacitado()) {
			discapacitado = "si";
		}
		System.out.println("En condición de discapacitado: " + discapacitado);
		
		if (cliente.getFactura() != null) {
			System.out.println("Se ha ganerado la siguiente factura para el cliente:");
			System.out.println(cliente.getFactura());
		}
	}
}
