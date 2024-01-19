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
    private double playerTimeHighScore;
    private String[][] playerGuesses;
    private String[][] computerGuesses;
    private String[][] playerShips;
    private String[][] computerShips;
    private JButton[][] playerGrid;
    private JButton[][] computerGrid;
    private String currentTurn;
    private String playerName;
    private boolean isGuessed; // double check use of said variable
    private boolean isGameEnded;
    private boolean isHit;
    private int[] playerShipLength = new int[] { 5, 5, 3, 3, 3, 2, 2, 1, 1, 1 };
    private int[] computerShipLength = new int[] { 5, 5, 3, 3, 3, 2, 2, 1, 1, 1 };
    private int computerShipNum;
    private int computerRow;
    private int computerCol;
    private int shipNum;
    private boolean isCompShipHorizontal;
    private boolean isPlayerShipHorizontal;
    private boolean isComputerDeploy;
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
                playerGuesses = new String[10][10];
                computerGuesses = new String[10][10];
                playerShips = new String[10][10];
                computerShips = new String[10][10];
                this.view.setGridSize(10);
                this.updateView();
                break;

            case "Medium":
                this.view.setPanelState(PANEL_STATES.GAME);

                playerGuesses = new String[20][20];
                computerGuesses = new String[20][20];
                playerShips = new String[20][20];
                computerShips = new String[20][20];
                this.view.setGridSize(20);
                this.updateView();

                break;

            case "Hard":
                view.setPanelState(PANEL_STATES.GAME);

                playerGuesses = new String[30][30];
                computerGuesses = new String[30][30];
                playerShips = new String[30][30];
                computerShips = new String[30][30];
                this.view.setGridSize(30);
                this.updateView();

                break;

            default:

                break;

            
        }
    }

    public void deployPlayerShips(JButton [][] playerGrid, JButton[][] computerGrid, int rowClicked, int columnClicked, boolean isHorizontal, 
            String alignment) {

            if(alignment.equalsIgnoreCase("U")) {
                this.isPlayerShipHorizontal = false;
            }
            else{
                this.isPlayerShipHorizontal = true;
            }

            if (shipNum != 5) {
                
                if (isHorizontal == true) {
    
                    if (this.isValidPlacement(isComputerDeploy, rowClicked, columnClicked, isHorizontal, playerGrid)) {
    
                        for (int i = 0; i < playerShipLength[shipNum]; i++) {
                            playerGrid[rowClicked][columnClicked+i].setText("X");
                        }
                        shipNum++;
    
                    } else {
                        // this.alignmentField.setText("That is not a valid placement!");
                    }
    
                } else if (this.isPlayerShipHorizontal == false) {
    
                    if (this.isValidPlacement(isComputerDeploy, rowClicked, columnClicked, isHorizontal, playerGrid)) {
    
                        for (int i = 0; i < playerShipLength[shipNum]; i++) {
    
                            playerGrid[rowClicked+i][columnClicked].setText("X");
                        }
                        shipNum++;
    
                    } else {
                        // this.alignmentField.setText("That is not a valid placement!");
                    }
    
                }
    
            }
    
            else {
                System.out.println("Ships deployed!");
                deployShipsComputer();
            }
            
    

    }

    public void deployShipsComputer() {

        this.isComputerDeploy = true;

        System.out.println(isCompShipHorizontal);

        while (computerShipNum != 5) {

            this.isCompShipHorizontal = randomDirection.nextBoolean();
            computerRow = (int) (Math.random() * (computerGrid.length));
            computerCol = (int) (Math.random() * (computerGrid[0].length));

            if (this.isCompShipHorizontal == true) {

                if (this.isValidPlacement(isComputerDeploy, computerRow, computerCol, isCompShipHorizontal, computerGrid)) {

                    for (int i = 0; i < computerShipLength[computerShipNum]; i++) {
                        computerGrid[computerRow][computerCol+i].setText("X");
                    }
                    computerShipNum++;

                }

            } else if (this.isCompShipHorizontal == false) {

                if (this.isValidPlacement(isComputerDeploy, computerRow, computerCol, isCompShipHorizontal, computerGrid)) {

                    for (int i = 0; i < computerShipLength[computerShipNum]; i++) {

                        computerGrid[computerRow+i][computerCol].setText("X");
                    }
                    computerShipNum++;

                } 

            }
        }
    }

    public boolean isValidPlacement(boolean isComp, int row, int col, boolean isHorizontal, JButton[][] grid) {

        if (isComp) {

            if (isHorizontal == true) {
                if (col + computerShipLength[computerShipNum] > grid.length) {
                    return false;
                }
                else if (grid[row][col].getText().equals("X")){
                    return false;
                }
                else {
                    for (int x = col; x < (col + computerShipLength[computerShipNum]); x++){
                        if (grid[row][x].getText().equals("X")) {
                            return false;
                        }
                    }
            }

            } else if (isHorizontal == false) {
                if (row + computerShipLength[computerShipNum] > grid[0].length) {
                    return false;
                }
                else if (grid[row][col].getText().equals("X")){
                    return false;
                }
                else {
                    for (int x = row; x < (row + computerShipLength[computerShipNum]); x++){
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
                }
                else if (grid[row][col].getText().equals("X")){
                    return false;
                }
                else {
                    for (int x = col; x < (col + playerShipLength[shipNum]); x++){
                        if (grid[row][x].getText().equals("X")) {
                            return false;
                        }
                    }
            }

            } else if (isHorizontal == false) {

                if (row + playerShipLength[shipNum] > grid.length) {
                    return false;
                }
                else if (playerGrid[row][col].getText().equals("X")){
                    return false;
                }
                else {
                    for (int x = row; x < (row + playerShipLength[shipNum]); x++){
                        if (playerGrid[x][col].getText() == "X") {
                            return false;
                        }
                }
            }
        }
            return true;

        }

    }

    public void playerShipTurn() {

    }

    public void computerShipTurn() {

    }

    public void startTimer() {

    }

    public void endGame() {

    }

    public void newGame() {

    }

    public void printResults() {

    }

    public boolean validateGuess() {
        return true;
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

}
