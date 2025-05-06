package es.unican.is2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CuentaValoresTest {
	
	private CuentaValores sut;
	
	@BeforeEach
	void inicializa() {
		sut = new CuentaValores("794311");
	}
	
	@Test
	void testConstructor() {
		assertTrue(sut.getNumCuenta().equals("794311"));
		assertTrue(sut.getValores().size()==0);
	}
	
	@Test
	void testAnhadeValor() {
		// CASOS VALIDOS
		Valor v = new Valor("Telepizza", 25, 1.05);
		assertTrue(sut.anhadeValor(v));
		assertTrue(sut.getValores().size()==1);
		assertEquals(sut.getValores().get(0), v);
		
		v = new Valor("BancoSantander", 100, 200);
		assertTrue(sut.anhadeValor(v));
		assertTrue(sut.getValores().size()==2);
		assertEquals(sut.getValores().get(1), v);
		
		// CASOS NO VALIDOS
		assertFalse(sut.anhadeValor(new Valor("Telepizza", 10, 2.5)));
		
	}

	@Test
	void testCalcularSaldo() {
		// CASO VACIO
		assertTrue(sut.calculaSaldo() == 0);

		// CASO VALIDO
		Valor v = new Valor("Telepizza", 25, 1.05);
		sut.anhadeValor(v);
		assertTrue(sut.calculaSaldo() == 26.25);
		
	
	}
}
