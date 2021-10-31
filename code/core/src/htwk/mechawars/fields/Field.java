package htwk.mechawars.fields;

import htwk.mechawars.board.Board;
import htwk.mechawars.GameScreen;

/**
 * Class of a Field.
 */
public class Field {

    protected int xcoor;
    protected int ycoor;

    /**
     * Constructor of a Field.
     */
    public Field(int xcoor, int ycoor) {
        this.xcoor = xcoor;
        this.ycoor = ycoor;
    }

    public static void createObjektMatrix(Board testboard){
        Field[][] testfieldmatrix = new Field[testboard.matrix.length][testboard.matrix[0].length];
        for (int i = 0; i < testboard.matrix.length; i++) {
            for (int j = 0; j < testboard.matrix[i].length; j++) {
                switch(testboard.matrix[i][j]){

                    //BarrierCorner
                    case 10001:
                        testfieldmatrix[i][j] = new BarrierCorner(i,j,1);
                        break;
                    case 10002:
                        testfieldmatrix[i][j] = new BarrierCorner(i,j,2);
                        break;
                    case 10003:
                        testfieldmatrix[i][j] = new BarrierCorner(i,j,3);
                        break;
                    case 10004:
                        testfieldmatrix[i][j] = new BarrierCorner(i,j,4);
                        break;

                    //BarrierSide
                    case 10101:
                        testfieldmatrix[i][j] = new BarrierSide(i,j,1);
                        break;
                    case 10102:
                        testfieldmatrix[i][j] = new BarrierSide(i,j,2);
                        break;
                    case 10103:
                        testfieldmatrix[i][j] = new BarrierSide(i,j,3);
                        break;
                    case 10104:
                        testfieldmatrix[i][j] = new BarrierSide(i,j,4);
                        break;

                    //BlackHole
                    case 10200:
                        testfieldmatrix[i][j] = new BlackHole(i,j);
                        break;

                    //Blockade
                    case 10301:
                        testfieldmatrix[i][j] = new Blockade(i,j,1);
                        break;
                    case 10302:
                        testfieldmatrix[i][j] = new Blockade(i,j,2);
                        break;
                    case 10303:
                        testfieldmatrix[i][j] = new Blockade(i,j,3);
                        break;
                    case 10304:
                        testfieldmatrix[i][j] = new Blockade(i,j,4);
                        break;

                    //Checkpoint
                    case 10400:
                        testfieldmatrix[i][j] = new Checkpoint(i,j,0);
                        break;
                    case 10401:
                        testfieldmatrix[i][j] = new Checkpoint(i,j,1);
                        break;
                    case 10402:
                        testfieldmatrix[i][j] = new Checkpoint(i,j,2);
                        break;
                    case 10403:
                        testfieldmatrix[i][j] = new Checkpoint(i,j,3);
                        break;
                    case 10404:
                        testfieldmatrix[i][j] = new Checkpoint(i,j,4);
                        break;
                    case 10405:
                        testfieldmatrix[i][j] = new Checkpoint(i,j,5);
                        break;

                    //ConveyorBelt
                    case 10521:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,2,1);
                        break;
                    case 10531:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,3,1);
                        break;
                    case 10541:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,4,1);
                        break;
                    case 10561:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,6,1);
                        break;
                    case 10571:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,7,1);
                        break;
                    case 10591:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,9,1);
                        break;
                    case 10502:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,0,2);
                        break;
                    case 10512:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,1,2);
                        break;
                    case 10532:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,3,2);
                        break;
                    case 10542:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,4,2);
                        break;
                    case 10552:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,5,2);
                        break;
                    case 10592:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,9,2);
                        break;
                    case 10503:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,0,3);
                        break;
                    case 10513:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,1,3);
                        break;
                    case 10523:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,2,3);
                        break;
                    case 10543:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,4,3);
                        break;
                    case 10563:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,6,3);
                        break;
                    case 10583:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,8,3);
                        break;
                    case 10514:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,1,4);
                        break;
                    case 10524:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,2,4);
                        break;
                    case 10534:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,3,4);
                        break;
                    case 10554:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,5,4);
                        break;
                    case 10574:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,7,4);
                        break;
                    case 10584:
                        testfieldmatrix[i][j] = new ConveyorBelt(i,j,8,4);
                        break;

                    //ExpressConveyorBelt
                    case 10621:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,2,1);
                        break;
                    case 10631:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,3,1);
                        break;
                    case 10641:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,4,1);
                        break;
                    case 10661:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,6,1);
                        break;
                    case 10671:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,7,1);
                        break;
                    case 10691:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,9,1);
                        break;
                    case 10602:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,0,2);
                        break;
                    case 10612:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,1,2);
                        break;
                    case 10632:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,3,2);
                        break;
                    case 10642:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,4,2);
                        break;
                    case 10652:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,5,2);
                        break;
                    case 10692:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,9,2);
                        break;
                    case 10603:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,0,3);
                        break;
                    case 10613:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,1,3);
                        break;
                    case 10623:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,2,3);
                        break;
                    case 10643:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,4,3);
                        break;
                    case 10663:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,6,3);
                        break;
                    case 10683:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,8,3);
                        break;
                    case 10614:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,1,4);
                        break;
                    case 10624:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,2,4);
                        break;
                    case 10634:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,3,4);
                        break;
                    case 10654:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,5,4);
                        break;
                    case 10674:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,7,4);
                        break;
                    case 10684:
                        testfieldmatrix[i][j] = new ExpressConveyorBelt(i,j,8,4);
                        break;

                    //Gear
                    case 10701:
                        testfieldmatrix[i][j] = new Gear(i,j,1);
                        break;
                    case 10702:
                        testfieldmatrix[i][j] = new Gear(i,j,2);
                        break;

                    //Laser
                    case 10800:
                        testfieldmatrix[i][j] = new Laser(i,j,0);
                        break;
                    case 10801:
                        testfieldmatrix[i][j] = new Laser(i,j,1);
                        break;
                    case 10802:
                        testfieldmatrix[i][j] = new Laser(i,j,2);
                        break;
                    case 10803:
                        testfieldmatrix[i][j] = new Laser(i,j,3);
                        break;
                    case 10804:
                        testfieldmatrix[i][j] = new Laser(i,j,4);
                        break;
                    case 10805:
                        testfieldmatrix[i][j] = new Laser(i,j,5);
                        break;
                    case 10806:
                        testfieldmatrix[i][j] = new Laser(i,j,6);
                        break;
                    case 10807:
                        testfieldmatrix[i][j] = new Laser(i,j,7);
                        break;
                    case 10808:
                        testfieldmatrix[i][j] = new Laser(i,j,8);
                        break;
                    case 10809:
                        testfieldmatrix[i][j] = new Laser(i,j,9);
                        break;

                    //RepairSite
                    case 10901:
                        testfieldmatrix[i][j] = new RepairSite(i,j,1);
                        break;
                    case 10902:
                        testfieldmatrix[i][j] = new RepairSite(i,j,2);
                        break;

                    //StandardField
                    case 11000:
                        testfieldmatrix[i][j] = new StandardField(i,j);
                        break;

                    //StartField
                    case 11101:
                        testfieldmatrix[i][j] = new StartField(i,j,1);
                        break;
                    case 11102:
                        testfieldmatrix[i][j] = new StartField(i,j,2);
                        break;
                    case 11103:
                        testfieldmatrix[i][j] = new StartField(i,j,3);
                        break;
                    case 11104:
                        testfieldmatrix[i][j] = new StartField(i,j,4);
                        break;
                    case 11105:
                        testfieldmatrix[i][j] = new StartField(i,j,5);
                        break;
                    case 11106:
                        testfieldmatrix[i][j] = new StartField(i,j,6);
                        break;
                    case 11107:
                        testfieldmatrix[i][j] = new StartField(i,j,7);
                        break;
                    case 11108:
                        testfieldmatrix[i][j] = new StartField(i,j,8);
                        break;


                    default:
                        System.out.println("Codierung beschreibt kein gültiges Feldobjekt");
                        break;
                }
            }
        }

        //zum Ausgeben der Objektmatrix mit Codierung, Klasse und Attributen
        for (int i = 0; i < testfieldmatrix.length; i++) {
            for (int j = 0; j < testfieldmatrix[i].length; j++) {
                System.out.print("(coding: "+ testboard.matrix[i][j] + ", class: " + testfieldmatrix[i][j].getClass() + ", " + testfieldmatrix[i][j].showAttributes() + ") ");
            }
            System.out.println();
        }
    }

    public String showAttributes(){
        String attributes = "xcoor: " + this.xcoor + "ycoor: " + this.ycoor;
        return attributes;
    }

    /**
     * Take the x coordinate.
     * @return xcoor The x coordinate
     */
    public int getXcoor(){
        return this.xcoor;
    }

    /**
     * Take the y coordinate.
     * @return ycoor The y coordinate
     */
    public int getYcoor(){
        return this.ycoor;
    }
}
