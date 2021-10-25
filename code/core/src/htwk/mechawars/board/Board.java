package htwk.mechawars.board;

import htwk.mechawars.cards.Card;
import htwk.mechawars.cards.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Class that presents the game board.
 */
public class Board {
    public int[][] matrix;
    protected static final Texture[] fieldAssets = new Texture[37];
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
     * Method that constructs a game board with a null matrix.
     */

    public Board() {
        this.matrix = new int[0][0];
    }

    /**
     * Method that reads the game plan as a string from a text file.
     *
     * @param fileName Name of the text file to be read in.
     * @return Board.fromString(mapString) Method fromString
     *         with the string from the text file as parameter
     */

    public static Board fromFile(String fileName) {
        FileHandle file = Gdx.files.internal(fileName);
        String mapString = file.readString();
        System.out.println(mapString);
        for (int i = 0; i < fieldAssets.length; i++) {
            fieldAssets[i] = new Texture(Gdx.files.internal("mapAssets/" + i + ".png"));
        }
        return Board.fromString(mapString);
    }

    /**
     * Method that reads the game plan as a matrix from a string.
     *
     * @param mapString String that is to be saved as the matrix of a board
     * @return board Board which contains the game plan as a matrix
     */

    public static Board fromString(String mapString) {
    
        ArrayList<ArrayList<Integer>> tempLayout = new ArrayList<>();
           
        String[] linesArray = mapString.split("\\r?\\n");
        String currentLine;
        
        Scanner scn = new Scanner(mapString);
        String s = ""; 
        while (scn.hasNext()) {
            s = scn.next();
            try {
                Integer.parseInt(s); 
            } catch (NumberFormatException z) {
                System.out.println("The map obtains elements which are not integer!");
                Gdx.app.exit();
                System.exit(-1);
            }
        }

        for (int i = 0; i < linesArray.length; i++) {
            currentLine = linesArray[i];
            ArrayList<Integer> row = new ArrayList<>();
            String[] values = currentLine.trim().split(" ");
            for (String string : values) {
                
                if (values.length > 12) {
                    System.out.println("The map has too many columns, only 12 are allowed!");
                    Gdx.app.exit();
                    System.exit(-1);
                } 
                
                if (!string.isEmpty()) {
                    int id = Integer.parseInt(string);
                    row.add(id);
                }
            }
            tempLayout.add(row);              
        }

        int width = tempLayout.get(0).size();
        int height = tempLayout.size();

        
        if (height > 12) {
            System.out.println("The map has too many rows, only 12 are allowed!");
            Gdx.app.exit();
            System.exit(-1);
        }                       
        
        Board board = new Board(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                board.matrix[y][x] = tempLayout.get(y).get(x);
            }
        }

        return board;
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
                System.out.print(board.matrix[i][j] + " ");
                int t = Gdx.graphics.getHeight() / board.matrix.length; //height of one tile
                int b = Gdx.graphics.getHeight(); //height of the entire board
                int c = (i + 1) * t; //the current height in the loop
                int r = b - c; //the result of the board height minus the current height
                batch.draw(fieldAssets[board.matrix[i][j]], x, r);
                x = x + (Gdx.graphics.getHeight() / board.matrix.length);
            } 
            System.out.println("");
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
