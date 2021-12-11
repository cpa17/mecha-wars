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
    
    /**
     * Constructor of class VictoryScreen.
     */
    public OptionScreen(Game g) {
        game = g;
        
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

        chooseMap = new TextField(" Bitte map angeben!", skin);
        chooseMap.setPosition(20, 20);
        chooseMap.setSize(300, 50);    
        
        start = new TextButton("Starten", skin);
        start.setPosition(440, 310);
        start.setSize(400, 100);
        start.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button) {
                ConfigReader.writePlayerNumber((int) enemyChooser.getValue()); 
                
                try {
                    ConfigReader.readConfigs();
                } catch (IOException c) {
                    c.printStackTrace();
                }
                
                String input = chooseMap.getText();
                
                // delete all wrong spaces
                if (input.matches(" " + "(.*)")) {
                    System.out.println("1");
                    input = input.replace(" ", "");
                }
                
                if (fileListRead(input)) {
                    if (!input.contains(".txt")) {
                        input = input + ".txt";
                    }
                    
                    MechaWars.setMap(input);

                    toGameScreen();
                }
                else {
                    Dialog dialogCloseOption = new Dialog("Eingabe falsch... | Programm wird beendet", skin) {
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
                    dialogCloseOption.button("Beenden", false);
                    dialogCloseOption.key(Input.Keys.ENTER, true);
                    dialogCloseOption.key(Input.Keys.ESCAPE, false);                
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
    
    public void toGameScreen() {
        game.setScreen(new GameScreen(game, MechaWars.getMap()));
        stage.dispose();
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
        String regex = "(.*)";        
        File folder = new File("bin/main");
        
        if (!input.matches(regex + ".txt")) {
            input = input + ".txt";
        }

        for (File file : folder.listFiles()) {
            if (file.getName().matches(regex + ".txt")) {
                filesInDirectory.add(file.getName());
            }
        }
        
        //Ausgabe zur Kontrolle
        for (int i = 0; i < filesInDirectory.size(); i += 1){
            System.out.println(filesInDirectory.get(i));
        }
        
        // vorhanden
        for (int i = 0; i < filesInDirectory.size(); i += 1){
            if (filesInDirectory.get(i).equals(input)) {
                return true;
            }
        }
        
        return false;
    }
}
