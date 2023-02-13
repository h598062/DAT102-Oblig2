package no.hvl.dat102.adt;

import no.hvl.dat102.exceptions.EmptyCollectionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class OrdnetListeADTTest {

	/**
	 * Test av OrdnetListe med heltall.
	 *
	 * @author Ole Olsen
	 */

	private OrdnetListeADT<Integer> liste;

	// Test data
	private Integer e0 = 1;
	private Integer e1 = 2;
	private Integer e2 = 3;
	private Integer e3 = 4;
	private Integer e4 = 5;
	private Integer e5 = 6;

	/**
	 * Opprett en tom liste for hver test.
	 */

	protected abstract OrdnetListeADT<Integer> reset();

	@BeforeEach
	public final void setup() {
		liste = reset();
	}

	/**
	 * Tester om en ny liste er tom.
	 */
	@Test
	public final void nyListeErTom() {
		assertTrue(liste.erTom());
	}

	/**
	 * Tester leggTil og fjern.
	 */
	@Test
	public final void leggTilOgFjern() {
		try {
			liste.leggTil(e0);
			liste.leggTil(e1);
			liste.leggTil(e2);
			liste.leggTil(e3);
			liste.leggTil(e4);
			liste.leggTil(e5);
			assertEquals(e5, liste.fjern(e5));
			assertEquals(e4, liste.fjern(e4));
			assertEquals(e3, liste.fjern(e3));
			assertEquals(e2, liste.fjern(e2));
			assertEquals(e1, liste.fjern(e1));
			assertEquals(e0, liste.fjern(e0));
		} catch (EmptyCollectionException e) {
			fail("feilet uventet " + e.getMessage());
		}
	}

	/**
	 * Tester ordning ikke-avtagende
	 */
	@Test
	public final void viseOrdnetIkkeAvtagende() {
		try {
			liste.leggTil(e1);
			liste.leggTil(e2);
			liste.leggTil(e5);
			liste.leggTil(e0);
			liste.leggTil(e4);
			liste.leggTil(e3);
			assertEquals(e0, liste.fjernFoerste());
			assertEquals(e1, liste.fjernFoerste());
			assertEquals(e2, liste.fjernFoerste());
			assertEquals(e3, liste.fjernFoerste());
			assertEquals(e4, liste.fjernFoerste());
			assertEquals(e5, liste.fjernFoerste());
		} catch (EmptyCollectionException e) {
			fail("feilet uventet " + e.getMessage());
		}
	}

	@Test
	public final void viseOrdnetIkkeStigende() {
		try {
			liste.leggTil(e1);
			liste.leggTil(e2);
			liste.leggTil(e5);
			liste.leggTil(e0);
			liste.leggTil(e4);
			liste.leggTil(e3);
			assertEquals(e5, liste.fjernSiste());
			assertEquals(e4, liste.fjernSiste());
			assertEquals(e3, liste.fjernSiste());
			assertEquals(e2, liste.fjernSiste());
			assertEquals(e1, liste.fjernSiste());
			assertEquals(e0, liste.fjernSiste());
		} catch (EmptyCollectionException e) {
			fail("feilet uventet " + e.getMessage());
		}
	}

	/**
	 * Tester leggTil og fjern med like verdier.
	 */
	@Test
	public final void leggTilOgfjernMedDuplikater() {
		try {
			liste.leggTil(e0);
			liste.leggTil(e1);
			liste.leggTil(e2);
			liste.leggTil(e3);
			liste.leggTil(e1);
			liste.leggTil(e3);
			liste.leggTil(e4);
			liste.leggTil(e5);
			liste.leggTil(e5);

			assertEquals(e0, liste.fjern(e0));
			assertEquals(e1, liste.fjern(e1));
			assertEquals(e4, liste.fjern(e4));
			assertEquals(e1, liste.fjern(e1));
			assertEquals(e2, liste.fjern(e2));
			assertEquals(e3, liste.fjern(e3));

		} catch (EmptyCollectionException e) {
			fail("feilet uventet " + e.getMessage());
		}

	}

	/**
	 * Tester leggTil og inneholder
	 */
	@Test
	public final void leggTilOgInnholder() {
		liste.leggTil(e2);
		liste.leggTil(e1);
		liste.leggTil(e4);
		liste.leggTil(e0);
		liste.leggTil(e3);

		assertTrue(liste.inneholder(e0));
		assertTrue(liste.inneholder(e1));
		assertTrue(liste.inneholder(e2));
		assertTrue(liste.inneholder(e3));
		assertTrue(liste.inneholder(e4));
		assertFalse(liste.inneholder(e5));

	}

	/**
	 * Tester om listen med verdier ikke er tom.
	 */
	@Test
	public final void erIkkeTom() {
		liste.leggTil(e1);
		liste.leggTil(e3);
		liste.leggTil(e2);
		liste.leggTil(e4);
		liste.leggTil(e5);
		assertFalse(liste.erTom());
	}

	/**
	 * Tester om leggTil-fjern på en tom liste gir en tom liste.
	 */
	@Test
	public final void leggTilFjernErTom() {
		try {
			liste.leggTil(e0);
			liste.leggTil(e1);
			liste.leggTil(e2);
			liste.leggTil(e3);
			liste.leggTil(e4);
			liste.leggTil(e5);
			liste.fjern(e5);
			liste.fjern(e4);
			liste.fjern(e3);
			liste.fjern(e2);
			liste.fjern(e1);
			liste.fjern(e0);
			assertTrue(liste.erTom());
		} catch (EmptyCollectionException e) {
			fail("feilet uventet " + e.getMessage());
		}
	}

	/**
	 * Prøver å ta ut et element fra en tom liste.
	 */
	@Test

	public void fjernFraTomListe() {
		Assertions.assertThrows(EmptyCollectionException.class, () -> {
			liste.fjernFoerste();
		});
	}


}
