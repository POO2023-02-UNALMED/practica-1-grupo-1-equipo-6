package gestorAplicacion.parqueadero;

public class Factura {
	private double totalCobro;
	
	public Factura(double cobro) {
		this.setTotalCobro(cobro);
		
	}

	public double getTotalCobro() {
		return totalCobro;
	}

	public void setTotalCobro(double totalCobro) {
		this.totalCobro = totalCobro;
	}
	
}
