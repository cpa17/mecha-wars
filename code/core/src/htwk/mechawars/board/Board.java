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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Class that presents the game board.
 */
public class Board {
    public int[][] matrix;
    private Texture[] fieldAssets = new Texture[85]; 

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
                fieldAssets[i] = new Texture(Gdx.files.internal("mapAssets/StandardField.png")); 
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
                System.exit(-1);
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
     * Method that creates a matrix with field objects from a int matrix.
     *
     * @param board Object which contains the int matrix as a parameter
     * @return fieldmatrix A matrix with field objects
     */
    public static Field[][] createFieldMatrix(Board board) {
        Field[][] fieldmatrix = new Field[board.matrix.length][board.matrix[0].length];
        int[] allowed;
        for (int i = 0; i < board.matrix.length; i++) {
            for (int j = 0; j < board.matrix[i].length; j++) {
                //Switch mit den ersten drei Ziffern die für die Klasse stehen
                switch (board.matrix[i][j] / 100) {

                    //BarrierCorner
                    case 100:
                        //für das Attribut wird über Modulo auf die letze Ziffer zugegriffen
                        int corner = board.matrix[i][j] % 10;
                        allowed = new int[]{1, 2, 3, 4};
                        //Test ob ausgelesener Attributwert in Menge der erlaubten Attributwerte enthalten ist mittels Lambda-Ausdruck
                        if (Arrays.stream(allowed).anyMatch(x -> x == corner)) {
                            fieldmatrix[i][j] = new BarrierCorner(j, i, corner);
                        } else {
                            System.out.println("Codierung " + board.matrix[i][j]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    //BarrierSide
                    case 101:
                        int side = board.matrix[i][j] % 10;
                        allowed = new int[]{1, 2, 3, 4};
                        if (Arrays.stream(allowed).anyMatch(x -> x == side)) {
                            fieldmatrix[i][j] = new BarrierSide(j, i, side);
                        } else {
                            System.out.println("Codierung " + board.matrix[i][j]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    //BlackHole
                    case 102:
                        fieldmatrix[i][j] = new BlackHole(j, i);
                        break;

                    //Blockade
                    case 103:
                        int typeB = board.matrix[i][j] % 10;
                        allowed = new int[]{1, 2, 3, 4};
                        if (Arrays.stream(allowed).anyMatch(x -> x == typeB)) {
                            fieldmatrix[i][j] = new Blockade(j, i, typeB);
                        } else {
                            System.out.println("Codierung " + board.matrix[i][j]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    //Checkpoint
                    case 104:
                        int numberC = board.matrix[i][j] % 10;
                        allowed = new int[]{0, 1, 2, 3, 4, 5};
                        if (Arrays.stream(allowed).anyMatch(x -> x == numberC)) {
                            fieldmatrix[i][j] = new Checkpoint(j, i, numberC);
                        } else {
                            System.out.println("Codierung " + board.matrix[i][j]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    //ConveyorBelt
                    case 105:
                        // zum Angeben des Attributes, dass an vorletzter Stelle steht, wird über das Teilen durch 10 und Modulo zugegriffen
                        int startC = (board.matrix[i][j] / 10) % 10;
                        int endC = board.matrix[i][j] % 10;
                        allowed = new int[]{21, 31, 41, 61, 71, 91, 2, 12, 32, 42, 52, 92,
                                3, 13, 23, 43, 63, 83, 14, 24, 34, 54, 74, 84};
                        if (Arrays.stream(allowed).anyMatch(x -> x == (10 * startC) + endC)) {
                            fieldmatrix[i][j] = new ConveyorBelt(j, i, startC, endC);
                        } else {
                            System.out.println("Codierung " + board.matrix[i][j]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    //ExpressConveyorBelt
                    case 106:
                        int startEc = (board.matrix[i][j] / 10) % 10;
                        int endEc = board.matrix[i][j] % 10;
                        allowed = new int[]{21, 31, 41, 61, 71, 91, 2, 12, 32, 42, 52, 92,
                                3, 13, 23, 43, 63, 83, 14, 24, 34, 54, 74, 84};
                        if (Arrays.stream(allowed).anyMatch(x -> x == (10 * startEc) + endEc)) {
                            fieldmatrix[i][j] = new ExpressConveyorBelt(j, i, startEc, endEc);
                        } else {
                            System.out.println("Codierung " + board.matrix[i][j]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    //Gear
                    case 107:
                        int direction = board.matrix[i][j] % 10;
                        allowed = new int[]{1, 2};
                        if (Arrays.stream(allowed).anyMatch(x -> x == direction)) {
                            fieldmatrix[i][j] = new Gear(j, i, direction);
                        } else {
                            System.out.println("Codierung " + board.matrix[i][j]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    //Laser
                    case 108:
                        int typeL = board.matrix[i][j] % 10;
                        allowed = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
                        if (Arrays.stream(allowed).anyMatch(x -> x == typeL)) {
                            fieldmatrix[i][j] = new Laser(j, i, typeL);
                        } else {
                            System.out.println("Codierung " + board.matrix[i][j]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    //RepairSite
                    case 109:
                        int typeR = board.matrix[i][j] % 10;
                        allowed = new int[]{1, 2};
                        if (Arrays.stream(allowed).anyMatch(x -> x == typeR)) {
                            fieldmatrix[i][j] = new RepairSite(j, i, typeR);
                        } else {
                            System.out.println("Codierung " + board.matrix[i][j]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    //StandardField
                    case 110:
                        fieldmatrix[i][j] = new StandardField(j, i);
                        break;

                    //StartField
                    case 111:
                        int numberS = board.matrix[i][j] % 10;
                        allowed = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
                        if (Arrays.stream(allowed).anyMatch(x -> x == numberS)) {
                            fieldmatrix[i][j] = new StartField(j, i, numberS);
                        } else {
                            System.out.println("Codierung " + board.matrix[i][j]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    default:
                        System.out.println("Codierung " + board.matrix[i][j]
                                + " beschreibt kein gueltige Feldklasse");
                        break;
                }
            }
        }
        return fieldmatrix;
    }

    //zum Ausgeben der Objektmatrix mit Klasse und Attributwerten je Objekt
    public static void showFieldMatrix(Field[][] fieldmatrix) {
        for (int i = 0; i < fieldmatrix.length; i++) {
            for (int j = 0; j < fieldmatrix[i].length; j++) {
                System.out.print("(class: " + fieldmatrix[i][j].getClass() + ", "
                        + fieldmatrix[i][j].showAttributes() + ") ");
            }
            System.out.println();
        }
    }

    /**
     * Method that moves the robot in the matrix.
     * @param phase List of cards
     * @param robot the robot that should move
     */
    public void move(LinkedList<Card> phase, Robot robot) {

        checkDoubleDamage(robot);

        checkShutDown(robot);

        try {
            for (Card card : phase) {
                if (card.getCardAttributeType() == Type.mov) {
                    robot.moveInDirection(card.getCardAttributeMovCount());
                } else {
                    robot.turn(card.getCardAttributeMovCount());
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            robot.setXcoor(robot.getStartX());
            robot.setYcoor(robot.getStartY());
        }
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
