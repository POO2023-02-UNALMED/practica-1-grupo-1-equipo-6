package gestorAplicacion.vehiculos;

public enum MarcasCarro { //TODO: adecuar este enum para carros y motos
	TOYOTA(5), MAZDA(4), CHEVROLET(3), KIA(2), RENAULT(1);
	
	private int ordenPrecio;
	private MarcasCarro(int ordenPrecio) {
		this.ordenPrecio = ordenPrecio;
	}
	
	public int getOrdenPrecio() {
		return this.ordenPrecio;
	}
}