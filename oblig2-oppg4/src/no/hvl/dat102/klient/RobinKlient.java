package no.hvl.dat102.klient;

import no.hvl.dat102.kjedet.DobbelKjedetOrdnetListe;

public class RobinKlient {
    public static void main(String[] args) {
        DobbelKjedetOrdnetListe<Integer> liste = new DobbelKjedetOrdnetListe<>(69, 420);

        for (int i = 70; i < 420; i++) {
            liste.leggTil(i);
        }

        System.out.println(liste.finnes(421));
        liste.visListe();

    }
}
