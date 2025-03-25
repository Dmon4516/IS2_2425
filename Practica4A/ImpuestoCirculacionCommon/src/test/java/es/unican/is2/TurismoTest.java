package es.unican.is2;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.Before;

public class TurismoTest {

    public Turismo turismo;

    @Before
    public void setUp() {
        turismo = new Turismo(1, "1234ABC", LocalDate.now().minusYears(1), TipoMotor.GASOLINA, 100);
    }

    // Casos de prueba para precioImpuesto()
    @Test
    public void testCalculaImpuestoPotenciaMenor8() {
        Turismo t = new Turismo(1, "1111AAA", LocalDate.now(), TipoMotor.GASOLINA, 7);
        assertEquals(25, t.precioImpuesto(), 0.01);
    }

    @Test
    public void testCalculaImpuestoPotenciaEntre8y12() {
        Turismo t = new Turismo(1, "1111BBB", LocalDate.now(), TipoMotor.GASOLINA, 10);
        assertEquals(67.0, t.precioImpuesto(), 0.01);
    }

    @Test
    public void testCalculaImpuestoElectrico() {
        Turismo t = new Turismo(1, "1111CCC", LocalDate.now(), TipoMotor.ELECTRICO, 10);
        assertEquals(16.75, t.precioImpuesto(), 0.01); // 75% de 67
    }

    @Test
    public void testCalculaImpuestoAntiguedad25Anios() {
        Turismo t = new Turismo(1, "1111DDD", LocalDate.now().minusYears(26), TipoMotor.GASOLINA, 10);
        assertEquals(0, t.precioImpuesto(), 0.01); // 100% de 67
    }
}

