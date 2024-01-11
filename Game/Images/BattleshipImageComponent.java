package Game.Images;

import javax.swing.*;
import java.awt.*;

public class BattleshipImageComponent extends JComponent {
    
    ImageIcon icon; // Current Icon
    String file;
    
    /**
     * Constructor of the PhotoComponentClass
     * 
     * @param fileName name of the file that stores the image
     */
    public BattleshipImageComponent(String fileName) {
        super();
        
        this.file = fileName;
        icon = new ImageIcon(fileName);

        this.setPreferredSize(new Dimension(icon.getImage().getWidth(null), icon.getImage().getHeight(null)));

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image pic = icon.getImage();
        g.drawImage(pic, 0, 0, null);
    }
}
