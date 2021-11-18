package htwk.mechawars.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;

import htwk.mechawars.VictoryScreen;
import htwk.mechawars.ZugInitialisierung;
import htwk.mechawars.board.Board;
import htwk.mechawars.board.Dir;
import htwk.mechawars.board.Robot;
import htwk.mechawars.fields.Field;

/**
 * Class that presents the surface of the game screen.
 */
public class GameScreen implements Screen {
    private Game game;
    private static boolean winCondition = false;
    private Field robotPosition;
    private Texture industrialTile;
    private Texture robot;
    private static Stage stage;
    private SpriteBatch batch;
    private Sprite sprite;
    protected static final ZugInitialisierung zugInitialisierung = new ZugInitialisierung();
    private static Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

    protected static final Board board = new Board("testmap.txt");
    private static Robot player = new Robot();

    /**
     * Constructor of class GameScreen.
     */
    public GameScreen(Game g) {
        game = g;
        
        setStage(new Stage());
        
        industrialTile = new Texture("mapAssets/StandardField.png");
        
        robot = new Texture("robot.png");
        
        batch = new SpriteBatch();
        sprite = new Sprite(robot);

        Gdx.input.setInputProcessor(getStage());

        addButtonsToStage(skin);
        addScrollPanelToStage(skin);
        board.startRobot(5, 5, Dir.NORTH, player);
    }

    /**
     * Function that adds the scroll panel to the Stage.
     * @param skin Object of class Skin.
     */
    public static void addScrollPanelToStage(Skin skin) {
        int containerBoundsX = (Gdx.graphics.getWidth()
                - ((Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) / 2)) + 10;
        int containerBoundsY = 10;
        int containerWidth = ((Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) / 2) - 20;
        int containerHeight = 600;

        Table container = new Table();
        getStage().addActor(container);
        container.setBounds(containerBoundsX, containerBoundsY, containerWidth, containerHeight);

        container.add(ScrollPanel.scrollPanel(skin, player)).grow();
    }

    /**
     * Function that adds the buttons to the Stage.
     * @param skin Object of class Skin.
     */
    public static void addButtonsToStage(Skin skin) {
        getStage().addActor(Buttons.startButton(skin, player));
        getStage().addActor(Buttons.endButton(skin));

        getStage().addActor(Buttons.removeButton(skin));

        if (player.getShutDown()) {
            Buttons.removeButton(skin).setTouchable(Touchable.disabled);
            Buttons.removeButton(skin).setDisabled(true);
        } else {
            Buttons.removeButton(skin).setTouchable(Touchable.enabled);
            Buttons.removeButton(skin).setDisabled(false);
        }

        getStage().addActor(Buttons.infoButton(skin));

        if (player.getShutDown()) {
            Buttons.shutDownButton(skin, player).setTouchable(Touchable.disabled);
            Buttons.wakeUpButton(skin, player).setTouchable(Touchable.enabled);
            Buttons.shutDownButton(skin, player).setDisabled(true);
            Buttons.wakeUpButton(skin, player).setDisabled(false);
        } else {
            Buttons.shutDownButton(skin, player).setTouchable(Touchable.enabled);
            Buttons.wakeUpButton(skin, player).setTouchable(Touchable.disabled);
            Buttons.shutDownButton(skin, player).setDisabled(false);
            Buttons.wakeUpButton(skin, player).setDisabled(true);
        }

        getStage().addActor(Buttons.shutDownButton(skin, player));
        getStage().addActor(Buttons.wakeUpButton(skin, player));

    }
    
    public static void setWinCondition() {
        winCondition = true;
    }
    
    public void changeScreen() {
        game.setScreen(new VictoryScreen(game));
        getStage().dispose();
    }
    
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.8f, 0.8f, 0.8f, 1);
        batch.begin();
        Board.toAsset(batch, board);
        player.drawRobot(sprite, board);
        player.drawParameters(batch);
        sprite.draw(batch);
        batch.end();
        robotPosition = board.fieldmatrix[player.getXcoor()][player.getYcoor()];
        player.setTexture(robotPosition.getTile());
        robotPosition.action(player);
        if (winCondition) {
            changeScreen();
        }
        getStage().act();
        getStage().draw();
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

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        GameScreen.stage = stage;
    }

}
