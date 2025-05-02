package es.unican.is2;

import java.util.LinkedList;
import java.util.List;

public class Cliente { // CCog = 1, CCogn = 1 / 11 = 0,09, WMC = 11, WMCn = 11 / 11 = 1
	
	// Cambio a private
	private String nombre;
	private String calle;
	private String zip;
	private String localidad;
	private String telefono;
	private String dni;
	
    private List<Cuenta> cuentas = new LinkedList<Cuenta>();
    private List<Tarjeta> tarjetas = new LinkedList<Tarjeta>(); // Estandarizador nombre

 	public Cliente(String titular, String calle, String zip, String localidad, 
 			String telefono, String dni) {  // CCog = 0, WMC = 1
		this.nombre = titular;
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}
	
	public void cambiaDireccion(String calle, String zip, String localidad) { // CCog = 0, WMC = 1
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}
	
	public void anhadeCuenta(Cuenta c) { // CCog = 0, WMC = 1
		cuentas.add(c);
	}
	
	public void anhadeTarjeta(Tarjeta t) { // CCog = 0, WMC = 1
		tarjetas.add(t);
		t.actualizaCaducidadCuenta();
	}
	
	// Revisar
	public double getSaldoTotal() { // CCog = 1, WMC = 1
		double total = 0.0;
		for (Cuenta c: cuentas) { // CCog + 1
			total += c.calculaSaldo();
		}
		return total;
	}
	
	public String getNombre() { // CCog = 0, WMC = 1
		return nombre;
	}

	public String getCalle() { // CCog = 0, WMC = 1
		return calle;
	}

	public String getZip() { // CCog = 0, WMC = 1
		return zip;
	}

	public String getLocalidad() { // CCog = 0, WMC = 1
		return localidad;
	}

	public String getTelefono() { // CCog = 0, WMC = 1
		return telefono;
	}

	public String getDni() { // CCog = 0, WMC = 1
		return dni;
	}
	
}