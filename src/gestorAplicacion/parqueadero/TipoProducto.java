package gestorAplicacion.parqueadero;

public enum TipoProducto {
		MOTOR,TRANSMISION,ACELERADOR,FRENO,BATERIA,GASOLINA,ACEITE,LIQUIDOS,LLANTA,RIN,PEDAL,CADENA,PEDALES,AMORTIGUADOR;
		
		@Override
		public String toString() {
			return cap(this.name());
		}
		
		private static String cap(String palabra) {
	        return Character.toUpperCase(palabra.charAt(0)) + palabra.substring(1).toLowerCase();
	    }
}
