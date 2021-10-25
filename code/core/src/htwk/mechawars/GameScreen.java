package htwk.mechawars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import htwk.mechawars.board.Board;
import htwk.mechawars.board.Dir;
import htwk.mechawars.board.Robot;
import htwk.mechawars.cards.Card;
import htwk.mechawars.cards.CardFunctions;

/**
 * Class that presents the surface of the game screen.
 */
public class GameScreen implements Screen {

    private Texture industrialTile;
    private Texture robot;
    private Stage stage;
    private Table container;
    private SpriteBatch batch;
    private Sprite sprite;
    private ZugInitialisierung zugInititalisierung = new ZugInitialisierung();

    private int[] cardOrder = { -1, -1, -1, -1, -1};
    private int pressCounter = 0;
    private int damagePoints = 0;
    private int choosableCardCount = 9;

    private Card[] deck;

    //zum Ausgeben der bisherigen, "normalen" Spielfelds map mit mapStd ersetzen
    private Board board = Board.fromFile("map.txt");
    private Robot player = new Robot();

    private TextButton[] buttons = new TextButton[choosableCardCount];

    /**
     * Constructor of class GameScreen.
     */
    public GameScreen() {
        industrialTile = new Texture("mapAssets/0.png");
        
        robot = new Texture("robot.png");
        
        batch = new SpriteBatch();
        sprite = new Sprite(robot);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        addButtonsToStage(skin);
        addScrollPanelToStage(skin);
        board.startRobot(5, 5, Dir.NORTH, player);
    }

    /**
     * Function that adds the scroll panel to the Stage.
     *
     * @param skin Object of class Skin which was initialized in the constructor.
     */
    public void addScrollPanelToStage(Skin skin) {
        int containerBoundsX = (Gdx.graphics.getWidth()
                - ((Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) / 2)) + 10;
        int containerBoundsY = 10;
        int containerWidth = ((Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) / 2) - 20;
        int containerHeight = 600;

        container = new Table();
        stage.addActor(container);
        container.setBounds(containerBoundsX, containerBoundsY, containerWidth, containerHeight);

        Table table = new Table();

        final ScrollPane scrollPanel = new ScrollPane(table, skin);

        // Array of Cards created
        deck = CardFunctions.initDeck();
        // shuffle Deck
        deck = CardFunctions.shuffle(deck);

        for (int cardPrintCounter = 0; cardPrintCounter < choosableCardCount;
                cardPrintCounter += 1) {
            Card aktuelleKarte = deck[cardPrintCounter];
            buttons[cardPrintCounter] = new TextButton(aktuelleKarte.getCardAttributePriority()
                    + " - " + aktuelleKarte, skin);
            table.row();
            table.add(buttons[cardPrintCounter]);
            int buttonNumber = (cardPrintCounter + 1);

            // Button-ClickListener
            buttons[cardPrintCounter].addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    buttonClickOrder(buttonNumber);
                    zugInititalisierung.addCard(aktuelleKarte);
                }
            });
        }

        container.add(scrollPanel).grow();
    }

    /**
     * If there were less than 5 valid button clicks: paints button green and adds
     *  " | Nr: " with the corresponding Number of at what time it was clicked.
     * @param buttonNumber -> ID-number of clicked button
     */
    private void buttonClickOrder(int buttonNumber) {
        if (pressCounter < 5 - damagePoints) {
            // write the number of the button in cardOrder at pressCounter
            cardOrder[pressCounter] = buttonNumber;

            pressCounter += 1;

            boolean clicked = true;

            for (int i = (pressCounter - 2); i >= 0; i -= 1) {
                if (cardOrder[i] == buttonNumber) {
                    clicked = false;
                    pressCounter -= 1;
                }
            }

            if (clicked) {
                buttons[buttonNumber - 1].setColor(Color.GREEN);
                buttons[buttonNumber - 1].setText(buttons[buttonNumber - 1].getText()
                        + " | Nr: " + (pressCounter));
            }
        }
    }

    /**
     * Initialize cardOrder[] to non reachable values.
     */
    private void cardOrderClear() {
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
    private void buttonsClean() {
        for (int i = 0; i < choosableCardCount; i += 1) {
            buttons[i].setColor(Color.LIGHT_GRAY);
            buttons[i].setText(deck[i].getCardAttributePriority() + " - "
                    + deck[i]);
        }
    }

    private void deaktiviereButtons() {
        for (TextButton button : buttons) {
            button.setTouchable(Touchable.disabled);
        }
    }

    private void aktiviereButtons() {
        for (TextButton button : buttons) {
            button.setTouchable(Touchable.enabled);
        }
    }

    /**
     * Function that adds the buttons to the Stage.
     *
     * @param skin Object of class Skin which was initialized in the constructor.
     */
    public void addButtonsToStage(Skin skin) {

        Button startExecutionButton = new TextButton("Ausfuehrung starten", skin);
        Button endGameButton = new TextButton("Spiel beenden", skin);

        startExecutionButton.setSize(160, 43);
        endGameButton.setSize(160, 43);

        int startExecutionButtonX = Gdx.graphics.getHeight()
                + (Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) / 3 - 64;
        int startExecutionButtonY = Gdx.graphics.getHeight() - 100;
        int endGameButtonX = Gdx.graphics.getHeight()
                + (((Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) * 2) / 3) - 64;
        int endGameButtonY = Gdx.graphics.getHeight() - 100;

        startExecutionButton.setPosition(startExecutionButtonX, startExecutionButtonY);
        endGameButton.setPosition(endGameButtonX, endGameButtonY);


        startExecutionButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                //If All Cards are chosen
                if (cardOrder[4 - damagePoints] != -1) {
                    deaktiviereButtons();
                    zugInititalisierung.initialisiereBewegung();
                    board.move(zugInititalisierung.getList(), player);
                    zugInititalisierung.resetList();
                    startExecutionButton.setColor(Color.LIGHT_GRAY);
                    cardOrderClear();
                    aktiviereButtons();
                } else {
                    startExecutionButton.setColor(Color.RED);
                }
            }
        });

        endGameButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        stage.addActor(startExecutionButton);
        stage.addActor(endGameButton);

        // add Button to remove cardOrder
        Button removeCardOrder = new TextButton("Loesche\nKartenreihenfolge", skin);

        removeCardOrder.setSize(128, 43);
        int removeCardOrderX = Gdx.graphics.getHeight()
                + (Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) / 3 - 64;
        int removeCardOrderY = Gdx.graphics.getHeight() - 200;

        removeCardOrder.setPosition(removeCardOrderX, removeCardOrderY);

        removeCardOrder.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // System.out.println("Rauf");
                cardOrderClear();
                zugInititalisierung.resetList();
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // System.out.println("Runter");
                return true;
            }
        });

        stage.addActor(removeCardOrder);
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
        //player.drawParameters(batch);
        sprite.draw(batch);
        batch.end();
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
