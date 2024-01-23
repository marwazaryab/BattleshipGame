package Game.View;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.print.attribute.standard.DocumentName;
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

    // title view instance variables
    private JPanel titleContentsPanel = new JPanel();
    private JPanel buttonsPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private JPanel namePanel = new JPanel();

    private JButton statsButton = new JButton("Stats");
    private JPanel endgamePanel = new JPanel();
    private JPanel endGameButtonsPanel = new JPanel();
    private JPanel labelsPanel = new JPanel();

    private JLabel winnerLabel = new JLabel();
    private JLabel creditsLabel = new JLabel("- MARWA & MOHIB");
    private JLabel thankYouLabel = new JLabel("THANK YOU FOR PLAYING!!! <3");

    private JButton endGame = new JButton("End Game");

    private JButton endExit = new JButton("Exit");

    // game view instance variables
    private JPanel gamePanel = new JPanel();
    private JPanel userPanel = new JPanel();
    private JPanel outputPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel leftNumberPanel1 = new JPanel();
    private JPanel leftNumberPanel2 = new JPanel();
    private JPanel feedbackPanel = new JPanel();
    private JPanel fullLeftPanel = new JPanel();
    private JPanel fullRightPanel = new JPanel();
    private JPanel topNumberPanel1 = new JPanel();
    private JPanel topNumberPanel2 = new JPanel();
    private JPanel alignmentPanel = new JPanel();
    private JPanel timerPanel = new JPanel();
    private JPanel playerPanel = new JPanel();
    private JPanel computerPanel = new JPanel();
    private JPanel playerStats = new JPanel();
    private JPanel computerStats = new JPanel();
    private JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel playerGridPanel = new JPanel();
    private JPanel computerGridPanel = new JPanel();
    private JPanel gapPanel = new JPanel();

    // buttons
    private JButton exit = new JButton("Exit");
    private JButton easy = new JButton("Easy");
    private JButton medium = new JButton("Medium");
    private JButton hard = new JButton("Hard");
    private JButton restart = new JButton("Restart");
    private JButton emptyButton = new JButton();

    // textfields
    private JTextField nameField = new JTextField();
    private JTextField alignment = new JTextField(10);

    // labels
    private JLabel userPrompt = new JLabel("Welcome Player 1! Please enter your name: ");
    private JLabel name = new JLabel();
    private JLabel computerName = new JLabel("Computer");
    private JLabel currentTurn = new JLabel("Turn: ");
    private JLabel computerShipsSunk = new JLabel("Ships Sunk: ");
    private JLabel playerShipsSunk = new JLabel("Ships Sunk: ");
    private JLabel playerGuess = new JLabel("Player Guess: ");
    private JLabel computerGuess = new JLabel("Computer Guess: ");
    private JLabel title = new JLabel();
    private JLabel validateOutput = new JLabel();
    private JLabel numPlayerGuesses = new JLabel("Player Guesses: ");
    private JLabel numCompGuesses = new JLabel("Computer Guesses: ");
    private JLabel playerShipsRemaining = new JLabel("Player Ships Left: ");
    private JLabel computerShipsRemaining = new JLabel("Computer Ships Left: ");
    private JLabel alignmentLabel = new JLabel("Alignment: Right(R) or Down(D)");
    private JLabel timer = new JLabel();
    private JLabel missLabel = new JLabel("! - Miss");
    private JLabel hitLabel = new JLabel("O - Hit");

    // grids
    private JButton[][] playerGrid;
    private JButton[][] computerGrid;

    // states
    private PANEL_STATES currentState;

    // ints
    private int gridSize;

    // timer variables
    private Timer time;
    private long startTime;
    private int timeElaspedSeconds;

    // parent frame
    private JFrame parentFrame;

    // image icon
    private ImageIcon icon = new ImageIcon("title.jpg");

    // colors
    private Color navyBlue = new Color(5, 1, 23);
    private Color gray = new Color(115, 147, 179);
    private Color lightBlue = new Color(0, 0, 205);
    private Color darkBlue = new Color(30, 144, 255);

    // booleans
    public boolean isRestart = false;

    // fonts
    Font f = new Font("Century Gothic", Font.BOLD, 14);

    // enums
    public enum PANEL_STATES {
        TITLE,
        GAME,
        END,
    }

    public BattleshipGUI(BattleshipGame data) {

        this.model = data;
        this.setPanelState(PANEL_STATES.TITLE);
        this.model.setGUI(this);
        this.update();
        this.registerButtonController();
        startTime = System.currentTimeMillis();
        time = new Timer(0, timeListener);
        time.start();

    }

    public int timePassed() {
        long currentTime = System.currentTimeMillis();
        return timeElaspedSeconds = (int) ((currentTime - startTime) / 1000);

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

        title.setIcon(icon);

        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        titleContentsPanel.setLayout(new BorderLayout());

        easy.setFont(new Font("Montserrat", Font.BOLD, 70));
        medium.setFont(new Font("Montserrat", Font.BOLD, 70));
        hard.setFont(new Font("Montserrat", Font.BOLD, 70));
        exit.setFont(new Font("Montserrat", Font.BOLD, 70));
        userPrompt.setFont(new Font("Montserrat", Font.BOLD, 30));
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
        userPrompt.setForeground(gray);

        buttonsPanel.add(easy);
        buttonsPanel.add(medium);
        buttonsPanel.add(hard);
        buttonsPanel.add(exit);

        namePanel.add(userPrompt);
        namePanel.add(nameField);

        bottomPanel.add(namePanel);
        bottomPanel.add(buttonsPanel);

        titleContentsPanel.add(bottomPanel, BorderLayout.SOUTH);
        titleContentsPanel.add(title, BorderLayout.NORTH);

        this.add(titleContentsPanel);

    }

    public void endGameView() {

        this.setPanelState(PANEL_STATES.END);

        endgamePanel.setLayout(new BorderLayout());
        endGameButtonsPanel.setLayout(new BoxLayout(endGameButtonsPanel, BoxLayout.X_AXIS));
        // TODO add to top
        JPanel winnerPanel = new JPanel();

        endgamePanel.setBackground(darkBlue);
        labelsPanel.setBackground(darkBlue);

        winnerLabel.setFont(new Font("Century Gothic", Font.BOLD, 40));
        creditsLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        thankYouLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        creditsLabel.setForeground(navyBlue);
        thankYouLabel.setForeground(navyBlue);

        winnerLabel.setForeground(navyBlue);

        endExit.setFont(new Font("Century Gothic", Font.BOLD, 43));
        statsButton.setFont(new Font("Century Gothic", Font.BOLD, 43));
        restart.setFont(new Font("Century Gothic", Font.BOLD, 43));

        endExit.setBackground(navyBlue);
        statsButton.setBackground(navyBlue);
        restart.setBackground(navyBlue);

        endExit.setForeground(gray);
        statsButton.setForeground(gray);
        restart.setForeground(gray);

        endGameButtonsPanel.add(restart);
        endGameButtonsPanel.add(statsButton);
        endGameButtonsPanel.add(endExit);

        winnerPanel.setBackground(darkBlue);

        if (this.model.getWinner().equals(this.model.getPlayerName())) {
            winnerLabel.setText("PLAYER 1 WINS");
        } else {
            winnerLabel.setText("COMPUTER WINS");

        }

        winnerPanel.add(winnerLabel);
        labelsPanel.add(thankYouLabel);
        labelsPanel.add(creditsLabel);

        endgamePanel.add(winnerPanel, BorderLayout.NORTH);
        endgamePanel.add(labelsPanel, BorderLayout.CENTER);
        endgamePanel.add(endGameButtonsPanel, BorderLayout.SOUTH);

        endgamePanel.setPreferredSize(new Dimension(400, 300));
        this.add(endgamePanel);

    }

    public void gameView() {

        // TODO make sure all variables are declared at the top of the method

        alignmentPanel.add(alignmentLabel);
        alignmentPanel.add(alignment);
        alignmentLabel.setForeground(Color.WHITE);
        alignmentPanel.setBackground(Color.black);

        restart.setFont(new Font("Century Gothic", Font.BOLD, 40));
        endGame.setFont(new Font("Century Gothic", Font.BOLD, 40));

        restart.setBackground(navyBlue);
        restart.setForeground(gray);
        endGame.setForeground(gray);
        endGame.setBackground(navyBlue);

        computerShipsSunk.setFont(f);
        playerShipsSunk.setFont(f);
        computerGuess.setFont(f);
        playerGuess.setFont(f);
        timer.setFont(f);
        currentTurn.setFont(f);
        alignmentLabel.setFont(f);
        alignment.setFont(f);
        validateOutput.setFont(new Font("Century Gothic", Font.BOLD, 18));
        numPlayerGuesses.setFont(f);
        numCompGuesses.setFont(f);
        playerShipsRemaining.setFont(f);
        computerShipsRemaining.setFont(f);
        missLabel.setFont(f);
        hitLabel.setFont(f);

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
        numPlayerGuesses.setForeground(Color.white);
        numCompGuesses.setForeground(Color.white);
        playerShipsRemaining.setForeground(Color.white);
        computerShipsRemaining.setForeground(Color.white);
        missLabel.setForeground(Color.white);
        hitLabel.setForeground(Color.white);

        // exit.setBackground(Color.blue);
        // exit.setForeground(Color.white);
        // exit.setBorder(BorderFactory.createLineBorder(Color.black));
        // exit.setPreferredSize(new Dimension(50, 40));
        // exit.setFont(f);

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
        GridLayout topNumberLayout1 = new GridLayout(1, this.getGridSize() + 2);
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
        timerPanel.add(restart);
        timerPanel.add(endGame);

        timerPanel.add(missLabel);
        timerPanel.add(hitLabel);

        // holds the feedback label
        feedbackPanel.add(validateOutput);
        feedbackPanel.setBackground(Color.black);

        // basically the entire bottom panel
        outputPanel.add(feedbackPanel);
        outputPanel.add(timerPanel);
        outputPanel.setBackground(Color.black);

        // make player grid
        System.out.println(this.getGridSize());
        for (int x = 0; x < this.getGridSize(); x++) {
            for (int y = 0; y < this.getGridSize(); y++) {
                playerGrid[x][y] = new JButton();
                playerGrid[x][y].setPreferredSize(new Dimension(30, 30));
                playerGrid[x][y].setBackground(darkBlue);
                playerGrid[x][y].setBorder(BorderFactory.createLineBorder(Color.black));
            }
        }

        // make computer grid
        for (int x = 0; x < this.getGridSize(); x++) {
            for (int y = 0; y < this.getGridSize(); y++) {
                computerGrid[x][y] = new JButton();
                computerGrid[x][y].setPreferredSize(new Dimension(30, 30));
                computerGrid[x][y].setBackground(lightBlue);
                computerGrid[x][y].setBorder(BorderFactory.createLineBorder(Color.black));
            }
        }

        // display both grids
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
        topNumberPanel1.setBackground(Color.black);
        topNumberPanel1.add(gapPanel);
        gapPanel.setBackground(Color.black);

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

        // player panel visuals
        playerPanel.setPreferredSize(new Dimension(this.getGridSize() * 30, 50));
        playerPanel.setBackground(Color.BLACK);
        playerPanel.add(name);
        playerPanel.add(playerStats);

        // ships sunks and player guess
        playerStats.setBackground(Color.BLACK);
        // playerStats.add(computerShipsSunk);
        playerStats.add(numPlayerGuesses);
        playerStats.add(computerShipsRemaining);

        // computer panel visuals
        computerPanel.setPreferredSize(new Dimension(this.getGridSize() * 30, 50));
        computerPanel.setBackground(Color.black);
        computerPanel.add(computerName);
        computerPanel.add(computerStats);

        // ships sunk and computer guess
        computerStats.setBackground(Color.black);
        // computerStats.add(playerShipsSunk);
        computerStats.add(numCompGuesses);
        computerStats.add(playerShipsRemaining);

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

        this.add(gamePanel);
    }

    public void update() {

        switch (currentState) {

            case TITLE:
                this.gamePanel.setVisible(false);
                this.titleContentsPanel.setVisible(true);
                this.endgamePanel.setVisible(false);
                this.titleView();

                if (this.isRestart == true) {
                    topNumberPanel1.removeAll();
                    topNumberPanel2.removeAll();
                    leftNumberPanel1.removeAll();
                    leftNumberPanel2.removeAll();
                    playerGridPanel.removeAll();
                    computerGridPanel.removeAll();
                    this.parentFrame = (JFrame) this.getTopLevelAncestor();
                    this.parentFrame.pack();
                    revalidate();
                    repaint();

                }

                break;

            case GAME:

                this.gamePanel.setVisible(true);
                this.endGame.setVisible(false);

                if (this.model.getNewGame() == true) {

                    this.titleContentsPanel.setVisible(false);
                    this.alignmentPanel.setVisible(true);
                    this.playerGrid = new JButton[this.model.getComputerGuesses().length][this.model
                            .getComputerGuesses().length];
                    this.computerGrid = new JButton[this.model.getComputerGuesses().length][this.model
                            .getComputerGuesses().length];

                    System.out.println(this.model.getComputerGuesses().length);

                    this.gameView();
                    this.registerShipController();

                    this.validateOutput.setText("Welcome " + this.model.getPlayerName() +
                            "! Please start by choosing an alignment for ship " + (this.model.getShipNum() + 1)
                            + " and then placing it on the left grid");

                    this.parentFrame = (JFrame) this.getTopLevelAncestor();
                    this.parentFrame.pack();
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

                    this.alignmentPanel.setVisible(false);
                    this.parentFrame.pack();
                    this.numPlayerGuesses
                            .setText("Player Guesses: ".concat(Integer.toString(this.model.getNumPlayerGuesses())));
                    this.numCompGuesses
                            .setText("Computer Guesses: ".concat(Integer.toString(this.model.getNumCompGuesses())));
                    // this.playerShipsSunk
                    // .setText("Ships Sunk:
                    // ".concat(Integer.toString(this.model.getPlayerShipsSunk())));
                    // this.computerShipsSunk
                    // .setText("Ships Sunk:
                    // ".concat(Integer.toString(this.model.getComputerShipsSunk())));
                    this.playerShipsRemaining
                            .setText("Player Ships Left: "
                                    .concat(Integer.toString(this.model.getPlayerRemainingShips())));
                    this.computerShipsRemaining
                            .setText("Computer Ships Left: "
                                    .concat(Integer.toString(this.model.getComputerRemainingShips())));

                    if (this.model.getGameStatus() == false) {

                        if (this.model.getGameTurn() == "Player") {
                            if (this.model.getNumPlayerGuesses() == 0) {
                                this.validateOutput.setText(this.model.getPlayerName() + "'s turn: ");
                            } else {
                                if (this.model.getHitStatus() == true) {
                                    this.validateOutput.setText(this.model.getPlayerName() + "'s turn: "
                                            + this.model.getPlayerName() + " guessed ("
                                            + this.model.getPlayerRowGuessed() + ", " + this.model.getPlayerColGuessed()
                                            + ") and hit a ship! Click the grid for computer's turn!");
                                    if (this.model.getShipSunk() == true) {
                                        this.validateOutput.setText(this.model.getPlayerName() + "'s turn: "
                                                + this.model.getPlayerName() + " guessed ("
                                                + this.model.getPlayerRowGuessed() + ", "
                                                + this.model.getPlayerColGuessed()
                                                + ") and sunk a ship! Click the grid for computer's turn!");
                                    }
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
                                        + ") and hit a ship! Please do your turn!");
                                if (this.model.getShipSunk() == true) {
                                    this.validateOutput.setText("Computer's turn: the computer guessed ("
                                            + this.model.getCompRowGuessed() + ", " + this.model.getCompColGuessed()
                                            + ") and sunk a ship! Please do your turn!");
                                }
                                this.playerGrid[this.model.getCompRowGuessed()][this.model
                                        .getCompColGuessed()].setText("O");
                            } else {
                                this.validateOutput.setText("Computer's turn: the computer guessed ("
                                        + this.model.getCompRowGuessed() + ", " + this.model.getCompColGuessed()
                                        + ") and missed! Please do your turn!");
                                this.playerGrid[this.model.getCompRowGuessed()][this.model
                                        .getCompColGuessed()].setText("!");
                            }
                        }
                    }

                    else {

                        endGame.setVisible(true);
                        this.validateOutput.setText("Game Ended! The winner is " + this.model.getWinner());
                        this.model.disableGrid(computerGrid);
                    }
                }
                break;

            case END:
                this.gamePanel.setVisible(false);
                this.titleContentsPanel.setVisible(false);
                this.endgamePanel.setVisible(true);
                this.endGameView();
                this.parentFrame = (JFrame) this.getTopLevelAncestor();
                this.parentFrame.pack();
                revalidate();
                repaint();

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

        ButtonController buttonController = new ButtonController(this.easy, this.medium, this.hard, this.exit,
                this.model,
                this.nameField, this.restart, this.endGame);

        exit.addActionListener(buttonController);
        easy.addActionListener(buttonController);
        medium.addActionListener(buttonController);
        hard.addActionListener(buttonController);
        restart.addActionListener(buttonController);
        endGame.addActionListener(buttonController);
        statsButton.addActionListener(buttonController);
        endExit.addActionListener(buttonController);
    }

    public void setGridSize(int i) {
        this.gridSize = i;
    }

    public int getGridSize() {
        return this.model.getPlayerGuesses().length;
    }

}
