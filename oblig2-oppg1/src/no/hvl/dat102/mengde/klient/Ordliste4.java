package no.hvl.dat102.mengde.klient;

import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;

import java.util.Iterator;

/**
 * Union av to ordlister + demonstrasjon av iterator vs utvidet for-løkke
 */
public class Ordliste4 {
	public static void main(String[] args) {

		MengdeADT<String> ordListe1 = new KjedetMengde<>();

		String[] ord1 = {"God", "dag", "Hans", "Hansen", "Hansaby", "Olsen", "Ole", "buss", "rute", "Bergen"};

		String[] ord2 = {"God", "Hei", "Eva", "Oslo", "Førde", "Olsen",};

		for (String s : ord1) {
			ordListe1.leggTil(s);
		}

		MengdeADT<String> ordListe2 = new KjedetMengde<>();

		for (String s : ord2) {
			ordListe2.leggTil(s);
		}

		// Lager unionen av de to ordlistene
		MengdeADT<String> ordListeBegge = ordListe1.union(ordListe2);
		// https://www.geeksforgeeks.org/iterator-vs-foreach-in-java/
		// for-each-løkke

		for (String str : ordListeBegge) {
			System.out.println(str);
		}
		// for-løkke med iterator
		System.out.println();

		for (Iterator<String> i = ordListeBegge.iterator(); i.hasNext(); ) {
			System.out.println(i.next());
		}


		/*
		 *
		 * System.out.println("Utskrift av unionen av begge ordlistene"); String
		 * hentStreng = ""; while (!ordListeBegge.erTom()) { hentStreng =
		 * ordListeBegge.fjernTilfeldig(); System.out.println(hentStreng);
		 *
		 * }
		 */
	}

}
