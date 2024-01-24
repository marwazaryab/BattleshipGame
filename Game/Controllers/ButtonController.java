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
 * @since 1/23/24
 *        A class used to carry out a set of actions when an action is
 *        performed; in this case when a button is pressed within the game
 */
public class ButtonController implements ActionListener {
    
    //instance variables
    private BattleshipGame model;
    private JTextField field;
    
    /**
     * @author Mohib and Marwa
     *         Constructor; sets the value of the instance variables in this class to the
     *         value of the JComponents within the arguments obtained from the GUI
     * @param easyButton the easy button
     * @param mediumButton the medium button 
     * @param hardButton the hard button 
     * @param exit the exit button
     * @param data the model 
     * @param f the name textfield
     * @param end the end button
     * @param titleScreen the title screen button
     */
    public ButtonController (BattleshipGame data, JTextField f) {
      
        this.field = f;
        this.model = data;
    }

    /**
     * @author Mohib and Marwa
     *         A method that is run everytime an action is performed within the
     *         game; in this case, when one of the buttons in the game is pressed
     * @param ActionEvent e the action event
     */
    public void actionPerformed (ActionEvent e) {

        //retrieve the text from the button clicked
        String buttonText = ((JButton)e.getSource()).getText();

        //switch case statement to evaluate the String on the button
        switch (buttonText) {

            // if the easy, medium, or hard buttons are pressed, create a new grid and set
            // the player name

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

            //if exit is pressed, exit the game and close the program
            case "Exit":
                System.exit(0);
                break;

            //if restart is clicked, call the models restart method to reset values 
            case "Restart":
                this.model.restart();
                break;

            //if the stats button is clicked, output the game stats to the file
            case "Stats":
                this.model.outputStats();
                break;

            // if the end game button is clicked, call the model's end method and return to
            // the title view
            case "End Game":
                this.model.end();
                break;
                
            default: 
                break;
        }


    }
}
