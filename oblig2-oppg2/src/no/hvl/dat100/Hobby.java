package no.hvl.dat100;

import java.util.Objects;

public class Hobby {
	private String hobbyNavn;

	public Hobby(String hobby) {
		hobbyNavn = hobby;
	}

	public String toString() {
		return "<" + hobbyNavn + ">";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Hobby hobbyDenAndre = (Hobby) o;
		return (hobbyNavn.equals(hobbyDenAndre.getHobbyNavn()));
	}

	@Override
	public int hashCode() {
		return Objects.hash(hobbyNavn);
	}

	public String getHobbyNavn() {
		return hobbyNavn;
	}

	public void setHobbyNavn(String hobbyNavn) {
		this.hobbyNavn = hobbyNavn;
	}
}
