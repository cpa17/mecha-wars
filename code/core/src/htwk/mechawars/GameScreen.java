package htwk.mechawars;

import java.util.ListIterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
        industrialTile = new Texture("industrialTile.png");

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
        
        // add Button for tipps and infos
        Button buttonInfo = new TextButton("Info's", skin);
        
        int a = 60;     // width
        int b = 40;     // height
        
        buttonInfo.setSize(a, b);
        int buttonInfoX = Gdx.graphics.getWidth() - (a + 10);
        int buttonInfoY = Gdx.graphics.getHeight() - (b + 10);
        
        buttonInfo.setPosition(buttonInfoX, buttonInfoY);

        buttonInfo.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // GUI/POPup start
                //InfoDialog infoDia = new InfoDialog("Beenden?", skin);
                //infoDia.show(stage);    // shows the dialog

                new Dialog("Infos und Hinweise", skin) {

                    // best constructor for Dialog in LibGDX
                    {
                        //
                        Label headlineFirst = new Label("Karten", skin);
                        Label headlineSecond = new Label("\nFelder", skin);
                        Label newLine = new Label("", skin);
                        Label movOne = new Label("1 Vor", skin);
                        Label movTwo = new Label("2 Vor", skin);
                        Label movThree = new Label("3 Vor", skin);
                        Label movBack = new Label("Rueckwaerts", skin);
                        Label turnL = new Label("Linksdrehung", skin);
                        Label turnR = new Label("Rechtsdrehung", skin);
                        Label turnU = new Label("Kehrtwendung", skin);
                        // Feld
                        Label freeTile = new Label("Freies Feld", skin);
                        Label pitTile = new Label("Grube", skin);
                        Label wallTile = new Label("Wand", skin);
                        Label conveyorTile = new Label("Foerderband", skin);
                        Label expressConveyorTile = new Label("Expressband", skin);
                        Label rotatingConveyorTile = new Label("Rotierendes Foerderband", skin);
                        Label rotatingMergingConveyorTile = new Label("Rotierendes und ineinander uebergehendes Foerderband", skin);
                        Label rotatingDoubleMergingConveyorTile = new Label("Rotierendes und zweisitiges ineinander uebergehendes Foerderband", skin);
                        Label pusherTile = new Label("Schieber", skin);
                        Label clockwiseTile = new Label("Im Uhrzeigersinn laufende Zahnraeder", skin);
                        Label counterClockwiseTile = new Label("Gegen den Uhrzeigersinn laufende Zahnraeder", skin);
                        Label laserTile = new Label("Laserbeschuss", skin);
                        Label checkpointFlagTile = new Label("Checkpoint", skin);
                        Label repairTile = new Label("Reparaturfeld", skin);
                        Label dockTile = new Label("Dock", skin);
                        TextField movOneText = new TextField("Bewegt den Roboter 1 Feld in Blickrichtung vor.", skin);
                        TextField movTwoText = new TextField("Bewegt den Roboter 2 Felder Blickrichtung vor.", skin);
                        TextField movThreeText = new TextField("Bewegt den Roboter 3 Felder Blickrichtung vor.", skin);
                        TextField movBackText = new TextField("Bewegt den Roboter 1 Feld Blickrichtung zurueck.", skin);
                        TextField turnLText = new TextField("Dreht den Roboter nach Links.", skin);
                        TextField turnRText = new TextField("Dreht den Roboter nach Rechts.", skin);
                        TextField turnUText = new TextField("Dreht den Roboter um.", skin);
                        TextField freeTileText = new TextField("", skin);
                        TextField pitTileText = new TextField("", skin);
                        TextField wallTileText = new TextField("", skin);
                        TextField conveyorTileText = new TextField("", skin);
                        TextField expressConveyorTileText = new TextField("", skin);
                        TextField rotatingConveyorTileText = new TextField("", skin);
                        TextField rotatingMergingConveyorTileText = new TextField("", skin);
                        TextField rotatingDoubleMergingConveyorTileText = new TextField("", skin);
                        TextField pusherTileText = new TextField("", skin);
                        TextField clockwiseTileText = new TextField("", skin);
                        TextField counterClockwiseTileText = new TextField("", skin);
                        TextField laserTileText = new TextField("", skin);
                        TextField checkpointFlagTileText = new TextField("", skin);
                        TextField repairTileText = new TextField("", skin);
                        TextField dockTileText = new TextField("", skin);
                        movOneText.setDisabled(true);
                        movTwoText.setDisabled(true);
                        movThreeText.setDisabled(true);
                        movBackText.setDisabled(true);
                        turnLText.setDisabled(true);
                        turnRText.setDisabled(true);
                        turnUText.setDisabled(true);
                        Table table = new Table();
                        table.center();
                        table.add(headlineFirst);
                        table.row();
                        table.add(movOne);
                        table.add(movOneText).width(500);
                        table.row();
                        table.add(movTwo);
                        table.add(movTwoText).width(500);
                        table.row();
                        table.add(movThree);
                        table.add(movThreeText).width(500);
                        table.row();
                        table.add(movBack);
                        table.add(movBackText).width(500);
                        table.row();
                        table.add(turnL);
                        table.add(turnLText).width(500);
                        table.row();
                        table.add(turnR);
                        table.add(turnRText).width(500);
                        table.row();
                        table.add(turnU);
                        table.add(turnUText).width(500);
                        table.row();
                        table.add(newLine);
                        table.row();

                        table.add(headlineSecond);
                        table.row();
                        //
                        table.add(freeTile);
                        table.add(freeTileText).width(500);
                        table.row();
                        table.add(pitTile);
                        table.add(pitTileText).width(500);
                        table.row();
                        table.add(wallTile);
                        table.add(wallTileText).width(500);
                        table.row();
                        table.add(conveyorTile);
                        table.add(conveyorTileText).width(500);
                        table.row();
                        table.add(expressConveyorTile);
                        table.add(expressConveyorTileText).width(500);
                        table.row();
                        table.add(rotatingConveyorTile);
                        table.add(rotatingConveyorTileText).width(500);
                        table.row();
                        table.add(rotatingMergingConveyorTile);
                        table.add(rotatingMergingConveyorTileText).width(500);
                        table.row();
                        table.add(rotatingDoubleMergingConveyorTile);
                        table.add(rotatingDoubleMergingConveyorTileText).width(500);
                        table.row();
                        table.add(pusherTile);
                        table.add(pusherTileText).width(500);
                        table.row();
                        table.add(clockwiseTile);
                        table.add(clockwiseTileText).width(500);
                        table.row();
                        table.add(counterClockwiseTile);
                        table.add(counterClockwiseTileText).width(500);
                        table.row();
                        table.add(laserTile);
                        table.add(laserTileText).width(500);
                        table.row();
                        table.add(checkpointFlagTile);
                        table.add(checkpointFlagTileText).width(500);
                        table.row();
                        table.add(repairTile);
                        table.add(repairTileText).width(500);
                        table.row();
                        table.add(dockTile);
                        table.add(dockTileText).width(500);
                        table.row();
                        add(table);

//                        //TableModel a = new TableModel();
//                        Table tableDialog = new Table(skin);
//                        Image robot = new Image();
//                        robot.setDrawable(new TextureRegionDrawable(AssetLoader.load()));
//                        //Pixmap pixmapEins = new Pixmap(AssetLoader.robot);
//                        tableDialog.add(pixmapEins);
//                        tableDialog.add(new Label("Hallo", skin));
//                        tableDialog.row();
//                        tableDialog.add(new Label("Hallo", skin));
//                        tableDialog.row();
//
//                        tableDialog.center();
//                        add(tableDialog);


                        button("Schliessen", "Button pressed");
                    }

                    @Override
                    protected void result(Object object) {
                        //super.result(object);
                        System.out.println(object);
                    } 
                    
                }.show(stage);//.setHeight(600);
                
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        
        stage.addActor(buttonInfo);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.8f, 0.8f, 0.8f, 1);
        batch = (SpriteBatch) stage.getBatch();
        batch.begin();
        drawPlayingField();
        drawRobot();
        batch.end();
        stage.act();
        stage.draw();
    }

    /**
     * Function that draws the robot on the playing field.
     */
    public void drawRobot() {
        int tileSize = (Gdx.graphics.getHeight() / 12);
        int row = 6;
        int column = 6;
        batch.draw(robot, tileSize * (column - 1), (tileSize * (row - 1)) + 5);
    }


    /**
     * Function that draws the playing field.
     */
    public void drawPlayingField() {
        int x = 0;
        int i = 0;
        while (i < 12) {
            int y = 0;
            int j = 0;
            while (j < 12) {
                batch.draw(industrialTile, x, y);
                y = y + (Gdx.graphics.getHeight() / 12);
                j++;
            }
            x = x + (Gdx.graphics.getHeight() / 12);
            i++;
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
