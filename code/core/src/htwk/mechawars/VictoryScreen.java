package htwk.mechawars;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Class that presents the victory screen of the game.
 */
public class VictoryScreen implements Screen {
    
    private Game game;
    private Stage stage;
    private SpriteBatch batch;
    private Texture endscreen;
    
    /**
     * Constructor of class VictoryScreen.
     */
    public VictoryScreen(Game g) {
        game = g;
        
        stage = new Stage();
        
        endscreen = new Texture(Gdx.files.internal("endscreen.png"));
        
        Gdx.input.setInputProcessor(stage);
        
        Skin skin = new Skin(Gdx.files.internal("skinMenu/star-soldier-ui.json"));
        
        TextButton backToStart = new TextButton("Zum Hauptmenu", skin);
        backToStart.setPosition(440, 430);
        backToStart.setSize(400, 100);
        backToStart.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button) {
                toMainMenu();
            }
        });
        
        stage.addActor(backToStart);
    }
    
    public void toMainMenu() {
        game.setScreen(new MainMenu(game));
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
        batch.draw(endscreen, 0, 0, 1280, 720);
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