package htwk.mechawars.board;

import htwk.mechawars.cards.Card;
import htwk.mechawars.cards.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Class that presents the game board.
 */
public class Board {
    public int[][] matrix;

    /**
     * Method that constructs the game board as a matrix.
     * @param width width of the game board
     * @param height height of the game board
     */

    public Board(int width, int height) {
        this.matrix = new int[height][width];

        for (int[] ints : matrix) {
            Arrays.fill(ints, 0);
        }
    }

    /**
     * Method that constructs the game board as a null matrix.
     */

    public Board() {
        this.matrix = new int[0][0];
    }

    /**
     * Method that reads the game plan as a matrix from a text file.
     * @param fileName Name of the text file to be read in.
     * @return New board with the matrix from the text file.
     */

    public static Board fromFile(String fileName) {
        FileHandle file = Gdx.files.internal(fileName);
        String text = file.readString();
    
        ArrayList<ArrayList<Integer>> tempLayout = new ArrayList<>();
           
        String[] linesArray = text.split("\\r?\\n");
        String currentLine;

        for (int i = 0; i < linesArray.length; i++) {
            currentLine = linesArray[i];
            ArrayList<Integer> row = new ArrayList<>();
            String[] values = currentLine.trim().split(" ");
            for (String string : values) {
                if (!string.isEmpty()) {
                    int id = Integer.parseInt(string);
                    row.add(id);
                }
            }
            tempLayout.add(row);              
        }

        int width = tempLayout.get(0).size();
        int height = tempLayout.size();

        Board board = new Board(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                board.matrix[y][x] = tempLayout.get(y).get(x);
            }
        }

        return board;
    }

    /**
     * Method that outputs any board matrix in the console for tests.
     *
     * @param board Board whose matrix is to be converted into a string
     * @return matrix Matrix of the board as a string
     */

    public static String toString(Board board) {
        String matrix = "";
        for (int i = 0; i < board.matrix.length; i++) {
            for (int j = 0; j < board.matrix[i].length; j++) {
                matrix = matrix + board.matrix[i][j] + " ";
            }
            matrix = matrix + "\n";
        }
        return matrix;
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
        this.matrix[y][x] = robot.getDir().getValue();
    }

    /**
     * Method that moves the robot in the matrix.
     * @param phase List of cards
     * @param robot the robot that should move
     */

    public void move(LinkedList<Card> phase, Robot robot) {
        try {
            for (Card card : phase) {

                if (card.getCardAttributeType() == Type.mov) {
                    this.matrix[robot.getYcoor()][robot.getXcoor()] = 0;
                    robot.moveInDirection(card.getCardAttributeMovCount());
                } else {
                    robot.turn(card.getCardAttributeMovCount());
                }
                this.matrix[robot.getYcoor()][robot.getXcoor()] = robot.getDir().getValue();

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            robot.setXcoor(robot.getStartX());
            robot.setYcoor(robot.getStartY());
            this.matrix[robot.getYcoor()][robot.getXcoor()] = robot.getDir().getValue();
        }
    }

}
