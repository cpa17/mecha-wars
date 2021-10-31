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
                
                if (values.length > 12) { //sollte auch passen weil es ja trotzdem 12 Strings sind
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

    public static Field[][] createFieldMatrix(Board testboard){
        Field[][] testfieldmatrix = new Field[testboard.matrix.length][testboard.matrix[0].length];
        for (int i = 0; i < testboard.matrix.length; i++) {
            for (int j = 0; j < testboard.matrix[i].length; j++) {
                switch(testboard.matrix[i][j]){

                    //BarrierCorner
                    case 10001:
                        testfieldmatrix[i][j] = new BarrierCorner(j,i,1);
                        break;
                    case 10002:
                        testfieldmatrix[i][j] = new BarrierCorner(j,i,2);
                        break;
                    case 10003:
                        testfieldmatrix[i][j] = new BarrierCorner(j,i,3);
                        break;
                    case 10004:
                        testfieldmatrix[i][j] = new BarrierCorner(j,i,4);
                        break;

                    //BarrierSide
                    case 10101:
                        testfieldmatrix[i][j] = new BarrierSide(j,i,1);
                        break;
                    case 10102:
                        testfieldmatrix[i][j] = new BarrierSide(j,i,2);
                        break;
                    case 10103:
                        testfieldmatrix[i][j] = new BarrierSide(j,i,3);
                        break;
                    case 10104:
                        testfieldmatrix[i][j] = new BarrierSide(j,i,4);
                        break;

                    //BlackHole
                    case 10200:
                        testfieldmatrix[i][j] = new BlackHole(j,i);
                        break;

                    //Blockade
                    case 10301:
                        testfieldmatrix[i][j] = new Blockade(j,i,1);
                        break;
                    case 10302:
                        testfieldmatrix[i][j] = new Blockade(j,i,2);
                        break;
                    case 10303:
                        testfieldmatrix[i][j] = new Blockade(j,i,3);
                        break;
                    case 10304:
                        testfieldmatrix[i][j] = new Blockade(j,i,4);
                        break;

                    //Checkpoint
                    case 10400:
                        testfieldmatrix[i][j] = new Checkpoint(j,i,0);
                        break;
                    case 10401:
                        testfieldmatrix[i][j] = new Checkpoint(j,i,1);
                        break;
                    case 10402:
                        testfieldmatrix[i][j] = new Checkpoint(j,i,2);
                        break;
                    case 10403:
                        testfieldmatrix[i][j] = new Checkpoint(j,i,3);
                        break;
                    case 10404:
                        testfieldmatrix[i][j] = new Checkpoint(j,i,4);
                        break;
                    case 10405:
                        testfieldmatrix[i][j] = new Checkpoint(j,i,5);
                        break;

                    //ConveyorBelt
                    case 10521:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,2,1);
                        break;
                    case 10531:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,3,1);
                        break;
                    case 10541:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,4,1);
                        break;
                    case 10561:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,6,1);
                        break;
                    case 10571:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,7,1);
                        break;
                    case 10591:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,9,1);
                        break;
                    case 10502:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,0,2);
                        break;
                    case 10512:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,1,2);
                        break;
                    case 10532:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,3,2);
                        break;
                    case 10542:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,4,2);
                        break;
                    case 10552:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,5,2);
                        break;
                    case 10592:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,9,2);
                        break;
                    case 10503:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,0,3);
                        break;
                    case 10513:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,1,3);
                        break;
                    case 10523:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,2,3);
                        break;
                    case 10543:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,4,3);
                        break;
                    case 10563:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,6,3);
                        break;
                    case 10583:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,8,3);
                        break;
                    case 10514:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,1,4);
                        break;
                    case 10524:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,2,4);
                        break;
                    case 10534:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,3,4);
                        break;
                    case 10554:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,5,4);
                        break;
                    case 10574:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,7,4);
                        break;
                    case 10584:
                        testfieldmatrix[i][j] = new ConveyorBelt(j,i,8,4);
                        break;

                    //ExpressConveyorBelt
                    case 10621:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,2,1);
                        break;
                    case 10631:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,3,1);
                        break;
                    case 10641:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,4,1);
                        break;
                    case 10661:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,6,1);
                        break;
                    case 10671:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,7,1);
                        break;
                    case 10691:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,9,1);
                        break;
                    case 10602:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,0,2);
                        break;
                    case 10612:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,1,2);
                        break;
                    case 10632:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,3,2);
                        break;
                    case 10642:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,4,2);
                        break;
                    case 10652:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,5,2);
                        break;
                    case 10692:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,9,2);
                        break;
                    case 10603:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,0,3);
                        break;
                    case 10613:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,1,3);
                        break;
                    case 10623:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,2,3);
                        break;
                    case 10643:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,4,3);
                        break;
                    case 10663:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,6,3);
                        break;
                    case 10683:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,8,3);
                        break;
                    case 10614:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,1,4);
                        break;
                    case 10624:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,2,4);
                        break;
                    case 10634:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,3,4);
                        break;
                    case 10654:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,5,4);
                        break;
                    case 10674:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,7,4);
                        break;
                    case 10684:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(j,i,8,4);
                        break;

                    //Gear
                    case 10701:
                        testfieldmatrix[i][j] = new Gear(j,i,1);
                        break;
                    case 10702:
                        testfieldmatrix[i][j] = new Gear(j,i,2);
                        break;

                    //Laser
                    case 10800:
                        testfieldmatrix[i][j] = new Laser(j,i,0);
                        break;
                    case 10801:
                        testfieldmatrix[i][j] = new Laser(j,i,1);
                        break;
                    case 10802:
                        testfieldmatrix[i][j] = new Laser(j,i,2);
                        break;
                    case 10803:
                        testfieldmatrix[i][j] = new Laser(j,i,3);
                        break;
                    case 10804:
                        testfieldmatrix[i][j] = new Laser(j,i,4);
                        break;
                    case 10805:
                        testfieldmatrix[i][j] = new Laser(j,i,5);
                        break;
                    case 10806:
                        testfieldmatrix[i][j] = new Laser(j,i,6);
                        break;
                    case 10807:
                        testfieldmatrix[i][j] = new Laser(j,i,7);
                        break;
                    case 10808:
                        testfieldmatrix[i][j] = new Laser(j,i,8);
                        break;
                    case 10809:
                        testfieldmatrix[i][j] = new Laser(j,i,9);
                        break;

                    //RepairSite
                    case 10901:
                        testfieldmatrix[i][j] = new RepairSite(j,i,1);
                        break;
                    case 10902:
                        testfieldmatrix[i][j] = new RepairSite(j,i,2);
                        break;

                    //StandardField
                    case 11000:
                        testfieldmatrix[i][j] = new StandardField(j,i);
                        break;

                    //StartField
                    case 11101:
                        testfieldmatrix[i][j] = new StartField(j,i,1);
                        break;
                    case 11102:
                        testfieldmatrix[i][j] = new StartField(j,i,2);
                        break;
                    case 11103:
                        testfieldmatrix[i][j] = new StartField(j,i,3);
                        break;
                    case 11104:
                        testfieldmatrix[i][j] = new StartField(j,i,4);
                        break;
                    case 11105:
                        testfieldmatrix[i][j] = new StartField(j,i,5);
                        break;
                    case 11106:
                        testfieldmatrix[i][j] = new StartField(j,i,6);
                        break;
                    case 11107:
                        testfieldmatrix[i][j] = new StartField(j,i,7);
                        break;
                    case 11108:
                        testfieldmatrix[i][j] = new StartField(j,i,8);
                        break;

                    default:
                        System.out.println("Codierung beschreibt kein gültiges Feldobjekt");
                        break;
                }
            }
        }
        return testfieldmatrix;
    }

    //zum Ausgeben der Objektmatrix mit Klasse und Attributwerten je Objekt
    public static void showFieldMatrix(Field[][] fieldmatrix) {
        for (int i = 0; i < fieldmatrix.length; i++) {
            for (int j = 0; j < fieldmatrix[i].length; j++) {
                System.out.print("(class: " + fieldmatrix[i][j].getClass() + ", " + fieldmatrix[i][j].showAttributes() + ") ");
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
