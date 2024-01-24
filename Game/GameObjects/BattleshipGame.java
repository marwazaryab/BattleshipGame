package Game.GameObjects;

import java.awt.Color;
import java.awt.Font;
import java.io.PrintWriter;
import java.util.*;
import javax.swing.JButton;
import Game.Util.Prompt;
import Game.View.BattleshipGUI;
import Game.View.BattleshipGUI.PANEL_STATES;

/**
 * BattleshipGame class
 * 
 * @since 1/23/24
 * @author Abdul Mustafa Mohib & Marwa Zaryab
 *         A class that contains all data for the Battleship game and updates it
 *         accordingly based on game actions
 */
public class BattleshipGame extends Object {

    // instance variables
    private BattleshipGUI view; // the view of the Battleship game

    private int playerShipsSunk; // The number of ships that the player has sunk
    private int computerShipsSunk; // The number of ships that the computer has sunk
    private int computerRemainingShips; // The number of ships that the computer has remaining
    private int playerRemainingShips; // The number of ships that the player has remaining
    private int playerGuessHighScore; // The highscore of the player; depends on the number of guesses it takes to
                                      // finish the program
    private int computerShipNum; // The ship number for the computer
    private int computerShipRow; // The row on which the computer ship is deployed
    private int computerShipCol; // The column on which the computer ship is deployed
    private int shipNum; // The ship number for the player
    private int playerRowGuessed; // The row of the grid that the player has guessed
    private int playerColGuessed; // The column of the grid that the player has guessed
    private int compRowGuessed; // The row of the grid that the computer has guessed
    private int compColGuessed; // The column of the grid that the computer has guessed
    private int playerHits; // The amount of hits the player has gotten
    private int computerHits; // The amount of hits the computer has done
    private int numPlayerGuesses; // The amount of guesses the player has made
    private int numCompGuesses; // The amount of guesses the computer has made
    private String[][] playerGuesses; // An array of the players guesses
    private String[][] computerGuesses; // An array of the computers guesses
    private String[][] playerShips; // An array of where the players ships are
    private String[][] computerShips; // An array of where the computers ships are
    private String currentTurn; // The current players turn
    private String playerName; // The name of the player
    private String winner = ""; // The winner of the game
    private int[] playerShipLength = new int[] { 5, 3, 3, 2, 1 }; // an array of the player ships length
    private int[] computerShipsLength = new int[] { 5, 3, 3, 2, 1 }; // an array of the computer ships length
    private boolean isGameEnded; // Boolean value that is true when the game is ended
    private boolean hasPlayerHit; // Boolean value that is true when a player lands a hit
    private boolean hasCompHit; // Boolean value that is true when the computer lands a hit
    private boolean isCompShipHorizontal; // Boolean value that is true when the computers ship is horizontal
    private boolean isComputerDeploy; // Boolean value that is true when the computers ships ar all deployed
    private boolean isDeploymentFinished; // Boolean value that is true when the deployment of both ships are done
    private boolean isValidPosition; // Boolean value that is true when there a guess is valid
    private boolean isNewGame; // Boolean value that is true when a new game can start
    private boolean hasCompShipSunk; // Boolean value that determines when a computer ship has been sunk
    private boolean hasPlayerShipSunk; // Boolean value that determines when a player ship has been sunk
    private Random randomDirection; // A random object to make a random direction when deploying computer ships
    private int currentRound; // The current round of the game (first game, second game etc)
    private int[] playerHighScores = new int[100]; // An array of the players high scores
    private int highScoreIndex; // An index of the players high scores

    int oldCompRowGuessed = 0;
    int oldCompColGuessed = 0;

    /**
     * BattleshipGame Constructor -- sets instance variable values
     */
    public BattleshipGame() {
        super(); // Calls the parent constructor

        // Set all default values
        this.shipNum = 0;
        this.playerShipsSunk = 0;
        this.computerShipsSunk = 0;
        this.computerRemainingShips = 0;
        this.playerRemainingShips = 0;
        this.playerGuessHighScore = 1000;
        this.isCompShipHorizontal = false;
        this.randomDirection = new Random();
        this.isComputerDeploy = false;
        this.isDeploymentFinished = false;
        this.computerShipNum = 0;
        this.numPlayerGuesses = 0;
        this.numCompGuesses = 0;
        this.hasCompShipSunk = false;
        this.hasPlayerShipSunk = false;
        this.currentRound = 1;
        this.computerRemainingShips = 5;
        this.playerRemainingShips = 5;
    }

    /**
     * Set the GUI to the GUI passed in the arguments
     * 
     * @param gui
     */
    public void setGUI(BattleshipGUI gui) {
        this.view = gui;
    }

    /**
     * a method that updates the view by calling the view's update method
     */
    public void updateView() {
        this.view.update();
    }

    /**
     * @author Mohib and Marwa
     *         A method that creates a 2D array grid
     * @param difficulty the difficulty level -- changes the grid size depending
     *                   on what difficulty it is
     */
    public void createGrid(String difficulty) {

        // Switch case statement for the different difficulty levels
        switch (difficulty) {

            // If the level is 'Easy'
            case "Easy":
                this.view.setPanelState(PANEL_STATES.GAME); // sets the state of the panel

                // Create new 15 by 15 arrays
                playerGuesses = new String[15][15];
                computerGuesses = new String[15][15];
                playerShips = new String[15][15];
                computerShips = new String[15][15];
                this.isNewGame = true;
                this.updateView(); // update the view to reflect the new grid
                this.isNewGame = false;
                break;

            // If the level is 'Medium'
            case "Medium":
                this.view.setPanelState(PANEL_STATES.GAME);

                playerGuesses = new String[20][20];
                computerGuesses = new String[20][20];
                playerShips = new String[20][20];
                computerShips = new String[20][20];
                this.isNewGame = true;
                this.updateView();
                this.isNewGame = false;
                break;

            // If the level is 'Hard'
            case "Hard":
                view.setPanelState(PANEL_STATES.GAME);

                playerGuesses = new String[25][25];
                computerGuesses = new String[25][25];
                playerShips = new String[25][25];
                computerShips = new String[25][25];
                this.isNewGame = true;
                this.updateView();
                this.isNewGame = false;
                break;

            default: // Default just breaks
                break;
        }

        this.setGridValues(); // Call the method to set the values of the grids
    }

    /**
     * @author Mohib
     *         A method to set the values of the player and computer grids
     */
    public void setGridValues() {

        // For loop to loop through the array and set it to the default value of 'O'
        // sets default values for player/computer guesses and player/computer ships
        for (int x = 0; x < playerShips.length; x++) {
            for (int y = 0; y < playerShips[x].length; y++) {
                playerShips[x][y] = "O";
            }
        }
        for (int x = 0; x < computerShips.length; x++) {
            for (int y = 0; y < computerShips[x].length; y++) {
                computerShips[x][y] = "O";
            }
        }
        for (int x = 0; x < playerGuesses.length; x++) {
            for (int y = 0; y < playerGuesses[x].length; y++) {
                playerGuesses[x][y] = "O";
            }
        }
        for (int x = 0; x < computerGuesses.length; x++) {
            for (int y = 0; y < computerGuesses[x].length; y++) {
                computerGuesses[x][y] = "O";
            }
        }
    }

    /**
     * @author Marwa and Mohib
     *         A method to deploy the player ships before the guessing starts
     * @param playerGrid    the players grid
     * @param computerGrid  the computers grid
     * @param rowClicked    the row that the player clicked
     * @param columnClicked the column that the player clicked
     * @param isHorizontal  if the player selected horizontal or not
     */
    public void deployPlayerShips(JButton[][] playerGrid, JButton[][] computerGrid, int rowClicked, int columnClicked,
            boolean isHorizontal) {

        // If the ship number is less than the length of the player ships array
        if (shipNum < playerShipLength.length) {

            // If the player wants the ship to be horizontal
            if (isHorizontal == true) {

                // If the ship placement is valid -- calls the isValidPlacaement method to check
                if (this.isValidPlacement(isComputerDeploy, rowClicked, columnClicked, isHorizontal, playerGrid)) {

                    // Loop through the array until it hits the row and column clicked
                    for (int i = 0; i < playerShipLength[shipNum]; i++) {
                        playerGrid[rowClicked][columnClicked + i].setText("X"); // set the text "X" so the player can
                                                                                // see where their ship is
                        playerShips[rowClicked][columnClicked + i] = "X"; // Set the internal array to be 'X'
                    }
                    this.updatePlayerShips(playerGrid, shipNum); // Call the update player ships method
                    this.isValidPosition = true;
                    shipNum++; // increment the number of ships

                } else {
                    this.isValidPosition = false; // Otherwise set the position to be invalid
                }

            } else if (isHorizontal == false) { // If the player wants the ship to be vertical

                // If the ship placement is valid -- uses a method to determine it
                if (this.isValidPlacement(isComputerDeploy, rowClicked, columnClicked, isHorizontal, playerGrid)) {

                    // Loop through the items in the array
                    for (int i = 0; i < playerShipLength[shipNum]; i++) {
                        playerGrid[rowClicked + i][columnClicked].setText("X"); // Set the text as 'X' so the player can
                                                                                // see
                        playerShips[rowClicked + i][columnClicked] = "X";
                    }
                    this.updatePlayerShips(playerGrid, shipNum); // Update the players ships
                    this.isValidPosition = true;
                    shipNum++;
                }

                else { // otherwise it is not valid
                    this.isValidPosition = false;
                }
            }

            this.updateView(); // Update the view to reflect the changes
        }

        else {
            deployShipsComputer(computerGrid); // Deploy the computers ships by calling the deployComputerShips method
        }
    }

    /**
     * @author Marwa and Mohib
     *         A method that deploys the computers ships randomly
     * @param computerGrid the grid of the computer
     */
    public void deployShipsComputer(JButton[][] computerGrid) {

        this.isComputerDeploy = true; // set the computer deployment status to true

        // While loop that checks whether computer ship number is less than computer
        // ship array length
        while (computerShipNum < computerShipsLength.length) {

            // retrive a random direction for the computer ship to be placed
            this.isCompShipHorizontal = randomDirection.nextBoolean();

            // retrieve a random row and column to place the ship
            computerShipRow = (int) (Math.random() * (computerGrid.length));
            computerShipCol = (int) (Math.random() * (computerGrid[0].length));

            // check to see if the computer ship is placed horizontally
            if (this.isCompShipHorizontal == true) {

                // check to see if the coordinates selected are valid or not
                if (this.isValidPlacement(isComputerDeploy, computerShipRow, computerShipCol, isCompShipHorizontal,
                        computerGrid)) {

                    // set text on the computer grid respective to size of computer ship length
                    for (int i = 0; i < computerShipsLength[computerShipNum]; i++) {
                        computerShips[computerShipRow][computerShipCol + i] = "X"; // set the array in the model to be
                                                                                   // equal to "X"
                    }

                    updateComputerShips(computerGrid, computerShipNum); // call the updateComputerShips method
                    computerShipNum++; // increment the computer ship number
                }

                // run the code below if the computer placed it vertically
            } else if (this.isCompShipHorizontal == false) {

                // check to see if the coordinates selected are valid or not
                if (this.isValidPlacement(isComputerDeploy, computerShipRow, computerShipCol, isCompShipHorizontal,
                        computerGrid)) {

                    // set text on the computer grid respective to size of computer ship length
                    for (int i = 0; i < computerShipsLength[computerShipNum]; i++) {
                        computerShips[computerShipRow + i][computerShipCol] = "X"; // set the array in the model
                                                                                   // to be equal to "X"
                    }

                    updateComputerShips(computerGrid, computerShipNum); // call the updateComputerShips method
                    computerShipNum++; // increment the computer ship number
                }
            }
        }

        this.isDeploymentFinished = true; // set the deployment status to finished
        this.currentTurn = "Player"; // set the current turn to Player

        this.updateView(); // update the view to display changes to the grids and turn labels
    }

    /**
     * @author Mohib
     *         A method that checks if the ship placement is valid
     * @param isComp       boolean value to check if it is the computers turn
     * @param row          row being checked
     * @param col          column being checked
     * @param isHorizontal true if the ship is horizontal
     * @param grid         the grid being checked
     * @return
     */
    public boolean isValidPlacement(boolean isComp, int row, int col, boolean isHorizontal, JButton[][] grid) {

        // If it is the computers turn
        if (isComp == true) {

            // If the ship is horizontal
            if (isHorizontal == true) {

                // If the col and the length of the ship exceeds the grid boundaries
                if (col + computerShipsLength[computerShipNum] > grid.length) {
                    return false; // it is not a valid placement

                    // If there is already a ship placed at the given position
                } else if (grid[row][col].getText().equals("X")) {
                    return false; // It is not a valid placement

                    // Otherwise if it is intersecting a ship
                } else {
                    for (int x = col; x < (col + computerShipsLength[computerShipNum]); x++) {
                        if (grid[row][x].getText().equals("X")) {
                            return false; // It is onot a valid placement
                        }
                    }
                }

                // if the ship is vertical
            } else if (isHorizontal == false) {

                // If the row and the length of the ship exceeds the grid boundaries
                if (row + computerShipsLength[computerShipNum] > grid[0].length) {
                    return false;

                    // If the ship is on another ship
                } else if (grid[row][col].getText().equals("X")) {
                    return false;

                    // If the ship is interfering with another ship
                } else {
                    for (int x = row; x < (row + computerShipsLength[computerShipNum]); x++) {
                        if (grid[x][col].getText().equals("X")) {
                            return false;
                        }
                    }
                }
            }

            // If none of the conditions above apply, return true (valid placement)
            return true;
        }

        // if it is the player's turn to place ships
        else {

            // If the ship is horizontal
            if (isHorizontal == true) {

                // If the col and the length of the ship exceeds the grid boundaries
                if (col + playerShipLength[shipNum] > grid.length) {
                    return false; // it is not a valid placement
                }

                // If there is already a ship placed at the given position
                else if (grid[row][col].getText().equals("X")) {
                    return false; // it is not a valid placement
                }

                // Otherwise if it is intersecting a ship
                else {
                    for (int x = col; x < (col + playerShipLength[shipNum]); x++) {
                        if (grid[row][x].getText().equals("X")) {
                            return false; // it is not a valid placement
                        }
                    }
                }

                // if the ship is vertical
            } else if (isHorizontal == false) {

                // If the row and the length of the ship exceeds the grid boundaries
                if (row + playerShipLength[shipNum] > grid.length) {
                    return false; // it is not a valid placement
                }

                // If there is already a ship placed at the given position
                else if (grid[row][col].getText().equals("X")) {
                    return false; // it is not a valid placement
                }

                // Otherwise if it is intersecting a ship
                else {
                    for (int x = row; x < (row + playerShipLength[shipNum]); x++) {
                        if (grid[x][col].getText().equals("X")) {
                            return false; // it is not a valid placement
                        }
                    }
                }
            }

            // If none of the conditions above apply, return true (valid placement)
            return true;
        }
    }

    /**
     * @author Mohib
     *         A method that updates the String arrays in the model respective to
     *         the number of the player ship that is being placed to create unique
     *         player ships
     * @param playerGrid the player's grid
     * @param shipNumber the player's ship number
     */
    public void updatePlayerShips(JButton[][] playerGrid, int shipNumber) {

        // switch case statement to evaluate the ship number
        switch (shipNumber) {

            // for each ship number, a unique number is assigned to the ship on the String
            // 2D array based on the number of buttons on it
            // which makes it easy to identify which ship has been hit

            // ship 1
            case 0:

                // loop through each row and column of the array
                for (int x = 0; x < playerGrid.length; x++) {
                    for (int y = 0; y < playerGrid[x].length; y++) {
                        if (playerShips[x][y].equals("X")) { // set the number based on if its "x"
                            this.playerShips[x][y] = "1";
                        }
                    }
                }
                break;

            // ship 2
            case 1:
                // loop through each row and column of the array
                for (int x = 0; x < playerGrid.length; x++) {
                    for (int y = 0; y < playerGrid[x].length; y++) {
                        if (playerShips[x][y].equals("X")) { // set the number based on if its "x"
                            this.playerShips[x][y] = "2";
                        }
                    }
                }
                break;

            // ship 3
            case 2:
                // loop through each row and column of the array
                for (int x = 0; x < playerGrid.length; x++) {
                    for (int y = 0; y < playerGrid[x].length; y++) {
                        if (playerShips[x][y].equalsIgnoreCase("X")) { // set the number based on if its "x"
                            this.playerShips[x][y] = "3";
                        }
                    }
                }
                break;

            // ship 4
            case 3:
                // loop through each row and column of the array
                for (int x = 0; x < playerGrid.length; x++) {
                    for (int y = 0; y < playerGrid[x].length; y++) {
                        if (playerShips[x][y].equals("X")) { // set the number based on if its "x"
                            this.playerShips[x][y] = "4";
                        }
                    }
                }
                break;

            // ship 5
            case 4:
                // loop through each row and column of the array
                for (int x = 0; x < playerGrid.length; x++) {
                    for (int y = 0; y < playerGrid[x].length; y++) {
                        if (playerShips[x][y].equals("X")) { // set the number based on if its "x"
                            this.playerShips[x][y] = "5";
                        }
                    }
                }
                break;
        }
    }

    /**
     * @author Mohib
     *         A method that updates the String arrays in the model respective to
     *         the number of the computer ship that is being placed to create unique
     *         computer ships
     * @param playerGrid the player's grid
     * @param shipNumber the player's ship number
     */
    public void updateComputerShips(JButton[][] computerGrid, int computerShipNumber) {

        // for each ship number, a unique number is assigned to the ship on the String
        // 2D array
        // which makes it easy to identify which ship has been hit

        switch (computerShipNumber) {

            // ship 1
            case 0:
                // loop through each row and column of the array
                for (int x = 0; x < computerGrid.length; x++) {
                    for (int y = 0; y < computerGrid[x].length; y++) {
                        if (computerShips[x][y].equals("X")) {
                            this.computerShips[x][y] = "1";
                        }
                    }
                }
                break;

            // ship 2
            case 1:
                // loop through each row and column of the array
                for (int x = 0; x < computerGrid.length; x++) {
                    for (int y = 0; y < computerGrid[x].length; y++) {
                        if (computerShips[x][y].equals("X")) {
                            this.computerShips[x][y] = "2";
                        }
                    }
                }
                break;

            // ship 3
            case 2:
                // loop through each row and column of the array
                for (int x = 0; x < computerGrid.length; x++) {
                    for (int y = 0; y < computerGrid[x].length; y++) {
                        if (computerShips[x][y].equals("X")) {
                            this.computerShips[x][y] = "3";
                        }
                    }
                }
                break;

            // ship 4
            case 3:
                // loop through each row and column of the array
                for (int x = 0; x < computerGrid.length; x++) {
                    for (int y = 0; y < computerGrid[x].length; y++) {
                        if (computerShips[x][y].equals("X")) {
                            this.computerShips[x][y] = "4";
                        }
                    }
                }
                break;

            // ship 5
            case 4:
                // loop through each row and column of the array
                for (int x = 0; x < computerGrid.length; x++) {
                    for (int y = 0; y < computerGrid[x].length; y++) {
                        if (computerShips[x][y].equals("X")) {
                            this.computerShips[x][y] = "5";
                        }
                    }
                }
                break;
        }

    }

    /**
     * @author Mohib
     *         A method that checks whether a ship has sunk or not upon a succesful
     *         hit
     * @param shipHit the unique number for the ship that has been hit
     */
    public void hasShipSunk(String shipHit) {

        // if the current turn is player's
        if (currentTurn.equals("Player")) {

            // cycle through the computer ships String 2D array to find a matching number
            for (int x = 0; x < computerShips.length; x++) {
                for (int y = 0; y < computerShips[x].length; y++) {

                    // if a matching number is found, the ship is not sunk
                    if (computerShips[x][y].equals(shipHit)) {
                        hasCompShipSunk = false;
                        break;
                    } else { // the ship is sunk
                        hasCompShipSunk = true;
                    }
                }

                // break out of the loop
                if (hasCompShipSunk == false) {
                    break;
                }
            }

            // if the computer ship is sunk decrement the computer remaining ships
            if (hasCompShipSunk == true) {
                computerRemainingShips--;
            }

        }

        // if it is the computer's turn
        else {

            // cycle through the player ships String 2D array to find a matching number
            for (int x = 0; x < playerShips.length; x++) {
                for (int y = 0; y < playerShips[x].length; y++) {

                    // if a matching number is found, the ship is not sunk
                    if (playerShips[x][y].equals(shipHit)) {
                        hasPlayerShipSunk = false;
                        break;
                    } else { // the ship is sunk
                        hasPlayerShipSunk = true;
                    }
                }

                // break out of the loop
                if (hasPlayerShipSunk == false) {
                    break;
                }
            }

            // if the player ship is sunk decrement the player remaining ships
            if (hasPlayerShipSunk == true) {
                playerRemainingShips--;
            }
        }
    }

    /**
     * @author Marwa
     *         A method that resets all of the values
     */
    public void restart() {

        currentRound++; // increment the round number

        // reset all the values
        this.isGameEnded = false;
        this.view.isRestart = true;
        this.setPlayerName(null);
        this.shipNum = 0;
        this.computerRemainingShips = 5;
        this.playerRemainingShips = 5;
        this.playerGuessHighScore = this.getPlayerGuessHighScore();
        this.isCompShipHorizontal = false;
        this.randomDirection = new Random();
        this.isComputerDeploy = false;
        this.isDeploymentFinished = false;
        this.computerShipNum = 0;
        this.numPlayerGuesses = 0;
        this.numCompGuesses = 0;
        this.playerHits = 0;
        this.computerHits = 0;

        // Switch the panel state so it can display the title screen
        this.view.setPanelState(PANEL_STATES.TITLE);

        // update the view
        this.updateView();
    }

    /**
     * @author Mohib
     *         A method that allows the player to perform their turn and identifies
     *         whether
     *         they have hit, miss, or sunk a ship
     * 
     * @param rowClicked the row that the button was clicked
     * @param colClicked the column that the button was clicked
     */
    public void playerShipTurn(int rowClicked, int colClicked) {

        // If the button clicked isn't an empty space or a missed guess
        if (computerShips[rowClicked][colClicked] != "O") {
            if (this.playerGuesses[rowClicked][colClicked] != "!") {

                this.hasPlayerHit = true; // Then the player hit a ship
                String shipHit = computerShips[rowClicked][colClicked]; // create a string to represent the ship to pass
                                                                        // into the method below
                this.playerHits++;
                this.computerShips[rowClicked][colClicked] = "O";
                this.hasShipSunk(shipHit); // Check to see if the ship has been sunk
            }

        } else {
            this.hasPlayerHit = false; // Otherwise the player did not hit any ship
        }

        this.playerGuesses[rowClicked][colClicked] = "!"; // Update the internal player guesses array
        this.numPlayerGuesses++; // Increase the number of guesses the player did
        this.playerRowGuessed = rowClicked;
        this.playerColGuessed = colClicked;
        checkGameStatus(); // Check to see if the game has been updated
        this.updateView(); // Update the view
        // Reset values
        this.playerRowGuessed = 0;
        this.playerColGuessed = 0;
        this.currentTurn = "Computer"; // Change the turn of the player
    }

    /**
     * @author Marwa
     *         A method that allows the computer to make guesses
     */
    public void computerShipTurn() {

        boolean guessHorizontal = true; // boolean value used to determine if computer should guess horizontall or not

        // retrieve a random guess
        this.compRowGuessed = (int) (Math.random() * (playerShips.length));
        this.compColGuessed = (int) (Math.random() * (playerShips.length));

        // if a ship has not been hit
        if (this.getCompHitStatus() == false) {

            // check to see if the random guess has not been guessed before and it hits a
            // ship
            if (!playerShips[compRowGuessed][compColGuessed].equals("O")) {
                if (!this.computerGuesses[compRowGuessed][compColGuessed].equals("!")) {

                    this.hasCompHit = true; // Then the computer hit the ship
                    String shipHit = playerShips[compRowGuessed][compColGuessed]; // Create a string of the ship that
                                                                                  // was
                                                                                  // hit to pass into the method
                    this.computerHits++;
                    this.playerShips[compRowGuessed][compColGuessed] = "O";
                    this.hasShipSunk(shipHit); // Run this method to check if the ship has been sunken

                    // if the ship does not sink after the hit, save the positions of the coordinate
                    if (hasPlayerShipSunk == false) {
                        oldCompRowGuessed = compRowGuessed;
                        oldCompColGuessed = compColGuessed;
                    }

                }

                // if a hit has not been made, set the boolean value to false
            } else {
                this.hasCompHit = false;
            }
        }

        // if a hit has been made
        else {

            // set the computer row and column guessed
            compRowGuessed = oldCompRowGuessed;
            compColGuessed = oldCompColGuessed;

            // check to see what direction the player ship is present in
            guessHorizontal = this.computerGuessHorizontal(compRowGuessed, compColGuessed);

            // if the ship is present horizontally increase the column guessed
            if (guessHorizontal == true) {
                compColGuessed++;
            }

            // else increase the row guessed
            else {
                compRowGuessed++;
            }

            // check to see if the random guess has not been guessed before and it hits a
            // ship
            if (!playerShips[compRowGuessed][compColGuessed].equals("O")) {
                if (!this.computerGuesses[compRowGuessed][compColGuessed].equals("!")) {

                    this.hasCompHit = true; // Then the computer hit the ship
                    String shipHit = playerShips[compRowGuessed][compColGuessed]; // Create a string of the ship that
                                                                                  // was
                                                                                  // hit to pass into the method
                    this.computerHits++;
                    this.playerShips[compRowGuessed][compColGuessed] = "O";
                    this.hasShipSunk(shipHit); // Run this method to check if the ship has been sunken

                    // check to see if the ships has sunk
                    if (hasPlayerShipSunk == false) {
                        oldCompRowGuessed = compRowGuessed;
                        oldCompColGuessed = compColGuessed;
                    }
                }

                // the ship has not been hit
            } else {
                this.hasCompHit = false;
            }
        }

        // incrmenet compouter guesses and set the position guessed to "!"
        this.numCompGuesses++;
        this.computerGuesses[compRowGuessed][compColGuessed] = "!";

        // check to see if game has ended and update view; also switch the turn
        checkGameStatus();
        this.updateView();
        this.currentTurn = "Player";
    }

    /**
     * @author Marwa
     *         A method to check to see if the computers next turn should be
     *         horizontal or not
     * @param row the row that was guessed
     * @param col the column that was guessed
     * @return a boolean value determining whether or not the next move should be
     *         horizontal
     */
    public boolean computerGuessHorizontal(int row, int col) {

        // If the column that was guessed +1 and the one after it is a ship that was hit
        if ((col + 1) < playerShips[0].length && playerShips[row][col + 1].equals("X")
                || col - 1 != -1 && playerShips[row][col - 1].equals("X")) { // Or the one before it
            return true; // Guess horizontal
        } else {
            return false;
        }
    }

    /**
     * @author Mohib
     *         A method to check whether the game has ended or not
     */
    public void checkGameStatus() {

        // If there are no ships remaining for the computer
        if (computerRemainingShips == 0) {
            this.winner = this.getPlayerName(); // The winner is the player
            this.isGameEnded = true; // set the game to ended

            // if the player hits a new highscore
            if (this.getNumPlayerGuesses() < this.getPlayerGuessHighScore()) {
                this.setPlayerGuessHighScore(this.getNumPlayerGuesses()); // Set the highscore of the player
            }

            // If the player has no ships left
        } else if (playerRemainingShips == 0) {
            this.winner = "Computer"; // the winner is the computer
            this.isGameEnded = true; // set the game to ended
        }
    }

    /**
     * @author Marwa
     *         A method to disable a grid
     * @param grid to the disabled
     */
    public void disableGrid(JButton[][] grid) {

        // cycle through array and disable buttons
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                grid[x][y].setEnabled(false);
            }
        }
    }

    /**
     * @author Marwa
     *         A method that outputs all the stats to a seperate file
     */
    public void outputStats() {

        PrintWriter outputFile = Prompt.getPrintWriter(); // Get the output file from the static Prompt Class

        // Format all the info
        outputFile.println("---------------------- GAME ANALYSIS ----------------------");

        outputFile.printf("%-30s", "Current Round: " + this.getCurrentRound());

        // Change the winner status based on who won the game
        if (this.getWinner().equals(this.getPlayerName())) {
            outputFile.printf("%-30s", "\tWINNER STATUS: PLAYER 1 WON");
        } else {
            outputFile.printf("%-30s", "\tWINNER STATUS: COMPUTER WON");
        }

        // output stats and labels to file
        outputFile.println("\nNumber of Hits (Player): " + this.getPlayerHits());
        outputFile.println("Number of Hits (Computer): " + this.getComputerHits());
        outputFile.println("Timer End: " + this.view.timePassed() + " seconds");

        // output player high score list
        outputFile.print("\nPlayer Guess Highscore list: ");
        this.sortPlayerGuessHighScore(playerHighScores);
        this.printArrayFile(outputFile, playerHighScores);

        // close file
        outputFile.close();
    }

    /**
     * @author Marwa
     *         Sort using the insert sort algorithm
     * @param array to be sorted
     */
    public void sortPlayerGuessHighScore(int[] array) {

        // Loop through the array
        for (int x = 1; x < array.length; x++) {
            int current = array[x]; // Set the current position
            int position = x;

            // While the index is over 0 and the element before the position is greater than
            // the current
            while (position > 0 && array[position - 1] > current) {

                // swap
                array[position] = array[position - 1];
                position--;
            }
            array[position] = current;
        }
    }

    /**
     * @author Marwa
     *         A method to print the highscore array to the output file
     * @param outputFile
     * @param array
     */
    public void printArrayFile(PrintWriter outputFile, int[] array) {

        // For loop to loop through all the elements in the array
        for (int i = 0; i < array.length; i++) {

            // only print out the array element when the highscore isn't zero
            if (array[i] != 0) {
                outputFile.print(array[i] + " | ");
            }
        }
    }

    /**
     * @author Marwa
     *         A method to end the game
     */
    public void end() {
        this.view.setPanelState(PANEL_STATES.END); // switch the enum state and then update the view
        this.updateView();
    }

    // ----------------------- ACCESSOR METHODS ---------------------------

    /**
     * method that retrieves the game winner
     * 
     * @return the winner of the game
     */
    public String getWinner() {
        return this.winner;
    }

    /**
     * method that checks if computer ship is sunk
     * 
     * @return whether ship is sunk
     */
    public boolean getCompShipSunk() {
        return this.hasCompShipSunk;
    }

    /**
     * method that checks if player ship is sunk
     * 
     * @return whether ship is sunk
     */
    public boolean getPlayerShipSunk() {
        return this.hasPlayerShipSunk;
    }

    /**
     * method that retrieves the number of player guesses
     * 
     * @return the number of player guesses
     */
    public int getNumPlayerGuesses() {
        return this.numPlayerGuesses;
    }

    /**
     * method that retrieves the number of computer guesses
     * 
     * @return number of computer guesses
     */
    public int getNumCompGuesses() {
        return this.numCompGuesses;
    }

    /**
     * method that retrieves the player row guessed
     * 
     * @return player row guessed
     */
    public int getPlayerRowGuessed() {
        return this.playerRowGuessed;
    }

    /**
     * method that retrieves the player col guessed
     * 
     * @return player column guessed
     */
    public int getPlayerColGuessed() {
        return this.playerColGuessed;
    }

    /**
     * method that retrieves the computer row guessed
     * 
     * @return computer row guessed
     */
    public int getCompRowGuessed() {
        return this.compRowGuessed;
    }

    /**
     * method that retrieves the computer col guessed
     * 
     * @return computer column guessed
     */
    public int getCompColGuessed() {
        return this.compColGuessed;
    }

    /**
     * method that retrieves ship number for player
     * 
     * @return ship num for player
     */
    public int getShipNum() {
        return this.shipNum;
    }

    /**
     * method that retrieves new game status
     * 
     * @return new game status
     */
    public boolean getNewGame() {
        return this.isNewGame;
    }

    /**
     * method that validates a guess
     * 
     * @return true
     */
    public boolean validateGuess() {
        return true;
    }

    /**
     * method that retrieves whether it is a valid position
     * 
     * @return whether it is a valid position
     */
    public boolean getValidPosition() {
        return this.isValidPosition;
    }

    /**
     * method that retrieves number of player hits
     * 
     * @return number of player hits
     */
    public int getPlayerHits() {
        return this.playerHits;
    }

    /**
     * method that retrieves number of computer hits
     * 
     * @return number of computer hits
     */
    public int getComputerHits() {
        return this.computerHits;
    }

    /**
     * method that retrieves number of player ships sunk
     * 
     * @return number of player ships sunk
     */
    public int getPlayerShipsSunk() {
        return this.playerShipsSunk;
    }

    /**
     * method that retrieves number of computer ships sunk
     * 
     * @return number of computer ships sunk
     */
    public int getComputerShipsSunk() {
        return this.computerShipsSunk;
    }

    /**
     * method that retrieves player remaining ships
     * 
     * @return number of player remaining ships
     */
    public int getPlayerRemainingShips() {
        return this.playerRemainingShips;
    }

    /**
     * method that retrieves computer remaining ships
     * 
     * @return number of computer remaining ships
     */
    public int getComputerRemainingShips() {
        return this.computerRemainingShips;
    }

    /**
     * method that retrieves player guess high score
     * 
     * @return player guess high score
     */
    public int getPlayerGuessHighScore() {
        return this.playerGuessHighScore;
    }

    /**
     * method that retrieves player name
     * 
     * @return player name
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * method that retrieves player guesses
     * 
     * @return player guesses 2D array
     */
    public String[][] getPlayerGuesses() {
        return this.playerGuesses;
    }

    /**
     * method that retrieves computer guesses
     * 
     * @return computer guesses 2D array
     */
    public String[][] getComputerGuesses() {
        return this.computerGuesses;
    }

    /**
     * method that retrieves player ships
     * 
     * @return player ships 2D array
     */
    public String[][] getPlayerShips() {
        return this.playerShips;
    }

    /**
     * method that retrieves computer ships
     * 
     * @return computer ships 2D array
     */
    public String[][] getComputerShips() {
        return this.computerShips;
    }

    /**
     * method that retrieves game status
     * 
     * @return whether game is ended
     */
    public boolean getGameStatus() {
        return this.isGameEnded;
    }

    /**
     * method that retrieves game turn
     * 
     * @return turn for the game
     */
    public String getGameTurn() {
        return this.currentTurn;
    }

    /**
     * method that retrieves player hit status
     * 
     * @return if player has landed a hit
     */
    public boolean getPlayerHitStatus() {
        return this.hasPlayerHit;
    }

    /**
     * method that retrieves computer hit status
     * 
     * @return if computer has landed a hit
     */
    public boolean getCompHitStatus() {
        return this.hasCompHit;
    }

    /**
     * method that retrieves deployment status
     * 
     * @return if ships have been deployed
     */
    public boolean getDeploymentStatus() {
        return this.isDeploymentFinished;
    }

    /**
     * method that retrieves grid size
     * 
     * @return length of player grid
     */
    public int getGridSize() {
        return this.getPlayerGuesses().length;
    }

    /**
     * method that retrieves the current round
     * 
     * @return the current round number
     */
    public int getCurrentRound() {
        return this.currentRound;
    }

    // ---------------------------- SETTOR METHODS----------------------------------

    /**
     * A method to set the high score of the player
     * 
     * @param score to add
     */
    public void setPlayerGuessHighScore(int score) {
        this.playerGuessHighScore = score;
        highScoreIndex++;
        playerHighScores[highScoreIndex] = score; // add to the array

    }

    /**
     * method that sets player remaining ships
     * 
     * @param num player remaining ships
     */
    public void setPlayerRemainingShips(int num) {
        this.playerRemainingShips = num;
    }

    /**
     * method that sets computer remaining ships
     * 
     * @param num computer remaining ships
     */
    public void setComputerRemainingShips(int num) {
        this.computerRemainingShips = num;
    }

    /**
     * method that sets game status
     * 
     * @param status game status
     */
    public void setGameStatus(boolean status) {
        this.isGameEnded = status;
    }

    /**
     * method that sets player hit status
     * 
     * @param status player hit status
     */
    public void setPlayerHitStatus(boolean status) {
        this.hasPlayerHit = status;
    }

    /**
     * method that sets computer hit status
     * 
     * @param status computer hit status
     */
    public void setCompHitStatus(boolean status) {
        this.hasCompHit = status;
    }

    /**
     * method that sets player name
     * 
     * @param name player name
     */
    public void setPlayerName(String name) {
        this.playerName = name;
    }

    /**
     * method that sets game turn
     * 
     * @param turn whomever's current turn
     */
    public void setGameTurn(String turn) {
        this.currentTurn = turn;
    }

    /**
     * method that sets player ships sunk
     * 
     * @param num number of player ships sunk
     */
    public void setPlayerShipsSunk(int num) {
        this.playerShipsSunk = num;
    }

    /**
     * method that sets computer ships sunk
     * 
     * @param num number of computer ships sunk
     */
    public void setComputerShipsSunk(int num) {
        this.computerShipsSunk = num;
    }
} // End of class
