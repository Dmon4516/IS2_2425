package es.unican.is2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta { // CCog = 7, CCogn = 7 / 13 = 0,53, WMC = 19, WMCn = 19 / 13 = 1,46

	private List<Movimiento> Movimientos;

	// Mover a la clase hija
	private LocalDate caducidadDebito;
	private LocalDate caducidadCredito;
	private double limiteDebito;

	private String ingresoEfectivo = "Ingreso en efectivo";
	private String retiradaEfectivo = "Retirada de efectivo";

	public CuentaAhorro(String numCuenta)  throws datoErroneoException { // CCog = 0, WMC = 1
		super(numCuenta);
		Movimientos = new LinkedList<Movimiento>();
		limiteDebito = 1000;
	}

	public void ingresar(double x) throws datoErroneoException { // CCog = 1, WMC = 2
		if (x <= 0) // CCog + 1, WMC + 1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		// Cambiar nombre de funciones y argumentos. Mejor cambiar la clase movimiento
		Movimiento m = new Movimiento(ingresoEfectivo, LocalDateTime.now(), x); // CCog + 1, WMC + 1
		this.Movimientos.add(m); 
	}

	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { // CCog = 2, WMC = 3
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Retirada de efectivo");
		m.setI(-x);
		this.Movimientos.add(m);
	}

	public void ingresar(String concepto, double x) throws datoErroneoException { // CCog = 1, WMC = 2
		if (x <= 0) // CCog + 1, WMC + 1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC(concepto);
		m.setI(x);
		this.Movimientos.add(m);
	}

	private void confirmaSaldo(double x) throws saldoInsuficienteException { 
		if (getSaldo() < x)
			throw new saldoInsuficienteException("Saldo insuficiente");
	}

	private void confirmaCantidadNegativa(String concepto, double x) throws datoErroneoException { // CCog = 1, WMC = 2
		if (x <= 0) // CCog + 1, WMC + 1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
	}

	public void retirar(String concepto, double x) throws saldoInsuficienteException, datoErroneoException { // CCog = 2, WMC = 3
		if (getSaldo() < x) // CCog + 1, WMC + 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		if (x <= 0) // CCog + 1, WMC + 1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		Movimiento m = new Movimiento(); // Cambiar movimiento
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC(concepto);
		m.setI(-x);
		this.Movimientos.add(m);
	}

	public double getSaldo() { // CCog = 1, WMC = 1
		double r = 0.0;
		for (int i = 0; i < this.Movimientos.size(); i++) { // CCog + 1
			Movimiento m = (Movimiento) Movimientos.get(i);
			r += m.getI(); // Cambiar nombre
		}
		return r;
	}

	public void addMovimiento(Movimiento m) { // CCog = 0, WMC = 1
		Movimientos.add(m); // Cambiar nombre
	}

	public List<Movimiento> getMovimientos() { // CCog = 0, WMC = 1
		return Movimientos;
	}

	// Mover a la clase hija
	public LocalDate getCaducidadDebito() { // CCog = 0, WMC = 1
		return caducidadDebito;
	}

	public void setCaducidadDebito(LocalDate caducidadDebito) { // CCog = 0, WMC = 1
		this.caducidadDebito = caducidadDebito;
	}

	public LocalDate getCaducidadCredito() { // CCog = 0, WMC = 1
		return caducidadCredito;
	}

	public void setCaducidadCredito(LocalDate caducidadCredito) { // CCog = 0, WMC = 1
		this.caducidadCredito = caducidadCredito;
	}

	public double getLimiteDebito() { // CCog = 0, WMC = 1
		return limiteDebito;
	}

}