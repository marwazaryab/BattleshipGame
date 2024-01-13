package Game.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import Game.GameObjects.BattleshipGame;
import java.awt.*;

public class ShipController implements ActionListener {
    
    private JButton [][] playerGrid;
    private JButton [][] computerGrid;
    private BattleshipGame model;
    private int rowClicked;
    private int columnClicked;
    private int shipNumber = 0;;
    private int ships = 0;
    private int buttonsPressed = 0;
    private int currentShipLength = 0;
    private int[]rowButtonsClicked;
    private int[]columnButtonsClicked;

    public ShipController (JButton [][] player, JButton[][] computer, BattleshipGame data) {
        this.playerGrid = player;
        this.computerGrid = computer;
        rowButtonsClicked = new int[playerGrid.length*playerGrid.length];
        columnButtonsClicked = new int[playerGrid.length*playerGrid.length]; 
        this.model = data;
    }

    public void actionPerformed(ActionEvent e) {

        JButton b = (JButton)e.getSource();
        buttonsPressed++;

        if (this.model.getGameTurn() == null) {

            if (buttonsPressed == 1) {
                b.setEnabled(false);
                b.setText("O");

                for (int x = 0; x < computerGrid.length; x++) {
                    for (int y = 0; y < computerGrid[x].length; y++) {
                        computerGrid[x][y].setEnabled(false);
                    }
                }
                
            }

            for (int x = 0; x < playerGrid.length; x++) {
                for (int y = 0; y < playerGrid[x].length; y++) {
                    if (playerGrid[x][y] == b) {
                        rowClicked = x;
                        columnClicked = y;

                        if (buttonsPressed == 1) {
                            rowButtonsClicked[buttonsPressed] = rowClicked;
                            columnButtonsClicked[buttonsPressed] = columnClicked;
                        }

                        break;
                    }
                }
            }

            System.out.println(rowClicked);
            System.out.println(columnClicked);

            if (buttonsPressed > 1) {
                if (rowClicked == rowButtonsClicked[buttonsPressed-1]-1 || rowClicked == rowButtonsClicked[buttonsPressed-1]+1){
                    rowButtonsClicked[buttonsPressed] = rowClicked;
                }
                if (columnClicked == columnButtonsClicked[buttonsPressed-1]-1 || columnClicked == columnButtonsClicked[buttonsPressed-1]+1) {
                    columnButtonsClicked[buttonsPressed] = columnClicked;
                    System.out.println("This is when the if runs " + columnButtonsClicked[buttonsPressed]);
                }

                if (ships < 10) {
                    if (currentShipLength < this.model.getShips()[shipNumber]) {
                        // if (rowClicked == rowButtonsClicked[buttonsPressed-1]-1 || rowClicked == rowButtonsClicked[buttonsPressed-1]+1) {
                            b.setEnabled(false);
                            b.setText("O");
                            currentShipLength++;
                        } 
                    // }
                    else {
                        ships++;
                        shipNumber++;
                        currentShipLength = 0;
                    }
                }//end of if
            
                else {
                    System.out.println(this.model.getShips()[currentShipLength]);
                }
            }
    }
        else {

            b.setEnabled(false);
            b.setFont(new Font("Century Gothic", Font.BOLD, 20));
            b.setForeground(Color.BLACK);
            b.setText("X");
            
    
            for (int x = 0; x < computerGrid.length; x++) {
                for (int y = 0; y < computerGrid[x].length; y++) {
                    if (computerGrid[x][y] == b) {
                        rowClicked = x;
                        columnClicked = y;
                        break;
                    }
                }
            }
    
            System.out.println(rowClicked + " " + columnClicked);
        }
    }

    }

