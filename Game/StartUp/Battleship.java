package Game.StartUp;
import javax.swing.*;

import Game.GameObjects.BattleshipGame;
import Game.View.BattleshipGUI;

public class Battleship {
    
    public static void main (String [] args) {

        JFrame frame = new JFrame();
        BattleshipGame model = new BattleshipGame();
        BattleshipGUI view = new BattleshipGUI(model);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(view);
        frame.pack();
        frame.setVisible(true);
    }
}
