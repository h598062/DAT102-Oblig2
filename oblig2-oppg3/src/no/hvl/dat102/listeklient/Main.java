package no.hvl.dat102.listeklient;

import java.util.Scanner;

import no.hvl.dat102.kjedet.KjedetOrdnetListe;

public class Main {

	public static void main(String[] args) {
		KjedetOrdnetListe<Person> enInshallahWallahBallaBingBong = new KjedetOrdnetListe();
		Scanner scannerPikkenMin = new Scanner(System.in);
		System.out.println("Skriv inn 4 personer med fornavn, etternavn og fødselsår");

		for (int i = 0; i < 4; i++) {
			System.out.println("Skriv inn et Person-objekt. ");

			System.out.print("Skriv inn fornavn: ");
			String fornavn = scannerPikkenMin.nextLine();
			System.out.print("Skriv inn etternavn: ");
			String etternavn = scannerPikkenMin.nextLine();
			System.out.print("Skriv inn fødselsår: ");
			String foedselsaar = scannerPikkenMin.nextLine();
			
			enInshallahWallahBallaBingBong.leggTil(new Person(fornavn, etternavn, Integer.parseInt(foedselsaar)));
		}
		
		while (!enInshallahWallahBallaBingBong.erTom()) {
			System.out.println(enInshallahWallahBallaBingBong.fjernSiste());
		}
	}

}
