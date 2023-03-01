package no.hvl.dat100;

public class Datakontakt {
	private Medlem[] medlemsListe;
	private int antallMedlemmer;

	/**
	 * Oppretter en ny datakontrakt med en medlemsliste som kan holde 20 medlemmer
	 */
	public Datakontakt() {
		this(20);
	}

	/**
	 * Oppretter en ny datakontrakt med en medlemsliste som kan holde spesifisert antall medlemmer
	 *
	 * @param antall Antall medlemmer listen skal holde
	 */
	public Datakontakt(int antall) {
		this.medlemsListe = new Medlem[antall > 0 ? antall : 20];
		this.antallMedlemmer = 0;
	}

	/**
	 * Legger til et nytt medlem i medlemsliste <br> Utvider intern medlemstabell når nødvendig
	 *
	 * @param person Medlem som skal legges inn
	 */
	public void leggTilMedlem(Medlem person) {
		if (antallMedlemmer + 1 == medlemsListe.length) {
			utvid();
		}
		medlemsListe[antallMedlemmer] = person;
		antallMedlemmer++;
	}

	/**
	 * Intern metode for å utvide tabellen
	 */
	private void utvid() {
		Medlem[] ny = new Medlem[medlemsListe.length * 2];
		System.arraycopy(medlemsListe, 0, ny, 0, medlemsListe.length);
		this.medlemsListe = ny;
	}

	/**
	 * Finner indeks i medlemliste for medlemmet med spesifisert navn, eller -1 hvis det ikke finnes
	 *
	 * @param medlemsnavn Medlem å lete etter
	 *
	 * @return Indeks til medlemmet, eller -1 hvis det ikke finnes
	 */
	public int finnMedlemsIndeks(String medlemsnavn) {
		if (medlemsnavn == null || medlemsnavn.length() == 0) return -1;
		for (int i = 0; i < antallMedlemmer; i++) {
			if (medlemsListe[i].getNavn().equals(medlemsnavn)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Finner en partner til et medlem i medlemlisten, og oppdaterer medlem og partner. <br> Denne vil kun
	 * matche partner hvis begge har like hobbyer. <br> Returnerer indeks til funnet medlem eller -1 hvis ingen
	 * blir funnet
	 *
	 * @param medlemsnavn Medlemmet som vi skal finne partner til
	 *
	 * @return Indeks til partner som blir funnet, eller -1 hvis ingen blir funnet
	 */
	public int finnPartnerFor(String medlemsnavn) {
		Medlem medlem = finnMedlem(medlemsnavn);
		if (medlem == null) return -1;
		for (int i = 0; i < antallMedlemmer; i++) {
			Medlem p = medlemsListe[i];
			if (!p.equals(medlem) && p.passerTil(medlem) &&
			    p.getStatusIndeks() == -1) {
				p.setStatusIndeks(finnMedlemsIndeks(medlemsnavn));
				medlem.setStatusIndeks(i);
				return i;
			}
		}
		return -1;
	}

	/**
	 * Interm metode for å hente Medlem-objekt ut ifra medlemsnavn tekststreng
	 *
	 * @param medlemsnavn Navn til medlem som skal finnes
	 *
	 * @return Referanse til Medlem objektet som tilhører oppgitt navn
	 */
	private Medlem finnMedlem(String medlemsnavn) {
		if (medlemsnavn == null || medlemsnavn.length() == 0) return null;
		int indeks = finnMedlemsIndeks(medlemsnavn);
		if (indeks == -1) return null;
		return medlemsListe[indeks];
	}

	/**
	 * Tilbakestiller et medlem til å ikke ha partner <br> Fjerner også statusindeks fra partneren som peker
	 * til dette medlemmet
	 *
	 * @param medlemsnavn medlemmet som skal få fjernet partneren sin
	 */
	public void tilbakestillStausIndeks(String medlemsnavn) {
		Medlem m = finnMedlem(medlemsnavn);
		if (m != null && m.getStatusIndeks() != -1 &&
		    medlemsListe[m.getStatusIndeks()] != null) {
			medlemsListe[m.getStatusIndeks()].setStatusIndeks(-1);
			m.setStatusIndeks(-1);

		}
	}

	public Medlem[] getMedlemsListe() {
		return medlemsListe;
	}

	public int getAntallMedlemmer() {
		return antallMedlemmer;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Datakontakt\n");
		sb.append("Medlemmer: ").append(antallMedlemmer);
		sb.append("\n");
		for (int i = 0; i < antallMedlemmer; i++) {
			sb.append(medlemsListe[i]);
			if (i < antallMedlemmer - 1) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}
}
