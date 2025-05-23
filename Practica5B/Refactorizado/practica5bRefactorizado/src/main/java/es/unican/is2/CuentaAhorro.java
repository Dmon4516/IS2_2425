package es.unican.is2;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta { // CCog = 1, CCogn = 1 / 8 = 0,125, WMC = 8, WMCn = 8 / 8 = 1

	private List<Movimiento> movimientos;

	private String ingresoEfectivo = "Ingreso en efectivo";
	private String retiradaEfectivo = "Retirada de efectivo";

	public CuentaAhorro(String numCuenta)  throws DatoErroneoException { // CCog = 0, WMC = 1
		super(numCuenta);
		movimientos = new LinkedList<>();
	}

	public void ingresar(double x) throws DatoErroneoException { // CCog = 0, WMC = 1
		ValidacionCantidades.confirmaCantidadNegativa(x); 
		// Cambiar nombre de funciones y argumentos. Mejor cambiar la clase movimiento
		Movimiento m = new Movimiento(ingresoEfectivo, LocalDateTime.now(), x);
		this.movimientos.add(m); 
	}

	public void retirar(double x) throws SaldoInsuficienteException, DatoErroneoException { // CCog = 0, WMC = 1
		ValidacionCantidades.confirmaCreditoOSaldo(x ,calculaSaldo(), "Saldo insuficiente");
		ValidacionCantidades.confirmaCantidadNegativa(x);
		Movimiento m = new Movimiento(retiradaEfectivo, LocalDateTime.now(), -x); // Cambiar nombre de funciones y argumentos. Mejor cambiar la clase movimiento

		this.movimientos.add(m);
	}

	public void ingresar(String concepto, double x) throws DatoErroneoException { // CCog = 0, WMC = 1
		ValidacionCantidades.confirmaCantidadNegativa(x);
		Movimiento m = new Movimiento(concepto, LocalDateTime.now(), x); // Cambiar nombre de funciones y argumentos. Mejor cambiar la clase movimiento
		this.movimientos.add(m);
	}

	public void retirar(String concepto, double x) throws SaldoInsuficienteException, DatoErroneoException { // CCog = 0, WMC = 1
		ValidacionCantidades.confirmaCreditoOSaldo(x, calculaSaldo(), "Saldo insuficiente");
		ValidacionCantidades.confirmaCantidadNegativa(x);
		
		Movimiento m = new Movimiento(concepto, LocalDateTime.now(), -x); // Cambiar movimiento
		this.movimientos.add(m);
	}

	@Override
	public double calculaSaldo() { // CCog = 1, WMC = 1
		double saldo = 0.0;
		for (int i = 0; i < this.movimientos.size(); i++) { // CCog + 1
			Movimiento m = movimientos.get(i);
			saldo += m.getImporte(); // Cambiar nombre
		}
		return saldo;
	}

	public void addMovimiento(Movimiento m) { // CCog = 0, WMC = 1
		movimientos.add(m); // Cambiar nombre
	}

	public List<Movimiento> getMovimientos() { // CCog = 0, WMC = 1
		return movimientos;
	}

}