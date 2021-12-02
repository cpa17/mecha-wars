package htwk.mechawars.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import htwk.mechawars.board.Robot;

import static htwk.mechawars.game.GameScreen.board;
import static htwk.mechawars.game.GameScreen.stage;

import static htwk.mechawars.game.GameScreen.addButtonsToStage;
import static htwk.mechawars.game.GameScreen.getStage;
import static htwk.mechawars.game.GameScreen.addScrollPanelToStage;

/**
 * Class that creates the ScrollPanel for the GameScreen.
 */
public class Buttons {


    /**
     * Creates the startButton.
     * @param players Array of all the Players.
     * @return startButton.
     */
    protected static Button startButton(Robot[] players, Button startExecutionButton) {
        startExecutionButton.setSize(160, 43);
        int startExecutionButtonX = Gdx.graphics.getHeight()
                + (Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) / 3 - 64;
        int startExecutionButtonY = Gdx.graphics.getHeight() - 100;
        startExecutionButton.setPosition(startExecutionButtonX, startExecutionButtonY);
        startExecutionButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if (!players[0].getShutDown()) {
                    //If All Cards are chosen
                    if (ScrollPanel.cardOrder[4 - ScrollPanel.damagePoints] != -1) {
                        deactivateButtons();
                        board.move(players);
                        Timer.schedule(new Timer.Task() {
                            @Override
                            public void run() {
                                players[0].resetList();
                                startExecutionButton.setColor(Color.LIGHT_GRAY);
                                ScrollPanel.cardOrderClear();
                                activateButtons();
                            }
                        }, 5);
                    } else {
                        startExecutionButton.setColor(Color.RED);
                    }
                } else {
                    System.out.println(players[0].getShutDown());
                    board.move(players);
                    players[0].resetList();
                    startExecutionButton.setColor(Color.LIGHT_GRAY);
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

                Dialog dialogCloseOption = new Dialog("                 Beenden?", skin) {
                    // many spaces, because then its nearly in the centre

                    @Override
                    protected void result(Object object) {
                        boolean exit = (Boolean) object;
                        if (exit) {
                            Gdx.app.exit();
                        } else {
                            remove();
                        }
                    }

                }.show(stage);

                dialogCloseOption.setSize(450, 110);

                dialogCloseOption.button("Beenden", true);
                dialogCloseOption.button("Abbruch", false);
                dialogCloseOption.key(Input.Keys.ENTER, true);
                dialogCloseOption.key(Input.Keys.ESCAPE, false);                
            }
        });

        return endGameButton;
    }

    /**
     * Creates the removeButton.
     * @return removeButton.
     */
    protected static Button removeButton(Button removeCardOrder) {
        removeCardOrder.setSize(160, 43);
        int removeCardOrderX = Gdx.graphics.getHeight()
                + (Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) / 3 - 64;
        int removeCardOrderY = Gdx.graphics.getHeight() - 200;

        removeCardOrder.setPosition(removeCardOrderX, removeCardOrderY);

        removeCardOrder.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                ScrollPanel.cardOrderClear();
                Robot.getPlayers()[0].resetList();
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
        GameScreen.removeCardOrder.setTouchable(Touchable.disabled);
        GameScreen.startExecutionButton.setTouchable(Touchable.disabled);
        GameScreen.wakeUpButton.setTouchable(Touchable.disabled);
        GameScreen.shutDownButton.setTouchable(Touchable.disabled);
    }

    /**
     * Activate the buttons.
     */
    protected static void activateButtons() {
        for (TextButton button : ScrollPanel.buttons) {
            button.setTouchable(Touchable.enabled);
        }
        GameScreen.removeCardOrder.setTouchable(Touchable.enabled);
        GameScreen.startExecutionButton.setTouchable(Touchable.enabled);
        GameScreen.wakeUpButton.setTouchable(Touchable.enabled);
        GameScreen.shutDownButton.setTouchable(Touchable.enabled);
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
     * @return wakeUpButton.
     */
    protected static Button wakeUpButton(Robot player, Button wakeUpButton) {
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
     * @return shutDownButton.
     */
    protected static Button shutDownButton(Robot player, Button shutDownButton) {
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
        getStage().clear();
        addButtonsToStage(skin);
        addScrollPanelToStage(skin);
    }
    

    

    
}
