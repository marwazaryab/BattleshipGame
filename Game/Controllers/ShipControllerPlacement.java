package Game.Controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import Game.GameObjects.BattleshipGame;

/**
 * ShipControllerPlacement class
 * @authors Abdul Mustafa Mohib and Marwa Zaryab
 * @since 1/23/24
 *        A class used to carry out Battleship gameplay; helps perform player
 *        and computer turns and retrieve game grid data
 */
public class ShipControllerPlacement implements ActionListener {

    //instance variables
    private JButton[][] playerGrid; 
    private JButton[][] computerGrid; 
    private BattleshipGame model;
    private int rowClicked; 
    private int columnClicked; 
    private boolean isHorizontal = false; 
    private String alignment; 
    private JTextField alignmentField;

    /**
     * @authors Mohib and Marwa
     *          Constructor: A method used to set the values of the instance variables
     * @param player    player grid
     * @param computer  computer grid
     * @param data      model
     * @param shipNum   number of ship
     * @param alignment if horizontal or vertical
     */
    public ShipControllerPlacement(JButton[][] player, JButton[][] computer, BattleshipGame data,
            JTextField alignment) {
        this.playerGrid = player;
        this.computerGrid = computer;
        this.model = data;
        this.alignmentField = alignment;
    }

    @Override
    /**
     * @author Mohib and Marwa
     *         A method that is run every time an action is performed within the
     *         game; in this case, when a grid box is pressed on the player or computer grid
     */
    public void actionPerformed(ActionEvent e) {

        //runs the code below if ship deployment is not finished
        if (this.model.getDeploymentStatus() == false) {

            //retrieve the button clicked on the grid
            JButton rawButtonClicked = (JButton) e.getSource();

            //retrive the text from the text field
            this.alignment = alignmentField.getText().toUpperCase();

            //disable computer grid
            for (int x = 0; x < computerGrid.length; x++) {
                for (int y = 0; y < computerGrid[x].length; y++) {
                    computerGrid[x][y].setEnabled(false);
                }
            }

            // Identify the coloumn and row clicked for the button
            for (int row = 0; row < playerGrid.length; row++) {
                for (int col = 0; col < playerGrid[row].length; col++) {
                    if (playerGrid[row][col] == rawButtonClicked) {
                        rowClicked = row;
                        columnClicked = col;
                        break;
                    }
                }
            }

            //check to see what alignment player has chosen and call methods and set variables accordingly
            if (alignment.equalsIgnoreCase("R")) {
                isHorizontal = true;
                this.model.deployPlayerShips(playerGrid, computerGrid, rowClicked, columnClicked, isHorizontal);
            } else if (alignment.equalsIgnoreCase("D")) {
                isHorizontal = false;
                this.model.deployPlayerShips(playerGrid, computerGrid, rowClicked, columnClicked, isHorizontal);
            } else { //invalid input
                alignmentField.selectAll();
            }
            
            //checks to see if all ships have been deployed for player
            if (this.model.getShipNum() == 5) {

                //disable player grid
                for (int x = 0; x < playerGrid.length; x++) {
                    for (int y = 0; y < playerGrid[x].length; y++) {
                        playerGrid[x][y].setEnabled(false);
                    }
                }

                //enable computer grid
                for (int x = 0; x < computerGrid.length; x++) {
                    for (int y = 0; y < computerGrid[x].length; y++) {
                        computerGrid[x][y].setEnabled(true);
                    }
                }
            }
    }

        //runs code below if deployment is finished and it is player's turn
        else if (this.model.getGameTurn().equals("Player")) {

            //retrieve the button clicked on the grid
            JButton buttonGuessed = (JButton) e.getSource();

            //cycle through computer grid to retrieve row and column for button
            for (int row = 0; row < computerGrid.length; row++) {
                for (int col = 0; col < computerGrid[row].length; col++) {
                    if (computerGrid[row][col] == buttonGuessed) {
                        rowClicked = row;
                        columnClicked = col;
                        break;
                    }
                }
            }

            //call the model's player ship turn method to carry out the player's turn
            this.model.playerShipTurn(rowClicked, columnClicked);
        }

        //if it is the computer's turn, run the computer ship turn method
        else {
            this.model.computerShipTurn();
        }
    }
}
