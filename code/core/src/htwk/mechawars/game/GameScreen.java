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
import htwk.mechawars.ConfigReader;
import htwk.mechawars.MechaWars;
import htwk.mechawars.VictoryScreen;
import htwk.mechawars.ZugInitialisierung;
import htwk.mechawars.board.Board;
import htwk.mechawars.board.Dir;
import htwk.mechawars.board.Robot;

import java.io.IOException;

/**
 * Class that presents the surface of the game screen.
 */
public class GameScreen implements Screen {
    private Game game;
    private static boolean winCondition = false;

    private Texture industrialTile;

    static Stage stage;

    private SpriteBatch batch;
    private Sprite[] robotSprites;
    protected static ZugInitialisierung zugInitialisierung = new ZugInitialisierung();

    private static Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

    static Board board;
    static Robot[] players;

    /**
     * Constructor of class GameScreen.
     */
    public GameScreen(Game g, String fileName) {
        game = g;

        initBoard(fileName);

        setStage(new Stage());

        industrialTile = new Texture("mapAssets/StandardField.png");

        ConfigReader.setPlayerNumber(MechaWars.getPlayerNumber());
        try {
            ConfigReader.readConfigs();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("playernumber:  " + ConfigReader.getPlayerNumber());
        players = createRobots(ConfigReader.getPlayerNumber());
        batch = new SpriteBatch();
        robotSprites = createSprites(ConfigReader.getPlayerNumber());
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        addButtonsToStage(skin);
        addScrollPanelToStage(skin);
        startRobots(players);
    }

    private Robot[] createRobots(int numberRobots) {
        Robot[] robots = new Robot[numberRobots];
        for (int i = 0; i < robots.length; i++) {
            robots[i] = new Robot();
        }
        return robots;
    }

    private Sprite[] createSprites(int numberRobots) {
        Sprite[] sprites = new Sprite[numberRobots];
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Sprite(new Texture("..//assets//robotskins//robot" + (i + 1)
                    + ".png"));
        }
        return sprites;
    }

    private void startRobots(Robot[] players) {
        for (int i = 0; i < players.length; i++) {
            board.startRobot(ConfigReader.getPlayerStartingPositions()[i].x,
                    ConfigReader.getPlayerStartingPositions()[i].y, Dir.NORTH, players[i], false);
        }

    }

    private static void initBoard(String fileName) {
        board = new Board(fileName);
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

        container.add(ScrollPanel.scrollPanel(skin, players[0])).grow();
    }

    /**
     * Function that adds the buttons to the Stage.
     * @param skin Object of class Skin.
     */
    public static void addButtonsToStage(Skin skin) {
        getStage().addActor(Buttons.startButton(skin, players[0]));
        getStage().addActor(Buttons.endButton(skin));

        getStage().addActor(Buttons.removeButton(skin));

        if (players[0].getShutDown()) {
            Buttons.removeButton(skin).setTouchable(Touchable.disabled);
            Buttons.removeButton(skin).setDisabled(true);
        } else {
            Buttons.removeButton(skin).setTouchable(Touchable.enabled);
            Buttons.removeButton(skin).setDisabled(false);
        }

        getStage().addActor(Buttons.infoButton(skin));

        if (players[0].getShutDown()) {
            Buttons.shutDownButton(skin, players[0]).setTouchable(Touchable.disabled);
            Buttons.wakeUpButton(skin, players[0]).setTouchable(Touchable.enabled);
            Buttons.shutDownButton(skin, players[0]).setDisabled(true);
            Buttons.wakeUpButton(skin, players[0]).setDisabled(false);
        } else {
            Buttons.shutDownButton(skin, players[0]).setTouchable(Touchable.enabled);
            Buttons.wakeUpButton(skin, players[0]).setTouchable(Touchable.disabled);
            Buttons.shutDownButton(skin, players[0]).setDisabled(false);
            Buttons.wakeUpButton(skin, players[0]).setDisabled(true);
        }

        getStage().addActor(Buttons.shutDownButton(skin, players[0]));
        getStage().addActor(Buttons.wakeUpButton(skin, players[0]));

    }
    
    public static void setWinCondition(boolean win) {
        winCondition = win;
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
        players[0].drawParameters(batch);

        for(int i = 0; i < MechaWars.getPlayerNumber(); i++) {
            players[i].drawRobot(robotSprites[i], board);
        }

        for (Sprite sprite : robotSprites) {
            sprite.draw(batch);
        }
        batch.end();       
        if (winCondition) {
            changeScreen();
            GameScreen.setWinCondition(false);
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
        for (Sprite sprite : robotSprites) {
            sprite.getTexture().dispose();
        }
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
