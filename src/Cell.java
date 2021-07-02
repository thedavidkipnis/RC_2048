public class Cell {

    private int val ;
    private boolean isEmpty ;
    private int xCoor ;
    private int yCoor ;

    public Cell() {
        this.val = 0 ;
        this.isEmpty = true ;
    }

    public void clearCell() {
        this.val = 0 ;
        this.isEmpty = true ;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public int getxCoor() {
        return xCoor;
    }

    public void setxCoor(int xCoor) {
        this.xCoor = xCoor;
    }

    public int getyCoor() {
        return yCoor;
    }

    public void setyCoor(int yCoor) {
        this.yCoor = yCoor;
    }
}
