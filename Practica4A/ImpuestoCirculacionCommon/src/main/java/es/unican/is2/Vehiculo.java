package es.unican.is2;

import java.time.LocalDate;
import java.time.Period;

/**
 * Clase abstracta que representa un vehiculo. 
 * Cada vehiculo tiene una matricula unica.
 */
public abstract class Vehiculo {

	// Clave primaria autogenerada
	private long id;

	private String matricula;
	private LocalDate fechaMatriculacion;
	private TipoMotor motor;

	public Vehiculo(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor) {
		this.id = id;
		this.matricula = matricula;
		this.fechaMatriculacion = fechaMatriculacion;
		this.motor = motor;
	}

	/**
	 * Retorna la matricula del vehiculo.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Retorna la fecha de primera matriculacion del vehiculo.
	 */
	public LocalDate getFechaMatriculacion() {
		return fechaMatriculacion;
	}

	/**
	 * Retorna el tipo de motor del vehiculo.
	 */
	public TipoMotor getMotor() {
		return motor;
	}

	/**
	 * Retorna el identificador del vehiculo.
	 */
	public long getId() {
		return id;
	}

	public abstract double precioImpuesto();

	public double bonificacion() {
		Period anhosAntiguedad = Period.between(fechaMatriculacion, LocalDate.now());
		if (anhosAntiguedad.getYears() >= 25) { // Vehiculos con mas de 25 anhos de antiguedad
			return 1;
		} else if (motor == TipoMotor.ELECTRICO || (anhosAntiguedad.getYears() <= 4 && motor == TipoMotor.HIBRIDO)) { // Vehiculos Electricos O Vehiculos Hibridos los primeros 4 anhos
			return 0.75;
		} else if (anhosAntiguedad.getYears() <= 1 && motor == TipoMotor.GAS) { // Vehiculos de Gas el primer anho
			return 0.5;
		} else { // Ninguna bonificacion
			return 0;
		}
	}
}
