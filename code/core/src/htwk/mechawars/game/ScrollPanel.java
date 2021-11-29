package htwk.mechawars.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import htwk.mechawars.board.Robot;
import htwk.mechawars.cards.Card;
import htwk.mechawars.cards.Deck;
import htwk.mechawars.cards.Type;

import static htwk.mechawars.game.GameScreen.zugInitialisierung;

import java.util.ArrayList;

/**
 * Class that creates the ScrollPanel for the GameScreen.
 */
public class ScrollPanel {

    static final int[] cardOrder = { -1, -1, -1, -1, -1};
    private static int pressCounter = 0;
    protected static int damagePoints = 0;
    private static int choosableCardCount = 9;
    static final TextButton[] buttons = new TextButton[choosableCardCount];
    private static Deck deck = new Deck();
    private static ArrayList<Card> lockedList = new ArrayList<>();

    /**
     * Constructor of class ScrollPanel.
     * @param player Object of class Robot.
     * @param skin Object of class Skin.
     * @return scrollPanel.
     */
    public static ScrollPane scrollPanel(Skin skin, Robot player) {
        Table table = new Table();
        final ScrollPane scrollPanel = new ScrollPane(table, skin);
        
        //initialize lockedList
        if (lockedList.size() != 5) {
            lockedList.add(new Card(Type.turn, (byte) 1, 0));
            lockedList.add(new Card(Type.turn, (byte) 1, 0));
            lockedList.add(new Card(Type.turn, (byte) 1, 0));
            lockedList.add(new Card(Type.turn, (byte) 1, 0));
            lockedList.add(new Card(Type.turn, (byte) 1, 0));
        }
        
        // shuffle Deck
        deck.shuffle();
        int cardPrintCounter = 0;

        if (!player.getShutDown() || !player.getDestroyed()) {
                  
            for (; cardPrintCounter < (choosableCardCount - player.getDamagePoints()); 
                    cardPrintCounter += 1) {
                Card currentCard = deck.getDeck().get(cardPrintCounter);
                if (cardPrintCounter < 5) {
                    lockedList.set(cardPrintCounter, currentCard);
                }
                buttons[cardPrintCounter] = new TextButton(currentCard.getCardAttributePriority()
                        + " - " + currentCard, skin);
                table.row();
                table.add(buttons[cardPrintCounter]);
                int buttonNumber = (cardPrintCounter + 1);

                // Button-ClickListener
                buttons[cardPrintCounter].addListener(new ClickListener() {
                    public void clicked(InputEvent event, float x, float y) {
                        if (buttonClickOrder(player, buttonNumber)) {
                            zugInitialisierung.addCard(currentCard);
                        }
                    }
                });
            }
            if (player.getDamagePoints() > 4 && player.getDamagePoints() < 9) {
                int cardNumber = 10 - player.getDamagePoints();
                for (int lockedPrintCounter = 4; lockedPrintCounter < player.getDamagePoints();
                        lockedPrintCounter += 1) {
                    Card currentCard = lockedList.get(8 - lockedPrintCounter);
                    buttons[cardPrintCounter] = 
                            new TextButton(currentCard.getCardAttributePriority()
                            + " - " + currentCard + " | Nr: " + cardNumber, skin);
                    buttons[cardPrintCounter].setColor(Color.RED);
                    zugInitialisierung.addCard(currentCard);
                    table.row();
                    table.add(buttons[cardPrintCounter]);
                    cardNumber += 1;
                }
            }
        }
        return scrollPanel;
    }

    /**
     * If there were less than 5 valid button clicks: paints button green and adds
     *  " | Nr: " with the corresponding Number of at what time it was clicked.
     * @param buttonNumber -> ID-number of clicked button
     */
    protected static boolean buttonClickOrder(Robot player, int buttonNumber) {
        int maxPressed = 5;
        if (player.getDamagePoints() > 4) {
            maxPressed = 9 - player.getDamagePoints();
        }
        if (pressCounter < maxPressed) {
            // write the number of the button in cardOrder at pressCounter
            for (int i = (pressCounter - 1); i >= 0; i -= 1) {
                if (cardOrder[i] == buttonNumber) {
                    return false;
                }
            }

            cardOrder[pressCounter] = buttonNumber;
            pressCounter += 1;

            buttons[buttonNumber - 1].setColor(Color.GREEN);
            buttons[buttonNumber - 1].setText(buttons[buttonNumber - 1].getText()
                    + " | Nr: " + (pressCounter));
            return true;
        }
        return false;
    }

    /**
     * Initialize cardOrder[] to non reachable values.
     */
    protected static void cardOrderClear(Robot player) {
        switch (player.getDamagePoints()) {
            case 5:     cardOrder[0] = -1;
                        cardOrder[1] = -1;
                        cardOrder[2] = -1;
                        cardOrder[3] = -1;
                        break;
                        
            case 6:     cardOrder[0] = -1;
                        cardOrder[1] = -1;
                        cardOrder[2] = -1;
                        break;
                        
            case 7:     cardOrder[0] = -1;
                        cardOrder[1] = -1;
                        break;
                        
            case 8:     cardOrder[0] = -1;
                        break;
                        
            case 9:     break;
            
            case 10:    break;
            
            default:    cardOrder[0] = -1;
                        cardOrder[1] = -1;
                        cardOrder[2] = -1;
                        cardOrder[3] = -1;
                        cardOrder[4] = -1;
                        break; 
                        
        }
        buttonsClean();
        pressCounter = 0;
    }

    /**
     *  Renames every button to " - " and sets the button color to light grey.
     */
    private static void buttonsClean() {
        for (int i = 0; i < choosableCardCount; i += 1) {
            buttons[i].setColor(Color.LIGHT_GRAY);
            buttons[i].setText(deck.getDeck().get(i).getCardAttributePriority() 
                    + " - " + deck.getDeck().get(i));
        }
    }

}
