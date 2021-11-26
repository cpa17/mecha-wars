package htwk.mechawars.board;

import htwk.mechawars.cards.Card;
import htwk.mechawars.cards.Type;
import htwk.mechawars.fields.BarrierCorner;
import htwk.mechawars.fields.BarrierSide;
import htwk.mechawars.fields.BlackHole;
import htwk.mechawars.fields.Blockade;
import htwk.mechawars.fields.Checkpoint;
import htwk.mechawars.fields.ConveyorBelt;
import htwk.mechawars.fields.ExpressConveyorBelt;
import htwk.mechawars.fields.Field;
import htwk.mechawars.fields.Gear;
import htwk.mechawars.fields.Laser;
import htwk.mechawars.fields.RepairSite;
import htwk.mechawars.fields.StandardField;
import htwk.mechawars.fields.StartField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

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
    public Field[][] fieldmatrix;
    private Field robotPosition;

    /**
     * Method that reads the game plan as a int matrix from a file and constructs the game board
     * as a int matrix and a field matrix.
     *
     * @param fileName Path to a file containing a map
     */
    public Board(String fileName) {
        FileHandle file = Gdx.files.internal(fileName);
        String mapString = file.readString();

        Board wrappedBoard = new Board(mapString, false);
        this.matrix = wrappedBoard.matrix;
        intToFieldMatrix(this.matrix);
    }

    /**
     * Method that reads the game plan as a int matrix from a file and constructs the game board,
     * but can skip creating the field matrix.
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

        if (height > 12) {
            System.out.println("The map has too many rows, only 12 are allowed!");
            Gdx.app.exit();
        }

        Board wrappedBoard = new Board(width, height, isTest);

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
        for (int row = 0; row < board.fieldmatrix.length; row++) {
            int x = 0;
            for (int cell = 0; cell < board.fieldmatrix[row].length; cell++) {
                int t = Gdx.graphics.getHeight() / board.fieldmatrix.length; //height of one tile
                int b = Gdx.graphics.getHeight(); //height of the entire board
                int c = (row + 1) * t; //the current height in the loop
                int r = b - c; //the result of the board height minus the current height
                batch.draw(board.fieldmatrix[cell][row].getTile(), x, r);
                x = x + (Gdx.graphics.getHeight() / board.fieldmatrix.length);
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
     */
    public void startRobot(int x, int y, Dir dir, Robot robot, boolean isTest) {
        int min = 1;
        int max = 8;
        int randomNumber =  ThreadLocalRandom.current().nextInt(min, max) + min;
        
        if (!isTest) {
            for (int i = 0; i < fieldmatrix.length; i++) {
                for (int j = 0; j < fieldmatrix[i].length; j++) {
                    if (fieldmatrix[i][j] instanceof StartField && 
                            ((StartField) fieldmatrix[i][j]).getNumber() == randomNumber) {
                        x = fieldmatrix[i][j].getYcoor();
                        y = fieldmatrix[i][j].getXcoor();    
                    }
                }
            }
        }
        
        robot.setXcoor(x);
        robot.setStartX(x);
        robot.setYcoor(y);
        robot.setStartY(y);
        robot.setDir(dir);
    }

    /**
     * This is a wrapper-function for the tests.
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

        checkShutDown(robot);

        if (booleanForTests) {
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
        } else {

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
                        if (robot.getXcoor() >= matrix[1].length ||
                                robot.getYcoor() >= matrix.length || 
                                robot.getXcoor() < 0 || 
                                robot.getYcoor() < 0) {
                            robot.setXcoor(robot.getStartX());
                            robot.setYcoor(robot.getStartY());
                            return;
                        }
                    }
                }, i);
                i += 1;
            }
        }

        if (!isTest) {
            robotPosition = this.fieldmatrix[robot.getXcoor()][robot.getYcoor()];
            robotPosition.action(robot);
        }
        
        checkShutDown(robot);
        robot.setLastRound(robot.getShutDown());
        robot.setShutDown(robot.getNextRound());

        checkDoubleDamage(robot);
    }

    /**
     * Method that checks whether the robot receives 2 damage points.
     *
     * @param robot the robot that should check
     */
    private void checkDoubleDamage(Robot robot) {
        if ((!robot.getShutDown() && robot.getLastRound()) || robot.getDestroyed()) {

            robot.damageUp();
            robot.damageUp();

            if (robot.getDestroyed()) {
                robot.setDestroyed(false);
                checkShutDown(robot);
            }
        }
    }

    /**
     * Method that checks whether the robot is in shutdown mode.
     *
     * @param robot the robot that should check
     */
    private void checkShutDown(Robot robot) {
        if (robot.getNextRound() || robot.getShutDown()) {
            robot.damageReset();
        }
    }

}
