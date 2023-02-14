package no.hvl.dat100.klient;

import no.hvl.dat100.Datakontakt;
import no.hvl.dat100.Medlem;

public class DatakontaktKlient {
	public static void main(String[] args) {
		Datakontakt dk = new Datakontakt();
		Medlem nyMedlem = Tekstgrensenitt.lesMedlem();
		dk.leggTilMedlem(nyMedlem);
		System.out.println(dk);
	}
}
