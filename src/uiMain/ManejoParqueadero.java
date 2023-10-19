package uiMain;

import java.util.List;

public class ManejoParqueadero extends Funcionalidad {
	@Override
	public void ejecutar() {
		long cedulaAdministrador = Consola.pedirLong("Ingrese la cédula del administrador");
		if (!parqueadero.getAdministrador().tieneIdentificacion(cedulaAdministrador)) {
			System.out.println("Cédula de administrador incorrecta");
			return;
		}

		int eleccion = Consola.pedirEleccion("Parte del parqueadero a manejar", List.of(
				"Taller",
				"Parqueadero",
				"Almacén"
		));

		switch (eleccion) {
			case 0 -> administrarTaller();
			case 1 -> administrarParqueadero();
			case 2 -> administrarAlmacen();
		}
	}

	private void administrarTaller() {

	}

	private void administrarParqueadero() {

	}

	private void administrarAlmacen() {

	}
}
