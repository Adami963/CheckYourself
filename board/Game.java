package chess;

import chess.board.Board;
import chess.board.Position;
import chess.utils.Utils;
import java.util.Scanner;

/**
 * Minimal console loop :
 * - Displays board
 * - Prompts a single move in format "E2 E4"
 * - Applies move without full legality checks
 */
public class Game {
    public static void main(String[] args) {
        Board board = new Board();
        Scanner sc = new Scanner(System.in);

        System.out.println("Console Chess (Phase One)");
        while (true) {
            board.display();
            System.out.print("Enter move (e.g., E2 E4) or 'q' to quit: ");
            String line = sc.nextLine();
            if (line == null) break;
            line = line.trim();
            if (line.equalsIgnoreCase("q")) break;
            if (!Utils.isBasicMoveFormat(line)) {
                System.out.println("Invalid format. Use like: E2 E4");
                continue;
            }
            String[] parts = line.split("\s+");
            Position from = Position.fromSquare(parts[0]);
            Position to = Position.fromSquare(parts[1]);
            boolean moved = board.movePiece(from, to);
            if (!moved) {
                System.out.println("No piece at " + from + ". Try again.");
            }
        }
        System.out.println("Goodbye!");
    }
}
