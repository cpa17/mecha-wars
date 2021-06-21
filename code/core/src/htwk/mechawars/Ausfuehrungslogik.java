package htwk.mechawars;

import java.util.LinkedList;

import htwk.mechawars.cards.Card;

/**
 * Class that presents the surface of the game screen.
 */
public class Ausfuehrungslogik {
    
    private static LinkedList<Card>  kartenListe;

    
    /**
     * returns the list of cards.
     * 
     * 
     * 
     * 
     * 
     * @return The initial cardDeck
     */
    public static LinkedList<Card> getKartenListe() {
        return kartenListe;
    }

    /**
     * setter for list of cards field.
     * 
     * 
     * @param kartenListe  -> the list of cards that will be set to be the new list
     */

    public void setKartenListe(LinkedList<Card> kartenListe) {
        Ausfuehrungslogik.kartenListe = kartenListe;
    }



}
