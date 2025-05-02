package es.unican.is2;

@SuppressWarnings("serial")
public class saldoInsuficienteException extends RuntimeException { // CCog = 0, CCogn = 0 / 1 = 0, WMC = 1, WMCn = 1 / 1 = 1

	public saldoInsuficienteException (String mensaje) { // CCog = 0, WMC = 1
		super(mensaje);
	}
}
