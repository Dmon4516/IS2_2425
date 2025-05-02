package es.unican.is2;

public class Cuenta { // CCog = 0, CCogn = 0 / 2 = 0, WMC = 2, WMCn = 2/2 = 1
	
	private String numCuenta;
	
	public Cuenta(String numCuenta) { // CCog = 0, WMC = 1
		this.numCuenta = numCuenta;
	}
	
	public String getNumCuenta() { // CCog = 0, WMC = 1
		return numCuenta;
	}
	
}
