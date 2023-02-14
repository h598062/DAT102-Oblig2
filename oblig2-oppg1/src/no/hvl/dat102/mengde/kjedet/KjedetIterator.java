package no.hvl.dat102.mengde.kjedet;

//****************************************************************

//    Representerer en iterator for en kjedet struktur av noder 
//    kjedet lineært. 
//****************************************************************

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Representerer en iterator for en kjedet struktur av noder kjedet lineært.
 *
 * @param <T> Elementtype som skal lagres
 */
public class KjedetIterator<T> implements Iterator<T> {
	private LinearNode<T> aktuell; // den aktuelle posisjonen.

	/**
	 * Lager en iterator (oppramsar)
	 *
	 * @param samling samling
	 */
	public KjedetIterator(LinearNode<T> samling) {
		aktuell = samling;
	}

	/************************************************************
	 * Returnerer sann hvis iteratoren har minst ett element igjen.
	 *************************************************************/
	@Override
	public boolean hasNext() {
		return (aktuell != null);
	}

	/*************************************************************
	 * Returnerer neste element hvis det fins.
	 *************************************************************/
	@Override
	public T next() {
		if (!hasNext()) {throw new NoSuchElementException();}
		T resultat = aktuell.getElement();
		aktuell = aktuell.getNeste();
		return resultat;
	}

	/*************************************************************
	 * Fjern er ikke implementert..
	 *************************************************************/
	@Override
	public void remove() {
		throw new UnsupportedOperationException("Denne er ikke implementert");
	}

}
