package es.unican.is2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class Credito extends Tarjeta { // CCog = 8, CCogn = 8 / 9 = 0,88, WMC = 14, WMCn = 14 / 9 = 1,55
	
	private double credito;
	private List<Movimiento> MovimientosMensuales;
	private List<Movimiento> historicoMovimientos; // Estandarizador nombre

	public Credito(String numero, String titular, String cvc,
			CuentaAhorro cuentaAsociada, double credito) { // CCog = 0, WMC = 1
		super(numero, titular, cvc, cuentaAsociada);
		this.credito = credito;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. Se aplica una comisi�n del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { // CCog = 3, WMC = 4
		// Mayoritariamente lo mismo que en CuentaAhorro, todos los throws a un metodo (cambiar clase Tarjeta para ello), y mirar la clase Movimiento
		if (x<0) // CCog + 1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		Movimiento m = new Movimiento(); // Estandarizador nombre
		// Revisar con cambios en movimiento
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Retirada en cajero");
		x += x * 0.05; // Comision por operacion con tarjetas credito
		m.setI(-x);
		
		if (getGastosAcumulados()+x > credito) // CCog + 1
			throw new saldoInsuficienteException("Credito insuficiente");
		else { // CCog + 1
			MovimientosMensuales.add(m); 
		}
	}

	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException { // CCog = 2, WMC = 3
		if (x<0) // CCog + 1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		if (getGastosAcumulados() + x > credito) // CCog + 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		
		// Revisar con cambios en movimiento
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Compra a credito en: " + datos);
		m.setI(-x);
		MovimientosMensuales.add(m);
	}
	
	// Cambiar nombre de variables
    private double getGastosAcumulados() { // CCog = 1, WMC = 1
		double r = 0.0;
		for (int i = 0; i < this.MovimientosMensuales.size(); i++) { // CCog + 1
			Movimiento m = (Movimiento) MovimientosMensuales.get(i);
			r += m.getI();
		}
		return r;
	}
	
	// Idk
	public LocalDate getCaducidadCredito() { // CCog = 0, WMC = 1
		return this.cuentaAsociada.getCaducidadCredito();
	}

	/**
	 * Metodo que se invoca automaticamente el dia 1 de cada mes
	 */
	public void liquidar() { // CCog = 2, WMC = 2
		Movimiento liq = new Movimiento(); // Revisar con cambios en movimiento
		LocalDateTime now = LocalDateTime.now();
		liq.setF(now);
		liq.setC("Liquidacion de operaciones tarjeta credito");
		double r = 0.0; // Estandarizador nombre
		for (int i = 0; i < this.MovimientosMensuales.size(); i++) { // CCog + 1
			Movimiento m = (Movimiento) MovimientosMensuales.get(i); // Estandarizador nombre
			r += m.getI(); // Cambiar nombre y revisar con cambios en movimiento
		}
		liq.setI(-r); // Revisar nombre y cambios con movimiento
	
		if (r != 0) // CCog + 1
			cuentaAsociada.addMovimiento(liq);
		
		historicoMovimientos.addAll(MovimientosMensuales);
		MovimientosMensuales.clear();
	}

	public List<Movimiento> getMovimientosMensuales() { // CCog = 0, WMC = 1
		return MovimientosMensuales;
	}
	
	// Mover al padre
	public CuentaAhorro getCuentaAsociada() { // CCog = 0, WMC = 1
		return cuentaAsociada;
	}
	
	public List<Movimiento> getMovimientos() { // CCog = 0, WMC = 1
		return historicoMovimientos;
	}

}