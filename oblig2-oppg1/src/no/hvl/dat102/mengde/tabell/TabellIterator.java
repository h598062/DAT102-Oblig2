package no.hvl.dat102.mengde.tabell;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Klasse som kan brukes til å gå gjennom alle elementer i et objekt av klasse Mengde når denne klassen Mengde
 * er implementert vha tabell.
 *
 * @param <T> Elementtype som skal lagres
 */
public class TabellIterator<T> implements Iterator<T> {
	private final T[] elementer;
	private final int antall; // antall elementer i mengden
	private int pos; // posisjonen til aktuelt element

	/**
	 * Konstruktør for å opprette TabellIterator
	 *
	 * @param tab    tabell som skal få iterator
	 * @param lengde lengde på iterator / tabell
	 */
	public TabellIterator(T[] tab, int lengde) {
		// Gi startverdier til iteratoren
		elementer = tab;
		antall = lengde;
		pos = 0;
	}

	@Override
	public boolean hasNext() {
		// Returner med posisjonen til neste aktuelle element
		return (pos < antall);
	}

	@Override
	public T next() {
		// Returner med posisjonen til neste aktuelle element
		if (!hasNext()) {throw new NoSuchElementException();}
		pos++;
		return elementer[pos - 1];
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("Denne er ikke implementert");
	}
}// class
