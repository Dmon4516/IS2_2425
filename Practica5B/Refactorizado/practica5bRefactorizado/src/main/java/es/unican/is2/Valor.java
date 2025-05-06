package es.unican.is2;

/**
 * Clase que representa un valor en bolsa (paquete de acciones). 
 * Cada valor contiene un n�mero de acciones 
 * de una de las entidades del IBEX 35
 */
public class Valor { // CCog = 3, CCogn = 3 / 7 = 0,42, WMC = 9, WMCn = 9 / 7 = 1,28
	
	private String entidad;	
	private int numAcciones;
	private double cotizacion;
	
	public Valor(String entidad, int numAcciones, double cotizacionActual) { // CCog = 0, WMC = 1
		this.entidad = entidad;
		this.numAcciones = numAcciones;
		this.cotizacion = cotizacionActual;
	}
	
	public int getNumValores() { // CCog = 0, WMC = 1
		return numAcciones; 
	}

	public void setNumValores(int numValores) { // CCog = 0, WMC = 1
		this.numAcciones = numValores;
	}

	public double getCotizacion() { // CCog = 0, WMC = 1
		return cotizacion;
	}
	
	public void setCotizacion(double cotizacion) { // CCog = 0, WMC = 1
		this.cotizacion = cotizacion;
	}
 
	public String getEntidad() { // CCog = 0, WMC = 1
		return entidad;
	}
	
	@Override
	public boolean equals(Object obj) { // CCog = 3, WMC = 3

		if (obj == null) { // CCog + 1, WMC + 1
			return false;
		}
		
		if (this.getClass() != obj.getClass()) // CCog + 1, WMC + 1
			return false;

		Valor other = (Valor)obj;
		return (entidad.equals(other.entidad) && numAcciones==other.numAcciones); // CCog + 1
	}
	
}