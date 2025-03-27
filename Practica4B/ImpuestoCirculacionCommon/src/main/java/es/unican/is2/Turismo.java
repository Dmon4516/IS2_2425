package es.unican.is2;

import java.time.LocalDate;

/**
 * Clase que representa un vehiculo de tipo turismo.
 */
public class Turismo extends Vehiculo {

	private double potencia;
	
	public Turismo(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor, double potencia) {
		super(id, matricula, fechaMatriculacion, motor);
		this.potencia = potencia;
	}

	/**
	 * Retorna la potencia en caballos fiscales del vehiculo.
	 */
	public double getPotencia() {
		return potencia;
	}

	@Override
	public double precioImpuesto() {
		double bonificacion = bonificacion();
		if (potencia < 8) {
			return 25 * (1 - bonificacion);
		} else if (potencia < 12) {
			return 67 - ( 67 *  bonificacion);
		} else if (potencia < 16) {
			return 143 - (143 * bonificacion);
		} else if (potencia < 20) {
			return 178 - ( 178 * bonificacion);
		} else {
			return 223 - ( 178 * bonificacion);
		}
	}
}
