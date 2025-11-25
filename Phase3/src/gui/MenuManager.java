package Phase3.src.gui;

import javax.swing.*;
import java.io.*;
/*
 * MenuManager
 * - Builds a menu bar with Game → New, Save, Load, Exit.
 * - Save/Load uses Java serialization of GUIChess.GameState (provided by GameLogic via GUIChess).
 * - Does not know how the game works internally; it just asks GUIChess to perform actions.
 */
public class MenuManager {
    private final GuiChess parent;

    //CONSTRUCTOR
    public MenuManager(GuiChess parent) {
        this.parent = parent;
    }//end of MenuManager

    /*
     * Build and return the menu bar.
     * GUIChess will call this once and install it via setJMenuBar(...).
     */
    public JMenuBar createMenuBar() {
        JMenuBar bar = new JMenuBar();
        JMenu game = new JMenu("Game");

        //New Game Item
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(e -> parent.startNewGame());

        //Save Game Item
        JMenuItem save = new JMenuItem("Save Game");
        save.addActionListener(e -> parent.saveGame());

        //Load Game Item
        JMenuItem load = new JMenuItem("Load Game");
        load.addActionListener(e -> parent.loadGame());

        //Exit Item
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));

        //Put items into the Game menu, with a separator before Exit
        game.add(newGame);
        game.add(save);
        game.add(load);
        game.addSeparator();
        game.add(exit);

        //put the Game menu into the menu bar
        bar.add(game);

        //Return complete menu bar to GUIChess
        return bar;
    }//end of createMenuBar

    /*
     * Save the game to a file using Java serialization.
     * Called by GUIChess after it fetches a GameState from GameLogic.
     */
    public void saveGame(GuiChess.GameState state) {
        //open a file
        JFileChooser fc = new JFileChooser();
        //if  'Save' clicked
        if (fc.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
            //try to auto close file
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fc.getSelectedFile()))) {
                oos.writeObject(state);
                JOptionPane.showMessageDialog(parent, "Game saved.", "Save", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                //if anything goes wrong, show an error dialog
                JOptionPane.showMessageDialog(parent, "Save failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }//end of catch
        }//end of if (fc.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION)
    }//end of saveGame

    /*
     * Load a previously saved game from a file using Java serialization.
     * Returns the GameState object (or null if canceled/failed).
     */
    public GuiChess.GameState loadGame() {
        //let user pick a file to open
        JFileChooser fc = new JFileChooser();
        //clicked 'open'
        if (fc.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
           //try to read 
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fc.getSelectedFile()))) {
                return (GuiChess.GameState) ois.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                // If anything goes wrong, show an error
                JOptionPane.showMessageDialog(parent, "Load failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }//end of catch
        }//end of if (fc.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION)

        // If user canceled or failed, return null so GUIChess won’t try to use it
        return null;
    }//end of loadGame
}
