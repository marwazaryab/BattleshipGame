package Game.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import Game.GameObjects.BattleshipGame;

public class ShipController implements ActionListener {
    
    private JButton button;
    private BattleshipGame model;

    public ShipController (JButton b, BattleshipGame data) {
        this.button = b;
        this.model = data;
    }

    public void actionPerformed(ActionEvent e) {

    }

    
}
