package htwk.mechawars.cards;

/**
 * Class of a single Card.
 */
public class Card {
 
    private Type cardAttributeType;
    private byte cardAttributeMovCount;
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
    public Card(Type cardAttributeType, byte cardAttributeMovCount) {
        this.cardAttributeType = cardAttributeType;
        this.cardAttributeMovCount = cardAttributeMovCount;
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
}
