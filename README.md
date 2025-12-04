# CS3354 – Object-Oriented Design & Programming

## Multi-Phase Java Project (Phase 1 → Phase 2 → Phase 3)

**Author:** Adami, Jennifer, and Abraham
**Last Updated:** 12/4/2025

---

## Project Overview

This project goes through three phases, each one building on the previous to show how a Java application can grow from a simple console program into a fully structured GUI application. Each phase focuses on core OOD concepts, refactoring, and stepping toward a cleaner architecture.

Here’s a quick overview of each phase:

1. **Phase 1 – Console Version**  
   A text-based version that handles all the main game logic: the board, the pieces, the players, and the main game loop. Everything happens in the terminal.

2. **Phase 2 – Basic GUI Version**  
    Switches from console input/output to a Swing-based graphical user interface.

   This phase brings in event-driven programming and clickable interactions while still relying on the logic from Phase 1.

3. **Phase 3 – Improved GUI Version**  
   This is the polished and reorganized version.
   It improves the GUI layout, fixes board coloring issues, integrates move tracking and notation, and reorganizes the code to better follow MVC-style design.

   Overall, the project highlights object-oriented programming, iterative design, GUI programming, and how software evolves and improves over multiple phases.

## Directory Structure

Phase1/
src/
README.md

Phase2/
src/
README.md

Phase3/
src/
board/
gui/
pieces/
players/
App.java
Main.java
Notation.java
Tracker.java
README.md

README.md <-- This file

Each phase contains its own README that explains how to run it and what changed from the previous version.

## Running Each Phase

Below are the commands for compiling and running each version of the program.

### **Phase 1 – Console**

cd Phase1

javac board/_.java pieces/_.java players/\*.java App java

java App

### **Phase 2 – GUI (Swing)**

cd Phase2_GUI

javac -d . \*.java

java Phase2_GUI.GuiChess

### **Phase 3 – Improved GUI Version **

cd Phase3

# optional -- clears previous class files

find src -name "\*.class" -delete

javac $(find src -name "\*.java")

java -cp .. Phase3.src.Main

# Architecture & Design Principles

This project demonstrates several key OOP concepts:

## Encapsulation:

Each part of the project (board, pieces, players, GUI) is separated into its own class or package.

## Inheritance & Polymorphism:

All chess pieces extend a shared Piece superclass and provide their own behavior.

## Abstraction:

Each phase hides unnecessary details and exposes only what other classes need.

## Composition:

The board is made up of tiles and pieces; the game is composed of its board and players.

## Phase 3 Architectural Improvements

Phase 3 is where the code becomes more organized and modular, following a loose MVC structure:

### Model:

        • board/, pieces/, players/
        • Handles game rules, movement, and state.

### View/Controller:

      • gui/
      • Handles everything the player sees and interacts with.

### Support Utilities:

        • Notation.java and Tracker.java
        • Manage how moves are stored and displayed.

### Entry Point:

        Main.java launches the Phase 3 application.

This structure makes the project easier to understand, scale, and maintain.

# Phase 3 Visual Improvements

To fix board coloring issues across systems, Phase 3 uses these settings:

    btn.setOpaque(true);
    btn.setContentAreaFilled(true);
    btn.setBorderPainted(false);

This ensures the alternating board colors show up correctly, regardless of the OS.

Other GUI improvements include:

        • Cleaner, more polished board layout
        • Better contrast and visuals
        • Optional move history panel
        • Status labels (current player, etc.)

# Final Notes

Each phase builds on the previous one, and by the time you reach Phase 3, the project has evolved into a well-structured GUI program with organized packages, better visuals, and added features like tracking and notation.

For details about each phase, see the individual READMEs inside the Phase1, Phase2, and Phase3 folders.
