package htwk.mechawars.board;

import htwk.mechawars.ConfigReader;
import htwk.mechawars.cards.AiCardGeneration;
import htwk.mechawars.cards.Card;
import htwk.mechawars.cards.Deck;
import htwk.mechawars.cards.Type;
import htwk.mechawars.fields.BarrierCorner;
import htwk.mechawars.fields.BarrierSide;
import htwk.mechawars.fields.BlackHole;
import htwk.mechawars.fields.Pusher;
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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

/**
 * Class that presents the game board.
 */
public class Board {
    public Field[][] fieldmatrix;
    private Field robotPosition;

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
                Gdx.app.exit();
            }
        }

        for (String value : linesArray) {
            currentLine = value;
            ArrayList<Integer> row = new ArrayList<>();
            String[] values = currentLine.trim().split(" ");
            for (String string : values) {
                if (values.length > 16) {
                    System.out.println("The map has too many columns, only 16 are allowed!");
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
        
        if (height > 16) {
            System.out.println("The map has too many rows, only 16 are allowed!");
            Gdx.app.exit();
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
        for (int col = 0; col < matrix.length; col++) {
            for (int cell = 0; cell < matrix[col].length; cell++) {
                // Switch with the first three digits that represent the class
                switch (matrix[col][cell] / 100) {

                    // BarrierCorner
                    case 100:
                        // Modulo 10 takes the last digit that represents the attribute
                        int corner = matrix[col][cell] % 10;
                        allowed = new int[]{1, 2, 3, 4};
                        // Test that the read-out attribute value is in the set
                        // of allowed attribute values
                        if (Arrays.stream(allowed).anyMatch(x -> x == corner)) {
                            this.fieldmatrix[col][cell] = new BarrierCorner(col, cell, corner,
                                    isTest);
                        } else {
                            System.out.println("Codierung " + matrix[col][cell]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    // BarrierSide
                    case 101:
                        int side = matrix[col][cell] % 10;
                        allowed = new int[]{1, 2, 3, 4};
                        if (Arrays.stream(allowed).anyMatch(x -> x == side)) {
                            this.fieldmatrix[col][cell] = new BarrierSide(col, cell, side, isTest);
                        } else {
                            System.out.println("Codierung " + matrix[col][cell]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    // BlackHole
                    case 102:
                        fieldmatrix[col][cell] = new BlackHole(col, cell, isTest);
                        break;

                    // Pusher
                    case 103:
                        int typeB = matrix[col][cell] % 10;
                        allowed = new int[]{1, 2, 3, 4};
                        if (Arrays.stream(allowed).anyMatch(x -> x == typeB)) {
                            this.fieldmatrix[col][cell] = new Pusher(col, cell, typeB, isTest);
                        } else {
                            System.out.println("Codierung " + matrix[col][cell]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    // Checkpoint
                    case 104:
                        int numberC = matrix[col][cell] % 10;
                        allowed = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
                        if (Arrays.stream(allowed).anyMatch(x -> x == numberC)) {
                            this.fieldmatrix[col][cell] = new Checkpoint(col, cell, numberC,
                                    isTest);
                        } else {
                            System.out.println("Codierung " + matrix[col][cell]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    // ConveyorBelt
                    case 105:
                        // Divide by 10 and module 10 takes the next-to-last digit,
                        // which represents another attribute
                        int startC = (matrix[col][cell] / 10) % 10;
                        int endC = matrix[col][cell] % 10;
                        allowed = new int[]{21, 31, 41, 61, 71, 91, 2, 12, 32, 42, 52, 92,
                                3, 13, 23, 43, 63, 83, 14, 24, 34, 54, 74, 84};
                        if (Arrays.stream(allowed).anyMatch(x -> x == (10 * startC) + endC)) {
                            this.fieldmatrix[col][cell] = new ConveyorBelt(col, cell, startC,
                                    endC, isTest);
                        } else {
                            System.out.println("Codierung " + matrix[col][cell]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    // ExpressConveyorBelt
                    case 106:
                        int startEc = (matrix[col][cell] / 10) % 10;
                        int endEc = matrix[col][cell] % 10;
                        allowed = new int[]{21, 31, 41, 61, 71, 91, 2, 12, 32, 42, 52, 92,
                                3, 13, 23, 43, 63, 83, 14, 24, 34, 54, 74, 84};
                        if (Arrays.stream(allowed).anyMatch(x -> x == (10 * startEc) + endEc)) {
                            this.fieldmatrix[col][cell] = new ExpressConveyorBelt(col, cell,
                                    startEc, endEc, isTest);
                        } else {
                            System.out.println("Codierung " + matrix[col][cell]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    // Gear
                    case 107:
                        int direction = matrix[col][cell] % 10;
                        allowed = new int[]{1, 2};
                        if (Arrays.stream(allowed).anyMatch(x -> x == direction)) {
                            this.fieldmatrix[col][cell] = new Gear(col, cell, direction, isTest);
                        } else {
                            System.out.println("Codierung " + matrix[col][cell]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    // Laser
                    case 108:
                        int typeL = matrix[col][cell] % 10;
                        allowed = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
                        if (Arrays.stream(allowed).anyMatch(x -> x == typeL)) {
                            this.fieldmatrix[col][cell] = new Laser(col, cell, typeL, isTest);
                        } else {
                            System.out.println("Codierung " + matrix[col][cell]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    // RepairSite
                    case 109:
                        int typeR = matrix[col][cell] % 10;
                        allowed = new int[]{1, 2};
                        if (Arrays.stream(allowed).anyMatch(x -> x == typeR)) {
                            this.fieldmatrix[col][cell] = new RepairSite(col, cell, typeR, isTest);
                        } else {
                            System.out.println("Codierung " + matrix[col][cell]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    // StandardField
                    case 110:
                        this.fieldmatrix[col][cell] = new StandardField(col, cell, isTest);
                        break;

                    // StartField
                    case 111:
                        int numberS = matrix[col][cell] % 10;
                        allowed = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
                        if (Arrays.stream(allowed).anyMatch(x -> x == numberS)) {
                            this.fieldmatrix[col][cell] = new StartField(col, cell, numberS,
                                    isTest);
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
        robot.setStartX(x);
        robot.setYcoor(y);
        robot.setStartY(y);
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
                if (ConfigReader.getAimodes()[i]) {

                    LinkedList<Card> generatedCards =
                            AiCardGeneration.generateRandomAiCardsfromDeck(i);
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

        if (!isTest) {
            // Delay for the state-function to run so that the robot has reached his final position
            Timer.schedule(new Task() {

                @Override
                public void run() {
                    state(players);
                    checkDoubleDamage(players);
                    checkShutDown(players);
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
                        checkRobotLaser(players);
                        checkBoardLaser(players);
                    }
                }, i);
            }
        } else {

            // No delay if this is a test
            checkRobotLaser(players);
            checkBoardLaser(players);
            state(players);
            checkDoubleDamage(players);
            checkShutDown(players);
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
                player.setXcoor(player.getStartX());
                player.setYcoor(player.getStartY());
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

                player.damageUp();
                player.damageUp();

                if (player.getDestroyed()) {
                    player.setDestroyed(false);
                }
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
     * Method that checks whether the robot is being shot at by a laser.
     *
     * @param players A array of robots
     */
    public void checkBoardLaser(Robot[] players) {
        Laser laser;
        Laser currentLaser;
        int flag;
        int q;

        for (int i = 0; i < this.fieldmatrix.length; i++) {
            for (int j = 0; j < this.fieldmatrix[i].length; j++) {

                if (this.fieldmatrix[i][j] instanceof Laser) {

                    laser = (Laser) this.fieldmatrix[i][j];
                    switch (laser.getType()) {

                        // begin left
                        case 0:
                            currentLaser = laser;
                            flag = 0;
                            q = 1;

                            while (flag == 0) {

                                for (int s = 0; (s < players.length) && (flag == 0); s++) {
                                    int x = players[s].getXcoor();
                                    int y = players[s].getYcoor();
                                    if ((x == currentLaser.getXcoor())
                                            && (y == currentLaser.getYcoor())) {
                                        players[s].damageUp();
                                        flag = 1;
                                    }
                                }

                                if (fieldmatrix[i + q][j] instanceof Laser) {
                                    currentLaser = (Laser) fieldmatrix[i + q][j];
                                    q = q + 1;
                                } else {
                                    flag = 1;
                                }
                            }
                            break;

                        // begin top
                        case 1:
                            currentLaser = laser;
                            flag = 0;
                            q = 1;

                            while (flag == 0) {

                                for (int s = 0; (s < players.length) && (flag == 0); s++) {
                                    int x = players[s].getXcoor();
                                    int y = players[s].getYcoor();
                                    if ((x == currentLaser.getXcoor())
                                            && (y == currentLaser.getYcoor())) {
                                        players[s].damageUp();
                                        flag = 1;
                                    }
                                }

                                if (fieldmatrix[i][j + q] instanceof Laser) {
                                    currentLaser = (Laser) fieldmatrix[i][j + q];
                                    q = q + 1;
                                } else {
                                    flag = 1;
                                }
                            }
                            break;

                        // begin right
                        case 2:
                            currentLaser = laser;
                            flag = 0;
                            q = 1;

                            while (flag == 0) {

                                for (int s = 0; (s < players.length) && (flag == 0); s++) {
                                    int x = players[s].getXcoor();
                                    int y = players[s].getYcoor();
                                    if ((x == currentLaser.getXcoor())
                                            && (y == currentLaser.getYcoor())) {
                                        players[s].damageUp();
                                        flag = 1;
                                    }
                                }

                                if (fieldmatrix[i - q][j] instanceof Laser) {
                                    currentLaser = (Laser) fieldmatrix[i - q][j];
                                    q = q + 1;
                                } else {
                                    flag = 1;
                                }
                            }
                            break;

                        // begin bottom
                        case 3:
                            currentLaser = laser;
                            flag = 0;
                            q = 1;

                            while (flag == 0) {

                                for (int s = 0; (s < players.length) && (flag == 0); s++) {
                                    int x = players[s].getXcoor();
                                    int y = players[s].getYcoor();
                                    if ((x == currentLaser.getXcoor())
                                            && (y == currentLaser.getYcoor())) {
                                        players[s].damageUp();
                                        flag = 1;
                                    }
                                }

                                if (fieldmatrix[i][j - q] instanceof Laser) {
                                    currentLaser = (Laser) fieldmatrix[i][j - q];
                                    q = q + 1;
                                } else {
                                    flag = 1;
                                }
                            }
                            break;

                        default:
                            break;
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

        BarrierSide barrierside;
        BarrierCorner barriercorner;

        for (Robot player : players) {

            int x = player.getXcoor();
            int y = player.getYcoor();

            //the variable z, checks if a player or a wall already have been hit
            int z = 0;

            switch (player.getDir()) {

                case NORTH:

                    if (this.fieldmatrix[x][y] instanceof BarrierSide) {
                        barrierside = (BarrierSide) this.fieldmatrix[x][y];
                        if (barrierside.getSide() == 2) {
                            break;
                        }
                    }

                    if (this.fieldmatrix[x][y] instanceof BarrierCorner) {
                        barriercorner = (BarrierCorner) this.fieldmatrix[x][y];
                        if (barriercorner.getCorner() == 1 || barriercorner.getCorner() == 2) {
                            break;
                        }
                    }

                    //i2 is the next tile the robot is facing
                    for (int i2 = (y - 1); i2 >= 0 && (z == 0); i2--) {

                        if (this.fieldmatrix[x][i2] instanceof BarrierSide) {
                            barrierside = (BarrierSide) this.fieldmatrix[x][i2];
                            if (barrierside.getSide() == 4) {
                                break;
                            }
                            if (barrierside.getSide() == 2) {
                                z++;
                            }
                        }

                        if (this.fieldmatrix[x][i2] instanceof BarrierCorner) {
                            barriercorner = (BarrierCorner) this.fieldmatrix[x][i2];
                            if (barriercorner.getCorner() == 3 || barriercorner.getCorner() == 4) {
                                break;
                            }
                            if (barriercorner.getCorner() == 1 || barriercorner.getCorner() == 2) {
                                z++;
                            }
                        }

                        /* checks if one of the players is on the current field [x][i2], if yes
                        gets damage and z becomes 1, so the loop breaks */
                        for (Robot robot : players) {

                            int x2 = robot.getXcoor();
                            int y2 = robot.getYcoor();

                            if (x2 == x && y2 == i2) {
                                robot.damageUp();
                                z++;
                            }
                        }
                    }
                    break;


                case SOUTH:

                    if (this.fieldmatrix[x][y] instanceof BarrierSide) {
                        barrierside = (BarrierSide) this.fieldmatrix[x][y];
                        if (barrierside.getSide() == 4) {
                            break;
                        }
                    }

                    if (this.fieldmatrix[x][y] instanceof BarrierCorner) {
                        barriercorner = (BarrierCorner) this.fieldmatrix[x][y];
                        if (barriercorner.getCorner() == 3 || barriercorner.getCorner() == 4) {
                            break;
                        }
                    }

                    for (int i2 = (y + 1); i2 < this.fieldmatrix[0].length && (z == 0); i2++) {

                        if (this.fieldmatrix[x][i2] instanceof BarrierSide) {
                            barrierside = (BarrierSide) this.fieldmatrix[x][i2];
                            if (barrierside.getSide() == 2) {
                                break;
                            }
                            if (barrierside.getSide() == 4) {
                                z++;
                            }
                        }

                        if (this.fieldmatrix[x][i2] instanceof BarrierCorner) {
                            barriercorner = (BarrierCorner) this.fieldmatrix[x][i2];
                            if (barriercorner.getCorner() == 1 || barriercorner.getCorner() == 2) {
                                break;
                            }
                            if (barriercorner.getCorner() == 3 || barriercorner.getCorner() == 4) {
                                z++;
                            }
                        }

                        for (Robot robot : players) {

                            int x2 = robot.getXcoor();
                            int y2 = robot.getYcoor();

                            if (x2 == x && y2 == i2) {
                                robot.damageUp();
                                z++;
                            }
                        }
                    }
                    break;


                case EAST:

                    if (this.fieldmatrix[x][y] instanceof BarrierSide) {
                        barrierside = (BarrierSide) this.fieldmatrix[x][y];
                        if (barrierside.getSide() == 3) {
                            break;
                        }
                    }

                    if (this.fieldmatrix[x][y] instanceof BarrierCorner) {
                        barriercorner = (BarrierCorner) this.fieldmatrix[x][y];
                        if (barriercorner.getCorner() == 2 || barriercorner.getCorner() == 3) {
                            break;
                        }
                    }

                    for (int i2 = (x + 1); i2 < this.fieldmatrix.length && (z == 0); i2++) {

                        if (this.fieldmatrix[i2][y] instanceof BarrierSide) {
                            barrierside = (BarrierSide) this.fieldmatrix[i2][y];
                            if (barrierside.getSide() == 1) {
                                break;
                            }
                            if (barrierside.getSide() == 3) {
                                z++;
                            }
                        }

                        if (this.fieldmatrix[i2][y] instanceof BarrierCorner) {
                            barriercorner = (BarrierCorner) this.fieldmatrix[i2][y];
                            if (barriercorner.getCorner() == 1 || barriercorner.getCorner() == 4) {
                                break;
                            }
                            if (barriercorner.getCorner() == 2 || barriercorner.getCorner() == 3) {
                                z++;
                            }
                        }

                        for (Robot robot : players) {

                            int x2 = robot.getXcoor();
                            int y2 = robot.getYcoor();

                            if (x2 == i2 && y2 == y) {
                                robot.damageUp();
                                z++;
                            }
                        }
                    }
                    break;


                case WEST:

                    if (this.fieldmatrix[x][y] instanceof BarrierSide) {
                        barrierside = (BarrierSide) this.fieldmatrix[x][y];
                        if (barrierside.getSide() == 1) {
                            break;
                        }
                    }

                    if (this.fieldmatrix[x][y] instanceof BarrierCorner) {
                        barriercorner = (BarrierCorner) this.fieldmatrix[x][y];
                        if (barriercorner.getCorner() == 1 || barriercorner.getCorner() == 4) {
                            break;
                        }
                    }

                    for (int i2 = (x - 1); i2 >= 0 && (z == 0); i2--) {

                        if (this.fieldmatrix[i2][y] instanceof BarrierSide) {
                            barrierside = (BarrierSide) this.fieldmatrix[i2][y];
                            if (barrierside.getSide() == 3) {
                                break;
                            }
                            if (barrierside.getSide() == 1) {
                                z++;
                            }
                        }

                        if (this.fieldmatrix[i2][y] instanceof BarrierCorner) {
                            barriercorner = (BarrierCorner) this.fieldmatrix[i2][y];
                            if (barriercorner.getCorner() == 2 || barriercorner.getCorner() == 3) {
                                break;
                            }
                            if (barriercorner.getCorner() == 1 || barriercorner.getCorner() == 4) {
                                z++;
                            }
                        }

                        for (Robot robot : players) {

                            int x2 = robot.getXcoor();
                            int y2 = robot.getYcoor();

                            if (x2 == i2 && y2 == y) {
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
