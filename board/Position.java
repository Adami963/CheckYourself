package Board;

/*
 * LEGEND:
 * row 0 = top of board (rank 8)
 * row 7 = bottom of board (rank 1)
 * 
 * col 0 = file 'A'
 * col 7 = file 'H'
 */
public class Position {
    private int row;
    private int col;

    //CONSTRUCTOR
    public Position(int row, int col){
        this.row = row;
        this.col = col; 
    }//end of CONSTRUCTOR

    //GETTERS
    public int getRow(){
        return row;
    }//end of int getRow

    public int getCol(){
        return col;
    }//end of int getCol
    
}
