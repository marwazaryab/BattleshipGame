package Game.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import java.awt.*;

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
    private JPanel playerPanel = new JPanel();
    private JPanel computerPanel = new JPanel();
    private JPanel timerPanel = new JPanel();
    private JPanel playerStats = new JPanel();
    private JPanel computerStats = new JPanel();
    private JPanel statsPanel = new JPanel();
    private JPanel playerGridPanel = new JPanel();
    private JPanel computerGridPanel = new JPanel();

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
        this.setPanelState(PANEL_STATES.TITLE);}
    

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

    public void difficultyView() {

    }

    public void gameView() {

        //initialize layouts
        BorderLayout gameLayout = new BorderLayout();
        BoxLayout playerLayout = new BoxLayout(playerPanel, BoxLayout.Y_AXIS); 
        BoxLayout computerLayout = new BoxLayout(computerPanel, BoxLayout.Y_AXIS); 
        BoxLayout timerLayout = new BoxLayout(timerPanel, BoxLayout.Y_AXIS);
        GridLayout gameGrid = new GridLayout(10, 10);

        //set layouts 
        gamePanel.setLayout(gameLayout);
        playerPanel.setLayout(playerLayout);
        computerPanel.setLayout(computerLayout);
        timerPanel.setLayout(timerLayout);

        //putting everything where it's supposed to go 
        gamePanel.add(statsPanel, BorderLayout.NORTH);
        gamePanel.add(playerGridPanel, BorderLayout.EAST);
        gamePanel.add(computerGridPanel, BorderLayout.WEST);

        //overall north panel visuals
        statsPanel.add(playerPanel);
        statsPanel.add(timerPanel);
        statsPanel.add(computerPanel);

        //player panel visuals
        playerPanel.add(name);
        playerPanel.add(playerStats);

        playerStats.add(playerShipsSunk);
        playerStats.add(playerGuess);

        //timer panel visuals 

        //computer panel visuals 
        computerPanel.add(new JLabel("Computer"));
        computerPanel.add(computerStats);

        computerStats.add(computerShipsSunk);
        computerStats.add(computerGuess);

        //empty panel in the middle
        gamePanel.add(new JPanel(), BorderLayout.CENTER);

        //set layouts for game grid
        playerGridPanel.setLayout(gameGrid);
        computerGridPanel.setLayout(gameGrid);

        //make player grids
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                playerGrid[x][y] = new JButton();
                playerGrid[x][y].setPreferredSize(new Dimension(20,20));
            }
        }

         for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                computerGrid[x][y] = new JButton();
                playerGrid[x][y].setPreferredSize(new Dimension(20,20));
            }
        }

        //display grids
        for (int x = 0; x < playerGrid.length; x++) {

            for (int y = 0; y < playerGrid[x].length; y++) {
                playerGridPanel.add(playerGrid[x][y]);
            }
        }

        for (int x = 0; x < computerGrid.length; x++) {

            for (int y = 0; y < computerGrid[x].length; y++) {
                computerGridPanel.add(computerGrid[x][y]);
            }
        }

        this.add(gamePanel);

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
