package no.hvl.dat102.mengde.klient;

import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;
import no.hvl.dat102.mengde.tabell.TabellMengde;

/**
 * Skriver ut union av to ordlister
 */
public class Ordliste3 {

	public static void main(String[] args) {

		MengdeADT<String> ordListe1 = new TabellMengde<>();

		String[] ord1 = {"God", "dag", "Hans", "Hansen", "Hansaby", "Olsen", "Ole", "buss", "rute", "Bergen"};

		String[] ord2 = {"God", "Hei", "Eva", "Oslo", "Foerde", "Olsen",};

		for (String s : ord1) {
			ordListe1.leggTil(s);
		}

		MengdeADT<String> ordListe2 = new KjedetMengde<>();

		for (String s : ord2) {
			ordListe2.leggTil(s);
		}

		// Lager unionen av de to ordlistene
		MengdeADT<String> ordListeBegge = ordListe1.union(ordListe2);

		System.out.println("Utskrift av unionen av begge ordlistene");
		String hentStreng = "";
		while (!ordListeBegge.erTom()) {
			hentStreng = ordListeBegge.fjernTilfeldig();
			System.out.println(hentStreng);

		}
	}

}
