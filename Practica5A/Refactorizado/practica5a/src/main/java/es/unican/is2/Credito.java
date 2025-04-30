package es.unican.is2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class Credito extends Tarjeta { // CCog = 5, CCogn = 5 / 13 = 0,38, WMC = 16, WMCn = 16 / 13 = 1,23
	
	private double credito;
	private List<Movimiento> movimientosMensuales;
	private List<Movimiento> historicoMovimientos; // Estandarizador nombre

	private String retiro = "Retirada en cajero";
	private String compra = "Compra a credito en: ";
	private String liquidacion = "Liquidacion de operaciones tarjeta credito";

	private LocalDate caducidadCredito;
	private LocalDate fechaCaducidad; 

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
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { // CCog = 0, WMC = 1
		
		ValidacionCantidades.confirmaCantidadNegativa(x);
		x += x * 0.05; // Comision por operacion con tarjetas credito
		
		ValidacionCantidades.confirmaCreditoOSaldo(getGastosAcumulados() + x, credito, "Credito insuficiente");

		Movimiento m = new Movimiento(retiro, LocalDateTime.now(), -x); 
		movimientosMensuales.add(m); 
	}

	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException { // CCog = 0, WMC = 1
		ValidacionCantidades.confirmaCantidadNegativa(x);
		ValidacionCantidades.confirmaCreditoOSaldo(getGastosAcumulados() + x, credito, "Saldo insuficiente");
		// Revisar con cambios en movimiento
		Movimiento m = new Movimiento(compra + datos, LocalDateTime.now(), -x); // Estandarizador nombre
		movimientosMensuales.add(m);
	}
	
	// Cambiar nombre de variables
    private double getGastosAcumulados() { // CCog = 1, WMC = 1
		double gastos = 0.0;
		for (Movimiento m : movimientosMensuales) {
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
			Movimiento m = (Movimiento) movimientosMensuales.get(i); 
			gastos += m.getImporte();
		}

		Movimiento liquidado = new Movimiento(liquidacion, LocalDateTime.now(), gastos);
	
		if (gastos != 0) // CCog + 1, WMC + 1
			cuentaAsociada.addMovimiento(liquidado);
		
		historicoMovimientos.addAll(movimientosMensuales);
		movimientosMensuales.clear();
	}

	// TODO: considerar repartir funcionalidades en diferentes clases (creando clases nuevas) y mover TODOS los throws a otra clase

	@Override
	public void actualizaCaducidadCuenta() { // CCog = 0, WMC = 1
		this.fechaCaducidad = caducidadCredito;
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
	
	// Mover al padre
	public CuentaAhorro getCuentaAsociada() { // CCog = 0, WMC = 1
		return cuentaAsociada;
	}
	
	public List<Movimiento> getMovimientos() { // CCog = 0, WMC = 1
		return historicoMovimientos;
	}

}