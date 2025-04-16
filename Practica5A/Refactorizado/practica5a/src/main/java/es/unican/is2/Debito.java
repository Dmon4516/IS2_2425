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
		confirmaSaldo(x);
		this.cuentaAsociada.retirar("Retirada en cajero", x);
		saldoDiarioDisponible -= x ;
	}
	
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException { // CCog = 0, WMC = 2
		confirmaSaldo(x);
		confirmaCantidadNegativa(x);
		this.cuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible -= x;
	}

	private void confirmaSaldo(double x) throws saldoInsuficienteException { // CCog = 1, WMC = 2
		if (saldoDiarioDisponible < x) { // CCog + 1, WMC + 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
	}
	private void confirmaCantidadNegativa(double x) throws datoErroneoException { // CCog = 1, WMC = 2
		if (x <= 0) { // CCog + 1, WMC + 1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		}
	}

	// TODO: considerar eliminar metodos de aqui si no se usa en el resto de clases para bajar el WMC, considerar sacar TODOS los throws y meterlos en una clase aparte

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