package Game.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

import Game.Controllers.ButtonController;
import Game.Controllers.ShipController;
import Game.GameObjects.BattleshipGame;
import Game.Images.BattleshipImageComponent;

public class BattleshipGUI extends JPanel {

    private BattleshipGame model;
    private BattleshipImageComponent battleship = new BattleshipImageComponent("Battleship.png");
    private ButtonController bController;
    private ShipController sController;
    private JPanel statsScreen = new JPanel();
    private JPanel difficultyScreen = new JPanel();
    private JPanel gamePanel = new JPanel();
    private JButton exit = new JButton("Exit");
    private JButton easy = new JButton("Easy");
    private JButton medium = new JButton("Medium");
    private JButton hard = new JButton("Hard");
    private JButton stats = new JButton("Stats");
    private JTextField nameField = new JTextField();
    private JLabel name = new JLabel("Enter your Name: ");
    private JLabel currentTurn;
    private JLabel computerShipsSunk;
    private JLabel playerShipsSunk;
    private JLabel playerGuess;
    private JLabel computerGuess;
    private JLabel timer;
    private JButton[][] playerGrid;
    private JButton[][] computerGrid;
    private PANEL_STATES currentState;

    public enum PANEL_STATES {
        TITLE,
        GAME,
        STATS
    }

    public BattleshipGUI(BattleshipGame model) {
        super();
        this.model = model;
        this.model.setGUI(this);
        this.update();
        this.registerControllers();
        this.setPanelState(PANEL_STATES.TITLE);
    }

    public void titleView() {
        JLabel title = new JLabel("BATTLESHIP");
        JPanel buttonsPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel namePanel = new JPanel();

        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));

        title.setFont(new Font("Montserrat", Font.BOLD, 30));
        easy.setFont(new Font("Montserrat", Font.BOLD, 14));
        medium.setFont(new Font("Montserrat", Font.BOLD, 14));
        hard.setFont(new Font("Montserrat", Font.BOLD, 14));
        exit.setFont(new Font("Montserrat", Font.BOLD, 14));

        buttonsPanel.add(easy);
        buttonsPanel.add(medium);
        buttonsPanel.add(hard);
        buttonsPanel.add(exit);

        namePanel.add(name);
        namePanel.add(nameField);

        middlePanel.add(battleship);
   

        this.setPreferredSize(new Dimension(300, 300));
        this.setLayout(new BorderLayout());
        this.setBackground(getBackground());
        this.add(buttonsPanel, BorderLayout.SOUTH);
        this.add(title, BorderLayout.NORTH);
        this.add(middlePanel, BorderLayout.CENTER);

    }

    public void statsView() {

    }

    public void gameView() {

    }

    public void update() {
        this.titleView();

    }

    public void registerControllers() {

    }

    public void setPanelState(PANEL_STATES state) {
        this.currentState = state;

    }
}
