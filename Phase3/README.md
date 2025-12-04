# Phase 3 – Improved GUI Version

## CS3354 – Object-Oriented Design & Programming

Phase 3 is the final version of the project.
This stage builds on the GUI from Phase 2 but cleans everything up, improves the layout, fixes issues like the board coloring, and reorganizes the code so it follows a clearer structure. This is also where move tracking and notation get added, and where the project starts to resemble an MVC-style design.

---

## Goals of Phase 3

- Making the GUI cleaner and easier to interact with
- Fixing the board coloring/rendering so it displays correctly
- Breaking the project into organized packages
- Adding move tracking + notation support
- Making the overall program feel more complete and polished
- Moving closer to a proper Model–View–Controller layout

---

## Directory Structure (Phase 3)

Phase3/

src/

board/ # Board logic and data structures

gui/ # All GUI components (Swing)

pieces/ # All chess piece classes

players/ # Player-related classes

App.java # Application helper

Main.java # Entry point for Phase 3

Notation.java # Handles move notation / algebraic formatting

Tracker.java # Records move history

# How to Compile & Run Phase 3

## Run these commands from inside the Phase3 folder:

cd Phase3

## Compile all Phase 3 source files

javac $(find src -name "\*.java")

## Run the program

java -cp .. Phase3.src.Main

# Improvements Introduced in Phase 3

## Fixed Board Coloring Issue

Buttons now paint correctly across platforms using:

btn.setOpaque(true);
btn.setContentAreaFilled(true);
btn.setBorderPainted(false);

This ensures consistent alternating-board colors (chessboard-style).

## Enhanced GUI Experience

• Cleaner layout and spacing

• More polished board appearance

• Better handling of selected squares and moves

• Keeps all GUI-related code grouped together in the gui package

## Integration of Notation & Move Tracking

• Tracker.java records each move

• Notation.java formats moves for display

• These can be displayed in the GUI (history panel, etc.)

## Cleaner Architecture (MVC-style)

• Model → board, pieces, players

• View / Controller → everything in gui

• Utilities → Notation, Tracker, App

This makes the code easier to maintain and extend in the future.

# Main Components in Phase 3

## Main.java

• Entry point

• Launches the Phase 3 GUI

## GuiChess (and other GUI classes)

• Builds the interface

• Draw the board and pieces

• Handle button clicks and interactions

## Board + Piece Classes

• Keep track of game state

• Store piece info and behavior

• Reuse logic originally created in Phase 1 and Phase 2

## Tracker / Notation

• Record all moves

• Convert moves into readable strings

# How to Use Phase 3

• Run the program to open the GUI window

• Click a piece, then click a destination square to move

• The board updates automatically after each move

• Move history appears as the game progresses

• The structure is set up for future additions (AI, rule checking, etc.)
