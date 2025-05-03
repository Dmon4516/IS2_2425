package es.unican.is2;

public abstract class Tarjeta { // CCog = 0, CCogn = 0 / 1 = 0, WMC = 1, WMCn = 1 / 1 = 1
	
	protected String numero, titular, cvc;		
	protected CuentaAhorro cuentaAsociada;

	// Añadir unico metodo con todos los throws

	public Tarjeta(String numero, String titular, String cvc,
			CuentaAhorro cuentaAsociada) { // CCog = 0, WMC = 1
		this.numero = numero;
		this.titular = titular;
		this.cvc = cvc;
		this.cuentaAsociada = cuentaAsociada;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. 
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void retirar(double x) throws saldoInsuficienteException, datoErroneoException;

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param x Cantidada a pagar
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void pagoEnEstablecimiento(String datos, double x)
			throws saldoInsuficienteException, datoErroneoException;
	
}