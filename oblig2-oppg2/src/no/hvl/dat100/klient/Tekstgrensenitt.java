package no.hvl.dat100.klient;

import no.hvl.dat100.Datakontakt;
import no.hvl.dat100.Hobby;
import no.hvl.dat100.Medlem;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;

import java.util.Scanner;

public class Tekstgrensenitt {

	private Tekstgrensenitt() {
	}

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
		Scanner scanner = new Scanner(System.in);
		String navn = scanner.nextLine();
		while (navn.length() == 0) {
			System.out.println("Navnet til medlemmet må være minst et tegn, prøv igjen.");
			System.out.print("Skriv inn navnet på det nye medlemmet: ");
			navn = scanner.nextLine();
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
		scanner.close();
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

	/**
	 * Skriver ut hobbylisten for et medlem
	 *
	 * @param medlem Medlemet som skal få hobbylisten printet ut
	 */
	public static void skrivHobbyListe(Medlem medlem) {
		if (medlem == null) return;
		System.out.println("Alle hobbyene ");
		System.out.println(medlem.getHobbyer().toString());
	}

	/**
	 * Skriver ut en oversikt over alle medlemmer som er koblet til hverandre som par
	 *
	 * @param arkiv Datakontakt hvor paroversikt skal skrives ut
	 */
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
		if (arkiv == null) return;
		KjedetMengde<Integer> funnet = new KjedetMengde<>();
		int x = 0;
		for (int i = 0; i < arkiv.getAntallMedlemmer(); i++) {
			StringBuilder par = new StringBuilder();
			Medlem medlem = arkiv.getMedlemsListe()[i];
			if (medlem.getStatusIndeks() != -1 && !funnet.inneholder(i)) {
				par.append(medlem.getNavn())
				   .append(" og ")
				   .append(arkiv.getMedlemsListe()[medlem.getStatusIndeks()].getNavn());
				par.append("\n").append(medlem.getHobbyer());
				funnet.leggTil(i);
				funnet.leggTil(medlem.getStatusIndeks());
				System.out.println(par);
				x++;
			}
		}
		System.out.println("Antall par funnet: " + x);
	}
}
