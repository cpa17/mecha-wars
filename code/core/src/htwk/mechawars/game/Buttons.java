package htwk.mechawars.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import htwk.mechawars.board.Robot;

/**
 * Class that creates the ScrollPanel for the GameScreen.
 */
public class Buttons extends GameScreen {

    public Buttons(Game g) {
        super(g);
        // TODO Auto-generated constructor stub
    }

    /**
     * Creates the startButton.
     * @param player Object of class Robot.
     * @param skin Object of class Skin.
     * @return startButton.
     */
    protected static Button startButton(Skin skin, Robot player) {
        Button startExecutionButton = new TextButton("Ausfuehrung starten", skin);
        startExecutionButton.setSize(160, 43);

        int startExecutionButtonX = Gdx.graphics.getHeight()
                + (Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) / 3 - 64;
        int startExecutionButtonY = Gdx.graphics.getHeight() - 100;

        startExecutionButton.setPosition(startExecutionButtonX, startExecutionButtonY);

        startExecutionButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if (!player.getShutDown()) {
                    //If All Cards are chosen
                    if (ScrollPanel.cardOrder[4 - ScrollPanel.damagePoints] != -1) {
                        deactivateButtons();
                        zugInitialisierung.initialisiereBewegung();
                        board.move(zugInitialisierung.getList(), player);
                        zugInitialisierung.resetList();
                        startExecutionButton.setColor(Color.LIGHT_GRAY);
                        ScrollPanel.cardOrderClear();
                        activateButtons();
                        updateButtons(skin);
                    } else {
                        startExecutionButton.setColor(Color.RED);
                    }
                } else {
                    zugInitialisierung.initialisiereBewegung();
                    board.move(zugInitialisierung.getList(), player);
                    zugInitialisierung.resetList();
                    startExecutionButton.setColor(Color.LIGHT_GRAY);
                    updateButtons(skin);
                }
            }
        });

        return startExecutionButton;
    }

    /**
     * Creates the endButton.
     * @param skin Object of class Skin.
     * @return endButton.
     */
    protected static Button endButton(Skin skin) {
        Button endGameButton = new TextButton("Spiel beenden", skin);

        endGameButton.setSize(160, 43);

        int endGameButtonX = Gdx.graphics.getHeight()
                + (((Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) * 2) / 3) - 64;
        int endGameButtonY = Gdx.graphics.getHeight() - 100;

        endGameButton.setPosition(endGameButtonX, endGameButtonY);

        endGameButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        return endGameButton;
    }

    /**
     * Creates the removeButton.
     * @param skin Object of class Skin.
     * @return removeButton.
     */
    protected static Button removeButton(Skin skin) {
        Button removeCardOrder = new TextButton("Loesche\nKartenreihenfolge", skin);

        removeCardOrder.setSize(128, 43);
        int removeCardOrderX = Gdx.graphics.getHeight()
                + (Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) / 3 - 64;
        int removeCardOrderY = Gdx.graphics.getHeight() - 200;

        removeCardOrder.setPosition(removeCardOrderX, removeCardOrderY);

        removeCardOrder.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                ScrollPanel.cardOrderClear();
                zugInitialisierung.resetList();
            }

        });

        return removeCardOrder;
    }

    /**
     * Deactivate the buttons.
     */
    protected static void deactivateButtons() {
        for (TextButton button : ScrollPanel.buttons) {
            button.setTouchable(Touchable.disabled);
        }
    }

    /**
     * Activate the buttons.
     */
    protected static void activateButtons() {
        for (TextButton button : ScrollPanel.buttons) {
            button.setTouchable(Touchable.enabled);
        }
    }

    /**
     * Creates the infoButton.
     * @param skin Object of class Skin.
     * @return infoButton.
     */
    protected static Button infoButton(Skin skin) {
        // add Button for hint and infos
        Button buttonInfo = new TextButton("Infos", skin);

        int a = 60;     // width
        int b = 40;     // height

        buttonInfo.setSize(a, b);
        int buttonInfoX = Gdx.graphics.getWidth() - (a + 10);
        int buttonInfoY = Gdx.graphics.getHeight() - (b + 10);

        buttonInfo.setPosition(buttonInfoX, buttonInfoY);

        buttonInfo.addListener(Info.infoListener(skin));

        return buttonInfo;
    }

    /**
     * Creates the wakeUpButton.
     * @param player Object of class Robot.
     * @param skin Object of class Skin.
     * @return wakeUpButton.
     */
    protected static Button wakeUpButton(Skin skin, Robot player) {
        Button wakeUpButton = new TextButton("WakeUp", skin);

        wakeUpButton.setSize(160, 43);

        int wakeUpButtonX = Gdx.graphics.getHeight()
                + (Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) / 3 - 64;
        int wakeUpButtonY = Gdx.graphics.getHeight() - 500;

        wakeUpButton.setPosition(wakeUpButtonX, wakeUpButtonY);

        wakeUpButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if (player.getNextRound()) {
                    player.setNextRound(false);
                    wakeUpButton.setColor(Color.GREEN);
                    System.out.println("wake up");
                } else {
                    player.setNextRound(true);
                    wakeUpButton.setColor(Color.LIGHT_GRAY);
                    System.out.println("off");
                }
            }
        });

        return wakeUpButton;
    }

    /**
     * Creates the shutDownButton.
     * @param player Object of class Robot.
     * @param skin Object of class Skin.
     * @return shutDownButton.
     */
    protected static Button shutDownButton(Skin skin, Robot player) {
        Button shutDownButton = new TextButton("ShutDown", skin);

        shutDownButton.setSize(160, 43);

        int shutDownButtonX = Gdx.graphics.getHeight()
                + (Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) / 3 - 64;
        int shutDownButtonY = Gdx.graphics.getHeight() - 400;

        shutDownButton.setPosition(shutDownButtonX, shutDownButtonY);

        shutDownButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if (!player.getNextRound()) {
                    player.setNextRound(true);
                    shutDownButton.setColor(Color.GREEN);
                    System.out.println("shutdown");
                } else {
                    player.setNextRound(false);
                    shutDownButton.setColor(Color.LIGHT_GRAY);
                    System.out.println("awake");
                }
            }
        });

        return shutDownButton;
    }

    /**
     * Update the Buttons and scroll panel.
     * @param skin Object of class Skin.
     */
    private static void updateButtons(Skin skin) {
        stage.clear();
        addButtonsToStage(skin);
        addScrollPanelToStage(skin);
    }
}
