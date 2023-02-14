package no.hvl.dat100;

import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;

import java.util.Objects;

public class Medlem {
	private String navn;
	private final MengdeADT<Hobby> hobbyer;
	/**
	 * Statusindeksen til partner i medlemstabell
	 */
	private int statusIndeks;

	// flere forskjellige konstruktører for å dekke alle måter å opprette medlem

	/**
	 * Standard konstruktøren for Medlem <br> Oppretter en tom hobbymengde og setter statusIndeks til -1
	 *
	 * @param navn navnet til medlemmet
	 */
	public Medlem(String navn) {
		this.navn = navn;
		this.hobbyer = new KjedetMengde<>();
		this.statusIndeks = -1;
	}

	/**
	 * Oppretter et medlem med forhåndsdefinert mengde med hobbyer
	 *
	 * @param navn    navnet til medlemmet
	 * @param hobbyer en forhåndsdefinert mengde med hobbyer
	 */
	public Medlem(String navn, MengdeADT<Hobby> hobbyer) {
		this.navn = navn;
		this.hobbyer = new KjedetMengde<>();
		this.hobbyer.leggTilAlle(hobbyer);
		this.statusIndeks = -1;
	}

	/**
	 * Oppretter et medlem med forhåndsdefinert mengde med hobbyer
	 *
	 * @param navn    navnet til medlemmet
	 * @param hobbytab en tabell med hobbynavn tekststrenger
	 */
	public Medlem(String navn, String[] hobbytab) {
		this.navn = navn;
		this.hobbyer = new KjedetMengde<>();
		for (String s : hobbytab) {
			this.hobbyer.leggTil(new Hobby(s));
		}
		this.statusIndeks = -1;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Medlem medlem = (Medlem) o;
		return statusIndeks == medlem.statusIndeks && Objects.equals(navn, medlem.navn) &&
		       Objects.equals(hobbyer, medlem.hobbyer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(navn, hobbyer, statusIndeks);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Medlem{");
		sb.append("navn='").append(navn).append('\'');
		sb.append(", hobbyer=").append(hobbyer);
		sb.append(", statusIndeks=").append(statusIndeks);
		sb.append('}');
		return sb.toString();
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public MengdeADT<Hobby> getHobbyer() {
		return hobbyer;
	}

	public int getStatusIndeks() {
		return statusIndeks;
	}

	public void setStatusIndeks(int statusIndeks) {
		this.statusIndeks = statusIndeks;
	}

	/**
	 * Sjekker om et annet medlem passer med dette medlemmet.<br> Funksjonelt så må begge medlemmene ha like
	 * hobbyer
	 *
	 * @param medlem2 Medlemmet som skal sammenlignes
	 *
	 * @return Sant hvis de har like hobbyer, usant hvis ikke
	 */
	public boolean passerTil(Medlem medlem2) {
		return this.hobbyer.equals(medlem2.getHobbyer());
	}

	public void leggTilHobby(String hobby) {
		this.hobbyer.leggTil(new Hobby(hobby));
	}
}
