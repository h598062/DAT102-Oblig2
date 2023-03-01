package no.hvl.dat102.listeklient;

import java.util.Scanner;

import no.hvl.dat102.kjedet.KjedetOrdnetListe;

public class SkrivInnPersonerKlient {

	public static void main(String[] args) {
		KjedetOrdnetListe<Person> liste = new KjedetOrdnetListe<>();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Skriv inn 4 personer med fornavn, etternavn og fødselsår");

		for (int i = 0; i < 4; i++) {
			System.out.println("Skriv inn et Person-objekt. ");

			System.out.print("Skriv inn fornavn: ");
			String fornavn = scanner.nextLine();
			System.out.print("Skriv inn etternavn: ");
			String etternavn = scanner.nextLine();
			System.out.print("Skriv inn fødselsår: ");
			String foedselsaar = scanner.nextLine();
			
			liste.leggTil(new Person(fornavn, etternavn, Integer.parseInt(foedselsaar)));
		}
		scanner.close();
		
		while (!liste.erTom()) {
			System.out.println(liste.fjernSiste());
		}
	}

}
