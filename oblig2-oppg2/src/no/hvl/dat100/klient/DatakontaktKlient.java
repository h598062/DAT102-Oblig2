package no.hvl.dat100.klient;

import no.hvl.dat100.Datakontakt;
import no.hvl.dat100.Hobby;
import no.hvl.dat100.Medlem;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;

public class DatakontaktKlient {
	public static void main(String[] args) {
		Datakontakt dk = new Datakontakt();
		Medlem nyMedlem = Tekstgrensenitt.lesMedlem();
		dk.leggTilMedlem(nyMedlem);
		KjedetMengde<Hobby> h1 = new KjedetMengde<>();
		h1.leggTil(new Hobby("Pokemon kort"));
		h1.leggTil(new Hobby("Strikking"));
		h1.leggTil(new Hobby("Fotball"));
		Medlem m1 = new Medlem("Storm", h1);
		Medlem m2 = new Medlem("Kristian", h1);
		Medlem m3 = new Medlem("Bjørnar");
		m3.leggTilHobby("is");
		dk.leggTilMedlem(m1);
		dk.leggTilMedlem(m2);
		dk.leggTilMedlem(m3);
		dk.leggTilMedlem(new Medlem("Gunn", new String[]{"Fotball", "Kjeks"}));
		dk.leggTilMedlem(new Medlem("Solga", new String[]{"Fotball", "Kjeks"}));
		dk.leggTilMedlem(new Medlem("Ragnhus", new String[]{"tiss"}));
		dk.leggTilMedlem(new Medlem("Bo", new String[]{"tiss"}));
		dk.leggTilMedlem(new Medlem("Bingus"));
		dk.leggTilMedlem(new Medlem("Bongo"));
		dk.leggTilMedlem(new Medlem("WRÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆ"));
		System.out.println();
		for (int i = 0; i < dk.getAntallMedlemmer(); i++) {
			Medlem m = dk.getMedlemsListe()[i];
			if (m.getStatusIndeks() == -1) {
				int x = dk.finnPartnerFor(m.getNavn());
				if (x != -1) {
					System.out.println("Fant en partner til " + m.getNavn() + ": " +
					                   dk.getMedlemsListe()[m.getStatusIndeks()].getNavn());
				} else {
					System.out.println("Fant ikke partner til " + m.getNavn());
				}
			}
		}
		System.out.println("\nAlle par:");
		Tekstgrensenitt.skrivParListe(dk);
		System.out.println("\nHele datakontakt atm:");
		System.out.println(dk);
		dk.tilbakestillStausIndeks("Storm");
		System.out.println("\nParliste etter tilbakestill Storm:");
		Tekstgrensenitt.skrivParListe(dk);
		System.out.println("\nHele datakontakt atm:");
		System.out.println(dk);
	}
}
