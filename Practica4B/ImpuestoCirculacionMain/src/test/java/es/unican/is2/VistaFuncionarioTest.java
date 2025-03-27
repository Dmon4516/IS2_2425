package es.unican.is2;

import org.junit.*;

import java.util.Arrays;

import org.fest.swing.fixture.FrameFixture;

public class VistaFuncionarioTest {

    private FrameFixture demo;

    @Before
    public void setUp() {
        ContribuyentesDAO contribuyentesDAO = new ContribuyentesDAO();
		VehiculosDAO vehiculosDAO = new VehiculosDAO();
		
		GestionImpuestoCirculacion negocio = new GestionImpuestoCirculacion(contribuyentesDAO, vehiculosDAO);
		
		VistaFuncionario gui = new VistaFuncionario(negocio);
        demo = new FrameFixture(gui);
        gui.setVisible(true);
    }

    @After
    public void tearDown() {
        demo.cleanUp();
    }

    @Test
    public void testContribuyenteNoExistente() {
        demo.textBox("txtDniContribuyente").enterText("11111111B"); // Contribuyente no en la BBDD
        demo.button("btnBuscar").click();
        demo.textBox("txtNombreContribuyente").requireText("DNI Incorrecto");
        demo.textBox("txtTotalContribuyente").requireText("0");
        demo.list("listMatriculasVehiculos").requireNoSelection();
    }

    @Test
    public void testNombresElementos() {
        demo.button("btnBuscar").requireText("Buscar");
    }

    @Test
    public void testContribuyentesExistentes() {
        String arrayDnis[] = {"11111111A", "22222222A", "33333333A", "44444444A"}; //Contribuyentes en la BBDD
        String arrayContribuyentes[] = {"Juan Perez Lopez", "Ana Cuesta Ruiz", "Luis Ruiz Rivas", "Pepe Lopez Abascal"};
        String arrayMatriculas[][] = {{"1111AAA", "1111BBB", "1111CCC"}, {"2222AAA"}, {}, {"4444AAA", "4444BBB"}};

        // Bucle de revision contribuyentes
        for (int i = 0; i < arrayDnis.length; i++) {
            String vehiculos[] = arrayMatriculas[i];
            demo.textBox("txtDniContribuyente").enterText(arrayDnis[i]);
            demo.button("btnBuscar").click();
            demo.textBox("txtNombreContribuyente").requireText(arrayContribuyentes[i]);
            if (vehiculos.length > 0) {
                demo.list("listMatriculasVehiculos").requireSelectedItems(Arrays.toString(vehiculos));
            } else {
                demo.list("listMatriculasVehiculos").requireNoSelection();
            }
            demo.textBox("txtDniContribuyente").deleteText();
        }
    }

}