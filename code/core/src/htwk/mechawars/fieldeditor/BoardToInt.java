package htwk.mechawars.fieldeditor;

import java.util.ArrayList;
import java.util.Arrays;

import htwk.mechawars.board.Board;
import htwk.mechawars.fields.BarrierCorner;
import htwk.mechawars.fields.BarrierSide;
import htwk.mechawars.fields.BlackHole;
import htwk.mechawars.fields.Checkpoint;
import htwk.mechawars.fields.ConveyorBelt;
import htwk.mechawars.fields.ExpressConveyorBelt;
import htwk.mechawars.fields.Gear;
import htwk.mechawars.fields.Laser;
import htwk.mechawars.fields.Pusher;
import htwk.mechawars.fields.RepairSite;
import htwk.mechawars.fields.StandardField;
import htwk.mechawars.fields.StartField;

/**
 * 
 * @author -.-
 *
 */
public class BoardToInt {
    
    public BoardToInt() {
        
    }
    
    /**
     * Function, to convert an Board / map to a List of Arrays, so it can be saved/exported.
     * 
     * @param list -> gives the ArrayList, in which the board should be saved.
     * @param board -> gives the Board / map, which is the base of changes.
     * @return
     */
    public ArrayList<Integer> convert(ArrayList<Integer> list, Board board){
        
        for (int x = 0; x < board.fieldmatrix.length; x += 1) {
            for (int y = 0; y < board.fieldmatrix[x].length; y += 1) {
                String abc = board.fieldmatrix[x][y].getTile().toString();
                abc = abc.substring(Math.abs(abc.lastIndexOf("/")) + 1, Math.abs(abc.lastIndexOf(".")));
                switch(abc) {
                case "BarrierCorner01" : list.add(10001);
                    break;
                case "BarrierCorner02" : list.add(10002);
                    break;
                case "BarrierCorner03" : list.add(10003);
                    break;
                case "BarrierCorner04" : list.add(10004);
                    break;
                
                case "BarrierSide1" : list.add(10101);
                    break;
                case "BarrierSide2" : list.add(10102);
                    break;
                case "BarrierSide3" : list.add(10103);
                    break;
                case "BarrierSide4" : list.add(10104);
                    break;
                    
                case "BlackHole" :  list.add(10200);
                    break;
                
                case "Pusher01" : list.add(10301);
                    break;
                case "Pusher02" : list.add(10302);
                    break;
                case "Pusher03" : list.add(10303);
                    break;
                case "Pusher04" : list.add(10304);
                    break;
                    
                case "Check1" : list.add(10401);
                    break;
                case "Check2" : list.add(10402);
                    break;
                case "Check3" : list.add(10403);
                    break;
                case "Check4" : list.add(10404);
                    break;
                case "Check5" : list.add(10405);
                    break;
                case "Check6" : list.add(10406);
                    break;
                case "Check7" : list.add(10407);
                    break;
                case "Check8" : list.add(10408);
                    break;
                    
                case "ConveyorBelt02" : list.add(10502);
                    break;
                case "ConveyorBelt03" : list.add(10503);
                    break;
                case "ConveyorBelt12" : list.add(10512);
                    break;
                case "ConveyorBelt13" : list.add(10513);
                    break;
                case "ConveyorBelt14" : list.add(10514);
                    break;
                case "ConveyorBelt21" : list.add(10521);
                    break;
                case "ConveyorBelt23" : list.add(10523);
                    break;
                case "ConveyorBelt24" : list.add(10524);
                    break;
                case "ConveyorBelt31" : list.add(10531);
                    break;
                case "ConveyorBelt32" : list.add(10532);
                    break;
                case "ConveyorBelt34" : list.add(10534);
                    break;
                case "ConveyorBelt41" : list.add(10541);
                    break;
                case "ConveyorBelt42" : list.add(10542);
                    break;
                case "ConveyorBelt43" : list.add(10543);
                    break;
                case "ConveyorBelt52" : list.add(10552);
                    break;
                case "ConveyorBelt54" : list.add(10554);
                    break;
                case "ConveyorBelt61" : list.add(10561);
                    break;
                case "ConveyorBelt63" : list.add(10563);
                    break;
                case "ConveyorBelt71" : list.add(10571);
                    break;
                case "ConveyorBelt74" : list.add(10574);
                    break;
                case "ConveyorBelt83" : list.add(10583);
                    break;
                case "ConveyorBelt84" : list.add(10584);
                    break;
                case "ConveyorBelt91" : list.add(10591);
                    break;
                case "ConveyorBelt92" : list.add(10592);
                    break;

                case "ExpressConveyorBelt02" : list.add(10602);
                    break;
                case "ExpressConveyorBelt03" : list.add(10603);
                    break;
                case "ExpressConveyorBelt12" : list.add(10612);
                    break;
                case "ExpressConveyorBelt13" : list.add(10613);
                    break;
                case "ExpressConveyorBelt14" : list.add(10614);
                    break;
                case "ExpressConveyorBelt21" : list.add(10621);
                    break;
                case "ExpressConveyorBelt23" : list.add(10623);
                    break;
                case "ExpressConveyorBelt24" : list.add(10624);
                    break;
                case "ExpressConveyorBelt31" : list.add(10631);
                    break;
                case "ExpressConveyorBelt32" : list.add(10632);
                    break;
                case "ExpressConveyorBelt34" : list.add(10634);
                    break;
                case "ExpressConveyorBelt41" : list.add(10641);
                    break;
                case "ExpressConveyorBelt42" : list.add(10642);
                    break;
                case "ExpressConveyorBelt43" : list.add(10643);
                    break;
                case "ExpressConveyorBelt52" : list.add(10652);
                    break;
                case "ExpressConveyorBelt54" : list.add(10654);
                    break;
                case "ExpressConveyorBelt61" : list.add(10661);
                    break;
                case "ExpressConveyorBelt63" : list.add(10663);
                    break;
                case "ExpressConveyorBelt71" : list.add(10671);
                    break;
                case "ExpressConveyorBelt74" : list.add(10674);
                    break;
                case "ExpressConveyorBelt83" : list.add(10683);
                    break;
                case "ExpressConveyorBelt84" : list.add(10684);
                    break;
                case "ExpressConveyorBelt91" : list.add(10691);
                    break;
                case "ExpressConveyorBelt92" : list.add(10692);
                    break;
                    
                case "Gear01" : list.add(10701);
                    break;
                case "Gear02" : list.add(10702);
                    break;
                    
                case "Laser00" : list.add(10800);
                    break;
                case "Laser01" : list.add(10801);
                    break;
                case "Laser02" : list.add(10802);
                    break;
                case "Laser03" : list.add(10803);
                    break;
                case "Laser04" : list.add(10804);
                    break;
                case "Laser05" : list.add(10805);
                    break;
                case "Laser06" : list.add(10806);
                    break;
                case "Laser07" : list.add(10807);
                    break;
                case "Laser08" : list.add(10808);
                    break;
                case "Laser09" : list.add(10809);
                    break;
                    
                case "StartField01" : list.add(11101);
                    break;
                case "StartField02" : list.add(11102);
                    break;
                case "StartField03" : list.add(11103);
                    break;
                case "StartField04" : list.add(11104);
                    break;
                case "StartField05" : list.add(11105);
                    break;
                case "StartField06" : list.add(11106);
                    break;
                case "StartField07" : list.add(11107);
                    break;
                case "StartField08" : list.add(11108);
                    break;
                    
                case "RepairSite01" : list.add(10901);
                    break;
                case "RepairSite02" : list.add(10902);
                    break;
                    
                case "StandardFied": list.add(11000);
                    break;
                    
                default : break;
                
                }  
            }
        }
        
        return list;
    }
    
    /**
     * Function, that changes one entry in the board, so it can be saved in the currentField and Backup.
     * 
     * @param col -> Column of the change
     * @param cell -> Row/Cell of the change
     * @param code -> to what it will be converted
     * @param board -> which board, should be updated
     * @return the hole board with the update.
     */
    public Board inverseConvert(int col, int cell, int code, Board board) {
        boolean isTest = false;
        int[] allowed;
        // Switch with the first three digits that represent the class
        switch (code / 100) {

            // BarrierCorner
            case 100:
                // Modulo 10 takes the last digit that represents the attribute
                int corner = code % 10;
                allowed = new int[]{1, 2, 3, 4};
                // Test that the read-out attribute value is in the set
                // of allowed attribute values
                if (Arrays.stream(allowed).anyMatch(x -> x == corner)) {
                    board.fieldmatrix[col][cell] = new BarrierCorner(col, cell, corner,
                            isTest);
                } else {
                    System.out.println("Codierung " + code
                            + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                }
                break;

            // BarrierSide
            case 101:
                int side = code % 10;
                allowed = new int[]{1, 2, 3, 4};
                if (Arrays.stream(allowed).anyMatch(x -> x == side)) {
                    board.fieldmatrix[col][cell] = new BarrierSide(col, cell, side, isTest);
                } else {
                    System.out.println("Codierung " + code
                            + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                }
                break;

            // BlackHole
            case 102:
                board.fieldmatrix[col][cell] = new BlackHole(col, cell, isTest);
                break;

            // Pusher
            case 103:
                int typeB = code % 10;
                allowed = new int[]{1, 2, 3, 4};
                if (Arrays.stream(allowed).anyMatch(x -> x == typeB)) {
                    board.fieldmatrix[col][cell] = new Pusher(col, cell, typeB, isTest);
                } else {
                    System.out.println("Codierung " + code
                            + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                }
                break;

            // Checkpoint
            case 104:
                int numberC = code % 10;
                allowed = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
                if (Arrays.stream(allowed).anyMatch(x -> x == numberC)) {
                    board.fieldmatrix[col][cell] = new Checkpoint(col, cell, numberC,
                            isTest);
                } else {
                    System.out.println("Codierung " + code
                            + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                }
                break;

            // ConveyorBelt
            case 105:
                // Divide by 10 and module 10 takes the next-to-last digit,
                // which represents another attribute
                int startC = (code / 10) % 10;
                int endC = code % 10;
                allowed = new int[]{21, 31, 41, 61, 71, 91, 2, 12, 32, 42, 52, 92,
                        3, 13, 23, 43, 63, 83, 14, 24, 34, 54, 74, 84};
                if (Arrays.stream(allowed).anyMatch(x -> x == (10 * startC) + endC)) {
                    board.fieldmatrix[col][cell] = new ConveyorBelt(col, cell, startC,
                            endC, isTest);
                } else {
                    System.out.println("Codierung " + code
                            + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                }
                break;

            // ExpressConveyorBelt
            case 106:
                int startEc = (code / 10) % 10;
                int endEc = code % 10;
                allowed = new int[]{21, 31, 41, 61, 71, 91, 2, 12, 32, 42, 52, 92,
                        3, 13, 23, 43, 63, 83, 14, 24, 34, 54, 74, 84};
                if (Arrays.stream(allowed).anyMatch(x -> x == (10 * startEc) + endEc)) {
                    board.fieldmatrix[col][cell] = new ExpressConveyorBelt(col, cell,
                            startEc, endEc, isTest);
                } else {
                    System.out.println("Codierung " + code
                            + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                }
                break;

            // Gear
            case 107:
                int direction = code % 10;
                allowed = new int[]{1, 2};
                if (Arrays.stream(allowed).anyMatch(x -> x == direction)) {
                    board.fieldmatrix[col][cell] = new Gear(col, cell, direction, isTest);
                } else {
                    System.out.println("Codierung " + code
                            + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                }
                break;

            // Laser
            case 108:
                int typeL = code % 10;
                allowed = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
                if (Arrays.stream(allowed).anyMatch(x -> x == typeL)) {
                    board.fieldmatrix[col][cell] = new Laser(col, cell, typeL, isTest);
                } else {
                    System.out.println("Codierung " + code
                            + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                }
                break;

            // RepairSite
            case 109:
                int typeR = code % 10;
                allowed = new int[]{1, 2};
                if (Arrays.stream(allowed).anyMatch(x -> x == typeR)) {
                    board.fieldmatrix[col][cell] = new RepairSite(col, cell, typeR, isTest);
                } else {
                    System.out.println("Codierung " + code
                            + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                }
                break;

            // StandardField
            case 110:
                board.fieldmatrix[col][cell] = new StandardField(col, cell, isTest);
                break;

            // StartField
            case 111:
                int numberS = code % 10;
                allowed = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
                if (Arrays.stream(allowed).anyMatch(x -> x == numberS)) {
                    board.fieldmatrix[col][cell] = new StartField(col, cell, numberS,
                            isTest);
                } else {
                    System.out.println("Codierung " + code
                            + " beschreibt kein gueltiges Attribut fuer dieses Feldobjekt");
                }
                break;

            default:
                System.out.println("Codierung " + code
                        + " beschreibt kein gueltige Feldklasse");
                break;
        }
        
        return board;
        
    }
}
