package htwk.mechawars;

import java.util.LinkedList;

import htwk.mechawars.cards.Card;

public class Ausf�hrungslogik {
    
    private static LinkedList<Card>  kartenListe;

    public static LinkedList<Card> getKartenListe() {
        return kartenListe;
    }

    public void setKartenListe(LinkedList<Card> kartenListe) {
        Ausf�hrungslogik.kartenListe = kartenListe;
    }



}
