package gestorAplicacion.vehiculos;

public enum MarcasMoto {
	YAMAHA(5), BAJAJ(4), HONDA(3), KTM(2), SUZUKI(1);
	
	private int ordenPrecio;
	private MarcasMoto(int ordenPrecio) {
		this.ordenPrecio = ordenPrecio;	
	}
	
	public int getOrdenPrecio() {
		return this.ordenPrecio;
	}
}
