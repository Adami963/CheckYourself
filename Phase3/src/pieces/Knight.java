/**
 * This is the knight piece
 * Does the L-Shape movement
 */
package Phase3.src.pieces;

import java.util.ArrayList;
import java.util.List;

import Phase3.src.board.Position;

public class Knight extends Piece {
    
    public Knight(Color color,Position position){
        super(color, position);
    }

    /**
     * All possible moves for the Knight
     */
    @Override
    public List<Position> possibleMove() {
       List<Position> moves = new ArrayList<>();

       int currentRow = position.getRow();
       int currentCol = position.getColumn();

       /**
        * All L-Shape moves
        */
       int [][] knightMoves = {
        {-2,-1}, {-2,1}, {-1,-2},{-1,2},{1,-2},{1,2},{2,-1},{2,1}
       };
       
       /**
        * Checking each move 
        */
       for(int[] move : knightMoves){
        int newRow = currentRow + move[0];
        int newCol = currentCol + move[1];

        /**
         * checking if new move is within boundaries
         */
        if(newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8){
            moves.add(new Position(newRow, newCol));
        }
       }
    
       return moves;

    }


    /**
     * our text representation of the knight
     * @return "wKN" for white, "bKN" for black
     */
    @Override
    public String getTextRepresentation(){
        return(color == Color.WHITE ? "w" : "b") + "KN";
    }

    
}
  
