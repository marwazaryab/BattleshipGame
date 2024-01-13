package Game.View;

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

    private JPanel gamePanel = new JPanel();
    private JPanel playerPanel = new JPanel();
    private JPanel computerPanel = new JPanel();
    private JPanel timerPanel = new JPanel();
    private JPanel playerStats = new JPanel();
    private JPanel computerStats = new JPanel();
    private JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel playerGridPanel = new JPanel();
    private JPanel computerGridPanel = new JPanel();

    private JButton exit = new JButton("Exit");
    private JButton easy = new JButton("Easy");
    private JButton medium = new JButton("Medium");
    private JButton hard = new JButton("Hard");
    private JButton stats = new JButton("Stats");

    private JTextField nameField = new JTextField();

    private JLabel name = new JLabel("Name: ");
    private JLabel computerName = new JLabel("Computer");
    private JLabel currentTurn= new JLabel("Turn: ");
    private JLabel computerShipsSunk = new JLabel("Computer Ships Sunk: ");
    private JLabel playerShipsSunk = new JLabel("Player Ships Sunk: ");
    private JLabel playerGuess = new JLabel("Player Guess: ");
    private JLabel computerGuess = new JLabel("Computer Guess: ");
    private JLabel timer = new JLabel("Timer: ");

    private JButton[][] playerGrid = new JButton[10][10];
    private JButton[][] computerGrid = new JButton[10][10];
    private PANEL_STATES currentState;

    public enum PANEL_STATES{
        TITLE,
        GAME,
        END
    }

    public BattleshipGUI (BattleshipGame data) {

        this.model = data;
        // this.titleView();
        this.model.setGUI(this);
        this.update();
        this.registerButtonController();
        this.setPanelState(PANEL_STATES.TITLE);
    }
        
    public void setPanelState(PANEL_STATES state){
        this.currentState = state;
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

    public void gameView() {

        this.add(gamePanel);

        Font f = new Font("Century Gothic", Font.BOLD, 14);
        computerShipsSunk.setFont(f);
        playerShipsSunk.setFont(f);
        computerGuess.setFont(f);
        playerGuess.setFont(f);
        timer.setFont(f);
        currentTurn.setFont(f);
        name.setFont(f);
        computerName.setFont(f);

        computerShipsSunk.setForeground(Color.white);
        playerShipsSunk.setForeground(Color.white);
        computerGuess.setForeground(Color.WHITE);
        playerGuess.setForeground(Color.white);
        timer.setForeground(Color.white);
        currentTurn.setForeground(Color.white);
        name.setForeground(Color.white);
        computerName.setForeground(Color.white);

        exit.setBackground(Color.blue);
        exit.setForeground(Color.white);
        exit.setBorder(BorderFactory.createLineBorder(Color.black));
        exit.setPreferredSize(new Dimension(50,40));

        //initialize layouts
        BorderLayout gameLayout = new BorderLayout();
        BoxLayout playerLayout = new BoxLayout(playerPanel, BoxLayout.Y_AXIS); 
        BoxLayout computerLayout = new BoxLayout(computerPanel, BoxLayout.Y_AXIS); 
        // BoxLayout timerLayout = new BoxLayout(timerPanel, BoxLayout.Y_AXIS);
        GridLayout gameGrid = new GridLayout(10, 10);

        //set layouts 
        gamePanel.setLayout(gameLayout);
        playerPanel.setLayout(playerLayout);
        computerPanel.setLayout(computerLayout);
        // timerPanel.setLayout(timerLayout);

        //putting everything where it's supposed to go 
        gamePanel.add(statsPanel, BorderLayout.NORTH);
        gamePanel.add(playerGridPanel, BorderLayout.WEST);
        gamePanel.add(computerGridPanel, BorderLayout.EAST);
        gamePanel.add(timerPanel, BorderLayout.SOUTH);

        //overall north panel visuals
        statsPanel.add(playerPanel);
        // statsPanel.add(timerPanel);
        statsPanel.add(computerPanel);
        statsPanel.setBackground(Color.BLACK);

        //timer panel visuals 
        timerPanel.setBackground(Color.BLACK);
        timerPanel.add(timer);
        timerPanel.add(exit);
        timerPanel.add(currentTurn);

        //TODO - put the player panel and computer panel visuals after making the grid so i can set the panel size respective to button size

        //player panel visuals
        playerPanel.setPreferredSize(new Dimension(400,50));
        playerPanel.setBackground(Color.BLACK);
        playerPanel.add(name);
        playerPanel.add(playerStats);

        //ships sunks and player guess
        playerStats.setBackground(Color.BLACK);
        playerStats.add(playerShipsSunk);
        playerStats.add(playerGuess);

        //computer panel visuals 
        computerPanel.setPreferredSize(new Dimension(400,50));
        computerPanel.setBackground(Color.black);
        computerPanel.add(computerName);
        computerPanel.add(computerStats);

        //ships sunk and computer guess
        computerStats.setBackground(Color.black);
        computerStats.add(computerShipsSunk);
        computerStats.add(computerGuess);

        //empty panel in the middle
        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(10,100));
        emptyPanel.setBackground(Color.black);
        gamePanel.add(emptyPanel, BorderLayout.CENTER);

        //set layouts for game grid
        playerGridPanel.setLayout(gameGrid);
        computerGridPanel.setLayout(gameGrid);

        //make player grid
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                playerGrid[x][y] = new JButton();
                playerGrid[x][y].setPreferredSize(new Dimension(40,40));
                playerGrid[x][y].setBackground(Color.BLUE);
                playerGrid[x][y].setBorder(BorderFactory.createLineBorder(Color.black));
            }
        }

        //make computer grid
         for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                computerGrid[x][y] = new JButton();
                computerGrid[x][y].setPreferredSize(new Dimension(40,40));
                computerGrid[x][y].setBackground(Color.BLUE);
                computerGrid[x][y].setBorder(BorderFactory.createLineBorder(Color.black));
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
    }

    public void update() {
        this.gameView();
        this.registerShipController();

    }

    public void registerShipController() {

        sController = new ShipController(playerGrid, computerGrid, model);

        for (int x = 0; x < computerGrid.length; x++) {
            for (int y = 0; y < computerGrid[x].length; y++) {
                computerGrid[x][y].addActionListener(sController);
            }
        }

        for (int x = 0; x < playerGrid.length; x++) {
            for (int y = 0; y < playerGrid[x].length; y++) {
                playerGrid[x][y].addActionListener(sController);
            }
        }
    }

    public void registerButtonController() {

        bController = new ButtonController(exit, model);
        ButtonController bController2 = new ButtonController(easy, medium, hard, model, nameField);

        exit.addActionListener(bController);
        easy.addActionListener(bController2);
        medium.addActionListener(bController2);
        hard.addActionListener(bController2);

    }

    // public void setPanelState(PANEL_STATES state) {
    //     this.currentState = state;

    // }
}
