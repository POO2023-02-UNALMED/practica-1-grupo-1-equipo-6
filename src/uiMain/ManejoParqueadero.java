package uiMain;

import java.util.List;
import java.util.stream.IntStream;

import gestorAplicacion.parqueadero.Almacen;
import gestorAplicacion.parqueadero.Producto;
import gestorAplicacion.parqueadero.TipoEstado;
import gestorAplicacion.parqueadero.TipoProducto;
import gestorAplicacion.personas.Empleado;
import gestorAplicacion.vehiculos.TipoVehiculo;
import gestorAplicacion.vehiculos.Carro;

public class ManejoParqueadero extends Funcionalidad {
	@Override
	public void ejecutar() {
		long cedulaAdministrador = Consola.pedirLong("Ingrese la cédula del administrador");
		if (!parqueadero.getAdministrador().tieneIdentificacion(cedulaAdministrador)) {
			System.out.println("Cédula de administrador incorrecta");
			return;
		}

		int eleccion;
		do {
		eleccion = Consola.pedirEleccion("Parte del parqueadero a manejar", List.of(
				"Taller",
				"Parqueadero",
				"Almacén",
				"Agregar carros para vender",
				"Regresar al menú principal"
		));
		
		switch (eleccion) {
			case 0 -> administrarTaller();
			case 1 -> administrarParqueadero();
			case 2 -> administrarAlmacen();
			case 3 -> agregarCarroVenta(parqueadero.getAdministrador());
		}
		} while (eleccion != 4);
	}

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

	private void administrarParqueadero() {
		
	}

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
	//método para agregar carros para vender
	private void agregarCarroVenta(Empleado administrador) {
		String placa = Consola.pedirString("Ingrese la placa: ");
		String marca = Consola.pedirString("Ingrese la marca: ");
		String color = Consola.pedirString("Ingrese el color: ");
		String modelo = Consola.pedirString("Ingrese el modelo: ");
		int tipoCarro = Consola.pedirEleccion("Elija el tipo de carro", List.of("Mecanico", "Automatico"));
		TipoVehiculo tipo = TipoVehiculo.MECANICO;
		if (tipoCarro == 1) {
			tipo = TipoVehiculo.AUTOMATICO;
		}
		int puestos = Consola.pedirEntero("Ingrese el numero de puestos del carro: ");
		long precioVenta = Consola.pedirLong("Ingrese el precio del carro: ");
		Carro carroVenta = new Carro(placa, null, marca, color, modelo, tipo, puestos, false, precioVenta);
		
		administrador.agregarVehiculosVenta(carroVenta);
	}
	// metodo para "contratar un nuevo empleado"
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

		double precio = Consola.pedirDouble("Ingrese el precio del producto");
		String marca = Consola.pedirString("Ingrese la marca del producto");

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

		Producto producto = new Producto(tipo, precio, marca, estado);

		almacen.agregarProducto(producto);

		System.out.println("Producto agregado");
	}

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
}
