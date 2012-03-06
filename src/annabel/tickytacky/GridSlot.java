package annabel.tickytacky;

public class GridSlot {

    private int iIndex, jIndex;
    private boolean isMarked, isPlayer;
    private String mark;
    // private picture
    
    public GridSlot(int i, int j, boolean marked, boolean player) {
        iIndex = i;
        jIndex = j;
        isMarked = marked;
        isPlayer = player;
        mark = new String("");
    }
    
    /*
     * Get the row index of this grid slot.
     */
    public int getRow() {
        return iIndex;
    }
    
    /*
     * Get the column index of this grid slot.
     */
    public int getColumn() {
        return jIndex;
    }
    
    /*
     * Returns true if this slot has been played.
     */
    public boolean isMarked() {
        return isMarked;
    }
    
    /*
     * Returns true if this slot was played by the
     * human player.
     */
    public boolean isPlayer() {
        return isPlayer;
    }
    
    /*
     * Returns true if this slot was played by the
     * computer player.
     */
    public boolean isComputer() {
        return !isPlayer;
    }
    
    /*
     * Returns the mark that corresponds to the
     * owner of this slot.
     */
    public String getMark() {
        return mark;
    }
    
    
    /*
     * Sets this slot's mark to be m.
     */
    public void setMark(String m) {
        mark = new String(m);
    }
    
    public void clear() {
        isMarked = false;
        isPlayer = false;
        mark = new String("");
    }
    
}
