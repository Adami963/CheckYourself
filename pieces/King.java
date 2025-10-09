package chess.pieces;

import chess.board.Board;
import chess.board.Position;
import java.util.Collections;
import java.util.List;

/**
 * King: rules coming later. For now it just exists and prints a token.
 */
public class King extends Piece {
    public King(Color color, Position position) {
        super(color, position);
    }
    @Override public String token() {
        return (color == Color.WHITE ? "w" : "b") + "K";
    }
    @Override public List<Position> possibleMoves(Board board) {
        // TODO Phase 2/3: add real King moves
        return Collections.emptyList();
    }
}
