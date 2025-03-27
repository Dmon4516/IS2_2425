package es.unican.is2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import es.unican.is2.adt.*;

public class ListaOrdenadaTest {

    private ListaOrdenada<Integer> lista;

    @BeforeEach
    void setUp() {
        lista = new ListaOrdenada<Integer>();
    }

    @Test
    void testAdd() {
        // Prueba agregar elemento nulo
        AssertionError error = assertThrows(AssertionError.class, () -> lista.add(null));
        assertEquals("Elemento nulo", error.getMessage());

        // Prueba agregar sin elementos
        lista.add(1);
        assertEquals(1, lista.get(0));
        
        // Prueba agregar con elementos
        lista.add(2);
        assertEquals(2, lista.get(1));
    }

    @Test
    void testClear() {
        // Prueba clear sin elementos
        lista.clear();
        assertEquals(0, lista.size());

        // Prueba clear con elementos
        for (int i = 0; i < 5; i++) {
            lista.add(i);
        }
        lista.clear();
        assertEquals(0, lista.size());
    }

    @Test
    void testGet() {
        for (int i = 0; i < 5; i++) {
            lista.add(i);
        }
        
        // Prueba get con indice negativo
        AssertionError error = assertThrows(AssertionError.class, () -> lista.get(-1));
        assertEquals("Indice negativo", error.getMessage());

        // Prueba get con indice mayor que el tamaño
        error = assertThrows(AssertionError.class, () -> lista.get(5));
        assertEquals("Indice fuera de rango", error.getMessage());

        // Prueba get con indice correcto
        assertEquals(0, lista.get(0));
        assertEquals(4, lista.get(4));
    }

    @Test
    void testRemove() {
        for (int i = 0; i < 5; i++) {
            lista.add(i);
        }
        
        // Prueba remove con indice negativo
        AssertionError error = assertThrows(AssertionError.class, () -> lista.remove(-1));
        assertEquals("Indice negativo", error.getMessage());

        // Prueba remove con indice mayor que el tamaño
        error = assertThrows(AssertionError.class, () -> lista.remove(5));
        assertEquals("Indice fuera de rango", error.getMessage());

        // Prueba remove con indice correcto
        assertEquals(0, lista.remove(0));
        assertEquals(4, lista.remove(3));
    }

    @Test
    void testSize() {
        // Prueba size sin elementos
        assertEquals(0, lista.size());

        // Prueba size con elementos
        for (int i = 0; i < 5; i++) {
            lista.add(i);
        }
        assertEquals(5, lista.size());
    }
}
