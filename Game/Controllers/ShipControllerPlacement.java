package Game.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Game.GameObjects.BattleshipGame;
import java.util.*;

public class ShipControllerPlacement implements ActionListener {

    private JButton[][] playerGrid; 
    private JButton[][] computerGrid; 
    private BattleshipGame model;
    private int rowClicked; 
    private int columnClicked; 
    private boolean isHorizontal = false; 
    private String alignment; 
    private JTextField alignmentField;
    private Random randomBoolean;

    int computerRow;
    int computerCol;
    int computerShipNum = 0;
    int [] computerShipLength;;

    /**
     * Constructor
     * 
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
        this.computerShipLength = new int[] {5,3,3,2,1};
        this.alignmentField = alignment;
        this.randomBoolean = new Random();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (this.model.getDeploymentStatus() == false) {

            JButton rawButtonClicked = (JButton) e.getSource();
            this.alignment = alignmentField.getText().toUpperCase();

            for (int x = 0; x < computerGrid.length; x++) {
                for (int y = 0; y < computerGrid[x].length; y++) {
                    computerGrid[x][y].setEnabled(false);
                }
            }

            // Identify the coloumn and row
            for (int row = 0; row < playerGrid.length; row++) {

                for (int col = 0; col < playerGrid[row].length; col++) {

                    if (playerGrid[row][col] == rawButtonClicked) {
                        rowClicked = row;
                        columnClicked = col;
                        break;
                    }
                }
            }


            if (alignment.equalsIgnoreCase("R")) {
                isHorizontal = true;
                this.model.deployPlayerShips(playerGrid, computerGrid, rowClicked, columnClicked, isHorizontal);
                // deployShipsPlayer();
            } else if (alignment.equalsIgnoreCase("D")) {
                isHorizontal = false;
                this.model.deployPlayerShips(playerGrid, computerGrid, rowClicked, columnClicked, isHorizontal);
                // deployShipsPlayer();
            } else {
                alignmentField.selectAll();
            }

            if (this.model.getShipNum() == 5) {
                for (int x = 0; x < playerGrid.length; x++) {
                    for (int y = 0; y < playerGrid[x].length; y++) {
                        playerGrid[x][y].setEnabled(false);
                    }
                }

                for (int x = 0; x < computerGrid.length; x++) {
                    for (int y = 0; y < computerGrid[x].length; y++) {
                        computerGrid[x][y].setEnabled(true);
                    }
                }
            }
    }

        else if (this.model.getGameTurn() == "Player") {

            JButton buttonGuessed = (JButton) e.getSource();

            for (int row = 0; row < computerGrid.length; row++) {

                for (int col = 0; col < computerGrid[row].length; col++) {

                    if (computerGrid[row][col] == buttonGuessed) {
                        rowClicked = row;
                        columnClicked = col;
                        break;
                    }
                }
            }

            this.model.playerShipTurn(rowClicked, columnClicked);

        }

        else {
            this.model.computerShipTurn();
        }

    }

}
