package gestorAplicacion.parqueadero;

public enum TipoProducto {
		MOTOR,ACELERADOR,TRANSMISION,FRENO,BATERIA,PEDAL,CADENA,PEDALES,GASOLINA,ACEITE,LIQUIDOS,LLANTA,RIN,AMORTIGUADOR;
		
		@Override
		public String toString() {
			return cap(this.name());
		}
		
		private static String cap(String palabra) {
	        return Character.toUpperCase(palabra.charAt(0)) + palabra.substring(1).toLowerCase();
	    }
}
