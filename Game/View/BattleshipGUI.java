package Game.View;

import javax.swing.*;
import java.awt.*;

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
    private JButton start = new JButton("Start");
    private JButton stats = new JButton("Stats");

    private JTextField nameField = new JTextField();

    private JLabel name = new JLabel("Name: ");
    private JLabel currentTurn= new JLabel();
    private JLabel computerShipsSunk = new JLabel("Computer Ships Sunk: ");
    private JLabel playerShipsSunk = new JLabel("Player Ships Sunk: ");
    private JLabel playerGuess = new JLabel("Player Guess: ");
    private JLabel computerGuess = new JLabel("Computer Guess: ");
    private JLabel timer = new JLabel("Timer: ");

    private JButton[][] playerGrid = new JButton[10][10];
    private JButton[][] computerGrid = new JButton[10][10];

    public BattleshipGUI (BattleshipGame data) {

        this.model = data;
        this.gameView();
        this.model.setGUI(this);
        this.registerControllers();
        this.update();
        
    }

    public void titleView() {

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
        timerPanel.add(timer);
        timerPanel.add(exit);

        //computer panel visuals 
        computerPanel.add(new JLabel("Computer"));
        computerPanel.add(computerStats);

        computerStats.add(computerShipsSunk);
        computerStats.add(computerGuess);

        //empty panel in the middle
        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension());
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

    }

    public void registerControllers() {

    }
}
