package no.hvl.dat102.klient;

import no.hvl.dat102.adt.Parentessjekker;
import no.hvl.dat102.adt.StabelADT;
import no.hvl.dat102.exception.EmptyCollectionException;
import no.hvl.dat102.kjedet.KjedetStabel;

public class TekststrengSjekker implements Parentessjekker {
	@Override
	public boolean erVenstreparentes(char p) {
		return p == '(' || p == '{' || p == '[';
	}

	@Override
	public boolean erHogreparentes(char p) {
		return p == ')' || p == '}' || p == ']';
	}

	@Override
	public boolean erParentes(char p) {
		return p == '(' || p == '{' || p == '[' || p == ')' || p == '}' || p == ']';
	}

	@Override
	public boolean erPar(char venstre, char hogre) {
		switch (venstre) {
			case '(' -> {
				if (hogre == ')')
					return true;
			}
			case '{' -> {
				if (hogre == '}')
					return true;
			}
			case '[' -> {
				if (hogre == ']')
					return true;
			}
			default -> {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean erBalansert(String s) {
		StabelADT<Character> stabel = new KjedetStabel<>();
		boolean balansert = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (erParentes(c)) {
				if (erVenstreparentes(c)) {
					stabel.push(c);
				} else if (erHogreparentes(c)) {
					char x;
					try {
						x = stabel.pop();
					} catch (EmptyCollectionException e) {
						return false;
					}
					balansert = erPar(x, c);
				}
			}
		}
		return balansert && stabel.erTom();
	}

	public static void main(String[] args) {
		TekststrengSjekker t = new TekststrengSjekker();
		// danner ikke par
		String feil1 = "dhjfghs[)";
		System.out.println("FEIL 1: " + t.erBalansert(feil1));
		// lukkesymbol og stabel er tom
		String feil2 = "dhjfghs()))";
		System.out.println("FEIL 2: " + t.erBalansert(feil2));
		// slutt på teksten og det er flere parantes igjen
		String feil3 = "dhjfghs((((())";
		System.out.println("FEIL 3: " + t.erBalansert(feil3));
		// riktig tekststreng
		String riktig = "dhfga((){{[]}[]})";
		System.out.println("RIKTIG: " + t.erBalansert(riktig));
	}
}
