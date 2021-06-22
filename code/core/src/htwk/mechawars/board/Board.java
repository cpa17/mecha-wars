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
     * Method that constructs the game board as a matrix.
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

    public int[][] move(LinkedList<Card> phase, Robot robot) {
        try {
            for (int i = 0; i < phase.size(); i++) {

                if (phase.get(i).getCardAttributeType() == Type.mov) {
                    this.matrix[robot.getYcoor()][robot.getXcoor()] = 0;
                    robot.moveInDirection(phase.get(i).getCardAttributeMovCount());
                    this.matrix[robot.getYcoor()][robot.getXcoor()] = robot.getDir().getValue();
                } else {
                    robot.turn(phase.get(i).getCardAttributeMovCount());
                    this.matrix[robot.getYcoor()][robot.getXcoor()] = robot.getDir().getValue();
                }

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            robot.setXcoor(robot.getStartX());
            robot.setYcoor(robot.getStartY());
            this.matrix[robot.getYcoor()][robot.getXcoor()] = robot.getDir().getValue();
        }
        return this.matrix;
    }

}
