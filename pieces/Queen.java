package pieces;

import Board.Board;
import Board.Position;
import java.util.Collections;
import java.util.List;

/**
 * Queen: rules coming later. For now it just exists and prints a token.
 */
public class Queen extends Piece {
    public Queen(Color color, Position position) {
        super(color, position);
    }
    @Override public String token() {
        return (color == Color.WHITE ? "w" : "b") + "Q";
    }
    @Override public List<Position> possibleMoves(Board board) {
        // TODO Phase 2/3: add real Queen moves
        return Collections.emptyList();
    }
}
