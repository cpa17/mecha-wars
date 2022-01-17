package htwk.mechawars;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

import htwk.mechawars.game.GameScreen;

/**
 * Class that presents the surface of the OptionScreen screen.
 */
public class OptionScreen implements Screen {
    private Game game;
    private Stage stage;
    private SpriteBatch batch;
    private Label howManyEnemys;
    private Label enemyCounter;
    private Slider enemyChooser;
    private TextField chooseMap;
    private CheckBox trainMode;
    private Texture img;
    private TextButton start;
    private Skin skin;
    private String mapPath = "../core/assets/maps";
    private boolean mapNotFound;
    
    /**
     * Constructor of class VictoryScreen.
     */
    public OptionScreen(Game g) {
        game = g;
        
        mapNotFound = false;
        
        stage = new Stage();
        
        Gdx.input.setInputProcessor(stage);
        
        img = new Texture(Gdx.files.internal("OptionBackground.jpg"));
        
        skin = new Skin(Gdx.files.internal("skinMenu/star-soldier-ui.json"));
        
        trainMode = new CheckBox("Trainingsmodus", skin);
        trainMode.setPosition(1015, 680);
        trainMode.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //TODO change to Train Mode
            }
        });
        
        howManyEnemys = new Label("Anzahl der Spieler:", skin);
        howManyEnemys.setPosition(480, 180);
        
        enemyCounter = new Label("1", skin);
        enemyCounter.setPosition(780, 180);
        
        enemyChooser = new Slider(1, 8, 1, false, skin);
        enemyChooser.setPosition(470, 200);
        enemyChooser.setSize(340, 50);
        enemyChooser.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                enemyCounter.setY(enemyChooser.getValue() - 1);
                enemyCounter.setText((int) enemyChooser.getValue());
                enemyCounter.setPosition(780, 180);
            }
        });
        
        chooseMap = new TextField(" Bitte Map angeben", skin);
        chooseMap.setPosition(20, 20);
        chooseMap.setSize(300, 50);    
        
        start = new TextButton("Starten", skin);
        start.setPosition(440, 310);
        start.setSize(400, 100);
        start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                loadConfig();

                String input = chooseMap.getText().toLowerCase();
                
                if (!(chooseMap.getText().matches(" Bitte Map angeben")) && 
                        !mapNotFound) {                       
                    if (fileListRead(input)) {
                        inputToMap(input);
                    } else if (!mapNotFound) {                  
                        Dialog dialogCloseOption = new Dialog("\t Mapname falsch", skin) {
                            @Override
                            protected void result(Object object) {
                                remove();
                            }
                        }.show(stage);
                        
                        dialogCloseOption.setSize(400, 100);
                        dialogCloseOption.setPosition(440, 310);
                        dialogCloseOption.button("Neue Eingabe", null);   
                        dialogCloseOption.key(Input.Keys.ENTER, null);
                    }
                } else if (mapNotFound) {
                    mapPath = input;
                    mapNotFound = false; 
                    start.setText("Starten");
                } else {                  
                    MechaWars.setMap("test.txt");
                    toGameScreen(); 
                }
            }
        });
        
        stage.addActor(trainMode);
        stage.addActor(enemyChooser);
        stage.addActor(howManyEnemys);
        stage.addActor(enemyCounter);
        stage.addActor(chooseMap);
        stage.addActor(start);
    }
    
    /**
     * Function to switch to the GameScreen Surface.
     */
    public void toGameScreen() {
        game.setScreen(new GameScreen(game, MechaWars.getMap()));
        stage.dispose();
    }
    
    /**
     * Function to load the right map or change to default map if an exception is thrown.
     * @param input - input of the Textfield
     */
    public void inputToMap(String input) {
        if (!input.contains(".txt")) {
            input = input + ".txt";
        } 
        
        try {
            MechaWars.setMap(input);
            toGameScreen();
        } catch (Exception e) {
            Dialog dialogCloseOption = new Dialog("\t  Map Fehlerhaft", skin) {
                @Override   
                protected void result(Object object) {
                    remove();
                    MechaWars.setMap("test.txt");
                    toGameScreen(); 
                }
            }.show(stage);
            
            dialogCloseOption.setSize(400, 100);
            dialogCloseOption.setPosition(440, 310);
            dialogCloseOption.button("Standartmap nehmen", null);   
            dialogCloseOption.key(Input.Keys.ENTER, null);
        } 
    }
    
    /**
     * Function to load the right number of Players.
     */
    public void loadConfig() {
        ConfigReader.writePlayerNumber((int) enemyChooser.getValue()); 
        
        try {
            ConfigReader.readConfigs();
        } catch (IOException c) {
            c.printStackTrace();
        }
    }
    
    @Override
    public void show() {
      
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.8f, 0.8f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch = (SpriteBatch) stage.getBatch(); 
        batch.begin();
        batch.draw(img, 0, 0, 1280, 720);
        batch.end();
        stage.act(delta);
        stage.draw();
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
    public void hide() {
        
    }

    @Override
    public void dispose() {
        
    }
    
    /**
     * Function check, if the choosen file exist.
     * 
     * @param input -> string from the input (choosen file)
     * @return true if the file exist
     */
    public boolean fileListRead(String input) { 
        ArrayList<String> filesInDirectory = new ArrayList<String>();  
        File folder = new File(mapPath);
        
        //(.*) is a group of zero or more of any character
        if (!input.matches("(.*)" + ".txt")) {
            input = input + ".txt";
        }
        
        try {
            for (File file : folder.listFiles()) {
                if (file.getName().matches("(.*)" + ".txt")) {
                    filesInDirectory.add(file.getName());
                }
            }
        } catch (NullPointerException npe) {
            mapNotFound = true;
            Dialog dialogCloseOption = new Dialog("\t    Keine Maps da", skin) {
                @Override   
                protected void result(Object object) {
                    remove();
                    start.setText("Neuen Pfad nehmen");                 
                    chooseMap.setText("Bitte Pfad angeben");  
                }
            }.show(stage);
            
            dialogCloseOption.setSize(400, 100);
            dialogCloseOption.setPosition(440, 310);
            dialogCloseOption.button("Neuen Pfad angeben", null);   
            dialogCloseOption.key(Input.Keys.ENTER, null);
        }
        
        for (int i = 0; i < filesInDirectory.size(); i += 1) {
            if (filesInDirectory.get(i).equals(input)) {
                return true;
            }
        }   
        return false;
    }
}