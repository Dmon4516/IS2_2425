package es.unican.is2;

import java.time.LocalDate;

public class Debito extends Tarjeta { // CCog = 2, CCogn = 2 / 11 = 0,18, WMC = 15, WMCn = 15 / 11 = 1,36
	
	private double limite;
	private double saldoDiarioDisponible;
	private LocalDate caducidadDebito;

	public Debito(String numero, String titular, String cvc, CuentaAhorro cuentaAsociada) { // CCog = 0, WMC = 1
		super(numero, titular, cvc, cuentaAsociada);
		limite = 1000.0;
		saldoDiarioDisponible = limite; 
	}

	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { // CCog = 0, WMC = 2
		confirmaCantidadNegativa(x);
		confirmaCredito(x, saldoDiarioDisponible);
		this.cuentaAsociada.retirar("Retirada en cajero", x);
		saldoDiarioDisponible -= x ;
	}
	
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException { // CCog = 0, WMC = 2
		confirmaCantidadNegativa(x);
		confirmaCredito(x, saldoDiarioDisponible);
		
		this.cuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible -= x;
	}

	@Override
	public void actualizaCaducidadCuenta() { // CCog = 0, WMC = 1
		this.fechaCaducidad = cuentaAsociada.getCaducidadDebito();
	}
	
	public LocalDate getCaducidadDebito() { // CCog = 0, WMC = 1
		return caducidadDebito;
	}

	public void setCaducidadDebito(LocalDate caducidadDebito) { // CCog = 0, WMC = 1
		this.caducidadDebito = caducidadDebito;
	}

	public double getLimiteDebito() { // CCog = 0, WMC = 1
		return limiteDebito;
	}
	
	// Mover de cuenta
	public void restableceSaldo() { // CCog = 0, WMC = 1
		saldoDiarioDisponible = limite;
	}
	
	//  Revisar padre
	public CuentaAhorro getCuentaAsociada() { // CCog = 0, WMC = 1
		return cuentaAsociada;
	}

}