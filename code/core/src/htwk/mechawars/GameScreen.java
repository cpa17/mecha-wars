package htwk.mechawars;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import htwk.mechawars.cards.Card;
import htwk.mechawars.cards.CardFunctions;

/**
 * Class that presents the surface of the game screen.
 */
public class GameScreen implements Screen {

<<<<<<< Upstream, based on origin/MW13
    Texture industrialTile;
    Texture robot;
    OrthographicCamera camera;
    Stage stage;
    Table container;
    private SpriteBatch batch;
=======
    final MechaWars game;

    private Texture industrialTile;
    private Texture robot;
    private OrthographicCamera camera;
    private Stage stage;
    private Table container;
>>>>>>> 5afb8c1 attribute private gemacht
    private static final int cameraWidth = 1280;
    private static final int cameraHeight = 720;

    private ZugInitialisierung zugInititalisierung = new ZugInitialisierung();

    private int[] cardOrder = { -1, -1, -1, -1, -1};
    private int pressCounter = 0;
    
    private Card[] deck = new Card[84];
    
    private TextButton[] buttons = new TextButton[84];
    LinkedList<TextButton> buttons = new LinkedList<TextButton>();

    /**
     * Constructor of class GameScreen.
     * 
     */
    public GameScreen() {
        industrialTile = new Texture("industrialTile.png");
        robot = new Texture("robot.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, cameraWidth, cameraHeight);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        addButtonsToStage(skin);
        addScrollPanelToStage(skin);
    }

    /**
     * Function that adds the scroll panel to the Stage.
     * 
     * @param skin Object of class Skin which was initialized in the constructor.
     */
    public void addScrollPanelToStage(Skin skin) {
        int containerBoundsX = (cameraWidth - ((cameraWidth - cameraHeight) / 2)) + 10;
        int containerBoundsY = 10;
        int containerWidth = ((cameraWidth - cameraHeight) / 2) - 20;

        container = new Table();
        stage.addActor(container);
        container.setBounds(containerBoundsX, containerBoundsY, containerWidth, 600);

        Table table = new Table();

        final ScrollPane scrollPanel = new ScrollPane(table, skin);

        // Array of Cards created
        deck = CardFunctions.initDeck(deck);
        // shuffle Deck
        deck = CardFunctions.shuffle(deck);
        for (int cardPrintCounter = 0; cardPrintCounter < 84; cardPrintCounter += 1) {
            buttons[cardPrintCounter] = new TextButton((cardPrintCounter + 1) + " - "
                    + deck[cardPrintCounter], skin);
            table.row();
            table.add(buttons[cardPrintCounter]);
            int buttonNumber = (cardPrintCounter + 1);
            
            // Button-ClickListener
            buttons[cardPrintCounter].addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    buttonClickOrder(buttonNumber);
                    System.out.print(buttonText + " ");
                    System.out.println(buttonNumber);
// can also be done with Try&Catch
                    if (pressCounter < 5) {
                        cardOrder[pressCounter] = buttonNumber;
                        System.out.println(cardOrder[pressCounter]);
                        pressCounter += 1;
                    } else {
// NOP
                    }
                    zugInititalisierung.addCard(aktuelleKarte);

                }

            });
        }

        container.add(scrollPanel).expand().fill();
    }
    
    /**
     * If there were less than 5 valid button clicks: paints button green and adds
     *  " | Nr: " with the corresponding Number of at what time it was clicked.
     * @param buttonNumber -> ID-number of clicked button
     */
    private void buttonClickOrder(int buttonNumber) {
        //System.out.print(buttonText + " ");
        //System.out.println(buttonNumber);
      
        // can also be done with Try&Catch
        if (pressCounter < 5) {
            // write the number of the button in cardOrder at pressCounter
            cardOrder[pressCounter] = buttonNumber;  
            //System.out.println(cardOrder[pressCounter] + " JO");

            pressCounter += 1;
          
            boolean testung = true;
          
            for (int i = (pressCounter - 2); i >= 0; i -= 1) {
                //System.out.println("FOR " + i);
                //System.out.println(cardOrder[i]);
                if (cardOrder[i] == buttonNumber) {
                    //System.out.println("Durchlauf" + i);
                    testung = false;
                    pressCounter -= 1;
                }
            }
          
            //System.out.println("vor if testung");
          
            if (testung) {
                //System.out.println("Juha testung");
                buttons[buttonNumber - 1].setColor(Color.GREEN);
                buttons[buttonNumber - 1].setText(buttons[buttonNumber - 1].getText() 
                        + " | Nr: " + (pressCounter));
            } 
        }
        //System.out.println("");
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
        for (int i = 0; i < 84; i += 1) {
            buttons[i].setColor(Color.LIGHT_GRAY);
            buttons[i].setText((i + 1) + " - " + deck[i]);
        }
    }

    private void deaktiviereButtons() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setTouchable(Touchable.disabled);
        }
// TODO Auto-generated method stub

    }

    /**
     * Function that adds the buttons to the Stage.
     * 
     * @param skin Object of class Skin which was initialized in the constructor.
     */
    public void addButtonsToStage(Skin skin) {

        Button startExecutionButton = new TextButton("Start Execution", skin);
        Button endGameButton = new TextButton("End Game", skin);

        startExecutionButton.setSize(128, 43);
        endGameButton.setSize(128, 43);

        int startExecutionButtonX = cameraHeight + (cameraWidth - cameraHeight) / 3 - 64;
        int startExecutionButtonY = cameraHeight - 100;
        int endGameButtonX = cameraHeight + (((cameraWidth - cameraHeight) * 2) / 3) - 64;
        int endGameButtonY = cameraHeight - 100;
        

        startExecutionButton.setPosition(startExecutionButtonX, startExecutionButtonY);
        endGameButton.setPosition(endGameButtonX, endGameButtonY);
        

        startExecutionButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("startExecutionButton touchUp ausgelöst!");
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("startExecutionButton touchDown ausgelöst!");
                deaktiviereButtons();
                zugInititalisierung.initialiereBewegung();
                return true;
            }
        });

        endGameButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("endGameButton touchUp ausgelöst!");
                Gdx.app.exit();
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("endGameButton touchDown ausgelöst!");
                return true;
            }
        });

        stage.addActor(startExecutionButton);
        stage.addActor(endGameButton);
        
        // add Button to remove cardOrder
        Button removeCardOrder = new TextButton("Loesche\nKartenreihenfolge", skin);
        
        removeCardOrder.setSize(128, 43);
        int removeCardOrderX = cameraHeight + (cameraWidth - cameraHeight) / 3 - 64;
        int removeCardOrderY = cameraHeight - 200;
        
        removeCardOrder.setPosition(removeCardOrderX, removeCardOrderY);
        
        removeCardOrder.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // System.out.println("Rauf");
                cardOrderClear();
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
        camera.update();
        batch = (SpriteBatch) stage.getBatch();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawPlayingField();
        drawRobot();
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    /**
     * Function that draws the robot on the playing field.
     */
    public void drawRobot() {
        int tileSize = (cameraHeight / 12);
        int reihe = 6;
        int spalte = 6;
        batch.draw(robot, tileSize * (spalte - 1), (tileSize * (reihe - 1)) + 5);
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
                y = y + (cameraHeight / 12);
                j++;
            }
            x = x + (cameraHeight / 12);
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
