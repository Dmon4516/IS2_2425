package es.unican.is2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class CuentaAhorroTest {
	private CuentaAhorro sut;
	private static Movimiento m1, m2, m3;

	
	
	@BeforeAll
	public static void inicializaAuxiliares() {

		m1 = new Movimiento("Concepto1", LocalDateTime.now(), 100);
		m2 = new Movimiento("Concepto2", LocalDateTime.now(), 200);
		m3 = new Movimiento("Concepto3", LocalDateTime.now(), 1500);
	}

	@BeforeEach
	public void inicializa() {
		sut = new CuentaAhorro("794311");
	}

	@Test
	public void testConstructor() {
		// Se elimina el getLimiteCredito ya que se ha movido a la clase debito 
		assertEquals(sut.getMovimientos().size(), 0);
		assertEquals(sut.getNumCuenta(), "794311");

		// Se eliminan getCaducidad ya que se han movido a las clases debito y credito	
	}
	
	// Se eliminan los test de getCaducidadCredito y getCaducidadDebito ya que se han movido a las clases debito y credito
	
	@Test
	public void testCalculaSaldoYAddMovimiento() {

		assertTrue(sut.calculaSaldo()==0);	

		sut.addMovimiento(m1);
		assertTrue(sut.calculaSaldo() == 100);
		assertTrue(sut.getMovimientos().size()==1);
		
		sut.addMovimiento(m2);
		sut.addMovimiento(m3);

		assertTrue(sut.calculaSaldo()==1800);
		assertTrue(sut.getMovimientos().size()==3);
	}
	
	@Test
	public void testRetirarSinConcepto() {
		
		try {
			sut.retirar(-10);
			fail("Debe lanzar DatoErroneoException");
		} catch (datoErroneoException e) {
		} catch (saldoInsuficienteException e) {
			fail("Debe lanzar DatoErroneoException");
		}
		
		sut.addMovimiento(m1);
		
		try {
			sut.retirar(50);
			assertTrue(sut.calculaSaldo()==50);
			assertTrue(sut.getMovimientos().size()==2);
			assertEquals(sut.getMovimientos().get(1).getConcepto(), "Retirada de efectivo");
		} catch (datoErroneoException e) {
			fail("No debe lanzar DatoErroneoException");
		} catch (saldoInsuficienteException e) {
			fail("No debe lanzar SaldoInsuficienteException");
		}
		
		
		try {
			sut.retirar(100);
			fail("Debe lanzar SaldoInsuficienteException");
		} catch (datoErroneoException e) {
			fail("Debe lanzar SaldoInsuficienteException");
		} catch (saldoInsuficienteException e) { }
	
	}
	
	@Test
	public void testIngresarSinConcepto () {
	
		try {
			sut.ingresar(-1);
			fail("Debe lanzar DatoErroneoException");
		} catch (datoErroneoException e) {
		}

		try {
			sut.ingresar(0.01);
			assertTrue(sut.calculaSaldo()==0.01);
			assertTrue(sut.getMovimientos().size()==1);
			assertEquals(sut.getMovimientos().get(0).getConcepto(),"Ingreso en efectivo");
			
			sut.ingresar(100);
			assertTrue(sut.calculaSaldo()==100.01);
			assertTrue(sut.getMovimientos().size()==2);
			
		} catch (datoErroneoException e) {
			fail("No debe lanzar la excepci�n");
		}
		
	}
	
	
	@Test
	public void testIngresarConConcepto () {
	
		// Test ingresar negativo
		try {
			sut.ingresar("Ingreso", -1);
			fail("Debe lanzar DatoErroneoException");
		} catch (datoErroneoException e) {
		}

		// Test ingresar el limite
		try {
			sut.ingresar("Ingreso1", 0.01);
			assertTrue(sut.calculaSaldo()==0.01);
			assertTrue(sut.getMovimientos().size()==1);
			assertEquals(sut.getMovimientos().get(0).getConcepto(), "Ingreso1");
			
			sut.ingresar("Ingreso2", 100);
			assertTrue(sut.calculaSaldo()==100.01);
			assertTrue(sut.getMovimientos().size()==2);
			assertEquals(sut.getMovimientos().get(1).getConcepto(), "Ingreso2");
			
		} catch (datoErroneoException e) {
			fail("No debe lanzar la excepci�n");
		}
		
	}
	
	@Test
	public void testRetirarConConcepto() {
		
		try {
			sut.retirar("Retirada", -10);
			fail("Debe lanzar DatoErroneoException");
		} catch (datoErroneoException e) {
		} catch (saldoInsuficienteException e) {
			fail("Deber�a lanzar DatoErroneoException");
		}
		
		sut.addMovimiento(m1);
		
		try {
			sut.retirar("Retirada1", 50);
			assertTrue(sut.calculaSaldo()==50);
			assertTrue(sut.getMovimientos().size()==2);
			assertEquals(sut.getMovimientos().get(1).getConcepto(),"Retirada1");
		} catch (datoErroneoException e) {
			fail("No debe lanzar DatoErroneoException");
		} catch (saldoInsuficienteException e) {
			fail("No debe lanzar SaldoInsuficienteException");
		}
		
		
		try {
			sut.retirar("Retirada2", 100);
			fail("Debe lanzar SaldoInsuficienteException");
		} catch (datoErroneoException e) {
			fail("Debe lanzar SaldoInsuficienteException");
		} catch (saldoInsuficienteException e) {
			
		}
	
	}

	
}
