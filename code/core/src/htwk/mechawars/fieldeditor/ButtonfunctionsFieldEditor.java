package htwk.mechawars.fieldeditor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import htwk.mechawars.fieldsfiedit.StandardField;

/**
 * ButtonFunctions for the FieldEditor.
 * 
 * @author -.-
 *
 */
public class ButtonfunctionsFieldEditor {

    private Board currentField;
    private FieldBackupForBackStep backup = new FieldBackupForBackStep();

    public ButtonfunctionsFieldEditor() {
    }

    /**
     * Only for the warnings.
     * 
     * @param t -> says if the functions will be called.
     */
    public ButtonfunctionsFieldEditor(boolean t) {
        Board board;
        if (t) {
            board = new Board("..\\core\\assets\\maps\\test.txt");
        } else {
            board = currentField;
        }
        if (t) {
            importField();
            exportField(board);
            resetField(board);
            oneStepBack();
            oneStepDone(board);
        }
    }

    /**
     * Function, that manage the import of a Map.
     */
    public String importField() {

        JFileChooser chooser = new JFileChooser();

        chooser.setDialogTitle("Map oeffnen");

        // Startfolder
        File filepfadLinkListe = new File("..\\core\\assets\\maps");
        chooser.setCurrentDirectory(filepfadLinkListe);

        int chooseroption = chooser.showOpenDialog(null);
        if (chooseroption == JFileChooser.APPROVE_OPTION) {
            if (chooser.getSelectedFile().toString().matches("(.*)" + ".txt")) {
                return chooser.getSelectedFile().toString();
            } else {
                return "2";
            }
        } else {
            System.out.println("Keine Datei ausgewaehlt!");
            return "0";
        }
    }

    /**
     * Manage the exportfunction, to save a field in a .txt file permanently.
     */
    public void exportField(Board board) {

        JFrame saveDialog = new JFrame();           
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Datei speichern unter...");  

        File filepfadLinkListe = new File("..\\core\\assets\\maps");
        fileChooser.setCurrentDirectory(filepfadLinkListe);

        int userSelection = fileChooser.showSaveDialog(saveDialog);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getAbsolutePath().endsWith(".txt")) {
                fileToSave = new File(fileToSave.getAbsoluteFile().toString() + ".txt");
            } else {
                fileToSave = new File(fileToSave.getAbsoluteFile().toString());
            }
            // make every String in Lower-Case, so there are no misstakes, when open it in the Game
            fileToSave.getName().toLowerCase();
            save(fileToSave, board);
        }

    }

    /**
     * Function to save the current Field in the File, that the user wanted to use/or create.
     * 
     * @param file - show the file to save the field.
     */
    private boolean save(File file, Board board) {

        OutputStream ostream;
        try {
            ostream = new FileOutputStream(file);
            PrintWriter schreiber = new PrintWriter(ostream);

            for (int index = 0; index < board.fieldmatrix.length; index += 1) {
                for (int row = 0; row < board.fieldmatrix[index].length; row += 1) {
                    schreiber.print(convert(board.fieldmatrix[index][row].getTile().toString())
                            + " ");
                }
                schreiber.println();
            }

            schreiber.close();

            return true;
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Fehler!");
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Reset the hole(/y) Field. That means, everyfield is a "standard-Field" and at the center
     * is the startposition of the robot. 
     */
    public Board resetField(Board board) {
        
        for (int i = 0; i < board.fieldmatrix.length; i += 1) {
            for (int j = 0; j < board.fieldmatrix[i].length; j += 1) {
                board.fieldmatrix[i][j] = new StandardField(i, j);
            }        
        }

        return board;
    }

    /**
     * Set the actuallField, which is draw continuously.
     */
    public Board oneStepBack() {
        
        return backup.getBackup();
        
    }

    /**
     * Saves the current change in a hole new (backup)Field.
     */
    public void oneStepDone(Board board) {        
        try {
            backup.addBackup(board);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error by saving the actuallField.");
        }
    }
    
    /**
     * Funktion that convert Assets to int-code.
     * @param abc -> String with the asset
     * @return the code of field as an int
     */
    private int convert(String abc) {
        System.out.println(abc);
        abc = abc.substring(Math.abs(abc.lastIndexOf("/") + 1), Math.abs(abc.lastIndexOf(".")));
        System.out.println(abc);
        switch (abc) {
            case "BarrierCorner01" : return 10001;
            case "BarrierCorner02" : return 10002;
            case "BarrierCorner03" : return 10003;
            case "BarrierCorner04" : return 10004;
            case "BarrierSide1" : return 10101;
            case "BarrierSide2" : return 10102;
            
            case "BarrierSide3" : return 10103;
            case "BarrierSide4" : return 10104;
            
            case "BlackHole" :  return 10200;
        
            case "Pusher01" : return 10301;
            case "Pusher02" : return 10302;
            case "Pusher03" : return 10303;
            case "Pusher04" : return 10304;
            
            case "Check1" : return 10401;
            case "Check2" : return 10402;
            case "Check3" : return 10403;
            case "Check4" : return 10404;
            case "Check5" : return 10405;
            case "Check6" : return 10406;
            case "Check7" : return 10407;
            case "Check8" : return 10408;
            
            
            case "ConveyorBelt02" : return 10502;
            case "ConveyorBelt03" : return 10503;
            case "ConveyorBelt12" : return 10512;
            case "ConveyorBelt13" : return 10513;
            case "ConveyorBelt14" : return 10514;
            case "ConveyorBelt21" : return 10521;
            case "ConveyorBelt23" : return 10523;
            case "ConveyorBelt24" : return 10524;
            case "ConveyorBelt31" : return 10531;
            case "ConveyorBelt32" : return 10532;
            case "ConveyorBelt34" : return 10534;
            case "ConveyorBelt41" : return 10541;
            case "ConveyorBelt42" : return 10542;
            case "ConveyorBelt43" : return 10543;
            case "ConveyorBelt52" : return 10552;
            case "ConveyorBelt54" : return 10554;
            case "ConveyorBelt61" : return 10561;
            case "ConveyorBelt63" : return 10563;
            case "ConveyorBelt71" : return 10571;
            case "ConveyorBelt74" : return 10574;
            case "ConveyorBelt83" : return 10583;
            case "ConveyorBelt84" : return 10584;
            case "ConveyorBelt91" : return 10591;
            case "ConveyorBelt92" : return 10592;
            
            case "ExpressConveyorBelt02" : return 10602;
            case "ExpressConveyorBelt03" : return 10603;
            case "ExpressConveyorBelt12" : return 10612;
            case "ExpressConveyorBelt13" : return 10613;
            case "ExpressConveyorBelt14" : return 10614;
            case "ExpressConveyorBelt21" : return 10621;
            case "ExpressConveyorBelt23" : return 10623;
            case "ExpressConveyorBelt24" : return 10624;
            case "ExpressConveyorBelt31" : return 10631;
            case "ExpressConveyorBelt32" : return 10632;
            case "ExpressConveyorBelt34" : return 10634;
            case "ExpressConveyorBelt41" : return 10641;
            case "ExpressConveyorBelt42" : return 10642;
            case "ExpressConveyorBelt43" : return 10643;
            case "ExpressConveyorBelt52" : return 10652;
            case "ExpressConveyorBelt54" : return 10654;
            case "ExpressConveyorBelt61" : return 10661;
            case "ExpressConveyorBelt63" : return 10663;
            case "ExpressConveyorBelt71" : return 10671;
            case "ExpressConveyorBelt74" : return 10674;
            case "ExpressConveyorBelt83" : return 10683;
            case "ExpressConveyorBelt84" : return 10684;
            case "ExpressConveyorBelt91" : return 10691;
            case "ExpressConveyorBelt92" : return 10692;
            
            case "Gear01" : return 10701;
            case "Gear02" : return 10702;
            
            case "Laser00" : return 10800;
            case "Laser01" : return 10801;
            case "Laser02" : return 10802;
            case "Laser03" : return 10803;
            case "Laser04" : return 10804;
            case "Laser05" : return 10805;
            case "Laser06" : return 10806;
            case "Laser07" : return 10807;
            case "Laser08" : return 10808;
            case "Laser09" : return 10809;
            
            case "StartField01" : return 11101;
            case "StartField02" : return 11102;
            case "StartField03" : return 11103;
            case "StartField04" : return 11104;
            case "StartField05" : return 11105;
            case "StartField06" : return 11106;
            case "StartField07" : return 11107;
            case "StartField08" : return 11108;
            
            case "RepairSite01" : return 10901;
            case "RepairSite02" : return 10902;
            
            case "StandardField": return 11000;
            
            default : return 0;
                    
        }  
    }
}
