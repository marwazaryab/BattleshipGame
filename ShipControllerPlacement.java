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
        this.alignment = alignment.getText(); // TODO add try catch
        this.shipNum = Integer.parseInt(shipNum.getText());
        this.isComputer = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (!isComputer) {
            for (int x = 0; x < computerGrid.length; x++) {
                for (int y = 0; y < computerGrid[x].length; y++) {
                    computerGrid[x][y].setEnabled(false);
                }
            }
        }

        JButton rawButtonClicked = (JButton) e.getSource();

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
        }

    }

    public void deployShipsPlayer() {
        if (isHorizontal) {

            for (int i = 0; i < shipNum; i++) {

            }

        }

        this.isComputer = true;

    }

    public void deployShipsComputer() {

        int computerRow;
        int computerCol;

        computerRow = (int) (Math.random() * (computerGrid.length - 0 + 1) + 0);
        computerCol = (int) (Math.random() * (computerGrid[0].length - 0 + 1) + 0);

        if (this.isValid(true, computerRow, computerCol, isHorizontal, this.computerGrid)) {

            computerGrid[computerRow][computerCol].setText("X");
        }

    }

    public boolean isValid(boolean isComp, int row, int col, boolean isHorizontal, JButton[][] grid) {
        return false;

    }

    public void generateRowShip() {
        System.out.println("making a row ship");
    }

    public void generateColumnShip() {
        System.out.println("making a column ship");

    }
}
