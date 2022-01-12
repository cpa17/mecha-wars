package htwk.mechawars.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import htwk.mechawars.board.Robot;
import htwk.mechawars.cards.Card;
import htwk.mechawars.cards.Deck;


/**
 * Class that creates the ScrollPanel for the GameScreen.
 */
public class ScrollPanel {

    private static Card[] cardOrder = {null, null, null, null, null};
    private static int pressCounter = 0;
    protected static final int damagePoints = 0;
    static final TextButton[] buttons = new TextButton[9];
    private static Deck deck = new Deck();
    private static boolean round1 = true;

    private static Table table = new Table();
    private static final ScrollPane scrollPanel = new ScrollPane(table, GameScreen.skin);

    /**
     * Constructor of class ScrollPanel.
     * @param skin Object of class Skin.
     * @return scrollPanel.
     */
    public static ScrollPane scrollPanel(Skin skin) {
        // shuffle Deck
        deck.shuffle();

        Robot.getPlayers()[0].damageUp();
        Robot.getPlayers()[0].damageUp();
        Robot.getPlayers()[0].damageUp();
        if (10 > Robot.getPlayers()[0].getDamagePoints() 
                && Robot.getPlayers()[0].getDamagePoints() > 4) {
            System.out.println("Red");
            
            for (int i = 5; i > (4 - (Robot.getPlayers()[0].getDamagePoints() - 5)); i -= 1) {
                
                System.out.print(cardOrder[0].getCardAttributePriority());
                System.out.print(cardOrder[1].getCardAttributePriority());
                System.out.print(cardOrder[2].getCardAttributePriority());
                System.out.print(cardOrder[3].getCardAttributePriority());
                System.out.println(cardOrder[4].getCardAttributePriority());
                
                Card currentCard = cardOrder[i-1];
                
                System.out.println(currentCard.getCardAttributePriority());
                
                buttons[i - 1] = new TextButton(currentCard.getCardAttributePriority()
                    + " - " + currentCard + " " + (i), skin);
                buttons[i - 1].setColor(Color.RED);
                buttons[i - 1].setTouchable(Touchable.disabled);
                table.row();
                table.add(buttons[i - 1]);
            }
            
            for (int j = 0; j < (9 - Robot.getPlayers()[0].getDamagePoints()); j += 1) {
                
                Card currentCard = deck.getDeck().get(j);
                
                buttons[j] = new TextButton(currentCard.getCardAttributePriority()
                        + " - " + currentCard, skin);
                table.row();
                table.add(buttons[j]);
                
                int buttonNumber = j + 1;
                
                // Button-ClickListener
                buttons[j].addListener(new ClickListener() {
                        public void clicked(InputEvent event, float x, float y) {
                            pressCounter += 1;
                            if (buttonClickOrder(buttonNumber, currentCard)) {
                                Robot.getPlayers()[0].addCard(currentCard, 0);
                                deck.getDeck().remove(currentCard);
                            }
                        }
                });
                
            }
            
        } else if (!Robot.getPlayers()[0].getDestroyed()) {
            
            for (int i = 0; i < (9 - Robot.getPlayers()[0].getDamagePoints()); i += 1) {

                Card currentCard = deck.getDeck().get(i);
                
                buttons[i] = new TextButton(currentCard.getCardAttributePriority()
                        + " - " + currentCard, skin);
                table.row();
                table.add(buttons[i]);
                
                int buttonNumber = i + 1;
                
                // Button-ClickListener
                buttons[i].addListener(new ClickListener() {
                        public void clicked(InputEvent event, float x, float y) {
                            pressCounter += 1;
                            if (buttonClickOrder(buttonNumber, currentCard)) {
                                Robot.getPlayers()[0].addCard(currentCard, 0);
                                deck.getDeck().remove(currentCard);
                            }
                        }
                });
            }
            
        }
        return scrollPanel;
    }

    /**
     * If there were less than 5 valid button clicks: paints button green and adds
     *  " | Nr: " with the corresponding Number of at what time it was clicked.
     * @param buttonNumber -> ID-number of clicked button
     */
    protected static boolean buttonClickOrder(int buttonNumber, Card card) {
        if(pressCounter < 6) {
            if(card == cardOrder[0] || card == cardOrder[1] || card == cardOrder[2]
                    || card == cardOrder[3] || card == cardOrder[4]) {
                pressCounter -= 1;
                return false;
            } else {
                buttons[buttonNumber - 1].setText(card.getCardAttributePriority()
                        + " - " + card + " " + (buttonNumber));
                buttons[buttonNumber - 1].setColor(Color.GREEN);
                cardOrder[pressCounter - 1] = card;
                return true;
            }
        } else {
              return false;
        }
    }

    /**
     * Initialize cardOrder[] to non reachable values.
     */
    protected static void cardOrderClear() {
        
        if (Robot.getPlayers()[0].getDamagePoints() < 5) {
            cardOrder[0] = null;
            cardOrder[1] = null;
            cardOrder[2] = null;
            cardOrder[3] = null;
            cardOrder[4] = null;
            buttonsClean();
            pressCounter = 0;
        } else {
            for (int i = 0; i < 9 - Robot.getPlayers()[0].getDamagePoints(); i += 1) {
                cardOrder[i] = null;
            }
            pressCounter = Robot.getPlayers()[0].getDamagePoints() - 4;
        }
        
    }

    /**
     *  Renames every button to " - " and sets the button color to light grey.
     */
    private static void buttonsClean() {
        for (int i = 0; i < 9; i += 1) {
            if(buttons[i] != null) {
                buttons[i].setColor(Color.LIGHT_GRAY);
                buttons[i].setText(deck.getDeck().get(i).getCardAttributePriority() 
                        + " - " + deck.getDeck().get(i));
            }
        }
    }

    static void clearScrollPanel(Skin skin) {
        table.clear();
        if (!Robot.getPlayers()[0].getShutDown()) {
            scrollPanel(skin);
        }
    }
    
    public static Deck getDeck() {
        return deck;
    }

    public static void setDeck(Deck deck) {
        ScrollPanel.deck = deck;
    }
    
    public static boolean allChosen() {
//        if(cardOrder[0] != null && cardOrder[1] != null && cardOrder[2] != null 
//                && cardOrder[3] != null && cardOrder[4] != null 
//                || cardOrder[0] == null && cardOrder[1] != null
//                || cardOrder[1] == null && cardOrder[2] != null
//                || cardOrder[2] == null && cardOrder[3] != null
//                || cardOrder[3] == null && cardOrder[4] != null) {
        if (pressCounter == 5) {
            return true;
        } else {
            return false;
        }
    }

}
