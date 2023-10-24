/**
 *  Funcionalidad del módulo: contiene un enum de con constantes del estado en que se encuentra un
 * vehiculo luego de haber sido revisado por un mecanico
 Componentes del módulo: TipoEstado
 */

package gestorAplicacion.parqueadero;

public enum TipoEstado {
	MAL_ESTADO(0), BUEN_ESTADO(1), EXCELENTE_ESTADO(2);
	
	private int estado;
	private TipoEstado(int estado) {
		this.estado = estado;
	}
	
	public int getEstado() {
		return this.estado;
	}
	
	public static TipoEstado segunNumero(int estado) {
		return switch (estado) {
			case 0 -> TipoEstado.MAL_ESTADO;
			case 1 -> TipoEstado.BUEN_ESTADO;
			case 2 -> TipoEstado.EXCELENTE_ESTADO;
			default -> throw new RuntimeException("Estado inválido");
		};
	}
}
