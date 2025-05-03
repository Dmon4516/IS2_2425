package es.unican.is2;

import java.util.LinkedList;
import java.util.List;

public class CuentaValores extends Cuenta { // CCog = 4, CCogn = 4 / 4 = 1, WMC = 5, WMCn = 5 / 4 = 1,25

	private List<Valor> valores;
	
	public CuentaValores(String numCuenta) { // CCog = 0, WMC = 1
		super(numCuenta);
		valores = new LinkedList<Valor>();
	}
	
	public List<Valor> getValores() { // CCog = 0, WMC = 1
		return valores;
	}

	// Revisar
	public boolean anhadeValor(Valor valor) { // CCog = 3, WMC = 2
		for (Valor v:valores) { // CCog + 1
			if (v.getEntidad().equals(valor.getEntidad())) // CCog + 2 (nesting=1), WMC + 1
				return false;
		}
		valores.add(valor);
		return true;
	}
	
	// Anadir valor total de valores
	@Override
	public double calculaSaldo() { // CCog = 1, WMC = 1
		double total = 0;
		for (Valor v:valores) { // CCog + 1
			total += v.getNumValores() * v.getCotizacion();
		}
		return total;
	}
}
