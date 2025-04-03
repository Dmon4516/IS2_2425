package es.unican.is2;

import java.util.LinkedList;
import java.util.List;

public class CuentaValores extends Cuenta { // CCog = 3, CCogn = 3 / 3 = 1

	private List<Valor> valores;
	
	public CuentaValores(String numCuenta) { // CCog = 0
		super(numCuenta);
		valores = new LinkedList<Valor>();
	}
	
	public List<Valor> getValores() { // CCog = 0
		return valores;
	}
	
	public boolean anhadeValor(Valor valor) { // CCog = 3
		for (Valor v:valores) { // CCog + 1
			if (v.getEntidad().equals(valor.getEntidad())) // CCog + 2 (nesting=1)
				return false;
		}
		valores.add(valor);
		return true;
	}
	
}
