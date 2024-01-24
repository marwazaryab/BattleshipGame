package Game.StartUp;
import javax.swing.*;
import Game.GameObjects.BattleshipGame;
import Game.View.BattleshipGUI;

/**
 * Battleship Class
 * @authors Marwa Zaryab & Abdul Mustafa Mohib
 * @since 1/23/24
 * A class used to run the game of Battleship
 */
public class Battleship {
    
    /**
     * main method; thread that runs upon program initialization
     * @param args String arguments
     */
    public static void main (String [] args) {

        //declare and initialize variables (frame, model, and view)
        JFrame frame = new JFrame("Battleship");
        BattleshipGame model = new BattleshipGame();
        BattleshipGUI view = new BattleshipGUI(model);
        
        //set frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(view);
        frame.pack();
        frame.setVisible(true);
    }
}
