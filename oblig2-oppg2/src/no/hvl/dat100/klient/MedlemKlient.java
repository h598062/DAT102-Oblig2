package no.hvl.dat100.klient;

import no.hvl.dat100.Medlem;

public class MedlemKlient {
	public static void main(String[] args) {
		Medlem[] medlemmer = new Medlem[3];
		int i = 0;
		medlemmer[i++] = new Medlem("Storm");
		medlemmer[i++] = new Medlem("Kristian");
		medlemmer[i] = new Medlem("Bjørnar");
		medlemmer[0].leggTilHobby("Se på lettkledde menn");
		medlemmer[1].leggTilHobby("Flodsvin");
		medlemmer[1].leggTilHobby("Fotball");
		medlemmer[2].leggTilHobby("Datamaskinspill");
		medlemmer[2].leggTilHobby("Thomastoget");
		for (Medlem m : medlemmer) {
			System.out.println(m);
		}
	}
}
