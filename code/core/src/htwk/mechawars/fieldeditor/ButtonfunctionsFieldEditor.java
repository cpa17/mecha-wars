package htwk.mechawars.fieldeditor;

import static htwk.mechawars.game.GameScreen.getStage;

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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;

import htwk.mechawars.board.Board;
import htwk.mechawars.fields.StandardField;

/**
 * ButtonFunctions for the FieldEditor.
 * 
 * @author -.-
 *
 */
public class ButtonfunctionsFieldEditor {

    private ArrayList<Integer> currentField = new ArrayList<>();
    private FieldBackupForBackStep backup = new FieldBackupForBackStep();

    private BoardToInt boardToInt = new BoardToInt();

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
            oneStepBack();
            oneStepDone();
            oneStepForward();
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
                openFile(chooser.getSelectedFile());

                controlImportField();

            }
            return chooser.getSelectedFile().toString();
        } else {
            System.out.println("Keine Datei ausgewaehlt!");
            return "0";
        }
    }

    /**
     * Function to check, if the imported Field is broken.
     */
    private void controlImportField() {
//      // Control, if the field is not to large (because of manual manipulation e.g.)
//      if (currentField.size() != 144) {
//          // ErrorDialog
//          Dialog dialogCloseOption = new Dialog("Error beim Laden!"
//                  + "Bitte Datei ueberpruefen.",
//                  skin) {
//              @Override
//              protected void result(Object object) {
//                  remove();
//              }
//          }.show(stage);
//
//          dialogCloseOption.setSize(450, 110);
//
//          dialogCloseOption.button("Verstanden.", true);
//          dialogCloseOption.key(Input.Keys.ENTER, true);   
//
//          // clean ArrayList
//          currentField.clear();
//          for (int index = 0; index < 144; index += 1) {
//              currentField.add(11000);
//          }
//      }
//  } else {
//      }
    }

    /**
     * Function, that manage the Inputstream of the choosen file.
     * 
     * @param file -> show, what file should be open.
     */
    private void openFile(File file) {

        currentField.clear();

        InputStream istream;
        try {
            istream = new FileInputStream(file);
            Scanner reader = new Scanner(istream);
            while (reader.hasNext()) {
                String[] arg = reader.nextLine().split(" ");
                for (int i = 0; i < arg.length; i += 1) {
                    int abc = Integer.parseInt(arg[i]);
                    currentField.add(abc);               
                }
            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

            for (int index = 0; index < currentField.size(); index += 1) {
                for (int row = 0; row < board.fieldmatrix.length; row += 1) {
                    schreiber.print(currentField.get(index) + " ");
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

        currentField.clear();

        //Board-Size
        Dialog dialogSizeOption = new Dialog("           Bitte Spielfeldgroesse eintragen!", skin) {
            @Override
            protected void result(Object object) {
                remove();
            }
                                 
        }.show(s);
        
        int num = 1;
        
        dialogSizeOption.setSize(700, 125);
        dialogSizeOption.button("12x12", num = 12);
        dialogSizeOption.button("16x16", num = 16);
        dialogSizeOption.button("20x20", num = 20);
        dialogSizeOption.button("24x24", num = 24);
        dialogSizeOption.button("28x28", num = 28);
        dialogSizeOption.button("32x32", num = 32);
        
        for (int i = 0; i < board.fieldmatrix.length; i += 1) {
            for (int j = 0; j < board.fieldmatrix[i].length; j += 1) {
                board.fieldmatrix[i][j] = new StandardField(i, j);
            }        
        }

        setCurrentField(board);

        return board;

    }

    /**
     * Set the actuallField, which is draw continuously.
     */
    public void oneStepBack() {
        currentField = backup.getBackup();
    }

    /**
     * Saves the current change in a hole new (backup)Field.
     * 
     * @return boolean, that show`s the victorious of the function.
     */
    public boolean oneStepDone() {
        try {
            backup.addBackup(currentField);
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error by saving the actuallField.");
            return false;
        }
    }

    /**
     * Gives the Field, that are forward of the current Step, when the user accidently make
     * a Step-Back.
     * 
     * @return boolean, which show of a forward is available of not.
     */
    public boolean oneStepForward() {
        if (backup.getForwardBackup() != null) {
            currentField = backup.getForwardBackup();
            return true;
        } else {
            System.out.println("No Step-Forward available!");
            return false;
        }
    }

    /**
     * Create an int[][]-Array out of the given ArrayList.
     * 
     * @param list -> the src-data for the int-Array
     * @return an int[][] with 12x12 
     */
    public int[][] listToIntArray(ArrayList<Integer> list){
        int[][] zw = new int[sqrtOfList()][sqrtOfList()];
        for (int index = 0; index < sqrtOfList(); index += 1) {
            for (int i = 0; i < sqrtOfList(); i += 1) {
                zw[index][i] = list.get(index + i);
            }
        }
        return zw;
    }

    public ArrayList<Integer> getCurrentField() {
        return currentField;
    }

    /**
     * Setter Function, that must be called, after the user clicked a change of the Map.
     * 
     * @param board -> give the current board / map
     */
    public void setCurrentField(Board board) {
        currentField = boardToInt.convert(currentField, board);
    }

    /**
     * Gives the squere-route of the size of arrayList.
     * @return the squere-route as an int
     */
    public int sqrtOfList() {  
        if ((currentField.size()%2) == 0) {
            return (int) Math.sqrt(currentField.size());
        } else {
            return 0;
        }
    }
}
