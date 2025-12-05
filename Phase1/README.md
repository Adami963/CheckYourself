# Phase 1 – Console Version

## CS3354 – Object-Oriented Design & Programming

Phase 1 is the foundation of the entire project.
This version runs completely in the terminal and handles all of the core game logic: the board, the pieces, player turns, and the overall game loop. No GUI is used here—everything is text-based.

---

## What Phase 1 Includes

### Game Engine (Board + Game Loop)

Phase 1 sets up the basic chess environment:

• Creates an 8×8 board with the standard chess starting layout

• Alternates turns (White always moves first)

• Prints the board in ASCII using Board.display()

• Runs the gameplay loop until:

- A player types QUIT, or

- The game naturally ends

### Pieces & Movement

All chess pieces inherit from a shared Piece superclass.
Phase 1 includes:

- Pawn
- Rook
- Knight
- Bishop
- Queen
- King

Each piece stores its position and color, and uses polymorphism to provide its display token.

### Player Input

• Both players share a single Scanner for reading input

• Players manually type their moves

• Typing QUIT immediately ends the game

### Packages Used

• board — Board, Game, and Position

• pieces — All chess piece classes

• players — Player logic and input handling

## Directory Structure

Phase1/

App.java # Main application entry point

board/

    Board.java
    Game.java
    Position.java
    
pieces/

    Piece.java
    Pawn.java
    Rook.java
    Knight.java
    Bishop.java
    Queen.java
    King.java
    Color.java
    
players/

    Player.java
    
README.md <-- We are here

App.java is responsible for starting the game by calling Game.start() and then Game.play().

---

## How to Compile & Run Phase 1

# 1. Make sure you are in the Phase1 directory:

cd Phase1

# 2. Compile all source files

**(You need to compile the board, piece, and player packages along with App.java.)**

javac board/_.java pieces/_.java players/\*.java App.java

# 3. Run the console game

java App

This will start the text-based chess game in the terminal.

## How to Play

• The game tells you whose turn it is (White or Black).

• Enter moves in the format your Player.makeMove() expects
**(ex: e2 e4).**

• The board prints after each move.

**Type QUIT anytime to exit the game.**

• When the loop ends, the program prints:

-- GAME OVER. Thank you for playing!
