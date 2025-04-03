package es.unican.is2;

import java.time.LocalDate;

public class Debito extends Tarjeta { // CCog 1
	
	private double saldoDiarioDisponible;

	public Debito(String numero, String titular, String cvc, CuentaAhorro cuentaAsociada) { // CC 0
		super(numero, titular, cvc, cuentaAsociada);
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito(); 
	}

	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { // 1
		if (saldoDiarioDisponible<x) { // 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Retirada en cajero", x);
		saldoDiarioDisponible-=x;
	}
	
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException { // CC 1
		if (saldoDiarioDisponible<x) {  // 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible-=x;
	}
	
	public LocalDate getCaducidadDebito() { // CC 0
		return this.cuentaAsociada.getCaducidadDebito(); // 0
	}
	
	public void restableceSaldo() { // CC 0
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito(); // 0
	}
	
	public CuentaAhorro getCuentaAsociada() { // CC 0
		return cuentaAsociada; // 0
	}

}