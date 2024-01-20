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
    private int [] shipLength;
    private String alignment; 
    private boolean isComputer; 
    private JTextField alignmentField;
    private int shipNum;
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
    public ShipControllerPlacement(JButton[][] player, JButton[][] computer, BattleshipGame data, JLabel shipNum,
            JTextField alignment) {
        this.playerGrid = player;
        this.computerGrid = computer;
        this.model = data;
        // this.shipNum = Integer.parseInt(shipNum.getText());
        this.shipLength = new int[] {5,3,3,2,1};
        this.computerShipLength = new int[] {5,3,3,2,1};
        this.isComputer = false;
        this.alignmentField = alignment;
        this.shipNum = 0;
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


            if (alignment.equalsIgnoreCase("L")) {
                isHorizontal = true;
                this.model.deployPlayerShips(playerGrid, computerGrid, rowClicked, columnClicked, isHorizontal);
                // deployShipsPlayer();
            } else if (alignment.equalsIgnoreCase("U")) {
                isHorizontal = false;
                this.model.deployPlayerShips(playerGrid, computerGrid, rowClicked, columnClicked, isHorizontal);
                // deployShipsPlayer();
            } else {
                alignmentField.selectAll();
            }

            // if(this.model.getShipNum() == 5) {
            //     for (int x = 0; x < playerGrid.length; x++) {
            //         for (int y = 0; y < playerGrid[x].length; y++) {
            //             playerGrid[x][y].setEnabled(false);
            //         }
            //     }

            //     for (int x = 0; x < computerGrid.length; x++) {
            //         for (int y = 0; y < computerGrid[x].length; y++) {
            //             computerGrid[x][y].setEnabled(true);
            //         }
            //     }
            // }
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

        else if (this.model.getGameTurn() == "Computer") {
            
        }

    }

    
    //MOVED ALL METHODS BELOW TO MODEL; REMOVE WHEN GAME FINALIZED

    // public void deployShipsPlayer() {

    //     if (shipNum != 5) {
            
    //         if (this.isHorizontal == true) {

    //             if (this.isValidPlacement(isComputer, rowClicked, columnClicked, isHorizontal, playerGrid)) {

    //                 for (int i = 0; i < shipLength[shipNum]; i++) {
    //                     playerGrid[rowClicked][columnClicked+i].setText("X");
    //                 }
    //                 shipNum++;

    //             } else {
    //                 this.alignmentField.setText("That is not a valid placement!");
    //             }

    //         } else if (this.isHorizontal == false) {

    //             if (this.isValidPlacement(isComputer, rowClicked, columnClicked, isHorizontal, playerGrid)) {

    //                 for (int i = 0; i < shipLength[shipNum]; i++) {

    //                     playerGrid[rowClicked+i][columnClicked].setText("X");
    //                 }
    //                 shipNum++;

    //             } else {
    //                 this.alignmentField.setText("That is not a valid placement!");
    //             }

    //         }

    //     }

    //     else {
    //         System.out.println("Ships deployed!");
    //         deployShipsComputer();
    //     }
        

    // }

    // public void deployShipsComputer() {

    //     this.isComputer = true;

    //     System.out.println(isHorizontal);

    //     while (computerShipNum != 5) {

    //         this.isHorizontal = randomBoolean.nextBoolean();
    //         computerRow = (int) (Math.random() * (computerGrid.length));
    //         computerCol = (int) (Math.random() * (computerGrid[0].length));

    //         if (this.isHorizontal == true) {

    //             if (this.isValidPlacement(isComputer, computerRow, computerCol, isHorizontal, computerGrid)) {

    //                 for (int i = 0; i < computerShipLength[computerShipNum]; i++) {
    //                     computerGrid[computerRow][computerCol+i].setText("X");
    //                 }
    //                 computerShipNum++;

    //             }

    //         } else if (this.isHorizontal == false) {

    //             if (this.isValidPlacement(isComputer, computerRow, computerCol, isHorizontal, computerGrid)) {

    //                 for (int i = 0; i < computerShipLength[computerShipNum]; i++) {

    //                     computerGrid[computerRow+i][computerCol].setText("X");
    //                 }
    //                 computerShipNum++;

    //             } 

    //         }
    //     }

    // }

    // public boolean isValidPlacement(boolean isComp, int row, int col, boolean isHorizontal, JButton[][] grid) {

    //     if (isComp) {

    //         if (isHorizontal == true) {
    //             if (col + computerShipLength[computerShipNum] > grid.length) {
    //                 return false;
    //             }
    //             else if (grid[row][col].getText().equals("X")){
    //                 return false;
    //             }
    //             else {
    //                 for (int x = col; x < (col + computerShipLength[computerShipNum]); x++){
    //                     if (grid[row][x].getText().equals("X")) {
    //                         return false;
    //                     }
    //                 }
    //         }

    //         } else if (isHorizontal == false) {
    //             if (row + computerShipLength[computerShipNum] > grid.length) {
    //                 return false;
    //             }
    //             else if (grid[row][col].getText().equals("X")){
    //                 return false;
    //             }
    //             else {
    //                 for (int x = row; x < (row + computerShipLength[computerShipNum]); x++){
    //                     if (grid[x][col].getText() == "X") {
    //                         return false;
    //                     }
    //             }
    //         }
    //         }

    //         return true;
    //     }

    //     else {

    //         if (isHorizontal == true) {
    //             if (col + shipLength[shipNum] > grid.length) {
    //                 return false;
    //             }
    //             else if (grid[row][col].getText().equals("X")){
    //                 return false;
    //             }
    //             else {
    //                 for (int x = col; x < (col + shipLength[shipNum]); x++){
    //                     if (grid[row][x].getText().equals("X")) {
    //                         return false;
    //                     }
    //                 }
    //         }

    //         } else if (isHorizontal == false) {

    //             if (row + shipLength[shipNum] > grid.length) {
    //                 return false;
    //             }
    //             else if (grid[row][col].getText().equals("X")){
    //                 return false;
    //             }
    //             else {
    //                 for (int x = row; x < (row + shipLength[shipNum]); x++){
    //                     if (grid[x][col].getText() == "X") {
    //                         return false;
    //                     }
    //             }
    //         }
    //     }
    //         return true;

    //     }

    // }  

}
