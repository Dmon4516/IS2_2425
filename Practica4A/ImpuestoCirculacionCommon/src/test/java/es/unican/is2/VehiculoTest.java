package test;

import java.beans.Transient;
import java.time.LocalDate;

public class VehiculoTest {
    private Vehiculo veh1; //Electrico de 26 anhos, Bonificacion 1
    private Vehiculo veh2; //Electrico de 25 anhos, Bonificacion 0.75
    private Vehiculo veh3; //Hibrido de menos de 1 anho, Bonificacion 0.75
    private Vehiculo veh4; //Hibrido de 4 anhos, Bonificacion 0
    private Vehiculo veh5; //Gas de 1 anho, Bonificacion 0
    private Vehiculo veh6; //Gas de menos de 1 anho, Bonificacion 0.5

    @BeforeEach
    public void setUp() throws Exception {
        veh1 = new Vehiculo(1, "3547NXB", LocalDate.now().minusYears(26), TipoMotor.ELECTRICO);
        veh2 = new Vehiculo(2, "3548NXB", LocalDate.now().minusYears(25), TipoMotor.ELECTRICO);
        veh3 = new Vehiculo(3, "3549NXB", LocalDate.now().minusMonths(6), TipoMotor.HIBRIDO);
        veh4 = new Vehiculo(4, "3550NXB", LocalDate.now().minusYears(3), TipoMotor.HIBRIDO);
        veh5 = new Vehiculo(5, "3551NXB", LocalDate.now().minusYears(1), TipoMotor.GAS);
        veh6 = new Vehiculo(6, "3552NXB", LocalDate.now().minusMonths(6), TipoMotor.GAS);
    }

    @Test
    public void testBonificacion() {
        assertEquals(1, veh1.bonificacion());
        assertEquals(0.75, veh2.bonificacion());
        assertEquals(0.75, veh3.bonificacion());
        assertEquals(0, veh4.bonificacion());
        assertEquals(0, veh5.bonificacion());
        assertEquals(0.5, veh6.bonificacion());
    }

}
