package Game.View;

import javax.swing.*;
import javax.xml.bind.ValidationEvent;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import Game.Controllers.ButtonController;
import Game.Controllers.ShipControllerPlacement;
import Game.GameObjects.BattleshipGame;
import Game.Images.BattleshipImageComponent;

public class BattleshipGUI extends JPanel {

    private BattleshipGame model;
    private BattleshipImageComponent battleship = new BattleshipImageComponent("icon.jpg");
    private ButtonController bController;

    private JPanel gamePanel = new JPanel();
    private JPanel playerPanel = new JPanel();
    private JPanel computerPanel = new JPanel();
    private JPanel timerPanel = new JPanel();
    private JPanel playerStats = new JPanel();
    private JPanel computerStats = new JPanel();
    private JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel playerGridPanel = new JPanel();
    private JPanel computerGridPanel = new JPanel();
    JPanel titleContentsPanel = new JPanel();

    private JButton exit = new JButton("Exit");
    private JButton easy = new JButton("Easy");
    private JButton medium = new JButton("Medium");
    private JButton hard = new JButton("Hard");

    private JTextField nameField = new JTextField();

    private JLabel name = new JLabel("Welcome Player 1! Please enter your name: ");
    private JLabel computerName = new JLabel("Computer");
    private JLabel currentTurn = new JLabel("Turn: ");
    private JLabel computerShipsSunk = new JLabel("Computer Ships Sunk: ");
    private JLabel playerShipsSunk = new JLabel("Player Ships Sunk: ");
    private JLabel playerGuess = new JLabel("Player Guess: ");
    private JLabel computerGuess = new JLabel("Computer Guess: ");
    private JLabel timer = new JLabel("Timer: ");
    private JLabel title = new JLabel();
    private JLabel validateOutput = new JLabel("fawfaw"); //

    private JLabel alignmentLabel = new JLabel("Alignment: Left(L) or Up(U)");
    private JTextField alignment = new JTextField(10);
    private JButton[][] playerGrid;
    private JButton[][] computerGrid;
    private PANEL_STATES currentState = PANEL_STATES.TITLE;
    private int gridSize;

    private JFrame parentFrame;

    public enum PANEL_STATES {
        TITLE,
        GAME,
        END
    }

    public BattleshipGUI(BattleshipGame data) {

        this.model = data;
        this.setPanelState(PANEL_STATES.TITLE);
        this.model.setGUI(this);
        this.update();
        this.registerButtonController();
    }

    public void setPanelState(PANEL_STATES state) {
        this.currentState = state;
    }

    public void titleView() {

        JPanel buttonsPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel namePanel = new JPanel();

        ImageIcon icon = new ImageIcon("title.jpg");
        title.setIcon(icon);

        Color navyBlue = new Color(5, 1, 23);
        Color gray = new Color(115, 147, 179);

        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        titleContentsPanel.setLayout(new BorderLayout());

        easy.setFont(new Font("Montserrat", Font.BOLD, 70));
        medium.setFont(new Font("Montserrat", Font.BOLD, 70));
        hard.setFont(new Font("Montserrat", Font.BOLD, 70));
        exit.setFont(new Font("Montserrat", Font.BOLD, 70));
        name.setFont(new Font("Montserrat", Font.BOLD, 30));
        nameField.setFont(new Font("Montserrat", Font.BOLD, 30));

        easy.setBackground(navyBlue);
        easy.setForeground(gray);

        medium.setBackground(navyBlue);
        medium.setForeground(gray);

        hard.setBackground(navyBlue);
        hard.setForeground(gray);

        exit.setBackground(navyBlue);
        exit.setForeground(gray);

        namePanel.setBackground(navyBlue);
        name.setForeground(gray);

        buttonsPanel.add(easy);
        buttonsPanel.add(medium);
        buttonsPanel.add(hard);
        buttonsPanel.add(exit);

        namePanel.add(name);
        namePanel.add(nameField);

        bottomPanel.add(namePanel);
        bottomPanel.add(buttonsPanel);

        titleContentsPanel.add(bottomPanel, BorderLayout.SOUTH);
        titleContentsPanel.add(title, BorderLayout.NORTH);

        this.add(titleContentsPanel);

    }

    public void gameView() {

        this.add(gamePanel);
        // TODO make sure all variables are declared at the top of the method

        JPanel alignmentPanel = new JPanel(); 
        alignmentPanel.add(alignmentLabel);
        alignmentPanel.add(alignment);
        alignmentLabel.setForeground(Color.WHITE);
        alignmentPanel.setBackground(Color.black);

        Font f = new Font("Century Gothic", Font.BOLD, 14);
        computerShipsSunk.setFont(f);
        playerShipsSunk.setFont(f);
        computerGuess.setFont(f);
        playerGuess.setFont(f);
        timer.setFont(f);
        currentTurn.setFont(f);
        alignmentLabel.setFont(f);
        alignment.setFont(f);

        alignment.setForeground(Color.BLACK);

        name.setFont(f);
        name.setText(this.model.getPlayerName());

        computerShipsSunk.setForeground(Color.white);
        playerShipsSunk.setForeground(Color.white);
        computerGuess.setForeground(Color.WHITE);
        playerGuess.setForeground(Color.white);
        timer.setForeground(Color.white);
        currentTurn.setForeground(Color.white);
        name.setForeground(Color.white);
        computerName.setForeground(Color.white);
        alignmentLabel.setForeground(Color.white);
        alignment.setForeground(Color.BLACK);

        exit.setBackground(Color.blue);
        exit.setForeground(Color.white);
        exit.setBorder(BorderFactory.createLineBorder(Color.black));
        exit.setPreferredSize(new Dimension(50, 40));

        // initialize layouts
        BorderLayout gameLayout = new BorderLayout();
        BoxLayout playerLayout = new BoxLayout(playerPanel, BoxLayout.Y_AXIS);
        BoxLayout computerLayout = new BoxLayout(computerPanel, BoxLayout.Y_AXIS);
        // BoxLayout timerLayout = new BoxLayout(timerPanel, BoxLayout.Y_AXIS);
        GridLayout gameGrid = new GridLayout(this.getGridSize(), this.getGridSize());

        // set layouts
        gamePanel.setLayout(gameLayout);
        playerPanel.setLayout(playerLayout);
        computerPanel.setLayout(computerLayout);
        // timerPanel.setLayout(timerLayout);

        // set layouts for game grid
        playerGridPanel.setLayout(gameGrid);
        computerGridPanel.setLayout(gameGrid);

        // overall north panel visuals
        statsPanel.add(playerPanel);
        // statsPanel.add(timerPanel);
        statsPanel.add(computerPanel);
        statsPanel.add(validateOutput);
        statsPanel.setBackground(Color.BLACK);

        // timer panel visuals
        timerPanel.setBackground(Color.BLACK);
        timerPanel.add(alignmentPanel);
        timerPanel.add(timer);
        timerPanel.add(exit);
        timerPanel.add(currentTurn);
        timerPanel.add(validateOutput);
        validateOutput.setForeground(Color.white);

        // TODO - put the player panel and computer panel visuals after making the grid
        // so i can set the panel size respective to button size

        // make player grid
        System.out.println(this.getGridSize());
        for (int x = 0; x < this.getGridSize(); x++) {
            for (int y = 0; y < this.getGridSize(); y++) {
                playerGrid[x][y] = new JButton();
                playerGrid[x][y].setPreferredSize(new Dimension(30, 30));
                playerGrid[x][y].setBackground(Color.BLUE);
                playerGrid[x][y].setBorder(BorderFactory.createLineBorder(Color.black));
            }
        }

        // make computer grid
        for (int x = 0; x < this.getGridSize(); x++) {
            for (int y = 0; y < this.getGridSize(); y++) {
                computerGrid[x][y] = new JButton();
                computerGrid[x][y].setPreferredSize(new Dimension(30, 30));
                computerGrid[x][y].setBackground(Color.BLUE);
                computerGrid[x][y].setBorder(BorderFactory.createLineBorder(Color.black));
            }
        }

        // display grids
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

        // player panel visuals
        playerPanel.setPreferredSize(new Dimension(this.getGridSize() * 30, 50));
        playerPanel.setBackground(Color.BLACK);
        playerPanel.add(name);
        playerPanel.add(playerStats);

        // ships sunks and player guess
        playerStats.setBackground(Color.BLACK);
        playerStats.add(playerShipsSunk);
        playerStats.add(playerGuess);

        // computer panel visuals
        computerPanel.setPreferredSize(new Dimension(this.getGridSize() * 30, 50));
        computerPanel.setBackground(Color.black);
        computerPanel.add(computerName);
        computerPanel.add(computerStats);

        // ships sunk and computer guess
        computerStats.setBackground(Color.black);
        computerStats.add(computerShipsSunk);
        computerStats.add(computerGuess);

        // empty panel in the middle
        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(10, 100));
        emptyPanel.setBackground(Color.black);
        gamePanel.add(emptyPanel, BorderLayout.CENTER);

        // putting everything where it's supposed to go
        gamePanel.add(statsPanel, BorderLayout.NORTH);
        gamePanel.add(playerGridPanel, BorderLayout.WEST);
        gamePanel.add(computerGridPanel, BorderLayout.EAST);
        gamePanel.add(timerPanel, BorderLayout.SOUTH);

    }

    public void update() {

        switch (currentState) {
            case TITLE:
                this.titleView();
                break;

            case GAME:

            if (this.model.getNewGame() == true) {

                this.titleContentsPanel.setVisible(false);
                this.playerGrid = new JButton[this.model.getComputerGuesses().length][this.model
                        .getComputerGuesses().length];
                this.computerGrid = new JButton[this.model.getComputerGuesses().length][this.model
                        .getComputerGuesses().length];

                        this.gameView();
                        this.registerShipController();
                this.parentFrame = (JFrame) this.getTopLevelAncestor();
                this.parentFrame.pack();

                this.validateOutput.setText("Welcome " + this.model.getPlayerName() + 
                    "!. Please start by choosing an alignment for ship " + (this.model.getShipNum()+1)
                        + " and then placing it on the left grid");
            }

            else if (this.model.getDeploymentStatus() == false) {

                if (this.model.getValidPosition() == true) {
                    this.validateOutput.setText("Please place ship " + (this.model.getShipNum()+1));

                }

                else if (this.model.getValidPosition() == false) {
                    this.validateOutput.setText("That is not a valid position! Please reselect a position");

                }

                if (this.model.getShipNum() == 5) {
                    this.validateOutput.setText("Player ships deployed! Click once more to deploy computer ships.");
                }
            }

            else {
                if (this.model.getGameTurn() == "Player") {
                    if (this.model.getRowGuessed() == 0 || this.model.getColGuessed() == 0) {
                        this.validateOutput.setText(this.model.getPlayerName() + "'s turn: ");
                    }
                    else {
                        if (this.model.getHitStatus() == true) {
                            this.validateOutput.setText(this.model.getPlayerName() + "'s turn: " + this.model.getPlayerName() + " guessed (" 
                            + this.model.getRowGuessed() + ", " + this.model.getColGuessed() + ") and hit a ship!");
                        }
                        else {
                            this.validateOutput.setText(this.model.getPlayerName() + "'s turn: " + this.model.getPlayerName() + " guessed (" 
                            + this.model.getRowGuessed() + ", " + this.model.getColGuessed() + ") and missed!");
                        }
                    }
                }

                if (this.model.getGameTurn() == "Computer") {

                }
            }

                break;

            default:
                break;
        }

    }

    public void registerShipController() {

        ShipControllerPlacement shipController = new ShipControllerPlacement(playerGrid, computerGrid, model, name,
                alignment);

        for (int row = 0; row < playerGrid.length; row++) {

            for (int col = 0; col < playerGrid[0].length; col++) {
                playerGrid[row][col].addActionListener(shipController);

            }
        }

        for (int row = 0; row < playerGrid.length; row++) {

            for (int col = 0; col < playerGrid[0].length; col++) {
                computerGrid[row][col].addActionListener(shipController);

            }
        }
    }

    public void registerButtonController() {

        ButtonController bController2 = new ButtonController(this.easy, this.medium, this.hard, this.exit, this.model,
                this.nameField);

        exit.addActionListener(bController2);
        easy.addActionListener(bController2);
        medium.addActionListener(bController2);
        hard.addActionListener(bController2);
    }

    public void setGridSize(int i) {
        this.gridSize = i;
    }

    public int getGridSize() {
        return this.playerGrid.length;
    }

}
