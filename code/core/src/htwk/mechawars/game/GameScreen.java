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
    static Stage stage;
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
        
        stage = new Stage();
        
        industrialTile = new Texture("mapAssets/StandardField.png");
        
        robot = new Texture("robot.png");
        
        batch = new SpriteBatch();
        sprite = new Sprite(robot);

        Gdx.input.setInputProcessor(stage);

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
        stage.addActor(container);
        container.setBounds(containerBoundsX, containerBoundsY, containerWidth, containerHeight);

        container.add(ScrollPanel.scrollPanel(skin, player)).grow();
    }

    /**
     * Function that adds the buttons to the Stage.
     * @param skin Object of class Skin.
     */
    public static void addButtonsToStage(Skin skin) {
        stage.addActor(Buttons.startButton(skin, player));
        stage.addActor(Buttons.endButton(skin));

        stage.addActor(Buttons.removeButton(skin));

        if (player.getShutDown()) {
            Buttons.removeButton(skin).setTouchable(Touchable.disabled);
            Buttons.removeButton(skin).setDisabled(true);
        } else {
            Buttons.removeButton(skin).setTouchable(Touchable.enabled);
            Buttons.removeButton(skin).setDisabled(false);
        }

        stage.addActor(Buttons.infoButton(skin));

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

        stage.addActor(Buttons.shutDownButton(skin, player));
        stage.addActor(Buttons.wakeUpButton(skin, player));

    }
    
    public static void setWinCondition() {
        winCondition = true;
    }
    
    public void changeScreen() {
        game.setScreen(new VictoryScreen(game));
        stage.dispose();
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
        robotPosition.action(player, robotPosition.getTile());
        if (winCondition) {
            changeScreen();
        }
        stage.act();
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
    public void dispose() {
        industrialTile.dispose();
        robot.dispose();
    }

    @Override
    public void hide() {

    }

}
