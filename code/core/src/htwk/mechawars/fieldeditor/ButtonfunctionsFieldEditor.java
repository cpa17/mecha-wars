package htwk.mechawars.fieldeditor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class ButtonfunctionsFieldEditor {

    private ArrayList<Integer> actuallField = new ArrayList<>();

    public ButtonfunctionsFieldEditor() {

    }

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
