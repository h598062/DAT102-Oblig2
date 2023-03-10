package no.hvl.dat102.tabell;

import java.util.Iterator;

import no.hvl.dat102.adt.OrdnetListeADT;
import no.hvl.dat102.exceptions.EmptyCollectionException;

public class TabellOrdnetListe<T extends Comparable<T>> implements OrdnetListeADT<T> {

	private static final int STDK = 100;
	private static final int IKKE_FUNNET = -1;
	private int bak;
	private T[] liste;

	public TabellOrdnetListe() {
		this(STDK);
	}

	public TabellOrdnetListe(int startKapasitet) {
		bak = 0;
		liste = (T[]) (new Comparable[startKapasitet]);
	}

	@Override
	public T fjernSiste() {
		if (erTom()) {
			throw new EmptyCollectionException("ordnet liste");
		}

		T resultat = null;
		resultat = liste[bak - 1];
		liste[bak - 1] = null;
		bak--;
		return resultat;
	}

	@Override
	public T fjernFoerste() {
		if (erTom()) {
			throw new EmptyCollectionException("ordnet liste");
		}

		T resultat = null;
		resultat = liste[0];
		for (int i = 1; i < bak; i++) {
			liste[i - 1] = liste[i];
		}
		return resultat;
	}

	@Override
	public T foerste() {
		if (erTom()) {
			throw new EmptyCollectionException("ordnet liste");
		}

		T resultat = liste[0];
		return resultat;
	}

	@Override
	public T siste() {
		if (erTom()) {
			throw new EmptyCollectionException("ordnet liste");
		}

		T resultat = liste[bak-1];

		return resultat;
	}

	@Override
	public boolean erTom() {
		return (bak == 0);
	}

	@Override
	public int antall() {
		return bak;
	}

	@Override
	public void leggTil(T element) {
		if (bak == liste.length) {
			utvid();
		}
		int pos = 0;
		boolean funnet = false;
		while (!funnet && pos < bak) {
			int noe = element.compareTo(liste[pos]);
			if (noe == -1) {
				funnet = true;
			} else {
				pos++;
			}
		}

		for (int i = bak; i >= pos; i--) {
			liste[i + 1] = liste[i];
		}
		liste[pos] = element;
		bak++;
	}

	@Override
	public boolean inneholder(T element) {
		return (finn(element) != IKKE_FUNNET);
	}

	@Override
	public T fjern(T element) {
		if (erTom()) {
			throw new EmptyCollectionException("ordnet liste");
		}
		
		int pos = finn(element);
		
		for (int i = pos + 1; i < bak; i++) {
			liste[i - 1] = liste[i];
		}
		bak--;
		
		return element;

	}

	private int finn(T el) {
		int resultat = IKKE_FUNNET;
		int pos = 0;
		boolean funnet = false;
		while (!funnet && pos<= bak) {
			if (el.equals(liste[pos])) {
				funnet = true;
				resultat = pos;
			} else {
				pos++;
			}
		}
		return resultat;
	}

	public String toString() {
		String resultat = "";

		for (int i = 0; i < bak; i++) {
			resultat = resultat + liste[i].toString() + "\n";
		}
		return resultat;
	}

	private void utvid() {
		T[] hjelpeTabell = (T[]) (new Comparable[liste.length * 2]);

		for (int i = 0; i < liste.length; i++) {
			hjelpeTabell[i] = liste[i];
		}

		liste = hjelpeTabell;
	}

}// class
