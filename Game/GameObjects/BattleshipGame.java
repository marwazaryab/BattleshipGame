package Game.GameObjects;

import java.util.*;

import javax.swing.JButton;

import Game.View.BattleshipGUI;
import Game.View.BattleshipGUI.PANEL_STATES;

public class BattleshipGame extends Object {

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
    private int[] playerShipLength = new int[] { 5, 3, 3, 2, 1 };
    private int[] computerShipLength = new int[] { 5, 3, 3, 2, 1 };
    private boolean isGameEnded;
    private boolean isHit;
    private boolean isCompShipHorizontal;
    private boolean isComputerDeploy;
    private boolean isDeploymentFinished;
    private boolean isValidPosition;
    private boolean isNewGame;
    private Random randomDirection;

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
    }

    public void setGUI(BattleshipGUI gui) {
        this.view = gui;
    }

    public void updateView() {
        this.view.update();
    }

    public void startGame() {

    }

    public void createGrid(String difficulty) {

        switch (difficulty) {

            case "Easy":
                this.view.setPanelState(PANEL_STATES.GAME);
                playerGuesses = new String[15][15];
                computerGuesses = new String[15][15];
                playerShips = new String[15][15];
                computerShips = new String[15][15];
                this.view.setGridSize(10);
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
                            playerGrid[rowClicked][columnClicked+i].setText("X");
                        }
                        this.isValidPosition = true;
                        shipNum++;
    
                    } else {
                        this.isValidPosition = false;
                    }
    
                } else if (isHorizontal == false) {
    
                    if (this.isValidPlacement(isComputerDeploy, rowClicked, columnClicked, isHorizontal, playerGrid)) {
    
                        for (int i = 0; i < playerShipLength[shipNum]; i++) {
                            
                            playerGrid[rowClicked+i][columnClicked].setText("X");
                        }
                        this.isValidPosition = true;
                        shipNum++;
    
                    } else {
                        this.isValidPosition = false;
                    }
    
                }

            this.updateView();
        }

        else {
            updatePlayerShips(playerGrid);
            deployShipsComputer(computerGrid);
        }

    }

    public void deployShipsComputer(JButton[][] computerGrid) {

        this.isComputerDeploy = true;

        System.out.println(isCompShipHorizontal);

        while (computerShipNum < computerShipLength.length) {

            this.isCompShipHorizontal = randomDirection.nextBoolean();
            computerShipRow = (int) (Math.random() * (computerGrid.length));
            computerShipCol = (int) (Math.random() * (computerGrid[0].length));

            if (this.isCompShipHorizontal == true) {

                if (this.isValidPlacement(isComputerDeploy, computerShipRow, computerShipCol, isCompShipHorizontal,
                        computerGrid)) {

                    for (int i = 0; i < computerShipLength[computerShipNum]; i++) {
                        // computerGrid[computerShipRow][computerShipCol+i].setText("X");
                        computerShips[computerShipRow][computerShipCol + i] = "X";
                    }
                    computerShipNum++;

                }

            } else if (this.isCompShipHorizontal == false) {

                if (this.isValidPlacement(isComputerDeploy, computerShipRow, computerShipCol, isCompShipHorizontal,
                        computerGrid)) {

                    for (int i = 0; i < computerShipLength[computerShipNum]; i++) {

                        // computerGrid[computerShipRow+i][computerShipCol].setText("X");
                        computerShips[computerShipRow + i][computerShipCol] = "X";

                    }
                    computerShipNum++;

                }

            }
        }

        updateComputerShips(computerGrid);
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

    public void updatePlayerShips(JButton[][] playerGrid) {

        for (int x = 0; x < playerGrid.length; x++) {
            for (int y = 0; y < playerGrid[x].length; y++) {
                if (playerGrid[x][y].getText() == "X") {
                    this.playerShips[x][y] = "X";
                }
            }
        }
    }

    public void updateComputerShips(JButton[][] computerGrid) {

        for (int x = 0; x < computerGrid.length; x++) {
            for (int y = 0; y < computerGrid[x].length; y++) {
                if (computerGrid[x][y].getText() == "X") {
                    this.computerShips[x][y] = "X";
                }
            }
        }

    }

    //TODO fill this out
    public void isShipSunk(){
      

    }

    public void setPlayerRemainingShips(int num) {
        this.playerRemainingShips = num;
    }

    public void setComputerRemainingShips(int num) {
        this.computerRemainingShips = num;
    }

    public void restart() {
        this.view.isRestart = true;
        this.setPlayerName(null);
        this.setPlayerShipsSunk(0);
        this.setComputerShipsSunk(0);
        this.setPlayerRemainingShips(0);
        this.setComputerRemainingShips(0);
        this.setPlayerGuessHighScore(0);
        this.setPlayerTimeHighScore(0);

        setGridValues();

        this.view.setPanelState(PANEL_STATES.TITLE);
        this.updateView();

    }

    public void playerShipTurn(int rowClicked, int colClicked) {

        if (computerShips[rowClicked][colClicked] == "X") {
            this.isHit = true;
            this.playerHits++;
            System.out.println("Hit a computer ship");

        } else {
            this.isHit = false;
        }

        this.playerGuesses[rowClicked][colClicked] = "!";
        this.playerRowGuessed = rowClicked;
        this.playerColGuessed = colClicked;
        this.numPlayerGuesses++;
        this.updateView();
        this.playerRowGuessed = 0;
        this.playerColGuessed = 0;
        this.currentTurn = "Computer";
    }

    public void computerShipTurn() {

        this.compRowGuessed = (int) (Math.random() * (playerShips.length));
        this.compColGuessed = (int) (Math.random() * (playerShips.length));

        if (playerShips[compRowGuessed][compColGuessed] == "X") {
            this.isHit = true;
            this.computerHits++;
        } else {
            this.isHit = false;
        }

        this.numCompGuesses++;
        this.computerGuesses[compRowGuessed][compColGuessed] = "!";
        this.updateView();
        this.currentTurn = "Player";
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

    public void endGame() {

    }

    public void newGame() {

    }

    public void printResults() {

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

}
