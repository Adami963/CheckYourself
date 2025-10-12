package pieces;

import Board.Board;
import Board.Position;
import java.util.Collections;
import java.util.List;

/**
 * Bishop: rules coming later. For now it just exists and prints a token.
 */
public class Bishop extends Piece {
    public Bishop(Color color, Position position) {
        super(color, position);
    }
    @Override public String token() {
        return (color == Color.WHITE ? "w" : "b") + "B";
    }
    @Override public List<Position> possibleMoves(Board board) {
        // TODO Phase 2/3: add real Bishop moves
        return Collections.emptyList();
    }
}
