package es.unican.is2;

import java.time.LocalDateTime;

public class Movimiento { // CCog = 1, CCogn = 1 / 7 = 0,14, WMC = 10, WMCn = 10 / 7 = 1,43
	private String concepto;
	private LocalDateTime fecha;
	private double importe;

	public double getI() { // CCog = 0, WMC = 1
		return importe;
	}

	public void setI(double newMImporte) { // CCog = 0, WMC = 1
		importe = newMImporte;
	}
	
	public String getC() { // CCog = 0, WMC = 1
		return concepto;
	}

	public void setC(String newMConcepto) { // CCog = 0, WMC = 1
		concepto = newMConcepto;
	}

	public LocalDateTime getF() { // CCog = 0, WMC = 1
		return fecha;
	}

	public void setF(LocalDateTime newMFecha) { // CCog = 0, WMC = 1
		fecha = newMFecha;
	}

	
	@Override
	public boolean equals(Object obj) { // CCog = 1, WMC = 4
		Movimiento other = (Movimiento)obj;
		return (concepto.equals(other.concepto) && fecha.equals(other.fecha) && importe==other.importe); // CCog + 1, WMC + 3
	}
	
}