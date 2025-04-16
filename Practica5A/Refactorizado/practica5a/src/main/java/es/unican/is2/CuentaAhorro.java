package es.unican.is2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta { // CCog = 3, CCogn = 3 / 10 = 0,3, WMC = 12, WMCn = 12 / 10 = 1,2

	private List<Movimiento> Movimientos;

	private String ingresoEfectivo = "Ingreso en efectivo";
	private String retiradaEfectivo = "Retirada de efectivo";

	public CuentaAhorro(String numCuenta)  throws datoErroneoException { // CCog = 0, WMC = 1
		super(numCuenta);
		Movimientos = new LinkedList<Movimiento>();
	}

	public void ingresar(double x) throws datoErroneoException { // CCog = 0, WMC = 1
		confirmaCantidadNegativa(x); 
		// Cambiar nombre de funciones y argumentos. Mejor cambiar la clase movimiento
		Movimiento m = new Movimiento(ingresoEfectivo, LocalDateTime.now(), x);
		this.Movimientos.add(m); 
	}

	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { // CCog = 0, WMC = 1
		confirmaSaldo(x);
		confirmaCantidadNegativa(x);
		Movimiento m = new Movimiento(retiradaEfectivo, LocalDateTime.now(), -x); // Cambiar nombre de funciones y argumentos. Mejor cambiar la clase movimiento

		this.Movimientos.add(m);
	}

	public void ingresar(String concepto, double x) throws datoErroneoException { // CCog = 0, WMC = 1
		confirmaCantidadNegativa(x);
		Movimiento m = new Movimiento(concepto, LocalDateTime.now(), x); // Cambiar nombre de funciones y argumentos. Mejor cambiar la clase movimiento
		this.Movimientos.add(m);
	}

	public void retirar(String concepto, double x) throws saldoInsuficienteException, datoErroneoException { // CCog = 0, WMC = 1
		confirmaSaldo(x);
		confirmaCantidadNegativa(x);
		
		Movimiento m = new Movimiento(concepto, LocalDateTime.now(), -X); // Cambiar movimiento
		this.Movimientos.add(m);
	}

	@Override
	public double calculaSaldo() { // CCog = 1, WMC = 1
		double saldo = 0.0;
		for (int i = 0; i < this.Movimientos.size(); i++) { // CCog + 1
			Movimiento m = (Movimiento) Movimientos.get(i);
			saldo += m.getImporte(); // Cambiar nombre
		}
		return saldo;
	}

	// Sacar fuera
	private void confirmaSaldo(double x) throws saldoInsuficienteException { // CCog = 1, WMC = 2
		if (calculaSaldo() < x) // CCog + 1, WMC + 1
			throw new saldoInsuficienteException("Saldo insuficiente");
	}

	private void confirmaCantidadNegativa(double x) throws datoErroneoException { // CCog = 1, WMC = 2
		if (x <= 0)
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
	}

	// TODO


	public void addMovimiento(Movimiento m) { // CCog = 0, WMC = 1
		Movimientos.add(m); // Cambiar nombre
	}

	public List<Movimiento> getMovimientos() { // CCog = 0, WMC = 1
		return Movimientos;
	}

}