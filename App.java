import Board.Game;

/*
 * PURPOSE: 
 *      -- create a game obj
 *      -- initialize the players and board 
 *      -- run the main game loop 
 */
public class App {
    public static void main(String[] args) {

        // (0) create board
        Game game = new Game();

        // (1) set up game (create board + pieces, create players + scanners )
        game.start();

        // (2) start playing
        game.play();
        
    }//end of main
}//end of class App
