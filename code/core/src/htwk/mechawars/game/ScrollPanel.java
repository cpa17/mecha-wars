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

import static htwk.mechawars.game.GameScreen.zugInitialisierung;

/**
 * Class that creates the ScrollPanel for the GameScreen.
 */
public class ScrollPanel {

    static final int[] cardOrder = { -1, -1, -1, -1, -1};
    private static int pressCounter = 0;
    protected static final int damagePoints = 0;
    private static int choosableCardCount = 9;
    static final TextButton[] buttons = new TextButton[choosableCardCount];
    private static Deck deck = new Deck();

    /**
     * Constructor of class ScrollPanel.
     * @param player Object of class Robot.
     * @param skin Object of class Skin.
     * @return scrollPanel.
     */
    public static ScrollPane scrollPanel(Skin skin, Robot player) {
        Table table = new Table();
        final ScrollPane scrollPanel = new ScrollPane(table, skin);

        // shuffle Deck
        deck.shuffle();

        if (!player.getShutDown()) {
            for (int cardPrintCounter = 0; cardPrintCounter < choosableCardCount;
                    cardPrintCounter += 1) {
                Card currentCard = deck.getDeck().get(cardPrintCounter);
                buttons[cardPrintCounter] = new TextButton(currentCard.getCardAttributePriority()
                        + " - " + currentCard, skin);
                table.row();
                table.add(buttons[cardPrintCounter]);
                int buttonNumber = (cardPrintCounter + 1);

                // Button-ClickListener
                buttons[cardPrintCounter].addListener(new ClickListener() {
                    public void clicked(InputEvent event, float x, float y) {
                        if (buttonClickOrder(buttonNumber)) {
                            zugInitialisierung.addCard(currentCard);
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
    protected static boolean buttonClickOrder(int buttonNumber) {
        if (pressCounter < 5 - damagePoints) {
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
    protected static void cardOrderClear() {
        cardOrder[0] = -1;
        cardOrder[1] = -1;
        cardOrder[2] = -1;
        cardOrder[3] = -1;
        cardOrder[4] = -1;
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
