package htwk.mechawars.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import htwk.mechawars.ConfigReader;
import htwk.mechawars.cards.AiCardGeneration;
import htwk.mechawars.cards.Card;
import htwk.mechawars.cards.Type;
import htwk.mechawars.fields.Field;
import htwk.mechawars.fields.BarrierCorner;
import htwk.mechawars.fields.BarrierSide;
import htwk.mechawars.fields.BlackHole;
import htwk.mechawars.fields.Blockade;
import htwk.mechawars.fields.Checkpoint;
import htwk.mechawars.fields.ConveyorBelt;
import htwk.mechawars.fields.ExpressConveyorBelt;
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

        int[][] matrix = new int[width][height];

        /*
         * matrix should be [col][cell], while tempLayout is [row][cell].
         *                  [x]  [y]                         [y]  [x]
         * Therefore we need to switch them around.
         */
        for (int col = 0; col < height; col++) {
            for (int cell = 0; cell < width; cell++) {
                matrix[col][cell] = tempLayout.get(cell).get(col);
            }
        }

        intToFieldMatrix(matrix, isTest);
    }

    /**
     * Method that creates a field matrix from a int matrix.
     *
     * @param matrix A int matrix
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
                            this.fieldmatrix[col][cell] = new BarrierCorner(cell, col, corner,
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
                            this.fieldmatrix[col][cell] = new BarrierSide(cell, col, side, isTest);
                        } else {
                            System.out.println("Codierung " + matrix[col][cell]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    // BlackHole
                    case 102:
                        fieldmatrix[col][cell] = new BlackHole(cell, col, isTest);
                        break;

                    // Blockade
                    case 103:
                        int typeB = matrix[col][cell] % 10;
                        allowed = new int[]{1, 2, 3, 4};
                        if (Arrays.stream(allowed).anyMatch(x -> x == typeB)) {
                            this.fieldmatrix[col][cell] = new Blockade(cell, col, typeB, isTest);
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
                            this.fieldmatrix[col][cell] = new Checkpoint(cell, col, numberC,
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
                            this.fieldmatrix[col][cell] = new ConveyorBelt(cell, col, startC,
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
                            this.fieldmatrix[col][cell] = new ExpressConveyorBelt(cell, col,
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
                            this.fieldmatrix[col][cell] = new Gear(cell, col, direction, isTest);
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
                            this.fieldmatrix[col][cell] = new Laser(cell, col, typeL, isTest);
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
                            this.fieldmatrix[col][cell] = new RepairSite(cell, col, typeR, isTest);
                        } else {
                            System.out.println("Codierung " + matrix[col][cell]
                                    + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                        }
                        break;

                    // StandardField
                    case 110:
                        this.fieldmatrix[col][cell] = new StandardField(cell, col, isTest);
                        break;

                    // StartField
                    case 111:
                        int numberS = matrix[col][cell] % 10;
                        allowed = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
                        if (Arrays.stream(allowed).anyMatch(x -> x == numberS)) {
                            this.fieldmatrix[col][cell] = new StartField(cell, col, numberS,
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
                fieldstring = fieldstring + "(" + field.getClass() + ", " + field + ") ";
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
     * @param players the robot that should move
     */
    public void move(LinkedList<Card> phase, Robot[] players) {
        move(phase, players, false);
    }

    /**
     * For the tests.
     *
     * @param phase List of cards
     * @param robot the robot that should move
     */
    public void move(LinkedList<Card> phase, Robot robot) {
        Robot[] players = new Robot[1];
        players[0] = robot;
        move(phase, players, true);
    }

    /**
     * Method that moves the robot in the matrix.
     *
     * @param phase List of cards
     * @param players The robots that should move
     * @param isTest indicates that this is a test
     */
    public void move(LinkedList<Card> phase, Robot[] players, boolean isTest) {
        for (int i = 0; i < players.length; i++) {
            if (i > 0 && ConfigReader.getAimodes()[i]) {
                phase = AiCardGeneration.generateRandomAiCards(i);
            }

            if (!isTest) {
                if (ConfigReader.getAimodes()[i] || i == 0) {
                    /* Delay in seconds,
                    increments for each phase in the linked list for another second */
                    int j = 0;
                    for (Card card : phase) {
                        int newI = i;
                        Timer.schedule(new Task() {

                            @Override
                            public void run() {
                                move1(card, players, newI);
                            }
                        }, j);
                        j += 1;
                    }
                }
            } else {
                if (ConfigReader.getAimodes()[i] || i == 0) {
                    // No delay if this is a test
                    for (Card card : phase) {
                        move1(card, players, i);
                        robotPosition =
                                this.fieldmatrix[players[i].getXcoor()][players[i].getYcoor()];
                    }
                }
            }

            if (!isTest) {
                int newI2 = i;
                /* Delay of 5 seconds for the code to run so
                that the robot has reached his final position */
                Timer.schedule(new Task() {

                    @Override
                    public void run() {
                        move2(players, isTest, newI2);
                    }
                }, 5);
            } else {
                // No delay if this is a test
                move2(players, isTest, i);
            }
        }

        //MW57
        if (!isTest) {
            /* Delay of 5 seconds for the code to run so
            that the robot has reached his final position */
            Timer.schedule(new Task() {

                @Override
                public void run() {
                    checkRobotLaser(players);
                    checkBoardLaser(players);
                }
            }, 5);
        } else {
            checkRobotLaser(players);
            checkBoardLaser(players);
        }
    }

    /**
     * Outsourced code from the move function, that would otherwise be duplicated.
     *
     * @param card Current card
     * @param players The robots that should move
     */
    public void move1(Card card, Robot[] players, int i) {
        if (card.getCardAttributeType() == Type.mov) {
            players[i].moveInDirection(card.getCardAttributeMovCount());
        } else {
            players[i].turn(card.getCardAttributeMovCount());
        }
        if (players[i].getXcoor() >= fieldmatrix[1].length
                || players[i].getYcoor() >= fieldmatrix.length
                || players[i].getXcoor() < 0
                || players[i].getYcoor() < 0) {
            players[i].setXcoor(players[i].getStartX());
            players[i].setYcoor(players[i].getStartY());
        }
    }

    /**
     * Outsourced code from the move function, that would otherwise be duplicated.
     *
     * @param players The robots that should move
     * @param isTest indicates that this is a test
     */
    public void move2(Robot[] players, boolean isTest, int i) {
        if (!isTest) {
            robotPosition = fieldmatrix[players[i].getXcoor()]
                    [players[i].getYcoor()];
            robotPosition.turnAction(players[i]);
        }

        checkShutDown(players[i]);
        players[i].setLastRound(players[i].getShutDown());
        players[i].setShutDown(players[i].getNextRound());

        checkDoubleDamage(players[i]);
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
                                    if ((y == currentLaser.getXcoor())
                                            && (x == currentLaser.getYcoor())) {
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
                                    if ((y == currentLaser.getXcoor())
                                            && (x == currentLaser.getYcoor())) {
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
                                    if ((y == currentLaser.getXcoor())
                                            && (x == currentLaser.getYcoor())) {
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
                                    if ((y == currentLaser.getXcoor())
                                            && (x == currentLaser.getYcoor())) {
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
    
    //MW57
    /**
     * Method that checks if a robot got hit by a Laser of another robot.
     *
     * @param players
     */
    public void checkRobotLaser(Robot[] players) {
        
        for (int i = 0; i < players.length; i++) {
            
            int x = players[i].getXcoor();
            int y = players[i].getYcoor();
            
            //the variable z, checks if a player or a wall already have been hit
            int z = 0;
            
            //switch for the direction the player is facing
            switch (players[i].getDir()) {
            case NORTH:
                //for-loop which checks every field from the tile the robot is standing on
                //in his direction, until the end of the board is reached
                //i2 is at first one tile ahead of the robot in the direction he is facing
                for (int i2 = (y-1); i2 >= 0; i2--) {
                    
                    //if a robot has been hit, the loop has too break, happens when z = 1
                    if (z == 1) {
                        break;
                    }
                    
                    //if a Barrier is in the way, the loop immediately breaks
                    if (this.fieldmatrix[x][i2] instanceof BarrierCorner 
                            || this.fieldmatrix[x][i2] instanceof BarrierSide ) {
                        break;
                    }
                    
                    //checks if one of the players is on the current field [x][i2], if yes
                    //he gets damage and z becomes 1, so the loop breaks
                    for (int i3 = 0; i3 < players.length; i3++) {
                        
                        int x2 = players[i3].getXcoor();
                        int y2 = players[i3].getYcoor();
                        
                        if (x2 == x && y2 == i2) {
                            players[i3].damageUp(); 
                            z++;
                        }   
                    }
                }
                
                
            case SOUTH:
                for (int i2 = (y+1); i2 <= 11; i2++) {
                    
                    if (z == 1) {
                        break;
                    }
                    
                    for (int i3 = 0; i3 < players.length; i3++) {
                        
                        int x2 = players[i3].getXcoor();
                        int y2 = players[i3].getYcoor();
                        
                        if (x2 == x && y2 == i2) {
                            players[i3].damageUp();
                            z++;
                        }   
                    } 
                }
                
                
            case EAST:
                for (int i2 = (x+1); i2 <= 11; i2++) {
                    
                    if (z == 1) {
                        break;
                    }
                    
                    for (int i3 = 0; i3 < players.length; i3++) {
                        
                        int x2 = players[i3].getXcoor();
                        int y2 = players[i3].getYcoor();
                        
                        if (x2 == i2 && y2 == y) {
                            players[i3].damageUp();
                            z++;
                        }   
                    }
                }
                
                
            case WEST:
                for (int i2 = (x-1); i2 >= 0; i2--) {
                    
                    if (z == 1) {
                        break;
                    }
                    
                    for (int i3 = 0; i3 < players.length; i3++) {
                        
                        int x2 = players[i3].getXcoor();
                        int y2 = players[i3].getYcoor();
                        
                        if (x2 == i2 && y2 == y) {
                            players[i3].damageUp();
                            z++;
                        }   
                    }                    
                }    
            }       
        }
    }
}

