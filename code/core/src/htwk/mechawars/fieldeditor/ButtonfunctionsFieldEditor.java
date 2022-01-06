package htwk.mechawars.fieldeditor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import htwk.mechawars.board.Board;
import htwk.mechawars.fields.StandardField;

/**
 * ButtonFunctions for the FieldEditor.
 * 
 * @author -.-
 *
 */
public class ButtonfunctionsFieldEditor {

    private Board currentField;
    private FieldBackupForBackStep backup = new FieldBackupForBackStep();

    private Stage stage;
    private Skin skin;

    public ButtonfunctionsFieldEditor(Stage stage, Skin skin) {
        this.stage = stage;
        this.skin = skin;
    }

    /**
     * Only for the warnings.
     * 
     * @param t -> says if the functions will be called.
     */
    public ButtonfunctionsFieldEditor(Stage stage, Skin skin, boolean t) {
        this.stage = stage;
        this.skin = skin;
        Board board = new Board("..\\core\\assets\\maps\\test.txt");
        if (t) {
            importField();
            exportField(board);
            resetField(board, stage);
            oneStepBack(board);
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
                fileToSave = new File(fileToSave.getAbsoluteFile() + ".txt");
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
                    schreiber.print(board.fieldmatrix[index][row] + " ");
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
    public Board resetField(Board board, Stage s) {
        
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
    public Board oneStepBack(Board board) {
        
        board = backup.getBackup();
        
        return board;
        
    }

    /**
     * Saves the current change in a hole new (backup)Field.
     * 
     * @return boolean, that show`s the victorious of the function.
     */
    public boolean oneStepDone(Board board) {
        try {
            backup.addBackup(board);
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error by saving the actuallField.");
            return false;
        }
    }
}
