package htwk.mechawars.board;

/**
 * Enum class for the directions of movement.
 */
public enum Dir {

    NORTH(1),
    EAST(2),
    SOUTH(3),
    WEST(4);

    Dir(int value) {
        this.value = value;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public Dir turn(byte rightTurnCount) {
        this.value = ((this.value + rightTurnCount) % 4) + 1;
        return intToDirection(this.value);
    }

    public Dir intToDirection(int value){
        switch (value){
            case 1:
                return Dir.NORTH;
            case 2:
                return Dir.EAST;
            case 3:
                return Dir.SOUTH;
            case 4:
                return Dir.WEST;
            default:
                return this;
        }
    }
}
