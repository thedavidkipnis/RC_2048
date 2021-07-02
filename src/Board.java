import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Board {

    private static Cell [][] cells ;
    private static final Random rand = new Random() ;
    private int filledCellAmount ;

    //board initialization method
    public void init() {
        cells = new Cell[4][4] ;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell() ;
                cells[i][j].setxCoor(i);
                cells[i][j].setyCoor(j);
            }
        }
        startGen();
        calcFilledCellAmount() ;
    }

    //method for generating the first two cells
    public void startGen() {
        int[] idx1 = new int[2] ;
        int[] idx2 = new int[2] ;

        idx1[0] = rand.nextInt(4) ;
        idx1[1] = rand.nextInt(4) ;

        idx2[0] = rand.nextInt(4) ;
        idx2[1] = rand.nextInt(4) ;
        while(idx1[0] == idx2[0] && idx1[1] == idx2[1]) {
            idx2[0] = rand.nextInt(4) ;
            idx2[1] = rand.nextInt(4) ;
        }
        cells[idx1[0]][idx1[1]].setVal(2);
        cells[idx1[0]][idx1[1]].setEmpty(false);
        cells[idx2[0]][idx2[1]].setVal(2);
        cells[idx2[0]][idx2[1]].setEmpty(false);

//        cells[0][2].setVal(2);
//        cells[0][2].setEmpty(false);
//        cells[1][2].setVal(2);
//        cells[1][2].setEmpty(false);
//        for (int i = 0; i < 4; i++) {
//            cells[i][2].setVal(2);
//            cells[i][2].setEmpty(false);
//        }
    }

    //method for processing user input and calling the right method
    public static void shiftBoard(String input) {
        switch(input) {
            case "a":
                shiftLeft() ;
                break;
            case "w":
                shiftUp() ;
                break;
            case "d":
                shiftCells(true, true, false) ;
                break;
            case "s":
                shiftCells(true, false, false) ;
                break;
        }
    }

    //methods for adjusting the board when the player picks the respective direction
    private static void shiftDown() {
        for (int i = cells.length - 1; i > -1; i--) {
            for (int j = cells.length - 1; j > -1; j--) {
                if(j != 0) {
                    int k = j-1 ;
                    while (k > 0 && cells[k][i].isEmpty()) {
                        k -- ;
                    }
                    if(cells[k][i].getVal() == cells[j][i].getVal()) {
                        cells[j][i].setVal(cells[j][i].getVal()*2) ;
                        cells[k][i].clearCell();
                    }
                    else if(cells[j][i].isEmpty()) {
                        cells[j][i].setVal(cells[k][i].getVal());
                        cells[j][i].setEmpty(false);
                        cells[k][i].clearCell();
                    }
                }
            }
        }
        generateRandomCell() ;
    }

    private static void shiftUp() {
        for (int i = cells.length - 1; i > -1; i--) {
            for (int j = 0; j < cells.length; j++) {
                if(j!=cells.length - 1) {
                    int k = j+1 ;
                    while (k < cells.length - 1 && cells[k][i].isEmpty()) {
                        k ++ ;
                    }
                    if(cells[k][i].getVal() == cells[j][i].getVal()) {
                        cells[j][i].setVal(cells[j][i].getVal()*2) ;
                        cells[k][i].clearCell();
                    }
                    else if(cells[j][i].isEmpty()) {
                        cells[j][i].setVal(cells[k][i].getVal());
                        cells[j][i].setEmpty(false);
                        cells[k][i].clearCell();
                    }
                }
            }
        }
        generateRandomCell() ;
    }

    private static void shiftRight() {
        for (int i = cells.length-1 ; i > -1; i--) {
            for (int j = cells.length-1; j > -1; j--) {
                if(i != 0) {
                    int k = i-1 ;
                    while (k > 0 && cells[j][k].isEmpty()) {
                        k -- ;
                    }
                    if(cells[j][k].getVal() == cells[j][i].getVal()) {
                        cells[j][i].setVal(cells[j][i].getVal()*2) ;
                        cells[j][k].clearCell();
                    }
                    else if(cells[j][i].isEmpty()) {
                        cells[j][i].setVal(cells[j][k].getVal());
                        cells[j][i].setEmpty(false);
                        cells[j][k].clearCell();
                    }
                }
            }
        }
        generateRandomCell() ;
    }

    private static void shiftLeft() {
        for (int i = cells.length-1 ; i > -1; i--) {
            for (int j = 0; j < cells[i].length; j++) {
                if(i!=cells.length - 1) {
                    int k = i+1 ;
                    while (k < cells.length - 1 && cells[j][k].isEmpty()) {
                        k ++ ;
                    }
                    if(cells[j][k].getVal() == cells[j][i].getVal()) {
                        cells[j][i].setVal(cells[j][i].getVal()*2) ;
                        cells[j][k].clearCell();
                    }
                    else if(cells[j][i].isEmpty()) {
                        cells[j][i].setVal(cells[j][k].getVal());
                        cells[j][i].setEmpty(false);
                        cells[j][k].clearCell();
                    }
                }
            }
        }
        generateRandomCell() ;
    }

    private static void shiftCells(boolean shiftDown, boolean shiftRight, boolean shiftLeft) {
        for (int i = cells.length-1; i > -1; i--) {
            //if we need to shift down
            if (shiftDown) {
                for (int j = cells.length - 1; j > -1; j--) {
                    //if we need to shift right
                    int const_idx1 = i ;
                    int const_idx2 = j ;
                    if (shiftRight) {
                        j = i ;
                    }
                    if(j != 0) {
                        int k = j-1 ;
                        if (shiftRight) {
                            i = k ;
                            k = const_idx2 ;
                        }
                        while (k > 0 && cells[k][i].isEmpty()) {
                            k--;
                        }
                        if(cells[k][i].getVal() == cells[const_idx2][const_idx1].getVal()) {
                            cells[const_idx2][const_idx1].setVal(cells[const_idx2][const_idx1].getVal()*2) ;
                            cells[k][i].clearCell();
                        }
                        else if(cells[const_idx2][const_idx1].isEmpty()) {
                            cells[const_idx2][const_idx1].setVal(cells[k][i].getVal());
                            cells[const_idx2][const_idx1].setEmpty(false);
                            cells[k][i].clearCell();
                        }
                    }
                }
            }
            //if we need to shift up
            else {
                for (int j = 0; j < cells[i].length; j++) {
                    //if we need to shift left
                    if (shiftLeft) {

                    }

                }
            }
        }
        generateRandomCell();
    }

    private void shiftEmptyCells(int idx1, int idx2) {

    }

    //generating a random cell on the board
    private static void generateRandomCell() {
        List<Cell> availableCells = new LinkedList<>() ;
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (cell.isEmpty()) {
                    availableCells.add(cell) ;
                }
            }
        }
        int nextIdx = rand.nextInt(availableCells.size()) ;
        cells[availableCells.get(nextIdx).getxCoor()][availableCells.get(nextIdx).getyCoor()].setVal(2);
        cells[availableCells.get(nextIdx).getxCoor()][availableCells.get(nextIdx).getyCoor()].setEmpty(false);
    }

    //checking how many cells are currently filled
    private void calcFilledCellAmount() {
        this.filledCellAmount = 0 ;
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (!cell.isEmpty()) {
                    this.filledCellAmount++;
                }
            }
        }
    }

    //method for displaying the board to console
    public void printBoard() {
        //System.out.println("# of filled cells: " + this.filledCellAmount);
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (cell.isEmpty()) {
                    System.out.print("[*]");
                } else {
                    System.out.print("[" + cell.getVal() + "]");
                }
            }
            System.out.println();
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        Board.cells = cells;
    }

    public int getFilledCellAmount() {
        return filledCellAmount;
    }

    public void setFilledCellAmount(int filledCellAmount) {
        this.filledCellAmount = filledCellAmount;
    }
}
