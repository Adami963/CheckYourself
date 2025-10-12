package chess.board;

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
    
/**
 * Immutable board coordinate using 0-based row and column.
 * Row 0 is rank 8, row 7 is rank 1.
 * Column 0 is file A, column 7 is file H.
 */
public final class Position {
    private final int row;
    private final int column;

    /**
     * Constructs a Position.
     * @param row 0..7 (0 is top / rank 8)
     * @param column 0..7 (0 is file A)
     */
    public Position(int row, int column) {
        if (row < 0 || row > 7 || column < 0 || column > 7) {
            throw new IllegalArgumentException("row/column must be in 0..7");
        }
        this.row = row;
        this.column = column;
    }

    public int getRow() { return row; }
    public int getColumn() { return column; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position)) return false;
        Position other = (Position) o;
        return this.row == other.row && this.column == other.column;
    }

    @Override
    public int hashCode() { return 31 * row + column; }

    @Override
    public String toString() {
        char file = (char)('A' + column);
        int rank = 8 - row;
        return "" + file + rank;
    }

    /**
     * Parses standard algebraic squares like "E2". Case-insensitive.
     */
    public static Position fromSquare(String square) {
        if (square == null || square.length() != 2) {
            throw new IllegalArgumentException("Square must be like E2");
        }
        char file = Character.toUpperCase(square.charAt(0));
        char rank = square.charAt(1);
        if (file < 'A' || file > 'H' || rank < '1' || rank > '8') {
            throw new IllegalArgumentException("Square must be A1..H8");
        }
        int column = file - 'A';
        int row = 8 - (rank - '0');
        return new Position(row, column);
    }
}
