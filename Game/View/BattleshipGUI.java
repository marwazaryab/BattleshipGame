package Game.View;

import javax.swing.*;
import javax.xml.bind.ValidationEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JLabel title = new JLabel();
    private JLabel validateOutput = new JLabel("fawfaw"); //

    private JLabel alignmentLabel = new JLabel("Alignment: Left(L) or Up(U)");
    private JTextField alignment = new JTextField(10);
    private JButton[][] playerGrid;
    private JButton[][] computerGrid;
    private PANEL_STATES currentState = PANEL_STATES.TITLE;
    private int gridSize;

    private JLabel timer = new JLabel();
    private Timer time;
    private int timeElaspedSeconds;
    private long startTime;

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

        startTime = System.currentTimeMillis();
        time = new Timer(1000, timeListener);
        time.start();

    }

    public int timePassed(){
        long currentTime = System.currentTimeMillis();  
        return timeElaspedSeconds = (int)((currentTime-startTime) / 1000);


    }

    ActionListener timeListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            timer.setText("Time: " + timePassed());
        }
    };

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
        validateOutput.setFont(new Font("Century Gothic", Font.BOLD, 18));

        name.setFont(new Font("Century Gothic", Font.BOLD, 20));
        computerName.setFont(new Font("Century Gothic", Font.BOLD, 20));
        name.setText(this.model.getPlayerName());

        alignment.setForeground(Color.BLACK);
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
        validateOutput.setForeground(Color.white);

        exit.setBackground(Color.blue);
        exit.setForeground(Color.white);
        exit.setBorder(BorderFactory.createLineBorder(Color.black));
        exit.setPreferredSize(new Dimension(50, 40));

        JPanel userPanel = new JPanel();
        JPanel outputPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel leftNumberPanel1 = new JPanel();
        JPanel leftNumberPanel2 = new JPanel();
        JPanel feedbackPanel = new JPanel();
        JPanel fullLeftPanel = new JPanel();
        JPanel fullRightPanel = new JPanel();
        JPanel topNumberPanel1 = new JPanel();
        JPanel topNumberPanel2 = new JPanel();
        JPanel topPanelLeft = new JPanel();
        JPanel gapPanel = new JPanel();

        JButton emptyButton = new JButton();
        emptyButton.setPreferredSize(new Dimension(30, 30));
        emptyButton.setForeground(Color.white);
        emptyButton.setBackground(Color.black);
        emptyButton.setBorder(BorderFactory.createLineBorder(Color.black));

        // initialize layouts
        BorderLayout gameLayout = new BorderLayout();
        BoxLayout playerLayout = new BoxLayout(playerPanel, BoxLayout.Y_AXIS);
        BoxLayout computerLayout = new BoxLayout(computerPanel, BoxLayout.Y_AXIS);
        // BoxLayout timerLayout = new BoxLayout(timerPanel, BoxLayout.Y_AXIS);
        GridLayout gameGrid = new GridLayout(this.getGridSize(), this.getGridSize());
        BoxLayout topLayout = new BoxLayout(statsPanel, BoxLayout.Y_AXIS);
        BoxLayout leftNumberLayout1 = new BoxLayout(leftNumberPanel1, BoxLayout.Y_AXIS);
        BoxLayout leftNumberLayout2 = new BoxLayout(leftNumberPanel2, BoxLayout.Y_AXIS);
        BoxLayout bottomLayout = new BoxLayout(outputPanel, BoxLayout.Y_AXIS);
        BoxLayout leftPanelLayout = new BoxLayout(fullLeftPanel, BoxLayout.Y_AXIS);
        BoxLayout rightPanelLayout = new BoxLayout(fullRightPanel, BoxLayout.Y_AXIS);
        GridLayout topNumberLayout1 = new GridLayout(1, this.getGridSize() + 1);
        GridLayout topNumberLayout2 = new GridLayout(1, this.getGridSize() + 1);

        // set layouts
        gamePanel.setLayout(gameLayout);
        playerPanel.setLayout(playerLayout);
        computerPanel.setLayout(computerLayout);
        statsPanel.setLayout(topLayout);
        leftNumberPanel1.setLayout(leftNumberLayout1);
        leftNumberPanel2.setLayout(leftNumberLayout2);
        outputPanel.setLayout(bottomLayout);
        fullLeftPanel.setLayout(leftPanelLayout);
        fullRightPanel.setLayout(rightPanelLayout);
        topNumberPanel1.setLayout(topNumberLayout1);
        topNumberPanel2.setLayout(topNumberLayout2);
        // timerPanel.setLayout(timerLayout);

        // set layouts for game grid
        playerGridPanel.setLayout(gameGrid);
        computerGridPanel.setLayout(gameGrid);

        // overall north panel visuals
        // statsPanel.add(playerPanel);
        // statsPanel.add(computerPanel);
        // statsPanel.add(validateOutput);
        statsPanel.setBackground(Color.black);

        // overall north panel
        statsPanel.add(userPanel);
        statsPanel.add(alignmentPanel);

        // stats for both people
        userPanel.add(playerPanel);
        userPanel.add(computerPanel);
        userPanel.setBackground(Color.black);

        // timer panel visuals
        timerPanel.setBackground(Color.black);
        // timerPanel.add(alignmentPanel);
        timerPanel.add(timer);
        timerPanel.add(exit);

        // holds the feedback label
        feedbackPanel.add(validateOutput);
        feedbackPanel.setBackground(Color.black);

        // basically the entire bottom panel
        outputPanel.add(feedbackPanel);
        outputPanel.add(timerPanel);
        outputPanel.setBackground(Color.black);

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

        leftPanel.add(leftNumberPanel1);
        leftPanel.add(playerGridPanel);
        leftPanel.setBackground(Color.black);
        leftNumberPanel1.setPreferredSize(new Dimension(16, this.getGridSize() * 30));
        leftNumberPanel1.setBackground(Color.black);

        rightPanel.add(leftNumberPanel2);
        rightPanel.add(computerGridPanel);
        rightPanel.setBackground(Color.black);
        leftNumberPanel2.setPreferredSize(new Dimension(16, this.getGridSize() * 30));
        leftNumberPanel2.setBackground(Color.black);

        // making left numbers for left grid
        for (int x = 0; x < this.getGridSize(); x++) {
            JButton numButton = new JButton(x + "");
            numButton.setForeground(Color.white);
            numButton.setBackground(Color.black);
            numButton.setPreferredSize(new Dimension(30, 35));
            numButton.setBorder(BorderFactory.createLineBorder(Color.black));
            leftNumberPanel1.add(numButton);
        }

        // making left numbers for right grid
        for (int x = 0; x < this.getGridSize(); x++) {
            JButton numButton = new JButton(x + "");
            numButton.setForeground(Color.white);
            numButton.setBackground(Color.black);
            numButton.setPreferredSize(new Dimension(30, 35));
            numButton.setBorder(BorderFactory.createLineBorder(Color.black));
            leftNumberPanel2.add(numButton);
        }

        // making top numbers for left grid
        // topPanelLeft.add(topNumberPanel1);
        // topPanelLeft.setBackground(Color.black);
        // gapPanel.setBackground(Color.black);
        topNumberPanel1.setBackground(Color.black);
        topNumberPanel1.add(emptyButton);
        for (int x = 0; x < this.getGridSize(); x++) {
            JButton numButton = new JButton(x + "");
            numButton.setForeground(Color.white);
            numButton.setBackground(Color.black);
            numButton.setPreferredSize(new Dimension(30, 30));
            numButton.setBorder(BorderFactory.createLineBorder(Color.black));
            topNumberPanel1.add(numButton);
        }

        // making top numbers for right grid
        topNumberPanel2.add(emptyButton);
        topNumberPanel2.setBackground(Color.black);
        for (int x = 0; x < this.getGridSize(); x++) {
            JButton numButton = new JButton(x + "");
            numButton.setForeground(Color.white);
            numButton.setBackground(Color.black);
            numButton.setPreferredSize(new Dimension(30, 30));
            numButton.setBorder(BorderFactory.createLineBorder(Color.black));
            topNumberPanel2.add(numButton);
        }

        // display grids
        for (int x = 0; x < playerGrid.length; x++) {

            for (int y = 0; y < playerGrid[x].length; y++) {
                playerGridPanel.add(playerGrid[x][y]);
            }
        }

        // trying to make numbers
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

        // full left panel
        fullLeftPanel.add(topNumberPanel1);
        fullLeftPanel.add(leftPanel);
        fullLeftPanel.setBackground(Color.black);

        // full right panel
        fullRightPanel.add(topNumberPanel2);
        fullRightPanel.add(rightPanel);
        fullLeftPanel.setBackground(Color.black);
        // empty panel in the middle
        // JPanel emptyPanel = new JPanel();
        // emptyPanel.setBackground(Color.black);
        // emptyPanel.setPreferredSize(new Dimension(30,10));
        // gamePanel.add(emptyPanel, BorderLayout.CENTER);

        // putting everything where it's supposed to go
        gamePanel.add(statsPanel, BorderLayout.NORTH);
        gamePanel.add(fullLeftPanel, BorderLayout.WEST);
        gamePanel.add(fullRightPanel, BorderLayout.EAST);
        gamePanel.add(outputPanel, BorderLayout.SOUTH);

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
                            "! Please start by choosing an alignment for ship " + (this.model.getShipNum() + 1)
                            + " and then placing it on the left grid");
                }

                else if (this.model.getDeploymentStatus() == false) {

                    if (this.model.getValidPosition() == true) {
                        this.validateOutput.setText("Please place ship " + (this.model.getShipNum() + 1));

                    }

                    else if (this.model.getValidPosition() == false) {
                        this.validateOutput.setText("That is not a valid position! Please reselect a position");

                    }

                    if (this.model.getShipNum() == 5) {
                        this.validateOutput.setText(
                                "Player ships deployed! Click once on computer grid to deploy computer ships.");
                    }
                }

                else {
                    if (this.model.getPlayerHits() < 14) {

                        if (this.model.getGameTurn() == "Player") {
                            if (this.model.getPlayerRowGuessed() == 0 || this.model.getPlayerColGuessed() == 0) {
                                this.validateOutput.setText(this.model.getPlayerName() + "'s turn: ");
                            } else {
                                if (this.model.getHitStatus() == true) {
                                    this.validateOutput.setText(this.model.getPlayerName() + "'s turn: "
                                            + this.model.getPlayerName() + " guessed ("
                                            + this.model.getPlayerRowGuessed() + ", " + this.model.getPlayerColGuessed()
                                            + ") and hit a ship! Click the grid for computer's turn!");
                                    this.computerGrid[this.model.getPlayerRowGuessed()][this.model
                                            .getPlayerColGuessed()].setText("O");
                                } else {
                                    this.validateOutput.setText(this.model.getPlayerName() + "'s turn: "
                                            + this.model.getPlayerName() + " guessed ("
                                            + this.model.getPlayerRowGuessed() + ", " + this.model.getPlayerColGuessed()
                                            + ") and missed! Click the grid for computer's turn!");
                                    this.computerGrid[this.model.getPlayerRowGuessed()][this.model
                                            .getPlayerColGuessed()].setText("!");

                                }
                            }
                        }

                        if (this.model.getGameTurn() == "Computer") {

                            if (this.model.getHitStatus() == true) {
                                this.validateOutput.setText("Computer's turn: the computer guessed ("
                                        + this.model.getCompRowGuessed() + ", " + this.model.getCompColGuessed()
                                        + ") and hit a ship!");
                            } else {
                                this.validateOutput.setText("Computer's turn: the computer guessed ("
                                        + this.model.getCompRowGuessed() + ", " + this.model.getCompColGuessed()
                                        + ") and missed!");
                            }
                        }
                    }

                    else {
                        if (this.model.getPlayerHits() == 14) {
                            this.validateOutput.setText("Game Ended! The winner is " + this.model.getPlayerName());
                        } else {
                            this.validateOutput.setText("Game Ended! The winner is Computer!");
                        }
                        this.model.disableGrid(computerGrid);
                    }
                }
                break;

            default:
                break;
        }

    }

    public void registerShipController() {

        ShipControllerPlacement shipController = new ShipControllerPlacement(playerGrid, computerGrid, model,
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
