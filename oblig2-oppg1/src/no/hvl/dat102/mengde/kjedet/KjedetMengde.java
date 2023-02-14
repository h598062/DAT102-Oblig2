package no.hvl.dat102.mengde.kjedet;

import no.hvl.dat102.exception.EmptyCollectionException;
import no.hvl.dat102.mengde.adt.MengdeADT;

import java.util.Iterator;
import java.util.Random;

/**
 * Kjedet implementasjon av en mengde.
 *
 * @param <T> Elementtype som skal lagres
 */
public class KjedetMengde<T> implements MengdeADT<T> {
	private static final Random rand = new Random();
	private int antall; // antall elementer i mengden
	private LinearNode<T> start;

	/**
	 * Oppretter en tom mengde.
	 */
	public KjedetMengde() {
		antall = 0;
		start = null;
	}

	@Override
	public void leggTil(T element) {
		if (!(inneholder(element))) {
			LinearNode<T> node = new LinearNode<>(element);
			node.setNeste(start);
			start = node;
			antall++;
		}
	}

	@Override
	public void leggTilAlle(MengdeADT<T> m2) {
		for (T element : m2) {
			this.leggTil(element);
		}
	}

	@Override
	public T fjernTilfeldig() {
		if (erTom()) {throw new EmptyCollectionException("mengde");}

		LinearNode<T> forgjenger, aktuell;
		T resultat;

		int valg = rand.nextInt(antall) + 1;
		if (valg == 1) {
			resultat = start.getElement();
			start = start.getNeste();
		} else {
			forgjenger = start;
			for (int nr = 2; nr < valg; nr++) {
				forgjenger = forgjenger.getNeste();
			}
			aktuell = forgjenger.getNeste();
			resultat = aktuell.getElement();
			forgjenger.setNeste(aktuell.getNeste());
		}
		antall--;

		return resultat;

	}//

	@Override
	public T fjern(T element) {

		if (erTom()) {throw new EmptyCollectionException("mengde");}

		boolean funnet = false;
		LinearNode<T> forgjenger, aktuell;
		T resultat = null;
		if (start.getElement().equals(element)) {// Sletter foran
			resultat = start.getElement();
			start = start.getNeste();
			antall--;
		} else {// Gjennomgår den kjedete strukturen
			forgjenger = start;
			aktuell = start.getNeste();
			for (int sok = 2; sok <= antall && !funnet; sok++) {
				if (aktuell.getElement().equals(element)) {funnet = true;} else {
					forgjenger = aktuell;
					aktuell = aktuell.getNeste();
				}
			}
			if (funnet) {
				resultat = aktuell.getElement();
				forgjenger.setNeste(aktuell.getNeste()); // Slette midt inni/bak
				antall--;
			}
		}
		return resultat;

	}//

	@Override
	public boolean inneholder(T element) {
		boolean funnet = false;
		LinearNode<T> aktuell = start;
		for (int soek = 0; soek < antall && !funnet; soek++) {
			if (aktuell.getElement().equals(element)) {
				funnet = true;
			} else {
				aktuell = aktuell.getNeste();
			}
		}
		return funnet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + antall;
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object ny) {

		if (this == ny) {
			return true;
		}
		if (ny == null) {
			return false;
		}
		if (getClass() != ny.getClass()) {
			return false;
		}
		boolean likeMengder = true;
		MengdeADT<T> m2 = (KjedetMengde<T>) ny;
		if (this.antall != m2.antall()) {
			likeMengder = false;
		} else {
			Iterator<T> teller = m2.iterator();
			while (teller.hasNext() && likeMengder) {
				T element = teller.next();
				if (!this.inneholder(element)) {
					likeMengder = false;
				}
			}
		}
		return likeMengder;
	}

	@Override
	public boolean erTom() {
		return antall == 0;
	}

	@Override
	public int antall() {
		return antall;
	}


	@Override
	public MengdeADT<T> union(MengdeADT<T> m2) {
		// alt i mengde 1 + alt i mengde 2
		KjedetMengde<T> begge = new KjedetMengde<>();
		for (T element : this) {
			// bruk settInn, siden vi vet at alle elementer fra denne mengden er unike
			begge.settInn(element);
		}
		for (T element : m2) {
			// bruk settInn og inneholder, bare at inneholder sjekker mot denne istedenfor begge
			// da blir ikke inneholder sjekk i leggTil kjørt, og den vil ta lengre tid for hvert element
			// vi legger inn i begge
			if (!this.inneholder(element)) {
				begge.settInn(element);
			}
		}
		return begge;
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> m2) {
		// snitt = det som er felles mellom begge
		MengdeADT<T> snittM = new KjedetMengde<>();
		for (T element : m2) {
			if (this.inneholder(element)) {
				snittM.leggTil(element);
			}
		}
		return snittM;
	}

	@Override
	public MengdeADT<T> differens(MengdeADT<T> m2) {
		// returnere det som er i denne mengden (this) men ikke i parameter mengde m2
		MengdeADT<T> differensM = new KjedetMengde<>();
		for (T element : this) {
			if (!m2.inneholder(element)) {
				differensM.leggTil(element);
			}
		}
		return differensM;
	}

	@Override
	public boolean undermengde(MengdeADT<T> m2) {
		// kan ikke være undermengde (delmengde) hvis denne mengden er mindre
		if (this.antall < m2.antall()) return false;
		// hvis count blir lik antall i m2 så har vår mengde (this) alle elementer som er i m2
		int count = 0;
		for (T element : m2) {
			if (this.inneholder(element)) {
				count++;
			}
		}
		return count == m2.antall();
	}

	@Override
	public Iterator<T> iterator() {
		return new KjedetIterator<>(start);
	}

	/**
	 * Legger til element uten å sjekke om det allerede finnes i mengde
	 *
	 * @param element element som skal legges til
	 */
	private void settInn(T element) {
		LinearNode<T> nyNode = new LinearNode<>(element);
		nyNode.setNeste(start);
		start = nyNode;
		antall++;
	}

}
