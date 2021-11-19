package htwk.mechawars;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import htwk.mechawars.game.GameScreen;

/**
 * Class that presents the surface of the MainMenu screen.
 */
public class MainMenu implements Screen {
    private Game game;
    private Stage stage;
    private SpriteBatch batch; 
    private Texture img; 

    /**
     * Constructor of class MainMenue.
     * @param g Object of class Game
     */
    public MainMenu(Game g) {
        game = g;
        img = new Texture(Gdx.files.internal("background.png"));
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("skinMenu/star-soldier-ui.json"));

        TextButton startGame = new TextButton("Spiel starten", skin);
        startGame.setPosition(440, 200);
        startGame.setSize(400, 100);
        startGame.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button) {
                changeScreen();
            }
        });

        TextButton endGame = new TextButton("Spiel beenden", skin);
        endGame.setPosition(440, 100);
        endGame.setSize(400, 100);
        endGame.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                Dialog dialogCloseOption = new Dialog("                 Beenden?", skin) {
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
                dialogCloseOption.button("Abbruch", false);
                dialogCloseOption.key(Input.Keys.ENTER, true);
                dialogCloseOption.key(Input.Keys.ESCAPE, false);                
            }
        });

        //add the buttons
        stage.addActor(startGame);
        stage.addActor(endGame);
    }

    /**
     * changeScreen function to switch to GameScreen.
     */
    public void changeScreen() {
        game.setScreen(new GameScreen(game));
        stage.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
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
