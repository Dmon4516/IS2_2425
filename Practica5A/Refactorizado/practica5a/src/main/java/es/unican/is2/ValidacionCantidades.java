package es.unican.is2;

public class ValidacionCantidades {
    
    public static void confirmaCantidadNegativa(double x) throws datoErroneoException { // CCog = 1, WMC = 2
        if (x <= 0) { // CCog + 1, WMC + 1
            throw new datoErroneoException("No se puede ingresar una cantidad negativa");
        }
    }
    
    public static void confirmaCreditoOSaldo(double x, double credito, String mensaje) throws saldoInsuficienteException { // CCog = 1, WMC = 2
        if (x > credito) { // CCog + 1, WMC + 1
            throw new saldoInsuficienteException(mensaje);
        }
    }

}