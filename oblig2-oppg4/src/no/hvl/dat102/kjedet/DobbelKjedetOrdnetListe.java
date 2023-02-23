package no.hvl.dat102.kjedet;

import no.hvl.dat102.adt.DobbelKjedetOrdnetListeADT;
import no.hvl.dat102.exceptions.EmptyCollectionException;

public class DobbelKjedetOrdnetListe<T extends Comparable<T>> implements DobbelKjedetOrdnetListeADT<T> {
    private final DobbelNode<T> foerste;
    private final DobbelNode<T> siste;
    private int antall;

    public DobbelKjedetOrdnetListe(T minVerdi, T maksVerdi) {
        this.foerste = new DobbelNode<>(minVerdi);
        this.siste = new DobbelNode<>(maksVerdi);
        this.foerste.setNeste(siste);
        this.siste.setForrige(foerste);
        this.antall = 0;
    }

    @Override
    public void leggTil(T el) {
        DobbelNode<T> nyNode = new DobbelNode<>(el);
        DobbelNode<T> aktuell = foerste.getNeste();
        while ((el.compareTo(aktuell.getElement()) > 0)) {
            aktuell = aktuell.getNeste();
        }
        // Legg inn foran aktuell
        nyNode.setNeste(aktuell);
        nyNode.setForrige(aktuell.getForrige());
        aktuell.getForrige().setNeste(nyNode);
        aktuell.setForrige(nyNode);
        antall++;

    }

    public void visListe() {

        DobbelNode<T> node = this.foerste.getNeste();
        int pos = 0;
        while (node != null && pos < antall) {
			System.out.println(node.getElement());
            node = node.getNeste();
            pos++;
        }
    }

    @Override
    public T fjern(T el) {
        T resultat = null;
        if (erTom())
            throw new EmptyCollectionException("dobbelkjedet ordnet liste er tom");
        DobbelNode<T> aktuell = finn(el);
        if (aktuell != null) {// returner og slett
            resultat = aktuell.getElement();
            aktuell.getForrige().setNeste(aktuell.getNeste());
            aktuell.getNeste().setForrige(aktuell.getForrige());

        }

        return resultat;

    }

    /*
     * Returnerer referansen til noden hvis el fins, ellers returneres
     * null-referansen
     */
    private DobbelNode<T> finn(T el) {
        boolean funnet = false;
        DobbelNode<T> node = this.foerste.getNeste();
        while (!funnet && node != null) {
            if (node.getElement()
                    .equals(el)) {
                funnet = true;
            } else {
                node = node.getNeste();
            }
        }
        return funnet ? node : null;
    }

    public boolean finnes(T el) {
        boolean funnet = false;
        DobbelNode<T> node = this.foerste.getNeste();
        while (!funnet && node != null) {
            if (node.getElement()
                    .equals(el)) {
                funnet = true;
            } else {
                node = node.getNeste();
            }
        }
        return funnet;
    }

    @Override
    public boolean erTom() {
        return (antall == 0);
    }

    @Override
    public int antall() {
        return antall;
    }

    public String toString() {
        String resultat = "";
        DobbelNode<T> aktuell = foerste.getNeste();
        while (aktuell != siste) {

            resultat = resultat + aktuell.getElement().toString();
            aktuell = aktuell.getNeste();
        }

        return resultat;
    }

    public String tilStrengBaklengs() {
        String resultat = "";
        DobbelNode<T> aktuell = siste.getForrige();
        while (aktuell != foerste) {

            resultat = resultat + aktuell.getElement().toString();
            aktuell = aktuell.getForrige();
        }

        return resultat;

    }

}
