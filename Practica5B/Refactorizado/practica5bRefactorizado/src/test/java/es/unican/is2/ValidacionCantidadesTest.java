package es.unican.is2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ValidacionCantidadesTest {
    
    @Test
    void testConfirmaCantidadNegativa() {
        try {
            ValidacionCantidades.confirmaCantidadNegativa(-1);
            assertTrue(false);
        } catch (DatoErroneoException e) {
            assertTrue(true);
        }
        
        try {
            ValidacionCantidades.confirmaCantidadNegativa(0);
            assertTrue(false);
        } catch (DatoErroneoException e) {
            assertTrue(true);
        }
        
        try {
            ValidacionCantidades.confirmaCantidadNegativa(1);
            assertTrue(true);
        } catch (DatoErroneoException e) {
            assertTrue(false);
        }
        
    }

    @Test
    void testConfirmaCreditoOSaldo() {
        try {
            ValidacionCantidades.confirmaCreditoOSaldo(1, 0, "No hay saldo suficiente");
            assertTrue(false);
        } catch (SaldoInsuficienteException e) {
            assertTrue(true);
        }
        
        try {
            ValidacionCantidades.confirmaCreditoOSaldo(1, 1, "No hay saldo suficiente");
            assertTrue(true);
        } catch (SaldoInsuficienteException e) {
            assertTrue(false);
        }
        
        try {
            ValidacionCantidades.confirmaCreditoOSaldo(1, 2, "No hay saldo suficiente");
            assertTrue(true);
        } catch (SaldoInsuficienteException e) {
            assertTrue(false);
        }
    }

}