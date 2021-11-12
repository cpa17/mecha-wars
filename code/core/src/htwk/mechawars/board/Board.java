package htwk.mechawars.board;

import htwk.mechawars.cards.Card;
import htwk.mechawars.cards.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Class that presents the game board.
 */
public class Board {
    public int[][] matrix;
    private Texture[] fieldAssets = new Texture[36];

    /**
     * Method that constructs the game board as a matrix.
     * @param width width of the game board
     * @param height height of the game board
     */
    public Board(int width, int height) {
        Board wrappedBoard = new Board(width, height, false);
        this.matrix = wrappedBoard.matrix;
        this.fieldAssets = wrappedBoard.fieldAssets;
    }

    /**
     * Method that constructs the game board as a matrix, but can skip creating the assets.
     * @param width width of the game board
     * @param height height of the game board
     * @param isTest allows to skip creating the assets
     */
    public Board(int width, int height, boolean isTest) {
        this.matrix = new int[height][width];
        if (!isTest) {
            for (int i = 0; i < fieldAssets.length; i++) {
                fieldAssets[i] = new Texture(Gdx.files.internal("mapAssets/" + i + ".png"));
            }
        }
        for (int[] ints : matrix) {
            Arrays.fill(ints, 0);
        }
    }

    /**
     * Method that constructs a game board with a null matrix.
     */
    public Board() {
        this.matrix = new int[0][0];
    }

    /**
     * Method that reads the game plan as a matrix from a file.
     * @param fileName Path to a file containing a map
     */
    public Board(String fileName) {
        FileHandle file = Gdx.files.internal(fileName);
        String mapString = file.readString();

        Board wrappedBoard = new Board(mapString, false);
        this.matrix = wrappedBoard.matrix;
        this.fieldAssets = wrappedBoard.fieldAssets;
    }

    /**
     * Method that reads the game plan as a matrix from a file, but can skip the creating the
     * assets.
     *
     * @param mapString String containing a map
     * @param isTest allows to skip creating the assets
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
                Gdx.app.exit();
                //System.exit(-1);
            }
        }

        for (String value : linesArray) {
            currentLine = value;
            ArrayList<Integer> row = new ArrayList<>();
            String[] values = currentLine.trim().split(" ");
            for (String string : values) {
                if (values.length > 12) {
                    System.out.println("The map has too many columns, only 12 are allowed!");
                    Gdx.app.exit();
                    //System.exit(-1);
                }

                if (!string.isEmpty()) {
                    int id = Integer.parseInt(string);
                    row.add(id);
                }
            }
            tempLayout.add(row);
        }
        scn.close();

        int width = tempLayout.get(0).size();
        int height = tempLayout.size();

        if (height > 12) {
            System.out.println("The map has too many rows, only 12 are allowed!");
            Gdx.app.exit();
            //System.exit(-1);
        }

        Board wrappedBoard = new Board(width, height, isTest);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                wrappedBoard.matrix[y][x] = tempLayout.get(y).get(x);
            }
        }

        this.matrix = wrappedBoard.matrix;
        this.fieldAssets = wrappedBoard.fieldAssets;
    }

    /**
     * Method that draws any board matrix as Textures.
     *
     * @param batch SpriteBatch to draw the Textures
     * @param board Board whose matrix is to be converted into a string
     */
    public static void toAsset(SpriteBatch batch, Board board) {
        int x = 0;
        for (int i = 0; i < board.matrix.length; i++) {
            for (int j = 0; j < board.matrix[i].length; j++) {
                int t = Gdx.graphics.getHeight() / board.matrix.length; //height of one tile
                int b = Gdx.graphics.getHeight(); //height of the entire board
                int c = (i + 1) * t; //the current height in the loop
                int r = b - c; //the result of the board height minus the current height
                batch.draw(board.fieldAssets[board.matrix[i][j]], x, r);
                x = x + (Gdx.graphics.getHeight() / board.matrix.length);
            }
            x = 0;
        }
    }

    /**
     * Method that places a robot in the matrix --> starting position.
     * @param x x-coordinate of the robot
     * @param y y-coordinate of the robot
     * @param dir direction of the robot
     * @param robot robot to which this applies
     */
    public void startRobot(int x, int y, Dir dir, Robot robot) {
        robot.setXcoor(x);
        robot.setStartX(x);
        robot.setYcoor(y);
        robot.setStartY(y);
        robot.setDir(dir);
    }

    /**
     * wrapper-function for the tests
     * 
     * @param phase List of cards
     * @param robot the robot that should move
     */
    public void move(LinkedList<Card> phase, Robot robot) {
        move(phase, robot, false);
    }
    
    /**
     * Method that moves the robot in the matrix.
     * 
     * @param phase List of cards
     * @param robot the robot that should move
     */
    public void move(LinkedList<Card> phase, Robot robot, boolean booleanForTests) {

        checkDoubleDamage(robot);

        checkShutDown(robot);

        if(booleanForTests) {
            for (Card card : phase) {
                if (card.getCardAttributeType() == Type.mov) {
                    robot.moveInDirection(card.getCardAttributeMovCount());
                } else {
                    robot.turn(card.getCardAttributeMovCount());
                }
                if (robot.getXcoor() >= matrix[1].length || robot.getYcoor() >= matrix.length
                        || robot.getXcoor() < 0 || robot.getYcoor() < 0) {
                    robot.setXcoor(robot.getStartX());
                    robot.setYcoor(robot.getStartY());
                    return;
                }
            }
        }
        else {

            // delay in seconds, increments for each phase in the linked list for another second
            int i = 0;
            for (Card card : phase) {
                Timer.schedule(new Task() {

                    @Override
                    public void run() {
                        if (card.getCardAttributeType() == Type.mov) {
                            robot.moveInDirection(card.getCardAttributeMovCount());
                        } else {
                            robot.turn(card.getCardAttributeMovCount());
                        }
                        if (robot.getXcoor() >= matrix[1].length || robot.getYcoor() >= matrix.length
                                || robot.getXcoor() < 0 || robot.getYcoor() < 0) {
                            robot.setXcoor(robot.getStartX());
                            robot.setYcoor(robot.getStartY());
                            return;
                        }
                    }
                }, i);
                i += 1;
            }
        }

        robot.setShutDown(robot.getNextRound());

    }

    /**
     * Method that checks whether the robot receives 2 damage points.
     * @param robot the robot that should check
     */
    private void checkDoubleDamage(Robot robot) {
        if ((!robot.getShutDown() && robot.getLastRound()) || robot.getDestroyed()) {

            robot.damageUp();
            robot.damageUp();

            if (robot.getDestroyed()) {
                robot.setDestroyed(false);
            } else {
                robot.setLastRound(false);
            }
        }
    }

    /**
     * Method that checks whether the robot is in shutdown mode.
     * @param robot the robot that should check
     */

    private void checkShutDown(Robot robot) {

        if (robot.getShutDown()) {
            robot.damageReset();
            robot.setLastRound(true);
        }
    }


}
