package htwk.mechawars.fieldeditor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import htwk.mechawars.fieldsFiEdit.BarrierCorner;
import htwk.mechawars.fieldsFiEdit.BarrierSide;
import htwk.mechawars.fieldsFiEdit.BlackHole;
import htwk.mechawars.fieldsFiEdit.Checkpoint;
import htwk.mechawars.fieldsFiEdit.ConveyorBelt;
import htwk.mechawars.fieldsFiEdit.ExpressConveyorBelt;
import htwk.mechawars.fieldsFiEdit.Field;
import htwk.mechawars.fieldsFiEdit.Gear;
import htwk.mechawars.fieldsFiEdit.Laser;
import htwk.mechawars.fieldsFiEdit.Pusher;
import htwk.mechawars.fieldsFiEdit.RepairSite;
import htwk.mechawars.fieldsFiEdit.StandardField;
import htwk.mechawars.fieldsFiEdit.StartField;

/**
 * Class that presents the game board.
 */
public class Board {
    public Field[][] fieldmatrix;

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

}
