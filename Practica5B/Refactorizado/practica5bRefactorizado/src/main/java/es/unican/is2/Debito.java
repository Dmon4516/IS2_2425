package es.unican.is2;

import java.time.LocalDate;

public class Debito extends Tarjeta { // CCog = 0, CCogn = 0 / 9 = 0, WMC = 9, WMCn = 9 / 9 = 1
	
	private double limite;
	private double saldoDiarioDisponible;
	private LocalDate caducidadDebito;
	private LocalDate fechaCaducidad;

	public Debito(String numero, String titular, String cvc, CuentaAhorro cuentaAsociada) { // CCog = 0, WMC = 1
		super(numero, titular, cvc, cuentaAsociada);
		limite = 1000.0;
		saldoDiarioDisponible = limite; 
	}

	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { // CCog = 0, WMC = 1
		ValidacionCantidades.confirmaCantidadNegativa(x);
		ValidacionCantidades.confirmaCreditoOSaldo(x, saldoDiarioDisponible, "Saldo insuficiente");
		this.cuentaAsociada.retirar("Retirada en cajero", x);
		saldoDiarioDisponible -= x ;
	}
	
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException { // CCog = 0, WMC = 1
		ValidacionCantidades.confirmaCantidadNegativa(x);
		ValidacionCantidades.confirmaCreditoOSaldo(x, saldoDiarioDisponible, "Saldo insuficiente");
		
		this.cuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible -= x;
	}

	@Override
	public void actualizaCaducidadCuenta() { // CCog = 0, WMC = 1
		this.fechaCaducidad = caducidadDebito;
	}
	
	public LocalDate getCaducidadDebito() { // CCog = 0, WMC = 1
		return caducidadDebito;
	}

	public void setCaducidadDebito(LocalDate caducidadDebito) { // CCog = 0, WMC = 1
		this.caducidadDebito = caducidadDebito;
	}

	public double getLimiteDebito() { // CCog = 0, WMC = 1
		return limite;
	}
	
	public void restableceSaldo() { // CCog = 0, WMC = 1
		saldoDiarioDisponible = limite;
	}
	
	public CuentaAhorro getCuentaAsociada() { // CCog = 0, WMC = 1
		return cuentaAsociada;
	}

}