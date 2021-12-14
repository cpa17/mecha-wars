package htwk.mechawars.fieldeditor;

import java.util.ArrayList;

/**
 * Class, to organize the Step`s back and forward.
 * 
 * @author -.-
 *
 */
public class FieldBackupForBackStep {

    private ArrayList<ArrayList<Integer>> backup = new ArrayList<>();
    private int backupNumber = 0;

    /**
     * Constructor.
     */
    public FieldBackupForBackStep() {
        // empty like Nirwana
    }

    /**
     * Getter-Function for the last Backup, not the actuall.
     * 
     * @return an ArrayList of Integer which includes the last field, before the last change.
     */
    public ArrayList<Integer> getBackup() {
        try {
            backupNumber -= 1;
            return backup.get(backupNumber + 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("[INDEXOUTOFBOUNDSE] - Backup not loadable");
            return null;
        }
    }

    /**
     * Function, to added a new BackupField.
     * 
     * @param backup -> Include the Backup, that should be save.
     */
    public void addBackup(ArrayList<Integer> backup) {
        if (backupNumber <= backup.size()) {
            this.backup.set(backupNumber, backup);
        } else {
            this.backup.add(backup);
        }
        backupNumber += 1;
    }

    /**
     * Function, that return a forward step, when someone is available.
     * 
     * @return an ArrayList of Integer which includes the field after the change.
     */
    public ArrayList<Integer> getForwardBackup() {
        try {
            backupNumber += 1;
            return backup.get(backupNumber);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("[INDEXOUTOFBOUNDSE] - Backup not loadable");
            return null;
        }
    }
}
