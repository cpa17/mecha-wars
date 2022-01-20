import static org.junit.jupiter.api.Assertions.assertEquals;

import htwk.mechawars.board.Board;

import htwk.mechawars.fields.BlackHole;
import htwk.mechawars.fields.Pusher;
import htwk.mechawars.fields.Checkpoint;
import htwk.mechawars.fields.ConveyorBelt;
import htwk.mechawars.fields.ExpressConveyorBelt;
import htwk.mechawars.fields.Field;
import htwk.mechawars.fields.Gear;
import htwk.mechawars.fields.RepairSite;
import htwk.mechawars.fields.StandardField;
import htwk.mechawars.fields.StartField;

import org.junit.jupiter.api.Test;

/**
 * Class that tests the creation of the game board and places the robot.
 */

public class BoardTest {
    private Board board = new Board(createBoardString(), true);
    private Field[][] fieldmatrix = createFieldmatrix();

    @Test
    public void boardTest() {
        assertEquals(board.fieldmatrix.length, fieldmatrix.length);
        assertEquals(board.fieldmatrix[0].length, fieldmatrix[0].length);
        for (int i = 0; i < board.fieldmatrix.length; i++) {
            for (int j = 0; j < board.fieldmatrix[i].length; j++) {
                assertEquals(board.fieldmatrix[i][j], fieldmatrix[i][j]);
            }
        }
    }

    /**
     * Method that generates a String for the board constructor.
     */
    private String createBoardString() {
        String dummyVar = "";
        String boardString = dummyVar +
                "990000000 990000101 990000102 990000103 990000104 990000201 990000202 \n" +
                "990000203 990000204 990000205 990000206 990000207 990000208 990000321 \n" +
                "990000331 990000341 990000361 990000371 990000391 990000302 990000312 \n" +
                "990000332 990000342 990000352 990000392 990000303 990000313 990000323 \n" +
                "990000343 990000363 990000383 990000314 990000324 990000334 990000354 \n" +
                "990000374 990000384 990000421 990000431 990000441 990000461 990000471 \n" +
                "990000491 990000402 990000412 990000432 990000442 990000452 990000492 \n" +
                "990000403 990000413 990000423 990000443 990000463 990000483 990000414 \n" +
                "990000424 990000434 990000454 990000474 990000484 990000501 990000502 \n" +
                "990000601 990000602 990000700 990000801 990000802 990000803 990000804 \n" +
                "990000805 990000806 990000807 990000808 120000700 130000700 140000700 \n" +
                "350000700 360000700 990001700 991010700 990011700 990110700 991101700";
        return boardString;
    }

    /**
     * Method that generates a field matrix.
     */
    public Field[][] createFieldmatrix() {
        fieldmatrix = new Field[7][12];

        fieldmatrix[0][0] = new BlackHole(0, 0, true);
        fieldmatrix[1][0] = new Pusher(1, 0, 1, true);
        fieldmatrix[2][0] = new Pusher(2, 0, 2, true);
        fieldmatrix[3][0] = new Pusher(3, 0, 3, true);
        fieldmatrix[4][0] = new Pusher(4, 0, 4, true);
        fieldmatrix[5][0] = new Checkpoint(5, 0, 1, true);
        fieldmatrix[6][0] = new Checkpoint(6, 0, 2, true);

        fieldmatrix[0][1] = new Checkpoint(0, 1, 3, true);
        fieldmatrix[1][1] = new Checkpoint(1, 1, 4, true);
        fieldmatrix[2][1] = new Checkpoint(2, 1, 5, true);
        fieldmatrix[3][1] = new Checkpoint(3, 1, 6, true);
        fieldmatrix[4][1] = new Checkpoint(4, 1, 7, true);
        fieldmatrix[5][1] = new Checkpoint(5, 1, 8, true);
        fieldmatrix[6][1] = new ConveyorBelt(6, 1, 2, 1, true);

        fieldmatrix[0][2] = new ConveyorBelt(0, 2, 3, 1, true);
        fieldmatrix[1][2] = new ConveyorBelt(1, 2, 4, 1, true);
        fieldmatrix[2][2] = new ConveyorBelt(2, 2, 6, 1, true);
        fieldmatrix[3][2] = new ConveyorBelt(3, 2, 7, 1, true);
        fieldmatrix[4][2] = new ConveyorBelt(4, 2, 9, 1, true);
        fieldmatrix[5][2] = new ConveyorBelt(5, 2, 0, 2, true);
        fieldmatrix[6][2] = new ConveyorBelt(6, 2, 1, 2, true);

        fieldmatrix[0][3] = new ConveyorBelt(0, 3, 3, 2, true);
        fieldmatrix[1][3] = new ConveyorBelt(1, 3, 4, 2, true);
        fieldmatrix[2][3] = new ConveyorBelt(2, 3, 5, 2, true);
        fieldmatrix[3][3] = new ConveyorBelt(3, 3, 9, 2, true);
        fieldmatrix[4][3] = new ConveyorBelt(4, 3, 0, 3, true);
        fieldmatrix[5][3] = new ConveyorBelt(5, 3, 1, 3, true);
        fieldmatrix[6][3] = new ConveyorBelt(6, 3, 2, 3, true);

        fieldmatrix[0][4] = new ConveyorBelt(0, 4, 4, 3, true);
        fieldmatrix[1][4] = new ConveyorBelt(1, 4, 6, 3, true);
        fieldmatrix[2][4] = new ConveyorBelt(2, 4, 8, 3, true);
        fieldmatrix[3][4] = new ConveyorBelt(3, 4, 1, 4, true);
        fieldmatrix[4][4] = new ConveyorBelt(4, 4, 2, 4, true);
        fieldmatrix[5][4] = new ConveyorBelt(5, 4, 3, 4, true);
        fieldmatrix[6][4] = new ConveyorBelt(6, 4, 5, 4, true);

        fieldmatrix[0][5] = new ConveyorBelt(0, 5, 7, 4, true);
        fieldmatrix[1][5] = new ConveyorBelt(1, 5, 8, 4, true);
        fieldmatrix[2][5] = new ExpressConveyorBelt(2, 5, 2, 1, true);
        fieldmatrix[3][5] = new ExpressConveyorBelt(3, 5, 3, 1, true);
        fieldmatrix[4][5] = new ExpressConveyorBelt(4, 5, 4, 1, true);
        fieldmatrix[5][5] = new ExpressConveyorBelt(5, 5, 6, 1, true);
        fieldmatrix[6][5] = new ExpressConveyorBelt(6, 5, 7, 1, true);

        fieldmatrix[0][6] = new ExpressConveyorBelt(0, 6, 9, 1, true);
        fieldmatrix[1][6] = new ExpressConveyorBelt(1, 6, 0, 2, true);
        fieldmatrix[2][6] = new ExpressConveyorBelt(2, 6, 1, 2, true);
        fieldmatrix[3][6] = new ExpressConveyorBelt(3, 6, 3, 2, true);
        fieldmatrix[4][6] = new ExpressConveyorBelt(4, 6, 4, 2, true);
        fieldmatrix[5][6] = new ExpressConveyorBelt(5, 6, 5, 2, true);
        fieldmatrix[6][6] = new ExpressConveyorBelt(6, 6, 9, 2, true);

        fieldmatrix[0][7] = new ExpressConveyorBelt(0, 7, 0, 3, true);
        fieldmatrix[1][7] = new ExpressConveyorBelt(1, 7, 1, 3, true);
        fieldmatrix[2][7] = new ExpressConveyorBelt(2, 7, 2, 3, true);
        fieldmatrix[3][7] = new ExpressConveyorBelt(3, 7, 4, 3, true);
        fieldmatrix[4][7] = new ExpressConveyorBelt(4, 7, 6, 3, true);
        fieldmatrix[5][7] = new ExpressConveyorBelt(5, 7, 8, 3, true);
        fieldmatrix[6][7] = new ExpressConveyorBelt(6, 7, 1, 4, true);

        fieldmatrix[0][8] = new ExpressConveyorBelt(0, 8, 2, 4, true);
        fieldmatrix[1][8] = new ExpressConveyorBelt(1, 8, 3, 4, true);
        fieldmatrix[2][8] = new ExpressConveyorBelt(2, 8, 5, 4, true);
        fieldmatrix[3][8] = new ExpressConveyorBelt(3, 8, 7, 4, true);
        fieldmatrix[4][8] = new ExpressConveyorBelt(4, 8, 8, 4, true);
        fieldmatrix[5][8] = new Gear(5, 8, 1, true);
        fieldmatrix[6][8] = new Gear(6, 8, 2, true);

        fieldmatrix[0][9] = new RepairSite(0, 9, 1, true);
        fieldmatrix[1][9] = new RepairSite(1, 9, 2, true);
        fieldmatrix[2][9] = new StandardField(2, 9, true);
        fieldmatrix[3][9] = new StartField(3, 9, 1, true);
        fieldmatrix[4][9] = new StartField(4, 9, 2, true);
        fieldmatrix[5][9] = new StartField(5, 9, 3, true);
        fieldmatrix[6][9] = new StartField(6, 9, 4, true);

        fieldmatrix[0][10] = new StartField(0, 10, 5, true);
        fieldmatrix[1][10] = new StartField(1, 10, 6, true);
        fieldmatrix[2][10] = new StartField(2, 10, 7, true);
        fieldmatrix[3][10] = new StartField(3, 10, 8, true);
        fieldmatrix[4][10] = new StandardField(4, 10, 1, 2,
                false, false, false, false, true);
        fieldmatrix[5][10] = new StandardField(5, 10, 1, 3,
                false, false, false, false, true);
        fieldmatrix[6][10] = new StandardField(6, 10, 1, 4,
                false, false, false, false, true);

        fieldmatrix[0][11] = new StandardField(0, 11, 3, 5,
                false, false, false, false, true);
        fieldmatrix[1][11] = new StandardField(1, 11, 3, 6,
                false, false, false, false, true);
        fieldmatrix[2][11] = new StandardField(2, 11, 9, 9,
                false, false, false, true, true);
        fieldmatrix[3][11] = new StandardField(3, 11, 9, 9,
                true, false, true, false, true);
        fieldmatrix[4][11] = new StandardField(4, 11, 9, 9,
                false, false, true, true, true);
        fieldmatrix[5][11] = new StandardField(5, 11, 9, 9,
                false, true, true, false, true);
        fieldmatrix[6][11] = new StandardField(6, 11, 9, 9,
                true, true, false, true, true);

        final Field[][] copiedFieldmatrix = fieldmatrix;
        return copiedFieldmatrix;
    }
}