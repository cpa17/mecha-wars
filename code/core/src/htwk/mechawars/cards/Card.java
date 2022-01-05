package htwk.mechawars.cards;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Class of a single Card.
 */
public class Card {

    private Type cardAttributeType;
    private byte cardAttributeMovCount;
    private int cardPlayerNumber;
    
    public void setCardPlayerNumber(int cardPlayerNumber) {
        this.cardPlayerNumber = cardPlayerNumber;
    }

    /*
     * amount of moves forward or amount of turns
     * -1:              backwards           -----
     *  1:              1                   right
     *  2:              2                   u-turn
     *  3:              3                   left
     */
    private int cardAttributePriority;

    /**
     * Constructor of a Card.
     */
    public Card(Type cardAttributeType, byte cardAttributeMovCount,
            int cardAttributePriority, int cardPlayerNumber) {
        this.cardAttributeType = cardAttributeType;
        this.cardAttributeMovCount = cardAttributeMovCount;
        this.cardAttributePriority = cardAttributePriority;
        this.cardPlayerNumber = cardPlayerNumber;
    }
    
    /**
     * Constructor of a Card for testing.
     */
    public Card(Type cardAttributeType, byte cardAttributeMovCount,
            int cardAttributePriority) {
        this.cardAttributeType = cardAttributeType;
        this.cardAttributeMovCount = cardAttributeMovCount;
        this.cardAttributePriority = cardAttributePriority;
        this.cardPlayerNumber = 0;
    }

    // getter functions --------------------------------------------------------

    /**
     * Take the content of the enumeration Type.
     * @return the enumeration-type
     */
    public Type getCardAttributeType() {
        return cardAttributeType;
    }

    /**
     * Take the content of movCount.
     * @return the amount of movCount (as a byte (8Bit Datatype))
     */
    public byte getCardAttributeMovCount() {
        return cardAttributeMovCount;
    }

    /**
     * Take the Priority of the Card.
     * @return the priority
     */
    public int getCardAttributePriority() {
        return cardAttributePriority;
    }

    public int getCardPlayerNumber() {
        return cardPlayerNumber;
    }

    /**
     * To print the correct Code.
     * @return Returns the Name of the Card
     */
    public String toString() {
        String xyz;

        if (this.cardAttributeType == Type.mov) {
            switch (this.cardAttributeMovCount) {

                case -1 :   xyz = "Rueckwaerts";
                    break;

                case 1 :    xyz = "1 Vor";
                    break;

                case 2 :    xyz = "2 Vor";
                    break;

                case 3 :    xyz = "3 Vor";
                    break;

                default:    xyz = "Fehler";
                    break;
            }
        } else {
            switch (this.cardAttributeMovCount) {

                case 1 :    xyz = "Rechtsdrehung";
                    break;

                case 2 :    xyz = "Kehrtwendung";
                    break;

                case 3 :    xyz = "Linksdrehung";
                    break;

                default:    xyz = "Fehler";
                    break;

            }
        }

        return xyz;
    }
    
    /** Function that sorts and outputs the input list by priority. 
     * @param list list of cards
     * @return the input list of cards, sorted by priority
     */
    public static LinkedList<Card> sortByPriority(LinkedList<Card> list) {
        Collections.sort(list, Comparator.comparing(Card::getCardAttributePriority));
        Collections.reverse(list);
        return list;
    }
    
    



}