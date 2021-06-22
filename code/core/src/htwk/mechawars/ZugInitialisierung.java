package htwk.mechawars;

import java.util.LinkedList;

import htwk.mechawars.cards.Card;


/**
 * initializes the execution of the turn.
 * 
 *
 */
public class ZugInitialisierung {

    private LinkedList<Card> kartenListe = new LinkedList<Card>();
    private Ausfuehrungslogik ausfuehrungsLogik = new Ausfuehrungslogik();

    /**
     * Constructor for ZugInitialisierung.
     */
    public ZugInitialisierung() {

    }

    /**
     * adds the given Card to the list of Cards.
     * 
     * @param karte -> card to be added to the list
     */
    public void addCard(Card karte) {

        kartenListe.add(karte);
    }

    /**calls the setKartenListe- function in Ausfuehrungslogik 
     * with the the list of cards as parameter.
     * 
     */
    private void uebergebeKarten() {
        ausfuehrungsLogik.setKartenListe(kartenListe);
    }

    /**initializes the Movement of the Player.
     * 
     */
    public void initialisiereBewegung() {
        uebergebeKarten();
        System.out.println(kartenListe.toString());
    }

	public void resetList() {
		kartenListe = new LinkedList<Card>();
		
	}

}
