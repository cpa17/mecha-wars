package htwk.mechawars.cards;

public class Main {

    public static void main(String[] args) {
        Card[] cardDeck = new Card[84];
        cardDeck = CardFunctions.initDeck(cardDeck);
        cardDeck = CardFunctions.shuffle(cardDeck);
        
        // prints the complete cardDeck in the console for control
        for (int i = 0; i < 84; i+=1) {
            System.out.print(cardDeck[i].cardAttributeName.get_Name());
            System.out.print(" - " + cardDeck[i].cardAttributeType);
            System.out.println(" - " + cardDeck[i].cardAttributeMovCount);
        }
    }
}
