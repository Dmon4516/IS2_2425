package es.unican.is2;

import java.time.LocalDate;

/**
 * Clase que representa un vehiculo de tipo motocicleta
 */
public class Motocicleta extends Vehiculo {

	private int cilindrada;

	public Motocicleta(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor, int cilindrada) {
		super(id, matricula, fechaMatriculacion, motor);
		this.cilindrada = cilindrada;
	}

	/**
	 * Retorna la cilindrada en CC de la motocicleta.
	 */
	public int getCilindrada() {
		return cilindrada;
	}

	@Override
	public double precioImpuesto() {
		double bonificacion = bonificacion();
		if (cilindrada <= 125) {
			return 8 * (1 - bonificacion);
		} else if (cilindrada <= 250) {
			return 15 * (1 - bonificacion);
		} else if (cilindrada <= 500) {
			return 30 * (1 - bonificacion);
		} else if (cilindrada <= 1000) {
			return 60 * (1 - bonificacion);
		} else {
			return 120;
		}
	}

}
