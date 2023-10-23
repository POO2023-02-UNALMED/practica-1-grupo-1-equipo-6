//Sofía
//sebas
// Katherine

package gestorAplicacion.personas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import gestorAplicacion.parqueadero.Producto;
import gestorAplicacion.parqueadero.TipoEstado;
import gestorAplicacion.parqueadero.TipoProducto;
import gestorAplicacion.vehiculos.*;
import gestorAplicacion.parqueadero.Almacen;

public class Empleado extends Persona implements Serializable{
    private static final long serialVersionUID = 1L;
	
	private String cargo;
    private double salario;
    private double comision;
    private int serviciosRealizados; // dependiendo del tipo de empleado serian productos vendidos o vehiculos vendidos y/o comprados, 
    								 // componentes arreglados, revisiones, servicios mecanicos en general, etc.
    private static ArrayList<Carro> vehiculosVenta = new ArrayList<Carro>();  //Lista de vehiculos disponibles para vender
    
    public Empleado(String nombre, long cedula, long telefono, String correo, String direccion, String cargo, double salario) {
    	super(nombre, cedula, telefono, correo, direccion);
    	this.cargo = cargo;
    	this.salario = salario;
    	this.comision = 0.1;
    	serviciosRealizados = 0;
    }
    
    public void setVehiculosVenta(ArrayList<Carro> vehVenta) {
    	Empleado.vehiculosVenta = vehVenta;
    }
    public ArrayList<Carro> getVehiculosVenta() {
    	return Empleado.vehiculosVenta;
    }
    
    public static void agregarVehiculosVenta(Carro carroVenta) {
    	Empleado.vehiculosVenta.add(carroVenta);
    }
    public void setCargo(String cargo) {
    	this.cargo = cargo;
    }
    public String getCargo() {
    	return this.cargo;
    }
    public void setSalario(double salario) {
    	this.salario = salario;
    }
    public double getSalario() {
    	return this.salario;
    }
    public void setComision(double comision) {
    	this.comision = comision;
    }
    public double getComision() {
    	return this.comision;
    }
    public void setServiciosRealizados(int serviciosRealizados) {
    	this.serviciosRealizados = serviciosRealizados;
    }
    public int getServiciosRealizados() {
    	return this.serviciosRealizados;
    }
    
    
    /**
     * Metodo que utiliza un empleado tipo mecanico para revisar un vehiculo y devolver su estado, usar para verificar en la funcionalidad venta de
     * Vehiculo
     */
    public List<Producto> revisarVehiculo(Vehiculo vehiculo) { // retornar boolean y array con los productos en mal estado
    	List<Producto> r = new ArrayList<>(); //lista para guardar los productos en mal estado
    	
    	//comprobar que el empleado sea de tipo mecanico, sino lanzar excepcion(implementar)
    	if (this.getCargo().equals("Mecanico")) {
    	
    		//verificar si el vehiculo es de tipo carro e ir buscando componente a componente si hay alguno en mal estado o dañado
    		if (vehiculo instanceof Carro) {
    			if (((Carro) vehiculo).getMotor().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Carro) vehiculo).getMotor());
    			}
    			if (((Carro) vehiculo).getTransmision().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Carro) vehiculo).getTransmision());
    			}
    			if (((Carro) vehiculo).getAcelerador().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Carro) vehiculo).getAcelerador());
    			}
    			if (((Carro) vehiculo).getFreno().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Carro) vehiculo).getFreno());
    			}
    			if (((Carro) vehiculo).getBateria().getEstado().equals(TipoEstado.MAL_ESTADO))  {
    				r.add(((Carro) vehiculo).getBateria());
    			}
    			if (((Carro) vehiculo).getPedal().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Carro) vehiculo).getPedal());
    			}
    		
    			for (Producto deposito : ((Carro) vehiculo).getDepositos()) {
    				if (deposito.getEstado().equals(TipoEstado.MAL_ESTADO)) {
    					r.add(deposito);
    				}
    			}
    			
    			for (Producto llanta : ((Carro) vehiculo).getLlantas()) {
    				if (llanta.getEstado().equals(TipoEstado.MAL_ESTADO)) {
    					r.add(llanta);
    				}
    			}
    		
    			for (Producto rin : ((Carro) vehiculo).getRines()) {
    				if (rin.getEstado().equals(TipoEstado.MAL_ESTADO)) {
    					r.add(rin);
    				}
    			}
    		
    			for (Producto amortiguador : ((Carro) vehiculo).getAmortiguadores()) {
    				if (amortiguador.getEstado().equals(TipoEstado.MAL_ESTADO)) {
    					r.add(amortiguador);
    				}
    			}
    		}
    		
    		//mismo proceso pero con un vehiculo de tipo moto
    		if (vehiculo instanceof Moto) {
    			if (((Moto) vehiculo).getMotor().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Moto) vehiculo).getMotor());
    			}
    			if (((Moto) vehiculo).getTransmision().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Moto) vehiculo).getTransmision());
    			}
    			if (((Moto) vehiculo).getAcelerador().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Moto) vehiculo).getAcelerador());
    			}
    			if (((Moto) vehiculo).getFreno().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Moto) vehiculo).getFreno());
    			}
    			if (((Moto) vehiculo).getCadena().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Moto) vehiculo).getCadena());
    			}
    			if (((Moto) vehiculo).getPedales().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Moto) vehiculo).getPedales());
    			}
    			if (((Moto) vehiculo).getBateria().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Moto) vehiculo).getBateria());
    			}
    			if (((Moto) vehiculo).getAmortiguador().getEstado().equals(TipoEstado.MAL_ESTADO)) {
    				r.add(((Moto) vehiculo).getAmortiguador());
    			}
    			
    			for (Producto deposito : ((Moto) vehiculo).getDepositos()) {
    				if (deposito.getEstado().equals(TipoEstado.MAL_ESTADO)) {
    					r.add(deposito);
    				}
    			}
    			
    			for (Producto llanta : ((Moto) vehiculo).getLlantas()) {
    				if (llanta.getEstado().equals(TipoEstado.MAL_ESTADO)) {
    					r.add(llanta);
    				}
    			}
    			
    			for (Producto rin : ((Moto) vehiculo).getRines()) {
    				if (rin.getEstado().equals(TipoEstado.MAL_ESTADO)) {
    					r.add(rin);
    				}
    			}
    		}
    		
    		//retornar la lista
        		return r;
    	}
    	else {
    		return null; //se retorna null si el empleado no es de tipo mecanico
    	}
    }
    
    // metodo para cambiar un componente de un vehiculo por otro
    public void cambiar(Producto productoViejo, Producto productoNuevo, Vehiculo vehiculo) {
    	productoNuevo.setMarca(vehiculo.getMarca()); productoNuevo.setPrecio(0);
    	
    	// se comprueba para carro
    	if (vehiculo instanceof Carro) {
    		 
    		if (((Carro)vehiculo).getMotor().equals(productoViejo)) {
    			((Carro) vehiculo).setMotor(productoNuevo);
    		}
    		if (((Carro)vehiculo).getTransmision().equals(productoViejo)) {
    			((Carro) vehiculo).setTransmision(productoNuevo);
    		}
    		if (((Carro)vehiculo).getAcelerador().equals(productoViejo)) {
    			((Carro) vehiculo).setAcelerador(productoNuevo);
    		}
    		if (((Carro)vehiculo).getFreno().equals(productoViejo)) {
    			((Carro) vehiculo).setFreno(productoNuevo);
    		}
    		if (((Carro)vehiculo).getBateria().equals(productoViejo)) {
    			((Carro) vehiculo).setBateria(productoNuevo);
    		}
    		if (((Carro)vehiculo).getPedal().equals(productoViejo)) {
    			((Carro) vehiculo).setPedal(productoNuevo);
    		}
    		 
    		int i = 0;
    		for (Producto deposito : ((Carro) vehiculo).getDepositos()) {
    			if (deposito.equals(productoViejo)) {
    				((Carro) vehiculo).getDepositos()[i] = productoNuevo;
 					i = 0; return;
 				}
 				i++;
 			}
    		 
    		for (Producto llanta : ((Carro) vehiculo).getLlantas()) {
  				if (llanta.equals(productoViejo)) {
  					((Carro) vehiculo).getLlantas()[i] = productoNuevo;
  					i = 0; return;
  				}
  				i++;
  			}
    		 
    		for (Producto rin : ((Carro) vehiculo).getRines()) {
  				if (rin.equals(productoViejo)) {
  					((Carro) vehiculo).getRines()[i] = productoNuevo;
  					i = 0; return;
  				}
  				i++;
  			}
    		 
    		for (Producto amortiguador : ((Carro) vehiculo).getAmortiguadores()) {
  				if (amortiguador.equals(productoViejo)) {
  					((Carro) vehiculo).getAmortiguadores()[i] = productoNuevo;
  					i = 0; return;
  				}
  				i++;
  			}
    	}
    	
    	//mismo pero para moto
    	if (vehiculo instanceof Moto) {
    		
    		if (((Moto)vehiculo).getMotor().equals(productoViejo)) {
    			((Moto) vehiculo).setMotor(productoNuevo);
   		 	}
    		if (((Moto)vehiculo).getTransmision().equals(productoViejo)) {
      			((Moto) vehiculo).setTransmision(productoNuevo);
      		}
    		if (((Moto)vehiculo).getAcelerador().equals(productoViejo)) {
    			((Moto) vehiculo).setAcelerador(productoNuevo);
   		 	}
    		if (((Moto)vehiculo).getFreno().equals(productoViejo)) {
      			((Moto) vehiculo).setFreno(productoNuevo);
      		}
    		if (((Moto)vehiculo).getCadena().equals(productoViejo)) {
    			((Moto) vehiculo).setCadena(productoNuevo);
   		 	}
    		if (((Moto)vehiculo).getPedales().equals(productoViejo)) {
      			((Moto) vehiculo).setPedales(productoNuevo);
      		}
    		if (((Moto)vehiculo).getBateria().equals(productoViejo)) {
    			((Moto) vehiculo).setBateria(productoNuevo);
   		 	}
    		if (((Moto)vehiculo).getAmortiguador().equals(productoViejo)) {
      			((Moto) vehiculo).setAmortiguador(productoNuevo);
      		}
    		
    		int i = 0;
    		for (Producto deposito : ((Moto) vehiculo).getDepositos()) {
    			if (deposito.equals(productoViejo)) {
    				((Moto) vehiculo).getDepositos()[i] = productoNuevo;
 					i = 0; return;
 				}
 				i++;
 			}
    		 
    		for (Producto llanta : ((Moto) vehiculo).getLlantas()) {
  				if (llanta.equals(productoViejo)) {
  					((Moto) vehiculo).getLlantas()[i] = productoNuevo;
  					i = 0; return;
  				}
  				i++;
  			}
    		 
    		for (Producto rin : ((Moto) vehiculo).getRines()) {
  				if (rin.equals(productoViejo)) {
  					((Moto) vehiculo).getRines()[i] = productoNuevo;
  					i = 0; return;
  				}
  				i++;
  			}
    	}

    }
    
    //Método que devuelve el precio de un carro en perfecto estado
    public long precioMaximoCarro(MarcasCarro marca) {
    	long precioTotal = 0;
    	precioTotal += Almacen.cotizarProducto(TipoProducto.MOTOR) * marca.getOrdenPrecio();
    	precioTotal += Almacen.cotizarProducto(TipoProducto.TRANSMISION) * marca.getOrdenPrecio();
    	precioTotal += Almacen.cotizarProducto(TipoProducto.ACELERADOR) * marca.getOrdenPrecio();
    	precioTotal += Almacen.cotizarProducto(TipoProducto.FRENO) * marca.getOrdenPrecio();
    	precioTotal += Almacen.cotizarProducto(TipoProducto.BATERIA) * marca.getOrdenPrecio();
    	precioTotal += Almacen.cotizarProducto(TipoProducto.PEDAL) * marca.getOrdenPrecio();
    	precioTotal += Almacen.cotizarProducto(TipoProducto.ACEITE) * marca.getOrdenPrecio();
    	precioTotal += Almacen.cotizarProducto(TipoProducto.GASOLINA) * marca.getOrdenPrecio();
    	precioTotal += Almacen.cotizarProducto(TipoProducto.LIQUIDOS) * marca.getOrdenPrecio();
    	for (int i=0; i<=3; i++) {
    		precioTotal += Almacen.cotizarProducto(TipoProducto.LLANTA) * marca.getOrdenPrecio();
    	}
    	for (int i=0; i<=3; i++) {
    		precioTotal += Almacen.cotizarProducto(TipoProducto.RIN) * marca.getOrdenPrecio();
    	}
    	for (int i=0; i<=3; i++) {
    		precioTotal += Almacen.cotizarProducto(TipoProducto.AMORTIGUADOR) * marca.getOrdenPrecio();
    	}
    	return precioTotal;
    				
    }
}