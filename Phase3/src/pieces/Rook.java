/**
 * This is a Rook piece
 * Only does horizontal and vertical movement 
 */
package Phase3.src.pieces;

import java.util.ArrayList;
import java.util.List;

import Phase3.src.board.Position;

public class Rook extends Piece{
    

 
    public Rook(Color color, Position position){
        super(color, position);
    }

  

    /**
     * All possible Rook moves
     */
    @Override
    public List<Position> possibleMove() {
        List<Position> moves = new ArrayList<>();
        
        int currentRow = position.getRow();
        int currentCol = position.getColumn();

        /**
         * creates all horizontal moves
         */
       for(int col = 0; col < 8; col++){
        if(col != currentCol){
            moves.add(new Position(currentRow, col));
        }
       } 

       /*
        * creates all vertical moves
        */
       for(int row = 0; row < 8; row++){
        if(row != currentRow){
            moves.add(new Position(row, currentCol));
        }
       }
       return moves;
    }

    /**
     * our text representation of the rook
     * @return "wR" for white, "bR" for black
     */
    @Override
    public String getTextRepresentation(){
        return (color == Color.WHITE ? "w" : "b") + "R";
    }

}  
    
    
