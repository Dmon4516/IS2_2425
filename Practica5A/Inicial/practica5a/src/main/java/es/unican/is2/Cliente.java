package es.unican.is2;

import java.util.LinkedList;
import java.util.List;

public class Cliente { // CCog = 9, CCogn = 9 / 11 = 0,81
	
	public String nombre;
	public String calle;
	public String zip;
	public String localidad;
	public String telefono;
	public String dni;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();
    
    private List<Tarjeta> tarjetas = new LinkedList<Tarjeta>();

 	public Cliente(String titular, String calle, String zip, String localidad, 
 			String telefono, String dni) {  // CCog = 0
		this.nombre = titular;
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}
	
	public void cambiaDireccion(String calle, String zip, String localidad) { // CCog = 0
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}
	
	public void anhadeCuenta(Cuenta c) { // CCog = 0
		Cuentas.add(c);
	}
	
	public void anhadeTarjeta(Tarjeta t) { // CCog = 2
		tarjetas.add(t);
		if (t instanceof Debito) { // CCog + 1
			Debito td = (Debito)t;
			td.getCuentaAsociada().setCaducidadDebito(td.getCaducidadDebito());
		} else { // CCog + 1
			Credito tc = (Credito) t;
			tc.getCuentaAsociada().setCaducidadCredito(tc.getCaducidadCredito());
		}
	}
	
	public double getSaldoTotal() { // CCog = 7
		double total = 0.0;
		for (Cuenta c: Cuentas) {  // CCog + 1
			if (c instanceof CuentaAhorro) { // CCog + 2 (nesting=1)
				total += ((CuentaAhorro) c).getSaldo();
			} else if (c instanceof CuentaValores)  { // CCog + 1
				for (Valor v: ((CuentaValores) c).getValores()) { // CCog + 3 (nesting=2)
					total += v.getCotizacion()*v.getNumValores();
				}
			}
		}
		return total;
	}
	
	public String getNombre() { // CCog = 0
		return nombre;
	}

	public String getCalle() { // CCog = 0
		return calle;
	}

	public String getZip() { // CCog = 0
		return zip;
	}

	public String getLocalidad() { // CCog = 0
		return localidad;
	}

	public String getTelefono() { // CCog = 0
		return telefono;
	}

	public String getDni() { // CCog = 0
		return dni;
	}
	
	
	
}