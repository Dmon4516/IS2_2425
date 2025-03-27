package es.unican.is2;

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
			while (indice < lista.size() && elemento.compareTo(lista.get(indice)) < 0) {
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
		for (int i=1; i<lista.size(); i++) {
			lista.remove(i);
		}
	}
}