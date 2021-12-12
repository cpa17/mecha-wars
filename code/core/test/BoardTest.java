import static org.junit.jupiter.api.Assertions.assertEquals;

import htwk.mechawars.board.Board;

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
                "10001 10002 10003 10004 10101 10102 10103 10104 10200 10301 \n" +
                "10302 10303 10304 10401 10402 10403 10404 10405 10406 10407 \n" +
                "10408 10521 10531 10541 10561 10571 10591 10502 10512 10532 \n" +
                "10542 10552 10592 10503 10513 10523 10543 10563 10583 10514 \n" +
                "10524 10534 10554 10574 10584 10621 10631 10641 10661 10671 \n" +
                "10691 10602 10612 10632 10642 10652 10692 10603 10613 10623 \n" +
                "10643 10663 10683 10614 10624 10634 10654 10674 10684 10701 \n" +
                "10702 10800 10801 10802 10803 10804 10805 10806 10807 10808 \n" +
                "10809 10901 10902 11000 11101 11102 11103 11104 11105 11106 \n" +
                "11107 11108 11000 11000 11000 11000 11000 11000 11000 11000";
        return boardString;
    }

    /**
     * Method that generates a field matrix.
     */
    public Field[][] createFieldmatrix() {
        fieldmatrix = new Field[10][10];

        fieldmatrix[0][0] = new BarrierCorner(0, 0, 1, true);
        fieldmatrix[1][0] = new BarrierCorner(1, 0, 2, true);
        fieldmatrix[2][0] = new BarrierCorner(2, 0, 3, true);
        fieldmatrix[3][0] = new BarrierCorner(3, 0, 4, true);
        fieldmatrix[4][0] = new BarrierSide(4, 0, 1, true);
        fieldmatrix[5][0] = new BarrierSide(5, 0, 2, true);
        fieldmatrix[6][0] = new BarrierSide(6, 0, 3, true);
        fieldmatrix[7][0] = new BarrierSide(7, 0, 4, true);
        fieldmatrix[8][0] = new BlackHole(8, 0, true);
        fieldmatrix[9][0] = new Pusher(9, 0, 1, true);

        fieldmatrix[0][1] = new Pusher(0, 1, 2, true);
        fieldmatrix[1][1] = new Pusher(1, 1, 3, true);
        fieldmatrix[2][1] = new Pusher(2, 1, 4, true);
        fieldmatrix[3][1] = new Checkpoint(3, 1, 1, true);
        fieldmatrix[4][1] = new Checkpoint(4, 1, 2, true);
        fieldmatrix[5][1] = new Checkpoint(5, 1, 3, true);
        fieldmatrix[6][1] = new Checkpoint(6, 1, 4, true);
        fieldmatrix[7][1] = new Checkpoint(7, 1, 5, true);
        fieldmatrix[8][1] = new Checkpoint(8, 1, 6, true);
        fieldmatrix[9][1] = new Checkpoint(9, 1, 7, true);

        fieldmatrix[0][2] = new Checkpoint(0, 2, 8, true);
        fieldmatrix[1][2] = new ConveyorBelt(1, 2, 2, 1, true);
        fieldmatrix[2][2] = new ConveyorBelt(2, 2, 3, 1, true);
        fieldmatrix[3][2] = new ConveyorBelt(3, 2, 4, 1, true);
        fieldmatrix[4][2] = new ConveyorBelt(4, 2, 6, 1, true);
        fieldmatrix[5][2] = new ConveyorBelt(5, 2, 7, 1, true);
        fieldmatrix[6][2] = new ConveyorBelt(6, 2, 9, 1, true);
        fieldmatrix[7][2] = new ConveyorBelt(7, 2, 0, 2, true);
        fieldmatrix[8][2] = new ConveyorBelt(8, 2, 1, 2, true);
        fieldmatrix[9][2] = new ConveyorBelt(9, 2, 3, 2, true);

        fieldmatrix[0][3] = new ConveyorBelt(0, 3, 4, 2, true);
        fieldmatrix[1][3] = new ConveyorBelt(1, 3, 5, 2, true);
        fieldmatrix[2][3] = new ConveyorBelt(2, 3, 9, 2, true);
        fieldmatrix[3][3] = new ConveyorBelt(3, 3, 0, 3, true);
        fieldmatrix[4][3] = new ConveyorBelt(4, 3, 1, 3, true);
        fieldmatrix[5][3] = new ConveyorBelt(5, 3, 2, 3, true);
        fieldmatrix[6][3] = new ConveyorBelt(6, 3, 4, 3, true);
        fieldmatrix[7][3] = new ConveyorBelt(7, 3, 6, 3, true);
        fieldmatrix[8][3] = new ConveyorBelt(8, 3, 8, 3, true);
        fieldmatrix[9][3] = new ConveyorBelt(9, 3, 1, 4, true);

        fieldmatrix[0][4] = new ConveyorBelt(0, 4, 2, 4, true);
        fieldmatrix[1][4] = new ConveyorBelt(1, 4, 3, 4, true);
        fieldmatrix[2][4] = new ConveyorBelt(2, 4, 5, 4, true);
        fieldmatrix[3][4] = new ConveyorBelt(3, 4, 7, 4, true);
        fieldmatrix[4][4] = new ConveyorBelt(4, 4, 8, 4, true);
        fieldmatrix[5][4] = new ExpressConveyorBelt(5, 4, 2, 1, true);
        fieldmatrix[6][4] = new ExpressConveyorBelt(6, 4, 3, 1, true);
        fieldmatrix[7][4] = new ExpressConveyorBelt(7, 4, 4, 1, true);
        fieldmatrix[8][4] = new ExpressConveyorBelt(8, 4, 6, 1, true);
        fieldmatrix[9][4] = new ExpressConveyorBelt(9, 4, 7, 1, true);

        fieldmatrix[0][5] = new ExpressConveyorBelt(0, 5, 9, 1, true);
        fieldmatrix[1][5] = new ExpressConveyorBelt(1, 5, 0, 2, true);
        fieldmatrix[2][5] = new ExpressConveyorBelt(2, 5, 1, 2, true);
        fieldmatrix[3][5] = new ExpressConveyorBelt(3, 5, 3, 2, true);
        fieldmatrix[4][5] = new ExpressConveyorBelt(4, 5, 4, 2, true);
        fieldmatrix[5][5] = new ExpressConveyorBelt(5, 5, 5, 2, true);
        fieldmatrix[6][5] = new ExpressConveyorBelt(6, 5, 9, 2, true);
        fieldmatrix[7][5] = new ExpressConveyorBelt(7, 5, 0, 3, true);
        fieldmatrix[8][5] = new ExpressConveyorBelt(8, 5, 1, 3, true);
        fieldmatrix[9][5] = new ExpressConveyorBelt(9, 5, 2, 3, true);

        fieldmatrix[0][6] = new ExpressConveyorBelt(0, 6, 4, 3, true);
        fieldmatrix[1][6] = new ExpressConveyorBelt(1, 6, 6, 3, true);
        fieldmatrix[2][6] = new ExpressConveyorBelt(2, 6, 8, 3, true);
        fieldmatrix[3][6] = new ExpressConveyorBelt(3, 6, 1, 4, true);
        fieldmatrix[4][6] = new ExpressConveyorBelt(4, 6, 2, 4, true);
        fieldmatrix[5][6] = new ExpressConveyorBelt(5, 6, 3, 4, true);
        fieldmatrix[6][6] = new ExpressConveyorBelt(6, 6, 5, 4, true);
        fieldmatrix[7][6] = new ExpressConveyorBelt(7, 6, 7, 4, true);
        fieldmatrix[8][6] = new ExpressConveyorBelt(8, 6, 8, 4, true);
        fieldmatrix[9][6] = new Gear(9, 6, 1, true);

        fieldmatrix[0][7] = new Gear(0, 7, 2, true);
        fieldmatrix[1][7] = new Laser(1, 7, 0, true);
        fieldmatrix[2][7] = new Laser(2, 7, 1, true);
        fieldmatrix[3][7] = new Laser(3, 7, 2, true);
        fieldmatrix[4][7] = new Laser(4, 7, 3, true);
        fieldmatrix[5][7] = new Laser(5, 7, 4, true);
        fieldmatrix[6][7] = new Laser(6, 7, 5, true);
        fieldmatrix[7][7] = new Laser(7, 7, 6, true);
        fieldmatrix[8][7] = new Laser(8, 7, 7, true);
        fieldmatrix[9][7] = new Laser(9, 7, 8, true);

        fieldmatrix[0][8] = new Laser(0, 8, 9, true);
        fieldmatrix[1][8] = new RepairSite(1, 8, 1, true);
        fieldmatrix[2][8] = new RepairSite(2, 8, 2, true);
        fieldmatrix[3][8] = new StandardField(3, 8, true);
        fieldmatrix[4][8] = new StartField(4, 8, 1, true);
        fieldmatrix[5][8] = new StartField(5, 8, 2, true);
        fieldmatrix[6][8] = new StartField(6, 8, 3, true);
        fieldmatrix[7][8] = new StartField(7, 8, 4, true);
        fieldmatrix[8][8] = new StartField(8, 8, 5, true);
        fieldmatrix[9][8] = new StartField(9, 8, 6, true);

        fieldmatrix[0][9] = new StartField(0, 9, 7, true);
        fieldmatrix[1][9] = new StartField(1, 9, 8, true);
        fieldmatrix[2][9] = new StandardField(2, 9, true);
        fieldmatrix[3][9] = new StandardField(3, 9, true);
        fieldmatrix[4][9] = new StandardField(4, 9, true);
        fieldmatrix[5][9] = new StandardField(5, 9, true);
        fieldmatrix[6][9] = new StandardField(6, 9, true);
        fieldmatrix[7][9] = new StandardField(7, 9, true);
        fieldmatrix[8][9] = new StandardField(8, 9, true);
        fieldmatrix[9][9] = new StandardField(9, 9, true);

        final Field[][] copiedFieldmatrix = fieldmatrix;
        return copiedFieldmatrix;
    }
}