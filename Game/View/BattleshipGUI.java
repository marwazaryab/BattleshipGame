package Game.View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Game.Controllers.ButtonController;
import Game.Controllers.ShipControllerPlacement;
import Game.GameObjects.BattleshipGame;

/**
 * BattleshipGUI class
 * 
 * @author Abdul Mustafa Mohib & Marwa Zaryab
 * @since 1/23/24
 *        A class that holds all components and methods associated with the view
 *        of the Battleship game; components are updated as
 *        a result of game actions and events
 */
public class BattleshipGUI extends JPanel {

    // ------------------------------------------ INSTANCE VARIABLES ------------------------------------------

    private BattleshipGame model; // the model of the view

    // title view 
    private JPanel titleContentsPanel = new JPanel(); // The overall panel for the title view; holds all components
    private JPanel buttonsPanel = new JPanel(); // The panel that holds all of the title view buttons
    private JPanel bottomPanel = new JPanel(); // The panel that is put into the SOUTH section of the title contents
                                               // panel
    private JPanel namePanel = new JPanel(); // The panel that holds the player name prompt and name textfield
    private JButton exit = new JButton("Exit"); // The button that allows the player to exit
    private JButton easy = new JButton("Easy"); // The button that allows the player to play the easy level
    private JButton medium = new JButton("Medium"); // The button that allows the player to play the medium level
    private JButton hard = new JButton("Hard"); // The button that allows the player to play the hard level
    private JTextField nameField = new JTextField(); // The button that allows the player to enter their name
    private JLabel title = new JLabel(); // The label that holds the image for the title view
    private JLabel userPrompt = new JLabel("Welcome Player 1! Please enter your name: "); // The label that displays
                                                                                          // upon starting the game

    // end game view 
    private JPanel endgamePanel = new JPanel(); // The overall panel for the end game view
    private JPanel endGameButtonsPanel = new JPanel(); // The panel that holds the buttons for the end game view
    private JPanel labelsPanel = new JPanel(); // The panel that holds the labels for the end game view
    private JPanel winnerPanel = new JPanel(); // The panel that holds the winner label
    private JLabel winnerLabel = new JLabel(); // The label that displays the winner of the game
    private JLabel creditsLabel = new JLabel("- MARWA & MOHIB"); // The label for game credits
    private JLabel thankYouLabel = new JLabel("THANK YOU FOR PLAYING! <3"); // The label for game credits
    private JLabel saveStatsLabel = new JLabel("(Click \"Stats\" to save game data to file)"); // The label to prompt
                                                                                               // player to save game
                                                                                               // data
    private JButton endExit = new JButton("Exit"); // The button that allows the player to exit the game
    private JButton statsButton = new JButton("Stats"); // The button that saves the game data to an output file

    // game view 
    private JPanel gamePanel = new JPanel(); // The overall panel that holds all of the game view components
    private JPanel userPanel = new JPanel(); // The panel that holds data for the player and computer
    private JPanel outputPanel = new JPanel(); // The panel that holds data for the SOUTH section of the game panel
    private JPanel leftPanel = new JPanel(); // The panel that holds the left number labels for the player grid and the
                                             // grid
    private JPanel rightPanel = new JPanel(); // The panel that holds the left number labels for the computer grid and
                                              // the grid
    private JPanel leftNumberPanel1 = new JPanel(); // The panel that holds the left number labels for the player grid
    private JPanel leftNumberPanel2 = new JPanel(); // The panel that holds the left number labels for the computer grid
    private JPanel feedbackPanel = new JPanel(); // The panel that holds the output for the game actions (whether a
                                                 // ships has been hit or not / game ended or not)
    private JPanel fullLeftPanel = new JPanel(); // The panel that is placed into the WEST section of the game panel
    private JPanel fullRightPanel = new JPanel(); // The panel that is placed into the EAST section of the game panel
    private JPanel topNumberPanel1 = new JPanel(); // The panel that holds the top number labels for the player grid
    private JPanel topNumberPanel2 = new JPanel(); // The panel that holds the top number labels for the computer grid
    private JPanel alignmentPanel = new JPanel(); // The panel that holds the alignment textfield using for ship
                                                  // directions
    private JPanel timerPanel = new JPanel(); // The panel that holds the timer and restart buttons, as well as the high
                                              // score and hit labels
    private JPanel playerPanel = new JPanel(); // The panel that contains the player's name and their stats
    private JPanel computerPanel = new JPanel(); // The panel that contains the computer's name and their stats
    private JPanel playerStats = new JPanel(); // The panel that contains the player's stats
    private JPanel computerStats = new JPanel(); // The panel that contains the computer's stats
    private JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // The panel that contains both the player
                                                                             // and computer panels and the alignment
                                                                             // panel
    private JPanel playerGridPanel = new JPanel(); // The panel that contains the player grid
    private JPanel computerGridPanel = new JPanel(); // The panel that contains the computer grid
    private JPanel gapPanel = new JPanel(); // An empty gap panel used for formatting
    private JButton endGame = new JButton("End Game"); // A button used to end the game after it is finished
    private JTextField alignment = new JTextField(10); // The textfield for choosing ship alignment
    private JLabel name = new JLabel(); // The label that contains the name of the player
    private JLabel computerName = new JLabel("Computer"); // The name of the computer
    private JLabel currentTurn = new JLabel("Turn: "); // The label that dictates whose turn it is currently
    private JLabel computerShipsSunk = new JLabel("Ships Sunk: "); // The label that displays the number of ships sunk
                                                                   // by computer
    private JLabel playerShipsSunk = new JLabel("Ships Sunk: "); // The label that displays the number of ships sunk by
                                                                 // player
    private JLabel playerGuess = new JLabel("Player Guess: "); // The label that displays the player guess
    private JLabel computerGuess = new JLabel("Computer Guess: "); // the label that displays the computer guess
    private JLabel validateOutput = new JLabel(); // The label that displays whether there was a hit or a miss and
                                                  // whether the game has ended
    private JLabel numPlayerGuesses = new JLabel("Player Guesses: "); // The label that displays the number of player
                                                                      // guesses done
    private JLabel numCompGuesses = new JLabel("Computer Guesses: "); // The label that displays the number of computer
                                                                      // guesses done
    private JLabel playerShipsRemaining = new JLabel("Player Ships Left: "); // The label that displays the number of
                                                                             // player ships remaining
    private JLabel computerShipsRemaining = new JLabel("Computer Ships Left: "); // The label that displays the number
                                                                                 // of computer ships remaining
    private JLabel alignmentLabel = new JLabel("Alignment: Right(R) or Down(D)"); // The label that prompts the user to
                                                                                  // enter the direction for the ship
    private JLabel timer = new JLabel();// The label that holds the timer
    private JLabel missLabel = new JLabel("! - Miss"); // The label that displays feedback for a miss
    private JLabel hitLabel = new JLabel("O - Hit"); // The label that displays feedback for a hit
    private JLabel playerHighScore = new JLabel("Player Guess High Score: "); // The label that displays a player's high
                                                                              // score
    private JButton[][] playerGrid; // The player grid
    private JButton[][] computerGrid; // The computer grid
    private JButton emptyButton = new JButton(); // The button that is used to format the number labels
    private JButton restart = new JButton("Restart"); // The button used to restart the game
    public boolean isRestart = false; // The boolean value that checks whether the button was pressed

    // other variables
    private PANEL_STATES currentState; // The current panel state of the game
    private Timer time; // The timer used to time the game
    private long startTime; // The starting time of the timer
    private int timeElaspedSeconds; // The number of seconds elapsed
    private JFrame parentFrame; // The parent frame of the JPanel used for the view
    private ImageIcon icon = new ImageIcon("title.jpg"); // The image used in the title
    private Color navyBlue = new Color(5, 1, 23); // Navy blu ecolour
    private Color gray = new Color(115, 147, 179); // Gray colour
    private Color lightBlue = new Color(0, 0, 205); // Light blue colour
    private Color darkBlue = new Color(30, 144, 255); // Dark blue colour
    private Font f = new Font("Century Gothic", Font.BOLD, 14); // Font used in game labels

    /**
     * An enum used to identify the current state of the view; used to switch panels
     * depending on game status
     */
    public enum PANEL_STATES {
        TITLE,
        GAME,
        END,
    }

    /**
     *         Default constructor for the GUI; sets the model within the class to
     *         the argument,
     *         registers button controller, and sets the panel state
     * @param model The model for the BattleshipGUI
     */
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

    /**
     * @author Marwa
     *         a method that returns the time in seconds that has been elapsed
     * @return time passed
     */
    public int timePassed() {
        long currentTime = System.currentTimeMillis();
        return timeElaspedSeconds = (int) ((currentTime - startTime) / 1000); // return time passed
    }

    /**
     * @author Marwa
     *         an action listener to retrieve the current time that has passed using
     *         the Timer object
     */
    ActionListener timeListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            timer.setText("Time: " + timePassed());
        }
    };

    /**
     * @author Marwa
     *         a method that sets the current panel state to the argument provided
     * @param state
     */
    public void setPanelState(PANEL_STATES state) {
        this.currentState = state;
    }

    /**
     * @author Marwa
     *         a method that displays the title view for the game; this is the view
     *         displayed upon startup
     */
    public void titleView() {

        // set layouts for panels
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        titleContentsPanel.setLayout(new BorderLayout());

        // set fonts for buttons and labels
        easy.setFont(new Font("Montserrat", Font.BOLD, 70));
        medium.setFont(new Font("Montserrat", Font.BOLD, 70));
        hard.setFont(new Font("Montserrat", Font.BOLD, 70));
        exit.setFont(new Font("Montserrat", Font.BOLD, 70));
        userPrompt.setFont(new Font("Montserrat", Font.BOLD, 30));
        nameField.setFont(new Font("Montserrat", Font.BOLD, 30));

        // set the icon to the image specified; displays image
        title.setIcon(icon);

        // set the background and foreground for all components
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

        // add the buttons to the button panel
        buttonsPanel.add(easy);
        buttonsPanel.add(medium);
        buttonsPanel.add(hard);
        buttonsPanel.add(exit);

        // add the user prompt and text field to the name panel
        namePanel.add(userPrompt);
        namePanel.add(nameField);

        // add the name panel and button panel to the bottom panel
        bottomPanel.add(namePanel);
        bottomPanel.add(buttonsPanel);

        // add all panels into their respective positions within BorderLayout
        titleContentsPanel.add(bottomPanel, BorderLayout.SOUTH);
        titleContentsPanel.add(title, BorderLayout.NORTH);

        // add the main title panel to this JPanel
        this.add(titleContentsPanel);
    }

    /**
     * @author Marwa
     *         a method that displays the end game view for the game; this is the
     *         view displayed once the game has ended
     */
    public void endGameView() {

        this.setPanelState(PANEL_STATES.END); // set the panel states to the end state

        // set layouts for panels
        endgamePanel.setLayout(new BorderLayout());
        endGameButtonsPanel.setLayout(new BoxLayout(endGameButtonsPanel, BoxLayout.X_AXIS));

        // set the background for all panels
        endgamePanel.setBackground(darkBlue);
        labelsPanel.setBackground(darkBlue);

        // set the font for the labels and buttons
        winnerLabel.setFont(new Font("Century Gothic", Font.BOLD, 40));
        creditsLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        thankYouLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        saveStatsLabel.setFont(new Font("Century Gothc", Font.BOLD, 15));
        endExit.setFont(new Font("Century Gothic", Font.BOLD, 43));
        statsButton.setFont(new Font("Century Gothic", Font.BOLD, 43));
        restart.setFont(new Font("Century Gothic", Font.BOLD, 43));

        // set the foreground color for the labels and buttons
        creditsLabel.setForeground(navyBlue);
        thankYouLabel.setForeground(navyBlue);
        saveStatsLabel.setForeground(navyBlue);
        winnerLabel.setForeground(Color.black);
        endExit.setForeground(gray);
        statsButton.setForeground(gray);
        restart.setForeground(gray);

        // set the background for the buttons and the winner panel
        endExit.setBackground(navyBlue);
        statsButton.setBackground(navyBlue);
        restart.setBackground(navyBlue);
        winnerPanel.setBackground(darkBlue);

        // add the buttons to the buttons panel
        endGameButtonsPanel.add(restart);
        endGameButtonsPanel.add(statsButton);
        endGameButtonsPanel.add(endExit);

        // check to see who won the game and display the name accordinly within winner
        // label
        if (this.model.getWinner().equals(this.model.getPlayerName())) {
            winnerLabel.setText(this.model.getPlayerName().toUpperCase() + " WINS!");
        } else {
            winnerLabel.setText("COMPUTER WINS!");
        }

        // add the winner's name to the winner panel
        winnerPanel.add(winnerLabel);

        // add the credits and end game labels to the labels panel
        labelsPanel.add(thankYouLabel);
        labelsPanel.add(creditsLabel);
        labelsPanel.add(saveStatsLabel);

        // add all panels into their respective positions within the BorderLayout
        endgamePanel.add(winnerPanel, BorderLayout.NORTH);
        endgamePanel.add(labelsPanel, BorderLayout.CENTER);
        endgamePanel.add(endGameButtonsPanel, BorderLayout.SOUTH);

        //set preferred size for labels panel
        labelsPanel.setPreferredSize(new Dimension(300,100));

        // add the end game panel to this JPanel
        this.add(endgamePanel);
    }

    /**
     * @author Mohib
     *         a method that displays the game view for the game; this is the view
     *         displayed while the game is being played
     */
    public void gameView() {

        // set the fonts for all labels and buttons within the game view
        restart.setFont(new Font("Century Gothic", Font.BOLD, 40));
        endGame.setFont(new Font("Century Gothic", Font.BOLD, 40));
        name.setFont(new Font("Century Gothic", Font.BOLD, 20));
        validateOutput.setFont(new Font("Century Gothic", Font.BOLD, 18));
        computerName.setFont(new Font("Century Gothic", Font.BOLD, 20));
        computerShipsSunk.setFont(f);
        playerShipsSunk.setFont(f);
        computerGuess.setFont(f);
        playerGuess.setFont(f);
        timer.setFont(f);
        currentTurn.setFont(f);
        alignmentLabel.setFont(f);
        alignment.setFont(f);
        numPlayerGuesses.setFont(f);
        numCompGuesses.setFont(f);
        playerShipsRemaining.setFont(f);
        computerShipsRemaining.setFont(f);
        missLabel.setFont(f);
        hitLabel.setFont(f);
        playerHighScore.setFont(f);
        restart.setFont(f);
        endGame.setFont(f);

        // set the foreground colour for all labels and buttons within the game view
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
        playerHighScore.setForeground(Color.white);
        restart.setForeground(gray);
        endGame.setForeground(gray);
        alignmentLabel.setForeground(Color.WHITE);
        emptyButton.setForeground(Color.white);

        // set the background for all buttons and panels in the game view
        restart.setBackground(navyBlue);
        endGame.setBackground(navyBlue);
        emptyButton.setBackground(Color.black);
        userPanel.setBackground(Color.black);
        statsPanel.setBackground(Color.black);
        timerPanel.setBackground(Color.black);
        feedbackPanel.setBackground(Color.black);
        outputPanel.setBackground(Color.black);
        leftPanel.setBackground(Color.black);
        rightPanel.setBackground(Color.black);
        leftNumberPanel1.setBackground(Color.black);
        leftNumberPanel2.setBackground(Color.black);
        alignmentPanel.setBackground(Color.black);
        topNumberPanel1.setBackground(Color.black);
        gapPanel.setBackground(Color.black);
        playerPanel.setBackground(Color.BLACK);
        playerStats.setBackground(Color.BLACK);
        topNumberPanel2.setBackground(Color.black);
        computerPanel.setBackground(Color.black);
        computerStats.setBackground(Color.black);
        fullLeftPanel.setBackground(Color.black);
        fullLeftPanel.setBackground(Color.black);

        // initialize layouts for all panels
        BorderLayout gameLayout = new BorderLayout();
        BoxLayout playerLayout = new BoxLayout(playerPanel, BoxLayout.Y_AXIS);
        BoxLayout computerLayout = new BoxLayout(computerPanel, BoxLayout.Y_AXIS);
        GridLayout gameGrid = new GridLayout(this.model.getGridSize(), this.model.getGridSize());
        BoxLayout topLayout = new BoxLayout(statsPanel, BoxLayout.Y_AXIS);
        BoxLayout leftNumberLayout1 = new BoxLayout(leftNumberPanel1, BoxLayout.Y_AXIS);
        BoxLayout leftNumberLayout2 = new BoxLayout(leftNumberPanel2, BoxLayout.Y_AXIS);
        BoxLayout bottomLayout = new BoxLayout(outputPanel, BoxLayout.Y_AXIS);
        BoxLayout leftPanelLayout = new BoxLayout(fullLeftPanel, BoxLayout.Y_AXIS);
        BoxLayout rightPanelLayout = new BoxLayout(fullRightPanel, BoxLayout.Y_AXIS);
        GridLayout topNumberLayout1 = new GridLayout(1, this.model.getGridSize() + 2);
        GridLayout topNumberLayout2 = new GridLayout(1, this.model.getGridSize() + 1);

        // set layouts for each panel
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
        playerGridPanel.setLayout(gameGrid);
        computerGridPanel.setLayout(gameGrid);

        // for loop that creates the player grid; uses the grid size of the player
        // guesses array from the model
        for (int x = 0; x < this.model.getGridSize(); x++) {
            for (int y = 0; y < this.model.getGridSize(); y++) {
                playerGrid[x][y] = new JButton();
                playerGrid[x][y].setPreferredSize(new Dimension(30, 30));
                playerGrid[x][y].setBackground(lightBlue);
                playerGrid[x][y].setBorder(BorderFactory.createLineBorder(Color.black));
            }
        }

        // for loop that creates the computer grid; uses the grid size of the player
        // guesses array from the model
        for (int x = 0; x < this.model.getGridSize(); x++) {
            for (int y = 0; y < this.model.getGridSize(); y++) {
                computerGrid[x][y] = new JButton();
                computerGrid[x][y].setPreferredSize(new Dimension(30, 30));
                computerGrid[x][y].setBackground(darkBlue);
                computerGrid[x][y].setBorder(BorderFactory.createLineBorder(Color.black));
            }
        }

        // for loop that displays the player grid; adds the button components to the
        // player grid panel
        for (int x = 0; x < playerGrid.length; x++) {
            for (int y = 0; y < playerGrid[x].length; y++) {
                playerGridPanel.add(playerGrid[x][y]);
            }
        }

        // for loop that displays the computer grid; adds the button components to the
        // computer grid panel
        for (int x = 0; x < computerGrid.length; x++) {
            for (int y = 0; y < computerGrid[x].length; y++) {
                computerGridPanel.add(computerGrid[x][y]);
            }
        }

        // for loop that creates the vertical grid labels for the player grid
        for (int x = 0; x < this.model.getGridSize(); x++) {
            JButton numButton = new JButton(x + "");
            numButton.setForeground(Color.white);
            numButton.setBackground(Color.black);
            numButton.setPreferredSize(new Dimension(30, 35));
            numButton.setBorder(BorderFactory.createLineBorder(Color.black));
            leftNumberPanel1.add(numButton);
        }

        // for loop that creates the vertical grid labels for the computer grid
        for (int x = 0; x < this.model.getGridSize(); x++) {
            JButton numButton = new JButton(x + "");
            numButton.setForeground(Color.white);
            numButton.setBackground(Color.black);
            numButton.setPreferredSize(new Dimension(30, 35));
            numButton.setBorder(BorderFactory.createLineBorder(Color.black));
            leftNumberPanel2.add(numButton);
        }

        // adding a gap panel for formatting purposes
        topNumberPanel1.add(gapPanel);

        // for loop that creates the horizontal grid labels for the player grid
        for (int x = 0; x < this.model.getGridSize(); x++) {
            JButton numButton = new JButton(x + "");
            numButton.setForeground(Color.white);
            numButton.setBackground(Color.black);
            numButton.setPreferredSize(new Dimension(30, 30));
            numButton.setBorder(BorderFactory.createLineBorder(Color.black));
            topNumberPanel1.add(numButton);
        }

        // adding an empty button for formatting purposes
        emptyButton.setPreferredSize(new Dimension(30, 30));
        emptyButton.setBorder(BorderFactory.createLineBorder(Color.black));
        topNumberPanel2.add(emptyButton);

        // for loop that creates the vertical grid labels for the computer grid
        for (int x = 0; x < this.model.getGridSize(); x++) {
            JButton numButton = new JButton(x + "");
            numButton.setForeground(Color.white);
            numButton.setBackground(Color.black);
            numButton.setPreferredSize(new Dimension(30, 30));
            numButton.setBorder(BorderFactory.createLineBorder(Color.black));
            topNumberPanel2.add(numButton);
        }

        // add the player grid and the vertical grid labels to the left panel
        leftPanel.add(leftNumberPanel1);
        leftPanel.add(playerGridPanel);
        leftNumberPanel1.setPreferredSize(new Dimension(16, this.model.getGridSize() * 30));

        // add the computer grid and the vertical grid labels to the right panel
        rightPanel.add(leftNumberPanel2);
        rightPanel.add(computerGridPanel);
        leftNumberPanel2.setPreferredSize(new Dimension(16, this.model.getGridSize() * 30));

        // add the alignment prompt and textfield to the alignment panel
        alignmentPanel.add(alignmentLabel);
        alignmentPanel.add(alignment);

        // add the user panel and alignment panel to the overall NORTH panel
        statsPanel.add(userPanel);
        statsPanel.add(alignmentPanel);

        // add the player and computer panel to the user panel
        userPanel.add(playerPanel);
        userPanel.add(computerPanel);

        // add all the components listed below within the timer panel; includes buttons
        // and labels
        timerPanel.add(playerHighScore);
        timerPanel.add(timer);
        timerPanel.add(restart);
        timerPanel.add(endGame);
        timerPanel.add(missLabel);
        timerPanel.add(hitLabel);

        // add the feedback label to the feedback panel
        feedbackPanel.add(validateOutput);

        // add the feedback panel and the timer panel to the overall SOUTH panel
        outputPanel.add(feedbackPanel);
        outputPanel.add(timerPanel);

        // set the text for the name label to the player's name
        name.setText(this.model.getPlayerName());

        // add the name label and the player stats to the player panel
        playerPanel.setPreferredSize(new Dimension(this.model.getGridSize() * 30, 50));
        playerPanel.add(name);
        playerPanel.add(playerStats);

        // add the number of player guesses and ships remaining to the player stats
        // panel
        playerStats.add(numPlayerGuesses);
        playerStats.add(computerShipsRemaining);

        // add the computer name label and the computer stats to the computer panel
        computerPanel.setPreferredSize(new Dimension(this.model.getGridSize() * 30, 50));
        computerPanel.add(computerName);
        computerPanel.add(computerStats);

        // add the number of computer guesses and computer ships remaining to the
        // computer stats panel
        computerStats.add(numCompGuesses);
        computerStats.add(playerShipsRemaining);

        // add the horizontal grid labels for the player grid and the left panel to the
        // full left panel
        fullLeftPanel.add(topNumberPanel1);
        fullLeftPanel.add(leftPanel);

        // add the horizontal grid labels for the computer grid and the right panel to
        // the full right panel
        fullRightPanel.add(topNumberPanel2);
        fullRightPanel.add(rightPanel);

        // putting all the panels in their respective positions within the BorderLayout
        gamePanel.add(statsPanel, BorderLayout.NORTH);
        gamePanel.add(fullLeftPanel, BorderLayout.WEST);
        gamePanel.add(fullRightPanel, BorderLayout.EAST);
        gamePanel.add(outputPanel, BorderLayout.SOUTH);

        // add the game panel to this JPanel
        this.add(gamePanel);
    }

    /**
     * @author Mohib
     *         a method that updates the display of the JComponents within the view
     *         everytime an action is performed;
     *         it integrates accessor methods from the model to provide accurate
     *         stats
     */
    public void update() {

        // switch-case statement that evalurates the current panel state of the game;
        // used to switch
        // between different panel layouts
        switch (currentState) {

            // if the panel states is set to TITLE
            case TITLE:

                // set the title contents panel to visible and set the other two panels to not
                // visible
                this.gamePanel.setVisible(false);
                this.titleContentsPanel.setVisible(true);
                this.endgamePanel.setVisible(false);
                this.titleView();

                // check to see if the game has restarted; if it has, update the grid sizes and
                // labels accordingly
                if (this.isRestart == true) {
                    topNumberPanel1.removeAll();
                    topNumberPanel2.removeAll();
                    leftNumberPanel1.removeAll();
                    leftNumberPanel2.removeAll();
                    playerGridPanel.removeAll();
                    computerGridPanel.removeAll();

                    // pack frame and revalidate
                    this.parentFrame = (JFrame) this.getTopLevelAncestor();
                    this.parentFrame.pack();
                    revalidate();
                    repaint();
                }
                break;

            // if the panel states is set to GAME
            case GAME:

                //set the game panel to visisble and the end game panel to non-visible
                this.gamePanel.setVisible(true);
                this.endGame.setVisible(false);

                //update the values for the player and computer stats using accessor methods from the model
                this.numPlayerGuesses
                        .setText("Player Guesses: "
                                .concat(Integer.toString(this.model.getNumPlayerGuesses())));
                this.numCompGuesses
                        .setText("Computer Guesses: ".concat(Integer.toString(this.model.getNumCompGuesses())));
                this.playerShipsRemaining
                        .setText("Player Ships Left: "
                                .concat(Integer.toString(this.model.getPlayerRemainingShips())));
                this.computerShipsRemaining
                        .setText("Computer Ships Left: "
                                .concat(Integer.toString(this.model.getComputerRemainingShips())));
                this.playerHighScore.setText("Player Guess High Score: "
                        .concat(Integer.toString(this.model.getPlayerGuessHighScore())));

                //check to see if a new game has been started or not
                if (this.model.getNewGame() == true) {

                    //set the title contents panel to non-visible and display the alignment panel
                    this.titleContentsPanel.setVisible(false);
                    this.alignmentPanel.setVisible(true);

                    //make the player and computer grids using the String 2D dimensions from the model
                    this.playerGrid = new JButton[this.model.getComputerGuesses().length][this.model
                            .getComputerGuesses().length];
                    this.computerGrid = new JButton[this.model.getComputerGuesses().length][this.model
                            .getComputerGuesses().length];

                    //call the gameView method and register the ship controllers used for gameplay
                    this.gameView();
                    this.registerShipController();

                    //output greeting message
                    this.validateOutput.setText("Welcome " + this.model.getPlayerName() +
                            "! Please start by choosing an alignment for ship " + (this.model.getShipNum() + 1)
                            + " and then placing it on the left grid");

                    // pack frame and revalidate
                    this.parentFrame = (JFrame) this.getTopLevelAncestor();
                    this.parentFrame.pack();
                }

                //if ship deployment is not finished run the code below
                else if (this.model.getDeploymentStatus() == false) {

                    //check to see if the position deployed is valid or not and display the respective message
                    if (this.model.getValidPosition() == true) {
                        this.validateOutput.setText("Please place ship " + (this.model.getShipNum() + 1));
                    }
                    else if (this.model.getValidPosition() == false) {
                        this.validateOutput.setText("That is not a valid position! Please reselect a position");
                    }

                    //if all ships have been deployed output the deployment message to the user
                    if (this.model.getShipNum() == 5) {
                        this.validateOutput.setText(
                                "Player ships deployed! Click once on computer grid to deploy computer ships.");
                    }
                }

                //if it is not a new game and deployment is also done, start the game play 
                else {

                    //set the alignment panel to non-visible and resize frame
                    this.alignmentPanel.setVisible(false);
                    this.parentFrame.pack();

                    //check to see if the game has ended or not
                    if (this.model.getGameStatus() == false) {

                        //check to see whose turn it is
                        if (this.model.getGameTurn() == "Player") {

                            //if it is the very first play of the game, prompt the user to do their turn 
                            if (this.model.getNumPlayerGuesses() == 0) {
                                this.validateOutput.setText(this.model.getPlayerName() + "'s turn: ");
                            } else {

                                //runs the code below if a player successfully hits a ship
                                if (this.model.getPlayerHitStatus() == true) {
                                    this.validateOutput.setText(this.model.getPlayerName() + "'s turn: "
                                            + this.model.getPlayerName() + " guessed ("
                                            + this.model.getPlayerRowGuessed() + ", " + this.model.getPlayerColGuessed()
                                            + ") and hit a ship! Click the grid for computer's turn!");

                                    //checks to see if the player has sunk a ship
                                    if (this.model.getCompShipSunk() == true) {
                                        this.validateOutput.setText(this.model.getPlayerName() + "'s turn: "
                                                + this.model.getPlayerName() + " guessed ("
                                                + this.model.getPlayerRowGuessed() + ", "
                                                + this.model.getPlayerColGuessed()
                                                + ") and sunk a ship! Click the grid for computer's turn!");
                                    }

                                    //sets the position clicked by the player to "O" to display a hit
                                    this.computerGrid[this.model.getPlayerRowGuessed()][this.model
                                            .getPlayerColGuessed()].setText("O");
                                } 
                                
                                //runs the code below if a player misses a ship; displays the respective symbol on the grid
                                else {
                                    this.validateOutput.setText(this.model.getPlayerName() + "'s turn: "
                                            + this.model.getPlayerName() + " guessed ("
                                            + this.model.getPlayerRowGuessed() + ", " + this.model.getPlayerColGuessed()
                                            + ") and missed! Click the grid for computer's turn!");
                                    this.computerGrid[this.model.getPlayerRowGuessed()][this.model
                                            .getPlayerColGuessed()].setText("!");
                                }
                            }
                        }

                        //if it is the computer's turn for the game
                        if (this.model.getGameTurn() == "Computer") {

                            //runs the code below if the computer hits a ship
                            if (this.model.getCompHitStatus() == true) {
                                this.validateOutput.setText("Computer's turn: the computer guessed ("
                                        + this.model.getCompRowGuessed() + ", " + this.model.getCompColGuessed()
                                        + ") and hit a ship! Please do your turn!");

                                //checks to see if the computer has sunk a ship
                                if (this.model.getPlayerShipSunk() == true) {
                                    this.validateOutput.setText("Computer's turn: the computer guessed ("
                                            + this.model.getCompRowGuessed() + ", " + this.model.getCompColGuessed()
                                            + ") and sunk a ship! Please do your turn!");
                                }

                                //sets the position clicked by the computer to "O" to display a hit
                                this.playerGrid[this.model.getCompRowGuessed()][this.model
                                        .getCompColGuessed()].setText("O");
                            } 
                            
                            //runs the code below if the computer misses a ship; displays the respective symbol on the grid
                            else {
                                this.validateOutput.setText("Computer's turn: the computer guessed ("
                                        + this.model.getCompRowGuessed() + ", " + this.model.getCompColGuessed()
                                        + ") and missed! Please do your turn!");
                                this.playerGrid[this.model.getCompRowGuessed()][this.model
                                        .getCompColGuessed()].setText("!");
                            }
                        }
                    }

                    //runs the code below if the game has ended and someone has won
                    else {

                        //set the end game panel to visible and set the restart button to non-visible
                        endGame.setVisible(true);
                        restart.setVisible(false);

                        //call the getWinner() method to retrieve the winner and display the winner
                        this.validateOutput.setText("Game Ended! The winner is " + this.model.getWinner());
                        this.model.disableGrid(computerGrid);
                    }
                }
                break;

            // if the panel states is set to END
            case END:

                //set the game panel and title contents panel to non-visible and set the end game panel to visible
                this.gamePanel.setVisible(false);
                this.titleContentsPanel.setVisible(false);
                this.endgamePanel.setVisible(true);
                this.restart.setVisible(true);

                //call the end game view and resize frame
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

    /**
     * @author Marwa
     * a method that registers the ship controller; adds action listeners to the player and computer grids
     */
    public void registerShipController() {

        //create the instance of the ship controller
        ShipControllerPlacement shipController = new ShipControllerPlacement(playerGrid, computerGrid, model,
                alignment);

        // for loop to add action listeners to player grid buttons
        for (int row = 0; row < playerGrid.length; row++) {
            for (int col = 0; col < playerGrid[0].length; col++) {
                playerGrid[row][col].addActionListener(shipController);
            }
        }

        // for loop to add action listeners to computer grid buttons
        for (int row = 0; row < playerGrid.length; row++) {
            for (int col = 0; col < playerGrid[0].length; col++) {
                computerGrid[row][col].addActionListener(shipController);
            }
        }
    }

     /**
     * @author Mohib
     * a method that registers the button controller; adds action listeners to buttons and textfields that 
     * are used throughout the game
     */
    public void registerButtonController() {

        //create the instance of the button controller
        ButtonController buttonController = new ButtonController(this.model, this.nameField);

        //add action listeners to the respective buttons used within the button controller
        exit.addActionListener(buttonController);
        easy.addActionListener(buttonController);
        medium.addActionListener(buttonController);
        hard.addActionListener(buttonController);
        restart.addActionListener(buttonController);
        endGame.addActionListener(buttonController);
        statsButton.addActionListener(buttonController);
        endExit.addActionListener(buttonController);
    }
}
