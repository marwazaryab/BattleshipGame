/**
 * BattleshipGame class
 * @since 1/23/24
 * @author Abdul Mustafa Mohib & Marwa Zaryab
 * A class that contains all data for the Battleship game and updates it accordingly based on game actions 
 */

//all imports 
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
 * BattleshipGame class; contains data (methods and variables) for the
 * Battleship game
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
    private int computerShipNum;
    private int computerShipRow;
    private int computerShipCol;
    private int shipNum;
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
    private int[] playerShipLength = new int[] { 5, 3, 3, 2, 1 }; // an array of the ships length
    private int[] computerShipsLength = new int[] { 5, 3, 3, 2, 1 }; // an array of the ships length
    private boolean isGameEnded; // Boolean value that is true when the game is ended
    private boolean hasPlayerHit; // Boolean value that is true when a player lands a hit
    private boolean hasCompHit; // Boolean value that is true when the computer lands a hit
    private boolean isCompShipHorizontal; // Boolean value that is true when the computers ship is horizontal
    private boolean isComputerDeploy; // Boolean value that is true when the computers ships ar all deployed
    private boolean isDeploymentFinished; // Boolean value that is true when the deployment of both ships are done
    private boolean isValidPosition; // Boolean value that is true when there a guess is valid
    private boolean isNewGame; // Boolean value that is true when a new game can start
    private boolean isSunk; // Boolean value that determines when a ship has been sunk
    private Random randomDirection; // A random object to make a random direction when deploying computer ships
    private int currentRound; // The current round of the game (first game, second game etc)
    private int[] playerHighScores = new int[100]; // An array of the players high scores
    private int highScoreIndex; // An index of the players high scores

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
        this.playerGuessHighScore = 0;
        this.isCompShipHorizontal = false;
        this.randomDirection = new Random();
        this.isComputerDeploy = false;
        this.isDeploymentFinished = false;
        this.computerShipNum = 0;
        this.numPlayerGuesses = 0;
        this.numCompGuesses = 0;
        this.isSunk = false;
        this.currentRound = 1;
        this.computerRemainingShips = 5;
        this.playerRemainingShips = 5;

    }

    /**
     * Set the GUI
     * 
     * @param gui
     */
    public void setGUI(BattleshipGUI gui) {
        this.view = gui;
    }

    /**
     * Updates the view
     */
    public void updateView() {
        this.view.update();
    }

    /**
     * @author Mohib and Marwa
     *         A method that creates a 2D array grid
     * @param difficulty the difficulty level -- changes the grid size depending
     *                   what what difficulty it is
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

            default:

                break;
        }

        this.setGridValues(); // Call the method to set the values of the grids
    }

    /**
     * @author Mohib
     *         A method to set the values of the player and computer grids
     */
    public void setGridValues() {

        // For loop to loop through the array and set the value of it to 'O'
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

        // If the ship number is less than the length of the grid array
        if (shipNum < playerShipLength.length) {

            // If the player wants the ship to be horizontal
            if (isHorizontal == true) {

                // If the ship placement is valid -- uses a method to check
                if (this.isValidPlacement(isComputerDeploy, rowClicked, columnClicked, isHorizontal, playerGrid)) {

                    // Loop through the array until it hits the row and column clicked
                    for (int i = 0; i < playerShipLength[shipNum]; i++) {
                        playerGrid[rowClicked][columnClicked + i].setText("X"); // set the text "X" so the player can
                                                                                // see where their ship is
                        playerShips[rowClicked][columnClicked + i] = "X"; // Set the internal array to be 'X'
                    }
                    this.updatePlayerShips(playerGrid, shipNum); // Call the update player ships method
                    this.isValidPosition = true;
                    shipNum++; // increment the number of ship

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

                else {
                    this.isValidPosition = false;
                }

            }

            this.updateView(); // Update the view to reflect the changes
        }

        else {
            deployShipsComputer(computerGrid); // Deploy the computers ships using a method
        }

    }

    /**
     * @author Marwa and Mohib
     *         A method that deploys the computers ships randomly
     * @param computerGrid the grid of the computer
     */
    public void deployShipsComputer(JButton[][] computerGrid) {

        this.isComputerDeploy = true;

        while (computerShipNum < computerShipsLength.length) {

            this.isCompShipHorizontal = randomDirection.nextBoolean();
            computerShipRow = (int) (Math.random() * (computerGrid.length));
            computerShipCol = (int) (Math.random() * (computerGrid[0].length));

            if (this.isCompShipHorizontal == true) {

                if (this.isValidPlacement(isComputerDeploy, computerShipRow, computerShipCol, isCompShipHorizontal,
                        computerGrid)) {

                    for (int i = 0; i < computerShipsLength[computerShipNum]; i++) {
                        computerGrid[computerShipRow][computerShipCol + i].setText("X");
                        computerShips[computerShipRow][computerShipCol + i] = "X";
                    }

                    updateComputerShips(computerGrid, computerShipNum);
                    computerShipNum++;

                }

            } else if (this.isCompShipHorizontal == false) {

                if (this.isValidPlacement(isComputerDeploy, computerShipRow, computerShipCol, isCompShipHorizontal,
                        computerGrid)) {

                    for (int i = 0; i < computerShipsLength[computerShipNum]; i++) {

                        computerGrid[computerShipRow + i][computerShipCol].setText("X");
                        computerShips[computerShipRow + i][computerShipCol] = "X";

                    }

                    updateComputerShips(computerGrid, computerShipNum);
                    computerShipNum++;

                }

            }
        }

        this.isDeploymentFinished = true;
        this.currentTurn = "Player";
        this.updateView();
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

                // If the col and the length of the ship is greater than the length of the grid
                if (col + computerShipsLength[computerShipNum] > grid.length) {
                    return false; // it is not a valid placement

                    // If there is already a ship placed
                } else if (grid[row][col].getText().equals("X")) {
                    return false; // It is not a valid placement

                } else { // Otherwise if it is intersecting a ship
                    for (int x = col; x < (col + computerShipsLength[computerShipNum]); x++) {
                        if (grid[row][x].getText().equals("X")) {
                            return false; // It is onot a valid placement
                        }
                    }
                }

                // if the ship is vertical
            } else if (isHorizontal == false) {

                // if the ship goes outside of the grid
                if (row + computerShipsLength[computerShipNum] > grid[0].length) {
                    return false;

                    // If the ship is on another ship
                } else if (grid[row][col].getText().equals("X")) {
                    return false;
                } else {

                    // If the ship is interfering with another ship
                    for (int x = row; x < (row + computerShipsLength[computerShipNum]); x++) {
                        if (grid[x][col].getText() == "X") {
                            return false;
                        }
                    }
                }
            }

            // If everything there is not true then it is a valid placement
            return true;
        }

        else {

            // Do the same check for the player
            if (isHorizontal == true) {
                System.out.println(shipNum);
                if (col + playerShipLength[shipNum] > grid.length) {
                    return false;
                } else if (grid[row][col].getText().equals("X")) {
                    return false;
                } else {
                    for (int x = col; x < (col + playerShipLength[shipNum]); x++) {
                        if (grid[row][x].getText().equals("X")) {
                            return false;
                        }
                    }
                }

            } else if (isHorizontal == false) {

                if (row + playerShipLength[shipNum] > grid.length) {
                    return false;
                } else if (grid[row][col].getText().equals("X")) {
                    return false;
                } else {
                    for (int x = row; x < (row + playerShipLength[shipNum]); x++) {
                        if (grid[x][col].getText() == "X") {
                            return false;
                        }
                    }
                }
            }

            return true;

        }

    }

    public void updatePlayerShips(JButton[][] playerGrid, int shipNumber) {

        switch (shipNumber) {

            case 0:
                for (int x = 0; x < playerGrid.length; x++) {
                    for (int y = 0; y < playerGrid[x].length; y++) {
                        if (playerShips[x][y] == "X") {
                            this.playerShips[x][y] = "1";
                        }
                    }
                }
                break;

            case 1:

                for (int x = 0; x < playerGrid.length; x++) {
                    for (int y = 0; y < playerGrid[x].length; y++) {
                        if (playerShips[x][y] == "X") {
                            this.playerShips[x][y] = "2";
                        }
                    }
                }
                break;

            case 2:

                for (int x = 0; x < playerGrid.length; x++) {
                    for (int y = 0; y < playerGrid[x].length; y++) {
                        if (playerShips[x][y] == "X") {
                            this.playerShips[x][y] = "3";
                        }
                    }
                }
                break;

            case 3:

                for (int x = 0; x < playerGrid.length; x++) {
                    for (int y = 0; y < playerGrid[x].length; y++) {
                        if (playerShips[x][y] == "X") {
                            this.playerShips[x][y] = "4";
                        }
                    }
                }
                break;

            case 4:

                for (int x = 0; x < playerGrid.length; x++) {
                    for (int y = 0; y < playerGrid[x].length; y++) {
                        if (playerShips[x][y] == "X") {
                            this.playerShips[x][y] = "5";
                        }
                    }
                }
                break;
        }

    }

    public void updateComputerShips(JButton[][] computerGrid, int computerShipNumber) {

        switch (computerShipNumber) {

            case 0:
                for (int x = 0; x < computerGrid.length; x++) {
                    for (int y = 0; y < computerGrid[x].length; y++) {
                        if (computerShips[x][y] == "X") {
                            this.computerShips[x][y] = "1";
                        }
                    }
                }
                break;

            case 1:

                for (int x = 0; x < computerGrid.length; x++) {
                    for (int y = 0; y < computerGrid[x].length; y++) {
                        if (computerShips[x][y] == "X") {
                            this.computerShips[x][y] = "2";
                        }
                    }
                }
                break;

            case 2:

                for (int x = 0; x < computerGrid.length; x++) {
                    for (int y = 0; y < computerGrid[x].length; y++) {
                        if (computerShips[x][y] == "X") {
                            this.computerShips[x][y] = "3";
                        }
                    }
                }
                break;

            case 3:

                for (int x = 0; x < computerGrid.length; x++) {
                    for (int y = 0; y < computerGrid[x].length; y++) {
                        if (computerShips[x][y] == "X") {
                            this.computerShips[x][y] = "4";
                        }
                    }
                }
                break;

            case 4:

                for (int x = 0; x < computerGrid.length; x++) {
                    for (int y = 0; y < computerGrid[x].length; y++) {
                        if (computerShips[x][y] == "X") {
                            this.computerShips[x][y] = "5";
                        }
                    }
                }
                break;
        }

    }

    public void hasShipSunk(String shipHit) {

        if (currentTurn == "Player") {

            for (int x = 0; x < computerShips.length; x++) {
                for (int y = 0; y < computerShips[x].length; y++) {
                    if (computerShips[x][y].equals(shipHit)) {
                        isSunk = false;
                        break;
                    } else {
                        isSunk = true;
                    }
                }

                if (isSunk == false) {
                    break;
                }
            }

            if (isSunk == true) {
                computerRemainingShips--;
            }

        }

        else {

            for (int x = 0; x < playerShips.length; x++) {
                for (int y = 0; y < playerShips[x].length; y++) {
                    if (playerShips[x][y].equals(shipHit)) {
                        isSunk = false;
                        break;
                    } else {
                        isSunk = true;
                    }
                }

                if (isSunk == false) {
                    break;
                }
            }

            if (isSunk == true) {
                playerRemainingShips--;
            }
        }

    }

    // TODO if we have time add a time highscore

    /**
     * @author Marwa
     *         A method that restarts all of the values
     */
    public void restart() {

        currentRound++;

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
        this.view.setPanelState(PANEL_STATES.TITLE);

        this.updateView();

    }

    /**
     * Marwa and Mohib
     * 
     * @param rowClicked the row that the button was clicked
     * @param colClicked the column that the button was clicked
     */
    public void playerShipTurn(int rowClicked, int colClicked) {

        // If the button clicked ins't an empty space or a missed ship
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
        this.playerRowGuessed = rowClicked;
        this.playerColGuessed = colClicked;
        this.numPlayerGuesses++; // Increase the number of guesses the player did
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

        this.compRowGuessed = (int) (Math.random() * (playerShips.length));
        this.compColGuessed = (int) (Math.random() * (playerShips.length));

        Random random = new Random();
        boolean guessHorizontal = true;

        if (!this.getShipSunk()) {
            guessHorizontal = this.computerGuessHorizontal(compRowGuessed, compColGuessed);
        }

        // If the ship hasn't been hit yet and the last guess did not hit sink a shit
        if (!this.getCompHitStatus() || this.getShipSunk()) {
            // Make a random guess
            this.compRowGuessed = (int) (Math.random() * (playerShips.length));
            this.compColGuessed = (int) (Math.random() * (playerShips.length));

        } else { // Otherwise if the ship has been hit before

            if (guessHorizontal == true) {
                this.compColGuessed++; // update the column but keep the row the same
            } else { // otherwise if guessing vertical
                this.compRowGuessed++; // increment the row but keep the column the same
            }

        }

        // If the coordinate guessed is not empty or a missed ship
        if (playerShips[compRowGuessed][compColGuessed] != "O") {
            if (this.computerGuesses[compRowGuessed][compColGuessed] != "!") {

                this.hasCompHit = true; // Then the computer hit the ship
                String shipHit = playerShips[compRowGuessed][compColGuessed]; // Create a string of the ship that was
                                                                              // hit to pass into the method
                this.computerHits++;
                this.playerShips[compRowGuessed][compColGuessed] = "O";
                this.hasShipSunk(shipHit); // Run this method to check if the ship has been sunken

            }

        } else {
            this.hasCompHit = false;
        }

        this.numCompGuesses++;
        this.computerGuesses[compRowGuessed][compColGuessed] = "!";
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
        if (playerShips[row + 1][col].equals("X")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Mohib
     * A method to check the status of the game
     */
    public void checkGameStatus() {

        // If the theres no ships remaining for the computer
        if (computerRemainingShips == 0) {
            this.winner = this.getPlayerName(); // The winner is the player
            this.isGameEnded = true;

            // if the player hits a new highscore
            if (this.getNumPlayerGuesses() > this.getPlayerGuessHighScore()) {
                this.setPlayerGuessHighScore(this.getNumPlayerGuesses()); // Set the highscore of the player
            }

            // If the player has no ships left
        } else if (playerRemainingShips == 0) {
            this.winner = "Computer"; // the winner is the computer
            this.isGameEnded = true;
        }
    }

    /**
     * Marwa
     * A method to disable a grid
     * 
     * @param grid to the disabled
     */
    public void disableGrid(JButton[][] grid) {

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

        outputFile.println("\nNumber of Guesses (Player): " + this.getPlayerHits());
        outputFile.println("Number of Guesses (Computer): " + this.getComputerHits());
        outputFile.println("Timer End: " + this.view.timePassed() + " seconds");

        outputFile.println("Player Highscore list: ");
        this.sortPlayerGuessHighScore(playerHighScores);
        this.printArrayFile(outputFile, playerHighScores);

        outputFile.close();

    }

    // TODO ask Mr. Burns if the sorting it good

    /**
     * @author Marwa
     *         Sort using the insert sort algorithm
     * @param array to be sorted
     */
    public void sortPlayerGuessHighScore(int[] array) {

        for (int x = 1; x < array.length; x++) {
            int current = array[x];
            int position = x;

            while (position > 0 && array[position - 1] > current) {
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
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                outputFile.println(array[i]);
            }
        }
    }

    /**
     * @author Marwa
     *         A method to end the game
     */
    public void end() {
        this.view.setPanelState(PANEL_STATES.END); // switch the enum state
        this.updateView();

    }

    // ------------------------------------------ ACCESSOR METHODS
    // ------------------------------------------

    public String getWinner() {
        return this.winner;
    }

    public boolean getShipSunk() {
        return this.isSunk;
    }

    public int getNumPlayerGuesses() {
        return this.numPlayerGuesses;
    }

    public int getNumCompGuesses() {
        return this.numCompGuesses;
    }

    public int getPlayerRowGuessed() {
        return this.playerRowGuessed;
    }

    public int getPlayerColGuessed() {
        return this.playerColGuessed;
    }

    public int getCompRowGuessed() {
        return this.compRowGuessed;
    }

    public int getCompColGuessed() {
        return this.compColGuessed;
    }

    public int getShipNum() {
        return this.shipNum;
    }

    public boolean getNewGame() {
        return this.isNewGame;
    }

    public boolean validateGuess() {
        return true;
    }

    public boolean getValidPosition() {
        return isValidPosition;
    }

    public int getPlayerHits() {
        return this.playerHits;
    }

    public int getComputerHits() {
        return this.computerHits;
    }

    public int getPlayerShipsSunk() {
        return this.playerShipsSunk;
    }

    public int getComputerShipsSunk() {
        return this.computerShipsSunk;
    }

    public int getPlayerRemainingShips() {
        return this.playerRemainingShips;
    }

    public int getComputerRemainingShips() {
        return this.computerRemainingShips;
    }

    public int getPlayerGuessHighScore() {
        return this.playerGuessHighScore;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public String[][] getPlayerGuesses() {
        return this.playerGuesses;
    }

    public String[][] getComputerGuesses() {
        return this.computerGuesses;
    }

    public String[][] getPlayerShips() {
        return this.playerShips;
    }

    public String[][] getComputerShips() {
        return this.computerShips;
    }

    public boolean getGameStatus() {
        return this.isGameEnded;
    }

    public String getGameTurn() {
        return this.currentTurn;
    }

    public boolean getPlayerHitStatus() {
        return this.hasPlayerHit;
    }

    public boolean getCompHitStatus() {
        return this.hasCompHit;
    }

    public boolean getDeploymentStatus() {
        return this.isDeploymentFinished;
    }

    public int getGridSize() {
        return this.getPlayerGuesses().length;
    }

    // ------------------------------------------ SETTOR METHODS
    // ------------------------------------------

    /**
     * @author Marwa
     *         A method to set the high score of the player
     * @param score to add
     */
    public void setPlayerGuessHighScore(int score) {
        this.playerGuessHighScore = score;
        highScoreIndex++;
        playerHighScores[highScoreIndex] = score; // add to the array

    }

    public void setPlayerRemainingShips(int num) {
        this.playerRemainingShips = num;
    }

    public void setComputerRemainingShips(int num) {
        this.computerRemainingShips = num;
    }

    public void setGameStatus(boolean status) {
        this.isGameEnded = status;
    }

    public void setPlayerHitStatus(boolean status) {
        this.hasPlayerHit = status;
    }

    public void setCompHitStatus(boolean status) {
        this.hasCompHit = status;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public void setGameTurn(String turn) {
        this.currentTurn = turn;
    }

    public void setPlayerShipsSunk(int num) {
        this.playerShipsSunk = num;
    }

    public void setComputerShipsSunk(int num) {
        this.computerShipsSunk = num;
    }

    public int getCurrentRound() {
        return this.currentRound;
    }

}
