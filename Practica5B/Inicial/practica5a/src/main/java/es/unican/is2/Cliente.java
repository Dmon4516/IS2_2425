package es.unican.is2;

import java.util.LinkedList;
import java.util.List;

public class Cliente { // CCog = 9, CCogn = 9 / 11 = 0,81, WMC = 15, WMCn = 15 / 11 = 1,36
	
	public String nombre;
	public String calle;
	public String zip;
	public String localidad;
	public String telefono;
	public String dni;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();
    
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
		Cuentas.add(c); // Cambiar variables
	}
	
	public void anhadeTarjeta(Tarjeta t) { // CCog = 2, WMC = 3
		tarjetas.add(t); // Cambiar variables

		// Cambiar dentro de la clases abtractas de la tarjeta
		if (t instanceof Debito) { // CCog + 1, WMC + 1
			Debito td = (Debito)t;
			td.getCuentaAsociada().setCaducidadDebito(td.getCaducidadDebito());
		} else { // CCog + 1, WMC + 1
			Credito tc = (Credito) t;
			tc.getCuentaAsociada().setCaducidadCredito(tc.getCaducidadCredito());
		}
	}
	
	// Revisar
	public double getSaldoTotal() { // CCog = 7, WMC = 3
		double total = 0.0;
		// Añadir metodo getTotal() en Cuenta, y mover todo esto ahi a partir del for
		for (Cuenta c: Cuentas) {  // CCog + 1
			if (c instanceof CuentaAhorro) { // CCog + 2 (nesting=1), WMC + 1
				total += ((CuentaAhorro) c).getSaldo();
			} else if (c instanceof CuentaValores)  { // CCog + 1, WMC + 1
				for (Valor v: ((CuentaValores) c).getValores()) { // CCog + 3 (nesting=2)
					// Agregar metodo en clase Valor
					total += v.getCotizacion()*v.getNumValores(); 
				}
			}
		}
		return total;
	}
	
	// Pensar si quitar esto para bajar el WMC (no lo usa ninguna otra clase)
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