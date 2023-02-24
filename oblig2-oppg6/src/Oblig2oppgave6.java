import java.util.HashSet;
import java.util.Set;

public class Oblig2oppgave6 {

	// brukes til oppgave b
	static Set<Integer> tall = new HashSet<>();

	// OPPGAVE 4A
	public static int oppgaveA(int n) {
		if (n == 1) {
			return 1;
		} else {
			return oppgaveA(n - 1) + n;
		}
	}

	// OPPGAVE 4B
	public static int oppgaveB(int n) {
		if (n == 1) {
			return 5;
		}
		if (n == 0) {
			return 2;
		}
		int svar = (5 * oppgaveB(n - 1)) - (6 * oppgaveB(n - 2)) + 2;
		tall.add(svar);
		return svar;
	}

	// OPPGAVE 4C
	public static int oppgaveC(int f) {
		if (f == 0) {
			return 0;
		} else if (f == 1) {
			return 1;
		} else {
			return oppgaveC(f - 1) + oppgaveC(f - 2);
		}
		// 𝑓𝑛 = 𝑓𝑛−1 + 𝑓𝑛−2
	}


	// OPPGAVE 4D
	public static void oppgaveD(int fi) {
		//     Det er lett å lage en ikkerekursiv metode for å beregne Fibonaccitallene. Lag en slik metode
		//     og observer at den vil være rask å utføre for verdier av n der den rekursive metoden bruker
		//     lang tid. Tips: Beregn 𝑓2, 𝑓3, … , 𝑓𝑛.
		int svar    = 0;
		int forrige = 1;
		int denne   = 1;
		while (svar < fi) {
			svar    = denne + forrige;
			forrige = denne;
			denne   = svar;
			System.out.println(svar);
		}
	}

	public static void main(String[] args) {
		// OPPGAVE 4A
		System.out.println("OPPGAVE A:");
		int n = 100;
		int s = oppgaveA(n);
		System.out.println("S" + n + " = " + s);

		// OPPGAVE 4B
		System.out.println("\nOPPGAVE B:");
		oppgaveB(11);
		System.out.println(tall);

		// OPPGAVE 4C
		System.out.println("\nOPPGAVE C:");
		int f = 10;
		for (int i = 0; i <= f; i++) {
			System.out.println("f" + i + " = " + oppgaveC(i));
		}

		// OPPGAVE 4D
		System.out.println("\nOPPGAVE D:");
		oppgaveD(1000);
	}
}