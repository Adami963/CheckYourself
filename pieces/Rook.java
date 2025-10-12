package pieces;

import Board.Board;
import Board.Position;
import java.util.Collections;
import java.util.List;

/**
 * Rook: rules coming later. For now it just exists and prints a token.
 */
public class Rook extends Piece {
    public Rook(Color color, Position position) {
        super(color, position);
    }
    @Override public String token() {
        return (color == Color.WHITE ? "w" : "b") + "R";
    }
    @Override public List<Position> possibleMoves(Board board) {
        // TODO Phase 2/3: add real Rook moves
        return Collections.emptyList();
    }
}
