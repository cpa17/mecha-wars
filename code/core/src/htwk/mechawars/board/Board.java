package htwk.mechawars.board;

import htwk.mechawars.cards.Card;
import htwk.mechawars.cards.Type;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Class that presents the game board.
 */
public class Board {
    public int[][] matrix;

    /**
     * Function that constructs the game board as a matrix.
     * @param weight weight of the game board
     * @param height height of the game board
     */

    public void board(int weight, int height) {
        this.matrix = new int[height][weight];

        for (int[] ints : matrix) {
            Arrays.fill(ints, 0);
        }
    }

    /**
     * Function that places a robot in the matrix --> starting position.
     * @param x x-coordinate of the robot
     * @param y y-coordinate of the robot
     */

    public void startRobot(int x, int y, Dir dir, Robot robot) {
        robot.setX(x);
        robot.setStartX(x);
        robot.setY(y);
        robot.setStartY(y);
        robot.setDir(dir);
        this.matrix[y][x] = robot.getDir().getValue();
    }

    /**
     * Function that moves the robot in the matrix.
     * @param phase List of directions
     * @param robot the robot that should move
     */

    public int[][] move(LinkedList<Card> phase, Robot robot) {
    try{
        for (int i = 0; i < phase.size(); i++) {

            if (phase.get(i).getCardAttributeType() == Type.mov) {
                this.matrix[robot.getY()][robot.getX()] = 0;
                robot.moveInDirection(phase.get(i).getCardAttributeMovCount());
                this.matrix[robot.getY()][robot.getX()] = robot.getDir().getValue();
            } else {
                robot.turn(phase.get(i).getCardAttributeMovCount());
                this.matrix[robot.getY()][robot.getX()] = robot.getDir().getValue();
            }

        }
    } catch (ArrayIndexOutOfBoundsException e) {
        robot.setX(robot.getStartX());
        robot.setY(robot.getStartY());
        this.matrix[robot.getY()][robot.getX()] = robot.getDir().getValue();
    }
        return this.matrix;
    }

}
