package htwk.mechawars;

import java.io.IOException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
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

        chooseMap = new TextField(" Bitte Map angeben!", skin);
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
                
                if (!chooseMap.getText().contains(".txt")) {
                    chooseMap.setText(chooseMap.getText() + ".txt");
                }
                
                MechaWars.setMap(chooseMap.getText());
                
                toGameScreen();
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
}
