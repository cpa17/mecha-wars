package htwk.mechawars.cards;

public class Card {
 
    protected Type cardAttributeType;
    protected Name cardAttributeName;
    protected byte cardAttributeMovCount;
    /*
     * amount of moves forward or amount of turns
     * -1:              backwards           -----
     *  1:              1                   right
     *  2:              2                   u-turn
     *  3:              3                   left
     */

    /**
     * Constructor of a Card.
     */
    public Card(Name cardAttributeName, Type cardAttributeType, byte cardAttributeMovCount) {
        super();
        this.cardAttributeType = cardAttributeType;
        this.cardAttributeName = cardAttributeName;
        this.cardAttributeMovCount = cardAttributeMovCount;
    }
 
    // getter functions --------------------------------------------------------
 
    /**
     * Take the content of the enumeration Name.
     * @return the enumeration-name
     */
    public Name getCardAttributeName() {
        return cardAttributeName;
    }
 
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
}
