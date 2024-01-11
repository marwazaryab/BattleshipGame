package Game.View;

import javax.swing.*;

import Game.Controllers.ButtonController;
import Game.Controllers.ShipController;
import Game.GameObjects.BattleshipGame;
import Game.Images.BattleshipImageComponent;

public class BattleshipGUI extends JPanel{
    
    private BattleshipGame model;
    private BattleshipImageComponent image;
    private ButtonController bController;
    private ShipController sController;
    private JPanel statsScreen = new JPanel();
    private JPanel difficultyScreen = new JPanel();
    private JPanel gamePaenl = new JPanel();
    private JButton exit = new JButton("Exit");
    private JButton start = new JButton("Start");
    private JButton stats = new JButton("Stats");
    private JTextField nameField = new JTextField();
    private JLabel name;
    private JLabel currentTurn;
    private JLabel computerShipsSunk;
    private JLabel playerShipsSunk;
    private JLabel playerGuess;
    private JLabel computerGuess;
    private JLabel timer;
    private JButton[][] playerGrid;
    private JButton[][] computerGrid;

    public BattleshipGUI (BattleshipGame model) {

    }

    public void titleView() {

    }

    public void statsView() {

    }

    public void gameView() {

    }

    public void update() {

    }

    public void registerControllers() {

    }
}
