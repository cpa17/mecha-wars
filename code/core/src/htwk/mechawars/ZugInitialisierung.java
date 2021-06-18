package htwk.mechawars;

import java.util.LinkedList;

import htwk.mechawars.cards.Card;

public class ZugInitialisierung {

    private static LinkedList<Card> kartenListe = new LinkedList<Card>();
    Ausführungslogik ausführungsLogik = new Ausführungslogik();

    public ZugInitialisierung() {

    }

    public void addCard(Card Karte) {
// TODO Auto-generated method stub

        kartenListe.add(Karte);
    }

    private void uebergebeKarten() {
        ausführungsLogik.setKartenListe(kartenListe);
    }

    public void initialiereBewegung() {
        uebergebeKarten();
        System.out.println(kartenListe.toString());
    }

}
