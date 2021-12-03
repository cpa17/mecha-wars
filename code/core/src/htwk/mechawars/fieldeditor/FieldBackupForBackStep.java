package htwk.mechawars.fieldeditor;

import java.util.ArrayList;

public class FieldBackupForBackStep {

    private ArrayList<ArrayList<Integer>> backup = new ArrayList<>();
    private int backupNumber = 0;

    public FieldBackupForBackStep() {
        // empty like Nirwana
    }

    public ArrayList<Integer> getBackup() {
        try {
            backupNumber -= 1;
            return backup.get(backupNumber + 1);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("[INDEXOUTOFBOUNDSE] - Backup not loadable");
            return null;
        }
    }

    public void addBackup(ArrayList<Integer> backup) {
        if ( backupNumber <= backup.size() ) {
            this.backup.set(backupNumber, backup);
        }
        else {
            this.backup.add(backup);
        }
        backupNumber += 1;
    }

    public ArrayList<Integer> getForwardBackup() {
        try {
            backupNumber += 1;
            return backup.get(backupNumber);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("[INDEXOUTOFBOUNDSE] - Backup not loadable");
            return null;
        }
    }
}
