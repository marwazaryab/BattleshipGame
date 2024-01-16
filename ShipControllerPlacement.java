import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Game.GameObjects.BattleshipGame;

public class ShipControllerPlacement implements ActionListener {

    private JButton[][] playerGrid;
    private JButton[][] computerGrid;
    private BattleshipGame model;
    private int rowClicked;
    private int columnClicked;
    private boolean isHorizontal = true;
    private int shipNum;
    private String alignment;
    private boolean isComputer;
    private JTextField alignmentField;

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
        this.alignment = alignment.getText();
        this.shipNum = Integer.parseInt(shipNum.getText());
        this.isComputer = false;
        this.alignmentField = alignment;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton rawButtonClicked = (JButton) e.getSource();

        // If it is the players turn
        if (!isComputer) {

            // Disable all the computers grids buttons
            for (int x = 0; x < computerGrid.length; x++) {
                for (int y = 0; y < computerGrid[x].length; y++) {
                    computerGrid[x][y].setEnabled(false);
                }
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

        if (alignment.equals("L")) {
            isHorizontal = false;
        } else if (alignment.equals("L") || alignment.equals("U")) {
            alignmentField.setText("Invalid. Try again");
        }

    }

    public void deployShipsPlayer() {

        if (!this.isHorizontal) {

            if (this.isValidPlacement(isComputer, rowClicked, columnClicked, !isHorizontal, playerGrid)) {

                for (int i = 0; i < shipNum; i++) {
                    playerGrid[rowClicked - i][columnClicked].setText("X");
                }

            } else {
                this.alignmentField.setText("Invalid Choice. Try again");
            }

        } else {

            if (this.isValidPlacement(isComputer, rowClicked, columnClicked, isHorizontal, playerGrid)) {

                for (int i = 0; i < shipNum; i++) {

                    playerGrid[rowClicked][columnClicked + i].setText("X");
                }

            } else {
                this.alignmentField.setText("Invalid Choice. Try again");
            }

            deployShipsComputer();

        }

    }

    public void deployShipsComputer() {

        this.isComputer = true;
        boolean isFinished = true;

        int computerRow;
        int computerCol;

        while (!isFinished) {
            computerRow = (int) (Math.random() * (computerGrid.length - 0 + 1) + 0);
            computerCol = (int) (Math.random() * (computerGrid[0].length - 0 + 1) + 0);

            if (this.isValidPlacement(isComputer, computerRow, computerCol, isHorizontal, this.computerGrid)) {

                for (int i = 0; i < shipNum; i++) {

                    if (isHorizontal) {
                        computerGrid[computerRow - i][computerCol].setText("X");

                    } else {
                        computerGrid[computerRow][computerCol + 1].setText("X");
                    }

                }

                isFinished = true;

            }

        }

    }

    public boolean isValidPlacement(boolean isComp, int row, int col, boolean isHorizontal, JButton[][] grid) {

        if (isHorizontal) {
            if (row + shipNum > grid.length) {
                return false;
            }
        } else if (!isHorizontal) {
            if (col + shipNum > grid[0].length) {
                return false;
            }
        }

        return true;

    }

}
