package no.hvl.dat100.klient;

import no.hvl.dat100.Datakontakt;
import no.hvl.dat100.Hobby;
import no.hvl.dat100.Medlem;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;

import java.util.Scanner;

public class Tekstgrensenitt {

	private Tekstgrensenitt() {
	}

	private static final Scanner scanner = new Scanner(System.in);
	// Hvis du vil lage meny, kan du også legge det inn i Tekstgrensesnitt

	/**
	 * Leser opplysningene om et medlem fra tastatur <br> looper helt til et medlem med navn med minst ett
	 * tegn blir skrevet inn <br> leser inn nye hobbyer helt til en tom streng sendes (trykker enter uten å
	 * skrive noe)
	 *
	 * @return et nytt medlem
	 */
	public static Medlem lesMedlem() {
		System.out.println("Skriv inn et nytt medlem");
		System.out.print("Skriv inn navnet på det nye medlemmet: ");
		String navn = scanner.nextLine();
		if (navn.length() == 0) {
			System.out.println("Navnet til medlemmet må være minst et tegn, prøv igjen.");
			lesMedlem();
		}
		KjedetMengde<Hobby> hobbyer = new KjedetMengde<>();
		System.out.println(
				"""
				Nå kan du legge inn hobbyer om ønsket.
				Skriv inn en hobby og trykk enter.
				du kan legge inn så mange som du vil
				hvis du ikke vil legge inn flere hobbyer trykker du bare enter""");
		System.out.print("Skriv inn en hobby til medlemmet: ");
		String hobby = scanner.nextLine();
		while (hobby.length() != 0) {
			hobbyer.leggTil(new Hobby(hobby));
			System.out.print("Skriv inn en til hobby til medlemmet: ");
			hobby = scanner.nextLine();
		}
		Medlem medlem;
		if (!hobbyer.erTom()) {
			medlem = new Medlem(navn, hobbyer);
		} else {
			medlem = new Medlem(navn);
		}
		System.out.println("\nLegger til dette medlemmet:");
		System.out.println(medlem);
		return medlem;
	}

	// Skriver ut hobbylisten for et medlem
	public static void skrivHobbyListe(Medlem medlem) {
		System.out.println("Alle hobbyene ");
		System.out.println(medlem.getHobbyer().toString());
	}

	public static void skrivParListe(Datakontakt arkiv) {
		/* skriver ut på skjermen en oversikt over medlemmer som er koblet
		til hverandre i medlemstabellen til enhver tid.
		Et slikt par skal kun vises én gang på utskriftlisten. Metoden
		skriver også ut antall par som er funnet.
		Eksempel på utskrift:
		PARNAVN
		HOBBYER
		Erna og Jonas
		<ski, musikk, politikk>
		Eva og Adam
		< epleplukking, paradishopping>
		…………………….
		Antall par funnet: 12
		*/
	}
}
