package es.unican.is2;

import java.time.LocalDateTime;

public class Movimiento { // CCog = 1, CCogn = 1 / 8 = 0,12, WMC = 8, WMCn = 8 / 8 = 1
	private String concepto;
	private LocalDateTime fecha;
	private double importe;

	// Agregar metodo constructor
	public Movimiento(String concepto, LocalDateTime fecha, double importe) { // CCog = 0, WMC = 1
		this.concepto = concepto;
		this.fecha = fecha;
		this.importe = importe;
	}

	public double getImporte() { // CCog = 0, WMC = 1
		return importe;
	}

	public void setImporte(double newMImporte) { // CCog = 0, WMC = 1
		importe = newMImporte;
	}

	public String getConcepto() { // CCog = 0, WMC = 1
		return concepto;
	}

	public void setConcepto(String newMConcepto) { // CCog = 0, WMC = 1
		concepto = newMConcepto;
	}

	public LocalDateTime getFecha() { // CCog = 0, WMC = 1
		return fecha;
	}

	public void setFecha(LocalDateTime newMFecha) { // CCog = 0, WMC = 1
		fecha = newMFecha;
	}
	
	@Override
	public boolean equals(Object obj) { // CCog = 1, WMC = 1
		if (obj == null) { // CCog + 1, WMC + 1
			return false;
		}
		
		if (this.getClass() != obj.getClass()) // CCog + 1, WMC + 1
			return false;

		Movimiento other = (Movimiento)obj;
		return (concepto.equals(other.concepto) && fecha.equals(other.fecha) && importe==other.importe); // CCog + 1
	}
	
}