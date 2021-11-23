import static org.junit.jupiter.api.Assertions.assertEquals;

import htwk.mechawars.board.Board;

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
        fieldmatrix[1][0] = new BarrierCorner(0, 1, 2, true);
        fieldmatrix[2][0] = new BarrierCorner(0, 2, 3, true);
        fieldmatrix[3][0] = new BarrierCorner(0, 3, 4, true);
        fieldmatrix[4][0] = new BarrierSide(0, 4, 1, true);
        fieldmatrix[5][0] = new BarrierSide(0, 5, 2, true);
        fieldmatrix[6][0] = new BarrierSide(0, 6, 3, true);
        fieldmatrix[7][0] = new BarrierSide(0, 7, 4, true);
        fieldmatrix[8][0] = new BlackHole(0, 8, true);
        fieldmatrix[9][0] = new Blockade(0, 9, 1, true);

        fieldmatrix[0][1] = new Blockade(1, 0, 2, true);
        fieldmatrix[1][1] = new Blockade(1, 1, 3, true);
        fieldmatrix[2][1] = new Blockade(1, 2, 4, true);
        fieldmatrix[3][1] = new Checkpoint(1, 3, 1, true);
        fieldmatrix[4][1] = new Checkpoint(1, 4, 2, true);
        fieldmatrix[5][1] = new Checkpoint(1, 5, 3, true);
        fieldmatrix[6][1] = new Checkpoint(1, 6, 4, true);
        fieldmatrix[7][1] = new Checkpoint(1, 7, 5, true);
        fieldmatrix[8][1] = new Checkpoint(1, 8, 6, true);
        fieldmatrix[9][1] = new Checkpoint(1, 9, 7, true);

        fieldmatrix[0][2] = new Checkpoint(2, 0, 8, true);
        fieldmatrix[1][2] = new ConveyorBelt(2, 1, 2, 1, true);
        fieldmatrix[2][2] = new ConveyorBelt(2, 2, 3, 1, true);
        fieldmatrix[3][2] = new ConveyorBelt(2, 3, 4, 1, true);
        fieldmatrix[4][2] = new ConveyorBelt(2, 4, 6, 1, true);
        fieldmatrix[5][2] = new ConveyorBelt(2, 5, 7, 1, true);
        fieldmatrix[6][2] = new ConveyorBelt(2, 6, 9, 1, true);
        fieldmatrix[7][2] = new ConveyorBelt(2, 7, 0, 2, true);
        fieldmatrix[8][2] = new ConveyorBelt(2, 8, 1, 2, true);
        fieldmatrix[9][2] = new ConveyorBelt(2, 9, 3, 2, true);

        fieldmatrix[0][3] = new ConveyorBelt(3, 0, 4, 2, true);
        fieldmatrix[1][3] = new ConveyorBelt(3, 1, 5, 2, true);
        fieldmatrix[2][3] = new ConveyorBelt(3, 2, 9, 2, true);
        fieldmatrix[3][3] = new ConveyorBelt(3, 3, 0, 3, true);
        fieldmatrix[4][3] = new ConveyorBelt(3, 4, 1, 3, true);
        fieldmatrix[5][3] = new ConveyorBelt(3, 5, 2, 3, true);
        fieldmatrix[6][3] = new ConveyorBelt(3, 6, 4, 3, true);
        fieldmatrix[7][3] = new ConveyorBelt(3, 7, 6, 3, true);
        fieldmatrix[8][3] = new ConveyorBelt(3, 8, 8, 3, true);
        fieldmatrix[9][3] = new ConveyorBelt(3, 9, 1, 4, true);

        fieldmatrix[0][4] = new ConveyorBelt(4, 0, 2, 4, true);
        fieldmatrix[1][4] = new ConveyorBelt(4, 1, 3, 4, true);
        fieldmatrix[2][4] = new ConveyorBelt(4, 2, 5, 4, true);
        fieldmatrix[3][4] = new ConveyorBelt(4, 3, 7, 4, true);
        fieldmatrix[4][4] = new ConveyorBelt(4, 4, 8, 4, true);
        fieldmatrix[5][4] = new ExpressConveyorBelt(4, 5, 2, 1, true);
        fieldmatrix[6][4] = new ExpressConveyorBelt(4, 6, 3, 1, true);
        fieldmatrix[7][4] = new ExpressConveyorBelt(4, 7, 4, 1, true);
        fieldmatrix[8][4] = new ExpressConveyorBelt(4, 8, 6, 1, true);
        fieldmatrix[9][4] = new ExpressConveyorBelt(4, 9, 7, 1, true);

        fieldmatrix[0][5] = new ExpressConveyorBelt(5, 0, 9, 1, true);
        fieldmatrix[1][5] = new ExpressConveyorBelt(5, 1, 0, 2, true);
        fieldmatrix[2][5] = new ExpressConveyorBelt(5, 2, 1, 2, true);
        fieldmatrix[3][5] = new ExpressConveyorBelt(5, 3, 3, 2, true);
        fieldmatrix[4][5] = new ExpressConveyorBelt(5, 4, 4, 2, true);
        fieldmatrix[5][5] = new ExpressConveyorBelt(5, 5, 5, 2, true);
        fieldmatrix[6][5] = new ExpressConveyorBelt(5, 6, 9, 2, true);
        fieldmatrix[7][5] = new ExpressConveyorBelt(5, 7, 0, 3, true);
        fieldmatrix[8][5] = new ExpressConveyorBelt(5, 8, 1, 3, true);
        fieldmatrix[9][5] = new ExpressConveyorBelt(5, 9, 2, 3, true);

        fieldmatrix[0][6] = new ExpressConveyorBelt(6, 0, 4, 3, true);
        fieldmatrix[1][6] = new ExpressConveyorBelt(6, 1, 6, 3, true);
        fieldmatrix[2][6] = new ExpressConveyorBelt(6, 2, 8, 3, true);
        fieldmatrix[3][6] = new ExpressConveyorBelt(6, 3, 1, 4, true);
        fieldmatrix[4][6] = new ExpressConveyorBelt(6, 4, 2, 4, true);
        fieldmatrix[5][6] = new ExpressConveyorBelt(6, 5, 3, 4, true);
        fieldmatrix[6][6] = new ExpressConveyorBelt(6, 6, 5, 4, true);
        fieldmatrix[7][6] = new ExpressConveyorBelt(6, 7, 7, 4, true);
        fieldmatrix[8][6] = new ExpressConveyorBelt(6, 8, 8, 4, true);
        fieldmatrix[9][6] = new Gear(6, 9, 1, true);

        fieldmatrix[0][7] = new Gear(7, 0, 2, true);
        fieldmatrix[1][7] = new Laser(7, 1, 0, true);
        fieldmatrix[2][7] = new Laser(7, 2, 1, true);
        fieldmatrix[3][7] = new Laser(7, 3, 2, true);
        fieldmatrix[4][7] = new Laser(7, 4, 3, true);
        fieldmatrix[5][7] = new Laser(7, 5, 4, true);
        fieldmatrix[6][7] = new Laser(7, 6, 5, true);
        fieldmatrix[7][7] = new Laser(7, 7, 6, true);
        fieldmatrix[8][7] = new Laser(7, 8, 7, true);
        fieldmatrix[9][7] = new Laser(7, 9, 8, true);

        fieldmatrix[0][8] = new Laser(8, 0, 9, true);
        fieldmatrix[1][8] = new RepairSite(8, 1, 1, true);
        fieldmatrix[2][8] = new RepairSite(8, 2, 2, true);
        fieldmatrix[3][8] = new StandardField(8, 3, true);
        fieldmatrix[4][8] = new StartField(8, 4, 1, true);
        fieldmatrix[5][8] = new StartField(8, 5, 2, true);
        fieldmatrix[6][8] = new StartField(8, 6, 3, true);
        fieldmatrix[7][8] = new StartField(8, 7, 4, true);
        fieldmatrix[8][8] = new StartField(8, 8, 5, true);
        fieldmatrix[9][8] = new StartField(8, 9, 6, true);

        fieldmatrix[0][9] = new StartField(9, 0, 7, true);
        fieldmatrix[1][9] = new StartField(9, 1, 8, true);
        fieldmatrix[2][9] = new StandardField(9, 2, true);
        fieldmatrix[3][9] = new StandardField(9, 3, true);
        fieldmatrix[4][9] = new StandardField(9, 4, true);
        fieldmatrix[5][9] = new StandardField(9, 5, true);
        fieldmatrix[6][9] = new StandardField(9, 6, true);
        fieldmatrix[7][9] = new StandardField(9, 7, true);
        fieldmatrix[8][9] = new StandardField(9, 8, true);
        fieldmatrix[9][9] = new StandardField(9, 9, true);

        return fieldmatrix;
    }
}