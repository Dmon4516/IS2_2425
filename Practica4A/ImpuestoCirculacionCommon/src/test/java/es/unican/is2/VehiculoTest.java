package es.unican.is2;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;

public class VehiculoTest {
    private Vehiculo veh1; //Electrico de 26 anhos, Bonificacion 1
    private Vehiculo veh2; //Electrico de 25 anhos, Bonificacion 0.75
    private Vehiculo veh3; //Hibrido de menos de 1 anho, Bonificacion 0.75
    private Vehiculo veh4; //Hibrido de 4 anhos, Bonificacion 0
    private Vehiculo veh5; //Gas de 1 anho, Bonificacion 0
    private Vehiculo veh6; //Gas de menos de 1 anho, Bonificacion 0.5

    @Before
    public void setUp() throws Exception {
        veh1 = new Turismo(1, "3547NXB", LocalDate.now().minusYears(26), TipoMotor.ELECTRICO, 0.0);
        veh2 = new Turismo(2, "3548NXB", LocalDate.now().minusYears(25), TipoMotor.ELECTRICO, 0.0);
        veh3 = new Turismo(3, "3549NXB", LocalDate.now().minusMonths(6), TipoMotor.HIBRIDO, 0.0);
        veh4 = new Turismo(4, "3550NXB", LocalDate.now().minusYears(4), TipoMotor.HIBRIDO, 0.0);
        veh5 = new Turismo(5, "3551NXB", LocalDate.now().minusYears(1), TipoMotor.GAS, 0.0);
        veh6 = new Turismo(6, "3552NXB", LocalDate.now().minusMonths(6), TipoMotor.GAS, 0.0);
    }

    @Test
    public void testBonificacion() {
        assertEquals(1.0, veh1.bonificacion(), 0);
        assertEquals(0.75, veh2.bonificacion(), 0);
        assertEquals(0.75, veh3.bonificacion(), 0);
        assertEquals(0.0, veh4.bonificacion(), 0);
        assertEquals(0.0, veh5.bonificacion(), 0);
        assertEquals(0.5, veh6.bonificacion(), 0);
    }

}
