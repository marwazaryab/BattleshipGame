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

    public ShipController (JButton [][] player, JButton[][] computer, BattleshipGame data) {
        this.playerGrid = player;
        this.computerGrid = computer;
        this.model = data;
    }

    public void actionPerformed(ActionEvent e) {

        JButton b = (JButton)e.getSource();

        if (this.model.getGameTurn() == null) {

            for (int x = 0; x < computerGrid.length; x++) {
                for (int y = 0; y < computerGrid[x].length; y++) {
                    computerGrid[x][y].setEnabled(false);
                }
            }

            b.setEnabled(false);
            
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
