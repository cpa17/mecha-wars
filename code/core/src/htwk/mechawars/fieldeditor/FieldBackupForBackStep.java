package htwk.mechawars.fieldeditor;

import java.util.ArrayList;

/**
 * Class, to organize the Step`s back and forward.
 * 
 * @author -.-
 *
 */
public class FieldBackupForBackStep {

    private ArrayList<Board> backup = new ArrayList<>();
    private Board tempBoard = new Board("..\\core\\assets\\maps\\test.txt");
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
     * @return an Board which includes the last field, before the last change.
     */
    public Board getBackup() {
        if (backupNumber > 1) {
            for (int i = 0; i < backup.size(); i += 1) {
                System.out.println(backup.get(i).toString() + "\n\n");
            }
            return backup.get(backupNumber - 2);
        } else {
            System.out.println("Kein Backup da.");
            return backup.get(0);
        }
    }

    /**
     * Function, to added a new BackupField.
     * 
     * @param toBackupBoard -> Include the Backup as an Board, that should be save.
     */
    public void addBackup(Board toBackupBoard) {
        this.tempBoard = toBackupBoard;
        //if (backupNumber < backup.size()) {
        //backup.set(backupNumber, toBackupBoard);
        //} else {
        backup.add(tempBoard);
        //}
        backupNumber += 1;
    }

    /**
     * Function, that return a forward step, when someone is available.
     * 
     * @return an Board which includes the fields.
     */
    public Board getForwardBackup() {
        try {
            backupNumber += 1;
            return backup.get(backupNumber);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("[INDEXOUTOFBOUNDSE] - Backup not loadable");
            return null;
        }
    }
}
