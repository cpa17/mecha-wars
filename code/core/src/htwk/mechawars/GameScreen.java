package htwk.mechawars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

import htwk.mechawars.cards.Card;
import htwk.mechawars.cards.CardFunctions;

/**
 * Class that presents the surface of the game screen.
 */
public class GameScreen implements Screen {

    Texture industrialTile;
    Texture robot;
    Stage stage;
    Table container;
    private SpriteBatch batch;
    
    private int[] cardOrder = { -1, -1, -1, -1, -1};
    private int pressCounter = 0;
    
    private Card[] deck = new Card[84];
    
    private TextButton[] buttons = new TextButton[84];


    /**
     * Constructor of class GameScreen.
     */
    public GameScreen() {
        industrialTile = new Texture("industrialTile.png");
        robot = new Texture("robot.png");

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        addButtonsToStage(skin);
        addScrollPanelToStage(skin);
    }

    /**
     * Function that adds the scroll panel to the Stage.
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

    /**
     * Function that adds the buttons to the Stage.
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
                System.out.println("startExecutionButton angeklickt!");
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
                        TextField movOneText = new TextField("Bewegt den Roboter 1 Feld in Blickrichtung vor.", skin);
                        TextField movTwoText = new TextField("Bewegt den Roboter 2 Felder Blickrichtung vor.", skin);
                        TextField movThreeText = new TextField("Bewegt den Roboter 3 Felder Blickrichtung vor.", skin);
                        TextField movBackText = new TextField("Bewegt den Roboter 1 Feld Blickrichtung zurueck.", skin);
                        TextField turnLText = new TextField("Dreht den Roboter nach Links.", skin);
                        TextField turnRText = new TextField("Dreht den Roboter nach Rechts.", skin);
                        TextField turnUText = new TextField("Dreht den Roboter um.", skin);

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
