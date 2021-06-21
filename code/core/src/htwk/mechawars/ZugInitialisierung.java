package htwk.mechawars;

import java.util.LinkedList;

import htwk.mechawars.cards.Card;

/**
 * 
 *
 */
public class ZugInitialisierung {

    private static LinkedList<Card> kartenListe = new LinkedList<Card>();
    private Ausfuehrungslogik ausfuehrungsLogik = new Ausfuehrungslogik();

    public ZugInitialisierung() {

    }

    public void addCard(Card karte) {
// TODO Auto-generated method stub

        kartenListe.add(karte);
    }

    private void uebergebeKarten() {
        ausfuehrungsLogik.setKartenListe(kartenListe);
    }

    public void initialiereBewegung() {
        uebergebeKarten();
        System.out.println(kartenListe.toString());
    }

}
