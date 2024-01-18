package Game.GameObjects;

import java.util.*;

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
    private String currentTurn;
    private String playerName;
    private boolean isGuessed; // double check use of said variable
    private boolean isGameEnded;
    private boolean isHit;
    private int[] ships = new int[] { 5, 5, 3, 3, 3, 2, 2, 1, 1, 1 };
    private int NUM_SHIPS = 10;
    private int gridSize = 0;

    public BattleshipGame() {

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

    public void deployShips() {

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

    public int[] getShips() {
        return this.ships;
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
