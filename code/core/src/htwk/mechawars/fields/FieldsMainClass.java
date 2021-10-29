package htwk.mechawars.fields;

import com.badlogic.gdx.Gdx;

import htwk.mechawars.board.Board;

public class FieldsMainClass {
    private final int xcoor;
    private final int ycoor;
    private int type;
    /**
     * Constructor of a Field
     */
    public FieldsMainClass(int type, int xcoor, int ycoor) {
        this.type = type;
        this.xcoor = xcoor;
        this.ycoor = ycoor;
    }
    
   public void showData(){
      System.out.print(xcoor + "  " + ycoor);
      System.out.println();
    }
}
