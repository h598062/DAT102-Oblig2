package no.hvl.dat102.mengde.adt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class MengdeADTTest {
	private MengdeADT<Integer> mengde1;
	private MengdeADT<Integer> mengde2;
	private MengdeADT<Integer> mengde3;

	private final Integer e0 = 1;
	private final Integer e1 = 2;
	private final Integer e2 = 3;
	private final Integer e3 = 4;
	private final Integer e4 = 5;
	private final Integer e5 = 6;

	protected abstract MengdeADT<Integer> reset();

	@BeforeEach
	public void setup() {
		mengde1 = reset();
		mengde2 = reset();
		mengde3 = reset();
	}

	@Test
	void TestLeggTil() {
		assertTrue(mengde1.erTom());
		assertEquals(0, mengde1.antall());
		mengde1.leggTil(e0);
		assertFalse(mengde1.erTom());
		assertEquals(1, mengde1.antall());
		mengde1.leggTil(e1);
		assertFalse(mengde1.erTom());
		assertEquals(2, mengde1.antall());
	}

	@Test
	void TestUnion() {
		mengde1.leggTil(e0);
		mengde1.leggTil(e1);
		mengde1.leggTil(e3);

		mengde2.leggTil(e2);
		mengde2.leggTil(e1);
		mengde2.leggTil(e4);

		mengde3.leggTil(e0);
		mengde3.leggTil(e1);
		mengde3.leggTil(e3);
		mengde3.leggTil(e2);
		mengde3.leggTil(e4);

		assertEquals(mengde3, mengde1.union(mengde2));

		mengde2.leggTil(e5);

		assertNotEquals(mengde3, mengde1.union(mengde2));
	}

	@Test
	void TestInneholder() {
		mengde1.leggTil(e0);
		mengde1.leggTil(e1);
		mengde1.leggTil(e3);
		assertTrue(mengde1.inneholder(e1));
		assertFalse(mengde1.inneholder(e5));
	}

	@Test
	void TestLeggTilFjernErTom() {
		assertTrue(mengde1.erTom());
		mengde1.leggTil(e0);
		mengde1.leggTil(e2);
		mengde1.leggTil(e5);
		mengde1.fjern(e5);
		mengde1.fjern(e0);
		mengde1.fjern(e2);
		assertTrue(mengde1.erTom());
	}

	@Test
	void TestAntall() {
		mengde1.leggTil(e0);
		mengde1.leggTil(e2);
		mengde1.leggTil(e5);
		assertEquals(3, mengde1.antall());
	}

	@Test
	void TestSnitt() {
		mengde1.leggTil(e0);
		mengde1.leggTil(e1);
		mengde1.leggTil(e2);
		mengde1.leggTil(e3);

		mengde2.leggTil(e2);
		mengde2.leggTil(e3);
		mengde2.leggTil(e4);

		mengde3.leggTil(e2);
		mengde3.leggTil(e3);

		assertEquals(mengde3, mengde1.snitt(mengde2));

		mengde2.leggTil(e0);

		assertNotEquals(mengde3, mengde1.snitt(mengde2));
	}

	@Test
	void TestDifferens() {
		mengde1.leggTil(e0);
		mengde1.leggTil(e1);
		mengde1.leggTil(e2);
		mengde1.leggTil(e3);

		mengde2.leggTil(e2);
		mengde2.leggTil(e3);
		mengde2.leggTil(e4);

		mengde3.leggTil(e0);
		mengde3.leggTil(e1);

		assertEquals(mengde3, mengde1.differens(mengde2));

		mengde2.leggTil(e0);

		assertNotEquals(mengde3, mengde1.differens(mengde2));
	}

	@Test
	void TestUndermengde() {
		mengde1.leggTil(e0);
		mengde1.leggTil(e1);
		mengde1.leggTil(e2);
		mengde1.leggTil(e3);
		mengde1.leggTil(e4);

		mengde2.leggTil(e2);
		mengde2.leggTil(e3);
		mengde2.leggTil(e4);

		assertTrue(mengde1.undermengde(mengde2));

		mengde2.leggTil(e5);

		assertFalse(mengde1.undermengde(mengde2));
	}
}