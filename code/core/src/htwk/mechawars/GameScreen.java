package htwk.mechawars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {

    final MechaWars game;

    Texture industrialTile;
    OrthographicCamera camera;
    Stage stage;
    private static final int cameraWidth = 1280;
    private static final int cameraHeight = 720;

    public GameScreen(final MechaWars game) {
        this.game = game;
        industrialTile = new Texture("industrialTile.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, cameraWidth, cameraHeight);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        addButtonsToStage(skin);
    }

    public void addButtonsToStage(Skin skin) {
        int startExecutionButtonX = cameraHeight + (cameraWidth - cameraHeight)/3 -64;
        int startExecutionButtonY = cameraHeight - 100;
        int endGameButtonX = cameraHeight + (((cameraWidth-cameraHeight)*2)/3)-64;
        int endGameButtonY = cameraHeight - 100;

        Button startExecutionButton = new TextButton("Start Execution", skin);
        Button endGameButton = new TextButton("End Game", skin);

        startExecutionButton.setSize(128, 43);
        endGameButton.setSize(128, 43);

        startExecutionButton.setPosition(startExecutionButtonX,startExecutionButtonY);
        endGameButton.setPosition(endGameButtonX,endGameButtonY);

        startExecutionButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("startExecutionButton touchUp ausgelöst!");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("startExecutionButton touchDown ausgelöst!");
                return true;
            }
        });

        endGameButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("endGameButton touchUp ausgelöst!");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("endGameButton touchDown ausgelöst!");
                return true;
            }
        });

        stage.addActor(startExecutionButton);
        stage.addActor(endGameButton);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        camera.update();
 //       game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        drawPlayingField();
        game.batch.end();
        stage.act();
        stage.draw();
    }

    public void drawPlayingField(){
        int x = 0;
        int i = 0;
        while (i < 12) {
            int y = 0;
            int j = 0;
            while (j < 12) {
                game.batch.draw(industrialTile, x, y);
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
    }

    @Override
    public void hide() {

    }

}
