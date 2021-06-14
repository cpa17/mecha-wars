package htwk.mechawars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Class that presents the surface of the game screen.
 */
public class GameScreen implements Screen {

    final MechaWars game;

    Texture industrialTile;
    Texture robot;
    OrthographicCamera camera;
    Stage stage;
    Table container;
    private static final int cameraWidth = 1280;
    private static final int cameraHeight = 720;

    /**
     * Constructor of class GameScreen.
     * @param game Object of class MechaWars.
     */
    public GameScreen(final MechaWars game) {
        this.game = game;

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

        for (int i = 1; i < 101; i++) {
            TextButton button;
            button = new TextButton(i + ". Karte", skin);
            table.row();
            table.add(button);
            String buttonText = i + ". Karte angeklickt";
            button.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println(buttonText);
                }
            });
        }

        container.add(scrollPanel).expand().fill();
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
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.8f, 0.8f, 0.8f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        drawPlayingField();
        drawRobot();
        game.batch.end();
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
        game.batch.draw(robot, tileSize * (spalte - 1), (tileSize * (reihe - 1)) + 5);
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
        robot.dispose();
    }

    @Override
    public void hide() {

    }

}
