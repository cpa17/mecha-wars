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
        if (checkCard(karte) == true) {
            kartenListe.add(karte);
        }
    }

    /**
     * Method that checks whether a card can be added.
     * @param card to check
     * @return true: if the card can be added
     *         false: if the card can't be added
     */
    public boolean checkCard(Card card) {
        boolean cardCheck = true;
        for (int i = 0; i < kartenListe.size(); i++) {
            if (kartenListe.get(i).getCardAttributePriority() == card.getCardAttributePriority()) {
                cardCheck = false;
            }
        }
        if (kartenListe.size() >= 5) {
            cardCheck = false;
        }
        return cardCheck;
    }

    /**
     * calls the setKartenListe- function in Ausfuehrungslogik
     * with the the list of cards as parameter.
     */
    private void uebergebeKarten() {
        ausfuehrungsLogik.setKartenListe(kartenListe);
    }

    /**
     * initializes the Movement of the Player.
     */
    public void initialisiereBewegung() {
        uebergebeKarten();
        System.out.println(kartenListe.toString());
    }

    /**
     * resets the List of Cards.
     */
    public void resetList() {
        kartenListe = new LinkedList<Card>();
    }

    public LinkedList<Card> getList() {
        return ausfuehrungsLogik.getKartenListe();
    }
}
