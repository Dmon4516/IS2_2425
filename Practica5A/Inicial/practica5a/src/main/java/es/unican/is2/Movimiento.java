package es.unican.is2;

import java.time.LocalDateTime;

public class Movimiento { // CCog 3
	private String concepto;
	private LocalDateTime fecha;
	private double importe;

	public double getI() { // CC 0
		return importe;
	}

	public void setI(double newMImporte) { // CC 0
		importe = newMImporte;
	}
	
	public String getC() { // CC 0
		return concepto;
	}

	public void setC(String newMConcepto) { // CC 0
		concepto = newMConcepto;
	}

	public LocalDateTime getF() { // CC 0
		return fecha;
	}

	public void setF(LocalDateTime newMFecha) { // CC 0
		fecha = newMFecha;
	}

	
	@Override
	public boolean equals(Object obj) { // CC 3
		Movimiento other = (Movimiento)obj;
		return (concepto.equals(other.concepto) && fecha.equals(other.fecha)&& importe==other.importe); // 3
	}
	
}