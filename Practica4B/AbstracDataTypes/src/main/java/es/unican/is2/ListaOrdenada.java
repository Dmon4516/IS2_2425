package es.unican.is2;

import java.util.*;

import es.unican.is2.adt.*;

/**
 * Clase que implementa el ADT ListaOrdenada
 */
public class ListaOrdenada<E extends Comparable<E>> implements IListaOrdenada<E> {

	private List<E> lista = new ArrayList<E>();

	public E get(int indice) {
		return lista.get(indice);
	}

	public void add(E elemento) {
		int indice = 0;
		if (lista.size() != 0) {
			while (indice < lista.size() && elemento.compareTo(lista.get(indice)) >= 0) { // Arreglado error del compareTo (< 0)
				indice++;
			}
		}
		lista.add(indice, elemento);
	}

	public E remove(int indice) {
		E borrado = lista.remove(indice);
		return borrado;
	}

	public int size() {
		return lista.size();
	}

	public void clear() {
		for (int i=lista.size() - 1; i > -1; i--) { // Arreglado error de inicio de bucle (i=1) y de que borraba solo la mitad
			lista.remove(i);
		}
	}
}