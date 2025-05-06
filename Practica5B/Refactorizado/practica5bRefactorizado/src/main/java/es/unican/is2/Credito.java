package es.unican.is2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class Credito extends Tarjeta { // CCog = 3, CCogn = 3 / 11 = 0,27, WMC = 12, WMCn = 12 / 11 = 1,09
	
	private double cantidadCredito;
	private List<Movimiento> movimientosMensuales;
	private List<Movimiento> historicoMovimientos; // Estandarizador nombre

	private String retiro = "Retirada en cajero";
	private String compra = "Compra a credito en: ";
	private String liquidacion = "Liquidacion de operaciones tarjeta credito";

	private LocalDate caducidadCredito;

	public Credito(String numero, String titular, String cvc,
			CuentaAhorro cuentaAsociada, double credito) { // CCog = 0, WMC = 1
		super(numero, titular, cvc, cuentaAsociada);
		this.cantidadCredito = credito;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. Se aplica una comisi�n del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { // CCog = 0, WMC = 1
		ValidacionCantidades.confirmaCantidadNegativa(x);
		x += x * 0.05; // Comision por operacion con tarjetas credito
		
		ValidacionCantidades.confirmaCreditoOSaldo(getGastosAcumulados() + x, cantidadCredito, "Credito insuficiente");
		
		Movimiento m = new Movimiento(retiro, LocalDateTime.now(), -x);
		movimientosMensuales.add(m); 
	}

	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException { // CCog = 0, WMC = 1
		ValidacionCantidades.confirmaCantidadNegativa(x);
		ValidacionCantidades.confirmaCreditoOSaldo(getGastosAcumulados() + x, cantidadCredito, "Saldo insuficiente");
		// Revisar con cambios en movimiento
		Movimiento m = new Movimiento(compra + datos, LocalDateTime.now(), -x); // Estandarizador nombre
		movimientosMensuales.add(m);
	}
	
	// Cambiar nombre de variables
    private double getGastosAcumulados() { // CCog = 1, WMC = 1
		double gastos = 0.0;
		for (Movimiento m : movimientosMensuales) { // CCog + 1
			gastos += m.getImporte();
		}
		return gastos;
	}

	/**
	 * Metodo que se invoca automaticamente el dia 1 de cada mes
	 */
	public void liquidar() { // CCog = 2, WMC = 2
		
		double gastos = 0.0; 
		for (int i = 0; i < this.movimientosMensuales.size(); i++) { // CCog + 1
			Movimiento m = movimientosMensuales.get(i); 
			gastos += m.getImporte();
		}

		Movimiento liquidado = new Movimiento(liquidacion, LocalDateTime.now(), gastos);
	
		if (gastos != 0) // CCog + 1, WMC + 1
			cuentaAsociada.addMovimiento(liquidado);
		
		historicoMovimientos.addAll(movimientosMensuales);
		movimientosMensuales.clear();
	}


	public void setCaducidadCredito(LocalDate caducidadCredito) { // CCog = 0, WMC = 1
		this.caducidadCredito = caducidadCredito;
	}

	public LocalDate getCaducidadCredito() { // CCog = 0, WMC = 1
		return caducidadCredito;
	}

	public List<Movimiento> getMovimientosMensuales() { // CCog = 0, WMC = 1
		return movimientosMensuales;
	}
	
	public CuentaAhorro getCuentaAsociada() { // CCog = 0, WMC = 1
		return cuentaAsociada;
	}
	
	public List<Movimiento> getMovimientos() { // CCog = 0, WMC = 1
		return historicoMovimientos;
	}

}