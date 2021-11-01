package htwk.mechawars;

import javax.swing.JOptionPane;

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

import htwk.mechawars.board.Board;

/**
 * Class that presents the surface of the MainMenu screen.
 */
public class MainMenu implements Screen {
    private Game game;
    private Stage stage;
    private SpriteBatch batch; //The SpriteBatch is a special class that is used to draw 2D images.
    private Texture img; 

    /**
     * Constructor of class MainMenue.
     * @param g Object of class Game
     */
    public MainMenu(Game g) {
        game = g;
        img = new Texture(Gdx.files.internal("background.png"));

        //Its like a new window (The stage will use its own Batch)
        stage = new Stage();
        //Now our input is sent to the stage and we can click on buttons etc.
        Gdx.input.setInputProcessor(stage);

        //Creates a skin containing the resources in the specified skin JSON file
        Skin skin = new Skin(Gdx.files.internal("skinMenu/star-soldier-ui.json"));

        //Creates a TextButton with a String and a Skin
        TextButton startGame = new TextButton("Spiel starten", skin);
        startGame.setPosition(440, 200);
        startGame.setSize(400, 100);
        //Create a listener where clicked(InputEvent, float, float) is only called for left clicks
        startGame.addListener(new ClickListener() {
            //Called when a mouse button or a finger touch goes up anywhere
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button) {
                changeScreen();
            }
        });

        TextButton endGame = new TextButton("Spiel beenden", skin);
        endGame.setPosition(440, 100);
        endGame.setSize(400, 100);
        endGame.addListener(new ClickListener() {
            @SuppressWarnings("unused")
            public void clicked(InputEvent e, float x, float y) {
                if (JOptionPane.showConfirmDialog(null, 
                        "Programm wird beendet.", "Schlieﬂen?", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    // tu was - beenden
                    Gdx.app.exit();
                }//Ende Aktion des if-Teils
                // sonst nichts
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
        game.setScreen(new GameScreen());
        stage.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //specify clear values for the color buffers
        Gdx.gl.glClearColor(0, 0, 0, 0);
        //clear buffers to preset values
        //GL_COLOR_BUFFER_BIT - Indicates the buffers currently enabled for color writing.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //now the screen is cleared

        //the stage uses its own batch so i store this batch in the global batch
        batch = (SpriteBatch) stage.getBatch(); 
        batch.begin();
        batch.draw(img, 0, 0, 1280, 720);
        batch.end();

        //Updates the actors based on time. Typically called each frame.
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
