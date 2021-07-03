package htwk.mechawars.board;

import htwk.mechawars.cards.Card;
import htwk.mechawars.cards.Type;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

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

    public Board() {
        this.matrix = new int[0][0];
    }

    public static Board FromFile (String fileName) {
//        Board board = null;
        ArrayList <ArrayList<Integer>> tempLayout = new ArrayList<>();

        try(BufferedReader br = new BufferedReader (new FileReader(fileName))) {
            String currentLine;

            while((currentLine = br.readLine()) != null) {
                if(currentLine.isEmpty()) {
                    continue;
                }
                ArrayList<Integer> row = new ArrayList<>();
                String[] values = currentLine.trim().split(" ");
                for (String string : values)
                {
                    if(!string.isEmpty()) {
                        int id = Integer.parseInt(string);
                        row.add(id);
                    }
                }
                tempLayout.add(row);
            }
        }
        catch(IOException e)
        {

        }

        int width = tempLayout.get(0).size();
        int height = tempLayout.size();

        Board board = new Board(width, height);

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                board.matrix[y][x] = tempLayout.get(y).get(x);
                System.out.print(board.matrix[y][x]);
            }
            System.out.println();
        }

 //       board.tileSheet = board.LoadTileSheet();

        return board;
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
