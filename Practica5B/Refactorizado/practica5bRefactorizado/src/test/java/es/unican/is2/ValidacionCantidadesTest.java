package es.unican.is2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ValidacionCantidadesTest {
    
    @Test
    public void testConfirmaCantidadNegativa() {
        try {
            ValidacionCantidades.confirmaCantidadNegativa(-1);
            assertTrue(false);
        } catch (datoErroneoException e) {
            assertTrue(true);
        }
        
        try {
            ValidacionCantidades.confirmaCantidadNegativa(0);
            assertTrue(false);
        } catch (datoErroneoException e) {
            assertTrue(true);
        }
        
        try {
            ValidacionCantidades.confirmaCantidadNegativa(1);
            assertTrue(true);
        } catch (datoErroneoException e) {
            assertTrue(false);
        }
        
    }

    @Test
    public void testConfirmaCreditoOSaldo() {
        try {
            ValidacionCantidades.confirmaCreditoOSaldo(1, 0, "No hay saldo suficiente");
            assertTrue(false);
        } catch (saldoInsuficienteException e) {
            assertTrue(true);
        }
        
        try {
            ValidacionCantidades.confirmaCreditoOSaldo(1, 1, "No hay saldo suficiente");
            assertTrue(true);
        } catch (saldoInsuficienteException e) {
            assertTrue(false);
        }
        
        try {
            ValidacionCantidades.confirmaCreditoOSaldo(1, 2, "No hay saldo suficiente");
            assertTrue(true);
        } catch (saldoInsuficienteException e) {
            assertTrue(false);
        }
    }

}