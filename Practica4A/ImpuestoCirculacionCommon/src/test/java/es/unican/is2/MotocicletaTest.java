package es.unican.is2;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class MotocicletaTest {

    private Motocicleta motoBase;
    private double Delta = 0.01;

    @Before
    public void configurar() {
        // Configura una motocicleta base para pruebas comunes
        motoBase = new Motocicleta(1, "1234MOT", LocalDate.now().minusYears(1), 
                                 TipoMotor.GASOLINA, 250);
    }

    // Pruebas para cilindrada < 125cc
    @Test
    public void testImpuestoCilindradaMinima() {
        Motocicleta m = new Motocicleta(1, "0001AAA", LocalDate.now(), 
                                      TipoMotor.GASOLINA, 0);
        assertEquals(8.0, m.precioImpuesto(), Delta);
    }

    @Test
    public void testImpuestoCilindrada124() {
        // Límite superior del rango mínimo
        Motocicleta m = new Motocicleta(1, "0124BBB", LocalDate.now(), 
                                      TipoMotor.GASOLINA, 124);
        assertEquals(8.0, m.precioImpuesto(), Delta);
    }

    // Pruebas para 125cc <= cilindrada < 250cc
    @Test
    public void testImpuestoCilindrada125() {
        // Límite inferior del rango intermedio
        Motocicleta m = new Motocicleta(1, "0125CCC", LocalDate.now(), 
                                      TipoMotor.GASOLINA, 125);
        assertEquals(8.0, m.precioImpuesto(), Delta); 
    }

    @Test
    public void testImpuestoCilindrada249() {
        // Límite superior del rango intermedio
        Motocicleta m = new Motocicleta(1, "0249DDD", LocalDate.now(), 
                                      TipoMotor.GASOLINA, 249);
        assertEquals(15.0, m.precioImpuesto(), Delta);
    }

    // Pruebas para 250cc <= cilindrada < 500cc
    @Test
    public void testImpuestoCilindrada250() {
        // Límite inferior del rango medio-alto
        Motocicleta m = new Motocicleta(1, "0250EEE", LocalDate.now(), 
                                      TipoMotor.GASOLINA, 250);
        assertEquals(15.0, m.precioImpuesto(), Delta); //10
    }

    // Pruebas para vehículos eléctricos (25% descuento)
    @Test
    public void testImpuestoElectrico125() {
        Motocicleta m = new Motocicleta(1, "E125KKK", LocalDate.now(), 
                                      TipoMotor.ELECTRICO, 125);
        assertEquals(2.0, m.precioImpuesto(), Delta); // 8 * 0.75
    }

    // Pruebas para vehículos antiguos (50% descuento)
    @Test
    public void testImpuestoAntiguo1000() {
        Motocicleta m = new Motocicleta(1, "A1000NNN", LocalDate.now().minusYears(26), 
                                      TipoMotor.GASOLINA, 1001);
        assertEquals(120.0, m.precioImpuesto(), Delta); // 120 
    }

    // Prueba combinada (eléctrico + antiguo)
    @Test
    public void testImpuestoElectricoYAntiguo500() {
        Motocicleta m = new Motocicleta(1, "EA500OOO", LocalDate.now().minusYears(26), 
                                      TipoMotor.ELECTRICO, 500);
        assertEquals(0.0, m.precioImpuesto(), Delta); // 0
    }
}
