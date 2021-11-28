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

public class ButtonfunctionsFieldEditor {

    private ArrayList<Integer> actuallField = new ArrayList<>();

    public ButtonfunctionsFieldEditor() {

    }

    private void importField() {
        
        JFileChooser chooser = new JFileChooser();

        chooser.setDialogTitle("Link-Liste �ffnen");

        // Startfolder
        File filepfadLinkListe = new File("code\\desktop\\bin\\main");
        chooser.setCurrentDirectory(filepfadLinkListe);

        int chooseroption = chooser.showOpenDialog(null);
        if (chooseroption == JFileChooser.APPROVE_OPTION) {
            openFile(chooser.getSelectedFile());
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

    private void exportField() {
        
        JFrame Speicherfenster = new JFrame();           
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Datei speichern unter...");  

        File filepfadLinkListe = new File("code\\desktop\\bin\\main");
        fileChooser.setCurrentDirectory(filepfadLinkListe);

        int userSelection = fileChooser.showSaveDialog(Speicherfenster);

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

    private void resetField() {

    }

    private void oneStapBack() {

    }

    public ArrayList<Integer> getActuallField() {
        return actuallField;
    }

    public void setActuallField(ArrayList<Integer> actuallField) {
        this.actuallField = actuallField;
    }
}
