package htwk.mechawars.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

import htwk.mechawars.ZugInitialisierung;
import htwk.mechawars.board.Board;
import htwk.mechawars.board.Dir;
import htwk.mechawars.board.Robot;
import htwk.mechawars.cards.Card;
import htwk.mechawars.cards.CardFunctions;
import htwk.mechawars.fields.Checkpoint;
import htwk.mechawars.fields.Field;
import htwk.mechawars.fields.RepairSite;

/**
 * Class that presents the surface of the game screen.
 */
public class GameScreen implements Screen {
    
    private Field robotPosition;
    private Texture industrialTile;
    private Texture robot;
    protected static Stage stage;
    private SpriteBatch batch;
    private Sprite sprite;
    protected static ZugInitialisierung zugInitialisierung = new ZugInitialisierung();
    private static Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

    protected static int[] cardOrder = { -1, -1, -1, -1, -1};
    private static int pressCounter = 0;
    protected static int damagePoints = 0;
    private static int choosableCardCount = 9;

    private static Card[] deck;

    protected static Board board = new Board("map.txt");
    private static Robot player = new Robot();

    protected static TextButton[] buttons = new TextButton[choosableCardCount];

    /**
     * Constructor of class GameScreen.
     */
    public GameScreen() {
        industrialTile = new Texture("mapAssets/StandardField.png");
        
        robot = new Texture("robot.png");

        batch = new SpriteBatch();
        sprite = new Sprite(robot);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        addButtonsToStage(skin);
        addScrollPanelToStage(skin);
        board.startRobot(5, 5, Dir.NORTH, player);
    }

    /**
     * Function that adds the scroll panel to the Stage.
     *
     * @param skin Object of class Skin which was initialized in the constructor.
     */
    public static void addScrollPanelToStage(Skin skin) {
        int containerBoundsX = (Gdx.graphics.getWidth()
                - ((Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) / 2)) + 10;
        int containerBoundsY = 10;
        int containerWidth = ((Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) / 2) - 20;
        int containerHeight = 600;

        Table container = new Table();
        stage.addActor(container);
        container.setBounds(containerBoundsX, containerBoundsY, containerWidth, containerHeight);

        Table table = new Table();

        final ScrollPane scrollPanel = new ScrollPane(table, skin);

        // Array of Cards created
        deck = CardFunctions.initDeck();
        // shuffle Deck
        deck = CardFunctions.shuffle(deck);

        if (!player.getShutDown()) {
            for (int cardPrintCounter = 0; cardPrintCounter < choosableCardCount;
                    cardPrintCounter += 1) {
                Card currentCard = deck[cardPrintCounter];
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

        container.add(scrollPanel).grow();
    }

    /**
     * If there were less than 5 valid button clicks: paints button green and adds
     *  " | Nr: " with the corresponding Number of at what time it was clicked.
     * @param buttonNumber -> ID-number of clicked button
     */
    private static boolean buttonClickOrder(int buttonNumber) {
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
            buttons[i].setText(deck[i].getCardAttributePriority() + " - "
                    + deck[i]);
        }
    }

    protected static void updateButtons() {
        stage.clear();
        addButtonsToStage(skin);
        addScrollPanelToStage(skin);
    }

    /**
     * Function that adds the buttons to the Stage.
     *
     * @param skin Object of class Skin which was initialized in the constructor.
     */
    public static void addButtonsToStage(Skin skin) {
        stage.addActor(Buttons.startButton(skin, player));
        stage.addActor(Buttons.endButton(skin));

        stage.addActor(Buttons.removeButton(skin));

        if (player.getShutDown()) {
            Buttons.removeButton(skin).setTouchable(Touchable.disabled);
            Buttons.removeButton(skin).setDisabled(true);
        } else {
            Buttons.removeButton(skin).setTouchable(Touchable.enabled);
            Buttons.removeButton(skin).setDisabled(false);
        }

        stage.addActor(Buttons.infoButton(skin));

        if (player.getShutDown()) {
            Buttons.shutDownButton(skin, player).setTouchable(Touchable.disabled);
            Buttons.wakeUpButton(skin, player).setTouchable(Touchable.enabled);
            Buttons.shutDownButton(skin, player).setDisabled(true);
            Buttons.wakeUpButton(skin, player).setDisabled(false);
        } else {
            Buttons.shutDownButton(skin, player).setTouchable(Touchable.enabled);
            Buttons.wakeUpButton(skin, player).setTouchable(Touchable.disabled);
            Buttons.shutDownButton(skin, player).setDisabled(false);
            Buttons.wakeUpButton(skin, player).setDisabled(true);
        }

        stage.addActor(Buttons.shutDownButton(skin, player));
        stage.addActor(Buttons.wakeUpButton(skin, player));

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.8f, 0.8f, 0.8f, 1);
        batch.begin();
        Board.toAsset(batch, board);
        drawRobot();
        player.drawParameters(batch);
        sprite.draw(batch);
        batch.end();
        robotPosition = board.fieldmatrix[player.getXcoor()][player.getYcoor()];
        robotPosition.action(player);
        stage.act();
        stage.draw();
    }

    /**
     * Function that draws the robot on the playing field.
     */
    public void drawRobot() {
        int tileSize = (Gdx.graphics.getHeight() / board.matrix.length);
        int x = player.getXcoor();
        int y = Math.abs(player.getYcoor() - (board.matrix.length - 1));

        if (player.getDir() == Dir.NORTH) {
            sprite.setPosition(tileSize * x, tileSize * y);
            sprite.setRotation(0);
        } else if (player.getDir() == Dir.EAST) {
            sprite.setPosition(tileSize * x, tileSize * y);
            sprite.setRotation(270);
        } else if (player.getDir() == Dir.SOUTH) {
            sprite.setPosition(tileSize * x, tileSize * y);
            sprite.setRotation(180);
        } else if (player.getDir() == Dir.WEST) {
            sprite.setPosition(tileSize * x, tileSize * y);
            sprite.setRotation(90);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        industrialTile.dispose();
        robot.dispose();
    }

    @Override
    public void hide() {

    }

}
