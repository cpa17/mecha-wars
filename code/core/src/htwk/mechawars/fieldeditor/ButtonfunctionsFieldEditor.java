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

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ButtonfunctionsFieldEditor {

    private ArrayList<Integer> actuallField = new ArrayList<>();
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
     * @param t -> say if the functions will be called.
     */
    public ButtonfunctionsFieldEditor(Stage stage, Skin skin, boolean t) {
        this.stage = stage;
        this.skin = skin;
        if (t) {
            importField();
            exportField();
            resetField();
            oneStepBack();
            oneStepDone();
            oneStepForward();
        }
    }

    /**
     * 
     */
    private void importField() {
        
        JFileChooser chooser = new JFileChooser();

        chooser.setDialogTitle("Link-Liste öffnen");

        // Startfolder
        File filepfadLinkListe = new File("code\\desktop\\bin\\main");
        chooser.setCurrentDirectory(filepfadLinkListe);

        int chooseroption = chooser.showOpenDialog(null);
        if (chooseroption == JFileChooser.APPROVE_OPTION) {
            openFile(chooser.getSelectedFile());
        }
        
        // Control, if the field is not to large (because of manual manipulation e.g.)
        if (actuallField.size() != 144) {
            // ErrorDialog
            Dialog dialogCloseOption = new Dialog("Error beim Laden! Bitte Datei ueberpruefen.",
                    skin) {
                @Override
                protected void result(Object object) {
                    remove();
                }
            }.show(stage);

            dialogCloseOption.setSize(450, 110);

            dialogCloseOption.button("Verstanden.", true);
            dialogCloseOption.key(Input.Keys.ENTER, true);   
            
            // clean ArrayList
            actuallField.clear();
            for (int index = 0; index < 144; index += 1) {
                actuallField.add(11000);
            }
        }
        
    }
    
    /**
     * 
     * @param file
     */
    private void openFile(File file) {

        actuallField.clear();

        InputStream istream;
        try {
            istream = new FileInputStream(file);
            Scanner reader = new Scanner(istream);
            while(reader.hasNext()) {
                String[] arg = reader.nextLine().split(" ");
                for (int i = 0; i < arg.length; i += 1) {
                    int abc = Integer.parseInt(arg[i]);
                    actuallField.add(abc);               
                }
            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    private void exportField() {
        
        JFrame saveDialog = new JFrame();           
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Datei speichern unter...");  

        File filepfadLinkListe = new File("code\\desktop\\bin\\main");
        fileChooser.setCurrentDirectory(filepfadLinkListe);

        int userSelection = fileChooser.showSaveDialog(saveDialog);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if(!fileToSave.getAbsolutePath().endsWith(".txt")) {
                fileToSave = new File(fileToSave.getAbsoluteFile() + ".txt");
            }
            save(fileToSave);
        }

    }
    
    /**
     * 
     * @param file
     * @param liste
     */
    public boolean save(File file) {
        
        OutputStream ostream;
        try {
            ostream = new FileOutputStream(file);
            PrintWriter schreiber = new PrintWriter(ostream);

            for (int index = 0; index<actuallField.size(); index += 1) {
                for (int row = 0; row < 12; row += 1) {
                    schreiber.print(actuallField.get(index) + " ");
                }
                schreiber.println();
            }

            schreiber.close();
            
            return true;
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Fehler!");
            e.printStackTrace();
            return false;
        }
        
    }

    /**
     * Reset the hole(/y) Field. That means, everyfield is a "standard-Field" and at the center
     * is the startposition of the robot. 
     */
    private void resetField() {
        
        for (int index = 0; index < 53; index += 1) {
            actuallField.set(index, 11000);
        }
        actuallField.set(53, 10401);
        for (int index = 54; index < 144; index += 1) {
            actuallField.set(index, 11000);
        }

    }

    /**
     * Set the actuallField, which is draw continuously.
     */
    private void oneStepBack() {
        actuallField = backup.getBackup();
    }
    
    /**
     * Saves the actuall change in a hole new (backup)Field.
     * 
     * @return boolean, that show`s the victorious of the function.
     */
    private boolean oneStepDone() {
        try {
            backup.addBackup(actuallField);
            return true;
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Error by saving the actuallField.");
            return false;
        }
    }
    
    /**
     * Gives the Field, that are forward of the actuall Step, when the user accidently make
     * a Step-Back.
     * 
     * @return boolean, which show of a forward is available of not.
     */
    private boolean oneStepForward() {
        if(backup.getForwardBackup() != null) {
            actuallField = backup.getForwardBackup();
            return true;
        }
        else {
            System.out.println("No Step-Forward available!");
            return false;
        }
    }

    public ArrayList<Integer> getActuallField() {
        return actuallField;
    }

    public void setActuallField(ArrayList<Integer> actuallField) {
        this.actuallField = actuallField;
    }
}
