package Phase3.src;

import javax.swing.SwingUtilities;
import Phase3.src.gui.GuiChess;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GuiChess chessGame = new GuiChess();
            chessGame.setVisible(true);
        });
    }
}
