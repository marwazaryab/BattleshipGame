package Game.GameObjects;

import java.util.*;

import Game.View.BattleshipGUI;

public class BattleshipGame extends Object{
    
    private BattleshipGUI view;
    private Timer time;
    private int playerShipsSunk;
    private int computerShipsSunk;
    private int computerRemainingShips;
    private int playerRemainingShips;
    private int playerGuessHighScore;
    private double playerTimeHighScore;
    private String [][] playerGuesses;
    private String [][] computerGuesses;
    private String [][] playerShips;
    private String [][] computerShips;
    private String currentTurn;
    private String playerName;
    private boolean isHit; //double check use of said variable
    private boolean isGameEnded;

    public BattleshipGame() {

    }
    
    public void setGUI (BattleshipGUI gui) {

    }

    public void updateView() {

    }

    public void startGame() {

    }

    public void deployShips(){

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

    public void printResults(){

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
