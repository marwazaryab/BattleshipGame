package Game.Controllers;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import Game.GameObjects.BattleshipGame;

/**
 * Button Controller Class
 * @author Marwa Zaryab and Abdul Mustafa Mohib
 */
public class ButtonController implements ActionListener {
    
    private JButton exit;
    private JButton easy;
    private JButton medium;
    private JButton hard;
    private BattleshipGame model;
    private JTextField field;


    public ButtonController (JButton easyButton, JButton mediumButton, JButton hardButton, JButton exit, BattleshipGame data, JTextField f, JButton end, JButton titleScreen) {
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

                if (this.field.getText().length() == 0) {
                    this.model.setPlayerName("Player 1");
                }
                else {
                    this.model.setPlayerName(this.field.getText());
                }
                this.model.createGrid(buttonText);
                break;
        
            case "Medium":
                if (this.field.getText().length() == 0) {
                    this.model.setPlayerName("Player 1");
                }
                else {
                    this.model.setPlayerName(this.field.getText());
                }                
                this.model.createGrid(buttonText);
                break;

            case "Hard":
                if (this.field.getText().length() == 0) {
                    this.model.setPlayerName("Player 1");
                }
                else {
                    this.model.setPlayerName(this.field.getText());
                }                
                this.model.createGrid(buttonText);
                break;

            case "Exit":
                System.exit(0);
                break;

            case "Restart":
                this.model.restart();
                break;

            case "Stats":
                this.model.outputStats();
                break;

            case "End Game":
                this.model.end();
                break;
                
            default: 
                break;
        }


    }
}
