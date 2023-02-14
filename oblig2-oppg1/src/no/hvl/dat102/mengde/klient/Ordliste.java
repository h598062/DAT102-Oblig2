package no.hvl.dat102.mengde.klient;

import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;

import java.util.Scanner;

/**
 * Skriv inn noen ord via console så blir de sammenlignet med ordlisten
 */
public class Ordliste {
	public static void main(String[] args) {

		MengdeADT<String> ordListe1 = new KjedetMengde<>();

		String[] ord = {
				"God",
				"dag",
				"Hans",
				"Hansen",
				"Hansaby",
				"Foerde",
				"Olsen",
				"Ole",
				"buss",
				"rute",
				"Bergen"};

		Scanner tastatur = new Scanner(System.in);
		// Legger til ordene i mengden ordListe1

		for (String s : ord) {
			ordListe1.leggTil(s);
		}
		MengdeADT<String> ordListe2 = new KjedetMengde<>();

		System.out.println("\nOppgi en streng, avslutt med zzz:\n");
		String streng = tastatur.nextLine();
		// Leser inn ord
		while (!streng.equals("zzz")) {

			if (ordListe1.inneholder(streng)) {
				System.out.println("ordListe1 inneholder " + streng);
			} else {
				System.out.println("ordListe1 inneholder ikke " + streng);

			}
			// Legger innleste ord inn i ordliste2
			ordListe2.leggTil(streng);
			System.out.println("\nOppgi en streng, avslutt med zzz\n");
			streng = tastatur.nextLine();

		}

		// Lager unionen av de to ordlistene
		MengdeADT<String> ordListeBegge = ordListe1.union(ordListe2);

		System.out.println("\nUtskrift av unionen av begge ordlistene");
		String hentStreng = "";
		while (!ordListeBegge.erTom()) {
			hentStreng = ordListeBegge.fjernTilfeldig();
			System.out.println(hentStreng);

		}

		// Lager snittet av de to ordlistene
		MengdeADT<String> ordListeFelles = ordListe1.snitt(ordListe2);

		System.out.println("\nUtskrift av snittet av begge ordlistene");
		hentStreng = "";
		while (!ordListeFelles.erTom()) {
			hentStreng = ordListeFelles.fjernTilfeldig();
			System.out.println(hentStreng);

		}

		// Lager differansen av de to ordlistene
		MengdeADT<String> ordListeDiff = ordListe1.differens(ordListe2);

		System.out.println("\nUtskrift av differensen av begge ordlistene");
		hentStreng = "";
		while (!ordListeDiff.erTom()) {
			hentStreng = ordListeDiff.fjernTilfeldig();
			System.out.println(hentStreng);

		}

	}
}
