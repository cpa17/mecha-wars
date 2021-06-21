package htwk.mechawars;

import java.util.LinkedList;

import htwk.mechawars.cards.Card;

/**
 * @author Nutzer
 *
 */
public class Ausfuehrungslogik {
    
    private static LinkedList<Card>  kartenListe;

    
    /**
     * @return
     */
    public static LinkedList<Card> getKartenListe() {
        return kartenListe;
    }

    /**
     * @param kartenListe
     */

    public void setKartenListe(LinkedList<Card> kartenListe) {
        Ausfuehrungslogik.kartenListe = kartenListe;
    }



}
