package es.unican.is2;

import java.time.LocalDate;

public class Debito extends Tarjeta { // CCog = 2, CCogn = 2 / 6 = 0,3, WMC = 8, WMCn = 8 / 6 = 1,33
	
	private double saldoDiarioDisponible;

	public Debito(String numero, String titular, String cvc, CuentaAhorro cuentaAsociada) { // CCog = 0, WMC = 1
		super(numero, titular, cvc, cuentaAsociada);
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito(); 
	}

	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { // CCog = 1, WMC = 2
		// Cambiar Tarjeta para mover todos los throws a un solo metodo
		if (saldoDiarioDisponible<x) { // CCog + 1, WMC + 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Retirada en cajero", x);
		saldoDiarioDisponible-=x;
	}
	
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException { // CCog = 1, WMC = 2
		if (saldoDiarioDisponible<x) {  // CCog + 1, WMC + 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible-=x;
	}

	// Mover al padre
	public LocalDate getCaducidadDebito() { // CCog = 0, WMC = 1
		return this.cuentaAsociada.getCaducidadDebito();
	}

	// Revisar padre y revisar cuenta asociada y cuenta
	public void restableceSaldo() { // CCog = 0, WMC = 1
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}
	
	// Mover al padre
	public CuentaAhorro getCuentaAsociada() { // CCog = 0, WMC = 1
		return cuentaAsociada;
	}

}