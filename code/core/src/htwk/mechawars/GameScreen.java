package htwk.mechawars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
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

    Texture industrialTile;
    Texture robot;
    OrthographicCamera camera;
    Stage stage;
    Table container;
    private SpriteBatch batch;
    private static final int cameraWidth = 1280;
    private static final int cameraHeight = 720;
    
    int[] cardOrder = {566, 567, 568, 569, 570};
    int pressCounter = 0;
    
    Card[] deck = new Card[84];
    
    TextButton[] buttons = new TextButton[84];
    
    /**
     * Constructor of class GameScreen.
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
        
        for (int cardPrintCounter = 0; cardPrintCounter < 84; cardPrintCounter+=1) {
            buttons[cardPrintCounter] = new TextButton((cardPrintCounter+1) + " - " + deck[cardPrintCounter].getCardAttributeName().get_Name(), skin);
            table.row();
            table.add(buttons[cardPrintCounter]);
            int buttonNumber = (cardPrintCounter+1);
            
            // Button-ClickListener
            buttons[cardPrintCounter].addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    buttonClickOrder(buttonNumber);
                }
            });
        }

        container.add(scrollPanel).expand().fill();
    }
    
    
    private void buttonClickOrder(int buttonNumber) {
//      System.out.print(buttonText + " ");
//      System.out.println(buttonNumber);
      
      // can also be done with Try&Catch
      if(pressCounter<5) {
          cardOrder[pressCounter] = buttonNumber;         // write the number of the button in cardOrder at pressCounter
//        System.out.println(cardOrder[pressCounter] + " JO");

          pressCounter+=1;
          
          boolean testung = true;
          
          for(int i=pressCounter-2; i>=0; i-=1) {
//            System.out.println("FOR " + i);
//            System.out.println(cardOrder[i]);
              if(cardOrder[i]==buttonNumber) {
//                System.out.println("Durchlauf" + i);
                  testung = false;
                  pressCounter-=1;
              }
          }
          
//          System.out.println("vor if testung");
          
          if(testung) {
//            System.out.println("Juha testung");
              buttons[buttonNumber-1].setColor(Color.GREEN);
              buttons[buttonNumber-1].setText(buttons[buttonNumber-1].getText() + " | Nr: " + (pressCounter));
          } 
      }
      else {
          // NOP
      }
//      System.out.println("");
    }
    
    private void cardOrderClear() {
        int[] cardOrder = {566, 567, 568, 569, 570};
        for(int i=0; i<84; i+=1) {
            buttons[i].setText((i+1) + " - " + deck[i].getCardAttributeName().get_Name());
        }
        buttonsColourClean();
        pressCounter = 0;
    }
    
    private void buttonsColourClean() {
        for(int i=0; i<84; i+=1) {
            buttons[i].setColor(Color.LIGHT_GRAY);
        }
    }

    /**
     * Function that adds the buttons to the Stage.
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
        int removeCardOrderX = cameraHeight + (cameraWidth - cameraHeight)/3 - 64;
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
