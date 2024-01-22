package Game.Controllers;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import Game.GameObjects.BattleshipGame;


public class ButtonController implements ActionListener {
    
    private JButton exit;
    private JButton easy;
    private JButton medium;
    private JButton hard;
    private BattleshipGame model;
    private JTextField field;


    public ButtonController (JButton easyButton, JButton mediumButton, JButton hardButton, JButton exit, BattleshipGame data, JTextField f) {
        this.easy = easyButton;
        this.medium = mediumButton;
        this.hard = hardButton;
        this.field = f;
        this.model = data;
    }

    public void actionPerformed (ActionEvent e) {

        String buttonText = ((JButton)e.getSource()).getText();

        switch (buttonText) {

            case "Easy":
                this.model.setPlayerName(this.field.getText());
                this.model.createGrid(buttonText);
                break;
        
            case "Medium":
                this.model.setPlayerName(this.field.getText());
                this.model.createGrid(buttonText);
                break;

            case "Hard":
                this.model.setPlayerName(this.field.getText());
                this.model.createGrid(buttonText);
                break;

            case "Exit":
                System.exit(0);
                break;

            case "Restart":
            //TODO fill out
                
            default: 
                break;
        }


    }
}
