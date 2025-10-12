package chess.utils;

import chess.board.Position;

/**
 * Utility helpers for parsing input and validating basic formats.
 */
public final class Utils {
    private Utils() {}

    /**
     * Validates a basic move string like "E2 E4". Case-insensitive.
     */
    public static boolean isBasicMoveFormat(String input) {
        if (input == null) return false;
        String[] parts = input.trim().split("\s+");
        if (parts.length != 2) return false;
        try {
            Position.fromSquare(parts[0]);
            Position.fromSquare(parts[1]);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
