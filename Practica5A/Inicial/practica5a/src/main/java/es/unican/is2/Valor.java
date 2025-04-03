package es.unican.is2;

/**
 * Clase que representa un valor en bolsa (paquete de acciones). 
 * Cada valor contiene un n�mero de acciones 
 * de una de las entidades del IBEX 35
 */ 
public class Valor { // CCog 2
	
	private String entidad;	
	private int numAcciones;
	private double cotizacion;
	
	public Valor(String entidad, int numAcciones, double cotizacionActual) { // 0
		this.entidad = entidad;
		this.numAcciones = numAcciones;
		this.cotizacion = cotizacionActual;
	}
	
	public int getNumValores() { // CC 0
		return numAcciones; 
	}

	public void setNumValores(int numValores) { // CC 0
		this.numAcciones = numValores;
	}

	public double getCotizacion() { // CC 0
		return cotizacion;
	}
	
	public void setCotizacion(double cotizacion) { // CC 0
		this.cotizacion = cotizacion;
	}
 
	public String getEntidad() { // CC 0
		return entidad;
	}
	
	@Override
	public boolean equals(Object obj) { // CC 2
		Valor other = (Valor)obj;
		return (entidad.equals(other.entidad) && numAcciones==other.numAcciones); // 2

	}

}