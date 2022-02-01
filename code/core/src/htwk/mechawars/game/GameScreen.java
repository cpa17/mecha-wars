package htwk.mechawars.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;

import htwk.mechawars.ConfigReader;
import htwk.mechawars.GameOverScreen;
import htwk.mechawars.VictoryScreen;
import htwk.mechawars.board.Board;
import htwk.mechawars.board.Dir;
import htwk.mechawars.board.Robot;

/**
 * Class that presents the surface of the game screen.
 */
public class GameScreen implements Screen {
    private Game game;
    private static boolean winCondition = false;
    private static boolean loseCondition = false;

    static Stage stage;
    private SpriteBatch batch;
    private Sprite[] robotSprites;
    static Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

    static Button removeCardOrder = new TextButton("Loesche\nKartenreihenfolge", skin);
    static Button startExecutionButton = new TextButton("Ausfuehrung starten", skin);
    static Button wakeUpButton = new TextButton("WakeUp", skin);
    static Button shutDownButton = new TextButton("ShutDown", skin);
    private static Button currentCardShowButton = new TextButton("aktuelle Karte", skin);
    
    static Board board;

    /**
     * Constructor of class GameScreen.
     */
    public GameScreen(Game g, String fileName) {
        game = g;
        initBoard(fileName);
        
        setStage(new Stage());

        batch = new SpriteBatch();
        robotSprites = createSprites(ConfigReader.getPlayerNumber());

        Gdx.input.setInputProcessor(getStage());

        Robot.create();
        Board.create();

        addButtonsToStage(skin);
        Buttons.setButtons(Robot.getPlayers());
        addScrollPanelToStage(skin);
        startRobots(Robot.getPlayers());
    }
   

    
    private static void initBoard(String fileName) {
        board = new Board(fileName);
    }
    




    private Sprite[] createSprites(int numberRobots) {
        Sprite[] sprites = new Sprite[numberRobots];
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Sprite(new Texture("robotskins/robot" + (i + 1) + ".png"));
        }
        return sprites;
    }
    
    private void startRobots(Robot[] players) {
        for (int i = 0; i < players.length; i++) {

            board.startRobot(ConfigReader.getPlayerStartingPositions()[i].x, 
                    ConfigReader.getPlayerStartingPositions()[i].y, Dir.NORTH, players[i], false);
            
            if (i != 0) {
                boolean check = false;
                
                while (!check) {
                    
                    for (int j = (i - 1); j >= 0; j--) {
                    
                        int x1 = players[i].getXcoor();
                        int x2 = players[j].getXcoor();
                        int y1 = players[i].getYcoor();
                        int y2 = players[j].getYcoor();
                        
                        if (x1 == x2 && y1 == y2) {
                            board.startRobot(ConfigReader.getPlayerStartingPositions()[i].x, 
                                    ConfigReader.getPlayerStartingPositions()[i].y,
                                    Dir.NORTH, players[i], false);
                            break;
                        } else if (j == 0) {
                            check = true;
                        } else {
                            continue;
                        }
                    } 
                }
            }
        }   
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

        container.add(ScrollPanel.scrollPanel(skin)).grow();
    }

    /**
     * Function that adds the buttons to the Stage.
     * @param skin Object of class Skin.
     */
    public static void addButtonsToStage(Skin skin) {
        getStage().addActor(Buttons.startButton(Robot.getPlayers(), startExecutionButton, skin));
        getStage().addActor(Buttons.endButton(skin));

        getStage().addActor(Buttons.removeButton(removeCardOrder));

        getStage().addActor(Buttons.infoButton(skin));
        
        getStage().addActor(Buttons.currentCardShowButton(currentCardShowButton));

        getStage().addActor(Buttons.shutDownButton(Robot.getPlayers()[0], shutDownButton));
        getStage().addActor(Buttons.wakeUpButton(Robot.getPlayers()[0], wakeUpButton));
    }
    
    public static void setWinCondition(boolean win) {
        winCondition = win;
    }

    public static void setLoseCondition(boolean lose) {
        loseCondition = lose;
    }
    
    public void changeScreenWin() {
        game.setScreen(new VictoryScreen(game));
        getStage().dispose();
    }

    public void changeScreenLose() {
        game.setScreen(new GameOverScreen(game));
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
        Board.barriersToAsset(batch, board);
        Board.lasersToAsset(batch, board);
        Robot.getPlayers()[0].drawParameters(batch);
        for (int i = 0; i < Robot.getPlayers().length; i++) {
            Robot.getPlayers()[i].drawRobot(robotSprites[i], board);
            robotSprites[i].draw(batch);
        }
        batch.end();       
        if (winCondition) {
            changeScreenWin();
            GameScreen.setWinCondition(false);
        }
        if (loseCondition) {
            changeScreenLose();
            GameScreen.setLoseCondition(false);
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
        batch.dispose();
        stage.dispose();
        skin.dispose();
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
    
    public static Board getBoard() {
        return board;
    }


    public static void setBoard(Board board) {
        GameScreen.board = board;
    }
    
    public static TextButton getCurrentCardShowButton() {
        return (TextButton) currentCardShowButton;
    }

    public static void setCurrentCardShowButton(Button currentCardShowButton) {
        GameScreen.currentCardShowButton = currentCardShowButton;
    }

}
