# Phase 2 – GUI Version (Swing)

## CS3354 – Object-Oriented Design & Programming

Phase 2 is where the project moves from a console-based game to a full graphical user interface using Java Swing.
This version keeps the logic from Phase 1 but replaces text input/output with buttons, panels, menus, and event-driven interaction.

---

## What Phase 2 Focuses On

• Switching from console output to an actual Swing-based GUI
• Rendering the board visually instead of printing it in ASCII
• Adding interactive components (buttons, menus, panels)
• Introducing event-driven programming
• Setting up the structure that Phase 3 builds on

All player interaction now happens through GUI elements instead of typing commands into the terminal.

---

## Files Included (Phase2_GUI)

Phase2_GUI/
GuiChess.java # Main class containing main() to launch GUI
BoardGUI.java # Visual board component (JPanel/JButtons)
GameHistoryPanel.java # Move history display (scrollable panel)
GameLogic.java # Logic controller connecting board and UI
MenuManager.java # Menu bar, actions, and UI controls

## How to Compile & Run (Terminal)

# 1. Make sure you're inside the Phase2_GUI folder:

cd Phase2_GUI

# 2. Compile everything

javac -d . \*.java

# 3. Run

java Phase2_GUI.GuiChess

**This launches the Swing GUI window for Phase 2.**

# How Phase 2 Is Structured

## GuiChess.java

        • Main application launcher
        • Builds the window
        • Initializes the main panels

## BoardGUI.java

        • Handles the board visual
        • Uses JButtons for each square
        • Manages tile selection and repainting

### GameLogic.java

        • Connects GUI events to the underlying logic
        • Updates the board when a piece is moved

### GameHistoryPanel.java

        • Shows a scrolling list of past moves
        • Updates whenever a new move is made

### MenuManager.java

        • Builds the menu bar (File, Help, etc.)
        • Handles menu button actions

These classes together recreate the Phase 1 logic but inside a full GUI environment.

# Features Added in Phase 2

        • A visual board instead of text
        • Click-based interaction
        • A menu bar for basic actions
        • Move history UI
        • Real-time board updates
        • General improvements to usability

This version is the bridge between Phase 1’s logic and Phase 3’s polished interface.

# How Phase 2 Relates to Phase 1

Phase 2 still uses the core logic from Phase 1, but removes all console-based interaction.

Instead of typing moves, players click on the board.
Phase 3 will refine this interface even further with cleaner visuals, better layouts, board coloring fixes, and integrated move tracking.
