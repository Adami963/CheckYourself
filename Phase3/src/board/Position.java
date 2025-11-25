package Phase3.src.board;

/*
 * Represents a postion on the chessboard 
 */
public class Position {
    private int row;
    private int column;

    /*
     * Constructor for position class
     * Row for the row coordinate
     * Column for the column coordinate
     */
    public Position(int row, int column){
        this.row = row;
        this.column = column;
    }

    /*
     * Constructor for position class using chess notation
     * chessNotation is the position in chess notation
     */
    public Position(String chessNotation){
        if(chessNotation.length() != 2){
        throw new IllegalArgumentException("Invalid chess notation: " + chessNotation);
        }
        char columnChar = chessNotation.charAt(0);
        char rowChar = chessNotation.charAt(1);

        this.column = columnChar - 'A';
        this.row = 8 - (rowChar - '0');
    }

    /*
     * Getters & Setters
     */
    public int getRow() {
        return row;
    }
    public void setRow(int row){
        this.row = row;
    }
    public int getColumn(){
        return column;
    }
    public void setColumn(int column){
        this.column = column;
    }

    /*
     * position to chess notation
     * @return the position in chess notation
     */
    public String toChessNotation(){
        char columnChar = (char) ('A' + column);
        int rowNumber = 8 - row;
        return "" + columnChar + rowNumber;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) 
        return true;
        if(obj == null || getClass() != obj.getClass()) 
        return false;
        Position PositionUtils = (Position) obj;
        return row == PositionUtils.row && column == PositionUtils.column;
    }

    @Override
    public String toString(){
        return toChessNotation();
    }
    
}
