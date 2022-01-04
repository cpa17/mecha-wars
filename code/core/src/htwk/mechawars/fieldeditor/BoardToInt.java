package htwk.mechawars.fieldeditor;

import java.util.ArrayList;

import htwk.mechawars.board.Board;

public class BoardToInt {
    
    public BoardToInt() {
        
    }
    
    /**
     * 
     * @param list
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
                case "BarrierCorner02" : 
                    break;
                case "BarrierCorner03" : 
                    break;
                case "BarrierCorner04" : 
                    break;
                
                case "BarrierSide1" :
                    break;
                case "BarrierSide2" :
                    break;
                case "BarrierSide3" :
                    break;
                case "BarrierSide4" :
                    break;
                    
                case "BlackHole" :
                    break;
                
                case "Pusher01" :
                    break;
                case "Pusher02" :
                    break;
                case "Pusher03" :
                    break;
                case "Pusher04" :
                    break;
                    
                case "Check1" :
                    break;
                case "Check2" :
                    break;
                case "Check3" :
                    break;
                case "Check4" :
                    break;
                case "Check5" :
                    break;
                case "Check6" :
                    break;
                case "Check7" :
                    break;
                case "Check8" :
                    break;
                    
                case "ConveyorBelt02" :
                    break;
                case "ConveyorBelt03" :
                    break;
                case "ConveyorBelt12" :
                    break;
                case "ConveyorBelt13" :
                    break;
                case "ConveyorBelt14" :
                    break;
                case "ConveyorBelt21" :
                    break;
                case "ConveyorBelt23" :
                    break;
                case "ConveyorBelt24" :
                    break;
                case "ConveyorBelt31" :
                    break;
                case "ConveyorBelt32" :
                    break;
                case "ConveyorBelt34" :
                    break;
                case "ConveyorBelt41" :
                    break;
                case "ConveyorBelt42" :
                    break;
                case "ConveyorBelt43" :
                    break;
                case "ConveyorBelt52" :
                    break;
                case "ConveyorBelt54" :
                    break;
                case "ConveyorBelt61" :
                    break;
                case "ConveyorBelt63" :
                    break;
                case "ConveyorBelt71" :
                    break;
                case "ConveyorBelt74" :
                    break;
                case "ConveyorBelt83" :
                    break;
                case "ConveyorBelt84" :
                    break;
                case "ConveyorBelt91" :
                    break;
                case "ConveyorBelt92" :
                    break;
                    
                case "ExpressConveyorBelt02" :
                    break;
                case "ExpressConveyorBelt03" :
                    break;
                case "ExpressConveyorBelt12" :
                    break;
                case "ExpressConveyorBelt13" :
                    break;
                case "ExpressConveyorBelt14" :
                    break;
                case "ExpressConveyorBelt21" :
                    break;
                case "ExpressConveyorBelt23" :
                    break;
                case "ExpressConveyorBelt24" :
                    break;
                case "ExpressConveyorBelt31" :
                    break;
                case "ExpressConveyorBelt32" :
                    break;
                case "ExpressConveyorBelt34" :
                    break;
                case "ExpressConveyorBelt41" :
                    break;
                case "ExpressConveyorBelt42" :
                    break;
                case "ExpressConveyorBelt43" :
                    break;
                case "ExpressConveyorBelt52" :
                    break;
                case "ExpressConveyorBelt54" :
                    break;
                case "ExpressConveyorBelt61" :
                    break;
                case "ExpressConveyorBelt63" :
                    break;
                case "ExpressConveyorBelt71" :
                    break;
                case "ExpressConveyorBelt74" :
                    break;
                case "ExpressConveyorBelt83" :
                    break;
                case "ExpressConveyorBelt84" :
                    break;
                case "ExpressConveyorBelt91" :
                    break;
                case "ExpressConveyorBelt92" :
                    break;
                    
                case "Gear01" :
                    break;
                case "Gear02" :
                    break;
                    
                case "Laser00" :
                    break;
                case "Laser01" :
                    break;
                case "Laser02" :
                    break;
                case "Laser03" :
                    break;
                case "Laser04" :
                    break;
                case "Laser05" :
                    break;
                case "Laser06" :
                    break;
                case "Laser07" :
                    break;
                case "Laser08" :
                    break;
                case "Laser09" :
                    break;
                    
                case "StartField01" :
                    break;
                case "StartField02" :
                    break;
                case "StartField03" :
                    break;
                case "StartField04" :
                    break;
                case "StartField05" :
                    break;
                case "StartField06" :
                    break;
                case "StartField07" :
                    break;
                case "StartField08" :
                    break;
                    
                case "RepairSite01" :
                    break;
                case "RepairSite02" :
                    break;
                    
                case "StandardFied":
                    break;
                    
                default : break;
                
                }  
            }
        }
        
        return list;
    }

}
