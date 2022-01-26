package htwk.mechawars.board;

import htwk.mechawars.ConfigReader;
import htwk.mechawars.ai.AiManager;
import htwk.mechawars.cards.Card;
import htwk.mechawars.cards.Deck;
import htwk.mechawars.cards.Type;
import htwk.mechawars.fields.BlackHole;
import htwk.mechawars.fields.Pusher;
import htwk.mechawars.fields.Checkpoint;
import htwk.mechawars.fields.ConveyorBelt;
import htwk.mechawars.fields.ExpressConveyorBelt;
import htwk.mechawars.fields.Field;
import htwk.mechawars.fields.Gear;
import htwk.mechawars.fields.RepairSite;
import htwk.mechawars.fields.StandardField;
import htwk.mechawars.fields.StartField;
import htwk.mechawars.game.ScrollPanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import htwk.mechawars.game.Buttons;
import htwk.mechawars.game.GameScreen;

//import ai.AiCardGeneration;

/**
 * Class that presents the game board.
 */
public class Board {
    public Field[][] fieldmatrix;
    private Field robotPosition;
    private static int checkpoint;

    /**
     * Method that reads the game plan as a int matrix from a file and constructs the game board
     * as a field matrix.
     *
     * @param fileName Path to a file containing a map
     */
    public Board(String fileName) {
        FileHandle file = Gdx.files.internal(fileName);
        String mapString = file.readString();

        Board wrappedBoard = new Board(mapString, false);
        this.fieldmatrix = wrappedBoard.fieldmatrix;
    }

    /**
     * Method that reads the game plan as a int matrix from a file and constructs the game board
     * as a field matrix, but can skip creating the field matrix.
     *
     * @param mapString String containing a map
     * @param isTest indicates that this is a test
     */
    public Board(String mapString, boolean isTest) {
        ArrayList<ArrayList<Integer>> tempLayout = new ArrayList<>();
           
        String[] linesArray = mapString.split("\\r?\\n");
        String currentLine;

        Scanner scn = new Scanner(mapString);
        String s;
        while (scn.hasNext()) {
            s = scn.next();
            try {
                Integer.parseInt(s); 
            } catch (NumberFormatException z) {
                System.out.println("The map obtains elements which are not integer!");
                //Gdx.app.exit();
            }
        }

        for (String value : linesArray) {
            currentLine = value;
            ArrayList<Integer> row = new ArrayList<>();
            String[] values = currentLine.trim().split(" ");
            for (String string : values) {
                if (values.length > 16) {
                    System.out.println("The map has too many columns, only 16 are allowed!");
                    //Gdx.app.exit();
                }
                if (!string.isEmpty()) {
                    int id = Integer.parseInt(string);
                    row.add(id);
                }
            }
            tempLayout.add(row);
        }
        scn.close();

        System.out.println(tempLayout);

        int width = tempLayout.get(0).size();
        int height = tempLayout.size();
        
        if (height > 16) {
            System.out.println("The map has too many rows, only 16 are allowed!");
            //Gdx.app.exit();
        }

        int[][] matrix = new int[width][height];

        /*
         * matrix should be [col][cell], while tempLayout is [row][cell].
         *                  [x]  [y]                         [y]  [x]
         * Therefore we need to switch them around.
         */
        for (int col = 0; col < width; col++) {
            for (int cell = 0; cell < height; cell++) {
                matrix[col][cell] = tempLayout.get(cell).get(col);
            }
        }

        intToFieldMatrix(matrix, isTest);
    }

    /**
     * Method that creates a field matrix from a int matrix.
     *
     * @param matrix A int matrix
     * @param isTest indicates that this is a test
     */
    private void intToFieldMatrix(int[][] matrix, boolean isTest) {

        this.fieldmatrix = new Field[matrix.length][matrix[0].length];

        int[] allowed;

        boolean barrierLeft;
        boolean barrierTop;
        boolean barrierRight;
        boolean barrierBottom;

        for (int col = 0; col < matrix.length; col++) {
            for (int cell = 0; cell < matrix[col].length; cell++) {

                // Takes the 1st digit, which represents the attribute laserVertical
                int laserVertical = matrix[col][cell] / 100000000;
                allowed = new int[]{1, 2, 3, 4, 5, 6, 9};
                if (Arrays.stream(allowed).noneMatch(x -> x == laserVertical)) {
                    System.out.println("Codierung " + laserVertical
                            + " beschreibt keinen gültigen Wert für das Attribut laserVertical");
                }

                // Takes the 2nd digit, which represents the attribute laserHorizontal
                int laserHorizontal = (matrix[col][cell] / 10000000) % 10;
                allowed = new int[]{1, 2, 3, 4, 5, 6, 9};
                if (Arrays.stream(allowed).noneMatch(x -> x == laserHorizontal)) {
                    System.out.println("Codierung " + laserVertical
                            + " beschreibt keinen gültigen Wert für das Attribut laserHorizontal");
                }

                // Takes the 3rd digit, which represents the attribute barrierLeft
                if (((matrix[col][cell] / 1000000) % 10) == 1) {
                    barrierLeft = true;
                } else {
                    barrierLeft = false;
                }

                // Takes the 4th digit, which represents the attribute barrierTop
                if (((matrix[col][cell] / 100000) % 10) == 1) {
                    barrierTop = true;
                } else {
                    barrierTop = false;
                }

                // Takes the 5th digit, which represents the attribute barrierRight
                if (((matrix[col][cell] / 10000) % 10) == 1) {
                    barrierRight = true;
                } else {
                    barrierRight = false;
                }

                // Takes the 6th digit, which represents the attribute barrierBottom
                if (((matrix[col][cell] / 1000) % 10) == 1) {
                    barrierBottom = true;
                } else {
                    barrierBottom = false;
                }

                // Switch with the 7th digits that represent the subclass
                switch ((matrix[col][cell] / 100) % 10) {

                    // BlackHole
                    case 0:
                        fieldmatrix[col][cell] = new BlackHole(col, cell, laserVertical,
                                laserHorizontal, barrierLeft, barrierTop, barrierRight,
                                barrierBottom, isTest);
                        break;

                    // Pusher
                    case 1:
                        // Takes the 9th digit, which represents the attribute type
                        // from the subclass Pusher
                        int typeB = matrix[col][cell] % 10;
                        allowed = new int[]{1, 2, 3, 4};
                        if (Arrays.stream(allowed).anyMatch(x -> x == typeB)) {
                            this.fieldmatrix[col][cell] = new Pusher(col, cell, typeB,
                                    laserVertical, laserHorizontal, barrierLeft, barrierTop,
                                    barrierRight, barrierBottom, isTest);
                        } else {
                            System.out.println("Codierung " + matrix[col][cell]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    // Checkpoint
                    case 2:
                        // Takes the 9th digit, which represents the attribute number
                        // from the subclass Checkpoint
                        int numberC = matrix[col][cell] % 10;
                        allowed = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
                        if (Arrays.stream(allowed).anyMatch(x -> x == numberC)) {
                            this.fieldmatrix[col][cell] = new Checkpoint(col, cell, numberC,
                                    laserVertical, laserHorizontal, barrierLeft, barrierTop,
                                    barrierRight, barrierBottom, isTest);
                            checkpoint++;
                        } else {
                            System.out.println("Codierung " + matrix[col][cell]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    // ConveyorBelt
                    case 3:
                        // Takes the 8th digit, which represents the attribute start
                        // from the subclass ConveyorBelt
                        int startC = (matrix[col][cell] / 10) % 10;
                        // Takes the 9th digit, which represents the attribute end
                        // from the subclass ConveyorBelt
                        int endC = matrix[col][cell] % 10;
                        allowed = new int[]{21, 31, 41, 61, 71, 91, 2, 12, 32, 42, 52, 92,
                                3, 13, 23, 43, 63, 83, 14, 24, 34, 54, 74, 84};
                        if (Arrays.stream(allowed).anyMatch(x -> x == (10 * startC) + endC)) {
                            this.fieldmatrix[col][cell] = new ConveyorBelt(col, cell, startC,
                                    endC, laserVertical, laserHorizontal, barrierLeft, barrierTop,
                                    barrierRight, barrierBottom, isTest);
                        } else {
                            System.out.println("Codierung " + matrix[col][cell]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    // ExpressConveyorBelt
                    case 4:
                        // Takes the 8th digit, which represents the attribute start
                        // from the subclass ExpressConveyorBelt
                        int startEc = (matrix[col][cell] / 10) % 10;
                        // Takes the 9th digit, which represents the attribute end
                        // from the subclass ExpressConveyorBelt
                        int endEc = matrix[col][cell] % 10;
                        allowed = new int[]{21, 31, 41, 61, 71, 91, 2, 12, 32, 42, 52, 92,
                                3, 13, 23, 43, 63, 83, 14, 24, 34, 54, 74, 84};
                        if (Arrays.stream(allowed).anyMatch(x -> x == (10 * startEc) + endEc)) {
                            this.fieldmatrix[col][cell] = new ExpressConveyorBelt(col, cell,
                                    startEc, endEc, laserVertical, laserHorizontal, barrierLeft,
                                    barrierTop, barrierRight, barrierBottom, isTest);
                        } else {
                            System.out.println("Codierung " + matrix[col][cell]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    // Gear
                    case 5:
                        // Takes the 9th digit, which represents the attribute direction
                        // from the subclass Gear
                        int direction = matrix[col][cell] % 10;
                        allowed = new int[]{1, 2};
                        if (Arrays.stream(allowed).anyMatch(x -> x == direction)) {
                            this.fieldmatrix[col][cell] = new Gear(col, cell, direction,
                                    laserVertical, laserHorizontal, barrierLeft, barrierTop,
                                    barrierRight, barrierBottom, isTest);
                        } else {
                            System.out.println("Codierung " + matrix[col][cell]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    // RepairSite
                    case 6:
                        // Takes the 9th digit, which represents the attribute type
                        // from the subclass RepairSite
                        int typeR = matrix[col][cell] % 10;
                        allowed = new int[]{1, 2};
                        if (Arrays.stream(allowed).anyMatch(x -> x == typeR)) {
                            this.fieldmatrix[col][cell] = new RepairSite(col, cell, typeR,
                                    laserVertical, laserHorizontal, barrierLeft, barrierTop,
                                    barrierRight, barrierBottom, isTest);
                        } else {
                            System.out.println("Codierung " + matrix[col][cell]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    // StandardField
                    case 7:
                        this.fieldmatrix[col][cell] = new StandardField(col, cell, laserVertical,
                                laserHorizontal, barrierLeft, barrierTop, barrierRight,
                                barrierBottom, isTest);
                        break;

                    // StartField
                    case 8:
                        // Takes the 9th digit, which represents the attribute number
                        // from the subclass StartField
                        int numberS = matrix[col][cell] % 10;
                        allowed = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
                        if (Arrays.stream(allowed).anyMatch(x -> x == numberS)) {
                            this.fieldmatrix[col][cell] = new StartField(col, cell, numberS,
                                    laserVertical, laserHorizontal, barrierLeft, barrierTop,
                                    barrierRight, barrierBottom, isTest);
                        } else {
                            System.out.println("Codierung " + matrix[col][cell]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    default:
                        System.out.println("Codierung " + matrix[col][cell]
                                + " beschreibt kein gueltige Feldklasse");
                        break;
                }
            }
        }
    }

    /**
     * Method that create a String from a field matrix that contains for each object the class
     * and the attribute values.
     *
     * @return A String with the class and the attribute values for each field object
     */
    public String toString() {
        String fieldstring = "";
        for (Field[] fields : fieldmatrix) {
            for (Field field : fields) {
                fieldstring = fieldstring + "(" + field.getClass() + ", "
                        + field + ") ";
            }
            fieldstring = fieldstring + "\n";
        }
        return fieldstring;
    }

    /**
     * Method that draws any board matrix as Textures.
     *
     * @param batch SpriteBatch to draw the Textures
     * @param board Board whose matrix is to be converted into a string
     */
    public static void toAsset(SpriteBatch batch, Board board) {
        for (int row = 0; row < board.fieldmatrix[0].length; row++) {
            int x = 0;
            for (int cell = 0; cell < board.fieldmatrix.length; cell++) {
                int t = Gdx.graphics.getHeight() / board.fieldmatrix[0].length; //height of one tile
                int b = Gdx.graphics.getHeight(); //height of the entire board
                int c = (row + 1) * t; //the current height in the loop
                int r = b - c; //the result of the board height minus the current height
                batch.draw(board.fieldmatrix[cell][row].getTile(), x, r, t, t);
                x = x + (Gdx.graphics.getHeight() / board.fieldmatrix[0].length);
            }
        }
    }

    /**
     * Method that draws the barriers as textures.
     *
     * @param batch SpriteBatch to draw the barrier textures
     * @param board Board whose matrix is to be converted into a string
     */
    public static void barriersToAsset(SpriteBatch batch, Board board) {
        Texture currentTile;
        for (int row = 0; row < board.fieldmatrix[0].length; row++) {
            int x = 0;
            for (int cell = 0; cell < board.fieldmatrix.length; cell++) {
                int t = Gdx.graphics.getHeight() / board.fieldmatrix[0].length; //height of one tile
                int b = Gdx.graphics.getHeight(); //height of the entire board
                int c = (row + 1) * t; //the current height in the loop
                int r = b - c; //the result of the board height minus the current height
                // if there is a barrier on the left of the current field
                if (board.fieldmatrix[cell][row].getBarrierLeft()) {
                    currentTile = new Texture(Gdx.files.internal(
                            "mapAssets/barriers/BarrierSide1.png"));
                    batch.draw(currentTile, x, r, t, t);
                }
                // if there is a barrier on the top of the current field
                if (board.fieldmatrix[cell][row].getBarrierTop()) {
                    currentTile = new Texture(Gdx.files.internal(
                            "mapAssets/barriers/BarrierSide2.png"));
                    batch.draw(currentTile, x, r, t, t);
                }
                // if there is a barrier on the right of the current field
                if (board.fieldmatrix[cell][row].getBarrierRight()) {
                    currentTile = new Texture(Gdx.files.internal(
                            "mapAssets/barriers/BarrierSide3.png"));
                    batch.draw(currentTile, x, r, t, t);
                }
                // if there is a barrier on the bottom of the current field
                if (board.fieldmatrix[cell][row].getBarrierBottom()) {
                    currentTile = new Texture(Gdx.files.internal(
                            "mapAssets/barriers/BarrierSide4.png"));
                    batch.draw(currentTile, x, r, t, t);
                }
                x = x + (Gdx.graphics.getHeight() / board.fieldmatrix[0].length);
            }
        }
    }
    
    /**
     * Method that draws the laser as textures.
     *
     * @param batch SpriteBatch to draw the laser textures
     * @param board Board whose matrix is to be converted into a string
     */
    public static void lasersToAsset(SpriteBatch batch, Board board) {
        Texture currentTile;
        int v;
        int h;
    
        for (int row = 0; row < board.fieldmatrix[0].length; row++) {
            int x = 0;
            for (int cell = 0; cell < board.fieldmatrix.length; cell++) {
                v = board.fieldmatrix[cell][row].getLaserVertical();
                h = board.fieldmatrix[cell][row].getLaserHorizontal();
        
                int t = Gdx.graphics.getHeight() / board.fieldmatrix[0].length; //height of one tile
                int b = Gdx.graphics.getHeight(); //height of the entire board
                int c = (row + 1) * t; //the current height in the loop
                int r = b - c; //the result of the board height minus the current height
                // if there is a vertical laser
                if (v == 1 || v == 2 || v == 3) {
                    currentTile = new Texture(Gdx.files.internal(
                            "mapAssets/laser/LaserV" + v + ".png"));
                    batch.draw(currentTile, x, r, t, t);
                }
                // if there is a horizontal laser
                if (h == 1 || h == 2 || h == 3) {
                    currentTile = new Texture(Gdx.files.internal(
                            "mapAssets/laser/LaserH" + h + ".png"));
                    batch.draw(currentTile, x, r, t, t);
                }
                //if there is a start of one or more vertical laser
                //0 - BarrierTop, 1 - BarrierBottom
                if (v == 4 || v == 5 || v == 6) {
                    if (board.fieldmatrix[cell][row].getBarrierTop()) {
                        currentTile = new Texture(Gdx.files.internal(
                                "mapAssets/laser/LaserSV" + v + "0.png"));
                        batch.draw(currentTile, x, r, t, t);
                    }

                    if (board.fieldmatrix[cell][row].getBarrierBottom()) {
                        currentTile = new Texture(Gdx.files.internal(
                                "mapAssets/laser/LaserSV" + v + "1.png"));
                        batch.draw(currentTile, x, r, t, t); 
                    }
                }
                //if there is a start of one or more horizontal laser
                //0 - BarrierLeft, 1 - BarrierRight
                if (h == 4 || h == 5 || h == 6) {
                    if (board.fieldmatrix[cell][row].getBarrierLeft()) {
                        currentTile = new Texture(Gdx.files.internal(
                                "mapAssets/laser/LaserSH" + h + "0.png"));
                        batch.draw(currentTile, x, r, t, t); 
                    }

                    if (board.fieldmatrix[cell][row].getBarrierRight()) {
                        currentTile = new Texture(Gdx.files.internal(
                                "mapAssets/laser/LaserSH" + h + "1.png"));
                        batch.draw(currentTile, x, r, t, t); 
                    }
                }
                x = x + (Gdx.graphics.getHeight() / board.fieldmatrix[0].length);
            }
        }
    }

    /**
     * Method that places a robot in the matrix --> starting position.
     *
     * @param x x-coordinate of the robot
     * @param y y-coordinate of the robot
     * @param dir direction of the robot
     * @param robot robot to which this applies
     * @param isTest indicates that this is a test
     */
    public void startRobot(int x, int y, Dir dir, Robot robot, boolean isTest) {
        int min = 1;
        int max = 0;

        for (int i = 0; i < fieldmatrix.length; i++) {
            for (int j = 0; j < fieldmatrix[i].length; j++) {
                if (fieldmatrix[i][j] instanceof StartField &&
                        ((StartField) fieldmatrix[i][j]).getNumber() >= max) {
                    max = ((StartField) fieldmatrix[i][j]).getNumber();
                }
            }
        }

        int randomNumber =  ThreadLocalRandom.current().nextInt(min, max + 1);

        if (!isTest) {
            for (int i = 0; i < fieldmatrix.length; i++) {
                for (int j = 0; j < fieldmatrix[i].length; j++) {
                    if (fieldmatrix[i][j] instanceof StartField &&
                            ((StartField) fieldmatrix[i][j]).getNumber() == randomNumber) {
                        x = fieldmatrix[i][j].getXcoor();
                        y = fieldmatrix[i][j].getYcoor();
                    }
                }
            }
        }

        robot.setXcoor(x);
        robot.setbackupCopyX(x);
        robot.setYcoor(y);
        robot.setbackupCopyY(y);
        robot.setDir(dir);
    }

    /**
     * This is a wrapper-function for the tests.
     *
     * @param players array of all players
     */
    public void move(Robot[] players) {
        move(players, false);
    }

    /**
     * Function that initialises Movement for the Robots.
     * @param players array of all players
     * @param isTest indicates that this is a test
     */
    public void move(Robot[] players, boolean isTest) {

        int maxCardCount = 0; /* keeps track of the max number of cards any robot has
                                -> determines the number of turns*/
        LinkedList<LinkedList<Card>> allCards = new LinkedList<LinkedList<Card>>();
        for (int i = 0; i < ConfigReader.getPlayerNumber(); i++) {
            if (i == 0) {
                allCards.add(players[0].getSelectedCards()); /* List of Lists of cards,
                                                            each List for one Players cards*/
                maxCardCount = players[0].getSelectedCards().size();
            } else {
                if (ConfigReader.getAimodes()[i] != 0) {
                    AiManager m = new AiManager();
                    LinkedList<Card> generatedCards =
                            m.getAi(ConfigReader.getAimodes()[i])
                            .generateCards(ScrollPanel.getDeck().getDeck(), 
                                    i, getNumberOfChoosableCards(players[i].getDamagePoints()));
                    // Random Cards generation for the AI-Players
                    allCards.add(generatedCards);
                    maxCardCount = Integer.max(generatedCards.size(), maxCardCount);
                } else {
                    allCards.add(new LinkedList<Card>());
                }
            }
        }
        allCards = Deck.transposeList(maxCardCount, allCards);  /*turns a list of List of Cards
                                                                    for each Player into a list
                                                               of lists of cards for each turn*/

        final LinkedList<LinkedList<Card>> allCard = allCards;

        if (players.length != 1 || allCard.size() != 0) {
            if (!isTest) {
                Timer.schedule(new Task() {
                    int turnCounter = 0;

                    @Override
                    public void run() {
                        moveSingleTurn(allCard.get(turnCounter), players, false);
                        turnCounter++;
                        }
                    }, 0, ConfigReader.getPlayerNumber() + 1,
                        allCards.size() - 1);

            } else {
                for (int i = 0; i < allCards.size(); i++) {
                    moveSingleTurn(allCard.get(i), players, true);
                }
            }
        }

        if (!isTest) {
            // Delay for the state-function to run so that the robot has reached his final position
            Timer.schedule(new Task() {

                @Override
                public void run() {
                    state(players);
                    checkDoubleDamage(players);
                    checkShutDown(players);
                    checkGameOver(players);
                }
            }, (ConfigReader.getPlayerNumber() * 5) + 5);

            // Calls cardAction after all robots have finished with their x. card
            for (int i = ConfigReader.getPlayerNumber();
                        i <= (ConfigReader.getPlayerNumber() * 5) + 4;
                                i = i + ConfigReader.getPlayerNumber() + 1) {

                Timer.schedule(new Task() {

                    @Override
                    public void run() {

                        // First the ExpressConveyorBelts move the robot forward one field
                        for (Robot robot : players) {
                            robotPosition = fieldmatrix[robot.getXcoor()][robot.getYcoor()];
                            if (robotPosition instanceof ExpressConveyorBelt) {
                                robotPosition.cardAction(robot);
                            }
                        }

                        checkRobotsOnBoard(players);
                        checkRobotsOnSamePosition(players);

                        // Then the ExpressConveyorBelts an ConveyorBelts move the robot
                        // forward one field
                        for (Robot robot : players) {
                            robotPosition = fieldmatrix[robot.getXcoor()][robot.getYcoor()];
                            if (robotPosition instanceof ExpressConveyorBelt ||
                                    robotPosition instanceof ConveyorBelt) {
                                robotPosition.cardAction(robot);
                            }
                        }

                        checkRobotsOnBoard(players);
                        checkRobotsOnSamePosition(players);

                        // Then all remaining cardActions are called
                        for (Robot robot : players) {
                            robotPosition = fieldmatrix[robot.getXcoor()][robot.getYcoor()];
                            if (!(robotPosition instanceof ExpressConveyorBelt) &&
                                    !(robotPosition instanceof ConveyorBelt)) {
                                robotPosition.cardAction(robot);
                            }
                        }

                        checkRobotsOnBoard(players);

                        Robot.setPlayers(players);
                        checkDestroyed(players);
                        checkRobotLaser(players);
                        checkBoardLaser(players);
                        checkDestroyed(players);
                    }
                }, i);
            }
        } else {

            // No delay if this is a test
            checkDestroyed(players);
            checkRobotLaser(players);
            checkBoardLaser(players);
            checkDestroyed(players);
            state(players);
            checkDoubleDamage(players);
            checkShutDown(players);
            checkGameOver(players);
        }
    }

    /**
     * Method that moves the robot in the matrix.
     *
     * @param phase List of cards
     * @param robots array of all robots
     * @param isTest indicates that this is a test
     */

    public void moveSingleTurn(LinkedList<Card> phase, Robot[] robots, boolean isTest) {

        if (isTest) {
            for (Card card : phase) {
                robotMovement(card, robots[card.getCardPlayerNumber()], robots);
            }
        } else {
            // delay in seconds, increments for each card in the linked list for a second
            int i = 0;
            for (Card card : phase) {
                Timer.schedule(new Task() {

                    @Override
                    public void run() {
                        Buttons.currentCardShowButton(robots[card.getCardPlayerNumber()], GameScreen.currentCardShowButton, card);
                        robotMovement(card, robots[card.getCardPlayerNumber()], robots);
                    }
                }, i);
                i += 1;
            }
        }
    }

    /**
     * Outsourced code from the move function, that would otherwise be duplicated.
     *
     * @param card Current card
     * @param robot The robot that should move
     * @param players array of all players
     */
    public void robotMovement(Card card, Robot robot, Robot[] players) {
        if (robot.getDestroyed()) {
            return;
        }

        if (card.getCardAttributeType() == Type.mov) {
            robot.moveInDirectionByCard(fieldmatrix, card.getCardAttributeMovCount(), players);
        } else {
            robot.turn(card.getCardAttributeMovCount());
        }
        checkRobotsOnBoard(players);
        Robot.setPlayers(players);
    }

    /**
     * Outsourced code from the move function, that would otherwise be duplicated.
     *
     * @param players array of all players
     */
    public void state(Robot[] players) {
        for (Robot player : players) {
            player.setLastRound(player.getShutDown());
            player.setShutDown(player.getNextRound());
        }
    }

    /**
     * Method that checks whether robots are on the same position because of a
     * (Express)ConveyorBelt. If so, one or both robots will be set to their previous position,
     * because robots never push other robots because of a (Express)ConveyorBelt.
     *
     * @param players array of all players
     */
    private void checkRobotsOnSamePosition(Robot[] players) {
        for (Robot robotA : players) {
            for (Robot robotB : players) {
                if (robotA != robotB && robotA.getXcoor() == robotB.getXcoor()
                        && robotA.getYcoor() == robotB.getYcoor()) {
                    if (robotA.getLastMovementByConveyor()) {
                        robotA.setXcoor(robotA.getLastConveyorField().getXcoor());
                        robotA.setYcoor(robotA.getLastConveyorField().getYcoor());
                        robotA.setLastMovementByConveyor(false);
                    }
                    if (robotB.getLastMovementByConveyor()) {
                        robotB.setXcoor(robotB.getLastConveyorField().getXcoor());
                        robotB.setYcoor(robotB.getLastConveyorField().getYcoor());
                        robotB.setLastMovementByConveyor(false);
                    }
                }
            }
        }
    }

    /**
     * Method that checks whether all robots still on the board.
     *
     * @param players array of all players
     */
    private void checkRobotsOnBoard(Robot[] players) {
        for (Robot player : players) {
            if (player.getXcoor() >= fieldmatrix.length
                    || player.getYcoor() >= fieldmatrix[0].length
                    || player.getXcoor() < 0 || player.getYcoor() < 0) {
                player.setDamage(10);
                player.setXcoor(player.getbackupCopyX());
                player.setYcoor(player.getbackupCopyY());
            }
        }
    }

    /**
     * Method that checks whether the robot receives 2 damage points.
     *
     * @param players array of all players
     */
    private void checkDoubleDamage(Robot[] players) {
        for (Robot player : players) {
            if ((!player.getShutDown() && player.getLastRound()) || player.getDestroyed()) {

                if (player.getDestroyed()) {
                    player.lifeDown();
                    player.setDestroyed(false);
                    player.damageReset();
                }
                player.damageUp();
                player.damageUp();
            }
        }
    }

    /**
     * Method that checks whether the robot is in shutdown mode.
     *
     * @param players array of all players
     */
    private void checkShutDown(Robot[] players) {
        for (Robot player : players) {
            if (player.getShutDown() || player.getNextRound()) {
                player.damageReset();
            }
        }
    }
    
    /**
     * Method that checks whether the robot is destroyed.
     *
     * @param players array of all players
     */
    private void checkDestroyed(Robot[] players) {
        for (Robot player : players) {
            if (player.getDamagePoints() >= 10) {
                player.setDestroyed(true);
                player.setXcoor(player.getbackupCopyX());
                player.setYcoor(player.getbackupCopyY());
            }
        }
    }

    /**
     * Method that checks whether the robot is destroyed.
     *
     * @param players array of all players
     */
    private void checkGameOver(Robot[] players) {
        for (Robot player : players) {
            if (player.getLifePoints() <= 0) {
                player.setDamage(10);
            }
        }

        Robot player = players[0];

        if (player.getLifePoints() <= 0) {
            GameScreen.setLoseCondition(true);
        }
    }

    /**
     * Method that checks whether the robot is being shot at by a laser.
     *
     * @param players A array of robots
     */
    public void checkBoardLaser(Robot[] players) {
        
        //for when a robot has been hit
        int flag;
        int q;
        //for horizontal
        int h;
        //for vertical
        int v;

        for (int i = 0; i < this.fieldmatrix.length; i++) {
            for (int j = 0; j < this.fieldmatrix[i].length; j++) {
                
                v = this.fieldmatrix[i][j].getLaserVertical();
                h = this.fieldmatrix[i][j].getLaserHorizontal();
                
                if (v == 4 || v == 5 || v == 6) {
                    
                    // BarrierTop
                    if (this.fieldmatrix[i][j].getBarrierTop()) {
                        flag = 0;
                        q = 1;

                        while (flag == 0) {

                            for (int s = 0; (s < players.length) && (flag == 0); s++) {
                                int x = players[s].getXcoor();
                                int y = players[s].getYcoor();
                                if ((x == fieldmatrix[i][j + q - 1].getXcoor())
                                        && (y == fieldmatrix[i][j + q - 1].getYcoor())) {

                                    for (int z = 3; z < v; z++) {
                                        players[s].damageUp();
                                    }
                                    if (fieldmatrix[i][j + q - 1].getBarrierBottom()) {
                                        flag = 1;
                                    }
                                    flag = 1;
                                }
                            }
                                
                            if ((j + q + 1) < fieldmatrix[0].length) {
                                if (fieldmatrix[i][j  + q + 1].getBarrierTop()) {
                                    flag = 1;
                                }
                                if (fieldmatrix[i][j + q].getLaserVertical() == (v - 3)) {
                                    q = q + 1;
                                } else {
                                    flag = 1;
                                }
                            }
                        }

                    // BarrierBottom
                    } else {
                        flag = 0;
                        q = 1;

                        while (flag == 0) {

                            for (int s = 0; (s < players.length) && (flag == 0); s++) {
                                int x = players[s].getXcoor();
                                int y = players[s].getYcoor();
                                if ((x == fieldmatrix[i][j - q + 1].getXcoor())
                                        && (y == fieldmatrix[i][j - q + 1].getYcoor())) {

                                    for (int z = 3; z < v; z++) {
                                        players[s].damageUp();
                                    }
                                    if (fieldmatrix[i][j - q + 1].getBarrierTop()) {
                                        flag = 1;
                                    }
                                    flag = 1;
                                }
                            }
                                
                            if ((j - q - 1) >= 0) {
                                if (fieldmatrix[i][j  - q - 1].getBarrierBottom()) {
                                    flag = 1;
                                }
                                if (fieldmatrix[i][j - q].getLaserVertical() == (v - 3)) {
                                    q = q + 1;
                                } else {
                                    flag = 1;
                                }
                            }
                        }
                    }
                }
                
                if (h == 4 || h == 5 || h == 6) {
                    
                    // BarrierLeft
                    if (this.fieldmatrix[i][j].getBarrierLeft()) {
                        flag = 0;
                        q = 1;

                        while (flag == 0) {

                            for (int s = 0; (s < players.length) && (flag == 0); s++) {
                                int x = players[s].getXcoor();
                                int y = players[s].getYcoor();
                                if ((x == fieldmatrix[i + q - 1][j].getXcoor())
                                        && (y == fieldmatrix[i + q - 1][j].getYcoor())) {

                                    for (int z = 3; z < h; z++) {
                                        players[s].damageUp();
                                    }
                                    if (fieldmatrix[i + q - 1][j].getBarrierRight()) {
                                        flag = 1;
                                    }
                                    flag = 1;
                                }
                            }
                                
                            if ((i + q + 1) < fieldmatrix.length) {
                                if (fieldmatrix[i + q + 1][j].getBarrierLeft()) {
                                    flag = 1;
                                }
                                if (fieldmatrix[i + q][j].getLaserHorizontal() == (h - 3)) { 
                                    q = q + 1;
                                } else {
                                    flag = 1;
                                }
                            }                   
                        }

                    // BarrierRight
                    } else {
                        flag = 0;
                        q = 1;

                        while (flag == 0) {

                            for (int s = 0; (s < players.length) && (flag == 0); s++) {
                                int x = players[s].getXcoor();
                                int y = players[s].getYcoor();
                                if ((x == fieldmatrix[i - q + 1][j].getXcoor())
                                        && (y == fieldmatrix[i - q + 1][j].getYcoor())) {

                                    for (int z = 3; z < h; z++) {
                                        players[s].damageUp();
                                    }
                                    if (fieldmatrix[i - q + 1][j].getBarrierLeft()) {
                                        flag = 1;
                                    }
                                    flag = 1;
                                }
                            }
                                
                            if ((i - q - 1) >= 0) {
                                if (fieldmatrix[i - q - 1][j].getBarrierRight()) {
                                    flag = 1;
                                }
                                if (fieldmatrix[i - q][j].getLaserHorizontal() == (h - 3)) {
                                    q = q + 1;
                                } else {
                                    flag = 1;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
                
    /**
     * Method that checks if a robot got hit by a Laser of another robot.
     *
     * @param players an array of robots
     */
    public void checkRobotLaser(Robot[] players) {

        for (Robot player : players) {
            if (player.getLifePoints() > 0 && !player.getDestroyed() && !player.getShutDown()) {

                int x = player.getXcoor();
                int y = player.getYcoor();

                //the variable z, checks if a player or a wall already have been hit
                int z = 0;

                switch (player.getDir()) {

                    case NORTH:

                        if (this.fieldmatrix[x][y].getBarrierTop()) {
                            break;
                        }

                        //i is the next tile the robot is facing
                        for (int i = (y - 1); i >= 0 && (z == 0); i--) {

                            if (this.fieldmatrix[x][i].getBarrierBottom()) {
                                break;
                            }
                            if (this.fieldmatrix[x][i].getBarrierTop()) {
                                z++;
                            }

                            /* checks if one of the players is on the current field [x][i2], if yes
                            gets damage and z becomes 1, so the loop breaks */
                            for (Robot robot : players) {

                                int x2 = robot.getXcoor();
                                int y2 = robot.getYcoor();

                                if (x2 == x && y2 == i) {
                                    robot.damageUp();
                                    z++;
                                }
                            }
                        }
                        break;

                    case SOUTH:

                        if (this.fieldmatrix[x][y].getBarrierBottom()) {
                            break;
                        }

                        //i is the next tile the robot is facing
                        for (int i = (y + 1); i < this.fieldmatrix[0].length && (z == 0); i++) {

                            if (this.fieldmatrix[x][i].getBarrierBottom()) {
                                z++;
                            }
                            if (this.fieldmatrix[x][i].getBarrierTop()) {
                                break;
                            }

                            /* checks if one of the players is on the current field [x][i2], if yes
                            gets damage and z becomes 1, so the loop breaks */
                            for (Robot robot : players) {

                                int x2 = robot.getXcoor();
                                int y2 = robot.getYcoor();

                                if (x2 == x && y2 == i) {
                                    robot.damageUp();
                                    z++;
                                }
                            }
                        }
                        break;

                    case EAST:

                        if (this.fieldmatrix[x][y].getBarrierRight()) {
                            break;
                        }

                        //i is the next tile the robot is facing
                        for (int i = (x + 1); i < this.fieldmatrix.length && (z == 0); i++) {

                            if (this.fieldmatrix[i][y].getBarrierRight()) {
                                z++;
                            }
                            if (this.fieldmatrix[i][y].getBarrierLeft()) {
                                break;
                            }

                            /* checks if one of the players is on the current field [x][i2], if yes
                            gets damage and z becomes 1, so the loop breaks */
                            for (Robot robot : players) {

                                int x2 = robot.getXcoor();
                                int y2 = robot.getYcoor();

                                if (x2 == i && y2 == y) {
                                    robot.damageUp();
                                    z++;
                                }
                            }
                        }
                        break;

                    case WEST:

                        if (this.fieldmatrix[x][y].getBarrierLeft()) {
                            break;
                        }

                        //i is the next tile the robot is facing
                        for (int i = (x - 1); i >= 0 && (z == 0); i--) {

                            if (this.fieldmatrix[i][y].getBarrierRight()) {
                                break;
                            }
                            if (this.fieldmatrix[i][y].getBarrierLeft()) {
                                z++;
                            }

                            /* checks if one of the players is on the current field [x][i2], if yes
                            gets damage and z becomes 1, so the loop breaks */
                            for (Robot robot : players) {

                                int x2 = robot.getXcoor();
                                int y2 = robot.getYcoor();

                                if (x2 == i && y2 == y) {
                                    robot.damageUp();
                                    z++;
                                }
                            }
                        }
                        break;

                    default:
                        break;
                }
            }
        }
    }
    
    private static int getNumberOfChoosableCards(int damage) {
        if (damage < 5) {
            return 5;
        }
        return 9 - damage;  
    }

    public static int getCheckpoint() {
        return checkpoint;
    }
}
