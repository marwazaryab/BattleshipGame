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
    private BattleshipGUI view;
    private Timer time;
    private int playerShipsSunk;
    private int computerShipsSunk;
    private int computerRemainingShips;
    private int playerRemainingShips;
    private int playerGuessHighScore;
    private int computerShipNum;
    private int computerShipRow;
    private int computerShipCol;
    private int shipNum;
    private int playerRowGuessed;
    private int playerColGuessed;
    private int compRowGuessed;
    private int compColGuessed;
    private int playerHits;
    private int computerHits;
    private int numPlayerGuesses;
    private int numCompGuesses;
    private double playerTimeHighScore;
    private String[][] playerGuesses;
    private String[][] computerGuesses;
    private String[][] playerShips;
    private String[][] computerShips;
    private String currentTurn;
    private String playerName;
    private String winner = "";
    private int[] playerShipLength = new int[] { 5, 3, 3, 2, 1 };
    private int[] computerShipLength = new int[] { 5, 3, 3, 2, 1 };
    private boolean isGameEnded;
    private boolean isHit;
    private boolean isCompShipHorizontal;
    private boolean isComputerDeploy;
    private boolean isDeploymentFinished;
    private boolean isValidPosition;
    private boolean isNewGame;
    private boolean isSunk;
    private Random randomDirection;
    private int currentRound;
    private int[] playerHighScores = new int[100];
    private int highScoreIndex;

    public BattleshipGame() {
        super();
        this.shipNum = 0;
        this.playerShipsSunk = 0;
        this.computerShipsSunk = 0;
        this.computerRemainingShips = 0;
        this.playerRemainingShips = 0;
        this.playerGuessHighScore = 0;
        this.playerTimeHighScore = 0;
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

    public void setGUI(BattleshipGUI gui) {
        this.view = gui;
    }

    public void updateView() {
        this.view.update();
    }

    public void createGrid(String difficulty) {

        switch (difficulty) {

            case "Easy":
                this.view.setPanelState(PANEL_STATES.GAME);
                playerGuesses = new String[15][15];
                computerGuesses = new String[15][15];
                playerShips = new String[15][15];
                computerShips = new String[15][15];
                this.view.setGridSize(15);
                this.isNewGame = true;
                this.updateView();
                this.isNewGame = false;

                break;

            case "Medium":
                this.view.setPanelState(PANEL_STATES.GAME);

                playerGuesses = new String[20][20];
                computerGuesses = new String[20][20];
                playerShips = new String[20][20];
                computerShips = new String[20][20];
                this.view.setGridSize(20);
                this.isNewGame = true;
                this.updateView();
                this.isNewGame = false;

                break;

            case "Hard":
                view.setPanelState(PANEL_STATES.GAME);

                playerGuesses = new String[25][25];
                computerGuesses = new String[25][25];
                playerShips = new String[25][25];
                computerShips = new String[25][25];
                this.view.setGridSize(25);
                this.isNewGame = true;
                this.updateView();
                this.isNewGame = false;
                break;

            default:

                break;
        }

        setGridValues();
    }

    public void setGridValues() {

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

    public void deployPlayerShips(JButton[][] playerGrid, JButton[][] computerGrid, int rowClicked, int columnClicked,
            boolean isHorizontal) {

        if (shipNum < playerShipLength.length) {

            if (isHorizontal == true) {

                if (this.isValidPlacement(isComputerDeploy, rowClicked, columnClicked, isHorizontal, playerGrid)) {

                    for (int i = 0; i < playerShipLength[shipNum]; i++) {
                        playerGrid[rowClicked][columnClicked + i].setText("X");
                        playerShips[rowClicked][columnClicked + i] = "X";
                    }
                    this.updatePlayerShips(playerGrid, shipNum);
                    this.isValidPosition = true;
                    shipNum++;

                } else {
                    this.isValidPosition = false;
                }

            } else if (isHorizontal == false) {

                if (this.isValidPlacement(isComputerDeploy, rowClicked, columnClicked, isHorizontal, playerGrid)) {

                    for (int i = 0; i < playerShipLength[shipNum]; i++) {

                        playerGrid[rowClicked + i][columnClicked].setText("X");
                        playerShips[rowClicked + i][columnClicked] = "X";
                    }
                    this.updatePlayerShips(playerGrid, shipNum);
                    this.isValidPosition = true;
                    shipNum++;

                }

                else {
                    this.isValidPosition = false;
                }

            }

            this.updateView();
        }

        else {
            deployShipsComputer(computerGrid);
        }

    }

    public void deployShipsComputer(JButton[][] computerGrid) {

        this.isComputerDeploy = true;

        while (computerShipNum < computerShipLength.length) {

            this.isCompShipHorizontal = randomDirection.nextBoolean();
            computerShipRow = (int) (Math.random() * (computerGrid.length));
            computerShipCol = (int) (Math.random() * (computerGrid[0].length));

            if (this.isCompShipHorizontal == true) {

                if (this.isValidPlacement(isComputerDeploy, computerShipRow, computerShipCol, isCompShipHorizontal,
                        computerGrid)) {

                    for (int i = 0; i < computerShipLength[computerShipNum]; i++) {
                        computerGrid[computerShipRow][computerShipCol + i].setText("X");
                        computerShips[computerShipRow][computerShipCol + i] = "X";
                    }

                    updateComputerShips(computerGrid, computerShipNum);
                    computerShipNum++;

                }

            } else if (this.isCompShipHorizontal == false) {

                if (this.isValidPlacement(isComputerDeploy, computerShipRow, computerShipCol, isCompShipHorizontal,
                        computerGrid)) {

                    for (int i = 0; i < computerShipLength[computerShipNum]; i++) {

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

    public boolean isValidPlacement(boolean isComp, int row, int col, boolean isHorizontal, JButton[][] grid) {

        if (isComp) {

            if (isHorizontal == true) {
                if (col + computerShipLength[computerShipNum] > grid.length) {
                    return false;
                } else if (grid[row][col].getText().equals("X")) {
                    return false;
                } else {
                    for (int x = col; x < (col + computerShipLength[computerShipNum]); x++) {
                        if (grid[row][x].getText().equals("X")) {
                            return false;
                        }
                    }
                }

            } else if (isHorizontal == false) {
                if (row + computerShipLength[computerShipNum] > grid[0].length) {
                    return false;
                } else if (grid[row][col].getText().equals("X")) {
                    return false;
                } else {
                    for (int x = row; x < (row + computerShipLength[computerShipNum]); x++) {
                        if (grid[x][col].getText() == "X") {
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        else {

            if (isHorizontal == true) {
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

        for (int x = 0; x < playerGrid.length; x++) {
            for (int y = 0; y < playerGrid[x].length; y++) {
                System.out.print(playerShips[x][y]);
            }
            System.out.println();
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

        for (int x = 0; x < computerGrid.length; x++) {
            for (int y = 0; y < computerGrid[x].length; y++) {
                System.out.print(computerShips[x][y]);
            }
            System.out.println();
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
            System.out.println(computerShipsSunk);
            System.out.println(isSunk);

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
            System.out.println(computerShipsSunk);
            System.out.println(isSunk);
        }

    }

    public void setPlayerRemainingShips(int num) {
        this.playerRemainingShips = num;
    }

    public void setComputerRemainingShips(int num) {
        this.computerRemainingShips = num;
    }

    public void restart() {

        currentRound++;

        this.view.isRestart = true;
        this.setPlayerName(null);
        this.shipNum = 0;
        this.playerShipsSunk = 0;
        this.computerShipsSunk = 0;
        this.computerRemainingShips = 0;
        this.playerRemainingShips = 0;
        this.playerGuessHighScore = 0;
        this.playerTimeHighScore = 0;
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

    public void playerShipTurn(int rowClicked, int colClicked) {

        if (computerShips[rowClicked][colClicked] != "O") {
            this.isHit = true;
            if (this.playerGuesses[rowClicked][colClicked] != "!") {

                String shipHit = computerShips[rowClicked][colClicked];
                this.playerHits++;
                this.computerShips[rowClicked][colClicked] = "O";
                this.hasShipSunk(shipHit);
            }
            System.out.println("Hit a computer ship");

        } else {
            this.isHit = false;
        }

        for (int x = 0; x < computerShips.length; x++) {
            for (int y = 0; y < computerShips[x].length; y++) {
                System.out.print(computerShips[x][y]);
            }
            System.out.println();
        }

        this.playerGuesses[rowClicked][colClicked] = "!";
        this.playerRowGuessed = rowClicked;
        this.playerColGuessed = colClicked;
        this.numPlayerGuesses++;
        checkGameStatus();
        this.updateView();
        this.playerRowGuessed = 0;
        this.playerColGuessed = 0;
        this.currentTurn = "Computer";
    }

    public void computerShipTurn() {

        this.compRowGuessed = (int) (Math.random() * (playerShips.length));
        this.compColGuessed = (int) (Math.random() * (playerShips.length));

        if (playerShips[compRowGuessed][compColGuessed] != "O") {
            this.isHit = true;
            if (this.computerGuesses[compRowGuessed][compColGuessed] != "!") {

                String shipHit = playerShips[compRowGuessed][compColGuessed];
                this.computerHits++;
                this.playerShips[compRowGuessed][compColGuessed] = "O";
                this.hasShipSunk(shipHit);
            }
        } else {
            this.isHit = false;
        }

        this.numCompGuesses++;
        this.computerGuesses[compRowGuessed][compColGuessed] = "!";
        checkGameStatus();
        this.updateView();
        this.currentTurn = "Player";
    }

    public void checkGameStatus() {

        if (computerRemainingShips == 0) {
            this.winner = this.getPlayerName();
            this.isGameEnded = true;
        } else if (playerRemainingShips == 0) {
            this.winner = "Computer";
            this.isGameEnded = true;
        }
    }

    public void disableGrid(JButton[][] grid) {

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                grid[x][y].setEnabled(false);
            }
        }
    }

    public void startTimer() {

    }

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

    public double getPlayerTimeHighScore() {
        return this.playerTimeHighScore;
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

    public boolean getHitStatus() {
        return this.isHit;
    }

    public boolean getDeploymentStatus() {
        return this.isDeploymentFinished;
    }

    public void setGameStatus(boolean status) {
        this.isGameEnded = status;
    }

    public void setHitStatus(boolean status) {
        this.isHit = status;
    }

    public void setPlayerGuessHighScore(int score) {
        this.playerGuessHighScore = score;
        highScoreIndex++;
        playerHighScores[highScoreIndex] = score;

    }

    public void setPlayerTimeHighScore(double score) {
        this.playerTimeHighScore = score;
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

    public void outputStats() {

        // TODO use format tools

        PrintWriter outputFile = Prompt.getPrintWriter();
        outputFile.println("---------------------------- GAME ANALYSIS ----------------------------");

        outputFile.println("Current Round: " + this.getCurrentRound());

        if (this.getWinner().equals(this.getPlayerName())) {
            outputFile.println("WINNER STATUS: PLAYER 1 WON");
        } else {
            outputFile.println("WINNER STATUS: COMPUTER WON");
        }

        outputFile.println("Number of Guesses (Player): " + this.getPlayerHits());
        outputFile.println("Number of Guesses (Computer): " + this.getComputerHits());
        outputFile.println("Timer End: " + this.view.timePassed());


        outputFile.println("Player Highscore: " + this.playerGuessHighScore);
        this.sortPlayerGuessHighScore(playerHighScores);
        this.printArrayFile(outputFile, playerHighScores);

        
        outputFile.println("Number of guesses Computer: " + this.getComputerHits());
        this.sortPlayerGuessHighScore(this.playerHighScores);

        outputFile.close();

    }

    /**
     * Sort using the insert sort algorithm
     * 
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

    public void printArrayFile(PrintWriter outputFile, int[]array){
        for(int i = 0; i < array.length; i++){
            if(array[i] != 0){
                outputFile.println(array[i]);
            }
        }
    }

    public void end() {
        this.view.setPanelState(PANEL_STATES.END);
        this.updateView();

    }

}
